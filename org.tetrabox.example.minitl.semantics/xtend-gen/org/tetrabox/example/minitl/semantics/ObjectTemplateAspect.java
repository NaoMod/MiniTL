package org.tetrabox.example.minitl.semantics;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.Step;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.tetrabox.example.minitl.Binding;
import org.tetrabox.example.minitl.ObjectTemplate;

@Aspect(className = ObjectTemplate.class)
@SuppressWarnings("all")
public class ObjectTemplateAspect {
  public static void reset(final ObjectTemplate _self) {
    final org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void reset()
    if (_self instanceof org.tetrabox.example.minitl.ObjectTemplate){
    	org.tetrabox.example.minitl.semantics.ObjectTemplateAspect._privk3_reset(_self_, (org.tetrabox.example.minitl.ObjectTemplate)_self);
    };
  }
  
  @Step
  public static void match(final ObjectTemplate _self, final EObject o) {
    final org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void match(EObject)
    if (_self instanceof org.tetrabox.example.minitl.ObjectTemplate){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			org.tetrabox.example.minitl.semantics.ObjectTemplateAspect._privk3_match(_self_, (org.tetrabox.example.minitl.ObjectTemplate)_self,o);
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {o}, command, "ObjectTemplate", "match");
    	} else {
    		command.execute();
    	}
    	;
    };
  }
  
  @Step
  public static void construct(final ObjectTemplate _self) {
    final org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void construct()
    if (_self instanceof org.tetrabox.example.minitl.ObjectTemplate){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			org.tetrabox.example.minitl.semantics.ObjectTemplateAspect._privk3_construct(_self_, (org.tetrabox.example.minitl.ObjectTemplate)_self);
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {_self}, command, "ObjectTemplate", "construct");
    	} else {
    		command.execute();
    	}
    	;
    };
  }
  
  public static EObject currentObject(final ObjectTemplate _self) {
    final org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# EObject currentObject()
    if (_self instanceof org.tetrabox.example.minitl.ObjectTemplate){
    	result = org.tetrabox.example.minitl.semantics.ObjectTemplateAspect._privk3_currentObject(_self_, (org.tetrabox.example.minitl.ObjectTemplate)_self);
    };
    return (org.eclipse.emf.ecore.EObject)result;
  }
  
  public static void currentObject(final ObjectTemplate _self, final EObject currentObject) {
    final org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void currentObject(EObject)
    if (_self instanceof org.tetrabox.example.minitl.ObjectTemplate){
    	org.tetrabox.example.minitl.semantics.ObjectTemplateAspect._privk3_currentObject(_self_, (org.tetrabox.example.minitl.ObjectTemplate)_self,currentObject);
    };
  }
  
  protected static void _privk3_reset(final ObjectTemplateAspectObjectTemplateAspectProperties _self_, final ObjectTemplate _self) {
    ObjectTemplateAspect.currentObject(_self, null);
  }
  
  protected static void _privk3_match(final ObjectTemplateAspectObjectTemplateAspectProperties _self_, final ObjectTemplate _self, final EObject o) {
    int _classifierID = o.eClass().getClassifierID();
    int _classifierID_1 = _self.getType().getClassifierID();
    boolean _equals = (_classifierID == _classifierID_1);
    if (_equals) {
      final Function1<Binding, Boolean> _function = (Binding b) -> {
        return Boolean.valueOf(BindingAspect.check(b, o));
      };
      boolean _forall = IterableExtensions.<Binding>forall(_self.getBindings(), _function);
      if (_forall) {
        ObjectTemplateAspect.currentObject(_self, o);
      }
    } else {
      ObjectTemplateAspect.currentObject(_self, null);
    }
  }
  
  protected static void _privk3_construct(final ObjectTemplateAspectObjectTemplateAspectProperties _self_, final ObjectTemplate _self) {
    ObjectTemplateAspect.currentObject(_self, EcoreUtil.create(_self.getType()));
    EList<Binding> _bindings = _self.getBindings();
    for (final Binding b : _bindings) {
      BindingAspect.assign(b);
    }
  }
  
  protected static EObject _privk3_currentObject(final ObjectTemplateAspectObjectTemplateAspectProperties _self_, final ObjectTemplate _self) {
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("getCurrentObject") &&
    			m.getParameterTypes().length == 0) {
    				Object ret = m.invoke(_self);
    				if (ret != null) {
    					return (org.eclipse.emf.ecore.EObject) ret;
    				} else {
    					return null;
    				}
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    return _self_.currentObject;
  }
  
  protected static void _privk3_currentObject(final ObjectTemplateAspectObjectTemplateAspectProperties _self_, final ObjectTemplate _self, final EObject currentObject) {
    boolean setterCalled = false;
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("setCurrentObject")
    				&& m.getParameterTypes().length == 1) {
    			m.invoke(_self, currentObject);
    			setterCalled = true;
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    if (!setterCalled) {
    	_self_.currentObject = currentObject;
    }
  }
}
