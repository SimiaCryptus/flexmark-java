package com.vladsch.flexmark.ext.spec.example;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

/**
 * A SpecExample block node
 */
public class SpecExampleSource extends Node {
  public SpecExampleSource() {
  }

  public SpecExampleSource(BasedSequence chars) {
    super(chars);
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    return EMPTY_SEGMENTS;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    astExtraChars(out);
  }
}
