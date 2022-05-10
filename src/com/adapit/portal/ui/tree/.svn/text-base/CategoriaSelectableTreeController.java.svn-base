package com.adapit.portal.ui.tree;


import java.awt.Cursor;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.Autoscroll;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.services.remote.RemoteCategoriaService;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.categoria.CategoriaInternalFrame;

public class CategoriaSelectableTreeController implements MouseListener, MouseMotionListener, ActionListener, FocusListener{


	private Object selected;

	private ObserverMutableTreeNode selectedNode;	

	private int frontier;

	private boolean lastDragged = false;

	protected JPopupMenu popup;

	protected JTree tree;
	
	protected CategoriaSelectable observer;

	public CategoriaSelectableTreeController(CategoriaSelectable observer) {
		super();
		this.observer = observer;
		tree = new AutoScrollingJTree();
		tree.setEditable(true);
		
		tree.addFocusListener(this);

		tree.addTreeSelectionListener(new WorkCASETreeSelectionListener());
		
		popup = getDefaultPopup();

		tree.setAutoscrolls(true);
		tree.setToggleClickCount(3);
		tree.setDragEnabled(false);
		
		
		tree.setRowHeight(20);
		tree.setScrollsOnExpand(true);
		CategoriasTreeRenderer mcr = new CategoriasTreeRenderer();
		
		tree.setCellRenderer(mcr);
		tree.setCellEditor(new MyTreeCellEditor(tree,mcr));
		
		tree.addMouseListener(new MyMouseAdapter());
		this.updateTree();
	}
	
	private CategoriaSelectableTreeController original;
	
