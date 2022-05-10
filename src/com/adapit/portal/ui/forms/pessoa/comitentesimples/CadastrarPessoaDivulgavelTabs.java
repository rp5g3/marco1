package com.adapit.portal.ui.forms.pessoa.comitentesimples;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.ComitenteSimples;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.services.validation.FieldMsgValidation;
import com.adapit.portal.services.validation.FieldMsgValidationException;
import com.adapit.portal.ui.forms.pessoa.AbstractComitenteImage;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings("serial")
public class CadastrarPessoaDivulgavelTabs  extends JPanel{
	
	private JTabbedPane tabbedPane;
		
	private JPanel dadosPessoaisPanel;
	
	private DadosPessoaDivulgavelPessoaFisicaPanel cadastrarDadosPessoaFisicaPanel;
		
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	//private AdminEnderecoCadastreForm adminEnderecoCadastreForm;
	
	private JPanel cadastreButtonsPanel;
	
	private JButton insertButton;
	
	private JButton newButton;
	
	private JButton deleteButton;
	
	//private JButton listarUsuariosButton;

	public PessoaEmDivulgacao pessoaEmDivulgacao;
	
	public PersonType personType = PersonType.Fisica;  //  @jve:decl-index=0:
	
	public CadastrarPessoaDivulgavelTabs(PersonType pt){
		this.personType = pt;
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(564, 424));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getTabbedPane());
		add(getCadastreButtonsPanel());
	}
	
	protected JTabbedPane getTabbedPane(){
		if(tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(559, 340));
			tabbedPane.setLocation(new Point(3, 5));
			//tabbedPane.add(getUserDataPanel(),"Dados de Usuário");
			tabbedPane.add(getDadosPessoaisPanel(),"Dados do PessoaEmDivulgacao");
			tabbedPane.add(getComitenteDataPanel(),"Imagem do PessoaEmDivulgacao");
			//tabbedPane.add(getAdminEnderecoCadastreForm(),"Endereço");
		}
		return tabbedPane;
	}
	
	/*protected JPanel getUserDataPanel(){

		if(userDataPanel == null){
			userDataPanel = new JPanel();
			userDataPanel.setSize(new java.awt.Dimension(350,230));
			userDataPanel.setLocation(new java.awt.Point(0,0));
			userDataPanel.setLayout(null);
			userDataPanel.add(getAdminUserDataForm());
		}
		return userDataPanel;
	}*/
	
	/*protected AdminUserDataForm getAdminUserDataForm(){

		if(adminUserDataForm == null){
			adminUserDataForm = new AdminUserDataForm();
			adminUserDataForm.setLayout(null);
			adminUserDataForm.setBounds(new Rectangle(15, 0, 520, 230));
			((JComboBox)adminUserDataForm.getRoleComboBox()).setSelectedItem("COMITENTE");
			((JComboBox)adminUserDataForm.getRoleComboBox()).setEnabled(false);
			adminUserDataForm.add(getInativoRadioButton(), null);
			adminUserDataForm.add(getAtivoRadioButton(), null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAtivoRadioButton());
			bg.add(getInativoRadioButton());
		}
		return adminUserDataForm;
	}*/
	
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
	
	protected DadosPessoaDivulgavelPessoaFisicaPanel getComitenteDadosPessoaFisicaPanel(){
		if(cadastrarDadosPessoaFisicaPanel == null){
			cadastrarDadosPessoaFisicaPanel = new DadosPessoaDivulgavelPessoaFisicaPanel(this);
			//cadastrarDadosPessoaFisicaPanel.getFisica().setProfissao("Empresário");
			cadastrarDadosPessoaFisicaPanel.setLayout(null);
			cadastrarDadosPessoaFisicaPanel.setBounds(new Rectangle(15, 20, 520, 250));
		}
		return cadastrarDadosPessoaFisicaPanel;
	}
	
	private DadosPessoaDivulgavelPessoaJuridicaPanel dadosPessoaDivulgavelPessoaJuridicaPanel;
	
	protected DadosPessoaDivulgavelPessoaJuridicaPanel getComitenteDadosPessoaJuridicaPanel(){
		if (dadosPessoaDivulgavelPessoaJuridicaPanel == null){
			dadosPessoaDivulgavelPessoaJuridicaPanel = new DadosPessoaDivulgavelPessoaJuridicaPanel(this);
			dadosPessoaDivulgavelPessoaJuridicaPanel.setBounds(new Rectangle(15, 20, 520, 250));
		}
		return dadosPessoaDivulgavelPessoaJuridicaPanel;
	}
		
	/*protected AdminEnderecoCadastreForm getAdminEnderecoCadastreForm(){
		if(adminEnderecoCadastreForm == null){
			adminEnderecoCadastreForm = new AdminEnderecoCadastreForm();
			adminEnderecoCadastreForm.setSize(new java.awt.Dimension(150,20));
			adminEnderecoCadastreForm.setLocation(new java.awt.Point(15,422));
			adminEnderecoCadastreForm.getEnderecoPanel().setLocation(120,(int) adminEnderecoCadastreForm.getEnderecoPanel().getLocation().getY());
			adminEnderecoCadastreForm.setLayout(null);
		}
		return adminEnderecoCadastreForm;
	}*/
	
	protected JPanel getCadastreButtonsPanel(){

		if(cadastreButtonsPanel == null){
			cadastreButtonsPanel = new JPanel();
			cadastreButtonsPanel.setSize(new Dimension(556, 39));
			cadastreButtonsPanel.setLocation(new Point(3, 345));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.add(getInsertButton());
			cadastreButtonsPanel.add(getNewButton());
			cadastreButtonsPanel.add(getDeleteButton());
			//cadastreButtonsPanel.add(getListarUsuariosButton());
			//cadastreButtonsPanel.add(getDeactivateButton());
		}
		return cadastreButtonsPanel;
	}
	
	/*private JButton deactivateButton;
	protected JButton getDeactivateButton(){

		if(deactivateButton == null){
			deactivateButton = new JButton("Desativar");
			deactivateButton.setSize(new java.awt.Dimension(80,22));
			deactivateButton.setLocation(new java.awt.Point(0,0));
			deactivateButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_deactive.png",20,20));
			
			//deactivateButton.setEnabled(false);
			deactivateButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					DesativarUsuarioDialog d = getDesativarUsuarioDialog();
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
						if (deactivateButton.getText().equals("Desativar"))
							try {
								d.getDeactivationReasonCadastreForm().newRegister(usuario);
							} catch (Exception e) {
								e.printStackTrace();
							}						
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
					getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_add.png",20,20));
				}				
			});
			desativarUsuarioDialog.getDeactivationReasonCadastreForm().getActivateButton().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					usuario.setActive(desativarUsuarioDialog.getDeactivationReasonCadastreForm().getUsuario().getActive());
					//editRegister();
					getDeactivateButton().setText("Desativar");
					getDeactivateButton().setIcon(AdapitVirtualFrame.getIcon("/imgs/vcard_delete.png",20,20));
				}				
			});
			
		}
		return desativarUsuarioDialog;
	}*/
	
	public JButton getInsertButton(){

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
					//getTabbedPane().setIconAt(2, null);
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
	
	/*protected JButton getListarUsuariosButton(){

		if(listarUsuariosButton == null){
			listarUsuariosButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.CadastrarUsuarioAdmin.Cancelar"));
			listarUsuariosButton.setSize(new java.awt.Dimension(80,22));
			listarUsuariosButton.setText("Listar Usuários");
			listarUsuariosButton.setLocation(new java.awt.Point(0,66));
			listarUsuariosButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_go.png",20,20));
			listarUsuariosButton.addActionListener(new ListarUsuariosAction());
		}
		return listarUsuariosButton;
	}*/
	
/*	private class ListarUsuariosAction extends AbstractAction{

		@Override
		protected void doAction(ActionEvent e) {
			AdapitVirtualFrame.getInstance().listarUsuarios();
		}
		
	}*/
	


	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;
	
	/**
	 * This method initializes apresentacaoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getComitenteDataPanel() {
		if (comitenteDataPanel == null) {
			descricaoLabel = new JLabel();
			descricaoLabel.setBounds(new Rectangle(10, 10, 61, 17));
			descricaoLabel.setText("Descrição:");
			comitenteDataPanel = new JPanel();
			comitenteDataPanel.setLayout(null);
			comitenteDataPanel.add(descricaoLabel, null);
			comitenteDataPanel.add(getDescricaoScrollPane(), null);
			comitenteDataPanel.add(getBuscarImagemButton(), null);
			comitenteDataPanel.add(getCaminhoImgTextFieldLabel(), null);
			comitenteDataPanel.add(getCaminhoImgTextField(), null);
			comitenteDataPanel.add(getImageMainPanel(), null);
			
		}
		return comitenteDataPanel;
	}
	
	JPanel imgPanelImage;

	protected JPanel getImageMainPanel() {

		if (imgPanelImage == null) {
			imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory.createLineBorder(Color.blue));
			imgPanelImage.setBounds(new Rectangle(315, 2, 200, 200));
			imgPanelImage.add(getImgLabelImage(), BorderLayout.CENTER);
		}
		return imgPanelImage;
	}

	/*protected JButton getBuscarImagemButton() {

		if (buscarImagemButton == null) {
			buscarImagemButton = new JButton(messages.getMessage("Buscar"));
			buscarImagemButton = new JButton("Buscar Imagem");
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setBounds(new Rectangle(147, 136, 154, 63));
			buscarImagemButton.setText("Buscar Logotipo");
			buscarImagemButton.setIcon(getIcon("/imgs/folder_picture.png"));
			buscarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					imageable.browseImage();
				}

			});
		}
		return buscarImagemButton;
	}*/

	protected JTextField getCaminhoImgTextField() {
		if (caminhoImgTextField == null) {
			caminhoImgTextField = new JTextField();
			caminhoImgTextField.setEditable(false);
			caminhoImgTextField.setBounds(new Rectangle(68, 204, 447, 22));
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
			imgLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			imgLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
			
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

	/*public void browseImage() {
		try {			
			int resp = getJfc().showOpenDialog(this);
			if (resp == JFileChooser.APPROVE_OPTION){
				File f = getJfc().getSelectedFile();
				this.getCaminhoImgTextField().setText(f.toURI().getPath());
				Imagem img = new Imagem();
				img.setFullImageBytes(f);
				getComitente().setLogotipo(img);
				updateImage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateImage() {
		if (getComitente().getLogotipo() != null)
			try {
				try {
					getComitente().getLogotipo().setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(getComitente().getLogotipo().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.getCaminhoImgTextField().setText(getComitente().getLogotipo().getPath());
				getImgLabelImage().setIcon(getComitente().getLogotipo().getSmallImageIcon(true));
				getImgLabelImage().updateUI();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else{
			getImgLabelImage().setIcon(null);
		}	
	}

	private void updateImage(boolean b) {
		{
			try {
				if (getComitente().getLogotipo() != null) {
					this.getCaminhoImgTextField().setText(getComitente().getLogotipo().getPath());
					getImgLabelImage().setIcon(getComitente().getLogotipo().getSmallImageIcon(b));
					getImgLabelImage().updateUI();
				} else {
					this.getCaminhoImgTextField().setText("");
					getImgLabelImage().setIcon(getComitente().getLogotipo().getSmallImageIcon(true));
					getImgLabelImage().updateUI();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setNullImage() {
		try {
			this.getCaminhoImgTextField().setText("");
			getImgLabelImage().setIcon(null);
			getImgLabelImage().updateUI();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	/**
	 * This method initializes descricaoComitenteTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JScrollPane descricaoScrollPane;
	public JScrollPane getDescricaoScrollPane() {
		if (descricaoScrollPane == null) {
			descricaoScrollPane = new JScrollPane();
			descricaoScrollPane.setBounds(new Rectangle(80, 10, 220, 100));
			descricaoScrollPane.setViewportView(getDescricaoComitenteTextArea());
		}
		return descricaoScrollPane;
	}
	
	@SuppressWarnings("unchecked")
	public JComponent getDescricaoComitenteTextArea() {
		if (descricaoComitenteTextArea == null) {
			descricaoComitenteTextArea = new JTextArea();
			descricaoComitenteTextArea.setBounds(new Rectangle(80, 10, 220, 100));
			this.binder.addBindProperty(this.getComitente(),this.descricaoComitenteTextArea, "descricao");			
			this.hashComps.put("descricao", this.descricaoComitenteTextArea);
			JWarningComponent warn = new JWarningComponent( this.descricaoComitenteTextArea);
			warn.setBounds(80, 10, 220, 100);
			return warn;
		}
		return descricaoComitenteTextArea;
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

	public void newRegister(){		
		getInsertButton().setText(messages.getMessage("Cadastrar"));
		imageable.setNullImage();
		descricaoComitenteTextArea.setText("");
		getDeleteButton().setEnabled(false);
		getNewButton().setEnabled(false);
		//getAdminEnderecoCadastreForm().newRegister();
		if (personType == PersonType.Fisica){
			getComitenteDadosPessoaFisicaPanel().newRegister();			
		}
		else{
			getComitenteDadosPessoaJuridicaPanel().newRegister();			
		}
	}
	
	public void editRegister(PessoaEmDivulgacao pessoaEmDivulgacao){
		getInsertButton().setText(messages.getMessage("Atualizar"));
		this.pessoaEmDivulgacao = pessoaEmDivulgacao;
		getDeleteButton().setEnabled(true);
		getNewButton().setEnabled(true);
		imageable.setNullImage();
		imageable.updateImage(true);
		
		descricaoComitenteTextArea.setText(pessoaEmDivulgacao.getDescricao());
		/*Endereco ender = null;
		try {
			ender = RemotePessoaService.getInstance().getEnderecoByPessoaId(getComitente().getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (ender != null)
			getAdminEnderecoCadastreForm().editRegister(ender);*/
		
		if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().editRegister(getComitente());
		else getComitenteDadosPessoaJuridicaPanel().editRegister(getComitente());
		
	}
	


	private JPanel comitenteDataPanel = null;
	
	private JLabel descricaoLabel = null;

	private JTextArea descricaoComitenteTextArea = null;

	
	private class CadastrarAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			try {
				cadastrar();
			} catch (Exception e1) {
				if (!e1.getMessage().equals("Erros de formulário"))
					e1.printStackTrace();
			}
		}		
	}
	
	private class RemoverAction extends AbstractAction{
		@Override
		protected void doAction(ActionEvent e) {
			remover();
		}		
	}
	
	private PessoaDivulgavelFilter pessoaDivulgavelFilter;
	public void remover(){
		
		try {
			
			if (pessoaEmDivulgacao.getId() == 0){
				return;
			}else{
				try {
					JOptionPane.showConfirmDialog(this, "Remover este pessoaEmDivulgacao?");
					RemoteServicesUtility.getInstance().delete(pessoaEmDivulgacao);
					JOptionPane.showMessageDialog(this, "Operação realizada com sucesso!");
					if (pessoaDivulgavelFilter != null){
						pessoaDivulgavelFilter.refresh();
						pessoaDivulgavelFilter.editFirst();
					}
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, "Operação não pode ser realizada!","Problemas ao remover pessoaEmDivulgacao",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
		getTabbedPane().setIconAt(2, null);
		getTabbedPane().setIconAt(1, null);
		getTabbedPane().setIconAt(0, null);
		
		
		try {
		
			if (pessoaEmDivulgacao.getId() == 0){
				AdapitVirtualFrame.getInstance().displayErrorMsg("Não foi possível remover o pessoaEmDivulgacao");
				AdapitVirtualFrame.getInstance().showErrorDialog("Operação: remover pessoaEmDivulgacao", "O identificar do usuário não foi informado");
			}else{
				AdapitVirtualFrame.getInstance().beginStatusBar("Removendo pessoaEmDivulgacao");
				Session s =LocalServicesUtility.getInstance().openSession();
				try {
					s.beginTransaction();
					s.delete(pessoaEmDivulgacao);
					s.getTransaction().commit();
					newRegister();
					AdapitVirtualFrame.getInstance().setOperationMessage("PessoaEmDivulgacao removido com sucesso");
					AdapitVirtualFrame.getInstance().showOperationSucess();
				} catch (RuntimeException e) {
					AdapitVirtualFrame.getInstance().displayErrorMsg("Não foi possível remover o pessoaEmDivulgacao");
					AdapitVirtualFrame.getInstance().showErrorDialog("Erro de base de dados", "Problemas com acesso à base de dados do pessoaEmDivulgacao");
				}finally{
					s.close();
				}
				AdapitVirtualFrame.getInstance().endStatusBar("Removendo pessoaEmDivulgacao");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}*/		
	}
	
	
	private Icon warn = getIcon("/imgs/warn.png");
	private StringBuffer errorMsgs = null;

	private ComitenteSimples validatePessoaData(){
		ComitenteSimples p = null;
		try {			
			if (personType == PersonType.Fisica) p = getComitenteDadosPessoaFisicaPanel().cadastrePessoa();
			else p = getComitenteDadosPessoaJuridicaPanel().cadastrePessoa();
		} catch (Exception e) {
			//e.printStackTrace();
			
		}
		return p;
	}
	
	
	
	/*private Endereco validateEnderecoData(){
		Endereco ender = null;
		try {
			ender = getAdminEnderecoCadastreForm().cadastreEndereco();
		} catch (Exception e) {
			e.printStackTrace();
			getTabbedPane().setIconAt(2, warn);
			if (errorMsgs == null) errorMsgs=  new StringBuffer();
			errorMsgs.append(e.getMessage());
			AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
			AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
		}
		return ender;		
	}*/
	
	public void saveData(ComitenteSimples p){
		if (p!= null)try {
			RemotePessoaService pessoaService = RemotePessoaService.getInstance();
			try{
				p = (ComitenteSimples) pessoaService.saveOrUpdate(p);	
				
				//editRegister(getComitente());		
				
				//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
				AdapitVirtualFrame.getInstance().setOperationMessage("Usuário cadastrado com sucesso");
				AdapitVirtualFrame.getInstance().showOperationSucess();
				if (pessoaDivulgavelFilter != null){
					pessoaDivulgavelFilter.refresh();
					pessoaDivulgavelFilter.edit(p);
				}
			}catch(FieldMsgValidationException ex){
				Hashtable<String,String> ht = ex.getErrors();
				if (ht != null && ht.size() > 0){
					for(String key : ht.keySet()){
						if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning(key,ht.get(key));
						else getComitenteDadosPessoaJuridicaPanel().reportWarning(key,ht.get(key));
						
					}
				}
				getTabbedPane().setIconAt(0, warn);
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
					getTabbedPane().setIconAt(0, warn);
					//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
					AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário!"+'\n'+"Verifique se já não existe alguém "+'\n'+"cadastrado com o mesmo cpf, rg ou email");
					AdapitVirtualFrame.getInstance().showErrorDialog("Dados duplicados o usuário", "Não foi possível cadastrar o usuário pois já existem registros com os mesmos dados");
				}else{
					//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
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
					getTabbedPane().setIconAt(0, warn);
					//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
					AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o usuário!"+'\n'+"Verifique se já não existe alguém "+'\n'+"cadastrado com o mesmo cpf, rg ou email");
					AdapitVirtualFrame.getInstance().showErrorDialog("Dados duplicados o usuário", "Não foi possível cadastrar o usuário pois já existem registros com os mesmos dados");
				}else{
					//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
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
			//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
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
		if (tp == null){
			FieldMsgValidation ex = pessoaService.validateUnique(-1,-1,null,null,p.getId(),email);
			if (ex != null){
				Hashtable<String,String> ht = ex.getErrors();
				if (ht != null && ht.size() > 0){
					for(String key : ht.keySet()){
						if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning(key,ht.get(key));
						else getComitenteDadosPessoaJuridicaPanel().reportWarning(key,ht.get(key));
					}
				}
				getTabbedPane().setIconAt(1, warn);
				throw new FieldMsgValidationException(ht);
			}
			
		}else{ 
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
						if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning(key,ht.get(key));
						else getComitenteDadosPessoaJuridicaPanel().reportWarning(key,ht.get(key));
					}
				}
				getTabbedPane().setIconAt(1, warn);
				throw new FieldMsgValidationException(ht);
			}
		}
		/*if (tp instanceof Juridica){
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
					if (personType == PersonType.Fisica) getComitenteDadosPessoaFisicaPanel().reportWarning(key,ht.get(key));
					else getComitenteDadosPessoaJuridicaPanel().reportWarning(key,ht.get(key));
				}
			}
			getTabbedPane().setIconAt(1, warn);
			throw new FieldMsgValidationException(ht);
		}*/
	}
	
	public void cadastrar() throws Exception{
		//AdapitVirtualFrame.getInstance().beginStatusBar("Verificando a corretude dos dados");
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
		
		//getTabbedPane().setIconAt(2, null);
		getTabbedPane().setIconAt(1, null);
		getTabbedPane().setIconAt(0, null);
		
		errorMsgs = null;
		
		ComitenteSimples p = validatePessoaData();	
				
		if (errorMsgs != null){
			//AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			AdapitVirtualFrame.getInstance().setOperationMessage("Cadastro suspenso ... verifique a corretude dos dados");
			AdapitVirtualFrame.getInstance().showErrorCamposInvalidosWithinTabs();
			updateUI();
			throw new Exception("Erros de formulário");
		}else{
			
			
			//AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			
			//AdapitVirtualFrame.getInstance().beginStatusBar("Salvando os dados");
			
			((ComitenteSimples)p).setLogotipo(getComitente().getLogotipo());
			

			try {
				//AdapitVirtualFrame.getInstance().beginStatusBar("Verificando repetição de dados");
				try {
					validateUnique(p);
					//AdapitVirtualFrame.getInstance().endStatusBar("Verificando repetição de dados");
				} catch (RuntimeException e) {
					e.printStackTrace();
					//AdapitVirtualFrame.getInstance().endStatusBar("Verificando repetição de dados");
					return;
				}
				//AdapitVirtualFrame.getInstance().beginStatusBar("Salvando os dados do usuário");
				saveData(p);
				
				//AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do usuário");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*@Deprecated
	public void cadastrar2(){
		AdapitVirtualFrame.getInstance().beginStatusBar("Verificando a corretude dos dados");
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
		Icon warn = getIcon("/imgs/warn.png");
		getTabbedPane().setIconAt(2, null);
		getTabbedPane().setIconAt(1, null);
		getTabbedPane().setIconAt(0, null);
		StringBuffer errorMsgs = null;
		
		Pessoa p=null;
		Endereco ender=null;
		
		
		try {			
			if (personType == PersonType.Fisica) p = getComitenteDadosPessoaFisicaPanel().cadastrePessoa();
			else p = getComitenteDadosPessoaJuridicaPanel().cadastrePessoa();
		} catch (Exception e) {
			e.printStackTrace();
			if (tipoComitenteComboBox.getSelectedItem() == null) getTabbedPane().setIconAt(1, warn);
			else
			{
				getTabbedPane().setIconAt(0, warn);
				if (errorMsgs == null) errorMsgs=  new StringBuffer();
				errorMsgs.append(e.getMessage());
				AdapitVirtualFrame.getInstance().displayErrorMsg(e.getMessage());
				AdapitVirtualFrame.getInstance().appendErrorMsg(e.getMessage());
			}
		}
		
		
		try {
			ender = getAdminEnderecoCadastreForm().cadastreEndereco();
		} catch (Exception e) {
			e.printStackTrace();
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
			AdapitVirtualFrame.getInstance().endStatusBar("Verificando a corretude dos dados");
			
			AdapitVirtualFrame.getInstance().beginStatusBar("Salvando os dados do pessoaEmDivulgacao");
			try {
				p.setEndereco(ender);
				ender.setPais(Pais.Brasil);
				if (p.getId() == 0){
					Session s = null;
					try {
						s=LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						s.save(ender);
						s.save(p);
						//s.merge(ender);
						s.getTransaction().commit();
					} catch (RuntimeException e) {
						s.getTransaction().rollback();
						e.printStackTrace();
					}finally{
						if (s != null) s.close();
					}
				}else{
					Session s = null;
					try {
						s=LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						s.update(p);					
						
						s.getTransaction().commit();
					} catch (RuntimeException e) {
						s.getTransaction().rollback();
						e.printStackTrace();
						
						throw new Exception("Não foi possível autalizar os dados do pessoaEmDivulgacao");
					}finally{
						if (s != null) s.close();
					}
				}
				//p = (Pessoa) LocalServicesUtility.getInstance().createOrUpdate(p);
				editRegister((PessoaEmDivulgacao) p);		
				
				
				AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do pessoaEmDivulgacao");
				AdapitVirtualFrame.getInstance().setOperationMessage("PessoaEmDivulgacao cadastrado com sucesso");
				AdapitVirtualFrame.getInstance().showOperationSucess();
			} catch (Exception e) {
				e.printStackTrace();
				AdapitVirtualFrame.getInstance().endStatusBar("Salvando os dados do pessoaEmDivulgacao");
				AdapitVirtualFrame.getInstance().setOperationMessage("Problemas ao cadastrar o pessoaEmDivulgacao");
				AdapitVirtualFrame.getInstance().showErrorDialog("Problemas para recuperar o pessoaEmDivulgacao", e.getMessage());
			}
		}
	}*/

	
	
	private PessoaEmDivulgacao getComitente(){
		if (pessoaEmDivulgacao == null){
			if (personType == PersonType.Fisica){
				pessoaEmDivulgacao = (PessoaEmDivulgacao) getComitenteDadosPessoaFisicaPanel().getComitente();
			}
			else{
				pessoaEmDivulgacao = (PessoaEmDivulgacao) getComitenteDadosPessoaJuridicaPanel().getComitente();
			}
		}
		return pessoaEmDivulgacao;
	}

	public void setComitente(PessoaEmDivulgacao c) {
		this.pessoaEmDivulgacao = c;
	}

	public PessoaDivulgavelFilter getComitenteFilter() {
		return pessoaDivulgavelFilter;
	}

	public void setComitenteFilter(PessoaDivulgavelFilter pessoaDivulgavelFilter) {
		this.pessoaDivulgavelFilter = pessoaDivulgavelFilter;
	}
	
	
	protected JButton getBuscarImagemButton() {
		if (buscarImagemButton == null) {
			buscarImagemButton = new JButton(messages.getMessage("Buscar"));
			buscarImagemButton = new JButton("Buscar Imagem");
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setBounds(new Rectangle(147, 136, 154, 63));
			buscarImagemButton.setIcon(getIcon("/imgs/folder_picture.png"));
			buscarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (getComitente().getId() != 0){
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
			super(CadastrarPessoaDivulgavelTabs.this, "Anexar no PessoaEmDivulgacao");
		}

		@Override
		public JButton getBuscarImagemButton() {
			return CadastrarPessoaDivulgavelTabs.this.getBuscarImagemButton();
		}

		@Override
		public JTextField getCaminhoImgTextField() {
			return CadastrarPessoaDivulgavelTabs.this.getCaminhoImgTextField();
		}

		@Override
		public JLabel getImgLabelImage() {
			return CadastrarPessoaDivulgavelTabs.this.getImgLabelImage();
		}

		@Override
		public JFileChooser getJfc() {
			return CadastrarPessoaDivulgavelTabs.this.getJfc();
		}

		@Override
		public PessoaEmDivulgacao getPersonComitente() {
			return getComitente();
		}
		
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"