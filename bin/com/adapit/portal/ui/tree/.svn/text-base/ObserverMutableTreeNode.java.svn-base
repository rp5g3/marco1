package com.adapit.portal.ui.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Random;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import com.adapit.portal.entidades.Categoria;

@SuppressWarnings("serial")
public class ObserverMutableTreeNode extends DefaultMutableTreeNode implements	Transferable {
	private Categoria element; 
	
	private int id;
	
	public ObserverMutableTreeNode(Categoria element) {
		this.element = element;
		Random rd = new Random();
		id = rd.nextInt(100000);
	}
	
	
	public Categoria getElement() {
		return element;
	}

	public void setElement(Categoria element) {
		this.element = element;
	}
	
	
	
	
	@Override
	public String toString() {
		if (element != null && 	element.getNome()!= null) return element.getNome();
		return super.toString();
	}

	@Override
	public void setUserObject(Object obj) {
		super.setUserObject(obj);
		if (obj instanceof String){
			element.setNome((String) obj);			
		}
	}




	public static DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class,
			"Tree Path");

	DataFlavor flavors[] = { TREE_PATH_FLAVOR };

	TreePath path;

	

	public synchronized DataFlavor[] getTransferDataFlavors() {
		return flavors;
	}

	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavor.getRepresentationClass() == TreePath.class);
	}

	public synchronized Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {
		if (isDataFlavorSupported(flavor)) {
			return (Object) this.getPath();
		} else {
			throw new UnsupportedFlavorException(flavor);
		}
	}
	
	public int hashCode() {
		return this.id;
	}

	public boolean equals(Object obj) {
		return (id == ((ObserverMutableTreeNode) obj).id);
	}
	

}
