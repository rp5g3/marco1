package com.adapit.portal.ui.forms.solution.itemsolution;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.Moeda;

@SuppressWarnings("serial")
public class ComercialSolutionItemCadastreForm extends JPanel {

	private JPanel itemEditPanel;

	private JTextField clienteTextField;

	private SwingBinder binder = new SwingBinder();

	private ComercialSolutionItem item = new ComercialSolutionItem();

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();

	private JLabel clienteLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel avaliacaoPanel;

	private JTextField valorTreinamentoTextField;

	private JLabel valorTreinamentoLabel;

	private JButton calcularByQtdButton;

	private JPanel valorQtdPanel;

	private JTextField valorItemTextField;

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel valorAcertadoLabel;

	private JSpinner descontoSpinner;

	private JLabel descontoSpinnerLabel;

	private JPanel itemsButtonsPanel;

	private JButton confirmarButton;
	

	public ComercialSolutionItemCadastreForm() {

		initialize();
	}

	public ComercialSolutionItemCadastreForm(Treinamento scheduledTraining) {
		this.treinamento = scheduledTraining;
		initialize();
	}

	private void initialize() {

		setSize(new Dimension(396, 253));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getItemEditPanel());
		add(getItemsButtonsPanel());
		// Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		// End

	}

	protected JPanel getItemEditPanel() {
		if (itemEditPanel == null) {
			itemEditPanel = new JPanel();
			itemEditPanel.setSize(new Dimension(418, 162));
			itemEditPanel.setLocation(new Point(0, 1));
			itemEditPanel.setLayout(null);
			itemEditPanel.add(getClienteTextField());
			itemEditPanel.add(getClienteLabel());
			itemEditPanel.add(getAvaliacaoPanel());
			itemEditPanel.add(getValorQtdPanel());
		}
		return itemEditPanel;
	}

