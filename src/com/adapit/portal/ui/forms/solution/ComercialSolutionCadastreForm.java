package com.adapit.portal.ui.forms.solution;


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
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.CommercialSolutionType;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.forms.imageform.ViewFullImageDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.Moeda;


@SuppressWarnings("serial")
public class ComercialSolutionCadastreForm extends JPanel implements CategoriaSelectable{
	
	private JTextPane descricaoTextPane;
	
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private ComercialSolution sol = new ComercialSolution();  //  @jve:decl-index=0:
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel descricaoTextAreaLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JPanel avaliacaoCategoriaPanel;
	
	private JFormattedTextField avaliacaoTextField;
	
	private JLabel avaliacaoTextFieldLabel;
	
	private JPanel subCategoriasPanel;
	
	private JTextField categoriaTextField;
	
	private JLabel categoriaTextFieldLabel;
	
	private JScrollPane treeScrollPane;
	
	private JPanel cadastreButtonsPanel;
	
	private JButton atualizarButton;
	
	private JButton novoButton;
	
	private JButton listarComercialSolutionsButton;

	private Categoria selectedElement;
	
	private JPanel contentPanel;
	
	private int scale=800;
	private int smallScale=80;
	
	public ComercialSolutionCadastreForm(){
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(650, 507));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getTabbedPane());		
	}
	
	private JPanel getContentPanel(){
		if (contentPanel == null){
			contentPanel = new JPanel();
			contentPanel.setLayout(null);
			contentPanel.add(getSubCategoriasPanel());
			contentPanel.add(getCadastreButtonsPanel());
			contentPanel.add(this.getErrorPanel());
			contentPanel.add(getFormatadorDescricaoCheckBox());
			contentPanel.add(getDadosGeraisTabbedPane(), null);
			newRegister();
			this.setErrorIcon(false);
		}
		return contentPanel;
	}
	
	protected JScrollPane descricaoScrollPane;
	
	protected JScrollPane getDescricaoScrollPane(){
		if(descricaoScrollPane == null){
			descricaoScrollPane = new JScrollPane();
			descricaoScrollPane.setSize(new Dimension(473, 180));
			descricaoScrollPane.setLocation(new Point(151, 7));
			descricaoScrollPane.add(getDescricaoTextPane());
			descricaoScrollPane.setViewportView(getDescricaoTextPane());
			return descricaoScrollPane;
		}
		return descricaoScrollPane;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getDescricaoTextPane(){
		if(descricaoTextPane == null){
			descricaoTextPane = new JTextPane();
			this.binder.addBindProperty(this.sol, this.descricaoTextPane, "descricao");
			
			this.hashComps.put("descricao", this.descricaoTextPane);
			JWarningComponent warn = new JWarningComponent( this.descricaoTextPane);
			return warn;
		}
		return descricaoTextPane;
	}
	
	public ComercialSolution validateComercialSolutionForm() throws Exception{
		setErrorIcon(false);
		binder.bind(sol);
		String val = (String) getSolutionTypeComboBox().getSelectedItem();
		sol.setSolutionType(CommercialSolutionType.valueOf(val.replace(" ","_")));	
		if (!validateComercialSolutionBean()) throw new Exception("Bean Not Validated!");
		
		if (getSelectedElement() == null) throw new Exception("comercialSolution.categoria");
		else sol.setCategoria(getSelectedElement());
		
		return sol;
	}
	
	public ComercialSolution getSol(){
		return sol;
	}
	public ComercialSolution cadastreComercialSolution() throws Exception{
		ComercialSolution p = validateComercialSolutionForm();
		
		if (p.getId() == 0 ){
			ComercialSolution prod = (ComercialSolution) RemoteServicesUtility.getInstance().save(p);
			return prod;
		}
		else{
			ComercialSolution prod = (ComercialSolution) RemoteServicesUtility.getInstance().update(p);
			return prod;
		}
		
		/*Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			if (p.getId() == 0 ){
				s.save(p);
			}
			else s.update(p);
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}*/		
		
		//return p;
	}
	
	/*public ComercialSolution cadastreComercialSolutionNotItem() throws Exception{
		ComercialSolution p = validateComercialSolutionForm();
		Lote l=null;
		if (lotes != null && lotes.size() > 0){
			l = 
				lotes.get((String)loteNumComboBox.getSelectedItem());
			if (l != null && comercialSolution.getItensComercialSolution() != null){
				comercialSolution.getItensComercialSolution().setLote(l);
				l.getItensComercialSolution().add(comercialSolution.getItensComercialSolution());
				
			}
		}
		LocalComercialSolutionService.getInstance().saveOrUpdate(p);
		return p;
	}*/
	
	@SuppressWarnings("unchecked")
	public boolean validateComercialSolutionBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.sol, "comercialSolution");
		if (errors == null && sol.getAvaliacao() > 0.0f) return true;
		if (errors != null){
			Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
			String name;
			JComponent comp;
			for (int i = 0; i < entrys.length; i++) {
				name = (String) entrys[i].getKey();
				comp = (JComponent) this.hashComps.get(name);
				if (comp != null) {
					comp.firePropertyChange("warnBorder", false, true);
					Object[] obj = (Object[]) entrys[i].getValue();
					String msg = (String) obj[0];
					if (obj[1] == null) {
						try{
							getErrorPanel().addError(messages.getMessage(msg),comp);
							comp.setToolTipText(messages.getMessage(msg));
						}catch(Exception e){
							e.printStackTrace();
						}
					} else {
						List args = (List) obj[1];
						Object[][] params = new Object[args.size()][2];
						for(int j=0; j < args.size(); j++) {
						   String key = (String) args.get(j);
						   params[j][0] = key;
						   params[j][1] = null;
						}
						try{
							getErrorPanel().addError(messages.getMessage(msg, params),comp);
							comp.setToolTipText(messages.getMessage(msg, params));
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}
			}
		}
		/*if (sol.getAvaliacao() <= 0.0f){
			getAvaliacaoTextField().firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("O campo Avaliação deve conter um valor maior do que zero",getAvaliacaoTextField());
			} catch (Exception e) {
				e.printStackTrace();
			}
			getAvaliacaoTextField().setToolTipText("O campo Avaliação deve conter um valor maior do que zero");
		}*/
		
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
		//Nunca definir um novo objeto entidade!!!
		sol.setCategoria(getSelectedElement());
		sol.setId(0);
		sol.setDescricao(null);
		sol.setResumo(null);
		sol.setKeyWords(null);
		sol.setNome(null);
		sol.setSolutionType(CommercialSolutionType.Sistemas);
		//comercialSolution.setDetalhes(null);
		sol.setAvaliacao(0.0f);
		sol.setImagens(new ArrayList<Imagem>());
		imgIndex=0;
		updateImage();
		binder.reverseBind(this.sol);
		this.avaliacaoTextField.setText(Moeda.getValorReal(sol.getAvaliacao()));
		this.setErrorIcon(false);
		updateUI();
	}
	
	public void editRegister(ComercialSolution objComsol ) throws Exception{
		if (sol == null){
			throw new Exception("O bean solution não pode ser substituído!");
		}
		if (objComsol == null){
			throw new Exception("O solution editado não pode ser nulo!");
		}
		try {

			objComsol = RemoteComercialSolutionService.getInstance().
				getCommercialSolutionByIdCascadingProperties(objComsol.getId(), true, true, true);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		//updateLoteComboBox();
		
		try{
			
			this.sol.setId(objComsol.getId());
			
			this.sol.setDescricao(objComsol.getDescricao());
			this.sol.setAvaliacao(objComsol.getAvaliacao());
			this.sol.setUtilizarFormatador(objComsol.getUtilizarFormatador());			
			this.sol.setDataCriacao(objComsol.getDataCriacao());
			this.sol.setCategoria(objComsol.getCategoria());
			this.sol.setKeyWords(objComsol.getKeyWords());
			this.sol.setResumo(objComsol.getResumo());
			this.sol.setNome(objComsol.getNome());
			this.sol.setSolutionType(objComsol.getSolutionType());
			
			this.sol.setImagens(objComsol.getImagens());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		setSelectedElement(sol.getCategoria());
		
		
		if (sol.getImagens() != null){
			sol.getImagens().iterator().hasNext();	
			imgIndex=0;
			updateImage();
		}
		
		
		binder.reverseBind(this.sol);
		this.avaliacaoTextField.setText(Moeda.getValorReal(sol.getAvaliacao()));
		this.formatadorDescricaoCheckBox.setSelected(sol.getUtilizarFormatador());
		this.setErrorIcon(false);
		
	}
	
/*	public void editRegister(ComercialSolution objComercialSolution ){
		try {
			System.out.println("Buscando ComercialSolution");
			objComercialSolution = LocalComercialSolutionService.getInstance().
				getComercialSolutionByIdCascadingProperties(objComercialSolution.getId(), true, true, true);
			System.out.println("ComercialSolution Retornado");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		updateLoteComboBox();
		
		try{
			//Hibernate.initialize(objComercialSolution);
			//Hibernate.initialize(objComercialSolution.getImagens());
			//Hibernate.initialize(objComercialSolution.getItemComercialSolution());
			//objComercialSolution = objComercialSolution.getItemComercialSolution().getComercialSolution();
			Hibernate.initialize(objComercialSolution);
			Hibernate.initialize(objComercialSolution.getImagens());
			Hibernate.initialize(objComercialSolution.getItemComercialSolution());
			Hibernate.initialize(objComercialSolution.getCategoria());
			
			this.comercialSolution.setId(objComercialSolution.getId());
			
			this.comercialSolution.setDescricao(objComercialSolution.getDescricao());
			this.comercialSolution.setAvaliacao(objComercialSolution.getAvaliacao());
						
			this.comercialSolution.setItensComercialSolution(objComercialSolution.getItensComercialSolution());
			this.comercialSolution.setCategoria(objComercialSolution.getCategoria());
			
			this.comercialSolution.setImagens(objComercialSolution.getImagens());
		}catch(Exception e){
			e.printStackTrace();
		}
		if (objComercialSolution.getItensComercialSolution() != null){
			getItensComercialSolutionButton().setEnabled(true);
		}
		
		setSelectedElement(comercialSolution.getCategoria());
		try {
			if (comercialSolution.getItensComercialSolution() != null && comercialSolution.getItensComercialSolution().getLote() != null){
				loteNumComboBox.setSelectedItem(comercialSolution.getItensComercialSolution().getLote().getCodLote());
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		
		if (comercialSolution.getImagens() != null){
			comercialSolution.getImagens().iterator().hasNext();	
			imgIndex=0;
			updateImage();
		}
		
		
		binder.reverseBind(this.comercialSolution);
		this.avaliacaoTextField.setText(Moeda.getValorReal(comercialSolution.getAvaliacao()));
		
		this.setErrorIcon(false);
		
	}*/
	
	

	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(608, 70));
			logErrorPanel.setLocation(15, 391);
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		this.descricaoTextPane.firePropertyChange("warnBorder", !bool, bool);
		this.avaliacaoTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getDescricaoTextAreaLabel(){

		if(descricaoTextAreaLabel == null){
			descricaoTextAreaLabel = new JLabel(messages.getMessage("Descrição"));
			descricaoTextAreaLabel.setSize(new Dimension(128, 20));
			descricaoTextAreaLabel.setLocation(new Point(15, 8));
			descricaoTextAreaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return descricaoTextAreaLabel;
	}
	
	protected JButton generateDescButton;
	
	@SuppressWarnings("unchecked")
	protected JButton getGenerateDescButton(){
		if (generateDescButton == null){
			generateDescButton = new JButton("Gerar Descrição");
			generateDescButton.setSize(new Dimension(130, 52));
			generateDescButton.setHorizontalTextPosition(SwingConstants.CENTER);
			generateDescButton.setVerticalTextPosition(SwingConstants.BOTTOM);
			generateDescButton.setPreferredSize(new Dimension(110, 45));
			generateDescButton.setIcon(new ImageIcon(getClass().getResource("/imgs/wand.png")));
			generateDescButton.setLocation(new Point(15, 35));
			generateDescButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					if (getSelectedElement() != null){
						ComercialSolutionDescricaoPropertyEditorDialog p = new ComercialSolutionDescricaoPropertyEditorDialog(AdapitVirtualFrame.getInstance(), getSelectedElement());
						p.processString(getSelectedElement().getTemplate());
						p.setVisible(true);
						String desc="";
						Enumeration e = p.getKeys().elements();
						if (e != null){
							int i=0;
							while (e.hasMoreElements()){
								String str = (String) e.nextElement();
								desc+=((i!=0)?'\n':"") + str+": " + p.getValues().get(str);
								i++;
							}
						}
						((JTextPane)getDescricaoTextPane()).setText(desc);
						//((JTextArea)getDescricaoTextArea()).setText(selectedElement.getTemplate());
					}else{
						JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Selecione primeiro a categoria do comercialSolution.",
								"Gerar descrição a partir da categoria", JOptionPane.ERROR_MESSAGE);
					}
				}				
			});
		}
		return generateDescButton;
	}
	
	protected JPanel getAvaliacaoCategoriaPanel(){
		if(avaliacaoCategoriaPanel == null){
			avaliacaoCategoriaPanel = new JPanel();
			avaliacaoCategoriaPanel.setSize(new Dimension(130, 40));
			avaliacaoCategoriaPanel.setLocation(new Point(15, 146));
			avaliacaoCategoriaPanel.setLayout(new GridLayout(2,1));
			avaliacaoCategoriaPanel.add(getAvaliacaoTextFieldLabel());
			avaliacaoCategoriaPanel.add(getAvaliacaoTextField());
			
		}
		return avaliacaoCategoriaPanel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getAvaliacaoTextField(){

		if(avaliacaoTextField == null){
			avaliacaoTextField = new JFormattedTextField();
			
			/*AbstractFormatterFactory abs = new AbstractFormatterFactory(){
				AbstractFormatter af = new AbstractFormatter(){
					@Override
					public Object stringToValue(String str) throws ParseException {
						Float f = new Float(str);
						return f;
					}

					@Override
					public String valueToString(Object v) throws ParseException {
						Float f = (Float) v;
						if (f == null) return "R$ ";
						return "R$ " + (f.floatValue());
					}
					
				};
				@Override
				public AbstractFormatter getFormatter(JFormattedTextField jft) {
					return af;
				}				
			};
			avaliacaoTextField.setFormatterFactory(abs);*/
			avaliacaoTextField.setHorizontalAlignment(JTextField.RIGHT);
			avaliacaoTextField.setText("");
			avaliacaoTextField.setSize(new java.awt.Dimension(60,20));
			avaliacaoTextField.setLocation(new java.awt.Point(105,0));
			
			this.binder.addBindProperty(this.sol, this.avaliacaoTextField, "avaliacao");
			
			this.hashComps.put("avaliacao", this.avaliacaoTextField);
			JWarningComponent warn = new JWarningComponent( this.avaliacaoTextField);
			warn.setBounds(105, 0, 60, 20);
			return warn;
		}
		return avaliacaoTextField;
	}
	
	protected JLabel getAvaliacaoTextFieldLabel(){

		if(avaliacaoTextFieldLabel == null){
			avaliacaoTextFieldLabel = new JLabel("Avaliação:"/*messages.getMessage("Avaliação")*/);
			//avaliacaoTextFieldLabel.setHorizontalAlignment(SwingConstants.CENTER);
			avaliacaoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			//avaliacaoTextFieldLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return avaliacaoTextFieldLabel;
	}
	
	protected JPanel getSubCategoriasPanel(){

		if(subCategoriasPanel == null){
			subCategoriasPanel = new JPanel();
			subCategoriasPanel.setSize(new Dimension(611, 92));
			subCategoriasPanel.setLocation(new Point(16, 228));
			subCategoriasPanel.setLayout(null);
			subCategoriasPanel.add(getCategoriaTextField());
			subCategoriasPanel.add(getCategoriaTextFieldLabel());
			subCategoriasPanel.add(getTreeScrollPane());
			subCategoriasPanel.add(getTreeButtonsPanel());
		}
		return subCategoriasPanel;
	}
	
	protected CategoriaSelectableTreeController treeController;
	
	protected CategoriaSelectableTreeController getTreeController(){
		if (treeController == null){
			treeController = AdapitVirtualFrame.getInstance().getTreeController(this);//new CategoriaSelectableTreeController(this);
		}
		return treeController;
	}
	
	protected JTextField getCategoriaTextField(){

		if(categoriaTextField == null){
			categoriaTextField = new JTextField();
			categoriaTextField.setText("");
			categoriaTextField.setEditable(false);
			categoriaTextField.setSize(new Dimension(473, 20));
			categoriaTextField.setLocation(new Point(136, 1));
			return categoriaTextField;
		}
		return categoriaTextField;
	}
	
	protected JLabel getCategoriaTextFieldLabel(){

		if(categoriaTextFieldLabel == null){
			categoriaTextFieldLabel = new JLabel("Selecione a Categoria:");
			categoriaTextFieldLabel.setSize(new Dimension(130, 20));
			categoriaTextFieldLabel.setLocation(new java.awt.Point(0,0));
			categoriaTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return categoriaTextFieldLabel;
	}
	
	protected JScrollPane getTreeScrollPane(){

		if(treeScrollPane == null){
			treeScrollPane = new JScrollPane();
			treeScrollPane.setSize(new Dimension(576, 68));
			treeScrollPane.setLocation(new Point(34, 23));
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			//treeScrollPane.add(getTreeController().getTree());
			treeScrollPane.setViewportView(getTreeController().getTree());
		}
		return treeScrollPane;
	}
	
	protected JPanel treeButtonsPanel;
	
	protected JPanel getTreeButtonsPanel(){
		if (treeButtonsPanel == null){
			treeButtonsPanel = new JPanel();
			treeButtonsPanel.setLayout(new GridLayout(3,1)/*fl*/);
			treeButtonsPanel.setSize(new Dimension(30, 69));
			treeButtonsPanel.setLocation(new Point(0, 21));
			treeButtonsPanel.add(getRefreshTree());
			treeButtonsPanel.add(getEditTree());
			treeButtonsPanel.add(new JPanel());
			treeButtonsPanel.add(new JPanel());
		}
		return treeButtonsPanel;
	}
	
	protected JButton refreshTree;
	
	protected JButton getRefreshTree(){
		if (refreshTree == null){
			refreshTree = new JButton();
			refreshTree.setSize(20,20);
			refreshTree.setToolTipText("Atualizar as categorias");
			refreshTree.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshTree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					
					treeController.newTree();
				}				
			});
		}
		return refreshTree;
	}
	
	protected JButton editTree;
	
	protected JButton getEditTree(){
		if (editTree == null){
			editTree = new JButton();
			editTree.setSize(20,20);
			editTree.setToolTipText("Editar as categorias");
			editTree.setIcon(new ImageIcon(getClass().getResource("/imgs/note_edit.png")));
			editTree.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					//CategoriaDialog categoriaInternalFrame = CategoriaDialog.getInstance();//new CategoriaInternalFrame();		
					AdapitVirtualFrame.getInstance().cadastrarCategorias();//new CategoriaInternalFrame();		
					//categoriaInternalFrame.setVisible(true);
				}				
			});
		}
		return editTree;
	}
	
	protected JPanel getCadastreButtonsPanel(){

		if(cadastreButtonsPanel == null){
			cadastreButtonsPanel = new JPanel();
			cadastreButtonsPanel.setSize(new Dimension(608, 40));
			cadastreButtonsPanel.setLocation(new Point(15, 350));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.setPreferredSize(new Dimension(263, 83));
			cadastreButtonsPanel.add(getAtualizarButton());
			cadastreButtonsPanel.add(getNovoButton());			
			cadastreButtonsPanel.add(getListarComercialSolutionsButton());
		}
		return cadastreButtonsPanel;
	}
	
	protected JButton getAtualizarButton(){

		if(atualizarButton == null){
			atualizarButton = new JButton(messages.getMessage("Cadastrar"));
			atualizarButton.setSize(new java.awt.Dimension(100,24));
			atualizarButton.setLocation(new java.awt.Point(0,0));
			atualizarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					atualizarComercialSolution();
				}				
			});
			atualizarButton.setIcon(getIcon("/imgs/package_save.png",18,18));
		}
		return atualizarButton;
	}
	
	public void atualizarComercialSolution(){
		try {
			ComercialSolution p= cadastreComercialSolution();
			JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de comercialSolution", JOptionPane.INFORMATION_MESSAGE);
			
			editRegister(p);
			
			setSelectedElement(p.getCategoria());
			getAtualizarButton().setText("Atualizar");
			
		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("comercialSolution.categoria")){
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else if (e.getMessage() != null && e.getMessage().equals("comercialSolution.lote")){
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "É necessário selecionar o lote.",
						"Lote não selecionado", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
			}
			e.printStackTrace();
		}
	}
	
