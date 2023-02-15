package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.Binding;

@SuppressWarnings("all")
public class BindingAspectBindingAspectContext {
  public static final BindingAspectBindingAspectContext INSTANCE = new BindingAspectBindingAspectContext();
  
  public static BindingAspectBindingAspectProperties getSelf(final Binding _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.BindingAspectBindingAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<Binding, BindingAspectBindingAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.Binding, org.tetrabox.example.minitl.semantics.BindingAspectBindingAspectProperties>();
  
  public Map<Binding, BindingAspectBindingAspectProperties> getMap() {
    return map;
  }
}
