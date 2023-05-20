package org.tetrabox.example.minitl.runtime.lrp;

import java.util.List;

public class GetBreakpointTypesResponse {
    private List<BreakpointType> breakpointTypes;

    public GetBreakpointTypesResponse(List<BreakpointType> breakpointTypes) {
        this.breakpointTypes = breakpointTypes;
    }

    public List<BreakpointType> getBreakpointTypes() {
        return breakpointTypes;
    }
}
