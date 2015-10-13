package sketch.compiler.dataflow.nodesToSB;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import sketch.compiler.ast.core.Annotation;
import sketch.compiler.ast.core.Function;
import sketch.compiler.ast.core.NameResolver;
import sketch.compiler.ast.core.Package;
import sketch.compiler.ast.core.Parameter;
import sketch.compiler.ast.core.Program;
import sketch.compiler.ast.core.TempVarGen;
import sketch.compiler.ast.core.exprs.ExprConstInt;
import sketch.compiler.ast.core.exprs.ExprFunCall;
import sketch.compiler.ast.core.exprs.Expression;
import sketch.compiler.ast.core.stmts.Statement;
import sketch.compiler.ast.core.stmts.StmtAssert;
import sketch.compiler.ast.core.stmts.StmtAssign;
import sketch.compiler.ast.core.stmts.StmtIfThen;
import sketch.compiler.ast.core.stmts.StmtReturn;
import sketch.compiler.ast.core.stmts.StmtVarDecl;
import sketch.compiler.ast.core.typs.StructDef;
import sketch.compiler.ast.core.typs.StructDef.StructFieldEnt;
import sketch.compiler.ast.core.typs.Type;
import sketch.compiler.ast.core.typs.TypeArray;
import sketch.compiler.ast.core.typs.TypePrimitive;
import sketch.compiler.ast.core.typs.TypeStructRef;
import sketch.compiler.ast.cuda.exprs.CudaThreadIdx;
import sketch.compiler.ast.cuda.stmts.CudaSyncthreads;
import sketch.compiler.dataflow.MethodState.Level;
import sketch.compiler.dataflow.PartialEvaluator;
import sketch.compiler.dataflow.abstractValue;
import sketch.compiler.dataflow.recursionCtrl.RecursionControl;
import sketch.compiler.solvers.constructs.ValueOracle;
import sketch.util.exceptions.ExceptionAtNode;
/**
 * This class translates the ast into a boolean function which is output to a file.
 * The format is suitable for the SBitII backend solver.<BR>
 * Preconditions:
 * <DL>
 * <DT>  Arrays
 * <DD>  * Only 1 dimensional arrays allowed.
 * <DD>  * The [] operator can only be used on an array variable.
 * <DD>  * binary operators work only for scalars.
 * <DD>  * Array to array assignments are supported. (arr1 = arr2)
 * <DD>  * Array ranges with len != 1 are not supported.
 * <DD>  * Scalar to array assignments are supported.
 * </DL>
 * 
 * @author asolar
 */
public class ProduceBooleanFunctions extends PartialEvaluator {
    boolean tracing = false;

    int maxArrSize;
    class SpecSketch{
        public final String spec;
        public final String sketch;
        SpecSketch(String spec, String sketch){ this.spec = spec; this.sketch = sketch; }
        public String toString(){
            return sketch + " SKETCHES " + spec;
        }
    }
    List<SpecSketch> assertions = new ArrayList<SpecSketch>();
    List<Statement> todoStmts = new ArrayList<Statement>();
    private void dischargeTodo(){
        for(Statement s : todoStmts){
            s.accept(this);
        }
        todoStmts.clear();
    }
    public ProduceBooleanFunctions(TempVarGen varGen, 
            ValueOracle oracle, PrintStream out, int maxUnroll, int maxArrSize, RecursionControl rcontrol, boolean tracing){
        super(new NtsbVtype(oracle, out), varGen, false, maxUnroll, rcontrol);
        this.tracing = tracing;
        if(tracing){
            rcontrol.activateTracing();
        }
        this.maxArrSize = maxArrSize;
        state.useRetTracker();
    }

    private String convertType(Type type) {
        // This is So Wrong in the greater scheme of things.
        if (type instanceof TypeArray)
        {
            TypeArray array = (TypeArray)type;
            String base = convertType(array.getBase());
            abstractValue iv = (abstractValue) array.getLength().accept(this);          
            return base + "[" + iv + "]";
        }
 else if (type instanceof TypeStructRef)
        {
            return ((TypeStructRef) type).getName();
        }
        else if (type instanceof TypePrimitive)
        {
            switch (((TypePrimitive)type).getType())
            {
                case TypePrimitive.TYPE_BIT:
                    return "bit";
                case TypePrimitive.TYPE_INT:
                    return "int";
                case TypePrimitive.TYPE_FLOAT:
                    return "float";
                case TypePrimitive.TYPE_DOUBLE:
                    return "double";
                case TypePrimitive.TYPE_VOID:
                    return "void";
            }
        }
        return null;
    }

