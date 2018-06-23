/**
 * @Copyrights G. Vaidhyanathan
 */package org.i18n.swing.util;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
/**
 * @author G. Vaidhyanathan
 * @version 1.0
 */
public class Messages {

  // The bundle name.
  private static final String BUNDLE_NAME = "org.i18n.swing.util.messages"; //$NON-NLS-1$
  // The resource bundle.
  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

  // Constructs the messages handler.
  private Messages() {
    // Do nothing.
  }

  /**
   * Gets the string from the specified key.
   * @param key_p
   *          The key.
   * @return The string result.
   */
  public static String getString(String key_p) {

    try {
      return RESOURCE_BUNDLE.getString(key_p);
    } catch (MissingResourceException e) {
      return '!' + key_p + '!';
    }
  }

}
