package com.adapit.portal.ui.forms.pessoa.personal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.dto.CategoriaPreferidaDTO;
import com.adapit.portal.services.remote.RemotePreferenciaService;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class CategoriasList extends JXTable implements PropertyChangeListener{		
	private long idPessoa;
	
	@SuppressWarnings("deprecation")
	public CategoriasList() {
		super();
		this.setModel(new CategoriasModel(null));
		addPropertyChangeListener(this);
		getColumnModel().getColumn(0).setPreferredWidth(200);

		Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
		setHighlighters(highlighters);
	}

	
	public void updateTable(Long id) {
		if (id != null)
			idPessoa = id;
		//Session s = null;
		try{
			
			//s = LocalServicesUtility.getInstance().openSession();
			List<CategoriaPreferidaDTO> list = null;
			
			/*list = s.getNamedQuery("participante.preferencias")
			.setParameter("id", pessoaEmDivulgacao.getId())
			.list();*/
			list = RemotePreferenciaService.getInstance()
				.listCategoriasPreferediasByPessoa(idPessoa);//CategoriasPreferediasByIdPessoa(pessoaEmDivulgacao.getId());
			
			if (list != null && list.size() > 0) {
				int i=0;
				int size = list.size();
				size = CategoriaPreferidaDTO.getChildrenSize(list, size);
				System.out.println("Número de cat " + size);
				java.lang.Object values[][] = CategoriaPreferidaDTO.getChildrenCategoryValues(list,"");
				/*for(CategoriaPreferidaDTO cat: list){
					String str = "";
					for (int o=0; o < cat.getOrdem(); o++) str+=""+'\t';
					values[i][0] = str+cat.getNomeCategoria();
					values[i][1] = cat.isSelected();
					i++;
				}*/
				if (values != null && values.length>0)
					setModel(new CategoriasModel(values));
				else setModel(new CategoriasModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				getColumnModel().getColumn(1).setPreferredWidth(20);
				getColumnModel().getColumn(2).setPreferredWidth(20);
				updateUI();
								
			} else {
				setModel(new CategoriasModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				getColumnModel().getColumn(1).setPreferredWidth(20);
				getColumnModel().getColumn(2).setPreferredWidth(20);
				updateUI();
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}/*finally{
			if (s != null) s.close();
		}*/
	}
	
	

	private class CategoriasModel extends DefaultTableModel {
		

		Class types[] = new java.lang.Class[] { String.class, Boolean.class, String.class};

		boolean canEdit[] = new boolean[] {false, false };

		public CategoriasModel(Object[][] values) {
			super(values,new String[] {"Categoria","Selecionada","Ordem"});
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
				
				/*RemotePreferenciaService.getInstance()
					.updatePreferenciaByPropertyName(
							propName, getPreferenciasList().pref.getId(), value);*/
				updateTable(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	public long getIdPessoa() {
		return idPessoa;
	}


	public void setIdPessoa(long idPessoa) {
		this.idPessoa = idPessoa;
	}


}
