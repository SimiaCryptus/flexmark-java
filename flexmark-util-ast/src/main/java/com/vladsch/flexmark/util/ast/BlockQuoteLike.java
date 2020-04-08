package com.vladsch.flexmark.util.ast;

import com.vladsch.flexmark.util.sequence.BasedSequence;

public interface BlockQuoteLike {
  BasedSequence getChars();

  Document getDocument();

  Node getFirstChild();

  BasedSequence getOpeningMarker();
}
