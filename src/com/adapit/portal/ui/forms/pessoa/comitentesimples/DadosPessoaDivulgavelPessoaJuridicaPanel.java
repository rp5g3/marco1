package com.adapit.portal.ui.forms.pessoa.comitentesimples;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.brazilutils.br.cpfcnpj.Cnpj;

import com.adapit.portal.entidades.ComitenteSimples;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.RepresentanteLegal;
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


@SuppressWarnings({"serial","unchecked","deprecation"})
public class DadosPessoaDivulgavelPessoaJuridicaPanel extends JPanel{
	
	private JTextField nomeTextField;
	
	private SwingBinder binder = new SwingBinder();
	
	private ComitenteSimples comitente = new ComitenteSimples();

	private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	
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
	
	private CadastrarPessoaDivulgavelTabs cadastrarComitenteForm;

	private JButton novoRepLegalButton = null;
	
	private JButton atualizarRepresentantesButton = null;

	private JButton editarRepresentanteButton = null;
	
	public DadosPessoaDivulgavelPessoaJuridicaPanel(CadastrarPessoaDivulgavelTabs cadastrarComitenteForm) {
		this.cadastrarComitenteForm = cadastrarComitenteForm;
		initialize();
	}

	private void initialize(){

		faxLabel = new JLabel();
		faxLabel.setBounds(new Rectangle(289, 0, 70, 20));
		faxLabel.setText("Fax:");
		ramoAtivLabel = new JLabel();
		ramoAtivLabel.setBounds(new Rectangle(0, 115, 100, 20));
		ramoAtivLabel.setText("* " +"Ramo de Atividade:");
		repLegalLabel = new JLabel();
		repLegalLabel.setBounds(new Rectangle(289, 93, 137, 20));
		repLegalLabel.setText("* " +"Representante Legal:");
		inscEstadualLabel = new JLabel();
		inscEstadualLabel.setBounds(new Rectangle(289, 46, 131, 20));
		inscEstadualLabel.setText("* " +"Inscrição Estadual:");
		cnpjLabel = new JLabel();
		cnpjLabel.setBounds(new Rectangle(289, 23, 70, 20));
		cnpjLabel.setText("* " +"CNPJ:");
		setSize(new Dimension(577, 340));
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
		
		this.add(cnpjLabel, null);
		this.add(getCnpjTextField(), null);
		this.add(inscEstadualLabel, null);
		this.add(getInscricaoEstadualTextField(), null);
		this.add(repLegalLabel, null);
		this.add(getRepresentanteLegalComboBox(), null);		
		this.add(ramoAtivLabel, null);
		this.add(getRamoAtividadeTextField(), null);
		
		this.add(faxLabel, null);
		this.add(getFaxTextField(), null);
		
		
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
		
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		//End

		
		
	}
	protected JComponent getNomeTextField(){
		if(nomeTextField == null){
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new java.awt.Dimension(180,20));
			nomeTextField.setLocation(new java.awt.Point(105,0));
			
			this.binder.addBindProperty(this.comitente, this.nomeTextField, "nome");
			new LengthLimitedDocument(100,nomeTextField);
			this.hashComps.put("nome", this.nomeTextField);
			JWarningComponent warn = new JWarningComponent( this.nomeTextField);
			warn.setBounds(105, 0, 180, 20);
			return warn;
		}
		return nomeTextField;
	}
	
	public void bindComitente(){
		comitente.setTipoPessoa(juridica);
		comitente.setNome(nomeTextField.getText());
		comitente.setEmail(emailTextField.getText());
		comitente.setTelefone(telefoneTextField.getText());
		comitente.setFax(faxTextField.getText());	
		if (cadastrarComitenteForm != null && cadastrarComitenteForm.pessoaEmDivulgacao != null){
			comitente.setLogotipo(cadastrarComitenteForm.pessoaEmDivulgacao.getLogotipo());
			comitente.setDescricao(((JTextArea)cadastrarComitenteForm.getDescricaoComitenteTextArea()).getText());
			
		}
	}
	
	public ComitenteSimples validatePessoaForm() throws Exception{
		setErrorIcon(false);
		bindComitente();
		binder.bind(juridica);
		comitente.setTipoPessoa(juridica);
		juridica.setPessoa(comitente);		
		if (representantes == null || representantes.size() <= 0){
			//JOptionPane.showMessageDialog(this,"Dados insuficientes","É necessário selecionar o representante legal",JOptionPane.ERROR_MESSAGE);
			reportWarning("representanteLegal", "É necessário selecionar o representante legal");
			throw new ValidationException("É necessário selecionar o representante legal");
		}
		if (!validatePessoaBean()) throw new Exception(messages.getMessage("ErroCamposPessoaIncorretos"));
		if (representantes != null && representantes.size() > 0) juridica.setRepresentanteLegal(representantes.get(representanteLegalComboBox.getSelectedIndex()));
		return comitente;
	}
	
	public ComitenteSimples cadastrePessoa() throws Exception{
		return validatePessoaForm();
	}
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
		boolean cnpj = false;
		cnpj=Cnpj.isValid(juridica.getCnpj());
		
		
		Map errors = validate.validate(this.comitente, "pessoaEmDivulgacao");
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
		comitente.setTipoPessoa(null);
		comitente.setEndereco(null);
		comitente.setId(0);
		comitente.setNome(null);
		comitente.setEmail(null);
		comitente.setTelefone(null);
		comitente.setFax(null);
		comitente.setLogotipo(null);
		
		juridica.setId(0);
		juridica.setCnpj(null);
		juridica.setInscricaoEstadual(null);
		juridica.setRamoAtividade(null);
		juridica.setRazaoSocial(null);
		juridica.setPessoa(null);
		juridica.setRepresentanteLegal(null);
		juridica.setTelefoneComercial(null);
		

		binder.reverseBind(this.comitente);
		binder.reverseBind(this.juridica);

		this.setErrorIcon(false);
		updateUI();
	}
	
	public void editRegister(Pessoa objPessoa ){
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.comitente, objPessoa);
			comitente.setId(objPessoa.getId());
			comitente.setNome(objPessoa.getNome());
			comitente.setEmail(objPessoa.getEmail());
			comitente.setTelefone(objPessoa.getTelefone());
			comitente.setFax(objPessoa.getFax());
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.juridica, objPessoa.getTipoPessoa());
			Juridica objJuridica  = null;
			objJuridica = (Juridica) objPessoa.getTipoPessoa();
			try {
				objJuridica = (Juridica) RemotePessoaService.getInstance().getPessoaJuridicaByIdPessoa(objPessoa.getId());
				this.juridica.setId(objJuridica.getId());
				juridica.setCnpj(objJuridica.getCnpj());
				juridica.setInscricaoEstadual(objJuridica.getInscricaoEstadual());
				juridica.setRamoAtividade(objJuridica.getRamoAtividade());
				juridica.setRazaoSocial(objJuridica.getRazaoSocial());
				juridica.setRepresentanteLegal(objJuridica.getRepresentanteLegal());
				juridica.setTelefoneComercial(objJuridica.getTelefoneComercial());
				juridica.setPessoa(comitente);				
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		binder.reverseBind(this.comitente);
		binder.reverseBind(juridica);
		this.setErrorIcon(false);
	}