    List<String> opnames;
    List<Integer> opsizes;

    private boolean visitingALen=false;



    public Object visitTypeArray(TypeArray t) {
        String extra = " here base " + t.getBase() + " len " + t.getLength();
        Type nbase = (Type)t.getBase().accept(this);
        visitingALen = true;
        abstractValue avlen = null;
        Expression nlen;
        try{
            if (t.getLength() != null) {
                avlen = (abstractValue) t.getLength().accept(this);
                nlen = this.exprRV;
            } else {
                avlen = vtype.BOTTOM("FARRAY");
                nlen = null;
            }
        }finally{
            visitingALen = false;
        }
        Expression elen = t.getLength();
        /*
         * if(avlen.isBottom()){ //nlen = maxArrSize; String msg =
         * "Arrays are not big enough" + elen + ">" + nlen; todoStmts.add(new
         * StmtAssert(new ExprBinary(elen, "<=", nlen), msg +extra, false)); }else{ nlen =
         * ExprConstInt.createConstant(avlen.getIntVal()); }
         */
        return t;
        // if(nbase == t.getBase() && t.getLength() == nlen ) return t;
        // return new TypeArray(nbase, nlen) ;
    }

    // if (formal.getType().isArray()) {
    // int maxlength = ((TypeArray) formal.getType()).getMaxlength();
    // if (maxlength > 0) {
    // return new ExprConstInt(maxlength);
    // }
    // }

    /*
     * protected Expression interpretActualParam(Expression e){ return maxArrSize; }
     */

    public void doParams(List<Parameter> params, List<String> addInitStmts) {
        PrintStream out = ((NtsbVtype)this.vtype).out;
        boolean first = true;

        out.print("(");

        for(Iterator<Parameter> iter = params.iterator(); iter.hasNext(); ){
            Parameter param = iter.next();
            Type ptype = (Type) param.getType().accept(this);
            if (!first) out.print(", ");
            first = false;
            if(param.isParameterOutput()) out.print("! ");
            out.print(printType(ptype) + " ");
            String lhs = param.getName();

            if(param.isParameterOutput()){
                state.outVarDeclare(lhs , ptype);
            }else{
                state.varDeclare(lhs , ptype);
            }
            IntAbsValue inval = (IntAbsValue) state.varValue(lhs);
            String invalName = inval.toString();

            if( ptype instanceof TypeArray ){
                TypeArray ta = (TypeArray) ptype;
                if (inval.isVect()) {
                    IntAbsValue tmp = (IntAbsValue) ta.getLength().accept(this);
                    assert inval.isVect() : "If it is not a vector, something is really wrong.\n";
                    int sz = tmp.getIntVal();
                    if (param.isParameterOutput()) {
                        opsizes.add(sz);
                    }
                    for (int tt = 0; tt < sz; ++tt) {
                        String nnm = inval.getVectValue().get(tt).toString();
                        if (param.isParameterOutput()) {
                            String opname =
                                    "_p_" + filterPound(param.getName()) + "_idx_" + tt +
                                            " ";
                            opnames.add(opname);
                            out.print(opname);
                        } else {
                            out.print(nnm + " ");
                        }
                    }
                } else {
                    if(param.isParameterOutput()){
                        opsizes.add(1);
                        String opname = "_p_" + filterPound(param.getName()) + " ";
                        opnames.add(opname);
                        out.print(opname);
                        // Armando: This statement below is experimental.
                        if (!param.isParameterInput()) {
                            state.setVarValue(
                                    lhs,
                                    (abstractValue) ta.getBase().defaultValue().accept(
                                            this));
                        }
                    }else{
                        out.print(invalName + " ");
                    }
                }
            }else{
                if(param.isParameterOutput()){
                    opsizes.add(1);
                    String opname = "_p_" + filterPound(param.getName()) + " ";
                    opnames.add(opname);
                    out.print(opname);
                }else{
                    out.print(invalName + " ");
                }
            }

            if (param.isParameterOutput()) {
                if (param.isParameterInput()) {
                    out.print(", ");
                    out.print(printType(ptype) + " ");
                    if (ptype instanceof TypeArray) {
                        if (inval.isVect()) {
                            TypeArray ta = (TypeArray) ptype;
                            IntAbsValue tmp = (IntAbsValue) ta.getLength().accept(this);
                            assert inval.isVect() : "If it is not a vector, something is really wrong.\n";
                            int sz = tmp.getIntVal();
                            for (int tt = 0; tt < sz; ++tt) {
                                String nnm = inval.getVectValue().get(tt).toString();
                                {
                                    out.print(nnm + " ");
                                }
                            }
                        } else {
                            out.print(invalName + " ");
                        }
                    } else {
                        out.print(invalName + " ");
                    }
                } else {
                    if (ptype instanceof TypeArray) {
                        if (inval.isVect()) {
                            TypeArray ta = (TypeArray) ptype;
                            IntAbsValue tmp = (IntAbsValue) ta.getLength().accept(this);
                            assert inval.isVect() : "If it is not a vector, something is really wrong.\n";
                            int sz = tmp.getIntVal();
                            for (int tt = 0; tt < sz; ++tt) {
                                String nnm = inval.getVectValue().get(tt).toString();
                                addInitStmts.add(nnm + "=0;");
                            }
                        } else {
                            addInitStmts.add(invalName + "=0;");
                        }
                    } else {
                        addInitStmts.add(invalName + "=0;");
                    }
                }
            }

        }
        out.print(")");     
    }

