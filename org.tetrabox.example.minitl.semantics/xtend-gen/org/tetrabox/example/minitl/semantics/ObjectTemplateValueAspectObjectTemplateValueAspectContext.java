package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.ObjectTemplateValue;

@SuppressWarnings("all")
public class ObjectTemplateValueAspectObjectTemplateValueAspectContext {
  public static final ObjectTemplateValueAspectObjectTemplateValueAspectContext INSTANCE = new ObjectTemplateValueAspectObjectTemplateValueAspectContext();
  
  public static ObjectTemplateValueAspectObjectTemplateValueAspectProperties getSelf(final ObjectTemplateValue _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspectObjectTemplateValueAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<ObjectTemplateValue, ObjectTemplateValueAspectObjectTemplateValueAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.ObjectTemplateValue, org.tetrabox.example.minitl.semantics.ObjectTemplateValueAspectObjectTemplateValueAspectProperties>();
  
  public Map<ObjectTemplateValue, ObjectTemplateValueAspectObjectTemplateValueAspectProperties> getMap() {
    return map;
  }
}
