package com.adapit.portal.ui.forms.pessoa.instrutor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteAdapitAutenticateUserService;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.forms.pessoa.BindHandler;
import com.adapit.portal.ui.forms.pessoa.participante.ApresentacaoParticipante;
import com.adapit.portal.ui.forms.pessoa.participante.CommonData;
import com.adapit.portal.ui.forms.usuario.AdminEnderecoCadastreForm;
import com.adapit.portal.ui.forms.usuario.AdminUserDataForm;
import com.adapit.portal.ui.forms.usuario.DesativarUsuarioDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.imagem.ListaImagemDialog;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings("serial")
public class CadastrarInstrutorForm extends JPanel implements BindHandler{
	
	private JTabbedPane tabbedPane;
	
	private JPanel userDataPanel;
	
	private AdminUserDataForm adminUserDataForm;
	
	private JPanel dadosPessoaisPanel;
	
	private InstrutorDadosPessoaisPanel cadastrarLeiloeiroPanel;
		
	private SwingBinder binder = new SwingBinder();
	
	public SwingBinder getBinder() {
		return binder;
	}

	@SuppressWarnings("unchecked")
	public Map getHashComps() {
		return hashComps;
	}

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private AdminEnderecoCadastreForm adminEnderecoCadastreForm;
	
	private JPanel cadastreButtonsPanel;
	
	private JButton cadastrarButton;
	
	private JButton newButton;
	
	//private JButton deleteButton;
	
	private JButton listarUsuariosButton;

	public ApresentacaoParticipante apresentacaoParticipante= null;
	
