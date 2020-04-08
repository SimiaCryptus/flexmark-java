package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.BasedSequenceImpl;
import org.jetbrains.annotations.NotNull;

public class DelimitedLinkNode extends LinkNode {

  protected BasedSequence openingMarker = BasedSequence.NULL;
  protected BasedSequence text = BasedSequence.NULL;
  protected BasedSequence closingMarker = BasedSequence.NULL;
  public DelimitedLinkNode() {
  }
  public DelimitedLinkNode(BasedSequence chars) {
    super(chars);
  }

  public DelimitedLinkNode(BasedSequence openingMarker, BasedSequence text, BasedSequence closingMarker) {
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

  public BasedSequence getLeadSegment() {
    return BasedSequenceImpl.firstNonNull(openingMarker, text);
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
