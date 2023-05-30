package org.tetrabox.example.minitl.runtime.lrp;

/**
 * Response to the parse LRP request.
 */
public class ParseResponse {
	
	/** Root of the AST. */
    private ModelElement astRoot;

    public ParseResponse(ModelElement astRoot) {
        this.astRoot = astRoot;
    }

    public ModelElement getAstRoot() {
        return astRoot;
    }
}
