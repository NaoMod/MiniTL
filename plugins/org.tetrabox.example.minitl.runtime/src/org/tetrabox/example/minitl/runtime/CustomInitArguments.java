package org.tetrabox.example.minitl.runtime;

import org.tetrabox.example.minitl.runtime.lrp.Arguments;

/**
 * MiniTL-specific arguments for the initialization of the execution.
 */
public class CustomInitArguments extends Arguments {
	private String inputModel;
	private String outputModel;
	
	public String getInputModel() {
		return inputModel;
	}
	
	public void setInputModel(String inputModel) {
		this.inputModel = inputModel;
	}
	
	public String getOutputModel() {
		return outputModel;
	}
	
	public void setOutputModel(String outputModel) {
		this.outputModel = outputModel;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if (!(obj instanceof CustomInitArguments)) return false;
		
		CustomInitArguments otherArgs = (CustomInitArguments) obj;
		return this.inputModel.equals(otherArgs.getInputModel()) && this.outputModel.equals(otherArgs.getOutputModel()) && super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return getSourceFile().hashCode() + inputModel.hashCode() + outputModel.hashCode();
	}
}