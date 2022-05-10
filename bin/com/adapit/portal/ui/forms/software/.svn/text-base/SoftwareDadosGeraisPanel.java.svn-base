package com.adapit.portal.ui.forms.software;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.CommercialSolutionType;
import com.adapit.portal.entidades.CssDefinition;
import com.adapit.portal.services.remote.RemoteComercialSolutionService;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.HtmlEditorPanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.warning.JWarningComponent;

public class SoftwareDadosGeraisPanel extends JPanel{

	private static final long serialVersionUID = -567904974161734136L;
	private SoftwareSolutionCadastreForm observer;	
	private JLabel resumoLabel = null;
	private JLabel solutionTypeLabel = null;
	private JLabel keywordsLabel = null;
	private JLabel solutionNameLabel = null;
	private JTextField solutionNameTextField = null;
	private JTextField keywordsTextField = null;
	private JComboBox solutionTypeComboBox = null;

	
	public SoftwareDadosGeraisPanel(SoftwareSolutionCadastreForm observer){
		this.observer = observer;
		initialize();
	}
	
	private void initialize(){
		setLayout(new BorderLayout());
		add(getTopPanel(), BorderLayout.NORTH);
		add(getApresentacaoEditor(), BorderLayout.CENTER);
	}
	
	
	private JPanel topPanel;
	
	private JPanel getTopPanel(){
		if(topPanel == null){
			topPanel = new JPanel();
			topPanel.setLayout(null);
			topPanel.setPreferredSize(new Dimension(653,100));
			resumoLabel = new JLabel();
			resumoLabel.setBounds(new Rectangle(5, 77, 139, 22));
			resumoLabel.setText("Apresentação:");
			solutionTypeLabel = new JLabel();
			solutionTypeLabel.setBounds(new Rectangle(5, 52, 140, 22));
			solutionTypeLabel.setText("Tipo do Software:");
			keywordsLabel = new JLabel();
			keywordsLabel.setBounds(new Rectangle(5, 28, 140, 22));
			keywordsLabel.setText("Palavras-Chave:");
			solutionNameLabel = new JLabel();
			solutionNameLabel.setBounds(new Rectangle(5, 4, 140, 22));
			solutionNameLabel.setText("Nome do Software:");
			setBounds(new Rectangle(5, 6, 653, 333));
			topPanel.add(getSolutionNameTextField(), null);
			topPanel.add(solutionNameLabel, null);
			topPanel.add(keywordsLabel, null);
			topPanel.add(getKeywordsTextField(), null);
			topPanel.add(solutionTypeLabel, null);
			topPanel.add(getSolutionTypeComboBox(), null);
			topPanel.add(resumoLabel, null);
		}
		return topPanel;
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
			observer.binder.addBindProperty(observer.sol, this.solutionNameTextField, "nome");
			
			observer.hashComps.put("nome", this.solutionNameTextField);
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
			observer.binder.addBindProperty(observer.sol, this.keywordsTextField, "keyWords");
			
			observer.hashComps.put("keyWords", this.keywordsTextField);
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
	@SuppressWarnings("unchecked")
	private JComponent getSolutionTypeComboBox() {
		if (solutionTypeComboBox == null) {
			solutionTypeComboBox = new JComboBox();
			solutionTypeComboBox.setBounds(new Rectangle(150, 52, 474, 22));
			CommercialSolutionType val[] = CommercialSolutionType.values();
			for (int i=0; i < val.length; i++){
				solutionTypeComboBox.addItem(val[i].name().replace("_"," "));
			}
			observer.binder.addBindProperty(observer.sol, this.solutionTypeComboBox, "solutionType");
			
			observer.hashComps.put("solutionType", this.solutionTypeComboBox);
			JWarningComponent warn = new JWarningComponent( this.solutionTypeComboBox);
			warn.setBounds(new Rectangle(150, 52, 474, 22));
			return warn;
		}
		return solutionTypeComboBox;
	}

	private HtmlEditorPanel apresentacaoEditor;
	private HtmlEditorPanel getApresentacaoEditor() {
		if (apresentacaoEditor == null) {
			getSolutionAbractTextPane();
			apresentacaoEditor = new HtmlEditorPanel((JTextComponent)solutionAbractTextPane,apresEventListener,null);
			apresentacaoEditor.setBounds(new Rectangle(5, 103, 619, 178));
		}
		return apresentacaoEditor;
	}

	private HtmlEditorEventListener apresEventListener = null;
	//apresEventListener = new HtmlEditorEventListener(solutionAbractTextPane,AdapitVirtualFrame.getInstance(),"Apresentação do Sistema");

	private JTextPane solutionAbractTextPane = null;
	
	@SuppressWarnings("unchecked")
	private JComponent getSolutionAbractTextPane() {
		if (solutionAbractTextPane == null) {
			solutionAbractTextPane = new JTextPane();
			apresEventListener = new HtmlEditorEventListener(solutionAbractTextPane,AdapitVirtualFrame.getInstance(),"Apresentação do Sistema");
			solutionAbractTextPane.addKeyListener(apresEventListener);
			observer.binder.addBindProperty(observer.sol, this.solutionAbractTextPane, "resumo");
			
			observer.hashComps.put("resumo", this.solutionAbractTextPane);
			JWarningComponent warn = new JWarningComponent( this.solutionAbractTextPane);
			warn.setBounds(new Rectangle(6, 76, 616, 109));
			return warn;
		}
		return solutionAbractTextPane;
	}

	public void updateByHtml() {
		if(apresentacaoEditor.getCss() == null){
			if(observer.sol != null && observer.sol.getId()>0){
				try {
					CssDefinition cdef = RemoteComercialSolutionService.getInstance().getCssDefinition(observer.sol);
					if(cdef != null)
						apresentacaoEditor.setCss(cdef.getStyle());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		getApresentacaoEditor().updateByHtml();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
