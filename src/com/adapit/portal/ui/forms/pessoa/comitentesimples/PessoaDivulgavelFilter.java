package com.adapit.portal.ui.forms.pessoa.comitentesimples;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.ComitenteSimples;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.frames.pessoa.rh.ComitenteCadastreDialog;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class PessoaDivulgavelFilter extends JPanel {

	private static final long serialVersionUID = 3544563474375L;
	private JLabel titleLabel = null;
	private JComboBox enderPresenciaisComboBox = null;
	private JButton editButton = null;
	private ComitenteCadastreDialog observer;

	/**
	 * This is the default constructor
	 */
	public PessoaDivulgavelFilter(ComitenteCadastreDialog observer) {
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
		titleLabel.setBounds(new Rectangle(10, 5, 500, 20));
		titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
		titleLabel.setText("Lista dos comitentes cadastrados no sistema:");
		this.setSize(550, 57);
		this.setLayout(null);
		this.add(titleLabel, null);
		this.add(getEnderPresenciaisComboBox(), null);
		this.add(getEditButton(), null);
		this.add(getRefreshButton(), null);
	}

	private List<ComitenteSimples> comitentes;
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
							observer.getCadastrarComitenteForm().editRegister(comitentes.get(enderPresenciaisComboBox.getSelectedIndex()));
							observer.getCadastrarComitenteForm().getInsertButton().setText("Atualizar");
						} catch (RuntimeException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			try {
				comitentes = RemoteServicesUtility.getInstance().retrieveAll(ComitenteSimples.class);
				if (comitentes != null){
					for(PessoaEmDivulgacao ender: comitentes){
						enderPresenciaisComboBox.addItem(ender.getNome()+" Email:" +ender.getEmail()+" - "+ender.getTelefone());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return enderPresenciaisComboBox;
	}

	/**
	 * This method initializes editButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditButton() {
		if (editButton == null) {
			editButton = new JButton();
			editButton.setBounds(new Rectangle(410, 28, 85, 26));
			editButton.setIcon(new ImageIcon(getClass().getResource("/imgs/edit.jpg")));
			editButton.setText("Editar");
			editButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						observer.getCadastrarComitenteForm().editRegister(comitentes.get(enderPresenciaisComboBox.getSelectedIndex()));
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
			comitentes = RemoteServicesUtility.getInstance().retrieveAll(ComitenteSimples.class);
			if (comitentes != null){
				for(PessoaEmDivulgacao ender: comitentes){
					enderPresenciaisComboBox.addItem(ender.getNome()+" Email:" +ender.getEmail()+" - "+ender.getTelefone());
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void editFirst(){
		try {
			ComitenteSimples com = comitentes.get(0);
			observer.getCadastrarComitenteForm().editRegister(com);
			select(com);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
		}
	}
	
	private void select(ComitenteSimples ender){
		try {
			if (comitentes != null && enderPresenciaisComboBox.getItemCount()>0){				
				enderPresenciaisComboBox.setSelectedItem(ender.getNome()+" Email:" +ender.getEmail()+" - "+ender.getTelefone());				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void edit(ComitenteSimples com){
		try {			
			observer.getCadastrarComitenteForm().editRegister(com);
			select(com);
		} catch (RuntimeException e1) {
			e1.printStackTrace();
		}
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
