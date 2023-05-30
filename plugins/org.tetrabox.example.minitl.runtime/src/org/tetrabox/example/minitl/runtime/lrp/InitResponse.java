package org.tetrabox.example.minitl.runtime.lrp;

/**
 * Response to the initExecution LRP request.
 */
public class InitResponse {
	
	/** True if the execution is done, false otherwise. */
    private boolean isExecutionDone;

    public InitResponse(boolean isExecutionDone) {
        this.isExecutionDone = isExecutionDone;
    }

    public boolean getIsExecutionDone() {
        return isExecutionDone;
    }
}
