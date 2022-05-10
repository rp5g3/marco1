package com.adapit.portal.ui.forms.solution.table;

import java.beans.PropertyChangeListener;

public class TableComercialSolutionListFormPropertyChangeListener implements
		PropertyChangeListener {
	public void propertyChange(java.beans.PropertyChangeEvent e) {
		com.adapit.portal.ui.forms.solution.table.TableComercialSolutionListForm jt = (com.adapit.portal.ui.forms.solution.table.TableComercialSolutionListForm) e
				.getSource();
		int col = jt.getSelectedColumn();
		int row = jt.getSelectedRow();
		if (jt.getElements() != null && row > -1)
			try {
				java.lang.Object obj = jt.getElements().get(row);
				if (obj instanceof com.adapit.portal.entidades.ComercialSolution) {
					com.adapit.portal.entidades.ComercialSolution sol = (com.adapit.portal.entidades.ComercialSolution) obj;
					if (col == 0)
						sol.setDescricao(((java.lang.String) jt.getValueAt(
								row, col)));
					if (col == 1)
						sol.setAvaliacao((java.lang.Float
								.parseFloat((java.lang.String) jt.getValueAt(
										row, col))));
					if (col == 2)
						sol
								.setCategoria((com.adapit.portal.entidades.Categoria) ((javax.swing.JComboBox) jt
										.getValueAt(row, col))
										.getSelectedItem());
					// if (col ==2)
					// produto.setNome((com.adapit.portal.entidades.Categoria)((javax.swing.JComboBox)jt.getValueAt(row,
					// col)).getSelectedItem());
				}
			} catch (java.lang.Exception ex) {
				ex.printStackTrace();
			}
	}
}