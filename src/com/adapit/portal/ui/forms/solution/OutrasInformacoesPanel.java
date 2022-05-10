package com.adapit.portal.ui.forms.solution;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.CommercialSolutionDetailTab;
import com.adapit.portal.entidades.CommercialSolutionDetailTabPK;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.HtmlEditorPanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import java.awt.Dimension;
@SuppressWarnings({"serial","unchecked"})
public class OutrasInformacoesPanel extends JPanel{

	private ComercialSolution sol;  //  @jve:decl-index=0:


	private JLabel detailsListLabel = null;

	private JScrollPane tabDetailsScrollPane = null;

	private JButton addTabDetailButton = null;

	private JButton editTabDetailButton = null;

	private JButton removeTabDetailButton = null;

	private JComboBox tabDetailNameComboBox = null;

	private JLabel tabDetailNameLabel = null;

	private JLabel linguagemLabel = null;

	private JComboBox linguagemComboBox = null;

	//private JScrollPane detailValueScrollPane = null;

	private JTextPane detailValueTextPane = null;


	
	private JButton updateTabButton = null;

	private JButton clearTabButton = null;
	public OutrasInformacoesPanel(){
		initialize();
		getUpdateButton();
	}
	
	private void initialize() {
		try{
			setLayout(new BorderLayout());		
			this.setSize(new Dimension(800, 800));
			
			add(getTopPanel(), BorderLayout.NORTH);
			add(getApresentacaoEditor(), BorderLayout.CENTER);
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private JPanel topPanel;
	
	private JPanel getTopPanel(){
		if(topPanel == null){
			topPanel = new JPanel();
			topPanel.setLayout(null);
			topPanel.setPreferredSize(new Dimension(653,250));
			tabIndexLabel = new JLabel();
			tabIndexLabel.setBounds(new Rectangle(394, 167, 181, 20));
			tabIndexLabel.setText("Índice de Publicação na Página");
			tabNameLabel = new JLabel();
			tabNameLabel.setBounds(new Rectangle(12, 198, 129, 19));
			tabNameLabel.setText("Nome do item na Web");
			linguagemLabel = new JLabel();
			linguagemLabel.setBounds(new Rectangle(504, 65, 122, 22));
			linguagemLabel.setText("Linguagem:");
			tabDetailNameLabel = new JLabel();
			tabDetailNameLabel.setBounds(new Rectangle(503, 11, 122, 19));
			tabDetailNameLabel.setText("Nome da Aba/Detalhe");
			detailsListLabel = new JLabel();
			detailsListLabel.setBounds(new Rectangle(11, 10, 141, 22));
			detailsListLabel.setText("Lista de Detalhes de Abas:");
			
			topPanel.add(detailsListLabel, null);
			topPanel.add(getTabDetailsScrollPane(), null);
			topPanel.add(getAddTabDetailButton(), null);
			topPanel.add(getEditTabDetailButton(), null);
			topPanel.add(getRemoveTabDetailButton(), null);
			topPanel.add(getTabDetailNameComboBox(), null);
			topPanel.add(tabDetailNameLabel, null);
			topPanel.add(linguagemLabel, null);
			topPanel.add(getLinguagemComboBox(), null);
			
			topPanel.add(getUpdateButton(), null);
			topPanel.add(getLimparButton(), null);
			topPanel.add(getRefreshTabTableButton(), null);			
			topPanel.add(getDetailNameTextField(), null);
			topPanel.add(tabNameLabel, null);
			topPanel.add(getPublishCheckBox(), null);
			topPanel.add(getIndexTextField(), null);
			topPanel.add(tabIndexLabel, null);
			topPanel.add(getFilterKindComboBox(), null);
		}
		return topPanel;
	}

	/**
	 * This method initializes tabDetailsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTabDetailsScrollPane() {
		if (tabDetailsScrollPane == null) {
			tabDetailsScrollPane = new JScrollPane();
			tabDetailsScrollPane.setBounds(new Rectangle(10, 35, 405, 124));
			tabDetailsScrollPane.setViewportView(getBaseTable());
			//tabDetailsScrollPane.add(getBaseTable());
		}
		return tabDetailsScrollPane;
	}

	/**
	 * This method initializes addTabDetailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddTabDetailButton() {
		if (addTabDetailButton == null) {
			addTabDetailButton = new JButton();
			addTabDetailButton.setBounds(new Rectangle(420, 10, 80, 22));
			addTabDetailButton.setText("Adicionar");
			addTabDetailButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CommercialSolutionDetailTab tab = new CommercialSolutionDetailTab();
					tab.setDetail("");
					getDetailValueTextPane().setText("");
					tab.setCommercial_solution_id(sol.getId());
					tab.setDetail_tab_name((String)tabDetailNameComboBox.getSelectedItem());
					tab.setDetail_text_language((String)linguagemComboBox.getSelectedItem());
					try {
						RemoteServicesUtility.getInstance().save(tab);
						currentTab = tab;
						updateTabButton.setEnabled(true);
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					getBaseTable().updateTable();
				}
			});
		}
		return addTabDetailButton;
	}

	/**
	 * This method initializes editTabDetailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditTabDetailButton() {
		if (editTabDetailButton == null) {
			editTabDetailButton = new JButton();
			editTabDetailButton.setBounds(new Rectangle(420, 61, 80, 24));
			editTabDetailButton.setText("Editar");
			editTabDetailButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						currentTab = (CommercialSolutionDetailTab) elements.get(row);
						detailValueTextPane.setText(currentTab.getDetail());
						getApresentacaoEditor().updateByHtml();
						detailNameTextField.setText(currentTab.getDetailName());
						indexTextField.setText(""+currentTab.getIndex());
						publishCheckBox.setSelected(currentTab.isToPublish());
						updateTabButton.setEnabled(true);
						defineCss();
					}
				}
			});
		}
		return editTabDetailButton;
	}
	
	private void defineCss(){
		if(apresentacaoEditor.getCss() == null){
			if(sol != null && sol.getId()>0){
				try {
					CssDefinition cdef = 
						RemoteComercialSolutionService.getInstance().
						getCssDefinition(sol);
					if(cdef != null)
						apresentacaoEditor.setCss(cdef.getStyle());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method initializes removeTabDetailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoveTabDetailButton() {
		if (removeTabDetailButton == null) {
			removeTabDetailButton = new JButton();
			removeTabDetailButton.setBounds(new Rectangle(420, 87, 80, 24));
			removeTabDetailButton.setText("Remover");
			removeTabDetailButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateTabButton.setEnabled(false);
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						currentTab = (CommercialSolutionDetailTab) elements.get(row);
						CommercialSolutionDetailTabPK pk = new CommercialSolutionDetailTabPK();
						pk.setCommercial_solution_id(currentTab.getCommercial_solution_id());
						pk.setDetail_tab_name(currentTab.getDetail_tab_name());
						pk.setDetail_text_language(currentTab.getDetail_text_language());
						try {
							RemoteServicesUtility.getInstance().deleteById(CommercialSolutionDetailTab.class, pk);
							AdapitVirtualFrame.getInstance().showOperationSucess();
							getDetailValueTextPane().setText("");
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						getBaseTable().updateTable();
					}
				}
			});
		}
		return removeTabDetailButton;
	}

	/**
	 * This method initializes tabDetailNameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTabDetailNameComboBox() {
		if (tabDetailNameComboBox == null) {
			tabDetailNameComboBox = new JComboBox();
			tabDetailNameComboBox.setBounds(new Rectangle(420, 34, 205, 22));
			tabDetailNameComboBox.setEditable(true);
			tabDetailNameComboBox.addItem("Benefícios");
			tabDetailNameComboBox.addItem("Características");
			tabDetailNameComboBox.addItem("Diferenciais");
			tabDetailNameComboBox.addItem("Público-Alvo");
			tabDetailNameComboBox.addItem("Portifólio");
			tabDetailNameComboBox.addItem("Carta de Apresentação");
			
		}
		return tabDetailNameComboBox;
	}

	/**
	 * This method initializes linguagemComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getLinguagemComboBox() {
		if (linguagemComboBox == null) {
			linguagemComboBox = new JComboBox();
			linguagemComboBox.setBounds(new Rectangle(504, 88, 121, 22));
			linguagemComboBox.setEditable(true);
			for(int i=0; i< langs.length;i++)
				linguagemComboBox.addItem(langs[i]);
		}
		return linguagemComboBox;
	}
	
	private static final String langs[]={
		"PT-BR","PT-PR","EN-US"
	};  //  @jve:decl-index=0:

	/**
	 * This method initializes detailValueScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	/*private JScrollPane getDetailValueScrollPane() {
		if (detailValueScrollPane == null) {
			detailValueScrollPane = new JScrollPane();
			detailValueScrollPane.setBounds(new Rectangle(10, 142, 618, 318));
			detailValueScrollPane.setViewportView(getDetailValueTextPane());
		}
		return detailValueScrollPane;
	}*/
	
	private HtmlEditorPanel apresentacaoEditor;
	private HtmlEditorPanel getApresentacaoEditor() {
		if (apresentacaoEditor == null) {
			getDetailValueTextPane();
			apresentacaoEditor = new HtmlEditorPanel((JTextComponent)detailValueTextPane,apresEventListener,null);
			apresentacaoEditor.setBounds(new Rectangle(10, 223, 618, 294));/*.setBounds(new Rectangle(5, 103, 619, 178));*/
		}
		return apresentacaoEditor;
	}

	private HtmlEditorEventListener apresEventListener = null;
	//apresEventListener = new HtmlEditorEventListener(solutionAbractTextPane,AdapitVirtualFrame.getInstance(),"Apresentação do Sistema");


	/**
	 * This method initializes detailValueTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getDetailValueTextPane() {
		if (detailValueTextPane == null) {
			detailValueTextPane = new JTextPane();
			String texto="";
			if(currentTab != null)try {
				texto = currentTab.getDetail_tab_name();
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
			apresEventListener = new HtmlEditorEventListener(detailValueTextPane,AdapitVirtualFrame.getInstance(),"Texto de " + texto);
		}
		return detailValueTextPane;
	}



	

	CommercialSolutionDetailTab currentTab = null;
	/**
	 * This method initializes updateButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getUpdateButton() {
		if (updateTabButton == null) {
			updateTabButton = new JButton();
			updateTabButton.setBounds(new Rectangle(10, 167, 128, 22));
			updateTabButton.setEnabled(false);
			updateTabButton.setText("Atualizar");
			updateTabButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (currentTab == null) return;
					
					//tab.setCommercial_solution_id(sol.getId());
					//tab.setDetail_tab_name((String)tabDetailNameComboBox.getSelectedItem());
					//tab.setDetail_text_language((String)linguagemComboBox.getSelectedItem());
					try {
						CommercialSolutionDetailTab tab = currentTab;
						tab.setDetail(getDetailValueTextPane().getText());
						tab.setDetailName(getDetailNameTextField().getText());
						int index = Integer.parseInt(getIndexTextField().getText());
						tab.setIndex(index);
						tab.setToPublish(publishCheckBox.isSelected());
						RemoteServicesUtility.getInstance().update(tab);
						currentTab = tab;
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					getBaseTable().updateTable();
				}
			});
		}
		return updateTabButton;
	}

	/**
	 * This method initializes limparButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLimparButton() {
		if (clearTabButton == null) {
			clearTabButton = new JButton();
			clearTabButton.setBounds(new Rectangle(156, 167, 127, 22));
			clearTabButton.setText("Cancelar");
			clearTabButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					currentTab=null;
					getDetailValueTextPane().setText("");
					updateTabButton.setEnabled(false);
					getDetailNameTextField().setText("");
					getIndexTextField().setText(""+10);
					getPublishCheckBox().setSelected(false);
				}
			});
		}
		return clearTabButton;
	}
	
	private BaseTable baseTable;
	
	public BaseTable getBaseTable(){
		if (baseTable == null){
			baseTable = new BaseTable();
		}
		return baseTable;
	}
	
	public List elements;

	private JButton refreshTabTableButton = null;


	private JTextField detailNameTextField = null;


	private JLabel tabNameLabel = null;


	private JCheckBox publishCheckBox = null;


	private JTextField indexTextField = null;


	private JLabel tabIndexLabel = null;


	private JComboBox filterKindComboBox = null;
	
	public class BaseTable extends JTable {
		
		public BaseTable() {
			super();
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			initialize();
		}

				
		@SuppressWarnings("deprecation")
		private void initialize(){
			//Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			//setHighlighters(highlighters);
			try {
				getColumnModel().getColumn(0).setPreferredWidth(240);
				getColumnModel().getColumn(1).setPreferredWidth(40);
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}

		public void updateTable() {
			try {
				String lang =(String) getFilterKindComboBox().getSelectedItem();
				if(lang.equals(""))
					lang=null;
				elements = RemoteComercialSolutionService.getInstance().listTabsByCommercialSolutionId(sol.getId(),
						lang);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				System.out.println(ne);
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][5];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof CommercialSolutionDetailTab) {
						CommercialSolutionDetailTab news = (CommercialSolutionDetailTab) obj;
						
						try{
							values[i][0] = news.getDetail_tab_name();
							values[i][1] = news.getDetail_text_language();
							values[i][2] = news.getIndex();
							values[i][3] = news.isToPublish();
						}catch(Exception ex){
							ex.printStackTrace();
						}finally{
							
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(240);
				getColumnModel().getColumn(1).setPreferredWidth(40);
				getColumnModel().getColumn(2).setPreferredWidth(30);
				getColumnModel().getColumn(3).setPreferredWidth(10);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(240);
				getColumnModel().getColumn(1).setPreferredWidth(40);
				getColumnModel().getColumn(2).setPreferredWidth(30);
				getColumnModel().getColumn(3).setPreferredWidth(10);
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

			}

		}
		
		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class,  String.class, Integer.class, Boolean.class };

			boolean canEdit[] = new boolean[] { true, false, false, false};

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Identificador da Aba","Lin","Ind","Pub"});
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		}

	}

	/**
	 * This method initializes refreshTabTableButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshTabTableButton() {
		if (refreshTabTableButton == null) {
			refreshTabTableButton = new JButton();
			refreshTabTableButton.setBounds(new Rectangle(278, 9, 22, 22));
			refreshTabTableButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshTabTableButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getBaseTable().updateTable();
				}
			});
		}
		return refreshTabTableButton;
	}

	public ComercialSolution getSol() {
		return sol;
	}

	public void setSol(ComercialSolution sol) {
		this.sol = sol;
	}

	/**
	 * This method initializes detailNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDetailNameTextField() {
		if (detailNameTextField == null) {
			detailNameTextField = new JTextField();
			detailNameTextField.setBounds(new Rectangle(148, 197, 475, 20));
		}
		return detailNameTextField;
	}

	/**
	 * This method initializes publishCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getPublishCheckBox() {
		if (publishCheckBox == null) {
			publishCheckBox = new JCheckBox();
			publishCheckBox.setBounds(new Rectangle(295, 167, 96, 21));
			publishCheckBox.setText("Publicar?");
		}
		return publishCheckBox;
	}

	/**
	 * This method initializes indexTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getIndexTextField() {
		if (indexTextField == null) {
			indexTextField = new JTextField();
			indexTextField.setBounds(new Rectangle(587, 167, 33, 20));
		}
		return indexTextField;
	}

	/**
	 * This method initializes filterKindComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getFilterKindComboBox() {
		if (filterKindComboBox == null) {
			filterKindComboBox = new JComboBox();
			filterKindComboBox.setBounds(new Rectangle(157, 10, 116, 22));
			filterKindComboBox.setEditable(true);
			filterKindComboBox.addItem("");
			for(int i=0; i< langs.length;i++)
				filterKindComboBox.addItem(langs[i]);
		}
		return filterKindComboBox;
	}
}  //  @jve:decl-index=0:visual-constraint="13,-14"
