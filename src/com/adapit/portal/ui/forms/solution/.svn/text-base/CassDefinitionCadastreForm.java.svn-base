package com.adapit.portal.ui.forms.solution;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

public class CassDefinitionCadastreForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JScrollPane contentScrollPane = null;
	private JPanel topPanel = null;
	private JTextField nomeCssTextField = null;
	private JTextPane cssContentTextPane = null;
	private JPanel centerPanel = null;
	private JPanel buttonsPanel = null;
	private JButton saveButton = null;
	private JPanel cssImagePanel = null;
	private JTextField imageTextField = null;
	private JPanel imageButtonsPanel = null;
	private JButton buscarImageButton = null;
	private JButton visualizarImageButton = null;
	private JButton novoButton = null;
	
	private CssDefinition cssDefinition = new CssDefinition();  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public CassDefinitionCadastreForm() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(565, 292);
		this.setLayout(new BorderLayout());
		this.add(getTopPanel(), BorderLayout.NORTH);
		this.add(getCenterPanel(), BorderLayout.CENTER);
		this.add(getButtonsPanel(), BorderLayout.SOUTH);
	}

	/**
	 * This method initializes contentScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getContentScrollPane() {
		if (contentScrollPane == null) {
			contentScrollPane = new JScrollPane();
			contentScrollPane.setViewportView(getCssContentTextPane());
		}
		return contentScrollPane;
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
			topPanel.setBorder(BorderFactory.createTitledBorder(null, "Nome do Arquivo CSS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), Color.black));
			topPanel.add(getNomeCssTextField(), BorderLayout.CENTER);
		}
		return topPanel;
	}

	/**
	 * This method initializes nomeCssTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeCssTextField() {
		if (nomeCssTextField == null) {
			nomeCssTextField = new JTextField();
		}
		return nomeCssTextField;
	}

	/**
	 * This method initializes cssContentTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getCssContentTextPane() {
		if (cssContentTextPane == null) {
			cssContentTextPane = new JTextPane();
		}
		return cssContentTextPane;
	}

	/**
	 * This method initializes centerPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCenterPanel() {
		if (centerPanel == null) {
			centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			centerPanel.setBorder(BorderFactory.createTitledBorder(null, "Conteúdo do CSS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), Color.black));
			centerPanel.add(getContentScrollPane(), BorderLayout.CENTER);
			centerPanel.add(getCssImagePanel(), BorderLayout.SOUTH);
		}
		return centerPanel;
	}

	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			buttonsPanel.add(getSaveButton(), null);
			buttonsPanel.add(getNovoButton(), null);
		}
		return buttonsPanel;
	}

	/**
	 * This method initializes saveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("Salvar");
			saveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cssDefinition.setName(nomeCssTextField.getText());
					cssDefinition.setStyle(cssContentTextPane.getText());
					try {
						RemoteComercialSolutionService.getInstance().saveOrUpdate(cssDefinition);
						AdapitVirtualFrame.getInstance().showOperationSucess();
					} catch (Exception e1) {
						e1.printStackTrace();
						AdapitVirtualFrame.getInstance().showErrorCamposInvalidos();
					}
				}
			});
		}
		return saveButton;
	}

	/**
	 * This method initializes cssImagePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getCssImagePanel() {
		if (cssImagePanel == null) {
			cssImagePanel = new JPanel();
			cssImagePanel.setLayout(new BorderLayout());
			cssImagePanel.setBorder(BorderFactory.createTitledBorder(null, "Imagem demonstrativa do CSS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Tahoma", Font.PLAIN, 11), Color.black));
			cssImagePanel.add(getImageTextField(), BorderLayout.CENTER);
			cssImagePanel.add(getImageButtonsPanel(), BorderLayout.EAST);
		}
		return cssImagePanel;
	}

	/**
	 * This method initializes imageTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getImageTextField() {
		if (imageTextField == null) {
			imageTextField = new JTextField();
		}
		return imageTextField;
	}

	/**
	 * This method initializes imageButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getImageButtonsPanel() {
		if (imageButtonsPanel == null) {
			imageButtonsPanel = new JPanel();
			imageButtonsPanel.setLayout(new GridLayout(2,1));
			imageButtonsPanel.add(getBuscarImageButton(), null);
			imageButtonsPanel.add(getVisualizarImageButton(), null);
		}
		return imageButtonsPanel;
	}

	/**
	 * This method initializes buscarImageButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarImageButton() {
		if (buscarImageButton == null) {
			buscarImageButton = new JButton();
			buscarImageButton.setText("Buscar");
			buscarImageButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JButton select = new JButton("Selecionar");
					final ImageListForm list = AdapitVirtualFrame.getInstance().listarImagens(select);
					select.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent arg0) {
							Imagem im = list.getSelectedImage();
							cssDefinition.setImageSample(im);
						}
					});
					
				}
			});
		}
		return buscarImageButton;
	}

	/**
	 * This method initializes visualizarImageButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVisualizarImageButton() {
		if (visualizarImageButton == null) {
			visualizarImageButton = new JButton();
			visualizarImageButton.setText("Visualizar");
		}
		return visualizarImageButton;
	}

	/**
	 * This method initializes novoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovoButton() {
		if (novoButton == null) {
			novoButton = new JButton();
			novoButton.setText("Novo");
			novoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cssDefinition.setId(0);
					cssDefinition.setName("");
					cssDefinition.setStyle("");
					editRegister(cssDefinition);
				}
			});
		}
		return novoButton;
	}
	
	public void editRegister(CssDefinition c){
		this.cssDefinition.setId(c.getId());
		this.cssDefinition.setName(c.getName());
		this.cssDefinition.setStyle(c.getStyle());
		cssContentTextPane.setText(this.cssDefinition.getStyle());
		nomeCssTextField.setText(this.cssDefinition.getName());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
