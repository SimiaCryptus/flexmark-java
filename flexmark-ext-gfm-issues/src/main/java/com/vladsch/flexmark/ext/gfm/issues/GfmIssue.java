package com.vladsch.flexmark.ext.gfm.issues;

import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

/**
 * A GfmIssue node
 */
public class GfmIssue extends Node implements DoNotDecorate {
  protected BasedSequence openingMarker = BasedSequence.NULL;
  protected BasedSequence text = BasedSequence.NULL;

  public GfmIssue() {
  }

  public GfmIssue(BasedSequence chars) {
    super(chars);
  }

  public GfmIssue(BasedSequence openingMarker, BasedSequence text) {
    super(spanningChars(openingMarker, text));
    this.openingMarker = openingMarker;
    this.text = text;
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
    return new BasedSequence[]{openingMarker, text};
  }

  public BasedSequence getText() {
    return text;
  }

  public void setText(BasedSequence text) {
    this.text = text;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    delimitedSegmentSpanChars(out, openingMarker, text, BasedSequence.NULL, "text");
  }
}
