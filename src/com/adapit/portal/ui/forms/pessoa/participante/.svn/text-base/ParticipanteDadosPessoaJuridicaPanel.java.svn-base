package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.brazilutils.br.cpfcnpj.Cnpj;

import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.RepresentanteLegal;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.validation.ValidationException;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.LengthLimitedDocument;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class ParticipanteDadosPessoaJuridicaPanel extends JPanel{
	
	private JTextField nomeTextField;
	
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private Participante participante = new Participante();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel nomeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField razaoSocialTextField;
	
	private Juridica juridica = new Juridica();  //  @jve:decl-index=0:
	
	private JLabel razaoSocialLabel;
	
	private JTextField emailTextField;
	
	private JLabel emailTextFieldLabel;
	
	private JTextField telefoneComercialTextField;
	
	private JLabel telefoneComercialLabel;
	
	private JTextField telefoneTextField;
	
	private JLabel telefoneTextFieldLabel;

	private JLabel cnpjLabel = null;

	private JTextField cnpjTextField = null;

	private JLabel inscEstadualLabel = null;

	private JTextField inscricaoEstadualTextField = null;

	private JLabel repLegalLabel = null;

	private JComboBox representanteLegalComboBox = null;


	private JLabel ramoAtivLabel = null;

	private JTextField ramoAtividadeTextField = null;

	private JLabel faxLabel = null;

	private JTextField faxTextField = null;
	
	CadastrarParticipanteTabs cadastrarComitenteParticipanteForm;
	//CadastrarComitenteForm cadastrarComitenteForm;

	private JButton novoRepLegalButton = null;
	
	private JButton atualizarRepresentantesButton = null;

	private JButton editarRepresentanteButton = null;

	public ParticipanteDadosPessoaJuridicaPanel(CadastrarParticipanteTabs cadastrarForm){	
		this.cadastrarComitenteParticipanteForm = cadastrarForm;
		initialize();
	}
	
	/*public ParticipanteDadosPessoaJuridicaPanel(CadastrarComitenteForm cadastrarComitenteForm) {
		this.cadastrarComitenteForm = cadastrarComitenteForm;
	}*/

	@SuppressWarnings("deprecation")
	private void initialize(){

		faxLabel = new JLabel();
		faxLabel.setBounds(new Rectangle(289, 0, 70, 20));
		faxLabel.setText("Fax:");
		ramoAtivLabel = new JLabel();
		ramoAtivLabel.setBounds(new Rectangle(0, 115, 100, 20));
		ramoAtivLabel.setText("* " +"Ramo Atividade:");
		repLegalLabel = new JLabel();
		repLegalLabel.setBounds(new Rectangle(289, 93, 138, 20));
		repLegalLabel.setText("* " +"Representante Legal:");
		inscEstadualLabel = new JLabel();
		inscEstadualLabel.setBounds(new Rectangle(289, 46, 131, 20));
		inscEstadualLabel.setText("* " +"Inscrição Estadual:");
		cnpjLabel = new JLabel();
		cnpjLabel.setBounds(new Rectangle(289, 23, 70, 20));
		cnpjLabel.setText("* " +"CNPJ:");
		setSize(new Dimension(577, 314));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getNomeTextField());
		add(getNomeTextFieldLabel());
		add(getRazaoSocialTextField());
		add(getSobrenomeTextFieldLabel());
		add(getEmailTextField());
		add(getEmailTextFieldLabel());
		add(getTelefoneComercialTextField());
		add(getTelefoneComercialLabel());
		add(getTelefoneTextField());
		add(getTelefoneTextFieldLabel());
		this.add(getInscricaoEstadualTextField(), null);
		this.add(getFaxTextField(), null);
		this.add(getRamoAtividadeTextField(), null);
		
		this.add(cnpjLabel, null);
		this.add(getCnpjTextField(), null);
		this.add(inscEstadualLabel, null);
		this.add(repLegalLabel, null);
		this.add(getRepresentanteLegalComboBox(), null);
		//this.add(cargoLabel, null);
		//this.add(getCargoScrollPane(), null);
		this.add(ramoAtivLabel, null);
		
		this.add(faxLabel, null);
		
		this.add(getNovoRepLegalButton(), null);
		this.add(getAtualizarRepresentantesButton(), null);
		this.add(getEditarRepresentanteButton(), null);
		
		
		getNomeTextField().setNextFocusableComponent(getRazaoSocialTextField());
		getRazaoSocialTextField().setNextFocusableComponent(getEmailTextField());
		getEmailTextField().setNextFocusableComponent(getTelefoneComercialTextField());
		getTelefoneComercialTextField().setNextFocusableComponent(getTelefoneTextField());
		getTelefoneTextField().setNextFocusableComponent(getRamoAtividadeTextField());
		getRamoAtividadeTextField().setNextFocusableComponent(getFaxTextField());
		getFaxTextField().setNextFocusableComponent(getCnpjTextField());
		getCnpjTextField().setNextFocusableComponent(getInscricaoEstadualTextField());
		getInscricaoEstadualTextField().setNextFocusableComponent(getRepresentanteLegalComboBox());
		//getEstadoCivilComboBox().setNextFocusableComponent(getCargoTextArea());
		//Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		//End

		atualizarRepresentantes();
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getNomeTextField(){
		if(nomeTextField == null){
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			new LengthLimitedDocument(100,nomeTextField);
			nomeTextField.setSize(new java.awt.Dimension(180,20));
			nomeTextField.setLocation(new java.awt.Point(105,0));
			
			this.binder.addBindProperty(this.participante, this.nomeTextField, "nome");
			
			this.hashComps.put("nome", this.nomeTextField);
			JWarningComponent warn = new JWarningComponent( this.nomeTextField);
			warn.setBounds(105, 0, 180, 20);
			return warn;
		}
		return nomeTextField;
	}
	
	public void bindComitente(){
		participante.setTipoPessoa(juridica);
		participante.setNome(nomeTextField.getText());
		participante.setEmail(emailTextField.getText());
		participante.setTelefone(telefoneTextField.getText());
		participante.setFax(faxTextField.getText());	
		try {
			if (cadastrarComitenteParticipanteForm != null && cadastrarComitenteParticipanteForm.getComitente() != null){
				participante.setLogotipo(cadastrarComitenteParticipanteForm.getComitente().getLogotipo());			
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public Pessoa validatePessoaForm() throws Exception{
		setErrorIcon(false);
		bindComitente();
		binder.bind(juridica);
		participante.setTipoPessoa(juridica);
		juridica.setPessoa(participante);
		juridica.setCnpj(null);
		juridica.setInscricaoEstadual(null);
		//juridica.setDataNascimento(dataNascimentoDateField.getDate());
		//Manual Code
		/*if (representantes == null || representantes.size() <= 0){
			//JOptionPane.showMessageDialog(this,"Dados insuficientes","É necessário selecionar o representante legal",JOptionPane.ERROR_MESSAGE);
			reportWarning("representanteLegal", "É necessário selecionar o representante legal");
			throw new ValidationException("É necessário selecionar o representante legal");
		}*/
		if (!validatePessoaBean()) throw new Exception(messages.getMessage("ErroCamposPessoaIncorretos"));
		if (representantes != null && representantes.size() > 0) juridica.setRepresentanteLegal(representantes.get(representanteLegalComboBox.getSelectedIndex()));
		return participante;
	}
	
	public Pessoa cadastrePessoa() throws Exception{
		return validatePessoaForm();
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean validatePessoaBean(){
		getErrorPanel().removeAllElements();
		try{
			if (processFocus) {
				if (UIUtil.processFocus(this)) {
					processFocus = false;
				}
			}
		}catch(Exception ex){}
		Validate validate = new Validate();
		boolean cnpj = true;
		juridica.setIgnoreValidation(true);
		/*if (naoValidarCNPJCheckBox.isSelected()){
			cnpj=Cnpj.isValid(juridica.getCnpj());
			juridica.setIgnoreValidation(false);
		}else{
			juridica.setIgnoreValidation(true);
			cnpj=true;
		}*/
		
		Map errors = validate.validate(this.participante, "participante");
		Map errors2 = validate.validate(this.juridica, "juridica");
		if (errors == null && errors2 == null && cnpj) return true;
		else if (errors != null && errors2 != null) errors.putAll(errors2);
		else if (errors2 != null && errors == null) errors = errors2;
		
		
		String name;
		JComponent comp;
		
		if (errors != null){
			Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
		
			for (int i = 0; i < entrys.length; i++) {
				name = (String) entrys[i].getKey();
				comp = (JComponent) this.hashComps.get(name);
				if (comp != null) {
					comp.firePropertyChange("warnBorder", false, true);
					Object[] obj = (Object[]) entrys[i].getValue();
					String msg = (String) obj[0];
					if (obj[1] == null) {
						try{
							getErrorPanel().addError(messages.getMessage(msg),comp);
							comp.setToolTipText(messages.getMessage(msg));
						}catch(Exception e){
							e.printStackTrace();
						}
					} else {
						List args = (List) obj[1];
						Object[][] params = new Object[args.size()][2];
						for(int j=0; j < args.size(); j++) {
						   String key = (String) args.get(j);
						   params[j][0] = key;
						   params[j][1] = null;
						}
						try{
							getErrorPanel().addError(messages.getMessage(msg, params),comp);
							comp.setToolTipText(messages.getMessage(msg, params));
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
		
			
		
		if (!cnpj){
			comp = (JComponent) this.hashComps.get("cnpj");
			comp.firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("A propriedade cnpj está inválida",(JComponent) hashComps.get("cnpj"));
				((JComponent) hashComps.get("cnpj")).setToolTipText("A propriedade cnpj está invalida");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}
		
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
//Nunca definir um novo objeto entidade!!!
		participante.setTipoPessoa(null);
		participante.setEndereco(null);
		participante.setId(0);
		participante.setNome(null);
		participante.setEmail(null);
		participante.setTelefone(null);
		participante.setFax(null);
		participante.setLogotipo(null);
		
		juridica.setId(0);
		juridica.setCnpj(null);
		juridica.setInscricaoEstadual(null);
		juridica.setRamoAtividade(null);
		juridica.setRazaoSocial(null);
		juridica.setPessoa(null);
		juridica.setRepresentanteLegal(null);
		juridica.setTelefoneComercial(null);
		

		binder.reverseBind(this.participante);
		binder.reverseBind(this.juridica);

		this.setErrorIcon(false);
		updateUI();
	}
	
	public Object[] editRegister(Pessoa objPessoa ){
//Nunca passar como argumento novos objetos!!!
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.participante, objPessoa);
			participante.setId(objPessoa.getId());
			participante.setNome(objPessoa.getNome());
			participante.setEmail(objPessoa.getEmail());
			participante.setTelefone(objPessoa.getTelefone());
			participante.setFax(objPessoa.getFax());
			if(objPessoa instanceof PessoaEmDivulgacao){
				PessoaEmDivulgacao ped = (PessoaEmDivulgacao) objPessoa;
				participante.setApresentacao(ped.getApresentacao());
				participante.setDescricao(ped.getDescricao());
				participante.setDivulgavel(ped.isDivulgavel());
				participante.setManagerExp(ped.isManagerExp());
				participante.setResearchExp(ped.isResearchExp());
				participante.setSaleExp(ped.isSaleExp());
				participante.setSoftDevExp(ped.isSoftDevExp());
				participante.setTrainExp(ped.isTrainExp());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.juridica, objPessoa.getTipoPessoa());
			Juridica objJuridica  = null;
			objJuridica = (Juridica) objPessoa.getTipoPessoa();
			//Session s =null;
			try {
				//s = LocalServicesUtility.getInstance().openSession();
				objJuridica = RemotePessoaService.getInstance().getPessoaJuridicaByIdPessoa(objPessoa.getId());//(Juridica) s.createQuery("from Juridica f where f.pessoa.id =" + objPessoa.getId()).uniqueResult();
				this.juridica.setId(objJuridica.getId());
				juridica.setCnpj(objJuridica.getCnpj());
				juridica.setInscricaoEstadual(objJuridica.getInscricaoEstadual());
				juridica.setRamoAtividade(objJuridica.getRamoAtividade());
				juridica.setRazaoSocial(objJuridica.getRazaoSocial());
				juridica.setRepresentanteLegal(objJuridica.getRepresentanteLegal());
				juridica.setTelefoneComercial(objJuridica.getTelefoneComercial());
				juridica.setPessoa(participante);
				juridica.setRepresentanteLegal(objJuridica.getRepresentanteLegal());
				if (objJuridica.getRepresentanteLegal() != null){
					representanteLegalComboBox.setSelectedItem(objJuridica.getRepresentanteLegal().getNome());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}/*finally{
				if (s != null) s.close();
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}

		binder.reverseBind(this.participante);
		binder.reverseBind(juridica);
		this.setErrorIcon(false);
		
		return new Object[]{participante,juridica};
	}
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(0, 140, 520, 65));
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		this.nomeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.razaoSocialTextField.firePropertyChange("warnBorder", !bool, bool);
		this.emailTextField.firePropertyChange("warnBorder", !bool, bool);
		this.telefoneTextField.firePropertyChange("warnBorder", !bool, bool);
		this.cnpjTextField.firePropertyChange("warnBorder", !bool, bool);
		this.inscricaoEstadualTextField.firePropertyChange("warnBorder", !bool, bool);
		this.representanteLegalComboBox.firePropertyChange("warnBorder", !bool, bool);
		//this.getCargoTextArea().firePropertyChange("warnBorder", !bool, bool);
		this.ramoAtividadeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getNomeTextFieldLabel(){

		if(nomeTextFieldLabel == null){
			nomeTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Nome"));
			nomeTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			nomeTextFieldLabel.setLocation(new java.awt.Point(0,0));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getRazaoSocialTextField(){
		if(razaoSocialTextField == null){
			razaoSocialTextField = new JTextField();
			razaoSocialTextField.setText("");
			new LengthLimitedDocument(120,razaoSocialTextField);
			razaoSocialTextField.setSize(new java.awt.Dimension(180,20));
			razaoSocialTextField.setLocation(new java.awt.Point(105,23));
			this.binder.addBindProperty(this.juridica, this.razaoSocialTextField, "razaoSocial");
			
			this.hashComps.put("razaoSocial", this.razaoSocialTextField);
			JWarningComponent warn = new JWarningComponent( this.razaoSocialTextField);
			warn.setBounds(105, 23, 180, 20);
			return warn;
		}
		return razaoSocialTextField;
	}
	
	protected JLabel getSobrenomeTextFieldLabel(){

		if(razaoSocialLabel == null){
			razaoSocialLabel = new JLabel("* " +"Razão Social");
			razaoSocialLabel.setSize(new java.awt.Dimension(100,20));
			razaoSocialLabel.setLocation(new java.awt.Point(0,23));
			razaoSocialLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return razaoSocialLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getEmailTextField(){
		if(emailTextField == null){
			emailTextField = new JTextField();
			emailTextField.setText("");
			new LengthLimitedDocument(200,emailTextField);
			emailTextField.setSize(new java.awt.Dimension(180,20));
			emailTextField.setLocation(new java.awt.Point(105,46));
			this.binder.addBindProperty(this.participante, this.emailTextField, "email");
			
			this.hashComps.put("email", this.emailTextField);
			JWarningComponent warn = new JWarningComponent( this.emailTextField);
			warn.setBounds(105, 46, 180, 20);
			return warn;
		}
		return emailTextField;
	}
	
	protected JLabel getEmailTextFieldLabel(){

		if(emailTextFieldLabel == null){
			emailTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Email"));
			emailTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			emailTextFieldLabel.setLocation(new java.awt.Point(0,46));
			emailTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return emailTextFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getTelefoneComercialTextField(){
		if(telefoneComercialTextField == null){
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				telefoneComercialTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				telefoneComercialTextField = new JTextField();
			}
			telefoneComercialTextField.setSize(new java.awt.Dimension(180,20));
			telefoneComercialTextField.setLocation(new java.awt.Point(105,69));
			this.binder.addBindProperty(this.juridica, this.telefoneComercialTextField, "telefoneComercial");
			
			this.hashComps.put("telefoneComercial", this.telefoneComercialTextField);
			JWarningComponent warn = new JWarningComponent( this.telefoneComercialTextField);
			warn.setBounds(105, 69, 180, 20);
			return warn;
		}
		return telefoneComercialTextField;
	}
	
	protected JLabel getTelefoneComercialLabel(){

		if(telefoneComercialLabel == null){
			telefoneComercialLabel = new JLabel("* " +"Fone Comercial:");
			telefoneComercialLabel.setSize(new Dimension(100, 20));
			telefoneComercialLabel.setLocation(new java.awt.Point(0,69));
			telefoneComercialLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return telefoneComercialLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getTelefoneTextField(){
		if(telefoneTextField == null){
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				telefoneTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				telefoneTextField = new JTextField();
			}
			telefoneTextField.setSize(new java.awt.Dimension(180,20));
			telefoneTextField.setLocation(new java.awt.Point(105,92));
			this.binder.addBindProperty(this.participante, this.telefoneTextField, "telefone");
			
			this.hashComps.put("telefone", this.telefoneTextField);
			JWarningComponent warn = new JWarningComponent( this.telefoneTextField);
			warn.setBounds(105, 92, 180, 20);
			return warn;
		}
		return telefoneTextField;
	}
	
	protected JLabel getTelefoneTextFieldLabel(){

		if(telefoneTextFieldLabel == null){
			telefoneTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Telefone"));
			telefoneTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			telefoneTextFieldLabel.setLocation(new java.awt.Point(0,92));
			telefoneTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return telefoneTextFieldLabel;
	}
	
	/**
	 * This method initializes cpfTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getCnpjTextField() {
		if (cnpjTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("##.###.###/####-##");
				cnpjTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cnpjTextField = new JTextField();
			}
			cnpjTextField.setBounds(new Rectangle(365, 23, 148, 20));
			this.binder.addBindProperty(this.juridica, this.cnpjTextField, "cnpj");
			
			this.hashComps.put("cnpj", this.cnpjTextField);
			JWarningComponent warn = new JWarningComponent( this.cnpjTextField);
			warn.setBounds(365, 23, 148, 20);
			return warn;
		}
		return cnpjTextField;
	}

	/**
	 * This method initializes rgTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getInscricaoEstadualTextField() {
		if (inscricaoEstadualTextField == null) {
			inscricaoEstadualTextField = new JTextField();
			inscricaoEstadualTextField.setBounds(new Rectangle(420, 46, 100, 20));
			new LengthLimitedDocument(20,inscricaoEstadualTextField);
			this.binder.addBindProperty(this.juridica, this.inscricaoEstadualTextField, "inscricaoEstadual");
			
			this.hashComps.put("inscricaoEstadual", this.inscricaoEstadualTextField);
			JWarningComponent warn = new JWarningComponent( this.inscricaoEstadualTextField);
			warn.setBounds(new Rectangle(420, 46, 100, 20));
			return warn;
		}
		return inscricaoEstadualTextField;
	}

	/**
	 * This method initializes estadoCivilComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComponent getRepresentanteLegalComboBox() {
		if (representanteLegalComboBox == null) {
			representanteLegalComboBox = new JComboBox();
			//new Rectangle(289, 46, 70, 20)
			representanteLegalComboBox.setBounds(new Rectangle(289, 115, 230, 20));
			this.hashComps.put("representanteLegal", this.representanteLegalComboBox);
			/*for (int i=0; i < EstadoCivil.values().length; i++){
				representanteLegalComboBox.addItem(EstadoCivil.values()[i]);
			}*/
			
			/*this.binder.addBindProperty(this.juridica, this.representanteLegalComboBox, "estadoCivil");
			
			this.hashComps.put("estadoCivil", this.representanteLegalComboBox);
			JWarningComponent warn = new JWarningComponent( this.representanteLegalComboBox);
			warn.setBounds(365, 46, 148, 20);
			return warn;*/
		}
		return representanteLegalComboBox;
	}


	/**
	 * This method initializes dataNascimentoDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getRamoAtividadeTextField() {
		if (ramoAtividadeTextField == null) {
			ramoAtividadeTextField = new JTextField();
			new LengthLimitedDocument(150,ramoAtividadeTextField);
			ramoAtividadeTextField.setSize(new java.awt.Dimension(180, 20));
			ramoAtividadeTextField.setLocation(new java.awt.Point(105, 115));
			this.binder.addBindProperty(this.juridica,this.ramoAtividadeTextField, "ramoAtividade");
			this.hashComps.put("ramoAtividade", this.ramoAtividadeTextField);
			JWarningComponent warn = new JWarningComponent(this.ramoAtividadeTextField);
			warn.setBounds(105, 115, 180, 20);
			return warn;
		}
		return ramoAtividadeTextField;
	}

	/**
	 * This method initializes faxTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getFaxTextField() {
		if (faxTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				faxTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				faxTextField = new JTextField();
			}
			faxTextField.setBounds(new Rectangle(365, 0, 148, 20));
			this.binder.addBindProperty(this.participante,this.faxTextField, "fax");			
			this.hashComps.put("fax", this.faxTextField);
			JWarningComponent warn = new JWarningComponent( this.faxTextField);
			warn.setBounds(365, 0, 148, 20);
			return warn;
		}
		return faxTextField;
	}

	/**
	 * This method initializes novoRepLegalButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoRepLegalButton() {
		if (novoRepLegalButton == null) {
			novoRepLegalButton = new JButton();
			novoRepLegalButton.setBounds(new Rectangle(456, 92, 24, 22));
			//novoRepLegalButton.setText("Novo");
			novoRepLegalButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_add.png")));
			novoRepLegalButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AdapitVirtualFrame.getInstance().cadastrarRepresentante();
				}
				
			});
		}
		return novoRepLegalButton;
	}
	private Hashtable<String,RepresentanteLegal> representantesHt = new Hashtable<String,RepresentanteLegal>();
	private JButton getEditarRepresentanteButton() {
		if (editarRepresentanteButton == null) {
			editarRepresentanteButton = new JButton();
			editarRepresentanteButton.setBounds(new Rectangle(483, 92, 24, 22));
			editarRepresentanteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_edit.png")));
			editarRepresentanteButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							//int index = representanteLegalComboBox.getSelectedIndex();
							String str = (String) representanteLegalComboBox.getSelectedItem();
							RepresentanteLegal rl = representantesHt.get(str);
							AdapitVirtualFrame.getInstance().editarParticipante(rl.getUser(),PersonType.Fisica);
							/*if (index < 0) return;
							LeilaoVirtualFrame.getInstance().editarRepresentante(representantes.get(index));*/
						}
					});
		}
		return editarRepresentanteButton;
	}
	
	private JButton getAtualizarRepresentantesButton() {
		if (atualizarRepresentantesButton == null) {
			atualizarRepresentantesButton = new JButton();
			atualizarRepresentantesButton.setBounds(new Rectangle(430, 92, 23, 22));
			atualizarRepresentantesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			atualizarRepresentantesButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent evt) {
							atualizarRepresentantes();
						}
					});
		}
		return atualizarRepresentantesButton;
	}
	
	@SuppressWarnings("unchecked")
	public void atualizarRepresentantes(){
		try {
			List<RepresentanteLegal> list = RemotePessoaService.getInstance().listRepresentanteLegalLoading();
			representanteLegalComboBox.removeAllItems();
			representantes.clear();
			representantesHt.clear();
			for(RepresentanteLegal rep: list){
				String str = rep.getNome() + " " + ((Fisica)rep.getTipoPessoa()).getSobrenome();
				representanteLegalComboBox.addItem(str);
				representantesHt.put(str,rep);
				representantes.add(rep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	public void atualizarRepresentantes(){
		Session s = null;
		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List<Object[]> list = s.createQuery("select rep.id,rep.nome from RepresentanteLegal rep").list();
			representanteLegalComboBox.removeAllItems();
			representantes.clear();
			for(Object[] objs: list){
				representanteLegalComboBox.addItem(objs[1]);
				RepresentanteLegal rep = new RepresentanteLegal();
				rep.setNome((String)objs[1]);
				rep.setId((Long)objs[0]);
				representantes.add(rep);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
	}*/
	
	private List<RepresentanteLegal> representantes = new ArrayList<RepresentanteLegal>();

	public static void main(String args[] ){

		/*new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(276,148));
					gui.add(new ComitenteDadosPessoaisJuridicaPanel(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();*/
	}
	
	@SuppressWarnings("unused")
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

	
	public Juridica getJuridica() {
		return juridica;
	}

	public PessoaEmDivulgacao getComitente() {
		return participante;
	}

	public void reportWarning(String propriedadeDuplicada) {
		try{
			JComponent comp = (JComponent) this.hashComps.get(propriedadeDuplicada);
			comp.firePropertyChange("warnBorder", false, true);
			try {				
				getErrorPanel().addError("A propriedade "+propriedadeDuplicada+" pode estar repetida em outro registro",(JComponent) hashComps.get(propriedadeDuplicada));
				((JComponent) hashComps.get(propriedadeDuplicada)).setToolTipText("A propriedade "+propriedadeDuplicada+" pode estar repetida em outro registro");
			} catch (Exception e) {
				e.printStackTrace();
			}
			getErrorPanel().updateErrorList();
			getErrorPanel().setVisible(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void reportWarning(String propriedadeDuplicada, String errorMsg) {
		try{
			JComponent comp = (JComponent) this.hashComps.get(propriedadeDuplicada);
			comp.firePropertyChange("warnBorder", false, true);
			try {
				
				getErrorPanel().addError("A propriedade "+propriedadeDuplicada+" "+errorMsg,(JComponent) hashComps.get(propriedadeDuplicada));
				((JComponent) hashComps.get(propriedadeDuplicada)).setToolTipText("A propriedade "+propriedadeDuplicada+" "+errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			getErrorPanel().updateErrorList();
			getErrorPanel().setVisible(true);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"