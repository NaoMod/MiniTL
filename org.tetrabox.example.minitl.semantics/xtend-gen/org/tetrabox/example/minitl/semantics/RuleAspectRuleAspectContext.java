package org.tetrabox.example.minitl.semantics;

import java.util.Map;
import org.tetrabox.example.minitl.Rule;

@SuppressWarnings("all")
public class RuleAspectRuleAspectContext {
  public static final RuleAspectRuleAspectContext INSTANCE = new RuleAspectRuleAspectContext();
  
  public static RuleAspectRuleAspectProperties getSelf(final Rule _self) {
    		if (!INSTANCE.map.containsKey(_self))
    			INSTANCE.map.put(_self, new org.tetrabox.example.minitl.semantics.RuleAspectRuleAspectProperties());
    		return INSTANCE.map.get(_self);
  }
  
  private Map<Rule, RuleAspectRuleAspectProperties> map = new java.util.WeakHashMap<org.tetrabox.example.minitl.Rule, org.tetrabox.example.minitl.semantics.RuleAspectRuleAspectProperties>();
  
  public Map<Rule, RuleAspectRuleAspectProperties> getMap() {
    return map;
  }
}
