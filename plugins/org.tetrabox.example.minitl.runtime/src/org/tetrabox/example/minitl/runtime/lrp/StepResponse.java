package org.tetrabox.example.minitl.runtime.lrp;

public class StepResponse {
    private boolean isExecutionDone;

    public StepResponse(boolean isExecutionDone) {
        this.isExecutionDone = isExecutionDone;
    }

    public boolean getIsExecutionDone() {
        return isExecutionDone;
    }
}
