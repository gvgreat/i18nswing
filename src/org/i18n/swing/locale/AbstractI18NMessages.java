/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.UIManager;

/**
 * Abstract I18NMessages with I18N feature.
 * <P>
 * This implements LocaleListener, and when the locale changes, changes the locale accordingly to the new one
 * selected.
 * <P>
 * Subclasses of this may have messages_*.properties in the same location of the <code>Messages</code> class
 * for various locales as follows...
 * 
 * <pre>
 * public class Messages extends AbstractI18NMessages {
 * 	// The bundle name.
 * 	private static final String BUNDLE_NAME = &quot;org.ucp.messages&quot;; //$NON-NLS-1$
 * 
 * 	private static final Messages instance = new Messages();
 * 
 * 	public static final Messages getInstance() {
 * 		return instance;
 * 	}
 * 
 * 	// Constructs the messages handler.
 * 	protected Messages() {
 * 		super(BUNDLE_NAME);
 * 	}
 * }
 * </pre>
 * 
 * @author G. Vaidhyanathan
 * @version 1.0
 */
public abstract class AbstractI18NMessages implements LocaleListener {

	/** Single ResourceBundle for the I18NMessages */
	private final ResourceBundle resourceBundle;
	private final String bundle;
	private final ResourceBundle.Control control = new LocaleResourceBundleControl();

	// Constructs the messages handler.
	protected AbstractI18NMessages(String bundle_p) {
		bundle = bundle_p;
		resourceBundle = ResourceBundle
				.getBundle(bundle, UIManager.getDefaults().getDefaultLocale(), control);
		LocaleRegistry.getRegistry().addLocaleListener(this);
	}

	/**
	 * Gets the string from the specified key.
	 * 
	 * @param key
	 *            The key.
	 * @return The string result.
	 */
	public final String getString(String key) {
		if (key.length() == 0) {
			return ""; //$NON-NLS-1$
		}
		try {
			return resourceBundle.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ucp.LocaleListener#localeChanged(java.util.Locale)
	 */
	@Override
	public void localeChanged(Locale newLocale) {
		LocalePropertyResourceBundle lpResbundle = (LocalePropertyResourceBundle) resourceBundle;
		try {
			if (!lpResbundle.containsLocale(newLocale)) {
				lpResbundle.addBundle(getStreamToAdd(newLocale), newLocale);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		lpResbundle.setCurrentLocale(newLocale);
	}

	private InputStream getStreamToAdd(Locale locale) {
		String bundleName = control.toBundleName(bundle, locale);
		String resourceName = control.toResourceName(bundleName, control.getFormats(bundle).get(0));
		InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
		return stream;
	}
}
