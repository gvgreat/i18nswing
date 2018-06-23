/**
 * @Copyrights G. Vaidhyanathan
 */
package org.i18n.swing.util;

import java.awt.Component;
import java.awt.Point;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.border.Border;
import javax.xml.bind.JAXBException;

import org.i18n.swing.I18NTitledBorder;
import org.i18n.swing.locale.AbstractI18NMessages;

/**
 * Miscellaneous Utility class
 * 
 * @author G. Vaidhyanathan
 * @version 1.0
 */
public class Utils {
	/** Date Format */
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); //$NON-NLS-1$

	/** Boxing map for primitives (especially useful when handling java.lang.reflection calls) */
	private static final Map<Class<?>, Class<?>> BOXING_MAP = new HashMap<Class<?>, Class<?>>(1);
	private static final Border etchBorder = BorderFactory.createEtchedBorder();

	static {
		BOXING_MAP.put(Integer.class, int.class);
		BOXING_MAP.put(Double.class, double.class);
		BOXING_MAP.put(Float.class, float.class);
		BOXING_MAP.put(Boolean.class, boolean.class);
	}

	/**
	 * Check the length of string for zero
	 * 
	 * @param string
	 * @return true if length is zero
	 */
	final public static boolean isZeroLength(String string) {
		return (string == null) || (string.trim().length() == 0);
	}

	/**
	 * Get the Date in "DDMMYYYY" format
	 * 
	 * @param date
	 * @return
	 */
	final public static String getFormattedDate(Date date) {
		try {
			return sdf.format(date);
		} catch (Exception ex) {
			return null;
		}

	}

	/**
	 * Parse the date using DDMMYYYY format
	 * 
	 * @param date
	 * @return
	 */
	final public static Date getParsedDate(String date) {
		try {
			return sdf.parse(date);
		} catch (ParseException ex) {
			return null;
		}
	}

	/**
	 * Capitalizes the first letter of a single word
	 * <P>
	 * e.g. returns "Name" if input is "name"
	 * 
	 * @param toCapitalize
	 *            the word to capitalize
	 * @return the capitalized word
	 */
	@SuppressWarnings("boxing")
	public static final String getCapitalized(String toCapitalize) {
		StringBuilder builder = new StringBuilder(toCapitalize);
		Character c = Character.toUpperCase(builder.charAt(0));
		builder.replace(0, 1, c.toString());
		return builder.toString();
	}

	/**
	 * Gets the accessor method name for the given field name of a class
	 * <P>
	 * e.g. if the field name is "name" then this method would return "getName"
	 * 
	 * @param fieldName
	 *            the field
	 * @return the accessor method of the field
	 */
	public static final String getAccessorMethodName(String fieldName) {
		StringBuilder builder = new StringBuilder(getCapitalized(fieldName));
		builder.insert(0, "get"); //$NON-NLS-1$
		return builder.toString();
	}

	/**
	 * Gets the mutator method name for the given field name of a class
	 * <P>
	 * e.g. if the field name is "name" then this method would return "setName"
	 * 
	 * @param fieldName
	 *            the field
	 * @return the mutator method of the field
	 */
	public static final String getMutatorMethodName(String fieldName) {
		StringBuilder builder = new StringBuilder(getCapitalized(fieldName));
		builder.insert(0, "set"); //$NON-NLS-1$
		return builder.toString();
	}

	/**
	 * Gets the string of values and capitalize it
	 * 
	 * @param toCapitalize
	 *            array of string to capitalize
	 * @return capitalized String array
	 */
	public static final String[] getValuesCapitalized(String[] toCapitalize) {
		String[] returnValue = new String[toCapitalize.length];
		int cnt = 0;
		for (String value : toCapitalize) {
			returnValue[cnt++] = getCapitalized(value);
		}
		return returnValue;
	}

	/**
	 * Unboxes wrapper class to the respective primitive
	 * 
	 * @param anyClass
	 * @return the unboxed class
	 */
	public static final Class<?> unboxClass(Class<?> anyClass) {
		Class<?> returnClass = BOXING_MAP.get(anyClass);
		if (returnClass == null) {
			returnClass = anyClass;
		}
		return returnClass;
	}

	/**
	 * Populates the values for the given object with the mutator method
	 * 
	 * @param values
	 *            the key value pair with the field name and the value
	 * @param object
	 *            the object to be mutated
	 */
	public static final void populateValuesForObject(Map<String, Object> values, Object object) {
		Set<String> keys = values.keySet();
		for (String field : keys) {
			try {
				Object value = values.get(field);
				object.getClass().getMethod(Utils.getMutatorMethodName(field), unboxClass(value.getClass()))
						.invoke(object, value);
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
			} catch (SecurityException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			} catch (InvocationTargetException ex) {
				ex.printStackTrace();
			} catch (NoSuchMethodException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * Gets message out of JAXBException
	 * 
	 * @param exception
	 *            the exception thrown
	 * @return linked exception with the current exception
	 */
	public static final String getMessage(Exception exception) {
		StringBuilder msg = new StringBuilder();
		if (exception instanceof JAXBException) {
			msg.append(((JAXBException) exception).getLinkedException().getMessage());
		}
		if (exception.getCause() instanceof JAXBException) {
			msg.append(((JAXBException) exception.getCause()).getLinkedException().getMessage());
		}
		return msg.toString();
	}

	/**
	 * Gets the titled border with I18N effect
	 * 
	 * @param titleKey
	 *            the title key in the messages properties
	 * @param messages
	 *            the AbstractI18NMessages
	 * @return titled compound border with etch
	 */
	public static Border getTitledBorder(String titleKey, AbstractI18NMessages messages) {
		return new I18NTitledBorder(etchBorder, titleKey, messages);
	}

	public static Point getLocationRelativeToComponent(Component component) {
		if (null != component && component.isShowing()) {
			Point buttonLocation = component.getLocationOnScreen();
			int x = buttonLocation.x;
			int y = buttonLocation.y;
			return new Point(x + component.getWidth() / 2, y + component.getHeight() / 2);
		}
		return new Point(0, 0);
	}
}
