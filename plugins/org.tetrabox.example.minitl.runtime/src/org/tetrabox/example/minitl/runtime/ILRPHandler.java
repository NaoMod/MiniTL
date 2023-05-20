package org.tetrabox.example.minitl.runtime;

import org.tetrabox.example.minitl.runtime.lrp.Arguments;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointArguments;
import org.tetrabox.example.minitl.runtime.lrp.CheckBreakpointResponse;
import org.tetrabox.example.minitl.runtime.lrp.GetBreakpointTypesResponse;
import org.tetrabox.example.minitl.runtime.lrp.GetRuntimeStateArguments;
import org.tetrabox.example.minitl.runtime.lrp.GetRuntimeStateResponse;
import org.tetrabox.example.minitl.runtime.lrp.InitResponse;
import org.tetrabox.example.minitl.runtime.lrp.ParseArguments;
import org.tetrabox.example.minitl.runtime.lrp.ParseResponse;
import org.tetrabox.example.minitl.runtime.lrp.StepArguments;
import org.tetrabox.example.minitl.runtime.lrp.StepResponse;

import com.googlecode.jsonrpc4j.JsonRpcError;
import com.googlecode.jsonrpc4j.JsonRpcErrors;
import com.googlecode.jsonrpc4j.JsonRpcParam;

class CustomInitArguments extends Arguments {
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
}

public interface ILRPHandler {

   @JsonRpcErrors({
         @JsonRpcError(exception = Exception.class, code = -32002)
   })
   ParseResponse parse(@JsonRpcParam(value = "params") ParseArguments args) throws Exception;

   GetBreakpointTypesResponse getBreakpointTypes();

   @JsonRpcErrors({
         @JsonRpcError(exception = Exception.class, code = -32002)
   })
   InitResponse initExecution(@JsonRpcParam(value = "params") CustomInitArguments args) throws Exception;

   @JsonRpcErrors({	
         @JsonRpcError(exception = Exception.class, code = -32002)
   })
   StepResponse nextStep(@JsonRpcParam(value = "params") StepArguments args) throws Exception;

   @JsonRpcErrors({
         @JsonRpcError(exception = Exception.class, code = -32002)
   })
   GetRuntimeStateResponse getRuntimeState(@JsonRpcParam(value = "params") GetRuntimeStateArguments args)
         throws Exception;

   @JsonRpcErrors({
         @JsonRpcError(exception = Exception.class, code = -32002)
   })
   CheckBreakpointResponse checkBreakpoint(@JsonRpcParam(value = "params") CheckBreakpointArguments args)
         throws Exception;
}
