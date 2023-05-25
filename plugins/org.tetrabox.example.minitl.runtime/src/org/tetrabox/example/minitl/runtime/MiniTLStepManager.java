package org.tetrabox.example.minitl.runtime;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;

/**
 * Manages MiniTL runtimes and passes step information to them.
 */
public class MiniTLStepManager implements IStepManager {
	
	private Map<Transformation, MiniTLRuntime> runtimes;
	
	public MiniTLStepManager() {
		runtimes = new HashMap<>();
	}

	@Override
	public void executeStep(Object caller, StepCommand command, String className, String methodName) {
		if (className.equals("Transformation") && methodName.equals("save")) {
			runtimes.get((Transformation) caller).setSaveCommand(command);
			return;
		}
			
		if (className.equals("Rule")) {
			Rule rule = (Rule) caller;
			runtimes.get((Transformation) EcoreUtil.getRootContainer(rule)).addRuleCommand(rule, command);
			return;
		}
		
		command.execute();
	}

	@Override
	public boolean canHandle(Object caller) {
		return true;
	}
	
	public boolean isExecutionDone(Transformation transformation) {
		return runtimes.get(transformation).isExecutionDone();
	}
	
	public void nextStep(Transformation transformation) {
		MiniTLRuntime runtime = runtimes.get(transformation);
		runtime.nextStep();
		runtime.checkSave();
	}
	
	public void addTransformation(Transformation transformation) {
		MiniTLRuntime runtime =  new MiniTLRuntime(transformation);
		runtimes.put(transformation, runtime);
		
		TransformationAspect.execute(transformation);
		runtime.checkSave();
	}
	
	public MiniTLRuntime getRuntime(Transformation transformation) {
		return runtimes.get(transformation);
	}
	
	public CheckBreakpointResponse checkBreakpoint(Transformation transformation, String type, String elementId) throws Exception {
        return runtimes.get(transformation).checkBreakpoint(type, elementId);
    }
}
