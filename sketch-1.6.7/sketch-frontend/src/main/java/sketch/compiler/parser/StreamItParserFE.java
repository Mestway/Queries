// $ANTLR 2.7.7 (20060906): "StreamItParserFE.g" -> "StreamItParserFE.java"$

package sketch.compiler.parser;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;

import sketch.compiler.Directive;
import sketch.compiler.ast.core.FEContext;
import sketch.compiler.ast.core.FieldDecl;
import sketch.compiler.ast.core.Function;
import sketch.compiler.ast.core.Parameter;
import sketch.compiler.ast.core.Program;
import sketch.compiler.ast.core.Annotation;
import sketch.compiler.ast.core.NameResolver;
import sketch.util.datastructures.HashmapList;
import sketch.compiler.ast.core.exprs.ExprStar.Kind;

import sketch.compiler.ast.core.Package;


import sketch.compiler.ast.core.exprs.*;
import sketch.compiler.ast.core.exprs.regens.*;
import sketch.compiler.ast.core.stmts.*;
import sketch.compiler.ast.core.typs.*;
import sketch.compiler.ast.cuda.exprs.*;
import sketch.compiler.ast.cuda.stmts.*;
import sketch.compiler.ast.cuda.typs.*;

import sketch.compiler.ast.promela.stmts.StmtFork;
import sketch.compiler.main.cmdline.SketchOptions;

import sketch.compiler.ast.spmd.stmts.StmtSpmdfork;

