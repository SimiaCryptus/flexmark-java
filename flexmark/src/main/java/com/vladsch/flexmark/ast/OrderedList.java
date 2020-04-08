package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class OrderedList extends ListBlock {
  private int startNumber;
  private char delimiter;

  public OrderedList() {
  }

  public OrderedList(BasedSequence chars) {
    super(chars);
  }

  public OrderedList(BasedSequence chars, List<BasedSequence> segments) {
    super(chars, segments);
  }

  public OrderedList(BlockContent blockContent) {
    super(blockContent);
  }

  public char getDelimiter() {
    return delimiter;
  }

  public void setDelimiter(char delimiter) {
    this.delimiter = delimiter;
  }

  @NotNull
  @Override
  public BasedSequence[] getSegments() {
    return EMPTY_SEGMENTS;
  }

  public int getStartNumber() {
    return startNumber;
  }

  public void setStartNumber(int startNumber) {
    this.startNumber = startNumber;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    super.getAstExtra(out);
    if (startNumber > 1) out.append(" start:").append(startNumber);
    out.append(" delimiter:'").append(delimiter).append("'");
  }
}
