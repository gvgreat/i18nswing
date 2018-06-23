/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing;

import javax.swing.JButton;

import org.i18n.swing.locale.AbstractI18NMessages;

/**
 * JButton with I18N feature.
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class I18NJButton extends JButton {
	private final String textCode;
	private final AbstractI18NMessages messages;
	private final boolean allVariablesInitialized;

	public I18NJButton(String code, AbstractI18NMessages msg) {
		textCode = code;
		messages = msg;
		allVariablesInitialized = true;
		setText(messages.getString(textCode));
	}

	@Override
	public void repaint() {
		super.repaint();
		if (allVariablesInitialized) {
			setText(messages.getString(textCode));
		}
	}
}
