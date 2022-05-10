package com.adapit.portal.ui.forms.projeto.agendaprojeto;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
@SuppressWarnings({"serial","unchecked"})
public class AgendaListaProjetoDialog extends JDialog {

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel loteLeilaoPanel;

	private JComboBox numeroLoteComboBox;

	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();

	@SuppressWarnings("unused")
	private Map hashComps = new java.util.HashMap();

	private JLabel numeroLoteComboBoxLabel;

	private JComboBox dataLeilaoComboBox;

	private JLabel dataLeilaoComboBoxLabel;

	private JTabbedPane tabbedPane;

	private JPanel basePanel;

	private JPanel estadoAgendaFilterPanel;

	private JButton listarButton;

	private JComboBox estadoAgendaComboBox;

	private JLabel estadoAgendaComboBoxLabel;

	private JScrollPane agendasTableScrollPane;

	private AgendasTable agendasTable;

	private JPanel baseButtonsPanel;

	private JButton novaAgendaButton;

	private JButton editarAgendaButton;

	private JButton removerAgendaButton;

	private JButton cancelarAgendaButton;

	private JPanel agendasLancesPanel;

	private JPanel lanceVencedorPanel;

	private JTextField lanceVencedorTextField;

	private JLabel lanceVencedorTextFieldLabel;

	private JTextField encerramentoTextField;

	private JLabel encerramentoTextFieldLabel;

	private JTextField numeroLancesTextField;

	private JLabel numeroLancesTextFieldLabel;

	private JPanel arrematadorPanel;

	private JTextField nomeVencedorTextField;

	private JLabel nomeVencedorTextFieldLabel;

	private JButton verDadosClienteButton;

	private JScrollPane lancesTableScrollPane;

	private LancesTable lancesTable;

	private JPanel lotesLancesButtonsPanel;

	private JButton listarLancesButton;

	private JButton verHistoricoClienteButton;

	private JPanel historicoLancesCleintesPanel;

	private JPanel historicoPartFilterPanel;

	private JTextField loginTextField;

	private JLabel loginTextFieldLabel;

	private JButton listarHistoricoParticipacaoButton;

	private JScrollPane usuarioHistoricoLancesTableScrollPane;

	private UsuarioHistoricoLancezTable usuarioHistoricoLancezTable;

	private JPanel historicoButtonsPanel;

	private JButton listarPrefCliente;

