package com.adapit.portal.ui.tree;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import com.adapit.portal.entidades.Categoria;

public class CategoriasTreeRenderer extends DefaultTreeCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9873468714614376L;
	
	private int iconTextGapValue=8;
	protected DefaultMutableTreeNode node=null;
	public CategoriasTreeRenderer() {
		super();
	}

	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		super.getTreeCellRendererComponent(tree, value, sel, expanded,
				leaf, row, hasFocus);
		
		super.setTextSelectionColor(Color.YELLOW);
		super.setTextNonSelectionColor(Color.BLACK/*new Color(60,64,64)*/);
		//super.setBackgroundNonSelectionColor(new Color(250,250,250));
		super.setBackgroundSelectionColor(new Color(123,134,96));
		super.setBackground(new Color(223,234,196));
		
		setFont(com.adapit.portal.util.global.FontDefinitions.getInstance().TreeCellFont);
		
		super.setMaximumSize(new Dimension(1500,50));
		super.setPreferredSize(new Dimension(1500,50));
		
		//super.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION);
		
		//The distance between the icon and the text
		super.setIconTextGap(iconTextGapValue);
		
		
		
		try {
			node = (DefaultMutableTreeNode) value;
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		if (node instanceof ObserverMutableTreeNode && ((ObserverMutableTreeNode)node).getElement() != null){
			Categoria d = (Categoria) ((ObserverMutableTreeNode)node).getElement();
			if (d.getCategoriaImagem() != null)try {
				setIcon(d.getCategoriaImagem().getSmallIcon(false));			
			} catch (Exception e1) {	
				setIcon(null);
				e1.printStackTrace();
			}else setIcon(null);
			String str="";
			try {
				str = ((ObserverMutableTreeNode)node).getElement().getNome();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.setText(str);
		}		
		return this;
	}

}
