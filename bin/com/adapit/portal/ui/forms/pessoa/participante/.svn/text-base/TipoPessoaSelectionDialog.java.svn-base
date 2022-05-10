package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class TipoPessoaSelectionDialog extends JDialog {

	private JPanel contentPanel = null;
	private JComboBox tipoPessoaComboBox = null;
	private JLabel tipoPessoaLabel = null;
	private JButton confirmarButton = null;
	private JButton cancelarButton = null;
	private JRadioButton comoUsuarioRadioButton = null;
	private JRadioButton simplesRadioButton = null;
	private JLabel criarComoLabel = null;

	/**
	 * This method initializes 
	 * 
	 */
	public TipoPessoaSelectionDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize() {
        this.setSize(new Dimension(261, 194));
        this.setContentPane(getContentPanel());
        this.setTitle("Tipo de PessoaEmDivulgacao");
		this.setModal(true);
		setLocation(UIUtil.getScreenCenter(this));
	}

	/**
	 * This method initializes contentPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getContentPanel() {
		if (contentPanel == null) {
			criarComoLabel = new JLabel();
			criarComoLabel.setBounds(new Rectangle(2, 1, 250, 22));
			criarComoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			criarComoLabel.setText("Cadastrar PessoaEmDivulgacao Como:");
			tipoPessoaLabel = new JLabel();
			tipoPessoaLabel.setBounds(new Rectangle(2, 77, 248, 22));
			tipoPessoaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tipoPessoaLabel.setText("Selecione o tipo de pessoa a ser cadastrada");
			contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.add(getTipoPessoaComboBox(), null);
			contentPanel.add(tipoPessoaLabel, null);
			contentPanel.add(getConfirmarButton(), null);
			contentPanel.add(getCancelarButton(), null);
			contentPanel.add(getComoUsuarioRadioButton(), null);
			contentPanel.add(getSimplesRadioButton(), null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getComoUsuarioRadioButton());
			bg.add(getSimplesRadioButton());
			contentPanel.add(criarComoLabel, null);
		}
		return contentPanel;
	}

	/**
	 * This method initializes tipoPessoaComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getTipoPessoaComboBox() {
		if (tipoPessoaComboBox == null) {
			tipoPessoaComboBox = new JComboBox();
			tipoPessoaComboBox.setBounds(new Rectangle(26, 102, 194, 22));
			tipoPessoaComboBox.addItem("Física");
			tipoPessoaComboBox.addItem("Jurídica");
			tipoPessoaComboBox.setSelectedItem("Física");
			tipoPessoaComboBox.setEnabled(false);
		}
		return tipoPessoaComboBox;
	}

	/**
	 * This method initializes confirmarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getConfirmarButton() {
		if (confirmarButton == null) {
			confirmarButton = new JButton();
			confirmarButton.setBounds(new Rectangle(26, 128, 90, 26));
			confirmarButton.setText("Confirmar");
			confirmarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PersonType pt = null;
					String str = (String)getTipoPessoaComboBox().getSelectedItem();
					if (str.equals("Física")) pt = PersonType.Fisica;
					else pt = PersonType.Juridica;
					if (getComoUsuarioRadioButton().isSelected()){
						AdapitVirtualFrame.getInstance().cadastrarComitenteParticipante(pt);
					}
					else{
						AdapitVirtualFrame.getInstance().cadastrarComitente(PersonType.Fisica);
					}
					dispose();
				}
			});
		}
		return confirmarButton;
	}

	/**
	 * This method initializes cancelarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getCancelarButton() {
		if (cancelarButton == null) {
			cancelarButton = new JButton();
			cancelarButton.setBounds(new Rectangle(130, 128, 90, 26));
			cancelarButton.setText("Cancelar");
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelarButton;
	}

	/**
	 * This method initializes comoUsuarioRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getComoUsuarioRadioButton() {
		if (comoUsuarioRadioButton == null) {
			comoUsuarioRadioButton = new JRadioButton();
			comoUsuarioRadioButton.setBounds(new Rectangle(0, 50, 258, 21));
			comoUsuarioRadioButton.setText("Criar como um participante que pode dar lances");
			comoUsuarioRadioButton.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						tipoPessoaComboBox.setEnabled(true);
				}				
			});
		}
		return comoUsuarioRadioButton;
	}

	/**
	 * This method initializes simplesRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getSimplesRadioButton() {
		if (simplesRadioButton == null) {
			simplesRadioButton = new JRadioButton();
			simplesRadioButton.setBounds(new Rectangle(0, 28, 253, 21));
			simplesRadioButton.setText("Criar como um pessoaEmDivulgacao simples");
			simplesRadioButton.setSelected(true);
			simplesRadioButton.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED)
						tipoPessoaComboBox.setEnabled(false);
				}				
			});
		}
		return simplesRadioButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
