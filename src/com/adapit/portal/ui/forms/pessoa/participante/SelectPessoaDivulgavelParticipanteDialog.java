package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.UserDataFilterType;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

public class SelectPessoaDivulgavelParticipanteDialog extends JDialog {

	private static final long serialVersionUID = 112366676744L;
	private JPanel jContentPane = null;
	private JPanel contentPanel = null;
	private JButton helpButton = null;
	private JLabel filtarPorjLabel = null;
	private JPanel filterFieldPanel = null;
	private JTextField queryStrTextField = null;
	private JRadioButton nomeUsuarioRadioButton = null;
	private JPanel porPessoaUsuarioPanel = null;
	private JRadioButton nomePessoaRadioButton = null;
	private JButton buscarButton = null;
	private JScrollPane usuariosScrollPane = null;
	private UsuariosTable usuariosTable = null;
	private Usuario usuario;

	/**
	 * @param owner
	 */
	public SelectPessoaDivulgavelParticipanteDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}
	
	public SelectPessoaDivulgavelParticipanteDialog(JFrame owner) {
		super(owner);
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(558, 342);
		this.setModal(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Selecione o usuário na tabela de usuários");
		this.setContentPane(getJContentPane());
		setLocation(UIUtil.getScreenCenter(this));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getContentPanel(), BorderLayout.CENTER);
		}
		return jContentPane;
	}
	
	private JPanel buttonsPanel;
	private JButton selecionarButton = null;
	private JTabbedPane pesquisaUsuariosTabbedPane = null;
	private JPanel pesquisarPessoaFisicaPanel = null;
	private JLabel cnpjLabel = null;
	private JTextField cpfTextField = null;
	private JButton pesquisarPorCPFButton = null;
	private JLabel ouRgLabel = null;
	private JLabel rgLabel = null;
	private JTextField rgTextField = null;

	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setBounds(new Rectangle(515, 6, 20, 20));
			helpButton.setIcon(new ImageIcon(getClass().getResource("/imgs/helpicon.png")));
			helpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					/*JavaHelpFrame helpFrame = JavaHelpFrame.getInstance();
					helpFrame.setCurrentTopic("Buscando_um_Comitente");
					helpFrame.setVisible(true);*/
				}
			});
		}
		return helpButton;
	}

	private JPanel getButtonsPanel(){
		if (buttonsPanel == null){
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			buttonsPanel.setBounds(new Rectangle(0, 261, 542, 35));
			buttonsPanel.add(getSelecionarButton(), null);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes contentPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getContentPanel() {
		if (contentPanel == null) {
			filtarPorjLabel = new JLabel();
			filtarPorjLabel.setText("Filtar Usuários:");
			filtarPorjLabel.setBounds(new Rectangle(14, 1, 82, 22));
			contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.add(getUsuariosScrollPane(), null);
			contentPanel.add(getPesquisaUsuariosTabbedPane(), null);
			contentPanel.add(getHelpButton(), null);
			contentPanel.add(getButtonsPanel(), null);
		}
		return contentPanel;
	}

	/**
	 * This method initializes filterFieldPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFilterFieldPanel() {
		if (filterFieldPanel == null) {
			filterFieldPanel = new JPanel();
			filterFieldPanel.setLayout(null);
			filterFieldPanel.add(filtarPorjLabel, null);
			filterFieldPanel.add(getQueryStrTextField(), null);
			filterFieldPanel.add(getPorPessoaUsuarioPanel(), null);
			filterFieldPanel.add(getBuscarButton(), null);
		}
		return filterFieldPanel;
	}

	/**
	 * This method initializes queryStrTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getQueryStrTextField() {
		if (queryStrTextField == null) {
			queryStrTextField = new JTextField();
			queryStrTextField.setBounds(new Rectangle(97, 1, 300, 22));
			queryStrTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarPessoaUsuario();
				}
			});
		}
		return queryStrTextField;
	}

	/**
	 * This method initializes nomeUsuarioRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNomeUsuarioRadioButton() {
		if (nomeUsuarioRadioButton == null) {
			nomeUsuarioRadioButton = new JRadioButton();
			nomeUsuarioRadioButton.setText("Pelo nome de usuário");
			nomeUsuarioRadioButton.setSelected(true);
		}
		return nomeUsuarioRadioButton;
	}

	/**
	 * This method initializes porPanelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPorPessoaUsuarioPanel() {
		if (porPessoaUsuarioPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			porPessoaUsuarioPanel = new JPanel();
			porPessoaUsuarioPanel.setBounds(new Rectangle(14, 24, 382, 22));
			porPessoaUsuarioPanel.setLayout(flowLayout);
			porPessoaUsuarioPanel.add(getNomeUsuarioRadioButton(), null);
			porPessoaUsuarioPanel.add(getNomePessoaRadioButton(), null);
			porPessoaUsuarioPanel.add(getEmailRadioButton(),null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getNomeUsuarioRadioButton());
			bg.add(getNomePessoaRadioButton());
			bg.add(getEmailRadioButton());
		}
		return porPessoaUsuarioPanel;
	}

	/**
	 * This method initializes nomePessoaRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNomePessoaRadioButton() {
		if (nomePessoaRadioButton == null) {
			nomePessoaRadioButton = new JRadioButton();
			nomePessoaRadioButton.setText("Pelo nome da pessoa");
		}
		return nomePessoaRadioButton;
	}
	private JRadioButton emailRadioButton;
	private JPanel buscarPessoaJuridicaPanel = null;
	private JRadioButton getEmailRadioButton() {
		if (emailRadioButton == null) {
			emailRadioButton = new JRadioButton();
			emailRadioButton.setText("Pelo email");
		}
		return emailRadioButton;
	}

	/**
	 * This method initializes buscarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarButton() {
		if (buscarButton == null) {
			buscarButton = new JButton();
			buscarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			buscarButton.setBounds(new Rectangle(408, 13, 106, 24));
			buscarButton.setText("Pesquisar");
			buscarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarPessoaUsuario();
				}
			});
		}
		return buscarButton;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarPessoaUsuario(){		
		//Session s = LocalServicesUtility.getInstance().openSession();
		//String str="";
		UserDataFilterType u = null;
		if (getNomeUsuarioRadioButton().isSelected())  u = UserDataFilterType.USUARIO;//str+=" usuario.login ";
		else if (getNomePessoaRadioButton().isSelected()) u = UserDataFilterType.PESSOA;//str+=" usuario.dadosPessoais.nome ";
		else u = UserDataFilterType.EMAIL;//str+=" usuario.dadosPessoais.email ";
		try {
			List l = RemoteUserService.getInstance().listUsuariosAccordingTo(u, getQueryStrTextField().getText());//s.createQuery("from Usuario usuario where "+str+" like '%"+getQueryStrTextField().getText()+"%'").list();
			getUsuariosTable().setElements(l);
			getUsuariosTable().updateTable();
		} catch (Exception e1) { 
			e1.printStackTrace();
			//s.getTransaction().rollback();
		}/*finally{
			s.close();
		}*/
		
	}
	
/*	public List<Usuario> listUsuariosAccordingTo(UserNameQueryKind qKind, String value){
		org.hibernate.Session s = com.adapit.portal.services.local.LocalServicesUtility.getInstance().openSession();
		String str="";
		if (qKind == UserNameQueryKind.USUARIO) str+=" usuario.login ";
		else if (qKind == UserNameQueryKind.PESSOA) str+=" usuario.dadosPessoais.nome ";
		else str+=" usuario.dadosPessoais.email ";
		try {
			List<Usuario> l = s.createQuery("from Usuario usuario where "+str+" like '%"+value+"%'").list();
			return l;
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		return null;
	}*/

	@SuppressWarnings("unchecked")
	private void buscarCpf(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByCpf(getCpfTextField().getText());
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}
			else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}
						
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
	}
	
	/*@SuppressWarnings("unchecked")
	private void buscarCpfOld(){		
		Session s = LocalServicesUtility.getInstance().openSession();
		//String str="";
		
		try {
			Long pid = (Long) s.createQuery("select fis.pessoa.id from Fisica fis where fis.cpf='"+getCpfTextField().getText()+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		
	}*/
	
	@SuppressWarnings("unchecked")
	private void buscarCnpj(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByCnpj(getCnpjTextField().getText());			
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		
	}
	/*private void buscarCnpj(){		
		Session s = LocalServicesUtility.getInstance().openSession();
		//String str="";
		
		try {
			Long pid = (Long) s.createQuery("select jur.pessoa.id from Juridica jur where jur.cnpj='"+getCnpjTextField().getText()+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		
	}*/
	@SuppressWarnings("unchecked")
	private void buscarInscricaoEstadual(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByInscricaoEstadual(getInscricaoEstadualTextField().getText());
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();			
		}
	}
	/*private void buscarInscricaoEstadual(){		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Long pid = (Long) s.createQuery("select jur.pessoa.id from Juridica jur where jur.inscricaoEstadual='"+getInscricaoEstadualTextField().getText()+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		
	}*/
	@SuppressWarnings("unchecked")
	private void buscarRg(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByRg(getRgTextField().getText());
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();			
		}		
	}
	/*private void buscarRg(){		
		Session s = LocalServicesUtility.getInstance().openSession();
		//String str="";
		
		try {
			Long pid = (Long) s.createQuery("select fis.pessoa.id from Fisica fis where fis.rg='"+getRgTextField().getText()+"'").uniqueResult();
			if (pid != null){
				Usuario user = (Usuario) s.createQuery("select u from Usuario u where u.dadosPessoais.id="+pid).uniqueResult();
				List l = new ArrayList();
				l.add(user);
				getUsuariosTable().setElements(l);
				getUsuariosTable().updateTable();
			}else{
				getUsuariosTable().setElements(new ArrayList());
				getUsuariosTable().updateTable();
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		
	}*/
	/**
	 * This method initializes usuariosScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getUsuariosScrollPane() {
		if (usuariosScrollPane == null) {
			usuariosScrollPane = new JScrollPane();
			usuariosScrollPane.setBounds(new Rectangle(7, 111, 529, 143));
			usuariosScrollPane.setViewportView(getUsuariosTable());
		}
		return usuariosScrollPane;
	}

	/**
	 * This method initializes usuariosTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private UsuariosTable getUsuariosTable() {
		if (usuariosTable == null) {
			usuariosTable = new UsuariosTable();
			usuariosTable.addFocusListener(new FocusAdapter(){

				@Override
				public void focusGained(FocusEvent arg0) {
					getSelecionarButton().setEnabled(true);
				}
				
			});
		}
		return usuariosTable;
	}

	/**
	 * This method initializes selecionarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSelecionarButton() {
		if (selecionarButton == null) {
			selecionarButton = new JButton("Selecionar");
			selecionarButton.setEnabled(false);
			selecionarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/accept.png")));
			selecionarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getUsuariosTable().getSelectedRow();
					if (row >=0){
						usuario= (Usuario) getUsuariosTable().getElements().get(row);
						dispose();
					}
				}
			});
			
		}
		return selecionarButton;
	}
	@SuppressWarnings({ "unchecked", "serial" })
	private class UsuariosTable extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		@SuppressWarnings("deprecation")
		public UsuariosTable() {
			super();
			this.setModel(new UsuariosTableModel(null));
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		@SuppressWarnings("deprecation")
		public UsuariosTable(List elements) {
			super();
			this.elements = elements;
			this.setModel(new UsuariosTableModel(null));
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		public void setDefineCellRenderers() {
		}

		public void updateTable() {
			getSelecionarButton().setEnabled(false);
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][5];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof Usuario) {
						Usuario usuario = (Usuario) obj;
						System.out.println(usuario.getLogin());
						values[i][0] = usuario.getLogin();
						try {
							values[i][1] = RemoteUserService.getInstance().getNomePessoaByUsuarioLogin(usuario.getLogin());
							System.out.print(" " + values[i][1]);
						} catch (Exception e) {
							e.printStackTrace();
						}
						values[i][2] = RemoteUserService.getInstance().getEmailPessoaByUsuarioLogin(usuario.getLogin());
						values[i][3] = usuario.getActive();
						System.out.print(" " + values[i][3]);
						values[i][4] = usuario.getUserCadastreType().name().replace("_"," ");
						i++;
					}
				}// End of while Loop
				if (values != null && values.length>0)
					setModel(new UsuariosTableModel(values));
				else setModel(new UsuariosTableModel(null));
				updateUI();
			} else {
				setModel(new UsuariosTableModel(null));
				updateUI();
			}
		}

		/*private String getNomePessoa(Usuario usuario){
			
			Session s = LocalServicesUtility.getInstance().openSession();
			try {
				return (String) s.getNamedQuery("usuario.getNome").setParameter("login", usuario.getLogin()).uniqueResult();				
			} catch (Exception e1) { 
				e1.printStackTrace();
			}finally{
				s.close();
			}
			return null;			
		}
		
		private String getEmailPessoa(Usuario user){
			
			Session s = LocalServicesUtility.getInstance().openSession();
			try {
				return (String) s.getNamedQuery("usuario.getEmail").setParameter("login", user.getLogin()).uniqueResult();				
			} catch (Exception e1) { 
				e1.printStackTrace();
			}finally{
				s.close();
			}
			return null;			
		}*/
		@SuppressWarnings({ "unchecked", "serial" })
		private class UsuariosTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class, String.class, Boolean.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false, false };

			public UsuariosTableModel(Object[][] values) {
				super(values,new String[] {"Usuário","Nome", "Email","Ativo","Tipo" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * This method initializes pesquisaUsuariosTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getPesquisaUsuariosTabbedPane() {
		if (pesquisaUsuariosTabbedPane == null) {
			pesquisaUsuariosTabbedPane = new JTabbedPane();
			pesquisaUsuariosTabbedPane.setBounds(new Rectangle(7, 27, 532, 78));
			pesquisaUsuariosTabbedPane.addTab("Pesquisar por nome ou email", getFilterFieldPanel());
			pesquisaUsuariosTabbedPane.addTab("Buscar uma pessoa física",getPesquisarPessoaFisicaPanel());
			pesquisaUsuariosTabbedPane.addTab("Buscar uma pessoa jurídica", getBuscarPessoaJuridicaPanel());
		}
		return pesquisaUsuariosTabbedPane;
	}

	/**
	 * This method initializes pesquisarPorNomePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPesquisarPessoaFisicaPanel() {
		if (pesquisarPessoaFisicaPanel == null) {
			rgLabel = new JLabel();
			rgLabel.setBounds(new Rectangle(310, 2, 32, 20));
			rgLabel.setText("RG:");
			ouRgLabel = new JLabel();
			ouRgLabel.setBounds(new Rectangle(249, 2, 30, 20));
			ouRgLabel.setText("Ou");
			cnpjLabel = new JLabel();
			cnpjLabel.setBounds(new Rectangle(10, 2, 37, 22));
			cnpjLabel.setText("CPF:");
			pesquisarPessoaFisicaPanel = new JPanel();
			pesquisarPessoaFisicaPanel.setLayout(null);
			pesquisarPessoaFisicaPanel.add(cnpjLabel, null);
			pesquisarPessoaFisicaPanel.add(getCpfTextField(), null);
			pesquisarPessoaFisicaPanel.add(getPesquisarPorCPFButton(), null);
			pesquisarPessoaFisicaPanel.add(ouRgLabel, null);
			pesquisarPessoaFisicaPanel.add(rgLabel, null);
			pesquisarPessoaFisicaPanel.add(getRgTextField(), null);
		}
		return pesquisarPessoaFisicaPanel;
	}

	/**
	 * This method initializes cpfTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCpfTextField() {
		if (cpfTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("###.###.###-##");
				cpfTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cpfTextField = new JTextField();
			}
			cpfTextField.setBounds(new Rectangle(49, 2, 159, 22));
			cpfTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarCpf();
				}
			});
		}
		return cpfTextField;
	}
	
	private JTextField cnpjTextField;
	private JTextField getCnpjTextField() {
		if (cnpjTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("##.###.###/####-##");
				cnpjTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cnpjTextField = new JTextField();
			}
			cnpjTextField.setBounds(new Rectangle(49, 2, 159, 22));
			cnpjTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarCnpj();
				}
			});
		}
		return cnpjTextField;
	}

	/**
	 * This method initializes pesquisarPorCPFButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPesquisarPorCPFButton() {
		if (pesquisarPorCPFButton == null) {
			pesquisarPorCPFButton = new JButton();
			pesquisarPorCPFButton.setBounds(new Rectangle(212, 25, 94, 24));
			pesquisarPorCPFButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			pesquisarPorCPFButton.setText("Buscar");
			pesquisarPorCPFButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String str = cpfTextField.getText();
					if (str != null && !str.equals("")){
						buscarCpf();
						return;
					}
					str = rgTextField.getText();
					if (str != null && !str.equals("")){
						buscarRg();
						return;
					}
				}
			});
		}
		return pesquisarPorCPFButton;
	}
	
	private JButton pesquisarPorCnpjButton;
	
	private JButton getPesquisarPorCnpjButton() {
		if (pesquisarPorCnpjButton == null) {
			pesquisarPorCnpjButton = new JButton();
			pesquisarPorCnpjButton.setBounds(new Rectangle(212, 25, 94, 24));
			pesquisarPorCnpjButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			pesquisarPorCnpjButton.setText("Buscar");
			pesquisarPorCnpjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String str = cnpjTextField.getText();
					if (str != null && !str.equals("")){
						buscarCnpj();
						return;
					}
					str = inscricaoEstadualTextField.getText();
					if (str != null && !str.equals("")){
						buscarInscricaoEstadual();
						return;
					}
				}
			});
		}
		return pesquisarPorCnpjButton;
	}

	/**
	 * This method initializes rgTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRgTextField() {
		if (rgTextField == null) {
			rgTextField = new JTextField();
			rgTextField.setBounds(new Rectangle(346, 2, 168, 20));
			rgTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarRg();
				}
			});
		}
		return rgTextField;
	}
	
	private JTextField inscricaoEstadualTextField; 
	private JTextField getInscricaoEstadualTextField() {
		if (inscricaoEstadualTextField== null) {
			inscricaoEstadualTextField = new JTextField();
			inscricaoEstadualTextField.setBounds(new Rectangle(346, 2, 168, 20));
			inscricaoEstadualTextField
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							buscarInscricaoEstadual();
						}
					});
		}
		return inscricaoEstadualTextField;
	}

	/**
	 * This method initializes buscarPessoaJuridicaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBuscarPessoaJuridicaPanel() {
		if (buscarPessoaJuridicaPanel == null) {
			buscarPessoaJuridicaPanel = new JPanel();
			buscarPessoaJuridicaPanel.setLayout(null);
			rgLabel = new JLabel();
			rgLabel.setBounds(new Rectangle(243, 2, 99, 20));
			rgLabel.setText("Inscrição Estadual:");
			ouRgLabel = new JLabel();
			ouRgLabel.setBounds(new Rectangle(214, 2, 23, 20));
			ouRgLabel.setText("Ou");
			cnpjLabel = new JLabel();
			cnpjLabel.setBounds(new Rectangle(10, 2, 37, 22));
			cnpjLabel.setText("CNPJ:");
			buscarPessoaJuridicaPanel.add(cnpjLabel, null);
			buscarPessoaJuridicaPanel.add(getCnpjTextField(), null);
			buscarPessoaJuridicaPanel.add(getPesquisarPorCnpjButton(), null);
			buscarPessoaJuridicaPanel.add(ouRgLabel, null);
			buscarPessoaJuridicaPanel.add(rgLabel, null);
			buscarPessoaJuridicaPanel.add(getInscricaoEstadualTextField(), null);
		}
		return buscarPessoaJuridicaPanel;
	}

}  //  @jve:decl-index=0:visual-constraint="10,29"
