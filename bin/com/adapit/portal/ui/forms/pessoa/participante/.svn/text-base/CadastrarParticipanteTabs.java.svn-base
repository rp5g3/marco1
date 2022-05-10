package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.TipoComitente;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemoteAdapitAutenticateUserService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.ui.forms.pessoa.AbstractComitenteImage;
import com.adapit.portal.ui.forms.pessoa.BindHandler;
import com.adapit.portal.ui.forms.usuario.AdminEnderecoCadastreForm;
import com.adapit.portal.ui.forms.usuario.AdminUserDataForm;
import com.adapit.portal.ui.forms.usuario.DesativarUsuarioDialog;
import com.adapit.portal.ui.forms.usuario.UserDataChangeListener;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings("serial")
public class CadastrarParticipanteTabs extends JPanel implements UserDataChangeListener, BindHandler{
	
	private JTabbedPane tabbedPane;
	
	private JPanel userDataPanel;
	
	private AdminUserDataForm adminUserDataForm;
	
	private JPanel dadosPessoaisPanel;
	
	private ParticipanteDadosPessoaFisicaPanel cadastrarDadosPessoaFisicaPanel;
		
	protected SwingBinder binder = new SwingBinder();
	
	public SwingBinder getBinder() {
		return binder;
	}

	public Map getHashComps() {
		return hashComps;
	}

	protected Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	
	protected ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private AdminEnderecoCadastreForm adminEnderecoCadastreForm;
	
	private JPanel cadastreButtonsPanel;
	
	private JButton insertButton;
	
	private JButton newButton;
	
	private JButton listarUsuariosButton;

	public Participante comitente;
	
	public PersonType personType = PersonType.Fisica;  //  @jve:decl-index=0:
	
	private ApresentacaoParticipante apresentacaoParticipante=null;
	
	public CadastrarParticipanteTabs(PersonType pt){
		super();
		apresentacaoParticipante= new ApresentacaoParticipante(this);
		this.personType = pt;
		initialize();
	}
	
	public CadastrarParticipanteTabs() {
		super();
		apresentacaoParticipante= new ApresentacaoParticipante(this);
	}

	private void initialize(){
		setSize(new Dimension(564, 434));
		setLocation(new java.awt.Point(0,0));
		setLayout(new BorderLayout());
		add(getTabbedPane(),BorderLayout.CENTER);
		add(getCadastreButtonsPanel(),BorderLayout.SOUTH);
		add(getHelpButton(), BorderLayout.NORTH);
	}
	
