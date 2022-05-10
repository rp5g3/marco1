package com.adapit.portal.ui.forms.software;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.entidades.SoftwareSolution;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.HtmlEditorPanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.warning.JWarningComponent;

public class SoftwareDetalhesPanel extends JPanel{
	private SoftwareSolutionCadastreForm observer;
	private JLabel funcionalidadesLabel = null;
	private JLabel sistemasOperacionaisLabel = null;
	private JLabel plataformaLabel = null;
	private JLabel licencaUsoLabel = null;
	private JLabel urlProjetoLabel = null;
	private JLabel versaoLabel = null;
	private JLabel tipoSoftwareLabel = null;
	private JLabel siglaLabel = null;
	private JTextField siglaTextField = null;
	private JComboBox tipoSoftwareComboBox = null;
	private JTextField versaoTextField = null;
	private JTextField urlProjetoTextField = null;
	private JTextField licencaUsoTextField = null;	
	private JTextPane plataformaTextPane = null;
	private JScrollPane sistemasOperacionaisScrollPane = null;
	private JTextPane sistemasOperacionaisTextPane = null;
	private JScrollPane plataformaScrollPane;
	private JTextPane funcionalidadesTextPane = null;
	
	public SoftwareDetalhesPanel(SoftwareSolutionCadastreForm observer){
		this.observer = observer;
		initialize();
	}
	
	private void initialize(){
		setLayout(new BorderLayout());
		add(getTopPanel(), BorderLayout.NORTH);
		add(getFuncionalidadesHtmlEditor(), BorderLayout.CENTER);
	}
	
	private JPanel topPanel;
	
	private JPanel getTopPanel(){
		if(topPanel == null){
			topPanel = new JPanel();
			topPanel.setLayout(null);
			topPanel.setPreferredSize(new Dimension(653,150));
			funcionalidadesLabel = new JLabel();
			funcionalidadesLabel.setBounds(new Rectangle(10, 120, 210, 20));
			funcionalidadesLabel.setText("Funcionalidades:");
			sistemasOperacionaisLabel = new JLabel();
			sistemasOperacionaisLabel.setBounds(new Rectangle(459, 28, 164, 20));
			sistemasOperacionaisLabel.setText("Sistemas Operacionais:");
			plataformaLabel = new JLabel();
			plataformaLabel.setBounds(new Rectangle(10, 75, 111, 20));
			plataformaLabel.setText("Plataforma:");
			licencaUsoLabel = new JLabel();
			licencaUsoLabel.setBounds(new Rectangle(10, 51, 111, 20));
			licencaUsoLabel.setText("Licença de Uso:");
			urlProjetoLabel = new JLabel();
			urlProjetoLabel.setBounds(new Rectangle(10, 28, 111, 20));
			urlProjetoLabel.setText("URL do Projeto:");
			versaoLabel = new JLabel();
			versaoLabel.setBounds(new Rectangle(459, 5, 74, 20));
			versaoLabel.setText("Versão:");
			tipoSoftwareLabel = new JLabel();
			tipoSoftwareLabel.setBounds(new Rectangle(160, 5, 111, 20));
			tipoSoftwareLabel.setText("Tipo do Sistema:");
			siglaLabel = new JLabel();
			siglaLabel.setBounds(new Rectangle(10, 5, 62, 20));
			siglaLabel.setText("Sigla:");
			
			topPanel.add(siglaLabel, null);
			topPanel.add(getSiglaTextField(), null);
			topPanel.add(tipoSoftwareLabel, null);
			topPanel.add(getTipoSoftwareComboBox(), null);
			topPanel.add(versaoLabel, null);
			topPanel.add(getVersaoTextField(), null);
			topPanel.add(urlProjetoLabel, null);
			topPanel.add(getUrlProjetoTextField(), null);
			topPanel.add(licencaUsoLabel, null);
			topPanel.add(getLicencaUsoTextField(), null);
			topPanel.add(plataformaLabel, null);
			topPanel.add(getPlataformaScrollPane(), null);
			topPanel.add(sistemasOperacionaisLabel, null);
			topPanel.add(getSistemasOperacionaisScrollPane(), null);
			topPanel.add(funcionalidadesLabel, null);
		}
		return topPanel;
	}

