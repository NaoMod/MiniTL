package org.tetrabox.example.minitl.runtime.exceptions;

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