    static String filterPound(String s) {
        if (s.length() > 0 && s.charAt(0) == '#') {
            return s.substring(1);
        } else {
            return s;
        }
    }

    private String printType(Type type){
        if (type instanceof TypeArray)
        {
            TypeArray array = (TypeArray)type;
            String base = printType(array.getBase());
            abstractValue iv;
            if (array.getLength() != null) {
                iv = (abstractValue) array.getLength().accept(this);
            } else {
                iv = vtype.BOTTOM("FARRAY");
            }
            if (iv.isBottom()) {
                return base + "[*" + maxArrSize + "]";
            } else {
                return base + "[" + iv + "]";
            }
        }
        if (type instanceof TypeStructRef) {
            TypeStructRef ts = (TypeStructRef) type;
            StructDef struct = nres.getStruct(ts.getName());
            if(struct.immutable()){
                return struct.getName().toUpperCase() + "_" +
                        struct.getPkg().toUpperCase();
            }
        }

        if(type.equals(TypePrimitive.bittype)){
            return "bit";
        }else{
            if (type.equals(TypePrimitive.floattype) ||
                    type.equals(TypePrimitive.doubletype))
            {
                return "float";
            } else {
                return "int";
            }
        }
    }

    public void doOutParams(List<Parameter> params) {
        PrintStream out = ((NtsbVtype)this.vtype).out;
        boolean first = true;
        Iterator<Integer> opsz = opsizes.iterator();
        Iterator<String> opnm = opnames.iterator();
        for(Iterator<Parameter> iter = params.iterator(); iter.hasNext(); ){
            Parameter param = iter.next();
            first = false;
            String lhs = param.getName();
            if(param.isParameterOutput()){          
                IntAbsValue inval = (IntAbsValue)state.varValue(lhs);
                assert opsz.hasNext() : "This can't happen.";
                int sz = opsz.next();
                for(int tt=0; tt<sz; ++tt){
                    String nnm = null;
                    if( inval.isVect() ){
                        nnm = inval.getVectValue().get(tt).toString();
                    }else{
                        assert tt == 0;
                        nnm = inval.toString();
                    }
                    String onm = opnm.next();
                    out.println(onm + " = " + nnm + ";");
                }
            }
        }
    }

    public Object visitStmtReturn(StmtReturn sr) {
        state.testReturn();
        return sr;
    }

    class inRange {
        public final int low;
        public final int high;
        public final String name;

        inRange(String annotation, Function f) {
            int ls = annotation.indexOf(':');
            int hs = annotation.indexOf(':', ls + 1);
            if (ls > 0 && ls < hs && hs < annotation.length()) {
                String lo = annotation.substring(ls + 1, hs);
                String hi = annotation.substring(hs + 1, annotation.length());
                try {
                low = Integer.decode(lo);
                high = Integer.decode(hi);
                } catch (NumberFormatException nfe) {
                    throw new ExceptionAtNode(
                            "The syntax for inrange is @inrange(\"param:lower:upper\") where lower and upper are integers.",
                            f);
                }
                name = annotation.substring(0, ls);
            } else {
                throw new ExceptionAtNode(
                        "The syntax for inrange is @inrange(\"param:lower:upper\")", f);
            }

        }

    }

    boolean isMain(Function func) {
        return mainfuns.contains(func.getName());
    }

