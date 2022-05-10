package com.adapit.portal.ui.forms.training;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.ScheduleScheduledTrainingFilterType;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.forms.treinamento.CriarFormacaoTreinamentoDialog;
import com.adapit.portal.ui.forms.treinamento.ScheduledTrainingCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings("serial")
public class TrainingSolutionListForm extends JPanel implements CategoriaSelectable{
	
	private JTabbedPane filtersPanel;

	private JPanel filterFieldAndSearchButtonPanel;

	private JPanel nomePanel;

	private JTextField nomeTextField;

	private JLabel nomeTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JCheckBox nomeCheckBox;

	private JPanel nomeFiltePanel;

	private JRadioButton likeRadioButton;

	private JRadioButton equalsRadioButton;

	private JRadioButton endingRadioButton;

	private JRadioButton beginingRadioButton;

	private JPanel categoriaPanel;

	private JLabel categoriaComboBoxLabel;

	private JCheckBox categoriaCheckBox;

	private JPanel valorPanel;

	private JComboBox valorInicialComboBox;

	private JLabel valorInicialComboBoxLabel;

	private JComboBox valorFinalComboBox;

	private JLabel valorFinalComboBoxLabel;

	private JCheckBox filtrarFaixaValorCheckBox;

	private JPanel lotesPanel;

	private JRadioButton somenteTreinamentoadosRadioButton;

	private JRadioButton excluirTreinamentoadosRadioButton;

	private JRadioButton ambosRadioButton;

	private JPanel valorEEstadoPanel;

	private JButton pesquisarButton;

	private JPanel basePanel;

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel buttonsPanel;

	private JButton novoComercialSolutionButton;

	private JButton editarSoluçãoButton;

	private JButton removerButton;

	private JButton removerSolDoTreinamentoButton;

	private JButton lotearSoluçãosButton;

	private JButton editarTreinamentoButton;
	
	private Categoria selectedElement;
	
	private StringQueryKind byDescKind=StringQueryKind.LIKE;
	private ScheduleScheduledTrainingFilterType byProdTreinamento = ScheduleScheduledTrainingFilterType.Todos_Treinamentos;
	
	private boolean selecionar = false;

	public TrainingSolutionListForm() {
		initialize();
	}
	
	public TrainingSolutionListForm(boolean sel) {
		this.selecionar = sel;
		initialize();
	}

	private void initialize() {
		setSize(new Dimension(725, 550));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getFiltersPanel());
		add(getBasePanel());
		
		this.add(getPesquisarButton(), null);
		this.add(getTreinamentosPanel(), null);
		
