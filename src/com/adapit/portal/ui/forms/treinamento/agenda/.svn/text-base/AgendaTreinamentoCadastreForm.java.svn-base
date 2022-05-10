package com.adapit.portal.ui.forms.treinamento.agenda;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Encerramento;
import com.adapit.portal.entidades.StatusAgenda;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.remote.RemoteAgendaTreinamentoService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.treinamento.ScheduledTrainingCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;


@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class AgendaTreinamentoCadastreForm extends JPanel{
	
	private JPanel loteDataPanel;
	
	private JTextField codTreinamentoTextField;
	
	private SwingBinder binder = new SwingBinder();@SuppressWarnings("unchecked")
  //  @jve:decl-index=0:
	
	private Map hashComps = new java.util.HashMap();
	
	private JLabel codTreinamentoTextField4Label;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField cargaHorariaTreinamentoTextField;
	
	private JLabel cargaHorariaLabel;
	
	private JPanel valorLancesPanel;
		
	private JLabel laceInicialTextFieldLabel;
	
	private JComboBox horaFimComboBox;
	
	private JLabel horaFimLabel;
	
	private JPanel inicioTerminoPrevistoPanel;
	
	private DateHourChooser dataInicialField;
	
	private JLabel dataInicialLabel;
	
	private DateHourChooser dataFinalField;
	
	private JLabel dataFinalLabel;
	
	private JPanel inicioPanel;
	
	private JLabel numeroLancesLabel;
	
	private JTextField lanceVencedor;
	
	private JPanel buttonsPanel;
	
	private JButton atualizarButton;
	
	private JButton agendarButton;
	
	private JButton cancelarButton;
	
	private JLabel obsLabel;
	
	private JLabel arrematanteLabel = null;

	private JTextField arrematanteTextField = null;

	private JLabel horaInicioLabel = null;

	private JComboBox horaInicioComboBox = null;

	private JLabel turnoLabel = null;

	private JComboBox turnoComboBox = null;

	private JButton novaAgendaButton = null;

	private LogErrorPanel errorPanel = null;

	private AgendaTreinamento agenda = new AgendaTreinamento();  //  @jve:decl-index=0:
	
	private Treinamento treinamento;  //  @jve:decl-index=0:
	
	private boolean processFocus = true;
	
	private DateHourChooser tempDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
	
	public AgendaTreinamentoCadastreForm(){	
		initialize();
	}
	
	private void initialize(){		
		this.setSize(new Dimension(570, 367));
		setLocation(new java.awt.Point(0,0));
		setLayout(new BorderLayout());
		add(getTabbedPane());
	}
	
	private JTabbedPane tabbedPane;
	
	private JTabbedPane getTabbedPane(){
		if (tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			tabbedPane.add(getAgendaCadastrePanel(),"Dados da agenda do lote");			
		}
		return tabbedPane;
	}
	
	private JPanel agendaCadastrePanel;

	private JButton listarAgendasButton = null;
	
	private JPanel getAgendaCadastrePanel(){
		if (agendaCadastrePanel == null){
			statusLabel = new JLabel();
			statusLabel.setBounds(new Rectangle(109, 110, 118, 20));
			statusLabel.setText("Estado da Agenda:");
			agendaCadastrePanel = new JPanel();
			turnoLabel = new JLabel();
			turnoLabel.setBounds(new Rectangle(15, 64, 169, 20));
			turnoLabel.setHorizontalAlignment(SwingConstants.LEFT);
			turnoLabel.setText("Turno:");
			horaInicioLabel = new JLabel();
			horaInicioLabel.setText("Hora Início:");
			horaInicioLabel.setBounds(new Rectangle(330, 0, 77, 20));
			agendaCadastrePanel.setSize(new Dimension(566, 347));
			agendaCadastrePanel.setLocation(new java.awt.Point(0,0));
			agendaCadastrePanel.setLayout(null);
			agendaCadastrePanel.add(getTreinamentoDataPanel());
			agendaCadastrePanel.add(getValorLancesPanel());
			agendaCadastrePanel.add(getButtonsPanel());			
			agendaCadastrePanel.add(getInicioTerminoPrevistoPanel(), null);
			agendaCadastrePanel.add(turnoLabel, null);
			agendaCadastrePanel.add(getTurnoComboBox(), null);
			agendaCadastrePanel.add(getErrorPanel(), null);
			agendaCadastrePanel.add(statusLabel, null);
			agendaCadastrePanel.add(getStatusAgendaComboBox(), null);
		}
		return agendaCadastrePanel;
	}
	
	protected JPanel getTreinamentoDataPanel(){
		if(loteDataPanel == null){
			porcentoValorLabel = new JLabel();
			porcentoValorLabel.setBounds(new Rectangle(405, 1, 135, 20));
			porcentoValorLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
			porcentoValorLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			porcentoValorLabel.setHorizontalAlignment(SwingConstants.CENTER);
			porcentoValorLabel.setText("Inicial 60% do Valor:");
			loteDataPanel = new JPanel();
			loteDataPanel.setSize(new Dimension(541, 21));
			loteDataPanel.setLocation(new Point(15, 15));
			loteDataPanel.setLayout(null);
			loteDataPanel.add(getCodTreinamentoTextField());
			loteDataPanel.add(getCodTreinamentoTextField4Label());
			loteDataPanel.add(getCargaHorariaTreinamentoTextField());
			loteDataPanel.add(getCargaHorariaLabel());
			loteDataPanel.add(horaInicioLabel, null);
			loteDataPanel.add(getHoraInicioComboBox(), null);
		}
		return loteDataPanel;
	}
	
	protected JTextField getCodTreinamentoTextField(){
		if(codTreinamentoTextField == null){
			codTreinamentoTextField = new JTextField();
			codTreinamentoTextField.setText("");
			codTreinamentoTextField.setSize(new Dimension(68, 20));
			codTreinamentoTextField.setEditable(false);
			codTreinamentoTextField.setLocation(new Point(92, 0));
			return codTreinamentoTextField;
		}
		return codTreinamentoTextField;
	}
	
	protected JLabel getCodTreinamentoTextField4Label(){
		if(codTreinamentoTextField4Label == null){
			codTreinamentoTextField4Label = new JLabel("Treinamento:");
			codTreinamentoTextField4Label.setSize(new Dimension(90, 20));
			codTreinamentoTextField4Label.setLocation(new java.awt.Point(0,0));
			codTreinamentoTextField4Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return codTreinamentoTextField4Label;
	}
	
	protected JTextField getCargaHorariaTreinamentoTextField(){
		if(cargaHorariaTreinamentoTextField == null){
			cargaHorariaTreinamentoTextField = new JTextField();
			cargaHorariaTreinamentoTextField.setText("");
			cargaHorariaTreinamentoTextField.setSize(new Dimension(59, 20));
			cargaHorariaTreinamentoTextField.setEditable(false);
			cargaHorariaTreinamentoTextField.setHorizontalAlignment(JTextField.RIGHT);
			cargaHorariaTreinamentoTextField.setLocation(new Point(264, 0));
			return cargaHorariaTreinamentoTextField;
		}
		return cargaHorariaTreinamentoTextField;
	}
	
	protected JLabel getCargaHorariaLabel(){
		if(cargaHorariaLabel == null){
			cargaHorariaLabel = new JLabel("Carga Horária:");
			cargaHorariaLabel.setSize(new Dimension(96, 20));
			cargaHorariaLabel.setLocation(new Point(163, 0));
			cargaHorariaLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return cargaHorariaLabel;
	}
	
	protected JPanel getValorLancesPanel(){
		if(valorLancesPanel == null){
			intervaloLabel = new JLabel();
			intervaloLabel.setBounds(new Rectangle(1, 0, 88, 20));
			intervaloLabel.setText("Intervalo:");
			valorLancesPanel = new JPanel();
			valorLancesPanel.setSize(new Dimension(541, 21));
			valorLancesPanel.setLocation(new Point(15, 38));
			valorLancesPanel.setLayout(null);
			
			valorLancesPanel.add(getHoraFimComboBox());
			valorLancesPanel.add(getHoraFimLabel());
			valorLancesPanel.add(intervaloLabel, null);
			valorLancesPanel.add(getIntervaloComboBox(), null);
		}
		return valorLancesPanel;
	}
	
	protected JLabel getLaceInicialTextFieldLabel(){
		if(laceInicialTextFieldLabel == null){
			laceInicialTextFieldLabel = new JLabel("Hora Final:");
			laceInicialTextFieldLabel.setSize(new Dimension(100, 20));
			laceInicialTextFieldLabel.setLocation(new Point(190, 0));
			laceInicialTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			laceInicialTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return laceInicialTextFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComboBox getHoraFimComboBox(){
		if(horaFimComboBox == null){
			horaFimComboBox = new JComboBox();
			//horaFimTextField.setText("");
			horaFimComboBox.setBounds(new Rectangle(410, 0, 130, 20));
			horaFimComboBox.setEditable(true);
			//horaFimTextField.setHorizontalAlignment(JTextField.RIGHT);
		
			
			horaFimComboBox.addItem("10");
			horaFimComboBox.addItem("12");
			horaFimComboBox.addItem("12:30");
			horaFimComboBox.addItem("17:00");
			horaFimComboBox.addItem("17:30");
			horaFimComboBox.addItem("18:00");
			horaFimComboBox.addItem("22:00");
			horaFimComboBox.addItem("22:30");
			horaFimComboBox.addItem("23:00");
			horaFimComboBox.addItem("23:30");
			/*this.binder.addBindProperty(this.agenda,this.horaFimTextField, "horaFim");

			this.hashComps.put("horaFim", this.horaFimTextField);
			JWarningComponent warn = new JWarningComponent(this.horaFimTextField);
			warn.setBounds(464, 0, 70, 20);
			return warn;*/
		}
		return horaFimComboBox;
	}
	
	protected JLabel getHoraFimLabel(){
		if(horaFimLabel == null){
			horaFimLabel = new JLabel("Hora Final:");
			horaFimLabel.setSize(new Dimension(66, 20));
			horaFimLabel.setLocation(new Point(330, 0));
			horaFimLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return horaFimLabel;
	}
	
	protected JPanel getInicioTerminoPrevistoPanel(){
		if(inicioTerminoPrevistoPanel == null){
			inicioTerminoPrevistoPanel = new JPanel();
			inicioTerminoPrevistoPanel.setLayout(null);
			inicioTerminoPrevistoPanel.setBounds(new Rectangle(260, 64, 296, 40));
			inicioTerminoPrevistoPanel.add(getDataInicialField());
			inicioTerminoPrevistoPanel.add(getDataInicialLabel());
			inicioTerminoPrevistoPanel.add(getDataFinalField());
			inicioTerminoPrevistoPanel.add(getDataFinalLabel());
		}
		return inicioTerminoPrevistoPanel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getDataInicialField(){
		if(dataInicialField == null){
			dataInicialField = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			//inicioPrevistoDateFieldChooser.setText("");
			dataInicialField.setSize(new Dimension(145, 20));
			dataInicialField.setLocation(new Point(0, 20));
			this.binder.addBindProperty(this.agenda,this.dataInicialField, "inicioPrevisto");

			this.hashComps.put("inicioPrevisto", this.dataInicialField);
			JWarningComponent warn = new JWarningComponent(this.dataInicialField);
			warn.setBounds(0, 20, 145, 20);
			return warn;
		}
		return dataInicialField;
	}
	
	protected JLabel getDataInicialLabel(){
		if(dataInicialLabel == null){
			dataInicialLabel = new JLabel("Data Inicial:");
			dataInicialLabel.setSize(new Dimension(122, 20));
			dataInicialLabel.setLocation(new Point(0, 0));
			dataInicialLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return dataInicialLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getDataFinalField(){
		if(dataFinalField == null){
			dataFinalField = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			dataFinalField.setSize(new Dimension(145, 20));
			dataFinalField.setLocation(new Point(146, 20));
			this.binder.addBindProperty(this.agenda,this.dataFinalField, "terminoPrevisto");

			this.hashComps.put("terminoPrevisto", this.dataFinalField);
			JWarningComponent warn = new JWarningComponent(this.dataFinalField);
			warn.setBounds(146, 20, 145, 20);
			return warn;
		}
		return dataFinalField;
	}
	
	protected JLabel getDataFinalLabel(){
		if(dataFinalLabel == null){
			dataFinalLabel = new JLabel("Data Final:");
			dataFinalLabel.setSize(new Dimension(122, 20));
			dataFinalLabel.setLocation(new Point(146, 0));
			dataFinalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return dataFinalLabel;
	}
	
	protected JPanel getInicioPanel(){
		if(inicioPanel == null){
			arrematanteLabel = new JLabel();
			arrematanteLabel.setBounds(new Rectangle(285, 0, 113, 20));
			arrematanteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			arrematanteLabel.setText("Arrematante:");
			inicioPanel = new JPanel();
			inicioPanel.setLayout(null);
			inicioPanel.setBounds(new Rectangle(15, 15, 541, 24));
			inicioPanel.add(getNumeroLancesLabel());
			inicioPanel.add(getLanceVencedor());
			inicioPanel.add(getLanceVencedorLabel());
			inicioPanel.add(arrematanteLabel, null);
			inicioPanel.add(getArrematanteTextField(), null);
			inicioPanel.add(getNumeroLancesTextField(), null);
		}
		return inicioPanel;
	}
	
	private JPanel inicioPanel2;
	protected JPanel getInicioPanel2(){
		if(inicioPanel2 == null){
			arrematanteLabel = new JLabel();
			arrematanteLabel.setBounds(new Rectangle(285, 0, 113, 20));
			arrematanteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			arrematanteLabel.setText("Arrematante:");
			inicioPanel2 = new JPanel();
			inicioPanel2.setLayout(null);
			inicioPanel2.setBounds(new Rectangle(15, 15, 541, 24));
		}
		return inicioPanel2;
	}
	

	private JTextField getArrematanteTextField() {
		if (arrematanteTextField == null) {
			arrematanteTextField = new JTextField();
			arrematanteTextField.setBounds(new Rectangle(405, 0, 125, 20));
			arrematanteTextField.setEditable(false);
		}
		return arrematanteTextField;
	}
	
	protected JLabel getNumeroLancesLabel(){
		if(numeroLancesLabel == null){
			numeroLancesLabel = new JLabel("Número de Lances:");
			numeroLancesLabel.setSize(new Dimension(100, 20));
			numeroLancesLabel.setHorizontalAlignment(SwingConstants.LEFT);
			numeroLancesLabel.setLocation(new Point(0, 0));
		}
		return numeroLancesLabel;
	}
	
	protected JTextField numeroLancesTextField;
	protected JTextField getNumeroLancesTextField(){
		if(numeroLancesTextField == null){
			numeroLancesTextField = new JTextField();
			numeroLancesTextField.setEditable(false);
			numeroLancesTextField.setSize(new Dimension(35, 20));
			numeroLancesTextField.setLocation(new java.awt.Point(100,0));
			
		}
		return numeroLancesTextField;
	}
	
	private JLabel lanceVencedorLabel;
	
	protected JLabel getLanceVencedorLabel(){
		if(lanceVencedorLabel == null){
			lanceVencedorLabel = new JLabel("Maior Lance:");
			lanceVencedorLabel.setSize(new Dimension(70, 20));
			lanceVencedorLabel.setLocation(new java.awt.Point(155,0));
			
		}
		return lanceVencedorLabel;
	}
	
	protected JTextField getLanceVencedor(){
		if(lanceVencedor == null){
			lanceVencedor = new JTextField();
			lanceVencedor.setEditable(false);
			lanceVencedor.setSize(new Dimension(100, 20));
			lanceVencedor.setLocation(new java.awt.Point(220,0));
			
		}
		return lanceVencedor;
	}
	
	protected JPanel getButtonsPanel(){
		if(buttonsPanel == null){
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(2);
			flowLayout.setVgap(2);
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(flowLayout);
			buttonsPanel.setSize(new Dimension(566, 49));
			buttonsPanel.setLocation(new Point(2, 140));
			buttonsPanel.add(getAtualizarButton());
			//buttonsPanel.add(getCondicaoPagtoButton(), null);
			buttonsPanel.add(getConfirmarButton());
			buttonsPanel.add(getCancelarButton());
			//buttonsPanel.add(getNovaAgendaButton(), null);
			buttonsPanel.add(getListarAgendasButton(), null);
			//buttonsPanel.add(getAgendarSegundoLeilaoButton(), null);			
		}
		return buttonsPanel;
	}
	
	protected JButton getAtualizarButton(){
		if(atualizarButton == null){
			atualizarButton = new JButton("Cadastrar");
			atualizarButton.setSize(new java.awt.Dimension(150,20));
			atualizarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_save.png")));
			atualizarButton.setLocation(new java.awt.Point(0,0));
			atualizarButton.addActionListener(new AbstractAction() {
				public void doAction(java.awt.event.ActionEvent e) {
					try {
						cadastreAgendaTreinamento();
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e1) {
						e1.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problemas para cadastrar/atualizar a agenda", "Não foi possível cadastrar ou atualizar a agenda");
					}
				}
			});
		}
		return atualizarButton;
	}

	
	public void setErrorIcon(boolean bool) {
		turnoComboBox.firePropertyChange("warnBorder",!bool, bool);
		dataInicialField.firePropertyChange("warnBorder",!bool, bool);
		dataFinalField.firePropertyChange("warnBorder",!bool, bool);
		this.getErrorPanel().setVisible(false);
	}
	
	private AgendaTreinamento validateAgendaTreinamento() throws Exception{
		setErrorIcon(false);
		clearErrorList();
		boolean invalid=false;
		agenda.setTreinamento(treinamento);
		agenda.setEncerramento(null);
		
		try {
			agenda.setTurno(TurnoTreinamento.valueOf(((String)turnoComboBox.getSelectedItem()).replaceAll(" ","_")));
			agenda.setStatus(StatusAgenda.valueOf(((String)statusAgendaComboBox.getSelectedItem()).replaceAll(" ","_")));
			
			if (!dataInicialField.getDateHourField().getText().equals(""))
				agenda.setInicioPrevisto(dataInicialField.getDate());
			if (agenda.getInicioPrevisto() == null){ 
				reportInvalidField("inicioPrevisto","não pode ser vazia");
				invalid=true;
			}
			if (!dataFinalField.getDateHourField().getText().equals(""))
				agenda.setTerminoPrevisto(dataFinalField.getDate());
			if (agenda.getTerminoPrevisto() == null){
				reportInvalidField("terminoPrevisto","não pode ser vazia");
				invalid=true;
			}
			
			agenda.setHoraFim((String)horaFimComboBox.getSelectedItem());
			agenda.setHoraInicio((String)horaInicioComboBox.getSelectedItem());
			agenda.setIntervalo((String)intervaloComboBox.getSelectedItem());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
				
		if (invalid){
			showErrorList();
			throw new Exception("Bean Not Validated!");
		}
		return agenda;
	}
	
	private AgendaTreinamento cadastreAgendaTreinamento() throws Exception{
		AgendaTreinamento a = validateAgendaTreinamento();
		try{			
			a = RemoteAgendaTreinamentoService.getInstance().saveOrUpdateMergeScheduledTrainingId(a, treinamento.getId());
			treinamento = a.getTreinamento();
			getAtualizarButton().setText("Atualizar");			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return a;
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
			String msg = "agendaTreinamento."+name ;
			try {
				getErrorPanel()
							.addError(messages.getMessage(msg)+ " " + errorMsg, comp);
				comp.setToolTipText(messages.getMessage(msg)+ " " + errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else{
			String msg = "agendaTreinamento."+name;
			try {
				getErrorPanel().addError(messages.getMessage(msg)+" "+errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("unchecked")
	public boolean validateAgendaTreinamentoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.agenda,	"agendaTreinamento");
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
	
	protected JButton getConfirmarButton(){

		if(agendarButton == null){
			agendarButton = new JButton("Confirmar Agenda");//messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaTreinamentoCadastreForm.ConfirmaroIníciodoLeilão"));
			agendarButton.setSize(new java.awt.Dimension(80,22));
			agendarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/clock_play.png")));
			agendarButton.setLocation(new java.awt.Point(0,20));
			agendarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						agenda.setTreinamento(treinamento);
						agenda.setConfirmada(true);
						agenda.setStatus(StatusAgenda.Confirmada);
						TurnoTreinamento chamada = TurnoTreinamento.valueOf(((String)turnoComboBox.getSelectedItem()).replaceAll(" ","_"));
						agenda.setTurno(chamada);						
						RemoteAgendaTreinamentoService.getInstance().confirmTrainingSchedule(agenda, treinamento, chamada);
						editRegister(agenda);
						AdapitVirtualFrame.getInstance().showOperationSucess();
					}catch(Exception ex){
						ex.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problemas para atualizar a agenda", "Não foi possível confirmar a agenda");
					}
					
				}
			});
		}
		return agendarButton;
	}
	
	protected JButton getCancelarButton(){

		if(cancelarButton == null){
			cancelarButton = new JButton("Cancelar Agenda");
			cancelarButton.setSize(new java.awt.Dimension(80,22));
			cancelarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/clock_stop.png")));
			//cancelarButton.setEnabled(false);
			cancelarButton.setLocation(new java.awt.Point(0,42));
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						agenda.setConfirmada(false);
						agenda.setStatus(StatusAgenda.Cancelada);
						RemoteAgendaTreinamentoService.getInstance().cancelTrainingSchedule(agenda);
						editRegister(agenda);
						AdapitVirtualFrame.getInstance().showOperationSucess();
					}catch(Exception ex){
						ex.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao cancelar a agenda", "Não foi possível cancelar a agenda especificada!");
					}
				}
			});
		}
		return cancelarButton;
	}
	
	/**
	 * This method initializes incrementoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */

	@SuppressWarnings("unchecked")
	private JComboBox getHoraInicioComboBox() {
		if (horaInicioComboBox == null) {
			horaInicioComboBox = new JComboBox();
			horaInicioComboBox.setEditable(true);
			horaInicioComboBox.setBounds(new Rectangle(410, 0, 130, 20));
			horaInicioComboBox.addItem("7");
			horaInicioComboBox.addItem("7:30");
			horaInicioComboBox.addItem("8");
			horaInicioComboBox.addItem("8:30");
			horaInicioComboBox.addItem("10");
			horaInicioComboBox.addItem("13");
			horaInicioComboBox.addItem("13:30");
			horaInicioComboBox.addItem("14");
			horaInicioComboBox.addItem("18");
			horaInicioComboBox.addItem("18:30");
			horaInicioComboBox.addItem("19");
			
			/*horaInicioTextField.setHorizontalAlignment(JTextField.RIGHT);
			this.binder.addBindProperty(this.agenda,this.horaInicioTextField, "horaInicio");

			this.hashComps.put("horaInicio", this.horaInicioTextField);
			JWarningComponent warn = new JWarningComponent(this.horaInicioTextField);
			warn.setBounds(new Rectangle(94, 0, 70, 20));
			return warn;*/
		}
		return horaInicioComboBox;
	}
	

	/**
	 * This method initializes chamadaLeilaoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getTurnoComboBox() {
		if (turnoComboBox == null) {
			turnoComboBox = new JComboBox();
			turnoComboBox.setBounds(new Rectangle(15, 84, 180, 20));
			for (int i=0; i < TurnoTreinamento.values().length; i++){
				turnoComboBox.addItem(TurnoTreinamento.values()[i].name().replaceAll("_"," "));
			}
			this.binder.addBindProperty(this.agenda,this.turnoComboBox, "turno");

			this.hashComps.put("turno", this.turnoComboBox);
			JWarningComponent warn = new JWarningComponent(this.turnoComboBox);
			warn.setBounds(15, 84, 180, 20);
			return warn;
		}
		return turnoComboBox;
	}

	

	/**
	 * This method initializes novaAgendaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovaAgendaButton() {
		if (novaAgendaButton == null) {
			novaAgendaButton = new JButton();
			novaAgendaButton.setText("Nova Agenda");
			novaAgendaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date.png")));
			novaAgendaButton.setEnabled(false);
			novaAgendaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try{
						RemoteAgendaTreinamentoService.getInstance().generateScheduleHistoryByScheduledTrainingId(treinamento.getId());
						newRegister();						
					}catch(Exception ex){
						ex.printStackTrace();
					}					
				}
			});
		}
		return novaAgendaButton;
	}

	/**
	 * This method initializes errorPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private LogErrorPanel getErrorPanel() {
		if (errorPanel == null) {
			errorPanel = new LogErrorPanel();
			errorPanel.setBounds(new Rectangle(15, 189, 538, 54));
			errorPanel.setVisible(false);
		}
		return errorPanel;
	}




	private Encerramento encerramento;
	
	public void updateEncerramento(int id){
		try {
			encerramento = RemoteAgendaTreinamentoService.getInstance().loadEncerramento(id);
			if (encerramento != null){
				//encerramento.getLanceVencedor();
				/*if (observer != null) observer.getLanceAgendaButton().setEnabled(false);*/
			}
			else{
				/*if (observer != null) observer.getLanceAgendaButton().setEnabled(true);*/
			}
		} catch (Exception e) {
			/*if (observer != null) observer.getLanceAgendaButton().setEnabled(true);*/
		}
	}


	
	/**
	 * This method initializes listarAgendasButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarAgendasButton() {
		if (listarAgendasButton == null) {
			listarAgendasButton = new JButton();
			listarAgendasButton.setText("Histórico");
			listarAgendasButton.setEnabled(false);
			listarAgendasButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AgendaTreinamentoListDialog dialog = new AgendaTreinamentoListDialog();
					
					dialog.setTreinamento(treinamento);
					dialog.setVisible(true);
				}
			});
		}
		return listarAgendasButton;
	}

	/**
	 * This method initializes statusAgendaComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getStatusAgendaComboBox() {
		if (statusAgendaComboBox == null) {
			statusAgendaComboBox = new JComboBox();
			statusAgendaComboBox.setBounds(new Rectangle(230, 110, 233, 20));
			for (int i=0; i < StatusAgenda.values().length; i++){
				String str = StatusAgenda.values()[i].name().replace("_", " ");
				statusAgendaComboBox.addItem(str);
			}
		}
		return statusAgendaComboBox;
	}


	/**
	 * This method initializes intervaloTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getIntervaloComboBox() {
		if (intervaloComboBox == null) {
			intervaloComboBox = new JComboBox();
			intervaloComboBox.setBounds(new Rectangle(91, 0, 233, 20));
			intervaloComboBox.setEditable(true);
			intervaloComboBox.addItem("Vinte Minutos, das  Até as Horas");
			intervaloComboBox.addItem("Quinze Minutos, das  Até as Horas");
			intervaloComboBox.addItem("Dez Minutos, das  Até as Horas");
			intervaloComboBox.addItem("Meia Hora, das  Até as Horas");
			/*
			this.binder.addBindProperty(this.agenda,this.intervaloTextField, "intervalo");

			this.hashComps.put("intervalo", this.intervaloTextField);
			JWarningComponent warn = new JWarningComponent(this.intervaloTextField);
			warn.setBounds(new Rectangle(227, 0, 166, 20));
			return warn;*/
		}
		return intervaloComboBox;
	}

	/**
	 * This method initializes descontoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
/*	private JTextField getDescontoTextField() {
		if (descontoTextField == null) {
			descontoTextField = new JTextField();
			descontoTextField.setBounds(new Rectangle(419, 0, 121, 20));
			descontoTextField.setEditable(false);
			descontoTextField.setHorizontalAlignment(JTextField.RIGHT);
		}
		return descontoTextField;
	}*/

	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(419,322));
					gui.add(new AgendaTreinamentoCadastreForm(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}
	
	@SuppressWarnings("unused")
	private static Icon getIcon(String name ){

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL).getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	public AgendaTreinamento getAgenda() {
		return agenda;
	}

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento treinamento) {
		this.treinamento = treinamento;
	}
	
	public void editRegister(AgendaTreinamento agendaTreinamento) throws Exception{	
		if (treinamento == null) throw new Exception("The Schedule cannot be null!!");
		dataInicialField.getCalendarButton().setEnabled(true);
		dataFinalField.getCalendarButton().setEnabled(true);
		try{
			agenda.setId(agendaTreinamento.getId());
			AgendaTreinamento al = (AgendaTreinamento) RemoteServicesUtility.getInstance().refresh(agenda);//s.refresh(agenda);
			agenda.setTurno(al.getTurno());
			agenda.setConfirmada(al.isConfirmada());
			agenda.setEncerrada(al.isEncerrada());
			agenda.setInicio(al.getInicio());
			agenda.setInicioPrevisto(al.getInicioPrevisto());
			agenda.setRecebendoInteressados(al.isRecebendoInteressados());
			agenda.setStatus(al.getStatus());
			agenda.setTermino(al.getTermino());
			agenda.setTerminoPrevisto(al.getTerminoPrevisto());
			agenda.getId();	
			agenda.setEncerrada(al.isEncerrada());			
			agenda.setHoraFim(al.getHoraFim());			
			agenda.setHoraInicio(al.getHoraInicio());			
			agenda.setIntervalo(al.getIntervalo());
						
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		updateFields();


	}
	
	private void updateFields(){
		
		if (agenda.getHoraFim() != null)
			horaFimComboBox.setSelectedItem(agenda.getHoraFim());
		else horaFimComboBox.setSelectedIndex(0);
		
		if (agenda.getHoraInicio() != null)
			horaInicioComboBox.setSelectedItem(agenda.getHoraInicio());
		else horaInicioComboBox.setSelectedIndex(0);
		
		if (agenda.getIntervalo() != null)
			intervaloComboBox.setSelectedItem(agenda.getIntervalo());
		else intervaloComboBox.setSelectedIndex(0);
		
		
		
		if (agenda.getStatus() != null && agenda.getStatus() == StatusAgenda.Cancelada){
			getNovaAgendaButton().setEnabled(true);
			getAtualizarButton().setEnabled(true);
			getAtualizarButton().setText("Atualizar");
			getCancelarButton().setEnabled(false);
			getConfirmarButton().setEnabled(true);
		}else if (agenda.getStatus() != null && agenda.getStatus() == StatusAgenda.Cadastrada){
			getNovaAgendaButton().setEnabled(false);
			getAtualizarButton().setEnabled(true);
			getAtualizarButton().setText("Atualizar");
			getConfirmarButton().setEnabled(true);
			getCancelarButton().setEnabled(true);
		}else if (agenda.getStatus() != null && agenda.getStatus() == StatusAgenda.Confirmada){
			getNovaAgendaButton().setEnabled(false);
			getAtualizarButton().setEnabled(true);
			getAtualizarButton().setText("Atualizar");
			getConfirmarButton().setEnabled(false);
			getCancelarButton().setEnabled(true);
			dataInicialField.getCalendarButton().setEnabled(true);
			dataFinalField.getCalendarButton().setEnabled(true);
		}else{
			getNovaAgendaButton().setEnabled(false);
			getAtualizarButton().setEnabled(true);
			getAtualizarButton().setText("Cadastrar");
			getConfirmarButton().setEnabled(false);
			getCancelarButton().setEnabled(false);
		}
		
		try {
			getCodTreinamentoTextField().setText(treinamento.getCodigo());
			getCargaHorariaTreinamentoTextField().setText(""+treinamento.getTreinamento().getCargaHoraria());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		dataInicialField.setDate(agenda.getInicioPrevisto());
		dataFinalField.setDate(agenda.getTerminoPrevisto());
		try {
			turnoComboBox.setSelectedItem(agenda.getTurno().name().replaceAll("_"," "));
		} catch (Exception e1) {
			e1.printStackTrace();
			turnoComboBox.setSelectedIndex(0);
		}
				
		
		try {
			int size = (Integer) RemoteAgendaTreinamentoService.getInstance().getScheduledTrainingScheduleNumber(agenda.getId()); 
			if (size > 1){
				getListarAgendasButton().setEnabled(true);
			}else{
				getListarAgendasButton().setEnabled(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (agenda.getStatus() != null)
			getStatusAgendaComboBox().setSelectedItem(agenda.getStatus().name().replace("_", " "));			
		
		
		if (agenda.getId() == 0)
			atualizarButton.setText("Cadastrar");
		else atualizarButton.setText("Atualizar");
	}

	public void newRegister() throws Exception{
		if (treinamento == null) throw new Exception("The Schedule cannot be null!!");
		agenda.setId(0);
		agenda.setTurno(TurnoTreinamento.Manhã);
		agenda.setConfirmada(false);
		agenda.setEncerrada(false);
		agenda.setEncerramento(null);
		agenda.setInicio(null);
		agenda.setInicioPrevisto(null);
		
		agenda.setTreinamento(treinamento);
		agenda.setObs(null);
		agenda.setStatus(StatusAgenda.Não_cadastrada);
		agenda.setTermino(null);
		agenda.setTerminoPrevisto(null);
		
		updateFields();
		
		dataInicialField.getDateHourField().setText("");
		dataFinalField.getDateHourField().setText("");
		
		dataInicialField.getCalendarButton().setEnabled(true);
		dataFinalField.getCalendarButton().setEnabled(true);	
		
	}
	

	private JLabel statusLabel = null;

	private JComboBox statusAgendaComboBox = null;
	

	private ScheduledTrainingCadastreForm observer;

	//private JButton condicaoPagtoButton = null;

	private JTextField descontoTextField = null;

	private JLabel porcentoValorLabel = null;

	private JLabel intervaloLabel = null;

	private JComboBox intervaloComboBox = null;

	public void setTreinamentoCadastreForm(ScheduledTrainingCadastreForm treinamentoCadastreForm) {
		observer = treinamentoCadastreForm;
		codTreinamentoTextField.setText(observer.getScheduledTraining().getCodigo());
		try {
			if (observer.getScheduledTraining().getTreinamento() != null)
				cargaHorariaTreinamentoTextField.setText(""+observer.getScheduledTraining().getTreinamento().getCargaHoraria());
		} catch (RuntimeException e) {
			// 
			e.printStackTrace();
		}
		this.treinamento = observer.getScheduledTraining();
	}

}  //  @jve:decl-index=0:visual-constraint="19,10"