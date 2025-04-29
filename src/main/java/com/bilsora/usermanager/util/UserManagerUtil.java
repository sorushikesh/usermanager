package com.bilsora.usermanager.util;

import java.util.Locale;
import org.springframework.stereotype.Component;

@Component
public class UserManagerUtil {

  public Locale resolveLocale(String localeHeader) {
    if (localeHeader == null || localeHeader.isBlank()) {
      return Locale.ENGLISH;
    }
    return Locale.forLanguageTag(localeHeader);
  }
}
