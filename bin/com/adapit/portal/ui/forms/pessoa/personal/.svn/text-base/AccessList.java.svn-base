package com.adapit.portal.ui.forms.pessoa.personal;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Access;
import com.adapit.portal.services.remote.RemoteUserService;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class AccessList extends JXTable {		
	private String idUser;	
	protected ResourceMessage messages = SpringResourceMessage.getInstance();
	protected DateHourChooser tempDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
	
	private List<Access> elements = new ArrayList();
	@SuppressWarnings("deprecation")
	public AccessList() {
		super();
		this.setModel(new AccessModel(null));
		getColumnModel().getColumn(0).setPreferredWidth(60);
		getColumnModel().getColumn(1).setPreferredWidth(200);
		Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
		setHighlighters(highlighters);
		//updateTable();			
	}

	
	public void updateTable(String login) {
		if (login!= null)
			idUser = login;
		//Session s = null;
		try{
			elements.clear();
			/*s = LocalServicesUtility.getInstance().openSession();
			List list = s.getNamedQuery("access.listAllByLogin")
			.setParameter("userlogin", getAdminUserDataForm().getUsuario().getLogin())
			.list();*/
			List list = RemoteUserService.getInstance()
				.listAccessByUsuarioLogin(idUser);
			
			if (list != null && list.size() > 0) {
				int ne = list.size();
				java.util.Iterator<Access> it = list.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][2];
				int i = 0;
				
				while (it.hasNext()) {
					Access a = (Access) it.next();
					values[i][0] = a.getId();
					tempDateFieldChooser.setDate(a.getDataHora());
					values[i][1] = tempDateFieldChooser.getDateHourField().getText();
											
					elements.add(a);
					
					i++;

				}
				if (values != null && values.length>0)
					setModel(new AccessModel(values));
				else setModel(new AccessModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(60);
				getColumnModel().getColumn(1).setPreferredWidth(200);
				updateUI();
			} else {
				setModel(new AccessModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(60);
				getColumnModel().getColumn(1).setPreferredWidth(200);
				updateUI();
			}
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}/*finally{
			if (s != null) s.close();
		}*/
	}

	private class AccessModel extends DefaultTableModel {
		

		Class types[] = new java.lang.Class[] { String.class,String.class };

		boolean canEdit[] = new boolean[] {false, false };

		public AccessModel(Object[][] values) {
			super(values,new String[] {"Identificador","Dia e hora do acesso"});
		}
		public Class getColumnClass(int columnIndex) {

			return types[columnIndex];
		}

		public boolean isCellEditable(int rowIndex, int columnIndex) {

			return canEdit[columnIndex];
		}

	}

	public List<Access> getElements() {
		return elements;
	}


	public void setElements(List<Access> elements) {
		this.elements = elements;
	}
}
