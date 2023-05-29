package org.tetrabox.example.minitl.runtime;

import java.util.LinkedList;
import java.util.List;

import org.tetrabox.example.minitl.runtime.lrp.BreakpointParameter;
import org.tetrabox.example.minitl.runtime.lrp.BreakpointType;

public class BreakpointTypes {

    private static List<BreakpointType> breakpointTypes;

    public static List<BreakpointType> getBreakpointTypes() {
        if (breakpointTypes != null) {
            return breakpointTypes;
        }

        breakpointTypes = new LinkedList<>();

        breakpointTypes.add(new BreakpointType("ruleApplied", "Rule Applied",
                List.of(new BreakpointParameter("targetElementType", false, "Rule")),
                "Breaks when a specific rule is about to be applied."));

        breakpointTypes.add(new BreakpointType("featureAssignedValue", "Value Assigned to Feature",
        		List.of(new BreakpointParameter("targetElementType", false, "Binding")),
                "Breaks when a value is about to be assigned to the feature of an element of the output model."));

        return breakpointTypes;
    }

}
