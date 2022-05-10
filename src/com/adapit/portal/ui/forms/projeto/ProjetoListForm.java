package com.adapit.portal.ui.forms.projeto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.adapit.portal.entidades.FormaPagamento;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
@SuppressWarnings({"serial","unchecked"})
public class ProjetoListForm extends JPanel {

	private JTabbedPane loteTabbedPane;

	private JPanel statusPanel;

	private JPanel filterBasePanel;

	private JComboBox statusLoteBaseComboBox;

	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();

	@SuppressWarnings("unused")
	private Map hashComps = new java.util.HashMap();

	private JLabel statusLoteBaseComboBoxLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JComboBox dataLeilaoBaseComboBox;

	private JLabel dataLeilaoBaseComboBoxLabel;

	private JButton consultarBaseButton;

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel dadosButtonsPanel;

	private JButton novoLoteButton;

	private JButton removerLoteButton;

	private JButton editarLoteButton;

	private JButton listarProdutosButton;

	private JButton editarLeilaoLoteButton;

	private JButton editarAgendaLoteButton;

	private JButton acompanharLeilaoVirtualButton;

	private JPanel lotesArrematadosPanel;

	private JPanel arrematadosFilterPanel;

	private JPanel formaPagtoPanel;

	private JComboBox formaPagtoComboBox;

	private JLabel formaPagtoComboBoxLabel;

	private JPanel pagoRadioButtonsPanel;

	private JRadioButton ambosPagtoRadioButton;

	private JRadioButton pagosRadioButton;

	private JRadioButton naoPagosRadioButton;

	private JButton consultarFormPagtoButton;

	private JScrollPane arrematadosTableScrollPane;

	private ArrematadosTable arrematadosTable;

	private JPanel arrematadosbuttonsPanel;

	private JButton efetuarPagtoButton;

	private JButton retirarDoDepositoButton;

	private JButton verDadosContatoCliente;

	private JButton gerarRelatorioPagtoButton;

	private JButton verificarDadosPagtoButton;

	private JButton listarLotesPagtoAtrazoButton;

	private JPanel produtosTab;  //  @jve:decl-index=0:visual-constraint="10,470"

	private JPanel listProdutosLotePanel;

	private JTextField numeroLoteTextField;

	private JLabel numeroLoteTextFieldLabel;

	private JButton listarProdutoByCodLoteButton;

	private JScrollPane produtosTableScrollPane;

	private ProdutosTable produtosTable;

	private JPanel buttonsPanel;

	private JButton adicionarButton;

	private JButton removerButton;

	private JButton editButton;

	private JRadioButton noDepositoRadioButton = null;

	private JPanel depositoPanel = null;

	private JRadioButton retiradosRadioButton = null;

	private JRadioButton ambosDepositoRadioButton = null;

	private JLabel estadoDepositoLabel = null;

	private JButton helpButton = null;

	private JCheckBox filtrarSomenteLeilaoCheckBox = null;

	public ProjetoListForm() {

		initialize();
	}

