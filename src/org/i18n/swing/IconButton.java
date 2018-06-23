/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Icon Button (a button with icon only)
 * 
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class IconButton extends JButton {

	/**
	 * @param icon
	 */
	public IconButton(Icon icon) {
		super(icon);
	}

	/**
	 * @param helpAction
	 */
	public IconButton(Action action) {
		super(action);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Component#repaint()
	 */
	@Override
	public void repaint() {
		setBorder(null);
		super.repaint();
	}
}
