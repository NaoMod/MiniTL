package org.tetrabox.example.minitl.runtime.exceptions;

/**
 * Thrown when an AST cannot be found for a source file.
 */
public class ASTNotFoundException extends Exception {
	private final String sourceFile;

	public ASTNotFoundException(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	@Override
	public String getMessage() {
		return "No AST for file \'" + sourceFile + "\'.";
	}
}
