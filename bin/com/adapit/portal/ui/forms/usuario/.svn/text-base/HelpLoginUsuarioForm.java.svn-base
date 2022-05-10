package com.adapit.portal.ui.forms.usuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteChangePasswordMailService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings("serial")
public class HelpLoginUsuarioForm extends JPanel{

private JTextField emailTextField;
	
	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();
	
	@SuppressWarnings("unused")
	private Usuario usuario = new Usuario();  //  @jve:decl-index=0:
	
	@SuppressWarnings({ "unchecked", "unused" })
	private Map hashComps = new java.util.HashMap();
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel loginTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	@SuppressWarnings("unused")
	private String login="leilao"; 
	@SuppressWarnings("unused")
	private String pwd="5c0fnGG";

	
	public HelpLoginUsuarioForm(){		
		initialize();
	}
	
	private void initialize(){		
		//setOpaque(false);
		
		acessarTitleLabel1 = new JLabel();
		acessarTitleLabel1.setBounds(new Rectangle(0, 25, 367, 14));
		acessarTitleLabel1.setForeground(Color.black);
		acessarTitleLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		acessarTitleLabel1.setText("para acessar o sistema");
		acessarTitleLabel1.setFont(new Font("Arial", Font.BOLD, 11));
		acessarTitleLabel = new JLabel();
		acessarTitleLabel.setBounds(new Rectangle(0, 5, 367, 20));
		acessarTitleLabel.setFont(new Font("Arial", Font.BOLD, 11));
		acessarTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		acessarTitleLabel.setForeground(Color.black);
		acessarTitleLabel.setText("É necessário possuir login e senha");
		setSize(368, 316);
		setLocation(203, 109);
		setLayout(null);
		
		this.add(acessarTitleLabel, null);
		
		this.add(getFecharButton(), null);
		
		this.add(getTabbedPane(), null);
		this.add(acessarTitleLabel1, null);
	}
	
	private JPanel novaSenhaPanel;
	
