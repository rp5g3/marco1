package com.adapit.portal.services.controllers;

import java.beans.PropertyEditorSupport;

import com.adapit.portal.entidades.Pais;

public class PaisPropertyEditor extends PropertyEditorSupport{
	
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditor#getAsText()
	 */
	public String getAsText() {
		String str = super.getAsText();
		return str.replaceAll("_"," ");
		//return str;
	}
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditor#setAsText(java.lang.String)
	 */
	public void setAsText(String str) throws IllegalArgumentException {
		if(str==null||"".equals(str.trim()))
			setValue(null);
		else
		{
			setValue(Pais.valueOf(str.replaceAll(" ","_")));
		}
	}
}