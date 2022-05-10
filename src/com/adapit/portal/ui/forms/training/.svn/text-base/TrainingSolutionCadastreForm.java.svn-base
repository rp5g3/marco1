package com.adapit.portal.ui.forms.training;


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
import com.adapit.portal.entidades.CommercialSolutionType;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.services.ImagemService;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.forms.imageform.ViewFullImageDialog;
import com.adapit.portal.ui.forms.solution.ComercialSolutionDescricaoPropertyEditorDialog;
import com.adapit.portal.ui.forms.solution.OutrasInformacoesPanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.tree.CategoriaSelectableTreeController;
import com.workcase.gui.custom.ImageFileChooser;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.LengthLimitedDocument;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.Moeda;


@SuppressWarnings("serial")
public class TrainingSolutionCadastreForm extends JPanel implements CategoriaSelectable{
	
	private JTextPane descricaoTextPane;
	
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private TrainingSolution sol = new TrainingSolution();  //  @jve:decl-index=0:
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
	
	private JButton listarTrainingSolutionsButton;

	private Categoria selectedElement;
	
	private JPanel contentPanel;
	
	private JPanel descricaoMainPanel = null;

	private JTabbedPane dadosGeraisTabbedPane = null;

	private JPanel nomaPalavraPanel = null;

	private JPanel dadosSoftwarePanel = null;  //  @jve:decl-index=0:visual-constraint="791,170"

	private JLabel codigoLabel = null;

	private JTextField codigoTextField = null;

	private JLabel tipoTreinamentoLabel = null;

	private JComboBox tipoTreinamentoComboBox = null;

	private JLabel cargaHorariaLabel = null;

	private JTextField cargaHorariaTextField = null;

	private JLabel materialApoioLabel = null;

	private JTextField materialApoioTextField = null;

	private JLabel tecnologiasLabel = null;

	private JTextPane tecnologiasTextField = null;

	private JLabel objetivosLabel = null;

	private JTextPane objetivosTextPane = null;

	private JLabel softwaresLabel = null;

	private JScrollPane softwaresScrollPane = null;

	private JTextPane softwaresTextPane = null;

	private JLabel conteudoProgramaticoLabel = null;

	private JScrollPane conteudoProgramaticoScrollPane = null;	

	private JLabel resumoLabel = null;	

	private JLabel solutionTypeLabel = null;
	
	private JTextField solutionNameTextField = null;

	private JLabel solutionNameLabel = null;

	private JLabel keywordsLabel = null;
	
	private JTextField keywordsTextField = null;

	private JComboBox solutionTypeComboBox = null;

	private JScrollPane objetivoEspecificoScrollPane = null;

	private JTextPane obetivoEspecíficoTextPane = null;
	
	private int scale=800;
	private int smallScale=80;
	
