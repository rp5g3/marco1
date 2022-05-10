package com.adapit.portal.ui.forms.destaque;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.MessageFeedbackCounter;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.workcase.utils.DatePropertyEditor;

public class MsgFeedTable  extends JXTable{		
	private List<MessageFeedbackCounter> elements;
	
	public List<MessageFeedbackCounter> getElements() {
		return elements;
	}

	public void setElements(List<MessageFeedbackCounter> elements) {
		this.elements = elements;
	}

	@SuppressWarnings("deprecation")
	public MsgFeedTable() {
		super();
		this.setModel(new PreferenciaModel(null));
		//getColumnModel().getColumn(0).setPreferredWidth(200);
		//getColumnModel().getColumn(1).setPreferredWidth(60);
		Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
		setHighlighters(highlighters);
		updateTable();			
	}

	DatePropertyEditor dt = new DatePropertyEditor();
	public void updateTable() {
		try{
			java.lang.Object values[][] = null;
			
			if(elements != null && elements.size()>0){
				values=new Object[elements.size()][7];
				int i=0;
				for(MessageFeedbackCounter msg: elements){
					dt.setValue(msg.getDataAcesso());
					values[i][0]=dt.getAsText() ;
					values[i][1]=msg.getFeedbackType().name();
					values[i][2]=msg.getIdTarget();
					long id = msg.getParticipant_id();
					Participante p = RemotePessoaService.getInstance().getParticipante(id);
					values[i][3]=p.getNomeFormatado();
					values[i][4]=msg.getTarget();
					i++;
				}
			}
			
			
			setModel(new PreferenciaModel(values));
			//getColumnModel().getColumn(0).setPreferredWidth(200);
			//getColumnModel().getColumn(1).setPreferredWidth(60);
			updateUI();	
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private class PreferenciaModel extends DefaultTableModel {
		

		Class types[] = new java.lang.Class[] { String.class,String.class,String.class,String.class,String.class };

		boolean canEdit[] = new boolean[] {false, false, false, false, false };

		public PreferenciaModel(Object[][] values) {
			super(values,new String[] {"Data Acesso","Tipo do Alvo","Id Alvo","Participante","Alvo"});
		}
		public Class getColumnClass(int columnIndex) {

			return types[columnIndex];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {

			return canEdit[columnIndex];
		}
	}
	
}