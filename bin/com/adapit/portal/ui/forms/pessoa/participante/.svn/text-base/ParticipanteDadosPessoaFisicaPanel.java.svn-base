package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.brazilutils.br.cpfcnpj.CpfCnpj;


import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.EstadoCivil;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.Sexo;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.LengthLimitedDocument;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings("serial")
public class ParticipanteDadosPessoaFisicaPanel extends JPanel{
	
	private JTextField nomeTextField;
	
	private SwingBinder binder = new SwingBinder();
	
	private Participante participante = new Participante();
	
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel nomeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField sobrenomeTextField;
	
	private Fisica fisica = new Fisica();  //  @jve:decl-index=0:
	
	private JLabel sobrenomeTextFieldLabel;
	
	private JTextField emailTextField;
	
	private JLabel emailTextFieldLabel;
	
	private JTextField celularTextField;
	
	private JLabel faxTextFieldLabel;
	
	private JTextField telefoneTextField;
	
	private JLabel telefoneTextFieldLabel;
	
	private JLabel cpfLabel = null;

	private JTextField cpfTextField = null;

	private JLabel rgLabel = null;

	private JTextField rgTextField = null;

	private JLabel estadoCivilLabel = null;

	private JComboBox estadoCivilComboBox = null;

	private JLabel dataNascimentoLabel = null;

	private DateHourChooser dataNascimentoDateField = null;

	private JLabel faxLabel = null;

	private JTextField faxTextField = null;
	
	CadastrarParticipanteTabs cadastrarComitenteParticipanteForm;
	//CadastrarComitenteForm cadastrarComitenteForm;

	private JLabel profissaoLabel = null;

	private JTextField profissaoTextField = null;

	//private JCheckBox naoValidarCpfCheckBox = null;

	public ParticipanteDadosPessoaFisicaPanel(CadastrarParticipanteTabs cadastrarForm){	
		this.cadastrarComitenteParticipanteForm = cadastrarForm;
		initialize();
	}
	
	/*public ParticipanteDadosPessoaFisicaPanel(	CadastrarComitenteForm cadastrarComitenteForm) {
		this.cadastrarComitenteForm = cadastrarComitenteForm;
	}*/

	@SuppressWarnings("deprecation")
	private void initialize(){

		profissaoLabel = new JLabel();
		profissaoLabel.setBounds(new Rectangle(289, 93, 70, 20));
		profissaoLabel.setText("* " +"Profissão:");
		faxLabel = new JLabel();
		faxLabel.setBounds(new Rectangle(289, 70, 70, 20));
		faxLabel.setText("Fax:");
		dataNascimentoLabel = new JLabel();
		dataNascimentoLabel.setBounds(new Rectangle(0, 115, 130, 20));
		dataNascimentoLabel.setText("* " +"Data de Nascimento:");
		estadoCivilLabel = new JLabel();
		estadoCivilLabel.setBounds(new Rectangle(289, 46, 70, 20));
		estadoCivilLabel.setText("* " +"Estado Civil:");
		rgLabel = new JLabel();
		rgLabel.setBounds(new Rectangle(289, 23, 70, 20));
		rgLabel.setText("* " +"RG:");
		cpfLabel = new JLabel();
		cpfLabel.setBounds(new Rectangle(289, 0, 70, 20));
		cpfLabel.setText("* " +"CPF:");
		setSize(new Dimension(577, 380));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getNomeTextField());
		add(getNomeTextFieldLabel());
		add(getSobrenomeTextField());
		add(getSobrenomeTextFieldLabel());
		add(getEmailTextField());
		add(getEmailTextFieldLabel());
		add(getCelularTextField());
		add(getFaxTextFieldLabel());
		add(getTelefoneTextField());
		add(getTelefoneTextFieldLabel());
		add(getSexoLabel());
		add(getSexoComboBox());
		
