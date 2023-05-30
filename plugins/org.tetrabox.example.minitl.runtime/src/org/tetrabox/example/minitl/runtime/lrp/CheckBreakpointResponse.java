package org.tetrabox.example.minitl.runtime.lrp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Response to the checkBreakpoint LRP request.
 */
public class CheckBreakpointResponse {
	
	/** True if the breakpoint is activated, false otherwise. */
    private boolean isActivated;

    /** 
     * Human-readable message to describe the cause of activation.
     * Should only be set if `isActivated` is true.
     */
    @JsonInclude(Include.NON_NULL)
    private String message;

    public CheckBreakpointResponse(boolean isActivated) {
        this(isActivated, null);
    }

    public CheckBreakpointResponse(boolean isActvated, String message) {
        this.isActivated = isActvated;
        this.message = message;
    }

    public boolean getIsActivated() {
        return isActivated;
    }

    public String getMessage() {
        return message;
    }
}
