package com.adapit.portal.ui.forms.usuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class WelcomeDesktopPane extends JDesktopPane implements ActionListener{

	private UsuarioDTO usuarioDTO=null;	
	

	
	@SuppressWarnings("unused")
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	public WelcomeDesktopPane(UsuarioDTO user){
		usuarioDTO = user;
		initialize();
	}
	private Timer timer;
	
	@SuppressWarnings("deprecation")
	private void initialize(){		
		ultimoAcessoLabel1 = new JLabel();
		ultimoAcessoLabel1.setBounds(new Rectangle(232, 147, 202, 25));
		ultimoAcessoLabel1.setForeground(Color.white);
		ultimoAcessoLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		ultimoAcessoLabel1.setText("");
		
		if (usuarioDTO != null && usuarioDTO.getLastAcess() != null)
			ultimoAcessoLabel1.setText(AdapitVirtualFrame.formatDateTime(usuarioDTO.getLastAcess())
					+" as " + usuarioDTO.getLastAcess().getHours() + ":"+usuarioDTO.getLastAcess().getMinutes());
		ultimoAcessoLabel1.setFont(new Font("Arial", Font.BOLD, 12));
		ultimoAcessoLabel = new JLabel();
		ultimoAcessoLabel.setBounds(new Rectangle(231, 115, 203, 27));
		ultimoAcessoLabel.setFont(new Font("Arial", Font.BOLD, 12));
		ultimoAcessoLabel.setForeground(Color.white);
		ultimoAcessoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ultimoAcessoLabel.setText("Seu último acesso ocorreu em: ");
		imageLabel = new JLabel();
		imageLabel.setBounds(new Rectangle(102, 187, 468, 315));
		imageLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/Tela inicial software.jpg")));
		imageLabel.setText("");
		setOpaque(true);
		setSize(new Dimension(657, 546));
		setLocation(UIUtil.getScreenCenter(this));
		setLayout(null);		
		//this.add(ultimoAcessoLabel, null);
		//this.add(ultimoAcessoLabel1, null);
		imageLabel.setOpaque(false);
		add(imageLabel,1);
		add(getAcessarTitleLabel(),0);
		add(ultimoAcessoLabel, 0);
		add(ultimoAcessoLabel1,0);
		timer = new Timer((5)*1000, this);
		timer.start();
	}

	private JLabel acessarTitleLabel = null;
	private JLabel getAcessarTitleLabel(){
		if (acessarTitleLabel == null){
			acessarTitleLabel = new JLabel();
			acessarTitleLabel.setBounds(new Rectangle(169, 81, 312, 26));
			acessarTitleLabel.setFont(new Font("Arial", Font.BOLD, 13));
			acessarTitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			acessarTitleLabel.setForeground(Color.WHITE);
			if (usuarioDTO != null) acessarTitleLabel.setText("Bem Vindo(a) " + usuarioDTO.getWelcomeName());
			else  acessarTitleLabel.setText("Você acessou o sistema com um usuário temporário! Faça o cadastro do seu usuário");
		}
		return acessarTitleLabel;
	}
	
	
	@SuppressWarnings("unused")
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



	private JLabel ultimoAcessoLabel = null;



	private JLabel ultimoAcessoLabel1 = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		remove(imageLabel);
		remove(getAcessarTitleLabel());
		remove(ultimoAcessoLabel);
		remove(ultimoAcessoLabel1);
		timer.stop();
		updateUI();
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"