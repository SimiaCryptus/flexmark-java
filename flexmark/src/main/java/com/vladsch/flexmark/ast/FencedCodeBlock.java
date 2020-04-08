package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.DoNotDecorate;
import com.vladsch.flexmark.util.misc.CharPredicate;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FencedCodeBlock extends Block implements DoNotDecorate {
  private int fenceIndent;
  private BasedSequence openingMarker = BasedSequence.NULL;
  private BasedSequence info = BasedSequence.NULL;
  private BasedSequence attributes = BasedSequence.NULL;
  private BasedSequence closingMarker = BasedSequence.NULL;

  public FencedCodeBlock() {
  }

  public FencedCodeBlock(BasedSequence chars) {
    super(chars);
  }

  public FencedCodeBlock(BasedSequence chars, BasedSequence openingMarker, BasedSequence info, List<BasedSequence> segments, BasedSequence closingMarker) {
    super(chars, segments);
    this.openingMarker = openingMarker;
    this.info = info;
    this.closingMarker = closingMarker;
  }

  public BasedSequence getAttributes() {
    return attributes;
  }

  public void setAttributes(BasedSequence attributes) {
    this.attributes = attributes;
  }

  public BasedSequence getClosingFence() {
    return this.closingMarker;
  }

  public BasedSequence getClosingMarker() {
    return closingMarker;
  }

  public void setClosingMarker(BasedSequence closingMarker) {
    this.closingMarker = closingMarker;
  }

  public int getFenceIndent() {
    return fenceIndent;
  }

  public void setFenceIndent(int fenceIndent) {
    this.fenceIndent = fenceIndent;
  }

  public int getFenceLength() {
    return getInfo().length();
  }

  /**
   * @return the sequence for the info part of the node
   * @see <a href="http://spec.commonmark.org/0.18/#info-string">CommonMark spec</a>
   */
  public BasedSequence getInfo() {
    return info;
  }

  public void setInfo(BasedSequence info) {
    this.info = info;
  }

  public BasedSequence getOpeningFence() {
    return this.openingMarker;
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
    return new BasedSequence[]{openingMarker, info, attributes, getContentChars(), closingMarker};
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    BasedSequence content = getContentChars();
    int lines = getContentLines().size();
    segmentSpanChars(out, openingMarker, "open");
    segmentSpanChars(out, info, "info");
    segmentSpanChars(out, attributes, "attributes");
    segmentSpan(out, content, "content");
    out.append(" lines[").append(lines).append("]");
    segmentSpanChars(out, closingMarker, "close");
  }

  public BasedSequence getInfoDelimitedByAny(CharPredicate delimiters) {
    BasedSequence language = BasedSequence.NULL;
    if (info.isNotNull() && !info.isBlank()) {
      int space = info.indexOfAny(delimiters);
      if (space == -1) {
        language = info;
      } else {
        language = info.subSequence(0, space);
      }
    }
    return language;
  }
}
