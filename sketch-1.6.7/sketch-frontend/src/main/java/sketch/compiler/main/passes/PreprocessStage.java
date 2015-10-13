package sketch.compiler.main.passes;

import sketch.compiler.ast.core.Program;
import sketch.compiler.ast.core.TempVarGen;
import sketch.compiler.cmdline.SolverOptions.ReorderEncoding;
import sketch.compiler.dataflow.cflowChecks.PerformFlowChecks;
import sketch.compiler.dataflow.preprocessor.PreprocessSketch;
import sketch.compiler.dataflow.preprocessor.TypeInferenceForStars;
import sketch.compiler.dataflow.recursionCtrl.RecursionControl;
import sketch.compiler.main.cmdline.SketchOptions;
import sketch.compiler.passes.lowering.*;
import sketch.compiler.passes.optimization.ReplaceMinLoops;
import sketch.compiler.passes.preprocessing.EliminateFieldHoles;
import sketch.compiler.passes.preprocessing.EliminateTripleEquals;
import sketch.compiler.passes.preprocessing.ExpandADTHoles;
import sketch.compiler.passes.preprocessing.MainMethodCreateNospec;
import sketch.compiler.passes.preprocessing.ReplaceADTHoles;
import sketch.compiler.passes.types.CheckProperFinality;

/**
 * @author Armando Solar-Lezama
 * @license This file is licensed under BSD license, available at
 *          http://creativecommons.org/licenses/BSD/. While not required, if you make
 *          changes, please consider contributing back!
 */
public class PreprocessStage extends MetaStage {
    // protected final CompilerStage preproc1;
    // protected final CompilerStage ir1;
    protected final RecursionControl rctrl;
    protected final boolean partialEval;

    public PreprocessStage(TempVarGen varGen, SketchOptions options,
    /* CompilerStage preproc1, CompilerStage ir1, */RecursionControl rctrl,
            boolean partialEval)
    {
        super("preproc", "Preprocessing (used for all further transformations)", varGen,
                options);
        // this.preproc1 = preproc1;
        // this.ir1 = ir1;
        this.rctrl = rctrl;
        this.partialEval = partialEval;
    }

    @Override
    public Program visitProgramInner(Program prog) {
        boolean useInsertEncoding =
                (options.solverOpts.reorderEncoding == ReorderEncoding.exponential);

        prog = (Program) prog.accept(new EliminateTripleEquals(varGen));
        // prog.debugDump("after expand ===");
        prog = (Program) prog.accept(new SeparateInitializers());
        prog = (Program) prog.accept(new BlockifyRewriteableStmts());
        prog = (Program) prog.accept(new ReplaceMinLoops(varGen));

        // prog.debugDump("After Replace Min Loops");

        // FIXME xzl: temporarily disable ExtractComplexLoopCondition to help stencil
        prog = (Program) prog.accept(new ExtractComplexLoopConditions(varGen));

        prog = (Program) prog.accept(new EliminateRegens(varGen));

        prog = (Program) prog.accept(new EliminateBitSelector(varGen));


        prog.accept(new CheckProperFinality());

        prog = (Program) prog.accept(new MainMethodCreateNospec());

        // prog = preproc1.run(prog);

        // prog = (Program)prog.accept (new BoundUnboundedLoops (varGen, params.flagValue
        // ("unrollamnt")));
        // prog = (Program)prog.accept(new NoRefTypes());
        prog =
                (Program) prog.accept(new EliminateReorderBlocks(varGen,
                        useInsertEncoding));
        prog = (Program) prog.accept(new EliminateInsertBlocks(varGen));
        prog = (Program) prog.accept(new DisambiguateUnaries(varGen));
        


        prog = (Program) prog.accept(new FunctionParamExtension(true, varGen));



        prog = (Program) prog.accept(new ExpandADTHoles());


        prog = (Program) prog.accept(new GlobalsToParams(varGen));

        // prog = ir1.run(prog);
        // prog.debugDump("before type inference");

        prog = (Program) prog.accept(new TypeInferenceForStars());

        prog = (Program) prog.accept(new EliminateFieldHoles());
        //prog.debugDump("af");
        
        prog = (Program) prog.accept(new ReplaceADTHoles());
        if (!SketchOptions.getSingleton().feOpts.lowOverhead) {
            prog.accept(new PerformFlowChecks());
        }
        // prog.debugDump("Before fun call");
        // prog = (Program) prog.accept(new CombineFunctionCalls(varGen));
        // prog.debugDump("After fun call");

        prog = (Program) prog.accept(new EliminateUnboxedStructs(varGen));



        prog = (Program) prog.accept(new EliminateNestedArrAcc(true));


        prog = (Program) prog.accept(new MakeMultiDimExplicit(varGen));
        // prog.debugDump("before preprocess");
        if (partialEval) {
            prog =
                    (Program) prog.accept(new PreprocessSketch(varGen,
                            options.bndOpts.unrollAmnt, rctrl));
            //prog.debugDump("after preprocess");
        }

        return prog;
    }
}
