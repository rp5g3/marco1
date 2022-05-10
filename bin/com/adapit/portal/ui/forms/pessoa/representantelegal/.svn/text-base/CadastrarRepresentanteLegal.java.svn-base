package com.adapit.portal.ui.forms.pessoa.representantelegal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Access;
import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.PreferenciaCategoria;
import com.adapit.portal.entidades.RepresentanteLegal;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteAdapitAutenticateUserService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemotePreferenciaService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.ui.forms.pessoa.PreferenciaUsuario;
import com.adapit.portal.ui.forms.pessoa.participante.CadastrarParticipanteForm;
import com.adapit.portal.ui.forms.usuario.AdminEnderecoCadastreForm;
import com.adapit.portal.ui.forms.usuario.AdminUserDataForm;
import com.adapit.portal.ui.forms.usuario.DesativarUsuarioDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class CadastrarRepresentanteLegal extends JPanel{

	private RepresentanteLegalCadastreForm pessoaFisicaCadastreForm;
	
	private RepresentanteLegal representante = new RepresentanteLegal();  //  @jve:decl-index=0:
	
	private JTabbedPane tabbedPane;
	
	private JPanel userDataPanel;
	
	private AdminUserDataForm adminUserDataForm;
	
	private JPanel dadosPessoaisPanel;
			
	protected SwingBinder binder = new SwingBinder();
	
	@SuppressWarnings("unchecked")
	protected Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	
	protected ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private AdminEnderecoCadastreForm adminEnderecoCadastreForm;
	
	private JPanel cadastreButtonsPanel;
	
	private JButton insertButton;
	
	private JButton newButton;
	
	//private JButton deleteButton;
	
	private JButton listarUsuariosButton;
	
	public CadastrarRepresentanteLegal(){	
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(564, 404));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getTabbedPane());
		add(getCadastreButtonsPanel());
	}
	
	protected JTabbedPane getTabbedPane(){
		if(tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(559, 340));
			tabbedPane.setLocation(new java.awt.Point(3,3));
			tabbedPane.add(getUserDataPanel(),"Dados de Usuário");
			tabbedPane.add(getDadosPessoaisPanel(),"Dados Pessoais");
			tabbedPane.add(getDadosComitentePanel(),"Fotografia");
			tabbedPane.add(getAdminEnderecoCadastreForm(),"Endereço");
		}
		return tabbedPane;
	}
	
	protected JPanel getDadosPessoaisPanel(){

		if(dadosPessoaisPanel == null){
			dadosPessoaisPanel = new JPanel();
			dadosPessoaisPanel.setSize(new java.awt.Dimension(307,192));
			dadosPessoaisPanel.setLocation(new java.awt.Point(0,230));
			dadosPessoaisPanel.setLayout(null);
			dadosPessoaisPanel.add(getPessoaFisicaCadastreForm());		
		}
		return dadosPessoaisPanel;
	}
	
	protected RepresentanteLegalCadastreForm getPessoaFisicaCadastreForm(){
		if(pessoaFisicaCadastreForm == null){
			pessoaFisicaCadastreForm = new RepresentanteLegalCadastreForm();
			pessoaFisicaCadastreForm.setLayout(null);
			pessoaFisicaCadastreForm.setBounds(new Rectangle(15, 20, 531, 274));
		}
		return pessoaFisicaCadastreForm;
	}


	public void setRepresentante(RepresentanteLegal u) {
		representante = u;		
	}


	
	public RepresentanteLegal getRepresentanteLegal(){
		if (representante == null){
			representante = (RepresentanteLegal) getPessoaFisicaCadastreForm().getRepresentante();
			
		}
		return representante;
	}

	
	private JPanel gridLancesAcessosPanel = null;
	
	protected JPanel getGridLancesAcessosPanel() {
		if (gridLancesAcessosPanel == null) {
			gridLancesAcessosPanel = new JPanel();
			gridLancesAcessosPanel.setLayout(new GridLayout(1,1,5,5));
			gridLancesAcessosPanel.add(getAcessosPanel());
		}
		return gridLancesAcessosPanel;
	}
	
	private JPanel acessosPanel = null;
	
	protected JPanel getAcessosPanel() {
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

	
	private JPanel acessosButtonsPanel;
	
	private JPanel getAcessosButtonsPanel(){
		if (acessosButtonsPanel == null){
			acessosButtonsPanel = new JPanel();
			acessosButtonsPanel.setLayout(new FlowLayout());
			JButton listarAcessosButton = new JButton("Listar");
			listarAcessosButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					getAccessList().updateTable();
				}				
			});
			acessosButtonsPanel.add(listarAcessosButton);
			
		}
		return acessosButtonsPanel;
	}
	

	
	private JScrollPane accessScrollPane;
	private JScrollPane getAccessScrollPane() {
		if (accessScrollPane == null) {
			accessScrollPane = new JScrollPane();
			accessScrollPane.setBounds(new Rectangle(15, 20, 520, 170));
			accessScrollPane.setViewportView(getAccessList());
		}
		return accessScrollPane;
	}
	
	private AccessList accessList;
	
	private AccessList getAccessList(){
		if (accessList == null){
			accessList = new AccessList();
		}
		return accessList;
	}

	
	@SuppressWarnings("unchecked")
	private class AccessList extends JXTable {		
		
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

		
		public void updateTable() {
			//Session s = null;
			try{
				elements.clear();
				/*s = LocalServicesUtility.getInstance().openSession();
				List list = s.getNamedQuery("access.listAllByLogin")
				.setParameter("userlogin", getAdminUserDataForm().getUsuario().getLogin())
				.list();*/
				List list = RemoteUserService.getInstance()
					.listAccessByUsuarioLogin(getAdminUserDataForm().getUsuario().getLogin());
				
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
					setModel(new AccessModel(values));
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
	
	
	
	
	private JPanel gridCategoriasPanel = null;
	
	protected JPanel getGridCategoriasPanel() {
		if (gridCategoriasPanel == null) {
			gridCategoriasPanel = new JPanel();
			gridCategoriasPanel.setLayout(new GridLayout(1,2,5,5));
			gridCategoriasPanel.add(getPreferenciasPanel());
			gridCategoriasPanel.add(getCategoriasPanel());
		}
		return gridCategoriasPanel;
	}
	
	private JPanel preferenciasPanel = null;
	
	protected JPanel getPreferenciasPanel() {
		if (preferenciasPanel == null) {
			preferenciasPanel = new JPanel();
			preferenciasPanel.setLayout(new BorderLayout());
			JLabel acLabel = new JLabel("Lista das Preferências");
			acLabel.setHorizontalTextPosition(JLabel.CENTER);
			preferenciasPanel.add(acLabel,BorderLayout.NORTH);
			preferenciasPanel.add(getPreferenciasScrollPane(),BorderLayout.CENTER);
			preferenciasPanel.add(getPreferenciaDescricaoPanel(),BorderLayout.SOUTH);
		}
		return preferenciasPanel;
	}
	
	private JPanel categoriasPanel = null;
	
	protected JPanel getCategoriasPanel() {
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
	
	private JTextField outrasCategoriasTextField;
	
	private JPanel getPreferenciaDescricaoPanel(){
		if (preferenciaDescricaoPanel == null){
			preferenciaDescricaoPanel = new JPanel();
			preferenciaDescricaoPanel.setLayout(new GridLayout(1,1));			
			outrasCategoriasTextField = new JTextField();
			outrasCategoriasTextField.setToolTipText("Tecle ENTER para gravar os dados");
			outrasCategoriasTextField.addKeyListener(new KeyAdapter(){
				@Override
				public void keyReleased(KeyEvent evt) {
					String value = outrasCategoriasTextField.getText();
					try {
						RemotePreferenciaService.getInstance()
							.updateOutrasCategoriasByPreferenciaId(
									getPreferenciasList().pref.getId(), value);
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*Session s = null;
					if (getPreferenciasList().pref == null) return;
					try {						
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						s.getNamedQuery("preferencia.updatePreferencias")
						.setParameter("value",value)
						.setParameter("id",getPreferenciasList().pref.getId()).executeUpdate();
						s.getTransaction().commit();						
					} catch (Exception e) {
						e.printStackTrace();
						s.getTransaction().rollback();
						
					}finally{
						if (s !=  null) s.close();
					}*/
				}
				
			});
			outrasCategoriasTextField.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					String value = outrasCategoriasTextField.getText();
					try {
						RemotePreferenciaService.getInstance()
						.updateOutrasCategoriasByPreferenciaId(
								getPreferenciasList().pref.getId(), value);
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*Session s = null;
					if (getPreferenciasList().pref == null) return;
					try {						
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						s.getNamedQuery("preferencia.updatePreferencias")
						.setParameter("value",value)
						.setParameter("id",getPreferenciasList().pref.getId()).executeUpdate();
						s.getTransaction().commit();						
					} catch (Exception e) {
						e.printStackTrace();
						s.getTransaction().rollback();
						
					}finally{
						if (s !=  null) s.close();
					}*/
				}				
			});
			//preferenciaDescricaoPanel.add(prefLabel);
			preferenciaDescricaoPanel.add(outrasCategoriasTextField);
		}
		return preferenciaDescricaoPanel;
	}
	
	private JPanel categoriasButtonsPanel;
	
	private JPanel getCategoriasButtonsPanel(){
		if (categoriasButtonsPanel == null){
			categoriasButtonsPanel = new JPanel();
			categoriasButtonsPanel.setLayout(new FlowLayout());
			JButton listarButton = new JButton("Listar");
			listarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					getCategoriasList().updateTable();
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
	protected JScrollPane getCategoriasScrollPane() {
		if (categoriasScrollPane == null) {
			categoriasScrollPane = new JScrollPane();
			categoriasScrollPane.setBounds(new Rectangle(15, 20, 520, 170));
			categoriasScrollPane.setViewportView(getCategoriasList());
		}
		return categoriasScrollPane;
	}
	
	protected JScrollPane getPreferenciasScrollPane() {
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
	
	
	protected DateHourChooser tempDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
	
	@SuppressWarnings("unchecked")
	protected class PreferenciasList extends JXTable implements PropertyChangeListener{		
		public PreferenciaCategoria pref=null;
		
		@SuppressWarnings("deprecation")
		public PreferenciasList() {
			super();
			this.setModel(new PreferenciaModel(null));
			addPropertyChangeListener(this);
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
				else outrasCategoriasTextField.setText("");
				setModel(new PreferenciaModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				getColumnModel().getColumn(1).setPreferredWidth(60);
				updateUI();
							
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		/*public void createPreferencia(boolean b){
			Session s = null;
			if (usuario == null) return;
			try{
				s = LocalServicesUtility.getInstance().openSession();
				if (b){
					pref = new PreferenciaCategoria();

					s.beginTransaction();
					s.save(pref);
					Usuario user = (Usuario) s.load(Usuario.class,usuario.getLogin());
					pref.setUsuario(user);
					s.merge(pref);
					s.getTransaction().commit();
					
				}else{
					pref = (PreferenciaCategoria) s.getNamedQuery("preferencia.getByLogin")
					.setParameter("userlogin", usuario.getLogin())
					.uniqueResult();
					if (pref == null){
						pref = new PreferenciaCategoria();		
						
						s.beginTransaction();
						s.save(pref);
						Usuario user = (Usuario) s.load(Usuario.class,usuario.getLogin());
						pref.setUsuario(user);
						s.merge(pref);
						s.getTransaction().commit();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (s.getTransaction().isActive()) s.getTransaction().rollback();
			}finally{
				if (s != null) s.close();
			}
		}
*/
		private class PreferenciaModel extends DefaultTableModel {
			

			Class types[] = new java.lang.Class[] { String.class,Boolean.class };

			boolean canEdit[] = new boolean[] {false, true };

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

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (evt.getPropertyName().equals("tableCellEditor")){
				int row = getSelectedRow();
				if (row < 0) return;
				Boolean value = (Boolean) getValueAt(row, 1);
				String propName = (String) getValueAt(row, 0);
				
				try {
					for(int i=0; i < PreferenciaUsuario.titles.length; i++){
						String val = PreferenciaUsuario.titles[i];
						if(val.equals(propName)){
							propName = PreferenciaUsuario.properties[i];
							break;
						}
					}
					RemotePreferenciaService.getInstance()
						.updatePreferenciaByPropertyName(
								propName, getPreferenciasList().pref.getId(), value);
				} catch (Exception e) {
					e.printStackTrace();
				}				
			}
		}
	}
	
	
	private class CategoriasList extends JXTable implements PropertyChangeListener{		
		
		
		@SuppressWarnings("deprecation")
		public CategoriasList() {
			super();
			this.setModel(new CategoriasModel(null));
			addPropertyChangeListener(this);
			getColumnModel().getColumn(0).setPreferredWidth(200);
	
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		
		public void updateTable() {
			//Session s = null;
			try{
				
				//s = LocalServicesUtility.getInstance().openSession();
				List<Categoria> list = null;
				
				/*list = s.getNamedQuery("participante.preferencias")
				.setParameter("id", pessoaEmDivulgacao.getId())
				.list();*/
				list = RemotePreferenciaService.getInstance()
					.listCategoriasPreferediasByIdPessoa(getRepresentanteLegal().getId());
				
				if (list != null && list.size() > 0) {
					int i=0;
					java.lang.Object values[][] = new java.lang.Object[list.size()][1];
					for(Categoria str: list){
						values[i][0] = str.getNome();					
						i++;
					}
					
					setModel(new CategoriasModel(values));
					getColumnModel().getColumn(0).setPreferredWidth(200);
					updateUI();
									
				} else {
					setModel(new CategoriasModel(null));
					getColumnModel().getColumn(0).setPreferredWidth(200);
					updateUI();
				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}/*finally{
				if (s != null) s.close();
			}*/
		}

		private class CategoriasModel extends DefaultTableModel {
			

			Class types[] = new java.lang.Class[] { String.class};

			boolean canEdit[] = new boolean[] {false };

			public CategoriasModel(Object[][] values) {
				super(values,new String[] {"Categoria"});
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
			System.out.println(evt.getPropertyName());
		}

	
	}
	
	protected JPanel getUserDataPanel(){

		if(userDataPanel == null){
			userDataPanel = new JPanel();
			//userDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Dados de Usuário")));
			userDataPanel.setSize(new java.awt.Dimension(350,230));
			userDataPanel.setLocation(new java.awt.Point(0,0));
			userDataPanel.setLayout(/*new BorderLayout()*/null);
			userDataPanel.add(getAdminUserDataForm()/*,BorderLayout.CENTER*/);
		}
		return userDataPanel;
	}
	
	protected AdminUserDataForm getAdminUserDataForm(){

		if(adminUserDataForm == null){
			adminUserDataForm = new AdminUserDataForm(null);
			adminUserDataForm.setLayout(null);
			adminUserDataForm.setBounds(new Rectangle(15, 0, 520, 296));
			((JComboBox)adminUserDataForm.getRoleComboBox()).setSelectedItem(UserCadastreType.Cliente.name().replace("_"," "));
			((JComboBox)adminUserDataForm.getRoleComboBox()).setEnabled(false);
			adminUserDataForm.add(getInativoRadioButton(), null);
			adminUserDataForm.add(getAtivoRadioButton(), null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAtivoRadioButton());
			bg.add(getInativoRadioButton());
		}
		return adminUserDataForm;
	}
	
		
	protected AdminEnderecoCadastreForm getAdminEnderecoCadastreForm(){
		if(adminEnderecoCadastreForm == null){
			adminEnderecoCadastreForm = new AdminEnderecoCadastreForm();
			adminEnderecoCadastreForm.setSize(new java.awt.Dimension(150,20));
			adminEnderecoCadastreForm.setLocation(new java.awt.Point(15,422));
			adminEnderecoCadastreForm.getEnderecoPanel().setLocation(120,(int) adminEnderecoCadastreForm.getEnderecoPanel().getLocation().getY());
			adminEnderecoCadastreForm.setLayout(null);
		}
		return adminEnderecoCadastreForm;
	}
	
	protected JPanel getCadastreButtonsPanel(){

		if(cadastreButtonsPanel == null){
			cadastreButtonsPanel = new JPanel();
			cadastreButtonsPanel.setSize(new Dimension(559, 52));
			cadastreButtonsPanel.setLocation(new Point(2, 349));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.add(getInsertButton());
			cadastreButtonsPanel.add(getNewButton());
			//cadastreButtonsPanel.add(getDeleteButton());
			cadastreButtonsPanel.add(getListarUsuariosButton());
			cadastreButtonsPanel.add(getDeactivateButton());
		}
		return cadastreButtonsPanel;
	}
	
	private JButton deactivateButton;
	@SuppressWarnings("unchecked")
	protected JButton getDeactivateButton(){

		if(deactivateButton == null){
			deactivateButton = new JButton("Desativar");
			deactivateButton.setSize(new java.awt.Dimension(80,22));
			deactivateButton.setLocation(new java.awt.Point(0,0));
			deactivateButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_deactive.png",18,18));
			
			//deactivateButton.setEnabled(false);
			deactivateButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					DesativarUsuarioDialog d = getDesativarUsuarioDialog();
					//Session s = LocalServicesUtility.getInstance().openSession();
					
					List<DeactivationReason> list = null;
					try{
						/*list = s.getNamedQuery("usuario.listDeactivationByLogin")
							.setParameter("userlogin", usuario.getLogin())
							.list();*/
						list = RemoteUserService.getInstance()
							.listDeactivationReasonByUserLogin(usuario.getLogin());
						usuario.setDeactivationReasonList(list);
					}catch(Exception ex){
						ex.printStackTrace();
					}/*finally{
						if (s != null) s.close();
					}*/
					
					if (usuario != null && usuario.getDeactivationReasonList() != null && usuario.getDeactivationReasonList().size()>0){
						if (deactivateButton.getText().equals("Desativar")){
							try {
								d.getDeactivationReasonCadastreForm().newRegister(usuario);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						else d.getDeactivationReasonCadastreForm().editRegister(usuario);
					}
					else if (usuario != null){
						if (deactivateButton.getText().equals("Desativar")){
							try {
								d.getDeactivationReasonCadastreForm().newRegister(usuario);
							} catch (Exception e) {
								e.printStackTrace();
							}						
						}else d.getDeactivationReasonCadastreForm().editRegister(usuario);
					}					
					d.setVisible(true);					
				}
				
			});
		}
		return deactivateButton;
	}
	
	protected DesativarUsuarioDialog desativarUsuarioDialog;
	
	protected DesativarUsuarioDialog getDesativarUsuarioDialog(){
		if (desativarUsuarioDialog == null){
			desativarUsuarioDialog = new DesativarUsuarioDialog();
			desativarUsuarioDialog.getDeactivationReasonCadastreForm().getConfirmarButton().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					usuario.setActive(desativarUsuarioDialog.getDeactivationReasonCadastreForm().getUsuario().getActive());
					//editRegister();
					getDeactivateButton().setText("Ativar");
					getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_add.png",18,18));
				}				
			});
			desativarUsuarioDialog.getDeactivationReasonCadastreForm().getActivateButton().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					usuario.setActive(desativarUsuarioDialog.getDeactivationReasonCadastreForm().getUsuario().getActive());
					//editRegister();
					getDeactivateButton().setText("Desativar");
					getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_delete.png",18,18));
				}				
			});
			
		}
		return desativarUsuarioDialog;
	}
	
	protected JButton getInsertButton(){

		if(insertButton == null){
			insertButton = new JButton(messages.getMessage("Cadastrar"));
			insertButton.setSize(new java.awt.Dimension(80,22));
			insertButton.setLocation(new java.awt.Point(0,0));
			insertButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_save.png",18,18));
			insertButton.addActionListener(new CadastrarAction());
		}
		return insertButton;
	}
	
	protected JButton getNewButton(){

		if(newButton == null){
			newButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Novo"));
			newButton.setSize(new java.awt.Dimension(80,22));
			newButton.setLocation(new java.awt.Point(0,22));
			newButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_add.png",18,18));
			newButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){					
					getTabbedPane().setIconAt(2, null);
					getTabbedPane().setIconAt(1, null);
					getTabbedPane().setIconAt(0, null);
					newRegister();
				}
			});
		}
		return newButton;
	}
	
/*	protected JButton getDeleteButton(){

		if(deleteButton == null){
			deleteButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Apagar"));
			deleteButton.setSize(new java.awt.Dimension(80,22));
			deleteButton.setLocation(new java.awt.Point(0,44));
			deleteButton.setIcon(LeilaoVirtualFrame.getIcon("/imgs/user_delete.png",18,18));
			deleteButton.addActionListener(new RemoverAction());
		}
		return deleteButton;
	}*/
	
	protected JButton getListarUsuariosButton(){

		if(listarUsuariosButton == null){
			listarUsuariosButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Cancelar"));
			listarUsuariosButton.setSize(new java.awt.Dimension(80,22));
			listarUsuariosButton.setText("Listar Usuários");
			listarUsuariosButton.setLocation(new java.awt.Point(0,66));
			listarUsuariosButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_go.png",18,18));
			listarUsuariosButton.addActionListener(new ListarUsuariosAction());
		}
		return listarUsuariosButton;
	}
	
	private class ListarUsuariosAction extends AbstractAction{

		@Override
		protected void doAction(ActionEvent e) {
			AdapitVirtualFrame.getInstance().listarUsuarios();
		}
		
	}
	
	/**
	 * This method initializes ativoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getAtivoRadioButton() {
		if (ativoRadioButton == null) {
			ativoRadioButton = new JRadioButton();
			ativoRadioButton.setBounds(new Rectangle(300, 100, 208, 20));
			ativoRadioButton.setText("Setar como ativo");
			ativoRadioButton.setSelected(true);
		}
		return ativoRadioButton;
	}

	/**
	 * This method initializes inativoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getInativoRadioButton() {
		if (inativoRadioButton == null) {
			inativoRadioButton = new JRadioButton();
			inativoRadioButton.setBounds(new Rectangle(300, 123, 208, 20));
			inativoRadioButton.setText("Setar como inativo");
		}
		return inativoRadioButton;
	}

	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;
	
	/**
	 * This method initializes apresentacaoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	protected JPanel getDadosComitentePanel() {
		if (dadosComitentePanel == null) {
			/*tipoComitenteLabel = new JLabel();
			tipoComitenteLabel.setBounds(new Rectangle(12, 39, 59, 21));
			tipoComitenteLabel.setText("Tipo:");
			descricaoLabel = new JLabel();
			descricaoLabel.setBounds(new Rectangle(12, 13, 61, 17));
			descricaoLabel.setText("Descrição:");*/
			dadosComitentePanel = new JPanel();
			dadosComitentePanel.setLayout(null);
			/*dadosComitentePanel.add(descricaoLabel, null);
			dadosComitentePanel.add(getDescricaoComitenteTextField(), null);
			dadosComitentePanel.add(tipoComitenteLabel, null);
			dadosComitentePanel.add(getTipoComitenteComboBox(), null);*/
			dadosComitentePanel.add(getBuscarImagemButton(), null);
			dadosComitentePanel.add(getCaminhoImgTextFieldLabel(), null);
			dadosComitentePanel.add(getCaminhoImgTextField(), null);
			dadosComitentePanel.add(getImageMainPanel(), null);			
		}
		return dadosComitentePanel;
	}
	
	JPanel imgPanelImage;

	protected JPanel getImageMainPanel() {
		if (imgPanelImage == null) {
			imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory.createLineBorder(Color.blue));
			imgPanelImage.setBounds(new Rectangle(290, 2, 200, 200));
			imgPanelImage.add(getImgLabelImage(), BorderLayout.CENTER);
		}
		return imgPanelImage;
	}

	protected JButton getBuscarImagemButton() {
		if (buscarImagemButton == null) {
			buscarImagemButton = new JButton(messages.getMessage("Buscar"));
			buscarImagemButton = new JButton("Buscar Imagem");
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setBounds(new Rectangle(105, 89, 157, 65));		
			buscarImagemButton.setIcon(getIcon("/imgs/folder_picture.png"));
			buscarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					browseImage();
				}

			});
		}
		return buscarImagemButton;
	}

	protected JTextField getCaminhoImgTextField() {
		if (caminhoImgTextField == null) {
			caminhoImgTextField = new JTextField();
			caminhoImgTextField.setEditable(false);
			caminhoImgTextField.setBounds(new Rectangle(68, 204, 422, 20));
			caminhoImgTextField.setText("");
			return caminhoImgTextField;
		}
		return caminhoImgTextField;
	}

	protected JLabel getCaminhoImgTextFieldLabel() {
		if (caminhoImgTextFieldLabel == null) {
			caminhoImgTextFieldLabel = new JLabel(messages
					.getMessage("Caminho"));
			caminhoImgTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
			caminhoImgTextFieldLabel.setBounds(new Rectangle(10, 204, 55, 20));
		}
		return caminhoImgTextFieldLabel;
	}

	protected JLabel getImgLabelImage() {
		if (imgLabelImage == null) {
			imgLabelImage = new JLabel();
			imgLabelImage.setHorizontalTextPosition(JLabel.CENTER);
			imgLabelImage.setHorizontalAlignment(JLabel.CENTER);
		}
		return imgLabelImage;
	}

	private JFileChooser jfc;

	protected JFileChooser getJfc() {
		if (jfc == null) {
			jfc = new ImageFileChooser();
		}
		return jfc;
	}

	public void browseImage() {
		try {
			
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			this.getCaminhoImgTextField().setText(f.toURI().getPath());
			Imagem img = new Imagem();
			img.setFullImageBytes(f);
			getRepresentanteLegal().setLogotipo(img);
			updateImage();
			
		} catch (Exception e) {
			// 
			e.printStackTrace();
		}
	}

	private void updateImage() {
		if (getRepresentanteLegal().getLogotipo() != null)
			try {
				try {
					getRepresentanteLegal().getLogotipo().setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(getRepresentanteLegal().getLogotipo().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.getCaminhoImgTextField().setText(getRepresentanteLegal().getLogotipo().getPath());
				getImgLabelImage().setIcon(getRepresentanteLegal().getLogotipo().getSmallImageIcon(true));
				//getImageLabel().setIcon(pessoaEmDivulgacao.getSmallIcon(true));
				getImgLabelImage().updateUI();
			} catch (HeadlessException e) {
				e.printStackTrace();
			}
		else{
			getImgLabelImage().setIcon(null);
			//getImageLabel().setIcon(null);
		}		
	}

	private void updateImage(boolean b) {
		{
			try {
				if (getRepresentanteLegal().getLogotipo() != null) {
					this.getCaminhoImgTextField().setText(getRepresentanteLegal().getLogotipo().getPath());
					getImgLabelImage().setIcon(getRepresentanteLegal().getLogotipo().getSmallImageIcon(b));
					getImgLabelImage().updateUI();
					//getImageLabel().setIcon(pessoaEmDivulgacao.getSmallIcon(b));
				} else {
					this.getCaminhoImgTextField().setText("");
					getImgLabelImage().setIcon(getRepresentanteLegal().getLogotipo().getSmallImageIcon(true));
					getImgLabelImage().updateUI();
					//getImageLabel().setIcon(pessoaEmDivulgacao.getSmallIcon(true));
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			}
		}
	}



	/**
	 * This method initializes descricaoComitenteTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	public JComponent getDescricaoComitenteTextField() {
		if (descricaoComitenteTextField == null) {
			descricaoComitenteTextField = new JTextField();
			descricaoComitenteTextField.setBounds(new Rectangle(81, 11, 458, 20));
			this.binder.addBindProperty(getRepresentanteLegal(),this.descricaoComitenteTextField, "descricao");			
			this.hashComps.put("descricao", this.descricaoComitenteTextField);
			JWarningComponent warn = new JWarningComponent( this.descricaoComitenteTextField);
			warn.setBounds(81, 11, 458, 20);
			return warn;
		}
		return descricaoComitenteTextField;
	}



	public static void main(String args[] ){
		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(353,407));
					gui.add(new CadastrarParticipanteForm(PersonType.Fisica),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}
	
	protected static Icon getIcon(String name ){
		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL).getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {

			e.printStackTrace(); 
		} catch (java.lang.Exception e) {

			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	public void newRegister(){
		getAtivoRadioButton().setSelected(true);
		getAtivoRadioButton().setVisible(true);
		getInativoRadioButton().setVisible(true);
		getAdminUserDataForm().getUsuario().setNewUser(true);
		
		getInsertButton().setText(messages.getMessage("Cadastrar"));
		//getListarUsuariosButton().setEnabled(false);
		//getDeleteButton().setEnabled(false);
		getNewButton().setEnabled(false);
		getDeactivateButton().setEnabled(false);
		getAdminEnderecoCadastreForm().newRegister();
		getAdminUserDataForm().newRegister();
		
		try {
			tabbedPane.remove(getGridCategoriasPanel());
			tabbedPane.remove(getGridLancesAcessosPanel());
			//getPreferenciasList().createPreferencia(true);
			getPreferenciasList().pref = 
				RemotePreferenciaService.getInstance().createPreferencia(true, usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getPessoaFisicaCadastreForm().newRegister();		
		
	}
	
	public void editRegister(){
		getInsertButton().setText(messages.getMessage("Atualizar"));
		getAtivoRadioButton().setVisible(false);
		getInativoRadioButton().setVisible(false);
		getAdminUserDataForm().getUsuario().setNewUser(false);
		representante = (RepresentanteLegal) usuario.getDadosPessoais();
		//getDeleteButton().setEnabled(true);
		getNewButton().setEnabled(true);
		Endereco ender = null;
		try {
			ender = RemotePessoaService.getInstance().getEnderecoByPessoaId(usuario.getDadosPessoais().getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ender != null )
			getAdminEnderecoCadastreForm().editRegister(ender);
		try {
			tabbedPane.add(getGridCategoriasPanel(),"Preferências e Categorias");
			tabbedPane.add(getGridLancesAcessosPanel(),"Acessos e Lances");
			//getPreferenciasList().createPreferencia(false);
			getPreferenciasList().pref = 
				RemotePreferenciaService.getInstance().createPreferencia(false, usuario);
			getPreferenciasList().updateTable();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getAdminUserDataForm().editRegister(usuario);
		getDeactivateButton().setEnabled(true);
		if (usuario.getActive()){			
			getDeactivateButton().setText("Desativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_delete.png",18,18));
		}else{
			getDeactivateButton().setText("Ativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_add.png",18,18));
		}
		getPessoaFisicaCadastreForm().editRegister(usuario.getDadosPessoais());
		
		if (usuario.getDadosPessoais() instanceof PessoaEmDivulgacao)
			editRegister((PessoaEmDivulgacao)getRepresentanteLegal());
	}
	
	public void editRegister(PessoaEmDivulgacao com){
		//descricaoComitenteTextField.setText(com.getDescricao());
		//tipoComitenteComboBox.setSelectedItem(com.getTipoComitente().name());
		updateImage(true);	
	}
	
	protected Usuario usuario;  //  @jve:decl-index=0:

	private JRadioButton ativoRadioButton = null;

	private JRadioButton inativoRadioButton = null;

	private JPanel dadosComitentePanel = null;

	private JScrollPane preferenciasScrollPane = null;

	private JTextField descricaoComitenteTextField = null;


	private class CadastrarAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			try {
				cadastrar();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), "Problemas ao cadastrar usuário","Cadastro de Usuário",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}		
	}
	
	@SuppressWarnings("unused")
	private class RemoverAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			remover();
		}		
	}
	
	public void remover(){
		/*int resp = JOptionPane.showConfirmDialog(this, "Remover este usuário?");
		if (resp != JOptionPane.YES_OPTION) return;
		
		LeilaoVirtualFrame.getInstance().cleanErrorMsg();
		getTabbedPane().setIconAt(2, null);
		getTabbedPane().setIconAt(1, null);
		getTabbedPane().setIconAt(0, null);
		Usuario user = null;
		
		try {
			user = getAdminUserDataForm().getUsuario();
			if (user.getLogin() == null || user.getLogin().equals("")){
				LeilaoVirtualFrame.getInstance().displayErrorMsg("Não foi possível remover o usuário");
				LeilaoVirtualFrame.getInstance().showErrorDialog("Operação: remover usuário", "O identificar do usuário não foi informado");
			}else{
				LeilaoVirtualFrame.getInstance().beginStatusBar("Removendo usuário");
				try {
					//LocalUserService.getInstance().deleteById(user.getLogin());
					Session s = null;
					try {						
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						s.delete(user);
						s.getTransaction().commit();
						newRegister();
						LeilaoVirtualFrame.getInstance().setOperationMessage("Usuário removido com sucesso");
						LeilaoVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e) {
						e.printStackTrace();
						s.getTransaction().rollback();
						throw e;
					}finally{
						if (s !=  null) s.close();
					}
				} catch (Exception e) {
					LeilaoVirtualFrame.getInstance().displayErrorMsg("Não foi possível remover o usuário");
					LeilaoVirtualFrame.getInstance().showErrorDialog("Erro de base de dados", "Problemas com acesso à base de dados do usuário");
				}
				LeilaoVirtualFrame.getInstance().endStatusBar("Removendo usuário");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	*/	
	}
	
	private Icon warn = getIcon("/imgs/warn.png");
	private StringBuffer errorMsgs = null;
	
	private Pessoa validatePessoaData(){
		Pessoa p = null;
		try {
			getPessoaFisicaCadastreForm().cadastrePessoa();						
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return p;
	}
	
	private Usuario validateUsuarioData(){
		try {
			Usuario user = getAdminUserDataForm().cadastreUsuario();
			usuario = user;
			if (getAtivoRadioButton().isVisible()){
				user.setActive(getAtivoRadioButton().isSelected());
				user.setAutenticado(getAtivoRadioButton().isSelected());
			}
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			getTabbedPane().setIconAt(0, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
		}
		return null;
	}
	
	private Endereco validateEnderecoData(){
		try {
			Endereco ender = getAdminEnderecoCadastreForm().cadastreEndereco();
			return ender;
		} catch (Exception e) {
			e.printStackTrace();
			getTabbedPane().setIconAt(4, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
		}
		return null;		
	}
	
	public void saveData(Usuario user, Pessoa p, Endereco ender){
		if (user!= null)try {
			RemotePessoaService pessoaService = RemotePessoaService.getInstance();
			try{
				user = pessoaService.saveOrUpdate(user, p, ender);	
				
				usuario = RemoteUserService.getInstance().getUserByLoginAndPassword(user.getLogin(), user.getPassword());
				editRegister();		
				
				if (!usuario.getActive()){
					AdapitVirtualFrame.getInstance().showOperationSucess("Envio de email", "Enviando email para o usuário autenticar o seu cadastro");
					RemoteAdapitAutenticateUserService.getInstance().sendAutenticateUserMsg(user);
				}
				AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
				AdapitVirtualFrame.getInstance().setOperationMessage("Usuário cadastrado com sucesso");
				AdapitVirtualFrame.getInstance().showOperationSucess();
				
			}catch(FieldMsgValidationException ex){
				Hashtable<String,String> ht = ex.getErrors();
				if (ht != null && ht.size() > 0){
					for(String key : ht.keySet()){
						getPessoaFisicaCadastreForm().reportWarning(key,ht.get(key));						
					}
				}
				getTabbedPane().setIconAt(1, warn);
			}catch(org.hibernate.exception.ConstraintViolationException ex){
				ex.printStackTrace();
				boolean cpfExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getCpf());
				boolean rgExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getRg());
				boolean emailExists = pessoaService.cpfExists(p.getEmail());
				if (cpfExists){
					getPessoaFisicaCadastreForm().reportWarning("cpf");
				}
				if (rgExists){
					getPessoaFisicaCadastreForm().reportWarning("rg");
				}
				if (emailExists){
					getPessoaFisicaCadastreForm().reportWarning("email");
				}
				if (cpfExists || rgExists || emailExists){
					getTabbedPane().setIconAt(1, warn);
					AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
					AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário!"+'\n'+"Verifique se já não existe alguém "+'\n'+"cadastrado com o mesmo cpf, rg ou email");
					AdapitVirtualFrame.getInstance().showErrorDialog("Dados duplicados o usuário", "Não foi possível cadastrar o usuário pois já existem registros com os mesmos dados");
				}else{
					AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
					AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário!"+'\n'+"Um erro inesperado ocorreu! Verifique com o desenvolvedor do sistema");
					AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao cadastrar o usuário", "Não foi possível cadastrar o usuário por um erro inesperado");
				}
			}catch(org.hibernate.exception.DataException ex){
				ex.printStackTrace();
				boolean cpfExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getCpf());
				boolean rgExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getRg());
				boolean emailExists = pessoaService.cpfExists(p.getEmail());
				if (cpfExists){
					getPessoaFisicaCadastreForm().reportWarning("cpf");
				}
				if (rgExists){
					getPessoaFisicaCadastreForm().reportWarning("rg");				
				}
				if (emailExists){
					getPessoaFisicaCadastreForm().reportWarning("email");
				}
				if (cpfExists || rgExists || emailExists){
					getTabbedPane().setIconAt(1, warn);
					AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
					AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário!"+'\n'+"Verifique se já não existe alguém "+'\n'+"cadastrado com o mesmo cpf, rg ou email");
					AdapitVirtualFrame.getInstance().showErrorDialog("Dados duplicados o usuário", "Não foi possível cadastrar o usuário pois já existem registros com os mesmos dados");
				}else{
					AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
					AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário!"+'\n'+"Um erro inesperado ocorreu! Verifique com o desenvolvedor do sistema");
					AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao cadastrar o usuário", "Não foi possível cadastrar o usuário por um erro inesperado");
				}
			}catch(org.hibernate.NonUniqueObjectException ex){
				ex.printStackTrace();
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
			}
							
		} catch (Exception e) {				
			e.printStackTrace();
			AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
			AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário");
			AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao cadastrar o usuário", "Não foi possível cadastrar o usuário por um erro inesperado");
		}
	}
	
	public void validateUnique(Pessoa p) throws Exception{
		RemotePessoaService pessoaService = RemotePessoaService.getInstance();
		TipoPessoa tp = p.getTipoPessoa();
		int tipo=0;
		String email = p.getEmail();
		String cpfcnpj="";
		String rgie="";
		if (tp instanceof Juridica){
			tipo=1;
			cpfcnpj = ((Juridica)tp).getCnpj();
			rgie = ((Juridica)tp).getInscricaoEstadual();
		}else{
			cpfcnpj = ((Fisica)tp).getCpf();
			rgie = ((Fisica)tp).getRg();
		}
		FieldMsgValidation ex = pessoaService.validateUnique(tipo,tp.getId(),cpfcnpj,rgie,p.getId(),email);
		if (ex != null){
			Hashtable<String,String> ht = ex.getErrors();
			if (ht != null && ht.size() > 0){
				for(String key : ht.keySet()){
					getPessoaFisicaCadastreForm().reportWarning(key,ht.get(key));
				}
			}
			getTabbedPane().setIconAt(1, warn);
			throw new FieldMsgValidationException(ht);
		}
	}
	
	public void cadastrar() throws Exception{
		AdapitVirtualFrame.getInstance().beginStatusBar("Verificando a corretude dos dados");
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
		

		getTabbedPane().setIconAt(2, null);
		getTabbedPane().setIconAt(3, null);
		getTabbedPane().setIconAt(1, null);
		getTabbedPane().setIconAt(0, null);
		
		errorMsgs = null;
		
		Pessoa p = validatePessoaData();
		Usuario user = validateUsuarioData();
		Endereco ender = validateEnderecoData();
				
		if (errorMsgs != null){
			AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			AdapitVirtualFrame.getInstance().setOperationMessage("Cadastro suspenso ... verifique a corretude dos dados");
			AdapitVirtualFrame.getInstance().showErrorCamposInvalidosWithinTabs();
			throw new Exception("Erros de formulário");
		}else{
			
			
			AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			
			AdapitVirtualFrame.getInstance().beginStatusBar("Salvando os dados do usuário");
			user.setPassword(Usuario.encript(user.getPassword()));
			user.setPasswordConf(user.getPassword());
			
			p.setEndereco(ender);
			ender.setPais(Pais.Brasil);
			
			((PessoaEmDivulgacao)p).setLogotipo(getRepresentanteLegal().getLogotipo());
			
			try {
				AdapitVirtualFrame.getInstance().beginStatusBar("Verificando repetição de dados");
				try {
					validateUnique(p);
					AdapitVirtualFrame.getInstance().endStatusBar("Verificando repetição de dados");
				} catch (RuntimeException e) {
					e.printStackTrace();
					AdapitVirtualFrame.getInstance().endStatusBar("Verificando repetição de dados");
					return;
				}
				AdapitVirtualFrame.getInstance().beginStatusBar("Salvando os dados do usuário");
				saveData(user,p,ender);
				
				AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	


	public void setUsuario(Usuario u) {
		this.usuario = u;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"