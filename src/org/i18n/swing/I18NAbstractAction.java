/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing;

import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.Action;

import org.i18n.swing.locale.AbstractI18NMessages;
import org.i18n.swing.locale.LocaleListener;
import org.i18n.swing.locale.LocaleRegistry;

/**
 * Abstract Action with I18N feature.
 * <P>
 * This implements LocaleListener, and when the locale changes, changes the locale accordingly to the new one
 * selected. Because Action cannot directly use the messages, LocaleListener is implemented explicitly.
 * 
 * @see LocaleRegistry
 * @see LocaleListener
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public abstract class I18NAbstractAction extends AbstractAction implements LocaleListener {
	private final String textCode;
	protected final AbstractI18NMessages messages;

	public I18NAbstractAction(String code, AbstractI18NMessages msg) {
		super(msg.getString(code));
		textCode = code;
		messages = msg;
		LocaleRegistry.getRegistry().addLocaleListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ucp.LocaleListener#localeChanged(java.util.Locale)
	 */
	@Override
	public void localeChanged(Locale newLocale) {
		putValue(Action.NAME, messages.getString(textCode));
	}
}
