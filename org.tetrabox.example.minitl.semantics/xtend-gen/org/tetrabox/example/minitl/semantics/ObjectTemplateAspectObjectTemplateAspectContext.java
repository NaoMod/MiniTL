package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.ObjectTemplate;

@SuppressWarnings("all")
public class ObjectTemplateAspectObjectTemplateAspectContext {
  public static final ObjectTemplateAspectObjectTemplateAspectContext INSTANCE = new ObjectTemplateAspectObjectTemplateAspectContext();
  
  public static ObjectTemplateAspectObjectTemplateAspectProperties getSelf(final ObjectTemplate _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<ObjectTemplate, ObjectTemplateAspectObjectTemplateAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.ObjectTemplate, org.tetrabox.example.minitl.semantics.ObjectTemplateAspectObjectTemplateAspectProperties>();
  
  public Map<ObjectTemplate, ObjectTemplateAspectObjectTemplateAspectProperties> getMap() {
    return map;
  }
}
