package com.adapit.portal.ui.forms.treinamento.contato;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


import org.jdesktop.swingx.JXTable;

import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.services.remote.RemoteContatoService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings("serial")
public class ProcessoTreinamentoListForm extends JPanel {

	private JPanel filtersPanel;

	private JPanel filterFieldAndSearchButtonPanel;

	private JTextField contatoTextField;
	
	private ContatoTreinamento contato = new ContatoTreinamento();

	private JButton listarButton;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel buttonsContainer;

	private JButton listarTodasButton;

	private JButton editarButton = null;

	private JButton novaButton = null;

	private JButton apagarButton = null;

	public ProcessoTreinamentoListForm() {

		initialize();
	}

	private void initialize() {

		setSize(new java.awt.Dimension(460, 313));
		setLayout(null);
		add(getFiltersPanel());
		add(getBaseTableScrollPane());
		add(getButtonsContainer());
	}

	protected JPanel getFiltersPanel() {

		if (filtersPanel == null) {
			filtersPanel = new JPanel();
			filtersPanel.setBorder(javax.swing.BorderFactory
					.createTitledBorder(javax.swing.BorderFactory
							.createTitledBorder("Listar processos por contato")));
			filtersPanel.setSize(new Dimension(448, 77));
			filtersPanel.setLocation(new java.awt.Point(0, 0));
			filtersPanel.setLayout(null);
			filtersPanel.add(getFilterFieldAndSearchButtonPanel());
		}
		return filtersPanel;
	}

	protected JPanel getFilterFieldAndSearchButtonPanel() {

		if (filterFieldAndSearchButtonPanel == null) {
			filterFieldAndSearchButtonPanel = new JPanel();
			filterFieldAndSearchButtonPanel.setSize(new Dimension(430, 54));
			filterFieldAndSearchButtonPanel.setLocation(new java.awt.Point(10,
					15));
			filterFieldAndSearchButtonPanel.setLayout(null);
			filterFieldAndSearchButtonPanel.add(getContatoTextField());
			filterFieldAndSearchButtonPanel.add(getListarButton());
			filterFieldAndSearchButtonPanel.add(getListarTodasButton(), null);
		}
		return filterFieldAndSearchButtonPanel;
	}

	protected JTextField getContatoTextField() {

		if (contatoTextField == null) {
			contatoTextField = new JTextField();
			contatoTextField.setText("");
			contatoTextField.setSize(new Dimension(191, 24));
			contatoTextField.setLocation(new Point(10, 13));
			return contatoTextField;
		}
		return contatoTextField;
	}

	protected JButton getListarButton() {
		if (listarButton == null) {
			listarButton = new JButton("Listar");
			listarButton.setSize(new Dimension(70, 24));
			listarButton.setLocation(new Point(220, 13));
			listarButton.addActionListener(new ActionListener() {
					
			@Override
			public void actionPerformed(ActionEvent e) {
				listByNomeComarca();
			}
			});			
			
		}
		return listarButton;
	}

	protected JScrollPane getBaseTableScrollPane() {
		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new Dimension(440, 171));
			baseTableScrollPane.setLocation(new Point(5, 80));

			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	protected BaseTable getBaseTable() {

		if (baseTable == null) {
			baseTable = new BaseTable();
			baseTable.setSize(new java.awt.Dimension(446, 194));
			return baseTable;
		}
		return baseTable;
	}

	protected JPanel getButtonsContainer() {

		if (buttonsContainer == null) {
			buttonsContainer = new JPanel();
			buttonsContainer.setSize(new java.awt.Dimension(446, 34));
			buttonsContainer.setLocation(new java.awt.Point(0, 253));
			buttonsContainer.setLayout(new java.awt.FlowLayout());
			buttonsContainer.add(getNovaButton(), null);
			buttonsContainer.add(getEditarButton(), null);
			buttonsContainer.add(getApagarButton(), null);
		}
		return buttonsContainer;
	}
	
	
	@SuppressWarnings("unchecked")
	private void listAllProcessos() {
		if (contato.getId() != 0)return;		
		try {			
			List list = RemoteContatoService.getInstance().listAllProcessos(0);			
			if (list != null && list.size() > 0){
				ArrayList arr = new ArrayList();
				arr.addAll(list);
				baseTable.setElements(arr);
				baseTable.updateTable();
			}	
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(ProcessoTreinamentoListForm.this,
					"Erro ao listar contatos");
		} 
		
	}
	@SuppressWarnings("unchecked")
	private void listByNomeComarca() {
		String nomeComarca = (String) contatoTextField.getText(); 		
		try {			
			List list = RemoteContatoService.getInstance().listProcessosByNomeContato(nomeComarca);	
			ArrayList arr = new ArrayList();
			arr.addAll(list);
			baseTable.setElements(arr);
			baseTable.updateTable();		
			
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(ProcessoTreinamentoListForm.this,
					"Erro ao buscar o contato");
		}
		
		
	}
	
	
	

	protected JButton getListarTodasButton() {
		if (listarTodasButton == null) {
			listarTodasButton = new JButton("Listar Todos");
			listarTodasButton.setBounds(new Rectangle(312, 13, 105, 24));		
			
			listarTodasButton.addActionListener(new ActionListener() {					
				@Override
				public void actionPerformed(ActionEvent e) {
					listAllProcessos();
				}
			});
			
		}
		return listarTodasButton;
	}

	/**
	 * This method initializes editarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarButton() {
		if (editarButton == null) {
			editarButton = new JButton();
			editarButton.setText("Editar Contato");
		}
		return editarButton;
	}

	/**
	 * This method initializes novaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovaButton() {
		if (novaButton == null) {
			novaButton = new JButton();
			novaButton.setText("Novo Contato");
		}
		return novaButton;
	}

	/**
	 * This method initializes apagarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getApagarButton() {
		if (apagarButton == null) {
			apagarButton = new JButton();
			apagarButton.setText("Apagar Contato");
		}
		return apagarButton;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(460, 313));
				gui.add(new ProcessoTreinamentoListForm(), java.awt.BorderLayout.CENTER);
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
			this.setModel(new BaseTableModel(null));
			this
					.addPropertyChangeListener(new BaseTablePropertyChangeListener());
		}

		public BaseTable(List elements) {
			super();
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
		}


		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][5];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.ContatoProcessoTreinamento) {
						com.adapit.portal.entidades.ContatoProcessoTreinamento procJud = (com.adapit.portal.entidades.ContatoProcessoTreinamento) obj;
						if (procJud.getContatoTreinamento() != null) values[i][0] = procJud.getContatoTreinamento().getNome();
						values[i][1] = procJud.getTecnologias();
						values[i][2] = procJud.getLocalRealizacao();
						values[i][3] = procJud.getRequerente();
						values[i][4] = AdapitVirtualFrame.getInstance().format(procJud.getDataPlanejada());
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				BaseTable jt = (BaseTable) e.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.ContatoProcessoTreinamento) {
							com.adapit.portal.entidades.ContatoProcessoTreinamento procJud = (com.adapit.portal.entidades.ContatoProcessoTreinamento) obj;
							
							if (col == 3)
								procJud.setTecnologias(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 4)
								procJud.setLocalRealizacao(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 5)
								procJud.setRequerente(((java.lang.String) jt
										.getValueAt(row, col)));
						
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, String.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					false };

			public BaseTableModel(Object[][] values) {

				super(
						values,
						new String[] {"Contato","Tecnologias","Local",
								messages
										.getMessage("com.adapit.portal.layout.ComarcaListForm.Requerente"),
								"Data" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}

}