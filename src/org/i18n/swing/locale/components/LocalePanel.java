/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.locale.components;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.i18n.swing.I18NJLabel;
import org.i18n.swing.locale.LocaleRegistry;
import org.i18n.swing.util.DialogUtil;

/**
 * Panel for showing available Locales.
 * 
 * @author G. Vaidhyanathan
 */
@SuppressWarnings("serial")
public class LocalePanel extends JPanel implements ItemListener {

	/** Default Locale is ENGLISH */
	private Locale selectedLocale = Locale.ENGLISH;
	private final JLabel langLabel;
	private final JComboBox cboLanguage;
	private Messages langMsg = Messages.getInstance();
	private final JFrame parentFrame;

	public LocalePanel(JFrame parent) {
		parentFrame = parent;
		setLayout(new BorderLayout());
		langLabel = new I18NJLabel("language.label", langMsg); //$NON-NLS-1$
		JPanel tmp = new JPanel();
		tmp.add(langLabel);
		cboLanguage = new JComboBox(Locales.values());
		cboLanguage.addItemListener(this);
		tmp.add(cboLanguage);

		add(tmp, BorderLayout.CENTER);

		JButton okButton = new JButton("OK"); //$NON-NLS-1$
		okButton.addActionListener(new ActionListener() {

			@SuppressWarnings("synthetic-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				saveLocale();
				DialogUtil.disposeDialog();
				LocaleRegistry.getRegistry().notifyLocaleListeners(selectedLocale);
				SwingUtilities.updateComponentTreeUI(parentFrame);
			}
		});

		JPanel pp = new JPanel(new BorderLayout());
		pp.add(okButton, BorderLayout.EAST);

		add(pp, BorderLayout.SOUTH);
	}

	/**
	 * Save the locale to a file to ensure the same locale used when the application is relaunched.
	 */
	protected void saveLocale() {
		try {
			UIManager.getDefaults().setDefaultLocale(selectedLocale);
			FileOutputStream fos = new FileOutputStream("preferred-locale"); //$NON-NLS-1$
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(selectedLocale);
			oos.flush();
			oos.close();
			fos.flush();
			fos.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		// fire the event only on selection
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Locales locales = (Locales) e.getItem();
			switch (locales) {
			case ENGLISH:
				selectedLocale = Locale.ENGLISH;
				break;
			case FRENCH:
				selectedLocale = Locale.FRENCH;
				break;

			/**
			 * TODO Add More Locales If needed
			 */
			default:
				selectedLocale = Locale.ENGLISH;
				break;
			}
		}
	}
}