		this.add(cpfLabel, null);
		this.add(getCpfTextField(), null);
		this.add(rgLabel, null);
		this.add(getRgTextField(), null);
		this.add(estadoCivilLabel, null);
		this.add(getEstadoCivilComboBox(), null);
		//this.add(cargoLabel, null);
		//this.add(getCargoScrollPane(), null);
		this.add(dataNascimentoLabel, null);
		this.add(getDataNascimentoDateField(), null);
		
		this.add(faxLabel, null);
		this.add(getFaxTextField(), null);
		this.add(profissaoLabel, null);
		this.add(getProfissaoTextField(), null);
		
		getNomeTextField().setNextFocusableComponent(getSobrenomeTextField());
		getSobrenomeTextField().setNextFocusableComponent(getEmailTextField());
		getEmailTextField().setNextFocusableComponent(getCelularTextField());
		getCelularTextField().setNextFocusableComponent(getTelefoneTextField());
		getTelefoneTextField().setNextFocusableComponent(getCpfTextField());
		getCpfTextField().setNextFocusableComponent(getRgTextField());
		getRgTextField().setNextFocusableComponent(getEstadoCivilComboBox());
		//getEstadoCivilComboBox().setNextFocusableComponent(getCargoTextArea());
		//Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		//End
	
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
		participante.setTipoPessoa(fisica);
		participante.setNome(nomeTextField.getText());
		participante.setEmail(emailTextField.getText());
		participante.setTelefone(telefoneTextField.getText());
		participante.setFax(faxTextField.getText());	
		if (cadastrarComitenteParticipanteForm != null && cadastrarComitenteParticipanteForm.getComitente() != null){
			participante.setLogotipo(cadastrarComitenteParticipanteForm.getComitente().getLogotipo());
			//participante.setDescricao(((JTextField)cadastrarComitenteParticipanteForm.getDescricaoComitenteTextField()).getText());
			//participante.setTipoComitente(TipoComitente.valueOf((String)((JComboBox)cadastrarComitenteParticipanteForm.getTipoComitenteComboBox()).getSelectedItem()));
		}else participante.setLogotipo(null);
		
