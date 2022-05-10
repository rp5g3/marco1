package com.adapit.portal.ui.forms.usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;


import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.UserDataFilterType;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AdvancedPersonSearchDialog  extends JDialog {

	private JPanel contentPanel = null;
	private JLabel filtarPorjLabel = null;
	private JPanel filterFieldPanel = null;
	private JTextField queryStrTextField = null;
	private JRadioButton nomeUsuarioRadioButton = null;
	private JPanel porPessoaUsuarioPanel = null;
	private JRadioButton nomePessoaRadioButton = null;
	private JButton buscarButton = null;
	private UsuarioListForm usuarioListForm;
	/**
	 * @param owner
	 */
	public AdvancedPersonSearchDialog(UsuarioListForm usuarioListForm) {
		super(AdapitVirtualFrame.getInstance());
		this.usuarioListForm = usuarioListForm;
		initialize();
		this.setModal(true);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setTitle("Selecione o usuário na tabela de usuários");
		setLocation(UIUtil.getScreenCenter(this));
	}
	


	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(395, 220);
		setLayout(new BorderLayout());
		add(getContentPanel(), BorderLayout.CENTER);
		setLocation(UIUtil.getScreenCenter(this));
	}


	
	private JTabbedPane pesquisaUsuariosTabbedPane = null;
	private JPanel pesquisarPessoaFisicaPanel = null;
	private JLabel cnpjLabel = null;
	private JTextField cpfTextField = null;
	private JButton pesquisarPorCPFButton = null;
	private JLabel ouRgLabel = null;
	private JLabel rgLabel = null;
	private JTextField rgTextField = null;
	
	/**
	 * This method initializes contentPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getContentPanel() {
		if (contentPanel == null) {
			filtarPorjLabel = new JLabel();
			filtarPorjLabel.setText("Filtar os Usuários");
			filtarPorjLabel.setHorizontalAlignment(SwingConstants.CENTER);
			filtarPorjLabel.setBounds(new Rectangle(10, 5, 345, 22));
			contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.add(getPesquisaUsuariosTabbedPane(), null);
		}
		return contentPanel;
	}

	/**
	 * This method initializes filterFieldPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getFilterFieldPanel() {
		if (filterFieldPanel == null) {
			filterFieldPanel = new JPanel();
			filterFieldPanel.setLayout(null);
			filterFieldPanel.add(filtarPorjLabel, null);
			filterFieldPanel.add(getQueryStrTextField(), null);
			filterFieldPanel.add(getPorPessoaUsuarioPanel());
			filterFieldPanel.add(getBuscarButton());
		}
		return filterFieldPanel;
	}

	/**
	 * This method initializes queryStrTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getQueryStrTextField() {
		if (queryStrTextField == null) {
			queryStrTextField = new JTextField();
			queryStrTextField.setBounds(new Rectangle(10, 30, 345, 22));
			queryStrTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarPessoaUsuario();
				}
			});
		}
		return queryStrTextField;
	}

	/**
	 * This method initializes nomeUsuarioRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNomeUsuarioRadioButton() {
		if (nomeUsuarioRadioButton == null) {
			nomeUsuarioRadioButton = new JRadioButton();
			nomeUsuarioRadioButton.setText("Pelo login");
			nomeUsuarioRadioButton.setSelected(true);
		}
		return nomeUsuarioRadioButton;
	}

	/**
	 * This method initializes porPanelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPorPessoaUsuarioPanel() {
		if (porPessoaUsuarioPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			porPessoaUsuarioPanel = new JPanel();
			porPessoaUsuarioPanel.setBounds(new Rectangle(10, 54, 345, 22));
			porPessoaUsuarioPanel.setLayout(flowLayout);
			porPessoaUsuarioPanel.add(getNomeUsuarioRadioButton(), null);
			porPessoaUsuarioPanel.add(getNomePessoaRadioButton(), null);
			porPessoaUsuarioPanel.add(getEmailRadioButton(),null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getNomeUsuarioRadioButton());
			bg.add(getNomePessoaRadioButton());
			bg.add(getEmailRadioButton());
		}
		return porPessoaUsuarioPanel;
	}

	/**
	 * This method initializes nomePessoaRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNomePessoaRadioButton() {
		if (nomePessoaRadioButton == null) {
			nomePessoaRadioButton = new JRadioButton();
			nomePessoaRadioButton.setText("Pelo nome");
		}
		return nomePessoaRadioButton;
	}
	private JRadioButton emailRadioButton;
	private JPanel buscarPessoaJuridicaPanel = null;
	private JRadioButton getEmailRadioButton() {
		if (emailRadioButton == null) {
			emailRadioButton = new JRadioButton();
			emailRadioButton.setText("Pelo email");
		}
		return emailRadioButton;
	}

	/**
	 * This method initializes buscarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarButton() {
		if (buscarButton == null) {
			buscarButton = new JButton();
			buscarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			buscarButton.setBounds(new Rectangle(135, 83, 116, 24));
			buscarButton.setText("Pesquisar");
			buscarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarPessoaUsuario();
					dispose();
				}
			});
		}
		return buscarButton;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarPessoaUsuario(){		
		UserDataFilterType u = null;
		if (getNomeUsuarioRadioButton().isSelected())  u = UserDataFilterType.USUARIO;
		else if (getNomePessoaRadioButton().isSelected()) u = UserDataFilterType.PESSOA;
		else u = UserDataFilterType.EMAIL;
		try {
			List l = RemoteUserService.getInstance().listUsuariosAccordingTo(u, getQueryStrTextField().getText());
			usuarioListForm.updateTable(l);
		} catch (Exception e1) { 
			e1.printStackTrace();			
		}	
	}

	@SuppressWarnings("unchecked")
	private void buscarCpf(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByCpf(getCpfTextField().getText());
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				usuarioListForm.updateTable(l);
			}
			else{
				usuarioListForm.updateTable(null);
			}
						
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void buscarCnpj(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByCnpj(getCnpjTextField().getText());			
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				usuarioListForm.updateTable(l);
			}else{
				usuarioListForm.updateTable(null);
			}
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		
	}

	@SuppressWarnings("unchecked")
	private void buscarInscricaoEstadual(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByInscricaoEstadual(getInscricaoEstadualTextField().getText());
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				usuarioListForm.updateTable(l);
			}else{
				usuarioListForm.updateTable(null);
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();			
		}
	}

	@SuppressWarnings("unchecked")
	private void buscarRg(){		
		try {
			Usuario user = RemoteUserService.getInstance().getByRg(getRgTextField().getText());
			if (user != null){
				List l = new ArrayList();
				l.add(user);
				usuarioListForm.updateTable(l);
			}else{
				usuarioListForm.updateTable(null);
			}			
		} catch (Exception e1) { 
			e1.printStackTrace();			
		}		
	}


	/**
	 * This method initializes pesquisaUsuariosTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getPesquisaUsuariosTabbedPane() {
		if (pesquisaUsuariosTabbedPane == null) {
			pesquisaUsuariosTabbedPane = new JTabbedPane();
			pesquisaUsuariosTabbedPane.setBounds(new Rectangle(10, 10, 370, 157));
			
			pesquisaUsuariosTabbedPane.addTab("Buscar uma PF",getPesquisarPessoaFisicaPanel());
			pesquisaUsuariosTabbedPane.addTab("Buscar uma PJ", getBuscarPessoaJuridicaPanel());
			pesquisaUsuariosTabbedPane.addTab("Pesquisa por nomes", getFilterFieldPanel());
		}
		return pesquisaUsuariosTabbedPane;
	}

	/**
	 * This method initializes pesquisarPorNomePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPesquisarPessoaFisicaPanel() {
		if (pesquisarPessoaFisicaPanel == null) {
			rgLabel = new JLabel();
			rgLabel.setBounds(new Rectangle(82, 54, 32, 20));
			rgLabel.setText("RG:");
			ouRgLabel = new JLabel();
			ouRgLabel.setBounds(new Rectangle(190, 29, 30, 20));
			ouRgLabel.setText("Ou");
			cnpjLabel = new JLabel();
			cnpjLabel.setBounds(new Rectangle(82, 5, 37, 22));
			cnpjLabel.setText("CPF:");
			pesquisarPessoaFisicaPanel = new JPanel();
			pesquisarPessoaFisicaPanel.setLayout(null);
			pesquisarPessoaFisicaPanel.add(cnpjLabel);
			pesquisarPessoaFisicaPanel.add(getCpfTextField(), null);
			pesquisarPessoaFisicaPanel.add(ouRgLabel, null);
			pesquisarPessoaFisicaPanel.add(rgLabel, null);
			pesquisarPessoaFisicaPanel.add(getRgTextField(), null);
			pesquisarPessoaFisicaPanel.add(getPesquisarPorCPFButton());
		}
		return pesquisarPessoaFisicaPanel;
	}

	/**
	 * This method initializes cpfTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCpfTextField() {
		if (cpfTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("###.###.###-##");
				cpfTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cpfTextField = new JTextField();
			}
			cpfTextField.setBounds(new Rectangle(125, 5, 160, 22));
			cpfTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarCpf();
				}
			});
		}
		return cpfTextField;
	}
	
	private JTextField cnpjTextField;
	private JTextField getCnpjTextField() {
		if (cnpjTextField == null) {
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("##.###.###/####-##");
				cnpjTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cnpjTextField = new JTextField();
			}
			cnpjTextField.setBounds(new Rectangle(125, 5, 160, 22));
			cnpjTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarCnpj();
				}
			});
		}
		return cnpjTextField;
	}

	/**
	 * This method initializes pesquisarPorCPFButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPesquisarPorCPFButton() {
		if (pesquisarPorCPFButton == null) {
			pesquisarPorCPFButton = new JButton();
			pesquisarPorCPFButton.setBounds(new Rectangle(135, 83, 116, 24));
			pesquisarPorCPFButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			pesquisarPorCPFButton.setText("Buscar");
			pesquisarPorCPFButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String str = cpfTextField.getText();
					if (str != null && !str.equals("")){
						buscarCpf();
						dispose();
						return;
					}
					str = rgTextField.getText();
					if (str != null && !str.equals("")){
						buscarRg();
						dispose();
						return;
					}
				}
			});
		}
		return pesquisarPorCPFButton;
	}
	
	private JButton pesquisarPorCnpjButton;
	
	private JButton getPesquisarPorCnpjButton() {
		if (pesquisarPorCnpjButton == null) {
			pesquisarPorCnpjButton = new JButton();
			pesquisarPorCnpjButton.setBounds(new Rectangle(135, 83, 116, 24));
			pesquisarPorCnpjButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			pesquisarPorCnpjButton.setText("Buscar");
			pesquisarPorCnpjButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					String str = cnpjTextField.getText();
					if (str != null && !str.equals("")){
						buscarCnpj();
						dispose();
						return;
					}
					str = inscricaoEstadualTextField.getText();
					if (str != null && !str.equals("")){
						buscarInscricaoEstadual();
						dispose();
						return;
					}
				}
			});
		}
		return pesquisarPorCnpjButton;
	}

	/**
	 * This method initializes rgTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getRgTextField() {
		if (rgTextField == null) {
			rgTextField = new JTextField();
			rgTextField.setBounds(new Rectangle(125, 54, 160, 20));
			rgTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					buscarRg();
				}
			});
		}
		return rgTextField;
	}
	
	private JTextField inscricaoEstadualTextField; 
	private JTextField getInscricaoEstadualTextField() {
		if (inscricaoEstadualTextField== null) {
			inscricaoEstadualTextField = new JTextField();
			inscricaoEstadualTextField.setBounds(new Rectangle(125, 54, 168, 20));
			inscricaoEstadualTextField
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							buscarInscricaoEstadual();
						}
					});
		}
		return inscricaoEstadualTextField;
	}

	/**
	 * This method initializes buscarPessoaJuridicaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getBuscarPessoaJuridicaPanel() {
		if (buscarPessoaJuridicaPanel == null) {
			buscarPessoaJuridicaPanel = new JPanel();
			buscarPessoaJuridicaPanel.setLayout(null);
			rgLabel = new JLabel();
			rgLabel.setBounds(new Rectangle(22, 54, 99, 20));
			rgLabel.setText("Inscrição Estadual:");
			ouRgLabel = new JLabel();
			ouRgLabel.setBounds(new Rectangle(190, 29, 30, 20));
			ouRgLabel.setText("Ou");
			cnpjLabel = new JLabel();
			cnpjLabel.setBounds(new Rectangle(82, 5, 37, 22));
			cnpjLabel.setText("CNPJ:");
			buscarPessoaJuridicaPanel.add(cnpjLabel, null);
			buscarPessoaJuridicaPanel.add(ouRgLabel, null);
			buscarPessoaJuridicaPanel.add(rgLabel, null);
			buscarPessoaJuridicaPanel.add(getInscricaoEstadualTextField(), null);
			buscarPessoaJuridicaPanel.add(getCnpjTextField());
			buscarPessoaJuridicaPanel.add(getPesquisarPorCnpjButton());
		}
		return buscarPessoaJuridicaPanel;
	}

} 
