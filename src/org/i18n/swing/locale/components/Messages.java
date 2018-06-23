/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale.components;

import org.i18n.swing.locale.AbstractI18NMessages;

/**
 * Singleton Messages for Locale Settings
 * 
 * @see AbstractI18NMessages
 * @author G. Vaidhyanathan
 */
public class Messages extends AbstractI18NMessages {

	// The bundle name.
	private static final String BUNDLE_NAME = "org.i18n.swing.locale.components.messages"; //$NON-NLS-1$

	private static final Messages instance = new Messages();

	public static final Messages getInstance() {
		return instance;
	}

	// Constructs the messages handler.
	protected Messages() {
		super(BUNDLE_NAME);
	}
}