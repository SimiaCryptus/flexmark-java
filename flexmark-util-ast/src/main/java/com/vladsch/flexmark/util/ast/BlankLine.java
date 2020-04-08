package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BlankLine extends Block {
  private Block claimedBlankLine = null;

  public BlankLine(@NotNull BasedSequence chars) {
    super(chars);
    setCharsFromContent();
  }

  public BlankLine(@NotNull BasedSequence chars, @NotNull Block claimedBlankLine) {
    super(chars);
    setCharsFromContent();
    this.claimedBlankLine = claimedBlankLine;
  }

  @Nullable
  public Block getClaimedBlankLine() {
    return claimedBlankLine;
  }

  @SuppressWarnings("UnusedReturnValue")
  public BlankLine setClaimedBlankLine(@NotNull Block claimedBlankLine) {
    this.claimedBlankLine = claimedBlankLine;
    return this;
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    return EMPTY_SEGMENTS;
  }

  public boolean isClaimed() {
    return claimedBlankLine != null;
  }
}
