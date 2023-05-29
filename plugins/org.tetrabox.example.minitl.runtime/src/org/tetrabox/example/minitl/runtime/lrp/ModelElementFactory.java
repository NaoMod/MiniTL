package org.tetrabox.example.minitl.runtime.lrp;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.BidiIterator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.ITextRegionWithLineInformation;
import org.eclipse.xtext.util.LineAndColumn;
import org.tetrabox.example.minitl.BinaryExpression;
import org.tetrabox.example.minitl.Binding;
import org.tetrabox.example.minitl.FieldAccessValue;
import org.tetrabox.example.minitl.Metamodel;
import org.tetrabox.example.minitl.ObjectTemplate;
import org.tetrabox.example.minitl.ObjectTemplateValue;
import org.tetrabox.example.minitl.Rule;
import org.tetrabox.example.minitl.StringValue;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.Value;
import org.tetrabox.example.minitl.runtime.MiniTLRuntime;
import org.tetrabox.example.minitl.runtime.exceptions.LocationException;
import org.tetrabox.example.minitl.runtime.exceptions.ValueTypeException;
import org.tetrabox.example.minitl.runtime.serializers.IDRegistry;
import org.tetrabox.example.minitl.semantics.ObjectTemplateAspect;
import org.tetrabox.example.minitl.semantics.TransformationAspect;
import org.tetrabox.example.minitl.semantics.ValueAspect;
	
public class ModelElementFactory {
	
	private ModelElementFactory() { }

    public static ModelElement fromTransformation(Transformation transformation) throws LocationException, ValueTypeException  {
        ModelElement element = new ModelElement(IDRegistry.getId(transformation), transformation.eClass().getName());
        element.getAttributes().put("name", Either.forLeft(transformation.getName()));

        element.getChildren().put("inputMetamodel", Either.forLeft(fromMetamodel(transformation.getInputMetamodel())));
        element.getChildren().put("outputMetamodel",
                Either.forLeft(fromMetamodel(transformation.getOutputMetamodel())));

        List<ModelElement> ruleModelElements = new LinkedList<>();
        for (Rule rule : transformation.getRules()) {
            ruleModelElements.add(fromRule(rule));
        }
        element.getChildren().put("rules", Either.forRight(ruleModelElements));

        return element;
    }

    public static ModelElement fromRule(Rule rule) throws LocationException, ValueTypeException  {
        ICompositeNode ruleNode = NodeModelUtils.getNode(rule);
        BidiIterator<INode> ite = ruleNode.getChildren().iterator();
        LineAndColumn startPosition = null;
        LineAndColumn endPosition = null;
        
        while (ite.hasNext() && endPosition == null) {
            INode currentNode = ite.next();
            EObject grammarElement = currentNode.getGrammarElement();

            if (grammarElement instanceof Keyword && ((Keyword) grammarElement).getValue().equals("rule")) {
            	startPosition = getStartPosition(currentNode);
                continue;
            }

            if (grammarElement instanceof RuleCall && ((RuleCall) grammarElement).getRule().getName().equals("ID"))
            	endPosition = getEndPosition(currentNode);
        }

        if (startPosition == null || endPosition == null)
            throw new LocationException(rule.getName());

        Location location = new Location(startPosition.getLine(), startPosition.getColumn(), endPosition.getLine(), endPosition.getColumn() - 1);

        ModelElement element = new ModelElement(IDRegistry.getId(rule), rule.eClass().getName(), location);

        element.getAttributes().put("name", Either.forLeft(rule.getName()));

        List<ModelElement> objectTemplateModelElements = new LinkedList<>();
        for (ObjectTemplate objectTemplate : rule.getObjectTemplates()) {
            objectTemplateModelElements.add(fromObjectTemplate(objectTemplate));
        }
        element.getChildren().put("objectTemplates", Either.forRight(objectTemplateModelElements));

        return element;
    }

    public static ModelElement fromObjectTemplate(ObjectTemplate objectTemplate) throws ValueTypeException, LocationException {
        ModelElement element = new ModelElement(IDRegistry.getId(objectTemplate), objectTemplate.eClass().getName());

        element.getAttributes().put("name", Either.forLeft(objectTemplate.getName()));

        List<ModelElement> bindingModelElements = new LinkedList<>();
        for (Binding binding : objectTemplate.getBindings()) {
            bindingModelElements.add(fromBinding(binding));
        }
        element.getChildren().put("bindings", Either.forRight(bindingModelElements));

        return element;
    }

    public static ModelElement fromBinding(Binding binding) throws ValueTypeException, LocationException {
    	ICompositeNode bindingNode = NodeModelUtils.getNode(binding);
        BidiIterator<INode> ite = bindingNode.getChildren().iterator();
        LineAndColumn startPosition = null;
        LineAndColumn endPosition = null;
        
        while (ite.hasNext() && endPosition == null) {
            INode currentNode = ite.next();
            EObject grammarElement = currentNode.getGrammarElement();
            
            if (grammarElement instanceof CrossReference) {
            	startPosition = getStartPosition(currentNode);
                continue;
            }

            if (grammarElement instanceof RuleCall && ((RuleCall) grammarElement).getRule().getName().equals("Value"))
            	endPosition = getEndPosition(currentNode);
        }
        
        if (startPosition == null || endPosition == null)
            throw new LocationException(binding.getFeature().getName());

        Location location = new Location(startPosition.getLine(), startPosition.getColumn(), endPosition.getLine(), endPosition.getColumn() - 1);
        
        ModelElement element = new ModelElement(IDRegistry.getId(binding), binding.eClass().getName(), location);

        element.getAttributes().put("feature", Either.forLeft(binding.getFeature().getName()));
        element.getChildren().put("value", Either.forLeft(fromValue(binding.getValue())));

        return element;
    }

