package org.tetrabox.example.minitl.runtime.lrp;

/**
 * Response to the getRuntimeState LRP request.
 */
public class GetRuntimeStateResponse {
	
	/** Root of the runtime state. */
    private ModelElement runtimeStateRoot;

    public GetRuntimeStateResponse(ModelElement runtimeStateRoot) {
        this.runtimeStateRoot = runtimeStateRoot;
    }

    public ModelElement getRuntimeStateRoot() {
        return runtimeStateRoot;
    }
}
