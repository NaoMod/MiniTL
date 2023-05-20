package org.tetrabox.example.minitl.runtime.lrp;

public class GetRuntimeStateResponse {
    private ModelElement runtimeStateRoot;

    public GetRuntimeStateResponse(ModelElement runtimeStateRoot) {
        this.runtimeStateRoot = runtimeStateRoot;
    }

    public ModelElement getRuntimeStateRoot() {
        return runtimeStateRoot;
    }
}
