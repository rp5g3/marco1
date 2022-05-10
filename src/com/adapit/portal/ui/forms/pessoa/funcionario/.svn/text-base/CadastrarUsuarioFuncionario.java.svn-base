package com.adapit.portal.ui.forms.pessoa.funcionario;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Funcionario;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteAdapitAutenticateUserService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.ui.forms.usuario.AdminEnderecoCadastreForm;
import com.adapit.portal.ui.forms.usuario.AdminUserDataForm;
import com.adapit.portal.ui.forms.usuario.DesativarUsuarioDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;


@SuppressWarnings("serial")
public class CadastrarUsuarioFuncionario extends JPanel{
	
	private JTabbedPane tabbedPane;
	
	private JPanel userDataPanel;
	
	private AdminUserDataForm adminUserDataForm;
	
	private JPanel dadosPessoaisPanel;
	
	private FuncionarioCadastreForm pessoaFisicaCadastreForm;
	
	@SuppressWarnings({ "unchecked", "unused" })
	private SwingBinder binder = new SwingBinder();
	@SuppressWarnings({ "unchecked", "unused" })
	private Map hashComps = new java.util.HashMap();
	
	/*private JLabel compComboBox3Label;
	*/
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	/*private JButton mostrarTipoPessoaButton;
	*/
	private AdminEnderecoCadastreForm adminEnderecoCadastreForm;
	
	private JPanel cadastreButtonsPanel;
	
	private JButton insertButton;
	
	private JButton newButton;
	
	private JButton deleteButton;
	
