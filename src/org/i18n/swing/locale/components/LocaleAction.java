/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale.components;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import org.i18n.swing.I18NAbstractAction;
import org.i18n.swing.util.DialogUtil;

/**
 * Action to set/change the current locale of the application
 * <P>
 * This can be used with a MenuItem, Button, Tool Item etc
 * 
 * @author G. Vaidhyanathan
 * 
 */
@SuppressWarnings("serial")
public class LocaleAction extends I18NAbstractAction {
	/** The parent frame for the action */
	private final JFrame parentFrame;

	public LocaleAction(JFrame parent) {
		super("language.action", Messages.getInstance());
		parentFrame = parent;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		LocalePanel langPanel = new LocalePanel(parentFrame);
		DialogUtil.createAndShowDialog(parentFrame, Messages.getInstance().getString(
				"language.title"), langPanel, null); //$NON-NLS-1$
	}
}
