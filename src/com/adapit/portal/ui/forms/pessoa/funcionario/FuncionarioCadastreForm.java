package com.adapit.portal.ui.forms.pessoa.funcionario;


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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.brazilutils.br.cpfcnpj.CpfCnpj;


import com.adapit.portal.entidades.EstadoCivil;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Funcionario;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings("serial")
public class FuncionarioCadastreForm extends JPanel{
	
	private JTextField nomeTextField;
	
	private SwingBinder binder = new SwingBinder();
	
	private Funcionario funcionario = new Funcionario();
	
	
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel nomeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField sobrenomeTextField;
	
	private Fisica fisica = new Fisica();
	
	private JLabel sobrenomeTextFieldLabel;
	
	private JTextField emailTextField;
	
	private JLabel emailTextFieldLabel;
	
	private JTextField celularTextField;
	
	private JLabel faxTextFieldLabel;
	
	private JTextField telefoneTextField;
	
	private JLabel telefoneTextFieldLabel;

	private String entityName="funcionario";  //  @jve:decl-index=0:

	private JLabel cpfLabel = null;

	private JTextField cpfTextField = null;

	private JLabel rgLabel = null;

	private JTextField rgTextField = null;

	private JLabel estadoCivilLabel = null;

	private JComboBox estadoCivilComboBox = null;

	private JLabel cargoLabel = null;

	private JTextArea cargoTextArea = null;

	private JScrollPane cargoScrollPane = null;

	private JLabel dataNascimentoLabel = null;

	private DateHourChooser dataNascimentoDateField = null;

	private JCheckBox naoValidarCpfCheckBox = null;

	
	/*public PessoaFisicaCadastreForm(){
		initialize();
	}*/
	
	public FuncionarioCadastreForm(/*Funcionario funcionario*/){
		//this.pessoa = funcionario;
		//entityName = "funcionario";
		initialize();
	}
	
	/*public PessoaFisicaCadastreForm(Leiloeiro funcionario){
		this.pessoa = funcionario;
		entityName = "leiloeiro";
		initialize();
	}*/
	
	@SuppressWarnings("deprecation")
	private void initialize(){

		dataNascimentoLabel = new JLabel();
		dataNascimentoLabel.setBounds(new Rectangle(0, 115, 130, 20));
		dataNascimentoLabel.setText("* " +"Data de Nascimento:");
		cargoLabel = new JLabel();
		cargoLabel.setBounds(new Rectangle(289, 69, 70, 20));
		cargoLabel.setText("* Ocupação:");
		estadoCivilLabel = new JLabel();
		estadoCivilLabel.setBounds(new Rectangle(289, 46, 70, 20));
		estadoCivilLabel.setText("* " +"Estado Civil:");
		rgLabel = new JLabel();
		rgLabel.setBounds(new Rectangle(289, 23, 70, 20));
		rgLabel.setText("* " +"RG:");
		cpfLabel = new JLabel();
		cpfLabel.setBounds(new Rectangle(289, 0, 70, 20));
		cpfLabel.setText("* " +"CPF:");
		setSize(new Dimension(577, 340));
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
		
		this.add(cpfLabel, null);
		this.add(getCpfTextField(), null);
		this.add(rgLabel, null);
		this.add(getRgTextField(), null);
		this.add(estadoCivilLabel, null);
		this.add(getEstadoCivilComboBox(), null);
		this.add(cargoLabel, null);
		this.add(getCargoScrollPane(), null);
		this.add(dataNascimentoLabel, null);
		this.add(getDataNascimentoDateField(), null);
		
		getNomeTextField().setNextFocusableComponent(getSobrenomeTextField());
		getSobrenomeTextField().setNextFocusableComponent(getEmailTextField());
		getEmailTextField().setNextFocusableComponent(getCelularTextField());
		getCelularTextField().setNextFocusableComponent(getTelefoneTextField());
		getTelefoneTextField().setNextFocusableComponent(getCpfTextField());
		getCpfTextField().setNextFocusableComponent(getRgTextField());
		getRgTextField().setNextFocusableComponent(getEstadoCivilComboBox());
		getEstadoCivilComboBox().setNextFocusableComponent(getCargoTextArea());
		//Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		//End

		this.add(getNaoValidarCpfCheckBox(), null);
		
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getNomeTextField(){

		if(nomeTextField == null){
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new java.awt.Dimension(180,20));
			nomeTextField.setLocation(new java.awt.Point(105,0));
			
			this.binder.addBindProperty(this.funcionario, this.nomeTextField, "nome");
			
			this.hashComps.put("nome", this.nomeTextField);
			JWarningComponent warn = new JWarningComponent( this.nomeTextField);
			warn.setBounds(105, 0, 180, 20);
			return warn;
		}
		return nomeTextField;
	}
	
