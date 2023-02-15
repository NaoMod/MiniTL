package org.tetrabox.example.minitl.semantics;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod;
import org.tetrabox.example.minitl.ObjectTemplateValue;

@Aspect(className = ObjectTemplateValue.class)
@SuppressWarnings("all")
public class ObjectTemplateValueAspect extends ValueAspect {
  @OverrideAspectMethod
  public static Object evaluate(final ObjectTemplateValue _self) {
    final org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspectObjectTemplateValueAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspectObjectTemplateValueAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# Object evaluate()
    if (_self instanceof org.tetrabox.example.minitl.ObjectTemplateValue){
    	result = org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspect._privk3_evaluate(_self_, (org.tetrabox.example.minitl.ObjectTemplateValue)_self);
    };
    return (java.lang.Object)result;
  }
  
  protected static Object _privk3_evaluate(final ObjectTemplateValueAspectObjectTemplateValueAspectProperties _self_, final ObjectTemplateValue _self) {
    return ObjectTemplateAspect.currentObject(_self.getObjecttemplate());
  }
}
