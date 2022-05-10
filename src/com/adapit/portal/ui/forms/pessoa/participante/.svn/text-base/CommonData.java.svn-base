package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemotePreferenciaService;
import com.adapit.portal.ui.forms.pessoa.PreferenciaUsuario;
import com.adapit.portal.ui.forms.pessoa.personal.AccessList;
import com.adapit.portal.ui.forms.pessoa.personal.AttributesList;
import com.adapit.portal.ui.forms.pessoa.personal.CategoriasList;
import com.adapit.portal.ui.forms.pessoa.personal.PreferenciasList;

public class CommonData {
	
	private JTabbedPane tabbedPane;
	private Participante comitente;
	private Usuario usuario;
	
	public CommonData(JTabbedPane jtb, Participante comitente, Usuario usuario){
		this.tabbedPane = jtb;
		this.comitente = comitente;
		this.usuario = usuario;
	}

	private JPanel gridLancesAcessosPanel = null;
	
	public JPanel getGridLancesAcessosPanel() {
		if (gridLancesAcessosPanel == null) {
			gridLancesAcessosPanel = new JPanel();
			gridLancesAcessosPanel.setLayout(new GridLayout(1,2,5,5));
			gridLancesAcessosPanel.add(getAcessosPanel());
			gridLancesAcessosPanel.add(getLancesPanel());
		}
		return gridLancesAcessosPanel;
	}
	
	private JPanel acessosPanel = null;
	
	public JPanel getAcessosPanel() {
		if (acessosPanel == null) {
			acessosPanel = new JPanel();
			acessosPanel.setLayout(new BorderLayout());
			JLabel acLabel = new JLabel("Lista dos Acessos");
			acLabel.setHorizontalTextPosition(JLabel.CENTER);
			acessosPanel.add(acLabel,BorderLayout.NORTH);
			acessosPanel.add(getAccessScrollPane(),BorderLayout.CENTER);
			acessosPanel.add(getAcessosButtonsPanel(),BorderLayout.SOUTH);
		}
		return acessosPanel;
	}
	
	private JPanel lancesPanel = null;
	
	public JPanel getLancesPanel() {
		if (lancesPanel == null) {
			lancesPanel = new JPanel();
			lancesPanel.setLayout(new BorderLayout());
			JLabel acLabel = new JLabel("Lista dos Atributos");
			acLabel.setHorizontalTextPosition(JLabel.CENTER);
			lancesPanel.add(acLabel,BorderLayout.NORTH);
			lancesPanel.add(getAttributesScrollPane(),BorderLayout.CENTER);
			lancesPanel.add(getAccessButtonsPanel(),BorderLayout.SOUTH);
		}
		return lancesPanel;
	}
	
	
	
	private JPanel acessosButtonsPanel;
	
