import json
import os
import re
import sqlite3
import subprocess
import urllib2

def processRequest (tableData, numCols):
  harnessFilename = generateHarness(tableData, numCols)
  sketchOutputFilename = callSketch(harnessFilename)
  if sketchOutputFilename is not None:
    (query, columnNames) = parseSketchOutput(sketchOutputFilename)
    queryResults = executeQuery(query)
    return json.dumps({"query": query, "columnNames": columnNames, "results": queryResults})
  else:
    return json.dumps({"query": "Cannot find query", "columnNames": [], "results": []}) 



def callSketch (filename):
  print "calling sketch on", filename

  SketchBin = "/Users/clwang/Research/DBQueries/sketch-1.6.7/sketch-frontend/sketch"#PATH TO sketch-frontend/sketch# 
  SketchOutfile = "sketch.out"

  sketchOut = open(SketchOutfile, "w")
  r = subprocess.call([SketchBin, "--bnd-inbits=6", filename], stdout=sketchOut, stderr=sketchOut)
  sketchOut.close()

  if r == 0:
    print "sketch finished!"
  else:
    print "sketch terminated with errors"
    SketchOutfile = None

  return SketchOutfile


def parseSketchOutput (filename):
  f = open(filename, "r")
  selectPredicates = []
  projectCols = []

  for line in f:
    m1 = re.search("predicate = 1 && ([^;]+);", line)    
    m2 = re.search("predicate = \(1 && ([^&]+)&& 1;", line)    
    m3 = re.search("predicate = \(predicate && ([^&]+)&& 1;", line)
    m4 = re.search("predicate = predicate && ([^;]+);", line)
    
    projCol = re.search("outSchema.intColIds\[(\d+)\] = (\d+);", line)

    if m1:
      selectPredicates.append(m1.group(1))
    elif m2:
      selectPredicates.append(m2.group(1))
    elif m3:
      selectPredicates.append(m3.group(1))
    elif m4:
      selectPredicates.append(m4.group(1))

    elif projCol:
      projectCols.append((projCol.group(1), projCol.group(2)))
         
  #if len(selectPredicates) == 0:
  #  raise Exception("Cannot find selection predicate")

  f.close()
 
  # process selection predicates  
  selectPredicates = [p.replace("(", "").replace(")", "").strip() for p in selectPredicates]
  print "select predicates:", selectPredicates
  
  convertedPredicates = []
  for pred in selectPredicates:
    splitted = pred.split()
    colName = "c" + re.search("tuple.ints\[(\d+)\]", splitted[0]).group(1)    
    convertedPredicates.append(colName + " " + splitted[1] + " " + splitted[2])
  
  print "converted predicates:", convertedPredicates

  whereClause = ""
  if len(convertedPredicates) > 0:
    whereClause = "WHERE " + " AND ".join(convertedPredicates)
  

  # process project predicates
  print "projected cols", projectCols
  convertedCols = []
  projectCols = sorted(projectCols)
  for i in xrange(len(projectCols)):
    t = projectCols[i]
    if t[0] != str(i):
      raise Exception("Columns not sorted" + repr(projectCols))
    
    convertedCols.append("c" + t[1])
 
  selectClause = ""
  if len(convertedCols) == 0:
    selectClause = "SELECT *"
  else:
    selectClause = "SELECT " + ", ".join(convertedCols)

  #query = selectClause + "<br>FROM table<br>" + whereClause
  query = selectClause + " FROM sample " + whereClause
  print "final query:", query
  return (query, convertedCols)


