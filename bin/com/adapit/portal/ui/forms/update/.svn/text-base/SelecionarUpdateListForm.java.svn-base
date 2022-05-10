package com.adapit.portal.ui.forms.update;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Update;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.remote.RemoteUpdateService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class SelecionarUpdateListForm extends JDialog{

	
	
	private JPanel filtersPanel;

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

	private JPanel buttonPanel;

	private JButton pesquisarButton;

	private JPanel basePanel;

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel buttonsPanel;

	private JButton selecionarButton;
	
	private StringQueryKind byDescKind=StringQueryKind.LIKE;

	private ComercialSolution solution;
	public SelecionarUpdateListForm(ComercialSolution c) {
		super(AdapitVirtualFrame.getInstance());
		this.solution = c;
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
	
	/*public Vector listaUpdates(){
		Vector v = new Vector();
		Session s = LocalServicesUtility.getInstance().openSession();
		try{
			String query = "select Update.id as PRODUTO_ID, Update.descricao as PRODUTO_DESC," +
					"     Categoria.id as Cat_id, Categoria.nome as NOME_CATEGORIA," +
					" ItemUpdate.id as ItemUpdate_id, ItemUpdate.qtd, ItemUpdate.valorTotal," +
					" Lote.id as LOTE_ID, Lote.codlote," +
					" Leilao.id as Leilao_id, Leilao.datapresencialprimeira, Leilao.datapresencialsegunda " +
					//" ItemUpdate.valorTotal" +
					" from Update, Categoria, ItemUpdate, Lote, Leilao" +
					" where    Update.categoria_id = Categoria.id" +
					"     and Update.id = ItemUpdate.update_id" +
					"     and ItemUpdate.lote_id = Lote.id" +
					"     and Lote.leilao_id = Leilao.id" +
					"     order by Categoria.nome";
			List l =  s.createSQLQuery(query).list();
			
			
			if (l != null && l.size()>0){
				Iterator it = l.iterator();
				while (it.hasNext()){
					Object objs[] = (Object[]) it.next();
					if (objs.length > 0){
						Update p = new Update();
						Categoria c = new Categoria();
						Lote lo = new Lote();
						ItemUpdate ip = new ItemUpdate();
						Leilao le = new Leilao();
						p.setCategoria(c);
						p.setItemUpdate(ip);
						ip.setUpdate(p);
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
						
						for (int i=0; i < objs.length;i++){
							Object obj = objs[i];
							System.out.println(obj.getClass().getName());
							if (i == 0) p.setId((Integer) objs[i]);
						}
					}
				}
			}//else System.out.println("menor que zero ou nulo");
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		return v;
	}
*/
	protected JPanel getFiltersPanel() {

		if (filtersPanel == null) {
			filtersPanel = new JPanel();
			filtersPanel
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(javax.swing.BorderFactory
									.createTitledBorder("Filtrar updates de acordo com as seguintes regras")));
			filtersPanel.setSize(new Dimension(683, 159));
			filtersPanel.setLocation(new java.awt.Point(15, 20));
			filtersPanel.setLayout(null);
			filtersPanel.add(getFilterFieldAndSearchButtonPanel());			
			filtersPanel.add(getButtonPanel());
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
					.getMessage("DescriçãodoUpdate"));
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
		
		if (getNomeCheckBox().isSelected()){
			desc = getNomeTextField().getText();
		}
		
		
		List prodList = RemoteUpdateService.getInstance().listAccordingTo(desc, byDescKind, new ComercialSolution(solution.getId()));
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
						update = (Update) getBaseTable().getElements().get(row);
						dispose();
					}
				}
			});
		}
		return selecionarButton;
	}
	
	private Update update;
	
	public Update getUpdate(){
		return update;
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
					if (obj instanceof com.adapit.portal.entidades.Update) {
						com.adapit.portal.entidades.Update update = (com.adapit.portal.entidades.Update) obj;
						
						try{
							values[i][0] = update.getId();
							values[i][1] = update.getDescricao();
							values[i][2] = update.getTitulo();
							values[i][3] = update.getResumo();							
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

				/*com.adapit.portal.ui.forms.baseleilao.ui.UpdateListForm.BaseTable jt = (com.adapit.portal.ui.forms.baseleilao.ui.UpdateListForm.BaseTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Update) {
							com.adapit.portal.entidades.Update update = (com.adapit.portal.entidades.Update) obj;
							if (col == 0)
								update.setDescricao(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 1)
								update.setAvaliacao((java.lang.Float
										.parseFloat((java.lang.String) jt
												.getValueAt(row, col))));
							if (col == 2)
								update
										.setCategoria((com.adapit.portal.entidades.Categoria) ((javax.swing.JComboBox) jt
												.getValueAt(row, col))
												.getSelectedItem());
							// if (col ==2)
							// update.getNome((com.adapit.portal.entidades.Categoria)((javax.swing.JComboBox)jt.getValueAt(row,
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

			Class types[] = new java.lang.Class[] { Integer.class,String.class,  String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Cod","Descrição",
						"Título","Resumo" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		}

	}


}  //  @jve:decl-index=0:visual-constraint="10,10"