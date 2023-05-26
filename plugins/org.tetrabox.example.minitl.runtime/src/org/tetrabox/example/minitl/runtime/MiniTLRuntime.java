package org.tetrabox.example.minitl.runtime;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.tetrabox.example.minitl.Binding;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.runtime.serializers.IDRegistry;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;

/**
 * Manages the execution of steps for MiniTL programs.
 */
public class MiniTLRuntime implements Runnable {
	
	private Transformation transformation;
	private List<String> transformationArgs;
	
	private Binding nextBinding;
	private Set<Rule> appliedRules;
	
	public MiniTLRuntime(Transformation transformation, List<String> transformationArgs) {
		this.transformation = transformation;
		this.transformationArgs = transformationArgs;
		appliedRules = new HashSet<>();
	}
	
	@Override
	public synchronized void run() {
		TransformationAspect.initialize(transformation, transformationArgs);
		TransformationAspect.execute(transformation);
	}

	public synchronized void executeStep(Object caller, StepCommand command, String className, String methodName) throws InterruptedException {
		if (className.equals("Binding") && methodName.equals("assign")) {
			nextBinding = (Binding) caller;
			notify();
			wait();
			
			appliedRules.add(findContainingRule(nextBinding));
			nextBinding = null;
		}
		
		command.execute();
	}
	
	public synchronized CheckBreakpointResponse checkBreakpoint(String type, String elementId) throws Exception {
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

            case "bindingAssigned":
            	Binding nextBinding = getNextBinding();
                isActivated = IDRegistry.getId(nextBinding).equals(elementId);

                if (isActivated) message = "Binding " + nextBinding.getFeature() + " is about to be assigned the value " + nextBinding.getValue() + ".";

                break;

            default:
                throw new Exception("Unknown breakpoint type " + type + ".");
        }

        return new CheckBreakpointResponse(isActivated, message);
    }
	
	public synchronized Rule getNextRule() {
		if (nextBinding == null) return null;
		
		return findContainingRule(nextBinding);
	}
	
	public synchronized Binding getNextBinding() {
		return nextBinding;
	}
	
	public synchronized boolean isExecutionDone() {
		return nextBinding == null;
	}
	
	private Rule findContainingRule(EObject object) {
		Rule rule = null;
        EObject currentAncestor = getNextBinding().eContainer();
        
        // find containing rule
        while (rule == null) {
        	if (currentAncestor instanceof Rule) rule = (Rule) currentAncestor;
        	currentAncestor = currentAncestor.eContainer();
        }
        
        return rule;
	}
}
