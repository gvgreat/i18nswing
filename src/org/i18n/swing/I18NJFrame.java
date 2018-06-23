/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing;

import javax.swing.JFrame;

import org.i18n.swing.locale.AbstractI18NMessages;

/**
 * JFrame with I18N feature.
 * 
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class I18NJFrame extends JFrame {
	private final String textCode;
	private final AbstractI18NMessages messages;
	private final boolean allVariablesInitialized;

	public I18NJFrame(String code, AbstractI18NMessages msg) {
		textCode = code;
		messages = msg;
		allVariablesInitialized = true;
	}

	@Override
	public void repaint() {
		super.repaint();
		if (allVariablesInitialized) {
			setTitle(messages.getString(textCode));
		}
	}
}