	public AgendaListaProjetoDialog() {
		super();
		//super(LeilaoVirtualFrame.getInstance());
		setTitle(messages
				.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.AgendaListaLoteDialog"));
		setModal(true);
		initialize();
	}

	private void initialize() {
		setSize(new java.awt.Dimension(495, 325));
		setLayout(null);
		add(getLoteLeilaoPanel());
		add(getTabbedPane());
	}

	protected JPanel getLoteLeilaoPanel() {

		if (loteLeilaoPanel == null) {
			loteLeilaoPanel = new JPanel();
			loteLeilaoPanel.setSize(new java.awt.Dimension(453, 21));
			loteLeilaoPanel.setLocation(new java.awt.Point(10, 13));
			loteLeilaoPanel.setLayout(null);
			loteLeilaoPanel.add(getNumeroLoteComboBox());
			loteLeilaoPanel.add(getNumeroLoteComboBoxLabel());
			loteLeilaoPanel.add(getDataLeilaoComboBox());
			loteLeilaoPanel.add(getDataLeilaoComboBoxLabel());
		}
		return loteLeilaoPanel;
	}

	protected JComboBox getNumeroLoteComboBox() {

		if (numeroLoteComboBox == null) {
			numeroLoteComboBox = new JComboBox();
			numeroLoteComboBox.setSize(new java.awt.Dimension(110, 20));
			numeroLoteComboBox.setLocation(new java.awt.Point(100, 0));
			return numeroLoteComboBox;
		}
		return numeroLoteComboBox;
	}

	protected JLabel getNumeroLoteComboBoxLabel() {

		if (numeroLoteComboBoxLabel == null) {
			numeroLoteComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.NúmerodoLote"));
			numeroLoteComboBoxLabel.setSize(new java.awt.Dimension(100, 20));
			numeroLoteComboBoxLabel.setLocation(new java.awt.Point(0, 0));
			numeroLoteComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return numeroLoteComboBoxLabel;
	}

	protected JComboBox getDataLeilaoComboBox() {

		if (dataLeilaoComboBox == null) {
			dataLeilaoComboBox = new JComboBox();
			dataLeilaoComboBox.setSize(new java.awt.Dimension(142, 20));
			dataLeilaoComboBox.setLocation(new java.awt.Point(310, 0));
			return dataLeilaoComboBox;
		}
		return dataLeilaoComboBox;
	}

	protected JLabel getDataLeilaoComboBoxLabel() {

		if (dataLeilaoComboBoxLabel == null) {
			dataLeilaoComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.DatadoLeilão"));
			dataLeilaoComboBoxLabel.setSize(new java.awt.Dimension(100, 20));
			dataLeilaoComboBoxLabel.setLocation(new java.awt.Point(210, 0));
			dataLeilaoComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return dataLeilaoComboBoxLabel;
	}

	protected JTabbedPane getTabbedPane() {

		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new java.awt.Dimension(466, 244));
			tabbedPane.setLocation(new java.awt.Point(10, 37));

			tabbedPane.add(getBasePanel(), "Lotes e Datas");
			tabbedPane.add(getAgendasLancesPanel(), "Lotes e Lances");
			tabbedPane.add(getHistoricoLancesCleintesPanel(),
					"Histórico da Participação de Clientes em Leilões");
		}
		return tabbedPane;
	}

	protected JPanel getBasePanel() {

		if (basePanel == null) {
			basePanel = new JPanel();
			basePanel.setSize(new java.awt.Dimension(453, 226));
			basePanel.setLocation(new java.awt.Point(0, 3));
			basePanel.setLayout(null);
			basePanel.add(getEstadoAgendaFilterPanel());
			basePanel.add(getAgendasTableScrollPane());
			basePanel.add(getBaseButtonsPanel());
		}
		return basePanel;
	}

	protected JPanel getEstadoAgendaFilterPanel() {

		if (estadoAgendaFilterPanel == null) {
			estadoAgendaFilterPanel = new JPanel();
			estadoAgendaFilterPanel.setSize(new java.awt.Dimension(452, 24));
			estadoAgendaFilterPanel.setLocation(new java.awt.Point(0, 3));
			estadoAgendaFilterPanel.setLayout(null);
			estadoAgendaFilterPanel.add(getListarButton());
			estadoAgendaFilterPanel.add(getEstadoAgendaComboBox());
			estadoAgendaFilterPanel.add(getEstadoAgendaComboBoxLabel());
		}
		return estadoAgendaFilterPanel;
	}

	protected JButton getListarButton() {

		if (listarButton == null) {
			listarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Listar"));
			listarButton.setSize(new java.awt.Dimension(90, 24));
			listarButton.setLocation(new java.awt.Point(0, 0));
		}
		return listarButton;
	}

	protected JComboBox getEstadoAgendaComboBox() {

		if (estadoAgendaComboBox == null) {
			estadoAgendaComboBox = new JComboBox();
			estadoAgendaComboBox.setSize(new java.awt.Dimension(160, 20));
			estadoAgendaComboBox.setLocation(new java.awt.Point(290, 0));
			return estadoAgendaComboBox;
		}
		return estadoAgendaComboBox;
	}

	protected JLabel getEstadoAgendaComboBoxLabel() {

		if (estadoAgendaComboBoxLabel == null) {
			estadoAgendaComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.EstadodaAgenda"));
			estadoAgendaComboBoxLabel.setSize(new java.awt.Dimension(100, 20));
			estadoAgendaComboBoxLabel.setLocation(new java.awt.Point(190, 0));
			estadoAgendaComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return estadoAgendaComboBoxLabel;
	}

	protected JScrollPane getAgendasTableScrollPane() {

		if (agendasTableScrollPane == null) {
			agendasTableScrollPane = new JScrollPane();
			agendasTableScrollPane.setSize(new java.awt.Dimension(452, 138));
			agendasTableScrollPane.setLocation(new java.awt.Point(0, 30));

			agendasTableScrollPane.add(getAgendasTable());
			agendasTableScrollPane.setViewportView(getAgendasTable());
		}
		return agendasTableScrollPane;
	}

	protected AgendasTable getAgendasTable() {

		if (agendasTable == null) {
			agendasTable = new AgendasTable();
			agendasTable.setSize(new java.awt.Dimension(452, 138));
			return agendasTable;
		}
		return agendasTable;
	}

	protected JPanel getBaseButtonsPanel() {

		if (baseButtonsPanel == null) {
			baseButtonsPanel = new JPanel();
			baseButtonsPanel.setSize(new java.awt.Dimension(452, 35));
			baseButtonsPanel.setLocation(new java.awt.Point(0, 171));
			baseButtonsPanel.setLayout(new java.awt.FlowLayout());
			baseButtonsPanel.add(getNovaAgendaButton());
			baseButtonsPanel.add(getEditarAgendaButton());
			baseButtonsPanel.add(getRemoverAgendaButton());
			baseButtonsPanel.add(getCancelarAgendaButton());
		}
		return baseButtonsPanel;
	}

	protected JButton getNovaAgendaButton() {

		if (novaAgendaButton == null) {
			novaAgendaButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.NovaAgenda"));
			novaAgendaButton.setSize(new java.awt.Dimension(130, 20));
			novaAgendaButton.setLocation(new java.awt.Point(0, 3));
		}
		return novaAgendaButton;
	}

	protected JButton getEditarAgendaButton() {

		if (editarAgendaButton == null) {
			editarAgendaButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Editar"));
			editarAgendaButton.setSize(new java.awt.Dimension(90, 20));
			editarAgendaButton.setLocation(new java.awt.Point(0, 26));
		}
		return editarAgendaButton;
	}

	protected JButton getRemoverAgendaButton() {

		if (removerAgendaButton == null) {
			removerAgendaButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Remover"));
			removerAgendaButton.setSize(new java.awt.Dimension(90, 20));
			removerAgendaButton.setLocation(new java.awt.Point(0, 49));
		}
		return removerAgendaButton;
	}

	protected JButton getCancelarAgendaButton() {

		if (cancelarAgendaButton == null) {
			cancelarAgendaButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.CancelaraAgenda"));
			cancelarAgendaButton.setSize(new java.awt.Dimension(130, 20));
			cancelarAgendaButton.setLocation(new java.awt.Point(0, 72));
		}
		return cancelarAgendaButton;
	}

	protected JPanel getAgendasLancesPanel() {

		if (agendasLancesPanel == null) {
			agendasLancesPanel = new JPanel();
			agendasLancesPanel.setSize(new java.awt.Dimension(461, 215));
			agendasLancesPanel.setLocation(new java.awt.Point(0, 232));
			agendasLancesPanel.setLayout(null);
			agendasLancesPanel.add(getLanceVencedorPanel());
			agendasLancesPanel.add(getArrematadorPanel());
			agendasLancesPanel.add(getLancesTableScrollPane());
			agendasLancesPanel.add(getLotesLancesButtonsPanel());
		}
		return agendasLancesPanel;
	}

	protected JPanel getLanceVencedorPanel() {

		if (lanceVencedorPanel == null) {
			lanceVencedorPanel = new JPanel();
			lanceVencedorPanel.setSize(new java.awt.Dimension(451, 20));
			lanceVencedorPanel.setLocation(new java.awt.Point(0, 6));
			lanceVencedorPanel.setLayout(null);
			lanceVencedorPanel.add(getLanceVencedorTextField());
			lanceVencedorPanel.add(getLanceVencedorTextFieldLabel());
			lanceVencedorPanel.add(getEncerramentoTextField());
			lanceVencedorPanel.add(getEncerramentoTextFieldLabel());
			lanceVencedorPanel.add(getNumeroLancesTextField());
			lanceVencedorPanel.add(getNumeroLancesTextFieldLabel());
		}
		return lanceVencedorPanel;
	}

	protected JTextField getLanceVencedorTextField() {

		if (lanceVencedorTextField == null) {
			lanceVencedorTextField = new JTextField();
			lanceVencedorTextField.setText("");
			lanceVencedorTextField.setSize(new java.awt.Dimension(90, 20));
			lanceVencedorTextField.setLocation(new java.awt.Point(85, 0));
			return lanceVencedorTextField;
		}
		return lanceVencedorTextField;
	}

	protected JLabel getLanceVencedorTextFieldLabel() {

		if (lanceVencedorTextFieldLabel == null) {
			lanceVencedorTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.LanceVencedor"));
			lanceVencedorTextFieldLabel.setSize(new java.awt.Dimension(85, 20));
			lanceVencedorTextFieldLabel.setLocation(new java.awt.Point(0, 0));
			lanceVencedorTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return lanceVencedorTextFieldLabel;
	}

	protected JTextField getEncerramentoTextField() {

		if (encerramentoTextField == null) {
			encerramentoTextField = new JTextField();
			encerramentoTextField.setText("");
			encerramentoTextField.setSize(new java.awt.Dimension(75, 20));
			encerramentoTextField.setLocation(new java.awt.Point(260, 0));
			return encerramentoTextField;
		}
		return encerramentoTextField;
	}

	protected JLabel getEncerramentoTextFieldLabel() {

		if (encerramentoTextFieldLabel == null) {
			encerramentoTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Encerramento"));
			encerramentoTextFieldLabel.setSize(new java.awt.Dimension(85, 20));
			encerramentoTextFieldLabel.setLocation(new java.awt.Point(175, 0));
			encerramentoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return encerramentoTextFieldLabel;
	}

	protected JTextField getNumeroLancesTextField() {

		if (numeroLancesTextField == null) {
			numeroLancesTextField = new JTextField();
			numeroLancesTextField.setText("");
			numeroLancesTextField.setSize(new java.awt.Dimension(30, 20));
			numeroLancesTextField.setLocation(new java.awt.Point(420, 0));
			return numeroLancesTextField;
		}
		return numeroLancesTextField;
	}

	protected JLabel getNumeroLancesTextFieldLabel() {

		if (numeroLancesTextFieldLabel == null) {
			numeroLancesTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.TotaldeLances"));
			numeroLancesTextFieldLabel.setSize(new java.awt.Dimension(85, 20));
			numeroLancesTextFieldLabel.setLocation(new java.awt.Point(335, 0));
			numeroLancesTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return numeroLancesTextFieldLabel;
	}

	protected JPanel getArrematadorPanel() {

		if (arrematadorPanel == null) {
			arrematadorPanel = new JPanel();
			arrematadorPanel.setSize(new java.awt.Dimension(452, 24));
			arrematadorPanel.setLocation(new java.awt.Point(0, 29));
			arrematadorPanel.setLayout(null);
			arrematadorPanel.add(getNomeVencedorTextField());
			arrematadorPanel.add(getNomeVencedorTextFieldLabel());
			arrematadorPanel.add(getVerDadosClienteButton());
		}
		return arrematadorPanel;
	}

	protected JTextField getNomeVencedorTextField() {

		if (nomeVencedorTextField == null) {
			nomeVencedorTextField = new JTextField();
			nomeVencedorTextField.setText("");
			nomeVencedorTextField.setSize(new java.awt.Dimension(275, 22));
			nomeVencedorTextField.setLocation(new java.awt.Point(85, 0));
			return nomeVencedorTextField;
		}
		return nomeVencedorTextField;
	}

	protected JLabel getNomeVencedorTextFieldLabel() {

		if (nomeVencedorTextFieldLabel == null) {
			nomeVencedorTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Arrematante"));
			nomeVencedorTextFieldLabel.setSize(new java.awt.Dimension(85, 20));
			nomeVencedorTextFieldLabel.setLocation(new java.awt.Point(0, 0));
			nomeVencedorTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeVencedorTextFieldLabel;
	}

	protected JButton getVerDadosClienteButton() {

		if (verDadosClienteButton == null) {
			verDadosClienteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.VerDados"));
			verDadosClienteButton.setSize(new java.awt.Dimension(90, 22));
			verDadosClienteButton.setLocation(new java.awt.Point(360, 0));
		}
		return verDadosClienteButton;
	}

	protected JScrollPane getLancesTableScrollPane() {

		if (lancesTableScrollPane == null) {
			lancesTableScrollPane = new JScrollPane();
			lancesTableScrollPane.setSize(new java.awt.Dimension(452, 111));
			lancesTableScrollPane.setLocation(new java.awt.Point(0, 56));

			lancesTableScrollPane.add(getLancesTable());
			lancesTableScrollPane.setViewportView(getLancesTable());
		}
		return lancesTableScrollPane;
	}

	protected LancesTable getLancesTable() {

		if (lancesTable == null) {
			lancesTable = new LancesTable();
			lancesTable.setSize(new java.awt.Dimension(452, 111));
			return lancesTable;
		}
		return lancesTable;
	}

	protected JPanel getLotesLancesButtonsPanel() {

		if (lotesLancesButtonsPanel == null) {
			lotesLancesButtonsPanel = new JPanel();
			lotesLancesButtonsPanel.setSize(new java.awt.Dimension(452, 34));
			lotesLancesButtonsPanel.setLocation(new java.awt.Point(0, 170));
			lotesLancesButtonsPanel.setLayout(new java.awt.FlowLayout());
			lotesLancesButtonsPanel.add(getListarLancesButton());
			lotesLancesButtonsPanel.add(getVerHistoricoClienteButton());
		}
		return lotesLancesButtonsPanel;
	}

	protected JButton getListarLancesButton() {

		if (listarLancesButton == null) {
			listarLancesButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Listar"));
			listarLancesButton.setSize(new java.awt.Dimension(90, 24));
			listarLancesButton.setLocation(new java.awt.Point(0, 0));
		}
		return listarLancesButton;
	}

	protected JButton getVerHistoricoClienteButton() {

		if (verHistoricoClienteButton == null) {
			verHistoricoClienteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.VerHistóricodoCliente"));
			verHistoricoClienteButton.setSize(new java.awt.Dimension(150, 24));
			verHistoricoClienteButton.setLocation(new java.awt.Point(190, 0));
		}
		return verHistoricoClienteButton;
	}

	protected JPanel getHistoricoLancesCleintesPanel() {

		if (historicoLancesCleintesPanel == null) {
			historicoLancesCleintesPanel = new JPanel();
			historicoLancesCleintesPanel.setSize(new java.awt.Dimension(200,
					200));
			historicoLancesCleintesPanel
					.setLocation(new java.awt.Point(0, 450));
			historicoLancesCleintesPanel.setLayout(null);
			historicoLancesCleintesPanel.add(getHistoricoPartFilterPanel());
			historicoLancesCleintesPanel
					.add(getUsuarioHistoricoLancesTableScrollPane());
			historicoLancesCleintesPanel.add(getHistoricoButtonsPanel());
		}
		return historicoLancesCleintesPanel;
	}

	protected JPanel getHistoricoPartFilterPanel() {

		if (historicoPartFilterPanel == null) {
			historicoPartFilterPanel = new JPanel();
			historicoPartFilterPanel.setSize(new java.awt.Dimension(450, 40));
			historicoPartFilterPanel.setLocation(new java.awt.Point(0, 3));
			historicoPartFilterPanel.setLayout(null);
			historicoPartFilterPanel.add(getLoginTextField());
			historicoPartFilterPanel.add(getLoginTextFieldLabel());
			historicoPartFilterPanel
					.add(getListarHistoricoParticipacaoButton());
		}
		return historicoPartFilterPanel;
	}

	protected JTextField getLoginTextField() {

		if (loginTextField == null) {
			loginTextField = new JTextField();
			loginTextField.setText("");
			loginTextField.setSize(new java.awt.Dimension(150, 20));
			loginTextField.setLocation(new java.awt.Point(50, 0));
			return loginTextField;
		}
		return loginTextField;
	}

	protected JLabel getLoginTextFieldLabel() {

		if (loginTextFieldLabel == null) {
			loginTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Login"));
			loginTextFieldLabel.setSize(new java.awt.Dimension(50, 20));
			loginTextFieldLabel.setLocation(new java.awt.Point(0, 0));
			loginTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return loginTextFieldLabel;
	}

	protected JButton getListarHistoricoParticipacaoButton() {

		if (listarHistoricoParticipacaoButton == null) {
			listarHistoricoParticipacaoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Listar"));
			listarHistoricoParticipacaoButton.setSize(new java.awt.Dimension(
					90, 22));
			listarHistoricoParticipacaoButton.setLocation(new java.awt.Point(
					200, 0));
		}
		return listarHistoricoParticipacaoButton;
	}

	protected JScrollPane getUsuarioHistoricoLancesTableScrollPane() {

		if (usuarioHistoricoLancesTableScrollPane == null) {
			usuarioHistoricoLancesTableScrollPane = new JScrollPane();
			usuarioHistoricoLancesTableScrollPane
					.setSize(new java.awt.Dimension(450, 124));
			usuarioHistoricoLancesTableScrollPane
					.setLocation(new java.awt.Point(0, 46));

			usuarioHistoricoLancesTableScrollPane.add(
					getUsuarioHistoricoLancezTable());
			usuarioHistoricoLancesTableScrollPane
					.setViewportView(getUsuarioHistoricoLancezTable());
		}
		return usuarioHistoricoLancesTableScrollPane;
	}

	protected UsuarioHistoricoLancezTable getUsuarioHistoricoLancezTable() {

		if (usuarioHistoricoLancezTable == null) {
			usuarioHistoricoLancezTable = new UsuarioHistoricoLancezTable();
			usuarioHistoricoLancezTable
					.setSize(new java.awt.Dimension(450, 124));
			return usuarioHistoricoLancezTable;
		}
		return usuarioHistoricoLancezTable;
	}

	protected JPanel getHistoricoButtonsPanel() {

		if (historicoButtonsPanel == null) {
			historicoButtonsPanel = new JPanel();
			historicoButtonsPanel.setSize(new java.awt.Dimension(450, 34));
			historicoButtonsPanel.setLocation(new java.awt.Point(0, 173));
			historicoButtonsPanel.setLayout(new java.awt.FlowLayout());
			historicoButtonsPanel.add(getListarPrefCliente());
			historicoButtonsPanel.add(getVerDadosClienteButton());
		}
		return historicoButtonsPanel;
	}

	protected JButton getListarPrefCliente() {

		if (listarPrefCliente == null) {
			listarPrefCliente = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.ListarasPreferênciasdoCliente"));
			listarPrefCliente.setSize(new java.awt.Dimension(150, 20));
			listarPrefCliente.setLocation(new java.awt.Point(0, 3));
		}
		return listarPrefCliente;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				AgendaListaProjetoDialog gui = new AgendaListaProjetoDialog();
				//gui.setDefaultCloseOperation(javax.swing.JDialog.EXIT_ON_CLOSE);
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

	private class UsuarioHistoricoLancezTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public UsuarioHistoricoLancezTable() {

			super();
			this.setModel(new UsuarioHistoricoLancezTableModel(null));
			this
					.addPropertyChangeListener(new UsuarioHistoricoLancezTablePropertyChangeListener());
		}

		public UsuarioHistoricoLancezTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new UsuarioHistoricoLancezTableModel(null));
			this
					.addPropertyChangeListener(new UsuarioHistoricoLancezTablePropertyChangeListener());
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
				java.lang.Object values[][] = new java.lang.Object[ne][5];
				//int i = 0;
				while (it.hasNext()) {
					//Object obj = it.next();
					/*
					 * if (obj instanceof
					 * com.adapit.portal.entidades.AgendaLote){
					 * com.adapit.portal.entidades.AgendaLote
					 * agendaLote=(com.adapit.portal.entidades.AgendaLote) obj;
					 * i++; }
					 */
				}// End of while Loop
				setModel(new UsuarioHistoricoLancezTableModel(values));
				updateUI();
			} else {
				setModel(new UsuarioHistoricoLancezTableModel(null));
				updateUI();
			}
		}

		private class UsuarioHistoricoLancezTablePropertyChangeListener
				implements PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				AgendaListaProjetoDialog.UsuarioHistoricoLancezTable jt = (AgendaListaProjetoDialog.UsuarioHistoricoLancezTable) e
						.getSource();
				//int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						//java.lang.Object obj = jt.getElements().get(row);
						/*
						 * if (obj instanceof
						 * com.adapit.portal.entidades.AgendaLote){
						 * com.adapit.portal.entidades.AgendaLote
						 * agendaLote=(com.adapit.portal.entidades.AgendaLote)
						 * obj; }
						 */
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class UsuarioHistoricoLancezTableModel extends
				DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, Boolean.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					true };

			public UsuarioHistoricoLancezTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Leilão"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.NúmerodoLote"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Início"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Ganhou"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.ÚltimoLance") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class AgendasTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public AgendasTable() {

			super();
			this.setModel(new AgendasTableModel(null));
			this
					.addPropertyChangeListener(new AgendasTablePropertyChangeListener());
		}

		public AgendasTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new AgendasTableModel(null));
			this
					.addPropertyChangeListener(new AgendasTablePropertyChangeListener());
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
				//int i = 0;
				while (it.hasNext()) {
					//Object obj = it.next();
					/*
					 * if (obj instanceof
					 * com.adapit.portal.entidades.AgendaLote){
					 * com.adapit.portal.entidades.AgendaLote
					 * agendaLote=(com.adapit.portal.entidades.AgendaLote) obj;
					 * values[i][4]=agendaLote.getInicioPrevisto();
					 * values[i][7]=agendaLote.getTerminoPrevisto();
					 * values[i][8]=agendaLote.getInicio();
					 * values[i][9]=agendaLote.getStatus(); i++; }
					 */
				}// End of while Loop
				setModel(new AgendasTableModel(values));
				updateUI();
			} else {
				setModel(new AgendasTableModel(null));
				updateUI();
			}
		}

