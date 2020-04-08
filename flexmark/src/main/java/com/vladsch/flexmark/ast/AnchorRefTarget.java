package com.vladsch.flexmark.ast;

public interface AnchorRefTarget {
  String getAnchorRefId();

  void setAnchorRefId(String anchorRefId);

  String getAnchorRefText();

  boolean isExplicitAnchorRefId();

  void setExplicitAnchorRefId(boolean value);
}
