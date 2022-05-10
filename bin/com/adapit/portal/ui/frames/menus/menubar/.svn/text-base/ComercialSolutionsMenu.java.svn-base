package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.imagem.CadastrarImagemDialog;

@SuppressWarnings("serial")
public class ComercialSolutionsMenu extends JMenu{


	private JMenuItem editarCategoriasMenuItem = null;

	private JMenuItem listarComercialSolutionsMenuItem = null;

	private JMenuItem cadastrarComercialSolutionsMenuItem = null;

	private JMenuItem verImagensMenuItem = null;
	
	public ComercialSolutionsMenu(){
		super("Soluções");
		add(getEditarCategoriasMenuItem());
		add(getListarComercialSolutionsMenuItem());
		add(getCadastrarComercialSolutionsMenuItem());
		add(getVerImagensMenuItem());
		add(getCadastroImagensButton());
	}
	
	/**
	 * This method initializes cadastrarCategoriasMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getEditarCategoriasMenuItem() {
		if (editarCategoriasMenuItem == null) {
			editarCategoriasMenuItem = new JMenuItem();
			editarCategoriasMenuItem.setText("Editar categorias");
			editarCategoriasMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarCategorias();
				}								
			});
		}
		return editarCategoriasMenuItem;
	}

	/**
	 * This method initializes listarProdutosMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getListarComercialSolutionsMenuItem() {
		if (listarComercialSolutionsMenuItem == null) {
			listarComercialSolutionsMenuItem = new JMenuItem();
			listarComercialSolutionsMenuItem.setText("Listar produtos");
			listarComercialSolutionsMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listCommercialSolutions();
				}				
			});
		}
		return listarComercialSolutionsMenuItem;
	}

	/**
	 * This method initializes cadastrarProdutosMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getCadastrarComercialSolutionsMenuItem() {
		if (cadastrarComercialSolutionsMenuItem == null) {
			cadastrarComercialSolutionsMenuItem = new JMenuItem();
			cadastrarComercialSolutionsMenuItem.setText("Cadastrar produtos");
			cadastrarComercialSolutionsMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().comercialSolutionCadastre();
				}				
			});
		}
		return cadastrarComercialSolutionsMenuItem;
	}

	/**
	 * This method initializes verHistoricoProdutosMenuItem
	 * 
	 * @return javax.swing.JMenuItem
	 */
	private JMenuItem getVerImagensMenuItem() {
		if (verImagensMenuItem == null) {
			verImagensMenuItem = new JMenuItem();
			verImagensMenuItem.setText("Listar Imagens");
			verImagensMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listImagens();
				}				
			});
		}
		return verImagensMenuItem;
	}
	
	private JMenuItem cadastroImagensButton;

	private JMenuItem getCadastroImagensButton() {
		if (cadastroImagensButton == null) {
			cadastroImagensButton = new JMenuItem("Cadastrar Imagens");			
			cadastroImagensButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					CadastrarImagemDialog c = new CadastrarImagemDialog();
					c.getImagemCadastreForm().newRegister();
					c.setVisible(true);
				}
			});
		}
		return cadastroImagensButton;
	}
}
