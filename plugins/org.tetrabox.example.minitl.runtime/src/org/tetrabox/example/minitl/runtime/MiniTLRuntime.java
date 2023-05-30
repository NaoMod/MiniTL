package org.tetrabox.example.minitl.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.tetrabox.example.minitl.Binding;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.exceptions.UnknownBreakpointTypeException;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.runtime.serializers.IDRegistry;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;

/**
 * Manages the execution of steps for a MiniTL program.
 */
public class MiniTLRuntime implements Runnable {
	
	private Transformation transformation;
	private List<String> transformationArgs;
	
	private Binding nextBinding;
	private Set<Rule> appliedRules;
	
	private boolean isPaused;
	
	public MiniTLRuntime(Transformation transformation, List<String> transformationArgs) {
		this.transformation = transformation;
		this.transformationArgs = transformationArgs;
		appliedRules = new HashSet<>();
		isPaused = false;
	}
	
	@Override
	public synchronized void run() {
		TransformationAspect.initialize(transformation, transformationArgs);
		TransformationAspect.execute(transformation);
	}

	/**
	 * Executes a step, as defined in the Kermeta3 semantics.
	 * When a binding assignment is about to be performed, the running thread is paused and all threads waiting on
	 * this runtime are notified.
	 * 
	 * @param caller The object on which the step is called.
	 * @param command The step command about to be performed.
	 * @param className The name of the class of the caller.
	 * @param methodName The name of the method implementing the step.
	 * @throws InterruptedException If the running thread is interrupted.
	 */
	public synchronized void executeStep(Object caller, StepCommand command, String className, String methodName) throws InterruptedException {
		if (className.equals("Binding") && methodName.equals("assign")) {
			nextBinding = (Binding) caller;
			
			isPaused = true;
			notifyAll();
			
			while (isPaused) {
				wait();
			}
			
			appliedRules.add(findContainingRule(nextBinding));
			nextBinding = null;
		}
		
		command.execute();
	}
	
	/**
	 * Checks whether a breakpoint is activated.
	 * 
	 * @param type The type of breakpoint to check.
	 * @param elementId The id of the element to which the breakpoint is assigned.
	 * @return The LRP response to the request.
	 * @throws UnknownBreakpointTypeException If the breakpoint type is not defined by the language runtime.
	 */
	public synchronized CheckBreakpointResponse checkBreakpoint(String type, String elementId) throws UnknownBreakpointTypeException {
        if (isExecutionDone())
            return new CheckBreakpointResponse(false);

        boolean isActivated = false;
        String message = null;
        
        switch (type) {
            case "ruleApplied":
            	Rule rule = findContainingRule(getNextBinding());
            	isActivated = !appliedRules.contains(rule) && IDRegistry.getId(rule).equals(elementId);
            	
            	if (isActivated) {
            		message = "Rule " + rule.getName() + " is about to be applied.";
            	}

                break;

            case "featureAssignedValue":
                isActivated = IDRegistry.getId(nextBinding).equals(elementId);

                if (isActivated) message = "Feature '" + nextBinding.getFeature().getName() + "' is about to be assigned a value.";

                break;

            default:
                throw new UnknownBreakpointTypeException(type);
        }
	
        return new CheckBreakpointResponse(isActivated, message);
    }
	
	public synchronized Rule getCurrentRule() {
		if (nextBinding == null) return null;
		
		return findContainingRule(nextBinding);
	}
	
	public synchronized Binding getNextBinding() {
		return nextBinding;
	}
	
	public synchronized Transformation getTransformation() {
		return transformation;
	}
	
	public synchronized boolean isExecutionDone() {
		return nextBinding == null;
	}
	
	public synchronized boolean isPaused() {
		return isPaused;
	}

	public synchronized void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	/**
	 * Finds the rule containing an EObject.
	 * 
	 * @param object The EObject for which to search for a containing rule.
	 * @return The rule containing the EObject, or null if there is none.
	 */
	private Rule findContainingRule(EObject object) {
		Rule rule = null;
        EObject currentAncestor = object.eContainer();
        
        // find containing rule
        while (rule == null && currentAncestor != null) {
        	if (currentAncestor instanceof Rule) rule = (Rule) currentAncestor;
        	currentAncestor = currentAncestor.eContainer();
        }
        
        return rule;
	}
}
