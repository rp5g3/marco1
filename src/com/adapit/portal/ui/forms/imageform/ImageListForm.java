package com.adapit.portal.ui.forms.imageform;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.imagem.CadastrarImagemDialog;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.adapit.portal.util.global.FilterResultSize;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;


@SuppressWarnings("serial")
public class ImageListForm extends JPanel implements CategoriaSelectable{

	
	
	private JTabbedPane filtersTabbedPane;

	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();

	@SuppressWarnings({ "unchecked", "unused" })
	private Map hashComps = new java.util.HashMap();

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	//private JComboBox categoriaComboBox;

	private JPanel rotuloFieldsPanel;

	private JButton pesquisarButton;

	private JPanel basePanel;

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel buttonsPanel;

	private JButton novaImagemButton;

	private JButton removerButton;

	private Categoria selectedElement;  //  @jve:decl-index=0:
	
	/*private StringQueryKind byDescKind=StringQueryKind.LIKE;
	private ProdutoLoteQueryKind byProdLote = ProdutoLoteQueryKind.AMBOS;
*/
	public ImageListForm() {

		initialize();
	}

	private void initialize() {
		reportResultsLabel = new JLabel();
		reportResultsLabel.setBounds(new Rectangle(10, 157, 266, 22));
		
		reportResultsLabel.setText("");
		imagemLabel = new JLabel();
		imagemLabel.setBounds(new Rectangle(554, 8, 118, 20));
		imagemLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imagemLabel.setFont(new Font("Arial", Font.BOLD, 12));
		imagemLabel.setText("Imagem");
		setSize(new Dimension(703, 479));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getFiltersTabbedPane());
		add(getBasePanel());
		this.add(getImagePanel(), null);
		this.add(imagemLabel, null);
		this.add(reportResultsLabel, null);
		add(getResultNumberPanel());
		
	}
	
	private JPanel resultNumberPanel = null;

	private JPanel getResultNumberPanel() {
		if (resultNumberPanel == null) {
			resultNumberPanel = new JPanel();
			GridLayout g = new GridLayout(1,20);
			g.setHgap(1);
			g.setVgap(1);
			resultNumberPanel.setLayout(g);
			resultNumberPanel.setBounds(new Rectangle(284, 157, 406, 22));
			
		}
		return resultNumberPanel;
	}
	
	int buttons=10;
	
	private void changeResultNumberPanel() {
		resultNumberPanel.removeAll();
		GridLayout g = new GridLayout(1, buttons);
		g.setHgap(1);
		g.setVgap(1);
		resultNumberPanel.setLayout(g);

	}
	private Integer countFirst=0;  //  @jve:decl-index=0:
	
	int max=FilterResultSize.imagemMaxSize;
	int total;
	int secBegin=0;
	int secCount;
	private void criarListar(){		
		
		total = countFirst;
		final int number = total/max;
		int rest = total%max;
		secBegin=0;
		secCount=0;
		reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando de 1 - " + max);
		getResultNumberPanel();
		changeResultNumberPanel();
		
		for (int i=0; i < buttons; i++){
			if (i < number){
				JButton jb = new JButton((i+1)+"");
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							int dif = (countFirst - ((i-1)*max));
							if (countFirst > (i-1)*max){
								listar((i-1)*max);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando de "+((i-1)*max) + " até " + (((i-1)*max)+max));
							}
							else{
								int oldmax = max;
								max = dif;
								listar((i-1)*max);	
								
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando de "+((i-1)*max) + " até " + (((i-1)*max)+max));
								
								max = oldmax;
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else if(rest > 0){				
				rest = 0;
				JButton jb = new JButton((i+1)+"");
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							listar((i-1)*max);
							reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando de "+((i-1)*max) + " até " + (((i-1)*max)+max));
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else{
				JLabel jl = new JLabel();
				resultNumberPanel.add(jl);
			}
		}
		
		if (total < max){
			try{
				listar(0);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				listar(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultNumberPanel.updateUI();
	}
	
	@SuppressWarnings("unchecked")
	private void listar(int firstResult){
		List prodList = null;
		Categoria cat = selectedElement;
		String rot = getRotuloTextField().getText();
		String desc = getDescricaoTextField().getText();
		if (tipoLista == TipoListaImagem.Todas){
			try {
				prodList = RemoteImagemService.getInstance().listAll(firstResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (tipoLista == TipoListaImagem.Rotulo){
			try {
				prodList = RemoteImagemService.getInstance().listLikeRotulo(rot,firstResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (tipoLista == TipoListaImagem.Descricao){
			try {
				prodList = RemoteImagemService.getInstance().listLikeDescricao(desc,firstResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if (tipoLista == TipoListaImagem.Categoria){
			try {
				prodList = RemoteImagemService.getInstance().listByCategoriaId(cat.getId(),firstResult);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		updateTable(prodList);
	}
	
	
	protected JPanel filtersPanel;

	protected JPanel getFiltersPanel() {
		if (filtersPanel == null) {
			filtersPanel = new JPanel();
			filtersPanel.setSize(new Dimension(515, 159));
			filtersPanel.setLocation(new java.awt.Point(15, 20));
			filtersPanel.setLayout(null);
			filtersPanel.add(getButtonPanel());
			filtersPanel.add(getPesquisarDescricaoButton(), null);
			
		}
		return filtersPanel;
	}	
	protected JPanel buttonPanel;
	protected JLabel descricaoLabel;
	
	protected JPanel getButtonPanel() {

		if (buttonPanel == null) {
			descricaoLabel = new JLabel();
			descricaoLabel.setText("Informe a descrição da imagem para pesquisar");
			descricaoLabel.setFont(new Font("Arial", Font.BOLD, 12));
			descricaoLabel.setHorizontalAlignment(SwingConstants.CENTER);
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new BorderLayout());
			buttonPanel.setBounds(new Rectangle(9, 10, 487, 36));
			buttonPanel.add(descricaoLabel, BorderLayout.NORTH);
			buttonPanel.add(getDescricaoTextField(), BorderLayout.CENTER);
		}
		return buttonPanel;
	}

	private JTextField descricaoTextField;
	private JTextField getDescricaoTextField() {
		if (descricaoTextField == null) {
			descricaoTextField = new JTextField();
		}
		return descricaoTextField;
	}

	private JButton pesquisarDescricaoButton;
	private JButton getPesquisarDescricaoButton() {
		if (pesquisarDescricaoButton == null) {
			pesquisarDescricaoButton = new JButton();
			pesquisarDescricaoButton.setBounds(new Rectangle(169, 64, 155, 26));
			pesquisarDescricaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.png")));
			pesquisarDescricaoButton.setText("Pesquisar");
			pesquisarDescricaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarLikeDescription(getDescricaoTextField().getText());
				}
			});
		}
		return pesquisarDescricaoButton;
	}
	
	protected JPanel porRotuloPanel;

	protected JPanel getPorRotuloPanel() {
		if (porRotuloPanel == null) {
			porRotuloPanel = new JPanel();
			porRotuloPanel.setSize(new Dimension(515, 159));
			porRotuloPanel.setLocation(new java.awt.Point(15, 20));
			porRotuloPanel.setLayout(null);
			porRotuloPanel.add(getRotuloFieldsPanel());
			porRotuloPanel.add(getPesquisarRotuloButton(), null);
			
		}
		return porRotuloPanel;
	}	
	
	protected JPanel getRotuloFieldsPanel() {

		if (rotuloFieldsPanel == null) {
			rotuloLabel = new JLabel();
			rotuloLabel.setText("Informe o rótulo da imagem para pesquisar");
			rotuloLabel.setFont(new Font("Arial", Font.BOLD, 12));
			rotuloLabel.setHorizontalAlignment(SwingConstants.CENTER);
			rotuloFieldsPanel = new JPanel();
			rotuloFieldsPanel.setLayout(new BorderLayout());
			rotuloFieldsPanel.setBounds(new Rectangle(9, 10, 487, 36));
			rotuloFieldsPanel.add(rotuloLabel, BorderLayout.NORTH);
			rotuloFieldsPanel.add(getRotuloTextField(), BorderLayout.CENTER);
		}
		return rotuloFieldsPanel;
	}

	private JTextField getRotuloTextField() {
		if (rotuloTextField == null) {
			rotuloTextField = new JTextField();
		}
		return rotuloTextField;
	}


	private JButton getPesquisarRotuloButton() {
		if (pesquisarRotuloButton == null) {
			pesquisarRotuloButton = new JButton();
			pesquisarRotuloButton.setBounds(new Rectangle(169, 64, 155, 26));
			pesquisarRotuloButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.png")));
			pesquisarRotuloButton.setText("Pesquisar");
			pesquisarRotuloButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarLikeRotulo(getRotuloTextField().getText());
				}
			});
		}
		return pesquisarRotuloButton;
	}
	
	
	protected JPanel categoriaFilterPanel;

	protected JPanel getCategoriaFilterPanel() {
		if (categoriaFilterPanel == null) {
			refreshLabel = new JLabel();
			refreshLabel.setBounds(new Rectangle(390, 28, 136, 22));
			refreshLabel.setHorizontalAlignment(SwingConstants.CENTER);
			refreshLabel.setText("Atualizar as categorias");
			categoriaFilterPanel = new JPanel();
			categoriaFilterPanel.setSize(new Dimension(515, 159));
			categoriaFilterPanel.setLocation(new java.awt.Point(15, 20));
			categoriaFilterPanel.setLayout(null);
			categoriaFilterPanel.add(getTreeScrollPane(), null);
			categoriaFilterPanel.add(getPesquisarButton(), null);
			categoriaFilterPanel.add(getRefreshTree(), null);
			categoriaFilterPanel.add(refreshLabel, null);
		}
		return categoriaFilterPanel;
	}

	protected JTabbedPane getFiltersTabbedPane() {
		if (filtersTabbedPane == null) {
			filtersTabbedPane = new JTabbedPane();
			filtersTabbedPane.setSize(new Dimension(533, 140));
			filtersTabbedPane.setLocation(new java.awt.Point(10, 10));
			filtersTabbedPane.add(getListarTodasImgsPanel(),"Listar Todas");
			filtersTabbedPane.add(getFiltersPanel(),"Pela Descrição da Imagem");
			filtersTabbedPane.add(getCategoriaFilterPanel(), "Pela Categoria da Imagem");
			filtersTabbedPane.add(getPorRotuloPanel(), "Pelo Rótulo da Imagem");
			
			
		}
		return filtersTabbedPane;
	}
	protected JButton refreshTree;
	private boolean inserirTree = true;
	protected JButton getRefreshTree(){
		if (refreshTree == null){
			refreshTree = new JButton();
			refreshTree.setToolTipText("Atualizar as categorias");
			refreshTree.setBounds(new Rectangle(446, 6, 20, 20));
			refreshTree.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshTree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					if (inserirTree){
						treeScrollPane.add(getTreeController().getTree());
						treeScrollPane.setViewportView(getTreeController().getTree());
						inserirTree = false;
					}
					treeController.newTree();
				}				
			});
		}
		return refreshTree;
	}
	
	private JScrollPane treeScrollPane;
	
	protected JScrollPane getTreeScrollPane(){
		if(treeScrollPane == null){
			treeScrollPane = new JScrollPane();
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			/*treeScrollPane.add(getTreeController().getTree());
			treeScrollPane.setViewportView(getTreeController().getTree());*/
			treeScrollPane.setBounds(new Rectangle(9, 8, 380, 81));
		}
		return treeScrollPane;
	}

	protected CategoriaSelectableTreeController treeController;

	private JPanel imagePanel = null;

	private JButton pesquisaTodasButton = null;
	
	protected CategoriaSelectableTreeController getTreeController(){
		if (treeController == null){
			treeController = new CategoriaSelectableTreeController(this);
			treeController.getTree().setEnabled(true);
		}
		return treeController;
	}
	
	



	enum TipoListaImagem{
		Todas,Categoria,Descricao,Rotulo
	}
	
	private TipoListaImagem tipoLista = TipoListaImagem.Todas; 
	
	protected JButton getPesquisarButton() {

		if (pesquisarButton == null) {
			pesquisarButton = new JButton("Pesquisar");
			pesquisarButton.setHorizontalTextPosition(SwingConstants.CENTER);
			pesquisarButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			pesquisarButton.setBounds(new Rectangle(411, 49, 99, 41));
			pesquisarButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/read_obj.gif", 15, 15));
			pesquisarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					pesquisarByCategoria();
				}								
			});
		}
		return pesquisarButton;
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarLikeRotulo(String rot){
		tipoLista = TipoListaImagem.Rotulo; 
		try {
			countFirst = RemoteImagemService.getInstance().countLikeRotulo(rot);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		try {			
			List<Imagem> prodList = RemoteImagemService.getInstance().listLikeRotulo(rot,0);
			updateTable(prodList);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		criarListar();
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarLikeDescription(String desc){
		tipoLista = TipoListaImagem.Descricao; 
		try {
			countFirst = RemoteImagemService.getInstance().countLikeDescricao(desc);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {			
			List<Imagem> prodList = RemoteImagemService.getInstance().listLikeDescricao(desc,0);
			updateTable(prodList);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		criarListar();
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarByCategoria(){
		tipoLista = TipoListaImagem.Categoria; 
		try {
			countFirst = RemoteImagemService.getInstance().countByCategoriaId(selectedElement.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		try {			
			if (selectedElement != null && selectedElement.getId() > 0){
				
				List<Imagem> prodList = RemoteImagemService.getInstance().listByCategoriaId(selectedElement.getId(),0);
				updateTable(prodList);	
			}else{
				List<Imagem> prodList = RemoteImagemService.getInstance().listAll(0);				
				updateTable(prodList);	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		criarListar();
	}
	
	@SuppressWarnings("unchecked")
	private void pesquisarTodas(){
		tipoLista = TipoListaImagem.Todas; 
		try {
			countFirst = RemoteImagemService.getInstance().countAll();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		criarListar();
		try {
			List<Imagem> prodList = RemoteImagemService.getInstance().listAll(0);
			
			updateTable(prodList);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	@SuppressWarnings("unchecked")
	private void updateTable(List prodList) {
		getBaseTable().setElements(prodList);
		getBaseTable().updateTable();
	}

	protected JPanel getBasePanel() {
		if (basePanel == null) {
			selecioneImagemLabel = new JLabel();
			selecioneImagemLabel.setBounds(new Rectangle(421, 0, 253, 24));
			selecioneImagemLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			selecioneImagemLabel.setText("Selecione a linha para apresentar a imagem");
			listaImagensLabel = new JLabel();
			listaImagensLabel.setBounds(new Rectangle(0, 0, 253, 24));
			listaImagensLabel.setText("Lista de imagens encontradas na pesquisa");
			basePanel = new JPanel();
			basePanel.setSize(new Dimension(681, 289));
			basePanel.setLocation(new Point(10, 183));
			basePanel.setLayout(null);
			basePanel.add(getBaseTableScrollPane());
			basePanel.add(getButtonsPanel());
			basePanel.add(listaImagensLabel, null);
			basePanel.add(selecioneImagemLabel, null);
		}
		return basePanel;
	}

	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setSize(new Dimension(675, 216));
			baseTableScrollPane.setLocation(new Point(0, 24));
			baseTableScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	public BaseTable getBaseTable() {
		if (baseTable == null) {
			baseTable = new BaseTable();
			baseTable.addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent evt) {
					getRemoverButton().setEnabled(true);	
					getEditarImagemButton().setEnabled(true);	
					//updateImage();
				}

				public void focusLost(FocusEvent evt) {
				}				
			});
			return baseTable;
		}
		return baseTable;
	}

	public JPanel getButtonsPanel() {

		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setSize(new Dimension(675, 34));
			buttonsPanel.setLocation(new Point(0, 243));
			buttonsPanel.setLayout(new java.awt.FlowLayout());
			buttonsPanel.add(getNovaImagemButton());
			buttonsPanel.add(getEditarImagemButton());
			buttonsPanel.add(getRemoverButton());
		}
		return buttonsPanel;
	}

	public ActionListener defaultNovaImagem;
	public JButton getNovaImagemButton() {
		if (novaImagemButton == null) {
			novaImagemButton = new JButton("Nova Imagem");
			novaImagemButton.setSize(new java.awt.Dimension(80, 22));
			novaImagemButton.setLocation(new java.awt.Point(0, 0));
			novaImagemButton.setIcon(new ImageIcon(getClass().getResource("/imgs/picture_add.png")));
			novaImagemButton.setEnabled(true);
			defaultNovaImagem = new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					CadastrarImagemDialog c = new CadastrarImagemDialog();
					c.getImagemCadastreForm().newRegister();
					c.setVisible(true);
				}				
			};
			novaImagemButton.addActionListener(defaultNovaImagem);
		}
		return novaImagemButton;
	}
	protected JButton editarImagemButton;
	
	protected JButton getEditarImagemButton() {
		if (editarImagemButton == null) {
			editarImagemButton = new JButton("Editar Imagem");
			editarImagemButton.setSize(new java.awt.Dimension(80, 22));
			editarImagemButton.setLocation(new java.awt.Point(0, 0));			
			editarImagemButton.setIcon(new ImageIcon(getClass().getResource("/imgs/picture_edit.png")));
			editarImagemButton.setEnabled(false);
			editarImagemButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					Imagem img = (Imagem) getBaseTable().getElements().get(getBaseTable().getSelectedRow());
					//LeilaoVirtualFrame.getInstance().editarImagem(img);
					CadastrarImagemDialog c = new CadastrarImagemDialog();
					c.getImagemCadastreForm().editRegister(img);
					c.setVisible(true);
				}				
			});
		}
		return editarImagemButton;
	}
	
	@SuppressWarnings("unchecked")
	public static void removerReferenciaImagem(Imagem imagem){
		try {
			RemoteImagemService.getInstance().removerReferenciaImagemComercialSolution(imagem.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.getTransaction().begin();
			//String query="delete im from Imagem im join im.produtos prod where im.id="+imagem.getId();
			//s.createQuery(query).executeUpdate();
			imagem = (Imagem) s.get(Imagem.class, imagem.getId());
			String query="select prod.id from Produto prod join prod.imagens im where im.id="+imagem.getId();
			List prodids= s.createQuery(query).list();
			Iterator it = prodids.iterator();
			
			s.getTransaction().begin();
			while(it.hasNext()){
				int pid = (Integer) it.next();			
				Produto p = (Produto) s.get(Produto.class,pid);
				p.getImagens().remove(imagem);
				s.merge(p);						
			}
			s.delete(imagem);
			
			
			s.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}*/
		
	}
/*	@SuppressWarnings({ "unchecked", "unused" })
	private void removerImagem2(Imagem imagem){
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.getTransaction().begin();
			imagem = (Imagem) s.get(Imagem.class, imagem.getId());
			imagem.setProdutos(new ArrayList());
			s.merge(imagem);
			s.delete(imagem);
			s.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			if (s != null) s.getTransaction().rollback();			
		}finally{
			if (s != null) s.close();
		}
		
	}*/


	protected JButton getRemoverButton() {

		if (removerButton == null) {
			removerButton = new JButton(messages.getMessage("Apagar") + " Imagem Selecionada");
			removerButton.setSize(new java.awt.Dimension(80, 22));
			removerButton.setLocation(new java.awt.Point(0, 44));
			removerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/picture_delete.png")));
			removerButton.setEnabled(false);
			removerButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					try {
						int row = getBaseTable().getSelectedRow();
						if (row > -1){
							Imagem im = (Imagem) getBaseTable().getElements().get(row);
							int resp = JOptionPane.showConfirmDialog(ImageListForm.this, "Apagar esta imagem implicará em apagá-la dos produtos!"+'\n'+"Você quer mesmo assim apagar esta imagem?", "Remover imagem", JOptionPane.YES_NO_OPTION);
							if (resp == JOptionPane.YES_OPTION){
								try {
									removerReferenciaImagem(im);
									selectedElement = null;
									pesquisarByCategoria();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					} catch (RuntimeException e) {
						e.printStackTrace();
					}
				}				
			});
		}
		return removerButton;
	}

	/**
	 * This method initializes imagePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getImagePanel() {
		if (imagePanel == null) {
			imagePanel = new JPanel();
			//imagePanel.setLayout(new GridBagLayout());
			
			
			imagePanel.setLayout(new BorderLayout());
			imagePanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
			imagePanel.setBounds(new Rectangle(553, 31, 120, 119));
			//imgPanelImage.setLocation(new Point(130, 55));
			//imgPanelImage.setSize(new Dimension(260, 260));
			imagePanel.add(getImgLabelImage(), BorderLayout.CENTER);
		}
		return imagePanel;
	}
	
	private JLabel imgLabelImage;
	private int scale=800;

	private JLabel imagemLabel = null;

	private JLabel listaImagensLabel = null;

	private JLabel selecioneImagemLabel = null;

	private JLabel reportResultsLabel = null;

	private JLabel rotuloLabel = null;

	private JTextField rotuloTextField = null;

	private JButton pesquisarRotuloButton = null;

	private JLabel refreshLabel = null;

	private JPanel listarTodasImgsPanel = null;

	
	protected JLabel getImgLabelImage() {
		if (imgLabelImage == null) {
			imgLabelImage = new JLabel();
			imgLabelImage.setHorizontalTextPosition(SwingConstants.CENTER);
			imgLabelImage.setHorizontalAlignment(SwingConstants.CENTER);
			imgLabelImage.setToolTipText("Duplo clique para mostrar a imagem em tamanho grande");
			imgLabelImage.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent evt) {
					if (evt.getClickCount() != 2) return;
					Imagem img = (Imagem) getBaseTable().getElements().get(getBaseTable().getSelectedRow());
					if (img != null){
						try {
							ViewFullImageDialog jd = new ViewFullImageDialog(img,scale,scale);							
							jd.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}else{
						JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(),
								"Primeiro selecione a imagem na lista de imagens","Necessário selecionar a imagem",JOptionPane.WARNING_MESSAGE);
					}
				}

				@Override
				public void mouseEntered(MouseEvent evt) {
					imgLabelImage.setBorder(BorderFactory.createLineBorder(Color.RED));
					
				}

				@Override
				public void mouseExited(MouseEvent evt0) {					
					imgLabelImage.setBorder(null);
				}
				
			});
			
			
		}
		return imgLabelImage;
	}
	
	
	private void updateImage(){
		try {
			Imagem img = (Imagem) getBaseTable().getElements().get(getBaseTable().getSelectedRow());
			if (img.getSmallImageBytes() == null){
				/*try{
					Byte[] fullbytes = RemoteImagemService.getInstance().getFullImageBytesFromImage(img.getId());
					
					byte[] full = new byte[fullbytes.length];
					for(int i=0; i < fullbytes.length; i++){
						Byte b = fullbytes[i];
						full[i]=b.byteValue();
					}
					img.setFullImageBytes(full);
				}catch (Exception e) {
					e.printStackTrace();
				}*/
				try{
					byte[] smallbytes = RemoteImagemService.getInstance().getSmallImageBytesFromImage(img.getId());
					if (smallbytes == null) return;
					
					/*byte[] small = new byte[smallbytes.length];
					for(int i=0; i < smallbytes.length; i++){
						Byte b = smallbytes[i];
						small[i]=b.byteValue();
					}*/
					img.setSmallImageBytes(smallbytes);
				}catch (Exception e) {
					e.printStackTrace();
				}
				getImgLabelImage().setIcon(img.getSmallImageIcon(true));
			}
			else getImgLabelImage().setIcon(img.getSmallImageIcon(false));
			//getImgLabelImage().setIcon(img.getScaledImage(155));
			getImagePanel().updateUI();
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	

	/**
	 * This method initializes pesquisaTodasButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getPesquisaTodasButton() {
		if (pesquisaTodasButton == null) {
			pesquisaTodasButton = new JButton();
			pesquisaTodasButton.setText("Listar Todas as Imagens Cadastradas");
			//pesquisaTodasButton.setHorizontalTextPosition(SwingConstants.CENTER);
			pesquisaTodasButton.setBounds(new Rectangle(125, 34, 305, 26));
			pesquisaTodasButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/read_obj.gif", 15, 15));
			pesquisaTodasButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					pesquisarTodas();
				}
			});
		}
		return pesquisaTodasButton;
	}



	/**
	 * This method initializes listarTodasImgsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getListarTodasImgsPanel() {
		if (listarTodasImgsPanel == null) {
			listarTodasImgsPanel = new JPanel();
			listarTodasImgsPanel.setLayout(null);
			listarTodasImgsPanel.add(getPesquisaTodasButton(), null);
		}
		return listarTodasImgsPanel;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(700, 508));
				gui.add(new ImageListForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	
	@SuppressWarnings("unchecked")
	public class BaseTable extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public BaseTable() {
			super();
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			initialize();
		}

		public BaseTable(List elements) {
			super();
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			initialize();
		}
		
		@SuppressWarnings("deprecation")
		private void initialize(){
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			
			//highlighters..addHighlighter(new AlternateRowHighlighter());
			setHighlighters(highlighters);
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof Imagem) {
						Imagem imagem = (Imagem) obj;
						
						try{
							
							values[i][0] = imagem.getId();
							values[i][1] = imagem.getRotulo();
							values[i][2] = imagem.getDescription();
							if (imagem.getCategoria() != null){
								try {
									//Categoria c = LocalCategoriaService.getInstance().getCategoriaById(imagem.getCategoria().getId());
									//imagem.setCategoria(c);
									values[i][3] = imagem.getCategoria().getNome();
								} catch (Exception e) {
									e.printStackTrace();
								} 
							}
							
							
						}catch(Exception ex){
							ex.printStackTrace();
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(30);
				getColumnModel().getColumn(1).setPreferredWidth(140);
				getColumnModel().getColumn(2).setPreferredWidth(240);
				getColumnModel().getColumn(3).setPreferredWidth(100);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(30);
				getColumnModel().getColumn(1).setPreferredWidth(140);
				getColumnModel().getColumn(2).setPreferredWidth(240);
				getColumnModel().getColumn(3).setPreferredWidth(100);
				updateUI();
			}
		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {
				/*System.out.println( e.getPropertyName().equals("swingx.clicked"));*/
				if (getSelectedRow() > -1 && e.getPropertyName().equals("swingx.clicked"))
					updateImage();
				/*com.adapit.portal.ui.forms.baseleilao.ui.ProdutoListForm.BaseTable jt = (com.adapit.portal.ui.forms.baseleilao.ui.ProdutoListForm.BaseTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Produto) {
							com.adapit.portal.entidades.Produto produto = (com.adapit.portal.entidades.Produto) obj;
							if (col == 0)
								produto.setDescricao(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 1)
								produto.setAvaliacao((java.lang.Float
										.parseFloat((java.lang.String) jt
												.getValueAt(row, col))));
							if (col == 2)
								produto
										.setCategoria((com.adapit.portal.entidades.Categoria) ((javax.swing.JComboBox) jt
												.getValueAt(row, col))
												.getSelectedItem());
							// if (col ==2)
							// produto.getNome((com.adapit.portal.entidades.Categoria)((javax.swing.JComboBox)jt.getValueAt(row,
							// col)).getSelectedItem());
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}*/
			}

		}
		//Converte um double em moeda (apenas 2 casas decimais) 
		 public double doubleParaMoeda(double value){
		         double currency = new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
		         return currency;
		     }
		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class, String.class, String.class,
					JComboBox.class};

			boolean canEdit[] = new boolean[] { false, false, false, false };

			public BaseTableModel(Object[][] values) {
				super(values, new String[] {
						"Id",
						"Rótulo",
						"Descrição",
						"Categoria"});
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}

		}

	}

	public Categoria getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(Categoria selectedElement) {
		this.selectedElement = selectedElement;
	}

	public Imagem getSelectedImage() {
		Imagem img = null;
		int row = getBaseTable().getSelectedRow();
		if(row >-1){
			img = (Imagem) getBaseTable().getElements().get(row);
		}
		return img;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