	/**
	 * This method initializes siglaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getSiglaTextField() {
		if (siglaTextField == null) {
			siglaTextField = new JTextField();
			siglaTextField.setBounds(new Rectangle(76, 5, 74, 20));
			observer.binder.addBindProperty(observer.sol, this.siglaTextField, "sigla");
			
			observer.hashComps.put("sigla", this.siglaTextField);
			JWarningComponent warn = new JWarningComponent( this.siglaTextField);
			warn.setBounds(new Rectangle(76, 5, 74, 20));
			return warn;
		}
		return siglaTextField;
	}

	/**
	 * This method initializes tipoSoftwareComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getTipoSoftwareComboBox() {
		if (tipoSoftwareComboBox == null) {
			tipoSoftwareComboBox = new JComboBox();
			tipoSoftwareComboBox.setBounds(new Rectangle(276, 5, 179, 20));
			SoftwareSolution.SoftwareSolutionType val[] = SoftwareSolution.SoftwareSolutionType.values();
			for(int i=0; i < val.length; i++){
				tipoSoftwareComboBox.addItem(observer.messages.getMessage(val[i].name()));
				observer.binder.putEnumValue(observer.messages.getMessage(val[i].name()), val[i]);				
			}
			observer.binder.addBindProperty(observer.sol, this.tipoSoftwareComboBox, "tipoSoftware");
			
			observer.hashComps.put("tipoSoftware", this.tipoSoftwareComboBox);
			JWarningComponent warn = new JWarningComponent( this.tipoSoftwareComboBox);
			warn.setBounds(new Rectangle(276, 5, 179, 20));
			return warn;
		}
		return tipoSoftwareComboBox;
	}

	/**
	 * This method initializes versaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getVersaoTextField() {
		if (versaoTextField == null) {
			versaoTextField = new JTextField();
			versaoTextField.setBounds(new Rectangle(535, 5, 86, 20));
			observer.binder.addBindProperty(observer.sol, this.versaoTextField, "versao");
			
			observer.hashComps.put("versao", this.versaoTextField);
			JWarningComponent warn = new JWarningComponent( this.versaoTextField);
			warn.setBounds(new Rectangle(535, 5, 86, 20));
			return warn;
		}
		return versaoTextField;
	}

	/**
	 * This method initializes urlProjetoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getUrlProjetoTextField() {
		if (urlProjetoTextField == null) {
			urlProjetoTextField = new JTextField();
			urlProjetoTextField.setBounds(new Rectangle(122, 28, 333, 20));
			observer.binder.addBindProperty(observer.sol, this.urlProjetoTextField, "urlProjeto");
			
			observer.hashComps.put("urlProjeto", this.urlProjetoTextField);
			JWarningComponent warn = new JWarningComponent( this.urlProjetoTextField);
			warn.setBounds(new Rectangle(122, 28, 333, 20));
			return warn;
		}
		return urlProjetoTextField;
	}

	/**
	 * This method initializes licencaUsoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getLicencaUsoTextField() {
		if (licencaUsoTextField == null) {
			licencaUsoTextField = new JTextField();
			licencaUsoTextField.setBounds(new Rectangle(122, 51, 333, 20));
			observer.binder.addBindProperty(observer.sol, this.licencaUsoTextField, "licencaUso");
			
			observer.hashComps.put("licencaUso", this.licencaUsoTextField);
			JWarningComponent warn = new JWarningComponent( this.licencaUsoTextField);
			warn.setBounds(new Rectangle(122, 51, 333, 20));
			return warn;
		}
		return licencaUsoTextField;
	}
	
	private JScrollPane getPlataformaScrollPane(){
		if (plataformaScrollPane == null){
			plataformaScrollPane = new JScrollPane();
			plataformaScrollPane.setBounds(new Rectangle(122, 75, 333, 44));
			plataformaScrollPane.setViewportView(getPlataformaTextPane());
		}
		return plataformaScrollPane;
	}
	/**
	 * This method initializes plataformaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getPlataformaTextPane() {
		if (plataformaTextPane == null) {
			plataformaTextPane = new JTextPane();
			plataformaTextPane.setBounds(new Rectangle(122, 75, 333, 44));
			observer.binder.addBindProperty(observer.sol, this.plataformaTextPane, "plataforma");
			
			observer.hashComps.put("plataforma", this.plataformaTextPane);
			JWarningComponent warn = new JWarningComponent( this.plataformaTextPane);
			warn.setBounds(new Rectangle(122, 75, 333, 44));
			return warn;
		}
		return plataformaTextPane;
	}

	/**
	 * This method initializes sistemasOperacionaisScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getSistemasOperacionaisScrollPane() {
		if (sistemasOperacionaisScrollPane == null) {
			sistemasOperacionaisScrollPane = new JScrollPane();
			sistemasOperacionaisScrollPane.setBounds(new Rectangle(458, 51, 165, 68));
			sistemasOperacionaisScrollPane.setViewportView(getSistemasOperacionaisTextPane());
		}
		return sistemasOperacionaisScrollPane;
	}

	/**
	 * This method initializes sistemasOperacionaisTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getSistemasOperacionaisTextPane() {
		if (sistemasOperacionaisTextPane == null) {
			sistemasOperacionaisTextPane = new JTextPane();
			observer.binder.addBindProperty(observer.sol, this.sistemasOperacionaisTextPane, "sistemasOperacionais");
			
			observer.hashComps.put("sistemasOperacionais", this.sistemasOperacionaisTextPane);
			JWarningComponent warn = new JWarningComponent( this.sistemasOperacionaisTextPane);
			warn.setBounds(new Rectangle(458, 51, 165, 68));
			return warn;
		}
		return sistemasOperacionaisTextPane;
	}

	
	private HtmlEditorPanel funcionalidadesHtmlEditor;
	private HtmlEditorPanel getFuncionalidadesHtmlEditor() {
		if (funcionalidadesHtmlEditor == null) {
			getFuncionalidadesTextPane();
			funcionalidadesHtmlEditor = new HtmlEditorPanel((JTextComponent) funcionalidadesTextPane,funcionalidadeEventListener,null);
			funcionalidadesHtmlEditor.setBounds(new Rectangle(10, 121, 614, 160));
		}
		return funcionalidadesHtmlEditor;
	}

	private HtmlEditorEventListener funcionalidadeEventListener = null;
	/**
	 * This method initializes funcionalidadesTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	@SuppressWarnings("unchecked")
	private JComponent getFuncionalidadesTextPane() {
		if (funcionalidadesTextPane == null) {
			funcionalidadesTextPane = new JTextPane();
			funcionalidadeEventListener = new HtmlEditorEventListener(funcionalidadesTextPane,AdapitVirtualFrame.getInstance(),"Funcionalidades do Sistema");
			funcionalidadesTextPane.addKeyListener(funcionalidadeEventListener);
			observer.binder.addBindProperty(observer.sol, this.funcionalidadesTextPane, "funcionalidades");
			
			observer.hashComps.put("funcionalidades", this.funcionalidadesTextPane);
			JWarningComponent warn = new JWarningComponent( this.funcionalidadesTextPane);
			warn.setBounds(new Rectangle(10, 122, 614, 63));
			return warn;
		}
		return funcionalidadesTextPane;
	}

	public void updateByHtml() {
		if(funcionalidadesHtmlEditor.getCss() == null){
			if(observer.sol != null && observer.sol.getId()>0){
				try {
					CssDefinition cdef = RemoteComercialSolutionService.getInstance().getCssDefinition(observer.sol);
					if(cdef != null)
						funcionalidadesHtmlEditor.setCss(cdef.getStyle());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		getFuncionalidadesHtmlEditor().updateByHtml();
	}
}
