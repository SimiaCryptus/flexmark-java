package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.ast.util.ReferenceRepository;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.*;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.Escaping;
import com.vladsch.flexmark.util.sequence.ReplacedTextMapper;
import com.vladsch.flexmark.util.sequence.builder.ISequenceBuilder;
import org.jetbrains.annotations.NotNull;

public abstract class RefNode extends Node implements LinkRefDerived, ReferencingNode<ReferenceRepository, Reference>, DoNotLinkDecorate, TextContainer {
  protected BasedSequence textOpeningMarker = BasedSequence.NULL;
  protected BasedSequence text = BasedSequence.NULL;
  protected BasedSequence textClosingMarker = BasedSequence.NULL;
  protected BasedSequence referenceOpeningMarker = BasedSequence.NULL;
  protected BasedSequence reference = BasedSequence.NULL;
  protected BasedSequence referenceClosingMarker = BasedSequence.NULL;
  protected boolean isDefined = false;

  public RefNode() {
  }

  public RefNode(BasedSequence chars) {
    super(chars);
  }

  public RefNode(BasedSequence textOpeningMarker, BasedSequence text, BasedSequence textClosingMarker, BasedSequence referenceOpeningMarker, BasedSequence reference, BasedSequence referenceClosingMarker) {
    super(textOpeningMarker.baseSubSequence(textOpeningMarker.getStartOffset(), referenceClosingMarker.getEndOffset()));
    this.textOpeningMarker = textOpeningMarker;
    this.text = text;
    this.textClosingMarker = textClosingMarker;
    this.referenceOpeningMarker = referenceOpeningMarker;
    this.reference = reference;
    this.referenceClosingMarker = referenceClosingMarker;
  }

  public RefNode(BasedSequence chars, BasedSequence textOpeningMarker, BasedSequence text, BasedSequence textClosingMarker, BasedSequence referenceOpeningMarker, BasedSequence reference, BasedSequence referenceClosingMarker) {
    super(chars);
    this.textOpeningMarker = textOpeningMarker;
    this.text = text;
    this.textClosingMarker = textClosingMarker;
    this.referenceOpeningMarker = referenceOpeningMarker;
    this.reference = reference;
    this.referenceClosingMarker = referenceClosingMarker;
  }

  public RefNode(BasedSequence textOpeningMarker, BasedSequence text, BasedSequence textClosingMarker) {
    super(textOpeningMarker.baseSubSequence(textOpeningMarker.getStartOffset(), textClosingMarker.getEndOffset()));
    this.textOpeningMarker = textOpeningMarker;
    this.text = text;
    this.textClosingMarker = textClosingMarker;
  }

  public RefNode(BasedSequence chars, BasedSequence textOpeningMarker, BasedSequence text, BasedSequence textClosingMarker) {
    super(chars);
    this.textOpeningMarker = textOpeningMarker;
    this.text = text;
    this.textClosingMarker = textClosingMarker;
  }

  public RefNode(BasedSequence textOpeningMarker, BasedSequence text, BasedSequence textClosingMarker, BasedSequence referenceOpeningMarker, BasedSequence referenceClosingMarker) {
    super(textOpeningMarker.baseSubSequence(textOpeningMarker.getStartOffset(), referenceClosingMarker.getEndOffset()));
    this.textOpeningMarker = textOpeningMarker;
    this.text = text;
    this.textClosingMarker = textClosingMarker;
    this.referenceOpeningMarker = referenceOpeningMarker;
    this.referenceClosingMarker = referenceClosingMarker;
  }

  public BasedSequence getDummyReference() {
    if (isDummyReference()) {
      return getChars().baseSubSequence(textOpeningMarker.getStartOffset(), textClosingMarker.getEndOffset());
    }
    return BasedSequence.NULL;
  }

  @NotNull
  @Override
  public BasedSequence getReference() {
    return reference;
  }

  public void setReference(BasedSequence reference) {
    this.reference = reference;
  }

  public BasedSequence getReferenceClosingMarker() {
    return referenceClosingMarker;
  }

  public void setReferenceClosingMarker(BasedSequence referenceClosingMarker) {
    this.referenceClosingMarker = referenceClosingMarker;
  }

  public BasedSequence getReferenceOpeningMarker() {
    return referenceOpeningMarker;
  }

