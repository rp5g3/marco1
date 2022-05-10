package com.adapit.portal.ui.forms.treinamento.contapagar;

import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;


import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.CondicaoPagamento;
import com.adapit.portal.entidades.TipoPagamento;
import com.adapit.portal.services.remote.RemoteContaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.utils.Moeda;

public class ContaPagarCadastreForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel tipoLabel = null;
	private JRadioButton avistaRadioButton = null;
	private JRadioButton aprazoRadioButton = null;
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	//private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	private CondicaoPagamento condicao = new CondicaoPagamento();  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public ContaPagarCadastreForm() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		//carenciaLabel = new JLabel();
		//carenciaLabel.setBounds(new Rectangle(25, 110, 125, 22));
		//carenciaLabel.setText("Meses de Carência:");
		//entradasLabel = new JLabel();
		//entradasLabel.setBounds(new Rectangle(25, 80, 125, 22));
		//entradasLabel.setText("Número de Entradas:");
		//prestacoesLabel = new JLabel();
		//prestacoesLabel.setBounds(new Rectangle(25, 50, 125, 22));
		//prestacoesLabel.setText("Número de Prestações:");
		//tipoLabel = new JLabel();
		//tipoLabel.setBounds(new Rectangle(25, 20, 45, 22));
		//tipoLabel.setText("Tipo:");
		this.setSize(479, 304);
		this.setLayout(null);
		//this.add(tipoLabel, null);
		//this.add(getAvistaRadioButton(), null);
		//this.add(getAprazoRadioButton(), null);
		
		//this.add(prestacoesLabel, null);
		//this.add(getNumeroPrestacoes(), null);
		//this.add(getNumeroEntradas(), null);
		//this.add(getCarenciaMeses(), null);
	
		//this.add(entradasLabel, null);
		//this.add(carenciaLabel, null);
		this.add(getSalvarButton(), null);
		this.add(getExcluirButton(), null);
		//this.add(getEntradaPagto(), null);
		//this.add(getInicioPagto(), null);
		this.add(getCondicaoPagamentoPanel(), null);
		this.add(getDadosAgendaPanel(), null);
		this.add(totalPagarLabel, null);
		this.add(getValorTotalPagarTextField(), null);
		this.add(loteLabel, null);
		this.add(getLoteTextField(), null);
		this.add(valorLoteLabel, null);
		this.add(getValorArremateTextField(), null);
		this.add(arrematanteLabel, null);
		this.add(getNomeArrematanteTextField(), null);
		//this.add(getEntradaPagto(), null);
		this.add(gerarVencimentoLabel, null);
		this.add(getDiaComboBox(), null);
		this.add(getGerarContasButton(), null);
		//this.add(getInicioPagto(), null);
		
		/*ButtonGroup bg = new ButtonGroup();
		bg.add(getAvistaRadioButton());
		bg.add(getAprazoRadioButton());*/
	}

	
	public void editRegister(CondicaoPagamento cond){
		 
		condicao.setId(cond.getId());
		condicao.setCarenciaMeses(cond.getCarenciaMeses());
		condicao.setNumeroEntradas(cond.getNumeroEntradas());
		condicao.setNumeroPrestacoes(cond.getNumeroPrestacoes());
		condicao.setTipo(cond.getTipo());
		condicao.setAgendaTreinamento(cond.getAgendaTreinamento());
		if (cond.getTipo() == TipoPagamento.A_vista) avistaRadioButton.setSelected(true);
		else aprazoRadioButton.setSelected(true);
		System.out.println("pres="+cond.getNumeroPrestacoes()+" car="+cond.getCarenciaMeses() + " entr="+cond.getNumeroEntradas());
		getNumeroPrestacoes().setValue(cond.getNumeroPrestacoes());
		getNumeroEntradas().setValue(cond.getNumeroEntradas());
		getCarenciaMeses().setValue(cond.getCarenciaMeses());
		
		getExcluirButton().setEnabled(true);
		getSalvarButton().setEnabled(true);
		getGerarContasButton().setEnabled(true);
		
		updateTotalPagar();
		
	}
	
	private void gerarContasPagar(){
		
	}
	
	private void updateTotalPagar(){
		try {
			float val=valor;
			if (getIncluirComissãoCheckBox().isSelected()){
				float percent = Float.parseFloat(getComissaoTextField().getText().replace("%", "").replace(".", "").replace(",", ".").trim());
				float comiss = ((val*percent)/100);
				
				getComissaoLeiloeiroTextField().setText(Moeda.getValorReal(comiss));
				val = val+comiss;
			}else getComissaoLeiloeiroTextField().setText("R$");
			if (getIncluirJurosCheckBox().isSelected()){
				float percent = Float.parseFloat(getJurosTextField().getText().replace("%", "").replace(".", "").replace(",", ".").trim());
				int prest = (Integer) getNumeroPrestacoes().getValue();
				if (prest >0){
					for(int i=0; i < prest; i++)
						val = val+((val*percent)/100);
				}
			}
			if (getCorrecaoCheckBox().isSelected()){
				float percent = Float.parseFloat(getCorrecaoTextField().getText().replace("%", "").replace(".", "").replace(",", ".").trim());
				int prest = (Integer) getNumeroPrestacoes().getValue();
				if (prest >0){
					for(int i=0; i < prest; i++)
						val = val+((val*percent)/100);
				}
			}
			getValorTotalPagarTextField().setText(Moeda.getValorReal(val));
		} catch (NumberFormatException e) {
			//e.printStackTrace();
		}
	}
	
