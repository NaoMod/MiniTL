package org.tetrabox.example.minitl.runtime.exceptions;

public class RuntimeNotFoundException extends Exception {
	private final String sourceFile;

	public RuntimeNotFoundException(String sourceFile) {
		this.sourceFile = sourceFile;
	}
	
	@Override
	public String getMessage() {
		return "No runtime for file \'" + sourceFile + "\'.";
	}
}
