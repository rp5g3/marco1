package com.adapit.portal.ui.forms.treinamento.formacao;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;

import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.entidades.TrainingFormationItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class TrainingFormationItemCadastreForm extends JPanel {

	private JPanel itemEditPanel;

	private JTextArea produtoTextArea;

	private SwingBinder binder = new SwingBinder();

	private TrainingFormationItem itemProduto = new TrainingFormationItem();

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();

	private JLabel produtoTextAreaLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();



	private JPanel valorQtdPanel;



	private boolean processFocus = true;

	private JLabel valorItemTextFieldLabel;

	private JSpinner itemOrderSpinner;

	private JLabel qtdSpinnerLabel;

	private JPanel itemsButtonsPanel;

	private JButton confirmarButton;
	
	private TrainingSolution trainingSolution;

	public TrainingFormationItemCadastreForm() {

		initialize();
	}

	private void initialize() {
		setSize(new Dimension(396, 142));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getItemEditPanel());
		add(getItemsButtonsPanel());
		// Put such code into the end of initialize body
		newRegister();
		this.setErrorIcon(false);
		// End

	}

	protected JPanel getItemEditPanel() {
		if (itemEditPanel == null) {
			itemEditPanel = new JPanel();
			itemEditPanel.setSize(new Dimension(418, 97));
			itemEditPanel.setLocation(new Point(0, 1));
			itemEditPanel.setLayout(null);
			itemEditPanel.add(getProdutoScrollPane());
			itemEditPanel.add(getProdutoTextAreaLabel());
			itemEditPanel.add(getValorQtdPanel());
		}
		return itemEditPanel;
	}

	protected JScrollPane produtoScrollPane;

	protected JScrollPane getProdutoScrollPane() {
		if (produtoScrollPane == null) {
			produtoScrollPane = new JScrollPane();
			produtoScrollPane.setSize(new Dimension(380, 35));
			produtoScrollPane.setLocation(new Point(10, 20));
			produtoScrollPane.add(getProdutoTextArea());
			produtoScrollPane.setViewportView(getProdutoTextArea());
		}
		return produtoScrollPane;
	}

	protected JTextArea getProdutoTextArea() {
		if (produtoTextArea == null) {
			produtoTextArea = new JTextArea();
			produtoTextArea.setSize(new java.awt.Dimension(266, 66));
			produtoTextArea.setLocation(new java.awt.Point(115, 15));
			produtoTextArea.setEditable(false);
			return produtoTextArea;
		}
		return produtoTextArea;
	}

	protected JLabel getProdutoTextAreaLabel() {

		if (produtoTextAreaLabel == null) {
			produtoTextAreaLabel = new JLabel("Nome do Treinamento");
			produtoTextAreaLabel.setSize(new Dimension(240, 20));
			produtoTextAreaLabel.setLocation(new Point(10, 0));
			produtoTextAreaLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return produtoTextAreaLabel;
	}


	protected JPanel getValorQtdPanel() {
		if (valorQtdPanel == null) {
			valorQtdPanel = new JPanel();
			valorQtdPanel.setSize(new Dimension(385, 29));
			valorQtdPanel.setLocation(new Point(10, 60));
			valorQtdPanel.setLayout(null);
			valorQtdPanel.add(getItemOrderSpinner());
			valorQtdPanel.add(getQtdSpinnerLabel());
		}
		return valorQtdPanel;
	}


	public TrainingFormationItem validateItemProdutoForm() throws Exception {
		setErrorIcon(false);
		Integer in = (Integer) itemOrderSpinner.getValue();
		itemProduto.setItemOrder(in);
		return itemProduto;
	}

	
	
	@SuppressWarnings("unchecked")
	public TrainingFormationItem cadastreItemProduto() throws Exception {
		try{			
			TrainingFormationItem ip =  validateItemProdutoForm();	
			if (formacao != null)
				return RemoteComercialSolutionService.getInstance()
				.saveOrUpdateComercialSolutionItem(ip, trainingSolution, formacao);
			return null;
		}catch(Exception e){
			e.printStackTrace();
			throw e;
		}
	}
	

	

	public void newRegister() {
		// Nunca definir um novo objeto entidade!!!
		itemProduto.setTrainingSolution(trainingSolution);
		itemProduto.setTrainingFormation(formacao);
		itemProduto.setId(0);
		itemProduto.setItemOrder(10);

		binder.reverseBind(this.itemProduto);

		this.setErrorIcon(false);
		updateUI();
	}

	public void editRegister(TrainingFormationItem objItemProduto) {
		// Nunca passar como argumento novos objetos!!!

		itemProduto.setId(objItemProduto.getId());
		itemProduto.setItemOrder(objItemProduto.getItemOrder());
		itemProduto.setTrainingSolution(trainingSolution);		
		itemProduto.setTrainingFormation(formacao);
		
		binder.reverseBind(this.itemProduto);
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

	public void setErrorIcon(boolean bool) {
		this.itemOrderSpinner.firePropertyChange("warnBorder", !bool, bool);
		
	}


	@SuppressWarnings("unchecked")
	protected JComponent getItemOrderSpinner() {
		if (itemOrderSpinner == null) {
			SpinnerNumberModel model = new SpinnerNumberModel(1, 1, 900000, 1);
			itemOrderSpinner = new JSpinner(model);
			itemOrderSpinner.setSize(new java.awt.Dimension(70, 22));
			itemOrderSpinner.setLocation(new java.awt.Point(310, 0));

			this.binder.addBindProperty(this.itemProduto, this.itemOrderSpinner,
					"itemOrder");

			this.hashComps.put("itemOrder", this.itemOrderSpinner);
			JWarningComponent warn = new JWarningComponent(this.itemOrderSpinner);
			warn.setBounds(310, 0, 70, 22);
			return warn;
		}
		return itemOrderSpinner;
	}
	
	protected JLabel getQtdSpinnerLabel() {
		if (qtdSpinnerLabel == null) {
			qtdSpinnerLabel = new JLabel("Ordem Deste Treinamento na Formação:");
			qtdSpinnerLabel.setSize(new Dimension(246, 25));
			qtdSpinnerLabel.setLocation(new Point(1, 1));
			qtdSpinnerLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return qtdSpinnerLabel;
	}

	protected JPanel getItemsButtonsPanel() {
		if (itemsButtonsPanel == null) {
			itemsButtonsPanel = new JPanel();
			itemsButtonsPanel.setSize(new Dimension(382, 36));
			itemsButtonsPanel.setLocation(new Point(10, 99));
			itemsButtonsPanel.setLayout(new java.awt.FlowLayout());
			itemsButtonsPanel.add(getConfirmarButton());
		}
		return itemsButtonsPanel;
	}

	public JButton getConfirmarButton() {
		if (confirmarButton == null) {
			confirmarButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.ItemProdutoCadastreForm.Confirmar"));
			confirmarButton.setSize(new java.awt.Dimension(100, 22));
			confirmarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_green.png")));
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
				gui.add(new TrainingFormationItemCadastreForm(),
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

	public TrainingSolution getTrainingSolution() {
		return trainingSolution;
	}

	public void setTrainingSolution(TrainingSolution produto) {
		this.trainingSolution = produto;
		itemProduto.setTrainingSolution(produto);
		produtoTextArea.setText(produto.getDescricao());
	}

	private FormacaoTreinamento formacao;
	
	public void setFormacao(FormacaoTreinamento formacao) {
		this.formacao = formacao;
		itemProduto.setTrainingFormation(formacao);
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"