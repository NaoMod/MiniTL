package org.tetrabox.example.minitl.runtime;

import java.util.LinkedList;
import java.util.List;

import org.tetrabox.example.minitl.runtime.lrp.BreakpointParameter;
import org.tetrabox.example.minitl.runtime.lrp.BreakpointType;

/**
 * Provides information about the breakpoint types defined by this language runtime.
 */
public class BreakpointTypes {
	
	private BreakpointTypes() { }

    private static List<BreakpointType> availableBreakpointTypes;

    /**
     * Retrieves the available breakpoint types defined by this language runtime.
     * 
     * @return The available breakpoint types.
     */
    public static List<BreakpointType> getAvailableBreakpointTypes() {
        if (availableBreakpointTypes != null) {
            return availableBreakpointTypes;
        }

        availableBreakpointTypes = new LinkedList<>();

        availableBreakpointTypes.add(new BreakpointType("ruleApplied", "Rule Applied",
                List.of(new BreakpointParameter("targetElementType", false, "Rule")),
                "Breaks when a specific rule is about to be applied."));

        availableBreakpointTypes.add(new BreakpointType("featureAssignedValue", "Value Assigned to Feature",
        		List.of(new BreakpointParameter("targetElementType", false, "Binding")),
                "Breaks when a value is about to be assigned to the feature of an element of the output model."));

        return availableBreakpointTypes;
    }
}