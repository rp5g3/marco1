package com.adapit.portal.ui.frames.categoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CategoriaToolbar extends JToolBar{

	private CategoriaInternalFrame observer;
	private JButton addCategoriaButton = null;
	private JButton removeCategoriaButton = null;
	private JButton recortarCategoriaButton = null;
	private JButton colarButton = null;
	private JButton saveButton = null;
	private JButton refreshButton = null;
	
	public CategoriaToolbar(CategoriaInternalFrame observer){
		this.observer= observer;
		initialize();
	}
	
	private void initialize(){
		setFloatable(false);
		add(getSaveButton());
		add(getAddCategoriaButton());
		add(getRemoveCategoriaButton());
		add(getRecortarCategoriaButton());
		add(getColarButton());
		add(getRefreshButton());
	}
	
	public JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setSize(24,24);
			saveButton.setToolTipText("Salva todas as modificações");
			saveButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_save.png", 20, 20));
			saveButton.addActionListener(observer.getCcf().getNewAtualizarAction());
		}
		return saveButton;
	}
	
	public JButton getAddCategoriaButton() {
		if (addCategoriaButton == null) {
			addCategoriaButton = new JButton();
			addCategoriaButton.setSize(24,24);
			addCategoriaButton.setToolTipText("Adiciona uma nova categoria");
			addCategoriaButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_add.png", 20, 20));
			addCategoriaButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					observer.addCategoria();
				}				
			});
		}
		return addCategoriaButton;
	}
	
	public JButton getRemoveCategoriaButton() {
		if (removeCategoriaButton == null) {
			removeCategoriaButton = new JButton();
			removeCategoriaButton.setSize(24,24);
			removeCategoriaButton.setToolTipText("Remove a categoria selecionada e seus filhos");
			removeCategoriaButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_delete.png", 20, 20));
			removeCategoriaButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					observer.removeCategoria();
				}				
			});
		}
		return removeCategoriaButton;
	}
	
	public JButton getRecortarCategoriaButton() {
		if (recortarCategoriaButton == null) {
			recortarCategoriaButton = new JButton();
			recortarCategoriaButton.setSize(24,24);
			recortarCategoriaButton.setToolTipText("Recorta uma categoria");
			recortarCategoriaButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_cut.png", 20, 20));
			recortarCategoriaButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					observer.cutCategoria();
				}				
			});
		}
		return recortarCategoriaButton;
	}

	public JButton getColarButton() {
		if (colarButton == null) {
			colarButton = new JButton();
			colarButton.setSize(24,24);
			colarButton.setToolTipText("Cola uma categoria");
			colarButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_paste.png", 20, 20));
			colarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					observer.pasteCategoria();					
				}				
			});
		}
		return colarButton;
	}
	
	public JButton getRefreshButton() {
		if (refreshButton == null) {
			refreshButton = new JButton();
			refreshButton.setSize(24,24);
			refreshButton.setToolTipText("Atualiza a árvore de categorias");
			refreshButton.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/action_refresh_blue.gif", 20, 20));
			refreshButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae) {
					observer.updateTree();
					observer.setSelectedElement(null);
				}				
			});
		}
		return refreshButton;
	}
	
	public void enableButtons(boolean b){
		getSaveButton().setEnabled(b);
		getRecortarCategoriaButton().setEnabled(b);
		getColarButton().setEnabled(b);
		getAddCategoriaButton().setEnabled(b);
		getRemoveCategoriaButton().setEnabled(b);
		if (CategoriaInternalFrame.getInstance().getCut() == null) getColarButton().setEnabled(false);
		else getColarButton().setEnabled(true);
	}

	public void enableToPaste(boolean b) {
		getColarButton().setEnabled(b);
	}

}
