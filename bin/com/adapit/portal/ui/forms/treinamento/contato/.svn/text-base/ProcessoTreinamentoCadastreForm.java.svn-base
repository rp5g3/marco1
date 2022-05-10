package com.adapit.portal.ui.forms.treinamento.contato;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import java.awt.Point;
import java.awt.Rectangle;

@SuppressWarnings("serial")
public class ProcessoTreinamentoCadastreForm extends JDialog{
	
	private SwingBinder binder = new SwingBinder();
	
	private ContatoProcessoTreinamento processo = new ContatoProcessoTreinamento();
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected Endereco endereco = new Endereco();
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel nomeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();	
		
	private JPanel dadosProcessoPanel;
	
	private JTextField tecnologiasTextField;
	
	private JLabel tecnologiasLabel;
	
	private JTextPane detalhesTextPane;
	
	private JLabel detalhesLabel;
	
	private JTextField requerenteTextField;
	
	private JLabel requerenteLabel;
	
	private JTextField localRealizacaoTextField;
	
	private JLabel localRealizacaoLabel;
	
	private JTextField numeroAlunosTextField;
	
	private JLabel numeroAlunosLabel;
	
	private JTextField recomendacoesTextField;
	
	private JLabel recomendacoesLabel;
	
	private DateHourChooser dataPlanejadaField;
	
	private JLabel dataPlanejadaLabel;	
		
	private JPanel buttonsPanel;
	
	private JButton cadastrarButton;
	
	private JButton cancelarButton;

	public ProcessoTreinamentoCadastreForm(JFrame pai){
		super(pai);
		initialize();
	}
	
	public ProcessoTreinamentoCadastreForm(){
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}
	
	private void initialize(){
		setSize(new java.awt.Dimension(471,324));
		setLayout(null);
		add(getDadosProcessoPanel());
		add(getButtonsPanel());
		add(this.getErrorPanel());
		setTitle("Cadastro e Atualização de Requisição");
		newRegister();
		this.setErrorIcon(false);
	}
	
	
	
