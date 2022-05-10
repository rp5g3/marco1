package com.adapit.portal.ui.forms.categoria;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import com.adapit.portal.ui.frames.categoria.CategoriaDialog;
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
public class CategoriaCadastreForm2Dialog extends JPanel {

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

	//private JPanel obsPanel;

	private JTextArea obsTextArea;

	//private JLabel obsTextAreaLabel;

	private JPanel imageMainPanel;

	private JLabel imageLabel;

	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;

	/*private JPanel subCategoriasPanel;

	private JScrollPane subCategoriasTableScrollPane;

	//private SubCategoriasTable subCategoriasTable;

	private JPanel subCategoriasButtonsListPanel;

	private JButton addSubcategoriasButton;

	private JButton removeSubcategoriasButton;

	private JButton editSubcategoriasButton;*/

	private JPanel centralButtonsPanel;

	private JButton atualizarButton;

	private JButton novoButton;

	private JButton cancelarButton;
	
	@SuppressWarnings("unused")
	private CategoriaDialog frame;

	public CategoriaCadastreForm2Dialog(CategoriaDialog frame) {
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
		//add(getSubCategoriasPanel());
		add(getCentralButtonsPanel());
		// Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		// End

	}

	
	@SuppressWarnings("unchecked")
	protected JComponent getNomeTextField() {

		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.setText("");
			nomeTextField.setSize(new java.awt.Dimension(350, 20));
			nomeTextField.setLocation(new java.awt.Point(100, 20));
			nomeTextField.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					
				}

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
		/*
		 * File img = new File(getCaminhoImgTextField().getText());
		 * FileOutputStream fos= new FileOutputStream(img);
		 * ByteArrayOutputStream baos = new ByteArrayOutputStream(); baos.
		 */
		// categoria.setImagem((fos.));
		// Manual Code
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
			/*this.categoria.setImgURI(objCategoria.getImgURI());*/
			this.categoria.setPai(objCategoria.getPai());
			try {
				List list = RemoteCategoriaService.getInstance().listByParentId(categoria.getId());
				Set s = new TreeSet();
				s.addAll(list);
				this.categoria.setSubCategorias(s/*objCategoria.getSubCategorias()*/);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}

		updateImage(false);
		//updateSubcategorias();
		// categoria.setId(0);

		binder.reverseBind(this.categoria);
		this.setErrorIcon(false);
		getAtualizarButton().setEnabled(false);
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

	/*protected JPanel getObsPanel() {

		if (obsPanel == null) {
			obsPanel = new JPanel();
			obsPanel.setSize(new java.awt.Dimension(437, 97));
			obsPanel.setLocation(new java.awt.Point(15, 66));
			obsPanel.setLayout(null);
			obsPanel.add(getObsScrollPane());

		}
		return obsPanel;
	}*/

	protected JScrollPane obsScollPane;

	protected JScrollPane getObsScrollPane() {

		if (obsScollPane == null) {
			obsScollPane = new JScrollPane();
			obsScollPane.setSize(new java.awt.Dimension(437, 97));
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
			obsTextArea.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					AdapitVirtualFrame.getInstance().reportWarning("Clique no botão Atualizar para gravar as modificações");
					getAtualizarButton().setEnabled(true);
				}
				
			});
			this.binder
					.addBindProperty(this.categoria, this.obsTextArea, "template");

			this.hashComps.put("template", this.obsTextArea);
			return obsTextArea;
		}
		return obsTextArea;
	}

	/*
	 * protected JLabel getObsTextAreaLabel() {
	 * 
	 * if (obsTextAreaLabel == null) { obsTextAreaLabel = new JLabel( messages
	 * .getMessage("com.adapit.portal.ui.forms.baseleilao.CategoriaCadastreForm.Obs"));
	 * obsTextAreaLabel.setSize(new java.awt.Dimension(32, 20));
	 * obsTextAreaLabel.setLocation(new java.awt.Point(0, 0));
	 * obsTextAreaLabel.setHorizontalAlignment(JLabel.LEFT); } return
	 * obsTextAreaLabel; }
	 */

	protected JPanel getImageMainPanel() {

		if (imageMainPanel == null) {
			imageMainPanel = new JPanel();
			imageMainPanel.setSize(new Dimension(442, 103));
			imageMainPanel.setLocation(new java.awt.Point(15, 166));
			imageMainPanel.setLayout(null);
			imageMainPanel.add(getImageLabel(), null);
			JPanel imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory.createLineBorder(Color.blue));
			imgPanelImage.setBounds(new Rectangle(200, 30, 50, 50));
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
			imageLabel.setBounds(new Rectangle(212, 0, 26, 26));
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
			buscarImagemButton.setBounds(new Rectangle(68, 17, 117, 45));
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
			caminhoImgTextField.setBounds(new Rectangle(58, 82, 380, 20));
			caminhoImgTextField.setText("");
			return caminhoImgTextField;
		}
		return caminhoImgTextField;
	}

	protected JLabel getCaminhoImgTextFieldLabel() {

		if (caminhoImgTextFieldLabel == null) {
			caminhoImgTextFieldLabel = new JLabel(messages
					.getMessage("Caminho"));
			caminhoImgTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
			caminhoImgTextFieldLabel.setBounds(new Rectangle(1, 82, 55, 20));
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			this.getCaminhoImgTextField().setText(f.toURI().getPath());
			CategoriaImagem ci = new CategoriaImagem();
			ci.setImgURI(f.toURI().getPath());
			ci.setImagem(f);			
			ci.setCategoria(categoria);
			//ci = (CategoriaImagem) LocalServicesUtility.getInstance().createOrUpdate(ci);
			categoria.setCategoriaImagem(ci);
			updateImage();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}else {
				getImgLabelImage().setIcon(null);
				getImageLabel().setIcon(null);
			}
		}
	}