/*	public void editRegister(Pessoa objPessoa ){
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.comitente, objPessoa);
			pessoaEmDivulgacao.setId(objPessoa.getId());
			pessoaEmDivulgacao.setNome(objPessoa.getNome());
			pessoaEmDivulgacao.setEmail(objPessoa.getEmail());
			pessoaEmDivulgacao.setTelefone(objPessoa.getTelefone());
			pessoaEmDivulgacao.setFax(objPessoa.getFax());
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.juridica, objPessoa.getTipoPessoa());
			Juridica objJuridica  = null;
			objJuridica = (Juridica) objPessoa.getTipoPessoa();
			Session s =null;
			try {
				s = LocalServicesUtility.getInstance().openSession();
				objJuridica = (Juridica) DataAccessUtils.uniqueResult(s.createQuery("from Juridica f where f.pessoa.id =" + objPessoa.getId()).list());
				this.juridica.setId(objJuridica.getId());
				juridica.setCnpj(objJuridica.getCnpj());
				juridica.setInscricaoEstadual(objJuridica.getInscricaoEstadual());
				juridica.setRamoAtividade(objJuridica.getRamoAtividade());
				juridica.setRazaoSocial(objJuridica.getRazaoSocial());
				juridica.setRepresentanteLegal(objJuridica.getRepresentanteLegal());
				juridica.setTelefoneComercial(objJuridica.getTelefoneComercial());
				juridica.setPessoa(pessoaEmDivulgacao);				
			} catch (Exception e1) {
				e1.printStackTrace();
				
			}finally{
				if (s != null) s.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

		binder.reverseBind(this.comitente);
		binder.reverseBind(juridica);
		this.setErrorIcon(false);
	}*/
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(0, 140, 520, 90));
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		this.nomeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.razaoSocialTextField.firePropertyChange("warnBorder", !bool, bool);
		this.emailTextField.firePropertyChange("warnBorder", !bool, bool);
		this.telefoneTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getCnpjTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getInscricaoEstadualTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getRepresentanteLegalComboBox().firePropertyChange("warnBorder", !bool, bool);
		this.getRamoAtividadeTextField().firePropertyChange("warnBorder", !bool, bool);
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

	protected JComponent getRazaoSocialTextField(){
		if(razaoSocialTextField == null){
			razaoSocialTextField = new JTextField();
			razaoSocialTextField.setText("");
			razaoSocialTextField.setSize(new java.awt.Dimension(180,20));
			razaoSocialTextField.setLocation(new java.awt.Point(105,23));
			this.binder.addBindProperty(this.juridica, this.razaoSocialTextField, "razaoSocial");
			new LengthLimitedDocument(120,razaoSocialTextField);
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

	protected JComponent getEmailTextField(){
		if(emailTextField == null){
			emailTextField = new JTextField();
			emailTextField.setText("");
			emailTextField.setSize(new java.awt.Dimension(180,20));
			emailTextField.setLocation(new java.awt.Point(105,46));
			this.binder.addBindProperty(this.comitente, this.emailTextField, "email");
			new LengthLimitedDocument(200,emailTextField);
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
			telefoneComercialLabel = new JLabel("* Fone Comercial:");
			telefoneComercialLabel.setSize(new Dimension(100, 20));
			telefoneComercialLabel.setLocation(new java.awt.Point(0,69));
			telefoneComercialLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return telefoneComercialLabel;
	}

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
			this.binder.addBindProperty(this.comitente, this.telefoneTextField, "telefone");
			
			this.hashComps.put("telefone", this.telefoneTextField);
			JWarningComponent warn = new JWarningComponent( this.telefoneTextField);
			warn.setBounds(105, 92, 180, 20);
			return warn;
		}
		return telefoneTextField;
	}
	
	protected JLabel getTelefoneTextFieldLabel(){
		if(telefoneTextFieldLabel == null){
			telefoneTextFieldLabel = new JLabel("* " + messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Telefone"));
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
	private JComponent getInscricaoEstadualTextField() {
		if (inscricaoEstadualTextField == null) {
			inscricaoEstadualTextField = new JTextField();
			inscricaoEstadualTextField.setBounds(new Rectangle(420, 46, 100, 20));
			this.binder.addBindProperty(this.juridica, this.inscricaoEstadualTextField, "inscricaoEstadual");
			new LengthLimitedDocument(20,inscricaoEstadualTextField);
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
			representanteLegalComboBox.setBounds(new Rectangle(289, 115, 231, 20));
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
	private JComponent getRamoAtividadeTextField() {
		if (ramoAtividadeTextField == null) {
			ramoAtividadeTextField = new JTextField();
			ramoAtividadeTextField.setSize(new java.awt.Dimension(180, 20));
			ramoAtividadeTextField.setLocation(new java.awt.Point(105, 115));
			new LengthLimitedDocument(150,ramoAtividadeTextField);
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
	private JComponent getFaxTextField() {
		if (faxTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				faxTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				faxTextField = new JTextField();
			}
			faxTextField.setBounds(new Rectangle(365, 0, 148, 20));
			this.binder.addBindProperty(this.comitente,this.faxTextField, "fax");			
			this.hashComps.put("fax", this.faxTextField);
			JWarningComponent warn = new JWarningComponent( this.faxTextField);
			warn.setBounds(365, 0, 148, 20);
			return warn;
		}
		return faxTextField;
	}

	private JButton getNovoRepLegalButton() {
		if (novoRepLegalButton == null) {
			novoRepLegalButton = new JButton();
			novoRepLegalButton.setBounds(new Rectangle(456, 92, 24, 22));
			//novoRepLegalButton.setText("Novo");
			novoRepLegalButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_add.png")));
			novoRepLegalButton.setToolTipText("cadastrar novo representante legal");
			novoRepLegalButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					AdapitVirtualFrame.getInstance().cadastrarRepresentante();
				}
				
			});
		}
		return novoRepLegalButton;
	}
	
	private JButton getEditarRepresentanteButton() {
		if (editarRepresentanteButton == null) {
			editarRepresentanteButton = new JButton();
			editarRepresentanteButton.setBounds(new Rectangle(483, 92, 24, 22));
			editarRepresentanteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_edit.png")));
			editarRepresentanteButton.setToolTipText("editar os dados do representante selecionado abaixo");
			editarRepresentanteButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int index = representanteLegalComboBox.getSelectedIndex();
							if (index < 0) return;
							AdapitVirtualFrame.getInstance().editarRepresentante(representantes.get(index));
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
			atualizarRepresentantesButton.setToolTipText("atualizar a lista de representantes abaixo");
			atualizarRepresentantesButton
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					try {
						List<RepresentanteLegal> list = RemotePessoaService.getInstance().listRepresentanteLegalLazy();//s.createQuery("select rep.id,rep.nome from RepresentanteLegal rep").list();
						representanteLegalComboBox.removeAllItems();
						representantes.clear();
						for(RepresentanteLegal rep: list){
							representanteLegalComboBox.addItem(rep.getNome());							
							representantes.add(rep);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*Session s = null;
					
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
					}*/
				}
			});
		}
		return atualizarRepresentantesButton;
	}

	private List<RepresentanteLegal> representantes = new ArrayList<RepresentanteLegal>();  //  @jve:decl-index=0:


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
	
	
	
	public Juridica getJuridica() {
		return juridica;
	}

	public PessoaEmDivulgacao getComitente() {
		return comitente;
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