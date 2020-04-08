package com.vladsch.flexmark.java.samples;

import com.vladsch.flexmark.ext.toc.TocBlock;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.toc.internal.TocNodeRenderer;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.html.HtmlRenderer.Builder;
import com.vladsch.flexmark.html.HtmlRenderer.HtmlRendererExtension;
import com.vladsch.flexmark.html.renderer.DelegatingNodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.LineAppendable;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TocSubContextSample {
  final private static DataHolder OPTIONS = new MutableDataSet().set(Parser.EXTENSIONS, Arrays.asList(
      TocExtension.create(),
      CustomExtension.create()
  ));

  static final Parser PARSER = Parser.builder(OPTIONS).build();
  static final HtmlRenderer RENDERER = HtmlRenderer.builder(OPTIONS).indentSize(2).build();

  // use the PARSER to parse and RENDERER to render with pegdown compatibility
  public static void main(String[] args) {
    // You can re-use parser and renderer instances
    Node document = PARSER.parse("" +
        "[TOC] \n" +
        "\n" +
        "# Heading **some bold** 1\n" +
        "## Heading 1.1 _some italic_\n" +
        "### Heading 1.1.1\n" +
        "### Heading 1.1.2  **_some bold italic_**\n" +
        "");
    String html = RENDERER.render(document);
    System.out.println(html);
  }

  static class CustomNodeRenderer implements NodeRenderer {
    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
      HashSet<NodeRenderingHandler<?>> set = new HashSet<>();
      set.add(new NodeRenderingHandler<>(TocBlock.class, (node, context, html) -> {
        // test the node to see if it needs overriding
        NodeRendererContext subContext = context.getDelegatedSubContext(true);
        subContext.delegateRender();
        String tocText = ((LineAppendable) subContext.getHtmlWriter()).toString(0, 0);

        // output to separate stream
        System.out.println("---- TOC HTML --------------------");
        System.out.print(tocText);
        System.out.println("----------------------------------\n");

        html.tagLineIndent("div", () -> html.append(subContext.getHtmlWriter()));
      }));

      return set;
    }

    public static class Factory implements DelegatingNodeRendererFactory {
      @Override
      public Set<Class<?>> getDelegates() {
        Set<Class<?>> set = new HashSet<>();
        // add node renderer factory classes to which this renderer will delegate some of its rendering
        // core node renderer is assumed to have all depend it so there is no need to add it
        set.add(TocNodeRenderer.Factory.class);
        return set;

        // return null if renderer does not delegate or delegates only to core node renderer
        //return null;
      }

      @NotNull
      @Override
      public NodeRenderer apply(@NotNull DataHolder options) {
        return new CustomNodeRenderer();
      }
    }
  }

  static class CustomExtension implements HtmlRendererExtension {
    static CustomExtension create() {
      return new CustomExtension();
    }

    @Override
    public void rendererOptions(@NotNull MutableDataHolder options) {

    }

    @Override
    public void extend(@NotNull Builder htmlRendererBuilder, @NotNull String rendererType) {
      htmlRendererBuilder.nodeRendererFactory(new CustomNodeRenderer.Factory());
    }
  }
}
