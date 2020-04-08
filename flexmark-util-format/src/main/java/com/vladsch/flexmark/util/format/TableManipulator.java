package com.vladsch.flexmark.util.format;

import com.vladsch.flexmark.util.ast.Node;

public interface TableManipulator {
  TableManipulator NULL = (table, tableNoe) -> {

  };

  void apply(MarkdownTable table, Node tableNoe);
}
