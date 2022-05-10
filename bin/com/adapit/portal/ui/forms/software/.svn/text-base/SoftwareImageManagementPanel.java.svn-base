package com.adapit.portal.ui.forms.software;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.io.File;
import java.util.ArrayList;
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
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.forms.imageform.ViewFullImageDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.ImageFileChooser;

public class SoftwareImageManagementPanel extends JPanel{

	private SoftwareSolution sol;
	private int scale=800;
	private int smallScale=80;
	private SoftwareSolutionCadastreForm form;
	public SoftwareImageManagementPanel(SoftwareSolution sol, SoftwareSolutionCadastreForm form){
		super();
		this.sol = sol;
		this.form = form;
		setLayout(new BorderLayout());
		add(getImageComponentsPanel(),BorderLayout.CENTER);
	}
	
	private JPanel imageComponentsPanel;

	private JLabel imageLabel;

	private JButton buscarImagemButton;

	private JTextField caminhoImgTextField;

	private JLabel caminhoImgTextFieldLabel;

	private JLabel imgLabelImage;

	private JPanel imgPanelImage;
	
	protected JPanel getImgPanelImage(){
		if (imgPanelImage == null){
			imgPanelImage = new JPanel();
			imgPanelImage.setLayout(new BorderLayout());
			imgPanelImage.setBorder(BorderFactory.createLineBorder(Color.lightGray));
			imgPanelImage.setLocation(new Point(180, 55));
			imgPanelImage.setSize(new Dimension(260, 260));
			imgPanelImage.add(getImgLabelImage(), BorderLayout.CENTER);
		}
		return imgPanelImage;
	}
	
	private JPanel smallPanelImage;
	
	protected JPanel getSmallPanelImage(){
		if (smallPanelImage == null){
			smallPanelImage = new JPanel();
			smallPanelImage.setLayout(new BorderLayout());
			smallPanelImage.setBorder(BorderFactory.createLineBorder(Color.lightGray));
			smallPanelImage.setLocation(new Point(500, 138));
			smallPanelImage.setSize(new Dimension(smallScale, smallScale));
			smallPanelImage.add(getSmallLabelImage(), BorderLayout.CENTER);
		}
		return smallPanelImage;
	}
	
	

	protected JPanel getImageComponentsPanel() {
		if (imageComponentsPanel == null) {
			descricaoLabel = new JLabel();
			descricaoLabel.setBounds(new Rectangle(5, 343, 74, 22));
			descricaoLabel.setText("Descrição:");
			imgNumLabel = new JLabel();
			imgNumLabel.setText("Imagem Número:");
			imgNumLabel.setHorizontalAlignment(SwingConstants.CENTER);
			imgNumLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			numImgsLabel = new JLabel();
			numImgsLabel.setText("Número de Imagens:");
			numImgsLabel.setHorizontalAlignment(SwingConstants.CENTER);
			descriçãoLabel = new JLabel();
			descriçãoLabel.setBounds(new Rectangle(5, 319, 73, 20));
			descriçãoLabel.setText("Rótulo:");
			imageComponentsPanel = new JPanel();
			imageComponentsPanel.setLayout(null);
			imageComponentsPanel.add(getBuscarImagemButton());
			imageComponentsPanel.add(getCaminhoImgTextField());
			imageComponentsPanel.add(getCaminhoImgTextFieldLabel());
			imageComponentsPanel.add(getImgPanelImage(), null);
			imageComponentsPanel.add(getSmallPanelImage(), null);
			imageComponentsPanel.add(getNextButton(), null);
			imageComponentsPanel.add(getPreviousButton(), null);
			imageComponentsPanel.add(getRemoverImagemButton(), null);
			imageComponentsPanel.add(descriçãoLabel, null);
			imageComponentsPanel.add(getDescricaoImagemTextField(), null);
			imageComponentsPanel.add(getDadosImgPanel(), null);
			imageComponentsPanel.add(getTrocarImagemButton(), null);
			imageComponentsPanel.add(getAnexarImagensButton(),null);
			imageComponentsPanel.add(descricaoLabel, null);
			imageComponentsPanel.add(getDescricaoImgScrollPane(), null);
			imageComponentsPanel.add(getViewFullImageButton(), null);
			imageComponentsPanel.addHierarchyListener(new HierarchyListener(){
				@Override
				public void hierarchyChanged(HierarchyEvent evt) {
					if (evt.getChanged() == imageComponentsPanel && evt.getID() == HierarchyEvent.HIERARCHY_CHANGED){
						if (sol.getImagens() != null && sol.getImagens().size()>0 && sol.getImagens().iterator().hasNext() && sol.getId() >0){
							try {
								AdapitVirtualFrame.getInstance().beginStatusBar("Importando as imagens! Essa operação pode ser demorada");
								SoftwareSolution prod = (SoftwareSolution)
									RemoteComercialSolutionService.getInstance().loadCommercialSolutionEagerImages(sol);
								sol.setImagens(prod.getImagens());
								imgIndex=0;
								updateImage();								
								AdapitVirtualFrame.getInstance().endStatusBar("Importando as imagens! Essa operação pode ser demorada");
							} catch (Exception e) {
								e.printStackTrace();
								AdapitVirtualFrame.getInstance().endStatusBar("Importando as imagens! Essa operação pode ser demorada");
							}
						}
					}
				}
				
			});			
			
		}
		return imageComponentsPanel;
	}

