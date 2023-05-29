package org.tetrabox.example.minitl.runtime;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.tetrabox.example.minitl.Transformation;

import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager;
import fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand;

/**
 * Manages MiniTL runtimes and passes step information to them.
 */
public class MiniTLStepManager implements IStepManager {
	
	private Map<Transformation, MiniTLRuntime> runtimes;
	
	public MiniTLStepManager() {
		runtimes = new HashMap<>();
	}

	@Override
	public void executeStep(Object caller, StepCommand command, String className, String methodName) {
		MiniTLRuntime runtime = runtimes.get((Transformation) EcoreUtil.getRootContainer((EObject) caller));
		try {
			runtime.executeStep(caller, command, className, methodName);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		
		synchronized (runtime) {
			if (runtime.isExecutionDone()) {
				runtime.setPaused(true);
				runtime.notifyAll();
			}
		}
	}

	@Override
	public boolean canHandle(Object caller) {
		return true;
	}
	
	public void addRuntime(Transformation transformation, MiniTLRuntime runtime) {
		runtimes.put(transformation, runtime);
	}
	
	public void stepManagerRemoveRuntime(Transformation transformation) {
		runtimes.remove(transformation);
	}
}
