package com.vladsch.flexmark.formatter;

import com.vladsch.flexmark.util.ast.Document;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface TranslationHandler extends TranslationContext {
  @NotNull List<String> getTranslatingTexts();

  void setMergeContext(@NotNull MergeContext context);

  void setRenderPurpose(@NotNull RenderPurpose renderPurpose);

  void setTranslatedTexts(@NotNull List<? extends CharSequence> translatedTexts);

  void beginRendering(@NotNull Document node, @NotNull NodeFormatterContext context, @NotNull MarkdownWriter out);
}
