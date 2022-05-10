package com.adapit.portal.ui.forms.categoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.CategoriaImagem;
import com.adapit.portal.services.remote.RemoteCategoriaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.categoria.CategoriaInternalFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;

@SuppressWarnings("serial")
public class CategoriaCadastreForm extends JPanel {

	private JTextField nomeTextField;

	private SwingBinder binder = new SwingBinder();

	private Categoria categoria = new Categoria(), categoriaObj;
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel nomeTextFieldLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JLabel observacaoLabel;

	private JTextArea obsTextArea;

	private JPanel imageMainPanel;

	private JLabel imageLabel;

	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;

	private JPanel centralButtonsPanel;

	private JButton atualizarButton;

	private JButton novoButton;

	private JButton cancelarButton;
	
	@SuppressWarnings("unused")
	private CategoriaInternalFrame frame;

	public CategoriaCadastreForm(CategoriaInternalFrame frame) {
		this.frame=frame;
		initialize();
	}

	private void initialize() {

		setSize(new Dimension(478, 373));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getNomeTextField());
		add(getNomeTextFieldLabel());
		add(getObservacaoLabel());
		add(getObsScrollPane());
		add(getImageMainPanel());		
		add(getCentralButtonsPanel());		
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);		

	}
	@SuppressWarnings("unchecked")
	protected JComponent getNomeTextField() {

		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new java.awt.Dimension(350, 20));
			nomeTextField.setLocation(new java.awt.Point(100, 20));
			nomeTextField.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent arg0) {
					AdapitVirtualFrame.getInstance().reportWarning("Clique no botão Atualizar para gravar as modificações");
					getAtualizarButton().setEnabled(true);
				}
				
			});
			this.binder.addBindProperty(this.categoria, this.nomeTextField,
					"nome");

			this.hashComps.put("nome", this.nomeTextField);
			JWarningComponent warn = new JWarningComponent(this.nomeTextField);
			warn.setBounds(100, 20, 350, 20);
			return warn;
		}
		return nomeTextField;
	}

	public Categoria validateCategoriaForm() throws Exception {
		setErrorIcon(false);
		binder.bind(categoria);
		if (categoriaObj != null) categoria.setId(categoriaObj.getId());		
		if (!validateCategoriaBean())
			throw new Exception(messages
					.getMessage("ErroCategoriaDadosInvalidos"));
		return categoria;
	}

	public Categoria cadastreCategoria() throws Exception {
		Categoria c = validateCategoriaForm();
		return c;
	}
	@SuppressWarnings("unchecked")
	public boolean validateCategoriaBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.categoria, "categoria");
		if (errors == null)
			return true;
		Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
		String name;
		JComponent comp;
		for (int i = 0; i < entrys.length; i++) {
			name = (String) entrys[i].getKey();
			comp = (JComponent) this.hashComps.get(name);
			if (comp != null) {
				comp.firePropertyChange("warnBorder", false, true);
				Object[] obj = (Object[]) entrys[i].getValue();
				String msg = (String) obj[0];
				if (obj[1] == null) {
					try {
						getErrorPanel()
								.addError(messages.getMessage(msg), comp);
						comp.setToolTipText(messages.getMessage(msg));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					List args = (List) obj[1];
					Object[][] params = new Object[args.size()][2];
					for (int j = 0; j < args.size(); j++) {
						String key = (String) args.get(j);
						params[j][0] = key;
						params[j][1] = null;
					}
					try {
						getErrorPanel().addError(
								messages.getMessage(msg, params), comp);
						comp.setToolTipText(messages.getMessage(msg, params));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}

	public void newRegister() {
		// Nunca definir um novo objeto entidade!!!
		categoria.setSubCategorias(null);
		categoria.setId(0);
		categoria.setNome(null);
		categoria.setTemplate(null);
		categoria.setCategoriaImagem(null);
		this.categoriaObj = null;

		binder.reverseBind(this.categoria);

		this.setErrorIcon(false);
		updateUI();
	}
	@SuppressWarnings("unchecked")
	public void editRegister(Categoria objCategoria) {
		// Nunca passar como argumento novos objetos!!!
		this.categoriaObj = objCategoria;
		try {
			org.apache.commons.beanutils.BeanUtils.copyProperties(this.categoria, objCategoria);
		} catch (Exception e) {
			// e.printStackTrace();
			this.categoria.setNome(objCategoria.getNome());
			this.categoria.setId(objCategoria.getId());
			this.categoria.setTemplate(objCategoria.getTemplate());
			if (objCategoria.getCategoriaImagem() == null || objCategoria.getCategoriaImagem().getImagem() == null) {
				System.out.println("Imagem é nula");
			}
			this.categoria.setCategoriaImagem(objCategoria.getCategoriaImagem());			
			this.categoria.setPai(objCategoria.getPai());
			try {
				List list = RemoteCategoriaService.getInstance().listByParentId(categoria.getId());
				Set s = new TreeSet();
				s.addAll(list);
				this.categoria.setSubCategorias(s);
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
		}

		updateImage(false);		

		binder.reverseBind(this.categoria);
		this.setErrorIcon(false);
		AdapitVirtualFrame.getInstance().cleanErrorMsg();
	}

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(438, 50));
			logErrorPanel.setLocation(15, 312);
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {

		this.nomeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getNomeTextFieldLabel() {

		if (nomeTextFieldLabel == null) {
			nomeTextFieldLabel = new JLabel(messages.getMessage("Nome"));
			nomeTextFieldLabel.setSize(new java.awt.Dimension(100, 20));
			nomeTextFieldLabel.setLocation(new java.awt.Point(15, 20));
			nomeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return nomeTextFieldLabel;
	}

	protected JLabel getObservacaoLabel() {

		if (observacaoLabel == null) {
			observacaoLabel = new JLabel(messages
					.getMessage("ModeloDefDescProdutos"));
			observacaoLabel.setSize(new java.awt.Dimension(400, 20));
			observacaoLabel.setLocation(new java.awt.Point(15, 43));
		}
		return observacaoLabel;
	}

	protected JScrollPane obsScollPane;

	protected JScrollPane getObsScrollPane() {

		if (obsScollPane == null) {
			obsScollPane = new JScrollPane();
			obsScollPane.setSize(new Dimension(437, 121));
			obsScollPane.setLocation(new java.awt.Point(15, 66));
			obsScollPane.add(getObsTextArea());
			obsScollPane.setViewportView(getObsTextArea());
		}
		return obsScollPane;
	}
	@SuppressWarnings("unchecked")
	protected JTextArea getObsTextArea() {

		if (obsTextArea == null) {
			obsTextArea = new JTextArea();
			obsTextArea.setSize(new java.awt.Dimension(436, 90));
			obsTextArea.setLocation(new java.awt.Point(0, 0));
			obsTextArea.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent arg0) {
					AdapitVirtualFrame.getInstance().reportWarning("Clique no botão Atualizar para gravar as modificações");
					
				}
				
			});
			this.binder
					.addBindProperty(this.categoria, this.obsTextArea, "template");

			this.hashComps.put("template", this.obsTextArea);
			return obsTextArea;
		}
		return obsTextArea;
	}



	protected JPanel getImageMainPanel() {

		if (imageMainPanel == null) {
			imageMainPanel = new JPanel();
			imageMainPanel.setSize(new Dimension(442, 80));
			imageMainPanel.setLocation(new Point(15, 189));
			imageMainPanel.setLayout(null);
			imageMainPanel.add(getImageLabel(), null);
			JPanel imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory.createLineBorder(Color.blue));
			imgPanelImage.setBounds(new Rectangle(230, 5, 50, 50));
			imgPanelImage.add(getImgLabelImage(), BorderLayout.CENTER);
			imageMainPanel.add(imgPanelImage, null);
			imageMainPanel.add(getBuscarImagemButton(), null);
			imageMainPanel.add(getCaminhoImgTextFieldLabel(), null);
			imageMainPanel.add(getCaminhoImgTextField(), null);
		}
		return imageMainPanel;
	}

	protected JLabel getImageLabel() {

		if (imageLabel == null) {
			imageLabel = new JLabel(/*messages.getMessage("Imagem")*/);
			imageLabel.setBorder(BorderFactory.createLineBorder(Color.blue));
			imageLabel.setHorizontalAlignment(JLabel.LEFT);
			imageLabel.setBounds(new Rectangle(183, 19, 26, 26));
			imageLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
		}
		return imageLabel;
	}

	protected JButton getBuscarImagemButton() {

		if (buscarImagemButton == null) {
			buscarImagemButton = new JButton(messages.getMessage("Buscar"));
			buscarImagemButton = new JButton("Buscar Imagem");
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setBounds(new Rectangle(5, 8, 150, 45));
			buscarImagemButton.setIcon(getIcon("/imgs/folder_picture.png"));
			buscarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					browseImage();
				}

			});
		}
		return buscarImagemButton;
	}

	protected JTextField getCaminhoImgTextField() {
		if (caminhoImgTextField == null) {
			caminhoImgTextField = new JTextField();
			caminhoImgTextField.setEditable(false);
			caminhoImgTextField.setBounds(new Rectangle(81, 56, 356, 20));
			caminhoImgTextField.setText("");
			return caminhoImgTextField;
		}
		return caminhoImgTextField;
	}

	protected JLabel getCaminhoImgTextFieldLabel() {

		if (caminhoImgTextFieldLabel == null) {
			caminhoImgTextFieldLabel = new JLabel("Arquivo");
			caminhoImgTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
			caminhoImgTextFieldLabel.setBounds(new Rectangle(7, 56, 72, 20));
		}
		return caminhoImgTextFieldLabel;
	}

	protected JLabel getImgLabelImage() {

		if (imgLabelImage == null) {
			imgLabelImage = new JLabel();
			try {
				imgLabelImage
						.setIcon(getIcon(
								"/C:/Documents and Settings/user/Meus documentos/imgs/wizardicon.gif",
								102, 102));
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

		}
		return imgLabelImage;
	}

	private JFileChooser jfc;

	protected JFileChooser getJfc() {
		if (jfc == null) {
			jfc = new ImageFileChooser();
		}
		return jfc;
	}

	public void browseImage() {
		try {
			try {
				if (categoria.getCategoriaImagem() != null){
					CategoriaImagem ci = categoria.getCategoriaImagem();
					categoria.setCategoriaImagem(null);
					RemoteServicesUtility.getInstance().delete(ci);							
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			this.getCaminhoImgTextField().setText(f.toURI().getPath());
			CategoriaImagem ci = new CategoriaImagem();
			ci.setImgURI(f.toURI().getPath());
			ci.setImagem(f);			
			ci.setCategoria(categoria);
			categoria.setCategoriaImagem(ci);
			updateImage();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateImage() {
		if (categoria.getCategoriaImagem() != null)
			try {
				this.getCaminhoImgTextField().setText(
						categoria.getCategoriaImagem().getImagem().getPath());
				getImgLabelImage().setIcon(categoria.getCategoriaImagem().getBigIcon(true));
				getImageLabel().setIcon(categoria.getCategoriaImagem().getSmallIcon(true));
				getImgLabelImage().updateUI();
			} catch (HeadlessException e) {
				e.printStackTrace();
			}
		else{
			getImgLabelImage().setIcon(null);
			getImageLabel().setIcon(null);
		}		
	}

	private void updateImage(boolean b) {
		if (categoriaObj == null){
			if(categoriaObj.getCategoriaImagem() != null )try {
				if (categoria.getCategoriaImagem().getImagem() != null) {
					this.getCaminhoImgTextField().setText(
							categoria.getCategoriaImagem().getImagem().getPath());
					getImgLabelImage().setIcon(categoria.getCategoriaImagem().getBigIcon(b));
					getImgLabelImage().updateUI();
					getImageLabel().setIcon(categoria.getCategoriaImagem().getSmallIcon(b));
				} else {
					this.getCaminhoImgTextField().setText("");
					getImgLabelImage().setIcon(categoria.getCategoriaImagem().getBigIcon(true));
					getImgLabelImage().updateUI();
					getImageLabel().setIcon(categoria.getCategoriaImagem().getSmallIcon(true));
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			}else {
				getImgLabelImage().setIcon(null);
				getImageLabel().setIcon(null);
			}
		}
		else {
			if (categoriaObj.getCategoriaImagem() != null )try {
				if (categoriaObj.getCategoriaImagem().getImagem() != null) {
					this.getCaminhoImgTextField().setText(
							categoriaObj.getCategoriaImagem().getImagem().getPath());
					getImgLabelImage().setIcon(categoriaObj.getCategoriaImagem().getBigIcon(b));
					getImgLabelImage().updateUI();
					getImageLabel().setIcon(categoriaObj.getCategoriaImagem().getSmallIcon(b));
				} else {
					this.getCaminhoImgTextField().setText("");
					getImgLabelImage().setIcon(categoriaObj.getCategoriaImagem().getBigIcon(true));
					getImgLabelImage().updateUI();
					getImageLabel().setIcon(categoriaObj.getCategoriaImagem().getSmallIcon(true));
				}
			} catch (HeadlessException e) {
				e.printStackTrace();
			}else {
				getImgLabelImage().setIcon(null);
				getImageLabel().setIcon(null);
			}
		}
	}

	protected JPanel getCentralButtonsPanel() {
		if (centralButtonsPanel == null) {
			centralButtonsPanel = new JPanel();
			centralButtonsPanel.setSize(new java.awt.Dimension(438, 37));
			centralButtonsPanel.setLocation(new Point(15, 272));
			centralButtonsPanel.setLayout(new java.awt.FlowLayout());
			centralButtonsPanel.add(getAtualizarButton());
		}
		return centralButtonsPanel;
	}

	protected JButton getAtualizarButton() {
		if (atualizarButton == null) {
			atualizarButton = new JButton("Atualizar");
			atualizarButton.setSize(new java.awt.Dimension(100, 24));
			atualizarButton.setIcon(getIcon("/imgs/note_save.png", 20, 20));
			atualizarButton.setLocation(new java.awt.Point(0, 0));
			atualizarButton.addActionListener(new AtualizarCategoriaAction());			
		}
		return atualizarButton;
	}
	
	public AtualizarCategoriaAction getNewAtualizarAction(){
		return new AtualizarCategoriaAction();
	}

	public class AtualizarCategoriaAction extends AbstractAction {

		@Override
		protected void doAction(ActionEvent e) {			
			AdapitVirtualFrame.getInstance().cleanErrorMsg();
			AdapitVirtualFrame.getInstance().beginStatusBar(
					"Verificando a corretude dos dados");
			AdapitVirtualFrame.getInstance().cleanErrorMsg();
			try {
				cadastreCategoria();

				AdapitVirtualFrame.getInstance().beginStatusBar(
						"Atualizando dados da categoria");
				AdapitVirtualFrame.getInstance().endStatusBar(
						"Verificando a corretude dos dados");
				Categoria cat =null;
				
				try {
					RemoteCategoriaService.getInstance().saveOrUpdate(categoria);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
				
				{
					cat = CategoriaInternalFrame.getInstance().getCategoriasTreeController().getCategoria(""+categoria.getId());
					if (cat == null) cat = categoria;
					else{
						cat.setCategoriaImagem(categoria.getCategoriaImagem());
						cat.setNome(categoria.getNome());
						cat.setTemplate(categoria.getTemplate());						
						CategoriaInternalFrame.getInstance().getCategoriasTreeController().getTree().updateUI();
					}
				}
				AdapitVirtualFrame.getInstance().endStatusBar(
						"Atualizando dados da categoria");
				AdapitVirtualFrame.getInstance().showOperationSucess();

			} catch (Exception e1) {
				AdapitVirtualFrame.getInstance().endStatusBar(
						"Verificando a corretude dos dados");
				AdapitVirtualFrame.getInstance().displayErrorMsg(
						e1.getMessage());
				AdapitVirtualFrame.getInstance()
						.appendErrorMsg(e1.getMessage());
				AdapitVirtualFrame.getInstance().showErrorCamposInvalidos();
			}
		}

	}

	protected JButton getNovoButton() {

		if (novoButton == null) {
			novoButton = new JButton(messages.getMessage("Novo"));
			novoButton.setSize(new java.awt.Dimension(100, 24));
			novoButton.setLocation(new java.awt.Point(0, 24));
			novoButton.setEnabled(false);
		}
		return novoButton;
	}

	protected JButton getCancelarButton() {

		if (cancelarButton == null) {
			cancelarButton = new JButton(messages.getMessage("Cancelar"));
			cancelarButton.setSize(new java.awt.Dimension(100, 24));
			cancelarButton.setLocation(new java.awt.Point(0, 48));
		}
		return cancelarButton;
	}

	private static Icon getIcon(String name, int w, int h) {
		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(w, h,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	
	private static Icon getIcon(String name ){

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL).getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public class CategoriasTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		private HashSet<Categoria> selection = new HashSet<Categoria>();

		public CategoriasTable() {
			super();
			this.setModel(new CategoriasTableModel(null));
			this
					.addPropertyChangeListener(new CategoriasTablePropertyChangeListener());
		}

		public CategoriasTable(List elements) {
			super();
			this.elements = elements;
			updateTable();
			this.addPropertyChangeListener(new CategoriasTablePropertyChangeListener());
		}


		public void updateTable() {
			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = null;
				values = new java.lang.Object[ne][2];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Categoria) {
						com.adapit.portal.entidades.Categoria cat = (com.adapit.portal.entidades.Categoria) obj;
						values[i][1] = cat.getNome();
						values[i][0] = new Boolean(false);
						i++;						
					}
				}
				setModel(new CategoriasTableModel(values));
				updateUI();
			} else {
				setModel(new CategoriasTableModel(null));
				updateUI();
			}
		}

		private class CategoriasTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Boolean.class, String.class };

			boolean canEdit[] = new boolean[] { true, false };

			public CategoriasTableModel(Object[][] values) {

				super(values, new String[] { "Selecionar",
						messages.getMessage("Nome") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class CategoriasTablePropertyChangeListener implements
				PropertyChangeListener {

			private boolean isCategoriaParent(Categoria comparator,
					Categoria categoria) {

				if (categoria.getPai() != null) {
					if (categoria.getPai().getId() == comparator.getId())
						return true;
					else
						return isCategoriaParent(comparator, categoria.getPai());
				}
				return false;
			}

			public void propertyChange(PropertyChangeEvent e) {

				CategoriasTable jt = (CategoriasTable) e.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Categoria) {
							com.adapit.portal.entidades.Categoria cat = (com.adapit.portal.entidades.Categoria) obj;
							if (col == 0) {
								try {
									Boolean b = (Boolean) jt.getValueAt(row,
											col);
									if (b) {
										if (!isCategoriaParent(cat, categoria))
											selection.add(cat);
									} else {
										selection
												.remove(cat);
									}
								} catch (RuntimeException e1) {
									e1.printStackTrace();
								}
							}
						} else if (obj instanceof Boolean) {
							boolean b = ((Boolean) obj).booleanValue();
							if (b) {

							}
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		public HashSet<Categoria> getSelection() {
			return selection;
		}

	}

}  //  @jve:decl-index=0:visual-constraint="10,10"