package com.vladsch.flexmark.docx.converter;

import com.vladsch.flexmark.test.util.spec.ResourceLocation;
import com.vladsch.flexmark.test.util.spec.SpecExample;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.List;

public class ComboDocxConverterAttributeSpecTest extends ComboDocxConverterSpecTestBase {
  final private static String SPEC_RESOURCE = "/docx_converter_attribute_ast_spec.md";
  final public static @NotNull ResourceLocation RESOURCE_LOCATION = ResourceLocation.of(SPEC_RESOURCE);

  public ComboDocxConverterAttributeSpecTest(@NotNull SpecExample example) {
    super(example, null);
  }

  @Parameterized.Parameters(name = "{0}")
  public static List<Object[]> data() {
    return getTestData(RESOURCE_LOCATION);
  }
}
