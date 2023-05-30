package org.tetrabox.example.minitl.runtime.exceptions;

/**
 * Thrown when a breakpoint type not defined by the language runtime is required.
 */
public class UnknownBreakpointTypeException extends Exception {
	private final String unknownBreakpointType;

	public UnknownBreakpointTypeException(String unknwonBreakpointType) {
		this.unknownBreakpointType = unknwonBreakpointType;
	}
	
	@Override
	public String getMessage() {
		return "Unknown breakpoint type " + unknownBreakpointType + ".";
	}
}