/*	public void atualizarComercialSolution(){
		try {
			ComercialSolution p= cadastreComercialSolution();
			JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de comercialSolution", JOptionPane.INFORMATION_MESSAGE);
			//ComercialSolution p = RemoteComercialSolutionService.getInstance().getComercialSolutionByDescricao(comercialSolution.getDescricao());
			if (p.getItensComercialSolution() == null){
				editItemComercialSolution(p);
			}
			editRegister(p);
			
			setSelectedElement(p.getCategoria());
			getAtualizarButton().setText("Atualizar");
			getItensComercialSolutionButton().setEnabled(true);
		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("comercialSolution.categoria")){
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else if (e.getMessage() != null && e.getMessage().equals("comercialSolution.lote")){
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "É necessário selecionar o lote.",
						"Lote não selecionado", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
			}
			e.printStackTrace();
		}
	}*/
	
	
/*
	public void atualizarComercialSolutionNotItem(){
		try {

			cadastreComercialSolutionNotItem();
			JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de comercialSolution", JOptionPane.INFORMATION_MESSAGE);
			//ComercialSolution p = RemoteComercialSolutionService.getInstance().getComercialSolutionByDescricao(comercialSolution.getDescricao());
			if (comercialSolution.getItensComercialSolution() == null){
				editItemComercialSolution(comercialSolution);
			}
			editRegister(comercialSolution);
			
			setSelectedElement(comercialSolution.getCategoria());
			getAtualizarButton().setText("Atualizar");
			getItensComercialSolutionButton().setEnabled(true);
		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("comercialSolution.categoria")){
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
			}
			e.printStackTrace();
		}
	}*/
	
