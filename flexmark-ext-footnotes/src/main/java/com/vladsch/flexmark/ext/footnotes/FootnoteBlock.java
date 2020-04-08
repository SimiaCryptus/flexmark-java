package com.vladsch.flexmark.ext.footnotes;

import com.vladsch.flexmark.ast.Paragraph;
import com.vladsch.flexmark.ast.ParagraphItemContainer;
import com.vladsch.flexmark.ext.footnotes.internal.FootnoteRepository;
import com.vladsch.flexmark.parser.ListOptions;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.ReferenceNode;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.SequenceUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A Footnote definition node containing text and other inline nodes nodes as children.
 */
public class FootnoteBlock extends Block implements ReferenceNode<FootnoteRepository, FootnoteBlock, Footnote>, ParagraphItemContainer {
  protected BasedSequence openingMarker = BasedSequence.NULL;
  protected BasedSequence text = BasedSequence.NULL;
  protected BasedSequence closingMarker = BasedSequence.NULL;
  protected BasedSequence footnote = BasedSequence.NULL;
  private int footnoteOrdinal = 0;
  private int firstReferenceOffset = Integer.MAX_VALUE;
  private int footnoteReferences = 0;

  public FootnoteBlock() {
  }

  public FootnoteBlock(BasedSequence chars) {
    super(chars);
  }

  public BasedSequence getClosingMarker() {
    return closingMarker;
  }

  public void setClosingMarker(BasedSequence closingMarker) {
    this.closingMarker = closingMarker;
  }

  public int getFirstReferenceOffset() {
    return firstReferenceOffset;
  }

  public void setFirstReferenceOffset(int firstReferenceOffset) {
    this.firstReferenceOffset = firstReferenceOffset;
  }

  public BasedSequence getFootnote() {
    return footnote;
  }

  public void setFootnote(BasedSequence footnote) {
    this.footnote = footnote;
  }

  public int getFootnoteOrdinal() {
    return footnoteOrdinal;
  }

  public void setFootnoteOrdinal(int footnoteOrdinal) {
    this.footnoteOrdinal = footnoteOrdinal;
  }

  public int getFootnoteReferences() {
    return footnoteReferences;
  }

  public void setFootnoteReferences(int footnoteReferences) {
    this.footnoteReferences = footnoteReferences;
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
    return new BasedSequence[]{openingMarker, text, closingMarker, footnote};
  }

  public BasedSequence getText() {
    return text;
  }

  public void setText(BasedSequence text) {
    this.text = text;
  }

  public boolean isReferenced() {
    return this.firstReferenceOffset < Integer.MAX_VALUE;
  }

  @Override
  public int compareTo(FootnoteBlock other) {
    return SequenceUtils.compare(text, other.text, true);
  }

  @Nullable
  @Override
  public Footnote getReferencingNode(@NotNull Node node) {
    return node instanceof Footnote ? (Footnote) node : null;
  }

  public void addFirstReferenceOffset(int firstReferenceOffset) {
    if (this.firstReferenceOffset < firstReferenceOffset) this.firstReferenceOffset = firstReferenceOffset;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    out.append(" ordinal: ").append(footnoteOrdinal).append(" ");
    segmentSpan(out, openingMarker, "open");
    segmentSpan(out, text, "text");
    segmentSpan(out, closingMarker, "close");
    segmentSpan(out, footnote, "footnote");
  }

  @Override
  public boolean isItemParagraph(Paragraph node) {
    return node == getFirstChild();
  }

  @Override
  public boolean isParagraphWrappingDisabled(Paragraph node, ListOptions listOptions, DataHolder options) {
    return false;
  }

  @Override
  public boolean isParagraphInTightListItem(Paragraph node) {
    return false;
  }
}
