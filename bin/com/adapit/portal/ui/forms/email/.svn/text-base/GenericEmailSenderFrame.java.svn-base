package com.adapit.portal.ui.forms.email;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.html.HTMLEditorKit;

import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemoteGenericMailService;
import com.adapit.portal.ui.forms.HtmlEditorFactory;
import com.adapit.portal.ui.forms.usuario.TimerMessageFrame;
import com.workcase.gui.utils.SwingWorker;
import com.workcase.gui.utils.UIUtil;

public class GenericEmailSenderFrame extends JFrame{

	private static final long serialVersionUID = 34623346434646L;
	private JLabel emailMsgLabel = null;
	private JPanel buttonsPanel = null;
	private JButton sendButton = null;
	private JButton htmlButton = null;
	private JButton cancelButton = null;
	private JScrollPane htmlContentScrollPane = null;
	private JTextPane htmlContentTextPane = null;
	private String to;  //  @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public GenericEmailSenderFrame() {
		super();		
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		emailMsgLabel = new JLabel();
		emailMsgLabel.setText("Texto html que será enviado no email:");
		emailMsgLabel.setBounds(new Rectangle(10, 83, 650, 16));
		this.setSize(682, 516);
		this.setLayout(new BorderLayout());
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
		this.add(getContentPanel(), BorderLayout.CENTER);
		setTitle("Enviar email para usuários");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private JPanel contentPanel;
	private JPanel getContentPanel(){
		if (contentPanel == null){
			contentPanel = new JPanel();
			contentPanel.setLayout(null);//new BorderLayout());
			contentPanel.add(getTopContent());//,BorderLayout.NORTH);
			contentPanel.add(getJtb());//,BorderLayout.CENTER);
			contentPanel.add(emailMsgLabel, null);
		}
		return contentPanel;
	}
	
	private JPanel topContent;
	private JPanel getTopContent(){
		if (topContent == null){
			topContent = new JPanel();
			topContent.setLayout(null);//new GridLayout(2,1));
			topContent.setBounds(10, 0, 650, 80);
			topContent.setMinimumSize(new Dimension(400,70));
			topContent.add(getSendToPanel());
			topContent.add(getAssuntoPanel());
			JLabel jlAssunto = new JLabel("Assunto:");
			jlAssunto.setBounds(new Rectangle(0, 50, 80, 20));
			topContent.add(jlAssunto, null);
			JLabel jl = new JLabel("Para:");
			jl.setBounds(new Rectangle(0, 0, 80, 20));
			topContent.add(jl, null);
		}		
		return topContent;
	}
	
	private JScrollPane sendToPanel;
	private JScrollPane getSendToPanel(){
		if(sendToPanel == null){
			sendToPanel = new JScrollPane();
			//sendToPanel.setLayout(null);
			sendToPanel.setBounds(new Rectangle(85, 0, 565, 48));
			
			sendToPanel.add(getSendToTextPane());
			sendToPanel.setViewportView(getSendToTextPane());
		}
		return sendToPanel;
	}
	
	private JTextPane sendToTextPane;
	private JTextPane getSendToTextPane(){
		if(sendToTextPane == null){
			sendToTextPane = new JTextPane();
			sendToTextPane.setBounds(120, 0, 528, 38);
		}
		return sendToTextPane;
	}
	
	private JScrollPane assuntoPanel;
	private JScrollPane getAssuntoPanel(){
		if(assuntoPanel == null){
			assuntoPanel = new JScrollPane();
			//assuntoPanel.setLayout(null);
			assuntoPanel.setBounds(new Rectangle(85, 50, 565, 30));			
			assuntoPanel.add(getAssuntoTextPane());
			assuntoPanel.setViewportView(getAssuntoTextPane());
		}
		return assuntoPanel;
	}
	
	/*private JPanel toPanel;
	private JPanel getToPanel(){
		if(toPanel == null){
			toPanel = new JPanel();
			toPanel.setLayout(null);
			JLabel jl = new JLabel("Para:");
			jl.setBounds(10,10,100,26);
			toPanel.add(jl);
			toPanel.add(getToTextPane());
		}
		return toPanel;
	}
	
	private JTextPane toTextPane;
	private JTextPane getToTextPane(){
		if(toTextPane == null){
			toTextPane = new JTextPane();
			toTextPane.setBounds(120,0,400,26);
		}
		return toTextPane;
	}*/
	
	private JTextPane assuntoTextPane;
	private JTextPane getAssuntoTextPane(){
		if(assuntoTextPane == null){
			assuntoTextPane = new JTextPane();
			assuntoTextPane.setBounds(120, 0, 529, 38);
		}
		return assuntoTextPane;
	}
	
	private JTabbedPane jtb;
	