import static sketch.util.DebugOut.assertFalse;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
@SuppressWarnings("deprecation")
public class StreamItParserFE extends antlr.LLkParser       implements StreamItParserFETokenTypes
 {

	private Set<String> processedIncludes=new HashSet<String> ();
    private Set<Directive> directives = new HashSet<Directive> ();
    private boolean preprocess;
    private List<String> cppDefs;
    private String currPkg;
    private FEContext curPkgCx;    
    private FEContext lastCx=null;
    private String lastFilename = null;
    private String shortFilename = null;
    
     //ADT
    private List<String> parentStructNames = new ArrayList<String>();

	public StreamItParserFE(StreamItLex lexer, Set<String> includes,
                            boolean preprocess, List<String> cppDefs)
	{
		this(lexer);
		processedIncludes = includes;
        this.preprocess = preprocess;
        this.cppDefs = cppDefs;
	}

	public static void main(String[] args)
	{
		try
		{
			DataInputStream dis = new DataInputStream(System.in);
			StreamItLex lexer = new StreamItLex(dis);
			StreamItParserFE parser = new StreamItParserFE(lexer);
			parser.program();
		}
		catch (Exception e)
		{
			e.printStackTrace(System.err);
		}
	}

	public FEContext getContext(Token t)
	{
		int line = t.getLine();
		if (line == 0) line = -1;
		int col = -1;
		String filename = getFilename();
		
		
		if(lastCx != null){
			if(line == lastCx.getLineNumber() && lastFilename != null && lastFilename.equals(filename)){
				return lastCx;
			}
		}
		
		if(lastFilename == null || !lastFilename.equals(filename)){
			lastFilename = filename;
			String lfile = filename;
			if(lfile.length() > 15){
	    		int ls=lfile.lastIndexOf("/");
	    		{
	        		int lb=lfile.lastIndexOf("\\");
	        		if(ls<0 || lb>ls) ls=lb;
	    		}
	    		if(ls>=0) lfile = lfile.substring(ls+1);
	            if (lfile.length() > 17) {
	                lfile =
	                        lfile.substring(0, 7) + ".." +
	                                lfile.substring(lfile.length() - 9);
	        	}
	    	}
	    	shortFilename = lfile;	
		}		
		lastCx = new FEContext(shortFilename, line, col);	
		return lastCx;
		
		// int col = t.getColumn();
		// if (col == 0) col = -1;		
	}

	private boolean hasError = false;

	public void reportError(RecognitionException ex)
	{
		hasError = true;
		super.reportError(ex);
	}

	public void reportError(String s)
	{
		hasError = true;
		super.reportError(s);
	}

    public void handleInclude(String name, List<Package> namespace)
    {
        try {
            List<String> incList = Arrays.asList(
                    SketchOptions.getSingleton().feOpts.inc);
        	Iterator<String> lit = null;
        	if(incList != null){ lit = incList.iterator(); }
        	File f = new File (name);
        	String errMsg = "";
        	while(!f.canRead()){
        		if(lit != null && lit.hasNext()){
        			errMsg += "\n\t" +  f.getCanonicalPath();
        			String tmp = lit.next(); 
        			File tmpf = new File(tmp);
        			String dir = tmpf.getCanonicalPath();
        			f = new File (dir, name);	
        		}else{
        			errMsg += "\n\t" + f.getCanonicalPath();
        			throw new IllegalArgumentException ("File not found: "+ name + "\n" + 
        					"Searched the following paths:" + errMsg + "\n");
        		}
        	}
            name = f.getCanonicalPath ();
        } catch (IOException ioe) {
            throw new IllegalArgumentException ("File not found: "+ name);
        }
        if (processedIncludes.contains(name))
            return;
        processedIncludes.add(name);
        StreamItParser parser =
            new StreamItParser (name, processedIncludes, preprocess, cppDefs);
        Program p = parser.parse ();
		assert p != null;		
		
		namespace.addAll(p.getPackages());		
        directives.addAll (parser.getDirectives ());
    }

    private void handlePragma (String pragma, String args) {
        directives.add (Directive.make (pragma, args));
    }

    public Set<Directive> getDirectives () {  return directives;  }


protected StreamItParserFE(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public StreamItParserFE(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected StreamItParserFE(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public StreamItParserFE(TokenStream lexer) {
  this(lexer,2);
}

public StreamItParserFE(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
}

	public final Program  program() throws RecognitionException, TokenStreamException {
		Program p;
		
		Token  id = null;
		Package pk;
		p = null; List vars = new ArrayList();  
			List<Function> funcs=new ArrayList(); Function f;
			List<Package> namespaces = new ArrayList<Package>();
		FieldDecl fd; StructDef ts; List<StructDef> structs = new ArrayList<StructDef>();
		List<StructDef> adtList;
		
		String file = null;
		String pkgName = null;
		FEContext pkgCtxt = null;
		
		
		try {      // for error handling
			{
			_loop10:
			do {
				switch ( LA(1)) {
				case TK_struct:
				{
					ts=struct_decl();
					if ( inputState.guessing==0 ) {
						structs.add(ts);
					}
					break;
				}
				case TK_adt:
				{
					adtList=adt_decl();
					if ( inputState.guessing==0 ) {
						structs.addAll(adtList);
					}
					break;
				}
				case TK_include:
				{
					file=include_stmt();
					if ( inputState.guessing==0 ) {
						handleInclude (file, namespaces);
					}
					break;
				}
				case TK_package:
				{
					match(TK_package);
					id = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						currPkg = (id.getText()); curPkgCx = getContext(id);
					}
					{
					switch ( LA(1)) {
					case SEMI:
					{
						match(SEMI);
						if ( inputState.guessing==0 ) {
							pkgName = currPkg;  pkgCtxt = getContext(id);
						}
						break;
					}
					case LCURLY:
					{
						pk=pkgbody();
						if ( inputState.guessing==0 ) {
							namespaces.add(pk);
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case TK_pragma:
				{
					pragma_stmt();
					break;
				}
				default:
					boolean synPredMatched6 = false;
					if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))))) {
						int _m6 = mark();
						synPredMatched6 = true;
						inputState.guessing++;
						try {
							{
							annotation_list();
							{
							_loop5:
							do {
								switch ( LA(1)) {
								case TK_serial:
								{
									match(TK_serial);
									break;
								}
								case TK_harness:
								{
									match(TK_harness);
									break;
								}
								case TK_generator:
								{
									match(TK_generator);
									break;
								}
								case TK_stencil:
								{
									match(TK_stencil);
									break;
								}
								case TK_model:
								{
									match(TK_model);
									break;
								}
								default:
								{
									break _loop5;
								}
								}
							} while (true);
							}
							return_type();
							match(ID);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched6 = false;
						}
						rewind(_m6);
inputState.guessing--;
					}
					if ( synPredMatched6 ) {
						f=function_decl();
						if ( inputState.guessing==0 ) {
							funcs.add(f);
						}
					}
					else {
						boolean synPredMatched8 = false;
						if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))))) {
							int _m8 = mark();
							synPredMatched8 = true;
							inputState.guessing++;
							try {
								{
								return_type();
								match(ID);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched8 = false;
							}
							rewind(_m8);
inputState.guessing--;
						}
						if ( synPredMatched8 ) {
							f=function_decl();
							if ( inputState.guessing==0 ) {
								funcs.add(f);
							}
						}
						else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2)))) {
							fd=field_decl();
							match(SEMI);
							if ( inputState.guessing==0 ) {
								vars.add(fd);
							}
						}
					else {
						break _loop10;
					}
					}}
				} while (true);
				}
				match(Token.EOF_TYPE);
				if ( inputState.guessing==0 ) {
					
								if(pkgName == null){
									pkgName="ANONYMOUS";
								}
								for(StructDef struct : structs){
									if(parentStructNames.contains(struct.getName())) struct.setIsInstantiable(false);
									struct.setPkg(pkgName);	
								}
								for(Function fun : funcs){
									fun.setPkg(pkgName);	
								}
								
							        Package ss=new Package(pkgCtxt, pkgName, structs, vars, funcs);
									namespaces.add(ss);
					if (!hasError) {
					if (p == null) {
					p = Program.emptyProgram();
					}
					p =
					p.creator().streams(namespaces).create();
					}
					
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_4);
				} else {
				  throw ex;
				}
			}
			return p;
		}
		
	public final HashmapList<String, Annotation>   annotation_list() throws RecognitionException, TokenStreamException {
		HashmapList<String, Annotation>  amap;
		
		
			 amap=null; Annotation an;
		
		try {      // for error handling
			{
			_loop77:
			do {
				if ((LA(1)==AT)) {
					an=annotation();
					if ( inputState.guessing==0 ) {
						if(amap==null){amap = new HashmapList<String, Annotation>();} amap.append(an.tag, an);
					}
				}
				else {
					break _loop77;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_5);
			} else {
			  throw ex;
			}
		}
		return amap;
	}
	
	public final Type  return_type() throws RecognitionException, TokenStreamException {
		Type t;
		
		t=null;
		
		try {      // for error handling
			t=data_type();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_6);
			} else {
			  throw ex;
			}
		}
		return t;
	}
	
	public final Function  function_decl() throws RecognitionException, TokenStreamException {
		Function f;
		
		Token  id = null;
		Token  impl = null;
		
		Type rt;
		List l;
		StmtBlock s;
		HashmapList<String, Annotation> amap;
		f = null;
		boolean isHarness = false;
		boolean isLibrary = false;
		boolean isPrintfcn = false;
		boolean isGenerator = false;
		boolean isDevice = false;
		boolean isGlobal = false;
		boolean isSerial = false;
		boolean isStencil = false;
		boolean isModel = false;
		
		
		try {      // for error handling
			amap=annotation_list();
			{
			_loop80:
			do {
				switch ( LA(1)) {
				case TK_serial:
				{
					match(TK_serial);
					if ( inputState.guessing==0 ) {
						isSerial = true;
					}
					break;
				}
				case TK_harness:
				{
					match(TK_harness);
					if ( inputState.guessing==0 ) {
						isHarness = true;
					}
					break;
				}
				case TK_generator:
				{
					match(TK_generator);
					if ( inputState.guessing==0 ) {
						isGenerator = true;
					}
					break;
				}
				case TK_stencil:
				{
					match(TK_stencil);
					if ( inputState.guessing==0 ) {
						isStencil = true;
					}
					break;
				}
				case TK_model:
				{
					match(TK_model);
					if ( inputState.guessing==0 ) {
						isModel = true;
					}
					break;
				}
				default:
				{
					break _loop80;
				}
				}
			} while (true);
			}
			rt=return_type();
			id = LT(1);
			match(ID);
			l=param_decl_list();
			{
			switch ( LA(1)) {
			case TK_implements:
			{
				match(TK_implements);
				impl = LT(1);
				match(ID);
				break;
			}
			case LCURLY:
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case LCURLY:
			{
				s=block();
				if ( inputState.guessing==0 ) {
					
					assert !(isGenerator && isHarness) : "The generator and harness keywords cannot be used together";
					Function.FunctionCreator fc = Function.creator(getContext(id), id.getText(), Function.FcnType.Static).returnType(
					rt).params(l).body(s).annotations(amap);
					
					// function type
					if (isGenerator) {
					fc = fc.type(Function.FcnType.Generator);
					} else if (isHarness) {
					assert impl == null : "harness functions cannot have implements";
					fc = fc.type(Function.FcnType.Harness);
					} else if (isModel) {
					assert impl == null : "A model can not implement another model or a concrete function";
					fc = fc.type(Function.FcnType.Model);
					} else if (impl != null) {
					fc = fc.spec(impl.getText());
					}
					
					// library type
					if (isLibrary) {
					fc = fc.libraryType(Function.LibraryFcnType.Library);
					}
					
					// print function
					if (isPrintfcn) {
					fc = fc.printType(Function.PrintFcnType.Printfcn);
					}
					
					// cuda type annotations
					if ((isDevice && isGlobal) || (isGlobal && isSerial) || (isDevice && isSerial)) {
					assertFalse("Only one of \"global\", \"device\", or \"serial\" qualifiers is allowed.");
					}
					if (isDevice) {
					fc = fc.cudaType(Function.CudaFcnType.DeviceInline);
					} else if (isGlobal) {
					fc = fc.cudaType(Function.CudaFcnType.Global);
					} else if (isSerial) {
					fc = fc.cudaType(Function.CudaFcnType.Serial);
					}
					
					// stencil type
					if (isStencil) {
					fc = fc.solveType(Function.FcnSolveType.Stencil);
					}
					
					f = fc.create();
						
				}
				break;
			}
			case SEMI:
			{
				match(SEMI);
				if ( inputState.guessing==0 ) {
					f = Function.creator(getContext(id), id.getText(), Function.FcnType.Uninterp).returnType(rt).params(l).annotations(amap).create();
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		return f;
	}
	
	public final FieldDecl  field_decl() throws RecognitionException, TokenStreamException {
		FieldDecl f;
		
		Token  id = null;
		Token  id2 = null;
		f = null; Type t; Expression x = null;
			List ts = new ArrayList(); List ns = new ArrayList();
			List xs = new ArrayList(); FEContext ctx = null;
		
		try {      // for error handling
			t=data_type();
			id = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case ASSIGN:
			{
				match(ASSIGN);
				x=var_initializer();
				break;
			}
			case SEMI:
			case COMMA:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				ctx = getContext(id); ts.add(t); ns.add(id.getText()); xs.add(x);
			}
			{
			_loop27:
			do {
				if ((LA(1)==COMMA)) {
					if ( inputState.guessing==0 ) {
						x = null;
					}
					match(COMMA);
					id2 = LT(1);
					match(ID);
					{
					switch ( LA(1)) {
					case ASSIGN:
					{
						match(ASSIGN);
						x=var_initializer();
						break;
					}
					case SEMI:
					case COMMA:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					if ( inputState.guessing==0 ) {
						ts.add(t); ns.add(id2.getText()); xs.add(x);
					}
				}
				else {
					break _loop27;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				f = new FieldDecl(ctx, ts, ns, xs);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return f;
	}
	
	public final StructDef  struct_decl() throws RecognitionException, TokenStreamException {
		StructDef ts;
		
		Token  t = null;
		Token  id = null;
		Token  parent = null;
		ts = null; Parameter p; List names = new ArrayList();
			Annotation an=null;
			HashmapList<String, Annotation> annotations = new HashmapList<String, Annotation>();
			List types = new ArrayList();
		
		try {      // for error handling
			t = LT(1);
			match(TK_struct);
			id = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case TK_extends:
			{
				match(TK_extends);
				parent = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					
								parentStructNames.add(parent.getText());
							
				}
				break;
			}
			case LCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LCURLY);
			{
			_loop251:
			do {
				switch ( LA(1)) {
				case TK_boolean:
				case TK_float:
				case TK_bit:
				case TK_int:
				case TK_void:
				case TK_double:
				case TK_fun:
				case TK_char:
				case TK_ref:
				case TK_global:
				case BITWISE_OR:
				case ID:
				{
					p=param_decl();
					match(SEMI);
					if ( inputState.guessing==0 ) {
						names.add(p.getName()); types.add(p.getType());
					}
					break;
				}
				case AT:
				{
					an=annotation();
					if ( inputState.guessing==0 ) {
						annotations.append(an.tag, an);
					}
					break;
				}
				default:
				{
					break _loop251;
				}
				}
			} while (true);
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				
							if(parent != null) {
								ts = StructDef.creator(getContext(t), id.getText(),parent.getText(), true, names, types, annotations).create();
							}else{
								ts = StructDef.creator(getContext(t), id.getText(),null, true, names, types, annotations).create();
							}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_9);
			} else {
			  throw ex;
			}
		}
		return ts;
	}
	
	public final List<StructDef>  adt_decl() throws RecognitionException, TokenStreamException {
		List<StructDef> adtList;
		
		Token  t = null;
		Token  id = null;
		adtList = new ArrayList<StructDef>(); List<StructDef> innerList; 
		StructDef str = null; Parameter p; List names = new ArrayList();
		Annotation an = null; StructDef innerStruct;
		HashmapList<String, Annotation> annotations = new HashmapList<String, Annotation>();
		List types = new ArrayList();
		
		try {      // for error handling
			t = LT(1);
			match(TK_adt);
			id = LT(1);
			match(ID);
			match(LCURLY);
			{
			_loop244:
			do {
				switch ( LA(1)) {
				case TK_adt:
				{
					innerList=adt_decl();
					if ( inputState.guessing==0 ) {
						innerStruct = innerList.get(0); innerStruct.setParentName(id.getText());
							adtList.addAll(innerList);
					}
					break;
				}
				case AT:
				{
					an=annotation();
					if ( inputState.guessing==0 ) {
						annotations.append(an.tag, an);
					}
					break;
				}
				default:
					if ((LA(1)==ID) && (LA(2)==LCURLY)) {
						innerStruct=structInsideADT_decl();
						if ( inputState.guessing==0 ) {
							innerStruct.setParentName(id.getText()); adtList.add(innerStruct);
						}
					}
					else if ((_tokenSet_10.member(LA(1))) && (_tokenSet_11.member(LA(2)))) {
						p=param_decl();
						match(SEMI);
						if ( inputState.guessing==0 ) {
							names.add(p.getName()); types.add(p.getType());
						}
					}
				else {
					break _loop244;
				}
				}
			} while (true);
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				str = StructDef.creator(getContext(t), id.getText(), null, false, names, types, annotations).create();
				str.setImmutable();
					 adtList.add(0, str);
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_12);
			} else {
			  throw ex;
			}
		}
		return adtList;
	}
	
	public final String  include_stmt() throws RecognitionException, TokenStreamException {
		String f;
		
		Token  fn = null;
		f = null;
		
		try {      // for error handling
			match(TK_include);
			fn = LT(1);
			match(STRING_LITERAL);
			match(SEMI);
			if ( inputState.guessing==0 ) {
				f = fn.getText ();  f = f.substring (1, f.length () - 1);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
		return f;
	}
	
	public final Package  pkgbody() throws RecognitionException, TokenStreamException {
		Package pk;
		
		
		pk = null;
		FieldDecl fd; 
			List vars = new ArrayList();  
			List<Function> funcs=new ArrayList(); Function f;
			parentStructNames = new ArrayList<String>();
			StructDef ts; List<StructDef> structs = new ArrayList<StructDef>();
			List<StructDef> adtList;
			FEContext pkgCtxt = null;
		
		
		try {      // for error handling
			match(LCURLY);
			{
			_loop19:
			do {
				switch ( LA(1)) {
				case TK_struct:
				{
					ts=struct_decl();
					if ( inputState.guessing==0 ) {
						structs.add(ts);
					}
					break;
				}
				case TK_adt:
				{
					adtList=adt_decl();
					if ( inputState.guessing==0 ) {
						structs.addAll(adtList);
					}
					break;
				}
				default:
					boolean synPredMatched16 = false;
					if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))))) {
						int _m16 = mark();
						synPredMatched16 = true;
						inputState.guessing++;
						try {
							{
							annotation_list();
							{
							_loop15:
							do {
								switch ( LA(1)) {
								case TK_serial:
								{
									match(TK_serial);
									break;
								}
								case TK_harness:
								{
									match(TK_harness);
									break;
								}
								case TK_generator:
								{
									match(TK_generator);
									break;
								}
								case TK_stencil:
								{
									match(TK_stencil);
									break;
								}
								case TK_model:
								{
									match(TK_model);
									break;
								}
								default:
								{
									break _loop15;
								}
								}
							} while (true);
							}
							return_type();
							match(ID);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched16 = false;
						}
						rewind(_m16);
inputState.guessing--;
					}
					if ( synPredMatched16 ) {
						f=function_decl();
						if ( inputState.guessing==0 ) {
							funcs.add(f);
						}
					}
					else {
						boolean synPredMatched18 = false;
						if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))))) {
							int _m18 = mark();
							synPredMatched18 = true;
							inputState.guessing++;
							try {
								{
								return_type();
								match(ID);
								match(LPAREN);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched18 = false;
							}
							rewind(_m18);
inputState.guessing--;
						}
						if ( synPredMatched18 ) {
							f=function_decl();
							if ( inputState.guessing==0 ) {
								funcs.add(f);
							}
						}
						else if ((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2)))) {
							fd=field_decl();
							match(SEMI);
							if ( inputState.guessing==0 ) {
								vars.add(fd);
							}
						}
					else {
						break _loop19;
					}
					}}
				} while (true);
				}
				match(RCURLY);
				if ( inputState.guessing==0 ) {
								
							for(StructDef struct : structs){
								if(parentStructNames.contains(struct.getName())) struct.setIsInstantiable(false);
								struct.setPkg(currPkg);	
							}
							for(Function fun : funcs){
								fun.setPkg(currPkg);	
							}
							
						    pk=new Package(pkgCtxt, currPkg, structs, vars, funcs);
					
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_13);
				} else {
				  throw ex;
				}
			}
			return pk;
		}
		
	public final void pragma_stmt() throws RecognitionException, TokenStreamException {
		
		Token  p = null;
		Token  a = null;
		String args = "";
		
		try {      // for error handling
			match(TK_pragma);
			p = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case STRING_LITERAL:
			{
				a = LT(1);
				match(STRING_LITERAL);
				if ( inputState.guessing==0 ) {
					args = a.getText ().substring (1, a.getText ().length ()-1);
				}
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			if ( inputState.guessing==0 ) {
				handlePragma (p.getText (), args);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_13);
			} else {
			  throw ex;
			}
		}
	}
	
	public final Type  data_type() throws RecognitionException, TokenStreamException {
		Type t;
		
		Token  prefix = null;
		Token  id = null;
		Token  prefix2 = null;
		Token  id2 = null;
		Token  l = null;
		Token  n = null;
		Token  num = null;
		t = null; Vector<Expression> params = new Vector<Expression>(); Vector<Integer> maxlens = new Vector<Integer>(); int maxlen = 0; Expression x; boolean isglobal = false;
		
		try {      // for error handling
			switch ( LA(1)) {
			case TK_boolean:
			case TK_float:
			case TK_bit:
			case TK_int:
			case TK_double:
			case TK_fun:
			case TK_char:
			case TK_global:
			case BITWISE_OR:
			case ID:
			{
				{
				switch ( LA(1)) {
				case TK_global:
				{
					match(TK_global);
					if ( inputState.guessing==0 ) {
						isglobal = true;
					}
					break;
				}
				case TK_boolean:
				case TK_float:
				case TK_bit:
				case TK_int:
				case TK_double:
				case TK_fun:
				case TK_char:
				case BITWISE_OR:
				case ID:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case TK_boolean:
				case TK_float:
				case TK_bit:
				case TK_int:
				case TK_double:
				case TK_fun:
				case TK_char:
				{
					t=primitive_type();
					break;
				}
				case ID:
				{
					{
					if ((LA(1)==ID) && (LA(2)==AT)) {
						prefix = LT(1);
						match(ID);
						match(AT);
					}
					else if ((LA(1)==ID) && (_tokenSet_14.member(LA(2)))) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					id = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						t = new TypeStructRef(prefix != null ? (prefix.getText() + "@" + id.getText() )  : id.getText(), false);
					}
					break;
				}
				case BITWISE_OR:
				{
					match(BITWISE_OR);
					{
					if ((LA(1)==ID) && (LA(2)==AT)) {
						prefix2 = LT(1);
						match(ID);
						match(AT);
					}
					else if ((LA(1)==ID) && (LA(2)==BITWISE_OR)) {
					}
					else {
						throw new NoViableAltException(LT(1), getFilename());
					}
					
					}
					id2 = LT(1);
					match(ID);
					match(BITWISE_OR);
					if ( inputState.guessing==0 ) {
						t = new TypeStructRef(prefix2 != null ? (prefix2.getText() + "@" + id2.getText() )  : id2.getText(), true);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				_loop65:
				do {
					if ((LA(1)==LSQUARE)) {
						l = LT(1);
						match(LSQUARE);
						{
						{
						switch ( LA(1)) {
						case TK_new:
						case TK_null:
						case TK_true:
						case TK_false:
						case LPAREN:
						case LCURLY:
						case INCREMENT:
						case MINUS:
						case DECREMENT:
						case BITWISE_OR:
						case BANG:
						case NDVAL:
						case NDVAL2:
						case NDANGELIC:
						case REGEN:
						case CHAR_LITERAL:
						case STRING_LITERAL:
						case HQUAN:
						case NUMBER:
						case ID:
						{
							if ( inputState.guessing==0 ) {
								maxlen = 0;
							}
							{
							x=expr_named_param();
							{
							switch ( LA(1)) {
							case LESS_COLON:
							{
								match(LESS_COLON);
								n = LT(1);
								match(NUMBER);
								if ( inputState.guessing==0 ) {
									maxlen = Integer.parseInt(n.getText());
								}
								break;
							}
							case RSQUARE:
							case COMMA:
							{
								break;
							}
							default:
							{
								throw new NoViableAltException(LT(1), getFilename());
							}
							}
							}
							if ( inputState.guessing==0 ) {
								params.add(x); maxlens.add(maxlen);
							}
							}
							break;
						}
						case RSQUARE:
						case COMMA:
						{
							if ( inputState.guessing==0 ) {
								throw new SemanticException("missing array bounds in type declaration", getFilename(), l.getLine());
							}
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						{
						_loop64:
						do {
							if ((LA(1)==COMMA)) {
								if ( inputState.guessing==0 ) {
									maxlen = 0;
								}
								match(COMMA);
								x=expr_named_param();
								{
								switch ( LA(1)) {
								case LESS_COLON:
								{
									match(LESS_COLON);
									num = LT(1);
									match(NUMBER);
									if ( inputState.guessing==0 ) {
										maxlen = Integer.parseInt(num.getText());
									}
									break;
								}
								case RSQUARE:
								case COMMA:
								{
									break;
								}
								default:
								{
									throw new NoViableAltException(LT(1), getFilename());
								}
								}
								}
								if ( inputState.guessing==0 ) {
									params.add(x); maxlens.add(maxlen);
								}
							}
							else {
								break _loop64;
							}
							
						} while (true);
						}
						}
						match(RSQUARE);
						if ( inputState.guessing==0 ) {
							
							while (!params.isEmpty()) {
							t = new TypeArray(t, params.lastElement(), maxlens.lastElement());
							params.remove(params.size() - 1);
							maxlens.remove(maxlens.size() - 1);
							}
							
						}
					}
					else {
						break _loop65;
					}
					
				} while (true);
				}
				if ( inputState.guessing==0 ) {
					
					if (isglobal) { t = t.withMemType(CudaMemoryType.GLOBAL); } 
					
				}
				break;
			}
			case TK_void:
			{
				match(TK_void);
				if ( inputState.guessing==0 ) {
					t =  TypePrimitive.voidtype;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_15);
			} else {
			  throw ex;
			}
		}
		return t;
	}
	
	public final Expression  var_initializer() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null;
		
		try {      // for error handling
			boolean synPredMatched163 = false;
			if (((LA(1)==LCURLY) && (_tokenSet_16.member(LA(2))))) {
				int _m163 = mark();
				synPredMatched163 = true;
				inputState.guessing++;
				try {
					{
					arr_initializer();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched163 = false;
				}
				rewind(_m163);
inputState.guessing--;
			}
			if ( synPredMatched163 ) {
				x=arr_initializer();
			}
			else if ((_tokenSet_17.member(LA(1))) && (_tokenSet_18.member(LA(2)))) {
				x=right_expr();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_19);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Statement  statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  tb = null;
		Token  tc = null;
		Token  t = null;
		s = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case TK_loop:
			case TK_repeat:
			{
				s=loop_statement();
				break;
			}
			case TK_minrepeat:
			{
				s=minrepeat_statement();
				break;
			}
			case TK_fork:
			{
				s=fork_statement();
				break;
			}
			case TK_spmdfork:
			{
				s=spmdfork_statement();
				break;
			}
			case TK_parfor:
			{
				s=parfor_statement();
				break;
			}
			case TK_insert:
			{
				s=insert_block();
				break;
			}
			case TK_reorder:
			{
				s=reorder_block();
				break;
			}
			case TK_atomic:
			{
				s=atomic_block();
				break;
			}
			case TK_break:
			{
				tb = LT(1);
				match(TK_break);
				match(SEMI);
				if ( inputState.guessing==0 ) {
					s = new StmtBreak(getContext(tb));
				}
				break;
			}
			case TK_continue:
			{
				tc = LT(1);
				match(TK_continue);
				match(SEMI);
				if ( inputState.guessing==0 ) {
					s = new StmtContinue(getContext(tc));
				}
				break;
			}
			case TK_switch:
			{
				s=switch_statement();
				break;
			}
			case TK_if:
			{
				s=if_else_statement();
				break;
			}
			case TK_while:
			{
				s=while_statement();
				break;
			}
			case TK_do:
			{
				s=do_while_statement();
				match(SEMI);
				break;
			}
			case TK_for:
			{
				s=for_statement();
				break;
			}
			case TK_assume:
			{
				s=assume_statement();
				match(SEMI);
				break;
			}
			case TK_assert:
			case TK_h_assert:
			{
				s=assert_statement();
				match(SEMI);
				break;
			}
			case TK_assert_max:
			{
				s=assert_max_statement();
				match(SEMI);
				break;
			}
			case TK_return:
			{
				s=return_statement();
				match(SEMI);
				break;
			}
			case SEMI:
			{
				t = LT(1);
				match(SEMI);
				if ( inputState.guessing==0 ) {
					s=new StmtEmpty(getContext(t));
				}
				break;
			}
			default:
				boolean synPredMatched30 = false;
				if (((LA(1)==LCURLY) && (_tokenSet_20.member(LA(2))))) {
					int _m30 = mark();
					synPredMatched30 = true;
					inputState.guessing++;
					try {
						{
						match(LCURLY);
						}
					}
					catch (RecognitionException pe) {
						synPredMatched30 = false;
					}
					rewind(_m30);
inputState.guessing--;
				}
				if ( synPredMatched30 ) {
					s=block();
				}
				else {
					boolean synPredMatched32 = false;
					if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))))) {
						int _m32 = mark();
						synPredMatched32 = true;
						inputState.guessing++;
						try {
							{
							return_type();
							match(ID);
							match(LPAREN);
							}
						}
						catch (RecognitionException pe) {
							synPredMatched32 = false;
						}
						rewind(_m32);
inputState.guessing--;
					}
					if ( synPredMatched32 ) {
						s=fdecl_statement();
					}
					else {
						boolean synPredMatched34 = false;
						if (((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2))))) {
							int _m34 = mark();
							synPredMatched34 = true;
							inputState.guessing++;
							try {
								{
								data_type();
								match(ID);
								}
							}
							catch (RecognitionException pe) {
								synPredMatched34 = false;
							}
							rewind(_m34);
inputState.guessing--;
						}
						if ( synPredMatched34 ) {
							s=variable_decl();
							match(SEMI);
						}
						else {
							boolean synPredMatched36 = false;
							if (((_tokenSet_21.member(LA(1))) && (_tokenSet_22.member(LA(2))))) {
								int _m36 = mark();
								synPredMatched36 = true;
								inputState.guessing++;
								try {
									{
									expr_statement();
									}
								}
								catch (RecognitionException pe) {
									synPredMatched36 = false;
								}
								rewind(_m36);
inputState.guessing--;
							}
							if ( synPredMatched36 ) {
								s=expr_statement();
								match(SEMI);
							}
							else {
								boolean synPredMatched40 = false;
								if (((_tokenSet_0.member(LA(1))) && (_tokenSet_1.member(LA(2))))) {
									int _m40 = mark();
									synPredMatched40 = true;
									inputState.guessing++;
									try {
										{
										annotation_list();
										{
										_loop39:
										do {
											switch ( LA(1)) {
											case TK_device:
											{
												match(TK_device);
												break;
											}
											case TK_serial:
											{
												match(TK_serial);
												break;
											}
											case TK_harness:
											{
												match(TK_harness);
												break;
											}
											case TK_generator:
											{
												match(TK_generator);
												break;
											}
											case TK_library:
											{
												match(TK_library);
												break;
											}
											case TK_printfcn:
											{
												match(TK_printfcn);
												break;
											}
											case TK_stencil:
											{
												match(TK_stencil);
												break;
											}
											case TK_model:
											{
												match(TK_model);
												break;
											}
											default:
											{
												break _loop39;
											}
											}
										} while (true);
										}
										return_type();
										match(ID);
										match(LPAREN);
										}
									}
									catch (RecognitionException pe) {
										synPredMatched40 = false;
									}
									rewind(_m40);
inputState.guessing--;
								}
								if ( synPredMatched40 ) {
									s=fdecl_statement();
								}
							else {
								throw new NoViableAltException(LT(1), getFilename());
							}
							}}}}}
						}
						catch (RecognitionException ex) {
							if (inputState.guessing==0) {
								reportError(ex);
								recover(ex,_tokenSet_23);
							} else {
							  throw ex;
							}
						}
						return s;
					}
					
	public final Statement  loop_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t1 = null;
		Token  t2 = null;
		s = null; Expression exp; Statement b; Token x=null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case TK_loop:
			{
				t1 = LT(1);
				match(TK_loop);
				if ( inputState.guessing==0 ) {
					x=t1;
				}
				break;
			}
			case TK_repeat:
			{
				t2 = LT(1);
				match(TK_repeat);
				if ( inputState.guessing==0 ) {
					x=t2;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LPAREN);
			exp=right_expr();
			match(RPAREN);
			b=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtLoop(getContext(x), exp, b);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  minrepeat_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t1 = null;
		s = null; Statement b; Token x=null;
		
		try {      // for error handling
			{
			t1 = LT(1);
			match(TK_minrepeat);
			if ( inputState.guessing==0 ) {
				x=t1;
			}
			}
			b=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtMinLoop(getContext(x), b);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  fork_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t = null;
		s = null; Statement ivar; Expression exp; Statement b;
		
		try {      // for error handling
			t = LT(1);
			match(TK_fork);
			match(LPAREN);
			ivar=variable_decl();
			match(SEMI);
			exp=right_expr();
			match(RPAREN);
			b=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtFork(getContext(t), (StmtVarDecl) ivar, exp, b);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  spmdfork_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t = null;
		Token  v = null;
		s = null; String ivar=null; Expression exp; Statement b;
		
		try {      // for error handling
			t = LT(1);
			match(TK_spmdfork);
			match(LPAREN);
			{
			if ((LA(1)==ID) && (LA(2)==SEMI)) {
				v = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					ivar=v.getText();
				}
				match(SEMI);
			}
			else if ((_tokenSet_17.member(LA(1))) && (_tokenSet_24.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			exp=right_expr();
			match(RPAREN);
			b=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtSpmdfork(getContext(t), ivar, exp, b);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  parfor_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t = null;
		s = null; Expression ivar; Expression exp; Statement b;
		
		try {      // for error handling
			t = LT(1);
			match(TK_parfor);
			match(LPAREN);
			ivar=var_expr();
			match(LARROW);
			exp=range_exp();
			match(RPAREN);
			b=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtParfor(getContext(t), ivar, exp, b);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final StmtInsertBlock  insert_block() throws RecognitionException, TokenStreamException {
		StmtInsertBlock ib;
		
		Token  t = null;
		ib=null; Statement s; List<Statement> insert = new ArrayList<Statement> (), into = new ArrayList<Statement> ();
		
		try {      // for error handling
			t = LT(1);
			match(TK_insert);
			match(LCURLY);
			{
			_loop99:
			do {
				if ((_tokenSet_25.member(LA(1)))) {
					s=statement();
					if ( inputState.guessing==0 ) {
						insert.add(s);
					}
				}
				else {
					break _loop99;
				}
				
			} while (true);
			}
			match(RCURLY);
			match(TK_into);
			match(LCURLY);
			{
			_loop101:
			do {
				if ((_tokenSet_25.member(LA(1)))) {
					s=statement();
					if ( inputState.guessing==0 ) {
						into.add(s);
					}
				}
				else {
					break _loop101;
				}
				
			} while (true);
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				ib = new StmtInsertBlock(getContext(t), insert, into);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return ib;
	}
	
	public final StmtReorderBlock  reorder_block() throws RecognitionException, TokenStreamException {
		StmtReorderBlock sb;
		
		Token  t = null;
		sb=null; Statement s; List l = new ArrayList();
		
		try {      // for error handling
			match(TK_reorder);
			t = LT(1);
			match(LCURLY);
			{
			_loop104:
			do {
				if ((_tokenSet_25.member(LA(1)))) {
					s=statement();
					if ( inputState.guessing==0 ) {
						l.add(s);
					}
				}
				else {
					break _loop104;
				}
				
			} while (true);
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				sb = new StmtReorderBlock(getContext(t), l);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return sb;
	}
	
	public final StmtAtomicBlock  atomic_block() throws RecognitionException, TokenStreamException {
		StmtAtomicBlock ab;
		
		Token  t = null;
		ab=null; Expression c = null; StmtBlock b = null;
		
		try {      // for error handling
			t = LT(1);
			match(TK_atomic);
			{
			switch ( LA(1)) {
			case LPAREN:
			{
				match(LPAREN);
				c=right_expr();
				match(RPAREN);
				break;
			}
			case LCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			b=block();
			if ( inputState.guessing==0 ) {
				ab = new StmtAtomicBlock (getContext (t), b.getStmts (), c);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return ab;
	}
	
	public final StmtBlock  block() throws RecognitionException, TokenStreamException {
		StmtBlock sb;
		
		Token  t = null;
		sb=null; Statement s; List l = new ArrayList();
		
		try {      // for error handling
			t = LT(1);
			match(LCURLY);
			{
			_loop96:
			do {
				if ((_tokenSet_25.member(LA(1)))) {
					s=statement();
					if ( inputState.guessing==0 ) {
						l.add(s);
					}
				}
				else {
					break _loop96;
				}
				
			} while (true);
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				sb = new StmtBlock(getContext(t), l);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_7);
			} else {
			  throw ex;
			}
		}
		return sb;
	}
	
	public final Statement  fdecl_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		s=null; Function f = null;
		
		try {      // for error handling
			f=function_decl();
			if ( inputState.guessing==0 ) {
				s = new StmtFunDecl(f.getCx(), f);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  variable_decl() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  id = null;
		Token  id2 = null;
		s = null; Type t; Expression x = null;
			List ts = new ArrayList(); List ns = new ArrayList();
			List xs = new ArrayList();
		
		try {      // for error handling
			t=data_type();
			id = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case ASSIGN:
			{
				match(ASSIGN);
				x=var_initializer();
				break;
			}
			case SEMI:
			case COMMA:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				ts.add(t); ns.add(id.getText()); xs.add(x);
			}
			{
			_loop72:
			do {
				if ((LA(1)==COMMA)) {
					if ( inputState.guessing==0 ) {
						x = null;
					}
					match(COMMA);
					id2 = LT(1);
					match(ID);
					{
					switch ( LA(1)) {
					case ASSIGN:
					{
						match(ASSIGN);
						x=var_initializer();
						break;
					}
					case SEMI:
					case COMMA:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					if ( inputState.guessing==0 ) {
						ts.add(t); ns.add(id2.getText()); xs.add(x);
					}
				}
				else {
					break _loop72;
				}
				
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				s = new StmtVarDecl(getContext (id), ts, ns, xs);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  expr_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		s = null; Expression x;
		
		try {      // for error handling
			s=assign_expr();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_26);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  switch_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  u = null;
		Token  name = null;
		Token  caseName = null;
		s = null; ExprVar  x;  Statement b =null;
		
		try {      // for error handling
			u = LT(1);
			match(TK_switch);
			match(LPAREN);
			name = LT(1);
			match(ID);
			match(RPAREN);
			match(LCURLY);
			if ( inputState.guessing==0 ) {
				x = new ExprVar(getContext(name), name.getText()); s= new StmtSwitch(getContext(u), x);
			}
			{
			switch ( LA(1)) {
			case TK_case:
			case TK_default:
			case RCURLY:
			{
				{
				_loop122:
				do {
					if ((LA(1)==TK_case)) {
						match(TK_case);
						caseName = LT(1);
						match(ID);
						match(COLON);
						b=pseudo_block();
						if ( inputState.guessing==0 ) {
							((StmtSwitch)s).addCaseBlock(caseName.getText(), b);
						}
					}
					else {
						break _loop122;
					}
					
				} while (true);
				}
				{
				switch ( LA(1)) {
				case TK_default:
				{
					match(TK_default);
					match(COLON);
					b=pseudo_block();
					if ( inputState.guessing==0 ) {
						((StmtSwitch)s).addCaseBlock("default",b);
					}
					break;
				}
				case RCURLY:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case TK_repeat_case:
			{
				match(TK_repeat_case);
				match(COLON);
				b=pseudo_block();
				if ( inputState.guessing==0 ) {
					((StmtSwitch)s).addCaseBlock("repeat", b);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RCURLY);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  if_else_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  u = null;
		s = null; Expression x; Statement t, f = null;
		
		try {      // for error handling
			u = LT(1);
			match(TK_if);
			match(LPAREN);
			x=right_expr();
			match(RPAREN);
			t=pseudo_block();
			{
			boolean synPredMatched127 = false;
			if (((LA(1)==TK_else) && (_tokenSet_25.member(LA(2))))) {
				int _m127 = mark();
				synPredMatched127 = true;
				inputState.guessing++;
				try {
					{
					match(TK_else);
					}
				}
				catch (RecognitionException pe) {
					synPredMatched127 = false;
				}
				rewind(_m127);
inputState.guessing--;
			}
			if ( synPredMatched127 ) {
				{
				match(TK_else);
				f=pseudo_block();
				}
			}
			else if ((_tokenSet_23.member(LA(1))) && (_tokenSet_27.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				s = new StmtIfThen(getContext(u), x, t, f);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  while_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t = null;
		s = null; Expression x; Statement b;
		
		try {      // for error handling
			t = LT(1);
			match(TK_while);
			match(LPAREN);
			x=right_expr();
			match(RPAREN);
			b=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtWhile(getContext(t), x, b);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  do_while_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t = null;
		s = null; Expression x; Statement b;
		
		try {      // for error handling
			t = LT(1);
			match(TK_do);
			b=pseudo_block();
			match(TK_while);
			match(LPAREN);
			x=right_expr();
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				s = new StmtDoWhile(getContext(t), b, x);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  for_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		Token  t = null;
		s = null; Expression x=null; Statement a, b, c;
		
		try {      // for error handling
			t = LT(1);
			match(TK_for);
			match(LPAREN);
			a=for_init_statement();
			match(SEMI);
			{
			switch ( LA(1)) {
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case NDANGELIC:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				x=right_expr();
				break;
			}
			case SEMI:
			{
				if ( inputState.guessing==0 ) {
					x = ExprConstInt.one;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(SEMI);
			b=for_incr_statement();
			match(RPAREN);
			c=pseudo_block();
			if ( inputState.guessing==0 ) {
				s = new StmtFor(getContext(t), a, x, b, c, false);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final StmtAssume  assume_statement() throws RecognitionException, TokenStreamException {
		StmtAssume s;
		
		Token  t = null;
		Token  ass = null;
		s = null; Expression cond;
		
		try {      // for error handling
			{
			t = LT(1);
			match(TK_assume);
			}
			cond=right_expr();
			{
			switch ( LA(1)) {
			case COLON:
			{
				match(COLON);
				ass = LT(1);
				match(STRING_LITERAL);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
							if (cond != null) {
								String msg = null;
								FEContext cx =getContext(t);
								if(ass!=null){
									String ps = ass.getText();
							        ps = ps.substring(1, ps.length()-1);
									msg = cx + "   "+ ps;	
								}
								s = new StmtAssume(cx, cond, msg);
							}
						
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final StmtAssert  assert_statement() throws RecognitionException, TokenStreamException {
		StmtAssert s;
		
		Token  t1 = null;
		Token  t2 = null;
		Token  ass = null;
		s = null; Expression x;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case TK_assert:
			{
				t1 = LT(1);
				match(TK_assert);
				break;
			}
			case TK_h_assert:
			{
				t2 = LT(1);
				match(TK_h_assert);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			x=right_expr();
			{
			switch ( LA(1)) {
			case COLON:
			{
				match(COLON);
				ass = LT(1);
				match(STRING_LITERAL);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
						String msg = null;
						Token t = t1;
						if(t==null){ t=t2;}
						FEContext cx =getContext(t); 
						if(ass!=null){
							String ps = ass.getText();
					        ps = ps.substring(1, ps.length()-1);
							msg = cx + "   "+ ps;	
						}
						s = new StmtAssert(cx, x, msg, t2!=null);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final StmtAssert  assert_max_statement() throws RecognitionException, TokenStreamException {
		StmtAssert s;
		
		Token  t = null;
		Token  defer = null;
		Token  ass = null;
		s = null; Expression cond; ExprVar var;
		
		try {      // for error handling
			t = LT(1);
			match(TK_assert_max);
			{
			switch ( LA(1)) {
			case BACKSLASH:
			{
				defer = LT(1);
				match(BACKSLASH);
				break;
			}
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case NDANGELIC:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			cond=right_expr();
			{
			switch ( LA(1)) {
			case COLON:
			{
				match(COLON);
				ass = LT(1);
				match(STRING_LITERAL);
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
					FEContext cx = getContext(t);
					String msg = null;
					if (ass != null) {
						String ps = ass.getText();
						ps = ps.substring(1, ps.length()-1);
						msg = cx + "   "+ ps;
					}
					s = StmtAssert.createAssertMax(cx, cond, msg, (defer!=null));
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final StmtReturn  return_statement() throws RecognitionException, TokenStreamException {
		StmtReturn s;
		
		Token  t = null;
		s = null; Expression x = null;
		
		try {      // for error handling
			t = LT(1);
			match(TK_return);
			{
			switch ( LA(1)) {
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case NDANGELIC:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				x=right_expr();
				break;
			}
			case SEMI:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				s = new StmtReturn(getContext(t), x);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_8);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Expression  right_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				x=right_expr_not_agmax();
				break;
			}
			case NDANGELIC:
			{
				x=agmax_expr();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final StmtBlock  pseudo_block() throws RecognitionException, TokenStreamException {
		StmtBlock sb;
		
		sb=null; Statement s; List l = new ArrayList();
		
		try {      // for error handling
			s=statement();
			if ( inputState.guessing==0 ) {
				l.add(s);
			}
			if ( inputState.guessing==0 ) {
				sb = new StmtBlock(s.getContext(), l);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_23);
			} else {
			  throw ex;
			}
		}
		return sb;
	}
	
	public final Expression  var_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  name = null;
		x = null; List rlist;
		
		try {      // for error handling
			name = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				x = new ExprVar(getContext(name), name.getText());
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_29);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  range_exp() throws RecognitionException, TokenStreamException {
		Expression e;
		
		Token  t = null;
		e = null; Expression from; Expression until; Expression by = new ExprConstInt(1);
		
		try {      // for error handling
			from=right_expr();
			t = LT(1);
			match(TK_until);
			until=right_expr();
			{
			switch ( LA(1)) {
			case TK_by:
			{
				match(TK_by);
				by=right_expr();
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
			}
			if ( inputState.guessing==0 ) {
				e = new ExprRange(getContext(t), from, until, by);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_30);
			} else {
			  throw ex;
			}
		}
		return e;
	}
	
	public final Type  primitive_type() throws RecognitionException, TokenStreamException {
		Type t;
		
		t = null;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case TK_boolean:
			{
				match(TK_boolean);
				if ( inputState.guessing==0 ) {
					t = TypePrimitive.bittype;
				}
				break;
			}
			case TK_bit:
			{
				match(TK_bit);
				if ( inputState.guessing==0 ) {
					t = TypePrimitive.bittype;
				}
				break;
			}
			case TK_int:
			{
				match(TK_int);
				if ( inputState.guessing==0 ) {
					t = TypePrimitive.inttype;
				}
				break;
			}
			case TK_float:
			{
				match(TK_float);
				if ( inputState.guessing==0 ) {
					t = TypePrimitive.floattype;
				}
				break;
			}
			case TK_double:
			{
				match(TK_double);
				if ( inputState.guessing==0 ) {
					t = TypePrimitive.doubletype;
				}
				break;
			}
			case TK_fun:
			{
				match(TK_fun);
				if ( inputState.guessing==0 ) {
					t = TypeFunction.singleton;
				}
				break;
			}
			case TK_char:
			{
				match(TK_char);
				if ( inputState.guessing==0 ) {
					t = TypePrimitive.chartype;
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_14);
			} else {
			  throw ex;
			}
		}
		return t;
	}
	
	public final  Expression  expr_named_param() throws RecognitionException, TokenStreamException {
		 Expression x ;
		
		Token  id = null;
		x = null; Token t = null;
		
		try {      // for error handling
			{
			if ((LA(1)==ID) && (LA(2)==ASSIGN)) {
				id = LT(1);
				match(ID);
				match(ASSIGN);
			}
			else if ((_tokenSet_17.member(LA(1))) && (_tokenSet_31.member(LA(2)))) {
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
			if ( inputState.guessing==0 ) {
				t = id;
			}
			x=right_expr();
			if ( inputState.guessing==0 ) {
				
				if (t != null) {
				x = new ExprNamedParam(getContext(t), t.getText(), x);
				}
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_32);
			} else {
			  throw ex;
			}
		}
		return x ;
	}
	
	public final Annotation  annotation() throws RecognitionException, TokenStreamException {
		Annotation an;
		
		Token  atc = null;
		Token  id = null;
		Token  slit = null;
		
			an = null;
		
		
		try {      // for error handling
			atc = LT(1);
			match(AT);
			id = LT(1);
			match(ID);
			match(LPAREN);
			{
			switch ( LA(1)) {
			case STRING_LITERAL:
			{
				slit = LT(1);
				match(STRING_LITERAL);
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RPAREN);
			if ( inputState.guessing==0 ) {
				
					an = Annotation.newAnnotation(getContext(atc), id.getText(), slit == null? "" : slit.getText());
				
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_33);
			} else {
			  throw ex;
			}
		}
		return an;
	}
	
	public final List  param_decl_list() throws RecognitionException, TokenStreamException {
		List l;
		
		l = new ArrayList(); List l2; Parameter p;
		
		try {      // for error handling
			match(LPAREN);
			{
			switch ( LA(1)) {
			case LSQUARE:
			{
				l2=impl_param();
				if ( inputState.guessing==0 ) {
					l.addAll(l2);
				}
				break;
			}
			case TK_boolean:
			case TK_float:
			case TK_bit:
			case TK_int:
			case TK_void:
			case TK_double:
			case TK_fun:
			case TK_char:
			case TK_ref:
			case TK_global:
			case RPAREN:
			case BITWISE_OR:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			{
			switch ( LA(1)) {
			case TK_boolean:
			case TK_float:
			case TK_bit:
			case TK_int:
			case TK_void:
			case TK_double:
			case TK_fun:
			case TK_char:
			case TK_ref:
			case TK_global:
			case BITWISE_OR:
			case ID:
			{
				p=param_decl();
				if ( inputState.guessing==0 ) {
					l.add(p);
				}
				{
				_loop88:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						p=param_decl();
						if ( inputState.guessing==0 ) {
							l.add(p);
						}
					}
					else {
						break _loop88;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RPAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_34);
			} else {
			  throw ex;
			}
		}
		return l;
	}
	
	public final List  impl_param() throws RecognitionException, TokenStreamException {
		List l;
		
		Token  id = null;
		Token  id2 = null;
		Parameter p; l = new ArrayList();
		
		try {      // for error handling
			match(LSQUARE);
			match(TK_int);
			id = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				p= new Parameter(getContext(id), TypePrimitive.inttype, id.getText(), Parameter.IN, true); l.add(p);
			}
			{
			_loop91:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					match(TK_int);
					id2 = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						p= new Parameter(getContext(id), TypePrimitive.inttype, id2.getText(), Parameter.IN, true); l.add(p);
					}
				}
				else {
					break _loop91;
				}
				
			} while (true);
			}
			match(RSQUARE);
			match(COMMA);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_35);
			} else {
			  throw ex;
			}
		}
		return l;
	}
	
	public final Parameter  param_decl() throws RecognitionException, TokenStreamException {
		Parameter p;
		
		Token  id = null;
		Type t; p = null; boolean isRef=false;
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case TK_ref:
			{
				match(TK_ref);
				if ( inputState.guessing==0 ) {
					isRef=true;
				}
				break;
			}
			case TK_boolean:
			case TK_float:
			case TK_bit:
			case TK_int:
			case TK_void:
			case TK_double:
			case TK_fun:
			case TK_char:
			case TK_global:
			case BITWISE_OR:
			case ID:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			t=data_type();
			id = LT(1);
			match(ID);
			if ( inputState.guessing==0 ) {
				p = new Parameter(getContext(id), t, id.getText(), isRef? Parameter.REF : Parameter.IN, false);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_36);
			} else {
			  throw ex;
			}
		}
		return p;
	}
	
	public final Statement  for_init_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		s = null;
		
		try {      // for error handling
			boolean synPredMatched135 = false;
			if (((_tokenSet_2.member(LA(1))) && (_tokenSet_3.member(LA(2))))) {
				int _m135 = mark();
				synPredMatched135 = true;
				inputState.guessing++;
				try {
					{
					variable_decl();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched135 = false;
				}
				rewind(_m135);
inputState.guessing--;
			}
			if ( synPredMatched135 ) {
				s=variable_decl();
			}
			else {
				boolean synPredMatched137 = false;
				if (((_tokenSet_21.member(LA(1))) && (_tokenSet_22.member(LA(2))))) {
					int _m137 = mark();
					synPredMatched137 = true;
					inputState.guessing++;
					try {
						{
						expr_statement();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched137 = false;
					}
					rewind(_m137);
inputState.guessing--;
				}
				if ( synPredMatched137 ) {
					s=expr_statement();
				}
				else if ((LA(1)==SEMI)) {
					if ( inputState.guessing==0 ) {
						s = new StmtEmpty((FEContext)null);
					}
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
			}
			catch (RecognitionException ex) {
				if (inputState.guessing==0) {
					reportError(ex);
					recover(ex,_tokenSet_8);
				} else {
				  throw ex;
				}
			}
			return s;
		}
		
	public final Statement  for_incr_statement() throws RecognitionException, TokenStreamException {
		Statement s;
		
		s = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				s=expr_statement();
				break;
			}
			case RPAREN:
			{
				if ( inputState.guessing==0 ) {
					s = new StmtEmpty((FEContext) null);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_30);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Statement  assign_expr() throws RecognitionException, TokenStreamException {
		Statement s;
		
		s = null; Expression l, r; int o = 0;
		
		try {      // for error handling
			l=prefix_expr();
			{
			switch ( LA(1)) {
			case PLUS_EQUALS:
			case MINUS_EQUALS:
			case STAR_EQUALS:
			case DIV_EQUALS:
			case ASSIGN:
			{
				{
				{
				switch ( LA(1)) {
				case ASSIGN:
				{
					match(ASSIGN);
					if ( inputState.guessing==0 ) {
						o = 0;
					}
					break;
				}
				case PLUS_EQUALS:
				{
					match(PLUS_EQUALS);
					if ( inputState.guessing==0 ) {
						o = ExprBinary.BINOP_ADD;
					}
					break;
				}
				case MINUS_EQUALS:
				{
					match(MINUS_EQUALS);
					if ( inputState.guessing==0 ) {
						o = ExprBinary.BINOP_SUB;
					}
					break;
				}
				case STAR_EQUALS:
				{
					match(STAR_EQUALS);
					if ( inputState.guessing==0 ) {
						o = ExprBinary.BINOP_MUL;
					}
					break;
				}
				case DIV_EQUALS:
				{
					match(DIV_EQUALS);
					if ( inputState.guessing==0 ) {
						o = ExprBinary.BINOP_DIV;
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				r=right_expr();
				if ( inputState.guessing==0 ) {
					s = new StmtAssign(l, r, o); s.resetOrigin();
				}
				}
				break;
			}
			case RPAREN:
			case SEMI:
			{
				if ( inputState.guessing==0 ) {
					s = new StmtExpr(l);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_26);
			} else {
			  throw ex;
			}
		}
		return s;
	}
	
	public final Expression  prefix_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  ii = null;
		Token  dd = null;
		Token  b = null;
		Token  m = null;
		x = null;  FEContext cx = null; int untype = -1;
		
		try {      // for error handling
			boolean synPredMatched210 = false;
			if (((LA(1)==LPAREN) && (_tokenSet_2.member(LA(2))))) {
				int _m210 = mark();
				synPredMatched210 = true;
				inputState.guessing++;
				try {
					{
					castExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched210 = false;
				}
				rewind(_m210);
inputState.guessing--;
			}
			if ( synPredMatched210 ) {
				x=castExpr();
			}
			else if ((_tokenSet_37.member(LA(1))) && (_tokenSet_38.member(LA(2)))) {
				x=postfix_expr();
			}
			else if ((_tokenSet_39.member(LA(1)))) {
				{
				switch ( LA(1)) {
				case INCREMENT:
				{
					ii = LT(1);
					match(INCREMENT);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_PREINC; cx = getContext(ii);
					}
					break;
				}
				case DECREMENT:
				{
					dd = LT(1);
					match(DECREMENT);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_PREDEC; cx = getContext(dd);
					}
					break;
				}
				case BANG:
				{
					b = LT(1);
					match(BANG);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_NOT; cx = getContext(b);
					}
					break;
				}
				case MINUS:
				{
					m = LT(1);
					match(MINUS);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_NEG; cx = getContext(m);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				x=prefix_expr();
				if ( inputState.guessing==0 ) {
					if(untype != -1){ x = new ExprUnary(cx, untype, x); }
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_40);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  func_call() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  prefix = null;
		Token  name = null;
		x = null; List l;
		
		try {      // for error handling
			prefix = LT(1);
			match(ID);
			{
			switch ( LA(1)) {
			case AT:
			{
				match(AT);
				name = LT(1);
				match(ID);
				break;
			}
			case LPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			l=func_call_params();
			if ( inputState.guessing==0 ) {
				x = new ExprFunCall(getContext(prefix), prefix.getText()+ (name != null ? ("@" + name.getText()) : ""), l);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final List  func_call_params() throws RecognitionException, TokenStreamException {
		List l;
		
		l = new ArrayList(); Expression x;
		
		try {      // for error handling
			match(LPAREN);
			{
			switch ( LA(1)) {
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case NDANGELIC:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				x=expr_named_param();
				if ( inputState.guessing==0 ) {
					l.add(x);
				}
				{
				_loop149:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						x=expr_named_param();
						if ( inputState.guessing==0 ) {
							l.add(x);
						}
					}
					else {
						break _loop149;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RPAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return l;
	}
	
	public final List  constr_params() throws RecognitionException, TokenStreamException {
		List l;
		
		l = new ArrayList(); Expression x;
		
		try {      // for error handling
			match(LPAREN);
			{
			switch ( LA(1)) {
			case ID:
			{
				x=expr_named_param_only();
				if ( inputState.guessing==0 ) {
					l.add(x);
				}
				{
				_loop153:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						x=expr_named_param();
						if ( inputState.guessing==0 ) {
							l.add(x);
						}
					}
					else {
						break _loop153;
					}
					
				} while (true);
				}
				break;
			}
			case RPAREN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RPAREN);
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return l;
	}
	
	public final  Expression  expr_named_param_only() throws RecognitionException, TokenStreamException {
		 Expression x ;
		
		Token  id = null;
		x = null; Token t = null;
		
		try {      // for error handling
			id = LT(1);
			match(ID);
			match(ASSIGN);
			x=right_expr();
			if ( inputState.guessing==0 ) {
				x = new ExprNamedParam(getContext(id), id.getText(), x);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_42);
			} else {
			  throw ex;
			}
		}
		return x ;
	}
	
	public final Expression  right_expr_not_agmax() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null;
		
		try {      // for error handling
			x=ternaryExpr();
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  ternaryExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression b, c;
		
		try {      // for error handling
			x=logicOrExpr();
			{
			switch ( LA(1)) {
			case QUESTION:
			{
				match(QUESTION);
				b=ternaryExpr();
				match(COLON);
				c=ternaryExpr();
				if ( inputState.guessing==0 ) {
					x = new ExprTernary(x, ExprTernary.TEROP_COND,
										x, b, c);
				}
				break;
			}
			case EOF:
			case TK_until:
			case TK_by:
			case RPAREN:
			case RCURLY:
			case RSQUARE:
			case COLON:
			case SEMI:
			case COMMA:
			case LESS_COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  agmax_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  t = null;
		Token  n = null;
		x = null;
		
		try {      // for error handling
			t = LT(1);
			match(NDANGELIC);
			{
			switch ( LA(1)) {
			case LPAREN:
			{
				match(LPAREN);
				n = LT(1);
				match(NUMBER);
				match(RPAREN);
				break;
			}
			case EOF:
			case TK_until:
			case TK_by:
			case RPAREN:
			case RCURLY:
			case RSQUARE:
			case COLON:
			case SEMI:
			case COMMA:
			case LESS_COLON:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				
						if (n != null) {
							x = new ExprStar(getContext(t), Integer.parseInt(n.getText()), Kind.ANGELIC);
						} else {
							x = new ExprStar(getContext(t), Kind.ANGELIC);
						}
					
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_28);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  arr_initializer() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  lc = null;
		ArrayList l = new ArrayList();
		x = null;
		Expression y;
		
		try {      // for error handling
			lc = LT(1);
			match(LCURLY);
			{
			switch ( LA(1)) {
			case TK_new:
			case TK_null:
			case TK_true:
			case TK_false:
			case LPAREN:
			case LCURLY:
			case INCREMENT:
			case MINUS:
			case DECREMENT:
			case BITWISE_OR:
			case BANG:
			case NDVAL:
			case NDVAL2:
			case NDANGELIC:
			case REGEN:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			case ID:
			{
				y=var_initializer();
				if ( inputState.guessing==0 ) {
					l.add(y);
				}
				{
				_loop170:
				do {
					if ((LA(1)==COMMA)) {
						match(COMMA);
						y=var_initializer();
						if ( inputState.guessing==0 ) {
							l.add(y);
						}
					}
					else {
						break _loop170;
					}
					
				} while (true);
				}
				break;
			}
			case RCURLY:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				x = new ExprArrayInit(getContext(lc), l);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  type_or_var_initializer() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null;
		
		try {      // for error handling
			boolean synPredMatched166 = false;
			if (((LA(1)==LCURLY) && (_tokenSet_16.member(LA(2))))) {
				int _m166 = mark();
				synPredMatched166 = true;
				inputState.guessing++;
				try {
					{
					arr_initializer();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched166 = false;
				}
				rewind(_m166);
inputState.guessing--;
			}
			if ( synPredMatched166 ) {
				x=arr_initializer();
			}
			else if ((_tokenSet_17.member(LA(1))) && (_tokenSet_43.member(LA(2)))) {
				x=right_expr();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_4);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  logicOrExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r; int o = 0;
		
		try {      // for error handling
			x=logicAndExpr();
			{
			_loop175:
			do {
				if ((LA(1)==LOGIC_OR)) {
					match(LOGIC_OR);
					r=logicAndExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(ExprBinary.BINOP_OR, x, r);
					}
				}
				else {
					break _loop175;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_44);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  logicAndExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r;
		
		try {      // for error handling
			x=bitwiseOrExpr();
			{
			_loop178:
			do {
				if ((LA(1)==LOGIC_AND)) {
					match(LOGIC_AND);
					r=bitwiseOrExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(ExprBinary.BINOP_AND, x, r);
					}
				}
				else {
					break _loop178;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_45);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  bitwiseOrExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r;
		
		try {      // for error handling
			x=bitwiseXorExpr();
			{
			_loop181:
			do {
				if ((LA(1)==BITWISE_OR)) {
					match(BITWISE_OR);
					r=bitwiseXorExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(ExprBinary.BINOP_BOR, x, r);
					}
				}
				else {
					break _loop181;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_46);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  bitwiseXorExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r;
		
		try {      // for error handling
			x=bitwiseAndExpr();
			{
			_loop184:
			do {
				if ((LA(1)==BITWISE_XOR)) {
					match(BITWISE_XOR);
					r=bitwiseAndExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(ExprBinary.BINOP_BXOR, x, r);
					}
				}
				else {
					break _loop184;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_47);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  bitwiseAndExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r;
		
		try {      // for error handling
			x=equalExpr();
			{
			_loop187:
			do {
				if ((LA(1)==BITWISE_AND)) {
					match(BITWISE_AND);
					r=equalExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(ExprBinary.BINOP_BAND, x, r);
					}
				}
				else {
					break _loop187;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_48);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  equalExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r; Expression last; int o = 0;
		
		try {      // for error handling
			x=compareExpr();
			if ( inputState.guessing==0 ) {
				last=x;
			}
			{
			_loop191:
			do {
				if (((LA(1) >= TRIPLE_EQUAL && LA(1) <= NOT_EQUAL))) {
					{
					switch ( LA(1)) {
					case EQUAL:
					{
						match(EQUAL);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_EQ;
						}
						break;
					}
					case TRIPLE_EQUAL:
					{
						match(TRIPLE_EQUAL);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_TEQ;
						}
						break;
					}
					case NOT_EQUAL:
					{
						match(NOT_EQUAL);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_NEQ;
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					r=compareExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(o, x, r);
					}
				}
				else {
					break _loop191;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_49);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  compareExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r; int o = 0;
		
		try {      // for error handling
			x=shiftExpr();
			{
			_loop195:
			do {
				if (((LA(1) >= LESS_THAN && LA(1) <= MORE_EQUAL))) {
					{
					switch ( LA(1)) {
					case LESS_THAN:
					{
						match(LESS_THAN);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_LT;
						}
						break;
					}
					case LESS_EQUAL:
					{
						match(LESS_EQUAL);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_LE;
						}
						break;
					}
					case MORE_THAN:
					{
						match(MORE_THAN);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_GT;
						}
						break;
					}
					case MORE_EQUAL:
					{
						match(MORE_EQUAL);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_GE;
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					r=shiftExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(o, x, r);
					}
				}
				else {
					break _loop195;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_50);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  shiftExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x=null; Expression r; int op=0;
		
		try {      // for error handling
			x=addExpr();
			{
			_loop199:
			do {
				if ((LA(1)==LSHIFT||LA(1)==RSHIFT)) {
					{
					switch ( LA(1)) {
					case LSHIFT:
					{
						match(LSHIFT);
						if ( inputState.guessing==0 ) {
							op=ExprBinary.BINOP_LSHIFT;
						}
						break;
					}
					case RSHIFT:
					{
						match(RSHIFT);
						if ( inputState.guessing==0 ) {
							op=ExprBinary.BINOP_RSHIFT;
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					r=addExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(op, x, r);
					}
				}
				else {
					break _loop199;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_51);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  addExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r; int o = 0;
		
		try {      // for error handling
			x=multExpr();
			{
			_loop203:
			do {
				if ((LA(1)==PLUS||LA(1)==MINUS||LA(1)==SELECT)) {
					{
					switch ( LA(1)) {
					case PLUS:
					{
						match(PLUS);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_ADD;
						}
						break;
					}
					case MINUS:
					{
						match(MINUS);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_SUB;
						}
						break;
					}
					case SELECT:
					{
						match(SELECT);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_SELECT;
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					r=multExpr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(o, x, r);
					}
				}
				else {
					break _loop203;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_52);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  multExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		x = null; Expression r; int o = 0;
		
		try {      // for error handling
			x=prefix_expr();
			{
			_loop207:
			do {
				if ((LA(1)==STAR||LA(1)==DIV||LA(1)==MOD)) {
					{
					switch ( LA(1)) {
					case STAR:
					{
						match(STAR);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_MUL;
						}
						break;
					}
					case DIV:
					{
						match(DIV);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_DIV;
						}
						break;
					}
					case MOD:
					{
						match(MOD);
						if ( inputState.guessing==0 ) {
							o = ExprBinary.BINOP_MOD;
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					r=prefix_expr();
					if ( inputState.guessing==0 ) {
						x = new ExprBinary(o, x, r);
					}
				}
				else {
					break _loop207;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_53);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  castExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  l = null;
		x = null; Type t = null; Expression bound = null;
		
		try {      // for error handling
			l = LT(1);
			match(LPAREN);
			t=data_type();
			match(RPAREN);
			x=prefix_expr_nominus();
			if ( inputState.guessing==0 ) {
				x = new ExprTypeCast(getContext(l), t, x);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_40);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  postfix_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  ii = null;
		Token  dd = null;
		x = null;  int untype = -1;
		
		try {      // for error handling
			x=primary_expr();
			{
			_loop219:
			do {
				switch ( LA(1)) {
				case INCREMENT:
				{
					ii = LT(1);
					match(INCREMENT);
					if ( inputState.guessing==0 ) {
						x = new ExprUnary(getContext(ii), ExprUnary.UNOP_POSTINC, x);
					}
					break;
				}
				case DECREMENT:
				{
					dd = LT(1);
					match(DECREMENT);
					if ( inputState.guessing==0 ) {
						x = new ExprUnary(getContext(dd), ExprUnary.UNOP_POSTDEC, x);
					}
					break;
				}
				default:
				{
					break _loop219;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_40);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  prefix_expr_nominus() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  ii = null;
		Token  dd = null;
		Token  b = null;
		x = null;  FEContext cx = null; int untype = -1;
		
		try {      // for error handling
			boolean synPredMatched214 = false;
			if (((LA(1)==LPAREN) && (_tokenSet_2.member(LA(2))))) {
				int _m214 = mark();
				synPredMatched214 = true;
				inputState.guessing++;
				try {
					{
					castExpr();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched214 = false;
				}
				rewind(_m214);
inputState.guessing--;
			}
			if ( synPredMatched214 ) {
				x=castExpr();
			}
			else if ((_tokenSet_37.member(LA(1))) && (_tokenSet_38.member(LA(2)))) {
				x=postfix_expr();
			}
			else if ((LA(1)==INCREMENT||LA(1)==DECREMENT||LA(1)==BANG)) {
				{
				switch ( LA(1)) {
				case INCREMENT:
				{
					ii = LT(1);
					match(INCREMENT);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_PREINC; cx = getContext(ii);
					}
					break;
				}
				case DECREMENT:
				{
					dd = LT(1);
					match(DECREMENT);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_PREDEC; cx = getContext(dd);
					}
					break;
				}
				case BANG:
				{
					b = LT(1);
					match(BANG);
					if ( inputState.guessing==0 ) {
						untype = ExprUnary.UNOP_NOT; cx = getContext(b);
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				x=prefix_expr();
				if ( inputState.guessing==0 ) {
					if(untype != -1){ x = new ExprUnary(cx, untype, x); }
				}
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_40);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  primary_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  field = null;
		Token  l = null;
		x = null; Vector<ExprArrayRange.RangeLen> rl;Type t = null;
		
		try {      // for error handling
			x=tminic_value_expr();
			{
			_loop223:
			do {
				switch ( LA(1)) {
				case DOT:
				{
					match(DOT);
					{
					switch ( LA(1)) {
					case ID:
					{
						field = LT(1);
						match(ID);
						if ( inputState.guessing==0 ) {
							x = new ExprField(x, x, field.getText(), false);
						}
						break;
					}
					case NDVAL2:
					{
						match(NDVAL2);
						if ( inputState.guessing==0 ) {
							x= new ExprField(x,x,"", true);
						}
						break;
					}
					case LCURLY:
					{
						match(LCURLY);
						t=data_type();
						if ( inputState.guessing==0 ) {
							x = new ExprFieldMacro(x, x, t);
						}
						match(RCURLY);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case LSQUARE:
				{
					l = LT(1);
					match(LSQUARE);
					rl=array_range();
					if ( inputState.guessing==0 ) {
						x = new ExprArrayRange(x, x, rl);
					}
					match(RSQUARE);
					break;
				}
				default:
				{
					break _loop223;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_54);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  tminic_value_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  r = null;
		x = null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case LPAREN:
			{
				match(LPAREN);
				x=right_expr();
				match(RPAREN);
				break;
			}
			case TK_new:
			case BITWISE_OR:
			{
				x=constructor_expr();
				break;
			}
			case TK_null:
			case TK_true:
			case TK_false:
			case NDVAL:
			case NDVAL2:
			case CHAR_LITERAL:
			case STRING_LITERAL:
			case HQUAN:
			case NUMBER:
			{
				x=constantExpr();
				break;
			}
			case LCURLY:
			{
				x=arr_initializer();
				break;
			}
			case REGEN:
			{
				r = LT(1);
				match(REGEN);
				if ( inputState.guessing==0 ) {
					x = new ExprRegen (getContext (r), r.getText ());
				}
				break;
			}
			default:
				boolean synPredMatched226 = false;
				if (((LA(1)==ID) && (LA(2)==LPAREN||LA(2)==AT))) {
					int _m226 = mark();
					synPredMatched226 = true;
					inputState.guessing++;
					try {
						{
						func_call();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched226 = false;
					}
					rewind(_m226);
inputState.guessing--;
				}
				if ( synPredMatched226 ) {
					x=func_call();
				}
				else if ((LA(1)==ID) && (_tokenSet_41.member(LA(2)))) {
					x=var_expr();
				}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Vector<ExprArrayRange.RangeLen>  array_range() throws RecognitionException, TokenStreamException {
		Vector<ExprArrayRange.RangeLen> x;
		
		x=null; Expression start,end,l;
		
		try {      // for error handling
			start=expr_named_param();
			if ( inputState.guessing==0 ) {
				
				assert (!(start instanceof ExprNamedParam));
				x = new Vector<ExprArrayRange.RangeLen>();
				x.add(new ExprArrayRange.RangeLen(start));
				
			}
			{
			_loop236:
			do {
				if ((LA(1)==COMMA)) {
					match(COMMA);
					start=expr_named_param();
					if ( inputState.guessing==0 ) {
						x.add(new ExprArrayRange.RangeLen(start));
					}
				}
				else {
					break _loop236;
				}
				
			} while (true);
			}
			{
			switch ( LA(1)) {
			case COLON:
			{
				match(COLON);
				{
				switch ( LA(1)) {
				case TK_new:
				case TK_null:
				case TK_true:
				case TK_false:
				case LPAREN:
				case LCURLY:
				case INCREMENT:
				case MINUS:
				case DECREMENT:
				case BITWISE_OR:
				case BANG:
				case NDVAL:
				case NDVAL2:
				case NDANGELIC:
				case REGEN:
				case CHAR_LITERAL:
				case STRING_LITERAL:
				case HQUAN:
				case NUMBER:
				case ID:
				{
					end=right_expr();
					if ( inputState.guessing==0 ) {
						
								  		assert x.size() == 1 : "cannot mix comma indices and array ranges yet";
								  		x.set(0, new ExprArrayRange.RangeLen(start,
								  			new ExprBinary(end, "-", start)));
					}
					break;
				}
				case COLON:
				{
					match(COLON);
					l=right_expr();
					if ( inputState.guessing==0 ) {
						
								    	assert x.size() == 1 : "cannot mix comma indices and array ranges yet";
								    	x.set(0, new ExprArrayRange.RangeLen(start,l));
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case RSQUARE:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_55);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  constructor_expr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  n = null;
		Token  prefix = null;
		Token  id = null;
		Token  prefix2 = null;
		Token  id2 = null;
		x = null; Type t=null; List l; boolean hole = false;
		
		try {      // for error handling
			switch ( LA(1)) {
			case TK_new:
			{
				n = LT(1);
				match(TK_new);
				{
				if ((LA(1)==ID) && (LA(2)==AT)) {
					prefix = LT(1);
					match(ID);
					match(AT);
				}
				else if ((LA(1)==NDVAL2||LA(1)==ID) && (LA(2)==LPAREN)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				{
				switch ( LA(1)) {
				case ID:
				{
					id = LT(1);
					match(ID);
					if ( inputState.guessing==0 ) {
						t = new TypeStructRef(prefix != null ? (prefix.getText() + "@" + id.getText() )  : id.getText(), false);
					}
					break;
				}
				case NDVAL2:
				{
					match(NDVAL2);
					if ( inputState.guessing==0 ) {
						hole = true;
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				l=constr_params();
				if ( inputState.guessing==0 ) {
					x = new ExprNew( getContext(n), t, l,hole);
				}
				break;
			}
			case BITWISE_OR:
			{
				match(BITWISE_OR);
				{
				if ((LA(1)==ID) && (LA(2)==AT)) {
					prefix2 = LT(1);
					match(ID);
					match(AT);
				}
				else if ((LA(1)==ID) && (LA(2)==BITWISE_OR)) {
				}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				
				}
				id2 = LT(1);
				match(ID);
				if ( inputState.guessing==0 ) {
					t = new TypeStructRef(prefix2 != null ? (prefix2.getText() + "@" + id2.getText() )  : id2.getText(), true);
				}
				match(BITWISE_OR);
				l=constr_params();
				if ( inputState.guessing==0 ) {
					x = new ExprNew( getContext(id2), t, l, hole);
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final Expression  constantExpr() throws RecognitionException, TokenStreamException {
		Expression x;
		
		Token  h = null;
		Token  n = null;
		Token  c = null;
		Token  s = null;
		Token  t = null;
		Token  f = null;
		Token  t1 = null;
		Token  t2 = null;
		x = null; Expression n1=null, n2=null;
		
		try {      // for error handling
			switch ( LA(1)) {
			case HQUAN:
			{
				h = LT(1);
				match(HQUAN);
				if ( inputState.guessing==0 ) {
					String tmp = h.getText().substring(2);
								   Integer iti = new Integer(
						 (int ) ( ( Long.parseLong(tmp, 16) - (long) Integer.MIN_VALUE )
							  % ( (long)Integer.MAX_VALUE - (long) Integer.MIN_VALUE + 1)
							  + Integer.MIN_VALUE) );
									x = ExprConstant.createConstant(getContext(h), iti.toString() );
				}
				break;
			}
			case NUMBER:
			{
				n = LT(1);
				match(NUMBER);
				if ( inputState.guessing==0 ) {
					x = ExprConstant.createConstant(getContext(n), n.getText());
				}
				break;
			}
			case CHAR_LITERAL:
			{
				c = LT(1);
				match(CHAR_LITERAL);
				if ( inputState.guessing==0 ) {
					x = ExprConstChar.create(c.getText());
				}
				break;
			}
			case STRING_LITERAL:
			{
				s = LT(1);
				match(STRING_LITERAL);
				if ( inputState.guessing==0 ) {
					x = new ExprArrayInit(getContext(s), ExprConstChar.createFromString(s.getText()));
				}
				break;
			}
			case TK_true:
			{
				t = LT(1);
				match(TK_true);
				if ( inputState.guessing==0 ) {
					x = ExprConstInt.one;
				}
				break;
			}
			case TK_false:
			{
				f = LT(1);
				match(TK_false);
				if ( inputState.guessing==0 ) {
					x = ExprConstInt.zero;
				}
				break;
			}
			case TK_null:
			{
				match(TK_null);
				if ( inputState.guessing==0 ) {
					x = ExprNullPtr.nullPtr;
				}
				break;
			}
			case NDVAL:
			{
				t1 = LT(1);
				match(NDVAL);
				if ( inputState.guessing==0 ) {
					x = new ExprStar(getContext(t1));
				}
				break;
			}
			case NDVAL2:
			{
				t2 = LT(1);
				match(NDVAL2);
				{
				switch ( LA(1)) {
				case LPAREN:
				{
					match(LPAREN);
					n1=addExpr();
					{
					switch ( LA(1)) {
					case COMMA:
					{
						match(COMMA);
						n2=addExpr();
						break;
					}
					case RPAREN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					match(RPAREN);
					break;
				}
				case EOF:
				case TK_until:
				case TK_by:
				case RPAREN:
				case RCURLY:
				case LSQUARE:
				case RSQUARE:
				case PLUS:
				case PLUS_EQUALS:
				case INCREMENT:
				case MINUS:
				case MINUS_EQUALS:
				case DECREMENT:
				case STAR:
				case STAR_EQUALS:
				case DIV:
				case DIV_EQUALS:
				case MOD:
				case LOGIC_AND:
				case LOGIC_OR:
				case BITWISE_AND:
				case BITWISE_OR:
				case BITWISE_XOR:
				case ASSIGN:
				case TRIPLE_EQUAL:
				case EQUAL:
				case NOT_EQUAL:
				case LESS_THAN:
				case LESS_EQUAL:
				case MORE_THAN:
				case MORE_EQUAL:
				case QUESTION:
				case COLON:
				case SEMI:
				case COMMA:
				case DOT:
				case LSHIFT:
				case RSHIFT:
				case SELECT:
				case LESS_COLON:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				if ( inputState.guessing==0 ) {
					if(n1 != null){
						Integer in1 = n1.getIValue();
						  if(n2 == null){            	  	
						  	x = new ExprStar(getContext(t2),in1);
						  }else{
						  	Integer in2 = n2.getIValue();
						  	x = new ExprStar(getContext(t2),in1, in2);
						  } 
						}else{
						  x = new ExprStar(getContext(t2)); 
						}
					
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_41);
			} else {
			  throw ex;
			}
		}
		return x;
	}
	
	public final StructDef  structInsideADT_decl() throws RecognitionException, TokenStreamException {
		StructDef ts;
		
		Token  id = null;
		ts = null; Parameter p; List names = new ArrayList();
			Annotation an=null;
			HashmapList<String, Annotation> annotations = new HashmapList<String, Annotation>();
			List types = new ArrayList();
		
		try {      // for error handling
			id = LT(1);
			match(ID);
			match(LCURLY);
			{
			_loop247:
			do {
				switch ( LA(1)) {
				case TK_boolean:
				case TK_float:
				case TK_bit:
				case TK_int:
				case TK_void:
				case TK_double:
				case TK_fun:
				case TK_char:
				case TK_ref:
				case TK_global:
				case BITWISE_OR:
				case ID:
				{
					p=param_decl();
					match(SEMI);
					if ( inputState.guessing==0 ) {
						names.add(p.getName()); types.add(p.getType());
					}
					break;
				}
				case AT:
				{
					an=annotation();
					if ( inputState.guessing==0 ) {
						annotations.append(an.tag, an);
					}
					break;
				}
				default:
				{
					break _loop247;
				}
				}
			} while (true);
			}
			match(RCURLY);
			if ( inputState.guessing==0 ) {
				
							ts = StructDef.creator(getContext(id), id.getText(),null, true, names, types, annotations).create();
							
			}
		}
		catch (RecognitionException ex) {
			if (inputState.guessing==0) {
				reportError(ex);
				recover(ex,_tokenSet_56);
			} else {
			  throw ex;
			}
		}
		return ts;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"atomic\"",
		"\"fork\"",
		"\"insert\"",
		"\"into\"",
		"\"loop\"",
		"\"repeat\"",
		"\"minrepeat\"",
		"\"new\"",
		"\"null\"",
		"\"reorder\"",
		"\"assume\"",
		"\"boolean\"",
		"\"float\"",
		"\"bit\"",
		"\"int\"",
		"\"void\"",
		"\"double\"",
		"\"fun\"",
		"\"char\"",
		"\"struct\"",
		"\"ref\"",
		"\"adt\"",
		"\"if\"",
		"\"else\"",
		"\"while\"",
		"\"for\"",
		"\"switch\"",
		"\"case\"",
		"\"repeat_case\"",
		"\"default\"",
		"\"break\"",
		"\"do\"",
		"\"continue\"",
		"\"return\"",
		"\"true\"",
		"\"false\"",
		"\"parfor\"",
		"\"until\"",
		"\"by\"",
		"\"implements\"",
		"\"assert\"",
		"\"assert_max\"",
		"\"h_assert\"",
		"\"generator\"",
		"\"harness\"",
		"\"model\"",
		"\"global\"",
		"\"serial\"",
		"\"spmdfork\"",
		"\"stencil\"",
		"\"include\"",
		"\"pragma\"",
		"\"package\"",
		"\"extends\"",
		"ARROW",
		"LARROW",
		"WS",
		"LINERESET",
		"SL_COMMENT",
		"ML_COMMENT",
		"LPAREN",
		"RPAREN",
		"LCURLY",
		"RCURLY",
		"LSQUARE",
		"RSQUARE",
		"PLUS",
		"PLUS_EQUALS",
		"INCREMENT",
		"MINUS",
		"MINUS_EQUALS",
		"DECREMENT",
		"STAR",
		"STAR_EQUALS",
		"DIV",
		"DIV_EQUALS",
		"MOD",
		"LOGIC_AND",
		"LOGIC_OR",
		"BITWISE_AND",
		"BITWISE_OR",
		"BITWISE_XOR",
		"ASSIGN",
		"DEF_ASSIGN",
		"TRIPLE_EQUAL",
		"EQUAL",
		"NOT_EQUAL",
		"LESS_THAN",
		"LESS_EQUAL",
		"MORE_THAN",
		"MORE_EQUAL",
		"QUESTION",
		"COLON",
		"SEMI",
		"COMMA",
		"DOT",
		"BANG",
		"LSHIFT",
		"RSHIFT",
		"NDVAL",
		"NDVAL2",
		"SELECT",
		"NDANGELIC",
		"AT",
		"BACKSLASH",
		"LESS_COLON",
		"REGEN",
		"CHAR_LITERAL",
		"STRING_LITERAL",
		"ESC",
		"DIGIT",
		"HQUAN",
		"NUMBER",
		"an identifier",
		"TK_device",
		"TK_library",
		"TK_printfcn"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 13370061402112000L, 9015995348811776L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 13370061402112000L, 9015995348811792L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 1125899915198464L, 9007199255789568L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 7831552L, 9015995348811792L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 13370061402112000L, 9007199255789568L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 0L, 9007199254740992L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 144099790601322354L, 16265702575966989L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 0L, 8589934592L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 139470851010428930L, 9015995348811784L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 1125899931975680L, 9007199255789568L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 1125899915198464L, 9015995348811792L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 139470851027206146L, 9015995348811784L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	private static final long[] mk_tokenSet_13() {
		long[] data = { 139470851010428930L, 9015995348811776L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_13 = new BitSet(mk_tokenSet_13());
	private static final long[] mk_tokenSet_14() {
		long[] data = { 0L, 9007199254741018L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_14 = new BitSet(mk_tokenSet_14());
	private static final long[] mk_tokenSet_15() {
		long[] data = { 0L, 9007199254741002L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_15 = new BitSet(mk_tokenSet_15());
	private static final long[] mk_tokenSet_16() {
		long[] data = { 824633726976L, 16261295939521293L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_16 = new BitSet(mk_tokenSet_16());
	private static final long[] mk_tokenSet_17() {
		long[] data = { 824633726976L, 16261295939521285L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_17 = new BitSet(mk_tokenSet_17());
	private static final long[] mk_tokenSet_18() {
		long[] data = { 1126724548925440L, 16272767783492445L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_18 = new BitSet(mk_tokenSet_18());
	private static final long[] mk_tokenSet_19() {
		long[] data = { 0L, 25769803784L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_19 = new BitSet(mk_tokenSet_19());
	private static final long[] mk_tokenSet_20() {
		long[] data = { 17998990121369456L, 16265702575966989L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_20 = new BitSet(mk_tokenSet_20());
	private static final long[] mk_tokenSet_21() {
		long[] data = { 824633726976L, 16256897893010181L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_21 = new BitSet(mk_tokenSet_21());
	private static final long[] mk_tokenSet_22() {
		long[] data = { 1126724548925440L, 16270134986452893L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_22 = new BitSet(mk_tokenSet_22());
	private static final long[] mk_tokenSet_23() {
		long[] data = { 17999000993005424L, 16265702575966989L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_23 = new BitSet(mk_tokenSet_23());
	private static final long[] mk_tokenSet_24() {
		long[] data = { 1126724548925440L, 16272742013688671L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_24 = new BitSet(mk_tokenSet_24());
	private static final long[] mk_tokenSet_25() {
		long[] data = { 17998990121369456L, 16265702575966981L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_25 = new BitSet(mk_tokenSet_25());
	private static final long[] mk_tokenSet_26() {
		long[] data = { 0L, 8589934594L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_26 = new BitSet(mk_tokenSet_26());
	private static final long[] mk_tokenSet_27() {
		long[] data = { 144099790601322482L, 16287731467464605L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_27 = new BitSet(mk_tokenSet_27());
	private static final long[] mk_tokenSet_28() {
		long[] data = { 6597069766658L, 35214436859946L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_28 = new BitSet(mk_tokenSet_28());
	private static final long[] mk_tokenSet_29() {
		long[] data = { 576467349373190146L, 37864423292922L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_29 = new BitSet(mk_tokenSet_29());
	private static final long[] mk_tokenSet_30() {
		long[] data = { 0L, 2L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_30 = new BitSet(mk_tokenSet_30());
	private static final long[] mk_tokenSet_31() {
		long[] data = { 1126724548925440L, 16307947860614015L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_31 = new BitSet(mk_tokenSet_31());
	private static final long[] mk_tokenSet_32() {
		long[] data = { 0L, 35205846925346L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_32 = new BitSet(mk_tokenSet_32());
	private static final long[] mk_tokenSet_33() {
		long[] data = { 13370061452443648L, 9015995348811784L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_33 = new BitSet(mk_tokenSet_33());
	private static final long[] mk_tokenSet_34() {
		long[] data = { 8796093022208L, 8589934596L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_34 = new BitSet(mk_tokenSet_34());
	private static final long[] mk_tokenSet_35() {
		long[] data = { 1125899931975680L, 9007199255789570L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_35 = new BitSet(mk_tokenSet_35());
	private static final long[] mk_tokenSet_36() {
		long[] data = { 0L, 25769803778L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_36 = new BitSet(mk_tokenSet_36());
	private static final long[] mk_tokenSet_37() {
		long[] data = { 824633726976L, 16256829173530629L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_37 = new BitSet(mk_tokenSet_37());
	private static final long[] mk_tokenSet_38() {
		long[] data = { 7421703493634L, 16307956454785023L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_38 = new BitSet(mk_tokenSet_38());
	private static final long[] mk_tokenSet_39() {
		long[] data = { 0L, 68719479552L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_39 = new BitSet(mk_tokenSet_39());
	private static final long[] mk_tokenSet_40() {
		long[] data = { 6597069766658L, 37830063552234L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_40 = new BitSet(mk_tokenSet_40());
	private static final long[] mk_tokenSet_41() {
		long[] data = { 6597069766658L, 37864423292922L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_41 = new BitSet(mk_tokenSet_41());
	private static final long[] mk_tokenSet_42() {
		long[] data = { 0L, 17179869186L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_42 = new BitSet(mk_tokenSet_42());
	private static final long[] mk_tokenSet_43() {
		long[] data = { 1126724548925442L, 16272742013688669L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_43 = new BitSet(mk_tokenSet_43());
	private static final long[] mk_tokenSet_44() {
		long[] data = { 6597069766658L, 35216584343594L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_44 = new BitSet(mk_tokenSet_44());
	private static final long[] mk_tokenSet_45() {
		long[] data = { 6597069766658L, 35216584605738L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_45 = new BitSet(mk_tokenSet_45());
	private static final long[] mk_tokenSet_46() {
		long[] data = { 6597069766658L, 35216584736810L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_46 = new BitSet(mk_tokenSet_46());
	private static final long[] mk_tokenSet_47() {
		long[] data = { 6597069766658L, 35216585785386L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_47 = new BitSet(mk_tokenSet_47());
	private static final long[] mk_tokenSet_48() {
		long[] data = { 6597069766658L, 35216587882538L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_48 = new BitSet(mk_tokenSet_48());
	private static final long[] mk_tokenSet_49() {
		long[] data = { 6597069766658L, 35216588406826L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_49 = new BitSet(mk_tokenSet_49());
	private static final long[] mk_tokenSet_50() {
		long[] data = { 6597069766658L, 35216705847338L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_50 = new BitSet(mk_tokenSet_50());
	private static final long[] mk_tokenSet_51() {
		long[] data = { 6597069766658L, 35218719113258L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_51 = new BitSet(mk_tokenSet_51());
	private static final long[] mk_tokenSet_52() {
		long[] data = { 6597069766658L, 35631035973674L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_52 = new BitSet(mk_tokenSet_52());
	private static final long[] mk_tokenSet_53() {
		long[] data = { 6597069766658L, 37830059229802L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_53 = new BitSet(mk_tokenSet_53());
	private static final long[] mk_tokenSet_54() {
		long[] data = { 6597069766658L, 37830063554538L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_54 = new BitSet(mk_tokenSet_54());
	private static final long[] mk_tokenSet_55() {
		long[] data = { 0L, 32L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_55 = new BitSet(mk_tokenSet_55());
	private static final long[] mk_tokenSet_56() {
		long[] data = { 1125899965530112L, 9015995348811784L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_56 = new BitSet(mk_tokenSet_56());
	
	}
