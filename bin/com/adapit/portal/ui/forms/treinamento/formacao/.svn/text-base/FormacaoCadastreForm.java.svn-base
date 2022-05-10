package com.adapit.portal.ui.forms.treinamento.formacao;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.Technologies;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.forms.solution.itemsolution.ComercialSolutionItemCadastreForm;
import com.adapit.portal.ui.forms.training.SelecionarTrainingSolutionListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.training.CadastrarTrainingSolutionDialog;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.Moeda;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class FormacaoCadastreForm extends JPanel {

	private JPanel numeroLotePanel;

	private JComboBox codigoComboBox;

	private SwingBinder binder = new SwingBinder();

	private FormacaoTreinamento formacao = new FormacaoTreinamento();  //  @jve:decl-index=0:

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel codigoLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel avaliacaoCodLotePanel;

	private JTextField avaliacaoTextField;

	private JLabel avaliacaoTextFieldLabel;

	private JButton calcularPelosItensButton;

	private JTextPane descricaoTextArea;

	private JLabel descricaoTextAreaLabel;

	private JPanel treinamentosPanel;

	private JScrollPane treinamentosListScrollPane;

	private TreinamentosList treinamentosList;

	private JPanel treinamentosButtonsListPanel;

	private JButton addTreinamentosButton;

	private JButton editarTreinamentoButton;

	private JButton removeTreinamentoButton;

	private JPanel buttonsPanel;

	private JButton atualizarFormacoesButton;

	private JButton novaFormacaoButton;

	private JButton removerFormacaoButton;

	public FormacaoCadastreForm() {
		initialize();
	}

	private void initialize() {		
		setLayout(null);
		add(getNumeroLotePanel());
		add(getCadastramentoTabbedPane());
		add(getButtonsPanel());
		this.setBounds(new Rectangle(0, 0, 660, 599));		
		this.add(getCodigoLabel(), null);

		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		initializeFormacoes();
	}

	protected JPanel getNumeroLotePanel() {
		if (numeroLotePanel == null) {
			numeroLotePanel = new JPanel();
			numeroLotePanel.setSize(new Dimension(237, 60));
			numeroLotePanel.setLocation(new Point(15, 30));
			numeroLotePanel.setLayout(null);
			numeroLotePanel.add(getCodigoComboBox());
			numeroLotePanel.add(getRefreshLotesButton(),null);
			numeroLotePanel.add(getEditarLoteButton(), null);
		}
		return numeroLotePanel;
	}
	
	private Hashtable<String,FormacaoTreinamento> formacoes;  //  @jve:decl-index=0:

	
	@SuppressWarnings("unchecked")
	public void initializeFormacoes(){
		getCadastramentoTabbedPane().setVisible(false);	
		try {			
			List<FormacaoTreinamento> list = RemoteTreinamentoService.getInstance()
				.listAllTrainingFormations();
			formacoes = new Hashtable<String,FormacaoTreinamento>();
			codigoComboBox.removeAllItems();
			int i=0;
			if (list != null && list.size() > 0){
				Iterator<FormacaoTreinamento> it = list.iterator();
				while (it.hasNext()){
					FormacaoTreinamento l = it.next();					
					if (i == 0) formacao = l;
					if (!formacoes.containsKey(l.getCodigo())){
						codigoComboBox.addItem(l.getCodigo());
						formacoes.put(l.getCodigo(),l);
					}
					i++;
				}				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}		
	}

	private void codigoComboEdited(){
		String num = (String) codigoComboBox.getSelectedItem();
		try {
			FormacaoTreinamento formacao = formacoes.get(num);			
			if (formacao != null){
				FormacaoCadastreForm.this.formacao = formacao;
				editarFormacaoSelecionada();
			}
			else{
				int conf = JOptionPane.showConfirmDialog(FormacaoCadastreForm.this, "A formação de código " + num + " não existe. Você deseja criá-la?", "Criar nova formação", JOptionPane.YES_NO_OPTION);
				if (conf == JOptionPane.YES_OPTION){					
					try {
						FormacaoTreinamento newFormacao = new FormacaoTreinamento();
						newFormacao.setCodigo(num);
						newFormacao.setAvaliacao(0.0f);
						newFormacao.setDescricao("");
						newFormacao.setTechnology(Technologies.Linguagem_de_Programação_Java);
						newFormacao.setNome("");
						
						try{
							newFormacao = (FormacaoTreinamento) RemoteServicesUtility.getInstance().save(newFormacao);							
							if (formacoes != null && !formacoes.containsKey(newFormacao.getCodigo())){
								codigoComboBox.addItem(newFormacao.getCodigo());
								formacoes.put(newFormacao.getCodigo(),newFormacao);
								codigoComboBox.setSelectedItem(newFormacao.getCodigo());								
							}
						}catch(Exception ex){
							ex.printStackTrace();							
						}
						editarFormacaoSelecionada();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	}

	
	private boolean refreshingCodTreinamentoComboBox=false;
	
	@SuppressWarnings("unchecked")
	public JComponent getCodigoComboBox() {
		if (codigoComboBox == null) {
			codigoComboBox = new JComboBox();
			
			codigoComboBox.setEditable(true);
			
			codigoComboBox.setSize(new java.awt.Dimension(106, 22));
			codigoComboBox.setLocation(new java.awt.Point(0, 0));
			
			codigoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED && !refreshingCodTreinamentoComboBox){
						if (formacoes != null){
							formacao = formacoes.get(codigoComboBox.getSelectedItem());
							if (formacao != null) editarFormacaoSelecionada();
						} 
					}
				}				
			});
			
			this.binder.addBindProperty(this.formacao, this.codigoComboBox,
					"codigo");

			this.hashComps.put("codigo", this.codigoComboBox);
			JWarningComponent warn = new JWarningComponent(
					this.codigoComboBox);
			warn.setBounds(0, 0, 106, 22);
			codigoComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					if (evt.getActionCommand().equalsIgnoreCase("comboBoxEdited")){
						codigoComboEdited();
					}
				}				
			});
			return warn;
		}
		return codigoComboBox;
	}
	
	

	public FormacaoTreinamento validateFormacaoForm() throws Exception {
		setErrorIcon(false);		
		try {
			formacao.setAvaliacao((float) Moeda.valorRealToDouble(avaliacaoTextField.getText()));
		} catch (Exception e) {
			e.printStackTrace();			
		}
		try {
			formacao.setCargaHorariaTotal(Integer.parseInt(cargaHorariaTextField.getText()));
		} catch (Exception e) {
			e.printStackTrace();			
		}
		formacao.setNome(nomeFormacaoTextField.getText());
		formacao.setTechnology(Technologies.valueOf(((String)technologyComboBox.getSelectedItem()).replace(" ", "_")));
		formacao.setDescricao(descricaoTextArea.getText());
		
		if (!validateFormacaoBean()) throw new Exception("Formação Inválida");
	
		return formacao;
	}

	
	public FormacaoTreinamento cadastreFormacao() throws Exception {
		try{
			validateFormacaoForm();
			RemoteTreinamentoService.getInstance().updateTrainingFormationProperties(formacao);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return formacao;
	}

	@SuppressWarnings("unchecked")
	public boolean validateFormacaoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) try{
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}catch(Exception ex){
			
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.formacao, "formacaoTreinamento");
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
		formacao.setTrainingFormationItens(null);
		formacao.setId(0);
		formacao.setDescricao(null);
		formacao.setTechnology(null);
		formacao.setAvaliacao(0.0f);
		formacao.setCodigo(null);
		formacao.setNome(null);
		formacao.setCargaHorariaTotal(0);

		binder.reverseBind(this.formacao);
		this.setErrorIcon(false);
		updateUI();
	}
	
	@SuppressWarnings("unchecked")
	public List<ComercialSolutionItem> getItens(int loteId){			
		try{
			return RemoteTreinamentoService.getInstance().getTrainingSolutionItensByFormationId(loteId);			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return null;
	}

	@SuppressWarnings("unchecked")
	public void editRegister(FormacaoTreinamento objFormacao) {
		// Nunca passar como argumento novos objetos!!!
		getCadastramentoTabbedPane().setVisible(true);
		
		if (this.formacao == null) return;	
		
		try {
			objFormacao = (FormacaoTreinamento) RemoteTreinamentoService.getInstance().loadTrainingFormationByFormationId(objFormacao.getId());
		
			this.formacao.setAvaliacao(objFormacao.getAvaliacao());
			this.formacao.setId(objFormacao.getId());
			this.formacao.setCodigo(objFormacao.getCodigo());
			this.formacao.setTechnology(objFormacao.getTechnology());
			this.formacao.setNome(objFormacao.getNome());
			this.formacao.setCargaHorariaTotal(objFormacao.getCargaHorariaTotal());
			
			this.formacao.setDescricao(objFormacao.getDescricao());
			try {
				formacao.setImagem(objFormacao.getImagem());
				if (formacao.getImagem()!= null){
					formacao.getImagem().setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(objFormacao.getImagem().getId()));
					Imagem im = formacao.getImagem();
					getSmallLabelImage().setIcon(im.getSmallImageIcon(false));
					getSmallLabelImage().updateUI();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		
			try {				
				getTreinamentosList().updateTable();
				/*gerarDescButton.setEnabled(true);
				anexarButton.setEnabled(true);*/
				addTreinamentosButton.setEnabled(true);
				calcularPelosItensButton.setEnabled(true);
					
				atualizarFormacoesButton.setEnabled(true);
				addTreinamentosButton.setEnabled(true);
				avaliacaoTextField.setEnabled(true);
				avaliacaoTextField.setText(Moeda.getValorReal((Float)  formacao.getAvaliacao()));
				descricaoTextArea.setEnabled(true);
				descricaoTextArea.setText(formacao.getDescricao());
				cargaHorariaTextField.setText(""+formacao.getCargaHorariaTotal());
				
				if (formacao.getNome() != null){
					nomeFormacaoTextField.setText(formacao.getNome());
				}else{
					nomeFormacaoTextField.setText("");
				}
				if (formacao.getTechnology() != null){
					technologyComboBox.setSelectedItem(formacao.getTechnology().name().replace("_", " "));
				}else{
					technologyComboBox.setSelectedItem(0);
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setErrorIcon(false);
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(16, 523, 629, 53));
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {
		this.codigoComboBox.firePropertyChange("warnBorder", !bool, bool);
		this.avaliacaoTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getCodigoLabel() {
		if (codigoLabel == null) {
			codigoLabel = new JLabel("Código da Formação");
			codigoLabel.setBounds(new Rectangle(15, 7, 156, 22));
			codigoLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_put.png")));
			codigoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return codigoLabel;
	}
	


	protected JPanel getAvaliacaoCodLotePanel() {
		if (avaliacaoCodLotePanel == null) {
			tempoLabel = new JLabel();
			tempoLabel.setText("Carga Horária:");
			tempoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(5);
			gridLayout.setColumns(1);
			avaliacaoCodLotePanel = new JPanel();
			avaliacaoCodLotePanel.setLayout(gridLayout);
			avaliacaoCodLotePanel.setBounds(new Rectangle(523, 153, 104, 117));
			avaliacaoCodLotePanel.add(tempoLabel, null);
			avaliacaoCodLotePanel.add(getCargaHorariaTextField(), null);			
			avaliacaoCodLotePanel.add(getAvaliacaoTextFieldLabel(), null);
			avaliacaoCodLotePanel.add(getAvaliacaoTextField(), null);
			avaliacaoCodLotePanel.add(getCalcularPelosItensButton(), null);
		}
		return avaliacaoCodLotePanel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getAvaliacaoTextField() {
		if (avaliacaoTextField == null) {
			avaliacaoTextField = new JTextField();
			avaliacaoTextField.setText("");
			avaliacaoTextField.setHorizontalAlignment(SwingConstants.RIGHT);
			this.binder.addBindProperty(this.formacao, this.avaliacaoTextField,
					"avaliacao");

			this.hashComps.put("avaliacao", this.avaliacaoTextField);
			JWarningComponent warn = new JWarningComponent(
					this.avaliacaoTextField);
			warn.setBounds(105, 0, 95, 20);
			return warn;
		}
		return avaliacaoTextField;
	}

	protected JLabel getAvaliacaoTextFieldLabel() {

		if (avaliacaoTextFieldLabel == null) {
			avaliacaoTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteCadastreForm.Valor(R$)"));
			avaliacaoTextFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
			avaliacaoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
		}
		return avaliacaoTextFieldLabel;
	}

	protected JButton getCalcularPelosItensButton() {
		if (calcularPelosItensButton == null) {
			calcularPelosItensButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteCadastreForm.CalcularPelosItensdeProduto"));
			calcularPelosItensButton.setText("Calcular");
			calcularPelosItensButton.setIcon(new ImageIcon(getClass().getResource("/imgs/calculator.png")));
			calcularPelosItensButton.setEnabled(false);
			calcularPelosItensButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try {
						avaliacaoTextField.setText(Moeda.getValorReal((Float)  soma));	
						cargaHorariaTextField.setText(""+somaCarga);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return calcularPelosItensButton;
	}

	protected JScrollPane descScrollPane;

	//private JButton gerarDescButton = null;

	private JButton editarLoteButton = null;

	//private JButton anexarButton = null;

	protected JScrollPane getDescScrollPane() {
		if (descScrollPane == null) {
			descScrollPane = new JScrollPane();
			descScrollPane.setBounds(new Rectangle(15, 265, 501, 73));
			descScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			getDescricaoTextArea();
			descScrollPane.setViewportView(getDescricaoTextArea());
		}
		return descScrollPane;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getDescricaoTextArea() {
		if (descricaoTextArea == null) {
			descricaoTextArea = new JTextPane();
			descricaoTextArea.setSize(new Dimension(334, 50));
			descricaoTextArea.setLocation(new Point(90, 71));
			
			this.binder.addBindProperty(this.formacao, this.descricaoTextArea,
					"descricao");

			this.hashComps.put("descricao", this.descricaoTextArea);
			JWarningComponent warn = new JWarningComponent(this.descricaoTextArea);
			warn.setBounds(15, 210, 501, 73);
			return warn;
		}
		return descricaoTextArea;
	}

	protected JLabel getDescricaoTextAreaLabel() {
		if (descricaoTextAreaLabel == null) {
			descricaoTextAreaLabel = new JLabel("Objetivo/Descrição da Formação:");
			descricaoTextAreaLabel.setHorizontalAlignment(JLabel.LEFT);
			descricaoTextAreaLabel.setBounds(new Rectangle(16, 243, 216, 20));
		}
		return descricaoTextAreaLabel;
	}
	

	protected JTabbedPane cadastramentoTabbedPane;	
	
	protected JTabbedPane getCadastramentoTabbedPane() {
		if (cadastramentoTabbedPane == null) {			
			cadastramentoTabbedPane = new JTabbedPane();
			cadastramentoTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			cadastramentoTabbedPane.setSize(new Dimension(635, 395));
			cadastramentoTabbedPane.setLocation(new Point(15, 90));
			cadastramentoTabbedPane.add(getTreinamentosPanel(),"Dados da Formação");	
			cadastramentoTabbedPane.add(getOutrasInformacoesPanel(),"Outras Informações");
		}
		return cadastramentoTabbedPane;
	}


	protected JPanel getTreinamentosPanel() {
		if (treinamentosPanel == null) {			
			
			treinamentosPanel = new JPanel();
			treinamentosPanel.setSize(new Dimension(631, 301));
			treinamentosPanel.setLocation(new Point(15, 85));
			treinamentosPanel.setLayout(null);
			//produtosPanel.setBorder(titledBorder);
			treinamentosPanel.add(getTreinamentosListScrollPane());
			treinamentosPanel.add(getTreinamentosButtonsListPanel());
			treinamentosPanel.add(getAvaliacaoCodLotePanel(), null);
			treinamentosPanel.add(getDescricaoTextAreaLabel(), null);
			treinamentosPanel.add(getDescScrollPane(), null);
			//treinamentosPanel.add(getGerarDescButton(), null);
			//treinamentosPanel.add(getAnexarButton(), null);
			treinamentosPanel.add(getNomeFormacaoLabel(), null);
			treinamentosPanel.add(getNomeFormacaoTextField(), null);	
			treinamentosPanel.add(getResumoLabel(), null);
			treinamentosPanel.add(getTechnologyComboBox(), null);
		}
		return treinamentosPanel;
	}

	protected JScrollPane getTreinamentosListScrollPane() {
		if (treinamentosListScrollPane == null) {
			treinamentosListScrollPane = new JScrollPane();
			treinamentosListScrollPane.setSize(new Dimension(503, 168));
			treinamentosListScrollPane.setLocation(new java.awt.Point(15, 20));

			treinamentosListScrollPane.add(getTreinamentosList());
			treinamentosListScrollPane.setViewportView(getTreinamentosList());
		}
		return treinamentosListScrollPane;
	}

	protected TreinamentosList getTreinamentosList() {
		if (treinamentosList == null) {
			treinamentosList = new TreinamentosList();
			treinamentosList.addFocusListener(new FocusAdapter(){
				@Override
				public void focusGained(FocusEvent arg0) {
					editarTreinamentoButton.setEnabled(true);
					removeTreinamentoButton.setEnabled(true);
					getItensTreinamentoButton().setEnabled(true);
				}				
			});
			return treinamentosList;
		}
		return treinamentosList;
	}

	protected JPanel getTreinamentosButtonsListPanel() {
		if (treinamentosButtonsListPanel == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(5);
			gridLayout1.setColumns(1);
			treinamentosButtonsListPanel = new JPanel();
			treinamentosButtonsListPanel.setBounds(new Rectangle(524, 25, 100, 121));
			treinamentosButtonsListPanel.setLayout(gridLayout1);
			treinamentosButtonsListPanel.add(getBuscarTreinamentoButton(), null);
			treinamentosButtonsListPanel.add(getAddprodutosButton(), null);
			treinamentosButtonsListPanel.add(getEditarTreinamentoButton(), null);
			treinamentosButtonsListPanel.add(getRemoveProdutosButton(), null);
			treinamentosButtonsListPanel.add(getItensTreinamentoButton(),null);
		}
		return treinamentosButtonsListPanel;
	}

	private CadastrarTrainingSolutionDialog cadastrarTreinamentoDialog;
	
	protected JButton getAddprodutosButton() {
		if (addTreinamentosButton == null) {
			addTreinamentosButton = new JButton("Novo");
			addTreinamentosButton.setSize(new java.awt.Dimension(112, 20));
			addTreinamentosButton.setLocation(new java.awt.Point(0, 0));
			addTreinamentosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_add.png")));
			addTreinamentosButton.setEnabled(false);
			
			addTreinamentosButton.addActionListener(new AdicionarTreinamentoEmFormacao());
		}
		return addTreinamentosButton;
	}
	
	protected JButton buscarTreinamentoButton;
	
	@SuppressWarnings("unchecked")
	protected JButton getBuscarTreinamentoButton() {
		if (buscarTreinamentoButton == null) {
			buscarTreinamentoButton = new JButton("Buscar");
			buscarTreinamentoButton.setSize(new java.awt.Dimension(112, 20));
			buscarTreinamentoButton.setLocation(new java.awt.Point(0, 0));
			buscarTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_add.png")));
			buscarTreinamentoButton.setEnabled(true);
			buscarTreinamentoButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent evt) {
					SelecionarTrainingSolutionListForm sel = new SelecionarTrainingSolutionListForm();
					sel.setVisible(true);
					TrainingSolution training = sel.getTrainingSolution();
					if (training != null){
						String code = formacao.getCodigo();						
						try{
							TrainingFormationItem ip = RemoteTreinamentoService.getInstance()
								.mergeNewTrainingSolutionOnTrainingFormation(training, formacao);
							AdapitVirtualFrame.getInstance()
								.showOperationSucess("Adição de Treinamentos em Formações", "Treinamento "
										+ip.getTrainingSolution().getId()+" adicionado! Atualize os dados do item deste treinamento");
						}catch(Exception ex){
							ex.printStackTrace();
							AdapitVirtualFrame.getInstance().showErrorDialog("Adição de Treinamentos em Formações", "Não foi possível adicionar este treinamento na formação");
						}
												
						initializeFormacoes();						
						codigoComboBox.setSelectedItem(code);
						editarFormacaoSelecionada();
						
					}
				}
				
			});
		}
		return buscarTreinamentoButton;
	}
	

	
	private CadastrarTrainingSolutionDialog getCadastrarTreinamentoDialog(){
		if (cadastrarTreinamentoDialog == null){
			cadastrarTreinamentoDialog = new CadastrarTrainingSolutionDialog();
			cadastrarTreinamentoDialog.addWindowListener(new TreinamentoFrameListener());
		}
		return cadastrarTreinamentoDialog;
	}

	private class AdicionarTreinamentoEmFormacao extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {			
			try {
				getCadastrarTreinamentoDialog().getComercialSolutionCadastreForm().newRegister();
				getCadastrarTreinamentoDialog().setLocation(UIUtil.getScreenCenter(getCadastrarTreinamentoDialog()));
				getCadastrarTreinamentoDialog().setVisible(true);
			} catch (RuntimeException e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	private JDialog itemSolutionDialog;  //  @jve:decl-index=0:visual-constraint="730,11"
	
	private JDialog getItemSolutionDialog(){
		if (itemSolutionDialog == null){
			itemSolutionDialog = new javax.swing.JDialog(AdapitVirtualFrame.getInstance());
			itemSolutionDialog.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
			itemSolutionDialog.setLayout(new java.awt.BorderLayout());
			itemSolutionDialog.setSize(new Dimension(409, 264));
			itemSolutionDialog.setResizable(false);
			itemSolutionDialog.setModal(true);
			itemSolutionDialog.setTitle("Dados do Item do Treinamento");
			itemSolutionDialog.setLocation(UIUtil.getScreenCenter(itemSolutionDialog));
			itemSolutionDialog.add(getItemSolutionCadastreForm(),java.awt.BorderLayout.CENTER);
			getItemSolutionCadastreForm().getConfirmarButton().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){					
					getItemSolutionDialog().dispose();
				}
			});
		}
		return itemSolutionDialog;
	}
	
	private TrainingFormationItemCadastreForm itemSolutionCadastreForm;
	
	private TrainingFormationItemCadastreForm getItemSolutionCadastreForm(){
		if (itemSolutionCadastreForm == null){
			itemSolutionCadastreForm = new TrainingFormationItemCadastreForm();
			
		}
		return itemSolutionCadastreForm;
	}
	
	public void editItemSolucao(TrainingFormationItem ip, TrainingSolution p){
		getItemSolutionCadastreForm().setTrainingSolution(p);
		getItemSolutionCadastreForm().setFormacao(formacao);
		getItemSolutionCadastreForm().editRegister(ip);
		getItemSolutionDialog().setVisible(true);
		try {
			getItemSolutionCadastreForm().cadastreItemProduto();
			getTreinamentosList().updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newTrainingSolutionItem(TrainingSolution p){		
		getItemSolutionCadastreForm().setTrainingSolution(p);
		getItemSolutionCadastreForm().setFormacao(formacao);
		getItemSolutionCadastreForm().newRegister();
		getItemSolutionDialog().setVisible(true);
		try {
			getItemSolutionCadastreForm().cadastreItemProduto();
			getTreinamentosList().updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private JButton itensTreinamentoButton;
	protected JButton getItensTreinamentoButton(){
		if(itensTreinamentoButton == null){
			itensTreinamentoButton = new JButton("Ordem");
			itensTreinamentoButton.setEnabled(false);
			itensTreinamentoButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/package_itens.png",18,18));
			itensTreinamentoButton.setToolTipText("Ordenar o Item Deste Produto");
			itensTreinamentoButton.setSize(new java.awt.Dimension(100,24));
			itensTreinamentoButton.setLocation(new java.awt.Point(0,48));
			itensTreinamentoButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					int row = getTreinamentosList().getSelectedRow();
					if (row >= 0)try{
						TrainingFormationItem ip = formacao.getTrainingFormationItens().get(row);
						if (ip != null){
							editItemSolucao(ip,ip.getTrainingSolution());
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}				
			});
		}
		return itensTreinamentoButton;
	}
	
	public class TreinamentoFrameListener extends WindowAdapter{
		@Override
		public void windowClosed(WindowEvent evt) {	
			if (novoTreinamento){
				TrainingSolution p = getCadastrarTreinamentoDialog().getComercialSolutionCadastreForm().getSol();			
				newTrainingSolutionItem(p);
				
			}else{
				TrainingSolution p = getCadastrarTreinamentoDialog().getComercialSolutionCadastreForm().getSol();			
				int row = getTreinamentosList().getSelectedRow();
				if (row >= 0)try{
					TrainingFormationItem ip = formacao.getTrainingFormationItens().get(row);
					editItemSolucao(ip,p);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				novoTreinamento = true;
			}
			getTreinamentosList().updateTable();			
		}		
	}
	
	protected JButton getEditarTreinamentoButton() {
		if (editarTreinamentoButton == null) {
			editarTreinamentoButton = new JButton("Editar");
			editarTreinamentoButton.setSize(new java.awt.Dimension(112, 20));
			editarTreinamentoButton.setEnabled(false);
			editarTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_edit.png")));
			editarTreinamentoButton.setLocation(new java.awt.Point(0, 20));
			editarTreinamentoButton.addActionListener(new EditarProdutoLote());
		}
		return editarTreinamentoButton;
	}
	
	private boolean novoTreinamento=true;
	private class EditarProdutoLote extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			int row = getTreinamentosList().getSelectedRow();
			if (row >= 0)try{
				TrainingFormationItem ip = formacao.getTrainingFormationItens().get(row);
				TrainingSolution p = (TrainingSolution) ip.getTrainingSolution();
				if (p != null){
					novoTreinamento = false;
					
					CadastrarTrainingSolutionDialog c = getCadastrarTreinamentoDialog();				
					c.getComercialSolutionCadastreForm().editRegister(p);
					c.setLocation(UIUtil.getScreenCenter(c));
					c.setVisible(true);					
					getTreinamentosList().updateTable();
					getErrorPanel().removeAllElements();
					logErrorPanel.addAlert("Não esqueça de atualizar o valor e a descrição da formação! salve as alterações clicando no botão 'Atualizar Formação'");
					logErrorPanel.updateErrorList();
					logErrorPanel.setVisible(true);
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	protected JButton getRemoveProdutosButton() {
		if (removeTreinamentoButton == null) {
			removeTreinamentoButton = new JButton("Remover");
			removeTreinamentoButton.setEnabled(false);
			removeTreinamentoButton.setSize(new java.awt.Dimension(112, 20));
			removeTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_delete.png")));
			removeTreinamentoButton.setLocation(new java.awt.Point(0, 40));
			removeTreinamentoButton.addActionListener(new RemoverTreinamentoDeFormacao());
		
		}
		return removeTreinamentoButton;
	}
	
	public void removerProduto(){
		int row = getTreinamentosList().getSelectedRow();
		if (row >= 0)try{
			//String code = lote.getCodLote();
			TrainingFormationItem ip = formacao.getTrainingFormationItens().get(row);
			String desc = "";
			if (ip.getTrainingSolution().getNome() != null
					&& ip.getTrainingSolution().getNome().length()>40)
				desc = ip.getTrainingSolution().getNome().substring(0,40);
			else if (ip.getTrainingSolution().getNome() != null) desc = ip.getTrainingSolution().getNome();
			if (desc != null){
				int resp = JOptionPane.showConfirmDialog(FormacaoCadastreForm.this, "Você quer retirar o treinamento " + desc + " da formação " + formacao.getCodigo() + "?","Remover Treinamento da Formação",JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION){					
					try {
						resp = JOptionPane.showConfirmDialog(FormacaoCadastreForm.this, "Apagar em definitivo o treinamento " + desc + "?","Remover Treinamento da Base",JOptionPane.YES_NO_OPTION);
						if (resp == JOptionPane.YES_OPTION){								
							RemoteTreinamentoService.getInstance()
								.removeTrainingSolutionFromTrainingFormation(true, ip.getId());
						}else{
							RemoteTreinamentoService.getInstance()
								.removeTrainingSolutionFromTrainingFormation(false, ip.getId());
						}
						
						getTreinamentosList().updateTable();						
						getErrorPanel().removeAllElements();
						logErrorPanel.addAlert("Não esqueça de atualizar o valor e a descrição da formação! salve as alterações clicando no botão 'Atualizar Formação'");
						logErrorPanel.updateErrorList();
						logErrorPanel.setVisible(true);
						JOptionPane.showMessageDialog(FormacaoCadastreForm.this, "Operação realizada com sucesso","Remover Produto",JOptionPane.INFORMATION_MESSAGE);
							
					} catch(org.hibernate.exception.ConstraintViolationException ex){
						ex.printStackTrace();
						JOptionPane.showMessageDialog(FormacaoCadastreForm.this, "O treinamento não foi removido," +
								" pois está sendo usado em outras formações!"+'\n'+
								" É permitido que você remova somente o item da formação."+'\n'
								+" Favor repetir a operação.","Problema ao remover treinamento",JOptionPane.ERROR_MESSAGE);												
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(FormacaoCadastreForm.this, "O treinamento não foi removido!","Problema ao remover treinamento",JOptionPane.ERROR_MESSAGE);
					}					
				}
				
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private class RemoverTreinamentoDeFormacao extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			removerProduto();
		}
	}

	protected JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(628, 35));
			buttonsPanel.setLocation(new Point(16, 487));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getAtualizarFormacoesButton());
			buttonsPanel.add(getNovaFormacaoButton());
			buttonsPanel.add(getRemoverFormacaoButton());
		}
		return buttonsPanel;
	}

	protected JButton getAtualizarFormacoesButton() {
		if (atualizarFormacoesButton == null) {
			atualizarFormacoesButton = new JButton("Atualizar Formação");
			atualizarFormacoesButton.setSize(new java.awt.Dimension(80, 22));
			atualizarFormacoesButton.setEnabled(false);
			atualizarFormacoesButton.setIcon(getIcon("/imgs/basket_save.png"));
			atualizarFormacoesButton.setLocation(new java.awt.Point(0, 0));
			atualizarFormacoesButton.addActionListener(new AbstractAction(){
				public void doAction(ActionEvent evt){
					try {
						cadastreFormacao();						
						getErrorPanel().removeAllElements();
						getErrorPanel().setVisible(false);
						
						AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de formações", "Operação de cadastro realizada com sucesso!");
						editarFormacaoSelecionada();
					} catch (Exception e) {
						AdapitVirtualFrame.getInstance().showErrorDialog("Formulário de cadastro de formações",messages.getMessage(e.getMessage()));
					}
				}
			});
		}
		return atualizarFormacoesButton;
	}

	protected JButton getNovaFormacaoButton() {
		if (novaFormacaoButton == null) {
			novaFormacaoButton = new JButton("Nova Formação");
			novaFormacaoButton.setSize(new java.awt.Dimension(80, 22));
			novaFormacaoButton.setIcon(getIcon("/imgs/basket_add.png"));
			novaFormacaoButton.setLocation(new java.awt.Point(0, 22));
			novaFormacaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					novaFormacao();
				}
			});
			
		}
		return novaFormacaoButton;
	}
	
	public void novaFormacao(){
		initializeFormacoes();
		String last ="";
		if (codigoComboBox.getItemCount() > 0){
			last = (String) codigoComboBox.getItemAt(codigoComboBox.getItemCount()-1);
			last = last.substring(0, last.length()-1);
		}else last="001";
		codigoComboBox.setSelectedItem(last);
		codigoComboBox.requestFocus();
		atualizarFormacoesButton.setEnabled(false);
	}

	protected JButton getRemoverFormacaoButton() {
		if (removerFormacaoButton == null) {
			removerFormacaoButton = new JButton("Remover Formação");
			removerFormacaoButton.setSize(new java.awt.Dimension(80, 22));
			removerFormacaoButton.setIcon(getIcon("/imgs/basket_delete.png"));
			removerFormacaoButton.setLocation(new java.awt.Point(0, 44));
			removerFormacaoButton.addActionListener(getRemoverAction());
		}
		return removerFormacaoButton;
	}
	
	private RemoverFormacaoAction removerAction;
	public RemoverFormacaoAction getRemoverAction(){
		if (removerAction == null){
			removerAction = new RemoverFormacaoAction();
		}
		return removerAction;
	}
	
	public class RemoverFormacaoAction extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			FormacaoTreinamento l = formacoes.get(codigoComboBox.getSelectedItem());
			if (l != null){
				int resp = JOptionPane.showConfirmDialog(FormacaoCadastreForm.this, "Apagar a formação " + l.getCodigo(),"Apagar Formação",JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION){
					try {
						
						/*Session s = LocalServicesUtility.getInstance().openSession();
						try {
							s.beginTransaction();
							Leilao leilao = (Leilao) s.load(Leilao.class, ((Leilao)leiloes.get(getDataLeilaoComboBox().getSelectedIndex())).getId());
							l = (Lote) s.load(Lote.class,l.getId());
							leilao.getLotes().remove(l);
							Iterator<ItemProduto> it = l.getItensProduto().iterator();
							while(it.hasNext()){
								ItemProduto ip = it.next();
								ip.setLote(null);
								s.save(ip);
							}
					
							s.delete(l);
							s.getTransaction().commit();
							
							initializeLotes();
							atualizarLoteButton.setEnabled(false);
							JOptionPane.showMessageDialog(LoteCadastreForm.this, "O lote foi removido com sucesso!","Apagar Lote",JOptionPane.INFORMATION_MESSAGE);
							
						} catch (Exception e1) {
							e1.printStackTrace();
							s.getTransaction().rollback();
							JOptionPane.showMessageDialog(LoteCadastreForm.this, "O lote não foi removido!","Apagar Lote",JOptionPane.ERROR_MESSAGE);
						}finally{
							s.close();
						}*/
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}


	/**
	 * This method initializes gerarDescButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/*private JButton getGerarDescButton() {
		if (gerarDescButton == null) {
			gerarDescButton = new JButton();
			gerarDescButton.setBounds(new Rectangle(523, 280, 95, 24));
			gerarDescButton.setEnabled(false);
			gerarDescButton.setIcon(new ImageIcon(getClass().getResource("/imgs/wand.png")));
			gerarDescButton.setText("Gerar");
			gerarDescButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					int ne = getTreinamentosList().getRowCount();
					if (ne > 0){
						
						String str="";
						for (int i=0; i < ne; i++){
							int qtd = ((Integer)getTreinamentosList().getValueAt(i,3)).intValue();
							if (qtd > 1) str+=qtd +" itens do tipo "+ (String)getTreinamentosList().getValueAt(i,1) + " ["+((String)getTreinamentosList().getValueAt(i,0)).replaceAll("\n",",") + "]"+'\n';
							else str+=qtd +" item do tipo "+ (String)getTreinamentosList().getValueAt(i,1) + " ["+((String)getTreinamentosList().getValueAt(i,0)).replaceAll("\n",",") + "]"+'\n';
						}
						descricaoTextArea.setText(str);
					}
				}
			});
		}
		return gerarDescButton;
	}*/

	/**
	 * This method initializes editarLoteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarLoteButton() {
		if (editarLoteButton == null) {
			editarLoteButton = new JButton();
			editarLoteButton.setText("Editar Formação");
			editarLoteButton.setBounds(new Rectangle(1, 25, 151, 24));
			editarLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarLoteButton.addActionListener(new AbstractAction(){
				@Override
				public void doAction(ActionEvent evt) {
					editarFormacaoSelecionada();
				}				
			});
		}
		return editarLoteButton;
	}
	
	private void editarFormacaoSelecionada(){
		try {
			atualizarFormacoesButton.setEnabled(true);
			addTreinamentosButton.setEnabled(true);
			avaliacaoTextField.setEnabled(true);
			descricaoTextArea.setEnabled(true);
			
			if (codigoComboBox.getSelectedItem() != null){
				if (formacao != null && formacao.getCodigo() != null
					&&((JComboBox)getCodigoComboBox()).getSelectedItem() != null
					&& formacao.getCodigo().equals(((JComboBox)getCodigoComboBox()).getSelectedItem())){
					
				}else{
					formacao = formacoes.get(codigoComboBox.getSelectedItem());				
				}
				if (formacao != null) editRegister(formacao);
				
			}else{
				System.out.println("Lote não selecionado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method initializes anexarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/*private JButton getAnexarButton() {
		if (anexarButton == null) {
			anexarButton = new JButton();
			anexarButton.setBounds(new Rectangle(523, 309, 95, 24));
			anexarButton.setEnabled(false);
			anexarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/attach.png")));
			anexarButton.setText("Anexar");
			anexarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					int ne = getTreinamentosList().getRowCount();
					if (ne > 0){
						int row = getTreinamentosList().getSelectedRow();
						if (row >= 0){
							String str="";
						
							int qtd = ((Integer)getTreinamentosList().getValueAt(row,3)).intValue();
							
							str+=qtd +" itens do tipo " + (String)getTreinamentosList().getValueAt(row,1) + " ["+((String)getTreinamentosList().getValueAt(row,0)).replaceAll("\n",",")+"]"+'\n';
							
							descricaoTextArea.setText(descricaoTextArea.getText()+'\n'+str);
						}
					}
				}
			});
		}
		return anexarButton;
	}*/

	
	private JButton refreshLotesButton;
	
	private JButton getRefreshLotesButton() {
		if (refreshLotesButton == null) {
			refreshLotesButton = new JButton();
			refreshLotesButton.setBounds(new Rectangle(130, 0, 22, 22));
			refreshLotesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshLotesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateFormacoesList();
				}
			});
		}
		return refreshLotesButton;
	}
	
	public static String getFormatedDate(Date d){		
		return AdapitVirtualFrame.getInstance().format(d).trim();
	}


	



	@SuppressWarnings("unchecked")
	public void updateFormacoesList(){			
		if (formacoes != null) formacoes.clear();
		else{
			initializeFormacoes();
		}
		codigoComboBox.removeAllItems();
		refreshingCodTreinamentoComboBox=true;
		
		try {
			Iterator<FormacaoTreinamento> it = 
				RemoteTreinamentoService.getInstance().listAllTrainingFormations().iterator();
			{
				int i=0;
				while(it.hasNext()){					
					FormacaoTreinamento l = it.next();
					formacoes.put(l.getCodigo(),l);
					codigoComboBox.addItem(l.getCodigo());
					i++;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		refreshingCodTreinamentoComboBox=false;
					
	}


	private JLabel resumoLabel = null;
	
	private JLabel getResumoLabel(){
		if (resumoLabel == null){
			resumoLabel = new JLabel();
			resumoLabel.setBounds(new Rectangle(15, 216, 146, 22));
			resumoLabel.setText("Tecnologia da Formação:");
		}
		return resumoLabel;
	}

	private JComboBox technologyComboBox = null;
	private JComboBox getTechnologyComboBox() {
		if (technologyComboBox == null) {
			technologyComboBox = new JComboBox();
			technologyComboBox.setBounds(new Rectangle(165, 216, 352, 22));
			Technologies vet[] = Technologies.values();
			for(int i=0; i < vet.length; i++){
				technologyComboBox.addItem(vet[i].name().replaceAll("_", " "));				
			}
		}
		return technologyComboBox;
	}
	
	private JLabel nomeFormacaoLabel = null;
	
	private JLabel getNomeFormacaoLabel(){
		if (nomeFormacaoLabel == null){
			nomeFormacaoLabel = new JLabel();
			nomeFormacaoLabel.setBounds(new Rectangle(15, 190, 146, 22));
			nomeFormacaoLabel.setText("Nome da Formação:");
		}
		return nomeFormacaoLabel;
	}

	private JTextField nomeFormacaoTextField = null;
	private JTextField getNomeFormacaoTextField() {
		if (nomeFormacaoTextField == null) {
			nomeFormacaoTextField = new JTextField();
			nomeFormacaoTextField.setBounds(new Rectangle(165, 190, 352, 22));
		}
		return nomeFormacaoTextField;
	}

	private JLabel imagemTitleLabel = null;

	private JButton buscarButton = null;

	private JPanel imagePanel = null;
	
	private JPanel getImageMainPanel() {
		if (imageMainPanel == null) {
			imageMainPanel = new JPanel();
			imageMainPanel.setLayout(null);
			imageMainPanel.setBounds(new Rectangle(5, 9, 270, 111));
			imagemTitleLabel = new JLabel();
			imagemTitleLabel.setBounds(new Rectangle(0, 0, 269, 20));
			imagemTitleLabel.setText("Imagem para Propaganda:");
					
			imageMainPanel.add(imagemTitleLabel, null);
			imageMainPanel.add(getBuscarButton(), null);
			imageMainPanel.add(getImagePanel(), null);
		}
		return imageMainPanel;
	}
	
	private JButton getBuscarButton() {
		if (buscarButton == null) {
			buscarButton = new JButton();
			buscarButton.setBounds(new Rectangle(3, 32, 123, 67));
			buscarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/imagem.png")));
			buscarButton.setHorizontalTextPosition(SwingConstants.CENTER);
			buscarButton.setVerticalTextPosition(SwingConstants.TOP);
			buscarButton.setText("Buscar");
			buscarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (formacao.getId() != 0){
						anexarImagem();
					}
					else{
						JOptionPane.showMessageDialog(FormacaoCadastreForm.this, "Primeiro é preciso cadastrar a formação!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						getCadastramentoTabbedPane().setSelectedIndex(0);
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

	private JLabel smallLabelImage;
	
	protected JLabel getSmallLabelImage() {
		if (smallLabelImage == null) {
			smallLabelImage = new JLabel();
			smallLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			smallLabelImage.setHorizontalAlignment(SwingConstants.CENTER);						
		}
		return smallLabelImage;
	}
	
	private JButton anexar;
	public void anexarImagem(){		
		try {				
			if (anexar == null){				 
				anexar = new JButton("Anexar na formação");
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
		
		@Override
		public void actionPerformed(ActionEvent evt) {
					
			int rows[] = ilf.getBaseTable().getSelectedRows();
			if (rows != null){
				try {
					Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[0]);
					RemoteTurmaService.getInstance().mergeImageTrainingFormation(im.getId(),formacao.getId());
					formacao.setImagem(im);
					getSmallLabelImage().setIcon(im.getSmallImageIcon(false));
					getSmallLabelImage().updateUI();
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Imagem em Formação", "Imagem anexada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm().getButtonsPanel().remove(anexar);								
			}
			AdapitVirtualFrame.getInstance().getListaImagensFrame().dispose();
		}
	}

	/**
	 * This method initializes outrasInformacoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOutrasInformacoesPanel() {
		if (outrasInformacoesPanel == null) {
			outrasInformacoesPanel = new JPanel();
			outrasInformacoesPanel.setLayout(null);
			outrasInformacoesPanel.add(getImageMainPanel(), null);
		}
		return outrasInformacoesPanel;
	}

	/**
	 * This method initializes cargaHorariaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCargaHorariaTextField() {
		if (cargaHorariaTextField == null) {
			cargaHorariaTextField = new JTextField();
			this.binder.addBindProperty(this.formacao, this.cargaHorariaTextField,
			"cargaHorariaTotal");
		}
		return cargaHorariaTextField;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(449, 389));
				gui.add(new FormacaoCadastreForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

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
	
	private float soma=0;
	
	private int somaCarga=0;

	private JPanel imageMainPanel = null;

	private JPanel outrasInformacoesPanel = null;  //  @jve:decl-index=0:visual-constraint="727,389"

	private JLabel tempoLabel = null;

	private JTextField cargaHorariaTextField = null;

	@SuppressWarnings("unchecked")
	private class TreinamentosList extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		@SuppressWarnings("deprecation")
		public TreinamentosList() {
			super();
			this.setModel(new ProdutosListModel(null));
			this.addPropertyChangeListener(new ProdutosListPropertyChangeListener());
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			setModel(new ProdutosListModel(null));
			getColumnModel().getColumn(0).setPreferredWidth(100);
			updateUI();
		}

		@SuppressWarnings("deprecation")
		public TreinamentosList(List elements) {

			super();
			this.elements = elements;
			this.setModel(new ProdutosListModel(null));
			this.addPropertyChangeListener(new ProdutosListPropertyChangeListener());
			getColumnModel().getColumn(0).setPreferredWidth(100);
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			updateTable();
		}

		public void updateTable() {			
			soma=0;
			somaCarga=0;
			try{
				
				List list = RemoteTreinamentoService.getInstance()
					.getTrainingFormationItensByFormationId(formacao.getId());
				formacao.setTrainingFormationItens(new ArrayList<TrainingFormationItem>());
				if (elements == null) elements = new ArrayList();
				elements.clear();
				if (list != null && list.size() > 0) {
					int ne = list.size();
					java.util.Iterator<TrainingFormationItem> it = list.iterator();
					java.lang.Object values[][] = new java.lang.Object[ne][6];
					int i = 0;					
					while (it.hasNext()) {						
						TrainingFormationItem ip = (TrainingFormationItem) it.next();
						
						Object[] prods = RemoteTreinamentoService.getInstance()
							.getTrainingFormationItemPropertiesByItemId(ip.getId());
						String catname = (String) prods[prods.length-1];

						values[i][0] = prods[2];
						values[i][1] = catname;
						values[i][2] = Moeda.getValorReal((Float)  prods[3]);
						
						
						soma += (Float)  prods[3];
						
						TrainingSolution prod = new TrainingSolution();
						prod.setId((Integer) prods[0]);
						prod.setCargaHoraria((Integer)prods[4]);
						somaCarga+=prod.getCargaHoraria();
						
						values[i][3] = prod.getCargaHoraria();
						prod.setAvaliacao((Float)prods[3]);
						prod.setNome((String)prods[2]);
						prod.setDataCriacao((Date)prods[1]);
						
						values[i][4] = AdapitVirtualFrame.formatDateTime(prod.getDataCriacao());
						
						values[i][5] = ip.getItemOrder();
						
						ip.setTrainingSolution(prod);
						
						if (formacao.getTrainingFormationItens() == null)
							formacao.setTrainingFormationItens(new ArrayList());
						formacao.getTrainingFormationItens().add(ip);
											
						i++;
						elements.add(ip);
					}
								
					setModel(new ProdutosListModel(values));
					getColumnModel().getColumn(0).setPreferredWidth(100);
					updateUI();
				} else {
					setModel(new ProdutosListModel(null));
					getColumnModel().getColumn(0).setPreferredWidth(100);
					updateUI();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}

		private class ProdutosListModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class,String.class,String.class,
					Integer.class, String.class, Integer.class };

			boolean canEdit[] = new boolean[] {false, false, false, false, false, false };

			public ProdutosListModel(Object[][] values) {

				super(
						values,
						new String[] {"Treinamento","Categoria","Valor Unitário","Carga Horária","Criado em","Ordem" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class ProdutosListPropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				FormacaoCadastreForm.TreinamentosList jt = (FormacaoCadastreForm.TreinamentosList) e
						.getSource();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.ComercialSolutionItem) {
							
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

	}
	
	public void setSelectedFormacaoTreinamento(FormacaoTreinamento l) {		
		editRegister(l);
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"