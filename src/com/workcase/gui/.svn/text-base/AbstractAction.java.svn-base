package com.workcase.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.workcase.gui.utils.SwingWorker;

public abstract class AbstractAction implements ActionListener {

	public AbstractAction(){
		super();
		
	}


	
	public void actionPerformed(ActionEvent evt) {
		final ActionEvent e = evt;	
		
		final SwingWorker s = new SwingWorker() {
			public Object construct() {					
				try {
					doAction(e);						
				} catch (RuntimeException e1) {
					
				}
				return null;
			}
		};
		s.start();
		
	}
	
	protected abstract void doAction(ActionEvent e);

	
	/**
	 * 
	 * @param str
	 * @return return a string with first in upper case
	 */
	public String getFirstUpperCase(String str) {
		if (str != null && str.length() > 0) {
			String n = str.substring(1);
			char ch = str.charAt(0);
			return (Character.toUpperCase(ch) + n);
		} else
			return "";
	}

	/**
	 * 
	 * @param str
	 * @return return a string with first in lower case
	 */
	public String getFirstLowerCase(String str) {
		if (str != null && str.length() > 0) {
			String n = str.substring(1);
			char ch = str.charAt(0);
			return (Character.toLowerCase(ch) + n);
		} else
			return "";
	}

	/**
	 * 
	 * @param str
	 * @return return a string with first in lower case, besides thirst
	 *         character
	 */
	public String getFirstLowerCaseWithoutGet(String str) {
		if (str != null && str.length() > 0){
			String n = str.substring(4);
			char ch = str.charAt(3);
			return (Character.toLowerCase(ch) + n);
		}return str;
	}

}
