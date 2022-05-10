package com.adapit.portal.ui.forms.usuario;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.forms.FormOperation;
import com.adapit.portal.ui.frames.usuario.ManutencaoUsuarioDialog;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.IdGenerator;


@SuppressWarnings("serial")
public class AdminUserDataForm extends JPanel{
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private SwingBinder binder = new SwingBinder();
	
	private Usuario usuario = new Usuario();  //  @jve:decl-index=0:
	

	private Map hashComps = new java.util.HashMap();

	
	private JTextField loginTextField;
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel loginTextFieldLabel;
	
	private JPasswordField passwordPasswordField;
	
	private JLabel passwordPasswordFieldLabel;
	
	private JPasswordField repeticaoPasswordField;
	
	private JLabel repeticaoPasswordFieldLabel;
	
	private JComboBox roleComboBox;
	
	private JLabel roleComboBoxLabel;
	
	private DateHourChooser lastAccessDateFieldChooser;
	
	private JLabel lastAccessDateFieldChooserLabel;

	public FormOperation formOperation=FormOperation.SAVE;  //  @jve:decl-index=0:

	private JButton editarUsuarioButton = null;
	
	private UserDataChangeListener userDataChangeListener;

	
	public AdminUserDataForm(UserDataChangeListener userDataChangeListener){
		this.userDataChangeListener = userDataChangeListener;
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(534, 340));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getLoginTextField());
		add(getLoginTextFieldLabel());
		add(getPasswordPasswordField());
		add(getPasswordPasswordFieldLabel());
		add(getRepeticaoPasswordField());
		add(getRepeticaoPasswordFieldLabel());
		add(getRoleComboBox());
		add(getRoleComboBoxLabel());
		add(getEditarUsuarioButton());
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getLoginTextField(){

		if(loginTextField == null){
			loginTextField = new JTextField();
			loginTextField.setText("");
			loginTextField.setSize(new java.awt.Dimension(180,20));
			loginTextField.setLocation(new java.awt.Point(105,53));
			this.binder.addBindProperty(this.usuario, this.loginTextField, "login");
			
			this.hashComps.put("login", this.loginTextField);
			JWarningComponent warn = new JWarningComponent( this.loginTextField);
			warn.setBounds(105, 53, 180, 20);
			return warn;
		}
		return loginTextField;
	}
	
	@SuppressWarnings("deprecation")
	public Usuario validateUsuarioForm() throws Exception{
		setErrorIcon(false);
		//if(usuario.getLogin() == null || usuario.getLogin().equals("")){
			try {
				usuario.setLogin(((JTextField)getLoginTextField()).getText());
				usuario.setPassword(((JPasswordField)getPasswordPasswordField()).getText());
				usuario.setPasswordConf(((JPasswordField)getRepeticaoPasswordField()).getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			usuario.setUserCadastreType(UserCadastreType.valueOf(((String)roleComboBox.getSelectedItem()).replace(" ", "_")));
		
			if (!validateUsuarioBean()) throw new Exception(messages.getMessage("ErroCamposUsuarioIncorretos"));
		//}
		return usuario;
	}
	
	
	
	public boolean usuarioExiste(){
		try{
			//RemoteUserService.getInstance().isValid(loginTextField.getText(), passwordPasswordField.getText());
			boolean usuarioExiste = RemoteUserService.getInstance().isValid(usuario.getLogin());
			if (usuarioExiste){
				getLoginTextField().firePropertyChange("warnBorder", false, true);
				try {
					getErrorPanel().addError("Login já existe! Informe um outro login",getLoginTextField());
				} catch (Exception e) {
					e.printStackTrace();
				}
				getLoginTextField().setToolTipText("Login já existe. Informe um outro login para efetivar a operação");
			
				getErrorPanel().updateErrorList();
				getErrorPanel().setVisible(true);
				return true;
			}else return false;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return false;
	}
	
	public Usuario cadastreUsuario() throws Exception{
		if (usuario.isNewUser()) return validateUsuarioForm();
		else return usuario;
	}
	
	@SuppressWarnings({ "deprecation" })
	public boolean validateUsuarioBean(){
		getErrorPanel().removeAllElements();
		try {
			if (processFocus) {
				if (UIUtil.processFocus(this)) {
					processFocus = false;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.usuario, "usuario");
		if (errors == null){
			if (passwordPasswordField.getText() != null
					&& repeticaoPasswordField.getText() != null){
				if (passwordPasswordField.getText().equals(repeticaoPasswordField.getText())
						&& !(passwordPasswordField.getText().length() < 6 || passwordPasswordField.getText().length() > 15)){
					if (formOperation == FormOperation.SAVE){
						if (usuarioExiste()) return false;
						else return true;
					}
					else return true;
				}
			} else {
				if (formOperation == FormOperation.SAVE){
					if (usuarioExiste()) return false;
					else return true;
				}
				else return true;
			}
		}
		if (errors != null){
			Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
			String name;
			JComponent comp;
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
		if (passwordPasswordField.getText() != null
				&& repeticaoPasswordField.getText() != null){
				if (!passwordPasswordField.getText().equals(repeticaoPasswordField.getText())){
					
					getRepeticaoPasswordField().firePropertyChange("warnBorder", false, true);
					try {
						getErrorPanel().addError("As senhas não conferem!",getRepeticaoPasswordField());
					} catch (Exception e) {
						e.printStackTrace();
					}
					getRepeticaoPasswordField().setToolTipText("As senhas devem ser idênticas");
				}
				if (passwordPasswordField.getText().length() < 6 || passwordPasswordField.getText().length() > 8){
					getPasswordPasswordField().firePropertyChange("warnBorder", false, true);
					try {
						getErrorPanel().addError("A senha deve conter de seis a oito caracteres",getPasswordPasswordField());
					} catch (Exception e) {
						e.printStackTrace();
					}
					getPasswordPasswordField().setToolTipText("Favor colocar entre 6 e 8 caracteres");
				}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
//Nunca definir um novo objeto entidade!!!
		formOperation = FormOperation.SAVE;
		usuario.setDeactivationReasonList(null);
		usuario.setDadosPessoais(null);
		usuario.setAccessList(null);
		this.usuario.setNewUser(true);
		usuario.setLogin(null);
		usuario.setPassword(null);
		usuario.setPasswordConf(null);
		usuario.setActive(false);
		usuario.setLastAccess(null);
		usuario.setTicket(IdGenerator.getInstance().generateId(25));

		binder.reverseBind(this.usuario);
		
		getLoginTextField().setEnabled(true);
		getPasswordPasswordField().setEnabled(true);
		getRepeticaoPasswordField().setEnabled(true);
		getPasswordPasswordField().setVisible(true);
		getRepeticaoPasswordField().setVisible(true);
		getEditarUsuarioButton().setVisible(false);
		pwdComponent.setVisible(true);
		this.setErrorIcon(false);
		loginTextField.setEditable(true);
		updateUI();
	}

	public void editRegister(Usuario objUsuario ){
		//Nunca passar como argumento novos objetos!!!
		formOperation = FormOperation.UPDATE;
		try {
			objUsuario = RemoteUserService.getInstance().getUsuarioByIdCascadingProperties(objUsuario.getLogin(), true, true, true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.usuario, objUsuario);
		}catch(Exception e){
			e.printStackTrace();
		}
		((JPasswordField)getRepeticaoPasswordField()).setText(objUsuario.getPassword());
		
		//this.usuario.setPassword(null);
		this.usuario.setNewUser(false);
		this.usuario.setPasswordConf(objUsuario.getPassword());
		this.usuario.setTicket(objUsuario.getTicket());
		
		try {
			binder.reverseBind(this.usuario);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		loginTextField.setEditable(false);
		getPasswordPasswordField().setVisible(false);
		getRepeticaoPasswordField().setVisible(false);
		getEditarUsuarioButton().setVisible(true);
		pwdComponent.setVisible(false);
		roleComboBox.setSelectedItem(usuario.getUserCadastreType().name().replace("_", " "));
		this.setErrorIcon(false);
	}
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(510,90));
			logErrorPanel.setLocation(0, 160);
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		this.loginTextField.firePropertyChange("warnBorder", !bool, bool);
		this.passwordPasswordField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getLoginTextFieldLabel(){

		if(loginTextFieldLabel == null){
			loginTextFieldLabel = new JLabel("* " + messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.Login"));
			loginTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			loginTextFieldLabel.setLocation(new java.awt.Point(0,53));
			loginTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return loginTextFieldLabel;
	}
	
	private JComponent pwdComponent;
	@SuppressWarnings("unchecked")
	protected JComponent getPasswordPasswordField(){

		if(passwordPasswordField == null){
			passwordPasswordField = new JPasswordField();
			passwordPasswordField.setSize(new java.awt.Dimension(180,20));
			passwordPasswordField.setLocation(new java.awt.Point(105,76));
			this.binder.addBindProperty(this.usuario, this.passwordPasswordField, "password");
			
			this.hashComps.put("password", this.passwordPasswordField);
			JWarningComponent warn = new JWarningComponent( this.passwordPasswordField);
			warn.setBounds(105, 76, 180, 20);
			pwdComponent = warn;
			return warn;
		}
		return passwordPasswordField;
	}
	
	protected JLabel getPasswordPasswordFieldLabel(){

		if(passwordPasswordFieldLabel == null){
			passwordPasswordFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.Senha"));
			passwordPasswordFieldLabel.setSize(new java.awt.Dimension(100,20));
			passwordPasswordFieldLabel.setLocation(new java.awt.Point(0,76));
			passwordPasswordFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return passwordPasswordFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getRepeticaoPasswordField(){

		if(repeticaoPasswordField == null){
			repeticaoPasswordField = new JPasswordField();
			repeticaoPasswordField.setText("");
			repeticaoPasswordField.setSize(new java.awt.Dimension(180,20));
			repeticaoPasswordField.setLocation(new java.awt.Point(105,99));
			this.binder.addBindProperty(this.usuario, this.repeticaoPasswordField, "passwordConf");
			
			this.hashComps.put("passwordConf", this.repeticaoPasswordField);
			JWarningComponent warn = new JWarningComponent( this.repeticaoPasswordField);
			warn.setBounds(105, 99, 180, 20);
			return warn;
		}
		return repeticaoPasswordField;
	}
	
	protected JLabel getRepeticaoPasswordFieldLabel(){

		if(repeticaoPasswordFieldLabel == null){
			repeticaoPasswordFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.RepitaaSenha"));
			repeticaoPasswordFieldLabel.setSize(new java.awt.Dimension(100,20));
			repeticaoPasswordFieldLabel.setLocation(new java.awt.Point(0,99));
			repeticaoPasswordFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return repeticaoPasswordFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	public JComponent getRoleComboBox(){

		if(roleComboBox == null){
			roleComboBox = new JComboBox();
			roleComboBox.setSize(new java.awt.Dimension(180,20));
			roleComboBox.setLocation(new java.awt.Point(105,122));
			for (int i=0; i < UserCadastreType.values().length; i++){
				roleComboBox.addItem(UserCadastreType.values()[i].name().replace("_", " "));
			}
			this.binder.addBindProperty(this.usuario, this.roleComboBox, "userCadastreType");
			
			this.hashComps.put("userCadastreType", this.roleComboBox);
			JWarningComponent warn = new JWarningComponent( this.roleComboBox);
			warn.setBounds(105,122,180,22);
			return warn;
		}
		return roleComboBox;
	}
	
	protected JLabel getRoleComboBoxLabel(){

		if(roleComboBoxLabel == null){
			roleComboBoxLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.TipodaConta"));
			roleComboBoxLabel.setSize(new java.awt.Dimension(100,20));
			roleComboBoxLabel.setLocation(new java.awt.Point(0,122));
			roleComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return roleComboBoxLabel;
	}
	
	protected DateHourChooser getLastAccessDateFieldChooser(){

		if(lastAccessDateFieldChooser == null){
			lastAccessDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), false, true, false);
			lastAccessDateFieldChooser.setSize(new java.awt.Dimension(180,20));
			lastAccessDateFieldChooser.setLocation(new java.awt.Point(105,145));
			return lastAccessDateFieldChooser;
		}
		return lastAccessDateFieldChooser;
	}
	
	protected JLabel getLastAccessDateFieldChooserLabel(){

		if(lastAccessDateFieldChooserLabel == null){
			lastAccessDateFieldChooserLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.ÚltimoAccesso"));
			lastAccessDateFieldChooserLabel.setSize(new java.awt.Dimension(100,20));
			lastAccessDateFieldChooserLabel.setLocation(new java.awt.Point(0,145));
			lastAccessDateFieldChooserLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return lastAccessDateFieldChooserLabel;
	}
	
	/**
	 * This method initializes editarUsuarioButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarUsuarioButton() {
		if (editarUsuarioButton == null) {
			editarUsuarioButton = new JButton();
			editarUsuarioButton.setBounds(new Rectangle(105, 14, 180, 26));
			editarUsuarioButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_edit.png")));
			editarUsuarioButton.setText("Editar dados de usuário");
			editarUsuarioButton.setVisible(false);
			editarUsuarioButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						getManutencaoDadosUsuarioDialog().editRegister(usuario);
						getManutencaoDadosUsuarioDialog().setVisible(true);
						if(userDataChangeListener != null)
							userDataChangeListener.userDataChanged();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return editarUsuarioButton;
	}

	
	private ManutencaoUsuarioDialog manutencaoDadosUsuarioDialog;
	
	public ManutencaoUsuarioDialog getManutencaoDadosUsuarioDialog(){
		if (manutencaoDadosUsuarioDialog == null){
			manutencaoDadosUsuarioDialog = new ManutencaoUsuarioDialog();
		}
		return manutencaoDadosUsuarioDialog;
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

	public Usuario getUsuario() {
		return usuario;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"