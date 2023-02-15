package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.BinaryExpression;

@SuppressWarnings("all")
public class BinaryExpressionAspectBinaryExpressionAspectContext {
  public static final BinaryExpressionAspectBinaryExpressionAspectContext INSTANCE = new BinaryExpressionAspectBinaryExpressionAspectContext();
  
  public static BinaryExpressionAspectBinaryExpressionAspectProperties getSelf(final BinaryExpression _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.BinaryExpressionAspectBinaryExpressionAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<BinaryExpression, BinaryExpressionAspectBinaryExpressionAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.BinaryExpression, org.tetrabox.example.minitl.semantics.BinaryExpressionAspectBinaryExpressionAspectProperties>();
  
  public Map<BinaryExpression, BinaryExpressionAspectBinaryExpressionAspectProperties> getMap() {
    return map;
  }
}
