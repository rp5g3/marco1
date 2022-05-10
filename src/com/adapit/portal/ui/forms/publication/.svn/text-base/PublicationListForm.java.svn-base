package com.adapit.portal.ui.forms.publication;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
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

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Publication;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.remote.RemotePublicationService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class PublicationListForm extends JPanel{
	
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

	private JButton novoPublicationButton;

	private JButton editarPublicationButton;

	private JButton removerButton;

	private StringQueryKind byDescKind=StringQueryKind.LIKE;

	public PublicationListForm() {
		initialize();
	}

	private void initialize() {
		setSize(new Dimension(737, 463));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getFiltersPanel());
		add(getBasePanel());
		

	}
	


	protected JPanel getFiltersPanel() {
		if (filtersPanel == null) {
			filtersPanel = new JPanel();
			filtersPanel
					.setBorder(javax.swing.BorderFactory
							.createTitledBorder(javax.swing.BorderFactory
									.createTitledBorder("Filtrar artigos de acordo com as seguintes regras")));
			filtersPanel.setSize(new Dimension(715, 129));
			filtersPanel.setLocation(new Point(10, 11));
			filtersPanel.setLayout(null);
			filtersPanel.add(getFilterFieldAndSearchButtonPanel());			
			filtersPanel.add(getButtonPanel());
		}
		return filtersPanel;
	}
	


	protected JPanel getFilterFieldAndSearchButtonPanel() {

		if (filterFieldAndSearchButtonPanel == null) {
			filterFieldAndSearchButtonPanel = new JPanel();
			filterFieldAndSearchButtonPanel.setSize(new Dimension(699, 56));
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
			nomePanel.setSize(new Dimension(684, 21));
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
			nomeTextField.setSize(new Dimension(375, 20));
			nomeTextField.setLocation(new Point(141, 0));
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
			nomeTextFieldLabel = new JLabel("Descrição do Artigo:");
			nomeTextFieldLabel.setSize(new Dimension(134, 20));
			nomeTextFieldLabel.setLocation(new Point(0, 0));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}

	protected JCheckBox getNomeCheckBox() {

		if (nomeCheckBox == null) {
			nomeCheckBox = new JCheckBox("Filtrar pela descrição");
			nomeCheckBox.setSize(new Dimension(153, 20));
			nomeCheckBox.setLocation(new Point(522, 0));
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
			nomeFiltePanel.setSize(new Dimension(684, 30));
			nomeFiltePanel.setLocation(new java.awt.Point(0, 25));
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

	
	
	protected JPanel getButtonPanel() {

		if (buttonPanel == null) {
			buttonPanel = new JPanel();
			buttonPanel.setSize(new Dimension(697, 42));
			buttonPanel.setLocation(new Point(12, 79));
			buttonPanel.setLayout(new java.awt.FlowLayout());
			buttonPanel.add(getPesquisarButton());
		}
		return buttonPanel;
	}

	protected JButton getPesquisarButton() {

		if (pesquisarButton == null) {
			pesquisarButton = new JButton("Pesquisar Artigos");
			pesquisarButton.setSize(new java.awt.Dimension(90, 22));
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

		List prodList = RemotePublicationService.getInstance().listAccordingTo(desc, byDescKind);
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
			basePanel.setSize(new Dimension(716, 315));
			basePanel.setLocation(new Point(10, 142));
			basePanel.setLayout(null);
			basePanel.add(getBaseTableScrollPane());
			basePanel.add(getButtonsPanel());
		}
		return basePanel;
	}

	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new Dimension(711, 235));
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
			
			baseTable.addMouseListener(new MouseAdapter(){
				 public void mouseReleased(MouseEvent e)
				  {
				    if ((e.getModifiers() & InputEvent.BUTTON1_MASK)!=0){
				    	//Publication prod = getSelectedPublication();					
						
						getRemoverButton().setEnabled(true);
						getEditarPublicationButton().setEnabled(true);
				    }
				  }
			});
			return baseTable;
		}
		return baseTable;
	}
	@SuppressWarnings("unchecked")
	public Publication getSelectedPublication(){
		Publication prod = null;
		Iterator<Publication> it = getBaseTable().getElements().iterator();
		int row = getBaseTable().getSelectedRow();
		if (row < 0) return null;
		int id = (Integer) getBaseTable().getValueAt(row, 0);
		while(it.hasNext()){
			Publication p = it.next();
			if (p.getId() == id) return p;
		}
		return prod;
	}
	@SuppressWarnings("unchecked")
	public Publication getSelectedPublication(int row){
		Publication prod = null;
		Iterator<Publication> it = getBaseTable().getElements().iterator();
		
		if (row < 0) return null;
		int id = (Integer) getBaseTable().getValueAt(row, 0);
		while(it.hasNext()){
			Publication p = it.next();
			if (p.getId() == id) return p;
		}
		return prod;
	}

	protected JPanel getButtonsPanel() {

		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(709, 69));
			buttonsPanel.setLocation(new Point(0, 243));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getNovoPublicationButton());
			buttonsPanel.add(getEditarPublicationButton());
			buttonsPanel.add(getRemoverButton());
		}
		return buttonsPanel;
	}

	protected JButton getNovoPublicationButton() {
		if (novoPublicationButton == null) {
			novoPublicationButton = new JButton("Novo Artigo");
			novoPublicationButton.setSize(new java.awt.Dimension(80, 22));
			novoPublicationButton.setLocation(new java.awt.Point(0, 0));
			novoPublicationButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_add.png")));
			novoPublicationButton.setEnabled(true);
			novoPublicationButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoPublication();
				}				
			});
		}
		return novoPublicationButton;
	}

	protected JButton getEditarPublicationButton() {

		if (editarPublicationButton == null) {
			editarPublicationButton = new JButton("Editar a Artigo");
			editarPublicationButton.setSize(new java.awt.Dimension(80, 22));
			editarPublicationButton.setLocation(new java.awt.Point(0, 22));
			editarPublicationButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_edit.png")));
			editarPublicationButton.setEnabled(false);
			editarPublicationButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					try {
						{
							Publication p = getSelectedPublication();
							if (p == null) return;
							AdapitVirtualFrame.getInstance().editarPublication(p);
						}
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}				
			});
		}
		return editarPublicationButton;
	}

	protected JButton getRemoverButton() {
		if (removerButton == null) {
			removerButton = new JButton("Apagar a Artigo");
			removerButton.setSize(new java.awt.Dimension(80, 22));
			removerButton.setLocation(new java.awt.Point(0, 44));
			removerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/package_delete.png")));
			removerButton.setEnabled(false);
			removerButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					try {
						int row = getBaseTable().getSelectedRow();
						if (row > -1){
							Publication p = getSelectedPublication();
							if (p == null) return;
							//String desc = p.getDescricao();
							//if (desc.length() > 20) desc = desc.substring(20);
							int resp = JOptionPane.showConfirmDialog(PublicationListForm.this, "Apagar este artigo implicará em apagar todo histório deste!"+'\n'+"Você quer mesmo assim apagar este publication?", "Remover artigo", JOptionPane.YES_NO_OPTION);
							if (resp == JOptionPane.YES_OPTION){
								try {									
									RemotePublicationService.getInstance().deleteById(p.getId());
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
	
	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(484, 508));
				gui.add(new PublicationListForm(), java.awt.BorderLayout.CENTER);
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
			getColumnModel().getColumn(0).setPreferredWidth(10);
			getColumnModel().getColumn(1).setPreferredWidth(150);
			getColumnModel().getColumn(2).setPreferredWidth(240);
			
		}

		public void updateTable() {
			getRemoverButton().setEnabled(false);
			getEditarPublicationButton().setEnabled(false);
			
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][7];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Publication) {
						com.adapit.portal.entidades.Publication publication = (com.adapit.portal.entidades.Publication) obj;
						
						try{
							values[i][0] = publication.getId();
							values[i][1] = publication.getTitulo();
							values[i][2] = publication.getResumo();
							//values[i][3] = publication.getResumo();							
						}catch(Exception ex){
							ex.printStackTrace();
						}finally{
							
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(10);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				getColumnModel().getColumn(2).setPreferredWidth(240);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(10);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				getColumnModel().getColumn(2).setPreferredWidth(240);
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

			Class types[] = new java.lang.Class[] { Integer.class, String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Cod",
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