	public CadastrarInstrutorForm(){	
		super();
		apresentacaoParticipante= new ApresentacaoParticipante(this);
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(780, 390));
		setLocation(new java.awt.Point(0,0));
		setLayout(new BorderLayout());
		add(getTabbedPane(),BorderLayout.CENTER);
		add(getCadastreButtonsPanel(),BorderLayout.SOUTH);
	}
	
	protected JTabbedPane getTabbedPane(){

		if(tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(559, 342));
			tabbedPane.setLocation(new java.awt.Point(3,3));
			tabbedPane.add(getUserDataPanel(),"Dados de Usuário");
			tabbedPane.add(getDadosPessoaisPanel(),"Dados Pessoais");
			tabbedPane.add(apresentacaoParticipante,"Apresentação");
			tabbedPane.add(getDadosComitentePanel(),"Fotografia");
			tabbedPane.add(getAdminEnderecoCadastreForm(),"Endereço");
		}
		return tabbedPane;
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
			adminUserDataForm.setBounds(new Rectangle(15, 0, 520, 304));
			((JComboBox)adminUserDataForm.getRoleComboBox()).setSelectedItem(UserCadastreType.Instrutor.name().replace("_"," "));
			((JComboBox)adminUserDataForm.getRoleComboBox()).setEnabled(false);
			adminUserDataForm.add(getInativoRadioButton(), null);
			adminUserDataForm.add(getAtivoRadioButton(), null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAtivoRadioButton());
			bg.add(getInativoRadioButton());
		}
		return adminUserDataForm;
	}
	
	protected JPanel getDadosPessoaisPanel(){

		if(dadosPessoaisPanel == null){
			dadosPessoaisPanel = new JPanel();
			//dadosPessoaisPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Dados Pessoais")));
			dadosPessoaisPanel.setSize(new java.awt.Dimension(307,192));
			dadosPessoaisPanel.setLocation(new java.awt.Point(0,230));
			dadosPessoaisPanel.setLayout(null);
			dadosPessoaisPanel.add(getCadastrarLeiloeiroPanel());
			/*dadosPessoaisPanel.add(getCompComboBox3());
			dadosPessoaisPanel.add(getCompComboBox3Label());
			dadosPessoaisPanel.add(getMostrarTipoPessoaButton());*/
		}
		return dadosPessoaisPanel;
	}
	
	protected InstrutorDadosPessoaisPanel getCadastrarLeiloeiroPanel(){
		if(cadastrarLeiloeiroPanel == null){
			cadastrarLeiloeiroPanel = new InstrutorDadosPessoaisPanel(this);
			cadastrarLeiloeiroPanel.getFisica().setProfissao("Instrutor");
			cadastrarLeiloeiroPanel.setLayout(null);
			cadastrarLeiloeiroPanel.setBounds(new Rectangle(15, 20, 520, 340));
		}
		return cadastrarLeiloeiroPanel;
	}
		
	protected AdminEnderecoCadastreForm getAdminEnderecoCadastreForm(){

		if(adminEnderecoCadastreForm == null){
			adminEnderecoCadastreForm = new AdminEnderecoCadastreForm();
			adminEnderecoCadastreForm.setSize(new java.awt.Dimension(150,20));
			adminEnderecoCadastreForm.setLocation(new java.awt.Point(15,422));
			adminEnderecoCadastreForm.getEnderecoPanel().setLocation(
					120,
					(int) adminEnderecoCadastreForm.getEnderecoPanel().getLocation().getY());
			adminEnderecoCadastreForm.setLayout(null);
		}
		return adminEnderecoCadastreForm;
	}
	
	protected JPanel getCadastreButtonsPanel(){

		if(cadastreButtonsPanel == null){
			cadastreButtonsPanel = new JPanel();
			cadastreButtonsPanel.setSize(new Dimension(559, 39));
			cadastreButtonsPanel.setLocation(new Point(3, 347));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.add(getCadastrarButton());
			cadastreButtonsPanel.add(getNewButton());
			//cadastreButtonsPanel.add(getDeleteButton());
			cadastreButtonsPanel.add(getListarUsuariosButton());
			cadastreButtonsPanel.add(getDeactivateButton());
		}
		return cadastreButtonsPanel;
	}
	
	private JButton deactivateButton;
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
					/*Session s = LocalServicesUtility.getInstance().openSession();
					
					List<DeactivationReason> list = null;
					try{
						//String query = "select d from DeactivationReason d where d.usuario.login=:userlogin";
						list = s.getNamedQuery("usuario.listDeactivationByLogin")
							.setParameter("userlogin", usuario.getLogin())
							.list();
						usuario.setDeactivationReasonList(list);
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
					}*/
					List<DeactivationReason> list = null;
					try{
						list = RemoteUserService.getInstance().listDeactivationReasonByUserLogin(usuario.getLogin());
						usuario.setDeactivationReasonList(list);
					}catch(Exception ex){
						ex.printStackTrace();
					}
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
	
	protected JButton getCadastrarButton(){
		if(cadastrarButton == null){
			cadastrarButton = new JButton(messages.getMessage("Cadastrar"));
			cadastrarButton.setSize(new java.awt.Dimension(80,22));
			cadastrarButton.setLocation(new java.awt.Point(0,0));
			cadastrarButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_save.png",18,18));
			cadastrarButton.addActionListener(new CadastrarAction());
		}
		return cadastrarButton;
	}
	
	protected JButton getNewButton(){
		if(newButton == null){
			newButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Novo"));
			newButton.setSize(new java.awt.Dimension(80,22));
			newButton.setLocation(new java.awt.Point(0,22));
			newButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_add.png",18,18));
			newButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					getTabbedPane().setIconAt(4, null);
					getTabbedPane().setIconAt(3, null);
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
			//deleteButton.setText("Destaivar Usuário");
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
	
	private static Icon getIcon(String name ){

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

	//TODO adicionar
	private CommonData commonData;
	private CommonData getCommonData(){
		if(commonData == null){
			commonData = new CommonData(tabbedPane,(Participante) usuario.getDadosPessoais(),usuario);
		}
		return commonData;
	}
	
	public void newRegister(){
		getAtivoRadioButton().setSelected(true);
		getAtivoRadioButton().setVisible(true);
		getInativoRadioButton().setVisible(true);
		getCadastrarButton().setText(messages.getMessage("Cadastrar"));
		//getListarUsuariosButton().setEnabled(false);
		//getDeleteButton().setEnabled(false);
		getNewButton().setEnabled(false);
		getDeactivateButton().setEnabled(false);
		getAdminEnderecoCadastreForm().newRegister();
		getAdminUserDataForm().newRegister();
		getCadastrarLeiloeiroPanel().newRegister();
		
		//TODO adicionar
		getCommonData().removeTabs();
		
	}
	
	public void editRegister(){
		getCadastrarButton().setText(messages.getMessage("Atualizar"));
		getAtivoRadioButton().setVisible(false);
		getInativoRadioButton().setVisible(false);
		
		//getListarUsuariosButton().setEnabled(true);
		//getDeleteButton().setEnabled(true);
		getNewButton().setEnabled(true);
		if (usuario == null) return;
		try {
			instrutor = (Instrutor) RemotePessoaService.getInstance().initializeInstrutor(usuario.getDadosPessoais().getId());
			
		} catch (Exception e) {
			e.printStackTrace();
			instrutor = (Instrutor) usuario.getDadosPessoais();
		}
		
		usuario.setNewUser(false);

		getAdminEnderecoCadastreForm().editRegister(instrutor.getEndereco());
		getAdminUserDataForm().editRegister(usuario);
		getDeactivateButton().setEnabled(true);
		if (usuario.getActive()){			
			//getAtivoRadioButton().setSelected(true);
			getDeactivateButton().setText("Desativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_delete.png",18,18));
		}else{
			//getDeactivateButton().setEnabled(false);
			getDeactivateButton().setText("Ativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_add.png",18,18));
		}
		//else getInativoRadioButton().setSelected(true);
		getCadastrarLeiloeiroPanel().editRegister(instrutor);
		
		try {
			apresentacaoParticipante.bind(instrutor);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*if (usuario.getDadosPessoais() instanceof PessoaEmDivulgacao)
			editRegister((PessoaEmDivulgacao)usuario.getDadosPessoais());*/
		updateImage(true);
		
		//TODO incluir
		getCommonData().addTabs();
		
	}
	
/*	public void editRegister(PessoaEmDivulgacao com){
		descricaoComitenteTextField.setText(com.getDescricao());
		tipoComitenteComboBox.setSelectedItem(com.getTipoComitente().name());
		updateImage(true);	
	}*/
	
	private Usuario usuario;  //  @jve:decl-index=0:

	private JRadioButton ativoRadioButton = null;

	private JRadioButton inativoRadioButton = null;

	
	private class CadastrarAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			try {
				cadastrar();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}		
	}
	
	private Icon warn = getIcon("/imgs/warn.png");  //  @jve:decl-index=0:
	private StringBuffer errorMsgs = null;
	
	private Pessoa validatePessoaData(){
		try {
			Pessoa p = getCadastrarLeiloeiroPanel().cadastrePessoa();
			PessoaEmDivulgacao ped = apresentacaoParticipante.reverseBind();
			if(p instanceof PessoaEmDivulgacao){
				((PessoaEmDivulgacao) p).setApresentacao(ped.getApresentacao());
				((PessoaEmDivulgacao) p).setDescricao(ped.getDescricao());
				((PessoaEmDivulgacao) p).setDivulgavel(ped.isDivulgavel());
				((PessoaEmDivulgacao) p).setManagerExp(ped.isManagerExp());
				((PessoaEmDivulgacao) p).setResearchExp(ped.isResearchExp());
				((PessoaEmDivulgacao) p).setSaleExp(ped.isSaleExp());
				((PessoaEmDivulgacao) p).setSoftDevExp(ped.isSoftDevExp());
				((PessoaEmDivulgacao) p).setTrainExp(ped.isTrainExp());
			}
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			apresentacaoParticipante.reverseBind();
			getTabbedPane().setIconAt(1, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
			
		}
		return null;
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
					getCadastrarLeiloeiroPanel().reportWarning(key,ht.get(key));
				}
			}
			getTabbedPane().setIconAt(1, warn);
			throw new FieldMsgValidationException(ht);
		}
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
						getCadastrarLeiloeiroPanel().reportWarning(key,ht.get(key));
					}
				}
				getTabbedPane().setIconAt(1, warn);
			}catch(org.hibernate.exception.ConstraintViolationException ex){
				ex.printStackTrace();
				boolean cpfExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getCpf());
				boolean rgExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getRg());
				boolean emailExists = pessoaService.cpfExists(p.getEmail());
				if (cpfExists) getCadastrarLeiloeiroPanel().reportWarning("cpf");
				if (rgExists) getCadastrarLeiloeiroPanel().reportWarning("rg");
				if (emailExists) getCadastrarLeiloeiroPanel().reportWarning("email");
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
				if (cpfExists) getCadastrarLeiloeiroPanel().reportWarning("cpf");
				if (rgExists) getCadastrarLeiloeiroPanel().reportWarning("rg");
				if (emailExists) getCadastrarLeiloeiroPanel().reportWarning("email");
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
	
	
	public void cadastrar() throws Exception{
		AdapitVirtualFrame.getInstance().beginStatusBar("Verificando a corretude dos dados");
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
		
		getTabbedPane().setIconAt(4, null);
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
			apresentacaoParticipante.reverseBind();
			
			AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			
			
			user.setPassword(Usuario.encript(user.getPassword()));
			user.setPasswordConf(user.getPassword());
			
			p.setEndereco(ender);
			ender.setPais(Pais.Brasil);
			
			
			((PessoaEmDivulgacao)p).setLogotipo(getParticipante().getLogotipo());
			apresentacaoParticipante.reverseBind();
			
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
		//this.leiloeiro = (Leiloeiro) u.getDadosPessoais();
	}
	
	private Instrutor instrutor;  //  @jve:decl-index=0:

	public void setInstrutor(Instrutor l) {
		try {
			instrutor = RemotePessoaService.getInstance().initializeInstrutor(l.getId());
			usuario = instrutor.getUser();
			usuario.setDadosPessoais(instrutor);
			apresentacaoParticipante.bind(instrutor);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	private JPanel getDadosComitentePanel() {
		if (dadosComitentePanel == null) {
			dadosComitentePanel = new JPanel();
			dadosComitentePanel.setLayout(null);
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
			buscarImagemButton.setBounds(new Rectangle(105, 77, 150, 57));
			buscarImagemButton.setIcon(getIcon("/imgs/folder_picture.png"));
			buscarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (getParticipante().getId() != 0){
						anexarImagem();
					}
					else{
						JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), "Primeiro é preciso cadastrar a pessoa!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);						
					}
				}

			});
		}
		return buscarImagemButton;
	}

	protected JTextField getCaminhoImgTextField() {
		if (caminhoImgTextField == null) {
			caminhoImgTextField = new JTextField();
			caminhoImgTextField.setEditable(false);
			caminhoImgTextField.setBounds(new Rectangle(67, 204, 423, 22));
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
			caminhoImgTextFieldLabel.setBounds(new Rectangle(10, 204, 55, 22));
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
	

	private JButton anexar;
	private ListaImagemDialog dialog;
	public void anexarImagem(){		
		try {			
			dialog = new ListaImagemDialog();
			if (anexar == null){				 
				anexar = new JButton("Anexar na formação");
				anexar.addActionListener(new AnexarImagemActionListener());	
				anexar.setIcon(getIcon("/imgs/picture_link.png"));
			}
			
			dialog.getImageListForm().getButtonsPanel().add(anexar,0);
			dialog.setVisible(true);
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private class AnexarImagemActionListener implements ActionListener{
		
		public AnexarImagemActionListener(){	
			ilf = dialog.getImageListForm();			
		}
		private ImageListForm ilf;
		
		@Override
		public void actionPerformed(ActionEvent evt) {
					
			int rows[] = ilf.getBaseTable().getSelectedRows();
			if (rows != null){
				try {
					Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[0]);
					RemotePessoaService.getInstance().mergePersonalImage(im.getId(),getParticipante().getId());
					getParticipante().setLogotipo(im);
					getImgLabelImage().setIcon(im.getSmallImageIcon(false));
					getImgLabelImage().updateUI();
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Fotografia", "Imagem anexada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				try {
					AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm().getButtonsPanel().remove(anexar);
				} catch (Exception e) {
					e.printStackTrace();
				}								
			}
			AdapitVirtualFrame.getInstance().getListaImagensFrame().dispose();
		}
	}

/*	private JFileChooser jfc;

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
			getParticipante().setLogotipo(img);
			updateImage();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateImage() {
		if (getParticipante().getLogotipo() != null)
			try {
				try {
					getParticipante().getLogotipo().setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(getParticipante().getLogotipo().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.getCaminhoImgTextField().setText(getParticipante().getLogotipo().getPath());
				getImgLabelImage().setIcon(getParticipante().getLogotipo().getSmallImageIcon(true));
				//getImageLabel().setIcon(pessoaEmDivulgacao.getSmallIcon(true));
				getImgLabelImage().updateUI();
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else{
			getImgLabelImage().setIcon(null);
			//getImageLabel().setIcon(null);
		}		
	}

	*/

	private void updateImage(boolean b) {
		if(getParticipante().getId() != 0){
			try {
				Imagem im =  null;
				try {
					im = RemotePessoaService.getInstance().getPersonalImage(getParticipante().getId());
				} catch (NullPointerException e1) {
					//e1.printStackTrace();
				}catch (Exception e1) {
					e1.printStackTrace();
				}
				if (im != null) {
					getParticipante().setLogotipo(im);
					try {
						im.setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(im.getId()));
						this.getCaminhoImgTextField().setText(im.getPath());
						getImgLabelImage().setIcon(im.getSmallImageIcon(b));
						getImgLabelImage().updateUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {
					this.getCaminhoImgTextField().setText("");
					getImgLabelImage().setIcon(null);
					getImgLabelImage().updateUI();
					//getImageLabel().setIcon(pessoaEmDivulgacao.getSmallIcon(true));
				}
			} catch (HeadlessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Instrutor getParticipante(){
		if (usuario != null && usuario.getDadosPessoais() != null)
			return (Instrutor) usuario.getDadosPessoais();
		if (getCadastrarLeiloeiroPanel().getInstrutor() != null)
			return getCadastrarLeiloeiroPanel().getInstrutor();
		if (instrutor != null) return instrutor;
		return null;
	}
	
	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;
		
	private JPanel dadosComitentePanel = null;

}  //  @jve:decl-index=0:visual-constraint="10,10"