/*
 * Created on Feb 11, 2005
 *
 * $Id: DatePropertyEditor.java,v 1.1 2007/05/30 18:04:47 jrflach Exp $
 */
package com.workcase.utils;

import java.beans.PropertyEditorManager;
import java.beans.PropertyEditorSupport;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.workcase.gui.utils.WebResourceMessage;

/**
 * 
 * @spring.bean id="fakeDateEditor" lazy="false"
 */
public class DatePropertyEditor extends PropertyEditorSupport {

	static {
		PropertyEditorManager.registerEditor(java.util.Date.class,
				DatePropertyEditor.class);
	}
	
	private String format;
	private SimpleDateFormat sdf;
		
	public DatePropertyEditor() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyEditor#getAsText()
	 */
	public String getAsText() {

		//Cria a referencia para o resource bundle
		//ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		//bundle.setBasename("com.workcase.i18n.application");
		format = WebResourceMessage.getInstance().getMessage("formats.date");//bundle.getMessage("formats.date", null, WebResourceMessage.getInstance().getCurrentLocale());

		Date datePerson = (Date) getValue();
		Object[] dateFormat = { datePerson };
		//java.sql.Timestamp ts = (java.sql.Timestamp) datePerson;

		String resultingDate = MessageFormat.format(
				"{0, date, " + format + "}", dateFormat);

		return resultingDate;
		//return sdf.format(datePerson);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.beans.PropertyEditor#setAsText(java.lang.String)
	 */
	public void setAsText(String text) throws IllegalArgumentException {

		//Cria a referencia para o resource bundle
		//ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		//bundle.setBasename("com.workcase.i18n.application");
		format = WebResourceMessage.getInstance().getMessage("formats.date");//bundle.getMessage("formats.date", null, WebResourceMessage.getInstance().getCurrentLocale());
		
		sdf = new SimpleDateFormat(format);		

		if (text == null || "".equals(text.trim())) {
			setValue(null);
		} else {
			try {
				setValue(sdf.parse(text));
			} catch (ParseException e) {
				throw new IllegalArgumentException(e.getMessage());
			}
		}
	}

	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
		sdf = new SimpleDateFormat(format);
	}
}