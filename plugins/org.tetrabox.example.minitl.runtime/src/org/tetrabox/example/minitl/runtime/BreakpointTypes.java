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

        BreakpointParameter ruleAppliedParameter = new BreakpointParameter("targetElementType", false, "Rule");
        breakpointTypes.add(new BreakpointType("ruleAppliedSingle", "Rule Applied To Single Element",
                List.of(ruleAppliedParameter),
                "Breaks when a specific rule is about to be applied to a single element."));

        breakpointTypes.add(new BreakpointType("ruleAppliedAll", "Rule Applied To All Elements",
                List.of(ruleAppliedParameter),
                "Breaks when a specific rule is about to be applied to a all elements."));

        return breakpointTypes;
    }

}
