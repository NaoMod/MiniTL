package org.tetrabox.example.minitl.semantics;

import com.google.common.base.Objects;
import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.Step;
import org.eclipse.emf.ecore.EObject;
import org.tetrabox.example.minitl.Binding;
import org.tetrabox.example.minitl.ObjectTemplate;

@Aspect(className = Binding.class)
@SuppressWarnings("all")
public class BindingAspect {
  @Step
  public static boolean check(final Binding _self, final EObject o) {
    final org.tetrabox.example.minitl.semantics.BindingAspectBindingAspectProperties _self_ = org.tetrabox.example.minitl.semantics.BindingAspectBindingAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# boolean check(EObject)
    if (_self instanceof org.tetrabox.example.minitl.Binding){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			addToResult(org.tetrabox.example.minitl.semantics.BindingAspect._privk3_check(_self_, (org.tetrabox.example.minitl.Binding)_self,o));
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {o}, command, "Binding", "check");
    	} else {
    		command.execute();
    	}
    	result = command.getResult();
    	;
    };
    return (boolean)result;
  }
  
  @Step
  public static void assign(final Binding _self) {
    final org.tetrabox.example.minitl.semantics.BindingAspectBindingAspectProperties _self_ = org.tetrabox.example.minitl.semantics.BindingAspectBindingAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void assign()
    if (_self instanceof org.tetrabox.example.minitl.Binding){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			org.tetrabox.example.minitl.semantics.BindingAspect._privk3_assign(_self_, (org.tetrabox.example.minitl.Binding)_self);
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {_self}, command, "Binding", "assign");
    	} else {
    		command.execute();
    	}
    	;
    };
  }
  
  protected static boolean _privk3_check(final BindingAspectBindingAspectProperties _self_, final Binding _self, final EObject o) {
    final Object existingValue = o.eGet(_self.getFeature());
    final Object patternValue = ValueAspect.evaluate(_self.getValue());
    return Objects.equal(existingValue, patternValue);
  }
  
  protected static void _privk3_assign(final BindingAspectBindingAspectProperties _self_, final Binding _self) {
    final Object value = ValueAspect.evaluate(_self.getValue());
    EObject _eContainer = _self.eContainer();
    ObjectTemplateAspect.currentObject(((ObjectTemplate) _eContainer)).eSet(_self.getFeature(), value);
  }
}
