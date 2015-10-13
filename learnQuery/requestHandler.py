import sys
import getopt


def processRequest ():
  f = open("harness2.sk", "w")
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
  Tuple [MAX_TUPLES] tuples;
  for (int i = 0; i < MAX_TUPLES; ++i)
  {
    tuples[i] = new Tuple(ints = {col0[i], col1[i], col2[i], col3[i], col4[i]});
  }
 
  Schema schema = new Schema(numInts = 5, intColIds = {0, 1, 2, 3, 4});
  Relation in = new Relation(sz = MAX_TUPLES, tuples = tuples, schema = schema);

  Relation out = project(select(in));

  if (in.tuples[0].ints[2] == 2)
    assert out.tuples[0].ints[2] == in.tuples[0].ints[2];
  else if (in.tuples[0].ints[2] >= 2) 
    assert out.sz == 0 || out.tuples[0].ints[2] != in.tuples[0].ints[2];

  assert(out.schema.numInts == 4);
  assert out.schema.intColIds[0] == 0;
 
}
  """
  )
  f.close()

import socket
HOST = ""
PORT = 8080

def startServer ():
  s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
  s.bind((HOST, PORT))
  s.listen(1)
  conn, addr = s.accept()
  print 'Connected by', addr
  while 1:
      data = conn.recv(1024)
      if not data: break
      #conn.send(data)
      print 'Received:', repr(data)
      conn.send("hello world")
      break

  conn.close()


class Usage(Exception):
    def __init__(self, msg):
        self.msg = msg

def main(argv=None):
    if argv is None:
        argv = sys.argv

    try:
        try:
            opts, args = getopt.getopt(argv[1:], "h", ["help"])
        except getopt.error, msg:
             raise Usage(msg)
        # more code, unchanged
        
        #processRequest();
        startServer();

    except Usage, err:
        print >>sys.stderr, err.msg
        print >>sys.stderr, "for help use --help"
        return 2

    return 0

if __name__ == "__main__":
    sys.exit(main())
