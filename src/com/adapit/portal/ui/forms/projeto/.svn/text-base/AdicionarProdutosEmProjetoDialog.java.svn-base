package com.adapit.portal.ui.forms.projeto;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.AlternateRowHighlighter;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.services.local.LocalCategoriaService;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class AdicionarProdutosEmProjetoDialog extends JDialog {

	private JPanel jContentPane = null;
	private JPanel produtosTablePanel = null;
	private JPanel buttonsPanel = null;
	private JButton lotearButton = null;
	private JButton cancelarButton = null;
	private JPanel tablePanel = null;
	private JPanel titlePanel = null;
	private JLabel titleLabel = null;
	private JScrollPane tableScrollPane = null;
	private JTable produtosTable = null;
	private List elements;
	private JLabel leilaoLabel = null;
	@SuppressWarnings("unused")
	private JComboBox leilaoComboBox = null;
	private JLabel numeroLoteLabel = null;
	private JComboBox numeroLoteComboBox = null;
	/**
	 * @param owner
	 */
	public AdicionarProdutosEmProjetoDialog(Frame owner,List elements, boolean asSelection) {
		super(owner);
		this.asSelection = asSelection;
		this.elements = elements;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(460, 320);
		setModal(true);
		//setUndecorated(true);
		
		this.setTitle("Lotear Produtos");
		this.setContentPane(getJContentPane());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setLocation(UIUtil.getScreenCenter(this));
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			numeroLoteLabel = new JLabel();
			numeroLoteLabel.setBounds(new Rectangle(268, 26, 87, 20));
			numeroLoteLabel.setText("Número do Lote:");
			leilaoLabel = new JLabel();
			leilaoLabel.setBounds(new Rectangle(8, 26, 44, 20));
			leilaoLabel.setText("Leilão:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);//new BorderLayout());
			jContentPane.add(getProdutosTablePanel(), BorderLayout.CENTER);
			jContentPane.add(getButtonsPanel(), BorderLayout.SOUTH);
			jContentPane.add(getTitlePanel(), BorderLayout.NORTH);
			jContentPane.add(leilaoLabel, null);
			jContentPane.add(numeroLoteLabel, null);
			jContentPane.add(getNumeroLoteComboBox(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes produtosTablePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getProdutosTablePanel() {
		if (produtosTablePanel == null) {
			produtosTablePanel = new JPanel();
			produtosTablePanel.setLayout(new BorderLayout());
			produtosTablePanel.setBounds(new Rectangle(7, 55, 438, 193));
			produtosTablePanel.add(getTablePanel(), BorderLayout.CENTER);
		}
		return produtosTablePanel;
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			
			buttonsPanel = new JPanel();
			buttonsPanel.setBounds(0, 250, 452, 35);
			buttonsPanel.setLayout(new FlowLayout());
			buttonsPanel.add(getLotearButton());
			buttonsPanel.add(getCancelarButton());
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes lotearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLotearButton() {
		if (lotearButton == null) {
			lotearButton = new JButton();
			lotearButton.setText("Lotear");
			lotearButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					Session s = LocalServicesUtility.getInstance().openSession();
					try{						
						int rows = elements.size();
						for (int i=0; i < rows; i++){							
							Boolean b = (Boolean) produtosTable.getValueAt(i,0);
							if (b.booleanValue()){
								s.beginTransaction();
								@SuppressWarnings("unused")
								ComercialSolution p = (ComercialSolution) elements.get(i);
								@SuppressWarnings("unused")
								Projeto lote = (Projeto) loteList.get(numeroLoteComboBox.getSelectedIndex());
								//ItemProjeto ip = p.getItemProjeto();
								//s.load(ip, ip.getId());
								//ip.setProjeto(lote);
								//lote.getItensProjeto().add(ip);
								s.flush();
								s.getTransaction().commit();
							}
						}						
					}catch(Exception ex){
						ex.printStackTrace();
						s.getTransaction().rollback();
					}finally{
						if (s != null) s.close();
					}
					dispose();
				}
			});
		}
		return lotearButton;
	}

	/**
	 * This method initializes cancelarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancelarButton() {
		if (cancelarButton == null) {
			cancelarButton = new JButton();
			cancelarButton.setText("Cancelar");
			cancelarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					dispose();
				}
			});
		}
		return cancelarButton;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTablePanel() {
		if (tablePanel == null) {
			tablePanel = new JPanel();
			tablePanel.setLayout(new BorderLayout());
			tablePanel.add(getTableScrollPane(), BorderLayout.CENTER);
		}
		return tablePanel;
	}

	/**
	 * This method initializes titlePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTitlePanel() {
		if (titlePanel == null) {
			titleLabel = new JLabel();
			
			titleLabel.setText("Selecione os produtos a serem loteados e clique em Lotear");
			titleLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
			titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
			titlePanel = new JPanel();
			titlePanel.setBounds(0, 2, 452, 22);
			titlePanel.setLayout(new BorderLayout());
			titlePanel.add(titleLabel, BorderLayout.NORTH);
		}
		return titlePanel;
	}

	/**
	 * This method initializes tableScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTableScrollPane() {
		if (tableScrollPane == null) {
			tableScrollPane = new JScrollPane();
			tableScrollPane.setViewportView(getProdutosTable());
		}
		return tableScrollPane;
	}

	/**
	 * This method initializes produtosTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getProdutosTable() {
		if (produtosTable == null) {
			produtosTable = new BaseTable();
		}
		return produtosTable;
	}
	
	private class BaseTable extends JXTable {

		public BaseTable() {
			super();
			this.setModel(new BaseTableModel(null));
			updateTable();
			initialize();
		}
		
		@SuppressWarnings("deprecation")
		private void initialize(){
			Highlighter highlighters = new AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		public void updateTable() {
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof ComercialSolution) {
						ComercialSolution produto = (ComercialSolution) obj;
						if (asSelection) values[i][0] = new Boolean(true);
						if (produto.getCategoria() != null){
							try {
								Categoria c = LocalCategoriaService.getInstance().getCategoriaById(produto.getCategoria().getId());
								produto.setCategoria(c);
								values[i][1] = produto.getCategoria().getNome();
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
						values[i][2] = produto.getDescricao();
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(30);
				getColumnModel().getColumn(1).setPreferredWidth(80);
				getColumnModel().getColumn(2).setPreferredWidth(200);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(30);
				getColumnModel().getColumn(1).setPreferredWidth(80);
				getColumnModel().getColumn(2).setPreferredWidth(200);
				updateUI();
			}
		}

		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] {Boolean.class, String.class, String.class};

			boolean canEdit[] = new boolean[] { true, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Lotear?", "Categoria","Descrição" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		}

	}


	@SuppressWarnings("unused")
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	@SuppressWarnings("unused")
	private List leilaoList;  //  @jve:decl-index=0:
	@SuppressWarnings("unused")
	private int idLeilao;
	private List loteList;
	

	/**
	 * This method initializes numeroLoteComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getNumeroLoteComboBox() {
		if (numeroLoteComboBox == null) {
			numeroLoteComboBox = new JComboBox();
			numeroLoteComboBox.setBounds(new Rectangle(358, 26, 86, 20));
		}
		return numeroLoteComboBox;
	}
	
	private boolean asSelection;

	public void setAsSelection(boolean b) {
		asSelection = b;
	}

	public boolean isAsSelection(){
		return asSelection;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
