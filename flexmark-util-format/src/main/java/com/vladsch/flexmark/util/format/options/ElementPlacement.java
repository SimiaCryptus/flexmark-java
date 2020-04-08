package com.vladsch.flexmark.util.format.options;

public enum ElementPlacement {
  AS_IS,
  DOCUMENT_TOP,
  GROUP_WITH_FIRST,
  GROUP_WITH_LAST,
  DOCUMENT_BOTTOM,
  ;

  public boolean isBottom() {
    return this == DOCUMENT_BOTTOM;
  }

  public boolean isChange() {
    return this != AS_IS;
  }

  public boolean isGroupFirst() {
    return this == GROUP_WITH_FIRST;
  }

  public boolean isGroupLast() {
    return this == GROUP_WITH_LAST;
  }

  public boolean isNoChange() {
    return this == AS_IS;
  }

  public boolean isTop() {
    return this == DOCUMENT_TOP;
  }
}