/*	protected JPanel getSubCategoriasPanel() {

		if (subCategoriasPanel == null) {
			subCategoriasPanel = new JPanel();
			subCategoriasPanel.setBorder(javax.swing.BorderFactory
					.createTitledBorder(javax.swing.BorderFactory
							.createTitledBorder("Sub-categorias")));
			subCategoriasPanel.setSize(new java.awt.Dimension(439, 123));
			subCategoriasPanel.setLocation(new java.awt.Point(15, 272));
			subCategoriasPanel.setLayout(null);
			subCategoriasPanel.add(getSubCategoriasTableScrollPane());
			subCategoriasPanel.add(getSubCategoriasButtonsListPanel());
		}
		return subCategoriasPanel;
	}

	protected JScrollPane getSubCategoriasTableScrollPane() {

		if (subCategoriasTableScrollPane == null) {
			subCategoriasTableScrollPane = new JScrollPane();
			subCategoriasTableScrollPane
					.setSize(new java.awt.Dimension(310, 89));
			subCategoriasTableScrollPane
					.setLocation(new java.awt.Point(15, 20));
			subCategoriasTableScrollPane
					.setLayout(new javax.swing.ScrollPaneLayout());
			subCategoriasTableScrollPane.add(getSubCategoriasTable());
			subCategoriasTableScrollPane
					.setViewportView(getSubCategoriasTable());
		}
		return subCategoriasTableScrollPane;
	}

	protected JTable getSubCategoriasTable() {

		if (subCategoriasTable == null) {
			subCategoriasTable = new SubCategoriasTable();
			return subCategoriasTable;
		}
		return subCategoriasTable;
	}
	
	private Set categorias;

	private void updateSubcategorias() {
		categorias = categoria.getSubCategorias();
		if (categorias != null
				&& categorias.size() > 0) {
			subCategoriasTableScrollPane.remove(subCategoriasTable);
			subCategoriasTable = new SubCategoriasTable(categorias);
			subCategoriasTable.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent arg0) {
					getRemoveSubcategoriasButton().setEnabled(true);
					getEditSubcategoriasButton().setEnabled(true);
				}

				public void focusLost(FocusEvent arg0) {

				}

			});
			subCategoriasTableScrollPane.add(subCategoriasTable);
			subCategoriasTableScrollPane.setViewportView(subCategoriasTable);
		} else {
			subCategoriasTable = new SubCategoriasTable();
			subCategoriasTableScrollPane.remove(subCategoriasTable);
			subCategoriasTableScrollPane.add(subCategoriasTable);
			subCategoriasTableScrollPane.setViewportView(subCategoriasTable);
		}
		getAddSubcategoriasButton().setEnabled(true);
		getRemoveSubcategoriasButton().setEnabled(false);
		getEditSubcategoriasButton().setEnabled(false);
	}

	protected JPanel getSubCategoriasButtonsListPanel() {

		if (subCategoriasButtonsListPanel == null) {
			subCategoriasButtonsListPanel = new JPanel();
			subCategoriasButtonsListPanel
					.setSize(new java.awt.Dimension(100, 70));
			subCategoriasButtonsListPanel.setLocation(new java.awt.Point(330,
					40));
			subCategoriasButtonsListPanel.setLayout(new GridLayout(3, 1));
			subCategoriasButtonsListPanel.add(getAddSubcategoriasButton());
			subCategoriasButtonsListPanel.add(getRemoveSubcategoriasButton());
			subCategoriasButtonsListPanel.add(getEditSubcategoriasButton());
		}
		return subCategoriasButtonsListPanel;
	}

	protected JButton getAddSubcategoriasButton() {

		if (addSubcategoriasButton == null) {
			addSubcategoriasButton = new JButton(messages
					.getMessage("Adicionar"));
			addSubcategoriasButton.setSize(new java.awt.Dimension(112, 20));
			addSubcategoriasButton.setLocation(new java.awt.Point(0, 0));
			addSubcategoriasButton.setIcon(new ImageIcon(getClass().getResource("/imgs/note_add.png")));
			addSubcategoriasButton.setEnabled(false);
			addSubcategoriasButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					List categorias = LocalCategoriaService.getInstance()
							.listCategoriasByNullParent(false);
					
					Iterator itcat = categorias.iterator();
					Categoria repeated = null;
					while (itcat.hasNext()) {
						Categoria c = (Categoria) itcat.next();
						if (c.getId() == categoria.getId()){
							repeated = c;
						}
					}
					if (repeated != null) categorias.remove(repeated);
					
					if (categorias != null && categorias.size() > 0) {
						final CategoriasTable ct = new CategoriasTable(categorias);
						final javax.swing.JDialog gui = new javax.swing.JDialog();
						gui.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
						gui.setLayout(new java.awt.BorderLayout());
						gui.setSize(new java.awt.Dimension(478, 200));

						JScrollPane jsp = new JScrollPane();
						jsp.add(ct);
						jsp.setViewportView(ct);
						gui.add(jsp, java.awt.BorderLayout.CENTER);

						JPanel jp = new JPanel();
						jp.setLayout(new FlowLayout());

						JButton jbconf = new JButton("Confirmar");
						jp.add(jbconf);
						jbconf.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								try {
									Iterator it = ct.getSelection().iterator();
									while (it.hasNext()) {
										Categoria c = (Categoria) it.next();
										if (c.getId() != categoria.getId()){
											c.setPai(categoria);
											LocalCategoriaService.getInstance().saveOrUpdate(c);
										}
									}
									// cadastreCategoria();
									CategoriaInternalFrame.getInstance().updateTree();
									updateSubcategorias();
								} catch (Exception e) {
									e.printStackTrace();
								}
								gui.dispose();
							}
						});

						JButton jbcan = new JButton("Cancelar");
						jp.add(jbcan);
						jbcan.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								gui.dispose();
							}
						});

						gui.add(jp, BorderLayout.SOUTH);

						gui.setLocation(UIUtil.getScreenCenter(gui));
						gui.setVisible(true);

					}
				}
			});
		}
		return addSubcategoriasButton;
	}

	protected Categoria getCategoria() {
		return categoria;
	}

	protected JButton getRemoveSubcategoriasButton() {

		if (removeSubcategoriasButton == null) {
			removeSubcategoriasButton = new JButton(messages
					.getMessage("Remover"));
			removeSubcategoriasButton.setSize(new java.awt.Dimension(112, 20));
			removeSubcategoriasButton.setLocation(new java.awt.Point(0, 20));
			removeSubcategoriasButton.setIcon(new ImageIcon(getClass().getResource("/imgs/note_delete.png")));
			removeSubcategoriasButton.setEnabled(false);
			removeSubcategoriasButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					int rows[] = getSubCategoriasTable().getSelectedRows();
					if (rows != null && rows.length > 0) {
						for (int i = 0; i < rows.length; i++) {
							int row = rows[i];
							try {
								LocalCategoriaService
										.getInstance()
										.deleteById(
												((Categoria) ((SubCategoriasTable) getSubCategoriasTable())
														.getElements().get(row))
														.getId());
								categoria
										.getSubCategorias()
										.remove(
												(Categoria) ((SubCategoriasTable) getSubCategoriasTable())
														.getElements().get(row));
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					CategoriaInternalFrame.getInstance().updateTree();
					updateSubcategorias();
				}
			});
		}
		return removeSubcategoriasButton;
	}

	protected JButton getEditSubcategoriasButton() {

		if (editSubcategoriasButton == null) {
			editSubcategoriasButton = new JButton(messages.getMessage("Editar"));
			editSubcategoriasButton.setSize(new java.awt.Dimension(150, 20));
			editSubcategoriasButton.setLocation(new java.awt.Point(0, 40));
			editSubcategoriasButton.setIcon(new ImageIcon(getClass().getResource("/imgs/note_edit.png")));
			editSubcategoriasButton.setEnabled(false);
			editSubcategoriasButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int row = getSubCategoriasTable().getSelectedRow();

					try {
						Categoria cat = (Categoria) ((SubCategoriasTable) getSubCategoriasTable())
								.getElements().get(row);
						frame.setSelectedElement(cat);
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
		}
		return editSubcategoriasButton;
	}
*/
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
			//createNewInstanceButton.setEnabled(false);
		}
		return atualizarButton;
	}
	
	public AtualizarCategoriaAction getNewAtualizarAction(){
		return new AtualizarCategoriaAction();
	}

	public class AtualizarCategoriaAction extends AbstractAction {

		@Override
		protected void doAction(ActionEvent e) {
			//Categoria c = null;
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
				/*LocalServicesUtility*/
				try {
					/*cat =*/ RemoteCategoriaService.getInstance().saveOrUpdate(categoria);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				getAtualizarButton().setEnabled(true);
				//getAddSubcategoriasButton().setEnabled(true);
				
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
		}// end of catch block
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
		}//end of catch block
		return null;
	}
