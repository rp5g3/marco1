package com.adapit.portal.ui.forms.pessoa.comitentesimples;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.adapit.portal.entidades.ComitenteSimples;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.LengthLimitedDocument;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings({"serial","unchecked"})
public class DadosPessoaDivulgavelPessoaFisicaPanel extends JPanel{
	
	private JTextField nomeTextField;
	
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private ComitenteSimples comitente = new ComitenteSimples();  //  @jve:decl-index=0:

	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel nomeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField emailTextField;
	
	private JLabel emailTextFieldLabel;
	
	private JTextField telefoneTextField;
	
	private JLabel telefoneTextFieldLabel;

	private JLabel faxLabel = null;

	private JTextField faxTextField = null;
	
	private CadastrarPessoaDivulgavelTabs cadastrarComitenteForm;

	public DadosPessoaDivulgavelPessoaFisicaPanel(	CadastrarPessoaDivulgavelTabs cadastrarComitenteForm) {
		this.cadastrarComitenteForm = cadastrarComitenteForm;
		initialize();
	}

	@SuppressWarnings("deprecation")
	private void initialize(){
		faxLabel = new JLabel();
		faxLabel.setBounds(new Rectangle(0, 69, 100, 20));
		faxLabel.setText("Fax:");
		setSize(new Dimension(577, 340));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getNomeTextField());
		add(getNomeTextFieldLabel());
		add(getEmailTextField());
		add(getEmailTextFieldLabel());
		add(getTelefoneTextField());
		add(getTelefoneTextFieldLabel());
		this.add(faxLabel, null);
		this.add(getFaxTextField(), null);
		