	public TrainingSolutionCadastreForm(){
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
			//contentPanel.add(getSubCategoriasPanel());
			contentPanel.add(getCadastreButtonsPanel());
			contentPanel.add(this.getErrorPanel());
			contentPanel.add(getFormatadorDescricaoCheckBox());
			contentPanel.add(getDadosGeraisTabbedPane(), null);
			newRegister();
			this.setErrorIcon(false);
		}
		return contentPanel;
	}
	
	/**
	 * This method initializes dadosGeraisTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getDadosGeraisTabbedPane() {
		if (dadosGeraisTabbedPane == null) {
			dadosGeraisTabbedPane = new JTabbedPane();
			dadosGeraisTabbedPane.setBounds(new Rectangle(0, 5, 635, 314));
			dadosGeraisTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			//dadosGeraisTabbedPane.addTab("Selecione a Cateogira",getCategoriaMainPanel());
			dadosGeraisTabbedPane.addTab("Dados Gerais",getNomaPalavraPanel());
			dadosGeraisTabbedPane.addTab("Dados do Treinamento",getDadosSoftwarePanel());
			dadosGeraisTabbedPane.addTab("Descrição, Avaliação e Categoria", getDescricaoMainPanel());			
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
			resumoLabel.setBounds(new Rectangle(6, 76, 139, 22));
			resumoLabel.setText("Objetivo Geral:");
			solutionTypeLabel = new JLabel();
			solutionTypeLabel.setBounds(new Rectangle(5, 52, 140, 22));
			solutionTypeLabel.setText("Tipo da Solução:");
			keywordsLabel = new JLabel();
			keywordsLabel.setBounds(new Rectangle(5, 28, 140, 22));
			keywordsLabel.setText("Palavras-Chave:");
			solutionNameLabel = new JLabel();
			solutionNameLabel.setBounds(new Rectangle(5, 4, 140, 22));
			solutionNameLabel.setText("Nome do Treinamento:");
			objetivosLabel = new JLabel();
			objetivosLabel.setBounds(new Rectangle(5, 137, 143, 20));
			objetivosLabel.setText("Objetivos Específicos:");
			nomaPalavraPanel = new JPanel();
			nomaPalavraPanel.setLayout(null);
			nomaPalavraPanel.setBounds(new Rectangle(5, 6, 626, 111));
			nomaPalavraPanel.add(objetivosLabel, null);
			nomaPalavraPanel.add(getObjetivosScrollPane(), null);
			
			nomaPalavraPanel.add(getSolutionNameTextField(), null);
			nomaPalavraPanel.add(solutionNameLabel, null);
			nomaPalavraPanel.add(keywordsLabel, null);
			nomaPalavraPanel.add(getKeywordsTextField(), null);
			nomaPalavraPanel.add(solutionTypeLabel, null);
			nomaPalavraPanel.add(getSolutionTypeComboBox(), null);
			nomaPalavraPanel.add(resumoLabel, null);
			nomaPalavraPanel.add(getObjetivoEspecificoScrollPane(), null);
		}
		return nomaPalavraPanel;
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
			keywordsTextField.setBounds(new Rectangle(150, 28, 470, 22));
			this.binder.addBindProperty(this.sol, this.keywordsTextField, "keyWords");
			
			this.hashComps.put("keyWords", this.keywordsTextField);
			JWarningComponent warn = new JWarningComponent( this.keywordsTextField);
			warn.setBounds(new Rectangle(150, 28, 470, 22));
			return warn;
		}
		return keywordsTextField;
	}

	/**
	 * This method initializes solutionTypeComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getSolutionTypeComboBox() {
		if (solutionTypeComboBox == null) {
			solutionTypeComboBox = new JComboBox();
			solutionTypeComboBox.setBounds(new Rectangle(150, 52, 470, 22));
			CommercialSolutionType val[] = CommercialSolutionType.values();
			for (int i=0; i < val.length; i++){
				solutionTypeComboBox.addItem(val[i].name().replace("_"," "));
			}
			this.binder.addBindProperty(this.sol, solutionTypeComboBox, "solutionType");
			
			this.hashComps.put("solutionType", solutionTypeComboBox);
			JWarningComponent warn = new JWarningComponent(solutionTypeComboBox);
			warn.setBounds(new Rectangle(150, 52, 470, 22));
			return warn;
		}
		return solutionTypeComboBox;
	}
	


	/**
	 * This method initializes solutionAbstractScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getObjetivoEspecificoScrollPane() {
		if (objetivoEspecificoScrollPane == null) {
			objetivoEspecificoScrollPane = new JScrollPane();
			objetivoEspecificoScrollPane.setBounds(new Rectangle(150, 76, 470, 82));
			objetivoEspecificoScrollPane.add(getObetivoEspecíficoTextPane());
			objetivoEspecificoScrollPane.setViewportView(getObetivoEspecíficoTextPane());
		}
		return objetivoEspecificoScrollPane;
	}

	/**
	 * This method initializes solutionAbractTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getObetivoEspecíficoTextPane() {
		if (obetivoEspecíficoTextPane == null) {
			obetivoEspecíficoTextPane = new JTextPane();
			obetivoEspecíficoTextPane.setBounds(new Rectangle(150, 76, 470, 82));
			this.binder.addBindProperty(this.sol, this.obetivoEspecíficoTextPane, "resumo");
			
			this.hashComps.put("resumo", this.obetivoEspecíficoTextPane);
			JWarningComponent warn = new JWarningComponent( this.obetivoEspecíficoTextPane);
			warn.setBounds(new Rectangle(150, 76, 470, 82));
			return warn;
		}
		return obetivoEspecíficoTextPane;
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
			solutionNameTextField.setBounds(new Rectangle(150, 4, 470, 22));
			this.binder.addBindProperty(this.sol, this.solutionNameTextField, "nome");
			
			this.hashComps.put("nome", this.solutionNameTextField);
			JWarningComponent warn = new JWarningComponent( this.solutionNameTextField);
			warn.setBounds(new Rectangle(150, 4, 470, 22));
			return warn;
		}
		return solutionNameTextField;
	}

	/**
	 * This method initializes dadosSoftwarePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDadosSoftwarePanel() {
		if (dadosSoftwarePanel == null) {
			conteudoProgramaticoLabel = new JLabel();
			conteudoProgramaticoLabel.setBounds(new Rectangle(10, 130, 111, 20));
			conteudoProgramaticoLabel.setText("Cont. Programático:");
			softwaresLabel = new JLabel();
			softwaresLabel.setBounds(new Rectangle(10, 49, 111, 20));
			softwaresLabel.setText("Softwares:");
			
			tecnologiasLabel = new JLabel();
			tecnologiasLabel.setBounds(new Rectangle(459, 28, 111, 20));
			tecnologiasLabel.setText("Tecnologias:");
			materialApoioLabel = new JLabel();
			materialApoioLabel.setBounds(new Rectangle(10, 28, 111, 20));
			materialApoioLabel.setText("Material de Apoio:");
			cargaHorariaLabel = new JLabel();
			cargaHorariaLabel.setBounds(new Rectangle(459, 5, 97, 20));
			cargaHorariaLabel.setText("Carga Horária:");
			tipoTreinamentoLabel = new JLabel();
			tipoTreinamentoLabel.setBounds(new Rectangle(160, 5, 121, 20));
			tipoTreinamentoLabel.setText("Tipo do Treinamento:");
			codigoLabel = new JLabel();
			codigoLabel.setBounds(new Rectangle(10, 5, 62, 20));
			codigoLabel.setText("Código - 6:");
			dadosSoftwarePanel = new JPanel();
			dadosSoftwarePanel.setLayout(null);
			dadosSoftwarePanel.add(codigoLabel, null);
			dadosSoftwarePanel.add(getCodigoTextField(), null);
			dadosSoftwarePanel.add(tipoTreinamentoLabel, null);
			dadosSoftwarePanel.add(getTipoTreinamentoComboBox(), null);
			dadosSoftwarePanel.add(cargaHorariaLabel, null);
			dadosSoftwarePanel.add(getCargaHorariaTextField(), null);
			dadosSoftwarePanel.add(materialApoioLabel, null);
			dadosSoftwarePanel.add(getMaterialApoioTextField(), null);
			dadosSoftwarePanel.add(tecnologiasLabel, null);
			dadosSoftwarePanel.add(tecnologiasScrollPane(), null);
			dadosSoftwarePanel.add(softwaresLabel, null);
			
			dadosSoftwarePanel.add(getSoftwaresScrollPane(), null);
			dadosSoftwarePanel.add(conteudoProgramaticoLabel, null);
			dadosSoftwarePanel.add(getConteudoProgramaticoScrollPane(), null);
		}
		return dadosSoftwarePanel;
	}

	/**
	 * This method initializes siglaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getCodigoTextField() {
		if (codigoTextField == null) {
			codigoTextField = new JTextField("T-");
			new LengthLimitedDocument(6,codigoTextField);
			

			codigoTextField.setBounds(new Rectangle(76, 5, 74, 20));
			this.binder.addBindProperty(this.sol, this.codigoTextField, "codigo");
			
			this.hashComps.put("codigo", this.codigoTextField);
			JWarningComponent warn = new JWarningComponent( this.codigoTextField);
			warn.setBounds(new Rectangle(76, 5, 74, 20));
			return warn;
		}
		return codigoTextField;
	}
	


	/**
	 * This method initializes tipoSoftwareComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getTipoTreinamentoComboBox() {
		if (tipoTreinamentoComboBox == null) {
			tipoTreinamentoComboBox = new JComboBox();
			tipoTreinamentoComboBox.setBounds(new Rectangle(276, 5, 179, 20));
			TrainingSolution.TrainingSolutionType val[] = TrainingSolution.TrainingSolutionType.values();
			for(int i=0; i < val.length; i++){
				tipoTreinamentoComboBox.addItem(val[i].name());
			}
			this.binder.addBindProperty(this.sol, this.tipoTreinamentoComboBox, "tipoTreinamento");
			
			this.hashComps.put("tipoTreinamento", this.tipoTreinamentoComboBox);
			JWarningComponent warn = new JWarningComponent( this.tipoTreinamentoComboBox);
			warn.setBounds(new Rectangle(276, 5, 179, 20));
			return warn;
		}
		return tipoTreinamentoComboBox;
	}

	/**
	 * This method initializes versaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getCargaHorariaTextField() {
		if (cargaHorariaTextField == null) {
			cargaHorariaTextField = new JTextField();
			cargaHorariaTextField.setBounds(new Rectangle(535, 5, 86, 20));
			this.binder.addBindProperty(this.sol, this.cargaHorariaTextField, "cargaHoraria");
			
			this.hashComps.put("cargaHoraria", this.cargaHorariaTextField);
			JWarningComponent warn = new JWarningComponent( this.cargaHorariaTextField);
			warn.setBounds(new Rectangle(535, 5, 86, 20));
			return warn;
		}
		return cargaHorariaTextField;
	}

	/**
	 * This method initializes urlProjetoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getMaterialApoioTextField() {
		if (materialApoioTextField == null) {
			materialApoioTextField = new JTextField();
			materialApoioTextField.setBounds(new Rectangle(122, 28, 333, 20));
			this.binder.addBindProperty(this.sol, this.materialApoioTextField, "materialApoio");
			
			this.hashComps.put("materialApoio", this.materialApoioTextField);
			JWarningComponent warn = new JWarningComponent( this.materialApoioTextField);
			warn.setBounds(new Rectangle(122, 28, 333, 20));
			return warn;
		}
		return materialApoioTextField;
	}

	private JScrollPane tecnologiasScrollPane;
	
	private JScrollPane tecnologiasScrollPane(){
		if (tecnologiasScrollPane == null){
			tecnologiasScrollPane = new JScrollPane();
			tecnologiasScrollPane.setBounds(new Rectangle(459, 50, 165, 98));
			tecnologiasScrollPane.setViewportView(getTecnologiasTextField());
		}
		return tecnologiasScrollPane;
	}
	/**
	 * This method initializes licencaUsoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getTecnologiasTextField() {
		if (tecnologiasTextField == null) {
			tecnologiasTextField = new JTextPane();
			tecnologiasTextField.setBounds(new Rectangle(459, 50, 167, 98));
			this.binder.addBindProperty(this.sol, this.tecnologiasTextField, "tecnologias");
			
			this.hashComps.put("tecnologias", this.tecnologiasTextField);
			JWarningComponent warn = new JWarningComponent( this.tecnologiasTextField);
			warn.setBounds(new Rectangle(459, 50, 167, 98));
			return warn;
		}
		return tecnologiasTextField;
	}

	private JScrollPane objetivosScrollPane;

	private JTextPane conteudoProgramaticoTextPane = null;


	
	private JScrollPane getObjetivosScrollPane(){
		if (objetivosScrollPane == null){
			objetivosScrollPane = new JScrollPane();
			objetivosScrollPane.setBounds(new Rectangle(5, 160, 615, 120));
			objetivosScrollPane.add(getObjetivosTextPane());
			objetivosScrollPane.setViewportView(getObjetivosTextPane());
		}
		return objetivosScrollPane;
	}
	/**
	 * This method initializes plataformaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getObjetivosTextPane() {
		if (objetivosTextPane == null) {
			objetivosTextPane = new JTextPane();
			objetivosTextPane.setBounds(new Rectangle(5, 160, 615, 120));
			this.binder.addBindProperty(this.sol, this.objetivosTextPane, "objetivos");
			
			this.hashComps.put("objetivos", this.objetivosTextPane);
			JWarningComponent warn = new JWarningComponent( this.objetivosTextPane);
			warn.setBounds(new Rectangle(5, 160, 615, 120));
			return warn;
		}
		return objetivosTextPane;
	}

	/**
	 * This method initializes sistemasOperacionaisScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSoftwaresScrollPane() {
		if (softwaresScrollPane == null) {
			softwaresScrollPane = new JScrollPane();
			softwaresScrollPane.setBounds(new Rectangle(122, 49, 333, 100));
			softwaresScrollPane.setViewportView(getSoftwaresTextPane());
		}
		return softwaresScrollPane;
	}

	/**
	 * This method initializes sistemasOperacionaisTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getSoftwaresTextPane() {
		if (softwaresTextPane == null) {
			softwaresTextPane = new JTextPane();
			this.binder.addBindProperty(this.sol, this.softwaresTextPane, "softwares");
			
			this.hashComps.put("softwares", this.softwaresTextPane);
			JWarningComponent warn = new JWarningComponent( this.softwaresTextPane);
			warn.setBounds(new Rectangle(458, 51, 165, 68));
			return warn;
		}
		return softwaresTextPane;
	}

	/**
	 * This method initializes funcionalidadesScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getConteudoProgramaticoScrollPane() {
		if (conteudoProgramaticoScrollPane == null) {
			conteudoProgramaticoScrollPane = new JScrollPane();
			conteudoProgramaticoScrollPane.setBounds(new Rectangle(10, 154, 614, 127));
			conteudoProgramaticoScrollPane.setViewportView(getConteudoProgramaticoTextPane());
		}
		return conteudoProgramaticoScrollPane;
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
			descricaoMainPanel.add(getSubCategoriasPanel(), null);
			
		}
		return descricaoMainPanel;
	}

	/**
	 * This method initializes funcionalidadesTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getConteudoProgramaticoTextPane() {
		if (conteudoProgramaticoTextPane == null) {
			conteudoProgramaticoTextPane = new JTextPane();
			this.binder.addBindProperty(this.sol, this.conteudoProgramaticoTextPane, "conteudoProgramatico");
			
			this.hashComps.put("conteudoProgramatico", this.conteudoProgramaticoTextPane);
			JWarningComponent warn = new JWarningComponent( this.conteudoProgramaticoTextPane);
			warn.setBounds(new Rectangle(10, 122, 614, 63));
			return warn;
		}
		return conteudoProgramaticoTextPane;
	}
	
	protected JScrollPane descricaoScrollPane;
	
	protected JScrollPane getDescricaoScrollPane(){
		if(descricaoScrollPane == null){
			descricaoScrollPane = new JScrollPane();
			descricaoScrollPane.setSize(new Dimension(473, 133));
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
	
	public TrainingSolution validateTrainingSolutionForm() throws Exception{
		setErrorIcon(false);
		binder.bind(sol);
		/*String val = (String) getSolutionTypeComboBox().getSelectedItem();
		sol.setSolutionType(CommercialSolutionType.valueOf(val.replace(" ","_")));	
		
		String val2 = (String) tipoTreinamentoComboBox.getSelectedItem();
		sol.setTipoTreinamento(TrainingSolution.TrainingSolutionType.valueOf(val2.replace(" ","_")));	*/
		if (!validateTrainingSolutionBean()) throw new Exception("Bean Not Validated!");
		
		if (getSelectedElement() == null) throw new Exception("traningSolution.categoria");
		else sol.setCategoria(getSelectedElement());
		
		return sol;
	}
	
	public TrainingSolution getSol(){
		return sol;
	}
	public TrainingSolution cadastreTrainingSolution() throws Exception{
		TrainingSolution p = validateTrainingSolutionForm();
		
		if (p.getId() == 0 ){
			TrainingSolution prod = (TrainingSolution) RemoteServicesUtility.getInstance().save(p);
			return prod;
		}
		else{
			TrainingSolution prod = (TrainingSolution) RemoteServicesUtility.getInstance().update(p);
			return prod;
		}
		
	}

	
	@SuppressWarnings("unchecked")
	public boolean validateTrainingSolutionBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.sol, "trainingSolution");
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
			getAvaliacaoTextField().firePropertyChange("warnBorder", false, true);
			try {
				getErrorPanel().addError("O campo Avaliação deve conter um valor maior do que zero",getAvaliacaoTextField());
			} catch (Exception e) {
				e.printStackTrace();
			}
			getAvaliacaoTextField().setToolTipText("O campo Avaliação deve conter um valor maior do que zero");
		}
		
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
		//Nunca definir um novo objeto entidade!!!
		sol.setCategoria(getSelectedElement());
		sol.setTrainingFormationItens(null);
		sol.setId(0);
		sol.setDescricao(null);
		sol.setResumo(null);
		sol.setKeyWords(null);
		sol.setNome(null);
		sol.setSolutionType(CommercialSolutionType.Treinamentos);		
		sol.setAvaliacao(0.0f);
		sol.setImagens(new ArrayList<Imagem>());
		
		this.sol.setCodigo(null);
		this.sol.setConteudoProgramatico(null);
		this.sol.setCargaHoraria(0);
		this.sol.setTecnologias(null);
		this.sol.setMaterialApoio(null);
		this.sol.setSoftwares(null);
		this.sol.setTipoTreinamento(null);
		this.sol.setObjetivos(null);
		this.sol.setAutor(null);
		
		imgIndex=0;
		updateImage();
		binder.reverseBind(this.sol);
		this.avaliacaoTextField.setText(Moeda.getValorReal(sol.getAvaliacao()));
		this.setErrorIcon(false);
		updateUI();
	}
	
	public void editRegister(TrainingSolution objComsol ) throws Exception{
		if (sol == null){
			throw new Exception("O bean solution não pode ser substituído!");
		}
		if (objComsol == null){
			throw new Exception("O solution editado não pode ser nulo!");
		}
		try {

			objComsol = (TrainingSolution) RemoteComercialSolutionService.getInstance().
				getTrainingSolutionByIdCascadingProperties(objComsol.getId(), true, true, true);

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
			
			this.sol.setImagens(objComsol.getImagens());
			
			this.sol.setCodigo(objComsol.getCodigo());
			this.sol.setConteudoProgramatico(objComsol.getConteudoProgramatico());
			this.sol.setCargaHoraria(objComsol.getCargaHoraria());
			this.sol.setTecnologias(objComsol.getTecnologias());
			this.sol.setMaterialApoio(objComsol.getMaterialApoio());
			this.sol.setSoftwares(objComsol.getSoftwares());
			this.sol.setTipoTreinamento(objComsol.getTipoTreinamento());
			this.sol.setObjetivos(objComsol.getObjetivos());
			this.sol.setAutor(objComsol.getAutor());
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		setSelectedElement(sol.getCategoria());
		
		
		if (sol.getImagens() != null){
			sol.getImagens().iterator().hasNext();	
			imgIndex=0;
			updateImage();
		}
		getOutrasInformacoesPanel().setSol(sol);
		getOutrasInformacoesPanel().getBaseTable().updateTable();
		
		binder.reverseBind(this.sol);
		this.avaliacaoTextField.setText(Moeda.getValorReal(sol.getAvaliacao()));
		this.formatadorDescricaoCheckBox.setSelected(sol.getUtilizarFormatador());
		this.setErrorIcon(false);
		
	}

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
					}else{
						JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Selecione primeiro a categoria do Treinamento.",
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
			avaliacaoCategoriaPanel.setLocation(new Point(14, 99));
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
			avaliacaoTextFieldLabel = new JLabel("Avaliação:");
			avaliacaoTextFieldLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));			
		}
		return avaliacaoTextFieldLabel;
	}
	
	protected JPanel categoriaMainPanel;
	
	protected JPanel getCategoriaMainPanel(){
		if (categoriaMainPanel == null){
			categoriaMainPanel = new JPanel();
			categoriaMainPanel.setLayout(null);
		}
		return categoriaMainPanel;
	}
	
	protected JPanel getSubCategoriasPanel(){
		if(subCategoriasPanel == null){
			subCategoriasPanel = new JPanel();
			subCategoriasPanel.setLayout(null);
			subCategoriasPanel.setBounds(new Rectangle(13, 144, 610, 134));
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
			treeScrollPane.setSize(new Dimension(576, 111));
			treeScrollPane.setLocation(new Point(34, 23));
			treeScrollPane.setLayout(new javax.swing.ScrollPaneLayout());
			treeScrollPane.setViewportView(getTreeController().getTree());
		}
		return treeScrollPane;
	}
	
	protected JPanel treeButtonsPanel;
	
	protected JPanel getTreeButtonsPanel(){
		if (treeButtonsPanel == null){
			treeButtonsPanel = new JPanel();
			treeButtonsPanel.setLayout(new GridLayout(3,1));
			treeButtonsPanel.setSize(new Dimension(30, 69));
			treeButtonsPanel.setLocation(new Point(0, 21));
			treeButtonsPanel.add(getRefreshTree());
			treeButtonsPanel.add(getEditTree());
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
					AdapitVirtualFrame.getInstance().cadastrarCategorias();
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
			cadastreButtonsPanel.add(getListarTrainingSolutionsButton());
			cadastreButtonsPanel.add(getAddUpdateButton());
			cadastreButtonsPanel.add(getListUpdateButton());
		}
		return cadastreButtonsPanel;
	}
	
	private JButton addUpdateButton;
	private JButton getAddUpdateButton(){
		if(addUpdateButton == null){
			addUpdateButton = new JButton("Adicionar Versão");
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
					atualizarTrainingSolution();
				}				
			});
			atualizarButton.setIcon(getIcon("/imgs/training-save.png",18,18));
		}
		return atualizarButton;
	}
	
	public void atualizarTrainingSolution(){
		try {
			TrainingSolution p= cadastreTrainingSolution();
			JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Cadastro realizado com sucesso.",
					"Cadastro de Treinamento", JOptionPane.INFORMATION_MESSAGE);
			
			editRegister(p);
			
			setSelectedElement(p.getCategoria());
			getAtualizarButton().setText("Atualizar");
			
		} catch (Exception e) {
			if (e.getMessage() != null && e.getMessage().equals("trainingSolution.categoria")){
				JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "É necessário selecionar uma categoria.",
						"Categoria não selecionada", JOptionPane.ERROR_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Algums campos não foram preenchidos corretamente.",
						"Campos não preenchidos corretamente", JOptionPane.ERROR_MESSAGE);
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
					treeController.updateTree();					
					getAtualizarButton().setText("Cadastrar");
					
				}				
			});
			novoButton.setIcon(getIcon("/imgs/training-add.png",18,18));
		}
		return novoButton;
	}
	
	protected JButton getListarTrainingSolutionsButton(){
		if(listarTrainingSolutionsButton == null){
			listarTrainingSolutionsButton = new JButton("Listar Treinamentos");
			listarTrainingSolutionsButton.setIcon(getIcon("/imgs/training-key.png",18,18));
			listarTrainingSolutionsButton.setSize(new java.awt.Dimension(150,20));
			listarTrainingSolutionsButton.setLocation(new java.awt.Point(0,72));
			listarTrainingSolutionsButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listCommercialSolutions();
				}				
			});
		}
		return listarTrainingSolutionsButton;
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
			tabbedPane.addTab("Dados do Treinamento", getContentPanel());
			tabbedPane.addTab("Imagens do Treinamento", getImageComponentsPanel());
			tabbedPane.addTab("Detalhes do Treinamento", getOutrasInformacoesPanel());
			
			tabbedPane.addChangeListener(new ChangeListener(){
				@Override
				public void stateChanged(ChangeEvent evt) {
					if (sol.getId() <= 0){
						if (tabbedPane.getSelectedIndex() == 1){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir a imagem você precisa cadastrar o Treinamento",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
						if (tabbedPane.getSelectedIndex() == 2){
							JOptionPane.showMessageDialog(AdapitVirtualFrame.getInstance(), 
									"Antes de inserir os detalhes você precisa cadastrar o Treinamento",
									"Tarefas pendentes",JOptionPane.WARNING_MESSAGE);
							tabbedPane.setSelectedIndex(0);
						}
					}
					
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
					updateImage();
				}				
			});
		}
		return previousButton;
	}

	
	private Object removeOptions[] = {"Retirar a imagem desse Treinamento",
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
						int resp = JOptionPane.showOptionDialog(
								TrainingSolutionCadastreForm.this, 
								"Selecione a opção para remover a imagem",
								"Remover Imagem",
								JOptionPane.YES_NO_CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE,
								null, removeOptions, removeOptions[0]);
						if (resp == 1){							
							removerImagemDefinitivamente();										
							updateImage();
							updateImagensIndice();
						}else if (resp == 0){
							if (sol.getImagens().size() == 1){
								try {
									removerImagemTrainingSolution();
									sol.getImagens().remove(img);
									img = null;
									imgIndex = 0;
								} catch (Exception e) {
									e.printStackTrace();
									AdapitVirtualFrame.getInstance().showErrorDialog("Remover referência de imagem", "Não foi possível desvincular a imagem deste Treinamento!");
								}
							}
							else{
								try {
									removerImagemTrainingSolution();
									sol.getImagens().remove(imgIndex);
									if (sol.getImagens().size() == 0) imgIndex = 0;
									else if (imgIndex > 1) imgIndex = imgIndex-1;
									else imgIndex = 0;
									img = sol.getImagens().get(imgIndex);
								} catch (Exception e) {
									e.printStackTrace();
									AdapitVirtualFrame.getInstance().showErrorDialog("Remover referência de imagem", "Não foi possível desvincular a imagem deste Treinamento!");
								}
								
							}
																	
							updateImage();
							updateImagensIndice();
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
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
				Imagem im = removerImagemTrainingSolution();
				RemoteServicesUtility.getInstance().delete(im);				
				sol.getImagens().remove(img);
				img = null;
				imgIndex = 0;
			}
			else{
				Imagem im = removerImagemTrainingSolution();
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
	
	private Imagem removerImagemTrainingSolution() throws Exception{
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
							JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Você deve informar um valor maior que zero!", "O campo só aceita números maiores que zero", JOptionPane.ERROR_MESSAGE);
							imgNumTextField.setText(""+(imgIndex+1));
						}else if (n > sol.getImagens().size()){
							JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Você deve informar um valor menor que "+n+"!", "Ordem incorreta das imagens", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Este valor não é um número!", "O campo só aceita números", JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Primeiro é preciso cadastrar o Treinamento!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
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
			viewFullImageButton.setText("Visializar em (800 X 800)");
			viewFullImageButton.setHorizontalTextPosition(JButton.CENTER);
			viewFullImageButton.setVerticalTextPosition(JButton.BOTTOM);
			viewFullImageButton.setIcon(new ImageIcon(getClass().getResource("/imgs/zoom.png")));
			viewFullImageButton.setBounds(new Rectangle(450, 65, 176, 58));
			viewFullImageButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					if (img != null){
						try {
							ViewFullImageDialog jd = new ViewFullImageDialog(img,scale,scale);							
							jd.setVisible(true);							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}					
				}
			});
		}
		return viewFullImageButton;
	}

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
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	
	private JPanel imageComponentsPanel;

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
								TrainingSolution prod = (TrainingSolution)
									RemoteComercialSolutionService.getInstance().loadCommercialSolutionEagerImages(sol);
								sol.setImagens(prod.getImagens());
								imgIndex=0;
								updateImage();								
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
						JOptionPane.showMessageDialog(TrainingSolutionCadastreForm.this, "Primeiro é preciso cadastrar o Treinamento!","Adicionar imagem",JOptionPane.WARNING_MESSAGE);
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
								
			} catch (Exception e1) {
				e1.printStackTrace();	
				AdapitVirtualFrame.getInstance().showErrorDialog("Cadastro de Imagem",
						"Não foi possível adicionar a imagem no Treinamento");
			}				
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
				
			}
		}
	}
	
	public void updateDescricaoImage() throws Exception{
		RemoteImagemService.getInstance().updateImageDescriptionByImageId(img.getId(), img.getDescription());
	}
	
	public void updateRotuloImage() throws Exception{
		RemoteImagemService.getInstance().updateImageRotuloByImageId(img.getId(), img.getRotulo());
	}
	
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
				anexar = new JButton("Anexar imagens selecionadas no Treinamento " + sol.getId());
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
					TrainingSolution prod = (TrainingSolution) RemoteComercialSolutionService.getInstance().attachImageIntoCommercialSolution(sol, list);
					sol.setImagens(prod.getImagens());
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Imagens em TrainingSolutions", "Imagens anexadas com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				
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

	private JLabel descricaoLabel = null;

	private JTextPane descricaoImageTextArea = null;

	private JScrollPane descricaoImgScrollPane = null;

	private JCheckBox formatadorDescricaoCheckBox = null;

	private OutrasInformacoesPanel outrasInformacoesPanel = null;

	
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
			Iterator<Imagem> it = sol.getImagens().iterator();
			while (it.hasNext()) {
				Imagem im = it.next();
				RemoteServicesUtility.getInstance().createOrUpdate(im);					
			}
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
			formatadorDescricaoCheckBox.setText("Utilizar o formatador de descrição para apresentar os dados do Software na web");
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
	private OutrasInformacoesPanel getOutrasInformacoesPanel() {
		if (outrasInformacoesPanel == null) {
			
			outrasInformacoesPanel = new OutrasInformacoesPanel();			
			
		}
		return outrasInformacoesPanel;
	}
}  // @jve:decl-index=0:visual-constraint="10,10"
