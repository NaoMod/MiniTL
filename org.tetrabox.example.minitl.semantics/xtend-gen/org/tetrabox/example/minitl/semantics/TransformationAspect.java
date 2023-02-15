package org.tetrabox.example.minitl.semantics;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import fr.inria.diverse.k3.al.annotationprocessor.Aspect;
import fr.inria.diverse.k3.al.annotationprocessor.InitializeModel;
import fr.inria.diverse.k3.al.annotationprocessor.Main;
import fr.inria.diverse.k3.al.annotationprocessor.Step;
import java.io.File;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.tetrabox.example.minitl.Metamodel;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.Transformation;

@Aspect(className = Transformation.class)
@SuppressWarnings("all")
public class TransformationAspect {
  @InitializeModel
  @Step
  public static void initialize(final Transformation _self, final List<String> args) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void initialize(List<String>)
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_initialize(_self_, (org.tetrabox.example.minitl.Transformation)_self,args);
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {args}, command, "Transformation", "initialize");
    	} else {
    		command.execute();
    	}
    	;
    };
  }
  
  @Main
  @Step
  public static void execute(final Transformation _self) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void execute()
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand command = new fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepCommand() {
    		@Override
    		public void execute() {
    			org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_execute(_self_, (org.tetrabox.example.minitl.Transformation)_self);
    		}
    	};
    	fr.inria.diverse.k3.al.annotationprocessor.stepmanager.IStepManager stepManager = fr.inria.diverse.k3.al.annotationprocessor.stepmanager.StepManagerRegistry.getInstance().findStepManager(_self);
    	if (stepManager != null) {
    		stepManager.executeStep(_self, new Object[] {_self}, command, "Transformation", "execute");
    	} else {
    		command.execute();
    	}
    	;
    };
  }
  
  public static List<EObject> inputModel(final Transformation _self) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# List<EObject> inputModel()
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	result = org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_inputModel(_self_, (org.tetrabox.example.minitl.Transformation)_self);
    };
    return (java.util.List<org.eclipse.emf.ecore.EObject>)result;
  }
  
  public static void inputModel(final Transformation _self, final List<EObject> inputModel) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void inputModel(List<EObject>)
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_inputModel(_self_, (org.tetrabox.example.minitl.Transformation)_self,inputModel);
    };
  }
  
  public static List<EObject> outputModel(final Transformation _self) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# List<EObject> outputModel()
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	result = org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_outputModel(_self_, (org.tetrabox.example.minitl.Transformation)_self);
    };
    return (java.util.List<org.eclipse.emf.ecore.EObject>)result;
  }
  
  public static void outputModel(final Transformation _self, final List<EObject> outputModel) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void outputModel(List<EObject>)
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_outputModel(_self_, (org.tetrabox.example.minitl.Transformation)_self,outputModel);
    };
  }
  
  public static String inputModelURI(final Transformation _self) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# String inputModelURI()
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	result = org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_inputModelURI(_self_, (org.tetrabox.example.minitl.Transformation)_self);
    };
    return (java.lang.String)result;
  }
  
  public static void inputModelURI(final Transformation _self, final String inputModelURI) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void inputModelURI(String)
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_inputModelURI(_self_, (org.tetrabox.example.minitl.Transformation)_self,inputModelURI);
    };
  }
  
  public static String outputFilePath(final Transformation _self) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    Object result = null;
    // #DispatchPointCut_before# String outputFilePath()
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	result = org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_outputFilePath(_self_, (org.tetrabox.example.minitl.Transformation)_self);
    };
    return (java.lang.String)result;
  }
  
  public static void outputFilePath(final Transformation _self, final String outputFilePath) {
    final org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectProperties _self_ = org.tetrabox.example.minitl.semantics.TransformationAspectTransformationAspectContext.getSelf(_self);
    // #DispatchPointCut_before# void outputFilePath(String)
    if (_self instanceof org.tetrabox.example.minitl.Transformation){
    	org.tetrabox.example.minitl.semantics.TransformationAspect._privk3_outputFilePath(_self_, (org.tetrabox.example.minitl.Transformation)_self,outputFilePath);
    };
  }
  
  protected static void _privk3_initialize(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self, final List<String> args) {
    TransformationAspect.inputModelURI(_self, args.get(0));
    TransformationAspect.outputFilePath(_self, args.get(1));
    final ResourceSet rs = _self.eResource().getResourceSet();
    final Resource inputModelResource = rs.getResource(URI.createURI(TransformationAspect.inputModelURI(_self)), true);
    Metamodel _inputMetamodel = _self.getInputMetamodel();
    boolean _notEquals = (!Objects.equal(_inputMetamodel, null));
    if (_notEquals) {
      final Function1<EPackage, Set<EClass>> _function = (EPackage p) -> {
        return IteratorExtensions.<EClass>toSet(Iterators.<EClass>filter(p.eAllContents(), EClass.class));
      };
      final Set<EClass> allInputMetamodelClasses = IterableExtensions.<EClass>toSet(Iterables.<EClass>concat(ListExtensions.<EPackage, Set<EClass>>map(_self.getInputMetamodel().getPackages(), _function)));
      TransformationAspect.inputModel(_self).addAll(IteratorExtensions.<EObject>toSet(inputModelResource.getAllContents()));
      List<EObject> _inputModel = TransformationAspect.inputModel(_self);
      for (final EObject o : _inputModel) {
        boolean _contains = allInputMetamodelClasses.contains(o.eClass());
        boolean _not = (!_contains);
        if (_not) {
          EClass _eClass = o.eClass();
          String _plus = ("The input model does not conform to the input metamodel: type " + _eClass);
          String _plus_1 = (_plus + " of object ");
          String _plus_2 = (_plus_1 + o);
          String _plus_3 = (_plus_2 + " is unknown");
          throw new RuntimeException(_plus_3);
        }
      }
    }
  }
  
  protected static void _privk3_execute(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self) {
    try {
      EList<Rule> _rules = _self.getRules();
      for (final Rule r : _rules) {
        RuleAspect.apply(r);
      }
      if (((!Objects.equal(TransformationAspect.outputFilePath(_self), null)) && (!Objects.equal(TransformationAspect.outputFilePath(_self), "")))) {
        final ResourceSetImpl rs = new ResourceSetImpl();
        String _outputFilePath = TransformationAspect.outputFilePath(_self);
        final File outputFile = new File(_outputFilePath);
        boolean _exists = outputFile.exists();
        if (_exists) {
          outputFile.delete();
        }
        outputFile.getParentFile().mkdirs();
        final URI outputModelURI = URI.createFileURI(outputFile.getAbsolutePath());
        final Resource outputModelResource = rs.createResource(outputModelURI);
        outputModelResource.getContents().addAll(TransformationAspect.outputModel(_self));
        outputModelResource.save(null);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected static List<EObject> _privk3_inputModel(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self) {
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("getInputModel") &&
    			m.getParameterTypes().length == 0) {
    				Object ret = m.invoke(_self);
    				if (ret != null) {
    					return (java.util.List) ret;
    				} else {
    					return null;
    				}
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    return _self_.inputModel;
  }
  
  protected static void _privk3_inputModel(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self, final List<EObject> inputModel) {
    boolean setterCalled = false;
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("setInputModel")
    				&& m.getParameterTypes().length == 1) {
    			m.invoke(_self, inputModel);
    			setterCalled = true;
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    if (!setterCalled) {
    	_self_.inputModel = inputModel;
    }
  }
  
  protected static List<EObject> _privk3_outputModel(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self) {
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("getOutputModel") &&
    			m.getParameterTypes().length == 0) {
    				Object ret = m.invoke(_self);
    				if (ret != null) {
    					return (java.util.List) ret;
    				} else {
    					return null;
    				}
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    return _self_.outputModel;
  }
  
  protected static void _privk3_outputModel(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self, final List<EObject> outputModel) {
    boolean setterCalled = false;
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("setOutputModel")
    				&& m.getParameterTypes().length == 1) {
    			m.invoke(_self, outputModel);
    			setterCalled = true;
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    if (!setterCalled) {
    	_self_.outputModel = outputModel;
    }
  }
  
  protected static String _privk3_inputModelURI(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self) {
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("getInputModelURI") &&
    			m.getParameterTypes().length == 0) {
    				Object ret = m.invoke(_self);
    				if (ret != null) {
    					return (java.lang.String) ret;
    				} else {
    					return null;
    				}
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    return _self_.inputModelURI;
  }
  
  protected static void _privk3_inputModelURI(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self, final String inputModelURI) {
    boolean setterCalled = false;
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("setInputModelURI")
    				&& m.getParameterTypes().length == 1) {
    			m.invoke(_self, inputModelURI);
    			setterCalled = true;
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    if (!setterCalled) {
    	_self_.inputModelURI = inputModelURI;
    }
  }
  
  protected static String _privk3_outputFilePath(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self) {
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("getOutputFilePath") &&
    			m.getParameterTypes().length == 0) {
    				Object ret = m.invoke(_self);
    				if (ret != null) {
    					return (java.lang.String) ret;
    				} else {
    					return null;
    				}
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    return _self_.outputFilePath;
  }
  
  protected static void _privk3_outputFilePath(final TransformationAspectTransformationAspectProperties _self_, final Transformation _self, final String outputFilePath) {
    boolean setterCalled = false;
    try {
    	for (java.lang.reflect.Method m : _self.getClass().getMethods()) {
    		if (m.getName().equals("setOutputFilePath")
    				&& m.getParameterTypes().length == 1) {
    			m.invoke(_self, outputFilePath);
    			setterCalled = true;
    		}
    	}
    } catch (Exception e) {
    	// Chut !
    }
    if (!setterCalled) {
    	_self_.outputFilePath = outputFilePath;
    }
  }
}
