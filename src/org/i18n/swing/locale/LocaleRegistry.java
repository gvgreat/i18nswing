/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Singleton Registry for Locales.
 * <P>
 * Adds, removes and notifies the locale listeners
 * 
 * @see LocaleListener
 * @author G. Vaidhyanathan
 */
public final class LocaleRegistry {
	/** Singleton instance */
	private static final LocaleRegistry registry = new LocaleRegistry();
	/** Listeners */
	private final List<LocaleListener> listeners = new ArrayList<LocaleListener>(1);

	/**
	 * @return the singleton instance
	 */
	public static final LocaleRegistry getRegistry() {
		return registry;
	}

	private LocaleRegistry() {
		// private constructor
	}

	/**
	 * Adds a locale listener to the registry
	 * 
	 * @param listener
	 *            the locale listener
	 */
	public void addLocaleListener(LocaleListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes a locale listener from the registry
	 * 
	 * @param listener
	 *            the locale listener
	 */
	public void removeLocaleListener(LocaleListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Notifies the Listeners associated with the registry that the locale has changed, and the when the UI is
	 * repainted, the text on the component will reflect the current Locale
	 * 
	 * @param locale
	 *            the changed locale
	 */
	public final void notifyLocaleListeners(Locale locale) {
		for (LocaleListener listener : listeners) {
			listener.localeChanged(locale);
		}
	}
}