		/*else if (cadastrarComitenteForm != null){
			participante.setLogotipo(cadastrarComitenteForm.comitente.getLogotipo());
			participante.setDescricao(((JTextField)cadastrarComitenteForm.getDescricaoComitenteTextField()).getText());
			participante.setTipoComitente(TipoComitente.valueOf((String)((JComboBox)cadastrarComitenteForm.getTipoComitenteComboBox()).getSelectedItem()));
		}*/
	}
	
	public Pessoa validatePessoaForm() throws Exception{
		setErrorIcon(false);
		bindComitente();
		binder.bind(fisica);
		participante.setTipoPessoa(fisica);
		fisica.setPessoa(participante);
		fisica.setDataNascimento(dataNascimentoDateField.getDate());
		fisica.setSexo(Sexo.valueOf((String)sexoComboBox.getSelectedItem()));
		fisica.setCpf(null);
		fisica.setRg(null);
		//Manual Code
		if (!validatePessoaBean()) throw new Exception(messages.getMessage("ErroCamposPessoaIncorretos"));
		return participante;
	}
	
	public Pessoa cadastrePessoa() throws Exception{
		return validatePessoaForm();
	}
	
	@SuppressWarnings("unchecked")
	public boolean validatePessoaBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.participante, "participante");
		Map errors2 = validate.validate(this.fisica, "fisica");
		boolean cpf = true;//CpfCnpj.isValid(fisica.getCpf());
		//if (naoValidarCpfCheckBox.isSelected()) cpf = true;
		fisica.setIgnoreValidation(true);
		
		if ((errors == null || errors2 == null) && 
				!dataNascimentoDateField.getDateHourField().getText().equals("")
				&& cpf) return true;
		else if (errors != null && errors2 != null) errors.putAll(errors2);
		else if (errors2 != null && errors == null) errors = errors2;
		else if (errors == null && errors2 == null){
			if (cpf) return true;			
		}
		/*else if (errors == null && errors2 == null){
			errors = new HashMap();
			errors.put("dataNascimento", dataNascimentoDateField);
		}*/
		String name;
		JComponent comp;
		if (errors != null || errors2 != null){
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
		/*
		if (dataNascimentoDateField.getDateHourField().getText().equals("")){
			comp = (JComponent) this.hashComps.get("dataNascimento");
			comp.firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("A propriedade data de nascimento deve ser setada",(JComponent) hashComps.get("dataNascimento"));
				((JComponent) hashComps.get("dataNascimento")).setToolTipText("A propriedade data de nascimento deve ser setada");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}*/	
		
		if (!cpf){
			comp = (JComponent) this.hashComps.get("cpf");
			comp.firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("A propriedade cpf está inválida",(JComponent) hashComps.get("cpf"));
				((JComponent) hashComps.get("cpf")).setToolTipText("A propriedade cpf está invalida");
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
		
		fisica.setId(0);
		fisica.setCelular(null);
		fisica.setCpf(null);
		fisica.setDataNascimento(null);
		fisica.setEstadoCivil(null);
		fisica.setPessoa(null);
		fisica.setProfissao(null);
		fisica.setRg(null);
		fisica.setSobrenome(null);
		fisica.setSexo(Sexo.Feminino);
		
		binder.reverseBind(this.participante);
		binder.reverseBind(this.fisica);

		this.setErrorIcon(false);
		updateUI();
	}
	
	private JComboBox sexoComboBox;
	private JLabel sexoLabel;
	/**
	 * This method initializes sexoLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getSexoLabel() {
		if (sexoLabel == null) {
			sexoLabel = new JLabel();
			sexoLabel.setText("Sexo:");
			sexoLabel.setBounds(new Rectangle(289, 116, 70, 20));
		}
		return sexoLabel;
	}

	/**
	 * This method initializes sexoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSexoComboBox() {
		if (sexoComboBox == null) {
			sexoComboBox = new JComboBox();
			sexoComboBox.setBounds(new Rectangle(365, 116, 148, 20));
			sexoComboBox.addItem(Sexo.Feminino.name().replace("_"," "));
			sexoComboBox.addItem(Sexo.Masculino.name().replace("_"," "));
		}
		return sexoComboBox;
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
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.fisica, objPessoa.getTipoPessoa());
			Fisica objFisica  = null;
			objFisica = (Fisica) objPessoa.getTipoPessoa();
			//Session s =null;
			try {
				//s = LocalServicesUtility.getInstance().openSession();
				//objFisica = (Fisica) DataAccessUtils.uniqueResult(s.createQuery("from Fisica f where f.pessoa.id =" + objPessoa.getId()).list());
				objFisica = (Fisica) RemotePessoaService.getInstance().getPessoaFisicaByIdPessoa(objPessoa.getId());
				this.fisica.setId(objFisica.getId());
				fisica.setCelular(objFisica.getCelular());
				fisica.setCpf(objFisica.getCpf());
				fisica.setDataNascimento(objFisica.getDataNascimento());
				fisica.setEstadoCivil(objFisica.getEstadoCivil());
				fisica.setProfissao(objFisica.getProfissao());
				fisica.setRg(objFisica.getRg());
				fisica.setSobrenome(objFisica.getSobrenome());
				fisica.setPessoa(participante);
				fisica.setProfissao(objFisica.getProfissao());
				fisica.setSexo(objFisica.getSexo());
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}/*finally{
				if (s != null) s.close();
			}*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
		sexoComboBox.setSelectedItem(fisica.getSexo().name());
		binder.reverseBind(this.participante);
		binder.reverseBind(fisica);
		this.setErrorIcon(false);
		
		return new Object[]{participante,fisica};
	}
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(0, 155, 520, 75));
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		this.nomeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.sobrenomeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.emailTextField.firePropertyChange("warnBorder", !bool, bool);
		this.telefoneTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getCpfTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getRgTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getEstadoCivilComboBox().firePropertyChange("warnBorder", !bool, bool);
		//this.getCargoTextArea().firePropertyChange("warnBorder", !bool, bool);
		this.getDataNascimentoDateField().firePropertyChange("warnBorder", !bool, bool);
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
	protected JComponent getSobrenomeTextField(){
		if(sobrenomeTextField == null){
			sobrenomeTextField = new JTextField();
			sobrenomeTextField.setText("");
			sobrenomeTextField.setSize(new java.awt.Dimension(180,20));
			sobrenomeTextField.setLocation(new java.awt.Point(105,23));
			new LengthLimitedDocument(50,sobrenomeTextField);
			this.binder.addBindProperty(this.fisica, this.sobrenomeTextField, "sobrenome");
			
			this.hashComps.put("sobrenome", this.sobrenomeTextField);
			JWarningComponent warn = new JWarningComponent( this.sobrenomeTextField);
			warn.setBounds(105, 23, 180, 20);
			return warn;
		}
		return sobrenomeTextField;
	}
	
	protected JLabel getSobrenomeTextFieldLabel(){

		if(sobrenomeTextFieldLabel == null){
			sobrenomeTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Sobrenome"));
			sobrenomeTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			sobrenomeTextFieldLabel.setLocation(new java.awt.Point(0,23));
			sobrenomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return sobrenomeTextFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getEmailTextField(){
		if(emailTextField == null){
			emailTextField = new JTextField();
			emailTextField.setText("");
			emailTextField.setSize(new java.awt.Dimension(180,20));
			emailTextField.setLocation(new java.awt.Point(105,46));
			new LengthLimitedDocument(200,emailTextField);
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
	protected JComponent getCelularTextField(){
		if(celularTextField == null){
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				celularTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				celularTextField = new JTextField();
			}
			celularTextField.setSize(new java.awt.Dimension(180,20));
			celularTextField.setLocation(new java.awt.Point(105,69));
			this.binder.addBindProperty(this.fisica, this.celularTextField, "celular");
			
			this.hashComps.put("celular", this.celularTextField);
			JWarningComponent warn = new JWarningComponent( this.celularTextField);
			warn.setBounds(105, 69, 180, 20);
			return warn;
		}
		return celularTextField;
	}
	
	protected JLabel getFaxTextFieldLabel(){

		if(faxTextFieldLabel == null){
			faxTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Fax"));
			faxTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			faxTextFieldLabel.setLocation(new java.awt.Point(0,69));
			faxTextFieldLabel.setText("Celular:");
			faxTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return faxTextFieldLabel;
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
			telefoneTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Telefone"));
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
	private JComponent getCpfTextField() {
		if (cpfTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("###.###.###-##");
				cpfTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cpfTextField = new JTextField();
			}
			
			cpfTextField.setBounds(new Rectangle(365, 0, 148, 20));
			this.binder.addBindProperty(this.fisica, this.cpfTextField, "cpf");
						
			this.hashComps.put("cpf", this.cpfTextField);
			JWarningComponent warn = new JWarningComponent( this.cpfTextField);
			warn.setBounds(365, 0, 148, 20);
			return warn;
		}
		return cpfTextField;
	}

	/**
	 * This method initializes rgTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getRgTextField() {
		if (rgTextField == null) {
			rgTextField = new JTextField();
			rgTextField.setBounds(new Rectangle(365, 23, 148, 20));
			new LengthLimitedDocument(10,rgTextField);
			this.binder.addBindProperty(this.fisica, this.rgTextField, "rg");
			
			this.hashComps.put("rg", this.rgTextField);
			JWarningComponent warn = new JWarningComponent( this.rgTextField);
			warn.setBounds(365, 23, 148, 20);
			return warn;
		}
		return rgTextField;
	}

	/**
	 * This method initializes estadoCivilComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getEstadoCivilComboBox() {
		if (estadoCivilComboBox == null) {
			estadoCivilComboBox = new JComboBox();
			//new Rectangle(289, 46, 70, 20)
			estadoCivilComboBox.setBounds(new Rectangle(365, 46, 148, 20));
			for (int i=0; i < EstadoCivil.values().length; i++){
				estadoCivilComboBox.addItem(EstadoCivil.values()[i]);
			}
			
			this.binder.addBindProperty(this.fisica, this.estadoCivilComboBox, "estadoCivil");
			
			this.hashComps.put("estadoCivil", this.estadoCivilComboBox);
			JWarningComponent warn = new JWarningComponent( this.estadoCivilComboBox);
			warn.setBounds(365, 46, 148, 20);
			return warn;
		}
		return estadoCivilComboBox;
	}

	/**
	 * This method initializes cargoTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
/*	private JComponent getCargoTextArea() {
		if (cargoTextArea == null) {
			cargoTextArea = new JTextArea();
			this.binder.addBindProperty(this.fisica, this.cargoTextArea, "profissao");
			
			this.hashComps.put("profissao", this.cargoTextArea);
			JWarningComponent warn = new JWarningComponent( this.cargoTextArea);
			//warn.setBounds(365, 115, 148, 20);
			return warn;
		}
		return cargoTextArea;
	}*/

	/**
	 * This method initializes cargoScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
/*	private JScrollPane getCargoScrollPane() {
		if (cargoScrollPane == null) {
			cargoScrollPane = new JScrollPane();
			cargoScrollPane.setBounds(new Rectangle(289, 88, 225, 48));
			cargoScrollPane.setViewportView(getCargoTextArea());
		}
		return cargoScrollPane;
	}*/

	/**
	 * This method initializes dataNascimentoDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getDataNascimentoDateField() {
		if (dataNascimentoDateField == null) {
			dataNascimentoDateField = new DateHourChooser(messages.getCurrentLocale(), false, true, false);
			dataNascimentoDateField.setSize(new java.awt.Dimension(150, 20));
			dataNascimentoDateField.setLocation(new java.awt.Point(135, 115));
			this.binder.addBindProperty(this.fisica,this.dataNascimentoDateField/*.getDateHourField()*/, "dataNascimento");
			this.hashComps.put("dataNascimento", this.dataNascimentoDateField/*.getDateHourField()*/);
			JWarningComponent warn = new JWarningComponent(this.dataNascimentoDateField);
			warn.setBounds(135, 115, 150, 20);
			return warn;
		}
		return dataNascimentoDateField;
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
			faxTextField.setBounds(new Rectangle(365, 70, 148, 20));
			this.binder.addBindProperty(this.participante,this.faxTextField, "fax");			
			this.hashComps.put("fax", this.faxTextField);
			JWarningComponent warn = new JWarningComponent( this.faxTextField);
			warn.setBounds(365, 70, 148, 20);
			return warn;
		}
		return faxTextField;
	}

	/**
	 * This method initializes profissaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getProfissaoTextField() {
		if (profissaoTextField == null) {
			profissaoTextField = new JTextField();
			profissaoTextField.setBounds(new Rectangle(365, 92, 148, 20));
			this.binder.addBindProperty(this.fisica,profissaoTextField, "profissao");			
			this.hashComps.put("profissao", profissaoTextField);
			JWarningComponent warn = new JWarningComponent(profissaoTextField);
			warn.setBounds(365, 92, 148, 20);
			return warn;
		}
		return profissaoTextField;
	}



	public static void main(String args[] ){

		/*new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(276,148));
					gui.add(new ComitenteDadosPessoaisFisicaPanel(),java.awt.BorderLayout.CENTER);
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

	
	public Fisica getFisica() {
		return fisica;
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