    public Object visitFunction(Function func)
    {
        if(tracing)
            System.out.println("Analyzing " + func.getName() + " " + new java.util.Date());

        if (func.isModel()) {
            ((NtsbVtype) this.vtype).out.print("mdl_def " + func.getName());
        } else {
            ((NtsbVtype) this.vtype).out.print("def " + func.getName());
        }

        if( func.getSpecification() != null ){
            assertions.add(new SpecSketch(func.getSpecification(), func.getName() ));
        }

        List<Integer> tmpopsz = opsizes;
        List<String> tmpopnm = opnames;

        opsizes = new ArrayList<Integer>();
        opnames = new ArrayList<String>();

        Level lvl = state.beginFunction(func.getName());
        List<String> initStmts = new ArrayList<String>();
        doParams(func.getParams(), initStmts);

        PrintStream out = ((NtsbVtype) this.vtype).out;
        out.println("{");
        if (func.isWrapper()) {
            String wname = func.getName();
            int ix = 0;
            do {
                ix = wname.indexOf("__Wrapper", 0);
            } while (wname.indexOf("__Wrapper", ix + 1) >= 0);
            String hname = func.getName().substring(0, ix);
            Function tfun = nres.getFun(hname);
            int ii = 0;
            while (tfun == null && ii < 10) {
                tfun = nres.getFun(hname + ii);
                ++ii;
            }
            if (tfun != null) {
                Vector<Annotation> annot = tfun.getAnnotation("inrange");
                for (Annotation a : annot) {
                    inRange ir = new inRange(a.contents(), tfun);
                    String pname = "";
                    boolean found = false;
                    Parameter thep = null;
                    for (Parameter p : func.getParams()) {
                        pname = p.getName();
                        if (pname.length() > ir.name.length()) {
                            if (pname.substring(0, ir.name.length()).equals(ir.name)) {
                                found = true;
                                thep = p;
                                break;
                            }
                        }
                    }
                    if (!found) {
                        throw new ExceptionAtNode("Parameter " + ir.name +
                                " from annotation " + a + " not found.", func);
                    }
                    if (!thep.getType().equals(TypePrimitive.inttype)) {
                        throw new ExceptionAtNode(
                                "inrange annotation only allowed for int parameters: " +
                                        a, func);
                    }
                    abstractValue av = state.varValue(pname);
                    state.setVarValue(pname, vtype.plus(av, vtype.CONST(ir.low)));
                    vtype.Assume(vtype.le(state.varValue(pname), vtype.CONST(ir.high)),
                            null);

                }
            }
        } else {
            /*
             * Vector<Annotation> annot = func.getAnnotation("inrange"); if (annot.size()
             * > 0 && !func.isSketchHarness()) { throw new ExceptionAtNode(
             * "@inrange annotation only allowed in harness functions.", func); }
             */
        }
        

        
        
        for (String s : initStmts) {
            out.println(s);
        }
        dischargeTodo();
        Statement newBody = (Statement)func.getBody().accept(this);

        state.handleReturnTrackers();
        doOutParams(func.getParams());

        state.endFunction(lvl);
        out.println("}");

        opsizes = tmpopsz;
        opnames = tmpopnm;

        if (tracing)
            System.out.println("Analyzed " + func.getName() + " " + new java.util.Date());

        return func;
    }

    Set<String> mainfuns = new HashSet<String>();

    public Object visitProgram(Program p) {
        PrintStream out = ((NtsbVtype) this.vtype).out;
        out.println("typedef{");
        nres = new NameResolver(p);
        for (Package pkg : p.getPackages()) {
            nres.setPackage(pkg);
            for (StructDef t : pkg.getStructs()) {
                if (t.immutable()) {
                    out.print(t.getName().toUpperCase() + "_" + t.getPkg().toUpperCase() +
                            " ( ");
                    for (StructFieldEnt e : t.getFieldEntriesInOrder()) {
                        out.print(printType(e.getType()) + " ");
                    }
                    out.println(")");
                }
            }
            for (Function f : pkg.getFuncs()) {
                if (f.getSpecification() != null) {
                    mainfuns.add(f.getName());
                    mainfuns.add(f.getSpecification());
                }
            }
        }
        out.println("}");

        Object o = super.visitProgram(p);
        for (SpecSketch s : assertions) {
            ((NtsbVtype) this.vtype).out.println("assert " + s + ";");
        }
        return o;
    }

