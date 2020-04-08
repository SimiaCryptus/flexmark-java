package com.vladsch.flexmark.util.sequence.builder;

import com.vladsch.flexmark.util.misc.BitFieldSet;
import com.vladsch.flexmark.util.sequence.Range;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public interface ISegmentBuilder<S extends ISegmentBuilder<S>> extends Iterable<Object> {
  Options O_INCLUDE_ANCHORS = Options.INCLUDE_ANCHORS;
  Options O_TRACK_FIRST256 = Options.TRACK_FIRST256;
  int F_INCLUDE_ANCHORS = BitFieldSet.intMask(O_INCLUDE_ANCHORS);
  int F_TRACK_FIRST256 = BitFieldSet.intMask(O_TRACK_FIRST256);
  int F_DEFAULT = F_INCLUDE_ANCHORS | F_TRACK_FIRST256;

  @Nullable Range getBaseSubSequenceRange();

  int getEndOffset();

  int getOptions();

  /**
   * Return iterator over segments
   *
   * @return iterator over segment builder segments
   */
  @NotNull
  Iterable<Seg> getSegments();

  int getSpan();

  int getStartOffset();

  CharSequence getText();

  int getTextFirst256Length();

  int getTextFirst256Segments();

  int getTextLength();

  int getTextSegments();

  int getTextSpaceLength();

  int getTextSpaceSegments();

  boolean isBaseSubSequenceRange();

  boolean isEmpty();

  boolean isIncludeAnchors();

  boolean isTrackTextFirst256();

  boolean haveOffsets();

  int size();

  int noAnchorsSize();

  int length();

  /**
   * Return iterator over segment parts
   * Range - BASE
   * CharSequence - TEXT
   *
   * @return iterator over segment builder parts
   */
  @NotNull
  @Override
  Iterator<Object> iterator();

  @NotNull S append(int startOffset, int endOffset);

  @NotNull S append(CharSequence text);

  @NotNull S appendAnchor(int offset);

  @NotNull S append(@NotNull Range range);

  @NotNull String toStringWithRangesVisibleWhitespace(@NotNull CharSequence chars);

  @NotNull String toStringWithRanges(@NotNull CharSequence chars);

  @NotNull String toString(@NotNull CharSequence chars);

  enum Options {
    INCLUDE_ANCHORS,
    TRACK_FIRST256,
  }
}
