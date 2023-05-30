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
import org.tetrabox.example.minitl.runtime.exceptions.ASTNotFoundException;
import org.tetrabox.example.minitl.runtime.exceptions.LocationException;
import org.tetrabox.example.minitl.runtime.exceptions.RuntimeNotFoundException;
import org.tetrabox.example.minitl.runtime.exceptions.UnknownBreakpointTypeException;
import org.tetrabox.example.minitl.runtime.exceptions.ValueTypeException;
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

import com.google.inject.Injector;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry;

/**
 * Implements LRP services for the MiniTL runtime.
 */
public class LRPHandler implements ILRPHandler {
	private Injector injector;

	private static final String BASE_FOLDER = "/org.tetrabox.example.minitl.examples/transfos";
	
    private Map<String, Transformation> transformations;
    private Map<String, MiniTLRuntime> runtimes;
    private MiniTLStepManager stepManager;
    
    public LRPHandler() {
        transformations = new HashMap<>();
        injector = new MinitlStandaloneSetup().createInjectorAndDoEMFRegistration();
        
        runtimes = new HashMap<>();
        
        stepManager = new MiniTLStepManager();
        StepManagerRegistry.getInstance().registerManager(stepManager);
    }

    @Override
    public ParseResponse parse(ParseArguments args) throws LocationException, ValueTypeException {
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
    public InitResponse initExecution(CustomInitArguments args) throws ASTNotFoundException, InterruptedException {
        if (!transformations.containsKey(args.getSourceFile()))
            throw new ASTNotFoundException(args.getSourceFile());
        
        String inputPath = args.getInputModel().split(BASE_FOLDER)[1];
        String inputModelPlatformPath = Paths.get(BASE_FOLDER, inputPath).toString();
        String inputModelURIString = URI.createPlatformPluginURI(inputModelPlatformPath, true).toString();
        
        String outputPath = args.getOutputModel().split(BASE_FOLDER)[1];
        String outputModelPlatformPath = Paths.get(BASE_FOLDER, outputPath).toString();
        String ouputModelURIString = URI.createPlatformPluginURI(outputModelPlatformPath, true).toString();
        
        List<String> transformationArgs = List.of(inputModelURIString, ouputModelURIString);
        Transformation transformation = transformations.get(args.getSourceFile());
        
        MiniTLRuntime runtime = new MiniTLRuntime(transformation, transformationArgs);
        stepManager.registerRuntime(transformation, runtime);
		
        synchronized (runtime) {
        	new Thread(runtime).start();
        	
        	while (!runtime.isPaused()) {
        		runtime.wait();
        	}
		}
		
		runtimes.put(args.getSourceFile(), runtime);

        return new InitResponse(runtime.isExecutionDone());
    }

    @Override
    public StepResponse nextStep(StepArguments args) throws RuntimeNotFoundException, InterruptedException {
        checkRuntimeExists(args.getSourceFile());

        MiniTLRuntime runtime = runtimes.get(args.getSourceFile());
        synchronized (runtime) {
        	runtime.setPaused(false);
        	runtime.notifyAll();
        	
        	while (!runtime.isPaused()) {
        		runtime.wait();
        	}
		}
        
        return new StepResponse(runtime.isExecutionDone());
    }

    @Override
    public GetRuntimeStateResponse getRuntimeState(GetRuntimeStateArguments args) throws RuntimeNotFoundException {
        checkRuntimeExists(args.getSourceFile());

        return new GetRuntimeStateResponse(ModelElementFactory.fromMiniTLRuntime(runtimes.get(args.getSourceFile())));
    }
    
    @Override
    public GetBreakpointTypesResponse getBreakpointTypes() {
        return new GetBreakpointTypesResponse(BreakpointTypes.getAvailableBreakpointTypes());
    }

    @Override
    public CheckBreakpointResponse checkBreakpoint(CheckBreakpointArguments args) throws RuntimeNotFoundException, UnknownBreakpointTypeException {
        checkRuntimeExists(args.getSourceFile());

        return runtimes.get(args.getSourceFile()).checkBreakpoint(args.getTypeId(), args.getElementId());
    }

    private void checkRuntimeExists(String sourceFile) throws RuntimeNotFoundException {
        if (!runtimes.containsKey(sourceFile))
            throw new RuntimeNotFoundException(sourceFile);
    }
}