    public Object visitExprFunCall(ExprFunCall exp) {
        String name = exp.getName();
        // Local function?
        Function fun = nres.getFun(name);
        String funPkg = fun.getPkg();
        if (fun.getSpecification() != null) {
            assert false : "The substitution of sketches for their respective specs should have been done in a previous pass.";
        }
        if (fun != null) {
            if (fun.isUninterp()) {
                Object o = super.visitExprFunCall(exp);
                dischargeTodo();
                return o;
            } else {
                if (rcontrol.testCall(exp)) {
                    /* Increment inline counter. */
                    rcontrol.pushFunCall(exp, fun);

                    List<Statement> oldNewStatements = newStatements;
                    newStatements = new ArrayList<Statement>();
                    Level lvl2 = state.pushFunCall(exp.getName());
                    try {
                        Level lvl;
                        {
                            Iterator<Expression> actualParams =
                                    exp.getParams().iterator();
                            Iterator<Parameter> formalParams = fun.getParams().iterator();
                            lvl =
                                    inParameterSetter(exp, formalParams, actualParams,
                                            false);
                        }
                        Statement body = null;
                        try {

                            body = (Statement) fun.getBody().accept(this);
                        } catch (RuntimeException ex) {
                            state.popLevel(lvl); // This is to compensate for a pushLevel
                            // in inParamSetter.
                            // Under normal circumstances, this gets offset by a popLevel
                            // in outParamSetter, but not in the pressence of exceptions.
                            throw ex;
                        }
                        addStatement(body);
                        {
                            Iterator<Expression> actualParams =
                                    exp.getParams().iterator();
                            Iterator<Parameter> formalParams = fun.getParams().iterator();
                            outParameterSetter(formalParams, actualParams, false, lvl);
                        }
                    } finally {
                        state.popFunCall(lvl2);
                        newStatements = oldNewStatements;
                    }
                    rcontrol.popFunCall(exp);
                } else {
                    if (rcontrol.leaveCallsBehind()) {
                        // System.out.println("        Stopped recursion:  " +
                        // fun.getName());
                        funcsToAnalyze.add(fun);
                        Object o = super.visitExprFunCall(exp);
                        dischargeTodo();
                        return o;
                    } else {
                        StmtAssert sas = new StmtAssert(exp, ExprConstInt.zero, false);
                        sas.setMsg((exp != null ? exp.getCx().toString() : "") +
                                exp.getName());
                        sas.accept(this);
                    }
                }
                exprRV = exp;
                return vtype.BOTTOM();
            }
        }
        exprRV = null;
        return vtype.BOTTOM();
    }

    private Object tmp = null;

    @Override

    public Object visitStmtIfThen(StmtIfThen s) {
        if (tracing) {
            // if(s.getCx() != tmp && s.getCx() != null){
            abstractValue av = (abstractValue) s.getCond().accept(this);
            System.out.println(s.getCx() + " : \t cond(" + s.getCond() + ")   " + av);
            tmp = s.getCx();
            // }
        }
        return super.visitStmtIfThen(s);
    }

    public Object visitStmtVarDecl(StmtVarDecl s) {
        if (tracing) {
            // if(s.getCx() != tmp && s.getCx() != null){

            tmp = s.getCx();
            if (s.getNumVars() == 1 && s.getInit(0) != null) {
                abstractValue av = (abstractValue) s.getInit(0).accept(this);
                System.out.println(s.getCx() + " : \t" + s + "\t rhs=" + av);
            } else {
                System.out.println(s.getCx() + " : \t" + s);
            }
            // }
        }
        Object o = super.visitStmtVarDecl(s);
        dischargeTodo();
        return o;
    }

    public Object visitStmtAssert(StmtAssert sa) {
        return super.visitStmtAssert(sa);
    }

    public Object visitStructDef(StructDef ts) {
        return ts;
    }

    public Object visitStmtAssign(StmtAssign s) {
        if (tracing) {
            // if(s.getCx() != tmp && s.getCx() != null){
            abstractValue av = (abstractValue) s.getRHS().accept(this);
            System.out.println(s.getCx() + " : \t" + s + "\t rhs=" + av);
            tmp = s.getCx();
            // }
        }
        String str = s.getLHS().toString();
        return super.visitStmtAssign(s);

    }


    @Override
    public Object visitCudaThreadIdx(CudaThreadIdx cudaThreadIdx) {
        throw new ExceptionAtNode("Cuda threadIdx should be erased by now! "
                + "Did you forget to add a \"device\" qualifier to the function?",
                cudaThreadIdx);
    }

    @Override
    public Object visitCudaSyncthreads(CudaSyncthreads cudaSyncthreads) {
        throw new ExceptionAtNode("Cuda __syncthreads() should be erased by now! "
                + "Did you forget to add a \"device\" qualifier to the function?",
                cudaSyncthreads);
    }
}