	public JPanel getAcessosButtonsPanel(){
		if (acessosButtonsPanel == null){
			acessosButtonsPanel = new JPanel();
			acessosButtonsPanel.setLayout(new FlowLayout());
			JButton listarAcessosButton = new JButton("Listar");
			listarAcessosButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					getAccessList().updateTable(usuario.getLogin());
				}				
			});
			acessosButtonsPanel.add(listarAcessosButton);
			
		}
		return acessosButtonsPanel;
	}
	
	private JPanel accessButtonsPanel;
	
	public JPanel getAccessButtonsPanel(){
		if (accessButtonsPanel == null){
			accessButtonsPanel = new JPanel();
			accessButtonsPanel.setLayout(new FlowLayout());
			JButton listarAcessosButton = new JButton("Listar");
			listarAcessosButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					getAttributesList().updateTable(comitente.getId());
				}				
			});
			accessButtonsPanel.add(listarAcessosButton);
			
		}
		return accessButtonsPanel;
	}

	
	private JScrollPane attributesScrollPane;
	public JScrollPane getAttributesScrollPane() {
		if (attributesScrollPane == null) {
			attributesScrollPane = new JScrollPane();
			attributesScrollPane.setBounds(new Rectangle(15, 20, 520, 170));
			attributesScrollPane.setViewportView(getAttributesList());
		}
		return attributesScrollPane;
	}
	
	private JScrollPane accessScrollPane;
	public JScrollPane getAccessScrollPane() {
		if (accessScrollPane == null) {
			accessScrollPane = new JScrollPane();
			accessScrollPane.setBounds(new Rectangle(15, 20, 520, 170));
			accessScrollPane.setViewportView(getAccessList());
		}
		return accessScrollPane;
	}
	
	private AccessList accessList;
	
	public AccessList getAccessList(){
		if (accessList == null){
			accessList = new AccessList();
		}
		return accessList;
	}
	
	private AttributesList attributesList;
	
	public AttributesList getAttributesList(){
		if (attributesList == null){
			attributesList = new AttributesList();
		}
		return attributesList;
	}
	
	
	

	
	
	private JPanel gridCategoriasPanel = null;
	
	public JPanel getGridCategoriasPanel() {
		if (gridCategoriasPanel == null) {
			gridCategoriasPanel = new JPanel();
			gridCategoriasPanel.setLayout(new GridLayout(1,2,5,5));
			gridCategoriasPanel.add(getPreferenciasPanel());
			gridCategoriasPanel.add(getCategoriasPanel());
		}
		return gridCategoriasPanel;
	}
	
	private JPanel preferenciasPanel = null;
	
	public JPanel getPreferenciasPanel() {
		if (preferenciasPanel == null) {
			preferenciasPanel = new JPanel();
			preferenciasPanel.setLayout(new BorderLayout());
			JLabel acLabel = new JLabel("Lista das Preferências");
			acLabel.setHorizontalTextPosition(JLabel.CENTER);
			preferenciasPanel.add(acLabel,BorderLayout.NORTH);
			JPanel jp = new JPanel();
			jp.setLayout(new GridLayout(2,1));
			jp.add(getPreferenciasScrollPane());
			jp.add(getPreferenciaDescricaoPanel());
			preferenciasPanel.add(jp,BorderLayout.CENTER);
			jp = new JPanel(new FlowLayout());
			JButton jb = new JButton("Trocar Seleção");
			jb.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						int row = getPreferenciasList().getSelectedRow();
						if (row < 0) return;
						Boolean value = (Boolean) getPreferenciasList().getValueAt(row, 1);
						value = !value.booleanValue();
						String propName = (String) getPreferenciasList().getValueAt(row, 0);
						
						System.out.println("propName " + propName + " value " + value);
					
						for(int i=0; i < PreferenciaUsuario.titles.length; i++){
							String val = PreferenciaUsuario.titles[i];
							if(val.equals(propName)){
								propName = PreferenciaUsuario.properties[i];
								break;
							}
						}
						System.out.println("propName " + propName + " value " + value);
						getPreferenciasList().pref = RemotePreferenciaService.getInstance()
							.updatePreferenciaByPropertyName(propName, getPreferenciasList().pref.getId(), value);
						getPreferenciasList().updateTable();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
			jp.add(jb);
			preferenciasPanel.add(jp,BorderLayout.NORTH);
		}
		return preferenciasPanel;
	}
	
	private JPanel categoriasPanel = null;
	
	public JPanel getCategoriasPanel() {
		if (categoriasPanel == null) {
			categoriasPanel = new JPanel();
			categoriasPanel.setLayout(new BorderLayout());
			JLabel acLabel = new JLabel("Lista das Categorias Preferidas do Usuário");
			acLabel.setHorizontalTextPosition(JLabel.CENTER);
			categoriasPanel.add(acLabel,BorderLayout.NORTH);
			categoriasPanel.add(getCategoriasScrollPane(),BorderLayout.CENTER);
			categoriasPanel.add(getCategoriasButtonsPanel(),BorderLayout.SOUTH);
		}
		return categoriasPanel;
	}
	
	
	
	private JPanel preferenciaDescricaoPanel;
	

	
	public JPanel getPreferenciaDescricaoPanel(){
		if (preferenciaDescricaoPanel == null){
			preferenciaDescricaoPanel = new JPanel();
			preferenciaDescricaoPanel.setLayout(new BorderLayout());
			preferenciaDescricaoPanel.add(new JLabel("Especificação textual para o item diversos"),BorderLayout.NORTH);
			preferenciaDescricaoPanel.add(new JScrollPane(getPreferenciasList().getOutrasCategoriasTextField()),BorderLayout.CENTER);
		}
		return preferenciaDescricaoPanel;
	}
	
	private JPanel categoriasButtonsPanel;
	
	public JPanel getCategoriasButtonsPanel(){
		if (categoriasButtonsPanel == null){
			categoriasButtonsPanel = new JPanel();
			categoriasButtonsPanel.setLayout(new FlowLayout());
			JButton listarButton = new JButton("Listar");
			listarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					getCategoriasList().updateTable(comitente.getId());
				}				
			});
			categoriasButtonsPanel.add(listarButton);			
			
		}
		return categoriasButtonsPanel;
	}

	/**
	 * This method initializes apresentacaoScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane categoriasScrollPane;
	public JScrollPane getCategoriasScrollPane() {
		if (categoriasScrollPane == null) {
			categoriasScrollPane = new JScrollPane();
			categoriasScrollPane.setBounds(new Rectangle(15, 20, 520, 170));
			categoriasScrollPane.setViewportView(getCategoriasList());
		}
		return categoriasScrollPane;
	}
	

	private JScrollPane preferenciasScrollPane = null;
	public JScrollPane getPreferenciasScrollPane() {
		if (preferenciasScrollPane == null) {
			preferenciasScrollPane = new JScrollPane();
			preferenciasScrollPane.setBounds(new Rectangle(15, 20, 520, 170));
			preferenciasScrollPane.setViewportView(getPreferenciasList());
		}
		return preferenciasScrollPane;
	}
	
	protected PreferenciasList preferenciasList;
	
	protected PreferenciasList getPreferenciasList(){
		if (preferenciasList == null){
			preferenciasList = new PreferenciasList();
		}
		return preferenciasList;
	}
	
	protected CategoriasList categoriasList;
	
	protected CategoriasList getCategoriasList(){
		if (categoriasList == null){
			categoriasList = new CategoriasList();
		}
		return categoriasList;
	}

	public void removeTabs() {
		if (usuario != null && usuario.getLogin() != null) try {
			tabbedPane.remove(getGridCategoriasPanel());
			tabbedPane.remove(getGridLancesAcessosPanel());
			getPreferenciasList().pref = 
					RemotePreferenciaService.getInstance().createPreferencia(true, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addTabs() {
		if (usuario != null && usuario.getLogin() != null) try {
			tabbedPane.add(getGridCategoriasPanel(),"Preferências e Categorias");
			tabbedPane.add(getGridLancesAcessosPanel(),"Acessos e Atributos");
			//getPreferenciasList().createPreferencia(false);
			getPreferenciasList().pref = 
				RemotePreferenciaService.getInstance().createPreferencia(false, usuario);
			getPreferenciasList().updateTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attributesList.setIdPessoa(usuario.getDadosPessoais().getId());
	}
	
	public void addTabs(Pessoa p) {
		if (usuario != null && usuario.getLogin() != null) try {
			tabbedPane.add(getGridCategoriasPanel(),"Preferências e Categorias");
			tabbedPane.add(getGridLancesAcessosPanel(),"Acessos e Atributos");
			//getPreferenciasList().createPreferencia(false);
			getPreferenciasList().pref = 
				RemotePreferenciaService.getInstance().createPreferencia(false, usuario);
			getPreferenciasList().updateTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		attributesList.setIdPessoa(p.getId());
	}

}
