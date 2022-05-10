package com.adapit.portal.ui.forms.software;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.entidades.CommercialSolutionType;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.validation.ValidationException;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.forms.solution.CassDefinitionCadastreForm;
import com.adapit.portal.ui.forms.solution.OutrasInformacoesPanel;
import com.adapit.portal.ui.forms.usuario.TimerMessageFrame;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.SwingWorker;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.Moeda;


@SuppressWarnings({"serial","unchecked"})
public class SoftwareSolutionCadastreForm extends JPanel implements CategoriaSelectable{
	
	protected SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	protected SoftwareSolution sol = new SoftwareSolution();  //  @jve:decl-index=0:
	
	protected Map hashComps = new java.util.HashMap();
	
	boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	protected ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JPanel cadastreButtonsPanel;
	
	private JButton atualizarButton;
	
	private JButton novoButton;
	
	private JButton listarSoftwareSolutionsButton;

	private Categoria selectedElement;
	
	private JPanel contentPanel;
	
	
	public SoftwareSolutionCadastreForm(){
		initialize();
	}
	
	private void initialize(){
		setSize(new Dimension(650, 507));
		setLocation(new java.awt.Point(0,0));
		setLayout(new BorderLayout());
		add(getToolBar(),BorderLayout.NORTH);
		add(getTabbedPane(),BorderLayout.CENTER);	
		listCss();
	}
	
	private JPanel toolBar;
	
