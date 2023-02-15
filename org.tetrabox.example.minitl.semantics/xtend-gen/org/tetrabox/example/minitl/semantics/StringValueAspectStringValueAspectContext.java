package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.StringValue;

@SuppressWarnings("all")
public class StringValueAspectStringValueAspectContext {
  public static final StringValueAspectStringValueAspectContext INSTANCE = new StringValueAspectStringValueAspectContext();
  
  public static StringValueAspectStringValueAspectProperties getSelf(final StringValue _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.StringValueAspectStringValueAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<StringValue, StringValueAspectStringValueAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.StringValue, org.tetrabox.example.minitl.semantics.StringValueAspectStringValueAspectProperties>();
  
  public Map<StringValue, StringValueAspectStringValueAspectProperties> getMap() {
    return map;
  }
}
