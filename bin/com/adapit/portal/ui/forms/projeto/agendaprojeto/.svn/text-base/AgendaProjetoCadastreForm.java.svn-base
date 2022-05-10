package com.adapit.portal.ui.forms.projeto.agendaprojeto;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.hibernate.Session;

import com.adapit.portal.entidades.AgendaProjeto;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.entidades.StatusAgenda;
import com.adapit.portal.services.local.LocalServicesUtility;
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
public class AgendaProjetoCadastreForm extends JPanel{
	
	private JPanel ProjetoDataPanel;
	
	private JTextField codProjetoTextField;
	
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private Map hashComps = new java.util.HashMap();
	
	private JLabel codProjetoTextField4Label;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField valorProjetoTextField;
	
	private JLabel valorProjetoTextField5Label;
	
	private JPanel valorLancesPanel;
	
	private JTextField lanceInicialTextField;
	
	/*private AgendaProjeto AgendaProjeto = new AgendaProjeto();*/
	
	private JLabel laceInicialTextFieldLabel;
	
	private JTextField lanceMinimoTextField;
	
	private JLabel lanceMinimoTextFieldLabel;
	
	private JPanel inicioTerminoPrevistoPanel;
	
	private DateHourChooser inicioPrevistoDateFieldChooser;
	
	private JLabel inicioPrevistoDateFieldChooserLabel;
	
	private DateHourChooser terminoPrevistoDateFieldChooser;
	
	private JLabel terminoPrevistoDateFieldChooserLabel;
	
	private JPanel inicioPanel;
	
	private JLabel inicioLabelComponent;
	
	private DateHourChooser inicioDateFieldChooser;
	
	private JPanel buttonsPanel;
	
	private JButton atualizarButton;
	
	private JButton agendarButton;
	
	private JButton cancelarButton;
	
	private JLabel obsLabel;
	
	private JScrollPane obsScrollPane;

	private JLabel terminoLabel = null;

	private DateHourChooser terminoLeilaoDateChooser = null;

	private JLabel incrementoLabel = null;

	private JTextField incrementoTextField = null;

	private JLabel chamadaLeilaoLabel = null;

	private JComboBox chamadaLeilaoComboBox = null;

	private JTextArea obsTextArea = null;

	private JButton agendarSegundoLeialoButton = null;

	private JButton notificarDesistenciaButton = null;

	private JButton verDadosArrematanteButton = null;

	private JButton novaAgendaButton = null;

	private LogErrorPanel errorPanel = null;

	private AgendaProjeto agenda = new AgendaProjeto();  //  @jve:decl-index=0:
	
	private Projeto projeto;  //  @jve:decl-index=0:
	
	private boolean processFocus = true;
	