	private JButton listarUsuariosButton;


	
	public CadastrarUsuarioFuncionario(){	
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(564, 390));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getTabbedPane());
		add(getCadastreButtonsPanel());
		//newRegister();
	}
	
	protected JTabbedPane getTabbedPane(){

		if(tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(559, 344));
			tabbedPane.setLocation(new java.awt.Point(0,0));
			
			tabbedPane.add(getUserDataPanel(),"Dados de Usuário");
			tabbedPane.add(getDadosPessoaisPanel(),"Dados Pessoais");
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
			adminUserDataForm.setBounds(new Rectangle(15, 0, 520, 303));
			adminUserDataForm.add(getAtivoRadioButton(), null);
			adminUserDataForm.add(getInativoRadioButton(), null);
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
			dadosPessoaisPanel.add(getPessoaFisicaCadastreForm());
			/*dadosPessoaisPanel.add(getCompComboBox3());
			dadosPessoaisPanel.add(getCompComboBox3Label());
			dadosPessoaisPanel.add(getMostrarTipoPessoaButton());*/
		}
		return dadosPessoaisPanel;
	}
	
	protected FuncionarioCadastreForm getPessoaFisicaCadastreForm(){

		if(pessoaFisicaCadastreForm == null){
			pessoaFisicaCadastreForm = new FuncionarioCadastreForm(/*new Funcionario()*/);
			pessoaFisicaCadastreForm.setLayout(null);
			pessoaFisicaCadastreForm.setBounds(new Rectangle(15, 20, 528, 284));
		}
		return pessoaFisicaCadastreForm;
	}
	
	/*protected JComboBox getCompComboBox3(){

		if(compComboBox3 == null){
			compComboBox3 = new JComboBox();
			compComboBox3.setSize(new java.awt.Dimension(160,20));
			compComboBox3.setLocation(new java.awt.Point(120,135));
			return compComboBox3;
		}
		return compComboBox3;
	}*/
	
	/*protected JLabel getCompComboBox3Label(){

		if(compComboBox3Label == null){
			compComboBox3Label = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.TipodePessoa"));
			compComboBox3Label.setSize(new java.awt.Dimension(100,20));
			compComboBox3Label.setLocation(new java.awt.Point(15,135));
			compComboBox3Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return compComboBox3Label;
	}*/
	
	/*protected JButton getMostrarTipoPessoaButton(){

		if(mostrarTipoPessoaButton == null){
			mostrarTipoPessoaButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Mostrarosdadosdotipodepessoa"));
			mostrarTipoPessoaButton.setSize(new java.awt.Dimension(266,24));
			mostrarTipoPessoaButton.setLocation(new java.awt.Point(15,155));
		}
		return mostrarTipoPessoaButton;
	}*/
	
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
			cadastreButtonsPanel.setSize(new Dimension(559, 40));
			cadastreButtonsPanel.setLocation(new Point(1, 349));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.add(getInsertButton());
			cadastreButtonsPanel.add(getNewButton());
			cadastreButtonsPanel.add(getDeleteButton());
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
					List<DeactivationReason> list = null;
					try {
						list = RemoteUserService.getInstance().listDeactivationReasonByUserLogin(usuario.getLogin());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					usuario.setDeactivationReasonList(list);
					/*Session s = LocalServicesUtility.getInstance().openSession();
					
					List<DeactivationReason> list = null;
					try{
						list = s.getNamedQuery("usuario.listDeactivationByLogin")
							.setParameter("userlogin", usuario.getLogin())
							.list();
						usuario.setDeactivationReasonList(list);
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
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
	
	protected JButton getDeleteButton(){

		if(deleteButton == null){
			deleteButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Apagar"));
			deleteButton.setSize(new java.awt.Dimension(80,22));
			//deleteButton.setText("Destaivar Usuário");
			deleteButton.setLocation(new java.awt.Point(0,44));
			deleteButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_delete.png",18,18));
			deleteButton.addActionListener(new RemoverAction());
		}
		return deleteButton;
	}
	
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

	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(353,407));
					gui.add(new CadastrarUsuarioFuncionario(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
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
			//TODO 
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			//TODO 
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	public void newRegister(){
		getAtivoRadioButton().setSelected(true);
		getAtivoRadioButton().setVisible(true);
		getInativoRadioButton().setVisible(true);
		getInsertButton().setText(messages.getMessage("Cadastrar"));
		getAdminUserDataForm().getUsuario().setNewUser(true);
		//getListarUsuariosButton().setEnabled(false);
		getDeleteButton().setEnabled(false);
		getNewButton().setEnabled(false);
		getDeactivateButton().setEnabled(false);
		getAdminEnderecoCadastreForm().newRegister();
		getAdminUserDataForm().newRegister();
		getPessoaFisicaCadastreForm().newRegister();
	}
	
	public void editRegister(){
		getInsertButton().setText(messages.getMessage("Atualizar"));
		getAtivoRadioButton().setVisible(false);
		getInativoRadioButton().setVisible(false);
		getAdminUserDataForm().getUsuario().setNewUser(false);
		//getListarUsuariosButton().setEnabled(true);
		getDeleteButton().setEnabled(true);
		getNewButton().setEnabled(true);
		getAdminEnderecoCadastreForm().editRegister(usuario.getDadosPessoais().getEndereco());
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
		getPessoaFisicaCadastreForm().editRegister(usuario.getDadosPessoais());
		
	}
	
	private Usuario usuario;  //  @jve:decl-index=0:

	private JRadioButton ativoRadioButton = null;

	private JRadioButton inativoRadioButton = null;

	
	private class CadastrarAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			cadastrar();
		}		
	}
	
	private class RemoverAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			remover();
		}		
	}
	
	public void remover(){
		
		/*LeilaoVirtualFrame.getInstance().cleanErrorMsg();
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
				} catch (RuntimeException e) {
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
				if (cpfExists) getPessoaFisicaCadastreForm().reportWarning("cpf");
				if (rgExists) getPessoaFisicaCadastreForm().reportWarning("rg");
				if (emailExists) getPessoaFisicaCadastreForm().reportWarning("email");
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
				if (cpfExists) getPessoaFisicaCadastreForm().reportWarning("cpf");
				if (rgExists) getPessoaFisicaCadastreForm().reportWarning("rg");
				if (emailExists) getPessoaFisicaCadastreForm().reportWarning("email");
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
	
	public void cadastrar(){
		AdapitVirtualFrame.getInstance().beginStatusBar("Verificando a corretude dos dados");
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
		Icon warn = getIcon("/imgs/warn.png");
		getTabbedPane().setIconAt(2, null);
		getTabbedPane().setIconAt(1, null);
		getTabbedPane().setIconAt(0, null);
		StringBuffer errorMsgs = null;
		Usuario user = null;
		Pessoa p=null;
		Endereco ender=null;
		
		
		try {
			p = getPessoaFisicaCadastreForm().cadastrePessoa();			
		} catch (Exception e) {
			getTabbedPane().setIconAt(1, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
		}
		
		try {
			user = getAdminUserDataForm().cadastreUsuario();
			if (getAtivoRadioButton().isVisible()){
				user.setActive(getAtivoRadioButton().isSelected());
				user.setAutenticado(getAtivoRadioButton().isSelected());
			}
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(CadastrarUsuarioAdmin.this, e.getMessage(), "Formulário não preenchido corretamente", JOptionPane.ERROR_MESSAGE);
			getTabbedPane().setIconAt(0, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
		}
		try {
			ender = getAdminEnderecoCadastreForm().cadastreEndereco();
		} catch (Exception e) {
			//JOptionPane.showMessageDialog(CadastrarUsuarioAdmin.this, ex.getMessage(), "Formulário não preenchido corretamente", JOptionPane.ERROR_MESSAGE);
			getTabbedPane().setIconAt(2, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
		}		
		if (errorMsgs != null){
			AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			AdapitVirtualFrame.getInstance().setOperationMessage("Cadastro suspenso ... verifique a corretude dos dados");
			AdapitVirtualFrame.getInstance().showErrorCamposInvalidosWithinTabs();
		}else{
			user.setPassword(Usuario.encript(user.getPassword()));
			user.setPasswordConf(user.getPassword());
			ender.setPais(Pais.Brasil);
			((Funcionario)p).setUser(user);
			AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			
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
			
			/*if (user!= null)try {
				LeilaoVirtualFrame.getInstance().beginStatusBar("Salvando os dados do usuário");
				ender.setPais(Pais.Brasil);
				((Funcionario)p).setUser(user);
					
				saveData(user,p,ender);					
				
			} catch (Exception e) {
				e.printStackTrace();
				LeilaoVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
				LeilaoVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário");
				LeilaoVirtualFrame.getInstance().showErrorDialog("Problemas para recuperar o usuário", "Não foi possível recuperar o usuário que foi cadastrado");
			}*/
		}
	}

	public void setUsuario(Usuario u) {
		this.usuario = u;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"