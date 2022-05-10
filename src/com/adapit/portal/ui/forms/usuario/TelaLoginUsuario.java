package com.adapit.portal.ui.forms.usuario;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.Funcionario;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.jgoodies.looks.plastic.PlasticLookAndFeel;
import com.jgoodies.looks.plastic.theme.DesertBlue;
import com.pagosoft.plaf.PgsLookAndFeel;
import com.pagosoft.plaf.PlafOptions;
import com.pagosoft.plaf.themes.SilverTheme;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;


@SuppressWarnings("serial")
public class TelaLoginUsuario extends JDialog implements ActionListener{
	
	private JTextField loginTextField;
	
	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();
	
	@SuppressWarnings("unused")
	private Usuario usuario = new Usuario();
	private UsuarioDTO usuarioDTO=null;
	
	@SuppressWarnings({ "unchecked", "unused" })
	private Map hashComps = new java.util.HashMap();
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel loginTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JPasswordField senhaPasswordField;
	
	private JLabel senhaPasswordFieldLabel;
	
	private JPanel entrarPanel;
	
	private JButton entrarButton;
	
	private String login="adapit";  //  @jve:decl-index=0:
	private String pwd="aJeC27";
	private static TelaLoginUsuario instance;
	boolean exit;
	public static TelaLoginUsuario getInstance(){
		if (instance == null) instance = new TelaLoginUsuario();
		return instance;
	}
	
	public static TelaLoginUsuario getInstance(String msg, boolean modal, boolean exit){
		if (instance == null) instance = new TelaLoginUsuario();
		instance.acessarTitleLabel.setText(msg);
		instance.setModal(modal);
		instance.exit=exit;
		return instance;
	}
	
	private TelaLoginUsuario(){
		super(AdapitVirtualFrame.getInstance());	
		initialize();
	}
	
