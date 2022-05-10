package com.adapit.portal.ui.forms.solution.table;


import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;


@SuppressWarnings({"serial","unchecked"})	
public class TableComercialSolutionListFormModel extends DefaultTableModel{
	
	
	Class types[] = new java.lang.Class[] {
			String.class,
			Float.class,
			JComboBox.class,
			String.class,
			Date.class};
	
	 boolean canEdit[] = new boolean[] {false,false,false,false,false};


	
	public TableComercialSolutionListFormModel(Object[][] values ){

			super(values, new String[]{});
	}
	
	@Override
	public Class getColumnClass(int columnIndex){
		return types[columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int columnIndex,int rowIndex){
		return canEdit[columnIndex];
	}



}