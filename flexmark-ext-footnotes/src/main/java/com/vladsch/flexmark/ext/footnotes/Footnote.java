package com.vladsch.flexmark.ext.footnotes;

import com.vladsch.flexmark.ext.footnotes.internal.FootnoteRepository;
import com.vladsch.flexmark.util.ast.*;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

/**
 * A Footnote referencing node
 */
public class Footnote extends Node implements DelimitedNode, DoNotDecorate, ReferencingNode<FootnoteRepository, FootnoteBlock> {
  protected BasedSequence openingMarker = BasedSequence.NULL;
  protected BasedSequence text = BasedSequence.NULL;
  protected BasedSequence closingMarker = BasedSequence.NULL;
  protected FootnoteBlock footnoteBlock;
  protected int referenceOrdinal;

  public Footnote() {
  }

  public Footnote(BasedSequence chars) {
    super(chars);
  }

  public Footnote(BasedSequence openingMarker, BasedSequence text, BasedSequence closingMarker) {
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

  public FootnoteBlock getFootnoteBlock() {
    return footnoteBlock;
  }

  public void setFootnoteBlock(FootnoteBlock footnoteBlock) {
    this.footnoteBlock = footnoteBlock;
  }

  public BasedSequence getOpeningMarker() {
    return openingMarker;
  }

  public void setOpeningMarker(BasedSequence openingMarker) {
    this.openingMarker = openingMarker;
  }

  @NotNull
  @Override
  public BasedSequence getReference() {
    return text;
  }

  public int getReferenceOrdinal() {
    return referenceOrdinal;
  }

  public void setReferenceOrdinal(int referenceOrdinal) {
    this.referenceOrdinal = referenceOrdinal;
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

  public boolean isDefined() {
    return footnoteBlock != null;
  }

  @Override
  public FootnoteBlock getReferenceNode(Document document) {
    if (footnoteBlock != null || text.isEmpty()) return footnoteBlock;
    footnoteBlock = getFootnoteBlock(FootnoteExtension.FOOTNOTES.get(document));
    return footnoteBlock;
  }

  @Override
  public FootnoteBlock getReferenceNode(FootnoteRepository repository) {
    if (footnoteBlock != null || text.isEmpty()) return footnoteBlock;
    footnoteBlock = getFootnoteBlock(repository);
    return footnoteBlock;
  }

  public FootnoteBlock getFootnoteBlock(FootnoteRepository footnoteRepository) {
    return text.isEmpty() ? null : footnoteRepository.get(text.toString());
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    out.append(" ordinal: ").append(footnoteBlock != null ? footnoteBlock.getFootnoteOrdinal() : 0).append(" ");
    delimitedSegmentSpanChars(out, openingMarker, text, closingMarker, "text");
  }
}
