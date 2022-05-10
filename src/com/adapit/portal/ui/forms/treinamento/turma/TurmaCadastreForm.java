package com.adapit.portal.ui.forms.treinamento.turma;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ClassificacaoTreinamento;
import com.adapit.portal.entidades.CondicaoParticipacaoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Instrutor;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.TipoExecucaoTreinamento;
import com.adapit.portal.entidades.TipoPacoteTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.services.remote.RemoteUserService;
import com.adapit.portal.ui.forms.endereco.EnderecoCadastreDialog;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.AutoCompletion;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class TurmaCadastreForm extends JPanel {

	private JTabbedPane tabbedPane;

	private JPanel baseTab;

	private JPanel instrutorPanel;

	private JComboBox instrutorComboBox;

	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();

	private JLabel instrutorComboBoxLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel instrutorButtonsPanel;

	private JButton editarInstrutorButton;

	private JButton novoInstrutorButton;

	private JPanel formacaoPanel;

	private JComboBox formacaoComboBox;

	private JLabel formacaoLabel;

	private JPanel formacaoButtonsPanel;

	private JButton editarFormacaoButton;

	private JButton novaFormacaoButton;

	private JPanel dataHoraPanel;

	private TurmaTreinamento turma = new TurmaTreinamento();  //  @jve:decl-index=0:

	private DateHourChooser dataTreinamentoField;

	private JLabel dataTreinamentoLabel;

	private JPanel procedenciaTipoLeilaoPanel;

	private JComboBox classificacaoComboBox;

	private JLabel tipoTreinamentoLabel;

	private JComboBox realizacaoComboBox;

	private JLabel realizacaoLabel;

	private JLabel executarLeilaoComoComboBoxLabel;

	private JPanel condPartTab;

	private JTextField descricaoCondicaoTextField;

	private CondicaoParticipacaoTreinamento condPartLeilao = new CondicaoParticipacaoTreinamento();  //  @jve:decl-index=0:

	private JLabel descricaoCondicaoTextFieldLabel;

	private JScrollPane codicoesParticipacaoScrollPane;

	private JTextArea condicaoParticipacaoTextArea;

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel condicaoParticipacaoTextAreaLabel;

	private JPanel condPartButtonsPanel;

	private JButton novaCondicaoButton;

	private JButton salvarCondicaoButton;

	private JPanel treinamentoAgendasTab;

	private JPanel agendasTurmaPanel;

	private JScrollPane agendasTurmasScrollPane;

	private AgendasTurmaTable agendasTurmasTable;

	private JPanel lotesEmLeilaoButtonsListPanel;

	private JButton novoLoteButton;

	private JButton removelotesemleilaoButton;

	private JButton editarLoteButton;

	private JPanel treinamentosSearchButtonsPanel;

	private JButton listarTreinamentosButton;

	private JButton definirAgendaTreinamentoButton;

	private JPanel buttonsPanel;

	private JButton cadastrarButton;

	private JButton novoButton;

	//private JButton removerButton;

	private JLabel localPresencialLabel = null;

	private JComboBox executarComoComboBox = null;
	
	private JComboBox turnoTreinamentoComboBox = null;

	private JComboBox enderecoComboBox = null;

	private JPanel enderecoButtonsPanel = null;

	private JButton editarLocalButton = null;

	private JButton novoLocalButton = null;

	private JButton refreshInstrutorButton = null;

	private JButton refreshFormacoesButton = null;

	private JButton refreshEnderecoButton = null;

	public TurmaCadastreForm() {
		initialize();
		updateInstrutorList();
		updateFormacoesList();
		updateEnderecoList();
		updateCondicaoParticipacaoList();
	}

	private void initialize() {
		setSize(new Dimension(648, 466));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getTabbedPane());
		newRegister();
		this.setErrorIcon(false);
		// End

		this.add(getErrorPanel(), null);
	}

	private void newCondicaoParticipacao() {
		condPartLeilao.setId(0);
		condPartLeilao.setTexto(null);
		condPartLeilao.setDescricao(null);
		getDescricaoCondicaoTextField().setText("");
		condicaoParticipacaoTextArea.setText("");
		
	}
	protected JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(639, 382));
			tabbedPane.setLocation(new java.awt.Point(0, 3));
			tabbedPane.add(getBaseTab(), "Dados do Treinamento");
			tabbedPane.add(getCondPartTab(),"Instruções de Participação");
			tabbedPane.add(getTreinamentoAgendasTab(),"Lista de Interessados");			
		}
		return tabbedPane;
	}

	protected JPanel getBaseTab() {

		if (baseTab == null) {
			instrucoesAosParticipantesLabel = new JLabel();
			instrucoesAosParticipantesLabel.setBounds(new Rectangle(11, 85, 334, 19));
			instrucoesAosParticipantesLabel.setText("Instruções aos Participantes:");
			localPresencialLabel = new JLabel();
			localPresencialLabel.setBounds(new Rectangle(11, 61, 104, 22));
			localPresencialLabel.setText("Local:");
			baseTab = new JPanel();
			baseTab.setSize(new java.awt.Dimension(573, 271));
			baseTab.setLocation(new java.awt.Point(0, 3));
			baseTab.setLayout(null);
			baseTab.add(getInstrutorPanel());
			baseTab.add(getFormacaoPanel());
			baseTab.add(getDataHoraPanel());
			baseTab.add(localPresencialLabel, null);
			baseTab.add(getEnderecoComboBox(), null);
			baseTab.add(getEnderecoButtonsPanel(), null);
			baseTab.add(getRefreshEnderecoButton(), null);
			baseTab.add(getButtonsPanel(), null);
			baseTab.add(instrucoesAosParticipantesLabel, null);
			baseTab.add(getInstrucoesParticipantesScrollPane(), null);
			baseTab.add(getImageMainPanel(), null);
		}
		return baseTab;
	}

	protected JPanel getInstrutorPanel() {
		if (instrutorPanel == null) {
			instrutorPanel = new JPanel();
			instrutorPanel.setSize(new Dimension(614, 22));
			instrutorPanel.setLocation(new java.awt.Point(11, 13));
			instrutorPanel.setLayout(null);
			instrutorPanel.add(getInstrutorComboBox());
			instrutorPanel.add(getInstrutorComboBoxLabel());
			instrutorPanel.add(getInstrutorButtonsPanel());
			instrutorPanel.add(getRefreshInstrutorButton(), null);
		}
		return instrutorPanel;
	}

	protected JComboBox getInstrutorComboBox() {
		if (instrutorComboBox == null) {
			instrutorComboBox = new JComboBox();
			instrutorComboBox.setSize(new Dimension(230, 22));
			instrutorComboBox.setLocation(new java.awt.Point(105, 0));
			instrutorComboBox.setEditable(true);
			AutoCompletion.enable(instrutorComboBox);
			return instrutorComboBox;
		}
		return instrutorComboBox;
	}

	protected JLabel getInstrutorComboBoxLabel() {
		if (instrutorComboBoxLabel == null) {
			instrutorComboBoxLabel = new JLabel("Instrutor:");
			instrutorComboBoxLabel.setSize(new java.awt.Dimension(100, 22));
			instrutorComboBoxLabel.setLocation(new java.awt.Point(0, 0));
			instrutorComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return instrutorComboBoxLabel;
	}

	protected JPanel getInstrutorButtonsPanel() {
		if (instrutorButtonsPanel == null) {
			instrutorButtonsPanel = new JPanel();
			instrutorButtonsPanel.setSize(new Dimension(235, 22));
			instrutorButtonsPanel.setLocation(new Point(375, 0));
			instrutorButtonsPanel.setLayout(new java.awt.GridLayout(1,2));
			instrutorButtonsPanel.add(getEditarInstrutorButton());
			instrutorButtonsPanel.add(getNovoInstrutorButton());
		}
		return instrutorButtonsPanel;
	}

	protected JButton getEditarInstrutorButton() {
		if (editarInstrutorButton == null) {
			editarInstrutorButton = new JButton("Editar Instrutor");
			editarInstrutorButton.setSize(new java.awt.Dimension(150, 20));
			editarInstrutorButton.setLocation(new java.awt.Point(0, 3));
			editarInstrutorButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (instrutors != null && instrutors.size()>0){
						int index = getInstrutorComboBox().getSelectedIndex();
						Instrutor l = (Instrutor) instrutors.get(index);
						AdapitVirtualFrame.getInstance().editarInstrutor(l);
						updateInstrutorList();
					}
				}
			});
		}
		return editarInstrutorButton;
	}

	protected JButton getNovoInstrutorButton() {
		if (novoInstrutorButton == null) {
			novoInstrutorButton = new JButton("Novo Instrutor");
			novoInstrutorButton.setSize(new java.awt.Dimension(150, 20));
			novoInstrutorButton.setLocation(new java.awt.Point(0, 26));
			novoInstrutorButton.addActionListener(new AbstractAction(){
				@Override
				public void doAction(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarInstrutor();
					updateInstrutorList();
				}				
			});
		}
		return novoInstrutorButton;
	}

	protected JPanel getFormacaoPanel() {
		if (formacaoPanel == null) {
			formacaoPanel = new JPanel();
			formacaoPanel.setSize(new Dimension(613, 22));
			formacaoPanel.setLocation(new java.awt.Point(11, 37));
			formacaoPanel.setLayout(null);
			formacaoPanel.add(getFormacaoComboBox());
			formacaoPanel.add(getFormacaoLabel());
			formacaoPanel.add(getFormacaoButtonsPanel());
			formacaoPanel.add(getRefreshFormacoesButton(), null);
		}
		return formacaoPanel;
	}

	protected JComboBox getFormacaoComboBox() {
		if (formacaoComboBox == null) {
			formacaoComboBox = new JComboBox();
			formacaoComboBox.setSize(new Dimension(230, 22));
			formacaoComboBox.setEditable(true);
			formacaoComboBox.setLocation(new java.awt.Point(105, 0));
			AutoCompletion.enable(formacaoComboBox);			
			return formacaoComboBox;
		}
		return formacaoComboBox;
	}

	protected JLabel getFormacaoLabel() {
		if (formacaoLabel == null) {
			formacaoLabel = new JLabel("Formação:");
			formacaoLabel.setSize(new Dimension(103, 22));
			formacaoLabel.setLocation(new java.awt.Point(0, 0));
			formacaoLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return formacaoLabel;
	}

	protected JPanel getFormacaoButtonsPanel() {
		if (formacaoButtonsPanel == null) {
			formacaoButtonsPanel = new JPanel();
			formacaoButtonsPanel.setSize(new Dimension(235, 22));
			formacaoButtonsPanel.setLocation(new Point(375, 0));
			formacaoButtonsPanel.setLayout(new java.awt.GridLayout(1,2));
			formacaoButtonsPanel.add(getEditarFormacaoButton());
			formacaoButtonsPanel.add(getNovaFormacaoButton());
		}
		return formacaoButtonsPanel;
	}

	protected JButton getEditarFormacaoButton() {
		if (editarFormacaoButton == null) {
			editarFormacaoButton = new JButton("Editar Formação");
			editarFormacaoButton.setSize(new java.awt.Dimension(150, 20));
			editarFormacaoButton.setLocation(new java.awt.Point(0, 3));
			editarFormacaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (formacoes != null && formacoes.size()>0){
						int index = getFormacaoComboBox().getSelectedIndex();
						FormacaoTreinamento l = (FormacaoTreinamento) formacoes.get(index);
						AdapitVirtualFrame.getInstance().editarFormacao(l);						
						updateFormacoesList();
					}
				}
			});
		}
		return editarFormacaoButton;
	}

	protected JButton getNovaFormacaoButton() {
		if (novaFormacaoButton == null) {
			novaFormacaoButton = new JButton("Nova Formação");
			novaFormacaoButton.setSize(new java.awt.Dimension(150, 20));
			novaFormacaoButton.setLocation(new java.awt.Point(0, 26));
			novaFormacaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().novaFormacao();
					updateFormacoesList();
				}
			});
		}
		return novaFormacaoButton;
	}

	protected JPanel getDataHoraPanel() {
		if (dataHoraPanel == null) {
			dataFinalTreinamentoLabel = new JLabel();
			dataFinalTreinamentoLabel.setBounds(new Rectangle(0, 25, 143, 22));
			dataFinalTreinamentoLabel.setText("Encerramento Previsto:");
			numeroMinimoParticpantesLabel = new JLabel();
			numeroMinimoParticpantesLabel.setBounds(new Rectangle(340, 25, 204, 20));
			numeroMinimoParticpantesLabel.setText("Número Mínimo de Participantes:");
			numeroMaxParticipantesLabel = new JLabel();
			numeroMaxParticipantesLabel.setBounds(new Rectangle(340, 0, 204, 20));
			numeroMaxParticipantesLabel.setText("Número Máximo de Participantes:");
			dataHoraPanel = new JPanel();
			dataHoraPanel.setSize(new Dimension(612, 107));
			dataHoraPanel.setLocation(new Point(12, 201));
			dataHoraPanel.setLayout(null);
			dataHoraPanel.add(getDataTreinamentoField());
			dataHoraPanel.add(getDataTreinamentoLabel());
			dataHoraPanel.add(numeroMaxParticipantesLabel, null);
			dataHoraPanel.add(getNumeroParticipantesTextField(), null);
			dataHoraPanel.add(numeroMinimoParticpantesLabel, null);
			dataHoraPanel.add(getNumeroMinimoParticipantesTextField(), null);
			dataHoraPanel.add(dataFinalTreinamentoLabel, null);
			dataHoraPanel.add(getEncerramentoPrevistoField(), null);
			dataHoraPanel.add(getClassifSubClassifRegrasComLeilaoPanel(), null);
			dataHoraPanel.add(getExecutarLeilaoComoComboBoxLabel(), null);
			dataHoraPanel.add(getExecutarComoComboBox(), null);
			dataHoraPanel.add(getRegrasComitentesLabel(), null);
			dataHoraPanel.add(getTurnoTreinamentoComboBox(), null);
		}
		return dataHoraPanel;
	}

	

	@SuppressWarnings("unchecked")
	protected JComponent getDataTreinamentoField() {
		if (dataTreinamentoField == null) {
			dataTreinamentoField = new DateHourChooser(messages
					.getCurrentLocale(), true, true, false);
			dataTreinamentoField.setSize(new Dimension(134, 22));
			dataTreinamentoField.setLocation(new Point(160, 0));
			this.binder.addBindProperty(this.turma, this.dataTreinamentoField, "dataTreinamento");

			this.hashComps.put("dataTreinamento", this.dataTreinamentoField);
			JWarningComponent warn = new JWarningComponent(this.dataTreinamentoField);
			warn.setBounds(160, 0, 134, 22);
			return warn;
		}
		return dataTreinamentoField;
	}

	protected JLabel getDataTreinamentoLabel() {
		if (dataTreinamentoLabel == null) {
			dataTreinamentoLabel = new JLabel("Data do Treinamento:");
			dataTreinamentoLabel.setSize(new Dimension(143, 22));
			dataTreinamentoLabel.setLocation(new java.awt.Point(0, 0));
			dataTreinamentoLabel
					.setHorizontalAlignment(JLabel.LEFT);
		}
		return dataTreinamentoLabel;
	}

	protected JPanel getClassifSubClassifRegrasComLeilaoPanel() {
		if (procedenciaTipoLeilaoPanel == null) {
			procedenciaTipoLeilaoPanel = new JPanel();
			procedenciaTipoLeilaoPanel.setLayout(null);
			procedenciaTipoLeilaoPanel.setBounds(new Rectangle(0, 50, 612, 22));
			procedenciaTipoLeilaoPanel.add(getClassificacaoComboBox());
			procedenciaTipoLeilaoPanel.add(getTipoTreinamentoLabel());
			procedenciaTipoLeilaoPanel.add(getRealizacaoComboBox());
			procedenciaTipoLeilaoPanel.add(getRealizacaoLabel());
			procedenciaTipoLeilaoPanel.add(getRegrasComitentesLabel());
		}
		return procedenciaTipoLeilaoPanel;
	}

	protected JComboBox getClassificacaoComboBox() {
		if (classificacaoComboBox == null) {
			classificacaoComboBox = new JComboBox();
			classificacaoComboBox.setSize(new java.awt.Dimension(170, 22));
			classificacaoComboBox.setLocation(new java.awt.Point(105, 0));
			for (int i=0; i < ClassificacaoTreinamento.values().length;i++){
				classificacaoComboBox.addItem(ClassificacaoTreinamento.values()[i].name().replace("_", " "));
			}
			classificacaoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						updateSubclassificacaoLeilaoComboBox(ClassificacaoTreinamento.valueOf(((String)classificacaoComboBox.getSelectedItem()).replace(" ", "_")));
					}
				}				
			});
			
			return classificacaoComboBox;
		}
		return classificacaoComboBox;
	}

	protected JLabel getTipoTreinamentoLabel() {
		if (tipoTreinamentoLabel == null) {
			tipoTreinamentoLabel = new JLabel("Tipo:");
			tipoTreinamentoLabel.setSize(new java.awt.Dimension(100, 22));
			tipoTreinamentoLabel.setLocation(new java.awt.Point(0, 0));
			tipoTreinamentoLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return tipoTreinamentoLabel;
	}

	protected JComboBox getRealizacaoComboBox() {

		if (realizacaoComboBox == null) {
			realizacaoComboBox = new JComboBox();
			realizacaoComboBox.setSize(new java.awt.Dimension(230, 22));
			realizacaoComboBox.setLocation(new java.awt.Point(380, 0));
			for (int i=0; i < TipoPacoteTreinamento.values().length;i++){
				realizacaoComboBox.addItem(TipoPacoteTreinamento.values()[i].name().replace("_", " "));
			}
			
			return realizacaoComboBox;
		}
		return realizacaoComboBox;
	}
	
	private void updateSubclassificacaoLeilaoComboBox(ClassificacaoTreinamento proc){
		realizacaoComboBox.removeAllItems();
		if (proc == ClassificacaoTreinamento.In_Company){
			for (int i=0; i < TipoPacoteTreinamento.values().length;i++){
				if (TipoPacoteTreinamento.values()[i] != TipoPacoteTreinamento.Formação_Própria
						&& TipoPacoteTreinamento.values()[i] != TipoPacoteTreinamento.Formação_Empresa_Parceira)
					realizacaoComboBox.addItem(TipoPacoteTreinamento.values()[i].name().replace("_", " "));
			}
			getTabbedPane().add(getRequerimentoTreinamentoPanel(),"Requerimento de Treinamento");
			try {
				turnoTreinamentoComboBox.removeItem(TurnoTreinamento.Noite.name().replace("_"," "));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			realizacaoComboBox.addItem(TipoPacoteTreinamento.Formação_Própria.name());
			realizacaoComboBox.addItem(TipoPacoteTreinamento.Formação_Empresa_Parceira.name().replace("_", " "));
			if (turnoTreinamentoComboBox.getItemCount()<3)
				turnoTreinamentoComboBox.addItem(TurnoTreinamento.Noite.name().replace("_"," "));			
			getTabbedPane().remove(getRequerimentoTreinamentoPanel());
		}
	}
	
	private void updateRegraLeilao(){		
		if (turma.getTurno() == TurnoTreinamento.Noite){			
			updateDatasMaisQueUmLeilao();
		}else if (turma.getTurno() == TurnoTreinamento.Tarde){			
			updateDatasMaisQueUmLeilao();
		}else{
			updateDatasUmLeilao();
		}
	}
	
	private void updateRegraTurmasByState(){
		String str = (String) turnoTreinamentoComboBox.getSelectedItem();
		TurnoTreinamento regra= TurnoTreinamento.valueOf(str.replace(" ", "_"));
		if (regra == TurnoTreinamento.Noite){
			updateDatasMaisQueUmLeilaoByState();
		}else if (regra == TurnoTreinamento.Tarde){
			updateDatasMaisQueUmLeilaoByState();
		}else{
			updateDatasUmLeilaoByState();
		}
	}
	
	private void updateDatasUmLeilao(){
		dataTreinamentoField.getCalendarButton().setEnabled(true);		
	}
	
	private void updateDatasUmLeilaoByState(){
		String str = (String) executarComoComboBox.getSelectedItem();
		TipoExecucaoTreinamento regra= TipoExecucaoTreinamento.valueOf(str.replace(" ", "_"));
		dataTreinamentoField.getCalendarButton().setEnabled(true);		
	}
	
	private void updateDatasMaisQueUmLeilao(){
		dataTreinamentoField.getCalendarButton().setEnabled(true);		
	}
	
	private void updateDatasMaisQueUmLeilaoByState(){
		String str = (String) executarComoComboBox.getSelectedItem();
		TipoExecucaoTreinamento regra= TipoExecucaoTreinamento.valueOf(str.replace(" ", "_"));
		dataTreinamentoField.getCalendarButton().setEnabled(true);		
	}

	
	protected ProcessoRequerimentoTreinamentoPanel requerimentoTreinamentoPanel;  //  @jve:decl-index=0:visual-constraint="10,486"
	
	public ProcessoRequerimentoTreinamentoPanel getRequerimentoTreinamentoPanel(){
		if (requerimentoTreinamentoPanel == null){
			requerimentoTreinamentoPanel = new ProcessoRequerimentoTreinamentoPanel();			
			requerimentoTreinamentoPanel.setSize(new Dimension(487, 373));
			requerimentoTreinamentoPanel.setTurma(turma);
		}
		if (turma.getProcesso() != null) requerimentoTreinamentoPanel.editRegister(turma.getProcesso());
		return requerimentoTreinamentoPanel;
	}
	
	private JLabel turnoTreinamentos;
	protected JLabel getRegrasComitentesLabel() {
		if (turnoTreinamentos == null) {
			turnoTreinamentos = new JLabel();
			turnoTreinamentos.setText("Turno:");
			turnoTreinamentos.setBounds(new Rectangle(282, 75, 90, 22));
			turnoTreinamentos.setHorizontalAlignment(JLabel.LEFT);
		}
		return turnoTreinamentos;
	}
	

	protected JLabel getRealizacaoLabel() {
		if (realizacaoLabel == null) {
			realizacaoLabel = new JLabel("Realização:");
			realizacaoLabel.setSize(new java.awt.Dimension(90, 22));
			realizacaoLabel.setLocation(new java.awt.Point(282, 0));
			realizacaoLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return realizacaoLabel;
	}

	protected JLabel getExecutarLeilaoComoComboBoxLabel() {
		if (executarLeilaoComoComboBoxLabel == null) {
			executarLeilaoComoComboBoxLabel = new JLabel("Executar Como");
			executarLeilaoComoComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
			executarLeilaoComoComboBoxLabel.setBounds(new Rectangle(0, 75, 100, 22));
		}
		return executarLeilaoComoComboBoxLabel;
	}

	protected JPanel getCondPartTab() {
		if (condPartTab == null) {
			condDesLabel = new JLabel();
			condDesLabel.setBounds(new Rectangle(10, 10, 137, 22));
			condDesLabel.setText("Selecione a Condição:");
			condPartTab = new JPanel();
			condPartTab.setSize(new java.awt.Dimension(575, 271));
			condPartTab.setLocation(new java.awt.Point(0, 277));
			condPartTab.setLayout(null);
			condPartTab.add(getDescricaoCondicaoTextField());
			condPartTab.add(getDescricaoCondicaoTextFieldLabel());
			condPartTab.add(getCodicoesParticipacaoScrollPane());
			condPartTab.add(getCondPartButtonsPanel());
			condPartTab.add(condDesLabel, null);
			condPartTab.add(getCondicoesCadastradasComboBox(), null);
			condPartTab.add(getAtualizarCondicaoPartButton(), null);
		}
		return condPartTab;
	}

	protected JTextField getDescricaoCondicaoTextField() {
		if (descricaoCondicaoTextField == null) {
			descricaoCondicaoTextField = new JTextField();
			descricaoCondicaoTextField.setText("");
			descricaoCondicaoTextField.setSize(new Dimension(467, 20));
			descricaoCondicaoTextField.setEnabled(false);
			descricaoCondicaoTextField.setLocation(new Point(153, 36));
			return descricaoCondicaoTextField;
		}
		return descricaoCondicaoTextField;
	}

	protected JLabel getDescricaoCondicaoTextFieldLabel() {
		if (descricaoCondicaoTextFieldLabel == null) {
			descricaoCondicaoTextFieldLabel = new JLabel("Condição:");
			descricaoCondicaoTextFieldLabel.setSize(new Dimension(137, 20));
			descricaoCondicaoTextFieldLabel.setLocation(new Point(10, 36));
			descricaoCondicaoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return descricaoCondicaoTextFieldLabel;
	}

	protected JScrollPane getCodicoesParticipacaoScrollPane() {
		if (codicoesParticipacaoScrollPane == null) {
			codicoesParticipacaoScrollPane = new JScrollPane();
			codicoesParticipacaoScrollPane.setSize(new Dimension(609, 137));
			codicoesParticipacaoScrollPane.setLocation(new Point(10, 60));

			getCondicaoParticipacaoTextArea();
			codicoesParticipacaoScrollPane.setViewportView(getCondicaoParticipacaoTextArea());
			
		}
		return codicoesParticipacaoScrollPane;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getCondicaoParticipacaoTextArea() {
		if (condicaoParticipacaoTextArea == null) {
			condicaoParticipacaoTextArea = new JTextArea();
			condicaoParticipacaoTextArea.setSize(new java.awt.Dimension(572,
					122));
			condicaoParticipacaoTextArea.setEnabled(false);
			condicaoParticipacaoTextArea.setLocation(new java.awt.Point(0, 0));
			
			this.hashComps.put("texto", this.condicaoParticipacaoTextArea);
			JWarningComponent warn = new JWarningComponent(
					this.condicaoParticipacaoTextArea);
			warn.setBounds(0, 0, 572, 122);
			return warn;
		}
		return condicaoParticipacaoTextArea;
	}

	public TurmaTreinamento validateTurmaForm() throws Exception {
		setErrorIcon(false);
		clearErrorList();
		boolean invalid=false;
		Instrutor l = null;
		if (instrutors.size()>0) l = (Instrutor) instrutors.get(getInstrutorComboBox().getSelectedIndex());
		if (l == null){
			invalid=true;
			reportInvalidField("turmaTreinamento.instrutor","Você deve selecionar um instrutor");
		}
		else{
			try {
				l = RemotePessoaService.getInstance().getInstrutor(l.getId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
		}
		FormacaoTreinamento c = null;
		if (getFormacaoComboBox().getSelectedIndex() != -1){
			if (formacoes.size()>0) c = (FormacaoTreinamento) formacoes.get(getFormacaoComboBox().getSelectedIndex());
		}else{
			if (formacoes.size()>0) c = (FormacaoTreinamento) formacoes.get(0);
		}
		if (c == null){
			invalid=true;
			reportInvalidField("turmaTreinamento.formacao","Você deve selecionar uma formacao");
		}else{
			try {
				c = RemoteTreinamentoService.getInstance().getFormacaoById(c.getId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
		}		
		Endereco e = null;
		if (getEnderecoComboBox().getSelectedIndex() != -1){
			if (enderecos.size() > 0) e = (Endereco) enderecos.get(getEnderecoComboBox().getSelectedIndex());
		}else{
			if (enderecos.size() > 0) e = (Endereco) enderecos.get(0);
		}
		if (e == null){
			invalid=true;
			reportInvalidField("turmaTreinamento.endereco","Você deve selecionar um endereço");

		}
		else{
			try {
				e = RemoteTurmaService.getInstance().loadAddress(e.getId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		CondicaoParticipacaoTreinamento cond = null;
		if (getCondicoesCadastradasComboBox().getSelectedIndex() != -1){
			if (condicoes.size() >0) cond = (CondicaoParticipacaoTreinamento) condicoes.get(getCondicoesCadastradasComboBox().getSelectedIndex());
		}else{
			if (condicoes.size() >0) cond = (CondicaoParticipacaoTreinamento) condicoes.get(0);
		}
		if (cond == null){
			invalid=true;
			reportInvalidField("turmaTreinamento.condicaoParticipacaoTreinamento","Você deve selecionar uma condição de participação");
			
		}
		else{
			try {
				cond = RemoteTurmaService.getInstance().loadTrainingClassParticipationConditionByTrainingClassId(cond.getId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		turma.setInstrutor(l);
		turma.setFormacao(c);
		turma.setEnderecoPresencial(e);
		turma.setCondicaoParticipacao(cond);
		
		ClassificacaoTreinamento cls = ClassificacaoTreinamento.valueOf(((String)getClassificacaoComboBox().getSelectedItem()).replace(" ", "_"));
		TipoPacoteTreinamento sub = TipoPacoteTreinamento.valueOf(((String)getRealizacaoComboBox().getSelectedItem()).replace(" ", "_"));
		TipoExecucaoTreinamento tipo = TipoExecucaoTreinamento.valueOf(((String) getExecutarComoComboBox().getSelectedItem()).replace(" ", "_"));
		TurnoTreinamento regra = TurnoTreinamento.valueOf(((String) getTurnoTreinamentoComboBox().getSelectedItem()).replace(" ", "_"));
		
		turma.setClassificacao(cls);
		turma.setSubClassificacao(sub);
		turma.setTipoExecucao(tipo);
		turma.setTurno(regra);
		
		Date presencial1 = ((DateHourChooser)getDataTreinamentoField()).getDate();
		if (presencial1 == null){
			invalid=true;
			reportInvalidField("dataTreinamento","A data do treinamento é um campo obrigatório");
		}	
		turma.setDataTreinamento(presencial1);
			
		
		turma.setInstrucoesParticipantes(instrucoesParticipantesTextPane.getText());
		turma.setNumeroMaximoParticipantes(Integer.parseInt(numeroParticipantesTextField.getText()));
		turma.setNumeroMinimoParticipantes(Integer.parseInt(numeroMinimoParticipantesTextField.getText()));		
		turma.setDataTreinamento(dataTreinamentoField.getDate());
		turma.setDataEncerramento(encerramentoPrevistoField.getDate());
		
		if (turma.getDataEncerramento() == null || turma.getDataTreinamento() == null){
			invalid=true;
			reportInvalidField("dataTreinamento","A data do treinamento é um campo obrigatório");
			reportInvalidField("dataEncerramento","A data de encerramento é um campo obrigatório");
		}
		
		if (invalid){
			showErrorList();
			throw new Exception("Bean Not Validated!");
		}
		return turma;
	}

	public TurmaTreinamento cadastreTurma() throws Exception {
		TurmaTreinamento l = validateTurmaForm();		
		try {
			TurmaTreinamento turma = RemoteTurmaService.getInstance().saveOrUpdate(l);
			l.setId(turma.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return l;
	}

	private void showErrorList(){		
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
	}
	
	private void clearErrorList(){
		getErrorPanel().removeAllElements();
		getErrorPanel().setVisible(false);
	}
	
	private void reportInvalidField(String name, String errorMsg){
		JComponent comp = (JComponent) this.hashComps.get(name);
		if (comp != null) {
			comp.firePropertyChange("warnBorder", false, true);
			String msg = "turmaTreinamento."+name;
			try {
				getErrorPanel()
							.addError(messages.getMessage(msg)+" " + errorMsg, comp);
				comp.setToolTipText(messages.getMessage(msg)+" " + errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else{
			String msg = "turmaTreinamento."+name;
			try {
				getErrorPanel().addError(messages.getMessage(msg)+" " + errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public CondicaoParticipacaoTreinamento validateCondicaoParticipacaoLeilaoForm()
			throws Exception {
		setErrorIcon(false);
		
		condPartLeilao.setDescricao(getDescricaoCondicaoTextField().getText());
		condPartLeilao.setTexto(condicaoParticipacaoTextArea.getText());
		
		if (!validateCondicaoParticipacaoLeilaoBean())
			throw new Exception("Bean Not Validated!");
		return condPartLeilao;
	}

	public CondicaoParticipacaoTreinamento cadastreCondicaoParticipacaoLeilao()
			throws Exception {
		return validateCondicaoParticipacaoLeilaoForm();
	}

	@SuppressWarnings("unchecked")
	public boolean validateCondicaoParticipacaoLeilaoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.condPartLeilao,
				"condicaoParticipacaoLeilao");
		if (errors == null)
			return true;
		Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
		String name;
		JComponent comp;
		for (int i = 0; i < entrys.length; i++) {
			name = (String) entrys[i].getKey();
			comp = (JComponent) this.hashComps.get(name);
			if (comp != null) {
				comp.firePropertyChange("warnBorder", false, true);
				Object[] obj = (Object[]) entrys[i].getValue();
				String msg = (String) obj[0];
				if (obj[1] == null) {
					try {
						getErrorPanel()
								.addError(messages.getMessage(msg), comp);
						comp.setToolTipText(messages.getMessage(msg));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					List args = (List) obj[1];
					Object[][] params = new Object[args.size()][2];
					for (int j = 0; j < args.size(); j++) {
						String key = (String) args.get(j);
						params[j][0] = key;
						params[j][1] = null;
					}
					try {
						getErrorPanel().addError(
								messages.getMessage(msg, params), comp);
						comp.setToolTipText(messages.getMessage(msg, params));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}

	public void newRegister() {
		// Nunca definir um novo objeto entidade!!!
		
		turma.setInstrutor(null);
		turma.setComitente(null);
		turma.setFormacao(null);
		turma.setId(0);
		turma.setDataTreinamento(null);
		turma.setEnderecoPresencial(null);
		turma.setProcesso(null);
		turma.setDataEncerramento(null);
		turma.setDataTreinamento(null);
		turma.setNumeroMaximoParticipantes(0);
		turma.setNumeroMinimoParticipantes(0);
		turma.setInstrucoesParticipantes(null);

		binder.reverseBind(this.turma);
		
		condPartLeilao.setId(0);
		condPartLeilao.setTexto(null);
		condPartLeilao.setDescricao(null);

		binder.reverseBind(this.condPartLeilao);
		
		try {
			tabbedPane.remove(getTreinamentoAgendasTab());
		} catch (Exception e) {}
		
		this.setErrorIcon(false);

		classificacaoComboBox.setSelectedIndex(1);
		updateSubclassificacaoLeilaoComboBox(ClassificacaoTreinamento.Turma_Fechada);
		
		
		updateUI();
	}

	public void editRegister(CondicaoParticipacaoTreinamento objCondicao) {
		// Nunca passar como argumento novos objetos!!!
		try {
			condPartLeilao.setId(objCondicao.getId());
			condicaoParticipacaoTextArea.setText(objCondicao.getTexto());
			descricaoCondicaoTextField.setText(objCondicao.getDescricao());			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		binder.reverseBind(this.condPartLeilao);
		
		this.setErrorIcon(false);
	}
	
	private void editRegister(){
		try {
			tabbedPane.remove(getTreinamentoAgendasTab());
		} catch (Exception e) {}
		tabbedPane.add(getTreinamentoAgendasTab(), "Treinamentos da Agenda");
		updateSubclassificacaoLeilaoComboBox(turma.getClassificacao());
		updateRegraLeilao();
		classificacaoComboBox.setSelectedItem(turma.getClassificacao().name().replaceAll("_", " "));
		realizacaoComboBox.setSelectedItem(turma.getSubClassificacao().name().replaceAll("_", " "));
		executarComoComboBox.setSelectedItem(turma.getTipoExecucao().name().replaceAll("_", " "));
		turnoTreinamentoComboBox.setSelectedItem(turma.getTurno().name().replaceAll("_", " "));
		
		encerramentoPrevistoField.setDate(turma.getDataEncerramento());
		dataTreinamentoField.setDate(turma.getDataTreinamento());
		numeroMinimoParticipantesTextField.setText(""+turma.getNumeroMinimoParticipantes());
		numeroParticipantesTextField.setText(""+turma.getNumeroMaximoParticipantes());
		instrucoesParticipantesTextPane.setText(turma.getInstrucoesParticipantes());
	}
		
	public void editRegister(TurmaTreinamento objTurma){		
		try {
			objTurma = RemoteTurmaService.getInstance().loadTrainingClassCascading(objTurma);
			getInstrutorComboBox().setSelectedItem(objTurma.getInstrutor().getNome());
			getFormacaoComboBox().setSelectedItem(objTurma.getFormacao().getNome());
			getEnderecoComboBox().setSelectedItem(objTurma.getEnderecoPresencial().getCidade()+" ("+objTurma.getEnderecoPresencial().getRua()+")");
			getCondicoesCadastradasComboBox().setSelectedItem(objTurma.getCondicaoParticipacao().getDescricao());
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		turma.setInstrutor(objTurma.getInstrutor());
		turma.setFormacao(objTurma.getFormacao());
		turma.setEnderecoPresencial(objTurma.getEnderecoPresencial());
		turma.setCondicaoParticipacao(objTurma.getCondicaoParticipacao());
		turma.setClassificacao(objTurma.getClassificacao());
		turma.setSubClassificacao(objTurma.getSubClassificacao());
		turma.setTipoExecucao(objTurma.getTipoExecucao());
		turma.setDataTreinamento(objTurma.getDataTreinamento());
		turma.setId(objTurma.getId());
		turma.setProcesso(objTurma.getProcesso());
		turma.setTurno(objTurma.getTurno());
		
		turma.setDataEncerramento(objTurma.getDataEncerramento());
		turma.setDataTreinamento(objTurma.getDataTreinamento());
		turma.setNumeroMaximoParticipantes(objTurma.getNumeroMaximoParticipantes());
		turma.setNumeroMinimoParticipantes(objTurma.getNumeroMinimoParticipantes());
		turma.setInstrucoesParticipantes(objTurma.getInstrucoesParticipantes());
		
		if (objTurma.getDataTreinamento() != null && dataTreinamentoField != null)
			dataTreinamentoField.setDate(objTurma.getDataTreinamento());
		
		getSalvarCondicaoButton().setText("Atualizar");
		
		editRegister();
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(1, 387, 634, 73));
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {

		this.condicaoParticipacaoTextArea.firePropertyChange("warnBorder",!bool, bool);

		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getCondicaoParticipacaoTextAreaLabel() {
		if (condicaoParticipacaoTextAreaLabel == null) {
			condicaoParticipacaoTextAreaLabel = new JLabel("Condição de Participação");
			condicaoParticipacaoTextAreaLabel.setSize(new java.awt.Dimension(
					272, 20));
			condicaoParticipacaoTextAreaLabel.setLocation(new java.awt.Point(0,
					0));
			condicaoParticipacaoTextAreaLabel
					.setHorizontalAlignment(JLabel.LEFT);
		}
		return condicaoParticipacaoTextAreaLabel;
	}

	protected JPanel getCondPartButtonsPanel() {
		if (condPartButtonsPanel == null) {
			condPartButtonsPanel = new JPanel();
			condPartButtonsPanel.setLayout(new java.awt.FlowLayout());
			condPartButtonsPanel.setBounds(new Rectangle(10, 302, 612, 40));
			condPartButtonsPanel.add(getNovaCondicaoButton());
			condPartButtonsPanel.add(getSalvarCondicaoButton());
			condPartButtonsPanel.add(getEditarCondicaoButton(), null);
		}
		return condPartButtonsPanel;
	}

	protected JButton getNovaCondicaoButton() {
		if (novaCondicaoButton == null) {
			novaCondicaoButton = new JButton("Nova Condição");
			novaCondicaoButton.setSize(new java.awt.Dimension(150, 20));
			novaCondicaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/script_add.png")));
			novaCondicaoButton.setLocation(new java.awt.Point(0, 26));
			novaCondicaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getDescricaoCondicaoTextField().setEnabled(true);
					getDescricaoCondicaoTextField().requestFocus();
					condicaoParticipacaoTextArea.setEnabled(true);
					salvarCondicaoButton.setEnabled(true);
					newCondicaoParticipacao();
				}

				
			});
		}
		return novaCondicaoButton;
	}

	protected JButton getSalvarCondicaoButton() {
		if (salvarCondicaoButton == null) {
			salvarCondicaoButton = new JButton("Atualizar Condição");
			salvarCondicaoButton.setSize(new java.awt.Dimension(150, 20));
			salvarCondicaoButton.setEnabled(false);
			salvarCondicaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/script_save.png")));
			salvarCondicaoButton.setLocation(new java.awt.Point(0, 49));
			salvarCondicaoButton.addActionListener(new AbstractAction() {
				public void doAction(java.awt.event.ActionEvent e) {
					try {
						cadastreCondicaoParticipacaoLeilao();
						RemoteTurmaService.getInstance().saveTrainingClassParticipationCondition(condPartLeilao);
						AdapitVirtualFrame.getInstance().showOperationSucess();
						salvarCondicaoButton.setEnabled(false);
						condicaoParticipacaoTextArea.setEnabled(false);
						descricaoCondicaoTextField.setEnabled(false);
						updateCondicaoParticipacaoList();
						getCondicoesCadastradasComboBox().setSelectedItem(condPartLeilao.getDescricao());
						
					} catch (Exception e1) {
						e1.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Condições", "Problema ao cadastrar a condição");						
					}
				}
			});
		}
		return salvarCondicaoButton;
	}

	protected JPanel getTreinamentoAgendasTab() {

		if (treinamentoAgendasTab == null) {
			treinamentoAgendasTab = new JPanel();
			treinamentoAgendasTab.setSize(new java.awt.Dimension(575, 200));
			treinamentoAgendasTab.setLocation(new java.awt.Point(0, 574));
			treinamentoAgendasTab.setLayout(null);
			treinamentoAgendasTab.add(getAgendasTurmaPanel());
			treinamentoAgendasTab.add(getTreinamentosSearchButtonsPanel());
			treinamentoAgendasTab.add(getListarTreinamentosButton(), null);
			treinamentoAgendasTab.add(getListPanel(), null);			
		}
		getAgendasTurmasTable().listAll();
		return treinamentoAgendasTab;
	}

	protected JPanel getAgendasTurmaPanel() {
		if (agendasTurmaPanel == null) {
			agendasTurmaPanel = new JPanel();
			agendasTurmaPanel.setSize(new Dimension(628, 275));
			agendasTurmaPanel.setLocation(new Point(0, 1));
			agendasTurmaPanel.setLayout(null);
			agendasTurmaPanel.add(getAgendasTurmasScrollPane());
			agendasTurmaPanel.add(getLotesEmLeilaoButtonsListPanel());
		}
		return agendasTurmaPanel;
	}

	protected JScrollPane getAgendasTurmasScrollPane() {
		if (agendasTurmasScrollPane == null) {
			agendasTurmasScrollPane = new JScrollPane();
			agendasTurmasScrollPane.setSize(new Dimension(514, 264));
			agendasTurmasScrollPane.setLocation(new Point(3, 4));

			agendasTurmasScrollPane.add(getAgendasTurmasTable());
			agendasTurmasScrollPane.setViewportView(getAgendasTurmasTable());
		}
		return agendasTurmasScrollPane;
	}

	protected AgendasTurmaTable getAgendasTurmasTable() {
		if (agendasTurmasTable == null) {
			agendasTurmasTable = new AgendasTurmaTable();
			agendasTurmasTable.addFocusListener(new FocusAdapter(){
				@Override
				public void focusGained(FocusEvent evt) {
					enableButtons(true);
				}				
			});
			return agendasTurmasTable;
		}
		return agendasTurmasTable;
	}
	
	private void enableButtons(boolean b){
		getEditarLoteButton().setEnabled(b);
		getRemoveLoteButton().setEnabled(b);
	}
	

	protected JPanel getLotesEmLeilaoButtonsListPanel() {
		if (lotesEmLeilaoButtonsListPanel == null) {
			lotesEmLeilaoButtonsListPanel = new JPanel();
			lotesEmLeilaoButtonsListPanel.setSize(new Dimension(96, 160));
			lotesEmLeilaoButtonsListPanel.setLocation(new Point(520, 14));
			lotesEmLeilaoButtonsListPanel.setLayout(new GridLayout(6,1));
			
			lotesEmLeilaoButtonsListPanel.add(getAddlotesemleilaoButton());
			lotesEmLeilaoButtonsListPanel.add(getRemoveLoteButton());
			lotesEmLeilaoButtonsListPanel.add(getEditarLoteButton());
			lotesEmLeilaoButtonsListPanel.add(getListarTodosButton(), null);
			lotesEmLeilaoButtonsListPanel.add(getAnexarButton(), null);	
			lotesEmLeilaoButtonsListPanel.add(getListarNulosButton(), null);
					
		}
		return lotesEmLeilaoButtonsListPanel;
	}

	protected JButton getAddlotesemleilaoButton() {
		if (novoLoteButton == null) {
			novoLoteButton = new JButton("Novo");
			novoLoteButton.setSize(new java.awt.Dimension(80, 24));
			novoLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_add.png")));
			novoLoteButton.setLocation(new java.awt.Point(0, 23));
			novoLoteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
						AdapitVirtualFrame.getInstance().novoTreinamento();
						AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(turma);
						AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().novoTreinamento();
					
				}
			});
		}
		return novoLoteButton;
	}

	protected JButton getRemoveLoteButton() {

		if (removelotesemleilaoButton == null) {
			removelotesemleilaoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.leilao.LeilaoCadastreForm.Remover"));
			removelotesemleilaoButton.setSize(new java.awt.Dimension(80, 24));
			removelotesemleilaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_delete.png")));
			removelotesemleilaoButton.setEnabled(false);
			removelotesemleilaoButton.setLocation(new java.awt.Point(0, 50));
			removelotesemleilaoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int row = getAgendasTurmasTable().getSelectedRow();
							if (row >=0){
								Treinamento l = (Treinamento) getAgendasTurmasTable().getElements().get(row);
								if (AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame != null){
									AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(turma);
									AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTreinamento(l);
									AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().getRemoverAction().doAction(e);
									getAgendasTurmasTable().getElements().remove(row);
									getAgendasTurmasTable().updateTable();
								}
								else if (l != null){
									int resp = JOptionPane.showConfirmDialog(TurmaCadastreForm.this, "Apagar o treinamento " + l.getCodigo(),"Apagar Treinamento",JOptionPane.YES_NO_OPTION);
									if (resp == JOptionPane.YES_OPTION){
										try {
											try {
												RemoteTurmaService.getInstance().removeScheduledTrainingFromTrainingClass(l, turma);
												JOptionPane.showMessageDialog(TurmaCadastreForm.this, "O treinamento foi removido com sucesso!","Apagar Treinamento",JOptionPane.INFORMATION_MESSAGE);
												getAgendasTurmasTable().getElements().remove(row);
												getAgendasTurmasTable().updateTable();
											} catch (Exception e1) {
												e1.printStackTrace();
												JOptionPane.showMessageDialog(TurmaCadastreForm.this, "O treinamento não foi removido!","Apagar Treinamento",JOptionPane.ERROR_MESSAGE);
											}
																						
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									}
								}
									
								AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().novoTreinamento();
							}
						}
					});
		}
		return removelotesemleilaoButton;
	}

	protected JButton getEditarLoteButton() {

		if (editarLoteButton == null) {
			editarLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.leilao.LeilaoCadastreForm.Editar"));
			editarLoteButton.setSize(new java.awt.Dimension(80, 24));
			editarLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarLoteButton.setEnabled(false);
			editarLoteButton.setLocation(new java.awt.Point(0, 77));
			editarLoteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getAgendasTurmasTable().getSelectedRow();
					if (row >=0){
						Treinamento l = (Treinamento) getAgendasTurmasTable().getElements().get(row);
						if (AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame != null){
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(turma);
							AdapitVirtualFrame.getInstance().editarTreinamento(l);
						}else{
							AdapitVirtualFrame.getInstance().novoTreinamento();
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(turma);
							AdapitVirtualFrame.getInstance().editarTreinamento(l);
						}
					}
				}
			});
		}
		return editarLoteButton;
	}

	protected JPanel getTreinamentosSearchButtonsPanel() {
		if (treinamentosSearchButtonsPanel == null) {
			treinamentosSearchButtonsPanel = new JPanel();
			treinamentosSearchButtonsPanel.setSize(new Dimension(616, 32));
			treinamentosSearchButtonsPanel.setLocation(new Point(2, 316));
			treinamentosSearchButtonsPanel.setLayout(new java.awt.FlowLayout());
			treinamentosSearchButtonsPanel.add(getCriarPorFormacaoButton(), null);
			treinamentosSearchButtonsPanel.add(getEncaminharLotesButton(), null);
			treinamentosSearchButtonsPanel.add(getDefinirAgendaTreinamentoButton());
		}
		return treinamentosSearchButtonsPanel;
	}

	protected JButton getListarTreinamentosButton() {
		if (listarTreinamentosButton == null) {
			listarTreinamentosButton = new JButton("Listar");
			listarTreinamentosButton.setBounds(new Rectangle(2, 290, 80, 24));
			listarTreinamentosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					enableButtons(false);
					if (getPendentesAgendaRadioButton().isSelected()) getAgendasTurmasTable().listPendentesDeAgenda();
					else if (getPendentesAprovacaoRadioButton().isSelected()) getAgendasTurmasTable().listPendentesAprovacao();
					
				}
			});
		}
		return listarTreinamentosButton;
	}

	protected JButton getDefinirAgendaTreinamentoButton() {
		if (definirAgendaTreinamentoButton == null) {
			definirAgendaTreinamentoButton = new JButton("Definir agenda");
			definirAgendaTreinamentoButton.setSize(new java.awt.Dimension(150,
					20));
			definirAgendaTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/calendar_edit.png")));
			definirAgendaTreinamentoButton.setEnabled(false);
			definirAgendaTreinamentoButton
					.setLocation(new java.awt.Point(0, 49));
		}
		return definirAgendaTreinamentoButton;
	}

	protected JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.setBounds(new Rectangle(11, 310, 612, 37));
			buttonsPanel.add(getCadastrarButton());
			buttonsPanel.add(getNovoButton());
		}
		return buttonsPanel;
	}

	protected JButton getCadastrarButton() {
		if (cadastrarButton == null) {
			cadastrarButton = new JButton("Cadastrar");
			cadastrarButton.setSize(new java.awt.Dimension(80, 22));
			//cadastrarButton.setIcon(getIcon("/imgs/database_save.png"));
			cadastrarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_save.png")));
			cadastrarButton.setLocation(new java.awt.Point(0, 0));
			cadastrarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						cadastreTurma();
						AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de Turma", "Turma cadastrada com sucesso!");
						cadastrarButton.setText("Atualizar");
						editRegister();
					} catch (Exception e1) {
						e1.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Turma", "A turma não foi cadastrada!");
					}
				}
			});
		}
		return cadastrarButton;
	}

	protected JButton getNovoButton() {
		if (novoButton == null) {
			novoButton = new JButton("Nova Turma");
			novoButton.setSize(new java.awt.Dimension(80, 22));
			novoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_add.png")));
			novoButton.setLocation(new java.awt.Point(0, 22));
			novoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					newRegister();
				}
			});
			
		}
		return novoButton;
	}



	/**
	 * This method initializes executarComoList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JComboBox getExecutarComoComboBox() {
		if (executarComoComboBox == null) {
			executarComoComboBox = new JComboBox();
			for (int i=0; i < TipoExecucaoTreinamento.values().length;i++){
				executarComoComboBox.addItem(TipoExecucaoTreinamento.values()[i].name().replaceAll("_", " "));
			}
			executarComoComboBox.setSelectedIndex(3);
			executarComoComboBox.setBounds(new Rectangle(105, 75, 170, 22));
			executarComoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						//updateRegraLeilao();
						updateRegraTurmasByState();
					}
				}
			});
			
		}
		return executarComoComboBox;
	}
	
	private JComboBox getTurnoTreinamentoComboBox() {
		if (turnoTreinamentoComboBox == null) {
			turnoTreinamentoComboBox = new JComboBox();
			for (int i=0; i < TurnoTreinamento.values().length;i++){
				turnoTreinamentoComboBox.addItem(TurnoTreinamento.values()[i].name().replaceAll("_", " "));
			}
			turnoTreinamentoComboBox.setSelectedIndex(1);
			turnoTreinamentoComboBox.setBounds(new Rectangle(380, 75, 230, 22));
			turnoTreinamentoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						//updateRegraLeilao();
						updateRegraTurmasByState();
					}
				}				
			});
		}
		return turnoTreinamentoComboBox;
	}

	/**
	 * This method initializes enderecoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getEnderecoComboBox() {
		if (enderecoComboBox == null) {
			enderecoComboBox = new JComboBox();
			enderecoComboBox.setBounds(new Rectangle(116, 61, 230, 22));
			enderecoComboBox.setEditable(true);
			AutoCompletion.enable(enderecoComboBox);
		}
		return enderecoComboBox;
	}

	/**
	 * This method initializes enderecoButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEnderecoButtonsPanel() {
		if (enderecoButtonsPanel == null) {
			enderecoButtonsPanel = new JPanel();
			enderecoButtonsPanel.setLayout(new GridLayout(1,2));
			enderecoButtonsPanel.setBounds(new Rectangle(386, 61, 234, 22));
			enderecoButtonsPanel.add(getEditarLocalButton(), null);
			enderecoButtonsPanel.add(getNovoLocalButton(), null);
		}
		return enderecoButtonsPanel;
	}

	/**
	 * This method initializes editarLocalButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarLocalButton() {
		if (editarLocalButton == null) {
			editarLocalButton = new JButton();
			editarLocalButton.setText("Editar Local");
			editarLocalButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					EnderecoCadastreDialog end = new EnderecoCadastreDialog(AddressType.Presencial);
					int index  = getEnderecoComboBox().getSelectedIndex();
					Endereco ender = (Endereco) enderecos.get(index);
					end.editRegister(ender);
					end.setVisible(true);
					updateEnderecoList();
				}
			});
		}
		return editarLocalButton;
	}

	/**
	 * This method initializes novoLocalButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoLocalButton() {
		if (novoLocalButton == null) {
			novoLocalButton = new JButton();
			novoLocalButton.setText("Novo Local");
			novoLocalButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					EnderecoCadastreDialog end = new EnderecoCadastreDialog(AddressType.Presencial);
					end.newRegister();
					end.setVisible(true);
					updateEnderecoList();
				}
			});
		}
		return novoLocalButton;
	}

	/**
	 * This method initializes refreshInstrutorButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshInstrutorButton() {
		if (refreshInstrutorButton == null) {
			refreshInstrutorButton = new JButton();
			refreshInstrutorButton.setBounds(new Rectangle(340, 0, 22, 22));
			refreshInstrutorButton.setToolTipText("Atualizar a lista de instrutores");
			refreshInstrutorButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshInstrutorButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateInstrutorList();
				}
			});
		}
		return refreshInstrutorButton;
	}
	@SuppressWarnings("unchecked")
	private List instrutors = new ArrayList();  //  @jve:decl-index=0:

	/**
	 * This method initializes refreshComitenteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshFormacoesButton() {
		if (refreshFormacoesButton == null) {
			refreshFormacoesButton = new JButton();
			refreshFormacoesButton.setBounds(new Rectangle(340, 0, 22, 22));
			refreshFormacoesButton.setToolTipText("Atualizar a lista de formações");
			refreshFormacoesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshFormacoesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateFormacoesList();
				}
			});
		}
		return refreshFormacoesButton;
	}
	
/*	@SuppressWarnings("unchecked")
	private List<ContatoTreinamento> contatos = new ArrayList();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	public void updateContatosList(){
		getComarcaComboBox().removeAllItems();
		contatos.clear();
		try {
			Iterator<ContatoTreinamento> it = RemoteContatoService.getInstance().listAllContatos().iterator();
			{
				while(it.hasNext()){
					ContatoTreinamento l = it.next();
					contatos.add(l);
					getComarcaComboBox().addItem(l.getNome());
				}
			}
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		if (contatos == null || contatos.size() == 0) getEditarContatoButton().setEnabled(false);
		else getEditarContatoButton().setEnabled(true);
	}*/
	
	@SuppressWarnings("unchecked")
	private List<FormacaoTreinamento> formacoes = new ArrayList();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	public void updateFormacoesList(){
		getFormacaoComboBox().removeAllItems();
		formacoes.clear();
		try {
			Iterator<FormacaoTreinamento> it = RemoteTreinamentoService.getInstance().listAllTrainingFormations().iterator();
			{
				while(it.hasNext()){
					FormacaoTreinamento l = it.next();
					formacoes.add(l);
					getFormacaoComboBox().addItem(l.getNome());
				}
			}
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		if (formacoes == null || formacoes.size() == 0) getEditarFormacaoButton().setEnabled(false);
		else getEditarFormacaoButton().setEnabled(true);
	}
	
	@SuppressWarnings("unchecked")	
	public void updateInstrutorList(){
		getInstrutorComboBox().removeAllItems();
		instrutors.clear();
		try {
			Iterator<Instrutor> it = 
				RemoteTurmaService.getInstance().listAllInstructors().iterator();
			{
				while(it.hasNext()){
					Instrutor l = it.next();
					instrutors.add(l);
					getInstrutorComboBox().addItem(l.getNome());
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (instrutors == null || instrutors.size() == 0) getEditarInstrutorButton().setEnabled(false);
		else getEditarInstrutorButton().setEnabled(true);
	}
	@SuppressWarnings("unchecked")
	public void updateCondicaoParticipacaoList(){
		getCondicoesCadastradasComboBox().removeAllItems();
		condicoes.clear();
		try {
			Iterator<CondicaoParticipacaoTreinamento> it = 
				RemoteTurmaService.getInstance().listTrainingClassParticipationConditions().iterator();
			{
				while(it.hasNext()){
					CondicaoParticipacaoTreinamento l = it.next();
					condicoes.add(l);
					getCondicoesCadastradasComboBox().addItem(l.getDescricao());
				}
			}			
		} catch (Exception e1) {
			e1.printStackTrace();			
		}
		
		if (condicoes == null || condicoes.size() == 0) getEditarCondicaoButton().setEnabled(false);
		else{
			getEditarCondicaoButton().setEnabled(true);
			getCondicoesCadastradasComboBox().setSelectedIndex(0);
		}
		salvarCondicaoButton.setEnabled(false);
	}
	
	@SuppressWarnings("unchecked")
	private List condicoes = new ArrayList();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	public void updateEnderecoList(){
		getEnderecoComboBox().removeAllItems();
		enderecos.clear();
		try {
			Iterator<Endereco> it = RemoteTurmaService.getInstance().listAddressesByAddressType(AddressType.Presencial).iterator();
			{
				while(it.hasNext()){
					Endereco l = it.next();
					enderecos.add(l);
					getEnderecoComboBox().addItem(l.getCidade()+" ("+l.getRua()+")");
				}
			}			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (enderecos == null || enderecos.size() == 0) getEditarLocalButton().setEnabled(false);
		else getEditarLocalButton().setEnabled(true);
	}

	/**
	 * This method initializes refreshEnderecoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshEnderecoButton() {
		if (refreshEnderecoButton == null) {
			refreshEnderecoButton = new JButton();
			refreshEnderecoButton.setBounds(new Rectangle(351, 61, 22, 22));
			refreshEnderecoButton.setToolTipText("Atualizar a lista de enderços");
			refreshEnderecoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshEnderecoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateEnderecoList();
				}
			});
		}
		return refreshEnderecoButton;
	}
	
	@SuppressWarnings("unchecked")
	private List enderecos = new ArrayList();  //  @jve:decl-index=0:

	private JLabel condDesLabel = null;

	private JComboBox condicoesCadastradasComboBox = null;

	private JButton atualizarCondicaoPartButton = null;

	private JButton editarCondicaoButton = null;

	private JButton listarTodosButton = null;

	private JButton anexarButton = null;

	private JButton listarRelatoriosButton = null;

	private JButton encaminharLotesButton = null;

	private JPanel listPanel = null;

	private JRadioButton pendentesAprovacaoRadioButton = null;

	private JRadioButton pendentesAgendaRadioButton = null;

	private JButton criarPorFormacaoButton = null;

	private JLabel numeroMaxParticipantesLabel = null;

	private JTextField numeroParticipantesTextField = null;

	private JLabel numeroMinimoParticpantesLabel = null;

	private JTextField numeroMinimoParticipantesTextField = null;

	private JLabel dataFinalTreinamentoLabel = null;

	private DateHourChooser encerramentoPrevistoField = null;

	private JLabel instrucoesAosParticipantesLabel = null;

	private JScrollPane instrucoesParticipantesScrollPane = null;

	private JTextPane instrucoesParticipantesTextPane = null;

	private JPanel imageMainPanel = null;

	private JLabel imagemTitleLabel = null;

	private JButton buscarButton = null;

	private JPanel imagePanel = null;

	/**
	 * This method initializes condicoesCadastradasComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCondicoesCadastradasComboBox() {
		if (condicoesCadastradasComboBox == null) {
			condicoesCadastradasComboBox = new JComboBox();
			condicoesCadastradasComboBox.setBounds(new Rectangle(153, 10, 393, 22));
		}
		return condicoesCadastradasComboBox;
	}

	/**
	 * This method initializes atualizarCondicaoPartButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAtualizarCondicaoPartButton() {
		if (atualizarCondicaoPartButton == null) {
			atualizarCondicaoPartButton = new JButton();
			atualizarCondicaoPartButton.setBounds(new Rectangle(553, 10, 22, 22));
			atualizarCondicaoPartButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
		}
		return atualizarCondicaoPartButton;
	}

	/**
	 * This method initializes editarCondicaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarCondicaoButton() {
		if (editarCondicaoButton == null) {
			editarCondicaoButton = new JButton();
			editarCondicaoButton.setText("Editar o Pré-Requisito");
			editarCondicaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/script_edit.png")));
			editarCondicaoButton.setEnabled(false);
			editarCondicaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = getCondicoesCadastradasComboBox().getSelectedIndex();
					CondicaoParticipacaoTreinamento cond = (CondicaoParticipacaoTreinamento) condicoes.get(index);
					try {
						cond = RemoteTurmaService.getInstance().loadTrainingClassParticipationCondition(cond.getId());
						editRegister(cond);
						getDescricaoCondicaoTextField().setEnabled(true);
						getDescricaoCondicaoTextField().requestFocus();
						condicaoParticipacaoTextArea.setEnabled(true);
						salvarCondicaoButton.setEnabled(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return editarCondicaoButton;
	}
	
	public static Participante getParticipanteByUsuario(Usuario user){		
		try {
			user = (Usuario) RemoteUserService.getInstance().getUserByLogin(user.getLogin());
			Participante p = (Participante) RemotePessoaService.getInstance()
			.getParticipante(user.getDadosPessoais().getId());
			return p;
		} catch (Exception e1) { 
			e1.printStackTrace();
		}
		return null;			
	}

	/**
	 * This method initializes listarTodosButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarTodosButton() {
		if (listarTodosButton == null) {
			listarTodosButton = new JButton();
			listarTodosButton.setBounds(new Rectangle(2, 105, 76, 25));
			listarTodosButton.setText("Listar Todos");
			listarTodosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					enableButtons(false);
					getAgendasTurmasTable().listAll();
				}
			});
		}
		return listarTodosButton;
	}

	/**
	 * This method initializes anexarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAnexarButton() {
		if (anexarButton == null) {
			anexarButton = new JButton();
			anexarButton.setText("Adicionar");
			anexarButton.setEnabled(false);
		}
		return anexarButton;
	}

	/**
	 * This method initializes listarNulosButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarNulosButton() {
		if (listarRelatoriosButton == null) {
			listarRelatoriosButton = new JButton("Relatórios");
			listarRelatoriosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					enableButtons(false);
					getAgendasTurmasTable().listNulls();
				}
			});
		}
		return listarRelatoriosButton;
	}

	/**
	 * This method initializes encaminharLotesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEncaminharLotesButton() {
		if (encaminharLotesButton == null) {
			encaminharLotesButton = new JButton();
			encaminharLotesButton.setText("Encaminhar para outra turma");
			encaminharLotesButton.setEnabled(false);
			encaminharLotesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
		}
		return encaminharLotesButton;
	}

	/**
	 * This method initializes listPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getListPanel() {
		if (listPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setVgap(0);
			listPanel = new JPanel();
			listPanel.setLayout(flowLayout);
			listPanel.setBounds(new Rectangle(83, 292, 537, 22));
			listPanel.add(getPendentesAgendaRadioButton(), null);
			listPanel.add(getPendentesAprovacaoRadioButton(), null);			
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getPendentesAgendaRadioButton());
			bg.add(getPendentesAprovacaoRadioButton());
		}
		return listPanel;
	}



	/**
	 * This method initializes comLancesRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getPendentesAprovacaoRadioButton() {
		if (pendentesAprovacaoRadioButton == null) {
			pendentesAprovacaoRadioButton = new JRadioButton();
			pendentesAprovacaoRadioButton.setText("Pendentes de aprovação");
		}
		return pendentesAprovacaoRadioButton;
	}

	/**
	 * This method initializes pendentesAgendaRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getPendentesAgendaRadioButton() {
		if (pendentesAgendaRadioButton == null) {
			pendentesAgendaRadioButton = new JRadioButton();
			pendentesAgendaRadioButton.setText("Pendentes de datas");
			pendentesAgendaRadioButton.setSelected(true);
		}
		return pendentesAgendaRadioButton;
	}

	/**
	 * This method initializes verDadosLancesLoteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCriarPorFormacaoButton() {
		if (criarPorFormacaoButton == null) {
			criarPorFormacaoButton = new JButton();
			criarPorFormacaoButton.setText("Criar Pela Formação");
			criarPorFormacaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_mode.png")));
			//criarPorFormacaoButton.setEnabled(false);
			criarPorFormacaoButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					FormacaoTreinamento f = formacoes.get(getFormacaoComboBox().getSelectedIndex());
					try {
						List<Treinamento> treinamentos = RemoteTreinamentoService.getInstance()
							.createTrainemantosByFormacaoIdAndTurmaId(f.getId(), turma.getId());
						getAgendasTurmasTable().setElements(treinamentos);
						getAgendasTurmasTable().updateTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				
			});
		}
		return criarPorFormacaoButton;
	}

	/**
	 * This method initializes numeroParticipantesTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JComponent getNumeroParticipantesTextField() {
		if (numeroParticipantesTextField == null) {
			numeroParticipantesTextField = new JTextField();
			numeroParticipantesTextField.setBounds(new Rectangle(550, 0, 60, 20));
			this.binder.addBindProperty(this.turma, numeroParticipantesTextField, "numeroMaximoParticipantes");

			this.hashComps.put("numeroMaximoParticipantes", numeroParticipantesTextField);
			JWarningComponent warn = new JWarningComponent(numeroParticipantesTextField);
			warn.setBounds(new Rectangle(550, 0, 60, 20));
			return warn;
		}
		return numeroParticipantesTextField;
	}

	/**
	 * This method initializes numeroMinimoParticipantesTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JComponent getNumeroMinimoParticipantesTextField() {
		if (numeroMinimoParticipantesTextField == null) {
			numeroMinimoParticipantesTextField = new JTextField();
			numeroMinimoParticipantesTextField.setBounds(new Rectangle(550, 24, 60, 20));
			this.binder.addBindProperty(this.turma, numeroMinimoParticipantesTextField, "numeroMinimoParticipantes");

			this.hashComps.put("numeroMinimoParticipantes", numeroMinimoParticipantesTextField);
			JWarningComponent warn = new JWarningComponent(numeroMinimoParticipantesTextField);
			warn.setBounds(new Rectangle(550, 24, 60, 20));
			return warn;
		}
		return numeroMinimoParticipantesTextField;
	}

	/**
	 * This method initializes encerramentoPrevistoField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JComponent getEncerramentoPrevistoField() {
		if (encerramentoPrevistoField == null) {			
			encerramentoPrevistoField = new DateHourChooser(messages
					.getCurrentLocale(), true, true, false);
			encerramentoPrevistoField.setSize(new Dimension(134, 22));
			encerramentoPrevistoField.setLocation(new Point(160, 25));
			this.binder.addBindProperty(this.turma, encerramentoPrevistoField, "dataEncerramento");

			this.hashComps.put("dataEncerramento", encerramentoPrevistoField);
			JWarningComponent warn = new JWarningComponent(encerramentoPrevistoField);
			warn.setBounds(160, 25, 134, 22);
			return warn;
		}
		return encerramentoPrevistoField;
	}

	/**
	 * This method initializes instrucoesParticipantesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getInstrucoesParticipantesScrollPane() {
		if (instrucoesParticipantesScrollPane == null) {
			instrucoesParticipantesScrollPane = new JScrollPane();
			instrucoesParticipantesScrollPane.setBounds(new Rectangle(12, 106, 334, 90));
			instrucoesParticipantesScrollPane.setViewportView(getInstrucoesParticipantesTextPane());
		}
		return instrucoesParticipantesScrollPane;
	}

	/**
	 * This method initializes instrucoesParticipantesTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JComponent getInstrucoesParticipantesTextPane() {
		if (instrucoesParticipantesTextPane == null) {
			instrucoesParticipantesTextPane = new JTextPane();
			this.binder.addBindProperty(this.turma, instrucoesParticipantesTextPane, "instrucoesParticipantes");

			this.hashComps.put("instrucoesParticipantes", instrucoesParticipantesTextPane);
			JWarningComponent warn = new JWarningComponent(instrucoesParticipantesTextPane);
			warn.setBounds(new Rectangle(12, 106, 612, 90));
			return warn;
		}
		return instrucoesParticipantesTextPane;
	}

	/**
	 * This method initializes imageMainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getImageMainPanel() {
		if (imageMainPanel == null) {
			imagemTitleLabel = new JLabel();
			imagemTitleLabel.setBounds(new Rectangle(0, 0, 269, 20));
			imagemTitleLabel.setText("Imagem para Propaganda do Treinamento:");
			imageMainPanel = new JPanel();
			imageMainPanel.setLayout(null);
			imageMainPanel.setBounds(new Rectangle(351, 85, 269, 110));
			imageMainPanel.add(imagemTitleLabel, null);
			imageMainPanel.add(getBuscarButton(), null);
			imageMainPanel.add(getImagePanel(), null);
		}
		return imageMainPanel;
	}

	/**
	 * This method initializes buscarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarButton() {
		if (buscarButton == null) {
			buscarButton = new JButton();
			buscarButton.setBounds(new Rectangle(6, 44, 111, 41));
			buscarButton.setText("Buscar");
			buscarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (turma.getId() != 0){
						anexarImagem();
					}
					else{
						JOptionPane.showMessageDialog(TurmaCadastreForm.this, "Primeiro é preciso cadastrar a turma!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						getTabbedPane().setSelectedIndex(0);
					}
				}				
			});
		}
		return buscarButton;
	}

	/**
	 * This method initializes imagePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new JPanel();
			imagePanel.setLayout(new BorderLayout());
			imagePanel.setBounds(new Rectangle(128, 19, 140, 90));
			imagePanel.add(getSmallLabelImage(),BorderLayout.CENTER);
		}
		return imagePanel;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(595, 391));
				gui.add(new TurmaCadastreForm(), java.awt.BorderLayout.CENTER);
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
		}
		return null;
	}

	private class AgendasTurmaTable extends JTable {
		@SuppressWarnings("unchecked")
		private List elements;
		@SuppressWarnings("unchecked")
		public void setElements(List elements) {
			this.elements = elements;
		}

		
		@SuppressWarnings("unchecked")
		public List getElements() {
			return this.elements;
		}

		public AgendasTurmaTable() {

			super();
			this.setModel(new AgendasTurmaTableModel(null));
			this.addPropertyChangeListener(new LotesEmLeilaoListPropertyChangeListener());
		}
		@SuppressWarnings("unchecked")
		public AgendasTurmaTable(List elements) {
			super();
			this.elements = elements;
			updateTable();
		}

		@SuppressWarnings("unchecked")
		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][3];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Treinamento) {
						com.adapit.portal.entidades.Treinamento lote = (com.adapit.portal.entidades.Treinamento) obj;
						values[i][0] = lote.getCodigo();
						values[i][1] = lote.getResumo();
						if (lote.getStatus() != null) values[i][2] = lote.getStatus();
						else values[i][2] = "Cadastro";
						i++;
					}
				}// End of while Loop
				setModel(new AgendasTurmaTableModel(values));
				getColumnModel().getColumn(1).setPreferredWidth(200);
				
				updateUI();
			} else {
				setModel(new AgendasTurmaTableModel(null));
				getColumnModel().getColumn(1).setPreferredWidth(200);
				updateUI();
			}
		}

		private class LotesEmLeilaoListPropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.treinamento.turma.TurmaCadastreForm.AgendasTurmaTable jt = (com.adapit.portal.ui.forms.treinamento.turma.TurmaCadastreForm.AgendasTurmaTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Treinamento) {
							com.adapit.portal.entidades.Treinamento lote = (com.adapit.portal.entidades.Treinamento) obj;
							if (col == 0)
								lote.setCodigo(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 1)
								lote.setResumo(((java.lang.String) jt
										.getValueAt(row, col)));
							
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}
		@SuppressWarnings("unchecked")
		private class AgendasTurmaTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class };

			boolean canEdit[] = new boolean[] { true, true, true };

			public AgendasTurmaTableModel(Object[][] values) {

				super(
						values,
						new String[] {"Número da Agenda",
								"Resumo","Status"});
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}



		public void listPendentesAprovacao() {
			try {
				elements = RemoteTurmaService.getInstance().listAllScheduledTrainingsWithPendingApproval();
			} catch (Exception e) {
				e.printStackTrace();
			}
			updateTable();
		}

		public void listPendentesDeAgenda() {
			try {
				elements = RemoteTurmaService.getInstance().listAllScheduledTrainingsWithoutDefinedSchedule();
			} catch (Exception e) {
				e.printStackTrace();
			}
			updateTable();
		}

		public void listNulls() {
			try {
				elements = RemoteTurmaService.getInstance().listAllScheduledTrainingsWithoutTrainingClass();
			} catch (Exception e) {
				e.printStackTrace();
			}
			updateTable();
		}

		public void listAll() {
			try {
				elements = RemoteTurmaService.getInstance().listScheduledTrainingsByTrainingClassId(turma.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}
			updateTable();
		}

	}
	
	private JButton anexar;
	public void anexarImagem(){		
		try {				
			if (anexar == null){				 
				anexar = new JButton("Anexar na turma");
				anexar.addActionListener(new AnexarImagemActionListener());	
				anexar.setIcon(getIcon("/imgs/picture_link.png"));
			}			
			AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm().getButtonsPanel().add(anexar,0);
			AdapitVirtualFrame.getInstance().listImagens();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private class AnexarImagemActionListener implements ActionListener{
		
		public AnexarImagemActionListener(){
			AdapitVirtualFrame.getInstance().listImagens();		
			ilf = AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm();
			buttonsPanel = ilf.getButtonsPanel();
		}
		private ImageListForm ilf;
		private JPanel buttonsPanel;
		
		@Override
		public void actionPerformed(ActionEvent evt) {
					
			int rows[] = ilf.getBaseTable().getSelectedRows();
			if (rows != null){
				try {
					Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[0]);
					RemoteTurmaService.getInstance().mergeImageTrainingClass(im.getId(),turma.getId());
					turma.setImagem(im);
					getSmallLabelImage().setIcon(im.getSmallImageIcon(false));
					getSmallLabelImage().updateUI();
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Imagem em Turma", "Imagem anexada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}				
				buttonsPanel.remove(anexar);				
			}
			AdapitVirtualFrame.getInstance().getListaImagensFrame().dispose();
		}
	}
	
	private JLabel smallLabelImage;
	
	protected JLabel getSmallLabelImage() {
		if (smallLabelImage == null) {
			smallLabelImage = new JLabel();
			smallLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			smallLabelImage.setHorizontalAlignment(SwingConstants.CENTER);						
		}
		return smallLabelImage;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"