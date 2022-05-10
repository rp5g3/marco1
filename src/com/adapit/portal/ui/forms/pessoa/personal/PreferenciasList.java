package com.adapit.portal.ui.forms.pessoa.personal;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Hashtable;

import javax.swing.JTextPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.services.remote.RemotePreferenciaService;
import com.adapit.portal.ui.forms.pessoa.PreferenciaUsuario;
@SuppressWarnings({"serial","unchecked"})
public class PreferenciasList  extends JXTable/* implements PropertyChangeListener*/{		
	public PreferenciaCategoria pref=null;
	private JTextPane outrasCategoriasTextField;
	
	public JTextPane getOutrasCategoriasTextField(){
		if (outrasCategoriasTextField == null){
			outrasCategoriasTextField = new JTextPane();
			//outrasCategoriasTextField.setToolTipText("Tecle ENTER para gravar os dados");
			outrasCategoriasTextField.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent evt) {
					String value = outrasCategoriasTextField.getText();
					try {
						RemotePreferenciaService.getInstance()
						.updateOutrasCategoriasByPreferenciaId(pref.getId(), value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}				
			});
			
		}
		return outrasCategoriasTextField;
	}
	
	
	@SuppressWarnings("deprecation")
	public PreferenciasList() {
		super();
		this.setModel(new PreferenciaModel(null));
		//addPropertyChangeListener(this);
		//getSelectionModel().addListSelectionListener(list);
		getColumnModel().getColumn(0).setPreferredWidth(200);
		getColumnModel().getColumn(1).setPreferredWidth(60);
		Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
		setHighlighters(highlighters);
		updateTable();			
	}

	
	public void updateTable() {
		try{
			java.lang.Object values[][] = PreferenciaUsuario.setPreferenciaUsuario(pref);
			
			if (outrasCategoriasTextField != null && pref != null)
				outrasCategoriasTextField.setText(pref.getPreferencias());
			else if (outrasCategoriasTextField != null)
				outrasCategoriasTextField.setText("");
			setModel(new PreferenciaModel(values));
			getColumnModel().getColumn(0).setPreferredWidth(200);
			getColumnModel().getColumn(1).setPreferredWidth(60);
			updateUI();	
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private class PreferenciaModel extends DefaultTableModel {
		

		Class types[] = new java.lang.Class[] { String.class,Boolean.class };

		boolean canEdit[] = new boolean[] {false, false };

		public PreferenciaModel(Object[][] values) {
			super(values,new String[] {"Preferência","Sim/Não"});
		}
		public Class getColumnClass(int columnIndex) {

			return types[columnIndex];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {

			return canEdit[columnIndex];
		}
	}
	
	ListSelectionListener list = new ListSelectionListener(){
		private Hashtable<String,Boolean> ht = new Hashtable<String,Boolean>();
		@Override
		public void valueChanged(ListSelectionEvent evt) {
			try {
				if(evt.getValueIsAdjusting() || !(evt.getSource() == getSelectionModel() && getRowSelectionAllowed())
					)
					return;
				System.out.println("list seleciontion listener");
				int row = getSelectedRow();
				if (row < 0) return;
				Boolean value = (Boolean) getValueAt(row, 1);
				String propName = (String) getValueAt(row, 0);
				if(ht.containsKey(propName)){
					Boolean val = ht.get(propName);
					if(val.booleanValue() == value.booleanValue())
						return;
				}
				ht.put(propName,value);
				
				System.out.println("propName " + propName + " value " + value);
			
				for(int i=0; i < PreferenciaUsuario.titles.length; i++){
					String val = PreferenciaUsuario.titles[i];
					if(val.equals(propName)){
						propName = PreferenciaUsuario.properties[i];
						break;
					}
				}
				System.out.println("propName " + propName + " value " + value);
				pref = RemotePreferenciaService.getInstance()
					.updatePreferenciaByPropertyName(propName, pref.getId(), value);
				updateTable();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	};

	/*@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("tableCellEditor")){
			int row = getSelectedRow();
			if (row < 0) return;
			Boolean value = (Boolean) getValueAt(row, 1);
			String propName = (String) getValueAt(row, 0);
			System.out.println("propName " + propName + " value " + value);
			try {
				for(int i=0; i < PreferenciaUsuario.titles.length; i++){
					String val = PreferenciaUsuario.titles[i];
					if(val.equals(propName)){
						propName = PreferenciaUsuario.properties[i];
						break;
					}
				}
				System.out.println("propName " + propName + " value " + value);
				pref = RemotePreferenciaService.getInstance()
					.updatePreferenciaByPropertyName(propName, pref.getId(), value);
				updateTable();
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}*/
}