	public ContatoProcessoTreinamento validateProcessoJudicialForm() throws Exception{
		setErrorIcon(false);
		binder.bind(processo);
		//Manual Code
		if (!validateProcessoJudicialBean()) throw new Exception("Bean Not Validated!");
		return processo;
	}
	@SuppressWarnings("unchecked")
	public ContatoProcessoTreinamento cadastreProcessoJudicial() throws Exception{
		validateProcessoJudicialForm();
		String str = (String) detalhesTextPane.getText();
		int resp = 0;
		if (processo.getId() == 0) 
			resp = JOptionPane.showConfirmDialog(ProcessoTreinamentoCadastreForm.this,
					"Você quer inserir o processo de treinamento " + str + "?");
			if (resp == JOptionPane.YES_OPTION) {				
				try {
					if (processo.getId() == 0) 
						RemoteServicesUtility.getInstance().save(processo);
					else {
						RemoteServicesUtility.getInstance().update(processo);
					}
					return processo;
				}catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(ProcessoTreinamentoCadastreForm.this,
						"Erro ao cadastrar o processo");
					throw ex;
				}
				
			}
			return null;
	}
	@SuppressWarnings("unchecked")
	public boolean validateProcessoJudicialBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			try {
				if (UIUtil.processFocus(this)) {
					processFocus = false;
				}
			} catch (RuntimeException e) {
				
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.processo, "contatoProcessoTreinamento");
		if (errors == null) return true;
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
					try{
						getErrorPanel().addError(messages.getMessage(msg),comp);
						comp.setToolTipText(messages.getMessage(msg));
					}catch(Exception e){
						e.printStackTrace();
					}
				} else {
					List args = (List) obj[1];
					Object[][] params = new Object[args.size()][2];
					for(int j=0; j < args.size(); j++) {
					   String key = (String) args.get(j);
					   params[j][0] = key;
					   params[j][1] = null;
					}
					try{
						getErrorPanel().addError(messages.getMessage(msg, params),comp);
						comp.setToolTipText(messages.getMessage(msg, params));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
//Nunca definir um novo objeto entidade!!!
		
		processo.setId(0);
		processo.setTecnologias(null);
		processo.setDetalhes(null);
		processo.setRequerente(null);
		processo.setLocalRealizacao(null);
		processo.setRecomendacoes(null);
		processo.setDataPlanejada(null);
		processo.setNumeroAlunos(0);

		binder.reverseBind(this.processo);


		this.setErrorIcon(false);
		//updateUI();
	}
	
	public void editRegister(ContatoProcessoTreinamento objProcJud ){
//Nunca passar como argumento novos objetos!!!
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.processo, objProcJud);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		

		binder.reverseBind(this.processo);
		this.setErrorIcon(false);		
	}
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(429,50));
			logErrorPanel.setLocation(0, 323);
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){
		this.tecnologiasTextField.firePropertyChange("warnBorder", !bool, bool);
		this.detalhesTextPane.firePropertyChange("warnBorder", !bool, bool);
		this.requerenteTextField.firePropertyChange("warnBorder", !bool, bool);
		this.localRealizacaoTextField.firePropertyChange("warnBorder", !bool, bool);
		this.numeroAlunosTextField.firePropertyChange("warnBorder", !bool, bool);
		
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getNomeTextFieldLabel(){
		if(nomeTextFieldLabel == null){
			nomeTextFieldLabel = new JLabel("Contato:");
			nomeTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			nomeTextFieldLabel.setLocation(new java.awt.Point(15,20));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}
	
	
	
	protected JPanel getDadosProcessoPanel(){

		if(dadosProcessoPanel == null){
			dadosProcessoPanel = new JPanel();
			dadosProcessoPanel.setSize(new Dimension(468, 187));
			dadosProcessoPanel.setLocation(new java.awt.Point(0,3));
			dadosProcessoPanel.setLayout(null);
			
			dadosProcessoPanel.add(getTecnologiasLabel());
			dadosProcessoPanel.add(getDetalhesScrollPane());
			dadosProcessoPanel.add(getDetalhesLabel());
			dadosProcessoPanel.add(getRequerenteTextField());
			dadosProcessoPanel.add(getRequerenteLabel());
			dadosProcessoPanel.add(getLocalRealizacaoTextField());
			dadosProcessoPanel.add(getLocalRealizacaoLabel());
			dadosProcessoPanel.add(getNumeroAlunosTextField());
			dadosProcessoPanel.add(getNumeroAlunosLabel());
			dadosProcessoPanel.add(getTecnologiasTextField());
		
			dadosProcessoPanel.add(getRecomendacoesTextField());
			dadosProcessoPanel.add(getRecomendacoesLabel());
			dadosProcessoPanel.add(getDataPlanejadaLabel(), null);
			dadosProcessoPanel.add(getDataPlanejadaField());
		}
		return dadosProcessoPanel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getTecnologiasTextField(){
		if(tecnologiasTextField == null){
			tecnologiasTextField = new JTextField();
			tecnologiasTextField.setText("");
			tecnologiasTextField.setSize(new java.awt.Dimension(334,20));
			tecnologiasTextField.setLocation(new java.awt.Point(115,15));
			this.binder.addBindProperty(this.processo, this.tecnologiasTextField, "tecnologias");
			
			this.hashComps.put("tecnologias", this.tecnologiasTextField);
			JWarningComponent warn = new JWarningComponent( this.tecnologiasTextField);
			warn.setBounds(115, 15, 334, 20);
			return warn;
		}
		return tecnologiasTextField;
	}
	
	protected JLabel getTecnologiasLabel(){
		if(tecnologiasLabel == null){
			tecnologiasLabel = new JLabel("Tecnologias");
			tecnologiasLabel.setSize(new java.awt.Dimension(100,20));
			tecnologiasLabel.setLocation(new java.awt.Point(10,15));
			tecnologiasLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return tecnologiasLabel;
	}
	
	protected JScrollPane detalhesScrollPane;
	
	protected JScrollPane getDetalhesScrollPane(){
		if(detalhesScrollPane == null){
			detalhesScrollPane = new JScrollPane();
			detalhesScrollPane.setSize(new Dimension(334, 42));
			detalhesScrollPane.setLocation(new java.awt.Point(115,38));
			detalhesScrollPane.setViewportView(getDetalhesTextPane());
			
		}
		return detalhesScrollPane;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getDetalhesTextPane(){
		if(detalhesTextPane == null){
			detalhesTextPane = new JTextPane();
			detalhesTextPane.setText("");
			this.binder.addBindProperty(this.processo, this.detalhesTextPane, "detalhes");

			this.hashComps.put("detalhes", this.detalhesTextPane);
			JWarningComponent warn = new JWarningComponent( this.detalhesTextPane);
			warn.setBounds(115,38, 334, 42);
			return warn;
		}
		return detalhesTextPane;
	}
	
	protected JLabel getDetalhesLabel(){
		if(detalhesLabel == null){
			detalhesLabel = new JLabel("Detalhes:");
			detalhesLabel.setSize(new java.awt.Dimension(100,20));
			detalhesLabel.setLocation(new java.awt.Point(10,38));
			detalhesLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return detalhesLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getRequerenteTextField(){
		if(requerenteTextField == null){
			requerenteTextField = new JTextField();
			requerenteTextField.setText("");
			requerenteTextField.setSize(new java.awt.Dimension(334,20));
			requerenteTextField.setLocation(new java.awt.Point(115,107));
			this.binder.addBindProperty(this.processo, this.requerenteTextField, "requerente");
			
			this.hashComps.put("requerente", this.requerenteTextField);
			JWarningComponent warn = new JWarningComponent( this.requerenteTextField);
			warn.setBounds(115, 107, 334, 20);
			return warn;
		}
		return requerenteTextField;
	}
	
	protected JLabel getRequerenteLabel(){
		if(requerenteLabel == null){
			requerenteLabel = new JLabel("Requerente:");
			requerenteLabel.setSize(new java.awt.Dimension(100,20));
			requerenteLabel.setLocation(new Point(10, 107));
			requerenteLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return requerenteLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getLocalRealizacaoTextField(){
		if(localRealizacaoTextField == null){
			localRealizacaoTextField = new JTextField();
			localRealizacaoTextField.setText("");
			localRealizacaoTextField.setSize(new java.awt.Dimension(334,20));
			localRealizacaoTextField.setLocation(new java.awt.Point(115,84));
			this.binder.addBindProperty(this.processo, this.localRealizacaoTextField, "localRealizacao");
			
			this.hashComps.put("localRealizacao", this.localRealizacaoTextField);
			JWarningComponent warn = new JWarningComponent( this.localRealizacaoTextField);
			warn.setBounds(115, 84, 334, 20);
			return warn;
		}
		return localRealizacaoTextField;
	}
	
	protected JLabel getLocalRealizacaoLabel(){
		if(localRealizacaoLabel == null){
			localRealizacaoLabel = new JLabel("Local:");
			localRealizacaoLabel.setSize(new java.awt.Dimension(100,20));
			localRealizacaoLabel.setLocation(new java.awt.Point(10,84));
			localRealizacaoLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return localRealizacaoLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getNumeroAlunosTextField(){
		if(numeroAlunosTextField == null){
			numeroAlunosTextField = new JTextField();
			numeroAlunosTextField.setText("");
			numeroAlunosTextField.setSize(new java.awt.Dimension(65,20));
			numeroAlunosTextField.setLocation(new Point(383, 154));
			this.binder.addBindProperty(this.processo, this.numeroAlunosTextField, "numeroAlunos");
			
			this.hashComps.put("numeroAlunos", this.numeroAlunosTextField);
			JWarningComponent warn = new JWarningComponent( this.numeroAlunosTextField);
			warn.setBounds(383, 154, 65, 20);
			return warn;
		}
		return numeroAlunosTextField;
	}
	
	protected JLabel getNumeroAlunosLabel(){
		if(numeroAlunosLabel == null){
			numeroAlunosLabel = new JLabel("Número de Alunos:");
			numeroAlunosLabel.setSize(new Dimension(117, 20));
			numeroAlunosLabel.setLocation(new Point(263, 154));
			numeroAlunosLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return numeroAlunosLabel;
	}
	

	
	@SuppressWarnings("unchecked")
	protected JComponent getRecomendacoesTextField(){
		if(recomendacoesTextField == null){
			recomendacoesTextField = new JTextField();
			recomendacoesTextField.setText("");
			recomendacoesTextField.setSize(new java.awt.Dimension(334,20));
			recomendacoesTextField.setLocation(new java.awt.Point(115,130));
			
			this.binder.addBindProperty(this.processo, recomendacoesTextField, "recomendacoes");
			
			this.hashComps.put("recomendacoes", recomendacoesTextField);
			JWarningComponent warn = new JWarningComponent( recomendacoesTextField);
			warn.setBounds(115, 130, 334, 20);
			return warn;
		}
		return recomendacoesTextField;
	}
	
	protected JLabel getRecomendacoesLabel(){
		if(recomendacoesLabel == null){
			recomendacoesLabel = new JLabel("Recomendações:");
			recomendacoesLabel.setSize(new java.awt.Dimension(114,20));
			recomendacoesLabel.setLocation(new java.awt.Point(10,130));
			recomendacoesLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return recomendacoesLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getDataPlanejadaField(){
		if(dataPlanejadaField == null){
			dataPlanejadaField = new DateHourChooser(messages
					.getCurrentLocale(), true, true, false);
			dataPlanejadaField.setSize(new Dimension(134, 20));
			dataPlanejadaField.setLocation(new Point(115, 154));
			
			this.binder.addBindProperty(this.processo, dataPlanejadaField, "dataPlanejada");
			
			this.hashComps.put("dataPlanejada", dataPlanejadaField);
			JWarningComponent warn = new JWarningComponent( dataPlanejadaField);
			warn.setBounds(115, 154, 134, 20);
			return warn;
		}
		return dataPlanejadaField;
	}
	
	protected JLabel getDataPlanejadaLabel(){
		if(dataPlanejadaLabel == null){
			dataPlanejadaLabel = new JLabel("Para a Data:");
			dataPlanejadaLabel.setHorizontalAlignment(JLabel.LEFT);
			dataPlanejadaLabel.setBounds(new Rectangle(10, 154, 114, 20));
		}
		return dataPlanejadaLabel;
	}
	
	
	
	protected JPanel getButtonsPanel(){

		if(buttonsPanel == null){
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(467, 40));
			buttonsPanel.setLocation(new Point(1, 190));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getCadastrarButton());
			buttonsPanel.add(getCancelarButton());
			
		}
		return buttonsPanel;
	}
	
	protected JButton getCadastrarButton(){

		if(cadastrarButton == null){
			cadastrarButton = new JButton(messages.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.Cadastrar"));
			cadastrarButton.setSize(new java.awt.Dimension(150,20));
			cadastrarButton.setLocation(new java.awt.Point(0,0));
			cadastrarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_save.png")));
			cadastrarButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						cadastreProcessoJudicial();
						dispose();
					}catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}});
				
		}
		return cadastrarButton;
	}
	
	protected JButton getCancelarButton(){
		if(cancelarButton == null){
			cancelarButton = new JButton("Cancelar");
			cancelarButton.setSize(new java.awt.Dimension(150,20));
			cancelarButton.setLocation(new java.awt.Point(0,20));
			cancelarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cancel.png")));
			cancelarButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {					
						dispose();		
					
				}});
		}
		return cancelarButton;
	}
	
	
	
	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(471,324));
					gui.add(new ProcessoTreinamentoCadastreForm(null),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}
	
	
	public ContatoProcessoTreinamento getProcesso() {
		return processo;
	}



}