	private void initialize() {

		setSize(new java.awt.Dimension(692, 450));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getLoteTabbedPane());
		this.add(getDataLeilaoBaseComboBoxLabel(), null);
		this.add(getDataLeilaoBaseComboBox(), null);
		this.add(getEditarLeilaoLoteButton(), null);
		this.add(getFiltrarSomenteLeilaoCheckBox(), null);
	}

	protected JTabbedPane getLoteTabbedPane() {

		if (loteTabbedPane == null) {
			loteTabbedPane = new JTabbedPane();
			loteTabbedPane.setSize(new java.awt.Dimension(676, 409));
			loteTabbedPane.setLocation(new Point(10, 35));
			loteTabbedPane.add(getStatusPanel(), "Listar lotes pelo estado");
			loteTabbedPane.add(getLotesArrematadosPanel(),
					"listar lotes arrematados");
			//loteTabbedPane.add(getProdutosTab(), "Produtos Cadastrados no Lote");
			
		}
		return loteTabbedPane;
	}

	protected JPanel getStatusPanel() {

		if (statusPanel == null) {
			statusPanel = new JPanel();
			statusPanel.setLayout(null);
			statusPanel.add(getFilterBasePanel());
			statusPanel.add(getBaseTableScrollPane());
			statusPanel.add(getDadosButtonsPanel());
		}
		return statusPanel;
	}

	protected JPanel getFilterBasePanel() {

		if (filterBasePanel == null) {
			filterBasePanel = new JPanel();
			filterBasePanel.setSize(new java.awt.Dimension(650, 50));
			filterBasePanel.setLocation(new java.awt.Point(10, 13));
			filterBasePanel.setLayout(null);
			filterBasePanel.add(getStatusLoteBaseComboBox(), null);
			filterBasePanel.add(getHelpButton(), null);
			filterBasePanel.add(getStatusLoteBaseComboBoxLabel(), null);
			filterBasePanel.add(getConsultarBaseButton(), null);
		}
		return filterBasePanel;
	}

	protected JComboBox getStatusLoteBaseComboBox() {

		if (statusLoteBaseComboBox == null) {
			statusLoteBaseComboBox = new JComboBox();
			statusLoteBaseComboBox.setBounds(new Rectangle(188, 5, 420, 20));
			statusLoteBaseComboBox.addItem("Todos os estados");
			for (int i=0; i < ScheduledTrainingStatus.values().length;i++){
				statusLoteBaseComboBox.addItem(ScheduledTrainingStatus.values()[i].name().replaceAll("_"," "));
			}
			return statusLoteBaseComboBox;
		}
		return statusLoteBaseComboBox;
	}

	protected JLabel getStatusLoteBaseComboBoxLabel() {

		if (statusLoteBaseComboBoxLabel == null) {
			statusLoteBaseComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EstadodoLote"));
			statusLoteBaseComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
			statusLoteBaseComboBoxLabel.setBounds(new Rectangle(94, 4, 90, 20));
		}
		return statusLoteBaseComboBoxLabel;
	}

	protected JComboBox getDataLeilaoBaseComboBox() {

		if (dataLeilaoBaseComboBox == null) {
			dataLeilaoBaseComboBox = new JComboBox();
			dataLeilaoBaseComboBox.setBounds(new Rectangle(236, 10, 223, 22));
			return dataLeilaoBaseComboBox;
		}
		return dataLeilaoBaseComboBox;
	}

	protected JLabel getDataLeilaoBaseComboBoxLabel() {

		if (dataLeilaoBaseComboBoxLabel == null) {
			dataLeilaoBaseComboBoxLabel = new JLabel(/*
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.DatadoLeilão")*/);
			dataLeilaoBaseComboBoxLabel.setText("Data do Leilão:");
			dataLeilaoBaseComboBoxLabel.setBounds(new Rectangle(146, 10, 90, 22));
			dataLeilaoBaseComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
		}
		return dataLeilaoBaseComboBoxLabel;
	}

	protected JButton getConsultarBaseButton() {

		if (consultarBaseButton == null) {
			consultarBaseButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Consultar"));
			consultarBaseButton.setBounds(new Rectangle(4, 2, 90, 24));
		}
		return consultarBaseButton;
	}

	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new java.awt.Dimension(650, 210));
			baseTableScrollPane.setLocation(new java.awt.Point(10, 66));

			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	protected BaseTable getBaseTable() {

		if (baseTable == null) {
			baseTable = new BaseTable();
			return baseTable;
		}
		return baseTable;
	}

	protected JPanel getDadosButtonsPanel() {

		if (dadosButtonsPanel == null) {
			dadosButtonsPanel = new JPanel();
			dadosButtonsPanel.setSize(new Dimension(651, 97));
			dadosButtonsPanel.setLocation(new java.awt.Point(10, 279));
			dadosButtonsPanel.setLayout(new java.awt.FlowLayout());
			dadosButtonsPanel.add(getNovoLoteButton());
			dadosButtonsPanel.add(getRemoverLoteButton());
			dadosButtonsPanel.add(getEditarLoteButton());
			dadosButtonsPanel.add(getListarProdutosButton());
			dadosButtonsPanel.add(getEditarAgendaLoteButton());
			dadosButtonsPanel.add(getAcompanharLeilaoVirtualButton());
		}
		return dadosButtonsPanel;
	}

	protected JButton getNovoLoteButton() {

		if (novoLoteButton == null) {
			novoLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NovoLote"));
			novoLoteButton.setSize(new java.awt.Dimension(150, 20));
			novoLoteButton.setLocation(new java.awt.Point(0, 0));
		}
		return novoLoteButton;
	}

	protected JButton getRemoverLoteButton() {

		if (removerLoteButton == null) {
			removerLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.RemoverLote"));
			removerLoteButton.setSize(new java.awt.Dimension(80, 22));
			removerLoteButton.setLocation(new java.awt.Point(0, 20));
		}
		return removerLoteButton;
	}

	protected JButton getEditarLoteButton() {

		if (editarLoteButton == null) {
			editarLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EditarLote"));
			editarLoteButton.setSize(new java.awt.Dimension(80, 22));
			editarLoteButton.setLocation(new java.awt.Point(0, 42));
		}
		return editarLoteButton;
	}

	protected JButton getListarProdutosButton() {

		if (listarProdutosButton == null) {
			listarProdutosButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.ListarProdutosdoLote"));
			listarProdutosButton.setSize(new java.awt.Dimension(80, 22));
			listarProdutosButton.setEnabled(false);
			listarProdutosButton.setLocation(new java.awt.Point(0, 64));
		}
		return listarProdutosButton;
	}

	protected JButton getEditarLeilaoLoteButton() {

		if (editarLeilaoLoteButton == null) {
			editarLeilaoLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EditaroLeilãodoLote"));
			editarLeilaoLoteButton.setBounds(new Rectangle(463, 10, 91, 22));
			editarLeilaoLoteButton.setText("Editar Leilão");
		}
		return editarLeilaoLoteButton;
	}

	protected JButton getEditarAgendaLoteButton() {

		if (editarAgendaLoteButton == null) {
			editarAgendaLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EditarAgendadoLote"));
			editarAgendaLoteButton.setSize(new java.awt.Dimension(150, 20));
			editarAgendaLoteButton.setLocation(new java.awt.Point(0, 106));
		}
		return editarAgendaLoteButton;
	}

	protected JButton getAcompanharLeilaoVirtualButton() {

		if (acompanharLeilaoVirtualButton == null) {
			acompanharLeilaoVirtualButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.AcompanharoLeilãoVirtual"));
			acompanharLeilaoVirtualButton.setSize(new java.awt.Dimension(150,
					20));
			acompanharLeilaoVirtualButton.setEnabled(false);
			acompanharLeilaoVirtualButton
					.setLocation(new java.awt.Point(0, 126));
		}
		return acompanharLeilaoVirtualButton;
	}

	protected JPanel getLotesArrematadosPanel() {

		if (lotesArrematadosPanel == null) {
			lotesArrematadosPanel = new JPanel();
			lotesArrematadosPanel.setSize(new java.awt.Dimension(150, 20));
			lotesArrematadosPanel.setLocation(new java.awt.Point(0, 281));
			lotesArrematadosPanel.setLayout(null);
			lotesArrematadosPanel.add(getArrematadosFilterPanel());
			lotesArrematadosPanel.add(getArrematadosTableScrollPane());
			lotesArrematadosPanel.add(getArrematadosbuttonsPanel());
		}
		return lotesArrematadosPanel;
	}

	protected JPanel getArrematadosFilterPanel() {

		if (arrematadosFilterPanel == null) {
			estadoDepositoLabel = new JLabel();
			estadoDepositoLabel.setBounds(new Rectangle(195, 24, 158, 23));
			estadoDepositoLabel.setText("Pelo estado do lote no depósito:");
			arrematadosFilterPanel = new JPanel();
			arrematadosFilterPanel.setSize(new java.awt.Dimension(650, 50));
			arrematadosFilterPanel.setLocation(new java.awt.Point(10, 13));
			arrematadosFilterPanel.setLayout(null);
			arrematadosFilterPanel.add(getFormaPagtoPanel());
			arrematadosFilterPanel.add(getConsultarFormPagtoButton());
			arrematadosFilterPanel.add(getDepositoPanel(), null);
			arrematadosFilterPanel.add(estadoDepositoLabel, null);
		}
		return arrematadosFilterPanel;
	}

	protected JPanel getFormaPagtoPanel() {

		if (formaPagtoPanel == null) {
			formaPagtoPanel = new JPanel();
			formaPagtoPanel.setSize(new Dimension(650, 22));
			formaPagtoPanel.setLocation(new Point(0, 2));
			formaPagtoPanel.setLayout(null);
			formaPagtoPanel.add(getFormaPagtoComboBox());
			formaPagtoPanel.add(getFormaPagtoComboBoxLabel());
			formaPagtoPanel.add(getPagoRadioButtonsPanel());
		}
		return formaPagtoPanel;
	}

	protected JComboBox getFormaPagtoComboBox() {

		if (formaPagtoComboBox == null) {
			formaPagtoComboBox = new JComboBox();
			formaPagtoComboBox.setSize(new Dimension(241, 22));
			formaPagtoComboBox.setLocation(new Point(114, 0));
			formaPagtoComboBox.addItem("Todas");
			for (int i=0; i < FormaPagamento.values().length;i++){
				formaPagtoComboBox.addItem(FormaPagamento.values()[i].name().replaceAll("_"," "));
			}
			return formaPagtoComboBox;
		}
		return formaPagtoComboBox;
	}

	protected JLabel getFormaPagtoComboBoxLabel() {

		if (formaPagtoComboBoxLabel == null) {
			formaPagtoComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.FormadePagamento"));
			formaPagtoComboBoxLabel.setSize(new Dimension(110, 22));
			formaPagtoComboBoxLabel.setLocation(new Point(0, 0));
			formaPagtoComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
		}
		return formaPagtoComboBoxLabel;
	}

	protected JPanel getPagoRadioButtonsPanel() {

		if (pagoRadioButtonsPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setVgap(0);
			flowLayout.setHgap(0);
			pagoRadioButtonsPanel = new JPanel();
			pagoRadioButtonsPanel.setLayout(flowLayout);
			pagoRadioButtonsPanel.setSize(new Dimension(201, 21));
			pagoRadioButtonsPanel.setLocation(new Point(359, -1));
			pagoRadioButtonsPanel.add(getAmbosPagtoRadioButton());
			pagoRadioButtonsPanel.add(getPagosRadioButton());
			pagoRadioButtonsPanel.add(getNaoPagosRadioButton());
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAmbosPagtoRadioButton());
			bg.add(getPagosRadioButton());
			bg.add(getNaoPagosRadioButton());
		}
		return pagoRadioButtonsPanel;
	}

	protected JRadioButton getAmbosPagtoRadioButton() {

		if (ambosPagtoRadioButton == null) {
			ambosPagtoRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Ambos"));
			ambosPagtoRadioButton.setSize(new java.awt.Dimension(80, 20));
			ambosPagtoRadioButton.setSelected(true);
			ambosPagtoRadioButton.setLocation(new java.awt.Point(0, 0));
			return ambosPagtoRadioButton;
		}
		return ambosPagtoRadioButton;
	}

	protected JRadioButton getPagosRadioButton() {

		if (pagosRadioButton == null) {
			pagosRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Pagos"));
			pagosRadioButton.setSize(new java.awt.Dimension(80, 20));
			pagosRadioButton.setLocation(new java.awt.Point(80, 0));
			return pagosRadioButton;
		}
		return pagosRadioButton;
	}

	protected JRadioButton getNaoPagosRadioButton() {

		if (naoPagosRadioButton == null) {
			naoPagosRadioButton = new JRadioButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NãoPagos"));
			naoPagosRadioButton.setSize(new java.awt.Dimension(80, 20));
			naoPagosRadioButton.setLocation(new java.awt.Point(160, 0));
			return naoPagosRadioButton;
		}
		return naoPagosRadioButton;
	}

	protected JButton getConsultarFormPagtoButton() {

		if (consultarFormPagtoButton == null) {
			consultarFormPagtoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Consultar"));
			consultarFormPagtoButton.setSize(new java.awt.Dimension(90, 24));
			consultarFormPagtoButton.setLocation(new Point(4, 23));
		}
		return consultarFormPagtoButton;
	}

	protected JScrollPane getArrematadosTableScrollPane() {

		if (arrematadosTableScrollPane == null) {
			arrematadosTableScrollPane = new JScrollPane();
			arrematadosTableScrollPane
					.setSize(new java.awt.Dimension(650, 210));
			arrematadosTableScrollPane.setLocation(new java.awt.Point(10, 66));

			arrematadosTableScrollPane.add(getArrematadosTable());
			arrematadosTableScrollPane.setViewportView(getArrematadosTable());
		}
		return arrematadosTableScrollPane;
	}

	protected ArrematadosTable getArrematadosTable() {

		if (arrematadosTable == null) {
			arrematadosTable = new ArrematadosTable();
			return arrematadosTable;
		}
		return arrematadosTable;
	}

	protected JPanel getArrematadosbuttonsPanel() {

		if (arrematadosbuttonsPanel == null) {
			arrematadosbuttonsPanel = new JPanel();
			arrematadosbuttonsPanel.setSize(new Dimension(650, 94));
			arrematadosbuttonsPanel.setLocation(new java.awt.Point(10, 279));
			arrematadosbuttonsPanel.setLayout(new java.awt.FlowLayout());
			arrematadosbuttonsPanel.add(getEfetuarPagtoButton());
			arrematadosbuttonsPanel.add(getRetirarDoDepositoButton());
			arrematadosbuttonsPanel.add(getVerDadosContatoCliente());
			arrematadosbuttonsPanel.add(getGerarRelatorioPagtoButton());
			arrematadosbuttonsPanel.add(getVerificarDadosPagtoButton());
			arrematadosbuttonsPanel.add(getListarLotesPagtoAtrazoButton());
		}
		return arrematadosbuttonsPanel;
	}

	protected JButton getEfetuarPagtoButton() {

		if (efetuarPagtoButton == null) {
			efetuarPagtoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EfetuaroPagamento"));
			efetuarPagtoButton.setSize(new java.awt.Dimension(150, 20));
			efetuarPagtoButton.setLocation(new java.awt.Point(0, 0));
		}
		return efetuarPagtoButton;
	}

	protected JButton getRetirarDoDepositoButton() {

		if (retirarDoDepositoButton == null) {
			retirarDoDepositoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.RetirardoDepósito"));
			retirarDoDepositoButton.setSize(new java.awt.Dimension(150, 20));
			retirarDoDepositoButton.setLocation(new java.awt.Point(0, 20));
		}
		return retirarDoDepositoButton;
	}

	protected JButton getVerDadosContatoCliente() {

		if (verDadosContatoCliente == null) {
			verDadosContatoCliente = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.VerDadosdeContatodoCliente"));
			verDadosContatoCliente.setSize(new java.awt.Dimension(150, 20));
			verDadosContatoCliente.setLocation(new java.awt.Point(0, 40));
		}
		return verDadosContatoCliente;
	}

	protected JButton getGerarRelatorioPagtoButton() {

		if (gerarRelatorioPagtoButton == null) {
			gerarRelatorioPagtoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.GerarRelatóriodoPagamento"));
			gerarRelatorioPagtoButton.setSize(new java.awt.Dimension(150, 20));
			gerarRelatorioPagtoButton.setLocation(new java.awt.Point(0, 60));
		}
		return gerarRelatorioPagtoButton;
	}

	protected JButton getVerificarDadosPagtoButton() {

		if (verificarDadosPagtoButton == null) {
			verificarDadosPagtoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.VerificarDadosSobreoPagamento"));
			verificarDadosPagtoButton.setSize(new java.awt.Dimension(150, 20));
			verificarDadosPagtoButton.setLocation(new java.awt.Point(0, 80));
		}
		return verificarDadosPagtoButton;
	}

	protected JButton getListarLotesPagtoAtrazoButton() {

		if (listarLotesPagtoAtrazoButton == null) {
			listarLotesPagtoAtrazoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.ListarLotescomPagamentosemAtrazo"));
			listarLotesPagtoAtrazoButton
					.setSize(new java.awt.Dimension(150, 20));
			listarLotesPagtoAtrazoButton
					.setLocation(new java.awt.Point(0, 100));
		}
		return listarLotesPagtoAtrazoButton;
	}

	protected JPanel getProdutosTab() {

		if (produtosTab == null) {
			produtosTab = new JPanel();
			produtosTab.setSize(new Dimension(671, 290));
			produtosTab.setLocation(new java.awt.Point(0, 301));
			produtosTab.setLayout(null);
			produtosTab.add(getListProdutosLotePanel());
			produtosTab.add(getProdutosTableScrollPane());
			produtosTab.add(getButtonsPanel());
		}
		return produtosTab;
	}

	protected JPanel getListProdutosLotePanel() {

		if (listProdutosLotePanel == null) {
			listProdutosLotePanel = new JPanel();
			listProdutosLotePanel.setSize(new java.awt.Dimension(653, 23));
			listProdutosLotePanel.setLocation(new java.awt.Point(10, 13));
			listProdutosLotePanel.setLayout(null);
			listProdutosLotePanel.add(getNumeroLoteTextField());
			listProdutosLotePanel.add(getNumeroLoteTextFieldLabel());
			listProdutosLotePanel.add(getListarProdutoByCodLoteButton());
		}
		return listProdutosLotePanel;
	}

	protected JTextField getNumeroLoteTextField() {

		if (numeroLoteTextField == null) {
			numeroLoteTextField = new JTextField();
			numeroLoteTextField.setText("");
			numeroLoteTextField.setSize(new Dimension(91, 22));
			numeroLoteTextField.setLocation(new Point(89, 0));
			return numeroLoteTextField;
		}
		return numeroLoteTextField;
	}

	protected JLabel getNumeroLoteTextFieldLabel() {

		if (numeroLoteTextFieldLabel == null) {
			numeroLoteTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NúmerodoLote")+":");
			numeroLoteTextFieldLabel.setSize(new Dimension(87, 20));
			numeroLoteTextFieldLabel.setLocation(new java.awt.Point(0, 0));
			numeroLoteTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return numeroLoteTextFieldLabel;
	}

	protected JButton getListarProdutoByCodLoteButton() {

		if (listarProdutoByCodLoteButton == null) {
			listarProdutoByCodLoteButton = new JButton("Listar");
			listarProdutoByCodLoteButton
					.setSize(new java.awt.Dimension(100, 24));
			listarProdutoByCodLoteButton
					.setLocation(new java.awt.Point(300, 0));
		}
		return listarProdutoByCodLoteButton;
	}

	protected JScrollPane getProdutosTableScrollPane() {

		if (produtosTableScrollPane == null) {
			produtosTableScrollPane = new JScrollPane();
			produtosTableScrollPane.setSize(new java.awt.Dimension(650, 200));
			produtosTableScrollPane.setLocation(new java.awt.Point(10, 39));

			produtosTableScrollPane.add(getProdutosTable());
			produtosTableScrollPane.setViewportView(getProdutosTable());
		}
		return produtosTableScrollPane;
	}

	protected ProdutosTable getProdutosTable() {

		if (produtosTable == null) {
			produtosTable = new ProdutosTable();
			return produtosTable;
		}
		return produtosTable;
	}

	protected JPanel getButtonsPanel() {

		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new java.awt.Dimension(650, 35));
			buttonsPanel.setLocation(new java.awt.Point(10, 242));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getAdicionarButton());
			buttonsPanel.add(getRemoverButton());
			buttonsPanel.add(getEditButton());
		}
		return buttonsPanel;
	}

	protected JButton getAdicionarButton() {

		if (adicionarButton == null) {
			adicionarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Adicionar"));
			adicionarButton.setSize(new java.awt.Dimension(80, 22));
			adicionarButton.setLocation(new java.awt.Point(0, 0));
		}
		return adicionarButton;
	}

	protected JButton getRemoverButton() {

		if (removerButton == null) {
			removerButton = new JButton("Remover");
			removerButton.setSize(new java.awt.Dimension(80, 22));
			removerButton.setLocation(new java.awt.Point(0, 22));
		}
		return removerButton;
	}

	protected JButton getEditButton() {

		if (editButton == null) {
			editButton = new JButton("Editar");
			editButton.setSize(new java.awt.Dimension(80, 22));
			editButton.setLocation(new java.awt.Point(0, 44));
		}
		return editButton;
	}

	/**
	 * This method initializes noDepositoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getNoDepositoRadioButton() {
		if (noDepositoRadioButton == null) {
			noDepositoRadioButton = new JRadioButton();
			noDepositoRadioButton.setText("No Depósito");
		}
		return noDepositoRadioButton;
	}

	/**
	 * This method initializes depositoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDepositoPanel() {
		if (depositoPanel == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setVgap(0);
			flowLayout1.setHgap(0);
			depositoPanel = new JPanel();
			depositoPanel.setLayout(flowLayout1);
			depositoPanel.setBounds(new Rectangle(360, 24, 221, 23));
			depositoPanel.add(getAmbosDepositoRadioButton(), null);
			depositoPanel.add(getNoDepositoRadioButton(), null);
			depositoPanel.add(getRetiradosRadioButton(), null);
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAmbosDepositoRadioButton());
			bg.add(getNoDepositoRadioButton());
			bg.add(getRetiradosRadioButton());
		}
		return depositoPanel;
	}

	/**
	 * This method initializes retiradosRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getRetiradosRadioButton() {
		if (retiradosRadioButton == null) {
			retiradosRadioButton = new JRadioButton();
			retiradosRadioButton.setText("Retirados");
		}
		return retiradosRadioButton;
	}

	/**
	 * This method initializes ambosDepositoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getAmbosDepositoRadioButton() {
		if (ambosDepositoRadioButton == null) {
			ambosDepositoRadioButton = new JRadioButton();
			ambosDepositoRadioButton.setText("Ambos");
			ambosDepositoRadioButton.setSelected(true);
		}
		return ambosDepositoRadioButton;
	}

	/**
	 * This method initializes helpButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setBounds(new Rectangle(611, 5, 20, 20));
			helpButton.setIcon(new ImageIcon(getClass().getResource("/imgs/helpicon.png")));
		}
		return helpButton;
	}

	/**
	 * This method initializes filtrarSomenteLeilaoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getFiltrarSomenteLeilaoCheckBox() {
		if (filtrarSomenteLeilaoCheckBox == null) {
			filtrarSomenteLeilaoCheckBox = new JCheckBox();
			filtrarSomenteLeilaoCheckBox.setBounds(new Rectangle(10, 10, 135, 22));
			filtrarSomenteLeilaoCheckBox.setText("Filtrar lotes pelo leilão");
		}
		return filtrarSomenteLeilaoCheckBox;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(692, 450));
				gui.add(new ProjetoListForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unused")
	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}// end of catch block
		return null;
	}

	private class ArrematadosTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public ArrematadosTable() {

			super();
			//this.setModel(new ArrematadosTableModel(null));
			this.addPropertyChangeListener(new ArrematadosTablePropertyChangeListener());
			updateTable();
		}

		public ArrematadosTable(List elements) {

			super();
			this.elements = elements;
			//this.setModel(new ArrematadosTableModel(null));
			this.addPropertyChangeListener(new ArrematadosTablePropertyChangeListener());
			updateTable();
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Projeto) {
						com.adapit.portal.entidades.Projeto lote = (com.adapit.portal.entidades.Projeto) obj;
						values[i][0] = lote.getCodigoProjeto();
						/*values[i][2] = lote.getComprador();
						values[i][3] = lote.getComprador();
						values[i][4] = lote.getComprador();*/
						i++;
					}
				}// End of while Loop
				setModel(new ArrematadosTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(80);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				updateUI();
			} else {
				setModel(new ArrematadosTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(80);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				updateUI();
			}
		}

		private class ArrematadosTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.projeto.ProjetoListForm.ArrematadosTable jt = (com.adapit.portal.ui.forms.projeto.ProjetoListForm.ArrematadosTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Projeto) {
							com.adapit.portal.entidades.Projeto lote = (com.adapit.portal.entidades.Projeto) obj;
							if (col == 0)
								lote.setCodigoProjeto(((java.lang.String) jt
										.getValueAt(row, col)));
							/*if (col == 2)
								lote.setComprador(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 3)
								lote.setComprador(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 4)
								lote.setComprador(((java.lang.String) jt
										.getValueAt(row, col)));*/
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class ArrematadosTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, Boolean.class, Boolean.class };

			boolean canEdit[] = new boolean[] { false, false, false, false, false };

			public ArrematadosTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NúmerodoLote"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NomedoArrematante"),
								
								"Forma de Pagamento","Pago","Retirado" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class ProdutosTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public ProdutosTable() {

			super();
			this.setModel(new ProdutosTableModel(null));
			this
					.addPropertyChangeListener(new ProdutosTablePropertyChangeListener());
		}

		public ProdutosTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new ProdutosTableModel(null));
			this
					.addPropertyChangeListener(new ProdutosTablePropertyChangeListener());
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				//java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][4];
				/*int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
				}*/// End of while Loop
				setModel(new ProdutosTableModel(values));
				updateUI();
			} else {
				setModel(new ProdutosTableModel(null));
				updateUI();
			}
		}

		private class ProdutosTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.projeto.ProjetoListForm.ProdutosTable jt = (com.adapit.portal.ui.forms.projeto.ProjetoListForm.ProdutosTable) e
						.getSource();
				//int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						//java.lang.Object obj = jt.getElements().get(row);
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class ProdutosTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					Integer.class, String.class };

			boolean canEdit[] = new boolean[] { true, true, true, true };

			public ProdutosTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Produto"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Categoria"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Qtd"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.ValordoItem") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class BaseTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public BaseTable() {

			super();
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			updateTable();
		}

		public BaseTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			updateTable();
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][4];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Projeto) {
						com.adapit.portal.entidades.Projeto lote = (com.adapit.portal.entidades.Projeto) obj;
						values[i][0] = lote.getCodigoProjeto();
						values[i][2] = lote.getStatus();
						/*values[i][3] = lote.getAgendas();
						values[i][4] = lote.getAgendas();*/
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(80);
				getColumnModel().getColumn(1).setPreferredWidth(350);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(80);
				getColumnModel().getColumn(1).setPreferredWidth(350);
				updateUI();
			}
		}

		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false};

			public BaseTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NúmerodoLote"),
								"Estado do Lote"
								 });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e ){

			BaseTable jt = (BaseTable) e.getSource();
			int col = jt.getSelectedColumn();
			int row = jt.getSelectedRow();
			if (jt.getElements() != null && row > -1) try{
				java.lang.Object obj = jt.getElements().get(row);
				if (obj instanceof com.adapit.portal.entidades.Projeto) {
					com.adapit.portal.entidades.Projeto lote = (com.adapit.portal.entidades.Projeto) obj;
					if (col ==0) lote.setCodigoProjeto(((java.lang.String)jt.getValueAt(row, col)));
					/*if (col ==2) lote.setStatus(((java.lang.String)jt.getValueAt(row, col)));
					if (col ==3) lote.setAgendas((new java.lang.Date((java.lang.String)jt.getValueAt(row, col)));
					if (col ==4) lote.setAgendas((new java.lang.Date((java.lang.String)jt.getValueAt(row, col)));*/
				}
			}catch(java.lang.Exception ex){
				ex.printStackTrace();
			}
	}

		}

	}

}