	private JTabbedPane getJtb(){
		if (jtb == null){
			jtb = new JTabbedPane();
			jtb.setBounds(10, 100, 650, 348);
			jtb.setTabPlacement(JTabbedPane.BOTTOM);
			jtb.add("View",getViewContentScrollPane());
			jtb.add("Fonte",getHtmlContentScrollPane());
		}
		return jtb;
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			buttonsPanel.add(getHtmlButton(), null);
			buttonsPanel.add(getSendButton(), null);
			buttonsPanel.add(getCancelButton(), null);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes sendButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSendButton() {
		if (sendButton == null) {
			sendButton = new JButton();
			sendButton.setText("Enviar");
			sendButton.setIcon(new ImageIcon(getClass().getResource("/imgs/email_go.png")));
			sendButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SwingWorker sw = new SwingWorker(){
						@Override
						public Object construct() {
							try {
								TimerMessageFrame jd = new TimerMessageFrame("Enviando email ... por favor aguarde");
								jd.setSize(310,80);					
								jd.setVisible(true);
								try {
									String nome="Adapit Soluções em TI";
									String email="fabiopbasso@gmail.com";
									String assunto=getAssuntoTextPane().getText();
									RemoteGenericMailService.getInstance().sendEmail(nome,email,assunto,getHtmlContentTextPane().getText(),sendToTextPane.getText());
									JOptionPane.showMessageDialog(GenericEmailSenderFrame.this, "O email foi enviado com sucesso!","Enviar email para usuário",JOptionPane.INFORMATION_MESSAGE);
								} catch (Exception e1) {						
									e1.printStackTrace();
									JOptionPane.showMessageDialog(GenericEmailSenderFrame.this, "O email não foi enviado!","Enviar email para usuário",JOptionPane.INFORMATION_MESSAGE);
								}
								jd.stop();
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						}
					
					};
					
					sw.start();
				}
			});
		}
		return sendButton;
	}

	/**
	 * This method initializes htmlButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHtmlButton() {
		if (htmlButton == null) {
			htmlButton = new JButton();
			htmlButton.setText("Formatar/Editar");
			htmlButton.setIcon(new ImageIcon(getClass().getResource("/imgs/xhtml.png")));
			htmlButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					HtmlEditorFactory.formatar(getHtmlContentTextPane(),getViewContentTextPane(),GenericEmailSenderFrame.this,"Editor HTML");
				}
			});
		}
		return htmlButton;
	}
	
	
	
	

	/**
	 * This method initializes cancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton();
			cancelButton.setText("Cancelar");
			cancelButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cancel.png")));
			cancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelButton;
	}
	
	private JScrollPane viewContentScrollPane;
	private JScrollPane getViewContentScrollPane() {
		if (viewContentScrollPane == null) {
			viewContentScrollPane = new JScrollPane();
			viewContentScrollPane.setViewportView(getViewContentTextPane());
		}
		return viewContentScrollPane;
	}

	private JTextPane viewContentTextPane;
	private JTextPane getViewContentTextPane() {
		if (viewContentTextPane == null) {
			viewContentTextPane = new JTextPane();
			final HTMLEditorKit kit = new HTMLEditorKit();
			viewContentTextPane.setEditorKit(kit);
			viewContentTextPane.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent evt) {
					htmlContentTextPane.setText(viewContentTextPane.getText());
				}				
			});
		}
		return viewContentTextPane;
	}

	/**
	 * This method initializes contentScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getHtmlContentScrollPane() {
		if (htmlContentScrollPane == null) {
			htmlContentScrollPane = new JScrollPane();
			htmlContentScrollPane.setViewportView(getHtmlContentTextPane());
			
		}
		return htmlContentScrollPane;
	}

	/**
	 * This method initializes contentTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getHtmlContentTextPane() {
		if (htmlContentTextPane == null) {
			htmlContentTextPane = new JTextPane();
			htmlContentTextPane.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent evt) {
					viewContentTextPane.setText(htmlContentTextPane.getText());
				}				
			});
		}
		return htmlContentTextPane;
	}

	
	public void setVisible(List<Usuario> users) {
		if (users != null && users.size()>0){
			to="";
			Iterator<Usuario> it = users.iterator();
			while(it.hasNext()){
				Usuario user = it.next();
				to+=user.getDadosPessoais().getEmail();
				if (it.hasNext())
					to+=";";
			}
			getSendToTextPane().setText(to);
		}
		setTitle("Enviar email para usuários");
		String text="";
		
		text+='<'+"html"+'>'+""+'<'+"body"+'>'+"";		
		text+='<'+"/body"+'>'+""+'<'+"/html"+'>'+"";
		htmlContentTextPane.setText(text);
		viewContentTextPane.setText(text);
		super.setVisible(true);
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"