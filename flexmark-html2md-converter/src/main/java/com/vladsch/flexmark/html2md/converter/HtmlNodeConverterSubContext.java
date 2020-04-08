package com.vladsch.flexmark.html2md.converter;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Node;

import java.io.IOException;

public abstract class HtmlNodeConverterSubContext implements HtmlNodeConverterContext {
  final protected HtmlMarkdownWriter markdown;
  NodeRenderingHandlerWrapper<?> renderingHandlerWrapper;
  Node myRenderingNode;

  public HtmlNodeConverterSubContext(HtmlMarkdownWriter markdown) {
    this.markdown = markdown;
    this.myRenderingNode = null;
    this.markdown.setContext(this);
  }

  public HtmlMarkdownWriter getMarkdown() {
    return markdown;
  }

  public Node getRenderingNode() {
    return myRenderingNode;
  }

  public void setRenderingNode(Node renderingNode) {
    this.myRenderingNode = renderingNode;
  }

  public void flushTo(@NotNull Appendable out, int maxTrailingBlankLines) {
    flushTo(out, getHtmlConverterOptions().maxBlankLines, maxTrailingBlankLines);
  }

  public void flushTo(Appendable out, int maxBlankLines, int maxTrailingBlankLines) {
    markdown.line();
    try {
      markdown.appendTo(out, maxBlankLines, maxTrailingBlankLines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
