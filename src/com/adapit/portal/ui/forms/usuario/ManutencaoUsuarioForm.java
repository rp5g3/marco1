package com.adapit.portal.ui.forms.usuario;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings("serial")
public class ManutencaoUsuarioForm extends JPanel {

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private SwingBinder binder = new SwingBinder();

	private Usuario usuario = new Usuario(); // @jve:decl-index=0:
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();

	private JTextField loginTextField;

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel loginTextFieldLabel;

	private JPasswordField passwordPasswordField;

	private JLabel passwordPasswordFieldLabel;

	private JPasswordField repeticaoPasswordField;

	private JLabel repeticaoPasswordFieldLabel;

	private JButton trocarButton = null;

	private JLabel substituirSenhaLabel = null;

	private JCheckBox autenticadoCheckBox = null;

	private JCheckBox ativoCheckBox = null;

	public ManutencaoUsuarioForm() {
		initialize();
	}

	private void initialize() {
		substituirSenhaLabel = new JLabel();
		substituirSenhaLabel.setBounds(new Rectangle(32, 9, 246, 28));
		substituirSenhaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		substituirSenhaLabel.setFont(new Font("Arial", Font.BOLD, 12));
		substituirSenhaLabel.setText("Manutenção de Dados de Usuário");
		this.setSize(new Dimension(311, 255));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getLoginTextField());
		add(getLoginTextFieldLabel());
		add(getPasswordPasswordField());
		add(getPasswordPasswordFieldLabel());
		add(getRepeticaoPasswordField());
		add(getRepeticaoPasswordFieldLabel());
		add(this.getErrorPanel());
		this.setErrorIcon(false);
		this.add(getTrocarButton(), null);
		this.add(substituirSenhaLabel, null);
		this.add(getAutenticadoCheckBox(), null);
		this.add(getAtivoCheckBox(), null);
	}

	@SuppressWarnings("unchecked")
	protected JComponent getLoginTextField() {
		if (loginTextField == null) {
			loginTextField = new JTextField();
			loginTextField.setText("");
			loginTextField.setSize(new java.awt.Dimension(180, 20));
			loginTextField.setLocation(new java.awt.Point(105, 53));
			this.binder.addBindProperty(this.usuario, this.loginTextField,
					"login");

			this.hashComps.put("login", this.loginTextField);
			JWarningComponent warn = new JWarningComponent(this.loginTextField);
			warn.setBounds(105, 53, 180, 20);
			return warn;
		}
		return loginTextField;
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public Usuario validateUsuarioForm() throws Exception {
		setErrorIcon(false);
		if (usuario.getLogin() == null)
			throw new Exception("Login de usuário não pode ser nulo");
		usuario.setPassword(((JPasswordField) getPasswordPasswordField())
				.getText());
		usuario.setPasswordConf(((JPasswordField) getRepeticaoPasswordField())
				.getText());
		usuario.setActive(ativoCheckBox.isSelected());
		usuario.setAutenticado(autenticadoCheckBox.isSelected());
		if (!validateUsuarioBean())
			throw new Exception(messages
					.getMessage("ErroCamposUsuarioIncorretos"));

		return usuario;
	}

	public Usuario updateUsuario() throws Exception {
		Usuario user = validateUsuarioForm();
		user.setPassword(Usuario.encript(user.getPassword()));
		return RemoteUserService.getInstance().updateUsuario(user);
		/*
		 * Session s = null; try{ s =
		 * LocalServicesUtility.getInstance().openSession();
		 * s.beginTransaction();
		 * user.setPassword(Usuario.encript(user.getPassword()));
		 * s.createQuery("update Usuario user set " + "user.password=:pwd, " +
		 * "user.active=:active, " + "user.autenticado=:aut " + "where
		 * user.login=:login") .setParameter("pwd", user.getPassword())
		 * .setParameter("active", user.getActive())
		 * .setParameter("aut",user.isAutenticado()) .setParameter("login",
		 * user.getLogin()).executeUpdate(); s.getTransaction().commit();
		 * }catch(Exception ex){ ex.printStackTrace(); throw ex; }finally{ if (s !=
		 * null && s.isOpen()) s.close(); } return user;
		 */
	}

	@SuppressWarnings( { "unchecked", "deprecation" })
	public boolean validateUsuarioBean() {
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
		if (errors == null) {
			if (passwordPasswordField.getText() != null
					&& repeticaoPasswordField.getText() != null) {
				if (passwordPasswordField.getText().equals(
						repeticaoPasswordField.getText())
						&& !(passwordPasswordField.getText().length() < 6 || passwordPasswordField
								.getText().length() > 15)) {
					return true;
				}
			} else
				return true;

		}
		if (errors != null) {
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
						try {
							getErrorPanel().addError(messages.getMessage(msg),
									comp);
							comp.setToolTipText(messages.getMessage(msg));
						} catch (Exception e) {
							e.printStackTrace();
						}
					} else {
						List args = (List) obj[1];
						Object[][] params = new Object[args.size()][2];
						for (int j = 0; j < args.size(); j++) {
							String key = (String) args.get(j);
							params[j][0] = key;
							params[j][1] = null;
						}
						try {
							getErrorPanel().addError(
									messages.getMessage(msg, params), comp);
							comp.setToolTipText(messages
									.getMessage(msg, params));
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		if (passwordPasswordField.getText() != null
				&& repeticaoPasswordField.getText() != null) {
			if (!passwordPasswordField.getText().equals(
					repeticaoPasswordField.getText())) {
				getRepeticaoPasswordField().firePropertyChange("warnBorder",
						false, true);
				try {
					getErrorPanel().addError("As senhas não conferem!",
							getRepeticaoPasswordField());
				} catch (Exception e) {
					e.printStackTrace();
				}
				getRepeticaoPasswordField().setToolTipText(
						"As senhas devem ser idênticas");
			}
			if (passwordPasswordField.getText().length() < 6
					|| passwordPasswordField.getText().length() > 8) {
				getPasswordPasswordField().firePropertyChange("warnBorder",
						false, true);
				try {
					getErrorPanel().addError(
							"A senha deve conter de seis a oito caracteres",
							getPasswordPasswordField());
				} catch (Exception e) {
					e.printStackTrace();
				}
				getPasswordPasswordField().setToolTipText(
						"Favor colocar entre 6 e 8 caracteres");
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}

	public void editRegister(Usuario objUsuario) {
		// Nunca passar como argumento novos objetos!!!

		try {
			objUsuario = RemoteUserService.getInstance()
					.getUsuarioByIdCascadingProperties(objUsuario.getLogin(),
							true, true, true);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.usuario,
					objUsuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		((JPasswordField) getRepeticaoPasswordField()).setText("");

		this.usuario.setNewUser(false);
		this.usuario.setPasswordConf(objUsuario.getPassword());
		this.usuario.setTicket(objUsuario.getTicket());

		try {
			binder.reverseBind(this.usuario);
		} catch (RuntimeException e) {
			e.printStackTrace();
		}

		loginTextField.setEditable(false);
		getPasswordPasswordField().setVisible(true);
		getRepeticaoPasswordField().setVisible(true);
		((JPasswordField) getPasswordPasswordField()).setText("");
		((JPasswordField) getRepeticaoPasswordField()).setText("");
		ativoCheckBox.setSelected(usuario.getActive());
		autenticadoCheckBox.setSelected(usuario.isAutenticado());
		this.setErrorIcon(false);
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(301, 50));
			logErrorPanel.setLocation(5, 198);
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {

		this.loginTextField.firePropertyChange("warnBorder", !bool, bool);
		this.passwordPasswordField
				.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getLoginTextFieldLabel() {

		if (loginTextFieldLabel == null) {
			loginTextFieldLabel = new JLabel(
					"* "
							+ messages
									.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.Login"));
			loginTextFieldLabel.setSize(new Dimension(94, 20));
			loginTextFieldLabel.setLocation(new Point(6, 53));
			loginTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return loginTextFieldLabel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getPasswordPasswordField() {

		if (passwordPasswordField == null) {
			passwordPasswordField = new JPasswordField();
			passwordPasswordField.setSize(new java.awt.Dimension(180, 20));
			passwordPasswordField.setLocation(new java.awt.Point(105, 76));
			this.binder.addBindProperty(this.usuario,
					this.passwordPasswordField, "password");

			this.hashComps.put("password", this.passwordPasswordField);
			JWarningComponent warn = new JWarningComponent(
					this.passwordPasswordField);
			warn.setBounds(105, 76, 180, 20);
			return warn;
		}
		return passwordPasswordField;
	}

	protected JLabel getPasswordPasswordFieldLabel() {

		if (passwordPasswordFieldLabel == null) {
			passwordPasswordFieldLabel = new JLabel(
					"* "
							+ messages
									.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.Senha"));
			passwordPasswordFieldLabel.setSize(new Dimension(93, 20));
			passwordPasswordFieldLabel.setLocation(new Point(7, 76));
			passwordPasswordFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return passwordPasswordFieldLabel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getRepeticaoPasswordField() {

		if (repeticaoPasswordField == null) {
			repeticaoPasswordField = new JPasswordField();
			repeticaoPasswordField.setText("");
			repeticaoPasswordField.setSize(new java.awt.Dimension(180, 20));
			repeticaoPasswordField.setLocation(new java.awt.Point(105, 99));
			this.binder.addBindProperty(this.usuario,
					this.repeticaoPasswordField, "passwordConf");

			this.hashComps.put("passwordConf", this.repeticaoPasswordField);
			JWarningComponent warn = new JWarningComponent(
					this.repeticaoPasswordField);
			warn.setBounds(105, 99, 180, 20);
			return warn;
		}
		return repeticaoPasswordField;
	}

	protected JLabel getRepeticaoPasswordFieldLabel() {

		if (repeticaoPasswordFieldLabel == null) {
			repeticaoPasswordFieldLabel = new JLabel(
					"* "
							+ messages
									.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminUserDataForm.RepitaaSenha"));
			repeticaoPasswordFieldLabel.setSize(new Dimension(93, 20));
			repeticaoPasswordFieldLabel.setLocation(new Point(7, 99));
			repeticaoPasswordFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return repeticaoPasswordFieldLabel;
	}

	/**
	 * This method initializes trocarButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getTrocarButton() {
		if (trocarButton == null) {
			trocarButton = new JButton();
			trocarButton.setBounds(new Rectangle(99, 170, 130, 26));
			trocarButton.setFont(new Font("Arial", Font.BOLD, 11));
			trocarButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/user_save.png")));
			trocarButton.setText("Atualizar");
			trocarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						updateUsuario();
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e1) {
						AdapitVirtualFrame
								.getInstance()
								.showErrorDialog("Modificação de senha",
										"Não foi possível modificar a senha do usuário");
					}
				}
			});
		}
		return trocarButton;
	}

	/**
	 * This method initializes autenticadoCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getAutenticadoCheckBox() {
		if (autenticadoCheckBox == null) {
			autenticadoCheckBox = new JCheckBox();
			autenticadoCheckBox.setBounds(new Rectangle(100, 120, 184, 21));
			autenticadoCheckBox.setText("Autenticado");
		}
		return autenticadoCheckBox;
	}

	/**
	 * This method initializes ativoCheckBox
	 * 
	 * @return javax.swing.JCheckBox
	 */
	private JCheckBox getAtivoCheckBox() {
		if (ativoCheckBox == null) {
			ativoCheckBox = new JCheckBox();
			ativoCheckBox.setBounds(new Rectangle(101, 143, 179, 21));
			ativoCheckBox.setText("Ativo");
		}
		return ativoCheckBox;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(295, 219));
				gui.add(new AdminUserDataForm(null), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unused")
	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}// end of catch block
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

} // @jve:decl-index=0:visual-constraint="10,10"
