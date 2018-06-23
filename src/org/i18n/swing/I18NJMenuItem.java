/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing;

import javax.swing.JMenuItem;

import org.i18n.swing.locale.AbstractI18NMessages;

/**
 * JMenuItem with I18N feature.
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class I18NJMenuItem extends JMenuItem {
	private final String textCode;
	private final AbstractI18NMessages messages;
	private final boolean allVariablesInitialized;

	public I18NJMenuItem(String code, AbstractI18NMessages msg) {
		textCode = code;
		messages = msg;
		setText(messages.getString(textCode));
		allVariablesInitialized = true;
	}

	@Override
	public void repaint() {
		super.repaint();
		if (allVariablesInitialized) {
			setText(messages.getString(textCode));
		}
	}
}
