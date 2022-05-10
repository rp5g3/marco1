package com.adapit.portal.ui.forms.file;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.services.remote.RemoteFileService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.util.global.GlobalVariables;
import com.workcase.gui.utils.SpringResourceMessage;

public class FileFilterPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane fileScrollPane = null;
	private JButton listButton = null;
	private JPanel buttonsPanel = null;
	private JButton salvarServerButton = null;
	private JButton salvarLocalButton = null;
	private JButton removeButton = null;

	/**
	 * This is the default constructor
	 */
	public FileFilterPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(606, 386);
		this.setLayout(new BorderLayout());
		JPanel jp = new JPanel(new GridLayout(2,1));
		jp.add(getButtonsSouthPanel2());
		jp.add(getButtonsSouthPanel());
		this.add(jp, BorderLayout.SOUTH);
		this.add(getTopPanel(), BorderLayout.CENTER);
	}

	private JLabel resultadosLabel = new JLabel();
	private JPanel filesListPanel;
	
	private JPanel getFilesListPanel(){
		if(filesListPanel == null){
			filesListPanel = new JPanel();
			filesListPanel.setLayout(new BorderLayout());
			filesListPanel.setBounds(new Rectangle(14, 46, 574, 230));
			filesListPanel.add(resultadosLabel,BorderLayout.NORTH);
			filesListPanel.add(getFileScrollPane(),BorderLayout.CENTER);
		}
		return filesListPanel;
	}
	
	/**
	 * This method initializes fileScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getFileScrollPane() {
		if (fileScrollPane == null) {
			fileScrollPane = new JScrollPane();
			
			fileScrollPane.setViewportView(arquivosTable);
		}
		return fileScrollPane;
	}
	
	private BaseTable arquivosTable = new BaseTable();
	private JButton buscarArquivoButton = null;

	/**
	 * This method initializes listButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListButton() {
		if (listButton == null) {
			listButton = new JButton();
			listButton.setText("Filtrar Arquivos");
			listButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					list();
				}
			});
		}
		return listButton;
	}
	
	public void list(){
		try {
			List<Arquivo> arr = RemoteFileService.getInstance().listBy(fileNameTextField.getText(),(String)fileFormatComboBox.getSelectedItem());
			//List<Arquivo> arr = RemoteGenericService.getInstance().retrieveAll(Arquivo.class);
			arquivosTable.setElements(arr);
			arquivosTable.updateTable();
			if(arr == null || arr.size()==0){
				resultadosLabel.setText("Nenhum arquivo foi encontrado");
			}
			else if(arr.size()>1)
				resultadosLabel.setText(arr.size()+" arquivos foram encontrados");
			else resultadosLabel.setText(arr.size()+" arquivo encontrado");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(flowLayout);
			buttonsPanel.setBounds(new Rectangle(14, 11, 572, 33));
			buttonsPanel.add(getListButton(), null);
			buttonsPanel.add(getBuscarArquivoButton(), null);
			buttonsPanel.add(getLimparCamposButton(), null);
			buttonsPanel.add(getReportButton(), null);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes salvarServerButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSalvarServerButton() {
		if (salvarServerButton == null) {
			salvarServerButton = new JButton();
			salvarServerButton.setText("Salvar no Diretório do Servidor");
			salvarServerButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Arquivo arq = getSelectedArquivo();
						System.out.println(arq.getName());
						Component comp = FileFilterPanel.this;
						saveOnServerDir(arq, comp);
						list();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return salvarServerButton;
	}
	
	public static void saveOnServerDir(Arquivo arq, Component comp) throws Exception{		
		String dir = JOptionPane.showInputDialog(comp,"Informe o diretório após " + GlobalVariables.getWebappsDir(),
				"");
		if(dir != null){
			dir = (String) RemoteFileService.getInstance().saveFileToDirectory(arq, dir);
			JOptionPane.showMessageDialog(comp, "O arquivo foi salvo com sucesso no diretório:"
					+'\n'+dir);
		}
	}
	
	/*public static void main(String args[]){
		try {
			Arquivo arq = RemoteFileService.getInstance().getArquivoById(30);
			System.out.println(arq.getName());
			//arq.setId(30);
			salvarNoDiretorio(arq, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
*/
	/**
	 * This method initializes salvarLocalButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSalvarLocalButton() {
		if (salvarLocalButton == null) {
			salvarLocalButton = new JButton();
			salvarLocalButton.setText("Salvar no Diretório Local");
			//salvarLocalButton.setEnabled(false);
			salvarLocalButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Arquivo arq = getSelectedFile();
					try {
						String dir = (String) RemoteFileService.getInstance().saveFileToLocalDirectory(arq,FileFilterPanel.this);
						System.out.println(dir);
						AdapitVirtualFrame.getInstance().showOperationSucess(
								"Arquivo gravado com sucesso", dir);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return salvarLocalButton;
	}

	/**
	 * This method initializes removeButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoveButton() {
		if (removeButton == null) {
			removeButton = new JButton();
			removeButton.setText("Remover Arquivo do Servidor");
			removeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						Arquivo arq = getSelectedArquivo();
						RemoteFileService.getInstance().delete(arq);
						list();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return removeButton;
	}

	private ImportFile openFile =  ImportFile.getInstance();  //  @jve:decl-index=0:
	private JPanel buttonsSouthPanel2 = null;
	private JPanel topPanel = null;
	
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
			this.setModel(new BaseTableModel(null));
			initialize();
		}

		public BaseTable(List elements) {
			super();			
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			initialize();
		}
		
		@SuppressWarnings("deprecation")
		private void initialize(){
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			getColumnModel().getColumn(0).setPreferredWidth(10);
			getColumnModel().getColumn(1).setPreferredWidth(200);
			getColumnModel().getColumn(2).setPreferredWidth(30);
			getColumnModel().getColumn(3).setPreferredWidth(200);
			
		}

		public void updateTable() {
			
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][7];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof Arquivo) {
						Arquivo solution = (Arquivo) obj;						
						try{
							values[i][0] = solution.getId();
							values[i][1] = solution.getName();
							values[i][2] = solution.getFormat();
							values[i][3] = solution.getLocalPath();			
							
						}catch(Exception ex){
							ex.printStackTrace();
						}finally{
							
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(10);
				getColumnModel().getColumn(1).setPreferredWidth(200);
				getColumnModel().getColumn(2).setPreferredWidth(30);
				getColumnModel().getColumn(3).setPreferredWidth(200);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(10);
				getColumnModel().getColumn(1).setPreferredWidth(200);
				getColumnModel().getColumn(2).setPreferredWidth(30);
				getColumnModel().getColumn(3).setPreferredWidth(200);
				updateUI();
			}
		}

	
		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class,String.class, String.class, String.class};

			boolean canEdit[] = new boolean[] { false, false, false, false};

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {"Cod","Nome","Formato","URL (Localização do Arquivo no Servidor)" });
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
	 * This method initializes buscarArquivoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarArquivoButton() {
		if (buscarArquivoButton == null) {
			buscarArquivoButton = new JButton();
			buscarArquivoButton.setText("Buscar Arquivo Local e Adicionar no Repositório");
			buscarArquivoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						openFile.actionPerformed(e);
						ImportFile.Status status = openFile.getStatus();
						if(status != ImportFile.Status.Cancel){
							if(status == ImportFile.Status.Success){
								AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de Arquivo", "Arquivo adicionado com sucesso!");
								editRegister(openFile.getArquivo());
							}else{
								AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Arquivo", "O arquivo não foi adicionado!");
							}
						}
					} catch (Exception e1) {
						AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Arquivo", "O arquivo não foi adicionado!");
					}
				}
			});
		}
		return buscarArquivoButton;
	}
	
	public Arquivo getSelectedArquivo(){
		Arquivo arq = null;
		int row = arquivosTable.getSelectedRow();
		if(row>-1){
			arq = (Arquivo) arquivosTable.getElements().get(row);
		}
		return arq;
	}

	public Arquivo getSelectedFile() {
		return getSelectedArquivo();
	}

	public void editRegister(Arquivo arq) {
		ArrayList arr = new ArrayList();
		arr.add(arq);
		arquivosTable.setElements(arr);
		arquivosTable.updateTable();
		arquivosTable.setRowSelectionInterval(0, 0);
	}

	/**
	 * This method initializes buttonsSouthPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsSouthPanel2() {
		if (buttonsSouthPanel2 == null) {
			buttonsSouthPanel2 = new JPanel();
			buttonsSouthPanel2.setLayout(new FlowLayout());
			buttonsSouthPanel2.setBounds(new Rectangle(14, 280, 574, 83));
			buttonsSouthPanel2.add(getSalvarLocalButton(), null);
			buttonsSouthPanel2.add(getSalvarServerButton(), null);
			buttonsSouthPanel2.add(getRemoveButton(), null);
		}
		return buttonsSouthPanel2;
	}
	
	private JPanel buttonsSouthPanel;
	
	private JPanel getButtonsSouthPanel() {
		if (buttonsSouthPanel == null) {
			buttonsSouthPanel = new JPanel();
			buttonsSouthPanel.setLayout(new FlowLayout());
			buttonsSouthPanel.setBounds(new Rectangle(14, 280, 574, 83));
			buttonsSouthPanel.add(getChangeBinFileButton(), null);
			
		}
		return buttonsSouthPanel;
	}

	/**
	 * This method initializes topPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getTopPanel() {
		if (topPanel == null) {
			topPanel = new JPanel();
			topPanel.setLayout(new BorderLayout());
			topPanel.setBounds(new Rectangle(3, 8, 589, 267));
			
			topPanel.add(getFilesListPanel(), BorderLayout.CENTER);
			topPanel.add(getTopFilterPanel(), BorderLayout.NORTH);
		}
		return topPanel;
	}
	
	private JPanel topFilterPanel;
	
	private JPanel getTopFilterPanel(){
		if(topFilterPanel == null){
			topFilterPanel = new JPanel(new BorderLayout());
			topFilterPanel.add(getButtonsPanel(),BorderLayout.NORTH);
			topFilterPanel.add(getFilterPanel(),BorderLayout.CENTER);
		}
		return topFilterPanel;
	}
	
	private JPanel filterPanel;
	private JPanel getFilterPanel(){
		if(filterPanel == null){
			filterPanel = new JPanel();
			filterPanel.setLayout(new GridLayout(2,2,5,5));
			filterPanel.add(new JLabel("Filtrar pelo nome do arquivo"));
			filterPanel.add(fileNameTextField);
			filterPanel.add(new JLabel("Filtrar pelo tipo do arquivo"));
			filterPanel.add(fileFormatComboBox);
			filterPanel.setPreferredSize(new Dimension(600,50));
			fileFormatComboBox.setEditable(true);
			fileFormatComboBox.addItem("");
			fileFormatComboBox.addItem("pdf");
			fileFormatComboBox.addItem("doc");
			fileFormatComboBox.addItem("xdoc");
			fileFormatComboBox.addItem("xsl");
			fileFormatComboBox.addItem("zip");
			fileFormatComboBox.addItem("gz");
			fileFormatComboBox.addItem("jpeg");
			fileFormatComboBox.addItem("jpg");
			fileFormatComboBox.addItem("gif");
			fileFormatComboBox.addItem("png");
			fileFormatComboBox.addItem("bmp");
			fileFormatComboBox.addItem("html");
			fileFormatComboBox.addItem("xml");
			ActionListener act = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					list();
				}				
			};
			fileFormatComboBox.addActionListener(act);
			fileNameTextField.addActionListener(act);
		}
		return filterPanel;
	}
	
	private JTextField fileNameTextField = new JTextField();
	
	private JComboBox fileFormatComboBox = new JComboBox();
	private JButton limparCamposButton = null;
	private JButton reportButton = null;
	private JButton changeBinFileButton = null;

	/**
	 * This method initializes limparCamposButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getLimparCamposButton() {
		if (limparCamposButton == null) {
			limparCamposButton = new JButton();
			limparCamposButton.setText("Limpar Campos");
			limparCamposButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					fileFormatComboBox.setSelectedItem("");
					fileNameTextField.setText("");
				}
			});
		}
		return limparCamposButton;
	}

	/**
	 * This method initializes reportButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getReportButton() {
		if (reportButton == null) {
			reportButton = new JButton();
			reportButton.setText("Relatório");
			reportButton.setEnabled(false);
			reportButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//TODO desenvolver o relatório de arquivo
				}
			});
		}
		return reportButton;
	}

	/**
	 * This method initializes changeBinFileButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getChangeBinFileButton() {
		if (changeBinFileButton == null) {
			changeBinFileButton = new JButton();
			changeBinFileButton.setText("Modificar o Binário");
			changeBinFileButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					Arquivo arq = getSelectedArquivo();
					if(arq == null || arq.getId()<=0)
						return;
					openFile.actionPerformed(e, arq);
					ImportFile.Status status = openFile.getStatus();
					if(status != ImportFile.Status.Cancel){
						if(status == ImportFile.Status.Success){
							AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de Arquivo", "Arquivo adicionado com sucesso!");
							editRegister(openFile.getArquivo());
						}else{
							AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Arquivo", "O arquivo não foi adicionado!");
						}
					}
				}
			});
		}
		return changeBinFileButton;
	}
}  //  @jve:decl-index=0:visual-constraint="11,9"
