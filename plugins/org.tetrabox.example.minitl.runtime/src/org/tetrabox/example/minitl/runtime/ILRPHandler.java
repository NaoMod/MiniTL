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

/**
 * Interface of an LRP server.
 */
public interface ILRPHandler {

	/**
	 * Parses a file and stores the generated AST.
	 * 
	 * @param args The arguments of the request.
	 * @return The LRP response to the request.
	 * @throws LocationException If a location cannot be processed for a model element.
	 * @throws ValueTypeException If a value type is unknown.
	 */
   @JsonRpcErrors({
         @JsonRpcError(exception = LocationException.class, code = -32002),
         @JsonRpcError(exception = ValueTypeException.class, code = -32003)
   })
   ParseResponse parse(@JsonRpcParam(value = "params") ParseArguments args) throws LocationException, ValueTypeException;

   /**
    * Creates a new runtime state for a given source file and stores it.
    * The AST for the given source file must have been previously constructed through the `parse` service.
    * 
    * @param args The arguments of the request
    * @return The LRP response to the request.
    * @throws ASTNotFoundException If the AST for the source file cannot be found.
    * @throws InterruptedException If the thread running the MiniTL program is interrupted.
    */
   @JsonRpcErrors({
         @JsonRpcError(exception = ASTNotFoundException.class, code = -32004),
         @JsonRpcError(exception = InterruptedException.class, code = -32005)
   })
   InitResponse initExecution(@JsonRpcParam(value = "params") CustomInitArguments args) throws ASTNotFoundException, InterruptedException;

   /**
    * Performs the next execution step in the runtime state associated to a given source file.
    * 
    * @param args The arguments of the request
    * @return The LRP response to the request.
    * @throws RuntimeNotFoundException If the runtime for the source file cannot be found.
    * @throws InterruptedException If the thread running the MiniTL program is interrupted.
    */
   @JsonRpcErrors({
	   @JsonRpcError(exception = InterruptedException.class, code = -32005),
       @JsonRpcError(exception = RuntimeNotFoundException.class, code = -32006)
   })
   StepResponse nextStep(@JsonRpcParam(value = "params") StepArguments args) throws RuntimeNotFoundException, InterruptedException;

   /**
    * Returns the current runtime state for a given source file.
    * 
    * @param args The arguments of the request
    * @return The LRP response to the request.
    * @throws RuntimeNotFoundException If the runtime for the source file cannot be found.
    */
   @JsonRpcErrors({
       @JsonRpcError(exception = RuntimeNotFoundException.class, code = -32006)
   })
   GetRuntimeStateResponse getRuntimeState(@JsonRpcParam(value = "params") GetRuntimeStateArguments args)
         throws RuntimeNotFoundException;
   
   /**
    * Returns the available breakpoint types defined by the language runtime.
    * 
    * @return The LRP response to the request.
    */
   GetBreakpointTypesResponse getBreakpointTypes();

   /**
    * Checks whether a breakpoint of a certain type is verified with the given arguments.
    * 
    * @param args The arguments of the request
    * @return The LRP response to the request.
    * @throws RuntimeNotFoundException If the runtime for the source file cannot be found.
    * @throws UnknownBreakpointTypeException If the breakpoint type is not defined by the language runtime.
    */
   @JsonRpcErrors({
       @JsonRpcError(exception = RuntimeNotFoundException.class, code = -32006),
       @JsonRpcError(exception = UnknownBreakpointTypeException.class, code = -32007),
   })
   CheckBreakpointResponse checkBreakpoint(@JsonRpcParam(value = "params") CheckBreakpointArguments args)
         throws RuntimeNotFoundException, UnknownBreakpointTypeException;
}
