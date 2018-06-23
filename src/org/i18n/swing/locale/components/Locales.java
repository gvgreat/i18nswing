/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale.components;

/**
 * ENUM for Locales.
 * <P>
 * Ensure the code of enums are initialized with the key from the properties file (e.g.) for English
 * (messages_en.properties), French (messages_fr.properties), ...
 * 
 * @author G. Vaidhyanathan
 */
public enum Locales {
	ENGLISH("language.EN"), FRENCH("language.FR"); //$NON-NLS-1$//$NON-NLS-2$

	private final String code;

	private Locales(String code_p) {
		code = code_p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return Messages.getInstance().getString(code);
	}
}