	private JPanel getNovaSenhaPanel(){
		if(novaSenhaPanel == null){
			novaSenhaPanel = new JPanel();
			novaSenhaPanel.setLayout(null);
			instruçõesLabel11 = new JLabel();
			instruçõesLabel11.setBounds(new Rectangle(25, 72, 311, 18));
			instruçõesLabel11.setHorizontalAlignment(SwingConstants.CENTER);
			instruçõesLabel11.setText("'Requisitar'. Assim, uma nova senha será enviada ao seu email.");
			instruçõesLabel1 = new JLabel();
			instruçõesLabel1.setBounds(new Rectangle(25, 52, 311, 20));
			instruçõesLabel1.setHorizontalAlignment(SwingConstants.CENTER);
			instruçõesLabel1.setText("informar o seu email no campo de texto abaixo e clicar no botão");
			instruçõesLabel = new JLabel();
			instruçõesLabel.setBounds(new Rectangle(25, 32, 311, 20));
			instruçõesLabel.setHorizontalAlignment(SwingConstants.CENTER);
			instruçõesLabel.setText("Em caso de ter esquecido sua senha, requisite uma nova. Basta");
			novaSenhaPanel.add(instruçõesLabel, null);
			novaSenhaPanel.add(instruçõesLabel1, null);
			novaSenhaPanel.add(instruçõesLabel11, null);
			
			novaSenhaPanel.add(getEmailTextField());
			novaSenhaPanel.add(getLoginTextFieldLabel());

			novaSenhaPanel.add(getRequisitarButton());
		}
		return novaSenhaPanel;
	}
	
	
	protected JTextField getEmailTextField(){

		if(emailTextField == null){
			emailTextField = new JTextField();
			emailTextField.setText("");
			emailTextField.setSize(new Dimension(193, 20));
			emailTextField.setLocation(new Point(90, 128));			
			emailTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					requestNewPassword();
				}
			});
		}
		return emailTextField;
	}

	
	protected JLabel getLoginTextFieldLabel(){

		if(loginTextFieldLabel == null){
			loginTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.TelaLoginUsuario.Login"));
			loginTextFieldLabel.setSize(new Dimension(193, 27));
			loginTextFieldLabel.setLocation(new Point(90, 101));
			loginTextFieldLabel.setFont(new Font("Arial", Font.BOLD, 12));
			loginTextFieldLabel.setText("Especifique o seu email:");
			loginTextFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return loginTextFieldLabel;
	}
	
	private RemoteUserService userService = RemoteUserService.getInstance();
	
	@SuppressWarnings("unused")
	private boolean logging(){
		
		
		Usuario u = new Usuario();
		
		String login = emailTextField.getText();
		
		u.setLogin(login);
		//u.setPassword(pass);
		
		//if (login != null && pass != null && login.equals(this.login) && pass.equals(pwd)) return true;
		
		boolean b = userService.isValid(login);
		//Se não existir usuário com login informado
		/*if (!b){
			//LeilaoVirtualFrame.getInstance().showErrorDialog("Login de Usuário", "Não foi encontrado o usuário informado");
			//return false;
			JOptionPane.showMessageDialog(this,"Login de Usuário", "Não foi encontrado o usuário informado",JOptionPane.ERROR_MESSAGE);			
			return false;
		}//se não existir usuário com login e senha
		else if (!userService.isValid(login,pass)){			
			//LeilaoVirtualFrame.getInstance().showErrorDialog("Login de Usuário", "A senha não confere! Tente novamente");
			JOptionPane.showMessageDialog(this,"Login de Usuário", "A senha não confere! Tente novamente",JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		try {
			UsuarioDTO udto = userService.loggingUser(login, pass); 
			
			if (udto.isActive() && udto.isAuthenticated()){
				long id = udto.getParticipanteId();
				Session session = LocalServicesUtility.getInstance().openSession();
				participante = (Leiloeiro) session.load(Leiloeiro.class,id);
				session.close();
				return true;
			}else{
				if (udto.isActive()){
					//LeilaoVirtualFrame.getInstance().showErrorDialog("Login de Usuário", "O usuário não foi autenticado");
					JOptionPane.showMessageDialog(this,"Login de Usuário", "O usuário não foi autenticado",JOptionPane.ERROR_MESSAGE);
					return false;
				}else{
					//LeilaoVirtualFrame.getInstance().showErrorDialog("Login de Usuário", "O usuário foi desativado do sistema");
					JOptionPane.showMessageDialog(this,"Login de Usuário", "O usuário foi desativado do sistema" ,JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();			
			return false;
		}*/
		return false;
	}
	
	/**
	 * This method initializes requisitarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRequisitarButton() {
		if (requisitarButton == null) {
			requisitarButton = new JButton();
			requisitarButton.setBounds(new Rectangle(130, 154, 113, 26));
			requisitarButton.setFont(new Font("Arial", Font.BOLD, 12));
			requisitarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_go.png")));
			requisitarButton.setText("Requisitar");
			requisitarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					requestNewPassword();
				}
			});
		}
		return requisitarButton;
	}
	
	private void requestNewPassword(){
		try {
			boolean b = RemoteChangePasswordMailService.getInstance().getMailService()
			.changePasswordByEmail(getEmailTextField().getText());
			if (b) JOptionPane.showMessageDialog(this, "Senha trocada com sucesso! Verifique seu email","Troca de senha",JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(this, "A senha não foi substituída! Verifique seu o seu email foi especificado corretamente","Troca de senha",JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes outrasInformacoesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	
	  private JScrollPane sobreCadastroScrollPane;
	  private JScrollPane getSobreCadastroScrollPane() {
		if (sobreCadastroScrollPane == null) {
			sobreCadastroScrollPane = new JScrollPane();
			sobreCadastroScrollPane.setBounds(new Rectangle(10, 10, 330, 170));
			sobreCadastroScrollPane.setViewportView(getSobreCadastroTextPane());
		}
		return sobreCadastroScrollPane;
	}
	
	private JScrollPane getSistemaInformacoesScrollPane() {
		if (sistemaInformacoesScrollPane == null) {
			sistemaInformacoesScrollPane = new JScrollPane();
			sistemaInformacoesScrollPane.setBounds(new Rectangle(10, 10, 330, 170));
			sistemaInformacoesScrollPane.setViewportView(getSistemaInformacaoTextPane());
		}
		return sistemaInformacoesScrollPane;
	}

	/**
	 * This method initializes outrasInfTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	
	private JTextPane sobreCadastroTextPane;
	private JTextPane getSobreCadastroTextPane() {
		if (sobreCadastroTextPane == null) {
			sobreCadastroTextPane = new JTextPane();
			sobreCadastroTextPane.setText("1) No caso de você" +
					" ser um funcionário da agência e" +
					" não estar cadastrado no sistema," +
					" entre em contato com a Agência" +
					" de Leilões Cargnelutti e" +
					" requisite um cadastro."+'\n'+
					"2) Caso você é um usuário interessado" +
					" em participar dos lances do leilões" +
					" da agência, faça o seu cadastro no" +
					" sistema web em www.cargneluttileiloes.com.br." +
					"" + '\n' + ""+'\n'+
					"Para mais informações entre em contato pelo fone:");
			sobreCadastroTextPane.setEditable(false);
		}
		return sobreCadastroTextPane;
	}
	
	private JScrollPane outrasInformacoesScrollPane;
	  private JScrollPane getOutrasInformacoesScrollPane() {
		if (outrasInformacoesScrollPane == null) {
			outrasInformacoesScrollPane = new JScrollPane();
			outrasInformacoesScrollPane.setBounds(new Rectangle(10, 10, 330, 170));
			outrasInformacoesScrollPane.setViewportView(getOutrasInfTextPane());
		}
		return outrasInformacoesScrollPane;
	}
	
	private JTextPane outrasInfTextPane;
	private JTextPane getOutrasInfTextPane() {
		if (outrasInfTextPane == null) {
			outrasInfTextPane = new JTextPane();
			outrasInfTextPane.setText("1) No caso de você" +
					" ser um funcionário da agência e" +
					" não estar cadastrado no sistema," +
					" entre em contato com a Agência" +
					" de Leilões Cargnelutti e" +
					" requisite um cadastro."+'\n'+
					"2) Caso você é um usuário interessado" +
					" em participar dos lances do leilões" +
					" da agência, faça o seu cadastro no" +
					" sistema web em www.cargneluttileiloes.com.br." +
					"" + '\n' + ""+'\n'+
					"Para mais informações entre em contato pelo fone:");
			outrasInfTextPane.setEditable(false);
		}
		return outrasInfTextPane;
	}
	
	private JTextPane getSistemaInformacaoTextPane() {
		if (sistemaInformacaoTextPane == null) {
			sistemaInformacaoTextPane = new JTextPane();
			sistemaInformacaoTextPane.setText("1) No caso de você" +
					" ser um funcionário da agência e" +
					" não estar cadastrado no sistema," +
					" entre em contato com a Agência" +
					" de Leilões Cargnelutti e" +
					" requisite um cadastro."+'\n'+
					"2) Caso você é um usuário interessado" +
					" em participar dos lances do leilões" +
					" da agência, faça o seu cadastro no" +
					" sistema web em www.cargneluttileiloes.com.br." +
					"" + '\n' + ""+'\n'+
					"Para mais informações entre em contato pelo fone:");
			sistemaInformacaoTextPane.setEditable(false);
		}
		return sistemaInformacaoTextPane;
	}

	/**
	 * This method initializes fecharButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getFecharButton() {
		if (fecharButton == null) {
			fecharButton = new JButton();
			fecharButton.setBounds(new Rectangle(145, 283, 96, 26));
			fecharButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cancel.png")));
			fecharButton.setText("Fechar");
		}
		return fecharButton;
	}

	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setBounds(new Rectangle(4, 42, 360, 228));
			tabbedPane.addTab("Esqueci Minha Senha",getNovaSenhaPanel());
			tabbedPane.addTab("Cadastro",getSobreCadastroPanel());
			tabbedPane.addTab("Termos & Regras",getSobreTermosRegrasPanel());
			tabbedPane.addTab("Licença de Uso",getSobreSistemaPanel());
		}
		return tabbedPane;
	}
	
	private JPanel sobreCadastroPanel;
	
	private JPanel getSobreCadastroPanel(){
		if (sobreCadastroPanel == null){
			sobreCadastroPanel = new JPanel();
			sobreCadastroPanel.setLayout(null);
			sobreCadastroPanel.add(getSobreCadastroScrollPane());
		}
		return sobreCadastroPanel;
	}
	
	private JPanel sobreTermosRegrasPanel;
	
	private JPanel getSobreTermosRegrasPanel(){
		if (sobreTermosRegrasPanel == null){
			sobreTermosRegrasPanel = new JPanel();
			sobreTermosRegrasPanel.setLayout(null);
			sobreTermosRegrasPanel.add(getOutrasInformacoesScrollPane());
		}
		return sobreTermosRegrasPanel;
	}
	
	private JPanel sobreSistemaPanel;
	
	private JPanel getSobreSistemaPanel(){
		if (sobreSistemaPanel == null){
			sobreSistemaPanel = new JPanel();
			sobreSistemaPanel.setLayout(null);
			sobreSistemaPanel.add(getSistemaInformacoesScrollPane());
		}
		return sobreSistemaPanel;
	}

	
	private Instrutor participante;

	//private JLabel imageLabel = null;

	private JLabel acessarTitleLabel = null;

	private JLabel instruçõesLabel = null;

	private JLabel instruçõesLabel1 = null;

	private JLabel instruçõesLabel11 = null;

	private JButton requisitarButton = null;

	private JScrollPane sistemaInformacoesScrollPane = null;

	private JTextPane sistemaInformacaoTextPane = null;

	private JButton fecharButton = null;

	private JTabbedPane tabbedPane = null;

	private JLabel acessarTitleLabel1 = null;
	
	public Instrutor getLeiloeiro() {
		return participante;
	}
}  //  @jve:decl-index=0:visual-constraint="15,8"
