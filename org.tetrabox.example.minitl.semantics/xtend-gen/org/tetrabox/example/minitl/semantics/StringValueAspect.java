package org.tetrabox.example.minitl.semantics;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod;
import org.tetrabox.example.minitl.StringValue;

@Aspect(className = StringValue.class)
@SuppressWarnings("all")
public class StringValueAspect extends ValueAspect {
  @OverrideAspectMethod
  public static Object evaluate(final StringValue _self) {
    final org.tetrabox.example.minitl.semantics.StringValueAspectStringValueAspectProperties _self_ = org.tetrabox.example.minitl.semantics.StringValueAspectStringValueAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# Object evaluate()
    if (_self instanceof org.tetrabox.example.minitl.StringValue){
    	result = org.tetrabox.example.minitl.semantics.StringValueAspect._privk3_evaluate(_self_, (org.tetrabox.example.minitl.StringValue)_self);
    };
    return (java.lang.Object)result;
  }
  
  protected static Object _privk3_evaluate(final StringValueAspectStringValueAspectProperties _self_, final StringValue _self) {
    return _self.getValue();
  }
}
