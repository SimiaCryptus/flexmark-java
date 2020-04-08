package com.vladsch.flexmark.ast;

import com.vladsch.flexmark.util.ast.BlankLineContainer;
import com.vladsch.flexmark.util.ast.Block;
import com.vladsch.flexmark.util.ast.BlockContent;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class ListBlock extends Block implements BlankLineContainer {

  private boolean tight;

  public ListBlock() {
  }

  public ListBlock(BasedSequence chars) {
    super(chars);
  }

  public ListBlock(BasedSequence chars, List<BasedSequence> segments) {
    super(chars, segments);
  }

  public ListBlock(BlockContent blockContent) {
    super(blockContent);
  }

  @Override
  public Node getLastBlankLineChild() {
    return getLastChild();
  }

  public boolean isLoose() {
    return !tight;
  }

  public void setLoose(boolean loose) {
    this.tight = !loose;
  }

  public boolean isTight() {
    return tight;
  }

  public void setTight(boolean tight) {
    this.tight = tight;
  }

  @Override
  public void getAstExtra(@NotNull StringBuilder out) {
    super.getAstExtra(out);
    if (isTight()) out.append(" isTight");
    else out.append(" isLoose");
  }
}
