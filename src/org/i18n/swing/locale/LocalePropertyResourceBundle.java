/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.UIManager;

import sun.util.ResourceBundleEnumeration;

/**
 * <code>LocalePropertyResourceBundle</code> class, which is responsible for loading the properties associated
 * with the specific Locale.
 * <P>
 * Usually, ResourceBundle loads a property file afresh everytime. This is avoided by mapping the locale
 * specific properties with the locale using the localeLookup. Otherwise acts similar to ResourceBundle.
 * <P>
 * Initialized with LocaleResourceBundleControl
 * @see LocaleResourceBundleControl
 * @author G. Vaidhyanathan
 */
public class LocalePropertyResourceBundle extends ResourceBundle {

	// ==================privates====================

	private Map<String, Object> lookup;
	private final Map<Locale, Map<String, Object>> localeLookup = new HashMap<Locale, Map<String, Object>>(1);
	private Locale currentLocale = UIManager.getDefaults().getDefaultLocale();

	/**
	 * @param stream
	 * @throws IOException
	 */
	public LocalePropertyResourceBundle(InputStream stream, Locale locale) throws IOException {
		Properties properties = new Properties();
		properties.load(stream);
		initMap(properties, locale);
	}

	/**
	 * @param reader
	 * @throws IOException
	 */
	public LocalePropertyResourceBundle(Reader reader, Locale locale) throws IOException {
		Properties properties = new Properties();
		properties.load(reader);
		initMap(properties, locale);
	}

	@SuppressWarnings("unchecked")
	private void initMap(Properties properties, Locale locale) {
		if (localeLookup.containsKey(locale)) {
			lookup = localeLookup.get(locale);
			lookup.putAll((Map) properties);
		} else {
			lookup = new HashMap(properties);
			localeLookup.put(locale, lookup);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ResourceBundle#getKeys()
	 */
	@Override
	public Enumeration<String> getKeys() {
		return new ResourceBundleEnumeration(lookup.keySet(), (parent != null) ? parent.getKeys() : null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.ResourceBundle#handleGetObject(java.lang.String)
	 */
	@Override
	protected Object handleGetObject(String key) {
		if (key == null) {
			throw new NullPointerException();
		}
		return localeLookup.get(currentLocale).get(key);
	}

	public void addBundle(InputStream stream, Locale locale) throws IOException {
		Properties properties = new Properties();
		properties.load(stream);
		initMap(properties, locale);
	}

	/**
	 * @return the currentLocale
	 */
	public Locale getCurrentLocale() {
		return currentLocale;
	}

	/**
	 * @param currentLocale_p
	 *            the currentLocale to set
	 */
	public void setCurrentLocale(Locale currentLocale_p) {
		this.currentLocale = currentLocale_p;
	}

	public final boolean containsLocale(Locale locale) {
		return localeLookup.containsKey(locale);
	}

}
