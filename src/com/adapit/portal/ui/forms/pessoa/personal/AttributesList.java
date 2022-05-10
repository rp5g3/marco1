package com.adapit.portal.ui.forms.pessoa.personal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.AtributoPessoa;
import com.adapit.portal.entidades.PersonAttributeType;
import com.adapit.portal.services.remote.RemotePessoaService;
@SuppressWarnings({"serial","unchecked"})
public class AttributesList  extends JXTable implements PropertyChangeListener{		
		
	private List<AtributoPessoa> list = null;
	
	@SuppressWarnings("deprecation")
	public AttributesList() {
		super();
		this.setModel(new PreferenciaModel(null));
		addPropertyChangeListener(this);
		getColumnModel().getColumn(0).setPreferredWidth(200);
		getColumnModel().getColumn(1).setPreferredWidth(60);
		Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
		setHighlighters(highlighters);
		updateTable(null);			
	}

	public boolean isSelected(PersonAttributeType type){
		if (list != null && list.size()>0){
			for(AtributoPessoa att: list){
				if (att.getAtributo() == type) return true;
			}
		}
		return false;
	}
	
	private Long id = null;
	
	public void updateTable(Long personid) {
		try{
			this.id = personid;
			PersonAttributeType types[] = PersonAttributeType.values();			
			java.lang.Object values[][] = new java.lang.Object[types.length][2];			
			if(personid != null)
				list=RemotePessoaService.getInstance().listAtributoPessoaByPersonId(personid);
			for(int i=0; i< types.length; i++) {
				PersonAttributeType type = types[i];
				
				values[i][0] = type.name();
				if (personid != null)
					values[i][1] = isSelected(type);
				else values[i][1] = false;				
			} 
			if (values != null && values.length>0)
				setModel(new PreferenciaModel(values));
			else setModel(new PreferenciaModel(null));
			getColumnModel().getColumn(0).setPreferredWidth(200);
			getColumnModel().getColumn(1).setPreferredWidth(60);
			updateUI();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private class PreferenciaModel extends DefaultTableModel {		

		Class types[] = new java.lang.Class[] { String.class,Boolean.class };

		boolean canEdit[] = new boolean[] {false, true };

		public PreferenciaModel(Object[][] values) {
			super(values,new String[] {"Atributo","Sim/Não"});
		}
		public Class getColumnClass(int columnIndex) {

			return types[columnIndex];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {

			return canEdit[columnIndex];
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("tableCellEditor")){
			int row = getSelectedRow();
			//int col = getSelectedColumn();
			//if (col != 1) return;
			if (row < 0) return;
			Boolean value = (Boolean) getValueAt(row, 1);
			String propName = (String) getValueAt(row, 0);
			try {
				System.out.println(value + " " + propName);
				PersonAttributeType type = PersonAttributeType.valueOf(propName);
				AtributoPessoa att = new AtributoPessoa();
				att.setAtributo(type);
				att.setIdPessoa(id);
				if (value)
					RemotePessoaService.getInstance().save(att);
				else
					RemotePessoaService.getInstance().delete(att);
				
				updateTable(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public void setIdPessoa(long id2) {
		id = id2;
		updateTable(id);
	}
}