		reportResultsLabel = new JLabel();
		reportResultsLabel.setBounds(new Rectangle(11, 199, 266, 20));
		reportResultsLabel.setText("");
		add(reportResultsLabel, null);
		add(getResultNumberPanel());
	}

	protected JTabbedPane getFiltersPanel() {
		if (filtersPanel == null) {
			filtersPanel = new JTabbedPane();
			filtersPanel.setSize(new Dimension(705, 150));
			filtersPanel.setLocation(new Point(10, 10));
			filtersPanel.add(getFilterFieldAndSearchButtonPanel(),"Listar pela Descrição do Treinamento");
			filtersPanel.add(getCategoriaPanel(), "Listar pela Categoria do Treinamento");
			filtersPanel.add(getValorEEstadoPanel(),"Por Faixa de Valores");
		}
		return filtersPanel;
	}

	protected JPanel getFilterFieldAndSearchButtonPanel() {
		if (filterFieldAndSearchButtonPanel == null) {
			filterFieldAndSearchButtonPanel = new JPanel();
			filterFieldAndSearchButtonPanel.setLayout(null);
			filterFieldAndSearchButtonPanel.setBounds(new Rectangle(21, 181, 509, 48));
			filterFieldAndSearchButtonPanel.add(getNomePanel());
			filterFieldAndSearchButtonPanel.add(getNomeFiltePanel());
			filterFieldAndSearchButtonPanel.add(getNomeCheckBox(), null);
		}
		return filterFieldAndSearchButtonPanel;
	}

	protected JPanel getNomePanel() {
		if (nomePanel == null) {
			nomePanel = new JPanel();
			nomePanel.setSize(new Dimension(675, 29));
			nomePanel.setLocation(new Point(10, 38));
			nomePanel.setLayout(null);
			nomePanel.add(getNomeTextField());
			nomePanel.add(getNomeTextFieldLabel());
		}
		return nomePanel;
	}

	protected JTextField getNomeTextField() {
		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new Dimension(500, 20));
			nomeTextField.setLocation(new Point(161, 0));
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
			nomeTextFieldLabel = new JLabel("Descrição do treinamento");
			nomeTextFieldLabel.setSize(new Dimension(155, 20));
			nomeTextFieldLabel.setLocation(new Point(0, 0));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}

	protected JCheckBox getNomeCheckBox() {
		if (nomeCheckBox == null) {
			nomeCheckBox = new JCheckBox(messages.getMessage("Filtrarpelonome"));
			nomeCheckBox.setBounds(new Rectangle(10, 12, 150, 20));
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
			nomeFiltePanel.setSize(new Dimension(674, 33));
			nomeFiltePanel.setLocation(new Point(10, 71));
			nomeFiltePanel.setLayout(new FlowLayout());
			
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
			categoriaPanel.setBounds(new Rectangle(14, 18, 673, 135));
			categoriaPanel.add(getTreeScrollPane());
			categoriaPanel.add(getCategoriaComboBoxLabel());
			categoriaPanel.add(getCategoriaCheckBox(), null);
		}
		return categoriaPanel;
	}

	private JScrollPane treeScrollPane;
	
	protected JScrollPane getTreeScrollPane(){
		if(treeScrollPane == null){
			treeScrollPane = new JScrollPane();
			treeScrollPane.setSize(new Dimension(518, 88));
			treeScrollPane.setLocation(new Point(140, 25));
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
		}
		return treeScrollPane;
	}

	protected CategoriaSelectableTreeController treeController;  //  @jve:decl-index=0:
	
	protected CategoriaSelectableTreeController getTreeController(){
		if (treeController == null){
			treeController = AdapitVirtualFrame.getInstance().getTreeController(this);			
			treeController.getTree().setEnabled(false);
		}
		return treeController;
	}
	
	protected JLabel getCategoriaComboBoxLabel() {
		if (categoriaComboBoxLabel == null) {
			categoriaComboBoxLabel = new JLabel(messages
					.getMessage("Categoria"));
			categoriaComboBoxLabel.setSize(new Dimension(121, 20));
			categoriaComboBoxLabel.setLocation(new Point(14, 26));
			categoriaComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return categoriaComboBoxLabel;
	}
	
	private boolean cat=true;

	protected JCheckBox getCategoriaCheckBox() {
		if (categoriaCheckBox == null) {
			categoriaCheckBox = new JCheckBox(messages
					.getMessage("Filtrarpelacategoria"));
			categoriaCheckBox.setBounds(new Rectangle(8, 4, 150, 20));
			categoriaCheckBox.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						if (cat){
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
			valorPanel.setLayout(null);
			valorPanel.setBounds(new Rectangle(451, 21, 159, 20));
		}
		return valorPanel;
	}

	protected JComboBox getValorInicialComboBox() {
		if (valorInicialComboBox == null) {
			valorInicialComboBox = new JComboBox();
			valorInicialComboBox.setEditable(true);
			valorInicialComboBox.setBounds(new Rectangle(198, 40, 100, 20));
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
			valorInicialComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
			valorInicialComboBoxLabel.setBounds(new Rectangle(96, 40, 100, 20));
		}
		return valorInicialComboBoxLabel;
	}

	protected JComboBox getValorFinalComboBox() {
		if (valorFinalComboBox == null) {
			valorFinalComboBox = new JComboBox();
			valorFinalComboBox.setEditable(true);
			valorFinalComboBox.setBounds(new Rectangle(198, 75, 100, 20));
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
			valorFinalComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
			valorFinalComboBoxLabel.setBounds(new Rectangle(96, 75, 100, 20));
		}
		return valorFinalComboBoxLabel;
	}

	protected JCheckBox getFiltrarFaixaValorCheckBox() {
		if (filtrarFaixaValorCheckBox == null) {
			filtrarFaixaValorCheckBox = new JCheckBox(messages
					.getMessage("Filtrarporvalor"));
			filtrarFaixaValorCheckBox.setBounds(new Rectangle(13, 8, 109, 20));
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

	protected JPanel getTreinamentosPanel() {
		if (lotesPanel == null) {
			lotesPanel = new JPanel();
			lotesPanel.setBounds(new Rectangle(160, 170, 436, 22));
			FlowLayout fl =new java.awt.FlowLayout();
			fl.setHgap(0);
			fl.setVgap(0);
			lotesPanel.setLayout(fl);
			lotesPanel.add(getSomenteTreinamentoadosRadioButton());
			lotesPanel.add(getExcluirTreinamentoadosRadioButton());
			lotesPanel.add(getAmbosRadioButton());
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getSomenteTreinamentoadosRadioButton());
			bg.add(getExcluirTreinamentoadosRadioButton());
			bg.add(getAmbosRadioButton());
		}
		return lotesPanel;
	}

	protected JRadioButton getSomenteTreinamentoadosRadioButton() {
		if (somenteTreinamentoadosRadioButton == null) {
			somenteTreinamentoadosRadioButton = new JRadioButton("Agendados");
			somenteTreinamentoadosRadioButton.setSize(new java.awt.Dimension(130, 20));
			somenteTreinamentoadosRadioButton.setLocation(new java.awt.Point(0, 0));
			somenteTreinamentoadosRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byProdTreinamento = ScheduleScheduledTrainingFilterType.Agendados;
					}
				}				
			});
			return somenteTreinamentoadosRadioButton;
		}
		return somenteTreinamentoadosRadioButton;
	}

	protected JRadioButton getExcluirTreinamentoadosRadioButton() {
		if (excluirTreinamentoadosRadioButton == null) {
			excluirTreinamentoadosRadioButton = new JRadioButton("Não Agendados");
			excluirTreinamentoadosRadioButton.setSize(new java.awt.Dimension(150, 20));
			excluirTreinamentoadosRadioButton.setLocation(new java.awt.Point(130, 0));
			excluirTreinamentoadosRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byProdTreinamento = ScheduleScheduledTrainingFilterType.Não_Agendados;
					}
				}				
			});
			return excluirTreinamentoadosRadioButton;
		}
		return excluirTreinamentoadosRadioButton;
	}

	protected JRadioButton getAmbosRadioButton() {
		if (ambosRadioButton == null) {
			ambosRadioButton = new JRadioButton("Todos");
			ambosRadioButton.setSize(new java.awt.Dimension(70, 20));
			ambosRadioButton.setLocation(new java.awt.Point(280, 0));
			ambosRadioButton.setSelected(true);
			ambosRadioButton.addItemListener(new ItemListener(){
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						byProdTreinamento = ScheduleScheduledTrainingFilterType.Todos_Treinamentos;
					}
				}				
			});
			return ambosRadioButton;
		}
		return ambosRadioButton;
	}

	protected JPanel getValorEEstadoPanel() {

		if (valorEEstadoPanel == null) {
			valorEEstadoPanel = new JPanel();
			valorEEstadoPanel.setLayout(null);
			valorEEstadoPanel.setBounds(new Rectangle(18, 218, 652, 97));
			valorEEstadoPanel.add(getValorPanel(), null);
			valorEEstadoPanel.add(getFiltrarFaixaValorCheckBox(), null);
			valorEEstadoPanel.add(getValorFinalComboBoxLabel(), null);
			valorEEstadoPanel.add(getValorFinalComboBox(), null);
			valorEEstadoPanel.add(getValorInicialComboBoxLabel(), null);
			valorEEstadoPanel.add(getValorInicialComboBox(), null);
		}
		return valorEEstadoPanel;
	}

	protected JButton getPesquisarButton() {

		if (pesquisarButton == null) {
			pesquisarButton = new JButton(messages.getMessage("Pesquisar"));
			pesquisarButton.setBounds(new Rectangle(10, 168, 123, 26));
			pesquisarButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/read_obj.gif", 15, 15));
			pesquisarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					pesquisar();
				}								
			});
		}
		return pesquisarButton;
	}
	
	String desc=null;
	Categoria c=null;
	float vi=-1,vf=-1;
	private void pesquisar(){				
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
		
		countFirst = RemoteComercialSolutionService.getInstance().countTrainingSolutionsAccordingTo(desc, byDescKind, c, vi, vf, byProdTreinamento);
		criarListar();		
	}
	
	@SuppressWarnings("unchecked")
	private void listar(int firstResult){
		List prodList = RemoteComercialSolutionService.getInstance().listTrainingSolutionsAccordingTo(desc, byDescKind, c, vi, vf, byProdTreinamento, firstResult);
		updateTable(prodList);
	}
	private JPanel resultNumberPanel = null;

	private JLabel reportResultsLabel = null;

	private JPanel getResultNumberPanel() {
		if (resultNumberPanel == null) {
			resultNumberPanel = new JPanel();
			GridLayout g = new GridLayout(1,20);
			g.setHgap(1);
			g.setVgap(1);
			resultNumberPanel.setLayout(g);
			resultNumberPanel.setBounds(new Rectangle(280, 198, 406, 21));
			
		}
		return resultNumberPanel;
	}
	
	int buttons=10;
	
	private void changeResultNumberPanel() {
		resultNumberPanel.removeAll();
		GridLayout g = new GridLayout(1, buttons);
		g.setHgap(1);
		g.setVgap(1);
		resultNumberPanel.setLayout(g);

	}
	private Integer countFirst=0;  //  @jve:decl-index=0:
	
	int max=FilterResultSize.solutionsListMaxSize;
	int total;
	int secBegin=0;
	int secCount;
	private void criarListar(){		
		
		total = countFirst;
		final int number = total/max;
		int rest = total%max;
		secBegin=0;
		secCount=0;
		reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando 1");
		getResultNumberPanel();
		changeResultNumberPanel();
		
		for (int i=0; i < buttons; i++){
			if (i < number){
				JButton jb = new JButton((i+1)+"");
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							int dif = (countFirst - ((i-1)*max));
							if (countFirst > (i-1)*max){
								listar((i-1)*max);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
							}
							else{
								int oldmax = max;
								max = dif;
								listar((i-1)*max);	
								
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
								
								max = oldmax;
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else if(rest > 0){				
				rest = 0;
				JButton jb = new JButton((i+1)+"");
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							listar((i-1)*max);
							reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else{
				JLabel jl = new JLabel();
				resultNumberPanel.add(jl);
			}
		}
		
		if (total < max){
			try{
				listar(0);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				listar(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultNumberPanel.updateUI();
	}
	
	
	@SuppressWarnings("unchecked")
	private void updateTable(List prodList) {
		getBaseTable().setElements(prodList);
		getBaseTable().updateTable();
	}

	protected JPanel getBasePanel() {

		if (basePanel == null) {
			basePanel = new JPanel();
			basePanel.setSize(new Dimension(708, 310));
			basePanel.setLocation(new Point(10, 220));
			basePanel.setLayout(null);
			basePanel.add(getBaseTableScrollPane());
			basePanel.add(getButtonsPanel());
		}
		return basePanel;
	}

	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new Dimension(704, 181));
			baseTableScrollPane.setLocation(new Point(0, 8));
			baseTableScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	public BaseTable getBaseTable() {
		if (baseTable == null) {
			baseTable = new BaseTable();
			if (!selecionar){
				baseTable.addMouseListener(new MouseAdapter(){
					 public void mouseReleased(MouseEvent e) {
					    if ((e.getModifiers() & InputEvent.BUTTON1_MASK)!=0){
					    	TrainingSolution prod = getSelectedSolução();						
							if (prod.getTrainingFormationItens() != null && prod.getTrainingFormationItens().size() > 0){
								getRemoverSolDoTreinamentoButton().setEnabled(true);
								getEditarTreinamentoButton().setEnabled(true);
								getTreinamentoarSoluçãosButton().setEnabled(false);
							}else{				
								getTreinamentoarSoluçãosButton().setEnabled(true);
								getRemoverSolDoTreinamentoButton().setEnabled(false);
								getEditarTreinamentoButton().setEnabled(false);
							}
							getRemoverButton().setEnabled(true);
							getEditarSoluçãoButton().setEnabled(true);
					    }
					  }
				});
			}
			return baseTable;
		}
		return baseTable;
	}
	@SuppressWarnings("unchecked")
	public TrainingSolution getSelectedSolução(){
		TrainingSolution prod = null;
		Iterator<TrainingSolution> it = getBaseTable().getElements().iterator();
		int row = getBaseTable().getSelectedRow();
		if (row < 0) return null;
		int id = (Integer) getBaseTable().getValueAt(row, 0);
		System.out.println(id);
		while(it.hasNext()){
			TrainingSolution p = it.next();
			if (p.getId() == id) return p;
		}
		return prod;
	}
	@SuppressWarnings("unchecked")
	public TrainingSolution getSelectedSolução(int row){
		TrainingSolution prod = null;
		Iterator<TrainingSolution> it = getBaseTable().getElements().iterator();
		
		if (row < 0) return null;
		int id = (Integer) getBaseTable().getValueAt(row, 0);
		while(it.hasNext()){
			TrainingSolution p = it.next();
			if (p.getId() == id) return p;
		}
		return prod;
	}

	public JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(703, 100));
			buttonsPanel.setLocation(new Point(0, 190));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			if (!selecionar){
				buttonsPanel.add(getNovoComercialSolutionButton());
				buttonsPanel.add(getEditarSoluçãoButton());
				buttonsPanel.add(getRemoverButton());
				buttonsPanel.add(getRemoverSolDoTreinamentoButton());
				buttonsPanel.add(getTreinamentoarSoluçãosButton());
				buttonsPanel.add(getEditarTreinamentoButton());
				buttonsPanel.add(getAddUpdateButton());
				buttonsPanel.add(getListUpdateButton());
			}
		}
		return buttonsPanel;
	}
	
	private JButton addUpdateButton;
	private JButton getAddUpdateButton(){
		if(addUpdateButton == null){
			addUpdateButton = new JButton("Adicionar Versão");
			addUpdateButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					ComercialSolution sol = getSelectedSolução();
					if(sol != null)
					AdapitVirtualFrame.getInstance().cadastrarUpdate(sol);
				}
			});
		}
		return addUpdateButton;
	}
	
	private JButton listUpdateButton;
	private JButton getListUpdateButton(){
		if(listUpdateButton == null){
			listUpdateButton = new JButton("Listar Versões");
			listUpdateButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					ComercialSolution sol = getSelectedSolução();
					if(sol != null)
					AdapitVirtualFrame.getInstance().listarUpdate(sol);
				}
			});
		}
		return listUpdateButton;
	}

	protected JButton getNovoComercialSolutionButton() {
		if (novoComercialSolutionButton == null) {
			novoComercialSolutionButton = new JButton("Novo Treinamento");
			novoComercialSolutionButton.setSize(new java.awt.Dimension(80, 22));
			novoComercialSolutionButton.setLocation(new java.awt.Point(0, 0));
			novoComercialSolutionButton.setIcon(new ImageIcon(getClass().getResource("/imgs/training-add.png")));
			novoComercialSolutionButton.setEnabled(true);
			novoComercialSolutionButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().newTrainingSolution();
				}				
			});
		}
		return novoComercialSolutionButton;
	}

	protected JButton getEditarSoluçãoButton() {

		if (editarSoluçãoButton == null) {
			editarSoluçãoButton = new JButton("Editar o Treinamento");
			editarSoluçãoButton.setSize(new java.awt.Dimension(80, 22));
			editarSoluçãoButton.setLocation(new java.awt.Point(0, 22));
			editarSoluçãoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/training-edit.png")));
			editarSoluçãoButton.setEnabled(false);
			editarSoluçãoButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					try {
						TrainingSolution p = getSelectedSolução();
						if (p == null) return;
						AdapitVirtualFrame.getInstance().editTrainingSolution(p);						
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}				
			});
		}
		return editarSoluçãoButton;
	}

	protected JButton getRemoverButton() {
		if (removerButton == null) {
			removerButton = new JButton("Apagar o Treinamento");
			removerButton.setSize(new java.awt.Dimension(80, 22));
			removerButton.setLocation(new java.awt.Point(0, 44));
			removerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/training-remove.png")));
			removerButton.setEnabled(false);
			removerButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					try {
						int row = getBaseTable().getSelectedRow();
						if (row > -1){
							ComercialSolution p = getSelectedSolução();
							if (p == null) return;
							int resp = JOptionPane.showConfirmDialog(TrainingSolutionListForm.this, "Apagar esta solução implicará em apagar todo histório dela!"+'\n'+"Você quer mesmo assim apagar este item?", "Remover solução", JOptionPane.YES_NO_OPTION);
							if (resp == JOptionPane.YES_OPTION){
								try {
									RemoteComercialSolutionService.getInstance().deleteCommercialSolutionById(p.getId(), p);
									pesquisar();
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}				
			});
		}
		return removerButton;
	}

	protected JButton getRemoverSolDoTreinamentoButton() {
		if (removerSolDoTreinamentoButton == null) {
			removerSolDoTreinamentoButton = new JButton("Remover Treinamento da Agenda");
			removerSolDoTreinamentoButton.setSize(new java.awt.Dimension(120, 22));
			removerSolDoTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_delete.png")));
			removerSolDoTreinamentoButton.setEnabled(false);
			removerSolDoTreinamentoButton.setLocation(new java.awt.Point(0, 66));
			removerSolDoTreinamentoButton.addActionListener(new RemoverSolucaoTreinamento());
		}
		return removerSolDoTreinamentoButton;
	}
	
	private class RemoverSolucaoTreinamento extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			int row = getBaseTable().getSelectedRow();
			if (row >= 0)try{
				TrainingSolution produto = getSelectedSolução();
				if (produto == null) return;
				if (produto.getComercialSolutionItens() != null && produto.getComercialSolutionItens().size()>0){
					for(ComercialSolutionItem ip: produto.getComercialSolutionItens()){
						if (ip.getTreinamento() != null){
							Treinamento lote = (Treinamento) ip.getTreinamento();							
							String desc = produto.getNome();
							if (desc != null){
								if (desc.length() > 40) desc = desc.substring(0,40);
								int resp = JOptionPane.showConfirmDialog(TrainingSolutionListForm.this, "Você quer retirar o treinamento" + '\n'+desc + '\n'+" da agenda " + lote.getCodigo() + "?","Remover Solução do Treinamento",JOptionPane.YES_NO_OPTION);
								if (resp == JOptionPane.YES_OPTION){
									
									RemoteComercialSolutionService.getInstance().removeTrainingSolutionItemFromScheduledTrainig(ip);
									
									resp = JOptionPane.showConfirmDialog(TrainingSolutionListForm.this, "Apagar em definitivo o treinamento " + desc + "?","Remover Treinamento",JOptionPane.YES_NO_OPTION);
									if (resp == JOptionPane.YES_OPTION){
										try {
											RemoteComercialSolutionService.getInstance().generateTrainingSolutionHistory(produto, lote, ip, true);
											JOptionPane.showMessageDialog(TrainingSolutionListForm.this, "Operação realizada com sucesso","Remover Treinamento",JOptionPane.INFORMATION_MESSAGE);
										} catch (Exception e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(TrainingSolutionListForm.this, "Problemas ao gerar o histórico da solução!","Problema ao remover solução",JOptionPane.ERROR_MESSAGE);
										}
									}else {
										try {
											RemoteComercialSolutionService.getInstance().generateTrainingSolutionHistory(produto, lote, ip, false);
											JOptionPane.showMessageDialog(TrainingSolutionListForm.this, "Operação realizada com sucesso","Remover Solução",JOptionPane.INFORMATION_MESSAGE);
										} catch (Exception e1) {
											e1.printStackTrace();
											JOptionPane.showMessageDialog(TrainingSolutionListForm.this, "Problemas ao gerar o histórico da solução!","Problema ao remover a solução",JOptionPane.ERROR_MESSAGE);
										}
									}									
								}	
							}
						}
					}	
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
			pesquisar();
		}
	}
	@SuppressWarnings("unchecked")
	protected JButton getTreinamentoarSoluçãosButton() {

		if (lotearSoluçãosButton == null) {
			lotearSoluçãosButton = new JButton("Adicionar Treinamento em uma Agenda");
			lotearSoluçãosButton.setSize(new java.awt.Dimension(120, 22));
			lotearSoluçãosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_put.png")));
			lotearSoluçãosButton.setEnabled(false);
			lotearSoluçãosButton.setLocation(new java.awt.Point(0, 88));
			lotearSoluçãosButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					List le = getBaseTable().getElements();
					int rows[] = getBaseTable().getSelectedRows();
					List filter = new Vector();
					boolean selection=false;
					if (rows != null && rows.length > 0){
						for(int i=0; i < rows.length; i++){
							TrainingSolution p = (TrainingSolution) getSelectedSolução(rows[i]);//le.get(rows[i]);
							if (p==null) continue;
							if (p.getComercialSolutionItens() != null && p.getComercialSolutionItens().size()>0){
								for(ComercialSolutionItem ip : p.getComercialSolutionItens()){
									if (ip.getTreinamento() == null) filter.add(p);
								}
							}
						}
						selection=true;
					}else{
						Iterator<TrainingSolution> it = le.iterator();
						while(it.hasNext()){
							TrainingSolution p = it.next();
							if (p.getComercialSolutionItens() != null && p.getComercialSolutionItens().size()>0){
								for(ComercialSolutionItem ip : p.getComercialSolutionItens()){
									if (ip.getTreinamento() == null) filter.add(p);
								}
							}
						}
					}
					CriarFormacaoTreinamentoDialog l = new CriarFormacaoTreinamentoDialog(AdapitVirtualFrame.getInstance(),filter,selection);					
					l.setVisible(true);
					pesquisar();
				}
				
			});
		}
		return lotearSoluçãosButton;
	}

	protected JButton getEditarTreinamentoButton() {
		if (editarTreinamentoButton == null) {
			editarTreinamentoButton = new JButton("Editar a Agenda do Treinamento");
			editarTreinamentoButton.setSize(new java.awt.Dimension(150, 20));
			editarTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarTreinamentoButton.setEnabled(false);
			editarTreinamentoButton.setLocation(new java.awt.Point(0, 110));
			editarTreinamentoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						TrainingSolution p = getSelectedSolução();
						if (p == null) return;
						if (p.getComercialSolutionItens() != null && p.getComercialSolutionItens().size()>0){
							for(ComercialSolutionItem ip : p.getComercialSolutionItens()){
								Treinamento l = (Treinamento) ip.getTreinamento();
								TurmaTreinamento leilao = l.getTurma();
								if (AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame != null){
									AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(leilao);
									AdapitVirtualFrame.getInstance().editarTreinamento(l,ScheduledTrainingCadastreForm.formatDataTreinamento(leilao));
								}else{
									AdapitVirtualFrame.getInstance().novoTreinamento();
									AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(leilao);
									AdapitVirtualFrame.getInstance().editarTreinamento(l,ScheduledTrainingCadastreForm.formatDataTreinamento(leilao));
								}
							}
						}
												
					}
				}
			});
		}
		return editarTreinamentoButton;
	}


	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(484, 508));
				gui.add(new TrainingSolutionListForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unchecked")
	public class BaseTable extends JXTable {

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
			getColumnModel().getColumn(0).setPreferredWidth(30);
			getColumnModel().getColumn(1).setPreferredWidth(200);
			getColumnModel().getColumn(4).setPreferredWidth(100);
			getColumnModel().getColumn(3).setPreferredWidth(50);
			getColumnModel().getColumn(2).setPreferredWidth(50);
			getColumnModel().getColumn(5).setPreferredWidth(50);
			addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent evt) {
					
					int col = getBaseTable().getSelectedColumn();
					int row = getBaseTable().getSelectedRow();
					if (getBaseTable().getElements() != null && row > -1 && col == 5){
						
						ComercialSolution solucao = getSelectedSolução();
						boolean b = !solucao.isPublicar();
						try {								
							RemoteComercialSolutionService.getInstance()
								.publishComercialSolutionBySolutionId(solucao.getId(), b);
							solucao.setPublicar(b);
							updateTable();
						} catch (Exception e1) {
							e1.printStackTrace();
							AdapitVirtualFrame.getInstance().showErrorDialog("Atualização de Dados", "Problemas ao atualizar o dado informado!");
						}							
						
					}
				}				
			});
		}

		public void updateTable() {
			getRemoverButton().setEnabled(false);
			getEditarSoluçãoButton().setEnabled(false);
			getRemoverSolDoTreinamentoButton().setEnabled(false);
			getTreinamentoarSoluçãosButton().setEnabled(false);
			getEditarTreinamentoButton().setEnabled(false);
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof TrainingSolution) {
						TrainingSolution solution = (TrainingSolution) obj;						
						try{
							values[i][0] = solution.getId();
							values[i][1] = solution.getNome();
							values[i][2] = solution.getAvaliacao();							
							if (solution.getCategoria() != null){
								try {									
									values[i][3] = solution.getCategoria().getNome();
								} catch (Exception e) {
									e.printStackTrace();
								} 
							}
							
							if (solution != null &&  solution.getTipoTreinamento() != null)try {
								values[i][4] = solution.getTipoTreinamento().name().replace("_"," ");
							} catch (RuntimeException e) {
								e.printStackTrace();
							}
							values[i][5] = solution.isPublicar();
							
						}catch(Exception ex){
							ex.printStackTrace();
						}finally{
							
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(30);
				getColumnModel().getColumn(1).setPreferredWidth(200);
				getColumnModel().getColumn(4).setPreferredWidth(100);
				getColumnModel().getColumn(3).setPreferredWidth(50);
				getColumnModel().getColumn(2).setPreferredWidth(50);
				getColumnModel().getColumn(5).setPreferredWidth(50);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(30);
				getColumnModel().getColumn(1).setPreferredWidth(200);
				getColumnModel().getColumn(4).setPreferredWidth(100);
				getColumnModel().getColumn(3).setPreferredWidth(50);
				getColumnModel().getColumn(2).setPreferredWidth(50);
				getColumnModel().getColumn(5).setPreferredWidth(50);
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {
			public void propertyChange(PropertyChangeEvent e) {

			}
		}
		//Converte um double em moeda (apenas 2 casas decimais) 
		 public double doubleParaMoeda(double value){
		         double currency = new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		         return currency;
		     }
		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class,String.class, Float.class,
					String.class, String.class, Boolean.class };

			boolean canEdit[] = new boolean[] { false, false, false, false, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Id","Nome","Valor","Categoria","Tipo","Publicar" });
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
		this.selectedElement=selectedElement;		
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"