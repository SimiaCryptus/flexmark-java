package com.vladsch.flexmark.docx.converter.util;

import com.vladsch.flexmark.docx.converter.DocxRendererOptions;
import com.vladsch.flexmark.html.renderer.AttributablePart;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.html.Attributes;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.WordprocessingML.FootnotesPart;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.*;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("UnusedReturnValue")
public interface DocxContext<T> extends DocxContextFrameProvider<T> {

  /**
   * Get current format provider
   *
   * @return format provider
   */
  BlockFormatProvider<T> getBlockFormatProvider();

  void setBlockFormatProvider(BlockFormatProvider<T> formatProvider);

  /**
   * get the atomic integer used to generate bookmark ids
   *
   * @return the id atomic integer
   */
  AtomicInteger getBookmarkIdAtomic();

  /**
   * Get CTShd from current R or create rPr if none and CTShd if none
   *
   * @return CTShd
   */
  CTShd getCTShd();

  /*
   * Get the current part (main document or footnotes)
   */
  Part getContainerPart();

  List<Object> getContent();

  /**
   * @return the main document
   */
  MainDocumentPart getDocxDocument();

  /**
   * Get the wml object factory
   *
   * @return object factory
   */
  ObjectFactory getFactory();

  /**
   * Get or create FootnotesPart for the document
   *
   * @return footnotes part
   * @throws Docx4JException if cannot create part
   */
  FootnotesPart getFootnotesPart() throws Docx4JException;

  DocxHelper getHelper();

  /**
   * Increment the last id and return the id
   *
   * @return next bookmark id
   */
  int getNextBookmarkId();

  /**
   * Get the last P element for the current block
   *
   * @return current P element or null if none is open
   */
  P getP();

  /**
   * @return the Wordprocessing package
   */
  WordprocessingMLPackage getPackage();

  /**
   * Get last R element of current P or create a new one
   *
   * @return R element
   */
  R getR();

  RPr getRPr();

  DocxRendererOptions getRenderingOptions();

  RunFormatProvider<T> getRunFormatProvider();

  void setRunFormatProvider(RunFormatProvider<T> formatProvider);

  void setContentContainer(ContentContainer container);

  void setParaContainer(ParaContainer container);

  void setRunContainer(RunContainer container);

  BlockFormatProvider<T> getBlockFormatProvider(T node);

  RunFormatProvider<T> getRunFormatProvider(T node);

  void addBlankLine(int size, String styleId);

  void addBlankLine(long size, String styleId);

  void addBlankLine(BigInteger size, String styleId);

  void addBlankLines(int count);

  /**
   * Extend the attributes by extensions for the node being currently rendered.
   *
   * @param part       the tag of the node being rendered, some nodes render multiple tags with attributes
   * @param attributes the attributes that were calculated by the renderer or null, these may be modified. To preserve originals pass a copy.
   * @return the extended attributes with added/updated/removed entries
   */
  Attributes extendRenderingNodeAttributes(AttributablePart part, Attributes attributes);

  /**
   * Extend the attributes by extensions for the node being currently rendered.
   *
   * @param node       node for which to get attributes
   * @param part       the tag of the node being rendered, some nodes render multiple tags with attributes
   * @param attributes the attributes that were calculated by the renderer or null, these may be modified. To preserve originals pass a copy.
   * @return the extended attributes with added/updated/removed entries
   */
  Attributes extendRenderingNodeAttributes(Node node, AttributablePart part, Attributes attributes);

  PPrBase.PStyle createPStyle(String style);

  /**
   * Get a new P element for the current block
   *
   * @return current P element or null if none is open
   */
  P createP();

  P createP(String style);

  /**
   * Create R element, with an RPr and add it to current P element
   *
   * @return R element
   */
  R createR();

  /**
   * Insert bookmark start into current P
   *
   * @param bookmarkName    name of the bookmark (optional), if not given the it will be BM_{id}
   * @param isBlockBookmark true if block bookmark
   * @return CTBookmark
   */
  CTBookmark createBookmarkStart(String bookmarkName, boolean isBlockBookmark);

  /**
   * Insert bookmark end into current P for given bookmark
   *
   * @param bookmarkStart   starting bookmark to close
   * @param isBlockBookmark true if block bookmark
   * @return CTMarkupRange
   */
  CTMarkupRange createBookmarkEnd(CTBookmark bookmarkStart, boolean isBlockBookmark);

