package org.tetrabox.example.minitl.semantics;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod;
import org.eclipse.emf.ecore.EObject;
import org.tetrabox.example.minitl.FieldAccessValue;

@Aspect(className = FieldAccessValue.class)
@SuppressWarnings("all")
public class FieldAccessValueAspect extends ValueAspect {
  @OverrideAspectMethod
  public static Object evaluate(final FieldAccessValue _self) {
    final org.tetrabox.example.minitl.semantics.FieldAccessValueAspectFieldAccessValueAspectProperties _self_ = org.tetrabox.example.minitl.semantics.FieldAccessValueAspectFieldAccessValueAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# Object evaluate()
    if (_self instanceof org.tetrabox.example.minitl.FieldAccessValue){
    	result = org.tetrabox.example.minitl.semantics.FieldAccessValueAspect._privk3_evaluate(_self_, (org.tetrabox.example.minitl.FieldAccessValue)_self);
    };
    return (java.lang.Object)result;
  }
  
  protected static Object _privk3_evaluate(final FieldAccessValueAspectFieldAccessValueAspectProperties _self_, final FieldAccessValue _self) {
    Object _evaluate = ValueAspect.evaluate(_self.getObject());
    return ((EObject) _evaluate).eGet(_self.getFeature());
  }
}
