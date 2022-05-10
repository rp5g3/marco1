package com.adapit.portal.ui.forms.imageform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.HtmlEditorPanel;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings( { "unchecked", "serial" })
public class ImagemCadastrePanel extends JPanel implements CategoriaSelectable {

	protected LogErrorPanel logErrorPanel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel subCategoriasPanel;

	private JTextField categoriaTextField;

	private JScrollPane treeScrollPane;

	private List<Imagem> imagens = new ArrayList();

	private Categoria selectedElement; // @jve:decl-index=0:

	private JPanel contentPanel;

	private int scale = 800;
	private int smallScale = 80;

	public ImagemCadastrePanel() {
		initialize();
	}

	private void initialize() {
		setSize(new Dimension(650, 507));
		setLocation(new java.awt.Point(0, 0));
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		add(getImageComponentsPanel(), BorderLayout.CENTER);
		add(getTabbedPane(), BorderLayout.SOUTH);
		JToolBar jtb = new JToolBar();
		jtb.add(getSaveButton());
		jtb.setFloatable(false);
		add(jtb,BorderLayout.NORTH);
	}

	private JButton saveButton;

	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton("Save");
			saveButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (img != null) {
							img
									.setDescription(descricaoImageTextPane
											.getText());
							updateDescricaoImage();
							AdapitVirtualFrame.getInstance().showOperationSucess();
						}
					} catch (Exception e) {
						e.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorDialog("Erro ao atualizar dados da imagem", e.getClass().getSimpleName());
					}

				}
			});
		}
		return saveButton;
	}

	private JPanel getContentPanel() {
		if (contentPanel == null) {
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(getSubCategoriasPanel(),BorderLayout.CENTER);
			newRegister();
		}
		return contentPanel;
	}

	public void newRegister() {
		imgIndex = 0;
		updateImage();
	}

	protected JPanel getSubCategoriasPanel() {

		if (subCategoriasPanel == null) {
			subCategoriasPanel = new JPanel();
			subCategoriasPanel.setSize(new Dimension(611, 134));
			subCategoriasPanel.setLocation(new Point(10, 10));
			subCategoriasPanel.setLayout(new BorderLayout());
			subCategoriasPanel.add(getCategoriaTextField(),BorderLayout.NORTH);
			subCategoriasPanel.add(getTreeScrollPane(),BorderLayout.CENTER);
			subCategoriasPanel.add(getTreeButtonsPanel(),BorderLayout.SOUTH);
		}
		return subCategoriasPanel;
	}

	protected CategoriaSelectableTreeController treeController;

	protected CategoriaSelectableTreeController getTreeController() {
		if (treeController == null) {
			treeController = AdapitVirtualFrame.getInstance()
					.getTreeController(this);// new
			// CategoriaSelectableTreeController(this);
		}
		return treeController;
	}

	protected JTextField getCategoriaTextField() {
		if (categoriaTextField == null) {
			categoriaTextField = new JTextField();
			categoriaTextField.setText("");
			categoriaTextField.setEditable(false);
			categoriaTextField.setSize(new Dimension(473, 20));
			categoriaTextField.setLocation(new Point(136, 0));

		}
		return categoriaTextField;
	}


	protected JScrollPane getTreeScrollPane() {
		if (treeScrollPane == null) {
			treeScrollPane = new JScrollPane();
			treeScrollPane.setSize(new Dimension(473, 102));
			treeScrollPane.setLocation(new Point(136, 23));
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			// treeScrollPane.add(getTreeController().getTree());
			treeScrollPane.setViewportView(getTreeController().getTree());
		}
		return treeScrollPane;
	}

	protected JPanel treeButtonsPanel;

	protected JPanel getTreeButtonsPanel() {
		if (treeButtonsPanel == null) {
			treeButtonsPanel = new JPanel();
			treeButtonsPanel.setLayout(new FlowLayout());
			treeButtonsPanel.add(getRefreshTree());
			treeButtonsPanel.add(getEditTree());
		}
		return treeButtonsPanel;
	}

	protected JButton refreshTree;

	protected JButton getRefreshTree() {
		if (refreshTree == null) {
			refreshTree = new JButton();
			refreshTree.setSize(20, 20);
			refreshTree.setToolTipText("Atualizar as categorias");
			refreshTree.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/action_refresh_blue.gif")));
			refreshTree.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					treeController.newTree();
				}
			});
		}
		return refreshTree;
	}

	protected JButton editTree;

	protected JButton getEditTree() {
		if (editTree == null) {
			editTree = new JButton();
			editTree.setSize(20, 20);
			editTree.setToolTipText("Editar as categorias");
			editTree.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/note_edit.png")));
			editTree.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarCategorias();
				}
			});
		}
		return editTree;
	}

	/**
	 * This method initializes tabbedPane
	 * 
	 * @return javax.swing.JTabbedPane
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setPreferredSize(new Dimension(642, 220));
			//tabbedPane.setLocation(new Point(3, 320));
			tabbedPane.addTab("Dados da Imagem", getDadosImagemPanel());
			tabbedPane.addTab("Categoria da Imagem", getContentPanel());
		}
		return tabbedPane;
	}

	private JPanel dadosImagemPanel;

	public JPanel getDadosImagemPanel() {
		if (dadosImagemPanel == null) {
			dadosImagemPanel = new JPanel();
			dadosImagemPanel.setLayout(new BorderLayout());
			JPanel jp = new JPanel(new GridLayout(2, 1));
			jp.add(new JLabel("Rótulo"), null);
			jp.add(getDescricaoImagemTextField(), null);
			jp.setPreferredSize(new Dimension(100, 44));
			dadosImagemPanel.add(jp, BorderLayout.NORTH);
			jp = new JPanel(new BorderLayout());
			jp.setBorder(BorderFactory.createTitledBorder("Descrição"));
			jp.add(getDescricaoImageEditor(), BorderLayout.CENTER);
			dadosImagemPanel.add(jp, BorderLayout.CENTER);
		}
		return dadosImagemPanel;
	}

	/**
	 * This method initializes nextButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/eastResize.png")));
			nextButton.setSize(new Dimension(20, 40));
			nextButton.setLocation(new Point(450, 158));
			nextButton.setText("");
			nextButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					imgIndex = imgIndex + 1;
					img = getImagens().get(imgIndex);
					// updateImage();
					viewRegister(img);
				}
			});
		}
		return nextButton;
	}

	/**
	 * This method initializes previousButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getPreviousButton() {
		if (previousButton == null) {
			previousButton = new JButton();
			previousButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/westResize.png")));
			previousButton.setSize(new Dimension(20, 40));
			previousButton.setLocation(new Point(150, 158));
			previousButton.setText("");
			previousButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent evt) {
					imgIndex = imgIndex - 1;
					img = getImagens().get(imgIndex);
					viewRegister(img);
					// updateImage();
				}
			});
		}
		return previousButton;
	}

	private Object removeOptions[] = { "Apagar a imagem definitivamente",
			"Cancelar" };

	/**
	 * This method initializes removerImagemButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getRemoverImagemButton() {
		if (removerImagemButton == null) {
			removerImagemButton = new JButton();
			removerImagemButton.setText("Remover Imagem");
			removerImagemButton.setHorizontalTextPosition(JButton.CENTER);
			removerImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			removerImagemButton.setSize(new Dimension(130, 45));
			removerImagemButton.setLocation(new Point(10, 130));
			removerImagemButton.setIcon(getIcon("/imgs/picture_delete.png"));
			// removerImagemButton.setEnabled(false);
			removerImagemButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (getImagens().size() <= 0) {
						return;
					}
					try {

						int resp = JOptionPane.showOptionDialog(
								ImagemCadastrePanel.this,
								"Selecione a opção para remover a imagem",
								"Remover Imagem",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE, null,
								removeOptions, removeOptions[0]);
						if (resp == 0) {
							removerImagemDefinitivamente();

							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(ImagemCadastrePanel.this,
								"Este valor não é um número!",
								"O campo só aceita números",
								JOptionPane.ERROR_MESSAGE);
						imgNumTextField.setText("" + (imgIndex + 1));
					}
				}
			});

		}
		return removerImagemButton;
	}

	private void removerImagemDefinitivamente() {
		try {
			if (getImagens().size() == 1) {
				// Imagem im = removerImagemProduto();
				RemoteServicesUtility.getInstance().delete(img);
				getImagens().remove(img);
				img = null;
				imgIndex = 0;
			} else {
				// Imagem im = removerImagemProduto();
				getImagens().remove(imgIndex);
				RemoteServicesUtility.getInstance().delete(img);
				if (getImagens().size() == 0)
					imgIndex = 0;
				else if (imgIndex > 1)
					imgIndex = imgIndex - 1;
				else
					imgIndex = 0;
				img = getImagens().get(imgIndex);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * private Imagem removerImagemProduto(){ Session s = null; try { Imagem
	 * imgRemove=null; s=LocalServicesUtility.getInstance().openSession();
	 * 
	 * Produto p = (Produto) s.get(Produto.class,produto.getId());
	 * Iterator<Imagem> it = p.getImagens().iterator(); while(it.hasNext()){
	 * Imagem im = it.next(); if (im.getId() == img.getId()) { imgRemove = im;
	 * break; } } if (imgRemove != null){ p.getImagens().remove(imgRemove);
	 * s.beginTransaction(); s.merge(p); s.getTransaction().commit(); return
	 * imgRemove; } } catch (HibernateException e) { e.printStackTrace(); if (s
	 * != null) s.getTransaction().rollback();
	 * 
	 * }finally{ if (s != null) s.close(); } return null; }
	 */

	/**
	 * This method initializes descricaoImagemTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getDescricaoImagemTextField() {
		if (descricaoImagemTextField == null) {
			descricaoImagemTextField = new JTextField();
			descricaoImagemTextField.setBounds(new Rectangle(82, 10, 545, 20));
			descricaoImagemTextField
					.addKeyListener(new java.awt.event.KeyAdapter() {
						@Override
						public void keyReleased(java.awt.event.KeyEvent e) {
							if (img != null)
								img.setRotulo(descricaoImagemTextField
										.getText());
						}
					});
			descricaoImagemTextField.addFocusListener(new FocusListener() {
				@Override
				public void focusGained(FocusEvent evt) {
					// TODO Auto-generated method stub
				}

				@Override
				public void focusLost(FocusEvent evt) {
					// int resp =
					// JOptionPane.showConfirmDialog(ProdutoCadastreForm.this,
					// "Salvar as Alterações?","Modificação",JOptionPane.YES_NO_OPTION);
					// if (resp == JOptionPane.YES_OPTION){
					/*
					 * try { saveImage(); } catch (Exception e) { // TODO
					 * Auto-generated catch block e.printStackTrace(); }
					 */
					// }
					try {
						updateRotuloImage();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
		}
		return descricaoImagemTextField;
	}

	/**
	 * This method initializes numImgsTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getNumImgsTextField() {
		if (numImgsTextField == null) {
			numImgsTextField = new JTextField();
			numImgsTextField.setEditable(false);
			numImgsTextField.setText("0");
			numImgsTextField.setHorizontalAlignment(JTextField.CENTER);
		}
		return numImgsTextField;
	}

	/**
	 * This method initializes imgNumTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getImgNumTextField() {
		if (imgNumTextField == null) {
			imgNumTextField = new JTextField();
			// imgNumTextField.setEditable(false);
			imgNumTextField.setText("0");
			imgNumTextField.setHorizontalAlignment(JTextField.CENTER);
			imgNumTextField.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (getImagens().size() <= 0) {
						return;
					}
					try {
						int n = Integer.parseInt(imgNumTextField.getText());
						if (n == 0) {
							JOptionPane
									.showMessageDialog(
											ImagemCadastrePanel.this,
											"Você deve informar um valor maior que zero!",
											"O campo só aceita números maiores que zero",
											JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText("" + (imgIndex + 1));
						} else if (n > getImagens().size()) {
							JOptionPane.showMessageDialog(
									ImagemCadastrePanel.this,
									"Você deve informar um valor menor que "
											+ n + "!",
									"Ordem incorreta das imagens",
									JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText("" + (imgIndex + 1));
						} else {
							img = getImagens().remove(imgIndex);
							img.setIndice(n - 1);
							getImagens().add(n - 1, img);
							imgIndex = n - 1;
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(ImagemCadastrePanel.this,
								"Este valor não é um número!",
								"O campo só aceita números",
								JOptionPane.ERROR_MESSAGE);
						imgNumTextField.setText("" + (imgIndex + 1));
					}
				}
			});
		}
		return imgNumTextField;
	}

	/**
	 * This method initializes dadosImgPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getDadosImgPanel() {
		if (dadosImgPanel == null) {
			dadosImgPanel = new JPanel();
			dadosImgPanel.setLayout(new GridLayout(4, 1));
			dadosImgPanel.setBounds(new Rectangle(5, 234, 165, 80));

			dadosImgPanel.add(numImgsLabel, null);
			dadosImgPanel.add(getNumImgsTextField(), null);
			dadosImgPanel.add(imgNumLabel, null);
			dadosImgPanel.add(getImgNumTextField(), null);

		}
		return dadosImgPanel;
	}

	/**
	 * This method initializes trocarImagemButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getTrocarImagemButton() {
		if (trocarImagemButton == null) {
			trocarImagemButton = new JButton();
			// trocarImagemButton.setEnabled(false);
			trocarImagemButton.setText("Substituir Imagem");
			trocarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			trocarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			trocarImagemButton.setSize(new Dimension(130, 45));
			trocarImagemButton.setLocation(new Point(10, 180));
			trocarImagemButton.setIcon(getIcon("/imgs/picture_edit.png"));
			trocarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					browseChangeImage();
				}
			});
		}
		return trocarImagemButton;
	}

	/**
	 * This method initializes salvarButton
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getViewFullImageButton() {
		if (viewFullImageButton == null) {
			viewFullImageButton = new JButton();
			viewFullImageButton.setText("Visializar em (800 X 800)");
			viewFullImageButton.setHorizontalTextPosition(JButton.CENTER);
			viewFullImageButton.setVerticalTextPosition(JButton.BOTTOM);
			viewFullImageButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/zoom.png")));
			viewFullImageButton.setBounds(new Rectangle(449, 243, 176, 58));
			viewFullImageButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (img != null) {
						try {
							ViewFullImageDialog jd = new ViewFullImageDialog(
									img, scale, scale);
							jd.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
		return viewFullImageButton;
	}

	/**
	 * This method initializes editarLoteButton
	 * 
	 * @return javax.swing.JButton
	 */
	/*
	 * private JButton getEditarLoteButton() { if (editarLoteButton == null) {
	 * editarLoteButton = new JButton(); editarLoteButton.setBounds(new
	 * Rectangle(245, 36, 106, 24)); editarLoteButton.setIcon(new
	 * ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
	 * editarLoteButton.setText("Editar Lote"); } return editarLoteButton; }
	 */

	/**
	 * This method initializes detalhesImagemButton
	 * 
	 * @return javax.swing.JButton
	 */
	/*
	 * private JButton getDetalhesImagemButton() { if (detalhesImagemButton ==
	 * null) { detalhesImagemButton = new JButton();
	 * detalhesImagemButton.setBounds(new Rectangle(392, 315, 74, 25));
	 * detalhesImagemButton.setText("Detalhes"); } return detalhesImagemButton;
	 * }
	 */

	/*
	 * public static void main(String args[] ){
	 * 
	 * new java.lang.Thread( new Runnable(){ public void run(){
	 * javax.swing.JFrame gui = new javax.swing.JFrame();
	 * gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	 * gui.setLayout(new java.awt.BorderLayout()); gui.setSize(new
	 * java.awt.Dimension(500,560)); gui.add(new
	 * ProdutoCadastreForm(),java.awt.BorderLayout.CENTER);
	 * gui.setVisible(true); } } ).run(); }
	 */

	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18,
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

	/*
	 * public Categoria getSelectedElement() { return selectedElement; }
	 */

	/*
	 * public void setSelectedElement(Categoria selectedElement) {
	 * this.selectedElement = selectedElement; try { if (selectedElement !=
	 * null){
	 * 
	 * getCategoriaTextField().setText(selectedElement.getNome());
	 * 
	 * } else getCategoriaTextField().setText(""); } catch
	 * (LazyInitializationException e) { } }
	 */

	/*
	 * private void setSelectedElement(int categoria_id) { if
	 * (getTreeController().categorias != null &&
	 * getTreeController().categorias.size() > 0){
	 * setSelectedElement(getTreeController().categorias.get(""+categoria_id));
	 * if (getSelectedElement() != null)
	 * getCategoriaTextField().setText(getSelectedElement().getNome()); else
	 * getCategoriaTextField().setText(""); }else
	 * getCategoriaTextField().setText(""); }
	 */

	private JPanel imageComponentsPanel;

	// private JPanel imageTitleButtonPanel;

	private JLabel imageLabel;

	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;

	private JPanel imgPanelImage;

	protected JPanel getImgPanelImage() {
		if (imgPanelImage == null) {
			imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory
					.createLineBorder(Color.lightGray));
			imgPanelImage.setLocation(new Point(180, 55));
			imgPanelImage.setSize(new Dimension(260, 260));
			imgPanelImage.add(getImgLabelImage(), BorderLayout.CENTER);
		}
		return imgPanelImage;
	}

	private JPanel smallPanelImage;

	protected JPanel getSmallPanelImage() {
		if (smallPanelImage == null) {
			smallPanelImage = new JPanel();
			smallPanelImage.setLayout(new BorderLayout());
			smallPanelImage.setBorder(BorderFactory
					.createLineBorder(Color.lightGray));
			smallPanelImage.setLocation(new Point(500, 138));
			smallPanelImage.setSize(new Dimension(smallScale, smallScale));
			smallPanelImage.add(getSmallLabelImage(), BorderLayout.CENTER);
		}
		return smallPanelImage;
	}

	protected JPanel getImageComponentsPanel() {
		if (imageComponentsPanel == null) {
			imgNumLabel = new JLabel();
			imgNumLabel.setText("Imagem Número:");
			imgNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imgNumLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			numImgsLabel = new JLabel();
			numImgsLabel.setText("Número de Imagens:");
			numImgsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imageComponentsPanel = new JPanel();
			imageComponentsPanel.setSize(new Dimension(635, 356));
			//imageComponentsPanel.setMinimumSize(new Dimension(635, 356));
			imageComponentsPanel.setLayout(null);
			imageComponentsPanel.add(getBuscarImagemButton());
			imageComponentsPanel.add(getCaminhoImgTextField());
			imageComponentsPanel.add(getCaminhoImgTextFieldLabel());
			imageComponentsPanel.add(getImgPanelImage(), null);
			imageComponentsPanel.add(getSmallPanelImage(), null);
			imageComponentsPanel.add(getNextButton(), null);
			imageComponentsPanel.add(getPreviousButton(), null);
			imageComponentsPanel.add(getRemoverImagemButton(), null);
			imageComponentsPanel.add(getDadosImgPanel(), null);
			imageComponentsPanel.add(getTrocarImagemButton(), null);
			imageComponentsPanel.add(getViewFullImageButton(), null);

		}
		return imageComponentsPanel;
	}

	protected JLabel getImageLabel() {
		if (imageLabel == null) {
			imageLabel = new JLabel(messages.getMessage("Imagem"));
			imageLabel.setSize(new java.awt.Dimension(100, 20));
			imageLabel.setHorizontalAlignment(JLabel.RIGHT);
			imageLabel.setLocation(new java.awt.Point(188, 11));
		}
		return imageLabel;
	}

	protected JButton getBuscarImagemButton() {
		if (buscarImagemButton == null) {
			buscarImagemButton = new JButton("Buscar Imagem");
			buscarImagemButton.setSize(new Dimension(130, 45));
			buscarImagemButton.setLocation(new Point(10, 80));
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setText("Adicionar Imagem");
			buscarImagemButton.setIcon(getIcon("/imgs/picture_add.png"));
			buscarImagemButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					AdapitVirtualFrame.getInstance().beginStatusBar(
							"Carregando Imagem ...");
					try {
						browseImage();
					} catch (Exception e) {
						AdapitVirtualFrame.getInstance().showWarningDialog(
								"Salvar Imagem", e.getMessage());
					}
					AdapitVirtualFrame.getInstance().endStatusBar(
							"Carregando Imagem ...");
				}

			});
		}
		return buscarImagemButton;
	}

	protected JTextField getCaminhoImgTextField() {
		if (caminhoImgTextField == null) {
			caminhoImgTextField = new JTextField();
			caminhoImgTextField.setEditable(false);
			caminhoImgTextField.setText("");
			caminhoImgTextField.setSize(new Dimension(545, 20));
			caminhoImgTextField.setLocation(new Point(80, 318));
			return caminhoImgTextField;
		}
		return caminhoImgTextField;
	}

	protected JLabel getCaminhoImgTextFieldLabel() {
		if (caminhoImgTextFieldLabel == null) {
			caminhoImgTextFieldLabel = new JLabel("Arquivo");
			caminhoImgTextFieldLabel.setSize(new Dimension(75, 20));
			caminhoImgTextFieldLabel.setLocation(new Point(4, 318));
			caminhoImgTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return caminhoImgTextFieldLabel;
	}

	protected JLabel getImgLabelImage() {
		if (imgLabelImage == null) {
			imgLabelImage = new JLabel();
			imgLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			imgLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgLabelImage;
	}

	private JLabel smallLabelImage;

	protected JLabel getSmallLabelImage() {
		if (smallLabelImage == null) {
			smallLabelImage = new JLabel();
			smallLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			smallLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return smallLabelImage;
	}

	private JFileChooser jfc;

	private JTabbedPane tabbedPane = null;

	private JButton nextButton = null;

	private JButton previousButton = null;

	private JButton removerImagemButton = null;

	private JLabel descriçãoLabel = null;

	private JTextField descricaoImagemTextField = null;

	private JLabel numImgsLabel = null;

	private JTextField numImgsTextField = null;

	private JLabel imgNumLabel = null;

	private JTextField imgNumTextField = null;

	private JPanel dadosImgPanel = null;

	protected JFileChooser getJfc() {
		if (jfc == null) {
			jfc = new ImageFileChooser();
		}
		return jfc;
	}

	public void browseImage() throws Exception {
		if (selectedElement == null)
			throw new Exception(
					"É necessário selecionar a categoria da imagem!");
		try {
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			if (f == null)
				return;

			this.getCaminhoImgTextField().setText(f.toURI().getPath());
			img = new Imagem();
			img.setFullImageBytes(f, scale);
			img.setSmallImageScallingAs(this.smallScale);

			getDescricaoImagemTextField().setText("");
			getDescricaoImageTextPane().setText("");
			this.descricaoImageEditor.updateByHtml();

			getImgLabelImage().setIcon(img.getMediumImageIcon(true));
			getSmallLabelImage().setIcon(img.getSmallImageIcon(true));

			try {

				img.setIndice(imgIndex);

				img.setPath(f.getAbsolutePath());

				img = RemoteImagemService.getInstance()
						.saveImagemMergeCategoria(img, getSelectedElement());

				addImagem(img);
				imgIndex++;
				viewRegister(img);
			} catch (Exception e1) {
				e1.printStackTrace();
				throw e1;
			}
			/*
			 * Session s = null; try { s =
			 * LocalServicesUtility.getInstance().openSession();
			 * s.beginTransaction();
			 * 
			 * img.setIndice(imgIndex);
			 * 
			 * img.setPath(f.getAbsolutePath());
			 * 
			 * 
			 * s.persist(img);
			 * 
			 * img.setCategoria(getSelectedElement());
			 * 
			 * s.merge(img);
			 * 
			 * s.getTransaction().commit(); addImagem(img); imgIndex++;
			 * viewRegister(img); } catch (Exception e1) { if (s != null)
			 * s.getTransaction().rollback(); e1.printStackTrace(); throw e1;
			 * }finally{ if (s != null) s.close(); }
			 */
		} catch (HeadlessException e) {
			e.printStackTrace();
		}

		updateImageComponents();
	}

	public void saveImage() throws Exception {
		if (img != null) {
			if (img.getId() != 0) {
				RemoteServicesUtility.getInstance().createOrUpdate(img);
			} else {
				Imagem i = (Imagem) RemoteServicesUtility.getInstance()
						.createOrUpdate(img);
				editRegister(i);
			}
		}
	}

	public void updateDescricaoImage() throws Exception {
		RemoteImagemService.getInstance().updateImageDescriptionByImageId(
				img.getId(), img.getDescription());
		/*
		 * Session s = null; try { if (img != null && img.getId() != 0) { s =
		 * LocalServicesUtility.getInstance().openSession();
		 * s.getTransaction().begin();
		 * 
		 * 
		 * 
		 * 
		 * s.createQuery("update Imagem im set im.description='"+img.getDescription
		 * ()+"' where im.id="+img.getId()).executeUpdate();
		 * 
		 * s.getTransaction().commit(); } } catch (Exception ex) {
		 * ex.printStackTrace(); s.getTransaction().rollback(); } finally { if
		 * (s != null) s.close(); }
		 */
	}

	public void updateRotuloImage() throws Exception {
		if (img != null)
			RemoteImagemService.getInstance().updateImageRotuloByImageId(
					img.getId(), img.getRotulo());
		/*
		 * Session s = null; try { if (img != null && img.getId() != 0) { s =
		 * LocalServicesUtility.getInstance().openSession();
		 * s.getTransaction().begin();
		 * 
		 * //s.merge(img);
		 * s.createQuery("update Imagem im set im.rotulo='"+img.getRotulo
		 * ()+"' where im.id="+img.getId()).executeUpdate();
		 * 
		 * s.getTransaction().commit(); } } catch (Exception ex) {
		 * ex.printStackTrace(); s.getTransaction().rollback(); } finally { if
		 * (s != null) s.close(); }
		 */
	}

	public void editRegister(Imagem objImg) {
		onEditing = true;
		if (img == null)
			img = new Imagem();
		img.setId(objImg.getId());

		img.setFullImageBytes(objImg.getFullImageBytes());
		img.setIndice(objImg.getIndice());
		img.setAltText(objImg.getAltText());
		img.setDescription(objImg.getDescription());
		img.setFormat(objImg.getFormat());
		img.setHeight(objImg.getHeight());
		img.setPath(objImg.getPath());
		img.setRotulo(objImg.getRotulo());
		img.setSmallImageBytes(objImg.getSmallImageBytes());
		img.setWidth(objImg.getWidth());
		img.setCategoria(objImg.getCategoria());

		if (img.getFullImageBytes() == null) {
			try {
				byte[] imbytes = RemoteImagemService.getInstance()
						.getFullImageBytesFromImage(img.getId());
				if (imbytes == null)
					return;
				img.setFullImageBytes(imbytes);
				objImg.setFullImageBytes(imbytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (img.getSmallImageBytes() == null) {
			try {
				byte[] imbytes = RemoteImagemService.getInstance()
						.getSmallImageBytesFromImage(img.getId());
				if (imbytes == null)
					return;
				img.setSmallImageBytes(imbytes);
				objImg.setSmallImageBytes(imbytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.descricaoImagemTextField.setText(img.getRotulo());
		this.descricaoImageTextPane.setText(img.getDescription());
		this.descricaoImageEditor.updateByHtml();

		this.caminhoImgTextField.setText(img.getPath());
		if (img.getCategoria() != null) {
			this.categoriaTextField.setText(img.getCategoria().getNome());
			setSelectedElement(img.getCategoria());
		}

		addImagem(objImg);

		imgIndex = getImagens().size();

		getImgLabelImage().updateUI();
		getSmallLabelImage().updateUI();
		updateImageComponents();
		updateImage();
		getImgLabelImage().setIcon(img.getMediumImageIcon(true));
		getSmallLabelImage().setIcon(img.getSmallImageIcon(true));

		onEditing = false;
	}

	public void viewRegister(Imagem objImg) {
		onEditing = true;
		if (img == null)
			img = new Imagem();
		img.setId(objImg.getId());

		img.setFullImageBytes(objImg.getFullImageBytes());
		img.setIndice(objImg.getIndice());
		img.setAltText(objImg.getAltText());
		img.setDescription(objImg.getDescription());
		img.setFormat(objImg.getFormat());
		img.setHeight(objImg.getHeight());
		img.setPath(objImg.getPath());
		img.setRotulo(objImg.getRotulo());
		img.setSmallImageBytes(objImg.getSmallImageBytes());
		img.setWidth(objImg.getWidth());
		img.setCategoria(objImg.getCategoria());

		if (img.getFullImageBytes() == null) {
			try {
				byte[] imbytes = RemoteImagemService.getInstance()
						.getFullImageBytesFromImage(img.getId());
				if (imbytes == null)
					return;
				img.setFullImageBytes(imbytes);
				objImg.setFullImageBytes(imbytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (img.getSmallImageBytes() == null) {
			try {
				byte[] imbytes = RemoteImagemService.getInstance()
						.getSmallImageBytesFromImage(img.getId());
				if (imbytes == null)
					return;
				img.setSmallImageBytes(imbytes);
				objImg.setSmallImageBytes(imbytes);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		this.descricaoImagemTextField.setText(img.getRotulo());
		this.descricaoImageTextPane.setText(img.getDescription());
		this.descricaoImageEditor.updateByHtml();

		this.caminhoImgTextField.setText(img.getPath());
		if (img.getCategoria() != null) {
			this.categoriaTextField.setText(img.getCategoria().getNome());
			setSelectedElement(img.getCategoria());
		}

		getImgLabelImage().updateUI();
		getSmallLabelImage().updateUI();
		updateImageComponents();
		updateImage();
		getImgLabelImage().setIcon(img.getMediumImageIcon(true));
		getSmallLabelImage().setIcon(img.getSmallImageIcon(true));

		onEditing = false;
	}

	public void browseChangeImage() {
		try {
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			this.getCaminhoImgTextField().setText(f.toURI().getPath());
			img.setFullImageBytes(f, scale);
			img.setSmallImageScallingAs(this.smallScale);

			getImgLabelImage().setIcon(img.getMediumImageIcon(true));
			getSmallLabelImage().setIcon(img.getSmallImageIcon(true));

			updateImageComponents();
			getImgLabelImage().updateUI();
			getSmallLabelImage().updateUI();

			try {
				saveImage();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}

	public void updateImageComponents() {
		if (getImagens() != null && getImagens().size() > 0) {
			getNumImgsTextField().setText(getImagens().size() + "");
			getImgNumTextField().setText("" + (imgIndex + 1));
			if (getImagens().size() == 1) {
				getPreviousButton().setEnabled(false);
				getNextButton().setEnabled(false);
				getImgNumTextField().setEnabled(false);
				getImgNumTextField().setText("1");
			} else {
				getPreviousButton().setEnabled(true);
				getNextButton().setEnabled(true);
				if (imgIndex == 0)
					getPreviousButton().setEnabled(false);
				else if (imgIndex + 1 == getImagens().size())
					getNextButton().setEnabled(false);
				getImgNumTextField().setEnabled(true);
			}
			getTrocarImagemButton().setEnabled(true);
			getRemoverImagemButton().setEnabled(true);
		} else {
			getNumImgsTextField().setText("0");
			getImgNumTextField().setText("");
			getImgNumTextField().setEnabled(false);
			getPreviousButton().setEnabled(false);
			getNextButton().setEnabled(false);
			getTrocarImagemButton().setEnabled(false);
			getRemoverImagemButton().setEnabled(false);
		}
	}

	int imgIndex = 0;
	Imagem img; // @jve:decl-index=0:

	private JButton trocarImagemButton = null;

	private JButton viewFullImageButton = null;

	private JLabel descricaoLabel = null;

	// private JTextPane descricaoImageTextPane = null;

	// private JScrollPane descricaoImgScrollPane = null;

	private void updateImage() {
		updateImageComponents();
		if (getImagens() != null && getImagens().size() > 0)
			try {
				int size = getImagens().size();
				if (size == imgIndex)
					img = getImagens().get(size - 1);
				else
					img = getImagens().get(imgIndex);
				this.getCaminhoImgTextField().setText(img.getPath());
				if (img.getRotulo() != null)
					this.getDescricaoImagemTextField().setText(img.getRotulo());
				else
					this.getDescricaoImagemTextField().setText("");

				if (img.getDescription() != null)
					this.getDescricaoImageTextPane().setText(
							img.getDescription());
				else
					this.getDescricaoImageTextPane().setText("");
				this.descricaoImageEditor.updateByHtml();

				getImgLabelImage().setIcon(img.getMediumImageIcon(false));
				getImgLabelImage().updateUI();

				getSmallLabelImage().setIcon(img.getSmallImageIcon(false));
				getSmallLabelImage().updateUI();
			} catch (HeadlessException e) {
				e.printStackTrace();
			}
		else
			try {
				this.getCaminhoImgTextField().setText("");
				getImgLabelImage().setIcon(null);
				getImgLabelImage().updateUI();

				getSmallLabelImage().setIcon(null);
				getSmallLabelImage().updateUI();
			} catch (HeadlessException e) {
				e.printStackTrace();
			}
	}

	private void updateImagensIndice() {
		if (getImagens() != null && getImagens().size() > 0) {
			{
				Iterator<Imagem> it = getImagens().iterator();
				int i = 0;
				while (it.hasNext()) {
					Imagem im = it.next();
					im.setIndice(i);
					i++;
				}
			}
			Iterator<Imagem> it = getImagens().iterator();
			while (it.hasNext()) {
				Imagem im = it.next();
				RemoteServicesUtility.getInstance().createOrUpdate(im);
			}
		}
	}

	private HtmlEditorPanel descricaoImageEditor;

	private HtmlEditorPanel getDescricaoImageEditor() {
		if (descricaoImageEditor == null) {
			getDescricaoImageTextPane();
			descricaoImageEditor = new HtmlEditorPanel(
					(JTextComponent) getDescricaoImageTextPane(),
					partApresEventListener, null);
			descricaoImageEditor.setBounds(new Rectangle(5, 103, 619, 178));
		}
		return descricaoImageEditor;
	}

	private HtmlEditorEventListener partApresEventListener = null;

	private JTextPane descricaoImageTextPane;

	private JTextPane getDescricaoImageTextPane() {
		if (descricaoImageTextPane == null) {
			descricaoImageTextPane = new JTextPane();
			partApresEventListener = new HtmlEditorEventListener(
					descricaoImageTextPane, AdapitVirtualFrame.getInstance(),
					"Apresentação");
			descricaoImageTextPane.addKeyListener(partApresEventListener);

			// JWarningComponent warn = new JWarningComponent(
			// this.descricaoImageTextArea);
			// warn.setBounds(new Rectangle(6, 76, 616, 109));
			// return warn;
		}
		return descricaoImageTextPane;
	}

	/**
	 * This method initializes descricaoImageTextPane
	 * 
	 * @return javax.swing.JTextArea
	 */
	// private JTextPane getDescricaoImageTextArea() {
	// if (descricaoImageTextPane == null) {
	// descricaoImageTextPane = new JTextPane();
	//			
	// descricaoImageTextPane.addKeyListener(new java.awt.event.KeyAdapter() {
	// @Override
	// public void keyReleased(java.awt.event.KeyEvent e) {
	// if (img != null) img.setDescription(descricaoImageTextPane.getText());
	// }
	// });
	// descricaoImageTextPane.addFocusListener(new FocusAdapter(){
	// @Override
	// public void focusLost(FocusEvent evt) {
	// try {
	// updateDescricaoImage();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	//				
	// });
	// }
	// return descricaoImageTextPane;
	// }

	/**
	 * This method initializes descricaoImgScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	// private JScrollPane getDescricaoImgScrollPane() {
	// if (descricaoImgScrollPane == null) {
	// descricaoImgScrollPane = new JScrollPane();
	// descricaoImgScrollPane.setBounds(new Rectangle(82, 40, 545 , 94));
	// descricaoImgScrollPane.setViewportView(getDescricaoImageTextArea());
	// }
	// return descricaoImgScrollPane;
	// }
	//	
	public Categoria getSelectedElement() {
		// Categoria selectedElement =
		// LeilaoVirtualFrame.getInstance().getSelectedElement();

		return selectedElement;
	}

	boolean onEditing = false;

	public void setSelectedElement(Categoria selectedElement) {
		try {
			if (selectedElement != null) {
				getCategoriaTextField().setText(selectedElement.getNome());
				if (!onEditing) {
					RemoteImagemService.getInstance().mergeCategoriaImagem(img,
							selectedElement);
					/*
					 * Session s = null; try { if (img != null && img.getId() !=
					 * 0) { s =
					 * LocalServicesUtility.getInstance().openSession();
					 * s.getTransaction().begin();
					 * img.setCategoria(selectedElement); s.merge(img);
					 * s.getTransaction().commit(); } } catch (Exception ex) {
					 * ex.printStackTrace(); s.getTransaction().rollback(); }
					 * finally { if (s != null) s.close(); }
					 */
				}
			} else
				getCategoriaTextField().setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.selectedElement = selectedElement;
	}

	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imgs) {
		this.imagens = imgs;
	}

	public void addImagem(Imagem img) {
		if (imgHt == null)
			imgHt = new Hashtable<Integer, Imagem>();
		if (!imgHt.contains(img.getId())) {
			imgHt.put(img.getId(), img);
			imagens.add(img);
		}
	}

	private Hashtable<Integer, Imagem> imgHt;
} // @jve:decl-index=0:visual-constraint="10,10"