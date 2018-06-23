/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing;

import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.i18n.swing.locale.AbstractI18NMessages;

/**
 * Titled Border with I18N feature
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class I18NTitledBorder extends TitledBorder {
	private final AbstractI18NMessages messages;

	public I18NTitledBorder(Border border_p, String title_p, AbstractI18NMessages msg) {
		super(border_p, title_p);
		messages = msg;
	}

	@Override
	public String getTitle() {
		return messages.getString(title);
	}
}