	public AgendaProjetoCadastreForm(){	
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
			tabbedPane.add(getAgendaCadastrePanel(),"Dados da Agenda");
			tabbedPane.addTab("Lances", null, getLancesPanel(), null);
			tabbedPane.addTab("Encerramento", null, getEncerramentoPanel(), null);
		}
		return tabbedPane;
	}
	
	private JPanel agendaCadastrePanel;

	private JPanel lancesPanel = null;

	private JPanel encerramentoPanel = null;

	private JPanel lancesButtonPanel = null;

	private JButton listarButton = null;

	private JButton verContasPagarArrematanteButton = null;

	private JButton verHistoricoClienteSelecionadoButton = null;

	private JButton verDadosComitenteButton = null;

	private JButton listarAgendasButton = null;
	
	private JPanel getAgendaCadastrePanel(){
		if (agendaCadastrePanel == null){
			agendaCadastrePanel = new JPanel();
			chamadaLeilaoLabel = new JLabel();
			chamadaLeilaoLabel.setBounds(new Rectangle(15, 84, 99, 20));
			chamadaLeilaoLabel.setHorizontalAlignment(SwingConstants.LEFT);
			chamadaLeilaoLabel.setText("Chamada do Leilão:");
			incrementoLabel = new JLabel();
			incrementoLabel.setBounds(new Rectangle(15, 61, 124, 20));
			incrementoLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			incrementoLabel.setText("Incremento (R$):");
			agendaCadastrePanel.setSize(new Dimension(566, 347));
			agendaCadastrePanel.setLocation(new java.awt.Point(0,0));
			agendaCadastrePanel.setLayout(null);
			agendaCadastrePanel.add(getProjetoDataPanel());
			agendaCadastrePanel.add(getValorLancesPanel());
			agendaCadastrePanel.add(getButtonsPanel());
			agendaCadastrePanel.add(getObsLabel());
			agendaCadastrePanel.add(getObsScrollPane());
			agendaCadastrePanel.add(getInicioTerminoPrevistoPanel(), null);
			agendaCadastrePanel.add(incrementoLabel, null);
			agendaCadastrePanel.add(getIncrementoTextField(), null);
			agendaCadastrePanel.add(chamadaLeilaoLabel, null);
			
			agendaCadastrePanel.add(getErrorPanel(), null);
			agendaCadastrePanel.add(getClockLabel());
		}
		return agendaCadastrePanel;
	}
	
	private /*Clock*/JLabel clockLabel;
	
	private JLabel getClockLabel(){
		/*clockLabel = new JLabel("");
		clockLabel.setText("Tempo decorrido");
		clockLabel.setSize(290,20);
		clockLabel.setLocation(260, 104);*/
		
		if (clockLabel == null){
			clockLabel = new ClockLabel("");
			clockLabel.setText("Tempo decorrido");
			clockLabel.setSize(290,20);
			clockLabel.setLocation(260, 105);
		}
		return clockLabel;
	}
	
	protected JPanel getProjetoDataPanel(){
		if(ProjetoDataPanel == null){
			ProjetoDataPanel = new JPanel();
			ProjetoDataPanel.setSize(new Dimension(541, 21));
			ProjetoDataPanel.setLocation(new Point(15, 15));
			ProjetoDataPanel.setLayout(null);
			ProjetoDataPanel.add(getCodProjetoTextField());
			ProjetoDataPanel.add(getCodProjetoTextField4Label());
			ProjetoDataPanel.add(getValorProjetoTextField());
			ProjetoDataPanel.add(getValorProjetoTextField5Label());
		}
		return ProjetoDataPanel;
	}
	
	protected JTextField getCodProjetoTextField(){
		if(codProjetoTextField == null){
			codProjetoTextField = new JTextField();
			codProjetoTextField.setText("");
			codProjetoTextField.setSize(new Dimension(115, 20));
			codProjetoTextField.setEditable(false);
			codProjetoTextField.setLocation(new Point(125, 0));
			return codProjetoTextField;
		}
		return codProjetoTextField;
	}
	
	protected JLabel getCodProjetoTextField4Label(){
		if(codProjetoTextField4Label == null){
			codProjetoTextField4Label = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.CódigodoProjeto"));
			codProjetoTextField4Label.setSize(new Dimension(123, 20));
			codProjetoTextField4Label.setLocation(new java.awt.Point(0,0));
			codProjetoTextField4Label.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_go.png")));
			codProjetoTextField4Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return codProjetoTextField4Label;
	}
	
	protected JTextField getValorProjetoTextField(){

		if(valorProjetoTextField == null){
			valorProjetoTextField = new JTextField();
			valorProjetoTextField.setText("");
			valorProjetoTextField.setSize(new Dimension(115, 20));
			valorProjetoTextField.setEditable(false);
			valorProjetoTextField.setHorizontalAlignment(JTextField.RIGHT);
			valorProjetoTextField.setLocation(new Point(420, 0));
			return valorProjetoTextField;
		}
		return valorProjetoTextField;
	}
	
	protected JLabel getValorProjetoTextField5Label(){
		if(valorProjetoTextField5Label == null){
			valorProjetoTextField5Label = new JLabel("Valor do Projeto (R$):");
			valorProjetoTextField5Label.setSize(new Dimension(125, 20));
			valorProjetoTextField5Label.setLocation(new Point(294, 0));
			valorProjetoTextField5Label.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			valorProjetoTextField5Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return valorProjetoTextField5Label;
	}
	
	protected JPanel getValorLancesPanel(){
		if(valorLancesPanel == null){
			valorLancesPanel = new JPanel();
			valorLancesPanel.setSize(new Dimension(540, 21));
			valorLancesPanel.setLocation(new Point(15, 38));
			valorLancesPanel.setLayout(null);
			valorLancesPanel.add(getLanceInicialTextField());
			valorLancesPanel.add(getLaceInicialTextFieldLabel());
			valorLancesPanel.add(getLanceMinimoTextField());
			valorLancesPanel.add(getLanceMinimoTextFieldLabel());
		}
		return valorLancesPanel;
	}
	
	protected JComponent getLanceInicialTextField(){
		if(lanceInicialTextField == null){
			lanceInicialTextField = new JTextField();
			lanceInicialTextField.setText("");
			lanceInicialTextField.setSize(new java.awt.Dimension(115,20));
			lanceInicialTextField.setHorizontalAlignment(JTextField.RIGHT);
			lanceInicialTextField.setLocation(new java.awt.Point(125,0));
			this.binder.addBindProperty(this.agenda,this.lanceInicialTextField, "lanceInicial");

			this.hashComps.put("lanceInicial", this.lanceInicialTextField);
			JWarningComponent warn = new JWarningComponent(this.lanceInicialTextField);
			warn.setBounds(125, 0, 115, 20);
			return warn;
		}
		return lanceInicialTextField;
	}
	
	protected JLabel getLaceInicialTextFieldLabel(){
		if(laceInicialTextFieldLabel == null){
			laceInicialTextFieldLabel = new JLabel("Lance Inicial (R$):");
			laceInicialTextFieldLabel.setSize(new Dimension(124, 20));
			laceInicialTextFieldLabel.setLocation(new java.awt.Point(0,0));
			laceInicialTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			laceInicialTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return laceInicialTextFieldLabel;
	}
	
	protected JComponent getLanceMinimoTextField(){
		if(lanceMinimoTextField == null){
			lanceMinimoTextField = new JTextField();
			lanceMinimoTextField.setText("");
			lanceMinimoTextField.setSize(new java.awt.Dimension(115,20));
			lanceMinimoTextField.setHorizontalAlignment(JTextField.RIGHT);
			lanceMinimoTextField.setLocation(new java.awt.Point(420,0));
			this.binder.addBindProperty(this.agenda,this.lanceMinimoTextField, "lanceMinimo");

			this.hashComps.put("lanceMinimo", this.lanceMinimoTextField);
			JWarningComponent warn = new JWarningComponent(this.lanceMinimoTextField);
			warn.setBounds(420, 0, 115, 20);
			return warn;
		}
		return lanceMinimoTextField;
	}
	
	protected JLabel getLanceMinimoTextFieldLabel(){
		if(lanceMinimoTextFieldLabel == null){
			lanceMinimoTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.LanceMínimo(R$)"));
			lanceMinimoTextFieldLabel.setSize(new Dimension(124, 20));
			lanceMinimoTextFieldLabel.setLocation(new Point(295, 0));
			lanceMinimoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			lanceMinimoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return lanceMinimoTextFieldLabel;
	}
	
	protected JPanel getInicioTerminoPrevistoPanel(){
		if(inicioTerminoPrevistoPanel == null){
			inicioTerminoPrevistoPanel = new JPanel();
			inicioTerminoPrevistoPanel.setLayout(null);
			inicioTerminoPrevistoPanel.setBounds(new Rectangle(260, 64, 290, 40));
			inicioTerminoPrevistoPanel.add(getInicioPrevistoDateFieldChooser());
			inicioTerminoPrevistoPanel.add(getInicioPrevistoDateFieldChooserLabel());
			inicioTerminoPrevistoPanel.add(getTerminoPrevistoDateFieldChooser());
			inicioTerminoPrevistoPanel.add(getTerminoPrevistoDateFieldChooserLabel());
		}
		return inicioTerminoPrevistoPanel;
	}
	
	protected JComponent getInicioPrevistoDateFieldChooser(){
		if(inicioPrevistoDateFieldChooser == null){
			inicioPrevistoDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			//inicioPrevistoDateFieldChooser.setText("");
			inicioPrevistoDateFieldChooser.setSize(new Dimension(145, 20));
			inicioPrevistoDateFieldChooser.setLocation(new Point(0, 20));
			//this.binder.addBindProperty(this.agenda,this.inicioPrevistoDateFieldChooser, "inicioPrevisto");

			this.hashComps.put("inicioPrevisto", this.inicioPrevistoDateFieldChooser);
			JWarningComponent warn = new JWarningComponent(this.inicioPrevistoDateFieldChooser);
			warn.setBounds(0, 20, 145, 20);
			return warn;
		}
		return inicioPrevistoDateFieldChooser;
	}
	
	protected JLabel getInicioPrevistoDateFieldChooserLabel(){
		if(inicioPrevistoDateFieldChooserLabel == null){
			inicioPrevistoDateFieldChooserLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.InícioPrevisto"));
			inicioPrevistoDateFieldChooserLabel.setSize(new Dimension(122, 20));
			inicioPrevistoDateFieldChooserLabel.setLocation(new Point(0, 0));
			inicioPrevistoDateFieldChooserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return inicioPrevistoDateFieldChooserLabel;
	}
	
	protected JComponent getTerminoPrevistoDateFieldChooser(){
		if(terminoPrevistoDateFieldChooser == null){
			terminoPrevistoDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			terminoPrevistoDateFieldChooser.setSize(new Dimension(145, 20));
			terminoPrevistoDateFieldChooser.setLocation(new Point(146, 20));
			//this.binder.addBindProperty(this.agenda,this.terminoPrevistoDateFieldChooser, "terminoPrevisto");

			this.hashComps.put("terminoPrevisto", this.terminoPrevistoDateFieldChooser);
			JWarningComponent warn = new JWarningComponent(this.terminoPrevistoDateFieldChooser);
			warn.setBounds(146, 20, 145, 20);
			return warn;
		}
		return terminoPrevistoDateFieldChooser;
	}
	
	protected JLabel getTerminoPrevistoDateFieldChooserLabel(){
		if(terminoPrevistoDateFieldChooserLabel == null){
			terminoPrevistoDateFieldChooserLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.TérminoPrevisto"));
			terminoPrevistoDateFieldChooserLabel.setSize(new Dimension(122, 20));
			terminoPrevistoDateFieldChooserLabel.setLocation(new Point(146, 0));
			terminoPrevistoDateFieldChooserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return terminoPrevistoDateFieldChooserLabel;
	}
	
	protected JPanel getInicioPanel(){
		if(inicioPanel == null){
			terminoLabel = new JLabel();
			terminoLabel.setBounds(new Rectangle(265, 0, 113, 20));
			terminoLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			terminoLabel.setText("Término do Leilão:");
			inicioPanel = new JPanel();
			inicioPanel.setLayout(null);
			inicioPanel.setBounds(new Rectangle(15, 15, 541, 24));
			inicioPanel.add(getInicioLabelComponent());
			inicioPanel.add(getInicioDateFieldChooser());
			inicioPanel.add(terminoLabel, null);
			inicioPanel.add(getTerminoLeilaoDateChooser(), null);
		}
		return inicioPanel;
	}
	
	protected JLabel getInicioLabelComponent(){
		if(inicioLabelComponent == null){
			inicioLabelComponent = new JLabel("Hora do Primeiro Lance:");//messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.Início")+" Confirmado:");
			inicioLabelComponent.setSize(new Dimension(120, 20));
			inicioLabelComponent.setHorizontalAlignment(SwingConstants.LEFT);
			inicioLabelComponent.setLocation(new Point(0, 0));
		}
		return inicioLabelComponent;
	}
	
	protected DateHourChooser getInicioDateFieldChooser(){
		if(inicioDateFieldChooser == null){
			inicioDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			//inicioDateFieldChooser.setText("");
			inicioDateFieldChooser.getCalendarButton().setEnabled(false);
			inicioDateFieldChooser.setSize(new Dimension(145, 20));
			inicioDateFieldChooser.setLocation(new java.awt.Point(120,0));
			return inicioDateFieldChooser;
		}
		return inicioDateFieldChooser;
	}
	
	protected JPanel getButtonsPanel(){
		if(buttonsPanel == null){
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(2);
			flowLayout.setVgap(2);
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(flowLayout);
			buttonsPanel.setSize(new Dimension(566, 63));
			buttonsPanel.setLocation(new Point(0, 280));
			buttonsPanel.add(getAtualizarButton());
			buttonsPanel.add(getConfirmarButton());
			buttonsPanel.add(getCancelarButton());
			buttonsPanel.add(getNovaAgendaButton(), null);
			buttonsPanel.add(getListarAgendasButton(), null);
			buttonsPanel.add(getAgendarSegundoLeilaoButton(), null);			
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
						cadastreAgendaProjeto();
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
		this.lanceInicialTextField.firePropertyChange("warnBorder",!bool, bool);
		incrementoTextField.firePropertyChange("warnBorder",!bool, bool);
		chamadaLeilaoComboBox.firePropertyChange("warnBorder",!bool, bool);
		lanceMinimoTextField.firePropertyChange("warnBorder",!bool, bool);
		inicioPrevistoDateFieldChooser.firePropertyChange("warnBorder",!bool, bool);
		terminoPrevistoDateFieldChooser.firePropertyChange("warnBorder",!bool, bool);
		this.getErrorPanel().setVisible(false);
	}
	
	private AgendaProjeto validateAgendaProjeto() throws Exception{
		setErrorIcon(false);
		clearErrorList();
		boolean invalid=false;
		agenda.setProjeto(projeto);
		
		agenda.setObs(obsTextArea.getText());
		try {
			//binder.bind(agenda);
			
			try {
				agenda.setLanceInicial(Float.parseFloat(lanceInicialTextField.getText()));
			} catch (Exception e) {
				reportInvalidField("lanceInicial","não é um valor numérico monetário");
				invalid=true;
			}
			
			try {
				agenda.setLanceMinimo(Float.parseFloat(lanceMinimoTextField.getText()));
			} catch (Exception e) {
				reportInvalidField("lanceMinimo","não é um valor numérico monetário");
				invalid=true;
			}
			try {
				agenda.setIncremento(Float.parseFloat(incrementoTextField.getText()));
			} catch (Exception e) {
				reportInvalidField("incremento","não é um valor numérico monetário");
				invalid=true;
			}
			if (!inicioPrevistoDateFieldChooser.getDateHourField().getText().equals(""))
				agenda.setInicioPrevisto(inicioPrevistoDateFieldChooser.getDate());
			if (agenda.getInicioPrevisto() == null){ 
				reportInvalidField("inicioPrevisto","não pode ser vazia");
				invalid=true;
			}
			if (!terminoPrevistoDateFieldChooser.getDateHourField().getText().equals(""))
				agenda.setTerminoPrevisto(terminoPrevistoDateFieldChooser.getDate());
			if (agenda.getTerminoPrevisto() == null){
				reportInvalidField("terminoPrevisto","não pode ser vazia");
				invalid=true;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		
		
		/*if (!validateAgendaProjetoBean())
			throw new Exception("Bean Not Validated!");*/
		if (invalid){
			showErrorList();
			throw new Exception("Bean Not Validated!");
		}
		return agenda;
	}
	

	
	private AgendaProjeto cadastreAgendaProjeto() throws Exception{
		AgendaProjeto a = validateAgendaProjeto();
		Session s = null;
		
		try{			
			s = LocalServicesUtility.getInstance().openSession();
			projeto = (Projeto) s.load(Projeto.class,projeto.getId());
			s.beginTransaction();
			s.saveOrUpdate(a);
			projeto.setAgendaProjeto(a);
			a.getId();
			s.update(projeto);
			s.getTransaction().commit();			
			getAtualizarButton().setText("Atualizar");
			if (!a.getConfirmada() && a.getStatus() == null || a.getStatus() != StatusAgenda.Cancelada) getConfirmarButton().setEnabled(true);
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null) s.close();
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
			String msg = "AgendaProjeto."+name ;
			try {
				getErrorPanel()
							.addError(messages.getMessage(msg)+ " " + errorMsg, comp);
				comp.setToolTipText(messages.getMessage(msg)+ " " + errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}else{
			String msg = "AgendaProjeto."+name;
			try {
				getErrorPanel().addError(messages.getMessage(msg)+" "+errorMsg);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public boolean validateAgendaProjetoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.agenda,	"AgendaProjeto");
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
			agendarButton = new JButton("Confirmar Agenda");//messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.ConfirmaroIníciodoLeilão"));
			agendarButton.setSize(new java.awt.Dimension(80,22));
			agendarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/clock_play.png")));
			agendarButton.setEnabled(false);
			agendarButton.setLocation(new java.awt.Point(0,20));
			agendarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Session s = null;
					try{
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						//s.createSQLQuery("update AgendaProjeto set confirmada=true where AgendaProjeto.id="+agenda.getId());
						agenda.setProjeto(projeto);
						agenda.setConfirmada(true);
						
						s.update("confirmada",agenda);
						s.getTransaction().commit();
						
						editRegister(agenda);
						AdapitVirtualFrame.getInstance().showOperationSucess();
					}catch(Exception ex){
						ex.printStackTrace();
						s.getTransaction().rollback();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problemas para atualizar a agenda", "Não foi possível confirmar a agenda");
					}finally{
						if (s != null) s.close();
					}
				}
			});
		}
		return agendarButton;
	}
	
	protected JButton getCancelarButton(){

		if(cancelarButton == null){
			cancelarButton = new JButton(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.CancelaraAgenda"));
			cancelarButton.setSize(new java.awt.Dimension(80,22));
			cancelarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/clock_stop.png")));
			cancelarButton.setEnabled(false);
			cancelarButton.setLocation(new java.awt.Point(0,42));
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Session s = null;
					try{
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						//s.createSQLQuery("update AgendaProjeto set confirmada=true where AgendaProjeto.id="+agenda.getId());
						agenda.setConfirmada(false);
						agenda.setStatus(StatusAgenda.Cancelada);
						agenda.setObs("Esta agenda foi cancelada pela(s) seguinte(s) razão/razões:"+'\n');
						s.update("confirmada,status",agenda);
						s.getTransaction().commit();
						
						editRegister(agenda);
						obsTextArea.setEditable(true);
						obsTextArea.setEnabled(true);
						obsTextArea.requestFocus();
						
						AdapitVirtualFrame.getInstance().showOperationSucess();
					}catch(Exception ex){
						ex.printStackTrace();
						s.getTransaction().rollback();
						AdapitVirtualFrame.getInstance().showErrorDialog("Problema ao cancelar a agenda", "Não foi possível cancelar a agenda especificada!");
					}finally{
						if (s != null) s.close();
					}
				}
			});
		}
		return cancelarButton;
	}
	
	protected JLabel getObsLabel(){

		if(obsLabel == null){
			obsLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaProjetoCadastreForm.Observação"));
			obsLabel.setSize(new Dimension(240, 20));
			obsLabel.setLocation(new Point(15, 105));
		}
		return obsLabel;
	}
	
	protected JScrollPane getObsScrollPane(){

		if(obsScrollPane == null){
			obsScrollPane = new JScrollPane();
			obsScrollPane.setSize(new Dimension(538, 98));
			getObsTextArea();
			obsScrollPane.setViewportView(getObsTextArea());
			obsScrollPane.setLocation(new Point(15, 125));
			
		}
		return obsScrollPane;
	}
	
	/**
	 * This method initializes terminoLeilaoDateChooser	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private DateHourChooser getTerminoLeilaoDateChooser() {
		if (terminoLeilaoDateChooser == null) {
			terminoLeilaoDateChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			terminoLeilaoDateChooser.setBounds(new Rectangle(385, 0, 145, 20));
			terminoLeilaoDateChooser.getCalendarButton().setEnabled(false);
		}
		return terminoLeilaoDateChooser;
	}

	/**
	 * This method initializes incrementoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JComponent getIncrementoTextField() {
		if (incrementoTextField == null) {
			incrementoTextField = new JTextField();
			incrementoTextField.setBounds(new Rectangle(140, 61, 115, 20));
			incrementoTextField.setHorizontalAlignment(JTextField.RIGHT);
			this.binder.addBindProperty(this.agenda,this.incrementoTextField, "incremento");

			this.hashComps.put("incremento", this.incrementoTextField);
			JWarningComponent warn = new JWarningComponent(this.incrementoTextField);
			warn.setBounds(140, 61, 115, 20);
			return warn;
		}
		return incrementoTextField;
	}



	/**
	 * This method initializes obsTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JComponent getObsTextArea() {
		if (obsTextArea == null) {
			obsTextArea = new JTextArea();
			obsTextArea.setEditable(false);
			this.binder.addBindProperty(this.agenda,this.obsTextArea, "obs");

			this.hashComps.put("obs", this.obsTextArea);
			JWarningComponent warn = new JWarningComponent(this.obsTextArea);
			warn.setBounds(15, 125, 538, 98);
			
			return warn;
		}
		return obsTextArea;
	}

	/**
	 * This method initializes agendarSegundoLeialoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAgendarSegundoLeilaoButton() {
		if (agendarSegundoLeialoButton == null) {
			agendarSegundoLeialoButton = new JButton();
			agendarSegundoLeialoButton.setText("Agendar Segundo Leilão");
			agendarSegundoLeialoButton.setEnabled(false);
			agendarSegundoLeialoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							Session s = null;					
							try{
								s = LocalServicesUtility.getInstance().openSession();
								projeto = (Projeto) s.load(Projeto.class,projeto.getId());
								if (projeto == null) return;
								if (projeto.getAgendaProjeto() != null){
									projeto.getAgendas().add(projeto.getAgendaProjeto());
									try {
										s.beginTransaction();
										s.update(projeto);
										//s.update(Projeto.getAgendas());
										s.getTransaction().commit();
										newRegister();
										chamadaLeilaoComboBox.setSelectedIndex(1);
										timer.start();
									} catch (Exception e1) {
										e1.printStackTrace();
										s.getTransaction().rollback();
									}
									
									
								}else{
									newRegister();
								}
							}catch(Exception ex){
								ex.printStackTrace();
							}finally{
								if (s != null) s.close();
							}
						}
					});
		}
		return agendarSegundoLeialoButton;
	}

	/**
	 * This method initializes notificarDesistenciaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNotificarDesistenciaButton() {
		if (notificarDesistenciaButton == null) {
			notificarDesistenciaButton = new JButton();
			notificarDesistenciaButton.setText("Notificar Desistência");
			notificarDesistenciaButton.setEnabled(false);
		}
		return notificarDesistenciaButton;
	}

	/**
	 * This method initializes verDadosArremateButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerDadosArrematanteButton() {
		if (verDadosArrematanteButton == null) {
			verDadosArrematanteButton = new JButton();
			verDadosArrematanteButton.setText("Ver Dados do Arrematante");
			verDadosArrematanteButton.setEnabled(false);
		}
		return verDadosArrematanteButton;
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
					Session s = null;					
					try{
						s = LocalServicesUtility.getInstance().openSession();
						projeto = (Projeto) s.load(Projeto.class,projeto.getId());
						if (projeto == null) return;
						if (projeto.getAgendaProjeto() != null){
							projeto.getAgendas().add(projeto.getAgendaProjeto());
							try {
								s.beginTransaction();
								s.update(projeto);
								//s.update(Projeto.getAgendas());
								s.getTransaction().commit();
								newRegister();
							} catch (Exception e1) {
								e1.printStackTrace();
								s.getTransaction().rollback();
							}
							
							
						}else{
							newRegister();
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
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
			//errorPanel.setLayout(new GridBagLayout());
			errorPanel.setBounds(new Rectangle(15, 224, 538, 54));
			errorPanel.setVisible(false);
		}
		return errorPanel;
	}

	/**
	 * This method initializes lancesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLancesPanel() {
		if (lancesPanel == null) {
			lancesPanel = new JPanel();
			lancesPanel.setLayout(null);
			lancesPanel.add(getInicioPanel(), null);
			lancesPanel.add(getLancesButtonPanel(), null);
		}
		return lancesPanel;
	}

	/**
	 * This method initializes encerramentoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEncerramentoPanel() {
		if (encerramentoPanel == null) {
			encerramentoPanel = new JPanel();
			encerramentoPanel.setLayout(null);
		}
		return encerramentoPanel;
	}

	/**
	 * This method initializes lancesButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLancesButtonPanel() {
		if (lancesButtonPanel == null) {
			lancesButtonPanel = new JPanel();
			lancesButtonPanel.setLayout(new FlowLayout());
			lancesButtonPanel.setSize(new Dimension(566, 63));
			lancesButtonPanel.setLocation(new Point(0, 280));
			lancesButtonPanel.add(getListarButton(), null);
			lancesButtonPanel.add(getVerDadosArrematanteButton());
			lancesButtonPanel.add(getVerContasPagarArrematanteButton(), null);
			lancesButtonPanel.add(getNotificarDesistenciaButton());
			lancesButtonPanel.add(getVerHistoricoClienteSelecionadoButton(), null);
			lancesButtonPanel.add(getVerDadosComitenteButton(), null);
		}
		return lancesButtonPanel;
	}

	/**
	 * This method initializes listarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarButton() {
		if (listarButton == null) {
			listarButton = new JButton();
			listarButton.setText("Listar");
		}
		return listarButton;
	}

	/**
	 * This method initializes verContasPagarArrematanteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerContasPagarArrematanteButton() {
		if (verContasPagarArrematanteButton == null) {
			verContasPagarArrematanteButton = new JButton();
			verContasPagarArrematanteButton.setText("Ver Contas a Pagar do Arrematante");
			verContasPagarArrematanteButton.setEnabled(false);
		}
		return verContasPagarArrematanteButton;
	}

	/**
	 * This method initializes verHistoricoClienteSelecionadoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerHistoricoClienteSelecionadoButton() {
		if (verHistoricoClienteSelecionadoButton == null) {
			verHistoricoClienteSelecionadoButton = new JButton();
			verHistoricoClienteSelecionadoButton.setText("Ver Histórico do Cliente Selecionado");
			verHistoricoClienteSelecionadoButton.setEnabled(false);
		}
		return verHistoricoClienteSelecionadoButton;
	}

	/**
	 * This method initializes verDadosComitenteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerDadosComitenteButton() {
		if (verDadosComitenteButton == null) {
			verDadosComitenteButton = new JButton();
			verDadosComitenteButton.setText("Ver Dados do PessoaEmDivulgacao");
			verDadosComitenteButton.setEnabled(false);
		}
		return verDadosComitenteButton;
	}

	/**
	 * This method initializes listarAgendasButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarAgendasButton() {
		if (listarAgendasButton == null) {
			listarAgendasButton = new JButton();
			listarAgendasButton.setText("Ver Agendas Anteriores");
			listarAgendasButton.setEnabled(false);
			listarAgendasButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AgendaProjetoListDialog dialog = new AgendaProjetoListDialog();
					
					dialog.setProjeto(projeto);
					dialog.setVisible(true);
				}
			});
		}
		return listarAgendasButton;
	}

	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(419,322));
					gui.add(new AgendaProjetoCadastreForm(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}
	
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

	public AgendaProjeto getAgenda() {
		return agenda;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto Projeto) {
		this.projeto = Projeto;
	}

	public void editRegister(AgendaProjeto AgendaProjeto) {		
		Session s = null;
		
		try{
			s = LocalServicesUtility.getInstance().openSession();
			AgendaProjeto= (AgendaProjeto) s.load(AgendaProjeto.class,AgendaProjeto.getId());
			
			agenda.setId(AgendaProjeto.getId());
			//agenda.setChamadaLeilao(chamadaLeilao)
			try {
				agenda.setConfirmada(AgendaProjeto.getConfirmada());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//agenda.setEncerramento();
			agenda.setIncremento(AgendaProjeto.getIncremento());
			agenda.setInicio(AgendaProjeto.getInicio());
			agenda.setInicioPrevisto(AgendaProjeto.getInicioPrevisto());
			agenda.setLanceInicial(AgendaProjeto.getLanceInicial());
			agenda.setLanceMinimo(AgendaProjeto.getLanceMinimo());
			//agenda.setLances(AgendaProjeto.getLances());
			//agenda.setProjeto(AgendaProjeto.getProjeto());
			agenda.setObs(AgendaProjeto.getObs());
			agenda.setStatus(AgendaProjeto.getStatus());
			agenda.setTermino(AgendaProjeto.getTermino());
			agenda.setTerminoPrevisto(AgendaProjeto.getTerminoPrevisto());
			
			
			lanceInicialTextField.setText(""+agenda.getLanceInicial());
			lanceMinimoTextField.setText(""+agenda.getLanceMinimo());
			incrementoTextField.setText(""+agenda.getIncremento());
			inicioPrevistoDateFieldChooser.setDate(agenda.getInicioPrevisto());
			terminoPrevistoDateFieldChooser.setDate(agenda.getTerminoPrevisto());
			
			//inicioPrevistoDateFieldChooser.getDateHourField().setText(""+agenda.getInicioPrevisto());
			//terminoPrevistoDateFieldChooser.getDateHourField().setText("");
			if (agenda.getObs() != null) obsTextArea.setText(""+agenda.getObs());
			else obsTextArea.setText("");
			
			
			
			
			Projeto l = (Projeto) s.load(Projeto.class,projeto.getId());
			if (l.getAgendas() != null && l.getAgendas().size() > 0){
				getListarAgendasButton().setEnabled(true);
			}else {
				getListarAgendasButton().setEnabled(false);
			}
			
			/*Calendar c = Calendar.getInstance();
			//c.setTime(new Date());
			if (c.after(agenda.getTerminoPrevisto())){
				getNovaAgendaButton().setEnabled(true);
			}else getNovaAgendaButton().setEnabled(false);*/
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		
		/*if (agenda.getConfirmada()) {
			getCancelarButton().setEnabled(true);
			getConfirmarButton().setEnabled(false);
			
		}else{
			getConfirmarButton().setEnabled(true);
			getCancelarButton().setEnabled(false);
		}
		getAtualizarButton().setText("Atualizar");*/
		
		getCodProjetoTextField().setText(projeto.getCodigoProjeto());
		getValorProjetoTextField().setText(projeto.getAvaliacao()+"");
		
		if (agenda.getStatus() != null && agenda.getStatus() == StatusAgenda.Cancelada){
			getNovaAgendaButton().setEnabled(true);
			getAtualizarButton().setEnabled(true);
			getAtualizarButton().setText("Atualizar");
			obsTextArea.setEditable(true);
			obsTextArea.setEnabled(true);
			
			getIncrementoTextField().setEnabled(false);
			getLanceMinimoTextField().setEnabled(false);
			getLanceInicialTextField().setEnabled(false);
			getAgendarSegundoLeilaoButton().setEnabled(false);
			getCancelarButton().setEnabled(false);
			
			getConfirmarButton().setEnabled(false);
			inicioPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
			terminoPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
		}
	
	}
	
/*	public void anexarId(AgendaProjeto AgendaProjeto) {		
		Session s = null;
		
		try{
			s = LocalServicesUtility.getInstance().openSession();
			AgendaProjeto= (AgendaProjeto) s.load(AgendaProjeto.class,AgendaProjeto.getId());
			agenda.setId(AgendaProjeto.getId());			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}		
		getAtualizarButton().setText("Atualizar");		
	}*/

	public void newRegister(){
		agenda.setId(0);
		agenda.setConfirmada(false);
		agenda.setIncremento(0.0f);
		agenda.setInicio(null);
		agenda.setInicioPrevisto(null);
		agenda.setLanceInicial(0.0f);
		agenda.setLanceMinimo(0.0f);
		agenda.setProjeto(projeto);
		agenda.setObs(null);
		agenda.setStatus(null);
		agenda.setTermino(null);
		agenda.setTerminoPrevisto(null);
		
		
		getAtualizarButton().setText("Cadastrar");
		getConfirmarButton().setEnabled(false);
		getCancelarButton().setEnabled(false);
		
		lanceInicialTextField.setText("");
		lanceMinimoTextField.setText("");
		incrementoTextField.setText("");
		inicioPrevistoDateFieldChooser.getDateHourField().setText("");
		terminoPrevistoDateFieldChooser.getDateHourField().setText("");
		obsTextArea.setText("");
		
		getNovaAgendaButton().setEnabled(false);
		getAtualizarButton().setEnabled(true);
		getAtualizarButton().setText("Cadastrar");
		
		getIncrementoTextField().setEnabled(true);
		getLanceMinimoTextField().setEnabled(true);
		getLanceInicialTextField().setEnabled(true);
		getAgendarSegundoLeilaoButton().setEnabled(false);
		getCancelarButton().setEnabled(false);		
		getConfirmarButton().setEnabled(false);
		inicioPrevistoDateFieldChooser.getCalendarButton().setEnabled(true);
		terminoPrevistoDateFieldChooser.getCalendarButton().setEnabled(true);
		try {
			getTabbedPane().remove(getLancesPanel());
			getTabbedPane().remove(getEncerramentoPanel());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Timer timer;
	
	public class ClockLabel extends JLabel implements ActionListener {
		private boolean begin=false;
		private Icon toStartIcon = AdapitVirtualFrame.getInstance().getIcon("/imgs/time.png",18,18);
		private Icon elapsedIcon = AdapitVirtualFrame.getInstance().getIcon("/imgs/time_go.png",18,18);
		private Icon timeOverIcon = AdapitVirtualFrame.getInstance().getIcon("/imgs/clock_red.png",18,18);
		
		public ClockLabel(String str) {
			  super(str);
		    timer= new Timer(1000, this);
		    timer.start();
		    setHorizontalAlignment(JLabel.CENTER);
		  }

		  public void actionPerformed(ActionEvent ae) {
		    if (agenda.getInicioPrevisto() != null && agenda.getTerminoPrevisto() != null){
		    	{
		    		setIcon(toStartIcon);
		    		long currentTime = System.currentTimeMillis();
					SimpleDateFormat dateFormat =	new SimpleDateFormat("HH:mm:ss");

					dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
					
					long startTime = agenda.getInicioPrevisto().getTime();
					
					long elapsed = currentTime - startTime;
					
					String str="";
					
					if (currentTime < startTime){
						str+="Tempo para o início do leilão: ";
						long tempoInicio = startTime - currentTime;
						
						/*// Get elapsed time in seconds
					    float elapsedTimeSec = (startTime - currentTime)/1000F;
					    
					    // Get elapsed time in minutes
					    float elapsedTimeMin = (startTime - currentTime)/(60*1000F);
					    
					    // Get elapsed time in hours
					    float elapsedTimeHour = (startTime - currentTime)/(60*60*1000F);
					    */
					    // Get elapsed time in days
					    float elapsedTimeDay = (startTime - currentTime)/(24*60*60*1000F);
					    if (((int)elapsedTimeDay) > 1) str+= ((int)elapsedTimeDay)+ " dias e ";
					    else if (((int)elapsedTimeDay) == 1) str+= " 1 dia e ";
						str+=dateFormat.format(new Date(tempoInicio));
					    
					}else {
						if (!begin){
							//getCancelarButton().setEnabled(false);
							inicioPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
							begin = true;
						}
						elapsed = currentTime;
						setIcon(elapsedIcon);
						
						str+="Tempo para o término do leilão: ";
						long endTime = agenda.getTerminoPrevisto().getTime();
						float elapsedTimeDay = (endTime - elapsed)/(24*60*60*1000F);
					    if (((int)elapsedTimeDay) > 1) str+= ((int)elapsedTimeDay)+ " dias e ";
					    else if (((int)elapsedTimeDay) == 1) str+= " 1 dia e ";
					    
						str+=dateFormat.format(new Date(endTime - elapsed));
					}
					
					setText(str);
		    	}
		    	atualizaBotoes();
		    }
		  }
		  
		  public void atualizaBotoes(){
				/*Calendar c = Calendar.getInstance();
				//c.setTime(new Date());
				if (c.after(agenda.getTerminoPrevisto())){//AgendaProjeto.getTermino() != null || (c.before(AgendaProjeto.getTerminoPrevisto()) && (AgendaProjeto.getLances() == null || AgendaProjeto.getLances().size() == 0)) ){
					getNovaAgendaButton().setEnabled(true);
				}else getNovaAgendaButton().setEnabled(false);*/
				long currentTime = System.currentTimeMillis();
				
				long startTime = agenda.getInicioPrevisto().getTime();
				
				long endTime = agenda.getTerminoPrevisto().getTime();
				
				long elapsed = endTime - currentTime;
				
				//String str="";
				
				if (currentTime < startTime){ // Não chegou na data inicial
					getAgendarSegundoLeilaoButton().setEnabled(false);
					if (agenda.getConfirmada()){
						getCancelarButton().setEnabled(true);
						getConfirmarButton().setEnabled(false);
					}
					else{
						getConfirmarButton().setEnabled(true);
						getCancelarButton().setEnabled(false);
					}
					getAtualizarButton().setEnabled(true);
					
					
					
					
					getIncrementoTextField().setEnabled(true);
					getLanceMinimoTextField().setEnabled(true);
					getLanceInicialTextField().setEnabled(true);
					
				}else {//o leilao ou está rolando ou ja terminou			
					if (elapsed < 0/*agenda.getTermino() != null*/){//terminou o leilão para este Projeto
						/*if (agenda.getChamadaLeilao() == ChamadaLeilao.Segundo_leilão){
							//TODO implementar funcionalidades para que o usuário informe o que quer fazer com o leilão
							//caso não tenha
							timer.stop();
							setText("Segundo Leilão Encerrado!");
							getAgendarSegundoLeilaoButton().setEnabled(false);
							getCancelarButton().setEnabled(false);
							getAtualizarButton().setEnabled(false);
							getConfirmarButton().setEnabled(false);
							getNovaAgendaButton().setEnabled(false);
							//getLancesPanel().requestFocus();
							terminoPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
							setIcon(timeOverIcon);
							getButtonsPanel().remove(getAgendarSegundoLeilaoButton());
							getButtonsPanel().add(getAdicionarProjetoOutroLeilao());
						}else{

							getAgendarSegundoLeilaoButton().setEnabled(true);
							getCancelarButton().setEnabled(false);
							getAtualizarButton().setEnabled(false);
							getConfirmarButton().setEnabled(false);
							getNovaAgendaButton().setEnabled(false);
							timer.stop();
							setText("Primeiro Leilão Encerrado!");
							//getLancesPanel().requestFocus();	
							terminoPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
							setIcon(timeOverIcon);
						}
						*/
					}else{//o leilão está rolando						
						getAgendarSegundoLeilaoButton().setEnabled(false);
						getCancelarButton().setEnabled(false);
						getAtualizarButton().setEnabled(true);
						getConfirmarButton().setEnabled(false);
						getNovaAgendaButton().setEnabled(false);
					}
					getIncrementoTextField().setEnabled(false);
					getLanceMinimoTextField().setEnabled(false);
					getLanceInicialTextField().setEnabled(false);
					
				}
				
				if (agenda.getStatus() == StatusAgenda.Cancelada){
					getNovaAgendaButton().setEnabled(true);
					getAtualizarButton().setEnabled(true);
					
					getIncrementoTextField().setEnabled(false);
					getLanceMinimoTextField().setEnabled(false);
					getLanceInicialTextField().setEnabled(false);
					getAgendarSegundoLeilaoButton().setEnabled(false);
					getCancelarButton().setEnabled(false);
					
					getConfirmarButton().setEnabled(false);
					inicioPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
					terminoPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
					timer.stop();
				}
				else getNovaAgendaButton().setEnabled(false);
			}
	}
	
	
	
	private JButton adicionarProjetoOutroLeilao;
	
	private JButton getAdicionarProjetoOutroLeilao(){
		if (adicionarProjetoOutroLeilao== null){
			adicionarProjetoOutroLeilao = new JButton("Adicionar em Outro Leilão");
			
		}
		return adicionarProjetoOutroLeilao;
	}

}  //  @jve:decl-index=0:visual-constraint="19,10"