/*	public void editRegister(AgendaLote agenda) {
		Session s = LocalServicesUtility.getInstance().openSession();
		try{	
			CondicaoPagamento cond = 
				(CondicaoPagamento)
				s.createQuery("select al.condicaoPagamento from AgendaLote al where al.id="+agenda.getId())
				.uniqueResult();
			if (cond != null) editRegister(cond);
			else newRegister(agenda);
			
		}catch(Exception e){
			e.printStackTrace();			
		}finally{
			if (s != null) s.close();
		}
	}*/
	public void editRegister(AgendaTreinamento agenda) {
		try{	
			CondicaoPagamento cond = RemoteContaService.getInstance().loadCondicaoPagamento(agenda.getId());
			if (cond != null) editRegister(cond);
			else newRegister(agenda);
			
		}catch(Exception e){
			e.printStackTrace();			
		}
	}
	public void newRegister(AgendaTreinamento al){
		condicao.setId(0);
		condicao.setCarenciaMeses(0);
		condicao.setNumeroEntradas(0);
		condicao.setNumeroPrestacoes(0);
		condicao.setTipo(TipoPagamento.A_vista);
		condicao.setAgendaTreinamento(al);
		
		avistaRadioButton.setSelected(true);
		
		getNumeroPrestacoes().setValue(0);
		getNumeroEntradas().setValue(0);
		getCarenciaMeses().setValue(0);
		
		getExcluirButton().setEnabled(false);
		getSalvarButton().setEnabled(true);
		getGerarContasButton().setEnabled(false);
		
		updateTotalPagar();
	}
	
	public void bind(){
		
		condicao.setCarenciaMeses((Integer)carenciaMeses.getValue());
		condicao.setNumeroPrestacoes((Integer)numeroPrestacoes.getValue());
		condicao.setNumeroEntradas((Integer)numeroEntradas.getValue());
		if (getAvistaRadioButton().isSelected())
			condicao.setTipo(TipoPagamento.A_vista);
		else condicao.setTipo(TipoPagamento.A_prazo);
	}
	
	public CondicaoPagamento cadastre() throws Exception {
		try{			
			bind();
			if (condicao.getId() == 0) RemoteServicesUtility.getInstance().save(condicao);
			else RemoteServicesUtility.getInstance().update(condicao);
			return condicao;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	/*public CondicaoPagamento cadastre() throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try{			
			s.beginTransaction();
			//binder.bind(condicao);
			bind();
			if (condicao.getId() == 0) s.save(condicao);
			else s.update(condicao);
			
			s.getTransaction().commit();
			return condicao;
			
		}catch(Exception e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}*/
	
	/*private void delete() throws Exception{
		Session s = LocalServicesUtility.getInstance().openSession();
		try{			
			s.beginTransaction();
			
			s.delete(condicao);
			
			s.getTransaction().commit();			
		}catch(Exception e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}*/
	/**
	 * This method initializes avistaRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getAvistaRadioButton() {
		if (avistaRadioButton == null) {
			avistaRadioButton = new JRadioButton();
			avistaRadioButton.setBounds(new Rectangle(60, 5, 66, 22));
			avistaRadioButton.setText("A vista");
			avistaRadioButton.setSelected(true);
			avistaRadioButton.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						getNumeroPrestacoes().setEnabled(false);
						getNumeroEntradas().setEnabled(false);
						getNumeroPrestacoes().setValue(0);
						getNumeroEntradas().setValue(0);
					}
				}
				
			});
		}
		return avistaRadioButton;
	}

	/**
	 * This method initializes aprazoRadioButton	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	private JRadioButton getAprazoRadioButton() {
		if (aprazoRadioButton == null) {
			aprazoRadioButton = new JRadioButton();
			aprazoRadioButton.setBounds(new Rectangle(130, 5, 68, 22));
			aprazoRadioButton.setText("A prazo");
			aprazoRadioButton.addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						getNumeroPrestacoes().setEnabled(true);
						getNumeroEntradas().setEnabled(true);
					}
				}
				
			});
		}
		return aprazoRadioButton;
	}
	
	private JSpinner numeroPrestacoes;
	protected JSpinner getNumeroPrestacoes() {
		if (numeroPrestacoes == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
			//model.addChangeListener(new SpinnerListener());
		    numeroPrestacoes = new JSpinner(model);
		    numeroPrestacoes.setBounds(new Rectangle(140, 32, 44, 22));
			this.binder.addBindProperty(this.condicao, this.numeroPrestacoes,
					"numeroPrestacoes");
			numeroPrestacoes.setEnabled(false);
			numeroPrestacoes.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					updateTotalPagar();
				}
			});
			
		}
		return numeroPrestacoes;
	}
	
	private JSpinner numeroEntradas;
	protected JSpinner getNumeroEntradas() {
		if (numeroEntradas == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
			//model.addChangeListener(new SpinnerListener());
		    numeroEntradas = new JSpinner(model);
		    numeroEntradas.setBounds(new Rectangle(140, 60, 44, 22));
			this.binder.addBindProperty(this.condicao, this.numeroEntradas,
					"numeroEntradas");
			numeroEntradas.setEnabled(false);
			numeroEntradas.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					updateTotalPagar();
				}
			});
			
		}
		return numeroEntradas;
	}
	
	private JSpinner carenciaMeses;
	private JLabel prestacoesLabel = null;
	private JLabel entradasLabel = null;
	private JLabel carenciaLabel = null;
	private JButton salvarButton = null;
	private JButton excluirButton = null;
	protected JSpinner getCarenciaMeses() {
		if (carenciaMeses == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
			//model.addChangeListener(new SpinnerListener());
		    carenciaMeses = new JSpinner(model);
		    carenciaMeses.setBounds(new Rectangle(140, 88, 44, 22));
		    carenciaMeses.addChangeListener(new javax.swing.event.ChangeListener() {
		    	public void stateChanged(javax.swing.event.ChangeEvent e) {
		    		updateTotalPagar();
		    	}
		    });
			this.binder.addBindProperty(this.condicao, this.carenciaMeses,
					"carenciaMeses");

			
		}
		return carenciaMeses;
	}
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	private DateHourChooser entradaPagto;
	protected DateHourChooser getEntradaPagto(){
		if(entradaPagto == null){
			entradaPagto = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			entradaPagto.setBounds(new Rectangle(5, 245, 149, 21));
			
		}
		return entradaPagto;
	}
	
	private DateHourChooser inicioPagto;
	private JButton gerarContasButton = null;
	private JPanel condicaoPagamentoPanel = null;
	private JPanel dadosAgendaPanel = null;
	private JLabel valorLoteLabel = null;
	private JTextField valorArremateTextField = null;
	private JCheckBox incluirComissãoCheckBox = null;
	private JLabel arrematanteLabel = null;
	private JTextField nomeArrematanteTextField = null;
	private JTextField jurosTextField = null;
	private JTextField comissaoTextField = null;
	private JLabel loteLabel = null;
	private JTextField loteTextField = null;
	private JLabel totalPagarLabel = null;
	private JTextField valorTotalPagarTextField = null;
	private JLabel gerarVencimentoLabel = null;
	private JComboBox diaComboBox = null;
	private JCheckBox incluirJurosCheckBox = null;
	private JCheckBox correcaoCheckBox = null;
	private JTextField correcaoTextField = null;
	protected DateHourChooser getInicioPagto(){
		if(inicioPagto == null){
			inicioPagto = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			inicioPagto.setBounds(new Rectangle(217, 245, 149, 21));
			
		}
		return inicioPagto;
	}

	/**
	 * This method initializes salvarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getSalvarButton() {
		if (salvarButton == null) {
			salvarButton = new JButton();
			salvarButton.setBounds(new Rectangle(48, 230, 80, 26));
			salvarButton.setText("Salvar");
			salvarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						cadastre();
						AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de Condição de Pagamento", "Dados da condição de pagamento gravados");
					} catch (Exception e1) {
						e1.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Condição de Pagamento", "Não foi possível gravar os dados da condição de pagamento");
					}
				}
			});
		}
		return salvarButton;
	}

	/**
	 * This method initializes excluirButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getExcluirButton() {
		if (excluirButton == null) {
			excluirButton = new JButton();
			excluirButton.setBounds(new Rectangle(137, 230, 80, 26));
			excluirButton.setText("Excluir");
			excluirButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						cadastre();
						AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de Condição de Pagamento", "Dados da condição de pagamento excluídos");
					} catch (Exception e1) {
						e1.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Condição de Pagamento", "Não foi possível excluir os dados da condição de pagamento");
					}
				}
			});
		}
		return excluirButton;
	}

	/**
	 * This method initializes gerarContasButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getGerarContasButton() {
		if (gerarContasButton == null) {
			gerarContasButton = new JButton();
			gerarContasButton.setText("Gerar Contas a Pagar");
			gerarContasButton.setBounds(new Rectangle(274, 230, 148, 26));
			gerarContasButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gerarContasPagar();
				}
			});
		}
		return gerarContasButton;
	}

	/**
	 * This method initializes condicaoPagamentoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCondicaoPagamentoPanel() {
		if (condicaoPagamentoPanel == null) {
			condicaoPagamentoPanel = new JPanel();
			condicaoPagamentoPanel.setLayout(null);
			condicaoPagamentoPanel.setBounds(new Rectangle(5, 70, 197, 113));
			carenciaLabel = new JLabel();
			carenciaLabel.setBounds(new Rectangle(10, 88, 125, 22));
			carenciaLabel.setText("Meses de Carência:");
			entradasLabel = new JLabel();
			entradasLabel.setBounds(new Rectangle(10, 60, 125, 22));
			entradasLabel.setText("Número de Entradas:");
			prestacoesLabel = new JLabel();
			prestacoesLabel.setBounds(new Rectangle(10, 32, 125, 22));
			prestacoesLabel.setText("Número de Prestações:");
			tipoLabel = new JLabel();
			tipoLabel.setBounds(new Rectangle(10, 5, 45, 22));
			tipoLabel.setText("Tipo:");
			
			condicaoPagamentoPanel.add(tipoLabel, null);
			condicaoPagamentoPanel.add(getAvistaRadioButton(), null);
			condicaoPagamentoPanel.add(getAprazoRadioButton(), null);
			
			condicaoPagamentoPanel.add(prestacoesLabel, null);
			condicaoPagamentoPanel.add(getNumeroPrestacoes(), null);
			condicaoPagamentoPanel.add(getNumeroEntradas(), null);
			condicaoPagamentoPanel.add(getCarenciaMeses(), null);
		
			condicaoPagamentoPanel.add(entradasLabel, null);
			condicaoPagamentoPanel.add(carenciaLabel, null);
			
		
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAvistaRadioButton());
			bg.add(getAprazoRadioButton());
		}
		return condicaoPagamentoPanel;
	}

	/**
	 * This method initializes dadosAgendaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosAgendaPanel() {
		if (dadosAgendaPanel == null) {
			gerarVencimentoLabel = new JLabel();
			gerarVencimentoLabel.setText("Gerar com vencimento de pagamento em cada mês para o dia:");
			gerarVencimentoLabel.setBounds(new Rectangle(39, 190, 334, 22));
			totalPagarLabel = new JLabel();
			totalPagarLabel.setText("Valor Total a Pagar:");
			totalPagarLabel.setBounds(new Rectangle(255, 15, 101, 22));
			loteLabel = new JLabel();
			loteLabel.setText("Lote:");
			loteLabel.setBounds(new Rectangle(15, 15, 28, 22));
			arrematanteLabel = new JLabel();
			arrematanteLabel.setText("Nome do Arrematante:");
			arrematanteLabel.setBounds(new Rectangle(15, 40, 116, 22));
			valorLoteLabel = new JLabel();
			valorLoteLabel.setText("Valor:");
			valorLoteLabel.setBounds(new Rectangle(111, 15, 31, 22));
			dadosAgendaPanel = new JPanel();
			dadosAgendaPanel.setLayout(null);
			dadosAgendaPanel.setBounds(new Rectangle(216, 70, 247, 113));
			dadosAgendaPanel.add(getIncluirComissãoCheckBox(), null);
			dadosAgendaPanel.add(getJurosTextField(), null);
			dadosAgendaPanel.add(getComissaoTextField(), null);
			dadosAgendaPanel.add(getIncluirJurosCheckBox(), null);
			dadosAgendaPanel.add(getCorrecaoCheckBox(), null);
			dadosAgendaPanel.add(getCorrecaoTextField(), null);
			dadosAgendaPanel.add(getComissaoLeiloeiroTextField(), null);
		}
		return dadosAgendaPanel;
	}

	/**
	 * This method initializes valorArremateTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getValorArremateTextField() {
		if (valorArremateTextField == null) {
			valorArremateTextField = new JTextField();
			valorArremateTextField.setEditable(false);
			valorArremateTextField.setBounds(new Rectangle(142, 15, 107, 22));
		}
		return valorArremateTextField;
	}

	/**
	 * This method initializes incluirComissãoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getIncluirComissãoCheckBox() {
		if (incluirComissãoCheckBox == null) {
			incluirComissãoCheckBox = new JCheckBox();
			incluirComissãoCheckBox.setBounds(new Rectangle(10, 60, 189, 21));
			incluirComissãoCheckBox.setSelected(true);
			incluirComissãoCheckBox.setText("Incluir a comissão do leiloeiro de:");
			incluirComissãoCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					updateTotalPagar();
				}
			});
		}
		return incluirComissãoCheckBox;
	}

	/**
	 * This method initializes nomeArrematanteTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getNomeArrematanteTextField() {
		if (nomeArrematanteTextField == null) {
			nomeArrematanteTextField = new JTextField();
			nomeArrematanteTextField.setEditable(false);
			nomeArrematanteTextField.setBounds(new Rectangle(142, 40, 315, 22));
		}
		return nomeArrematanteTextField;
	}

	/**
	 * This method initializes jurosTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJurosTextField() {
		if (jurosTextField == null) {
			jurosTextField = new JTextField();
			jurosTextField.setBounds(new Rectangle(200, 5, 41, 22));
			jurosTextField.setText("0%");
			jurosTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					updateTotalPagar();
				}
			});
		}
		return jurosTextField;
	}

	/**
	 * This method initializes comissaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getComissaoTextField() {
		if (comissaoTextField == null) {
			comissaoTextField = new JTextField();
			comissaoTextField.setBounds(new Rectangle(200, 60, 41, 20));
			comissaoTextField.setText("5%");
			comissaoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					updateTotalPagar();
				}
			});
		}
		return comissaoTextField;
	}

	/**
	 * This method initializes loteTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getLoteTextField() {
		if (loteTextField == null) {
			loteTextField = new JTextField();
			loteTextField.setEditable(false);
			loteTextField.setBounds(new Rectangle(44, 15, 59, 22));
		}
		return loteTextField;
	}

	/**
	 * This method initializes valorTotalPagarTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getValorTotalPagarTextField() {
		if (valorTotalPagarTextField == null) {
			valorTotalPagarTextField = new JTextField();
			valorTotalPagarTextField.setEditable(false);
			valorTotalPagarTextField.setBounds(new Rectangle(356, 15, 101, 22));
		}
		return valorTotalPagarTextField;
	}

	/**
	 * This method initializes diaComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getDiaComboBox() {
		if (diaComboBox == null) {
			diaComboBox = new JComboBox();
			diaComboBox.setBounds(new Rectangle(376, 190, 40, 22));
			for (int i=1; i <=28; i++){
				diaComboBox.addItem(i);
			}
		}
		return diaComboBox;
	}

	/**
	 * This method initializes incluirJurosCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getIncluirJurosCheckBox() {
		if (incluirJurosCheckBox == null) {
			incluirJurosCheckBox = new JCheckBox();
			incluirJurosCheckBox.setBounds(new Rectangle(10, 5, 168, 22));
			incluirJurosCheckBox.setText("Incluir juros ao mês de:");
			incluirJurosCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					updateTotalPagar();
				}
			});
		}
		return incluirJurosCheckBox;
	}

	/**
	 * This method initializes correcaoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getCorrecaoCheckBox() {
		if (correcaoCheckBox == null) {
			correcaoCheckBox = new JCheckBox();
			correcaoCheckBox.setBounds(new Rectangle(10, 32, 176, 22));
			correcaoCheckBox.setText("Incluir correção ao mês de:");
			correcaoCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					updateTotalPagar();
				}
			});
		}
		return correcaoCheckBox;
	}

	/**
	 * This method initializes correcaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCorrecaoTextField() {
		if (correcaoTextField == null) {
			correcaoTextField = new JTextField();
			correcaoTextField.setBounds(new Rectangle(200, 32, 41, 20));
			correcaoTextField.setText("0%");
			correcaoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					updateTotalPagar();
				}
			});
		}
		return correcaoTextField;
	}

	/*private Encerramento encerramento;
	public void setEncerramento(Encerramento encerramento) {
		this.encerramento = encerramento;
	}*/

	private float valor;
	private JTextField comissaoLeiloeiroTextField = null;
	public float getValor() {
		return valor;
	}

	@SuppressWarnings("static-access")
	public void setValor(float valor) {
		this.valor = valor;
		Moeda m = new Moeda();    
		String str=(m.mascaraDinheiro(valor, Moeda.DINHEIRO_REAL));
		getValorArremateTextField().setText(str);
	}

	/**
	 * This method initializes comissaoLeiloeiroTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getComissaoLeiloeiroTextField() {
		if (comissaoLeiloeiroTextField == null) {
			comissaoLeiloeiroTextField = new JTextField();
			comissaoLeiloeiroTextField.setBounds(new Rectangle(69, 86, 103, 20));
			comissaoLeiloeiroTextField.setEditable(false);
		}
		return comissaoLeiloeiroTextField;
	}
	
/*	private JSpinner qtdSpinner;
	protected JSpinner getQtdSpinner() {
		if (qtdSpinner == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 900000, 1);
			//model.addChangeListener(new SpinnerListener());
		    qtdSpinner = new JSpinner(model);
		    qtdSpinner.setBounds(new Rectangle(116, 62, 70, 20));
			this.binder.addBindProperty(this.condicao, this.qtdSpinner,
					"qtd");

			
		}
		return qtdSpinner;
	}*/

	/*private JSpinner qtdSpinner;
	protected JComponent getQtdSpinner() {
		if (qtdSpinner == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 900000, 1);
			//model.addChangeListener(new SpinnerListener());
		    qtdSpinner = new JSpinner(model);
		    qtdSpinner.setBounds(new Rectangle(116, 62, 70, 20));
			this.binder.addBindProperty(this.condicao, this.qtdSpinner,
					"qtd");

			this.hashComps.put("qtd", this.qtdSpinner);
			JWarningComponent warn = new JWarningComponent(this.qtdSpinner);
			warn.setBounds(310, 0, 70, 20);
			return warn;
		}
		return qtdSpinner;
	}*/
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