/*	protected JScrollPane produtoScrollPane;

	protected JScrollPane getProdutoScrollPane() {
		if (produtoScrollPane == null) {
			produtoScrollPane = new JScrollPane();
			produtoScrollPane.setSize(new Dimension(380, 93));
			produtoScrollPane.setLocation(new Point(10, 20));
			produtoScrollPane.add(getClienteTextField());
			produtoScrollPane.setViewportView(getClienteTextField());
		}
		return produtoScrollPane;
	}*/

	protected JTextField getClienteTextField() {
		if (clienteTextField == null) {
			clienteTextField = new JTextField();
			clienteTextField.setSize(new java.awt.Dimension(266, 22));
			clienteTextField.setLocation(new java.awt.Point(115, 15));
			clienteTextField.setEditable(false);
			return clienteTextField;
		}
		return clienteTextField;
	}

	protected JLabel getClienteLabel() {

		if (clienteLabel == null) {
			clienteLabel = new JLabel("Cliente:");
			clienteLabel.setSize(new Dimension(54, 20));
			clienteLabel.setLocation(new Point(10, 0));
			clienteLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return clienteLabel;
	}

	protected JPanel getAvaliacaoPanel() {

		if (avaliacaoPanel == null) {
			avaliacaoPanel = new JPanel();
			avaliacaoPanel.setSize(new Dimension(403, 24));
			avaliacaoPanel.setLocation(new Point(10, 115));
			avaliacaoPanel.setLayout(null);
			avaliacaoPanel.add(getValorTreinamentoTextField());
			avaliacaoPanel.add(getValorTreinamentoLabel());
			avaliacaoPanel.add(getCalcularByQtdButton());
		}
		return avaliacaoPanel;
	}

	protected JTextField getValorTreinamentoTextField() {
		if (valorTreinamentoTextField == null) {
			valorTreinamentoTextField = new JTextField();
			valorTreinamentoTextField.setText("");
			valorTreinamentoTextField.setHorizontalAlignment(JTextField.RIGHT);
			valorTreinamentoTextField.setSize(new java.awt.Dimension(100, 20));
			valorTreinamentoTextField.setLocation(new java.awt.Point(105, 2));
			valorTreinamentoTextField.setEditable(false);
			return valorTreinamentoTextField;
		}
		return valorTreinamentoTextField;
	}

	protected JLabel getValorTreinamentoLabel() {
		if (valorTreinamentoLabel == null) {
			valorTreinamentoLabel = new JLabel("Valor do Treianmento:");
			valorTreinamentoLabel.setSize(new java.awt.Dimension(100,20));
			valorTreinamentoLabel.setLocation(new java.awt.Point(0, 2));
			valorTreinamentoLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return valorTreinamentoLabel;
	}

	protected JButton getCalcularByQtdButton() {

		if (calcularByQtdButton == null) {
			calcularByQtdButton = new JButton("Calcula pelo desconto");
			calcularByQtdButton.setSize(new Dimension(172, 24));
			calcularByQtdButton.setIcon(new ImageIcon(getClass().getResource("/imgs/calculator.png")));
			calcularByQtdButton.setLocation(new java.awt.Point(210, 0));
			calcularByQtdButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					calcularDesconto();
				}				
			});
		}
		return calcularByQtdButton;
	}
	
	private void calcularDesconto(){
		Integer in = (Integer) ((JSpinner)getDescontoSpinner()).getValue();
		if (treinamento.getTreinamento() != null && in != null){
			float desconto = (in.intValue() * treinamento.getTreinamento().getAvaliacao())/100;
			float valor = treinamento.getTreinamento().getAvaliacao() - desconto;
			valorItemTextField.setText(Moeda.getValorReal((valor)));
		}
	}

	protected JPanel getValorQtdPanel() {

		if (valorQtdPanel == null) {
			valorQtdPanel = new JPanel();
			valorQtdPanel.setSize(new Dimension(385, 20));
			valorQtdPanel.setLocation(new Point(10, 140));
			valorQtdPanel.setLayout(null);
			valorQtdPanel.add(getValorItemTextField());
			valorQtdPanel.add(getValorAcertadoLabel());
			valorQtdPanel.add(getDescontoSpinner());
			valorQtdPanel.add(getDescontoSpinnerLabel());
		}
		return valorQtdPanel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getValorItemTextField() {

		if (valorItemTextField == null) {
			valorItemTextField = new JTextField();
			valorItemTextField.setText("");
			valorItemTextField.setHorizontalAlignment(JTextField.RIGHT);
			valorItemTextField.setSize(new java.awt.Dimension(100, 20));
			valorItemTextField.setLocation(new java.awt.Point(105, 0));
			this.binder.addBindProperty(this.item,
					this.valorItemTextField, "valorAcertado");

			this.hashComps.put("valorAcertado", this.valorItemTextField);
			JWarningComponent warn = new JWarningComponent(
					this.valorItemTextField);
			warn.setBounds(105, 0, 100, 20);
			return warn;
		}
		return valorItemTextField;
	}

	public ComercialSolutionItem validateItemProdutoForm() throws Exception {
		setErrorIcon(false);
		/*try {
			binder.bind(item);
		} catch(java.lang.IllegalArgumentException ie){
			
		}catch (Exception e) {
			
		}*/
		Integer in = (Integer) descontoSpinner.getValue();
		item.setDesconto(in);
		item.setValorAcertado(Moeda.valorRealToFloat(valorItemTextField.getText()));
		item.setTrainingSolution(treinamento.getTreinamento());
		item.setTreinamento(treinamento);
		item.setInscrito(inscrito);
		// Manual Code
		if (!validateItemProdutoBean())
			throw new Exception("ErroItemProdutoDadosInvalidos");
		return item;
	}

	private Treinamento treinamento;
	
	@SuppressWarnings("unchecked")
	public ComercialSolutionItem cadastreItemProduto() throws Exception {
		try{			
			ComercialSolutionItem ip =  validateItemProdutoForm();	
			if (treinamento != null)
				return RemoteComercialSolutionService.getInstance()
				.saveOrUpdateComercialSolutionItem(ip, inscrito, treinamento);
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
/*	public ItemProduto cadastreItemProduto() throws Exception {
		Session s = LocalServicesUtility.getInstance().openSession();
		try{			
			s.beginTransaction();

			ItemProduto ip =  validateItemProdutoForm();			

			ip.setProduto(produto);
			
			ip.setLote(lote);			
			
			if (ip.getId() != 0 ){
				if (produto.getItensProduto() == null) produto.setItensProduto(new ArrayList());
				produto.getItensProduto().add(ip);
				if (lote.getItensProduto() == null) lote.setItensProduto(new ArrayList());
				lote.getItensProduto().add(ip);				
				s.update(ip);
			}else{				
				s.save(ip);							
			}
			
			s.getTransaction().commit();
			return ip;
			
		}catch(org.hibernate.exception.ConstraintViolationException e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}catch(Exception e){
			e.printStackTrace();
			s.getTransaction().rollback();
			throw e;
		}finally{
			if (s != null) s.close();
		}
	}*/
	

	
	

	@SuppressWarnings("unchecked")
	public boolean validateItemProdutoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.item, "comercialSolutionItem");
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
		item.setInscrito(inscrito);
		item.setTreinamento(treinamento);
		item.setId(0);
		item.setDesconto(1);
		item.setValorAcertado(0.0f);
		
		item.setQuitada(false);
		item.setConfirmada(false);
		item.setCondicaoPagto(null);

		binder.reverseBind(this.item);

		this.setErrorIcon(false);
		updateUI();
	}

	public void editRegister(ComercialSolutionItem objItemProduto) {
		// Nunca passar como argumento novos objetos!!!

		item.setId(objItemProduto.getId());
		item.setDesconto(objItemProduto.getDesconto());
		item.setValorAcertado(objItemProduto.getValorAcertado());
		item.setInscrito(inscrito);
		item.setTreinamento(treinamento);
		item.setQuitada(objItemProduto.isQuitada());
		item.setConfirmada(objItemProduto.isConfirmada());
		item.setCondicaoPagto(objItemProduto.getCondicaoPagto());
		
		this.valorTreinamentoTextField.setText(""+treinamento.getTreinamento().getAvaliacao());
		
		binder.reverseBind(this.item);
		this.setErrorIcon(false);
	}

	/*public void editRegister(Produto objProduto) {
		this.produto = objProduto;
		if (objProduto.getItensProduto() != null)try {
			Session s = null;
						
			try{	
				s = LocalServicesUtility.getInstance().openSession();
				List list = s.createQuery("select ip from ItemProduto ip where ip.produto.id=:id")
				.setParameter("id", objProduto.getId()).list();
				//this.itemProduto.setId(objProduto.getItensProduto().getId());
				//s.load(itemProduto,itemProduto.getId());
			}catch(Exception e){
				e.printStackTrace();
				//s.getTransaction().rollback();
			}finally{
				if (s != null && s.isOpen()) s.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		else {
			itemProduto.setId(0);
			itemProduto.setLote(null);
			itemProduto.setProduto(produto);
			itemProduto.setQtd(1);
			itemProduto.setValorTotal(produto.getAvaliacao());
			objProduto.setItensProduto(new ArrayList());
		}

		getProdutoTextArea().setText(objProduto.getDescricao());
		getValorIndividualTextField().setText(""+objProduto.getAvaliacao());
		
		binder.reverseBind(this.itemProduto);
		this.setErrorIcon(false);
	}*/

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(381, 50));
			logErrorPanel.setLocation(9, 202);
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {
		this.valorItemTextField.firePropertyChange("warnBorder", !bool, bool);
		this.descontoSpinner.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getValorAcertadoLabel() {
		if (valorAcertadoLabel == null) {
			valorAcertadoLabel = new JLabel("Valor Acertado(R$)");
			valorAcertadoLabel.setSize(new java.awt.Dimension(100, 20));
			valorAcertadoLabel.setLocation(new java.awt.Point(0, 0));
			valorAcertadoLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return valorAcertadoLabel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getDescontoSpinner() {
		if (descontoSpinner == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 99, 1);
			model.addChangeListener(new SpinnerListener());
		    descontoSpinner = new JSpinner(model);
			descontoSpinner.setSize(new java.awt.Dimension(70, 20));
			descontoSpinner.setLocation(new java.awt.Point(310, 0));

			this.binder.addBindProperty(this.item, this.descontoSpinner,	"desconto");

			this.hashComps.put("desconto", this.descontoSpinner);
			JWarningComponent warn = new JWarningComponent(this.descontoSpinner);
			warn.setBounds(310, 0, 70, 20);
			return warn;
		}
		return descontoSpinner;
	}
	
	public class SpinnerListener implements ChangeListener {
		public void stateChanged(ChangeEvent evt) {
			calcularDesconto();
		}
	}

	protected JLabel getDescontoSpinnerLabel() {
		if (descontoSpinnerLabel == null) {
			descontoSpinnerLabel = new JLabel("Desconto:");
			descontoSpinnerLabel.setSize(new java.awt.Dimension(90, 20));
			descontoSpinnerLabel.setLocation(new java.awt.Point(210, 0));
			descontoSpinnerLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return descontoSpinnerLabel;
	}

	protected JPanel getItemsButtonsPanel() {
		if (itemsButtonsPanel == null) {
			itemsButtonsPanel = new JPanel();
			itemsButtonsPanel.setSize(new Dimension(382, 36));
			itemsButtonsPanel.setLocation(new Point(9, 164));
			itemsButtonsPanel.setLayout(new java.awt.FlowLayout());
			itemsButtonsPanel.add(getConfirmarButton());
		}
		return itemsButtonsPanel;
	}

	public JButton getConfirmarButton() {
		if (confirmarButton == null) {
			confirmarButton = new JButton("Confirmar");
			confirmarButton.setSize(new java.awt.Dimension(100, 22));
			confirmarButton.setLocation(new java.awt.Point(0, 0));
		}
		return confirmarButton;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(404, 279));
				gui.add(new ComercialSolutionItemCadastreForm(),
						java.awt.BorderLayout.CENTER);
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
		}// end of catch block
		return null;
	}

	public Treinamento getTreinamento() {
		return treinamento;
	}

	public void setTreinamento(Treinamento t) {
		this.treinamento = t;
		item.setTreinamento(t);
		valorTreinamentoTextField.setText(Moeda.getValorReal(treinamento.getTreinamento().getAvaliacao()));
	}

	private Participante inscrito;
	
	public void setParticipante(Participante p) {
		inscrito = p;
		clienteTextField.setText(inscrito.getNome());		
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"