  public void setReferenceOpeningMarker(BasedSequence referenceOpeningMarker) {
    this.referenceOpeningMarker = referenceOpeningMarker;
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    if (isReferenceTextCombined()) {
      return new BasedSequence[]{
          referenceOpeningMarker,
          reference,
          referenceClosingMarker,
          textOpeningMarker,
          text,
          textClosingMarker,
      };
    } else {
      return new BasedSequence[]{
          textOpeningMarker,
          text,
          textClosingMarker,
          referenceOpeningMarker,
          reference,
          referenceClosingMarker,
      };
    }
  }

  public BasedSequence getText() {
    return text;
  }

  public void setText(BasedSequence text) {
    this.text = text;
  }

  public BasedSequence getTextClosingMarker() {
    return textClosingMarker;
  }

  public void setTextClosingMarker(BasedSequence textClosingMarker) {
    this.textClosingMarker = textClosingMarker;
  }

  public BasedSequence getTextOpeningMarker() {
    return textOpeningMarker;
  }

  public void setTextOpeningMarker(BasedSequence textOpeningMarker) {
    this.textOpeningMarker = textOpeningMarker;
  }

  @Override
  public boolean isDefined() {
    return isDefined;
  }

  public void setDefined(boolean defined) {
    isDefined = defined;
  }

  public boolean isDummyReference() {
    return textOpeningMarker != BasedSequence.NULL && text == BasedSequence.NULL && textClosingMarker != BasedSequence.NULL;
  }

  public boolean isReferenceTextCombined() {
    return text == BasedSequence.NULL;
  }

  @Override
  public boolean isTentative() {
    return !isDefined;
  }

  public void setReferenceChars(BasedSequence referenceChars) {
    int referenceCharsLength = referenceChars.length();
    int openingOffset = referenceChars.charAt(0) == '!' ? 2 : 1;
    this.referenceOpeningMarker = referenceChars.subSequence(0, openingOffset);
    this.reference = referenceChars.subSequence(openingOffset, referenceCharsLength - 1).trim();
    this.referenceClosingMarker = referenceChars.subSequence(referenceCharsLength - 1, referenceCharsLength);
  }

  public void setTextChars(BasedSequence textChars) {
    int textCharsLength = textChars.length();
    this.textOpeningMarker = textChars.subSequence(0, 1);
    this.text = textChars.subSequence(1, textCharsLength - 1).trim();
    this.textClosingMarker = textChars.subSequence(textCharsLength - 1, textCharsLength);
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    if (isReferenceTextCombined()) {
      delimitedSegmentSpanChars(out, referenceOpeningMarker, reference, referenceClosingMarker, "reference");
      delimitedSegmentSpanChars(out, textOpeningMarker, text, textClosingMarker, "text");
    } else {
      delimitedSegmentSpanChars(out, textOpeningMarker, text, textClosingMarker, "text");
      delimitedSegmentSpanChars(out, referenceOpeningMarker, reference, referenceClosingMarker, "reference");
    }
  }

  @Override
  public Reference getReferenceNode(Document document) {
    return getReferenceNode(Parser.REFERENCES.get(document));
  }

  @Override
  public Reference getReferenceNode(ReferenceRepository repository) {
    if (repository == null) return null;
    String normalizeRef = repository.normalizeKey(reference);
    return repository.get(normalizeRef);
  }

  @Override
  public boolean collectText(ISequenceBuilder<? extends ISequenceBuilder<?, BasedSequence>, BasedSequence> out, int flags) {
    int urlType = flags & F_LINK_TEXT_TYPE;

    if (urlType == F_LINK_TEXT) {
      return true;
    } else {
      Reference reference = getReferenceNode(getDocument());
      if (urlType == F_LINK_NODE_TEXT) {
        out.append(getChars());
      } else {
        if (reference == null) {
          return true;
        } else {
          BasedSequence url;

          switch (urlType) {
            case F_LINK_PAGE_REF:
              url = reference.getPageRef();
              break;

            case F_LINK_ANCHOR:
              url = reference.getAnchorRef();
              break;

            case F_LINK_URL:
              url = reference.getUrl();
              break;

            default:
              return true;
          }

          ReplacedTextMapper textMapper = new ReplacedTextMapper(url);
          BasedSequence unescaped = Escaping.unescape(url, textMapper);
          BasedSequence percentDecoded = Escaping.percentDecodeUrl(unescaped, textMapper);
          out.append(percentDecoded);
        }
      }

      return false;
    }
  }

  @NotNull
  @Override
  protected String toStringAttributes() {
    return "text=" + text + ", reference=" + reference;
  }
}
