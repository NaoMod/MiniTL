package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.Value;

@SuppressWarnings("all")
public class ValueAspectValueAspectContext {
  public static final ValueAspectValueAspectContext INSTANCE = new ValueAspectValueAspectContext();
  
  public static ValueAspectValueAspectProperties getSelf(final Value _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.ValueAspectValueAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<Value, ValueAspectValueAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.Value, org.tetrabox.example.minitl.semantics.ValueAspectValueAspectProperties>();
  
  public Map<Value, ValueAspectValueAspectProperties> getMap() {
    return map;
  }
}
