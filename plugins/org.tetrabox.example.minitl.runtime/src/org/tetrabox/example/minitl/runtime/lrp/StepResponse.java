package org.tetrabox.example.minitl.runtime.lrp;

/**
 * Response to the nextStep LRP request.
 */
public class StepResponse {
	
	/** True if the execution is done, false otherwise. */
    private boolean isExecutionDone;

    public StepResponse(boolean isExecutionDone) {
        this.isExecutionDone = isExecutionDone;
    }

    public boolean getIsExecutionDone() {
        return isExecutionDone;
    }
}
