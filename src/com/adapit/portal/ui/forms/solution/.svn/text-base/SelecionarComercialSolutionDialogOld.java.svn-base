package com.adapit.portal.ui.forms.solution;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.services.ScheduleScheduledTrainingFilterType;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class SelecionarComercialSolutionDialogOld  extends JDialog implements CategoriaSelectable{

	
	
	private JPanel filtersPanel;

	private JPanel filterFieldAndSearchButtonPanel;

	private JPanel nomePanel;

	private JTextField nomeTextField;

	//private SwingBinder binder = new SwingBinder();

	//private Map hashComps = new java.util.HashMap();

	private JLabel nomeTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JCheckBox nomeCheckBox;

	private JPanel nomeFiltePanel;

	private JRadioButton likeRadioButton;

	private JRadioButton equalsRadioButton;

	private JRadioButton endingRadioButton;

	private JRadioButton beginingRadioButton;

	private JPanel categoriaPanel;

	//private JComboBox categoriaComboBox;

	private JLabel categoriaComboBoxLabel;

	private JCheckBox categoriaCheckBox;

	private JPanel valorPanel;

	private JComboBox valorInicialComboBox;

	private JLabel valorInicialComboBoxLabel;

	private JComboBox valorFinalComboBox;

	private JLabel valorFinalComboBoxLabel;

	private JCheckBox filtrarFaixaValorCheckBox;

	private JPanel lotesPanel;

	private JRadioButton somenteLoteadosRadioButton;

	private JRadioButton excluirLoteadosRadioButton;

	private JRadioButton ambosRadioButton;

	private JPanel buttonPanel;

	private JButton pesquisarButton;

	private JPanel basePanel;

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel buttonsPanel;

	private JButton selecionarButton;

	private Categoria selectedElement;
	
	private StringQueryKind byDescKind=StringQueryKind.LIKE;
	private ScheduleScheduledTrainingFilterType byProdLote = ScheduleScheduledTrainingFilterType.Todos_Treinamentos;

	public SelecionarComercialSolutionDialogOld() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize() {
		setSize(new Dimension(713, 396));
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		setLayout(null);
		add(getFiltersPanel());
		add(getBasePanel());
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	/*public Vector listaProdutos(){
		Vector v = new Vector();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			String query = "select Produto.id as PRODUTO_ID, Produto.descricao as PRODUTO_DESC," +
					"     Categoria.id as Cat_id, Categoria.nome as NOME_CATEGORIA," +
					" ItemProduto.id as ItemProduto_id, ItemProduto.qtd, ItemProduto.valorTotal," +
					" Lote.id as LOTE_ID, Lote.codlote," +
					" Leilao.id as Leilao_id, Leilao.datapresencialprimeira, Leilao.datapresencialsegunda " +
					//" ItemProduto.valorTotal" +
					" from Produto, Categoria, ItemProduto, Lote, Leilao" +
					" where    Produto.categoria_id = Categoria.id" +
					"     and Produto.id = ItemProduto.produto_id" +
					"     and ItemProduto.lote_id = Lote.id" +
					"     and Lote.leilao_id = Leilao.id" +
					"     order by Categoria.nome";
			List l =  s.createSQLQuery(query).list();
			
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					Object objs[] = (Object[]) it.next();
					if (objs.length > 0){
						Produto p = new Produto();
						Categoria c = new Categoria();
						Lote lo = new Lote();
						ItemProduto ip = new ItemProduto();
						Leilao le = new Leilao();
						p.setCategoria(c);
						p.setItensProduto(ip);
						ip.setProduto(p);
						ip.setLote(lo);
						lo.setLeilao(le);
						
						p.setId(((Integer) objs[0]).intValue());
						p.setDescricao((String) objs[1]);
						c.setId(((Integer) objs[2]).intValue());
						c.setNome((String) objs[3]);
						ip.setId(((Integer) objs[4]).intValue());
						ip.setQtd(((Integer) objs[5]).intValue());
						
						ip.setValorTotal(((Float) objs[6]).floatValue());
						
						lo.setId(((Integer) objs[7]).intValue());
						lo.setCodLote((String) objs[8]);
						le.setId(((Integer) objs[9]).intValue());
						le.setDataPresencialPrimeira((Date) objs[10]);
						le.setDataPresencialSegunda((Date) objs[11]);
						
						v.add(p);
						
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		return v;
	}*/

	protected JPanel getFiltersPanel() {

		if (filtersPanel == null) {
			filtersPanel = new JPanel();
			filtersPanel
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(javax.swing.BorderFactory
									.createTitledBorder("Filtrar produtos de acordo com as seguintes regras")));
			filtersPanel.setSize(new Dimension(683, 159));
			filtersPanel.setLocation(new java.awt.Point(15, 20));
			filtersPanel.setLayout(null);
			filtersPanel.add(getFilterFieldAndSearchButtonPanel());
			filtersPanel.add(getValorPanel());
			filtersPanel.add(getLotesPanel());
			filtersPanel.add(getButtonPanel());
			filtersPanel.add(getCategoriaPanel(), null);
		}
		return filtersPanel;
	}

	protected JPanel getFilterFieldAndSearchButtonPanel() {

		if (filterFieldAndSearchButtonPanel == null) {
			filterFieldAndSearchButtonPanel = new JPanel();
			filterFieldAndSearchButtonPanel.setSize(new java.awt.Dimension(430,
					42));
			filterFieldAndSearchButtonPanel.setLocation(new java.awt.Point(10,
					18));
			filterFieldAndSearchButtonPanel.setLayout(null);
			filterFieldAndSearchButtonPanel.add(getNomePanel());
			filterFieldAndSearchButtonPanel.add(getNomeFiltePanel());
		}
		return filterFieldAndSearchButtonPanel;
	}

	protected JPanel getNomePanel() {

		if (nomePanel == null) {
			nomePanel = new JPanel();
			nomePanel.setSize(new java.awt.Dimension(430, 21));
			nomePanel.setLocation(new java.awt.Point(0, 0));
			nomePanel.setLayout(null);
			nomePanel.add(getNomeTextField());
			nomePanel.add(getNomeTextFieldLabel());
			nomePanel.add(getNomeCheckBox());
		}
		return nomePanel;
	}

	protected JTextField getNomeTextField() {

		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new java.awt.Dimension(200, 20));
			nomeTextField.setLocation(new java.awt.Point(110, 0));
			nomeTextField.setEditable(false);
			nomeTextField.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					pesquisar();
				}				
			});
			return nomeTextField;
		}
		return nomeTextField;
	}

	protected JLabel getNomeTextFieldLabel() {

		if (nomeTextFieldLabel == null) {
			nomeTextFieldLabel = new JLabel(messages
					.getMessage("DescriçãodoProduto"));
			nomeTextFieldLabel.setSize(new java.awt.Dimension(110, 20));
			nomeTextFieldLabel.setLocation(new Point(0, 0));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}

	protected JCheckBox getNomeCheckBox() {

		if (nomeCheckBox == null) {
			nomeCheckBox = new JCheckBox(messages.getMessage("Filtrarpelonome"));
			nomeCheckBox.setSize(new java.awt.Dimension(150, 20));
			nomeCheckBox.setLocation(new java.awt.Point(310, 0));
			nomeCheckBox.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						getLikeRadioButton().setEnabled(true);
						getEqualsRadioButton().setEnabled(true);
						getEndingRadioButton().setEnabled(true);
						getBeginingRadioButton().setEnabled(true);
						getNomeTextField().setEditable(true);
					}else{
						getLikeRadioButton().setEnabled(false);
						getEqualsRadioButton().setEnabled(false);
						getEndingRadioButton().setEnabled(false);
						getBeginingRadioButton().setEnabled(false);
						getNomeTextField().setEditable(false);
					}
				}
				
			});
			return nomeCheckBox;
		}
		return nomeCheckBox;
	}

	protected JPanel getNomeFiltePanel() {

		if (nomeFiltePanel == null) {
			nomeFiltePanel = new JPanel();
			nomeFiltePanel.setSize(new java.awt.Dimension(430, 20));
			nomeFiltePanel.setLocation(new java.awt.Point(0, 21));
			nomeFiltePanel.setLayout(null);
			nomeFiltePanel.add(getLikeRadioButton());
			nomeFiltePanel.add(getEqualsRadioButton());
			nomeFiltePanel.add(getEndingRadioButton());
			nomeFiltePanel.add(getBeginingRadioButton());
			ButtonGroup bg = new ButtonGroup();
			bg.add(getLikeRadioButton());
			bg.add(getEqualsRadioButton());
			bg.add(getEndingRadioButton());
			bg.add(getBeginingRadioButton());
		}
		return nomeFiltePanel;
	}

	protected JRadioButton getLikeRadioButton() {

		if (likeRadioButton == null) {
			likeRadioButton = new JRadioButton(messages
					.getMessage("Quecontenha"));
			likeRadioButton.setSize(new java.awt.Dimension(100, 20));
			likeRadioButton.setLocation(new java.awt.Point(0, 0));
			likeRadioButton.setSelected(true);
			likeRadioButton.setEnabled(false);
			likeRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byDescKind = StringQueryKind.LIKE;
					}
				}				
			});
			return likeRadioButton;
		}
		return likeRadioButton;
	}

	protected JRadioButton getEqualsRadioButton() {

		if (equalsRadioButton == null) {
			equalsRadioButton = new JRadioButton(messages
					.getMessage("Exatamentecomo"));
			equalsRadioButton.setSize(new java.awt.Dimension(120, 20));
			equalsRadioButton.setLocation(new java.awt.Point(100, 0));
			equalsRadioButton.setEnabled(false);
			equalsRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byDescKind = StringQueryKind.EQUALS;
					}
				}				
			});
			return equalsRadioButton;
		}
		return equalsRadioButton;
	}

	protected JRadioButton getEndingRadioButton() {

		if (endingRadioButton == null) {
			endingRadioButton = new JRadioButton(messages
					.getMessage("Terminandoem"));
			endingRadioButton.setSize(new java.awt.Dimension(110, 20));
			endingRadioButton.setLocation(new java.awt.Point(220, 0));
			endingRadioButton.setEnabled(false);
			endingRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byDescKind = StringQueryKind.ENDS_WITH;
					}
				}				
			});
			return endingRadioButton;
		}
		return endingRadioButton;
	}

	protected JRadioButton getBeginingRadioButton() {

		if (beginingRadioButton == null) {
			beginingRadioButton = new JRadioButton(messages
					.getMessage("Iniciandoem"));
			beginingRadioButton.setSize(new java.awt.Dimension(120, 20));
			beginingRadioButton.setLocation(new java.awt.Point(330, 0));
			beginingRadioButton.setEnabled(false);
			beginingRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byDescKind = StringQueryKind.BEGINS_WITH;
					}
				}				
			});
			return beginingRadioButton;
		}
		return beginingRadioButton;
	}

	protected JPanel getCategoriaPanel() {

		if (categoriaPanel == null) {
			categoriaPanel = new JPanel();
			categoriaPanel.setLayout(null);
			categoriaPanel.setBounds(new Rectangle(445, 18, 228, 135));
			categoriaPanel.add(getTreeScrollPane());
			categoriaPanel.add(getCategoriaComboBoxLabel());
			categoriaPanel.add(getCategoriaCheckBox(), null);
		}
		return categoriaPanel;
	}

	/*protected JComboBox getCategoriaComboBox() {

		if (categoriaComboBox == null) {
			categoriaComboBox = new JComboBox();
			categoriaComboBox.setSize(new java.awt.Dimension(210, 52));
			categoriaComboBox.setLocation(new java.awt.Point(100, 0));
			return categoriaComboBox;
		}
		return categoriaComboBox;
	}*/
	
	private JScrollPane treeScrollPane;
	
	protected JScrollPane getTreeScrollPane(){
		if(treeScrollPane == null){
			treeScrollPane = new JScrollPane();
			treeScrollPane.setSize(new Dimension(221, 106));
			treeScrollPane.setLocation(new Point(2, 26));
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			//treeScrollPane.add(getTreeController().getTree());
			//treeScrollPane.setViewportView(getTreeController().getTree());
		}
		return treeScrollPane;
	}

	protected CategoriaSelectableTreeController treeController;
	
	protected CategoriaSelectableTreeController getTreeController(){
		if (treeController == null){
			treeController = new CategoriaSelectableTreeController(this);
			treeController.getTree().setEnabled(false);
		}
		return treeController;
	}
	
	protected JLabel getCategoriaComboBoxLabel() {

		if (categoriaComboBoxLabel == null) {
			categoriaComboBoxLabel = new JLabel(messages
					.getMessage("Categoria"));
			categoriaComboBoxLabel.setSize(new Dimension(63, 20));
			categoriaComboBoxLabel.setLocation(new java.awt.Point(0, 0));
			categoriaComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
			
		}
		return categoriaComboBoxLabel;
	}

	private boolean cat=true;
	protected JCheckBox getCategoriaCheckBox() {

		if (categoriaCheckBox == null) {
			categoriaCheckBox = new JCheckBox(messages
					.getMessage("Filtrarpelacategoria"));
			categoriaCheckBox.setBounds(new Rectangle(71, 1, 150, 20));
			categoriaCheckBox.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						if (cat){
							treeScrollPane.add(getTreeController().getTree());
							treeScrollPane.setViewportView(getTreeController().getTree());
							cat=false;
						}
						getTreeController().getTree().setEnabled(true);
					}else{
						getTreeController().getTree().setEnabled(false);
					}
				}
				
			});
			return categoriaCheckBox;
		}
		return categoriaCheckBox;
	}

	protected JPanel getValorPanel() {

		if (valorPanel == null) {
			valorPanel = new JPanel();
			valorPanel.setSize(new java.awt.Dimension(431, 20));
			valorPanel.setLocation(new Point(10, 62));
			valorPanel.setLayout(null);
			valorPanel.add(getValorInicialComboBox());
			valorPanel.add(getValorInicialComboBoxLabel());
			valorPanel.add(getValorFinalComboBox());
			valorPanel.add(getValorFinalComboBoxLabel());
			valorPanel.add(getFiltrarFaixaValorCheckBox());
		}
		return valorPanel;
	}

	protected JComboBox getValorInicialComboBox() {

		if (valorInicialComboBox == null) {
			valorInicialComboBox = new JComboBox();
			valorInicialComboBox.setSize(new java.awt.Dimension(67, 20));
			valorInicialComboBox.setLocation(new Point(101, 0));
			valorInicialComboBox.setEditable(true);
			valorInicialComboBox.setEnabled(false);
			valorInicialComboBox.addItem(100);
			valorInicialComboBox.addItem(200);
			valorInicialComboBox.addItem(500);
			valorInicialComboBox.addItem(1000);
			valorInicialComboBox.addItem(5000);
			valorInicialComboBox.addItem(10000);
			valorInicialComboBox.addItem(20000);
			return valorInicialComboBox;
		}
		return valorInicialComboBox;
	}

	protected JLabel getValorInicialComboBoxLabel() {

		if (valorInicialComboBoxLabel == null) {
			valorInicialComboBoxLabel = new JLabel(messages
					.getMessage("ValorInicial"));
			valorInicialComboBoxLabel.setSize(new Dimension(99, 20));
			valorInicialComboBoxLabel.setLocation(new Point(1, 0));
			valorInicialComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return valorInicialComboBoxLabel;
	}

	protected JComboBox getValorFinalComboBox() {

		if (valorFinalComboBox == null) {
			valorFinalComboBox = new JComboBox();
			valorFinalComboBox.setSize(new java.awt.Dimension(67, 20));
			valorFinalComboBox.setLocation(new Point(243, 0));
			valorFinalComboBox.setEditable(true);
			valorFinalComboBox.setEnabled(false);
			
			valorFinalComboBox.addItem(200);
			valorFinalComboBox.addItem(500);
			valorFinalComboBox.addItem(1000);
			valorFinalComboBox.addItem(5000);
			valorFinalComboBox.addItem(10000);
			valorFinalComboBox.addItem(20000);
			valorFinalComboBox.addItem(50000);
			valorFinalComboBox.addItem(100000);
			valorFinalComboBox.addItem(500000);
			
			
			return valorFinalComboBox;
		}
		return valorFinalComboBox;
	}

	protected JLabel getValorFinalComboBoxLabel() {

		if (valorFinalComboBoxLabel == null) {
			valorFinalComboBoxLabel = new JLabel(messages
					.getMessage("ValorFinal"));
			valorFinalComboBoxLabel.setSize(new Dimension(68, 20));
			valorFinalComboBoxLabel.setLocation(new Point(175, 0));
			valorFinalComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return valorFinalComboBoxLabel;
	}

	protected JCheckBox getFiltrarFaixaValorCheckBox() {

		if (filtrarFaixaValorCheckBox == null) {
			filtrarFaixaValorCheckBox = new JCheckBox(messages
					.getMessage("Filtrarporvalor"));
			filtrarFaixaValorCheckBox.setSize(new Dimension(119, 20));
			filtrarFaixaValorCheckBox.setLocation(new Point(310, 0));
			filtrarFaixaValorCheckBox.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						getValorInicialComboBox().setEnabled(true);
						getValorFinalComboBox().setEnabled(true);
					}else{
						getValorInicialComboBox().setEnabled(false);
						getValorFinalComboBox().setEnabled(false);
					}
				}				
			});
			return filtrarFaixaValorCheckBox;
		}
		return filtrarFaixaValorCheckBox;
	}

	protected JPanel getLotesPanel() {

		if (lotesPanel == null) {
			lotesPanel = new JPanel();
			lotesPanel.setSize(new java.awt.Dimension(430, 22));
			lotesPanel.setLocation(new Point(10, 85));
			FlowLayout fl =new java.awt.FlowLayout();
			fl.setHgap(0);
			fl.setVgap(0);
			lotesPanel.setLayout(fl);
			lotesPanel.add(getSomenteLoteadosRadioButton());
			lotesPanel.add(getExcluirLoteadosRadioButton());
			lotesPanel.add(getAmbosRadioButton());
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getSomenteLoteadosRadioButton());
			bg.add(getExcluirLoteadosRadioButton());
			bg.add(getAmbosRadioButton());
		}
		return lotesPanel;
	}

	protected JRadioButton getSomenteLoteadosRadioButton() {

		if (somenteLoteadosRadioButton == null) {
			somenteLoteadosRadioButton = new JRadioButton(messages
					.getMessage("SomenteosLoteados"));
			somenteLoteadosRadioButton.setSize(new java.awt.Dimension(130, 20));
			somenteLoteadosRadioButton.setLocation(new java.awt.Point(0, 0));
			somenteLoteadosRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byProdLote = ScheduleScheduledTrainingFilterType.Agendados;
					}
				}				
			});
			return somenteLoteadosRadioButton;
		}
		return somenteLoteadosRadioButton;
	}

	protected JRadioButton getExcluirLoteadosRadioButton() {

		if (excluirLoteadosRadioButton == null) {
			excluirLoteadosRadioButton = new JRadioButton(messages
					.getMessage("SomenteosnãoLoteados"));
			excluirLoteadosRadioButton.setSize(new java.awt.Dimension(150, 20));
			excluirLoteadosRadioButton.setLocation(new java.awt.Point(130, 0));
			excluirLoteadosRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byProdLote = ScheduleScheduledTrainingFilterType.Não_Agendados;
					}
				}				
			});
			return excluirLoteadosRadioButton;
		}
		return excluirLoteadosRadioButton;
	}

	protected JRadioButton getAmbosRadioButton() {

		if (ambosRadioButton == null) {
			ambosRadioButton = new JRadioButton(messages.getMessage("Ambos"));
			ambosRadioButton.setSize(new java.awt.Dimension(70, 20));
			ambosRadioButton.setLocation(new java.awt.Point(280, 0));
			ambosRadioButton.setSelected(true);
			ambosRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byProdLote = ScheduleScheduledTrainingFilterType.Todos_Treinamentos;
					}
				}				
			});
			return ambosRadioButton;
		}
		return ambosRadioButton;
	}

	protected JPanel getButtonPanel() {

		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setSize(new Dimension(430, 42));
			buttonPanel.setLocation(new Point(10, 112));
			buttonPanel.setLayout(new java.awt.FlowLayout());
			buttonPanel.add(getPesquisarButton());
		}
		return buttonPanel;
	}

	protected JButton getPesquisarButton() {

		if (pesquisarButton == null) {
			pesquisarButton = new JButton(messages.getMessage("Pesquisar"));
			pesquisarButton.setSize(new java.awt.Dimension(90, 24));
			pesquisarButton.setLocation(new java.awt.Point(0, 3));
			pesquisarButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/read_obj.gif", 15, 15));
			pesquisarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					pesquisar();
				}								
			});
		}
		return pesquisarButton;
	}
	@SuppressWarnings("unchecked")
	private void pesquisar(){
		String desc=null;
		float vi=-1,vf=-1;
		Categoria c=null;
		if (getNomeCheckBox().isSelected()){
			desc = getNomeTextField().getText();
		}
		if (getFiltrarFaixaValorCheckBox().isSelected()){
			float f= -1;
			try {
				f=Float.parseFloat((String)getValorFinalComboBox().getSelectedItem().toString());
				vf=f;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			try {
				f=Float.parseFloat((String)getValorInicialComboBox().getSelectedItem().toString());
				vi=f;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		if (getCategoriaCheckBox().isSelected()){
			c = getSelectedElement();
		}
		
		List prodList = RemoteComercialSolutionService.getInstance().listTrainingSolutionsAccordingTo(desc, byDescKind, c, vi, vf, byProdLote,0);
		updateTable(prodList);
	}
	@SuppressWarnings("unchecked")
	private void updateTable(List prodList) {
		getBaseTable().setElements(prodList);
		getBaseTable().updateTable();
	}

	protected JPanel getBasePanel() {

		if (basePanel == null) {
			basePanel = new JPanel();
			basePanel.setSize(new Dimension(685, 184));
			basePanel.setLocation(new Point(16, 183));
			basePanel.setLayout(null);
			basePanel.add(getBaseTableScrollPane());
			basePanel.add(getButtonsPanel());
		}
		return basePanel;
	}

	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new Dimension(682, 140));
			baseTableScrollPane.setLocation(new Point(0, 5));
			baseTableScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	protected BaseTable getBaseTable() {
		if (baseTable == null) {
			baseTable = new BaseTable();
			baseTable.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent evt) {
					getSelecionarButton().setEnabled(true);
					
				}

				public void focusLost(FocusEvent evt) {
				}				
			});
			return baseTable;
		}
		return baseTable;
	}

	protected JPanel getButtonsPanel() {

		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(682, 33));
			buttonsPanel.setLocation(new Point(0, 149));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getSelecionarButton());
		}
		return buttonsPanel;
	}

	protected JButton getSelecionarButton() {

		if (selecionarButton == null) {
			selecionarButton = new JButton(messages
					.getMessage("RemoverdoLote"));
			selecionarButton.setSize(new java.awt.Dimension(120, 22));
			selecionarButton.setText("Selecionar");
			selecionarButton.setEnabled(false);
			selecionarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/accept.png")));
			selecionarButton.setLocation(new java.awt.Point(0, 66));
			selecionarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						produto = (ComercialSolution) getBaseTable().getElements().get(row);
						dispose();
					}
				}
			});
		}
		return selecionarButton;
	}
	
	private ComercialSolution produto;
	
	public ComercialSolution getProduto(){
		return produto;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(484, 508));
				gui.add(new ComercialSolutionListForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unchecked")
	private class BaseTable extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public BaseTable() {
			super();
			//setRowHeight(0, 60);
			//setRowHeight(20);
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			initialize();
		}

		public BaseTable(List elements) {
			super();			
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			initialize();
		}
		
		@SuppressWarnings("deprecation")
		private void initialize(){
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			getColumnModel().getColumn(0).setPreferredWidth(40);
			getColumnModel().getColumn(1).setPreferredWidth(240);
			getColumnModel().getColumn(2).setPreferredWidth(30);
			getColumnModel().getColumn(4).setPreferredWidth(100);
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][7];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof ComercialSolution) {
						ComercialSolution produto = (ComercialSolution) obj;
						
						try{
							values[i][0] = produto.getId();
							values[i][1] = produto.getDescricao();
							/*if (produto.getComercialSolutionItens() != null ){
								
								for(ComercialSolutionItem ip : produto.getComercialSolutionItens()){
									try {
										values[i][2] = ip.getQtd();
										//String numb = DecimalFormat.getNumberInstance(messages.getCurrentLocale()).format(doubleParaMoeda(ip.getValorTotal()));
										values[i][3] = doubleParaMoeda(ip.getValorTotal());
										
									} catch (RuntimeException e) {
										e.printStackTrace();
									}
								}
							}*/
							if (produto.getCategoria() != null){
								try {									
									values[i][4] = produto.getCategoria().getNome();
								} catch (Exception e) {
									e.printStackTrace();
								} 
							}
							
							/*if (produto.getComercialSolutionItens() != null ){
								for(ComercialSolutionItem ip : produto.getComercialSolutionItens()){
									Treinamento l = ip.getTreinamento();
									if (l != null) values[i][5]=ip.getTreinamento().getCodigo();
									
									if (ip.getTreinamento() != null){
										if (ip.getTreinamento().getTurma() != null){
											try {
												if (ip.getTreinamento().getTurma().getDataTreinamento() != null){
													Date dt = ip.getTreinamento().getTurma().getDataTreinamento();
													values[i][6]=LeilaoVirtualFrame.getInstance().format(dt);
												}
											} catch (RuntimeException e) {
												e.printStackTrace();
											}
										}
									}
								}
							}*/
						}catch(Exception ex){
							ex.printStackTrace();
						}finally{
							
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(40);
				getColumnModel().getColumn(1).setPreferredWidth(240);
				getColumnModel().getColumn(2).setPreferredWidth(30);
				getColumnModel().getColumn(4).setPreferredWidth(100);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(40);
				getColumnModel().getColumn(1).setPreferredWidth(240);
				getColumnModel().getColumn(2).setPreferredWidth(30);
				getColumnModel().getColumn(4).setPreferredWidth(100);
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				/*com.adapit.portal.ui.forms.baseleilao.ui.ProdutoListForm.BaseTable jt = (com.adapit.portal.ui.forms.baseleilao.ui.ProdutoListForm.BaseTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Produto) {
							com.adapit.portal.entidades.Produto produto = (com.adapit.portal.entidades.Produto) obj;
							if (col == 0)
								produto.setDescricao(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 1)
								produto.setAvaliacao((java.lang.Float
										.parseFloat((java.lang.String) jt
												.getValueAt(row, col))));
							if (col == 2)
								produto
										.setCategoria((com.adapit.portal.entidades.Categoria) ((javax.swing.JComboBox) jt
												.getValueAt(row, col))
												.getSelectedItem());
							// if (col ==2)
							// produto.getNome((com.adapit.portal.entidades.Categoria)((javax.swing.JComboBox)jt.getValueAt(row,
							// col)).getSelectedItem());
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}*/
			}

		}
		//Converte um double em moeda (apenas 2 casas decimais) 
		 public double doubleParaMoeda(double value){
		         double currency = new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		         return currency;
		     }
		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class,String.class, Integer.class, Float.class,
					JComboBox.class, String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false, false, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Cod","Descrição",
						"Qtd",
						Currency.getInstance(messages.getCurrentLocale()).getSymbol()+" Valor do lote",
						"Categoria",
						"Lote",
						"Data do leilão" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		}

	}

	public Categoria getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Categoria selectedElement) {
		this.selectedElement=(selectedElement);		
	}

}