	public CategoriaSelectableTreeController(CategoriaSelectable observer, CategoriaSelectableTreeController original) {
		super();
		this.observer = observer;
		this.original = original;
		tree = new AutoScrollingJTree();
		tree.setEditable(true);
		
		tree.addFocusListener(this);

		tree.addTreeSelectionListener(new WorkCASETreeSelectionListener());
		
		popup = getDefaultPopup();

		tree.setAutoscrolls(true);
		tree.setToggleClickCount(3);
		tree.setDragEnabled(false);
		
		
		tree.setRowHeight(20);
		tree.setScrollsOnExpand(true);
		CategoriasTreeRenderer mcr = new CategoriasTreeRenderer();
		
		tree.setCellRenderer(mcr);
		tree.setCellEditor(new MyTreeCellEditor(tree,mcr));
		
		tree.addMouseListener(new MyMouseAdapter());
		this.updateTree();
	}
	
	
	private class MyMouseAdapter extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {				
			if (/*selected != null &&*/ e.getButton() == MouseEvent.BUTTON3) {
				
				if (selectedElements != null && selectedElements.size()>1){
					
				}
				else{
					TreePath tp = tree.getClosestPathForLocation(e.getX(), e.getY());
					Categoria el = ((ObserverMutableTreeNode) tp.getLastPathComponent()).getElement();
					observer.setSelectedElement(el);					
				}				
				popup.show(e.getComponent(), e.getX(), e.getY());
			}

		}

	}
	
	private class MyTreeCellEditor extends DefaultTreeCellEditor implements KeyListener{

		public MyTreeCellEditor(JTree tree, DefaultTreeCellRenderer dtcr) {
			super(tree, dtcr);	
			font = com.adapit.portal.util.global.FontDefinitions.getInstance().TreeCellFont;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {			
			super.actionPerformed(evt);
			tree.updateUI();
		}

		public void keyPressed(KeyEvent evt) {
			if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_R){
				String str = this.getCellEditorValue().toString();
				str.replace(".", "::");
				str.replace(" ", "");
				((JTextField)evt.getSource()).setText(str);
			}
		}

		public void keyReleased(KeyEvent evt) {
			// TODO Auto-generated method stub
			
		}

		public void keyTyped(KeyEvent evt) {
			// TODO Auto-generated method stub
			
		}
		
		public void startEditing(){
			prepareForEditing();
			super.startEditingTimer();
		}



		@Override
		protected void prepareForEditing() {
			if (observer != null && observer.getSelectedElement() != null)try {
				super.editingIcon = observer.getSelectedElement().getCategoriaImagem().getSmallIcon(false);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			super.prepareForEditing();
			//super.editingComponent.addKeyListener(this);
		}

	
		
	}
		
	private class AutoScrollingJTree extends JTree implements Autoscroll{
		/**
		 * 
		 */
		private static final long serialVersionUID = 8461949862346436L;
		private int margin = 18;

	    public AutoScrollingJTree() {
			super();
		}

		

		public void autoscroll(Point p) {
			int realrow = getRowForLocation(p.x, p.y);
			Rectangle outer = getBounds();
			realrow = (p.y + outer.y <= margin ? realrow < 1 ? 0 : realrow - 1
					: realrow < getRowCount() - 1 ? realrow + 1 : realrow);
			scrollRowToVisible(realrow);
			expandRow(realrow);
		}

		/*public void expandPath(TreePath parent){
			super.expandPath(parent);
		}*/
		public Insets getAutoscrollInsets() {
			Rectangle outer = getBounds();
			Rectangle inner = getParent().getBounds();
			return new Insets(inner.y - outer.y + margin, inner.x - outer.x
					+ margin, outer.height - inner.height - inner.y + outer.y
					+ margin, outer.width - inner.width - inner.x + outer.x
					+ margin);
		}

		
	}
	
	public Hashtable<String,Categoria> categorias;
	@SuppressWarnings("unchecked")
	public List categoriasList=null;
	
	public ArrayList<Categoria> rootCat=null;
	@SuppressWarnings("unchecked")
	public void updateTree(){
		
		if(original == null){			
			try {
				nodes = new Hashtable<Categoria,ObserverMutableTreeNode>();
				categorias = new Hashtable<String,Categoria>();
				rootCat = new ArrayList<Categoria>();
				categoriasList = RemoteCategoriaService.getInstance().listCategoriasByNullParent(true);
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorias");
				if (categoriasList != null){
					
					Iterator it = categoriasList.iterator();
					while(it.hasNext()){
						Categoria c = (Categoria) it.next();
						rootCat.add(c);
						categorias.put(""+c.getId(),c);
						ObserverMutableTreeNode ob = new ObserverMutableTreeNode(c);
						nodes.put(c, ob);
						root.add(ob);
						createChildrenNodes(ob);
					}
				}
				tree.setModel(new DefaultTreeModel(root));
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		}else{
			categoriasList = original.categoriasList;
			categorias = original.categorias;
			
			nodes = new Hashtable<Categoria,ObserverMutableTreeNode>();
			
			DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorias");
			if (categoriasList != null){
				
				Iterator it = categoriasList.iterator();
				while(it.hasNext()){
					Categoria c = (Categoria) it.next();
					categorias.put(""+c.getId(),c);
					ObserverMutableTreeNode ob = new ObserverMutableTreeNode(c);
					nodes.put(c, ob);
					root.add(ob);
					createChildrenNodes(ob);
				}
			}
			tree.setModel(new DefaultTreeModel(root));
		}
		
		
		
		tree.repaint();
		
	}
	@SuppressWarnings("unchecked")
	public void newTree(){
					
			try {
				nodes = new Hashtable<Categoria,ObserverMutableTreeNode>();
				categorias = new Hashtable<String,Categoria>();
				rootCat = new ArrayList<Categoria>();
				categoriasList = RemoteCategoriaService.getInstance().listCategoriasByNullParent(true);
				DefaultMutableTreeNode root = new DefaultMutableTreeNode("Categorias");
				if (categoriasList != null){
					
					Iterator it = categoriasList.iterator();
					while(it.hasNext()){
						Categoria c = (Categoria) it.next();
						rootCat.add(c);
						categorias.put(""+c.getId(),c);
						ObserverMutableTreeNode ob = new ObserverMutableTreeNode(c);
						nodes.put(c, ob);
						root.add(ob);
						createChildrenNodes(ob);
					}
				}
				tree.setModel(new DefaultTreeModel(root));
			} catch (RuntimeException e) {
				e.printStackTrace();
			}
		
		
		
		
		tree.repaint();
		
	}
	
	public Hashtable<Categoria,ObserverMutableTreeNode> nodes; 
	@SuppressWarnings("unchecked")
	private void createChildrenNodes(ObserverMutableTreeNode node){
		if (node != null && node.getElement() != null){
			Categoria pai = node.getElement();
			try {				
				/*Iterator it = pai.getSubCategorias().iterator();
				while(it.hasNext()){
					Categoria c = (Categoria) it.next();
					ObserverMutableTreeNode ob = new ObserverMutableTreeNode(c);
					nodes.put(c, ob);
					node.add(ob);
					createChildrenNodes(ob);
				}*/
				Iterator it = pai.getSubCategorias()/*list*/.iterator();
				while(it.hasNext()){
					Categoria c = (Categoria) it.next();
					categorias.put(""+c.getId(),c);
					//categorias.put(""+c.getId(),c);
					ObserverMutableTreeNode ob = new ObserverMutableTreeNode(c);
					nodes.put(c, ob);
					node.add(ob);
					createChildrenNodes(ob);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings({ "unchecked", "unused" })
	private void createChildrenNodes2(ObserverMutableTreeNode node){
		if (node != null && node.getElement() != null){
			Categoria pai = node.getElement();
			try {				

				Iterator it = pai.getSubCategorias().iterator();
				while(it.hasNext()){
					Categoria c = (Categoria) it.next();
					categorias.put(""+c.getId(),c);
					
					ObserverMutableTreeNode ob = new ObserverMutableTreeNode(c);
					nodes.put(c, ob);
					node.add(ob);
					createChildrenNodes(ob);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public JPopupMenu getDefaultPopup() {
		popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Editar Categorias");
		menuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				CategoriaInternalFrame c = new CategoriaInternalFrame();				
				AdapitVirtualFrame.getInstance().getMainDesktopPane().add(c);
				c.setVisible(true);
			}
		});
		popup.add(menuItem);
		
		return popup;
	}

	

	public void updateTree(ObserverMutableTreeNode top) {
		//WorkCaseTreeModel treeModel = new WorkCaseTreeModel(top);
		tree.setModel(/*treeModel*/new DefaultTreeModel(top));
		tree.setScrollsOnExpand(true);
		CategoriasTreeRenderer myrend = new CategoriasTreeRenderer();
		tree.setCellRenderer(myrend);
		//tree.setCellEditor(new MyTreeCellEditor(tree, myrend));

	}

	
	
	private Vector<TreePath> selectedElements = new Vector<TreePath>();

	private class WorkCASETreeSelectionListener implements
			TreeSelectionListener {
		
		
		public WorkCASETreeSelectionListener() {
			super();
			
		}

		public void valueChanged(TreeSelectionEvent e) {			
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) (e.getPath().getLastPathComponent());
			if (node == null)
				return;
			if (node instanceof ObserverMutableTreeNode){
				Object o = (Object) ((ObserverMutableTreeNode)node).getElement();
				selected = o;
				observer.setSelectedElement((Categoria)selected);
				selectedNode = ((ObserverMutableTreeNode)node);
				
				//selectedElements.removeAllElements();
				for (int i = 0; i < e.getPaths().length; i++){
					if (e.isAddedPath(i)) selectedElements.add(e.getPaths()[i]);
					else selectedElements.remove(e.getPaths()[i]);
				}			
			}
			else{
				observer.setSelectedElement(null);
				selected = null;
			}
		}
	}

	class WorkCASETreeModelListener implements TreeModelListener {
		
		
		public WorkCASETreeModelListener() {
			super();
			
		}

		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());
			if (node instanceof ObserverMutableTreeNode)try {
				int index = e.getChildIndices()[0];
				node = (ObserverMutableTreeNode) (node.getChildAt(index));
				Categoria element = (Categoria) ((ObserverMutableTreeNode)node).getElement();

				System.out.println(element.getNome());

				node.removeAllChildren();
				tree.updateUI();
			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}

		public void treeNodesInserted(TreeModelEvent e) {

		}

		public void treeNodesRemoved(TreeModelEvent e) {
		}

		public void treeStructureChanged(TreeModelEvent e) {
		}
	}

	/**
	 * @return Returns the selected.
	 */
	public Object getSelected() {
		return selected;
	}

	/**
	 * @param selected
	 *            The selected to set.
	 */
	public void setSelected(Object selected) {
		this.selected = selected;
	}

	@SuppressWarnings("serial")
	private class ElementTextField extends JTextField implements KeyListener{
		private Categoria element;

		public ElementTextField(Categoria element) {
			super();
			this.element = element;
			// super.setBackground(new Color(50,50,250));
			// super.setCaretColor(Color.YELLOW);
			// super.setForeground(Color.YELLOW);
			super.setFont(com.adapit.portal.util.global.FontDefinitions.getInstance().TreeCellFont);
			super.setBorder(BorderFactory.createEmptyBorder());
			this.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {

					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						/*if (ElementTextField.this.element instanceof NamedElement)
							((NamedElement) ElementTextField.this.element)
									.setName(getText());*/
						ElementTextField.this.element.setNome(getText());
						setVisible(false);
						tree.remove(ElementTextField.this);
						tree.updateUI();						
					}
				}
			});
			
			initialize();
		}

		private void initialize() {
			if (element != null) {				
				setText(element.getNome());
			}

		}

		public Categoria getElement() {
			return element;
		}

		public void setElement(Categoria element) {
			this.element = element;
			initialize();
		}

		public void keyPressed(KeyEvent evt) {
			if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_R){
				String str = getText();
				str.replaceAll(".", "::");
				updateUI();
			}
		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}

	public ElementTextField elementTextField;

	public void mouseClicked(MouseEvent e) {
		if (selected != null && e.getButton() == MouseEvent.BUTTON3) {
			popup.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		lastDragged = false;
	}

	public void mouseExited(MouseEvent arg0) {
		lastDragged = false;

	}

	public void mousePressed(MouseEvent arg0) {
		lastDragged = false;
	}
	
	public void mouseReleased(MouseEvent e) {
		if (lastDragged) {
			@SuppressWarnings("unused")
			Cursor c = new Cursor(Cursor.DEFAULT_CURSOR);
			lastDragged = false;
			{
				Object o = tree.getComponentAt(new Point(e.getX(), e.getY()));
				if (o instanceof Categoria) {
					try {
						Categoria pk = (Categoria) o;
						pk.addSubcategoria((Categoria) selected);
						((Categoria) selected).setPai(pk);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		}
	}

	
	public void mouseDragged(MouseEvent e) {
		@SuppressWarnings("unused")
		Cursor c = new Cursor(Cursor.HAND_CURSOR);		
		lastDragged = true;
	}

	public void mouseMoved(MouseEvent arg0) {

	}

	/**
	 * @return Returns the frontier.
	 */
	public int getFrontier() {
		return frontier;
	}

	/**
	 * @param frontier
	 *            The frontier to set.
	 */
	public void setFrontier(int frontier) {
		this.frontier = frontier;
	}

	/**
	 * @return Returns the lastDragged.
	 */
	public boolean isLastDragged() {
		return lastDragged;
	}

	/**
	 * @param lastDragged
	 *            The lastDragged to set.
	 */
	public void setLastDragged(boolean lastDragged) {
		this.lastDragged = lastDragged;
	}

	public void actionPerformed(ActionEvent e) {
		JMenuItem source = (JMenuItem) (e.getSource());
		if (source.getText().equalsIgnoreCase("Delete")) {
			if (this.selected instanceof Categoria) {
				Categoria el = (Categoria) selected;
				if (el.getPai() != null) {
					try {
						el.getPai().removeSubcategoria(el);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else
					el = null;
				selectedNode.removeFromParent();
				tree.updateUI();
			}
		}
		if (source.getText().equalsIgnoreCase("Refresh")) {
			tree.updateUI();
		}
	}

	/**
	 * @return Returns the tree.
	 */
	public JTree getTree() {
		return tree;
	}

	/**
	 * @param tree
	 *            The tree to set.
	 */
	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
	}

	public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void notifyElementChanged() {
		//expandSelectedElement();
		tree.treeDidChange();
		tree.updateUI();	
	}
	
	@SuppressWarnings("unused")
	private void expandSelectedElement() {
		try {
			TreePath parent = new TreePath(new ObserverMutableTreeNode(observer.getSelectedElement()));
			tree.setSelectionPath(parent);
			tree.expandPath(parent);
			expandAll(tree, parent, true);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	@SuppressWarnings("unchecked")
	private void expandAll(JTree tree, TreePath parent, boolean expand) {
        // Traverse children
		TreeNode node = null;
		if (parent.getLastPathComponent() instanceof TreePath)return; 
        node= (TreeNode) parent.getLastPathComponent();
        if (node.getChildCount() >= 0) {
            for (Enumeration e=node.children(); e.hasMoreElements(); ) {
                TreeNode n = (TreeNode)e.nextElement();
                TreePath path = parent.pathByAddingChild(n);
                expandAll(tree, path, expand);
            }
        }
    
        // Expansion or collapse must be done bottom-up
        if (expand) {
            tree.expandPath(parent);
        } else {
            tree.collapsePath(parent);
        }
    }

	
	public Vector<TreePath> getSelectedElements() {
		return selectedElements;
	}

	public void setSelectedElements(Vector<TreePath> selectedElements) {
		this.selectedElements = selectedElements;
	}

	public Hashtable<String, Categoria> getCategorias() {
		return categorias;
	}

	public Hashtable<Categoria, ObserverMutableTreeNode> getNodes() {
		return nodes;
	}

}