	public void bindFuncionario(){
		funcionario.setTipoPessoa(fisica);
		//funcionario.setId(0);
		
		funcionario.setNome(nomeTextField.getText());
		funcionario.setEmail(emailTextField.getText());
		funcionario.setTelefone(telefoneTextField.getText());
		funcionario.setFax(null);		
	}
	
	public Pessoa validatePessoaForm() throws Exception{
		setErrorIcon(false);
		//binder.bind(funcionario);
		bindFuncionario();
		binder.bind(fisica);
		funcionario.setTipoPessoa(fisica);
		fisica.setPessoa(funcionario);
		fisica.setDataNascimento(dataNascimentoDateField.getDate());
		//Manual Code
		if (!validatePessoaBean()) throw new Exception(messages.getMessage("ErroCamposPessoaIncorretos"));
		return funcionario;
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
		boolean cpf = CpfCnpj.isValid(fisica.getCpf());
		if (naoValidarCpfCheckBox.isSelected()){
			fisica.setIgnoreValidation(true);
			cpf = true;
		}else fisica.setIgnoreValidation(false);
		
		Validate validate = new Validate();
		Map errors = validate.validate(this.funcionario, entityName);
		Map errors2 = validate.validate(this.fisica, "fisica");
		if ((errors == null || errors2 == null) && 
				!dataNascimentoDateField.getDateHourField().getText().equals("")
				&& cpf) return true;
		else if ((errors == null || errors2 == null) && !dataNascimentoDateField.getDateHourField().getText().equals("")) return true;
		else if (errors != null && errors2 != null) errors.putAll(errors2);
		else if (errors2 != null && errors == null) errors = errors2;
		/*else if (errors == null && errors2 == null){
			errors = new HashMap();
			errors.put("dataNascimento", dataNascimentoDateField);
		}*/
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
		if (dataNascimentoDateField.getDateHourField().getText().equals("")){
			comp = (JComponent) this.hashComps.get("dataNascimento");
			comp.firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("A propriedade data de nascimento deve ser setada",(JComponent) hashComps.get("dataNascimento"));
				((JComponent) hashComps.get("dataNascimento")).setToolTipText("A propriedade data de nascimento deve ser setada");
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}	
		
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
		funcionario.setTipoPessoa(null);
		funcionario.setEndereco(null);
		funcionario.setId(0);
		funcionario.setNome(null);
		funcionario.setEmail(null);
		funcionario.setTelefone(null);
		funcionario.setFax(null);
		
		fisica.setId(0);
		fisica.setCelular(null);
		fisica.setCpf(null);
		fisica.setDataNascimento(null);
		fisica.setEstadoCivil(null);
		fisica.setPessoa(null);
		fisica.setProfissao(null);
		fisica.setRg(null);
		fisica.setSobrenome(null);

		binder.reverseBind(this.funcionario);
		binder.reverseBind(this.fisica);

		this.setErrorIcon(false);
		updateUI();
	}
	