		private class AgendasTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				AgendaListaProjetoDialog.AgendasTable jt = (AgendaListaProjetoDialog.AgendasTable) e
						.getSource();
				//int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						//java.lang.Object obj = jt.getElements().get(row);
						/*
						 * if (obj instanceof
						 * com.adapit.portal.entidades.AgendaLote){
						 * com.adapit.portal.entidades.AgendaLote
						 * agendaLote=(com.adapit.portal.entidades.AgendaLote)
						 * obj; if (col ==4)
						 * agendaLote.setInicioPrevisto(((java.lang.String)jt.getValueAt(row,
						 * col))); if (col ==7)
						 * agendaLote.setTerminoPrevisto(((java.lang.String)jt.getValueAt(row,
						 * col))); if (col ==8)
						 * agendaLote.setInicio(((java.lang.String)jt.getValueAt(row,
						 * col))); if (col ==9)
						 * agendaLote.setStatus(((java.lang.String)jt.getValueAt(row,
						 * col))); }
						 */
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class AgendasTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, String.class };

			boolean canEdit[] = new boolean[] { true, true, true, true };

			public AgendasTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.InícioPrevisto"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.TérminoPrevisto"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Início"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Estado") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

	private class LancesTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public LancesTable() {

			super();
			this.setModel(new LancesTableModel(null));
			this
					.addPropertyChangeListener(new LancesTablePropertyChangeListener());
		}

		public LancesTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new LancesTableModel(null));
			this
					.addPropertyChangeListener(new LancesTablePropertyChangeListener());
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
				//int i = 0;
				while (it.hasNext()) {
					//Object obj = it.next();
					/*
					 * if (obj instanceof
					 * com.adapit.portal.entidades.AgendaLote){
					 * com.adapit.portal.entidades.AgendaLote
					 * agendaLote=(com.adapit.portal.entidades.AgendaLote) obj;
					 * i++; }
					 */
				}// End of while Loop
				setModel(new LancesTableModel(values));
				updateUI();
			} else {
				setModel(new LancesTableModel(null));
				updateUI();
			}
		}

		private class LancesTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, String.class };

			boolean canEdit[] = new boolean[] { true, true, true, true };

			public LancesTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Número"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.NomedoCliente"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.Hora(hhmmss)"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaListaLoteDialog.ValordoLance") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class LancesTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				AgendaListaProjetoDialog.LancesTable jt = (AgendaListaProjetoDialog.LancesTable) e
						.getSource();
				//int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						//java.lang.Object obj = jt.getElements().get(row);
						/*
						 * if (obj instanceof
						 * com.adapit.portal.entidades.AgendaLote){
						 * com.adapit.portal.entidades.AgendaLote
						 * agendaLote=(com.adapit.portal.entidades.AgendaLote)
						 * obj; }
						 */
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

	}

}