package com.adapit.portal.ui.forms.endereco;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.services.remote.RemoteEnderecoService;

public class EnderecoPresencialFilter extends JPanel {

	private static final long serialVersionUID = 35462346234L;
	private JLabel titleLabel = null;
	private JComboBox enderPresenciaisComboBox = null;
	private JButton editButton = null;
	private EnderecoCadastreDialog observer;

	/**
	 * This is the default constructor
	 */
	public EnderecoPresencialFilter(EnderecoCadastreDialog observer) {
		super();
		this.observer = observer;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		titleLabel = new JLabel();
		titleLabel.setBounds(new Rectangle(10, 5, 399, 20));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
		titleLabel.setText("Lista dos endereços presenciais cadastrados no sistema:");
		this.setSize(417, 57);
		this.setLayout(null);
		this.add(titleLabel, null);
		this.add(getEnderPresenciaisComboBox(), null);
		//this.add(getEditButton(), null);
		this.add(getRefreshButton(), null);
	}

	private List<Endereco> enderecos;
	private JButton refreshButton = null;
	/**
	 * This method initializes enderPresenciaisComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getEnderPresenciaisComboBox() {
		if (enderPresenciaisComboBox == null) {
			enderPresenciaisComboBox = new JComboBox();
			enderPresenciaisComboBox.setBounds(new Rectangle(39, 28, 366, 26));
			enderPresenciaisComboBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED){
						try {
							observer.getEnderecoCadastreForm().editRegister(enderecos.get(enderPresenciaisComboBox.getSelectedIndex()));
							observer.getCadastrarButton().setText("Atualizar");
						} catch (RuntimeException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			refresh();
			
		}
		return enderPresenciaisComboBox;
	}

	/**
	 * This method initializes editButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	@SuppressWarnings("unused")
	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setBounds(new Rectangle(324, 28, 85, 26));
			editButton.setIcon(new ImageIcon(getClass().getResource("/imgs/edit.jpg")));
			editButton.setText("Editar");
			editButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						observer.getEnderecoCadastreForm().editRegister(enderecos.get(enderPresenciaisComboBox.getSelectedIndex()));
					} catch (RuntimeException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return editButton;
	}

	/**
	 * This method initializes refreshButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshButton() {
		if (refreshButton == null) {
			refreshButton = new JButton();
			refreshButton.setBounds(new Rectangle(10, 28, 26, 26));
			refreshButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					refresh();
				}
			});
		}
		return refreshButton;
	}
	
	public void refresh(){
		enderPresenciaisComboBox.removeAllItems();
		try {
			enderecos = RemoteEnderecoService.getInstance().listEnderecoEagerByTipo(AddressType.Presencial);
			if (enderecos != null){
				for(Endereco ender: enderecos){
					String str = ender.getEstado()+"-"+ender.getCidade();
					if(ender.getBairro() != null && !ender.getBairro().trim().equals("")) str+=" B:"+ender.getBairro();
					if (ender.getRua() != null && !ender.getRua().trim().equals("")) str+=" R:" + ender.getRua();					
					if(ender.getNumero() >0) str += " N:" +ender.getNumero();
					if (ender.getLocal() != null && !ender.getLocal().trim().equals(""))
						str += "-"+ender.getLocal();
					enderPresenciaisComboBox.addItem(str /*+ " C:"+ender.getCidade() + " E:" + ender.getEstado()*/);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		/*try {
			enderecos = RemoteEnderecoService.getInstance().listEnderecoEagerByTipo(TipoEndereco.Presencial);
			if (enderecos != null){
				for(Endereco ender: enderecos){
					enderPresenciaisComboBox.addItem(ender.getRua()+" N:" +ender.getNumero()+" - "+ender.getBairro() + " - "+ender.getCidade() + " - " + ender.getEstado());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
