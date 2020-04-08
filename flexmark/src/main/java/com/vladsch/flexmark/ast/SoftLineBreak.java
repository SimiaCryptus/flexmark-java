package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.DoNotAttributeDecorate;
import com.vladsch.flexmark.util.ast.DoNotTrim;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import org.jetbrains.annotations.NotNull;

public class SoftLineBreak extends Node implements DoNotAttributeDecorate, DoNotTrim, TextContainer {
  public SoftLineBreak() {
  }

  public SoftLineBreak(BasedSequence chars) {
    super(chars);
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    return EMPTY_SEGMENTS;
  }

  @Override
  public void setChars(@NotNull BasedSequence chars) {
    super.setChars(chars);
    assert getChars().isNotEmpty();
  }

  @Override
  public void setCharsFromContentOnly() {
    super.setCharsFromContentOnly();
    assert getChars().isNotEmpty();
  }

  @Override
  public void setCharsFromContent() {
    super.setCharsFromContent();
    assert getChars().isNotEmpty();
  }

  @Override
  public void setCharsFromSegments() {
    super.setCharsFromSegments();
    assert getChars().isNotEmpty();
  }

  @Override
  public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> out, int flags) {
    out.add(getChars());
    return false;
  }
}
