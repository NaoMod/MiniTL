package org.tetrabox.example.minitl.semantics.test.nogemoc;

import java.util.Collections;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.tetrabox.example.minitl.Transformation;
import org.tetrabox.example.minitl.semantics.TransformationAspect;

@SuppressWarnings("all")
public class SemanticsTest {
  @Test
  public void test() {
    final URI inputTransformationURI = URI.createPlatformPluginURI(
      "/org.tetrabox.example.minitl.tcs/examples/Transformation.xmi", true);
    final ResourceSet rs = new ResourceSetImpl();
    final Resource transfoResource = rs.getResource(inputTransformationURI, true);
    EObject _head = IterableExtensions.<EObject>head(transfoResource.getContents());
    final Transformation transformation = ((Transformation) _head);
    final String inputModelURIString = "platform:/plugin/org.tetrabox.example.minitl.tcs/examples/input_model.xmi";
    final String outputModelFilePath = "out/output_model.xmi";
    TransformationAspect.initialize(transformation, Collections.<String>unmodifiableList(CollectionLiterals.<String>newArrayList(inputModelURIString, outputModelFilePath)));
    TransformationAspect.execute(transformation);
    final int nbOutput = TransformationAspect.outputModel(transformation).size();
    Assert.assertEquals(nbOutput, 4);
  }
}
