package com.vladsch.flexmark.util.collection.iteration;

import org.jetbrains.annotations.NotNull;

public interface ReversibleIterable<E> extends Iterable<E> {
  boolean isReversed();

  @Override
  @NotNull ReversibleIterator<E> iterator();

  @NotNull ReversibleIterable<E> reversed();

  @NotNull ReversibleIterator<E> reversedIterator();
}
