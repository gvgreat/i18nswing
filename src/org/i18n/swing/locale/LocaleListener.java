/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale;

import java.util.EventListener;
import java.util.Locale;

/**
 * <code>LocaleListener</code> class. When a swing application needs to change the locale dynamically
 * immediately after user action, this is handy.
 * <P>
 * LocaleListener is registered with a LocaleRegistry (a singleton instance for the application), will effect
 * in change of locale of the application dynamically.
 * 
 * @see LocaleRegistry
 * @author G. Vaidhyanathan
 * 
 */
public interface LocaleListener extends EventListener {

	/**
	 * Notifies the locale changed event
	 */
	void localeChanged(Locale newLocale);
}
