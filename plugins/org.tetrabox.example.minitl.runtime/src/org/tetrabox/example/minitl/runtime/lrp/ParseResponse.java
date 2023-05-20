package org.tetrabox.example.minitl.runtime.lrp;

public class ParseResponse {
    private ModelElement astRoot;

    public ParseResponse(ModelElement astRoot) {
        this.astRoot = astRoot;
    }

    public ModelElement getAstRoot() {
        return astRoot;
    }
}
