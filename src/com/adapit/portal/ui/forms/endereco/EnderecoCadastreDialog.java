package com.adapit.portal.ui.forms.endereco;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class EnderecoCadastreDialog extends JDialog {

	private JPanel jContentPane = null;
	private AddressType tipo;
	/**
	 * @param owner
	 */
	public EnderecoCadastreDialog(AddressType t) {
		super(AdapitVirtualFrame.getInstance());
		tipo = t;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(422, 287);
		this.setTitle("Manutenção de Endereço");
		this.setModal(true);
		this.setContentPane(getJContentPane());
		setLocation(UIUtil.getScreenCenter(this));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getOrganizerPanel(),BorderLayout.CENTER);
			jContentPane.add(getButtonsPanel(), BorderLayout.SOUTH);
		}
		return jContentPane;
	}
	
	private EnderecoCadastreForm enderecoCadastreForm;
	private JPanel buttonsPanel = null;
	private JButton cadastrarButton = null;
	private JButton removerButton = null;
	private JButton cancelarButton = null;
	private JButton novoButton = null;
	
	private JPanel organizerPanel;  //  @jve:decl-index=0:visual-constraint="441,49"
	
	private JPanel getOrganizerPanel(){
		if (organizerPanel == null){
			organizerPanel = new JPanel();
			organizerPanel.setLayout(null);
			organizerPanel.setBounds(450, 367, 402, 186);
			organizerPanel.add(getEnderecoCadastreForm());
		}
		return organizerPanel;
	}
	
	EnderecoCadastreForm getEnderecoCadastreForm(){
		if (enderecoCadastreForm == null){
			enderecoCadastreForm = new EnderecoCadastreForm();
			enderecoCadastreForm.setTipoEndereco(tipo);
			enderecoCadastreForm.setBounds(50, 0, 318, 210);
		}
		return enderecoCadastreForm;
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
			buttonsPanel.add(getCadastrarButton(), null);
			buttonsPanel.add(getNovoButton(), null);
			buttonsPanel.add(getRemoverButton(), null);
			buttonsPanel.add(getCancelarButton(), null);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes cadastrarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	JButton getCadastrarButton() {
		if (cadastrarButton == null) {
			cadastrarButton = new JButton();
			cadastrarButton.setText("Cadastrar");
			cadastrarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/house_save.png")));
			cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Endereco end = getEnderecoCadastreForm().cadastreEndereco();
						RemoteServicesUtility utility = RemoteServicesUtility.getInstance();
						try {
							if (end.getPais() == null) end.setPais(Pais.Brasil);
							if (end.getId() == 0){
								end = (Endereco) utility.save(end);
							}
							else{
								end = (Endereco) utility.update(end);
							}
							
							AdapitVirtualFrame.getInstance().showOperationSucess();
							editRegister(end);
						} catch (Exception e1) {
							e1.printStackTrace();
							AdapitVirtualFrame.getInstance().showErrorCamposInvalidos();
						}
						/*Session s = LocalServicesUtility.getInstance().openSession();
						try {
							s.beginTransaction();
							if (end.getPais() == null) end.setPais(Pais.Brasil);
							if (end.getId() == 0) s.save(end);
							else s.update(end);
							s.getTransaction().commit();
							LeilaoVirtualFrame.getInstance().showOperationSucess();
							editRegister(end);
						} catch (Exception e1) {
							e1.printStackTrace();
							s.getTransaction().rollback();
							LeilaoVirtualFrame.getInstance().showErrorCamposInvalidos();
						}finally{
							s.close();
						}*/
					} catch (Exception e1) {
						AdapitVirtualFrame.getInstance().showErrorCamposInvalidos();
					}
					
				}
			});
		}
		return cadastrarButton;
	}

	/**
	 * This method initializes removerButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoverButton() {
		if (removerButton == null) {
			removerButton = new JButton();
			removerButton.setText("Remover");
			removerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/house_delete.png")));
			removerButton.setEnabled(false);
			removerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Endereco end = getEnderecoCadastreForm().getEndereco();
					//Session s = LocalServicesUtility.getInstance().openSession();
					RemoteServicesUtility utility = RemoteServicesUtility.getInstance();
					try {
						//s.beginTransaction();
						utility.delete(end);
						//s.getTransaction().commit();
						AdapitVirtualFrame.getInstance().showOperationSucess();
						newRegister();
					} catch (Exception e1) {
						e1.printStackTrace();
						//s.getTransaction().rollback();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao remover endereço", "Não foi possível remover o endereço!");
					}/*finally{
						s.close();
					}*/
					
				}
			});
		}
		return removerButton;
	}

	public void newRegister() {
		getEnderecoCadastreForm().newRegister();
		removerButton.setEnabled(false);
		getCadastrarButton().setText("Cadastrar");
	}
	
	public void editRegister(Endereco end){
		//Session s = LocalServicesUtility.getInstance().openSession();
		RemoteServicesUtility utility = RemoteServicesUtility.getInstance();
		try {
			//s.beginTransaction();
			end = (Endereco) utility.load(Endereco.class,end.getId());
			//s.load(Endereco.class,end.getId());
			
			//s.getTransaction().commit();
			getEnderecoCadastreForm().editRegister(end);
			removerButton.setEnabled(true);
			getCadastrarButton().setText("Atualizar");
		} catch (Exception e1) {
			e1.printStackTrace();
			//s.getTransaction().rollback();
			
		}/*finally{
			s.close();
		}*/
		
		
	}

	/**
	 * This method initializes cancelarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelarButton() {
		if (cancelarButton == null) {
			cancelarButton = new JButton();
			cancelarButton.setText("Fechar");
			cancelarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/house_key.png")));
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelarButton;
	}

	/**
	 * This method initializes novoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoButton() {
		if (novoButton == null) {
			novoButton = new JButton();
			novoButton.setText("Novo");
			novoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/house.png")));
		}
		return novoButton;
	}

}  //  @jve:decl-index=0:visual-constraint="9,17"
