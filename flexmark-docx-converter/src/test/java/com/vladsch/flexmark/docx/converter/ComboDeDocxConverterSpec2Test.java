package com.vladsch.flexmark.docx.converter;

import com.vladsch.flexmark.test.util.spec.ResourceLocation;
import com.vladsch.flexmark.test.util.spec.SpecExample;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.List;

public class ComboDeDocxConverterSpec2Test extends ComboDocxConverterSpecTestBase {
  final private static String SPEC_RESOURCE = "/docx_converter_de_ast_spec2.md";
  final public static @NotNull ResourceLocation RESOURCE_LOCATION = ResourceLocation.of(SPEC_RESOURCE);
  final private static String TEMPLATE_XML = "/DE-Template.xml";

  final private static DataHolder OPTIONS = new MutableDataSet()
      .set(DocxRenderer.DEFAULT_TEMPLATE_RESOURCE, TEMPLATE_XML)
      .toImmutable();

  public ComboDeDocxConverterSpec2Test(@NotNull SpecExample example) {
    super(example, null, OPTIONS);
  }

  @Parameterized.Parameters(name = "{0}")
  public static List<Object[]> data() {
    return getTestData(RESOURCE_LOCATION);
  }
}
