package org.tetrabox.example.minitl.runtime;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.tetrabox.example.MinitlStandaloneSetup;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointArguments;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.runtime.lrp.GetBreakpointTypesResponse;
import org.tetrabox.example.minitl.runtime.lrp.GetRuntimeStateArguments;
import org.tetrabox.example.minitl.runtime.lrp.GetRuntimeStateResponse;
import org.tetrabox.example.minitl.runtime.lrp.InitResponse;
import org.tetrabox.example.minitl.runtime.lrp.ModelElementFactory;
import org.tetrabox.example.minitl.runtime.lrp.ParseArguments;
import org.tetrabox.example.minitl.runtime.lrp.ParseResponse;
import org.tetrabox.example.minitl.runtime.lrp.StepArguments;
import org.tetrabox.example.minitl.runtime.lrp.StepResponse;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

import com.google.inject.Injector;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry;


public class LRPHandler implements ILRPHandler {
	private Injector injector;

	private final String BASE_FOLDER = "/org.tetrabox.example.minitl.examples/transfos";
	
    private Map<String, Transformation> transformations;
    private MiniTLStepManager stepManager;
    
    public LRPHandler() {
        transformations = new HashMap<>();
        injector = new MinitlStandaloneSetup().createInjectorAndDoEMFRegistration();
        
        stepManager = new MiniTLStepManager();
        StepManagerRegistry.getInstance().registerManager(stepManager);
    }

    @Override
    public ParseResponse parse(ParseArguments args) throws Exception {
    	XtextResourceSet rs = injector.<XtextResourceSet>getInstance(XtextResourceSet.class);
    	
    	String path = args.getSourceFile().split(BASE_FOLDER)[1];
    	
    	String platformPath = Paths.get(BASE_FOLDER, path).toString();
        URI transfoFileURI = URI.createPlatformPluginURI(platformPath, true);
        Resource transfoResource = rs.getResource(transfoFileURI, true);
        
        Path parentPath = Paths.get(path).getParent();
        String parentPathString = parentPath == null ? "" : parentPath.toString();
        
        String currentPathString = new File(".").getAbsolutePath();
        File folder = new File(Paths.get(Paths.get(currentPathString).getParent().getParent().toString(), BASE_FOLDER).toString()  + parentPathString);
                
        for (File file : folder.listFiles()) {
			if (file.getName().endsWith(".ecore")) {
				URI mmURI = URI.createPlatformPluginURI(Paths.get(BASE_FOLDER, parentPathString, file.getName()).toString(), true);
				rs.getResource(mmURI, true);
			}
		}
        
        Transformation transformation = (Transformation) transfoResource.getContents().get(0);
        transformations.put(args.getSourceFile(), transformation);

        return new ParseResponse(ModelElementFactory.fromTransformation(transformation));
    }

    @Override
    public GetBreakpointTypesResponse getBreakpointTypes() {
        return new GetBreakpointTypesResponse(BreakpointTypes.getBreakpointTypes());
    }

    @Override
    public InitResponse initExecution(CustomInitArguments args) throws Exception {
        if (!transformations.containsKey(args.getSourceFile()))
            throw new Exception("No AST for file \'" + args.getSourceFile() + "\'.");
        
        String inputPath = args.getInputModel().split(BASE_FOLDER)[1];
        String inputModelPlatformPath = Paths.get(BASE_FOLDER, inputPath).toString();
        String inputModelURIString = URI.createPlatformPluginURI(inputModelPlatformPath, true).toString();
        
        String outputPath = args.getOutputModel().split(BASE_FOLDER)[1];
        String outputModelPlatformPath = Paths.get(BASE_FOLDER, outputPath).toString();
        String ouputModelURIString = URI.createPlatformPluginURI(outputModelPlatformPath, true).toString();
        
        List<String> transformationArgs = List.of(inputModelURIString, ouputModelURIString);
        Transformation transformation = transformations.get(args.getSourceFile());
        TransformationAspect.initialize(transformation, transformationArgs);
        stepManager.addTransformation(transformation);

        return new InitResponse(stepManager.isExecutionDone(transformation, true));
    }

    @Override
    public StepResponse nextStep(StepArguments args) throws Exception {
        checkRuntimeExists(args.getSourceFile());

        stepManager.nextStep(transformations.get(args.getSourceFile()));

        return new StepResponse(stepManager.isExecutionDone(transformations.get(args.getSourceFile()), false));
    }

    @Override
    public GetRuntimeStateResponse getRuntimeState(GetRuntimeStateArguments args) throws Exception {
        checkRuntimeExists(args.getSourceFile());
        Transformation transformation = transformations.get(args.getSourceFile());

        return new GetRuntimeStateResponse(ModelElementFactory.fromMiniTLRuntime(stepManager.getRuntime(transformation)));
    }

    @Override
    public CheckBreakpointResponse checkBreakpoint(CheckBreakpointArguments args) throws Exception {
        checkRuntimeExists(args.getSourceFile());

        return stepManager.checkBreakpoint(transformations.get(args.getSourceFile()), args.getTypeId(), args.getElementId());
    }

    private void checkRuntimeExists(String sourceFile) throws Exception {
    	Transformation transformation = transformations.get(sourceFile);
        if (stepManager.getRuntime(transformation) == null)
            throw new Exception("No runtime for file \'" + sourceFile + "\'.");
    }
}
