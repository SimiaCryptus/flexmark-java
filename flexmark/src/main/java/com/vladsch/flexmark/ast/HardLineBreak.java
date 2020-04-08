package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.DoNotTrim;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextContainer;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import org.jetbrains.annotations.NotNull;

public class HardLineBreak extends Node implements DoNotTrim, TextContainer {
  public HardLineBreak() {
  }

  public HardLineBreak(BasedSequence chars) {
    super(chars);
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    return EMPTY_SEGMENTS;
  }

  @Override
  public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> out, int flags) {
    BasedSequence chars = getChars();
    out.add(chars.subSequence(chars.length() - 1, chars.length()));
    return false;
  }
}
