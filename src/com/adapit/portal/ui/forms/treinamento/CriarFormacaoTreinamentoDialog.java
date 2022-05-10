package com.adapit.portal.ui.forms.treinamento;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Iterator;
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

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.remote.RemoteCategoriaService;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class CriarFormacaoTreinamentoDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel solucoesTablePanel = null;
	private JPanel buttonsPanel = null;
	private JButton criarButton = null;
	private JButton cancelarButton = null;
	private JPanel tablePanel = null;
	private JPanel titlePanel = null;
	private JLabel titleLabel = null;
	private JScrollPane tableScrollPane = null;
	private JTable comercialSolutionsTable = null;
	
	@SuppressWarnings("unchecked")
	private List elements;
	private JLabel leilaoLabel = null;
	private JComboBox turmaComboBox = null;
	private JLabel numeroLoteLabel = null;
	private JComboBox numeroLoteComboBox = null;
	/**
	 * @param owner
	 */
	@SuppressWarnings("unchecked")
	public CriarFormacaoTreinamentoDialog(Frame owner,List elements, boolean asSelection) {
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
		
		this.setTitle("Especificar Formações de Treinamentos");
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
			numeroLoteLabel.setText("Código da Formação:");
			leilaoLabel = new JLabel();
			leilaoLabel.setBounds(new Rectangle(8, 26, 44, 20));
			leilaoLabel.setText("Turma:");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);//new BorderLayout());
			jContentPane.add(getSolucoesTablePanel(), BorderLayout.CENTER);
			jContentPane.add(getButtonsPanel(), BorderLayout.SOUTH);
			jContentPane.add(getTitlePanel(), BorderLayout.NORTH);
			jContentPane.add(leilaoLabel, null);
			jContentPane.add(getTurmaComboBox(), null);
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
	private JPanel getSolucoesTablePanel() {
		if (solucoesTablePanel == null) {
			solucoesTablePanel = new JPanel();
			solucoesTablePanel.setLayout(new BorderLayout());
			solucoesTablePanel.setBounds(new Rectangle(7, 55, 438, 193));
			solucoesTablePanel.add(getTablePanel(), BorderLayout.CENTER);
		}
		return solucoesTablePanel;
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
			buttonsPanel.add(getCriarButton());
			buttonsPanel.add(getCancelarButton());
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes lotearButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCriarButton() {
		if (criarButton == null) {
			criarButton = new JButton();
			criarButton.setText("Criar Formação");
			criarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
					try{						
						int rows = elements.size();
						ArrayList<TrainingSolution> arr = new ArrayList<TrainingSolution>();
						for (int i=0; i < rows; i++){							
							Boolean b = (Boolean) comercialSolutionsTable.getValueAt(i,0);
							if (b.booleanValue()){
								TrainingSolution p = (TrainingSolution) elements.get(i);	
								arr.add(p);
							}
						}	
						
						TrainingSolution[] prods = (TrainingSolution[]) arr.toArray();
						
						RemoteTreinamentoService.getInstance().createScheduledTrainingWithTrainingSolutions(prods,(Treinamento) treinamentoList.get(numeroLoteComboBox.getSelectedIndex()));
					}catch(Exception ex){
						ex.printStackTrace();
					}
					/*Session s = LocalServicesUtility.getInstance().openSession();
					try{						
						int rows = elements.size();
						for (int i=0; i < rows; i++){							
							Boolean b = (Boolean) produtosTable.getValueAt(i,0);
							if (b.booleanValue()){
								s.beginTransaction();
								Produto p = (Produto) elements.get(i);
								Lote lote = (Lote) loteList.get(numeroLoteComboBox.getSelectedIndex());
								ItemProduto ip = (ItemProduto) s.createQuery("select ip from ItemProduto where ip.lote.id=:idlote and ip.produto.id=:idprod")
									.setParameter("idlote", lote.getId())
									.setParameter("idprod", p.getId()).uniqueResult();
								
								ip.setLote(lote);								
								lote.getItensProduto().add(ip);
								
								ip.setProduto(p);
								p.getItensProduto().add(ip);
								
								s.flush();
								s.getTransaction().commit();
							}
						}						
					}catch(Exception ex){
						ex.printStackTrace();
						s.getTransaction().rollback();
					}finally{
						if (s != null) s.close();
					}*/
					dispose();
				}
			});
		}
		return criarButton;
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
			
			titleLabel.setText("Selecione os produtos a serem loteados e clique em 'Criar Formação'");
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
			tableScrollPane.setViewportView(getComercialSolutionsTable());
		}
		return tableScrollPane;
	}

	/**
	 * This method initializes produtosTable	
	 * 	
	 * @return javax.swing.JTable	
	 */
	private JTable getComercialSolutionsTable() {
		if (comercialSolutionsTable == null) {
			comercialSolutionsTable = new BaseTable();
		}
		return comercialSolutionsTable;
	}
	@SuppressWarnings({ "unchecked", "serial" })
	private class BaseTable extends JXTable {

		public BaseTable() {
			super();
			this.setModel(new BaseTableModel(null));
			updateTable();
			initialize();
		}
		
		@SuppressWarnings("deprecation")
		private void initialize(){
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
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
					if (obj instanceof TrainingSolution) {
						TrainingSolution solution = (TrainingSolution) obj;
						if (asSelection) values[i][0] = new Boolean(true);
						if (solution.getCategoria() != null){
							try {
								Categoria c = RemoteCategoriaService.getInstance().getCategoriaById(solution.getCategoria().getId());
								solution.setCategoria(c);
								values[i][1] = solution.getCategoria().getNome();
							} catch (Exception e) {
								e.printStackTrace();
							} 
						}
						values[i][2] = solution.getNome();
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
				super(values, new String[] {"Formação?", "Categoria","Nome" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		}

	}


	//private ResourceMessage messages = SpringResourceMessage.getInstance();
	@SuppressWarnings("unchecked")
	private List turmaList;  //  @jve:decl-index=0:
	private int idTurma;
	@SuppressWarnings("unchecked")
	private List treinamentoList;
	/**
	 * This method initializes leilaoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComboBox getTurmaComboBox() {
		if (turmaComboBox == null) {
			turmaComboBox = new JComboBox();
			turmaComboBox.setBounds(new Rectangle(56, 26, 205, 20));
			try{
				turmaList = RemoteTurmaService.getInstance().listTrainingClassesOnValidInterval();
				{	
					Iterator<TurmaTreinamento> it = turmaList.iterator();
					int i=0;
					while(it.hasNext()){
						TurmaTreinamento lei = it.next();
						if (i ==0) idTurma = lei.getId();
						turmaComboBox.addItem("Data do Treinamento: "+
								AdapitVirtualFrame.getInstance().format(lei.getDataTreinamento())
								);
						i++;
					}				
				}
				treinamentoList = RemoteTreinamentoService.getInstance().listAllScheduledTrainingByTurmaId(idTurma);
				{	
					Iterator<Treinamento> it = treinamentoList.iterator();
					while(it.hasNext()){
						Treinamento lot = it.next();
						getNumeroLoteComboBox().addItem(lot.getCodigo());
					}				
				}
				
			}catch(Exception ex){
				ex.printStackTrace();				
			}
			turmaComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						int selected = turmaComboBox.getSelectedIndex();
						TurmaTreinamento leilao = (TurmaTreinamento) turmaList.get(selected);
						int id = leilao.getId();
						getNumeroLoteComboBox().removeAllItems();
						try{
							treinamentoList = RemoteTreinamentoService.getInstance().listAllScheduledTrainingByTurmaId(id);
							{	
								Iterator<Treinamento> it = treinamentoList.iterator();
								while(it.hasNext()){
									Treinamento lot = it.next();
									getNumeroLoteComboBox().addItem(lot.getCodigo());
								}				
							}
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
				}
				
			});
		}
		return turmaComboBox;
	}
	/*private JComboBox getLeilaoComboBox() {
		if (leilaoComboBox == null) {
			leilaoComboBox = new JComboBox();
			leilaoComboBox.setBounds(new Rectangle(56, 26, 205, 20));
			Session s = LocalServicesUtility.getInstance().openSession();
			try{
				leilaoList = s.createQuery("from Leilao l where l.dataPresencialPrimeira > current_time or l.dataPresencialSegunda > current_time").list();
				{	
					Iterator<Leilao> it = leilaoList.iterator();
					int i=0;
					while(it.hasNext()){
						Leilao lei = it.next();
						if (i ==0) idLeilao = lei.getId();
						leilaoComboBox.addItem("1: "+
								LeilaoVirtualFrame.getInstance().format(lei.getDataPresencialPrimeira())
								+"      2: " +
								LeilaoVirtualFrame.getInstance().format(lei.getDataPresencialSegunda())
						);
						i++;
					}				
				}
				loteList = s.createQuery("from Lote l where l.leilao.id="+idLeilao).list();
				{	
					Iterator<Lote> it = loteList.iterator();
					while(it.hasNext()){
						Lote lot = it.next();
						getNumeroLoteComboBox().addItem(lot.getCodLote());
					}				
				}
				
			}catch(Exception ex){
				ex.printStackTrace();				
			}finally{
				if (s != null) s.close();
			}
			leilaoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						int selected = leilaoComboBox.getSelectedIndex();
						Leilao leilao = (Leilao) leilaoList.get(selected);
						int id = leilao.getId();
						getNumeroLoteComboBox().removeAllItems();
						Session s = LocalServicesUtility.getInstance().openSession();
						try{
							loteList = s.createQuery("from Lote l where l.leilao.id="+id).list();
							{	
								Iterator<Lote> it = loteList.iterator();
								while(it.hasNext()){
									Lote lot = it.next();
									getNumeroLoteComboBox().addItem(lot.getCodLote());
								}				
							}
						}catch(Exception ex){
							ex.printStackTrace();
						}finally{
							if (s == null) s.close();
						}
					}
				}
				
			});
		}
		return leilaoComboBox;
	}*/

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
