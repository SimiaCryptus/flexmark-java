package com.vladsch.flexmark.ext.gitlab;

import com.vladsch.flexmark.util.ast.DelimitedNode;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

/**
 * A Ins node
 */
public class GitLabInlineMath extends Node implements DelimitedNode {
  protected BasedSequence openingMarker = BasedSequence.NULL;
  protected BasedSequence text = BasedSequence.NULL;
  protected BasedSequence closingMarker = BasedSequence.NULL;

  public GitLabInlineMath() {
  }

  public GitLabInlineMath(BasedSequence chars) {
    super(chars);
  }

  public GitLabInlineMath(BasedSequence openingMarker, BasedSequence text, BasedSequence closingMarker) {
    super(openingMarker.baseSubSequence(openingMarker.getStartOffset(), closingMarker.getEndOffset()));
    this.openingMarker = openingMarker;
    this.text = text;
    this.closingMarker = closingMarker;
  }

  public BasedSequence getClosingMarker() {
    return closingMarker;
  }

  public void setClosingMarker(BasedSequence closingMarker) {
    this.closingMarker = closingMarker;
  }

  public BasedSequence getOpeningMarker() {
    return openingMarker;
  }

  public void setOpeningMarker(BasedSequence openingMarker) {
    this.openingMarker = openingMarker;
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    //return EMPTY_SEGMENTS;
    return new BasedSequence[]{openingMarker, text, closingMarker};
  }

  public BasedSequence getText() {
    return text;
  }

  public void setText(BasedSequence text) {
    this.text = text;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    delimitedSegmentSpanChars(out, openingMarker, text, closingMarker, "text");
  }
}
