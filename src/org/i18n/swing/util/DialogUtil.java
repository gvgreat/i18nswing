/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.util;

import java.awt.Component;
import java.awt.Point;
import java.awt.Window;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Utility class for Dialog operations
 * 
 * @author G. Vaidhyanathan
 */
public class DialogUtil {
	private static JProgressBar bar = new JProgressBar(SwingConstants.CENTER);
	private static JDialog dialog;

	/**
	 * Show error dialog
	 * 
	 * @param component
	 * @param message
	 */
	final static public void showErrorDialog(Component component, String message) {
		JOptionPane.showMessageDialog(component, message,
				Messages.getString("DialogUtil.ERROR"), JOptionPane.ERROR_MESSAGE); //$NON-NLS-1$
	}

	/**
	 * Show success dialog
	 * 
	 * @param component
	 * @param message
	 */
	final static public void showSuccessDialog(Component component, String message) {
		JOptionPane.showMessageDialog(component, message,
				Messages.getString("DialogUtil.SUCCESS"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
	}

	/**
	 * Show Information dialog
	 * 
	 * @param component
	 * @param message
	 */
	final static public void showInformationDialog(Component component, String message) {
		JOptionPane.showMessageDialog(component, message,
				Messages.getString("DialogUtil.INFO"), JOptionPane.INFORMATION_MESSAGE); //$NON-NLS-1$
	}

	/**
	 * Display the progress monitor
	 * 
	 * @param message
	 */
	final static public void showProgressMonitor(String message) {
		bar.setVisible(true);
		bar.setString(message);
	}

	/**
	 * Close progress bar
	 */
	final static public void closeProgress() {
		bar.setVisible(false);
	}

	/**
	 * Create and Show the dialog box
	 * 
	 * @param title
	 * @param content
	 * @param location
	 */
	final public static <P extends JPanel> void createAndShowDialog(String title, P content) {
		createAndShowDialog(getWindow(content), title, content, null);
	}

	final public static <P extends JPanel> void createAndShowDialog(JFrame window, String title,
			P content, Point location) {
		dialog = new JDialog(window, title, true);
		dialog.setContentPane(content);
		dialog.pack();
		if (location != null) {
			dialog.setLocation(location);
		} else {
			dialog.setLocationRelativeTo(window);
		}
		dialog.setResizable(false);
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
	}

	/**
	 * Create and show Clock
	 * 
	 * @param content
	 * @param location
	 * @param invoker
	 */
	final public static void createAndShowClock(JPanel content, Point location, Component invoker) {
		final JPopupMenu menu = new JPopupMenu();
		menu.setLocation(location);
		menu.add(content);
		menu.pack();
		menu.setVisible(true);
		if (invoker != null)
			menu.setInvoker(invoker);
	}

	/**
	 * Dispose the Dialog box
	 */
	final public static void disposeDialog() {
		if (dialog != null) {
			dialog.dispose();
			dialog = null;
		}
	}

	public static final JFrame getWindow(JComponent component) {
		Component current = component;
		Component parent = current.getParent();
		if (parent.getClass() == JPopupMenu.class) {
			current = ((JPopupMenu) parent).getInvoker();
		}
		Window window = SwingUtilities.getWindowAncestor(current);
		if (window.getClass() == JFrame.class) {
			return (JFrame) window;
		}
		if (window.getClass() == JDialog.class) {
			return (JFrame) ((JDialog) window).getOwner();
		}
		return null;
	}

}
