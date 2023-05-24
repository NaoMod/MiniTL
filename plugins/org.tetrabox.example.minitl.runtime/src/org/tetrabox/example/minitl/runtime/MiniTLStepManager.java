package org.tetrabox.example.minitl.runtime;

import java.util.HashMap;
import java.util.Map;

import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;

public class MiniTLStepManager implements IStepManager {
	
	private Map<Transformation, MiniTLRuntime> runtimes;
	private StepCommand lastSuspendedCommand;
	private Rule lastSuspendedRule;
	
	public MiniTLStepManager() {
		runtimes = new HashMap<>();
	}

	@Override
	public void executeStep(Object caller, StepCommand command, String className, String methodName) {
		if (className.equals("Rule")) {
			lastSuspendedCommand = command;
			lastSuspendedRule = (Rule) caller;
			return;
		}
		
		lastSuspendedCommand = null;
		lastSuspendedRule = null;
		command.execute();
	}

	@Override
	public boolean canHandle(Object caller) {
		return true;
	}
	
	public boolean isExecutionDone(Transformation transformation, boolean initialState) {
		if (initialState) return transformation.getRules().size() == 0;
		
		return runtimes.get(transformation).isExecutionDone();
	}
	
	public void nextStep(Transformation transformation) {
		MiniTLRuntime runtime = runtimes.get(transformation);
		StepCommand command = runtime.getSuspendedCommand();
		
		if (command == null) return;
		
		command.execute();
		runtime.setSuspendedCommand(lastSuspendedCommand);
		runtime.setNextRule(lastSuspendedRule);
	}
	
	public void addTransformation(Transformation transformation) {
		MiniTLRuntime runtime =  new MiniTLRuntime(transformation);
		runtimes.put(transformation, runtime);
		
		TransformationAspect.execute(transformation);
		runtime.setSuspendedCommand(lastSuspendedCommand);
		runtime.setNextRule(lastSuspendedRule);
	}
	
	public MiniTLRuntime getRuntime(Transformation transformation) {
		return runtimes.get(transformation);
	}
	
	public CheckBreakpointResponse checkBreakpoint(Transformation transformation, String type, String elementId) throws Exception {
        return runtimes.get(transformation).checkBreakpoint(type, elementId);
    }
}