	protected JLabel getImageLabel() {

		if (imageLabel == null) {
			imageLabel = new JLabel(
					form.messages
							.getMessage("Imagem"));
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
			buscarImagemButton.setLocation(new Point(175, 5));
			buscarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			buscarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			buscarImagemButton.setText("Adicionar Imagem");
			buscarImagemButton.setIcon(getIcon("/imgs/picture_add.png"));
			buscarImagemButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (sol.getId() != 0){
						AdapitVirtualFrame.getInstance().beginStatusBar("Carregando Imagem ...");
						browseImage();
						AdapitVirtualFrame.getInstance().endStatusBar("Carregando Imagem ...");
					}
					else{
						JOptionPane.showMessageDialog(form,
								"Primeiro é preciso cadastrar o Software!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						//getTabbedPane().setSelectedIndex(0);
					}
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
			caminhoImgTextField.setLocation(new Point(82, 440));
			return caminhoImgTextField;
		}
		return caminhoImgTextField;
	}

	protected JLabel getCaminhoImgTextFieldLabel() {
		if (caminhoImgTextFieldLabel == null) {
			caminhoImgTextFieldLabel = new JLabel("Arquivo");
			caminhoImgTextFieldLabel.setSize(new Dimension(75, 20));
			caminhoImgTextFieldLabel.setLocation(new Point(5, 440));
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

	private JButton removerImagemButton = null;

	private JLabel descriçãoLabel = null;

	private JTextField descricaoImagemTextField = null;

	private JLabel numImgsLabel = null;

	private JTextField numImgsTextField = null;

	private JLabel imgNumLabel = null;

	private JTextField imgNumTextField = null;

	private JPanel dadosImgPanel = null;
	
	protected JFileChooser getJfc(){
		if (jfc == null){
			jfc = new ImageFileChooser();			
		}
		return jfc;
	}
	
	public void browseImage(){		
		try {
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			if (f == null ) return;
			
			this.getCaminhoImgTextField().setText(f.toURI().getPath());	
			img = new Imagem();
			img.setFullImageBytes(f,scale);
			img.setSmallImageScallingAs(this.smallScale);
			
			
			getDescricaoImagemTextField().setText("");
			getDescricaoImageTextArea().setText("");
			
			getImgLabelImage().setIcon(img.getMediumImageIcon(true));
			getSmallLabelImage().setIcon(img.getSmallImageIcon(true));
			
			try {				
				
				imgIndex = sol.getImagens().size();
				img.setIndice(imgIndex);
				
				img.setPath(f.getAbsolutePath());				
				
				updateImageComponents();				
				
				getImgLabelImage().updateUI();
				getSmallLabelImage().updateUI();
				ImagemService service = com.adapit.portal.services.remote.RemoteImagemService.getInstance();
				Categoria cat = form.getSelectedElement();
				img = service.saveImagemMergeCategoria(img, cat);
				img = service.mergeImagemComercialSolution(img, sol);
				sol.getImagens().add(img);
								
			} catch (Exception e1) {
				e1.printStackTrace();	
				AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Imagem",
						"Não foi possível adicionar a imagem no Software");
			}				
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		
		updateImageComponents();
	}
	
	public void saveImage() throws Exception{
		if(img != null){
			if(img.getId() != 0){
				RemoteServicesUtility.getInstance().createOrUpdate(img);			
			}
			else{
				Imagem i = (Imagem) RemoteServicesUtility.getInstance().createOrUpdate(img);
				editRegister(i);
				
			}
		}
	}
	
	public void updateDescricaoImage() throws Exception{
		RemoteImagemService.getInstance().updateImageDescriptionByImageId(img.getId(), img.getDescription());
	}
	
	public void updateRotuloImage() throws Exception{
		RemoteImagemService.getInstance().updateImageRotuloByImageId(img.getId(), img.getRotulo());
	}
	
	public void editRegister(Imagem objImg){
		img.setId(objImg.getId());	
		img.setFullImageBytes(objImg.getFullImageBytes());
		img.setIndice(objImg.getIndice());
		img.setAltText(objImg.getAltText());
		img.setDescription(objImg.getDescription());
		img.setFormat(objImg.getFormat());
		img.setHeight(objImg.getHeight());
		img.setPath(objImg.getPath());
		img.setComercialSolution(objImg.getComercialSolution());
		img.setRotulo(objImg.getRotulo());
		
		img.setSmallImageBytes(objImg.getSmallImageBytes());
		img.setWidth(objImg.getWidth());
		
		if (img.getFullImageBytes() == null){								
			try{
				byte[] imbytes = RemoteImagemService.getInstance().getFullImageBytesFromImage(img.getId());
				if (imbytes == null) return;									
				img.setFullImageBytes(imbytes);
				objImg.setFullImageBytes(imbytes);
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		if (img.getSmallImageBytes() == null){								
			try{
				byte[] imbytes = RemoteImagemService.getInstance().getSmallImageBytesFromImage(img.getId());
				if (imbytes == null) return;									
				img.setSmallImageBytes(imbytes);
				objImg.setSmallImageBytes(imbytes);
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
	}
	
	private JButton anexar;
	public void anexarImagem(){		
		try {				
			if (anexar == null){				 
				anexar = new JButton("Anexar imagens selecionadas no Software " + sol.getId());
				anexar.addActionListener(new AnexarImagemActionListener());	
				anexar.setIcon(getIcon("/imgs/picture_link.png"));
			}			
			AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm().getButtonsPanel().add(anexar,0);
			AdapitVirtualFrame.getInstance().listImagens();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private class AnexarImagemActionListener implements ActionListener{		
		public AnexarImagemActionListener(){
			AdapitVirtualFrame.getInstance().listImagens();		
			ilf = AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm();
			buttonsPanel = ilf.getButtonsPanel();
		}
		private ImageListForm ilf;
		private JPanel buttonsPanel;
		
		@Override
		public void actionPerformed(ActionEvent evt) {					
			int rows[] = ilf.getBaseTable().getSelectedRows();
			if (rows != null){
				try {
					List<Imagem> list = new ArrayList<Imagem>();
					for (int i=0; i < rows.length; i++){
						Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[i]);
						list.add(im);
					}
					SoftwareSolution prod = (SoftwareSolution) RemoteComercialSolutionService.getInstance().attachImageIntoCommercialSolution(sol, list);
					sol.setImagens(prod.getImagens());
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Imagens em Software", "Imagens anexadas com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				
				buttonsPanel.remove(anexar);				
			}
			AdapitVirtualFrame.getInstance().getListaImagensFrame().dispose();
			updateImageComponents();
		}
	}
	
	public void browseChangeImage(){		
		try {
			getJfc().showOpenDialog(this);
			File f = getJfc().getSelectedFile();
			this.getCaminhoImgTextField().setText(f.toURI().getPath());	
			img.setFullImageBytes(f,scale);
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
	@SuppressWarnings("unchecked")
	public void updateImageComponents(){
		try{
			sol.getImagens().size();
		}catch(Exception ex){
			try {
				List l = RemoteServicesUtility.getInstance().listImagensByComercialSolutionId(sol.getId());
				sol.setImagens(l);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if (sol.getImagens() != null && sol.getImagens().size()>0){
			getNumImgsTextField().setText(sol.getImagens().size()+"");
			getImgNumTextField().setText(""+(imgIndex+1));
			if (sol.getImagens().size() == 1){
				getPreviousButton().setEnabled(false);
				getNextButton().setEnabled(false);
				getImgNumTextField().setEnabled(false);
				getImgNumTextField().setText("1");
			}else{
				getPreviousButton().setEnabled(true);
				getNextButton().setEnabled(true);
				if (imgIndex == 0) getPreviousButton().setEnabled(false);
				else if (imgIndex + 1 == sol.getImagens().size()) getNextButton().setEnabled(false);
				getImgNumTextField().setEnabled(true);
			}
			getTrocarImagemButton().setEnabled(true);
			getRemoverImagemButton().setEnabled(true);
		}else{
			getNumImgsTextField().setText("0");
			getImgNumTextField().setText("");
			getImgNumTextField().setEnabled(false);
			getPreviousButton().setEnabled(false);
			getNextButton().setEnabled(false);
			getTrocarImagemButton().setEnabled(false);
			getRemoverImagemButton().setEnabled(false);
		}
	}
	
	int imgIndex=0;
	Imagem img;

	private JButton trocarImagemButton = null;

	private JButton viewFullImageButton = null;

	private JLabel descricaoLabel = null;

	private JTextPane descricaoImageTextArea = null;

	private JScrollPane descricaoImgScrollPane = null;

	
	public void newRegister(){
		imgIndex=0;
		updateImage();
		
	}
	
	public void editRegister(SoftwareSolution solu){
		//this.sol = solu;
		try {
			System.out.println("Buscando sol " + solu.getId());
			this.sol = (SoftwareSolution) RemoteComercialSolutionService.getInstance().
				getSoftwareSolutionByIdCascadingProperties(solu.getId(), true, true);
			System.out.println("Find  "+sol.getNome());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (this.sol.getImagens() != null){
			this.sol.getImagens().iterator().hasNext();	
			imgIndex=0;
			updateImage();
		}
	}
	
	/**
	 * This method initializes nextButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNextButton() {
		if (nextButton == null) {
			nextButton = new JButton();
			nextButton.setIcon(new ImageIcon(getClass().getResource("/imgs/eastResize.png")));
			nextButton.setSize(new Dimension(20, 40));
			nextButton.setLocation(new Point(450, 158));
			nextButton.setText("");
			nextButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					imgIndex = imgIndex+1;
					img = sol.getImagens().get(imgIndex);
					//getImgLabelImage().setIcon(img.getBigIcon(true));
					updateImage();
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
			previousButton.setIcon(new ImageIcon(getClass().getResource("/imgs/westResize.png")));
			previousButton.setSize(new Dimension(20, 40));
			previousButton.setLocation(new Point(150, 158));
			previousButton.setText("");
			previousButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					imgIndex = imgIndex-1;
					img = sol.getImagens().get(imgIndex);
					updateImage();
				}				
			});
		}
		return previousButton;
	}

	
	private Object removeOptions[] = {"Retirar a imagem desse Software",
			"Apagar a imagem definitivamente",
			"Cancelar"};
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
			removerImagemButton.setLocation(new Point(315, 5));
			removerImagemButton.setIcon(getIcon("/imgs/picture_delete.png"));
			removerImagemButton.setEnabled(false);
			removerImagemButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (sol.getImagens().size() <=0){						
						return;
					}
					try {						
						int resp = JOptionPane.showOptionDialog(
								form, 
								"Selecione a opção para remover a imagem",
								"Remover Imagem",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE,
								null, removeOptions, removeOptions[0]);
						if (resp == 1){							
							removerImagemDefinitivamente();										
							updateImage();
							updateImagensIndice();
						}else if (resp == 0){
							if (sol.getImagens().size() == 1){
								try {
									removerImagemSoftwareSolution();
									sol.getImagens().remove(img);
									img = null;
									imgIndex = 0;
								} catch (Exception e) {
									e.printStackTrace();
									AdapitVirtualFrame.getInstance().showErrorDialog("Remover referência de imagem", "Não foi possível desvincular a imagem deste Software!");
								}
							}
							else{
								try {
									removerImagemSoftwareSolution();
									sol.getImagens().remove(imgIndex);
									if (sol.getImagens().size() == 0) imgIndex = 0;
									else if (imgIndex > 1) imgIndex = imgIndex-1;
									else imgIndex = 0;
									img = sol.getImagens().get(imgIndex);
								} catch (Exception e) {
									e.printStackTrace();
									AdapitVirtualFrame.getInstance().showErrorDialog("Remover referência de imagem", "Não foi possível desvincular a imagem deste Software!");
								}
								
							}
																	
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(form,
								"Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
						imgNumTextField.setText(""+(imgIndex+1));
					}
				}				
			});
			
		}
		return removerImagemButton;
	}
	
	private void removerImagemDefinitivamente(){
		try {
			if (sol.getImagens().size() == 1){
				Imagem im = removerImagemSoftwareSolution();
				RemoteServicesUtility.getInstance().delete(im);				
				sol.getImagens().remove(img);
				img = null;
				imgIndex = 0;
			}
			else{
				Imagem im = removerImagemSoftwareSolution();
				sol.getImagens().remove(imgIndex);
				RemoteServicesUtility.getInstance().delete(im);
				if (sol.getImagens().size() == 0) imgIndex = 0;
				else if (imgIndex > 1) imgIndex = imgIndex-1;
				else imgIndex = 0;
				img = sol.getImagens().get(imgIndex);				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Imagem removerImagemSoftwareSolution() throws Exception{
		return RemoteComercialSolutionService.getInstance().removeImageFromCommercialSolution(sol.getId(), img.getId());
	}

	/**
	 * This method initializes descricaoImagemTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDescricaoImagemTextField() {
		if (descricaoImagemTextField == null) {
			descricaoImagemTextField = new JTextField();
			descricaoImagemTextField.setBounds(new Rectangle(82, 319, 545, 20));
			descricaoImagemTextField.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {
					if (img != null) img.setRotulo(descricaoImagemTextField.getText());
				}
			});
			descricaoImagemTextField.addFocusListener(new FocusListener(){
				@Override
				public void focusGained(FocusEvent evt) {
				
				}
				@Override
				public void focusLost(FocusEvent evt) {					
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
			imgNumTextField.setText("0");
			imgNumTextField.setHorizontalAlignment(JTextField.CENTER);
			imgNumTextField.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (sol.getImagens().size() <=0){						
						return;
					}
					try {
						int n = Integer.parseInt(imgNumTextField.getText());
						if (n == 0){
							JOptionPane.showMessageDialog(form, "Você deve informar um valor maior que zero!", "O campo só aceita números maiores que zero", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else if (n > sol.getImagens().size()){
							JOptionPane.showMessageDialog(form, "Você deve informar um valor menor que "+n+"!", "Ordem incorreta das imagens", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else{
							img = sol.getImagens().remove(imgIndex);
							img.setIndice(n-1);
							sol.getImagens().add(n-1,img);
							imgIndex = n-1;
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(form, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
						imgNumTextField.setText(""+(imgIndex+1));
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
			dadosImgPanel.setLayout(new GridLayout(4,1));
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
			trocarImagemButton.setEnabled(false);
			trocarImagemButton.setText("Substituir Imagem");
			trocarImagemButton.setHorizontalTextPosition(JButton.CENTER);
			trocarImagemButton.setVerticalTextPosition(JButton.BOTTOM);
			trocarImagemButton.setSize(new Dimension(130, 45));
			trocarImagemButton.setLocation(new Point(455, 5));
			trocarImagemButton.setIcon(getIcon("/imgs/picture_edit.png"));
			trocarImagemButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					browseChangeImage();
				}				
			});
		}
		return trocarImagemButton;
	}
	
	
	private JButton anexarImagensButton;

	private JButton getAnexarImagensButton() {
		if (anexarImagensButton == null) {
			anexarImagensButton = new JButton();
			anexarImagensButton.setEnabled(true);
			anexarImagensButton.setText("Anexar Imagens");
			anexarImagensButton.setHorizontalTextPosition(JButton.CENTER);
			anexarImagensButton.setVerticalTextPosition(JButton.BOTTOM);
			anexarImagensButton.setLocation(new Point(35, 5));
			anexarImagensButton.setSize(new Dimension(130, 45));
			anexarImagensButton.setIcon(getIcon("/imgs/pictures.png"));
			anexarImagensButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					if (sol.getId() != 0){
						anexarImagem();
					}
					else{
						JOptionPane.showMessageDialog(form, "Primeiro é preciso cadastrar o Software!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						//getTabbedPane().setSelectedIndex(0);
					}
				}				
			});
		}
		return anexarImagensButton;
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
			viewFullImageButton.setIcon(new ImageIcon(getClass().getResource("/imgs/zoom.png")));
			viewFullImageButton.setBounds(new Rectangle(450, 65, 176, 58));
			viewFullImageButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if (img != null){
						try {
							ViewFullImageDialog jd = new ViewFullImageDialog(img,scale,scale);							
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

	private JButton nextButton = null;

	private JButton previousButton = null;

	private void updateImage(){

		updateImageComponents();
		if (sol.getImagens() != null && sol.getImagens().size() > 0)try {
			img = sol.getImagens().get(imgIndex);
			this.getCaminhoImgTextField().setText(img.getPath());
			if (img.getRotulo() != null) this.getDescricaoImagemTextField().setText(img.getRotulo());
			else this.getDescricaoImagemTextField().setText("");
			
			if (img.getDescription() != null) this.getDescricaoImageTextArea().setText(img.getDescription());
			else this.getDescricaoImageTextArea().setText("");
			
			getImgLabelImage().setIcon(img.getMediumImageIcon(false));
			getImgLabelImage().updateUI();
			
			getSmallLabelImage().setIcon(img.getSmallImageIcon(false));
			getSmallLabelImage().updateUI();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
		else try {
			this.getCaminhoImgTextField().setText("");
			getImgLabelImage().setIcon(null);
			getImgLabelImage().updateUI();
			
			getSmallLabelImage().setIcon(null);
			getSmallLabelImage().updateUI();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private void updateImagensIndice(){
		if (sol.getImagens() != null && sol.getImagens().size() > 0){
			{
				Iterator<Imagem> it = sol.getImagens().iterator();
				int i=0;
				while(it.hasNext()){
					Imagem im = it.next();
					im.setIndice(i);
					i++;				
				}
			}			
			Iterator<Imagem> it = sol.getImagens().iterator();
			while (it.hasNext()) {
				Imagem im = it.next();
				RemoteServicesUtility.getInstance().createOrUpdate(im);					
			}
		}
	}

	/**
	 * This method initializes descricaoImageTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextPane getDescricaoImageTextArea() {
		if (descricaoImageTextArea == null) {
			descricaoImageTextArea = new JTextPane();
			descricaoImageTextArea.addKeyListener(new HtmlEditorEventListener(descricaoImageTextArea,AdapitVirtualFrame.getInstance(),"Descrição da Imagem"));
			descricaoImageTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
				@Override
				public void keyReleased(java.awt.event.KeyEvent e) {
					if (img != null) img.setDescription(descricaoImageTextArea.getText());
				}
			});
			descricaoImageTextArea.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent evt) {
					try {
						updateDescricaoImage();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			});
		}
		return descricaoImageTextArea;
	}

	/**
	 * This method initializes descricaoImgScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDescricaoImgScrollPane() {
		if (descricaoImgScrollPane == null) {
			descricaoImgScrollPane = new JScrollPane();
			descricaoImgScrollPane.setBounds(new Rectangle(82, 342, 545	, 94));
			descricaoImgScrollPane.setViewportView(getDescricaoImageTextArea());
		}
		return descricaoImgScrollPane;
	}

}
