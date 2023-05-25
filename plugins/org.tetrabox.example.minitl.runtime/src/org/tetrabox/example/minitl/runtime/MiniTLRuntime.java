package org.tetrabox.example.minitl.runtime;

import java.util.ArrayDeque;
import java.util.Queue;

import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.runtime.serializers.IDRegistry;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;

/**
 * Manages the execution of steps for MiniTL programs.
 */
public class MiniTLRuntime {
	
	private Transformation transformation;
	private Queue<RuleCommand> ruleCommands;
	private StepCommand saveCommand;
	
	public MiniTLRuntime(Transformation transformation) {
		this.transformation = transformation;
		ruleCommands = new ArrayDeque<>();
	}

	public void addRuleCommand(Rule rule, StepCommand command) {
		ruleCommands.add(new RuleCommand(rule, command));
	}
	
	public Transformation getTransformation() {
		return transformation;
	}

	public Rule getNextRule() {
		if (ruleCommands.isEmpty()) return null;
		
		return ruleCommands.peek().getRule();
	}

	public boolean isExecutionDone() {
		return ruleCommands.isEmpty();
	}
	
	public void nextStep() {
		if (ruleCommands.isEmpty()) return;
		
		RuleCommand ruleCommand = ruleCommands.poll();
		ruleCommand.getCommand().execute();
	}
	
	public CheckBreakpointResponse checkBreakpoint(String type, String elementId) throws Exception {
        if (isExecutionDone())
            return new CheckBreakpointResponse(false);

        boolean isActivated = false;
        String message = null;
        
        Rule nextRule = ruleCommands.peek().getRule();

        switch (type) {
            case "ruleAppliedSingle":
            	isActivated = IDRegistry.getId(nextRule).equals(elementId);
            	
            	if (isActivated) {
            		message = "Rule " + nextRule.getName() + " is about to be applied to a single element.";
            	}

                break;

            case "ruleAppliedAll":
            	//TODO
                isActivated = IDRegistry.getId(nextRule).equals(elementId);

                if (isActivated) message = "Rule " + nextRule.getName() + " is about to be applied to all elements.";

                break;

            default:
                throw new Exception("Unknown breakpoint type " + type + ".");
        }

        return new CheckBreakpointResponse(isActivated, message);
    }
	
	public void checkSave() {
		if (ruleCommands.isEmpty())
			saveCommand.execute();
	}
	
	public void setSaveCommand(StepCommand command) {
		saveCommand = command;
	}
	
	private class RuleCommand {
		private Rule rule;
		private StepCommand command;
		
		public RuleCommand(Rule rule, StepCommand command) {
			this.rule = rule;
			this.command = command;
		}

		public Rule getRule() {
			return rule;
		}

		public StepCommand getCommand() {
			return command;
		}		
	}
}