    public static ModelElement fromValue(Value value) throws ValueTypeException {
        if (value instanceof StringValue)
            return fromStringValue((StringValue) value);

        if (value instanceof ObjectTemplateValue)
            return fromObjectTemplateValue((ObjectTemplateValue) value);

        if (value instanceof FieldAccessValue)
            return fromFieldAccessValue((FieldAccessValue) value);

        if (value instanceof BinaryExpression)
            return fromBinaryExpression((BinaryExpression) value);

        throw new ValueTypeException(value.eClass().getName());
    }

    public static ModelElement fromStringValue(StringValue value) {
        ModelElement element = new ModelElement(IDRegistry.getId(value), value.eClass().getName());

        element.getAttributes().put("value", Either.forLeft(value.getValue()));

        return element;
    }

    public static ModelElement fromObjectTemplateValue(ObjectTemplateValue value) {
        ModelElement element = new ModelElement(IDRegistry.getId(value), value.eClass().getName());

        element.getRefs().put("objecttemplate", Either.forLeft(IDRegistry.getId(value.getObjecttemplate())));

        return element;
    }

    public static ModelElement fromFieldAccessValue(FieldAccessValue value) {
        ModelElement element = new ModelElement(IDRegistry.getId(value), value.eClass().getName());

        element.getAttributes().put("feature", Either.forLeft(value.getFeature().getName()));

        return element;
    }

    public static ModelElement fromBinaryExpression(BinaryExpression expression) throws ValueTypeException {
        ModelElement element = new ModelElement(IDRegistry.getId(expression), expression.eClass().getName());

        element.getAttributes().put("operator", Either.forLeft(expression.getOperator()));

        List<ModelElement> expressions = new LinkedList<>();
        for (Value expr : expression.getExpressions()) {
            expressions.add(fromValue(expr));
        }
        element.getChildren().put("expressions", Either.forRight(expressions));

        return element;
    }

    public static ModelElement fromMetamodel(Metamodel metamodel) {
        ModelElement element = new ModelElement(IDRegistry.getId(metamodel), metamodel.eClass().getName());
        
        List<ModelElement> packages = new LinkedList<>();
        for (EPackage pack : metamodel.getPackages()) {
			packages.add(fromPackage(pack));
		}
        
        element.getChildren().put("packages", Either.forRight(packages));

        return element;
    }
    
    public static ModelElement fromPackage(EPackage pack) {
        ModelElement element = new ModelElement(IDRegistry.getId(pack), pack.eClass().getName());
        
        element.getAttributes().put("name", Either.forLeft(pack.getName()));

        return element;
    }

    public static ModelElement fromMiniTLRuntime(MiniTLRuntime runtime) {
        ModelElement element = new ModelElement(IDRegistry.getId(runtime), "Runtime");
        Binding nextBinding = runtime.getNextBinding();
        Object value = nextBinding == null ? null : ValueAspect.evaluate(runtime.getNextBinding().getValue());
        
        element.getAttributes().put("featureValue", Either.forLeft(value));
        
        List<ModelElement> inputModel = new LinkedList<>();
        for (EObject modelElement : TransformationAspect.inputModel(runtime.getTransformation())) {
			inputModel.add(fromEObject(modelElement));
		}
        
        List<ModelElement> outputModel = new LinkedList<>();
        for (EObject modelElement : TransformationAspect.outputModel(runtime.getTransformation())) {
			outputModel.add(fromEObject(modelElement));
		}
        
        EObject currentObject = nextBinding == null ? null : ObjectTemplateAspect.currentObject((ObjectTemplate) nextBinding.eContainer());
        
        element.getChildren().put("inputModel", Either.forRight(inputModel));
        element.getChildren().put("outputModel", Either.forRight(outputModel));
        element.getChildren().put("currentObject", Either.forLeft(fromEObject(currentObject)));
        
        element.getRefs().put("currentRule", Either.forLeft(IDRegistry.getId(runtime.getNextRule())));
        element.getRefs().put("nextBinding", Either.forLeft(IDRegistry.getId(nextBinding)));
        
        return element;
    }
    
    public static ModelElement fromEObject(EObject eobject) {
    	if (eobject == null) return null;
    	
    	ModelElement element = new ModelElement(IDRegistry.getId(eobject), "EObject");
    	
    	for (EStructuralFeature feature : eobject.eClass().getEStructuralFeatures()) {
    		element.getAttributes().put(feature.getName(), Either.forLeft(eobject.eGet(feature)));
		}
    	
    	return element;
    }
    
    private static LineAndColumn getStartPosition(INode node) {
    	ITextRegionWithLineInformation nodeRegion = node.getTextRegionWithLineInformation();
    	return NodeModelUtils.getLineAndColumn(node, nodeRegion.getOffset());
    }
    
    private static LineAndColumn getEndPosition(INode node) {
    	ITextRegionWithLineInformation nodeRegion = node.getTextRegionWithLineInformation();
    	return NodeModelUtils.getLineAndColumn(node, nodeRegion.getOffset() + nodeRegion.getLength());
    }
}
