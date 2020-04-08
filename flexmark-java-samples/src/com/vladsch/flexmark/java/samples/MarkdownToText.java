package com.vladsch.flexmark.java.samples;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profile.pegdown.Extensions;
import com.vladsch.flexmark.profile.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.TextCollectingVisitor;
import com.vladsch.flexmark.util.data.DataHolder;
import com.vladsch.flexmark.util.data.MutableDataSet;

public class MarkdownToText {
  static final MutableDataSet FORMAT_OPTIONS = new MutableDataSet();
  final private static DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions(
      Extensions.ALL
  );
  static final Parser PARSER = Parser.builder(OPTIONS).build();

  static {
    // copy extensions from Pegdown compatible to Formatting
    FORMAT_OPTIONS.set(Parser.EXTENSIONS, Parser.EXTENSIONS.get(OPTIONS));
  }

  // use the PARSER to parse and RENDERER to parse pegdown indentation rules and render CommonMark

  public static void main(String[] args) {
    String pegdown = "#Heading\n" +
        "-----\n" +
        "paragraph text \n" +
        "lazy continuation\n" +
        "\n" +
        "* list item\n" +
        "    > block quote\n" +
        "    lazy continuation\n" +
        "\n" +
        "~~~info\n" +
        "        with uneven indent\n" +
        "           with uneven indent\n" +
        "     indented code\n" +
        "~~~ \n" +
        "\n" +
        "        with uneven indent\n" +
        "           with uneven indent\n" +
        "     indented code\n" +
        "1. numbered item 1   \n" +
        "1. numbered item 2   \n" +
        "1. numbered item 3   \n" +
        "    - bullet item 1   \n" +
        "    - bullet item 2   \n" +
        "    - bullet item 3   \n" +
        "        1. numbered sub-item 1   \n" +
        "        1. numbered sub-item 2   \n" +
        "        1. numbered sub-item 3   \n" +
        "    \n" +
        "    ~~~info\n" +
        "            with uneven indent\n" +
        "               with uneven indent\n" +
        "         indented code\n" +
        "    ~~~ \n" +
        "    \n" +
        "            with uneven indent\n" +
        "               with uneven indent\n" +
        "         indented code\n" +
        "";
    System.out.println("pegdown\n");
    System.out.println(pegdown);

    Node document = PARSER.parse(pegdown);
    TextCollectingVisitor textCollectingVisitor = new TextCollectingVisitor();
    String text = textCollectingVisitor.collectAndGetText(document);

    System.out.println("\n\nText\n");
    System.out.println(text);
  }
}
