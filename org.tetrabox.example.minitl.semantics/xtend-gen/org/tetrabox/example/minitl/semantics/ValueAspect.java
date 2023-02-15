package org.tetrabox.example.minitl.semantics;

import fr.inria.diverse.k3.al.annotationprocessor.Abstract;
import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import org.tetrabox.example.minitl.Value;

@Aspect(className = Value.class)
@SuppressWarnings("all")
public abstract class ValueAspect {
  @Abstract
  public static Object evaluate(final Value _self) {
    final org.tetrabox.example.minitl.semantics.ValueAspectValueAspectProperties _self_ = org.tetrabox.example.minitl.semantics.ValueAspectValueAspectContext.getSelf(_self);
    Object result = null;
    	// BeginInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.FieldAccessValueAspect
    		if (_self instanceof org.tetrabox.example.minitl.FieldAccessValue){
    			result = org.tetrabox.example.minitl.semantics.FieldAccessValueAspect.evaluate((org.tetrabox.example.minitl.FieldAccessValue)_self);
    		} else
    		// EndInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.FieldAccessValueAspect
    	// BeginInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspect
    		if (_self instanceof org.tetrabox.example.minitl.ObjectTemplateValue){
    			result = org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspect.evaluate((org.tetrabox.example.minitl.ObjectTemplateValue)_self);
    		} else
    		// EndInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspect
    	// BeginInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.BinaryExpressionAspect
    		if (_self instanceof org.tetrabox.example.minitl.BinaryExpression){
    			result = org.tetrabox.example.minitl.semantics.BinaryExpressionAspect.evaluate((org.tetrabox.example.minitl.BinaryExpression)_self);
    		} else
    		// EndInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.BinaryExpressionAspect
    	// BeginInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.StringValueAspect
    		if (_self instanceof org.tetrabox.example.minitl.StringValue){
    			result = org.tetrabox.example.minitl.semantics.StringValueAspect.evaluate((org.tetrabox.example.minitl.StringValue)_self);
    		} else
    		// EndInjectInto org.tetrabox.example.minitl.semantics.ValueAspect#Object evaluate() from org.tetrabox.example.minitl.semantics.StringValueAspect
    // #DispatchPointCut_before# Object evaluate()
    if (_self instanceof org.tetrabox.example.minitl.Value){
    	result = org.tetrabox.example.minitl.semantics.ValueAspect._privk3_evaluate(_self_, (org.tetrabox.example.minitl.Value)_self);
    };
    return (java.lang.Object)result;
  }
  
  protected static Object _privk3_evaluate(final ValueAspectValueAspectProperties _self_, final Value _self) {
    throw new java.lang.RuntimeException("Not implemented");
  }
}
