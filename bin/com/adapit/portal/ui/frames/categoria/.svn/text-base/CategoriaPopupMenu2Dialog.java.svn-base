package com.adapit.portal.ui.frames.categoria;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaTreeController2Dialog;

@SuppressWarnings("serial")
public class CategoriaPopupMenu2Dialog extends JPopupMenu {
	
	private final CategoriaTreeController2Dialog controller;

	public CategoriaPopupMenu2Dialog(CategoriaTreeController2Dialog controller){
		this.controller = controller;
		initialize();
		//addFocusListener(new MyFocusListener());
		addPopupMenuListener(new MyPopupListener());
	}
	
	private class MyPopupListener implements PopupMenuListener{
		
		@Override
		public void popupMenuCanceled(PopupMenuEvent arg0) {
			
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {
			
		}

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
			
			if (/*CategoriaInternalFrame.getInstance()*/controller.getSelected() == null || !(controller.getSelected() instanceof Categoria)){
				apagarMenuItem.setEnabled(false);
				recortarMenuItem.setEnabled(false);				
			}else{
				apagarMenuItem.setEnabled(true);
				recortarMenuItem.setEnabled(true);
			}
			if (CategoriaInternalFrame.getInstance().getCut() == null) colarMenuItem.setEnabled(false);
			else colarMenuItem.setEnabled(true);
		}
	}
	
	private JMenuItem newMenuItem;
	private JMenuItem apagarMenuItem;
	private JMenuItem recortarMenuItem;
	private JMenuItem colarMenuItem;
	
	public void initialize(){
		{
			newMenuItem = new JMenuItem("Nova categoria");
			newMenuItem.setToolTipText("Adiciona uma nova categoria");
			newMenuItem.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_add.png", 20, 20));
			newMenuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.getObserver().addCategoria();
				}			
			});
			add(newMenuItem);
		}
		
		/*menuItem = new JMenuItem("Renomear");
		menuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				((CategoriasTreeController.MyTreeCellEditor)controller.getTree().getCellEditor()).startEditing();
			}			
		});
		add(menuItem);*/
		{
			apagarMenuItem = new JMenuItem("Apagar");
			apagarMenuItem.setToolTipText("Remove a categoria selecionada e seus filhos");
			apagarMenuItem.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_delete.png", 20, 20));
			apagarMenuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.getObserver().removeCategoria();
				}			
			});
			add(apagarMenuItem);
		}
		
		{
			recortarMenuItem = new JMenuItem("Recortar");
			recortarMenuItem.setToolTipText("Recorta uma categoria");
			recortarMenuItem.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_cut.png", 20, 20));
			recortarMenuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.getObserver().cutCategoria();
				}			
			});
			add(recortarMenuItem);
		}
		
		{
			colarMenuItem = new JMenuItem("Colar");
			//menuItem.addActionListener(this);
			colarMenuItem.setToolTipText("Cola uma categoria");
			colarMenuItem.setIcon(AdapitVirtualFrame.getIcon(
					"/imgs/note_paste.png", 20, 20));
			colarMenuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					controller.getObserver().pasteCategoria();
				}			
			});
			add(colarMenuItem);
		}
	}
}