/*	public void editItemComercialSolution(){
		getItemComercialSolutionDialog();
		getItemComercialSolutionCadastreForm().editRegister(comercialSolution);
		getItemComercialSolutionDialog().setVisible(true);
	}
	
	
	public void editItemComercialSolution(ComercialSolution p){
		getItemComercialSolutionDialog();
		getItemComercialSolutionCadastreForm().editRegister(p);
		getItemComercialSolutionCadastreForm().getConfirmarButton().requestFocus();
		getItemComercialSolutionDialog().setVisible(true);
	}
	
	public void editAndSaveItemComercialSolution(ComercialSolution p){
		getItemComercialSolutionDialog();
		getItemComercialSolutionCadastreForm().editRegister(p);
		getItemComercialSolutionDialog().setVisible(true);
		atualizarComercialSolutionNotItem();
	}*/
	
	
	protected JButton getNovoButton(){
		if(novoButton == null){
			novoButton = new JButton(messages.getMessage("Novo"));
			novoButton.setSize(new java.awt.Dimension(100,24));
			novoButton.setLocation(new java.awt.Point(0,24));
			novoButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					newRegister();
					setSelectedElement(null);
					treeController.updateTree();					
					getAtualizarButton().setText("Cadastrar");
					
				}				
			});
			novoButton.setIcon(getIcon("/imgs/package_add.png",18,18));
		}
		return novoButton;
	}
	

	
	protected JButton getListarComercialSolutionsButton(){

		if(listarComercialSolutionsButton == null){
			listarComercialSolutionsButton = new JButton("Listar Soluções");
			listarComercialSolutionsButton.setIcon(getIcon("/imgs/package_table.png",18,18));
			listarComercialSolutionsButton.setSize(new java.awt.Dimension(150,20));
			listarComercialSolutionsButton.setLocation(new java.awt.Point(0,72));
			listarComercialSolutionsButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listCommercialSolutions();
				}				
			});
		}
		return listarComercialSolutionsButton;
	}
	
	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(642, 493));
			tabbedPane.setLocation(new Point(3, 3));
			tabbedPane.addTab("Dados da Solução", getContentPanel());
			tabbedPane.addTab("Imagens da Solução", getImageComponentsPanel());
			tabbedPane.addTab("Detalhes da Solução", getOutrasInformacoesPanel());
			
			tabbedPane.addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent evt) {
					if (sol.getId() <= 0){
						if (tabbedPane.getSelectedIndex() == 1){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir a imagem você precisa cadastrar o comercialSolution",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
						if (tabbedPane.getSelectedIndex() == 2){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir os detalhes você precisa cadastrar o comercialSolution",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
					}
					/*if (evt.getSource() == getImageComponentsPanel()){
						if (comercialSolution.getImagens().size()==0){
							LeilaoVirtualFrame.getInstance().beginStatusBar("Importando as imagens! Essa operação pode ser demorada");
							Session s = LocalServicesUtility.getInstance().openSession();
							try{
								s.load(comercialSolution,comercialSolution.getId());
								comercialSolution.getImagens().size();	
								imgIndex=0;
								updateImage();
							}catch(Exception ex){
								ex.printStackTrace();
							}finally{
								s.close();
							}
							LeilaoVirtualFrame.getInstance().endStatusBar("Importando as imagens! Essa operação pode ser demorada");
						}
							
					}*/
					System.out.println("Elements changed");
				}
				
			});
		}
		return tabbedPane;
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
					//getImgLabelImage().setIcon(img.getBigIcon(true));
					updateImage();
				}				
			});
		}
		return previousButton;
	}

	
	private Object removeOptions[] = {"Retirar a imagem desse comercialSolution",
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
						//int n = Integer.parseInt(imgNumTextField.getText());
						/*if (n == 0){
							JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Você deve informar um valor maior que zero!", "O campo só aceita números maiores que zero", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else if (n > comercialSolution.getImagens().size()){
							JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Você deve informar um valor menor que "+n+"!", "Ordem incorreta das imagens", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else*/
						int resp = JOptionPane.showOptionDialog(
								ComercialSolutionCadastreForm.this, 
								"Selecione a opção para remover a imagem",
								"Remover Imagem",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE,
								null, removeOptions, removeOptions[0]);
						if (resp == 1){
							/*if (comercialSolution.getImagens().size() == 1){
								Imagem im = comercialSolution.getImagens().iterator().next();
								LocalServicesUtility.getInstance().delete(im);
								img = null;
								comercialSolution.getImagens().clear();
								imgIndex = 0;
							}
							else{
								Imagem im = comercialSolution.getImagens().remove(imgIndex);
								LocalServicesUtility.getInstance().delete(im);
								if (comercialSolution.getImagens().size() == 0) imgIndex = 0;
								else if (imgIndex > 1) imgIndex = imgIndex-1;
								else imgIndex = 0;
								img = comercialSolution.getImagens().get(imgIndex);
							}*/
							removerImagemDefinitivamente();
							
										
							updateImage();
							updateImagensIndice();
						}else if (resp == 0){
							if (sol.getImagens().size() == 1){
								try {
									removerImagemComercialSolution();
									sol.getImagens().remove(img);
									img = null;
									imgIndex = 0;
								} catch (Exception e) {
									e.printStackTrace();
									AdapitVirtualFrame.getInstance().showErrorDialog("Remover referência de imagem", "Não foi possível desvincular a imagem deste comercialSolution!");
								}
							}
							else{
								try {
									removerImagemComercialSolution();
									sol.getImagens().remove(imgIndex);
									if (sol.getImagens().size() == 0) imgIndex = 0;
									else if (imgIndex > 1) imgIndex = imgIndex-1;
									else imgIndex = 0;
									img = sol.getImagens().get(imgIndex);
								} catch (Exception e) {
									e.printStackTrace();
									AdapitVirtualFrame.getInstance().showErrorDialog("Remover referência de imagem", "Não foi possível desvincular a imagem deste comercialSolution!");
								}
								
							}
																	
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
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
				Imagem im = removerImagemComercialSolution();
				RemoteServicesUtility.getInstance().delete(im);				
				sol.getImagens().remove(img);
				img = null;
				imgIndex = 0;
			}
			else{
				Imagem im = removerImagemComercialSolution();
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
	
	private Imagem removerImagemComercialSolution() throws Exception{
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
			//imgNumTextField.setEditable(false);
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
							JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Você deve informar um valor maior que zero!", "O campo só aceita números maiores que zero", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else if (n > sol.getImagens().size()){
							JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Você deve informar um valor menor que "+n+"!", "Ordem incorreta das imagens", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Primeiro é preciso cadastrar o comercialSolution!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						getTabbedPane().setSelectedIndex(0);
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
			//viewFullImageButton.setEnabled(false);
			viewFullImageButton.setText("Visializar em (800 X 800)");
			viewFullImageButton.setHorizontalTextPosition(JButton.CENTER);
			viewFullImageButton.setVerticalTextPosition(JButton.BOTTOM);
			viewFullImageButton.setIcon(new ImageIcon(getClass().getResource("/imgs/zoom.png")));
			viewFullImageButton.setBounds(new Rectangle(450, 65, 176, 58));
			//viewFullImageButton.setIcon(getIcon("/imgs/zoom.png"));
			viewFullImageButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if (img != null){
						try {
							ViewFullImageDialog jd = new ViewFullImageDialog(img,scale,scale);							
							jd.setVisible(true);
							/*JDialog jd = new JDialog(LeilaoVirtualFrame.getInstance());
							jd.setTitle(img.getRotulo());
							JLabel jl = new JLabel();
							jl.setSize(scale,scale);
							jd.setSize(600,600);
							jd.setModal(true);
							JScrollPane js = new JScrollPane();
							js.add(jl);
							js.setViewportView(jl);
							jd.add(js);
							jl.setIcon(img.getImageIcon());
							jd.setLocation(UIUtil.getScreenCenter(jd));
							jd.setVisible(true);*/
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}					
				}
			});
		}
		return viewFullImageButton;
	}

	/**
	 * This method initializes editarLoteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
/*	private JButton getEditarLoteButton() {
		if (editarLoteButton == null) {
			editarLoteButton = new JButton();
			editarLoteButton.setBounds(new Rectangle(245, 36, 106, 24));
			editarLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarLoteButton.setText("Editar Lote");
		}
		return editarLoteButton;
	}*/

	/**
	 * This method initializes detalhesImagemButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/*private JButton getDetalhesImagemButton() {
		if (detalhesImagemButton == null) {
			detalhesImagemButton = new JButton();
			detalhesImagemButton.setBounds(new Rectangle(392, 315, 74, 25));
			detalhesImagemButton.setText("Detalhes");
		}
		return detalhesImagemButton;
	}*/

	/*public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(500,560));
					gui.add(new ComercialSolutionCadastreForm(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}*/
	
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
			//TODO 
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			//TODO 
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	/*public Categoria getSelectedElement() {
		return selectedElement;
	}*/

