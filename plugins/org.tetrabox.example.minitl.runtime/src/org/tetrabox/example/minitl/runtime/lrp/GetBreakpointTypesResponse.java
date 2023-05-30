package org.tetrabox.example.minitl.runtime.lrp;

import java.util.List;

/**
 * Response to the getBreakpointTypes LRP request.
 */
public class GetBreakpointTypesResponse {
	
	/** Breakpoint types defined by the language runtime. */
    private List<BreakpointType> breakpointTypes;

    public GetBreakpointTypesResponse(List<BreakpointType> breakpointTypes) {
        this.breakpointTypes = breakpointTypes;
    }

    public List<BreakpointType> getBreakpointTypes() {
        return breakpointTypes;
    }
}
