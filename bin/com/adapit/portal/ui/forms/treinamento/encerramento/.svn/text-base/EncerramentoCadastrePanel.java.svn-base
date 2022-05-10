package com.adapit.portal.ui.forms.treinamento.encerramento;

import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.adapit.portal.entidades.TipoPagamento;

public class EncerramentoCadastrePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel formaPagamentoLabel = null;
	private JComboBox formaPagtoComboBox = null;
	private JLabel numeroPrestacoesLabel = null;
	private JComboBox numeroPrestacoesComboBox = null;
	private JLabel entradasLabel = null;
	private JComboBox entradasComboBox = null;
	private JLabel mesesCarenciasLabel = null;
	private JComboBox mesesCarenciaComboBox = null;

	/**
	 * This is the default constructor
	 */
	public EncerramentoCadastrePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		mesesCarenciasLabel = new JLabel();
		mesesCarenciasLabel.setBounds(new Rectangle(15, 103, 136, 22));
		mesesCarenciasLabel.setText("Meses de Carência:");
		entradasLabel = new JLabel();
		entradasLabel.setBounds(new Rectangle(15, 75, 136, 22));
		entradasLabel.setText("Número de Entradas:");
		numeroPrestacoesLabel = new JLabel();
		numeroPrestacoesLabel.setBounds(new Rectangle(15, 47, 136, 22));
		numeroPrestacoesLabel.setText("Número de Prestações:");
		formaPagamentoLabel = new JLabel();
		formaPagamentoLabel.setBounds(new Rectangle(15, 20, 136, 22));
		formaPagamentoLabel.setText("Forma de Pagamento:");
		this.setSize(435, 256);
		this.setLayout(null);
		this.add(formaPagamentoLabel, null);
		this.add(getFormaPagtoComboBox(), null);
		this.add(numeroPrestacoesLabel, null);
		this.add(getNumeroPrestacoesComboBox(), null);
		this.add(entradasLabel, null);
		this.add(getEntradasComboBox(), null);
		this.add(mesesCarenciasLabel, null);
		this.add(getMesesCarenciaComboBox(), null);
	}

	/**
	 * This method initializes formaPagtoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFormaPagtoComboBox() {
		if (formaPagtoComboBox == null) {
			formaPagtoComboBox = new JComboBox();
			formaPagtoComboBox.setBounds(new Rectangle(164, 20, 176, 22));
			for (int i=0; i < TipoPagamento.values().length; i++){
				formaPagtoComboBox.addItem(TipoPagamento.values()[i].name().replace("_", " "));
			}
			
		}
		return formaPagtoComboBox;
	}

	/**
	 * This method initializes numeroPrestacoesComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getNumeroPrestacoesComboBox() {
		if (numeroPrestacoesComboBox == null) {
			numeroPrestacoesComboBox = new JComboBox();
			numeroPrestacoesComboBox.setEditable(true);
			numeroPrestacoesComboBox.setBounds(new Rectangle(164, 48, 80, 22));
			for (int i=0; i < 120; i++){
				numeroPrestacoesComboBox.addItem(i);
			}
		}
		return numeroPrestacoesComboBox;
	}

	/**
	 * This method initializes entradasComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getEntradasComboBox() {
		if (entradasComboBox == null) {
			entradasComboBox = new JComboBox();
			entradasComboBox.setEditable(true);
			entradasComboBox.setBounds(new Rectangle(164, 75, 80, 22));
			for (int i=0; i < 36; i++){
				entradasComboBox.addItem(i);
			}
		}
		return entradasComboBox;
	}

	/**
	 * This method initializes mesesCarenciaComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getMesesCarenciaComboBox() {
		if (mesesCarenciaComboBox == null) {
			mesesCarenciaComboBox = new JComboBox();
			mesesCarenciaComboBox.setEditable(true);
			mesesCarenciaComboBox.setBounds(new Rectangle(164, 103, 80, 22));
			for (int i=0; i < 24; i++){
				mesesCarenciaComboBox.addItem(i);
			}
		}
		return mesesCarenciaComboBox;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
