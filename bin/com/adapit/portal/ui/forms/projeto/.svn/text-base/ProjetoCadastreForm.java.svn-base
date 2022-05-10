package com.adapit.portal.ui.forms.projeto;

import java.awt.Dimension;
import java.awt.GridLayout;
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
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.ItemProjeto;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.services.local.LocalProjetoService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.projeto.AgendaProjetoDialog;
import com.adapit.portal.ui.frames.solution.CadastrarComercialSolutionDialog;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
@SuppressWarnings({"serial","unchecked"})
public class ProjetoCadastreForm extends JPanel {

	private JPanel numeroProjetoPanel;

	private JComboBox codProjetoComboBox;

	private SwingBinder binder = new SwingBinder();

	private Projeto projeto = new Projeto();  //  @jve:decl-index=0:

	private Map hashComps = new java.util.HashMap();

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel codProjetoTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel avaliacaoCodProjetoPanel;

	private JTextField avaliacaoTextField;

	private JLabel avaliacaoTextFieldLabel;

	private JButton calcularPelosItensButton;

	private JTextArea descricaoTextArea;

	private JLabel descricaoTextAreaLabel;

	private JPanel produtosPanel;

	private JScrollPane produtosListScrollPane;

	private ProdutosList produtosList;

	private JPanel produtosButtonsListPanel;

	private JButton addProdutosButton;

	private JButton editarProdutoButton;

	private JButton removeProdutoButton;

	private JPanel buttonsPanel;

	private JButton atualizarProjetoButton;

	private JButton novoProjetoButton;

	private JButton removerProjetoButton;

	public ProjetoCadastreForm() {

		initialize();
	}

	private void initialize() {
		setLayout(null);
		add(getNumeroProjetoPanel());
		add(getProdutosPanel());
		add(getButtonsPanel());
		this.setBounds(new Rectangle(0, 0, 660, 478));
		this.add(getNovoLeilaoButton(), null);
		this.add(getEditarLeilaoButton(), null);
		this.add(getAgendarLeilaoButton(), null);
		this.add(getEditarProjetoButton(), null);
		
		this.add(getCodProjetoTextFieldLabel(), null);
		// Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		// End
		//updateLeilaoList();
		initializeProjetos();
	}

	protected JPanel getNumeroProjetoPanel() {

		if (numeroProjetoPanel == null) {
			numeroProjetoPanel = new JPanel();
			numeroProjetoPanel.setSize(new Dimension(631, 22));
			numeroProjetoPanel.setLocation(new Point(15, 30));
			numeroProjetoPanel.setLayout(null);
			numeroProjetoPanel.add(getCodProjetoComboBox());
			
			numeroProjetoPanel.add(getRefreshLeiloesButton(), null);
		}
		return numeroProjetoPanel;
	}
	
	private Hashtable<String,Projeto> projetos;  //  @jve:decl-index=0:

	
	private void initializeProjetos(){
		
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("from Projeto projeto " ).list();
			projetos = new Hashtable<String,Projeto>();
			codProjetoComboBox.removeAllItems();
			int i=0;
			if (list != null && list.size() > 0){
				Iterator<Projeto> it = list.iterator();
				while (it.hasNext()){
					Projeto l = it.next();
					l.getAgendaProjeto();					
					l.getAgendas().iterator();
					if (i == 0) projeto = l;
					if (!projetos.containsKey(l.getCodigoProjeto())){
						codProjetoComboBox.addItem(l.getCodigoProjeto());
						projetos.put(l.getCodigoProjeto(),l);
					}
					i++;
				}
			}
		} catch (Exception e2) {
			
			e2.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
	}
	
