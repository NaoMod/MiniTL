package org.tetrabox.example.minitl.runtime.lrp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class CheckBreakpointResponse {
    private boolean isActivated;

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
