package com.vladsch.flexmark.test.util.spec;

import org.jetbrains.annotations.NotNull;

import java.util.function.Function;

public interface ResourceUrlResolver extends Function<String, String> {
  String FILE_PROTOCOL = "file://";

  static boolean isFileProtocol(@NotNull String externalForm) {
    return externalForm.startsWith("file:/");
  }

  static boolean hasProtocol(@NotNull String externalForm) {
    int pos = externalForm.indexOf(":");
    // allow windows drive letter to not be treated as protocol
    return pos > 1;
  }

  @NotNull
  static String removeProtocol(@NotNull String externalForm) {
    int pos = externalForm.indexOf(':');
    if (pos > 0) {
      return externalForm.substring(pos + 1);
    }
    return externalForm;
  }

  @Override
  String apply(String externalForm);
}