		getNomeTextField().setNextFocusableComponent(getTelefoneTextField());
		getEmailTextField().setNextFocusableComponent(getTelefoneTextField());
		getTelefoneTextField().setNextFocusableComponent(getTelefoneTextField());

		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		
	}

	protected JComponent getNomeTextField(){
		if(nomeTextField == null){
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new Dimension(400, 20));
			nomeTextField.setLocation(new java.awt.Point(105,23));
			new LengthLimitedDocument(100,nomeTextField);
			this.binder.addBindProperty(this.comitente, this.nomeTextField, "nome");
			
			this.hashComps.put("nome", this.nomeTextField);
			JWarningComponent warn = new JWarningComponent( this.nomeTextField);
			warn.setBounds(105, 23, 400, 20);
			return warn;
		}
		return nomeTextField;
	}
	
	public void bindComitente(){
		//pessoaEmDivulgacao.setTipoPessoa(fisica);
		comitente.setNome(nomeTextField.getText());
		comitente.setEmail(emailTextField.getText());
		if (comitente.getEmail() == null || comitente.getEmail().equals(""))
			comitente.setEmail(comitente.getNome());
		comitente.setTelefone(telefoneTextField.getText());
		if (comitente.getTelefone() == null || comitente.getTelefone().equals(""))
			comitente.setTelefone("(??) ????-????");
		comitente.setFax(faxTextField.getText());	
		try {
			if (cadastrarComitenteForm != null && cadastrarComitenteForm.pessoaEmDivulgacao != null){
				comitente.setLogotipo(cadastrarComitenteForm.pessoaEmDivulgacao.getLogotipo());
				comitente.setDescricao(((JTextArea)cadastrarComitenteForm.getDescricaoComitenteTextArea()).getText());
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ComitenteSimples validatePessoaForm() throws Exception{
		setErrorIcon(false);
		bindComitente();
		if (comitente.getNome() == null || comitente.getNome().equals("")){
			reportWarning("nome", "O valor para a propriedade", "deve ser especificado");
			throw new Exception(messages.getMessage("ErroCamposPessoaIncorretos"));
		}
		//if (!validatePessoaBean()) throw new Exception(messages.getMessage("ErroCamposPessoaIncorretos"));
		return comitente;
	}
	
	public ComitenteSimples cadastrePessoa() throws Exception{
		return validatePessoaForm();
	}
	
	public boolean validatePessoaBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.comitente, "comitenteSimples");
		
		if (errors == null) return true;
		
		String name;
		JComponent comp;
		if (errors != null ){
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
		
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
//Nunca definir um novo objeto entidade!!!

		comitente.setId(0);
		comitente.setNome(null);
		comitente.setEmail(null);
		comitente.setFax(null);
		comitente.setTelefone(null);
		comitente.setLogotipo(null);
		
		binder.reverseBind(this.comitente);

		this.setErrorIcon(false);
		updateUI();
	}

	public void editRegister(Pessoa objPessoa) {
		try {
			comitente.setId(objPessoa.getId());
			comitente.setNome(objPessoa.getNome());
			comitente.setEmail(objPessoa.getEmail());
			comitente.setTelefone(objPessoa.getTelefone());
			comitente.setFax(objPessoa.getFax());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		binder.reverseBind(this.comitente);
		this.setErrorIcon(false);
	}

	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(0, 156, 520, 74));
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){
		this.nomeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.emailTextField.firePropertyChange("warnBorder", !bool, bool);
		this.telefoneTextField.firePropertyChange("warnBorder", !bool, bool);		
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getNomeTextFieldLabel(){
		if(nomeTextFieldLabel == null){
			nomeTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Nome"));
			nomeTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			nomeTextFieldLabel.setLocation(new java.awt.Point(0,23));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}
	
	protected JComponent getEmailTextField(){
		if(emailTextField == null){
			emailTextField = new JTextField();
			emailTextField.setText("");
			emailTextField.setSize(new java.awt.Dimension(400,20));
			emailTextField.setLocation(new java.awt.Point(105,46));
			this.binder.addBindProperty(this.comitente, this.emailTextField, "email");
			new LengthLimitedDocument(200,emailTextField);
			this.hashComps.put("email", this.emailTextField);
			
			JWarningComponent warn = new JWarningComponent( this.emailTextField);
			warn.setBounds(105, 46, 400, 20);
			return warn;
		}
		return emailTextField;
	}
	
	protected JLabel getEmailTextFieldLabel(){
		if(emailTextFieldLabel == null){
			emailTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Email"));
			emailTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			emailTextFieldLabel.setLocation(new java.awt.Point(0,46));
			emailTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return emailTextFieldLabel;
	}

	protected JComponent getTelefoneTextField(){
		if(telefoneTextField == null){
			/*try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				telefoneTextField = new JFormattedTextField(format);
			} catch (Exception e) {*/
				telefoneTextField = new JTextField();
			//}
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
			telefoneTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.pessoacadastro.PessoaFisicaCadastreForm.Telefone"));
			telefoneTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			telefoneTextFieldLabel.setLocation(new java.awt.Point(0,92));
			telefoneTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return telefoneTextFieldLabel;
	}
	
	private JComponent getFaxTextField() {
		if (faxTextField == null) {
			/*try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("(##) ####-####");
				faxTextField = new JFormattedTextField(format);
			} catch (Exception e) {*/
				faxTextField = new JTextField();
			//}
			faxTextField.setBounds(new Rectangle(105, 69, 180, 20));
			this.binder.addBindProperty(this.comitente,this.faxTextField, "fax");			
			this.hashComps.put("fax", this.faxTextField);
			
			JWarningComponent warn = new JWarningComponent( this.faxTextField);
			warn.setBounds(105, 69, 180, 20);
			return warn;
		}
		return faxTextField;
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
	
	public void reportWarning(String propriedade, String title, String errorMsg) {
		try{
			JComponent comp = (JComponent) this.hashComps.get(propriedade);
			comp.firePropertyChange("warnBorder", false, true);
			try {
				
				getErrorPanel().addError(title+" "+propriedade+" "+errorMsg,(JComponent) hashComps.get(propriedade));
				((JComponent) hashComps.get(propriedade)).setToolTipText(title+" "+propriedade+" "+errorMsg);
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