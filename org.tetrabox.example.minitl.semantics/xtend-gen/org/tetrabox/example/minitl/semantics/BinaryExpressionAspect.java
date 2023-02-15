package org.tetrabox.example.minitl.semantics;

import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.OverrideAspectMethod;
import org.tetrabox.example.minitl.BinaryExpression;
import org.tetrabox.example.minitl.Operator;
import org.tetrabox.example.minitl.Value;

@Aspect(className = BinaryExpression.class)
@SuppressWarnings("all")
public class BinaryExpressionAspect extends ValueAspect {
  @OverrideAspectMethod
  public static Object evaluate(final BinaryExpression _self) {
    final org.tetrabox.example.minitl.semantics.BinaryExpressionAspectBinaryExpressionAspectProperties _self_ = org.tetrabox.example.minitl.semantics.BinaryExpressionAspectBinaryExpressionAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# Object evaluate()
    if (_self instanceof org.tetrabox.example.minitl.BinaryExpression){
    	result = org.tetrabox.example.minitl.semantics.BinaryExpressionAspect._privk3_evaluate(_self_, (org.tetrabox.example.minitl.BinaryExpression)_self);
    };
    return (java.lang.Object)result;
  }
  
  protected static Object _privk3_evaluate(final BinaryExpressionAspectBinaryExpressionAspectProperties _self_, final BinaryExpression _self) {
    final Value left = _self.getExpressions().get(0);
    final Value right = _self.getExpressions().get(1);
    final Object leftComputedValue = ValueAspect.evaluate(left);
    final Object rightComputedValue = ValueAspect.evaluate(right);
    Operator _operator = _self.getOperator();
    if (_operator != null) {
      switch (_operator) {
        case ADD:
          if (((leftComputedValue instanceof String) || (rightComputedValue instanceof String))) {
            String _string = leftComputedValue.toString();
            String _string_1 = rightComputedValue.toString();
            return (_string + _string_1);
          }
          if (((leftComputedValue instanceof Integer) && (rightComputedValue instanceof Integer))) {
            return new Integer(((((Integer) leftComputedValue)).intValue() + (((Integer) rightComputedValue)).intValue()));
          }
          throw new RuntimeException("Incompatible types for addition operator.");
        case SUB:
          if (((leftComputedValue instanceof Integer) && (rightComputedValue instanceof Integer))) {
            return new Integer(((((Integer) leftComputedValue)).intValue() - (((Integer) rightComputedValue)).intValue()));
          }
          throw new RuntimeException("Incompatible types for addition operator.");
        default:
          throw new RuntimeException("Not a valid operator.");
      }
    } else {
      throw new RuntimeException("Not a valid operator.");
    }
  }
}