def generateHarness (tableData, actualNumCols):
  assertions = []
  numExistentialVar = 0

  numRows = 4
  numCols = 5

  i = 0 # row
  j = 0 # column

  for e in tableData:
    
    if e != "":
      e = urllib2.unquote(e)
      print "coord:", i, j, "for:", e
      
      univForm = re.search("(\w+)\s*([=|>|<])\s*(\d+)", e)
      existentialForm = re.search("(\d+)", e)
      projForm = re.search("(\w+)\s*=\s*col\((\w+)\)", e)
      colCompForm = re.search("(\w+)([=|>|<])(\w+)", e)

      if univForm:
        op = univForm.group(2)
        if op == "=":
          op = "=="
        
        assertion = "  assert out.sz > 0;\n" + \
                    "  for (int i = 0; i < out.sz; ++i) { assert out.tuples[i].ints[" + str(j) + "] " + \
                          op + " " + univForm.group(3) + "; }\n"          
        assertions.append(assertion)
        print "got op form:", univForm.group(1), "2:", univForm.group(2), "3:", univForm.group(3)
      
      elif existentialForm:
        varName = "existVar" + str(numExistentialVar)
        assertion = "  bit " + varName + " = false;\n" + \
                    "  for (int i = 0; i < out.sz; ++i) {\n" + \
                    "    if (out.tuples[i].ints[" + str(j) + "] == " + existentialForm.group(1) + ")\n" + \
                    "      " + varName + " = true;\n" + \
                    "  }\n" + \
                    "  assert " + varName + ";\n"

        assertions.append(assertion)
        numExistentialVar += 1
        print "got existential form:", existentialForm.group(1)
     
      elif colCompForm:
        op = colCompForm.group(2)
        if op == "=":
          op = "=="
        colNum = colCompForm.group(3)
        # XXXX hack for now
        if colNum == "a":
          colNum = "0"
        elif colNum == "b":
          colNum = "1"
        elif colNum == "c":
          colNum = "2"
        elif colNum == "d":
          colNum = "3"
        elif colNum == "e":
          colNum = "4"

        assertion = "  assert out.sz > 0;\n" + \
                    "  for (int i = 0; i < out.sz; ++i) {\n" + \
                    "    assert out.tuples[i].ints[" + str(j) + "] " + op + " out.tuples[i].ints[" + colNum + "];\n" + \
                    "  }\n"
        assertions.append(assertion)
        print "got col comp form:", colNum
     
      elif projForm:
        colNum = projForm.group(1)
        # XXXX hack for now
        if colNum == "c0":
          colNum = "0"
        elif colNum == "c1":
          colNum = "1"
        elif colNum == "c2":
          colNum = "2"
        elif colNum == "c3":
          colNum = "3"
        elif colNum == "c4":
          colNum = "4"

        assertions.append("  assert out.schema.intColIds[" + str(j) + "] == " + colNum + ";")

      else:
        raise Exception("Unknown constraint: " + e)

      """
      if e.startswith("@"): 
        colNum = re.search("@col\((\d+)\)", e).group(1)
        print "col s:", colNum
        assertions.append("assert out.schema.intColIds[" + str(j) + "] == " + colNum + ";")

      else: # a cell specific constraint
        assertions.append("assert out.tuples[" + str(i) + "].ints[" + str(j) + "] == " + e + ";") 
      """

    if j == numCols - 1:
      j = 0
      i += 1
    else:
      j += 1

  print "assertions:", assertions
      

  harnessFilename = "harness.sk"
  f = open(harnessFilename, "w")
  f.write(\
  """
include \"dbOps.sk\";\n

int MAX_COLS = 5;
int MAX_TUPLES = 4;

struct Tuple
{
  int [MAX_COLS] ints;
}

harness void main (int [MAX_TUPLES] col0, int [MAX_TUPLES] col1, int [MAX_TUPLES] col2, 
                   int [MAX_TUPLES] col3, int [MAX_TUPLES] col4)
{
  col0 = {0, 0, 0, 0};
  col1 = {1, 2, 3, 4};
  col2 = {2, 4, 6, 8};
  col3 = {1, 3, 5, 7};
  col4 = {10, 20, 30, 40};


  Tuple [MAX_TUPLES] tuples;
  for (int i = 0; i < MAX_TUPLES; ++i)
  {
    tuples[i] = new Tuple(ints = {col0[i], col1[i], col2[i], col3[i], col4[i]});
  }
 
  Schema schema = new Schema(numInts = 5, intColIds = {0, 1, 2, 3, 4});
  Relation in = new Relation(sz = MAX_TUPLES, tuples = tuples, schema = schema);

  Relation out = project(select(in));

"""
  )
  f.write("  " + "\n".join(assertions) + "\n")
  f.write("  assert out.schema.numInts == " + str(actualNumCols) + ";\n")
  f.write(\
  """ 
}

  """
  )
  f.close()

  return harnessFilename


def executeQuery (query):
  conn = sqlite3.connect("sample.db")
  c = conn.cursor()
  results = []
  for r in c.execute(query):
    results.append(r)
  conn.close()
  return results


if __name__ == "__main__":
  #processRequest([])
  parseSketchOutput("test.out")


