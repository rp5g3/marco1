package com.adapit.portal.ui.forms.treinamento.agenda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.ChamadaAgendaTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings("serial")
public class AgendaTreinamentoListPanel extends JPanel{
private JPanel loteDataPanel;
	
	private JTextField codigoTextField;
	
	private JLabel codLoteTextField4Label;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField valorLoteTextField;
	
	private JLabel valorLoteTextField5Label;
	
	//private JPanel valorLancesPanel;
	
	//private JTextField laceInicialTextField;
	
	//private JLabel laceInicialTextFieldLabel;
	
	//private JTextField lanceMinimoTextField;
	
	//private JLabel lanceMinimoTextFieldLabel;
	
	private JPanel inicioTerminoPrevistoPanel;
	
	private DateHourChooser inicioPrevistoDateFieldChooser;
	
	private JLabel inicioPrevistoDateFieldChooserLabel;
	
	private DateHourChooser terminoPrevistoDateFieldChooser;
	
	private JLabel terminoPrevistoDateFieldChooserLabel;
	
	private JPanel buttonsPanel;
	
	private JButton voltarButton;
	
	private JButton fecharDialogoButton;
	
	private JButton proximaButton;
	
	private JLabel obsLabel;
	
	private JScrollPane obsScrollPane;

	//private JLabel incrementoLabel = null;

	//private JTextField incrementoTextField = null;

	private JLabel chamadaLeilaoLabel = null;

	private JComboBox chamadaLeilaoComboBox = null;

	private JTextArea obsTextArea = null;

	private JPanel agendaCadastrePanel;

	private JPanel voltarPanel = null;

	private JPanel proximaPanel = null;
	
	
	public AgendaTreinamentoListPanel(){
		//super(LeilaoVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		this.setSize(new Dimension(570, 367));
		//setModal(true);
		setLayout(new BorderLayout());
		add(getAgendaCadastrePanel());		
		//setLocation(UIUtil.getScreenCenter(this));
		//this.setTitle("Lista das Agendas de um Lote");
	}
	
	private JPanel getAgendaCadastrePanel(){
		if (agendaCadastrePanel == null){
			agendaCadastrePanel = new JPanel();
			chamadaLeilaoLabel = new JLabel();
			chamadaLeilaoLabel.setBounds(new Rectangle(15, 84, 99, 20));
			chamadaLeilaoLabel.setHorizontalAlignment(SwingConstants.LEFT);
			chamadaLeilaoLabel.setText("Chamada do Leilão:");
			/*incrementoLabel = new JLabel();
			incrementoLabel.setBounds(new Rectangle(15, 61, 124, 20));
			incrementoLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			incrementoLabel.setText("Incremento (R$):");*/
			agendaCadastrePanel.setSize(new Dimension(566, 347));
			agendaCadastrePanel.setLocation(new java.awt.Point(0,0));
			agendaCadastrePanel.setLayout(null);
			agendaCadastrePanel.add(getLoteDataPanel());
			//agendaCadastrePanel.add(getValorLancesPanel());
			agendaCadastrePanel.add(getButtonsPanel());
			agendaCadastrePanel.add(getObsLabel());
			agendaCadastrePanel.add(getObsScrollPane());
			agendaCadastrePanel.add(getInicioTerminoPrevistoPanel(), null);
			//agendaCadastrePanel.add(getInicioPanel(), null);
			//agendaCadastrePanel.add(incrementoLabel, null);
			//agendaCadastrePanel.add(getIncrementoTextField(), null);
			agendaCadastrePanel.add(chamadaLeilaoLabel, null);
			agendaCadastrePanel.add(getChamadaLeilaoComboBox(), null);
			agendaCadastrePanel.add(getVoltarPanel(), null);
			agendaCadastrePanel.add(getProximaPanel(), null);
			
		}
		return agendaCadastrePanel;
	}
	
