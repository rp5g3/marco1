package com.adapit.portal.ui.forms.treinamento.encerramento;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.ChamadaAgendaTreinamento;
import com.adapit.portal.entidades.Encerramento;
import com.adapit.portal.entidades.EncerramentoCondicionado;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemoteAgendaTreinamentoService;
import com.adapit.portal.services.remote.RemoteContaService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.treinamento.agenda.AgendaTreinamentoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.utils.Moeda;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class EncerramentoAgendaForm  extends JPanel{
	

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	

	
	private JPanel inicioPanel;
	
	private JLabel numeroLancesLabel;
	
	private JTextField lanceVencedor;
	

	


	private JLabel arrematanteLabel = null;

	private JTextField arrematanteTextField = null;


	private JButton notificarDesistenciaButton = null;

	private JButton verDadosArrematanteButton = null;


	private AgendaTreinamento agenda = new AgendaTreinamento();  //  @jve:decl-index=0:
	
	private Treinamento lote;  //  @jve:decl-index=0:
	
	//private boolean processFocus = true;
	
	private DateHourChooser tempDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
	
	private CodicaoPagamentoCadastreForm condicaoPagtoForm;
	
	public EncerramentoAgendaForm(){	
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
			tabbedPane.setTabPlacement(JTabbedPane.TOP);
			tabbedPane.addTab("Lista dos Lances", null, getLancesPanel(), null);
			//tabbedPane.addTab("Encerramento do Leilão do Lote", null, getEncerramentoPanel(), null);
			tabbedPane.addTab("Condição de Pagamento do Lote", null, getCondicaoPagtoForm(), null);
		}
		return tabbedPane;
	}
	


	private JPanel lancesPanel = null;

	private JPanel encerramentoPanel = null;

	private JPanel lancesButtonPanel = null;

	//private JButton listarButton = null;

	private JButton verContasPagarArrematanteButton = null;

	private JButton verHistoricoArrematanteButton = null;

	private JButton substituirArrematanteButton = null;

	//private JButton listarAgendasButton = null;
	
	
	
	protected JPanel getInicioPanel(){
		if(inicioPanel == null){
			arrematanteLabel = new JLabel();
			arrematanteLabel.setBounds(new Rectangle(322, 0, 83, 20));
			arrematanteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			arrematanteLabel.setText("Arrematante:");
			encerramentoDataLabel = new JLabel();
			encerramentoDataLabel.setText("Encerramento:");
			encerramentoDataLabel.setBounds(new Rectangle(291, 25, 89, 20));
			valorDefinidoLabel = new JLabel("Avaliação:");
			valorDefinidoLabel.setBounds(0, 25, 62, 20);
			inicioPanel = new JPanel();
			inicioPanel.setLayout(null);
			inicioPanel.setBounds(new Rectangle(15, 15, 541, 48));
			inicioPanel.add(getNumeroLancesLabel());
			inicioPanel.add(getLanceVencedor());
			inicioPanel.add(getLanceVencedorLabel());
			inicioPanel.add(arrematanteLabel, null);
			inicioPanel.add(getArrematanteTextField(), null);
			inicioPanel.add(getNumeroLancesTextField(), null);
			inicioPanel.add(encerramentoDataLabel, null);
			inicioPanel.add(getDataEncerramento(), null);
			inicioPanel.add(valorDefinidoLabel, null);
			inicioPanel.add(getValorDefinidoTextField(), null);
		}
		return inicioPanel;
	}
	
	public void updateData(){
		getTabbedPane().setVisible(true);
		
		updateEncerramento();
		
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
			/*
			inicioPanel2.add(getLanceVencedor());
			inicioPanel2.add(getLanceVencedorLabel());
			inicioPanel2.add(arrematanteLabel, null);
			inicioPanel2.add(getArrematanteTextField(), null);*/
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
			lanceVencedorLabel.setSize(new Dimension(74, 20));
			lanceVencedorLabel.setLocation(new Point(146, 0));
			
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
	
	/**
	 * This method initializes notificarDesistenciaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNotificarDesistenciaButton() {
		if (notificarDesistenciaButton == null) {
			notificarDesistenciaButton = new JButton();
			notificarDesistenciaButton.setText("Notificar Desistência");
			notificarDesistenciaButton.setBounds(new Rectangle(407, 213, 152, 26));
			notificarDesistenciaButton.setEnabled(true);
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
			verDadosArrematanteButton.setText("Ver os Dados Pessoais");
			verDadosArrematanteButton.setEnabled(true);
			verDadosArrematanteButton
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (arrematante != null){
						try {
							arrematante = initializeArrematanteTipoPessoa();
							if (arrematante instanceof Participante){
								if (arrematante.getTipoPessoa() instanceof Fisica)
									AdapitVirtualFrame.getInstance().editarParticipante(((Participante)arrematante).getUser(),PersonType.Fisica);
								else AdapitVirtualFrame.getInstance().editarParticipante(((Participante)arrematante).getUser(),PersonType.Juridica);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
					}
				}
			});
		}
		return verDadosArrematanteButton;
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
			lancesPanel.add(getInicioPanel(),null);
			lancesPanel.add(getEncerramentoPanel(),null);
			lancesPanel.add(getLancesButtonPanel(), null);
			lancesPanel.add(getNotificarDesistenciaButton(), null);
			
		}
		return lancesPanel;
	}
	


	private Encerramento encerramento;  //  @jve:decl-index=0:
	
	public void updateEncerramento(){		
		try {			
			
			Object objs[] = RemoteContaService.getInstance().loadEncerramentoValuesByAgendaId(agenda.getId()); 
			
			encerramento = (Encerramento) objs[0];			
			float valor = (Float) objs[1];			
			ChamadaAgendaTreinamento cl = (ChamadaAgendaTreinamento) objs[2];
			TurnoTreinamento regra = null;
			if (objs.length == 4 && objs[3] != null){ 
				regra = (TurnoTreinamento) objs[3];			
				if (regra != null && regra == TurnoTreinamento.Tarde
						&& cl != null && cl == ChamadaAgendaTreinamento.Segunda){
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor)
							+" -> 60% = " + Moeda.getValorReal((valor*60)/100));
				}else{
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor));
				}
			}else{
				if (cl != null && cl == ChamadaAgendaTreinamento.Segunda){
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor)
							+" -> 60% = " + Moeda.getValorReal((valor*60)/100));
				}else{
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor));
				}
			}
			if (encerramento != null){
				getDataEncerramento().setDate(encerramento.getData());
				if (encerramento.getCondicionado()){
					
					valorAlcancadoLabel.setVisible(true);
					getAprovarButton().setVisible(true);
					getValorAlcancadoTextField().setVisible(true);
					getEncerramentoPanel().setVisible(true);
					
					
					encerramento.getEncerrCond().getId();
					if (encerramento.getEncerrCond().getAprovado()){
						getAprovarButton().setEnabled(false);
						getValorAlcancadoTextField().setEditable(false);
						valorAlcancadoLabel.setText("Valor Aprovado");
					}else{
						getAprovarButton().setEnabled(true);
						getValorAlcancadoTextField().setEditable(true);
						valorAlcancadoLabel.setText("Valor em Aprovação");
					}
					getValorAlcancadoTextField().setText(Moeda.getValorReal(encerramento.getEncerrCond().getValorAprovado()));
					getCondicaoPagtoForm().setValor(encerramento.getEncerrCond().getValorAprovado());
				}else{
					valorAlcancadoLabel.setVisible(false);
					getAprovarButton().setVisible(false);
					getValorAlcancadoTextField().setVisible(false);
					getEncerramentoPanel().setVisible(false);
					
					getAprovarButton().setEnabled(false);
					getValorAlcancadoTextField().setEditable(false);
					getValorAlcancadoTextField().setText("");
				}
				
				
				getCondicaoPagtoForm().setEncerramento(encerramento);
				getCondicaoPagtoForm().updateContasButton(agenda,arrematante);
			}
			else{
				//System.err.println("Encerramento é nulo!");
				//getCondicaoPagtoButton().setEnabled(false);
				//getCondicaoPagtoForm().getGerarContasButton().setEnabled(false);
				getCondicaoPagtoForm().setEncerramento(null);
				getCondicaoPagtoForm().updateContasButton(agenda,arrematante);
				//getE
				//if (observer != null) observer.getLanceAgendaButton().setEnabled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//if (observer != null) observer.getLanceAgendaButton().setEnabled(true);
		}
	}
	
/*	public void updateEncerramento(){
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			
			Object objs[] = (Object[]) s.createQuery("select al.id, al.lanceInicial, al.chamadaLeilao, al.lote.leilao.regraExecucaoLeilao from AgendaLote al where al.id="+agenda.getId()).uniqueResult();//s.load(Encerramento.class, id);
			if (objs == null || objs.length == 0){
				System.err.println("Sem dados retornados na consulta 1");
				objs = (Object[]) s.createQuery("select al.encerramento, al.lanceInicial, al.chamadaLeilao from AgendaLote al where al.id="+agenda.getId()).uniqueResult();//s.load(Encerramento.class, id);
				if (objs == null || objs.length == 0){
					System.err.println("Sem dados retornados na consulta 2");
					return;
				}
			}
			
			encerramento = (Encerramento) s.createQuery("select al.encerramento from AgendaLote al where al.id="+agenda.getId()).uniqueResult();//objs[0];			
			float valor = (Float) objs[1];//s.createQuery("select al.lanceInicial from AgendaLote al where al.id="+agenda.getId()).uniqueResult();
			
			ChamadaLeilao cl = (ChamadaLeilao) objs[2];
			RegraExecucaoLeilao regra = null;
			if (objs.length == 4){ 
				regra = (RegraExecucaoLeilao) objs[3];			
				if (regra != null && regra == RegraExecucaoLeilao.Agência_Cargnelutti_Para_Dois_Leilões
						&& cl != null && cl == ChamadaLeilao.Segundo_leilão){
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor)
							+" -> 60% = " + Moeda.getValorReal((valor*60)/100));
				}else{
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor));
				}
			}else{
				if (cl != null && cl == ChamadaLeilao.Segundo_leilão){
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor)
							+" -> 60% = " + Moeda.getValorReal((valor*60)/100));
				}else{
					getValorDefinidoTextField().setText(Moeda.getValorReal(valor));
				}
			}
			if (encerramento != null){
				encerramento.getLanceVencedor().getId();
				getDataEncerramento().setDate(encerramento.getData());
				if (encerramento.getCondicionado()){
					
					valorAlcancadoLabel.setVisible(true);
					getAprovarButton().setVisible(true);
					getRejeitarButton().setVisible(true);
					getValorAlcancadoTextField().setVisible(true);
					getEncerramentoPanel().setVisible(true);
					
					
					encerramento.getEncerrCond().getId();
					if (encerramento.getEncerrCond().getAprovado()){
						getAprovarButton().setEnabled(false);
						getRejeitarButton().setEnabled(false);
						getValorAlcancadoTextField().setEditable(false);
						valorAlcancadoLabel.setText("Valor Aprovado");
					}else{
						getAprovarButton().setEnabled(true);
						getRejeitarButton().setEnabled(true);
						getValorAlcancadoTextField().setEditable(true);
						valorAlcancadoLabel.setText("Valor em Aprovação");
					}
					getValorAlcancadoTextField().setText(Moeda.getValorReal(encerramento.getEncerrCond().getValorAprovado()));
					getCondicaoPagtoForm().setValor(encerramento.getEncerrCond().getValorAprovado());
				}else{
					valorAlcancadoLabel.setVisible(false);
					getAprovarButton().setVisible(false);
					getRejeitarButton().setVisible(false);
					getValorAlcancadoTextField().setVisible(false);
					getEncerramentoPanel().setVisible(false);
					
					getAprovarButton().setEnabled(false);
					getRejeitarButton().setEnabled(false);
					getValorAlcancadoTextField().setEditable(false);
					getValorAlcancadoTextField().setText("");
				}
				
				
				getCondicaoPagtoForm().setEncerramento(encerramento);
				getCondicaoPagtoForm().updateContasButton(agenda,arrematante);
			}
			else{
				//System.err.println("Encerramento é nulo!");
				//getCondicaoPagtoButton().setEnabled(false);
				//getCondicaoPagtoForm().getGerarContasButton().setEnabled(false);
				getCondicaoPagtoForm().setEncerramento(null);
				getCondicaoPagtoForm().updateContasButton(agenda,arrematante);
				//getE
				//if (observer != null) observer.getLanceAgendaButton().setEnabled(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			//if (observer != null) observer.getLanceAgendaButton().setEnabled(true);
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}*/
	/**
	 * This method initializes encerramentoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEncerramentoPanel() {
		if (encerramentoPanel == null) {
			valorAlcancadoLabel = new JLabel();
			valorAlcancadoLabel.setBounds(new Rectangle(9, 22, 127, 18));
			valorAlcancadoLabel.setText("Valor Aprovado:");
			
			
			encerramentoPanel = new JPanel();
			encerramentoPanel.setLayout(null);
			encerramentoPanel.setSize(150, 144);
			encerramentoPanel.setLocation(408, 64);
			encerramentoPanel.setBorder(BorderFactory.createTitledBorder(null, "Homologação", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 70, 213)));
			encerramentoPanel.add(valorAlcancadoLabel, null);
			//encerramentoPanel.add(getEncerramentoButtonPanel());
			encerramentoPanel.add(getValorAlcancadoTextField(), null);
			encerramentoPanel.add(getAprovarButton(), null);
		}
		return encerramentoPanel;
	}
	
	private DateHourChooser dataEncerramento;
	
	private DateHourChooser getDataEncerramento(){
		if (dataEncerramento == null){
			dataEncerramento = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			dataEncerramento.getCalendarButton().setEnabled(false);
			dataEncerramento.setEnabled(false);
			dataEncerramento.setBounds(new Rectangle(382, 25, 147, 20));
		}
		return dataEncerramento;
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
			lancesButtonPanel.setSize(new Dimension(541, 85));
			lancesButtonPanel.setLocation(new Point(15, 252));
			lancesButtonPanel.setBorder(BorderFactory.createTitledBorder(null, "Operações relacionadas com o arrematante", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), new Color(0, 70, 213)));
			
		}
		return lancesButtonPanel;
	}
	
	/*private JPanel encerramentoButtonPanel;
	private JPanel getEncerramentoButtonPanel() {
		if (encerramentoButtonPanel == null) {
			encerramentoButtonPanel = new JPanel();
			encerramentoButtonPanel.setLayout(new FlowLayout());
			encerramentoButtonPanel.setSize(new Dimension(566, 63));
			encerramentoButtonPanel.setLocation(new Point(-3, 190));
			
			encerramentoButtonPanel.add(getVerContasPagarArrematanteButton(), null);
		}
		return encerramentoButtonPanel;
	}*/

	/**
	 * This method initializes listarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/*private JButton getListarButton() {
		if (listarButton == null) {
			listarButton = new JButton();
			listarButton.setText("Listar");
			listarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					getLancesList().updateTable();
				}				
			});
		}
		return listarButton;
	}*/

	/**
	 * This method initializes verContasPagarArrematanteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerContasPagarArrematanteButton() {
		if (verContasPagarArrematanteButton == null) {
			verContasPagarArrematanteButton = new JButton();
			verContasPagarArrematanteButton.setText("Lista de Contas a Pagar");
			verContasPagarArrematanteButton.setEnabled(true);
		}
		return verContasPagarArrematanteButton;
	}

	/**
	 * This method initializes verHistoricoClienteSelecionadoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerHistoricoArrematanteButton() {
		if (verHistoricoArrematanteButton == null) {
			verHistoricoArrematanteButton = new JButton();
			verHistoricoArrematanteButton.setText("Ver o Histórico do Arrematante");
			verHistoricoArrematanteButton.setEnabled(true);
		}
		return verHistoricoArrematanteButton;
	}

	/**
	 * This method initializes valorDefinidoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getValorDefinidoTextField() {
		if (valorDefinidoTextField == null) {
			valorDefinidoTextField = new JTextField();
			valorDefinidoTextField.setEnabled(false);
			valorDefinidoTextField.setBounds(new Rectangle(62, 25, 223, 20));
		}
		return valorDefinidoTextField;
	}

	/**
	 * This method initializes valorAlcancadoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getValorAlcancadoTextField() {
		if (valorAlcancadoTextField == null) {
			valorAlcancadoTextField = new JTextField();
			valorAlcancadoTextField.setBounds(new Rectangle(9, 46, 127, 20));
			valorAlcancadoTextField.setEnabled(true);
		}
		return valorAlcancadoTextField;
	}

	/**
	 * This method initializes aprovarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAprovarButton() {
		if (aprovarButton == null) {
			aprovarButton = new JButton();
			aprovarButton.setBounds(new Rectangle(8, 70, 130, 26));
			aprovarButton.setEnabled(false);
			aprovarButton.setText("Aprovar Arremate");
			aprovarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int resp = JOptionPane.showConfirmDialog(AdapitVirtualFrame.getInstance(), "Você tem certeza de que quer aprovar a homologação deste lote?","Aprovação de Homologação",JOptionPane.YES_NO_OPTION);
					if (resp == JOptionPane.NO_OPTION) return;
					try{
						lote.setStatus(ScheduledTrainingStatus.Realizado);
						EncerramentoCondicionado enc = RemoteAgendaTreinamentoService.getInstance().aprovarHomologacao(lote.getId(),
								encerramento.getEncerrCond(),
								Moeda.valorRealToFloat(getValorAlcancadoTextField().getText()));
						encerramento.setEncerrCond(enc);
						updateEncerramento();
					}catch(Exception ex){
						ex.printStackTrace();
					}
					/*Session s = null;
					try{
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						lote.setStatus(StatusLote.Leiloado);
						//s.update("status",lote);
						s.createQuery("update Lote l set l.status='"+lote.getStatus().name()+"' where l.id="+lote.getId()).executeUpdate();
						encerramento.getEncerrCond().setAprovado(true);
						encerramento.getEncerrCond().setValorAprovado(Moeda.valorRealToFloat(getValorAlcancadoTextField().getText()));
						//s.update("aprovado,valorAprovado",encerramento.getEncerrCond());
						s.createQuery("update EncerramentoCondicionado e set e.aprovado=true, e.valorAprovado="+encerramento.getEncerrCond().getValorAprovado()+" where e.id="+encerramento.getEncerrCond().getId()).executeUpdate();
						s.getTransaction().commit();
						updateEncerramento();
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
					}*/
				}
			});
		}
		return aprovarButton;
	}

	

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
	
	

	public AgendaTreinamento getAgenda() {
		return agenda;
	}

	public Treinamento getLote() {
		return lote;
	}

	public void setLote(Treinamento lote) {
		this.lote = lote;
	}
	
	
	/*public void editRegister(AgendaLote agendaLote, Session s) {
		try{
			agenda.setId(agendaLote.getId());
			s.refresh(agenda);	
			agenda.getId();	
		}catch(Exception ex){
			ex.printStackTrace();
		}	
		
		
	}	
	
	public void editRegister(AgendaLote agendaLote) {		
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			editRegister(agendaLote,s);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		
	}*/

	public void editRegister(AgendaTreinamento agendaLote) {		
		try{
			agenda.setId(agendaLote.getId());
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
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	private JLabel encerramentoDataLabel = null;

	private JLabel valorDefinidoLabel = null;

	private Participante arrematante;

	private JLabel valorAlcancadoLabel = null;

	private JTextField valorDefinidoTextField = null;

	private JTextField valorAlcancadoTextField = null;

	private JButton aprovarButton = null;

	//private JButton rejeitarButton = null;

	public CodicaoPagamentoCadastreForm getCondicaoPagtoForm() {
		if (condicaoPagtoForm == null){
			condicaoPagtoForm = new CodicaoPagamentoCadastreForm(); 
		}
		return condicaoPagtoForm;
	}

	/*private Participante initializeArrematanteTipoPessoa(){
		Session s =null;
		if (arrematante == null) return null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Object obj = s.createQuery("from Participante p where p.id="+arrematante.getId()).uniqueResult();
			if (obj != null){
				((Participante)obj).getTipoPessoa();
				((Participante)obj).getUser();
				return (Participante) obj;
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}finally{
			if (s != null) s.close();
		}
		return null;
	}*/
	private Participante initializeArrematanteTipoPessoa() throws Exception{
		return RemotePessoaService.getInstance().initializeParticipante(arrematante.getId());
	}

} 