  /**
   * Get a hyperlink to a bookmark in the document
   *
   * @param bookmarkName name of the bookmark to link to
   * @param linkText     text of the link
   * @return hyperlink
   */
  P.Hyperlink createBookmarkHyperlink(String bookmarkName, String linkText);

  /**
   * Create and add wrapped Text element to R element
   *
   * @return Text element
   * @deprecated use {@link #addText(String)} instead
   */
  @Deprecated
  default Text addWrappedText() {
    return addText("");
  }

  /**
   * Create and add wrapped Text element to R element
   * set value and optionally space preserve if value starts or ends in a space
   *
   * @param value      value to set
   * @param noProofRPr if true will add rPr with noProof tag to R
   * @param createR    if true then create a new R, otherwise add to existing one
   * @return Text element
   */
  Text addText(String value, boolean noProofRPr, boolean createR);

  default Text addTextCreateR(String value, boolean noProofRPr) {
    return addText(value, noProofRPr, true);
  }

  default Text addTextCreateR(String value) {
    return addText(value, false, true);
  }

  default Text addText(String value, boolean noProofRPr) {
    return addText(value, noProofRPr, false);
  }

  default Text addText(String value) {
    return addText(value, false, false);
  }

  /**
   * Create and add wrapped Text element to R element as RInstrText
   * set value and optionally space preserve if value starts or ends in a space
   *
   * @param value      instrText Value
   * @param noProofRPr if true will add rPr with noProof #BooleanDefaultTrue
   * @param createR    if true then create a new R, otherwise add to existing one
   * @return Text element
   */
  Text addInstrText(String value, boolean noProofRPr, boolean createR);

  default Text addInstrTextCreateR(String value, boolean noProofRPr) {
    return addInstrText(value, noProofRPr, true);
  }

  default Text addInstrTextCreateR(String value) {
    return addInstrText(value, false, true);
  }

  default Text addInstrText(String value, boolean noProofRPr) {
    return addInstrText(value, noProofRPr, false);
  }

  default Text addInstrText(String value) {
    return addInstrText(value, false);
  }

  /**
   * Create and add wrapped Text element to R element as RInstrText
   *
   * @param charType char type to set
   * @param createR  if true then create a new R, otherwise add to existing one
   * @return FldChar element
   */
  FldChar addFldChar(STFldCharType charType, boolean createR);

  default FldChar addFldCharCreateR(STFldCharType charType) {
    return addFldChar(charType, true);
  }

  default FldChar addFldChar(STFldCharType charType) {
    return addFldChar(charType, true);
  }

  void addPageBreak();

  void addBreak(STBrType breakType);

  RPr addBold();

  BooleanDefaultTrue getBooleanDefaultTrue(boolean value);

  Color createColor();

  HpsMeasure createHpsMeasure(long val);

  /**
   * Add text to current P, create R and add wrapped text
   *
   * @param text text to add
   * @return text element
   * @deprecated use {@link #addTextCreateR(String)}
   */
  @Deprecated
  default Text text(String text) {
    return addTextCreateR(text);
  }

  /**
   * Get a paragraph style of given name
   *
   * @param styleName name of the style
   * @return style
   */
  Style getStyle(String styleName);

  /**
   * Get an external hyperlink relationship for the given url
   *
   * @param url url
   * @return relationship
   */
  Relationship getHyperlinkRelationship(String url);

  /**
   * Add a line break to current R
   */
  void addLineBreak();

  /**
   * Run within a context, after run the format providers and containers will be restored.
   * <p>
   * Use when you need to create a container or format provider but only for a part of the
   * node rendering process.
   *
   * @param runnable code to run
   */
  void contextFramed(Runnable runnable);

  void renderFencedCodeLines(CharSequence... lines);

  void renderFencedCodeLines(List<? extends CharSequence> lines);

  /**
   * Add footnote and return it
   *
   * @param footnoteID re-use footnote id or 0 if new footnote
   * @return footnote element
   * @throws Docx4JException thrown if cannot get or create footnotes part of the document
   */
  CTFtnEdn addFootnote(BigInteger footnoteID) throws Docx4JException;

  /**
   * Get id for a node
   *
   * @param node node
   * @return id string or null if none
   */
  String getNodeId(Node node);

  /**
   * Get a valid bookmark for given id
   *
   * @param id node id
   * @return mapped to valid bookmark name
   */
  String getValidBookmarkName(String id);

  /**
   * Get Node from id
   *
   * @param nodeId id string
   * @return node or null if no node with given id attribute is defined
   */
  Node getNodeFromId(String nodeId);
}
