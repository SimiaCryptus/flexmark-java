package com.vladsch.flexmark.docx.converter;

import com.vladsch.flexmark.docx.converter.util.DocxContext;
import com.vladsch.flexmark.html.renderer.LinkResolverContext;
import com.vladsch.flexmark.util.ast.Node;

import java.util.Collection;

/**
 * The context for node rendering, including configuration and functionality for the node renderer to use.
 */
public interface DocxRendererContext extends DocxContext<Node>, LinkResolverContext {
  /**
   * @return the {@link DocxRendererOptions} for the context.
   */
  DocxRendererOptions getDocxRendererOptions();

  /**
   * @return current rendering phase
   */
  DocxRendererPhase getPhase();

  /**
   * Get iterable of nodes of given types, in order of their appearance in the document tree, depth first traversal.
   * Only node classes returned by {@link NodeDocxRenderer#getNodeClasses()} of all loaded extensions
   * will be available to formatters.
   * <p>
   *
   * @param classes node classes to return
   * @return iterable
   */
  Iterable<? extends Node> nodesOfType(Class<?>[] classes);

  Iterable<? extends Node> nodesOfType(Collection<Class<?>> classes);

  /**
   * Get iterable of nodes of given types, in reverse order of their appearance in the document tree, depth first traversal.
   * Only node classes returned by {@link NodeDocxRenderer#getNodeClasses()} of all loaded extensions
   * will be available to formatters.
   *
   * @param classes node classes to return
   * @return iterable
   */
  Iterable<? extends Node> reversedNodesOfType(Class<?>[] classes);

  Iterable<? extends Node> reversedNodesOfType(Collection<Class<?>> classes);
}