	public void editRegister(Pessoa objPessoa ){
//Nunca passar como argumento novos objetos!!!
		
		/*try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			objPessoa = (Pessoa) s.load(Pessoa.class, objPessoa.getId());
			Hibernate.initialize(objPessoa);
			Hibernate.initialize(objPessoa.getTipoPessoa());
			objPessoa.getTipoPessoa();
			s.getTransaction().commit();
			s.close();
		} catch (HibernateException e1) {
			if (s != null) s.close();
		}*/
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.funcionario, objPessoa);
		}catch(Exception e){
			e.printStackTrace();
		}		
			Fisica objFisica  = null;
			objFisica = (Fisica) objPessoa.getTipoPessoa();
			try {
				objFisica = (Fisica) RemotePessoaService.getInstance().getPessoaFisicaByIdPessoa(objPessoa.getId());
				this.fisica.setId(objFisica.getId());
				fisica.setCelular(objFisica.getCelular());
				fisica.setCpf(objFisica.getCpf());
				fisica.setDataNascimento(objFisica.getDataNascimento());
				fisica.setEstadoCivil(objFisica.getEstadoCivil());
				fisica.setProfissao(objFisica.getProfissao());
				fisica.setRg(objFisica.getRg());
				fisica.setSobrenome(objFisica.getSobrenome());
				fisica.setPessoa(funcionario);		
			} catch (Exception e) {
				e.printStackTrace();
			}
			/*Session s =null;
			try {
				s = LocalServicesUtility.getInstance().openSession();
				objFisica = (Fisica) DataAccessUtils.uniqueResult(s.createQuery("from Fisica f where f.pessoa.id =" + objPessoa.getId()).list());
				this.fisica.setId(objFisica.getId());
				fisica.setCelular(objFisica.getCelular());
				fisica.setCpf(objFisica.getCpf());
				fisica.setDataNascimento(objFisica.getDataNascimento());
				fisica.setEstadoCivil(objFisica.getEstadoCivil());
				fisica.setProfissao(objFisica.getProfissao());
				fisica.setRg(objFisica.getRg());
				fisica.setSobrenome(objFisica.getSobrenome());
				fisica.setPessoa(funcionario);				
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}finally{
				if (s != null) s.close();
			}*/
		

		binder.reverseBind(this.funcionario);
		binder.reverseBind(fisica);
		this.setErrorIcon(false);
	}
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(0, 140, 520, 90));
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
		this.getCargoTextArea().firePropertyChange("warnBorder", !bool, bool);
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
			this.binder.addBindProperty(this.funcionario, this.emailTextField, "email");
			
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
			celularTextField = new JTextField();
			celularTextField.setText("");
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
			telefoneTextField = new JTextField();
			telefoneTextField.setText("");
			telefoneTextField.setSize(new java.awt.Dimension(180,20));
			telefoneTextField.setLocation(new java.awt.Point(105,92));
			this.binder.addBindProperty(this.funcionario, this.telefoneTextField, "telefone");
			
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
	@SuppressWarnings("unchecked")
	private JComponent getCargoTextArea() {
		if (cargoTextArea == null) {
			cargoTextArea = new JTextArea();
			this.binder.addBindProperty(this.fisica, this.cargoTextArea, "profissao");
			
			this.hashComps.put("profissao", this.cargoTextArea);
			JWarningComponent warn = new JWarningComponent( this.cargoTextArea);
			//warn.setBounds(365, 115, 148, 20);
			return warn;
		}
		return cargoTextArea;
	}

	/**
	 * This method initializes cargoScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getCargoScrollPane() {
		if (cargoScrollPane == null) {
			cargoScrollPane = new JScrollPane();
			cargoScrollPane.setBounds(new Rectangle(289, 88, 225, 48));
			cargoScrollPane.setViewportView(getCargoTextArea());
		}
		return cargoScrollPane;
	}

	/**
	 * This method initializes dataNascimentoDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getDataNascimentoDateField() {
		if (dataNascimentoDateField == null) /*{
			dataNascimentoDateField = new JTextField();
			dataNascimentoDateField.setBounds(new Rectangle(135, 115, 150, 20));
		}*/
		
		{
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
	 * This method initializes naoValidarCpfCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getNaoValidarCpfCheckBox() {
		if (naoValidarCpfCheckBox == null) {
			naoValidarCpfCheckBox = new JCheckBox();
			naoValidarCpfCheckBox.setBounds(new Rectangle(359, 68, 168, 20));
			naoValidarCpfCheckBox.setText("Não validar o cpf neste caso");
		}
		return naoValidarCpfCheckBox;
	}

	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(276,148));
					gui.add(new FuncionarioCadastreForm(/*new Funcionario()*/),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
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