	public JComponent getCodProjetoComboBox() {

		if (codProjetoComboBox == null) {
			codProjetoComboBox = new JComboBox();
			codProjetoComboBox.setEditable(true);
			//codProjetoComboBox.setText("");
			codProjetoComboBox.setSize(new java.awt.Dimension(106, 22));
			codProjetoComboBox.setLocation(new java.awt.Point(0, 0));
			//initializeProjetos();
			
			codProjetoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						/*getProdutosList().setElements(null);
						getProdutosList().updateTable();
						atualizarProjetoButton.setEnabled(false);
						gerarDescButton.setEnabled(false);
						anexarButton.setEnabled(false);
						calcularPelosItensButton.setEnabled(false);
						editarProdutoButton.setEnabled(false);
						removeProdutoButton.setEnabled(false);
						addProdutosButton.setEnabled(false);
						avaliacaoTextField.setText("");
						avaliacaoTextField.setEnabled(false);
						descricaoTextArea.setText("");
						descricaoTextArea.setEnabled(false);*/
						projeto = projetos.get(codProjetoComboBox.getSelectedItem());
						if (projeto != null) editarProjetoSelecionado();
					}
				}				
			});
			
			this.binder.addBindProperty(this.projeto, this.codProjetoComboBox,
					"codProjeto");

			this.hashComps.put("codProjeto", this.codProjetoComboBox);
			JWarningComponent warn = new JWarningComponent(
					this.codProjetoComboBox);
			warn.setBounds(0, 0, 106, 22);
			codProjetoComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					if (evt.getActionCommand().equalsIgnoreCase("comboBoxEdited")){
						
						String num = (String) codProjetoComboBox.getSelectedItem();
						try {
							Projeto projeto = LocalProjetoService.getInstance().getLoteByCodLote(num);
							if (projeto != null){
								ProjetoCadastreForm.this.projeto = projeto;
								editarProjetoSelecionado();
								//editRegister(projeto);
							}
							else{
								int conf = JOptionPane.showConfirmDialog(ProjetoCadastreForm.this, "O projeto de número " + num + " não existe. Você deseja criá-lo", "Criar novo projeto", JOptionPane.YES_NO_OPTION);
								if (conf == JOptionPane.YES_OPTION){
									
									try {
										//cadastreProjeto();
										Projeto newProjeto = new Projeto();
										newProjeto.setCodigoProjeto(num);
										newProjeto.setAvaliacao(0.0f);
										newProjeto.setDescricao("projeto vazio");
										Session s = LocalServicesUtility.getInstance().openSession();
										try{
											s.beginTransaction();											
											s.save(newProjeto);
											s.getTransaction().commit();
											
											if (!projetos.containsKey(newProjeto.getCodigoProjeto())){
												codProjetoComboBox.addItem(newProjeto.getCodigoProjeto());
												projetos.put(newProjeto.getCodigoProjeto(),newProjeto);
												codProjetoComboBox.setSelectedItem(newProjeto.getCodigoProjeto());
												editRegister(newProjeto);
											}
										}catch(Exception ex){
											ex.printStackTrace();
											s.getTransaction().rollback();
										}finally{
											s.close();
										}
										/*LocalProjetoService.getInstance().saveOrUpdate(newProjeto);
										
										List list = LocalProjetoService.getInstance().listLikeCodProjeto("");
										projetos = new Hashtable<String,Projeto>();
										if (list != null && list.size() > 0){
											Iterator<Projeto> it = list.iterator();
											codProjetoComboBox.removeAllItems();
											while (it.hasNext()){
												Projeto l = it.next();
												if (!projetos.containsKey(l.getCodProjeto())){
													codProjetoComboBox.addItem(l.getCodProjeto());
													projetos.put(l.getCodProjeto(),l);
												}
											}
										}
										codProjetoComboBox.setSelectedItem(num);
										editRegister(newProjeto);*/
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}
							}
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}				
			});
			return warn;
		}
		return codProjetoComboBox;
	}
	
	

	public Projeto validateProjetoForm() throws Exception {
		setErrorIcon(false);
		try {
			projeto.setAvaliacao(Float.parseFloat(avaliacaoTextField.getText()));
		} catch (Exception e) {
			e.printStackTrace();			
		}
		projeto.setDescricao(descricaoTextArea.getText());
		if (!validateProjetoBean()) throw new Exception("Projeto Inválido");
		// Manual Code
		/*if (!validateProjetoBean())
			throw new Exception("ErroProjetoDadosInvalidos");*/
		return projeto;
	}

	public Projeto cadastreProjeto() throws Exception {
		
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.load(projeto,projeto.getId());
			validateProjetoForm();
			s.update(projeto);
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null) s.close();
		}
		//LocalProjetoService.getInstance().saveOrUpdate(l);
		return projeto;
	}

	public boolean validateProjetoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.projeto, "projeto");
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

		// projeto.setComprador(null);
		// projeto.setComitente(null);
		projeto.setItensProjeto(null);
		projeto.setId(0);
		projeto.setDescricao(null);
		projeto.setAvaliacao(0.0f);
		projeto.setCodigoProjeto(null);
		// projeto.setRetirado();

		binder.reverseBind(this.projeto);

		this.setErrorIcon(false);
		updateUI();
	}

	public void editRegister(Projeto objProjeto) {
		// Nunca passar como argumento novos objetos!!!
		/*Hibernate.initialize(objProjeto);
		Hibernate.initialize(objProjeto.getComitente());
		Hibernate.initialize(objProjeto.getItensProduto());
		Hibernate.initialize(objProjeto.getLeilao());*/
		Session s = LocalServicesUtility.getInstance().openSession();
		//Hibernate.initialize(objProjeto.getComitente());
		try {
			objProjeto = (Projeto) s.load(Projeto.class,objProjeto.getId());
			objProjeto.getFornecedor();
			objProjeto.getItensProjeto();
			objProjeto.getAgendaProjeto();
			objProjeto.getAgendas().size();
			
			
			this.projeto.setAvaliacao(objProjeto.getAvaliacao());
			this.projeto.setId(objProjeto.getId());
			this.projeto.setCodigoProjeto(objProjeto.getCodigoProjeto());
			try {
				if (objProjeto.getAgendaProjeto() != null) this.projeto.setAgendaProjeto(objProjeto.getAgendaProjeto());
				else this.projeto.setAgendaProjeto(null);
			} catch (Exception e) {
				this.projeto.setAgendaProjeto(null);
			}
			try {
				if (objProjeto.getAgendas().size() > 0) this.projeto.setAgendas(objProjeto.getAgendas());
				
			} catch (Exception e) {
				
			}
			try {
				if (objProjeto.getFornecedor() != null) this.projeto.setFornecedor(objProjeto.getFornecedor());
				else this.projeto.setFornecedor(null);
			} catch (Exception e) {
				this.projeto.setFornecedor(null);
			}
			this.projeto.setDescricao(objProjeto.getDescricao());
			try {
				if (objProjeto.getItensProjeto() != null) this.projeto.setItensProjeto(objProjeto.getItensProjeto());
				else this.projeto.setItensProjeto(new ArrayList());
			} catch (Exception e) {
				this.projeto.setItensProjeto(new ArrayList());
			}
			
			this.projeto.setRetirado(objProjeto.getRetirado());
			this.projeto.setStatus(objProjeto.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s!=null) s.close();
		}
		
		getProdutosList().setElements(projeto.getItensProjeto());
		getProdutosList().updateTable();
		gerarDescButton.setEnabled(true);
		anexarButton.setEnabled(true);
		addProdutosButton.setEnabled(true);
		calcularPelosItensButton.setEnabled(true);
			
		atualizarProjetoButton.setEnabled(true);
		addProdutosButton.setEnabled(true);
		avaliacaoTextField.setEnabled(true);
		avaliacaoTextField.setText(""+projeto.getAvaliacao());
		descricaoTextArea.setEnabled(true);
		descricaoTextArea.setText(projeto.getDescricao());

		this.setErrorIcon(false);
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(16, 423, 629, 53));
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {
		this.codProjetoComboBox.firePropertyChange("warnBorder", !bool, bool);
		this.avaliacaoTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getCodProjetoTextFieldLabel() {

		if (codProjetoTextFieldLabel == null) {
			codProjetoTextFieldLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.CódigodoProjeto"));
			codProjetoTextFieldLabel.setBounds(new Rectangle(15, 7, 106, 22));
			codProjetoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_put.png")));
			codProjetoTextFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return codProjetoTextFieldLabel;
	}

	
	protected JPanel getAvaliacaoCodProjetoPanel() {

		if (avaliacaoCodProjetoPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(3);
			gridLayout.setColumns(1);
			avaliacaoCodProjetoPanel = new JPanel();
			avaliacaoCodProjetoPanel.setLayout(gridLayout);
			avaliacaoCodProjetoPanel.setBounds(new Rectangle(523, 112, 90, 76));
			avaliacaoCodProjetoPanel.add(getAvaliacaoTextFieldLabel(), null);
			avaliacaoCodProjetoPanel.add(getAvaliacaoTextField(), null);
			avaliacaoCodProjetoPanel.add(getCalcularPelosItensButton(), null);
		}
		return avaliacaoCodProjetoPanel;
	}

	protected JComponent getAvaliacaoTextField() {

		if (avaliacaoTextField == null) {
			avaliacaoTextField = new JTextField();
			avaliacaoTextField.setText("");
			avaliacaoTextField.setHorizontalAlignment(SwingConstants.RIGHT);
			this.binder.addBindProperty(this.projeto, this.avaliacaoTextField,
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
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Valor(R$)"));
			avaliacaoTextFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
			avaliacaoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
		}
		return avaliacaoTextFieldLabel;
	}

	protected JButton getCalcularPelosItensButton() {

		if (calcularPelosItensButton == null) {
			calcularPelosItensButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.CalcularPelosItensdeProduto"));
			calcularPelosItensButton.setText("Calcular");
			calcularPelosItensButton.setIcon(new ImageIcon(getClass().getResource("/imgs/calculator.png")));
			calcularPelosItensButton.setEnabled(false);
			calcularPelosItensButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if (projeto.getItensProjeto() != null && projeto.getItensProjeto().size() > 0){
						float soma = 0;
						Iterator<ItemProjeto> it = projeto.getItensProjeto().iterator();
						while (it.hasNext()){
							ItemProjeto ip = it.next();
							soma +=ip.getValorTotal();
						}
						avaliacaoTextField.setText(""+soma);
					}
				}
			});
		}
		return calcularPelosItensButton;
	}

	protected JScrollPane descScrollPane;

	private JButton novoLeilaoButton = null;

	private JButton editarLeilaoButton = null;

	private JButton agendarLeilaoButton = null;

	private JButton gerarDescButton = null;

	private JButton editarProjetoButton = null;

	private JButton anexarButton = null;

	private JButton refreshLeiloesButton = null;

	protected JScrollPane getDescScrollPane() {

		if (descScrollPane == null) {
			descScrollPane = new JScrollPane();
			descScrollPane.setBounds(new Rectangle(15, 210, 501, 73));
			/*descScrollPane.add(*/getDescricaoTextArea()/*)*/;
			descScrollPane.setViewportView(getDescricaoTextArea());
		}
		return descScrollPane;
	}

	protected JComponent getDescricaoTextArea() {

		if (descricaoTextArea == null) {
			descricaoTextArea = new JTextArea();
			descricaoTextArea.setSize(new Dimension(334, 50));
			descricaoTextArea.setLocation(new Point(90, 71));
			this.binder.addBindProperty(this.projeto, this.descricaoTextArea,
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
			descricaoTextAreaLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Descrição"));
			descricaoTextAreaLabel.setHorizontalAlignment(JLabel.LEFT);
			descricaoTextAreaLabel.setBounds(new Rectangle(16, 190, 70, 20));
		}
		return descricaoTextAreaLabel;
	}

	protected JPanel getProdutosPanel() {

		if (produtosPanel == null) {
			
			TitledBorder titledBorder = BorderFactory.createTitledBorder("Produtos do projeto");
			
			produtosPanel = new JPanel();
			produtosPanel.setSize(new Dimension(631, 301));
			produtosPanel.setLocation(new Point(15, 85));
			produtosPanel.setLayout(null);
			produtosPanel.setBorder(titledBorder);
			produtosPanel.add(getProdutosListScrollPane());
			produtosPanel.add(getProdutosButtonsListPanel());
			produtosPanel.add(getAvaliacaoCodProjetoPanel(), null);
			produtosPanel.add(getDescricaoTextAreaLabel(), null);
			produtosPanel.add(getDescScrollPane(), null);
			produtosPanel.add(getGerarDescButton(), null);
			produtosPanel.add(getAnexarButton(), null);
		}
		return produtosPanel;
	}

	protected JScrollPane getProdutosListScrollPane() {

		if (produtosListScrollPane == null) {
			produtosListScrollPane = new JScrollPane();
			produtosListScrollPane.setSize(new Dimension(503, 168));
			produtosListScrollPane.setLocation(new java.awt.Point(15, 20));

			produtosListScrollPane.add(getProdutosList());
			produtosListScrollPane.setViewportView(getProdutosList());
		}
		return produtosListScrollPane;
	}

	protected ProdutosList getProdutosList() {

		if (produtosList == null) {
			produtosList = new ProdutosList();
			produtosList.addFocusListener(new FocusAdapter(){
				@Override
				public void focusGained(FocusEvent arg0) {
					editarProdutoButton.setEnabled(true);
					removeProdutoButton.setEnabled(true);
					
				}				
			});
			return produtosList;
		}
		return produtosList;
	}

	protected JPanel getProdutosButtonsListPanel() {

		if (produtosButtonsListPanel == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(3);
			gridLayout1.setColumns(1);
			produtosButtonsListPanel = new JPanel();
			produtosButtonsListPanel.setBounds(new Rectangle(523, 33, 100, 76));
			produtosButtonsListPanel.setLayout(gridLayout1);
			produtosButtonsListPanel.add(getAddprodutosButton(), null);
			produtosButtonsListPanel.add(getEditarProdutoButton(), null);
			produtosButtonsListPanel.add(getRemoveprodutosButton(), null);
		}
		return produtosButtonsListPanel;
	}

	protected JButton getAddprodutosButton() {
		if (addProdutosButton == null) {
			addProdutosButton = new JButton("Novo");
			addProdutosButton.setSize(new java.awt.Dimension(112, 20));
			addProdutosButton.setLocation(new java.awt.Point(0, 0));
			addProdutosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_add.png")));
			addProdutosButton.setEnabled(false);
			addProdutosButton.addActionListener(new AdicionarProdutoProjeto());
		}
		return addProdutosButton;
	}

	private class AdicionarProdutoProjeto extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			CadastrarComercialSolutionDialog c = new CadastrarComercialSolutionDialog();
			c.setModal(true);
			String code = projeto.getCodigoProjeto();
			//c.getProdutoCadastreForm().getLoteNumComboBox().setSelectedItem(code);
			c.setLocation(UIUtil.getScreenCenter(c));
			c.setVisible(true);
			initializeProjetos();
			//projeto = projetos.get(code);
			codProjetoComboBox.setSelectedItem(code);
			editarProjetoSelecionado();
			getErrorPanel().removeAllElements();
			logErrorPanel.addAlert("Não esqueça de atualizar o valor e a descrição do projeto! salve as alterações clicando no botão 'Atualizar Projeto'");
			logErrorPanel.updateErrorList();
			logErrorPanel.setVisible(true);
			/*Projeto l = projetos.get(code);
			editRegister(l);*/
		}
	}
	protected JButton getEditarProdutoButton() {

		if (editarProdutoButton == null) {
			editarProdutoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Editar"));
			editarProdutoButton.setSize(new java.awt.Dimension(112, 20));
			editarProdutoButton.setEnabled(false);
			editarProdutoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_edit.png")));
			editarProdutoButton.setLocation(new java.awt.Point(0, 20));
			editarProdutoButton.addActionListener(new EditarProdutoProjeto());
		}
		return editarProdutoButton;
	}
	
	private class EditarProdutoProjeto extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			int row = getProdutosList().getSelectedRow();
			if (row >= 0)try{
				ItemProjeto ip = projeto.getItensProjeto().get(row);
				ComercialSolution p = LocalServicesUtility.getInstance().getComercialSolutionByIdItem(ip.getId());
				if (p != null){
					CadastrarComercialSolutionDialog c = new CadastrarComercialSolutionDialog();
					c.setModal(true);
					String code = projeto.getCodigoProjeto();
					//c.getProdutoCadastreForm().getLoteNumComboBox().setSelectedItem(code);
					c.getComercialSolutionCadastreForm().editRegister(p);
					c.setLocation(UIUtil.getScreenCenter(c));
					c.setVisible(true);
					/*initializeProjetos();
					Projeto l = projetos.get(code);
					editRegister(l);*/
					initializeProjetos();
					codProjetoComboBox.setSelectedItem(code);
					editarProjetoSelecionado();
					getErrorPanel().removeAllElements();
					logErrorPanel.addAlert("Não esqueça de atualizar o valor e a descrição do projeto! salve as alterações clicando no botão 'Atualizar Projeto'");
					logErrorPanel.updateErrorList();
					logErrorPanel.setVisible(true);
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	protected JButton getRemoveprodutosButton() {

		if (removeProdutoButton == null) {
			removeProdutoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Remover"));
			removeProdutoButton.setEnabled(false);
			removeProdutoButton.setSize(new java.awt.Dimension(112, 20));
			removeProdutoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_delete.png")));
			removeProdutoButton.setLocation(new java.awt.Point(0, 40));
			removeProdutoButton.addActionListener(new RemoverProdutoProjeto());
		
		}
		return removeProdutoButton;
	}
	
	private class RemoverProdutoProjeto extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			int row = getProdutosList().getSelectedRow();
			if (row > 0)try{
				String code = projeto.getCodigoProjeto();
				ItemProjeto ip = projeto.getItensProjeto().get(row);
				String desc = LocalServicesUtility.getInstance().getDescricaoComercialSolutionByIdItem(ip.getId());
				if (desc != null){
					int resp = JOptionPane.showConfirmDialog(ProjetoCadastreForm.this, "Você quer retirar o produto" + '\n'+desc + '\n'+" do projeto " + projeto.getCodigoProjeto() + "?","Remover Produto do Projeto",JOptionPane.YES_NO_OPTION);
					if (resp == JOptionPane.YES_OPTION){
						
						Session s = null;
						try {
							s = LocalServicesUtility.getInstance().openSession();
							
							ip = (ItemProjeto) s.load(ItemProjeto.class,ip.getId());
							
							 
							s.beginTransaction();							
							ip.setProjeto(null);							
							s.save(ip);
							
							resp = JOptionPane.showConfirmDialog(ProjetoCadastreForm.this, "Apagar em definitivo o produto " + desc + "?","Remover Produto",JOptionPane.YES_NO_OPTION);
							if (resp == JOptionPane.YES_OPTION){
								//LocalProdutoService.getInstance().deleteByIdItemProduto(ip.getId());
								Iterator<Imagem> it =ip.getProduto().getImagens().iterator();
								ComercialSolution prod =ip.getProduto(); 
								while(it.hasNext()){
									Imagem im = it.next();
									
									//prod.getImagens().remove(im);
									s.delete(im);			
								}
								//prod.setItemProduto(null);
								ip.setProduto(null);
								s.delete(prod);
								s.delete(ip);
							}
							s.getTransaction().commit();
							
							initializeProjetos();
							codProjetoComboBox.setSelectedItem(code);
							editarProjetoSelecionado();
							getErrorPanel().removeAllElements();
							logErrorPanel.addAlert("Não esqueça de atualizar o valor e a descrição do projeto! salve as alterações clicando no botão 'Atualizar Projeto'");
							logErrorPanel.updateErrorList();
							logErrorPanel.setVisible(true);
							JOptionPane.showMessageDialog(ProjetoCadastreForm.this, "Operação realizada com sucesso","Remover Produto",JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							s.getTransaction().rollback();
							JOptionPane.showMessageDialog(ProjetoCadastreForm.this, "O produto não foi removido!","Problema ao remover produto",JOptionPane.ERROR_MESSAGE);
						}finally{
							s.close();
						}
					}
					
				}
				/*if (p != null){
					CadastrarProdutoDialog c = new CadastrarProdutoDialog();
					c.setModal(true);
					String code = projeto.getCodProjeto();
					c.getProdutoCadastreForm().getProjetoNumComboBox().setSelectedItem(code);
					c.getProdutoCadastreForm().editRegister(p);
					c.setLocation(UIUtil.getScreenCenter(c));
					c.setVisible(true);
					initializeProjetos();
					Projeto l = projetos.get(code);
					editRegister(l);
				}*/
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	protected JPanel getButtonsPanel() {

		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(628, 35));
			buttonsPanel.setLocation(new Point(16, 387));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getAtualizarProjetoButton());
			buttonsPanel.add(getNovoProjetoButton());
			buttonsPanel.add(getRemoverProjetoButton());
		}
		return buttonsPanel;
	}

	protected JButton getAtualizarProjetoButton() {

		if (atualizarProjetoButton == null) {
			atualizarProjetoButton = new JButton("Atualizar Projeto"/*
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Cadastrar")*/);
			atualizarProjetoButton.setSize(new java.awt.Dimension(80, 22));
			atualizarProjetoButton.setEnabled(false);
			atualizarProjetoButton.setIcon(getIcon("/imgs/basket_save.png"));
			atualizarProjetoButton.setLocation(new java.awt.Point(0, 0));
			atualizarProjetoButton.addActionListener(new AbstractAction(){
				public void doAction(ActionEvent evt){
					try {
						cadastreProjeto();						
						/*String code = projeto.getCodProjeto();
						initializeProjetos();
						projeto = projetos.get(code);
						editRegister(projeto);*/
						getErrorPanel().removeAllElements();
						getErrorPanel().setVisible(false);
						AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de projetos", "Operação de cadastro realizada com sucesso!");
					} catch (Exception e) {
						AdapitVirtualFrame.getInstance().showErrorDialog("Formulário de cadastro de projeto",messages.getMessage(e.getMessage()));
					}
				}
			});
		}
		return atualizarProjetoButton;
	}

	protected JButton getNovoProjetoButton() {

		if (novoProjetoButton == null) {
			novoProjetoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Novo") + " Projeto");
			novoProjetoButton.setSize(new java.awt.Dimension(80, 22));
			novoProjetoButton.setIcon(getIcon("/imgs/basket_add.png"));
			novoProjetoButton.setLocation(new java.awt.Point(0, 22));
			novoProjetoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					novoProjeto();
				}
			});
			
		}
		return novoProjetoButton;
	}
	
	public void novoProjeto(){
		String last ="";
		if (codProjetoComboBox.getItemCount() > 0){
			last = (String) codProjetoComboBox.getItemAt(codProjetoComboBox.getItemCount()-1);
			last = last.substring(0, last.length()-1);
		}else last="001";
		codProjetoComboBox.setSelectedItem(last);
		codProjetoComboBox.requestFocus();
		atualizarProjetoButton.setEnabled(false);
	}

	protected JButton getRemoverProjetoButton() {

		if (removerProjetoButton == null) {
			removerProjetoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Remover") + " Projeto");
			removerProjetoButton.setSize(new java.awt.Dimension(80, 22));
			removerProjetoButton.setIcon(getIcon("/imgs/basket_delete.png"));
			removerProjetoButton.setLocation(new java.awt.Point(0, 44));
			removerProjetoButton.addActionListener(getRemoverAction());
		}
		return removerProjetoButton;
	}
	
	private RemoverProjetoAction removerAction;
	public RemoverProjetoAction getRemoverAction(){
		if (removerAction == null){
			removerAction = new RemoverProjetoAction();
		}
		return removerAction;
	}
	
	public class RemoverProjetoAction extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			Projeto l = projetos.get(codProjetoComboBox.getSelectedItem());
			if (l != null){
				int resp = JOptionPane.showConfirmDialog(ProjetoCadastreForm.this, "Apagar o projeto " + l.getCodigoProjeto(),"Apagar Projeto",JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION){
					try {
						Session s = LocalServicesUtility.getInstance().openSession();
						try {
							s.beginTransaction();
							
							l = (Projeto) s.load(Projeto.class,l.getId());
							
							//LocalServicesUtility.getInstance().saveOrUpdate(leilao);
							//LocalProjetoService.getInstance().deleteById(l.getId());
							Iterator<ItemProjeto> it = l.getItensProjeto().iterator();
							while(it.hasNext()){
								ItemProjeto ip = it.next();
								ip.setProjeto(null);
								s.save(ip);
							}
					
							s.delete(l);
							s.getTransaction().commit();
							
							initializeProjetos();
							//editRegister(l);
							atualizarProjetoButton.setEnabled(false);
							JOptionPane.showMessageDialog(ProjetoCadastreForm.this, "O projeto foi removido com sucesso!","Apagar Projeto",JOptionPane.INFORMATION_MESSAGE);
							
						} catch (Exception e1) {
							e1.printStackTrace();
							s.getTransaction().rollback();
							JOptionPane.showMessageDialog(ProjetoCadastreForm.this, "O projeto não foi removido!","Apagar Projeto",JOptionPane.ERROR_MESSAGE);
						}finally{
							s.close();
						}
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * This method initializes novoLeilaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoLeilaoButton() {
		if (novoLeilaoButton == null) {
			novoLeilaoButton = new JButton();
			novoLeilaoButton.setBounds(new Rectangle(558, 53, 87, 24));
			novoLeilaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_add.png")));
			novoLeilaoButton.setText("Novo");
			novoLeilaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//LeilaoVirtualFrame.getInstance().cadastrarLeilao();
				}
			});
		}
		return novoLeilaoButton;
	}

	/**
	 * This method initializes editarLeilaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarLeilaoButton() {
		if (editarLeilaoButton == null) {
			editarLeilaoButton = new JButton();
			editarLeilaoButton.setBounds(new Rectangle(467, 53, 90, 24));
			editarLeilaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/database_edit.png")));
			editarLeilaoButton.setText("Editar");
			editarLeilaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//LeilaoVirtualFrame.getInstance().editarLeilao(leiloes.get(getDataLeilaoComboBox().getSelectedIndex()));					
				}
			});
		}
		return editarLeilaoButton;
	}

	/**
	 * This method initializes agendarLeilaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAgendarLeilaoButton() {
		if (agendarLeilaoButton == null) {
			agendarLeilaoButton = new JButton();
			agendarLeilaoButton.setBounds(new Rectangle(250, 53, 98, 24));
			agendarLeilaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/calendar_edit.png")));
			agendarLeilaoButton.setText("Agenda");
			agendarLeilaoButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					AgendaProjetoDialog  agenda = new AgendaProjetoDialog();
					Session s = null;
					//System.out.println("tamanho do projetos "+projetos.size());
					projeto = projetos.get((String)codProjetoComboBox.getSelectedItem());
					try{
						s = LocalServicesUtility.getInstance().openSession();
						Projeto projeto2 = (Projeto) s.load(Projeto.class,projeto.getId());
						projeto = projeto2;
						projetos.remove((String)codProjetoComboBox.getSelectedItem());
						projetos.put((String)codProjetoComboBox.getSelectedItem(),projeto2);
						if (projeto2 == null) return;
						if (projeto2.getAgendaProjeto() != null){
							agenda.editRegister(projeto2.getAgendaProjeto(), projeto2);
						}else{
							agenda.newRegister(projeto2);
						}
						
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
					}
					agenda.setVisible(true);
					
					
				}
			});
		}
		return agendarLeilaoButton;
	}

	/**
	 * This method initializes gerarDescButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGerarDescButton() {
		if (gerarDescButton == null) {
			gerarDescButton = new JButton();
			gerarDescButton.setBounds(new Rectangle(523, 210, 90, 24));
			gerarDescButton.setEnabled(false);
			gerarDescButton.setIcon(new ImageIcon(getClass().getResource("/imgs/wand.png")));
			gerarDescButton.setText("Gerar");
			gerarDescButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					int ne = getProdutosList().getRowCount();
					if (ne > 0){
						
						String str="";
						for (int i=0; i < ne; i++){
							int qtd = ((Integer)getProdutosList().getValueAt(i,3)).intValue();
							str+=qtd +" itens do tipo "+ (String)getProdutosList().getValueAt(i,1) + " ["+((String)getProdutosList().getValueAt(i,0)).replaceAll("\n",",") + "]"+'\n';
						}
						descricaoTextArea.setText(str);
					}
				}
			});
		}
		return gerarDescButton;
	}

	/**
	 * This method initializes editarProjetoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarProjetoButton() {
		if (editarProjetoButton == null) {
			editarProjetoButton = new JButton();
			editarProjetoButton.setText("Editar Projeto");
			editarProjetoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarProjetoButton.setBounds(new Rectangle(15, 53, 106, 24));
			editarProjetoButton.addActionListener(new AbstractAction(){
				@Override
				public void doAction(ActionEvent evt) {
					editarProjetoSelecionado();
				}				
			});
		}
		return editarProjetoButton;
	}
	
	private void editarProjetoSelecionado(){
		atualizarProjetoButton.setEnabled(true);
		addProdutosButton.setEnabled(true);
		avaliacaoTextField.setEnabled(true);
		descricaoTextArea.setEnabled(true);
		
		if (codProjetoComboBox.getSelectedItem() != null){
			if (projeto != null && projeto.getCodigoProjeto() != null
				&&((JComboBox)getCodProjetoComboBox()).getSelectedItem() != null
				&& projeto.getCodigoProjeto().equals(((JComboBox)getCodProjetoComboBox()).getSelectedItem())){
				
			}else{
				projeto = projetos.get(codProjetoComboBox.getSelectedItem());				
			}
			if (projeto != null) editRegister(projeto);
		}
	}

	/**
	 * This method initializes anexarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAnexarButton() {
		if (anexarButton == null) {
			anexarButton = new JButton();
			anexarButton.setBounds(new Rectangle(523, 236, 90, 24));
			anexarButton.setEnabled(false);
			anexarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/attach.png")));
			anexarButton.setText("Anexar");
			anexarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					int ne = getProdutosList().getRowCount();
					if (ne > 0){
						int row = getProdutosList().getSelectedRow();
						if (row >= 0){
							String str="";
						
							int qtd = ((Integer)getProdutosList().getValueAt(row,3)).intValue();
							
							str+=qtd +" itens do tipo " + (String)getProdutosList().getValueAt(row,1) + " ["+((String)getProdutosList().getValueAt(row,0)).replaceAll("\n",",")+"]"+'\n';
							/*str = str.substring(1,str.length()-1);
							str+="]"+'\n';*/
							descricaoTextArea.setText(descricaoTextArea.getText()+'\n'+str);
						}
					}
				}
			});
		}
		return anexarButton;
	}

	/**
	 * This method initializes refreshLeiloesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshLeiloesButton() {
		if (refreshLeiloesButton == null) {
			refreshLeiloesButton = new JButton();
			refreshLeiloesButton.setBounds(new Rectangle(605, 0, 22, 22));
			refreshLeiloesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshLeiloesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//updateLeilaoList();
				}
			});
		}
		return refreshLeiloesButton;
	}
	
	public String getFormatedDate(Date d){
		//Calendar c = Calendar.getInstance();
		//c.setTime(d);
		return AdapitVirtualFrame.getInstance().format(d);
		/*if (d == null) return "";
		String str= d.getDate()+"/"+d.getMonth()+"/"+(d.getYear()+1900);
		try {
			str+= " " + d.getHours() + ":" + d.getMinutes() ;
		} catch (Exception e) {
			
		}
		return str;*/
	}
	
	
	
	/*public void updateProjetosList(){	
		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Iterator it = s.createQuery("select leilao.id, leilao.dataPresencialPrimeira, leilao.dataPresencialSegunda from Leilao leilao order by leilao.dataPresencialPrimeira ASC").list().iterator();
			{
				int i=0;
				while(it.hasNext()){
					if (i >9) break;
					Object obj[] = (Object[])it.next();
					
					Leilao l = new Leilao();
					l.setId(((Integer)obj[0]).intValue());
					l.setDataPresencialPrimeira((Date)obj[1]);
					l.setDataPresencialSegunda((Date)obj[2]);					
					leiloes.add(l);
					getDataLeilaoComboBox().addItem(getFormatedDate(l.getDataPresencialPrimeira())+" .. " +getFormatedDate(l.getDataPresencialSegunda()));
					i++;
				}
			}
			//s.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		if (leiloes == null || leiloes.size() == 0) getDataLeilaoComboBox().setEnabled(false);
		else getDataLeilaoComboBox().setEnabled(true);
					
	}*/
	
	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(449, 389));
				gui.add(new ProjetoCadastreForm(), java.awt.BorderLayout.CENTER);
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
			// TODO
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			// TODO
			e.printStackTrace();
		}// end of catch block
		return null;
	}

	private class ProdutosList extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public ProdutosList() {

			super();
			this.setModel(new ProdutosListModel(null));
			getColumnModel().getColumn(0).setPreferredWidth(200);
			this.addPropertyChangeListener(new ProdutosListPropertyChangeListener());
			updateTable();
		}

		public ProdutosList(List elements) {

			super();
			this.elements = elements;
			this.setModel(new ProdutosListModel(null));
			this.addPropertyChangeListener(new ProdutosListPropertyChangeListener());
			getColumnModel().getColumn(0).setPreferredWidth(200);
			updateTable();
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
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.ItemProjeto) {
						com.adapit.portal.entidades.ItemProjeto itemProduto = (com.adapit.portal.entidades.ItemProjeto) obj;
						try {
							values[i][0] =	LocalServicesUtility.getInstance().getDescricaoComercialSolutionByIdItem(itemProduto.getId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							values[i][1] =	LocalServicesUtility.getInstance().getCategoriaComercialSolutionByIdItem(itemProduto.getId());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Session s = LocalServicesUtility.getInstance().openSession();
						ItemProjeto ip = (ItemProjeto) s.load(ItemProjeto.class,itemProduto.getId());
						
						values[i][2] = ip.getProduto().getAvaliacao();//itemProduto.getValorTotal()/itemProduto.getQtd();
						s.close();
						
						values[i][3] = itemProduto.getQtd();
						values[i][4] = itemProduto.getValorTotal();
						i++;
					}
				}// End of while Loop
				setModel(new ProdutosListModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				updateUI();
			} else {
				setModel(new ProdutosListModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				updateUI();
			}
		}

		private class ProdutosListModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class,String.class,Number.class,
					Integer.class, Number.class };

			boolean canEdit[] = new boolean[] {false, false, false, false, false };

			public ProdutosListModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Descrição"),
										"Categoria","Preço Unitário",
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Quantidade"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.ProjetoCadastreForm.Valor")+" do Item" });
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

				ProjetoCadastreForm.ProdutosList jt = (ProjetoCadastreForm.ProdutosList) e
						.getSource();
				//int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.ItemProjeto) {
							//com.adapit.portal.entidades.ItemProjeto itemProduto = (com.adapit.portal.entidades.ItemProjeto) obj;
							// if (col ==1)
							// itemProduto.getNome(((java.lang.String)jt.getValueAt(row,
							// col)));
							// if (col ==2)
							// itemProduto.setQtd((java.lang.Integer.parseInt((java.lang.String)jt.getValueAt(row,
							// col)));
							// if (col ==3)
							// itemProduto.setValorTotal(((java.lang.String)jt.getValueAt(row,
							// col)));
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

	}
	
	/*private String formatDataLeilao(Leilao l){
		return getFormatedDate(l.getDataPresencialPrimeira())+" | " +getFormatedDate(l.getDataOnlinePrimeira());
	}
	
	public void setSelectedLeilao(Leilao leilao) {
		updateLeilaoList();
		getDataLeilaoComboBox().setSelectedItem(formatDataLeilao(leilao));
	}*/

	public void setSelectedProjeto(Projeto l) {
		editRegister(l);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"