	protected JPanel getLoteDataPanel(){

		if(loteDataPanel == null){
			loteDataPanel = new JPanel();
			loteDataPanel.setSize(new Dimension(541, 21));
			loteDataPanel.setLocation(new Point(15, 15));
			loteDataPanel.setLayout(null);
			loteDataPanel.add(getCodLoteTextField4());
			loteDataPanel.add(getCodLoteTextField4Label());
			loteDataPanel.add(getValorLoteTextField5());
			loteDataPanel.add(getValorLoteTextField5Label());
		}
		return loteDataPanel;
	}
	
	protected JTextField getCodLoteTextField4(){

		if(codigoTextField == null){
			codigoTextField = new JTextField();
			codigoTextField.setText("");
			codigoTextField.setSize(new Dimension(115, 20));
			codigoTextField.setEditable(false);
			codigoTextField.setLocation(new Point(125, 0));
			return codigoTextField;
		}
		return codigoTextField;
	}
	
	protected JLabel getCodLoteTextField4Label(){

		if(codLoteTextField4Label == null){
			codLoteTextField4Label = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaLoteCadastreForm.CódigodoLote"));
			codLoteTextField4Label.setSize(new Dimension(123, 20));
			codLoteTextField4Label.setLocation(new java.awt.Point(0,0));
			codLoteTextField4Label.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_go.png")));
			codLoteTextField4Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return codLoteTextField4Label;
	}
	
	protected JTextField getValorLoteTextField5(){

		if(valorLoteTextField == null){
			valorLoteTextField = new JTextField();
			valorLoteTextField.setText("");
			valorLoteTextField.setSize(new Dimension(115, 20));
			valorLoteTextField.setEditable(false);
			valorLoteTextField.setHorizontalAlignment(JTextField.RIGHT);
			valorLoteTextField.setLocation(new Point(420, 0));
			return valorLoteTextField;
		}
		return valorLoteTextField;
	}
	
	protected JLabel getValorLoteTextField5Label(){

		if(valorLoteTextField5Label == null){
			valorLoteTextField5Label = new JLabel("Valor do Lote (R$):");
			valorLoteTextField5Label.setSize(new Dimension(125, 20));
			valorLoteTextField5Label.setLocation(new Point(294, 0));
			valorLoteTextField5Label.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			valorLoteTextField5Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return valorLoteTextField5Label;
	}
	
/*	protected JPanel getValorLancesPanel(){

		if(valorLancesPanel == null){
			valorLancesPanel = new JPanel();
			valorLancesPanel.setSize(new Dimension(540, 21));
			valorLancesPanel.setLocation(new Point(15, 38));
			valorLancesPanel.setLayout(null);
			valorLancesPanel.add(getLaceInicialTextField());
			valorLancesPanel.add(getLaceInicialTextFieldLabel());
			valorLancesPanel.add(getLanceMinimoTextField());
			valorLancesPanel.add(getLanceMinimoTextFieldLabel());
		}
		return valorLancesPanel;
	}*/
	
	/*protected JTextField getLaceInicialTextField(){

		if(laceInicialTextField == null){
			laceInicialTextField = new JTextField();
			laceInicialTextField.setText("");
			laceInicialTextField.setSize(new java.awt.Dimension(115,20));
			laceInicialTextField.setHorizontalAlignment(JTextField.RIGHT);
			laceInicialTextField.setEditable(false);
			laceInicialTextField.setLocation(new java.awt.Point(125,0));
			return laceInicialTextField;
		}
		return laceInicialTextField;
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
	
	protected JTextField getLanceMinimoTextField(){

		if(lanceMinimoTextField == null){
			lanceMinimoTextField = new JTextField();
			lanceMinimoTextField.setText("");
			lanceMinimoTextField.setSize(new java.awt.Dimension(115,20));
			lanceMinimoTextField.setHorizontalAlignment(JTextField.RIGHT);
			lanceMinimoTextField.setEditable(false);
			lanceMinimoTextField.setLocation(new java.awt.Point(420,0));
			return lanceMinimoTextField;
		}
		return lanceMinimoTextField;
	}
	
	protected JLabel getLanceMinimoTextFieldLabel(){

		if(lanceMinimoTextFieldLabel == null){
			lanceMinimoTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaLoteCadastreForm.LanceMínimo(R$)"));
			lanceMinimoTextFieldLabel.setSize(new Dimension(124, 20));
			lanceMinimoTextFieldLabel.setLocation(new Point(295, 0));
			lanceMinimoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			lanceMinimoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return lanceMinimoTextFieldLabel;
	}*/
	
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
	
	protected DateHourChooser getInicioPrevistoDateFieldChooser(){

		if(inicioPrevistoDateFieldChooser == null){
			inicioPrevistoDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			//inicioPrevistoDateFieldChooser.setText("");
			inicioPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
			inicioPrevistoDateFieldChooser.setSize(new Dimension(145, 20));
			inicioPrevistoDateFieldChooser.setLocation(new Point(0, 20));
			return inicioPrevistoDateFieldChooser;
		}
		return inicioPrevistoDateFieldChooser;
	}
	
	protected JLabel getInicioPrevistoDateFieldChooserLabel(){

		if(inicioPrevistoDateFieldChooserLabel == null){
			inicioPrevistoDateFieldChooserLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaLoteCadastreForm.InícioPrevisto"));
			inicioPrevistoDateFieldChooserLabel.setSize(new Dimension(122, 20));
			inicioPrevistoDateFieldChooserLabel.setLocation(new Point(0, 0));
			inicioPrevistoDateFieldChooserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return inicioPrevistoDateFieldChooserLabel;
	}
	
	protected DateHourChooser getTerminoPrevistoDateFieldChooser(){

		if(terminoPrevistoDateFieldChooser == null){
			terminoPrevistoDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			terminoPrevistoDateFieldChooser.getCalendarButton().setEnabled(false);
			terminoPrevistoDateFieldChooser.setSize(new Dimension(145, 20));
			terminoPrevistoDateFieldChooser.setLocation(new Point(146, 20));
			return terminoPrevistoDateFieldChooser;
		}
		return terminoPrevistoDateFieldChooser;
	}
	
	protected JLabel getTerminoPrevistoDateFieldChooserLabel(){

		if(terminoPrevistoDateFieldChooserLabel == null){
			terminoPrevistoDateFieldChooserLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaLoteCadastreForm.TérminoPrevisto"));
			terminoPrevistoDateFieldChooserLabel.setSize(new Dimension(122, 20));
			terminoPrevistoDateFieldChooserLabel.setLocation(new Point(146, 0));
			terminoPrevistoDateFieldChooserLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return terminoPrevistoDateFieldChooserLabel;
	}
		
	protected JPanel getButtonsPanel(){

		if(buttonsPanel == null){
			FlowLayout flowLayout = new FlowLayout();
			//flowLayout.setHgap(2);
			//flowLayout.setVgap(2);
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(flowLayout);
			buttonsPanel.setSize(new Dimension(233, 35));
			buttonsPanel.setLocation(new Point(167, 226));
			buttonsPanel.add(getFecharDialogoButton());
		}
		return buttonsPanel;
	}
	
	public JButton getVoltarButton(){

		if(voltarButton == null){
			voltarButton = new JButton("Cadastrar");
			voltarButton.setText("<< Voltar");
			voltarButton.setEnabled(false);
			voltarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ordem = ordem-1;
					AgendaTreinamento a = elements.get(ordem);
					editRegister(a);
					if (ordem == 0) voltarButton.setEnabled(false);
					proximaButton.setEnabled(true);
				}
			});
		}
		return voltarButton;
	}
	
	public JButton getFecharDialogoButton(){

		if(fecharDialogoButton == null){
			fecharDialogoButton = new JButton("Confirmar Agenda");//messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaLoteCadastreForm.ConfirmaroIníciodoLeilão"));
			fecharDialogoButton.setSize(new java.awt.Dimension(80,22));
			fecharDialogoButton.setEnabled(true);
			fecharDialogoButton.setText("Fechar");
			fecharDialogoButton.setLocation(new java.awt.Point(0,20));
		}
		return fecharDialogoButton;
	}
	
	public JButton getProximaButton(){
		if(proximaButton == null){
			proximaButton = new JButton("Próxima >>");
			proximaButton.setEnabled(false);
			proximaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ordem = ordem+1;
					AgendaTreinamento a = elements.get(ordem);
					editRegister(a);
					if (ordem == elements.size()-1) proximaButton.setEnabled(false);
					voltarButton.setEnabled(true);
				}
			});
		}
		return proximaButton;
	}
	
	protected JLabel getObsLabel(){

		if(obsLabel == null){
			obsLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.baseleilao.AgendaLoteCadastreForm.Observação"));
			obsLabel.setSize(new Dimension(240, 20));
			obsLabel.setLocation(new Point(15, 105));
		}
		return obsLabel;
	}
	
	protected JScrollPane getObsScrollPane(){

		if(obsScrollPane == null){
			obsScrollPane = new JScrollPane();
			obsScrollPane.setSize(new Dimension(538, 98));
			obsScrollPane.setViewportView(getObsTextArea());
			obsScrollPane.setLocation(new Point(15, 125));
			
		}
		return obsScrollPane;
	}
	

	/**
	 * This method initializes incrementoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
/*	private JTextField getIncrementoTextField() {
		if (incrementoTextField == null) {
			incrementoTextField = new JTextField();
			incrementoTextField.setBounds(new Rectangle(140, 61, 115, 20));
			incrementoTextField.setEditable(false);
			incrementoTextField.setHorizontalAlignment(JTextField.RIGHT);
		}
		return incrementoTextField;
	}*/

	/**
	 * This method initializes chamadaLeilaoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getChamadaLeilaoComboBox() {
		if (chamadaLeilaoComboBox == null) {
			chamadaLeilaoComboBox = new JComboBox();
			chamadaLeilaoComboBox.setBounds(new Rectangle(140, 84, 115, 20));
			chamadaLeilaoComboBox.setEnabled(false);
			for (int i=0; i < ChamadaAgendaTreinamento.values().length; i++){
				chamadaLeilaoComboBox.addItem(ChamadaAgendaTreinamento.values()[i].name().replaceAll("_"," "));
			}
		}
		return chamadaLeilaoComboBox;
	}

	/**
	 * This method initializes obsTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getObsTextArea() {
		if (obsTextArea == null) {
			obsTextArea = new JTextArea();
			obsTextArea.setEnabled(false);
			obsTextArea.setEditable(false);
		}
		return obsTextArea;
	}

	/**
	 * This method initializes voltarPanelPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getVoltarPanel() {
		if (voltarPanel == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(FlowLayout.LEFT);
			voltarPanel = new JPanel();
			voltarPanel.setLayout(flowLayout1);
			voltarPanel.setBounds(new Rectangle(15, 226, 153, 35));
			voltarPanel.add(getVoltarButton(), null);
		}
		return voltarPanel;
	}

	/**
	 * This method initializes proximaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getProximaPanel() {
		if (proximaPanel == null) {
			FlowLayout flowLayout2 = new FlowLayout();
			flowLayout2.setAlignment(FlowLayout.RIGHT);
			proximaPanel = new JPanel();
			proximaPanel.setLayout(flowLayout2);
			proximaPanel.setBounds(new Rectangle(400, 226, 153, 35));
			proximaPanel.add(getProximaButton(), null);
		}
		return proximaPanel;
	}
	
	@SuppressWarnings("unused")
	private Treinamento treinamento=null;  

	/*public void setLote(Lote lote) {
		this.lote = lote;
		elements.clear();
		Session s = null;
		
		try{
			s = LocalServicesUtility.getInstance().openSession();
			lote = (Lote) s.load(Lote.class,lote.getId());
			codLoteTextField.setText(lote.getCodLote());
			valorLoteTextField.setText(""+lote.getAvaliacao());
			lote.getAgendas().iterator();
			elements.addAll(lote.getAgendas());
		}catch(Exception ex){
			ex.printStackTrace();
			
		}finally{
			if (s != null) s.close();
		}
	}*/
	
	public void setTreinamento(Treinamento lote) {
		this.treinamento = lote;
		elements.clear();
		try{			
			lote = (Treinamento) RemoteTreinamentoService.getInstance().loadScheduledTrainingById(lote.getId(),true);
			codigoTextField.setText(lote.getCodigo());
			valorLoteTextField.setText(""+lote.getReceita());
			lote.getAgendas().iterator();
			elements.addAll(lote.getAgendas());
		}catch(Exception ex){
			ex.printStackTrace();
			
		}
	}
	
	int ordem=0;

	public void editRegister() {
		ordem=0;
		if (elements != null && elements.size() > 0){
			if (elements.size() > 1) getProximaButton().setEnabled(true);
			else getProximaButton().setEnabled(false);
			getVoltarButton().setEnabled(false);
			/*Iterator<AgendaLote> it = elements.iterator();
			while(it.hasNext()){
				AgendaLote a = it.next();
				
			}*/
			AgendaTreinamento a = elements.get(0);
			editRegister(a);
		}
	}
	
	private ArrayList<AgendaTreinamento> elements = new ArrayList<AgendaTreinamento>();

	public void editRegister(AgendaTreinamento agenda) {		
		try{
			AgendaTreinamento al = (AgendaTreinamento) RemoteServicesUtility.getInstance().refresh(agenda);//s.refresh(agenda);
			agenda.setTurno(al.getTurno());
			agenda.setConfirmada(al.isConfirmada());
			agenda.setEncerrada(al.isEncerrada());
			//agenda.setIncremento(al.getIncremento());
			agenda.setInicio(al.getInicio());
			agenda.setInicioPrevisto(al.getInicioPrevisto());
			//agenda.setLanceInicial(al.getLanceInicial());
			agenda.setRecebendoInteressados(al.isRecebendoInteressados());
			agenda.setStatus(al.getStatus());
			agenda.setTermino(al.getTermino());
			agenda.setTerminoPrevisto(al.getTerminoPrevisto());
			agenda.getId();				
			
			//laceInicialTextField.setText(""+agenda.getLanceInicial());
			//lanceMinimoTextField.setText(""+agendaLote.getLanceMinimo());
			//incrementoTextField.setText(""+agenda.getIncremento());
			inicioPrevistoDateFieldChooser.setDate(agenda.getInicioPrevisto());
			terminoPrevistoDateFieldChooser.setDate(agenda.getTerminoPrevisto());
			try {
				chamadaLeilaoComboBox.setSelectedItem(agenda.getTurno().name().replaceAll("_"," "));
			} catch (Exception e1) {
				chamadaLeilaoComboBox.setSelectedIndex(0);
			}
			//inicioPrevistoDateFieldChooser.getDateHourField().setText(""+agenda.getInicioPrevisto());
			//terminoPrevistoDateFieldChooser.getDateHourField().setText("");
			/*if (agenda.getObs() != null) obsTextArea.setText(""+agenda.getObs());
			else obsTextArea.setText("");
			*/
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/*public void editRegister(AgendaLote agendaLote) {		
		Session s = null;
		
		try{
			s = LocalServicesUtility.getInstance().openSession();
			agendaLote= (AgendaLote) s.load(AgendaLote.class,agendaLote.getId());
			
			
			laceInicialTextField.setText(""+agendaLote.getLanceInicial());
			//lanceMinimoTextField.setText(""+agendaLote.getLanceMinimo());
			incrementoTextField.setText(""+agendaLote.getIncremento());
			inicioPrevistoDateFieldChooser.setDate(agendaLote.getInicioPrevisto());
			terminoPrevistoDateFieldChooser.setDate(agendaLote.getTerminoPrevisto());
			try {
				chamadaLeilaoComboBox.setSelectedItem(agendaLote.getChamadaLeilao().name().replaceAll("_"," "));
			} catch (Exception e1) {
				chamadaLeilaoComboBox.setSelectedIndex(0);
			}
			//inicioPrevistoDateFieldChooser.getDateHourField().setText(""+agenda.getInicioPrevisto());
			//terminoPrevistoDateFieldChooser.getDateHourField().setText("");
			if (agendaLote.getObs() != null) obsTextArea.setText(""+agendaLote.getObs());
			else obsTextArea.setText("");
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		
		
	
	}*/
}
