package com.adapit.portal.ui.frames.categoria;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.services.remote.RemoteCategoriaService;
import com.adapit.portal.ui.forms.categoria.CategoriaCadastreForm2Dialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaTreeController2Dialog;
import com.adapit.portal.ui.tree.ObserverMutableTreeNode;

@SuppressWarnings("serial")
public class CategoriaDialog extends JDialog {

	private JPanel jContentPane = null;
	private JSplitPane mainSplitPane = null;
	private JPanel arvoreCategoriasPanel = null;
	private CategoriaToolbar2Dialog treeToolBar = null;
	private JScrollPane treeScrollPane = null;
	private JTree jTree = null;

	private Categoria selectedElement;  //  @jve:decl-index=0:
	
	private static CategoriaDialog instance;
	/**
	 * This is the xxx default constructor
	 */
	@SuppressWarnings("static-access")
	public CategoriaDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
		this.instance = this;
		setSelectedElement(null);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(700, 480);
		setModal(true);
		this.setResizable(true);
		this.setTitle("Manutenção de Categorias");
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getTreeToolBar(), BorderLayout.NORTH);
			jContentPane.add(getMainSplitPane(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes mainSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getMainSplitPane() {
		if (mainSplitPane == null) {
			mainSplitPane = new JSplitPane();
			mainSplitPane.setDividerSize(10);
			mainSplitPane.setLeftComponent(getArvoreCategoriasPanel());
			mainSplitPane.setRightComponent(emptyPanel);
			mainSplitPane.setDividerLocation(200);
		}
		return mainSplitPane;
	}
	
	private JPanel emptyPanel = new JPanel();

	private CategoriaCadastreForm2Dialog ccf;
	
	public CategoriaCadastreForm2Dialog getCcf(){
		if (ccf == null){
			ccf = new CategoriaCadastreForm2Dialog(this);
		}
		 return ccf;
	}
	/**
	 * This method initializes arvoreCategoriasPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getArvoreCategoriasPanel() {
		if (arvoreCategoriasPanel == null) {
			arvoreCategoriasPanel = new JPanel();
			arvoreCategoriasPanel.setLayout(new BorderLayout());
			//arvoreCategoriasPanel.add(getTreeToolBar(), BorderLayout.NORTH);
			arvoreCategoriasPanel.add(getTreeScrollPane(), BorderLayout.CENTER);
		}
		return arvoreCategoriasPanel;
	}

	/**
	 * This method initializes treeToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getTreeToolBar() {
		if (treeToolBar == null) {
			treeToolBar = new CategoriaToolbar2Dialog(this);
		}
		return treeToolBar;
	}

	/**
	 * This method initializes treeScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTreeScrollPane() {
		if (treeScrollPane == null) {
			treeScrollPane = new JScrollPane();
			treeScrollPane.setBorder(null);
			treeScrollPane.setViewportView(getJTree());
		}
		return treeScrollPane;
	}

	private CategoriaTreeController2Dialog categoriasTreeController;
	/**
	 * This method initializes jTree	
	 * 	
	 * @return javax.swing.JTree	
	 */
	private JTree getJTree() {
		if (jTree == null) {
						
			jTree = getCategoriasTreeController().getTree();
		}
		return jTree;
	}
	
	public CategoriaTreeController2Dialog getCategoriasTreeController(){
		if (categoriasTreeController == null){
			categoriasTreeController = new CategoriaTreeController2Dialog(this);
		}
		return categoriasTreeController;
	}

	
	public void addCategoria(){
		if (selectedElement!= null){
			try {
				Categoria pai = selectedElement;
				Categoria c = new Categoria();
				c.setNome("categoria"+catNumber++);
				c.setPai(pai);
				//c.setCategoriaImagem(new CategoriaImagem());
				/*LeilaoVirtualFrame.getInstance().getProgressBar().setString("Salvando...");
				LeilaoVirtualFrame.getInstance().getProgressBar().setStringPainted(true);
				LeilaoVirtualFrame.getInstance().getProgressBar().setIndeterminate(true);
				LeilaoVirtualFrame.getInstance().getProgressBar().updateUI();*/
				try {
					c = RemoteCategoriaService.getInstance().saveOrUpdate(c);
					//c = RemoteCategoriaService.getInstance().getCategoriaByName(c.getNome());
					c.setPai(pai);
					pai.getSubCategorias().add(c);
					RemoteCategoriaService.getInstance().saveOrUpdate(pai);
					//RemoteCategoriaService.getInstance().saveOrUpdate(pai);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*LeilaoVirtualFrame.getInstance().getProgressBar().setIndeterminate(false);*/
				getCategoriasTreeController().setSelected(c);
				//getCategoriasTreeController().updateTree();
				ObserverMutableTreeNode cNode = new ObserverMutableTreeNode(c);
				ObserverMutableTreeNode parentNode = getCategoriasTreeController().nodes.get(""+pai.getId());
				parentNode.add(cNode);
				getCategoriasTreeController().nodes.put(""+c.getId(), cNode);
				getCategoriasTreeController().categorias.put(""+c.getId(), c);
				try {
					getCategoriasTreeController().expandNode(cNode, true);
					getCategoriasTreeController().getTree().updateUI();
					
				} catch (RuntimeException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				setSelectedElement(c);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			try {
				Categoria c = new Categoria();
				c.setNome("categoria"+catNumber++);
				c.setPai(null);
				try {
					c = /*c = (Categoria)*/ RemoteCategoriaService.getInstance().saveOrUpdate(c);
					//c = RemoteCategoriaService.getInstance().getCategoriaByName(c.getNome());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (getCategoriasTreeController().root == null){
					getCategoriasTreeController().updateTree();
					try {
						getCategoriasTreeController().getTree().updateUI();									
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else{
					ObserverMutableTreeNode cNode = new ObserverMutableTreeNode(c);
					getCategoriasTreeController().root.add(cNode);
					getCategoriasTreeController().nodes.put(""+c.getId(), cNode);
					getCategoriasTreeController().categorias.put(""+c.getId(), c);
					try {
						getCategoriasTreeController().expandNode(cNode, true);
						getCategoriasTreeController().getTree().updateUI();
						
					} catch (RuntimeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}						
				setSelectedElement(c);
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cut = null;
		treeToolBar.enableToPaste(false);
	}
	/**
	 * This method initializes addCategoriaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	
	static int catNumber=1;

	
	public void removeCategoria(){
		if (selectedElement != null && selectedElement.getPai() != null){
			try {
				selectedElement.getPai().getSubCategorias().remove(selectedElement);
				//RemoteCategoriaService.getInstance().saveOrUpdate(selectedElement.getPai());
				RemoteCategoriaService.getInstance().deleteById(selectedElement.getId());
				ObserverMutableTreeNode node = getCategoriasTreeController().nodes.remove(""+selectedElement.getId());
				if (node != null) node.removeFromParent();
				getCategoriasTreeController().getTree().updateUI();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else try {
			RemoteCategoriaService.getInstance().deleteById(selectedElement.getId());
			ObserverMutableTreeNode node = getCategoriasTreeController().nodes.remove(""+selectedElement.getId());
			try {
				if (node != null) node.removeFromParent();
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getCategoriasTreeController().getTree().updateUI();
		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("RemoteCategoriaService_deleteByIdError")){
				JOptionPane.showMessageDialog(CategoriaDialog.this,"Para remover esta categoria, primeiro remova todos os produtos desta categoria!","Erro de integridade referencial",JOptionPane.ERROR_MESSAGE);
				return;
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setSelectedElement(null);
		cut = null;
		treeToolBar.enableToPaste(false);
	}
	/**
	 * This method initializes removeCategoriaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	private Categoria cut;
	public Categoria getCut() {
		return cut;
	}

	public void setCut(Categoria cut) {
		this.cut = cut;
	}

	/**
	 * This method initializes recortarCategoriaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	
	public void cutCategoria(){
		cut = selectedElement;
		treeToolBar.enableToPaste(true);
	}

	/**
	 * This method initializes colarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	
	public void pasteCategoria(){
		if (selectedElement != null){
			selectedElement.getSubCategorias().add(cut);
			cut.setPai(selectedElement);
			try {
				RemoteCategoriaService.getInstance().saveOrUpdate(cut);
				RemoteCategoriaService.getInstance().saveOrUpdate(selectedElement);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (cut.getPai() != null){
			cut.getPai().getSubCategorias().remove(cut);
			cut.setPai(null);
			try {
				RemoteCategoriaService.getInstance().saveOrUpdate(cut);							
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}					
		
		ObserverMutableTreeNode node = getCategoriasTreeController().nodes.get(""+cut.getId());
		if (node != null) node.removeFromParent();
		
		if (cut.getPai() != null){
			ObserverMutableTreeNode nodep = getCategoriasTreeController().nodes.get(""+cut.getPai().getId());
			nodep.add(node);
		}
		else getCategoriasTreeController().root.add(node);
		
		try {
			getCategoriasTreeController().getTree().updateUI();
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cut = null;
		treeToolBar.enableToPaste(false);
	}

	/**
	 * This method initializes saveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	/**
	 * This method initializes refreshButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	
	
	public void updateTree(){
		if (getCategoriasTreeController() == null) return;
		getCategoriasTreeController().updateTree();
		treeToolBar.enableButtons(false);
		
	}

	public Categoria getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Categoria selectedElement) {
		this.selectedElement = selectedElement;
		int dl = getMainSplitPane().getDividerLocation();
		if (dl <=0) dl = 250;
		if (selectedElement != null){
			getMainSplitPane().setRightComponent(getCcf());
			getMainSplitPane().setDividerLocation(dl);
			getCcf().editRegister(selectedElement);
			treeToolBar.enableButtons(true);
			/*getSaveButton().setEnabled(true);
			getRecortarCategoriaButton().setEnabled(true);
			getColarButton().setEnabled(true);
			getAddCategoriaButton().setEnabled(true);
			getRemoveCategoriaButton().setEnabled(true);*/
		}
		else{
			getMainSplitPane().setRightComponent(emptyPanel);
			getMainSplitPane().setDividerLocation(dl);
			treeToolBar.getSaveButton().setEnabled(false);
			treeToolBar.getRecortarCategoriaButton().setEnabled(false);
			if (cut == null) treeToolBar.getColarButton().setEnabled(false);
			else treeToolBar.getColarButton().setEnabled(true);
			treeToolBar.getAddCategoriaButton().setEnabled(true);
			treeToolBar.getRemoveCategoriaButton().setEnabled(false);
		}
	}

	public static CategoriaDialog getInstance() {
		if (instance==null) instance= new CategoriaDialog();
		return instance;
	}
	
	

} 