package com.vladsch.flexmark.ext.toc;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

/**
 * A sim toc contents node containing all text that came after the sim toc node
 */
public class SimTocOption extends Node implements DoNotDecorate {
  public SimTocOption() {
  }

  public SimTocOption(BasedSequence chars) {
    super(chars);
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    //return EMPTY_SEGMENTS;
    return EMPTY_SEGMENTS;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    astExtraChars(out);
  }
}
