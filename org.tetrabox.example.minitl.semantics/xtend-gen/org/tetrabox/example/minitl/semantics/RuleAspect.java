package org.tetrabox.example.minitl.semantics;

import com.google.common.base.Objects;
import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.Step;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.tetrabox.example.minitl.ObjectTemplate;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;

@Aspect(className = Rule.class)
@SuppressWarnings("all")
public class RuleAspect {
  /**
   * Applies the rule to each possible match in the input model.
   */
  @Step
  public static void apply(final Rule _self) {
    final org.tetrabox.example.minitl.semantics.RuleAspectRuleAspectProperties _self_ = org.tetrabox.example.minitl.semantics.RuleAspectRuleAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void apply()
    if (_self instanceof org.tetrabox.example.minitl.Rule){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			org.tetrabox.example.minitl.semantics.RuleAspect._privk3_apply(_self_, (org.tetrabox.example.minitl.Rule)_self);
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {_self}, command, "Rule", "apply");
    	} else {
    		command.execute();
    	}
    	;
    };
  }
  
  protected static void _privk3_apply(final RuleAspectRuleAspectProperties _self_, final Rule _self) {
    final ObjectTemplate inputObjectTemplate = _self.getObjectTemplates().get(0);
    final ObjectTemplate outputObjectTemplate = _self.getObjectTemplates().get(1);
    EObject _eContainer = _self.eContainer();
    List<EObject> _inputModel = TransformationAspect.inputModel(((Transformation) _eContainer));
    for (final EObject o : _inputModel) {
      {
        ObjectTemplateAspect.match(inputObjectTemplate, o);
        EObject _currentObject = ObjectTemplateAspect.currentObject(inputObjectTemplate);
        boolean _notEquals = (!Objects.equal(_currentObject, null));
        if (_notEquals) {
          ObjectTemplateAspect.construct(outputObjectTemplate);
          EObject _eContainer_1 = _self.eContainer();
          TransformationAspect.outputModel(((Transformation) _eContainer_1)).add(ObjectTemplateAspect.currentObject(outputObjectTemplate));
        }
        ObjectTemplateAspect.reset(inputObjectTemplate);
        ObjectTemplateAspect.reset(outputObjectTemplate);
      }
    }
  }
}
