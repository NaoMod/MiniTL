package org.tetrabox.example.minitl.aspects;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod;
import org.tetrabox.example.minitl.minitl.ObjectTemplate;
import org.tetrabox.example.minitl.minitl.ObjectTemplateValue;
import org.tetrabox.example.minitl.aspects.ObjectTemplateAspect;
import org.tetrabox.example.minitl.aspects.ObjectTemplateValueAspectObjectTemplateValueAspectProperties;
import org.tetrabox.example.minitl.aspects.ValueAspect;

@Aspect(className = ObjectTemplateValue.class)
@SuppressWarnings("all")
public class ObjectTemplateValueAspect extends ValueAspect {
  @OverrideAspectMethod
  public static Object evaluate(final ObjectTemplateValue _self) {
    final org.tetrabox.example.minitl.aspects.ObjectTemplateValueAspectObjectTemplateValueAspectProperties _self_ = org.tetrabox.example.minitl.aspects.ObjectTemplateValueAspectObjectTemplateValueAspectContext.getSelf(_self);
    Object result = null;
     if (_self instanceof org.tetrabox.example.minitl.minitl.ObjectTemplateValue){
    					result = org.tetrabox.example.minitl.aspects.ObjectTemplateValueAspect._privk3_evaluate(_self_, (org.tetrabox.example.minitl.minitl.ObjectTemplateValue)_self);
    } else  if (_self instanceof org.tetrabox.example.minitl.minitl.Value){
    					result = org.tetrabox.example.minitl.aspects.ValueAspect.evaluate((org.tetrabox.example.minitl.minitl.Value)_self);
    } else  { throw new IllegalArgumentException("Unhandled parameter types: " + java.util.Arrays.<Object>asList(_self).toString()); };
    return (java.lang.Object)result;
  }
  
  protected static Object _privk3_evaluate(final ObjectTemplateValueAspectObjectTemplateValueAspectProperties _self_, final ObjectTemplateValue _self) {
    ObjectTemplate _objecttemplate = _self.getObjecttemplate();
    return ObjectTemplateAspect.currentObject(_objecttemplate);
  }
}