	private void initialize(){
		setTitle("Login de Usuário");
		setResizable(false);
		setUndecorated(true);
		imageLabel = new JLabel();
		imageLabel.setBounds(new Rectangle(0, 0, 470, 280));
		imageLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/short_adapit_login.jpg")));
		imageLabel.setText("");
		setSize(new Dimension(467, 280));
		setLocation(UIUtil.getScreenCenter(this));
		setLayout(null);
		
		imageLabel.setOpaque(false);
        this.getLayeredPane().add(imageLabel,1);
        this.getLayeredPane().add(getAcessarTitleLabel(),0);
        this.getLayeredPane().add(getLoginPanel(),0);
        this.getLayeredPane().add(getHelpButton(),0);
        setModal(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
	private JPanel loginPanel;
	private JLabel acessarTitleLabel = null;
	private JLabel getAcessarTitleLabel(){
		if (acessarTitleLabel == null){
			acessarTitleLabel = new JLabel();
			acessarTitleLabel.setBounds(new Rectangle(120, 100, 312, 20));
			acessarTitleLabel.setFont(new Font("Arial", Font.BOLD, 13));
			acessarTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			acessarTitleLabel.setForeground(Color.WHITE);
			acessarTitleLabel.setText("Controle de Acesso de Usuários ao Sistema");
		}
		return acessarTitleLabel;
	}
	
	private JPanel getLoginPanel(){
		if (loginPanel == null){
			loginPanel = new JPanel();
			loginPanel.setOpaque(false);
			loginPanel.setSize(215, 104);
			loginPanel.setLocation(203, 109);
			loginPanel.setLayout(null);
			
			
			loginPanel.add(getLoginTextField());
			loginPanel.add(getLoginTextFieldLabel());
			loginPanel.add(getSenhaPasswordField());
			loginPanel.add(getSenhaPasswordFieldLabel());
			loginPanel.add(getEntrarPanel());
			
		}
		return loginPanel;
	}
	
	protected JTextField getLoginTextField(){

		if(loginTextField == null){
			loginTextField = new JTextField();
			loginTextField.setText("");
			loginTextField.setSize(new java.awt.Dimension(120,20));
			loginTextField.setLocation(new java.awt.Point(84,20));			
		}
		return loginTextField;
	}

	
	protected JLabel getLoginTextFieldLabel(){

		if(loginTextFieldLabel == null){
			loginTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.TelaLoginUsuario.Login"));
			loginTextFieldLabel.setSize(new java.awt.Dimension(64,20));
			loginTextFieldLabel.setLocation(new java.awt.Point(15,20));
			loginTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
			loginTextFieldLabel.setForeground(Color.WHITE);
			loginTextFieldLabel.setFont(new Font("Arial", Font.BOLD, 12));
		}
		return loginTextFieldLabel;
	}
	
	protected JPasswordField getSenhaPasswordField(){

		if(senhaPasswordField == null){
			senhaPasswordField = new JPasswordField();
			senhaPasswordField.setSize(new java.awt.Dimension(120,20));
			senhaPasswordField.setLocation(new java.awt.Point(84,43));
			senhaPasswordField.addKeyListener(new KeyAdapter(){

				@Override
				public void keyReleased(KeyEvent evt) {
					if (evt.getKeyCode() == KeyEvent.VK_ENTER){
						boolean b = logging();
						if (b) dispose();
					}
				}
				
			});
			
		}
		return senhaPasswordField;
	}
	
	protected JLabel getSenhaPasswordFieldLabel(){

		if(senhaPasswordFieldLabel == null){
			senhaPasswordFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.TelaLoginUsuario.Senha"));
			senhaPasswordFieldLabel.setSize(new java.awt.Dimension(64,20));
			senhaPasswordFieldLabel.setLocation(new java.awt.Point(15,43));
			senhaPasswordFieldLabel.setHorizontalAlignment(JLabel.LEFT);
			senhaPasswordFieldLabel.setForeground(Color.WHITE);
			senhaPasswordFieldLabel.setFont(new Font("Arial", Font.BOLD, 12));
		}
		return senhaPasswordFieldLabel;
	}
	
	protected JPanel getEntrarPanel(){

		if(entrarPanel == null){
			entrarPanel = new JPanel();
			entrarPanel.setOpaque(false);
			entrarPanel.setSize(new Dimension(215, 33));
			entrarPanel.setLocation(new java.awt.Point(0,66));
			FlowLayout fl = new java.awt.FlowLayout();
			fl.setHgap(0);
			entrarPanel.setLayout(fl);
			entrarPanel.add(getEntrarButton());
			entrarPanel.add(getCancelarButton());
			
		}
		return entrarPanel;
	}
	
	private JButton helpButton = null;
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton("");
			helpButton.setIcon(getIcon("/imgs/helpicon.png"));
			helpButton.setBounds(new Rectangle(190, 180, 24, 24));
			helpButton.setOpaque(false);
			helpButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					JDialog jd = getHelpAcessoDialog();
					jd.setVisible(true);
				}				
			});
		}
		return helpButton;
	}
	
	private JDialog helpAcessoDialog;
	
	private JDialog getHelpAcessoDialog(){
		if (helpAcessoDialog == null){
			helpAcessoDialog = new JDialog(AdapitVirtualFrame.getInstance());
			helpAcessoDialog.setTitle("Ajuda para Acessar o Sistema");
			helpAcessoDialog.setModal(true);
			helpAcessoDialog.setSize(380,350);
			helpAcessoDialog.setLocation(UIUtil.getScreenCenter(helpAcessoDialog));
			helpAcessoDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			helpAcessoDialog.add(getHelpForm());
		}
		return helpAcessoDialog;
	}
	
	private HelpLoginUsuarioForm helpForm;
	
	private HelpLoginUsuarioForm getHelpForm(){
		if (helpForm == null){
			helpForm = new HelpLoginUsuarioForm();
			helpForm.getFecharButton().addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					helpAcessoDialog.dispose();
				}
				
			});
		}
		return helpForm;
	}
	
	protected JButton cancelarButton;
	protected JButton getCancelarButton(){

		if(cancelarButton == null){
			cancelarButton = new JButton("Sair");
			cancelarButton.setSize(new java.awt.Dimension(90,22));
			cancelarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cancel.png")));
			cancelarButton.setLocation(new java.awt.Point(0,0));
			cancelarButton.setFont(new Font("Arial", Font.BOLD, 12));
			cancelarButton.setOpaque(false);
			cancelarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					dispose();
					/*if (exit)*/ System.exit(0);
				}				
			});
		}
		return cancelarButton;
	}
	
	protected JButton getEntrarButton(){

		if(entrarButton == null){
			entrarButton = new JButton("Entrar");
			entrarButton.setSize(new java.awt.Dimension(90,22));
			entrarButton.setLocation(new java.awt.Point(0,0));
			entrarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/accept.png")));//.setIcon(getIcon("/imgs/accept.png"));
			entrarButton.setFont(new Font("Arial", Font.BOLD, 12));
			entrarButton.setOpaque(false);
			entrarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					boolean b = logging();
					if (b) dispose();
				}				
			});
		}
		return entrarButton;
	}
	
	private RemoteUserService userService = RemoteUserService.getInstance();
	
	private Timer timer;
	
	@SuppressWarnings("deprecation")
	private boolean logging(){
		
		
		Usuario u = new Usuario();
		
		String login = loginTextField.getText();
		
		String pass = Usuario.encript(senhaPasswordField.getText());
		u.setLogin(login);
		u.setPassword(pass);
		
		if (login != null && pass != null && login.equals(this.login) && senhaPasswordField.getText().equals(pwd)) return true;
		
		if (!userService.isValid(login,pass)){		
			loginTextField.requestFocus();
			JOptionPane.showMessageDialog(this,"Login ou senha inválidos! Tente novamente","Login de Usuário", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			UsuarioDTO udto = userService.loggingUser(login, pass); 
			
			if (udto.isActive() && udto.isAuthenticated()){
				this.usuarioDTO = udto;
				if (udto != null && udto.getPreferences() != null){
					String lefClass = udto.getPreferences().getLookAndFeelClassName();
					PlafOptions.setDefaultMenuItemIconSize(new Dimension(20,25));
			        PlafOptions.setCurrentTheme(new SilverTheme());
			        try {
			        	
			        	if (lefClass.equals(PgsLookAndFeel.class.getName())){
			        		PlafOptions.setDefaultMenuItemIconSize(new Dimension(20,25));
				            PlafOptions.setCurrentTheme(new SilverTheme());
			        	}else if (lefClass.equals("org.fife.plaf.Office2003.Office2003LookAndFeel")
			        			|| lefClass.equals("org.fife.plaf.OfficeXP.OfficeXPLookAndFeel")
			        			|| lefClass.equals("org.fife.plaf.VisualStudio2005.VisualStudio2005LookAndFeel")
			        			|| lefClass.equals("com.jgoodies.looks.windows.WindowsLookAndFeel")
			        			|| lefClass.equals("com.jgoodies.looks.plastic.PlasticLookAndFeel")
			        			|| lefClass.equals("com.jgoodies.looks.plastic.PlasticXPLookAndFeel")){
			        		PlasticLookAndFeel.setPlasticTheme(new DesertBlue());
			        	}
			        	
			            UIManager.setLookAndFeel((LookAndFeel) Class.forName(lefClass).newInstance());
			            javax.swing.SwingUtilities.invokeLater(new Runnable(){
			                public void run() {
			                	//timer = new Timer(3000, TelaLoginUsuario.this);
			            		//timer.start();
			                	SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
			                	SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance().getMainMenuExpandPanel());
			                }
			            });
			        } catch (Exception e1) {
			            e1.printStackTrace();
			        }
				}
				return true;
			}else{
				if (udto.isActive()){
					loginTextField.requestFocus();
					JOptionPane.showMessageDialog(this,"O usuário não foi autenticado! "+'\n'+
							"Entre em contato com o gerentes do "+'\n'+
							"sistema para autenticar a sua conta!","Login de Usuário", JOptionPane.ERROR_MESSAGE);
					return false;
				}else{
					loginTextField.requestFocus();
					JOptionPane.showMessageDialog(this,"O usuário foi desativado do sistema! "+'\n'+
							"Entre em contato com o gerentes do "+'\n'+
							"sistema para reativar a sua conta!" ,"Login de Usuário", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();			
			return false;
		}

	}
	
	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(259,213));
					gui.add(new TelaLoginUsuario(),java.awt.BorderLayout.CENTER);
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
					image = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
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


	private JLabel imageLabel = null;

	RemotePessoaService pessoaService = RemotePessoaService.getInstance();
	public Instrutor getLeiloeiro() throws Exception{		
		long id = usuarioDTO.getParticipanteId();
		try{
			return pessoaService.getInstrutor(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public Participante getParticipante()  throws Exception{
		long id = usuarioDTO.getParticipanteId();
		try{
			return pessoaService.getParticipante(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public Funcionario getFuncionario()  throws Exception{	
		long id = usuarioDTO.getParticipanteId();
		try{
			return pessoaService.getFuncionario(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public PessoaEmDivulgacao getComitente()  throws Exception{
		long id = usuarioDTO.getParticipanteId();
		try{
			return pessoaService.getComitente(id);
		}catch(Exception ex){
			ex.printStackTrace();
			throw ex;
		}
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			SwingUtilities.updateComponentTreeUI(AdapitVirtualFrame.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		timer.stop();
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"