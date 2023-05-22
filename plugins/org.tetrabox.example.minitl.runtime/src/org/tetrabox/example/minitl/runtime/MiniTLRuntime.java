package org.tetrabox.example.minitl.runtime;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.runtime.serializers.IDRegistry;
import org.tetrabox.example.minitl.semantics.RuleAspect;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

import com.google.common.base.Objects;

public class MiniTLRuntime {

    private Transformation transformation;
    private int nextRuleIndex;
    private Set<String> activatedSingleRule;

    public MiniTLRuntime(Transformation transformation, List<String> args) {
        TransformationAspect.initialize(transformation, args);
        this.transformation = transformation;
        nextRuleIndex = 0;
        activatedSingleRule = new HashSet<>();
    }

    public boolean isExecutionDone() {
        return nextRuleIndex == transformation.getRules().size();
    }

    public void nextStep() throws Exception {
        if (isExecutionDone())
            throw new Exception("Execution already done.");

        Rule rule = transformation.getRules().get(nextRuleIndex);
        RuleAspect.apply(rule);

        nextRuleIndex++;

        if (nextRuleIndex == transformation.getRules().size())
            doSave();
    }

    public CheckBreakpointResponse checkBreakpoint(String type, String elementId) throws Exception {
        if (isExecutionDone())
            return new CheckBreakpointResponse(false);

        boolean isActivated = false;
        String message = null;

        switch (type) {
            case "ruleAppliedSingle":
            	isActivated = !activatedSingleRule.contains(elementId);
            	
            	if (isActivated) {
            		activatedSingleRule.add(elementId);
            		Rule rule = transformation.getRules().get(nextRuleIndex);
            		message = "Rule " + rule.getName() + " is about to be applied.";
            	}

                break;

            case "ruleAppliedAll":
                Rule rule = transformation.getRules().get(nextRuleIndex);
                isActivated = IDRegistry.getId(rule).equals(elementId);

                if (isActivated) message = "Rule " + rule.getName() + " is about to be applied.";

                break;

            default:
                throw new Exception("Unknown breakpoint type " + type + ".");
        }

        return new CheckBreakpointResponse(isActivated, message);
    }
    
    public Rule getNextRule() {
    	if (isExecutionDone()) return null;
    	
    	return transformation.getRules().get(nextRuleIndex);
    }

    private void doSave() throws IOException {
        if (((!Objects.equal(TransformationAspect.outputFilePath(transformation), null))
                && (!Objects.equal(TransformationAspect.outputFilePath(transformation), "")))) {
            final ResourceSetImpl rs = new ResourceSetImpl();
            String _outputFilePath = TransformationAspect.outputFilePath(transformation);
            String relativeOutputFilePath = _outputFilePath.replaceFirst("platform:/plugin", "");
            String currentPathString = new File(".").getAbsolutePath();
            File outputFile = new File(Paths.get(Paths.get(currentPathString).getParent().getParent().toString(), relativeOutputFilePath).toString());
            boolean _exists = outputFile.exists();
            if (_exists) {
                outputFile.delete();
            }
            outputFile.getParentFile().mkdirs();
            final URI outputModelURI = URI.createFileURI(outputFile.getAbsolutePath());
            final Resource outputModelResource = rs.createResource(outputModelURI);
            outputModelResource.getContents().addAll(TransformationAspect.outputModel(transformation));
            outputModelResource.save(null);
        }
    }
}
