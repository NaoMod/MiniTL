package org.tetrabox.example.minitl.runtime.lrp;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.nodemodel.BidiIterator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
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
import org.tetrabox.example.minitl.runtime.serializers.IDRegistry;

public class ModelElementFactory {

    public static ModelElement fromTransformation(Transformation transformation) throws Exception {
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

    public static ModelElement fromRule(Rule rule) throws Exception {
        ICompositeNode ruleNode = NodeModelUtils.getNode(rule);
        BidiIterator<INode> ite = ruleNode.getChildren().iterator();
        Integer startLine = null;
        Integer endLine = null;
        Integer startColumn = null;
        Integer endColumn = null;
        int columnOffset = 0;

        while (ite.hasNext() && endColumn == null) {
            INode currentNode = ite.next();
            EObject grammarElement = currentNode.getGrammarElement();
            
            if (grammarElement instanceof TerminalRule && ((TerminalRule) grammarElement).getName().equals("WS")) {
            	columnOffset += 1;
            }

            if (grammarElement instanceof Keyword && ((Keyword) grammarElement).getValue().equals("rule")) {
                LineAndColumn position = NodeModelUtils.getLineAndColumn(currentNode, currentNode.getTotalOffset());
                startLine = position.getLine();
                startColumn = 1 + columnOffset;
                columnOffset += currentNode.getLength();
            }

            if (grammarElement instanceof RuleCall && ((RuleCall) grammarElement).getRule().getName().equals("ID")) {
                LineAndColumn position = NodeModelUtils.getLineAndColumn(currentNode, currentNode.getTotalOffset());
                endLine = position.getLine();
                endColumn = columnOffset + currentNode.getLength();
            }
        }

        if (endColumn == null)
            throw new Exception("Couldn't process position of rule " + rule.getName() + ".");

        Location location = new Location(startLine, startColumn, endLine, endColumn);

        ModelElement element = new ModelElement(IDRegistry.getId(rule), rule.eClass().getName(), location);

        element.getAttributes().put("name", Either.forLeft(rule.getName()));

        List<ModelElement> objectTemplateModelElements = new LinkedList<>();
        for (ObjectTemplate objectTemplate : rule.getObjectTemplates()) {
            objectTemplateModelElements.add(fromObjectTemplate(objectTemplate));
        }
        element.getChildren().put("objectTemplates", Either.forRight(objectTemplateModelElements));

        return element;
    }

    public static ModelElement fromObjectTemplate(ObjectTemplate objectTemplate) throws Exception {
        ModelElement element = new ModelElement(IDRegistry.getId(objectTemplate), objectTemplate.eClass().getName());

        element.getAttributes().put("name", Either.forLeft(objectTemplate.getName()));

        List<ModelElement> bindingModelElements = new LinkedList<>();
        for (Binding binding : objectTemplate.getBindings()) {
            bindingModelElements.add(fromBinding(binding));
        }
        element.getChildren().put("bindings", Either.forRight(bindingModelElements));

        /*
        String typeId = IDRegistry.getId(objectTemplate.getType());
        element.getRefs().put("type", Either.forLeft(typeId));
        */

        return element;
    }

    public static ModelElement fromBinding(Binding binding) throws Exception {
        ModelElement element = new ModelElement(IDRegistry.getId(binding), binding.eClass().getName());

        // TODO: add feature as child or ref
        element.getAttributes().put("feature", Either.forLeft(binding.getFeature().getName()));

        element.getChildren().put("value", Either.forLeft(fromValue(binding.getValue())));

        return element;
    }

    public static ModelElement fromValue(Value value) throws Exception {
        if (value instanceof StringValue)
            return fromStringValue((StringValue) value);

        if (value instanceof ObjectTemplateValue)
            return fromObjectTemplateValue((ObjectTemplateValue) value);

        if (value instanceof FieldAccessValue)
            return fromFieldAccessValue((FieldAccessValue) value);

        if (value instanceof BinaryExpression)
            return fromBinaryExpression((BinaryExpression) value);

        throw new Exception("Unknwon value type " + value.eClass().getName() + ".");
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

    public static ModelElement fromFieldAccessValue(FieldAccessValue value) throws Exception {
        ModelElement element = new ModelElement(IDRegistry.getId(value), value.eClass().getName());

        element.getAttributes().put("feature", Either.forLeft(value.getFeature().getName()));

        return element;
    }

    public static ModelElement fromBinaryExpression(BinaryExpression expression) throws Exception {
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
        
        element.getRefs().put("nextRule", Either.forLeft(IDRegistry.getId(runtime.getNextRule())));

        return element;
    }
}
