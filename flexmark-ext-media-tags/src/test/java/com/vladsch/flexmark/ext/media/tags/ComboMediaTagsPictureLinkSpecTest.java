package com.vladsch.flexmark.ext.media.tags;

import com.vladsch.flexmark.core.test.util.RendererSpecTest;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.test.util.spec.ResourceLocation;
import com.vladsch.flexmark.test.util.spec.SpecExample;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.jetbrains.annotations.NotNull;
import org.junit.runners.Parameterized;

import java.util.Collections;
import java.util.List;

public class ComboMediaTagsPictureLinkSpecTest extends RendererSpecTest {
  final private static String SPEC_RESOURCE = "/ext_media_tags_picture_link_spec.md";
  final public static @NotNull ResourceLocation RESOURCE_LOCATION = ResourceLocation.of(SPEC_RESOURCE);
  final private static DataHolder OPTIONS = new MutableDataSet()
      .set(Parser.EXTENSIONS, Collections.singleton(MediaTagsExtension.create()))
      .toImmutable();

  public ComboMediaTagsPictureLinkSpecTest(@NotNull SpecExample example) {
    super(example, null, OPTIONS);
  }

  @Parameterized.Parameters(name = "{0}")
  public static List<Object[]> data() {
    return getTestData(RESOURCE_LOCATION);
  }
}
