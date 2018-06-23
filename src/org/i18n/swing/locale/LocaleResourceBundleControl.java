/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ResourceBundle.Control;

/**
 * Control for LocalePropertyResourceBundle
 * 
 * @see Control
 * 
 * @author G. Vaidhyanathan
 */
public class LocaleResourceBundleControl extends Control {
	/**
	 * Returns the list of formats applicable for this current ResourceBundle. (properties file in this case)
	 * 
	 * @see Control#getFormats(String)
	 */
	@Override
	public List<String> getFormats(String baseName) {
		if (baseName == null) throw new NullPointerException();

		return Collections.unmodifiableList(Arrays.asList("properties")); //$NON-NLS-1$
	}

	/**
	 * Usually newBundle() creates a new bundle on every call. But to avoid unnecessary creation of bundles
	 * for various locales, properties are mapped with locale (a Map<Locale, Map<String, Object> of maps is
	 * used in LocalePropertyResourceBundle) to ensure a single creation of the ResourceBundle
	 * @see AbstractI18NMessages
	 * @see Control#newBundle(String, Locale, String, ClassLoader, boolean)
	 */
	@Override
	public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader,
			boolean reload) throws IllegalAccessException, InstantiationException, IOException {

		if (baseName == null || locale == null || format == null || loader == null) {
			throw new NullPointerException();
		}
		ResourceBundle bundle = null;
		String bundleName = toBundleName(baseName, locale);
		String resourceName = toResourceName(bundleName, format);
		InputStream stream = null;
		if (reload) {
			URL url = loader.getResource(resourceName);
			if (url != null) {
				URLConnection connection = url.openConnection();
				if (connection != null) {
					// Disable caches to get fresh data for
					// reloading.
					connection.setUseCaches(false);
					stream = connection.getInputStream();
				}
			}
		} else {
			stream = loader.getResourceAsStream(resourceName);
		}
		if (stream != null) {
			BufferedInputStream bis = new BufferedInputStream(stream);
			// bundle is created only once in this case
			bundle = new LocalePropertyResourceBundle(bis, locale);
			bis.close();
		}
		return bundle;
	}
}