	private JPanel getToolBar(){
		if(toolBar == null){
			toolBar = new JPanel(new BorderLayout());
			toolBar.add(publicarCheckBox,BorderLayout.WEST);
			JPanel jp = new JPanel(new BorderLayout());
			jp.setBorder(BorderFactory.createTitledBorder("Definção de CSS para o Software"));
			jp.add(cssComboBox,BorderLayout.CENTER);
			JPanel cp = new JPanel(new GridLayout(1,2));
			JButton add = new JButton();
			add.setIcon(new ImageIcon(getClass().getResource("/imgs/add.png")));
			add.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setTitle("Cadastro de Css");
					jd.setModal(true);
					CassDefinitionCadastreForm form =
						new CassDefinitionCadastreForm();
					jd.add(form,BorderLayout.CENTER);
					jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					jd.setSize(600,400);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setVisible(true);
					listCss();
				}
			});
			cp.add(add);
			JButton ed = new JButton();
			ed.setIcon(new ImageIcon(getClass().getResource("/imgs/note_edit.png")));
			ed.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = cssComboBox.getSelectedIndex();
					if(index < 0 || css == null || css.size() < index)
						return;
					CssDefinition c = css.get(index);
					JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setTitle("Cadastro de Css");
					jd.setModal(true);
					CassDefinitionCadastreForm form =
						new CassDefinitionCadastreForm();
					form.editRegister(c);
					jd.add(form,BorderLayout.CENTER);
					jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					jd.setSize(600,400);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setVisible(true);
					listCss();
				}
			});
			cp.add(ed);
			JButton ref =new JButton();
			ref.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			ref.addActionListener(new java.awt.event.ActionListener() {   
				public void actionPerformed(java.awt.event.ActionEvent e) {    
					listCss();
				}
			
			});
			cp.add(ref);
			jp.add(cp,BorderLayout.EAST);
			toolBar.add(jp,BorderLayout.CENTER);
		}
		return toolBar;
	}
	
	private void listCss(){
		if(cssComboBox.getItemCount()>0)
			cssComboBox.removeAllItems();
		try {
			css = RemoteComercialSolutionService.getInstance().listCssDefinitions(null);
			if(css != null){
				
				for(CssDefinition c: css){
					cssComboBox.addItem(c.getName());
				}
			}
			else if(cssComboBox.getItemCount()>0)
				cssComboBox.removeAllItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private JComboBox cssComboBox = new JComboBox();
	private List<CssDefinition> css = null;  //  @jve:decl-index=0:
	
	private JCheckBox publicarCheckBox = new JCheckBox("Publicar o sistema na internet?");
	
	private JPanel getContentPanel(){
		if (contentPanel == null){
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			//contentPanel.add(getSubCategoriasPanel());
			
			
			contentPanel.add(getDadosGeraisTabbedPane(), BorderLayout.CENTER);
			
			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(670,170));
			jp.setLayout(new BorderLayout());
			
			jp.add(getCadastreButtonsPanel(),BorderLayout.NORTH);
			jp.add(this.getErrorPanel(),BorderLayout.CENTER);
			
			contentPanel.add(jp,BorderLayout.SOUTH);
			
			newRegister();
			this.setErrorIcon(false);
		}
		return contentPanel;
	}
	
	
	

	
	public SoftwareSolution validateSoftwareSolutionForm() throws Exception{
		setErrorIcon(false);
		binder.bind(sol);
		sol.setPublicar(publicarCheckBox.isSelected());
		
		if (!validateSoftwareSolutionBean()) throw new Exception("Bean Not Validated!");
		
		if (getSelectedElement() == null) throw new Exception("comercialSolution.categoria");
		else sol.setCategoria(getSelectedElement());
		
		return sol;
	}
	
	public SoftwareSolution getSol(){
		return sol;
	}
	public ComercialSolution cadastreSoftwareSolution() throws Exception{
		SoftwareSolution p = validateSoftwareSolutionForm();
		
		ComercialSolution cm =  RemoteComercialSolutionService.getInstance().saveOrUpdate(p);
		return cm;	
	}

	
	public boolean validateSoftwareSolutionBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.sol, "softwareSolution");
		if (errors == null && sol.getAvaliacao() > 0.0f) return true;
		/*Map errors2 = validate.validate(this.sol, "comercialSolution");
		if (errors == null && sol.getAvaliacao() > 0.0f && errors2 == null) return true;
		if (errors != null && errors2 != null) errors.putAll(errors2);
		else if (errors2 != null) errors = errors2;*/
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
		if (sol.getAvaliacao() <= 0.0f){
			getSoftwareDescriptionPanel().getAvaliacaoTextField().firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("O campo Avaliação deve conter um valor maior do que zero",getSoftwareDescriptionPanel().getAvaliacaoTextField());
			} catch (Exception e) {
				e.printStackTrace();
			}
			getSoftwareDescriptionPanel().getAvaliacaoTextField().setToolTipText("O campo Avaliação deve conter um valor maior do que zero");
		}
		
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
		sol.setAvaliacao(0.0f);
		sol.setImagens(new ArrayList<Imagem>());
		sol.setPublicar(false);
		this.sol.setSigla(null);
		this.sol.setFuncionalidades(null);
		this.sol.setLicencaUso(null);
		this.sol.setTipoSoftware(null);
		this.sol.setUrlProjeto(null);
		this.sol.setVersao(null);
		this.sol.setPlataforma(null);
		this.sol.setSistemasOperacionais(null);
		
		binder.reverseBind(this.sol);
		getSoftwareDescriptionPanel().avaliacaoTextField.setText(Moeda.getValorReal(sol.getAvaliacao()));
		this.setErrorIcon(false);
		updateUI();
	}
	
	public void editRegister(SoftwareSolution objComsol ) throws Exception{
		if (sol == null){
			throw new Exception("O bean solution não pode ser substituído!");
		}
		if (objComsol == null){
			throw new Exception("O solution editado não pode ser nulo!");
		}
		try {

			objComsol = (SoftwareSolution) RemoteComercialSolutionService.getInstance().
				getSoftwareSolutionByIdCascadingProperties(objComsol.getId(), false, true);

		} catch (Exception e1) {
			e1.printStackTrace();
		}
				
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
			this.sol.setPublicar(objComsol.isPublicar());
			this.sol.setImagens(/*objComsol.getImagens()*/null);
			
			this.sol.setSigla(objComsol.getSigla());
			this.sol.setFuncionalidades(objComsol.getFuncionalidades());
			this.sol.setLicencaUso(objComsol.getLicencaUso());
			this.sol.setTipoSoftware(objComsol.getTipoSoftware());
			this.sol.setUrlProjeto(objComsol.getUrlProjeto());
			this.sol.setVersao(objComsol.getVersao());
			this.sol.setPlataforma(objComsol.getPlataforma());
			this.sol.setSistemasOperacionais(objComsol.getSistemasOperacionais());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		setSelectedElement(sol.getCategoria());
		
		
		
		
		
		binder.reverseBind(this.sol);
		getSoftwareDescriptionPanel().avaliacaoTextField.setText(Moeda.getValorReal(sol.getAvaliacao()));
		getSoftwareDescriptionPanel().formatadorDescricaoCheckBox.setSelected(sol.getUtilizarFormatador());
		
		getSoftwareDadosGeraisPanel().updateByHtml();
		getDadosSoftwarePanel().updateByHtml();
		publicarCheckBox.setSelected(sol.isPublicar());
		
		try {
			CssDefinition c = RemoteComercialSolutionService.getInstance().getCssDefinition(sol);
			if(c != null){
				cssComboBox.setSelectedItem(c.getName());
			}
			else cssComboBox.setSelectedItem("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setErrorIcon(false);
		
	}

	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(608, 70));
			logErrorPanel.setLocation(15, 61);
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){
		getSoftwareDescriptionPanel().getDescricaoTextPane().firePropertyChange("warnBorder", !bool, bool);
		getSoftwareDescriptionPanel().getAvaliacaoTextField().firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}
		
	protected JPanel getCadastreButtonsPanel(){
		if(cadastreButtonsPanel == null){
			cadastreButtonsPanel = new JPanel();
			cadastreButtonsPanel.setPreferredSize(new Dimension(608, 65));
			cadastreButtonsPanel.setLocation(new Point(15, 0));
			cadastreButtonsPanel.setLayout(new java.awt.FlowLayout());
			cadastreButtonsPanel.setPreferredSize(new Dimension(263, 83));
			cadastreButtonsPanel.add(getAtualizarButton());
			cadastreButtonsPanel.add(getNovoButton());			
			cadastreButtonsPanel.add(getListarSoftwareSolutionsButton());
			cadastreButtonsPanel.add(getAddUpdateButton());
			cadastreButtonsPanel.add(getListUpdateButton());
			cadastreButtonsPanel.add(getImagensButton(), null);
			cadastreButtonsPanel.add(getDetalhesButton(), null);
		}
		return cadastreButtonsPanel;
	}
	
	private JButton addUpdateButton;
	private JButton getAddUpdateButton(){
		if(addUpdateButton == null){
			addUpdateButton = new JButton("Adicionar Versão");
			addUpdateButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_add.png")));
			addUpdateButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarUpdate(sol);
				}
			});
		}
		return addUpdateButton;
	}
	
	private JButton listUpdateButton;
	private JButton getListUpdateButton(){
		if(listUpdateButton == null){
			listUpdateButton = new JButton("Listar Versões");
			listUpdateButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_magnify.png")));
			listUpdateButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listarUpdate(sol);
				}
			});
		}
		return listUpdateButton;
	}
	
	protected JButton getAtualizarButton(){
		if(atualizarButton == null){
			atualizarButton = new JButton(messages.getMessage("Cadastrar"));
			atualizarButton.setSize(new java.awt.Dimension(100,24));
			atualizarButton.setLocation(new java.awt.Point(0,0));
			atualizarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					atualizarSoftwareSolution();
				}				
			});
			atualizarButton.setIcon(getIcon("/imgs/package_save.png",18,18));
		}
		return atualizarButton;
	}
	
	public void atualizarSoftwareSolution(){
		try {
			SoftwareSolution p = (SoftwareSolution) cadastreSoftwareSolution();
			
			int index = cssComboBox.getSelectedIndex();
			if(!(index < 0 || css == null || css.size() < index)){
				CssDefinition c = css.get(index);
				//sol.setCssDefintion(c);
				RemoteComercialSolutionService.getInstance().merge(p, c, true);
			}
			JOptionPane.showMessageDialog(SoftwareSolutionCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de Software", JOptionPane.INFORMATION_MESSAGE);
			
			editRegister(p);
			
			setSelectedElement(p.getCategoria());
			getAtualizarButton().setText("Atualizar");
			
		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("comercialSolution.categoria")){
				JOptionPane.showMessageDialog(SoftwareSolutionCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else if(e instanceof ValidationException){
				JOptionPane.showMessageDialog(SoftwareSolutionCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(SoftwareSolutionCadastreForm.this, "Problema em camada DAO.",
						"Não foi possível cadastrar o software", JOptionPane.ERROR_MESSAGE);
			}
			e.printStackTrace();
		}
	}
	
	protected JButton getNovoButton(){
		if(novoButton == null){
			novoButton = new JButton(messages.getMessage("Novo"));
			novoButton.setSize(new java.awt.Dimension(100,24));
			novoButton.setLocation(new java.awt.Point(0,24));
			novoButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					newRegister();
					setSelectedElement(null);
					getSoftwareDescriptionPanel().treeController.updateTree();					
					getAtualizarButton().setText("Cadastrar");
					
				}				
			});
			novoButton.setIcon(getIcon("/imgs/package_add.png",18,18));
		}
		return novoButton;
	}
	
	protected JButton getListarSoftwareSolutionsButton(){
		if(listarSoftwareSolutionsButton == null){
			listarSoftwareSolutionsButton = new JButton("Listar Softwares");
			listarSoftwareSolutionsButton.setIcon(getIcon("/imgs/package_table.png",18,18));
			listarSoftwareSolutionsButton.setSize(new java.awt.Dimension(150,20));
			listarSoftwareSolutionsButton.setLocation(new java.awt.Point(0,72));
			listarSoftwareSolutionsButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listSystemSolutions();
				}				
			});
		}
		return listarSoftwareSolutionsButton;
	}
	
	/**
	 * This method initializes tabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JPanel getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JPanel();
			tabbedPane.setSize(new Dimension(642, 493));
			tabbedPane.setLocation(new Point(3, 3));
			tabbedPane.setLayout(new BorderLayout());
			tabbedPane.setBorder(BorderFactory.createTitledBorder("Dados Cadastrais do Software"));
			tabbedPane.add(getContentPanel(),BorderLayout.CENTER);
			/*tabbedPane.addTab("Dados Cadastrais do Software", getContentPanel());
			tabbedPane.addTab("Imagens do Software", getImageComponentsPanel());			
			tabbedPane.addTab("Detalhes do Software", getOutrasInformacoesPanel());
			*///TODO desenvolver o recurso
			getCategoriaMainPanel();
			//tabbedPane.addTab("Clientes Autorizados (TODO Desenvolver)",);
			/*tabbedPane.addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent evt) {
					if (sol.getId() <= 0){
						if (tabbedPane.getSelectedIndex() == 1){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir a imagem você precisa cadastrar o Software",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
						if (tabbedPane.getSelectedIndex() == 2){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir os detalhes você precisa cadastrar o Software",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
					}
					
					System.out.println("Elements changed");
				}
				
			});*/
		}
		return tabbedPane;
	}

	protected JPanel categoriaMainPanel;
	
	protected JPanel getCategoriaMainPanel(){
		if (categoriaMainPanel == null){
			categoriaMainPanel = new JPanel();
			categoriaMainPanel.setLayout(null);
			//categoriaMainPanel.add(getSubCategoriasPanel());
		}
		return categoriaMainPanel;
	}

	
	
	
	
	private JPanel tabbedPane = null;


	private JTabbedPane dadosGeraisTabbedPane = null;

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

	
	public Categoria getSelectedElement() {
		//Categoria selectedElement = LeilaoVirtualFrame.getInstance().getSelectedElement();
		
		return selectedElement;
	}

	public void setSelectedElement(Categoria selectedElement) {
		try {
			if (selectedElement != null) {
				getSoftwareDescriptionPanel().getCategoriaTextField().setText(selectedElement.getNome());
			} else
				getSoftwareDescriptionPanel().getCategoriaTextField().setText("");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.selectedElement=selectedElement;		
	}

	

	/**
	 * This method initializes dadosGeraisTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getDadosGeraisTabbedPane() {
		if (dadosGeraisTabbedPane == null) {
			dadosGeraisTabbedPane = new JTabbedPane();
			dadosGeraisTabbedPane.setBounds(new Rectangle(0, 5, 635, 316));
			dadosGeraisTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			//dadosGeraisTabbedPane.addTab("Selecione a Cateogira",getCategoriaMainPanel());
			dadosGeraisTabbedPane.addTab("1) Especifique os Dados Gerais",getSoftwareDadosGeraisPanel());
			dadosGeraisTabbedPane.addTab("2) Especifique os Dados do Sistema",getDadosSoftwarePanel());
			dadosGeraisTabbedPane.addTab("3) Especifique Descrição, Avaliação e Categoria", getSoftwareDescriptionPanel());			
		}
		return dadosGeraisTabbedPane;
	}
	
	private SoftwareDescriptionPanel softwareDescriptionPanel;
	private SoftwareDescriptionPanel getSoftwareDescriptionPanel(){
		if(softwareDescriptionPanel == null){
			softwareDescriptionPanel= new SoftwareDescriptionPanel(this);
		}
		return softwareDescriptionPanel;
	}
	
	private SoftwareDetalhesPanel dadosSoftwarePanel;
	private SoftwareDetalhesPanel getDadosSoftwarePanel(){
		if(dadosSoftwarePanel == null){
			dadosSoftwarePanel = new SoftwareDetalhesPanel(this);
		}
		return dadosSoftwarePanel;
	}

	private SoftwareDadosGeraisPanel softwareDadosGeraisPanel;

	private JButton imagensButton = null;

	private JButton detalhesButton = null;
	
	private SoftwareDadosGeraisPanel getSoftwareDadosGeraisPanel(){
		if(softwareDadosGeraisPanel == null){
			softwareDadosGeraisPanel=new SoftwareDadosGeraisPanel(this);
		}
		return softwareDadosGeraisPanel;
	}

	/**
	 * This method initializes imagensButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getImagensButton() {
		if (imagensButton == null) {
			imagensButton = new JButton();
			imagensButton.setText("Imagens");
			imagensButton.setIcon(new ImageIcon(getClass().getResource("/imgs/pictures.png")));
			imagensButton.addActionListener(new java.awt.event.ActionListener() {
				private SoftwareImageManagementPanel imagesPanel = null;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					SwingWorker sw = new SwingWorker(){
						@Override
						public Object construct() {
							processAction();
							return null;
						}
					
					};
					
					sw.start();
					
				}
				
				public void processAction() {
					TimerMessageFrame jdf = new TimerMessageFrame("Carregando informações ... por favor aguarde");
					jdf.setSize(310,80);					
					jdf.setVisible(true);
					
					getImagesPanel().editRegister(sol);
					
					
					if(jd == null){
						ActionListener al = new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent arg0) {
								closeDialog();
							}
						};
						jd = AdapitVirtualFrame.getInstance().createDialog(
								"Detalhes do Software " + sol.getNome(),
								(JComponent) getImagesPanel(),
								new String[]{"Fechar"},
								new ActionListener[]{al},
								null,
								700,600,
								false,
								true);
					}
					
					
					jd.setVisible(true);
					jdf.stop();
					
				}
				private JDialog jd;
				
				public void closeDialog(){
					jd.dispose();
				}
				
				private SoftwareImageManagementPanel getImagesPanel() {
					if (imagesPanel == null) {			
						imagesPanel = new SoftwareImageManagementPanel(
								sol,SoftwareSolutionCadastreForm.this);			
						
					}
					return imagesPanel;
				}
			});

		}
		return imagensButton;
	}

	/**
	 * This method initializes detalhesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDetalhesButton() {
		if (detalhesButton == null) {
			detalhesButton = new JButton();
			detalhesButton.setText("Detalhes");
			detalhesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/tab_edit.png")));
			detalhesButton.addActionListener(new java.awt.event.ActionListener() {
				private OutrasInformacoesPanel outrasInformacoesPanel = null;

				public void actionPerformed(java.awt.event.ActionEvent e) {
					//getOutrasInformacoesPanel();
					getOutrasInformacoesPanel().setSol(sol);
					getOutrasInformacoesPanel().getBaseTable().updateTable();
					if(jd == null){
						ActionListener al = new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent arg0) {
								closeDialog();
							}
						};
						jd = AdapitVirtualFrame.getInstance().createDialog(
								"Detalhes do Software " + sol.getNome(),
								(JComponent) getOutrasInformacoesPanel(),
								new String[]{"Fechar"},
								new ActionListener[]{al},
								null,
								700,600,
								true,
								true,
								true);
					}
					jd.setVisible(true);
				}
				private JDialog jd;
				
				public void closeDialog(){
					jd.dispose();
				}
				
				private OutrasInformacoesPanel getOutrasInformacoesPanel() {
					if (outrasInformacoesPanel == null) {			
						outrasInformacoesPanel = new OutrasInformacoesPanel();			
						
					}
					return outrasInformacoesPanel;
				}
			});
		}
		return detalhesButton;
	}

	

	
}  // @jve:decl-index=0:visual-constraint="10,10"