/*	public void setSelectedElement(Categoria selectedElement) {
		this.selectedElement = selectedElement;
		try {
			if (selectedElement != null){
				
					getCategoriaTextField().setText(selectedElement.getNome());
				
			}
			else getCategoriaTextField().setText("");
		} catch (LazyInitializationException e) {			
		}
	}*/

/*	private void setSelectedElement(int categoria_id) {
		if (getTreeController().categorias != null && getTreeController().categorias.size() > 0){
			setSelectedElement(getTreeController().categorias.get(""+categoria_id));
			if (getSelectedElement() != null) getCategoriaTextField().setText(getSelectedElement().getNome());
			else getCategoriaTextField().setText("");
		}else getCategoriaTextField().setText("");
	}*/
	
	private JPanel imageComponentsPanel;

	//private JPanel imageTitleButtonPanel;

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
						if (sol.getImagens().iterator().hasNext() && sol.getId() >0){
							try {
								AdapitVirtualFrame.getInstance().beginStatusBar("Importando as imagens! Essa operação pode ser demorada");
								ComercialSolution prod = RemoteComercialSolutionService.getInstance().loadCommercialSolutionEagerImages(sol);
								sol.setImagens(prod.getImagens());
								imgIndex=0;
								updateImage();
								/*Session s = LocalServicesUtility.getInstance().openSession();
								try{
									s.load(comercialSolution,comercialSolution.getId());
									comercialSolution.getImagens().iterator().hasNext();	
									imgIndex=0;
									updateImage();
								}catch(Exception ex){
									ex.printStackTrace();
								}finally{
									s.close();
								}*/
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

	/*protected JPanel getImageTitleButtonPanel() {

		if (imageTitleButtonPanel == null) {
			imageTitleButtonPanel = new JPanel();
			imageTitleButtonPanel.setSize(new java.awt.Dimension(331, 75));
			imageTitleButtonPanel.setLocation(new java.awt.Point(0, 3));
			imageTitleButtonPanel.setLayout(null);
			imageTitleButtonPanel.add(getImageLabel());
			imageTitleButtonPanel.add(getBuscarImagemButton());
		}
		return imageTitleButtonPanel;
	}*/

	protected JLabel getImageLabel() {

		if (imageLabel == null) {
			imageLabel = new JLabel(
					messages
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
						JOptionPane.showMessageDialog(ComercialSolutionCadastreForm.this, "Primeiro é preciso cadastrar o comercialSolution!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
						getTabbedPane().setSelectedIndex(0);
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
			try {
				/*imgLabelImage
						.setIcon(getIcon("/C:/Documents and Settings/user/Meus documentos/imgs/wizardicon.gif",102,102));*/
			} catch (RuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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

	private JTabbedPane tabbedPane = null;

	private JButton nextButton = null;

	private JButton previousButton = null;

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
			/*jfc.setDialogTitle("Escolha a imagem da Categoria");
			jfc.setLocation(UIUtil.getScreenCenter(jfc));*/
			
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
				Categoria cat = getSelectedElement();
				img = service.saveImagemMergeCategoria(img, cat);
				img = service.mergeImagemComercialSolution(img, sol);
				sol.getImagens().add(img);
				
				//imgIndex++;
				
			} catch (Exception e1) {
				e1.printStackTrace();	
				AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Imagem",
						"Não foi possível adicionar a imagem no comercialSolution");
			}
			/*org.hibernate.Session s = null;
			try {
				s = com.adapit.portal.services.local.LocalServicesUtility.getInstance().openSession();
				s.beginTransaction();
							
				imgIndex = comercialSolution.getImagens().size();
				img.setIndice(imgIndex);
				
				img.setPath(f.getAbsolutePath());
				
				
				updateImageComponents();
				
				
				getImgLabelImage().updateUI();
				getSmallLabelImage().updateUI();
				
				s.persist(img);
				
				s.load(comercialSolution,comercialSolution.getId());
				comercialSolution.getImagens().add(img);
				
				if (img.getComercialSolutions() == null) img.setComercialSolutions(new ArrayList<ComercialSolution>());
				img.getComercialSolutions().add(comercialSolution);
				
				Categoria c =  new Categoria();
				c.setId(comercialSolution.getCategoria().getId());
				img.setCategoria(c);
				
				s.flush();
				
				s.getTransaction().commit();
			} catch (Exception e1) {
				if (s != null) s.getTransaction().rollback();
				e1.printStackTrace();
			}finally{
				if (s != null) s.close();
			}	*/		
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
				//img = i;
			}
		}
	}
	
	public void updateDescricaoImage() throws Exception{
		RemoteImagemService.getInstance().updateImageDescriptionByImageId(img.getId(), img.getDescription());
	}
	
	public void updateRotuloImage() throws Exception{
		RemoteImagemService.getInstance().updateImageRotuloByImageId(img.getId(), img.getRotulo());
	}
	
	/*public void updateDescricaoImage() throws Exception{
		Session s = null;
		try {
			if (img != null && img.getId() != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				//s.merge(img);
				s.createQuery("update Imagem im set im.description='"+img.getDescription()+"' where im.id="+img.getId()).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
		} finally {
			if (s != null)
				s.close();
		}
	}*/
	
	/*public void updateRotuloImage() throws Exception{
		Session s = null;
		try {
			if (img != null && img.getId() != 0) {
				s = LocalServicesUtility.getInstance().openSession();
				s.getTransaction().begin();
				
				//s.merge(img);
				s.createQuery("update Imagem im set im.rotulo='"+img.getRotulo()+"' where im.id="+img.getId()).executeUpdate();
				
				s.getTransaction().commit();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			s.getTransaction().rollback();
		} finally {
			if (s != null)
				s.close();
		}
	}*/
	
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
				anexar = new JButton("Anexar imagens selecionadas no comercialSolution " + sol.getId());
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
					ComercialSolution prod = RemoteComercialSolutionService.getInstance().attachImageIntoCommercialSolution(sol, list);
					sol.setImagens(prod.getImagens());
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Imagens em ComercialSolutions", "Imagens anexadas com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				/*Session s = null;
				try {
					s = LocalServicesUtility.getInstance().openSession();
					s.load(comercialSolution,comercialSolution.getId());
					
					s.beginTransaction();
					for (int i=0; i < rows.length; i++){
						Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[i]);
						comercialSolution.getImagens().add(im);
					}
					s.flush();//merge(comercialSolution);
					s.getTransaction().commit();
					LeilaoVirtualFrame.getInstance().showOperationSucess("Anexar Imagens em ComercialSolutions", "Imagens anexadas com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();
					s.getTransaction().rollback();
				}finally{
					if (s != null) s.close();
				}*/
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

	//private JButton editarLoteButton = null;

	//private JButton detalhesImagemButton = null;

	private JLabel descricaoLabel = null;

	private JTextPane descricaoImageTextArea = null;

	private JScrollPane descricaoImgScrollPane = null;

	private JCheckBox formatadorDescricaoCheckBox = null;

	private JPanel outrasInformacoesPanel = null;

	private JTextField solutionNameTextField = null;

	private JLabel solutionNameLabel = null;

	private JLabel keywordsLabel = null;

	private JTextField keywordsTextField = null;

	private JLabel solutionTypeLabel = null;

	private JComboBox solutionTypeComboBox = null;

	private JLabel resumoLabel = null;

	private JScrollPane solutionAbstractScrollPane = null;

	private JTextPane solutionAbractTextPane = null;

	private JLabel detailsListLabel = null;

	private JScrollPane tabDetailsScrollPane = null;

	private JButton addTabDetailButton = null;

	private JButton editTabDetailButton = null;

	private JButton removeTabDetailButton = null;

	private JComboBox tabDetailNameComboBox = null;

	private JLabel tabDetailNameLabel = null;

	private JLabel linguagemLabel = null;

	private JComboBox linguagemComboBox = null;

	private JScrollPane detailValueScrollPane = null;

	private JTextPane detailValueTextPane = null;

	private JPanel descricaoMainPanel = null;

	private JTabbedPane dadosGeraisTabbedPane = null;

	private JPanel nomaPalavraPanel = null;
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
	
	private static Icon getIcon(String name, int w, int h) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(w, h,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}// end of catch block
		return null;
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
			//int resp = JOptionPane.showConfirmDialog(ComercialSolutionCadastreForm.this, "Salvar as Alterações?","Modificação",JOptionPane.YES_NO_OPTION);
			//if (resp == JOptionPane.YES_OPTION) {
				Iterator<Imagem> it = sol.getImagens().iterator();
				while (it.hasNext()) {
					Imagem im = it.next();
					RemoteServicesUtility.getInstance().createOrUpdate(im);
					/*try {
						saveImage();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}*/
				}
			//}
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
	
	public Categoria getSelectedElement() {
		//Categoria selectedElement = LeilaoVirtualFrame.getInstance().getSelectedElement();
		
		return selectedElement;
	}

	public void setSelectedElement(Categoria selectedElement) {
		try {
			if (selectedElement != null) {

				getCategoriaTextField().setText(selectedElement.getNome());

			} else
				getCategoriaTextField().setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.selectedElement=selectedElement;		
	}

	/**
	 * This method initializes formatadorDescricaoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getFormatadorDescricaoCheckBox() {
		if (formatadorDescricaoCheckBox == null) {
			formatadorDescricaoCheckBox = new JCheckBox();
			formatadorDescricaoCheckBox.setBounds(new Rectangle(15, 325, 607, 21));
			formatadorDescricaoCheckBox.setText("Utilizar o formatador de descrição para apresentar os dados do comercialSolution na web");
			this.binder.addBindProperty(this.sol, this.formatadorDescricaoCheckBox, "utilizarFormatador");
			
			this.hashComps.put("utilizarFormatador", this.formatadorDescricaoCheckBox);
			JWarningComponent warn = new JWarningComponent( this.formatadorDescricaoCheckBox);
			warn.setBounds(new Rectangle(15, 325, 607, 21));
			return warn;
		}
		return formatadorDescricaoCheckBox;
	}

	/**
	 * This method initializes outrasInformacoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOutrasInformacoesPanel() {
		if (outrasInformacoesPanel == null) {
			linguagemLabel = new JLabel();
			linguagemLabel.setBounds(new Rectangle(431, 65, 195, 22));
			linguagemLabel.setText("Linguagem:");
			tabDetailNameLabel = new JLabel();
			tabDetailNameLabel.setBounds(new Rectangle(430, 11, 195, 19));
			tabDetailNameLabel.setText("Nome da Aba/Detalhe");
			detailsListLabel = new JLabel();
			detailsListLabel.setBounds(new Rectangle(11, 10, 169, 22));
			detailsListLabel.setText("Lista de Detalhes de Abas:");
			
			outrasInformacoesPanel = new JPanel();
			outrasInformacoesPanel.setLayout(null);			
			
			outrasInformacoesPanel.add(detailsListLabel, null);
			outrasInformacoesPanel.add(getTabDetailsScrollPane(), null);
			outrasInformacoesPanel.add(getAddTabDetailButton(), null);
			outrasInformacoesPanel.add(getEditTabDetailButton(), null);
			outrasInformacoesPanel.add(getRemoveTabDetailButton(), null);
			outrasInformacoesPanel.add(getTabDetailNameComboBox(), null);
			outrasInformacoesPanel.add(tabDetailNameLabel, null);
			outrasInformacoesPanel.add(linguagemLabel, null);
			outrasInformacoesPanel.add(getLinguagemComboBox(), null);
			outrasInformacoesPanel.add(getDetailValueScrollPane(), null);
			
		}
		return outrasInformacoesPanel;
	}

	/**
	 * This method initializes solutionNameTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getSolutionNameTextField() {
		if (solutionNameTextField == null) {
			solutionNameTextField = new JTextField();
			solutionNameTextField.setBounds(new Rectangle(150, 4, 474, 22));
			this.binder.addBindProperty(this.sol, this.solutionNameTextField, "nome");
			
			this.hashComps.put("nome", this.solutionNameTextField);
			JWarningComponent warn = new JWarningComponent( this.solutionNameTextField);
			warn.setBounds(new Rectangle(150, 4, 474, 22));
			return warn;
		}
		return solutionNameTextField;
	}

	/**
	 * This method initializes keywordsTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getKeywordsTextField() {
		if (keywordsTextField == null) {
			keywordsTextField = new JTextField();
			keywordsTextField.setBounds(new Rectangle(150, 28, 474, 22));
			this.binder.addBindProperty(this.sol, this.keywordsTextField, "keyWords");
			
			this.hashComps.put("keyWords", this.keywordsTextField);
			JWarningComponent warn = new JWarningComponent( this.keywordsTextField);
			warn.setBounds(new Rectangle(150, 28, 474, 22));
			return warn;
		}
		return keywordsTextField;
	}

	/**
	 * This method initializes solutionTypeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getSolutionTypeComboBox() {
		if (solutionTypeComboBox == null) {
			solutionTypeComboBox = new JComboBox();
			solutionTypeComboBox.setBounds(new Rectangle(292, 52, 328, 22));
			CommercialSolutionType val[] = CommercialSolutionType.values();
			for (int i=0; i < val.length; i++){
				solutionTypeComboBox.addItem(val[i].name().replace("_"," "));
			}
			/*this.binder.addBindProperty(this.sol, this.solutionTypeComboBox, "solutionType");
			
			this.hashComps.put("solutionType", this.solutionTypeComboBox);
			JWarningComponent warn = new JWarningComponent( this.solutionTypeComboBox);
			warn.setBounds(new Rectangle(150, 52, 293, 22));
			return warn;*/
		}
		return solutionTypeComboBox;
	}

	/**
	 * This method initializes solutionAbstractScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSolutionAbstractScrollPane() {
		if (solutionAbstractScrollPane == null) {
			solutionAbstractScrollPane = new JScrollPane();
			solutionAbstractScrollPane.setBounds(new Rectangle(6, 76, 616, 109));
			solutionAbstractScrollPane.setViewportView(getSolutionAbractTextPane());
		}
		return solutionAbstractScrollPane;
	}

	/**
	 * This method initializes solutionAbractTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getSolutionAbractTextPane() {
		if (solutionAbractTextPane == null) {
			solutionAbractTextPane = new JTextPane();
			this.binder.addBindProperty(this.sol, this.solutionAbractTextPane, "resumo");
			
			this.hashComps.put("resumo", this.solutionAbractTextPane);
			JWarningComponent warn = new JWarningComponent( this.solutionAbractTextPane);
			warn.setBounds(new Rectangle(6, 76, 616, 109));
			return warn;
		}
		return solutionAbractTextPane;
	}

	/**
	 * This method initializes tabDetailsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTabDetailsScrollPane() {
		if (tabDetailsScrollPane == null) {
			tabDetailsScrollPane = new JScrollPane();
			tabDetailsScrollPane.setBounds(new Rectangle(10, 35, 291, 77));
		}
		return tabDetailsScrollPane;
	}

	/**
	 * This method initializes addTabDetailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAddTabDetailButton() {
		if (addTabDetailButton == null) {
			addTabDetailButton = new JButton();
			addTabDetailButton.setBounds(new Rectangle(307, 35, 118, 24));
			addTabDetailButton.setText("Adicionar");
		}
		return addTabDetailButton;
	}

	/**
	 * This method initializes editTabDetailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditTabDetailButton() {
		if (editTabDetailButton == null) {
			editTabDetailButton = new JButton();
			editTabDetailButton.setBounds(new Rectangle(307, 61, 118, 24));
			editTabDetailButton.setText("Editar");
		}
		return editTabDetailButton;
	}

	/**
	 * This method initializes removeTabDetailButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoveTabDetailButton() {
		if (removeTabDetailButton == null) {
			removeTabDetailButton = new JButton();
			removeTabDetailButton.setBounds(new Rectangle(307, 87, 118, 24));
			removeTabDetailButton.setText("Remover");
		}
		return removeTabDetailButton;
	}

	/**
	 * This method initializes tabDetailNameComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTabDetailNameComboBox() {
		if (tabDetailNameComboBox == null) {
			tabDetailNameComboBox = new JComboBox();
			tabDetailNameComboBox.setBounds(new Rectangle(430, 34, 195, 22));
			tabDetailNameComboBox.addItem("Benefícios");
			tabDetailNameComboBox.addItem("Características");
			tabDetailNameComboBox.addItem("Diferenciais");
			tabDetailNameComboBox.addItem("Público-Alvo");
			tabDetailNameComboBox.addItem("Portifólio");
			tabDetailNameComboBox.addItem("Carta de Apresentação");
			
		}
		return tabDetailNameComboBox;
	}

	/**
	 * This method initializes linguagemComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getLinguagemComboBox() {
		if (linguagemComboBox == null) {
			linguagemComboBox = new JComboBox();
			linguagemComboBox.setBounds(new Rectangle(431, 88, 194, 22));
			linguagemComboBox.addItem("PT-BR");
			linguagemComboBox.addItem("PT-PR");
			linguagemComboBox.addItem("EN-US");
		}
		return linguagemComboBox;
	}

	/**
	 * This method initializes detailValueScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDetailValueScrollPane() {
		if (detailValueScrollPane == null) {
			detailValueScrollPane = new JScrollPane();
			detailValueScrollPane.setBounds(new Rectangle(10, 123, 618, 337));
			detailValueScrollPane.setViewportView(getDetailValueTextPane());
		}
		return detailValueScrollPane;
	}

	/**
	 * This method initializes detailValueTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getDetailValueTextPane() {
		if (detailValueTextPane == null) {
			detailValueTextPane = new JTextPane();
		}
		return detailValueTextPane;
	}

	/**
	 * This method initializes descricaoMainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDescricaoMainPanel() {
		if (descricaoMainPanel == null) {
			descricaoMainPanel = new JPanel();
			descricaoMainPanel.setLayout(null);
			descricaoMainPanel.add(getDescricaoScrollPane());
			descricaoMainPanel.add(getDescricaoTextAreaLabel());
			descricaoMainPanel.add(getGenerateDescButton());
			descricaoMainPanel.add(getAvaliacaoCategoriaPanel());
			
		}
		return descricaoMainPanel;
	}

	/**
	 * This method initializes dadosGeraisTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getDadosGeraisTabbedPane() {
		if (dadosGeraisTabbedPane == null) {
			dadosGeraisTabbedPane = new JTabbedPane();
			dadosGeraisTabbedPane.setBounds(new Rectangle(0, 5, 635, 220));
			dadosGeraisTabbedPane.addTab("Nome e Key Words",getNomaPalavraPanel());
			dadosGeraisTabbedPane.addTab("Descrição e Avaliação", getDescricaoMainPanel());
			
		}
		return dadosGeraisTabbedPane;
	}

	/**
	 * This method initializes nomaPalavraPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getNomaPalavraPanel() {
		if (nomaPalavraPanel == null) {
			resumoLabel = new JLabel();
			resumoLabel.setBounds(new Rectangle(5, 52, 139, 22));
			resumoLabel.setText("Resumo:");
			solutionTypeLabel = new JLabel();
			solutionTypeLabel.setBounds(new Rectangle(148, 52, 140, 22));
			solutionTypeLabel.setText("Tipo da Solução:");
			keywordsLabel = new JLabel();
			keywordsLabel.setBounds(new Rectangle(5, 28, 140, 22));
			keywordsLabel.setText("Palavras-Chave:");
			solutionNameLabel = new JLabel();
			solutionNameLabel.setBounds(new Rectangle(5, 4, 140, 22));
			solutionNameLabel.setText("Nome da Solução:");
			nomaPalavraPanel = new JPanel();
			nomaPalavraPanel.setLayout(null);
			nomaPalavraPanel.setBounds(new Rectangle(5, 6, 626, 111));
			nomaPalavraPanel.add(getSolutionNameTextField(), null);
			nomaPalavraPanel.add(solutionNameLabel, null);
			nomaPalavraPanel.add(keywordsLabel, null);
			nomaPalavraPanel.add(getKeywordsTextField(), null);
			nomaPalavraPanel.add(solutionTypeLabel, null);
			nomaPalavraPanel.add(getSolutionTypeComboBox(), null);
			nomaPalavraPanel.add(resumoLabel, null);
			nomaPalavraPanel.add(getSolutionAbstractScrollPane(), null);
		}
		return nomaPalavraPanel;
	}
}  // @jve:decl-index=0:visual-constraint="10,10"
