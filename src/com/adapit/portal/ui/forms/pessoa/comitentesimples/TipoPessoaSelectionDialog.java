package com.adapit.portal.ui.forms.pessoa.comitentesimples;

import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
        this.setSize(new Dimension(268, 120));
        this.setContentPane(getContentPanel());
        this.setTitle("Selecione o tipo de pessoa");
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
			tipoPessoaLabel = new JLabel();
			tipoPessoaLabel.setBounds(new Rectangle(4, 3, 252, 22));
			tipoPessoaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tipoPessoaLabel.setText("Selecione o tipo de pessoa a ser cadastrada");
			contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.add(getTipoPessoaComboBox(), null);
			contentPanel.add(tipoPessoaLabel, null);
			contentPanel.add(getConfirmarButton(), null);
			contentPanel.add(getCancelarButton(), null);
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
			tipoPessoaComboBox.setBounds(new Rectangle(28, 28, 194, 22));
			tipoPessoaComboBox.addItem("Física");
			tipoPessoaComboBox.addItem("Jurídica");
			tipoPessoaComboBox.setSelectedItem("Física");
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
			confirmarButton.setBounds(new Rectangle(28, 54, 90, 26));
			confirmarButton.setText("Confirmar");
			confirmarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PersonType pt = null;
					String str = (String)getTipoPessoaComboBox().getSelectedItem();
					if (str.equals("Física")) pt = PersonType.Fisica;
					else pt = PersonType.Juridica;
					AdapitVirtualFrame.getInstance().cadastrarComitente(pt);
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
			cancelarButton.setBounds(new Rectangle(132, 54, 90, 26));
			cancelarButton.setText("Cancelar");
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelarButton;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
