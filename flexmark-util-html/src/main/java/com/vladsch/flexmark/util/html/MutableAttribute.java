package com.vladsch.flexmark.util.html;

import com.vladsch.flexmark.util.misc.Mutable;

public interface MutableAttribute extends Attribute, Mutable<MutableAttribute, Attribute> {
  MutableAttribute setValue(CharSequence value);

  MutableAttribute copy();

  boolean containsValue(CharSequence value);

  MutableAttribute replaceValue(CharSequence value);

  MutableAttribute removeValue(CharSequence value);
}
