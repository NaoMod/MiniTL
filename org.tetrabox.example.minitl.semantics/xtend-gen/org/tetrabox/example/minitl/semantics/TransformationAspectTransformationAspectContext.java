package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.Transformation;

@SuppressWarnings("all")
public class TransformationAspectTransformationAspectContext {
  public static final TransformationAspectTransformationAspectContext INSTANCE = new TransformationAspectTransformationAspectContext();
  
  public static TransformationAspectTransformationAspectProperties getSelf(final Transformation _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<Transformation, TransformationAspectTransformationAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.Transformation, org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties>();
  
  public Map<Transformation, TransformationAspectTransformationAspectProperties> getMap() {
    return map;
  }
}
