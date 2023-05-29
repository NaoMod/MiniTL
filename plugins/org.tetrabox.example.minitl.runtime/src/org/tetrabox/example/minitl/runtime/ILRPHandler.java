package org.tetrabox.example.minitl.runtime;

import org.tetrabox.example.minitl.runtime.exceptions.ASTNotFoundException;
import org.tetrabox.example.minitl.runtime.exceptions.LocationException;
import org.tetrabox.example.minitl.runtime.exceptions.RuntimeNotFoundException;
import org.tetrabox.example.minitl.runtime.exceptions.UnknownBreakpointTypeException;
import org.tetrabox.example.minitl.runtime.exceptions.ValueTypeException;
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

public interface ILRPHandler {

   @JsonRpcErrors({
         @JsonRpcError(exception = LocationException.class, code = -32002),
         @JsonRpcError(exception = ValueTypeException.class, code = -32003)
   })
   ParseResponse parse(@JsonRpcParam(value = "params") ParseArguments args) throws LocationException, ValueTypeException;

   GetBreakpointTypesResponse getBreakpointTypes();

   @JsonRpcErrors({
         @JsonRpcError(exception = ASTNotFoundException.class, code = -32004),
         @JsonRpcError(exception = InterruptedException.class, code = -32005)
   })
   InitResponse initExecution(@JsonRpcParam(value = "params") CustomInitArguments args) throws ASTNotFoundException, InterruptedException;

   @JsonRpcErrors({
	   @JsonRpcError(exception = InterruptedException.class, code = -32005),
       @JsonRpcError(exception = RuntimeNotFoundException.class, code = -32006)
   })
   StepResponse nextStep(@JsonRpcParam(value = "params") StepArguments args) throws RuntimeNotFoundException, InterruptedException;

   @JsonRpcErrors({
       @JsonRpcError(exception = RuntimeNotFoundException.class, code = -32006)
   })
   GetRuntimeStateResponse getRuntimeState(@JsonRpcParam(value = "params") GetRuntimeStateArguments args)
         throws RuntimeNotFoundException;

   @JsonRpcErrors({
       @JsonRpcError(exception = RuntimeNotFoundException.class, code = -32006),
       @JsonRpcError(exception = UnknownBreakpointTypeException.class, code = -32007),
   })
   CheckBreakpointResponse checkBreakpoint(@JsonRpcParam(value = "params") CheckBreakpointArguments args)
         throws RuntimeNotFoundException, UnknownBreakpointTypeException;
}