	protected JTabbedPane getTabbedPane(){
		if(tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(559, 340));
			tabbedPane.setLocation(new Point(3, 27));
			tabbedPane.add(getUserDataPanel(),"Dados de Usuário");
			tabbedPane.add(getDadosPessoaisPanel(),"Dados Pessoais");
			tabbedPane.add(getDadosComitentePanel(),"Fotografia");
			tabbedPane.add(getAdminEnderecoCadastreForm(),"Endereço");
			tabbedPane.add(apresentacaoParticipante,"Apresentação");
		}
		return tabbedPane;
	}
	
	protected DateHourChooser tempDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);

	protected JPanel getUserDataPanel(){

		if(userDataPanel == null){
			userDataPanel = new JPanel();
			userDataPanel.setSize(new java.awt.Dimension(350,230));
			userDataPanel.setLocation(new java.awt.Point(0,0));
			userDataPanel.setLayout(/*new BorderLayout()*/null);
			userDataPanel.add(getAdminUserDataForm()/*,BorderLayout.CENTER*/);
		}
		return userDataPanel;
	}
	
	protected AdminUserDataForm getAdminUserDataForm(){
		if(adminUserDataForm == null){
			adminUserDataForm = new AdminUserDataForm(this);
			adminUserDataForm.setLayout(null);
			adminUserDataForm.setBounds(new Rectangle(15, 0, 520, 296));
			((JComboBox)adminUserDataForm.getRoleComboBox()).setSelectedItem(UserCadastreType.Cliente.name().replace("_"," "));
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
			dadosPessoaisPanel.setSize(new java.awt.Dimension(307,192));
			dadosPessoaisPanel.setLocation(new java.awt.Point(0,230));
			dadosPessoaisPanel.setLayout(null);
			if (personType == PersonType.Fisica) dadosPessoaisPanel.add(getComitenteDadosPessoaFisicaPanel());
			else dadosPessoaisPanel.add(getComitenteDadosPessoaJuridicaPanel());
			
		}
		return dadosPessoaisPanel;
	}
	
	protected ParticipanteDadosPessoaFisicaPanel getComitenteDadosPessoaFisicaPanel(){
		if(cadastrarDadosPessoaFisicaPanel == null){
			cadastrarDadosPessoaFisicaPanel = new ParticipanteDadosPessoaFisicaPanel(this);
			cadastrarDadosPessoaFisicaPanel.setLayout(null);
			cadastrarDadosPessoaFisicaPanel.setBounds(new Rectangle(15, 20, 520, 340));
		}
		return cadastrarDadosPessoaFisicaPanel;
	}
	
	private ParticipanteDadosPessoaJuridicaPanel comitenteDadosPessoaJuridicaPanel;
	
	protected ParticipanteDadosPessoaJuridicaPanel getComitenteDadosPessoaJuridicaPanel(){
		if (comitenteDadosPessoaJuridicaPanel == null){
			comitenteDadosPessoaJuridicaPanel = new ParticipanteDadosPessoaJuridicaPanel(this);
			comitenteDadosPessoaJuridicaPanel.setBounds(new Rectangle(15, 20, 520, 340));
		}
		return comitenteDadosPessoaJuridicaPanel;
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
			cadastreButtonsPanel.setLocation(new Point(2, 371));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.add(getInsertButton());
			cadastreButtonsPanel.add(getNewButton());
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
			deactivateButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					DesativarUsuarioDialog d = getDesativarUsuarioDialog();
					List<DeactivationReason> list = null;
					try{
						list = RemoteUserService.getInstance()
							.listDeactivationReasonByUserLogin(usuario.getLogin());
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
		if (dadosDivulgacaoPanel == null) {
			dadosDivulgacaoPanel = new JPanel();
			dadosDivulgacaoPanel.setLayout(null);
			dadosDivulgacaoPanel.add(getBuscarImagemButton(), null);
			dadosDivulgacaoPanel.add(getCaminhoImgTextFieldLabel(), null);
			dadosDivulgacaoPanel.add(getCaminhoImgTextField(), null);
			dadosDivulgacaoPanel.add(getImageMainPanel(), null);			
		}
		return dadosDivulgacaoPanel;
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
			this.binder.addBindProperty(this.getParticipante(),this.descricaoComitenteTextField, "descricao");			
			this.hashComps.put("descricao", this.descricaoComitenteTextField);
			JWarningComponent warn = new JWarningComponent( this.descricaoComitenteTextField);
			warn.setBounds(81, 11, 458, 20);
			return warn;
		}
		return descricaoComitenteTextField;
	}

	/**
	 * This method initializes tipoComitenteComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	public JComponent getTipoComitenteComboBox() {
		if (tipoComitenteComboBox == null) {
			tipoComitenteComboBox = new JComboBox();
			tipoComitenteComboBox.setBounds(new Rectangle(81, 38, 221, 20));
			tipoComitenteComboBox.addItem(TipoComitente.Individual_participante.name());			
			this.binder.addBindProperty(this.getParticipante(),this.tipoComitenteComboBox, "tipoComitente");			
			this.hashComps.put("tipoComitente", this.tipoComitenteComboBox);
			JWarningComponent warn = new JWarningComponent( this.tipoComitenteComboBox);
			warn.setBounds(81, 38, 221, 20);
			return warn;
		}
		return tipoComitenteComboBox;
	}

	/**
	 * This method initializes helpButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setBounds(new Rectangle(529, 6, 20, 20));
			helpButton.setIcon(new ImageIcon(getClass().getResource("/imgs/helpicon.png")));
			helpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
				}
			});
		}
		return helpButton;
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

	private CommonData commonData;
	private CommonData getCommonData(){
		if(commonData == null){
			commonData = new CommonData(tabbedPane,comitente,usuario);
		}
		return commonData;
	}
	
	public void newRegister(){
		getAtivoRadioButton().setSelected(true);
		getAtivoRadioButton().setVisible(true);
		getInativoRadioButton().setVisible(true);
		getAdminUserDataForm().getUsuario().setNewUser(true);
		getInsertButton().setText(messages.getMessage("Cadastrar"));
		getNewButton().setEnabled(false);
		getDeactivateButton().setEnabled(false);
		getAdminEnderecoCadastreForm().newRegister();
		getAdminUserDataForm().newRegister();
		getCommonData().removeTabs();
		
		try {
			if (personType == PersonType.Fisica){
				getComitenteDadosPessoaFisicaPanel().newRegister();	
				apresentacaoParticipante.bind(getComitenteDadosPessoaFisicaPanel().getComitente());
			}
			else{
				getComitenteDadosPessoaJuridicaPanel().newRegister();	
				apresentacaoParticipante.bind(getComitenteDadosPessoaJuridicaPanel().getComitente());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void editRegister(){
		getInsertButton().setText(messages.getMessage("Atualizar"));
		getAtivoRadioButton().setVisible(false);
		getInativoRadioButton().setVisible(false);
		getAdminUserDataForm().getUsuario().setNewUser(false);
		try {
			comitente = (Participante) RemotePessoaService.getInstance().initializeParticipante(usuario.getDadosPessoais().getId());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		getNewButton().setEnabled(true);
		Endereco ender = null;
		try {
			ender = RemotePessoaService.getInstance().getEnderecoByPessoaId(usuario.getDadosPessoais().getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ender != null )
			getAdminEnderecoCadastreForm().editRegister(ender);
		
		getAdminUserDataForm().editRegister(usuario);
		getDeactivateButton().setEnabled(true);
		if (usuario.getActive()){			
			getDeactivateButton().setText("Desativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_delete.png",18,18));
		}else{
			getDeactivateButton().setText("Ativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_add.png",18,18));
		}
		
		if (personType == PersonType.Fisica){
			Object[] objs = getComitenteDadosPessoaFisicaPanel().editRegister(usuario.getDadosPessoais());
		}
		else{
			Object[] objs = getComitenteDadosPessoaJuridicaPanel().editRegister(usuario.getDadosPessoais());
		}
		
		try {
			editRegister(comitente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		getCommonData().addTabs(comitente);
	}
	
	public void editRegister(PessoaEmDivulgacao com) throws Exception{
		imageable.setNullImage();
		imageable.updateImage(true);	
		apresentacaoParticipante.bind(comitente);
	}
	
	protected Usuario usuario;  //  @jve:decl-index=0:

	private JRadioButton ativoRadioButton = null;

	private JRadioButton inativoRadioButton = null;

	private JPanel dadosDivulgacaoPanel = null;


	private JTextField descricaoComitenteTextField = null;

	protected JComboBox tipoComitenteComboBox = null;

	
	private class CadastrarAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			try {
				cadastrar();
			} catch (Exception e1) {
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

	}
	
	private Icon warn = getIcon("/imgs/warn.png");
	private StringBuffer errorMsgs = null;

	private JButton helpButton = null;
	
	private Pessoa validatePessoaData(){
		Pessoa p = null;
		try {
			if (personType == PersonType.Fisica) p = getComitenteDadosPessoaFisicaPanel().cadastrePessoa();
			else p = getComitenteDadosPessoaJuridicaPanel().cadastrePessoa();
			if(p instanceof PessoaEmDivulgacao){
				PessoaEmDivulgacao ped = apresentacaoParticipante.reverseBind();
				if(ped != p){
					((PessoaEmDivulgacao)p).setSoftDevExp(ped.isSoftDevExp());
					((PessoaEmDivulgacao)p).setApresentacao(ped.getApresentacao());
					((PessoaEmDivulgacao)p).setDescricao(ped.getDescricao());
					((PessoaEmDivulgacao)p).setSaleExp(ped.isSaleExp());
					((PessoaEmDivulgacao)p).setResearchExp(ped.isResearchExp());
					((PessoaEmDivulgacao)p).setDivulgavel(ped.isDivulgavel());
					((PessoaEmDivulgacao)p).setTrainExp(ped.isTrainExp());
					((PessoaEmDivulgacao)p).setManagerExp(ped.isManagerExp());
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			if (tipoComitenteComboBox != null && tipoComitenteComboBox.getSelectedItem() == null) getTabbedPane().setIconAt(2, warn);
			else{
				getTabbedPane().setIconAt(1, warn);
				if (errorMsgs == null) errorMsgs=  new StringBuffer();
				errorMsgs.append(e.getMessage());
				AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
				AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
			}
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
			//e.printStackTrace();
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
			//e.printStackTrace();
			getTabbedPane().setIconAt(3, warn);
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
				//editRegister();		
				
				if (!usuario.getActive()){
					AdapitVirtualFrame.getInstance().showOperationSucess("Envio de email", "Enviando email para o usuário autenticar o seu cadastro");
					RemoteAdapitAutenticateUserService.getInstance().sendAutenticateUserMsg(user);
				}
				AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
				AdapitVirtualFrame.getInstance().setOperationMessage("Usuário cadastrado com sucesso. Feche a tela e edite o usuário.");
				AdapitVirtualFrame.getInstance().showOperationSucess();
				
			}catch(FieldMsgValidationException ex){
				Hashtable<String,String> ht = ex.getErrors();
				if (ht != null && ht.size() > 0){
					for(String key : ht.keySet()){
						if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning(key,ht.get(key));
						else getComitenteDadosPessoaJuridicaPanel().reportWarning(key,ht.get(key));
						//getCadastrarLeiloeiroPanel().reportWarning(key,ht.get(key));
					}
				}
				getTabbedPane().setIconAt(1, warn);
			}catch(org.hibernate.exception.ConstraintViolationException ex){
				ex.printStackTrace();
				boolean cpfExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getCpf());
				boolean rgExists = pessoaService.cpfExists(((Fisica)p.getTipoPessoa()).getRg());
				boolean emailExists = pessoaService.cpfExists(p.getEmail());
				if (cpfExists){
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning("cpf");
					else getComitenteDadosPessoaJuridicaPanel().reportWarning("cpf");
				}
				if (rgExists){
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning("rg");
					else getComitenteDadosPessoaJuridicaPanel().reportWarning("rg");
				}
				if (emailExists){
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning("email");
					else getComitenteDadosPessoaJuridicaPanel().reportWarning("email");
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
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning("cpf");
					else getComitenteDadosPessoaJuridicaPanel().reportWarning("cpf");
				}
				if (rgExists){
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning("rg");
					else getComitenteDadosPessoaJuridicaPanel().reportWarning("rg");
				}
				if (emailExists){
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning("email");
					else getComitenteDadosPessoaJuridicaPanel().reportWarning("email");
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
			
			((PessoaEmDivulgacao)p).setLogotipo(getParticipante().getLogotipo());
			
			try {
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
	
	public Participante getParticipante(){
		if (comitente == null){
			if (personType == PersonType.Fisica){
				comitente = (Participante) getComitenteDadosPessoaFisicaPanel().getComitente();
			}
			else{
				comitente = (Participante) getComitenteDadosPessoaJuridicaPanel().getComitente();
			}
		}
		return comitente;
	}

	public PessoaEmDivulgacao getComitente() {
		return comitente;
	}
	
	@Override
	public void userDataChanged() {
		usuario = getAdminUserDataForm().getManutencaoDadosUsuarioDialog().getUsuario();
		if (usuario.getActive()){			
			getDeactivateButton().setText("Desativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_delete.png",18,18));
		}else{
			getDeactivateButton().setText("Ativar");
			getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_add.png",18,18));
		}
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
						imageable.anexarImagem();
					}
					else{
						JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), "Primeiro é preciso cadastrar a pessoa!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);						
					}
				}

			});
		}
		return buscarImagemButton;
	}
	private Imageable imageable = new Imageable();

	private class Imageable extends AbstractComitenteImage{

		public Imageable() {
			super(CadastrarParticipanteTabs.this, "Anexar no Participante");
		}

		@Override
		public JButton getBuscarImagemButton() {
			return CadastrarParticipanteTabs.this.getBuscarImagemButton();
		}

		@Override
		public JTextField getCaminhoImgTextField() {
			return CadastrarParticipanteTabs.this.getCaminhoImgTextField();
		}

		@Override
		public JLabel getImgLabelImage() {
			return CadastrarParticipanteTabs.this.getImgLabelImage();
		}

		@Override
		public JFileChooser getJfc() {
			return CadastrarParticipanteTabs.this.getJfc();
		}

		@Override
		public PessoaEmDivulgacao getPersonComitente() {
			return getParticipante();
		}
		
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"