/*	public class SubCategoriasTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public SubCategoriasTable() {

			super();
			this.setModel(new SubCategoriasTableModel(null));
			this
					.addPropertyChangeListener(new SubCategoriasTablePropertyChangeListener());
		}

		public SubCategoriasTable(Collection<Categoria> els) {
			super();
			this.elements = new Vector();
			this.elements.addAll(els);
			this.setModel(new SubCategoriasTableModel(null));
			this
					.addPropertyChangeListener(new SubCategoriasTablePropertyChangeListener());
			updateTable();
		}


		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][3];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Categoria) {
						com.adapit.portal.entidades.Categoria categoria = (com.adapit.portal.entidades.Categoria) obj;
						values[i][0] = categoria.getNome();
						values[i][1] = categoria.getTemplate();
						if (categoria.getCategoriaImagem() !=null) values[i][2] = categoria.getCategoriaImagem().getSmallIcon(false);
						i++;
					}
				}// End of while Loop
				setModel(new SubCategoriasTableModel(values));
				updateUI();
			} else {
				setModel(new SubCategoriasTableModel(null));
				updateUI();
			}
		}

		private class SubCategoriasTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					ImageIcon.class };

			boolean canEdit[] = new boolean[] { true, true, false };

			public SubCategoriasTableModel(Object[][] values) {

				super(values, new String[] { messages.getMessage("Nome"),
						"Modelo", "Imagem" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class SubCategoriasTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.baseleilao.ui.categoria.CategoriaCadastreForm.SubCategoriasTable jt = (com.adapit.portal.ui.forms.baseleilao.ui.categoria.CategoriaCadastreForm.SubCategoriasTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Categoria) {
							com.adapit.portal.entidades.Categoria categoria = (com.adapit.portal.entidades.Categoria) obj;
							if (col == 0)
								categoria.setNome(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 1)
								categoria.setTemplate(((java.lang.String) jt
										.getValueAt(row, col)));
							LocalCategoriaService.getInstance().saveOrUpdate(
									categoria);
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

	}
*/
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
			// this.setModel(new CategoriasTableModel(elements));
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
						// if (cat.getId() != categoria.getId()){
						values[i][1] = cat.getNome();
						values[i][0] = new Boolean(false);
						i++;
						// }
					}
				}// End of while Loop
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
										if (!isCategoriaParent(cat, categoria))/*
																				 * if
																				 * (!selection.contains(categoria))
																				 */
											selection.add(cat);
									} else {
										/* if (selection.contains(categoria)) */selection
												.remove(cat);
									}
								} catch (RuntimeException e1) {
									// TODO Auto-generated catch block
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

}