package com.adapit.portal.ui.forms.treinamento;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



import com.adapit.portal.entidades.ContatoProcessoTreinamento;
import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.remote.RemoteContatoService;
import com.adapit.portal.ui.forms.treinamento.contato.ProcessoTreinamentoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class ProcessoJudicialPanel extends JPanel{
private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private ContatoProcessoTreinamento processoJudicial = new ContatoProcessoTreinamento();  //  @jve:decl-index=0:
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected Endereco endereco = new Endereco();  //  @jve:decl-index=0:
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel nomeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
		
	private JPanel dadosProcessoPanel;
	
	private JTextField varaTextField;
	
	private JLabel varaTextFieldLabel;
	
	private JTextField processoTextField;
	
	private JLabel processoTextFieldLabel;
	
	private JTextField requerenteTextField;
	
	private JLabel requerenteTextFieldLabel;
	
	private JTextField requeridoTextField;
	
	private JLabel requeridoTextFieldLabel;
	
	private JTextField ofJusticaTextField;
	
	private JLabel ofJusticaTextFieldLabel;
	
	private JPanel processoPanel;
	
	private JTextField procRequerenteTextField;
	
	private JLabel procRequerenteTextFieldLabel;
	
	private JTextField procRequeridoTextField;
	
	private JLabel procRequeridoLabel;
	
		
	private JPanel buttonsPanel;
	
	private JButton editarProcessoButton;
	
	//private JButton cancelarButton;

		
	public ProcessoJudicialPanel(){
		super();
		initialize();
	}
	
	private void initialize(){
		comarcaLabel = new JLabel();
		comarcaLabel.setBounds(new Rectangle(15, 5, 70, 22));
		comarcaLabel.setText("Comarca:");
		setSize(new java.awt.Dimension(471,324));
		setLayout(null);
		add(getComarcaComboBox());
		add(getAdicionarButton());
		add(comarcaLabel);
		add(getDadosProcessoPanel());
		add(getButtonsPanel());
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		this.add(getEditarButton(), null);
		this.add(getRefreshButton(), null);
		updateComarcasComboBox();
	}
	
	private Hashtable<String, ContatoTreinamento> comarcas = new Hashtable<String,ContatoTreinamento>();  //  @jve:decl-index=0:
	@SuppressWarnings("unchecked")
	private void updateComarcasComboBox() {
		comarcaComboBox.removeAllItems();
		comarcas.clear();

		try {
			
			List list = RemoteContatoService.getInstance().listAllContatos();
			Iterator<ContatoTreinamento> it = list.iterator();
			
			int i = 0;
			while (it.hasNext()) {
				ContatoTreinamento c = it.next();
				comarcaComboBox.addItem(c.getNome());
				comarcas.put(c.getNome(), c);
				
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
	}
	/*private void updateComarcasComboBox() {
		
		Session s = null;
		comarcaComboBox.removeAllItems();
		comarcas.clear();

		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from Comarca c order by c.nome ASC").list();
			Iterator<Comarca> it = list.iterator();
			
			int i = 0;
			while (it.hasNext()) {
				Comarca c = it.next();
				comarcaComboBox.addItem(c.getNome());
				comarcas.put(c.getNome(), c);
				
				i++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (s != null)
				s.close();
		}
		
	}*/
	
	public ContatoProcessoTreinamento validateProcJudForm() throws Exception{
		setErrorIcon(false);
		binder.bind(processoJudicial);
		//Manual Code
		if (!validateProcessoJudicialBean()) throw new Exception("Bean Not Validated!");
		return processoJudicial;
	}
	
	public ContatoProcessoTreinamento cadastreProcessoJudicial() throws Exception{
		validateProcJudForm();
		String str = (String) processoTextField.getText();
		//return processoJudicial;
		if (lote == null || lote.getId() == 0){
			JOptionPane.showMessageDialog(this, "Primeiro é necessário salvar o lote!","Operações pendentes",JOptionPane.WARNING_MESSAGE);
			return null;
		}else if (comarcaComboBox.getSelectedItem() == null){
			JOptionPane.showMessageDialog(this, "É necessário informar a comarca!","Operações pendentes",JOptionPane.WARNING_MESSAGE);
			return null;
		}
		int resp = 0;
		if (processoJudicial.getId() == 0) 
			resp = JOptionPane.showConfirmDialog(ProcessoJudicialPanel.this,
					"Você quer inserir o processo judicial " + str + " no lote?");
			if (resp == JOptionPane.YES_OPTION) {
				try {
					ContatoTreinamento com = comarcas.get(comarcaComboBox.getSelectedItem());
					Object[] objs = null;//RemoteContatoService.getInstance().merge(com, processoJudicial, lote);
					return (ContatoProcessoTreinamento) objs[1];					
				}catch (Exception ex){
					ex.printStackTrace();
					JOptionPane.showMessageDialog(ProcessoJudicialPanel.this,
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
		Map errors = validate.validate(this.processoJudicial, "processoJudicial");
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
		
		processoJudicial.setId(0);
		processoJudicial.setTecnologias(null);
		processoJudicial.setDetalhes(null);
		processoJudicial.setRequerente(null);
		processoJudicial.setLocalRealizacao(null);
		processoJudicial.setRecomendacoes(null);
		processoJudicial.setDataPlanejada(null);
		processoJudicial.setNumeroAlunos(0);

		binder.reverseBind(this.processoJudicial);


		this.setErrorIcon(false);
		//updateUI();
	}
	
	public void editRegister(ContatoProcessoTreinamento objProcJud ){
//Nunca passar como argumento novos objetos!!!
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.processoJudicial, objProcJud);
			processoJudicial.setContatoTreinamento(objProcJud.getContatoTreinamento());
			System.out.println("Editando o processo");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		comarcaComboBox.setSelectedItem(objProcJud.getContatoTreinamento().getNome());
		binder.reverseBind(this.processoJudicial);
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
		this.getVaraTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getProcessoTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getRequerenteTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getRequeridoTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getOfJusticaTextField().firePropertyChange("warnBorder", !bool, bool);
		
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getNomeTextFieldLabel(){

		if(nomeTextFieldLabel == null){
			nomeTextFieldLabel = new JLabel("Nome da Comarca");
			nomeTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			nomeTextFieldLabel.setLocation(new java.awt.Point(15,20));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}
	
	
	
	protected JPanel getDadosProcessoPanel(){

		if(dadosProcessoPanel == null){
			dadosProcessoPanel = new JPanel();
			dadosProcessoPanel.setSize(new Dimension(458, 186));
			dadosProcessoPanel.setLocation(new Point(5, 27));
			dadosProcessoPanel.setLayout(null);
			
			/*dadosProcessoPanel.add(getVaraTextFieldLabel());
			dadosProcessoPanel.add(getProcessoTextFieldLabel());
			dadosProcessoPanel.add(getRequeridoTextFieldLabel());
			dadosProcessoPanel.add(getOfJusticaTextField());
			dadosProcessoPanel.add(getOfJusticaTextFieldLabel());
			dadosProcessoPanel.add(getProcessoPanel());*/
			dadosProcessoPanel.add(getVaraTextFieldLabel());
			dadosProcessoPanel.add(getProcessoTextField());
			dadosProcessoPanel.add(getProcessoTextFieldLabel());
			dadosProcessoPanel.add(getRequerenteTextField());
			dadosProcessoPanel.add(getRequerenteTextFieldLabel());
			dadosProcessoPanel.add(getRequeridoTextField());
			dadosProcessoPanel.add(getRequeridoTextFieldLabel());
			dadosProcessoPanel.add(getOfJusticaTextField());
			dadosProcessoPanel.add(getOfJusticaTextFieldLabel());
			dadosProcessoPanel.add(getVaraTextField());
			dadosProcessoPanel.add(getProcessoPanel());
			dadosProcessoPanel.add(getProcRequeridoPanel(), null);
		}
		return dadosProcessoPanel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getVaraTextField(){
		if(varaTextField == null){
			varaTextField = new JTextField();
			varaTextField.setText("");
			varaTextField.setSize(new java.awt.Dimension(70,20));
			varaTextField.setLocation(new java.awt.Point(115,15));
			//varaTextField.setEditable(false);
			this.binder.addBindProperty(this.processoJudicial, this.varaTextField, "vara");
			
			this.hashComps.put("vara", this.varaTextField);
			JWarningComponent warn = new JWarningComponent( this.varaTextField);
			warn.setBounds(115, 15, 70, 20);
			return warn;
		}
		return varaTextField;
	}
	
	protected JLabel getVaraTextFieldLabel(){

		if(varaTextFieldLabel == null){
			varaTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.Vara"));
			varaTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			varaTextFieldLabel.setLocation(new java.awt.Point(10,15));
			varaTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return varaTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getProcessoTextField(){
		if(processoTextField == null){
			processoTextField = new JTextField();
			processoTextField.setText("");
			processoTextField.setSize(new java.awt.Dimension(190,20));
			//processoTextField.setEditable(false);
			processoTextField.setLocation(new java.awt.Point(115,38));
			this.binder.addBindProperty(this.processoJudicial, this.processoTextField, "processo");
			
			this.hashComps.put("processo", this.processoTextField);
			JWarningComponent warn = new JWarningComponent( this.processoTextField);
			warn.setBounds(115, 38, 190, 20);
			return warn;
		}
		return processoTextField;
	}
	
	protected JLabel getProcessoTextFieldLabel(){

		if(processoTextFieldLabel == null){
			processoTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.Processo"));
			processoTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			processoTextFieldLabel.setLocation(new java.awt.Point(10,38));
			processoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return processoTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getRequerenteTextField(){
		if(requerenteTextField == null){
			requerenteTextField = new JTextField();
			requerenteTextField.setText("");
			requerenteTextField.setSize(new java.awt.Dimension(300,20));
			requerenteTextField.setLocation(new java.awt.Point(115,61));
			//requerenteTextField.setEditable(false);
			this.binder.addBindProperty(this.processoJudicial, this.requerenteTextField, "requerente");
			
			this.hashComps.put("requerente", this.requerenteTextField);
			JWarningComponent warn = new JWarningComponent( this.requerenteTextField);
			warn.setBounds(115, 61, 300, 20);
			return warn;
		}
		return requerenteTextField;
	}
	
	protected JLabel getRequerenteTextFieldLabel(){

		if(requerenteTextFieldLabel == null){
			requerenteTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.Requerente"));
			requerenteTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			requerenteTextFieldLabel.setLocation(new java.awt.Point(10,61));
			requerenteTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return requerenteTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getRequeridoTextField(){
		if(requeridoTextField == null){
			requeridoTextField = new JTextField();
			requeridoTextField.setText("");
			requeridoTextField.setSize(new java.awt.Dimension(300,20));
			requeridoTextField.setLocation(new java.awt.Point(115,84));
			//requeridoTextField.setEditable(false);
			this.binder.addBindProperty(this.processoJudicial, this.requeridoTextField, "requerido");
			
			this.hashComps.put("requerido", this.requeridoTextField);
			JWarningComponent warn = new JWarningComponent( this.requeridoTextField);
			warn.setBounds(115, 84, 300, 20);
			return warn;
		}
		return requeridoTextField;
	}
	
	protected JLabel getRequeridoTextFieldLabel(){

		if(requeridoTextFieldLabel == null){
			requeridoTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.Requerido"));
			requeridoTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			requeridoTextFieldLabel.setLocation(new java.awt.Point(10,84));
			requeridoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return requeridoTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getOfJusticaTextField(){
		if(ofJusticaTextField == null){
			ofJusticaTextField = new JTextField();
			ofJusticaTextField.setText("");
			//ofJusticaTextField.setEditable(false);
			ofJusticaTextField.setSize(new java.awt.Dimension(70,20));
			ofJusticaTextField.setLocation(new java.awt.Point(115,107));
			this.binder.addBindProperty(this.processoJudicial, this.ofJusticaTextField, "ofJustica");
			
			this.hashComps.put("ofJustica", this.ofJusticaTextField);
			JWarningComponent warn = new JWarningComponent( this.ofJusticaTextField);
			warn.setBounds(115, 107, 70, 20);
			return warn;
		}
		return ofJusticaTextField;
	}
	
	protected JLabel getOfJusticaTextFieldLabel(){

		if(ofJusticaTextFieldLabel == null){
			ofJusticaTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.layout.ComarcaCadastreForm.OficialdeJustiça"));
			ofJusticaTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			ofJusticaTextFieldLabel.setLocation(new java.awt.Point(10,107));
			ofJusticaTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return ofJusticaTextFieldLabel;
	}
	
	protected JPanel getProcessoPanel(){

		if(processoPanel == null){
			processoPanel = new JPanel();
			processoPanel.setSize(new java.awt.Dimension(407,23));
			processoPanel.setLocation(new java.awt.Point(10,130));
			processoPanel.setLayout(null);
			processoPanel.add(getProcRequerenteTextField());
			processoPanel.add(getProcRequerenteTextFieldLabel());
			
		}
		return processoPanel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getProcRequerenteTextField(){
		if(procRequerenteTextField == null){
			procRequerenteTextField = new JTextField();
			procRequerenteTextField.setText("");
			//procRequerenteTextField.setEditable(false);
			procRequerenteTextField.setSize(new java.awt.Dimension(260,20));
			procRequerenteTextField.setLocation(new java.awt.Point(140,0));
			
			this.binder.addBindProperty(this.processoJudicial, procRequerenteTextField, "procRequerente");
			
			this.hashComps.put("procRequerente", procRequerenteTextField);
			JWarningComponent warn = new JWarningComponent( procRequerenteTextField);
			
			warn.setBounds(140, 0, 260, 20);
			return warn;
		}
		return procRequerenteTextField;
	}
	
	protected JLabel getProcRequerenteTextFieldLabel(){

		if(procRequerenteTextFieldLabel == null){
			procRequerenteTextFieldLabel = new JLabel("Procurador Requerente:");
			procRequerenteTextFieldLabel.setSize(new Dimension(132, 20));
			procRequerenteTextFieldLabel.setLocation(new java.awt.Point(0,0));
			procRequerenteTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return procRequerenteTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getProcRequeridoTextField(){
		if(procRequeridoTextField == null){
			procRequeridoTextField = new JTextField();
			procRequeridoTextField.setText("");
			//procRequeridoTextField.setEditable(false);
			procRequeridoTextField.setSize(new Dimension(260, 20));
			procRequeridoTextField.setLocation(new Point(140, 0));
			
			this.binder.addBindProperty(this.processoJudicial, procRequeridoTextField, "procRequerido");
			
			this.hashComps.put("procRequerido", procRequeridoTextField);
			JWarningComponent warn = new JWarningComponent( procRequeridoTextField);
			warn.setBounds(140, 0, 260, 20);
			return warn;
		}
		return procRequeridoTextField;
	}
	
	protected JLabel getProcRequeridoLabel(){

		if(procRequeridoLabel == null){
			procRequeridoLabel = new JLabel("Procurador Requerido:");
			procRequeridoLabel.setHorizontalAlignment(JLabel.LEFT);
			procRequeridoLabel.setBounds(new Rectangle(0, 0, 131, 20));
		}
		return procRequeridoLabel;
	}
	
	
	protected JPanel getButtonsPanel(){

		if(buttonsPanel == null){
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new java.awt.Dimension(429,40));
			buttonsPanel.setLocation(new Point(15, 217));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getEditarProcessoButton());
			
			
		}
		return buttonsPanel;
	}
	
	protected JButton getEditarProcessoButton(){

		if(editarProcessoButton == null){
			editarProcessoButton = new JButton("Cadastrar o processo");
			editarProcessoButton.setSize(new java.awt.Dimension(150,20));
			editarProcessoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_save.png")));
			editarProcessoButton.setLocation(new java.awt.Point(0,0));
			editarProcessoButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try{
						cadastreProcessoJudicial();						
					}catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}});
				
		}
		return editarProcessoButton;
	}

	protected JButton buscarButton;

	private JLabel comarcaLabel = null;

	private JComboBox comarcaComboBox = null;

	private JButton adicionarButton = null;

	private JButton editarButton = null;

	private JButton refreshButton = null;
	protected JButton getBuscarButton(){

		if(buscarButton == null){
			buscarButton = new JButton("Adicionar processo");
			buscarButton.setSize(new java.awt.Dimension(150,20));
			buscarButton.setLocation(new java.awt.Point(0,0));
			buscarButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try{
											
					}catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}});
				
		}
		return buscarButton;
	}
	
	
	/**
	 * This method initializes comarcaComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComarcaComboBox() {
		if (comarcaComboBox == null) {
			comarcaComboBox = new JComboBox();
			comarcaComboBox.setBounds(new Rectangle(119, 5, 199, 22));
			comarcaComboBox.setEditable(false);	

		}
		return comarcaComboBox;
	}

	/**
	 * This method initializes adicionarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAdicionarButton() {
		if (adicionarButton == null) {
			adicionarButton = new JButton();
			adicionarButton.setBounds(new Rectangle(328, 5, 31, 22));
			adicionarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_add.png")));
			
			adicionarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().cadastrarContato();
				}
			});
		}
		return adicionarButton;
	}

	/**
	 * This method initializes editarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarButton() {
		if (editarButton == null) {
			editarButton = new JButton();
			editarButton.setBounds(new Rectangle(364, 5, 99, 22));
			editarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_edit.png")));
			editarButton.setText("Editar");
			editarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().editarContatos(comarcas.get(comarcaComboBox.getSelectedItem()));
				}
			});
		}
		return editarButton;
	}

	/**
	 * This method initializes refreshButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshButton() {
		if (refreshButton == null) {
			refreshButton = new JButton();
			refreshButton.setBounds(new Rectangle(89, 5, 22, 22));
			refreshButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateComarcasComboBox();
				}
			});
		}
		return refreshButton;
	}

	/**
	 * This method initializes procRequeridoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getProcRequeridoPanel() {
		if (procRequeridoPanel == null) {
			procRequeridoPanel = new JPanel();
			procRequeridoPanel.setLayout(null);
			procRequeridoPanel.setSize(new java.awt.Dimension(407,23));
			procRequeridoPanel.setLocation(new java.awt.Point(10,160));
			
			procRequeridoPanel.add(getProcRequeridoLabel(), null);
			procRequeridoPanel.add(getProcRequeridoTextField());
		}
		return procRequeridoPanel;
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
	
	
	public ContatoProcessoTreinamento getProcessoJudicial() {
		return processoJudicial;
	}

	private Treinamento lote;  //  @jve:decl-index=0:

	private JPanel procRequeridoPanel = null;
	public void setLote(Treinamento lote) {
		this.lote=lote;
	}




}
