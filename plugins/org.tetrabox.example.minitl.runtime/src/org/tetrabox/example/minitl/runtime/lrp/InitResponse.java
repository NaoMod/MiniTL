package org.tetrabox.example.minitl.runtime.lrp;

public class InitResponse {
    private boolean isExecutionDone;

    public InitResponse(boolean isExecutionDone) {
        this.isExecutionDone = isExecutionDone;
    }

    public boolean getIsExecutionDone() {
        return isExecutionDone;
    }
}
