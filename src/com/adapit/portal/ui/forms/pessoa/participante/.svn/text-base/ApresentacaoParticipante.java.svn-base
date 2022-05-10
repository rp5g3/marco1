package com.adapit.portal.ui.forms.pessoa.participante;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.ui.forms.HtmlEditorEventListener;
import com.adapit.portal.ui.forms.HtmlEditorPanel;
import com.adapit.portal.ui.forms.pessoa.BindHandler;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.warning.JWarningComponent;

public class ApresentacaoParticipante extends JPanel{

	private static final long serialVersionUID = -6728573691510586972L;

	private PessoaEmDivulgacao participante;
	
	//private BindHandler observer;
	
	public ApresentacaoParticipante(BindHandler observer){
		super();
		//this.observer = observer;
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());
		
		JPanel textos = new JPanel(new GridLayout(2,1));
		
		JPanel apresPanel = new JPanel();
		apresPanel.setLayout(new BorderLayout());
		apresPanel.setBorder(BorderFactory.createTitledBorder("Apresentação"));
		apresPanel.add(getPartApresentacaoEditor(),BorderLayout.CENTER);
		textos.add(apresPanel);
		
		JPanel descPanel = new JPanel();
		descPanel.setLayout(new BorderLayout());
		descPanel.setBorder(BorderFactory.createTitledBorder("Descrição"));
		descPanel.add(getPartDescEditor(),BorderLayout.CENTER);
		textos.add(descPanel);
		add(textos,BorderLayout.CENTER);
		
		JPanel aPanel = new JPanel();
		aPanel.setPreferredSize(new Dimension(100,44));
		aPanel.setLayout(new GridLayout(2,3));
		aPanel.add(divulgavel);
		aPanel.add(managerExp);
		aPanel.add(softDevExp);
		aPanel.add(saleExp);
		aPanel.add(trainExp);
		aPanel.add(researchExp);
		
		add(aPanel,BorderLayout.NORTH);
	}
	
	private HtmlEditorPanel partApresentacaoEditor;
	private HtmlEditorPanel getPartApresentacaoEditor() {
		if (partApresentacaoEditor == null) {
			getPartApresTextPane();
			partApresentacaoEditor = new HtmlEditorPanel((JTextComponent)getPartApresTextPane(),partApresEventListener,null);
			partApresentacaoEditor.setBounds(new Rectangle(5, 103, 619, 178));
		}
		return partApresentacaoEditor;
	}

	private HtmlEditorEventListener partApresEventListener = null;

	private JTextPane partApresTextPane;
	private JComponent getPartApresTextPane() {
		if (partApresTextPane == null) {
			partApresTextPane = new JTextPane();
			partApresEventListener = new HtmlEditorEventListener(partApresTextPane,AdapitVirtualFrame.getInstance(),"Apresentação");
			partApresTextPane.addKeyListener(partApresEventListener);
			//observer.getBinder().addBindProperty(participante, this.partApresTextPane, "apresentacao");
			
			//observer.getHashComps().put("apresentacao", this.partApresTextPane);
			JWarningComponent warn = new JWarningComponent( this.partApresTextPane);
			warn.setBounds(new Rectangle(6, 76, 616, 109));
			return warn;
		}
		return partApresTextPane;
	}
	
	private HtmlEditorPanel partDescEditor;
	private HtmlEditorPanel getPartDescEditor() {
		if (partDescEditor == null) {
			getPartDescTextPane();
			partDescEditor = new HtmlEditorPanel((JTextComponent)getPartDescTextPane(),partDescEventListener,null);
			partDescEditor.setBounds(new Rectangle(5, 103, 619, 178));
		}
		return partDescEditor;
	}

	private HtmlEditorEventListener partDescEventListener = null;

	private JTextPane partDescTextPane;
	private JComponent getPartDescTextPane() {
		if (partDescTextPane == null) {
			partDescTextPane = new JTextPane();
			partDescEventListener = new HtmlEditorEventListener(partDescTextPane,AdapitVirtualFrame.getInstance(),"Descrição");
			partDescTextPane.addKeyListener(partDescEventListener);
			//observer.getBinder().addBindProperty(participante, this.partDescTextPane, "descricao");
			
			//observer.getHashComps().put("descricao", this.partDescTextPane);
			JWarningComponent warn = new JWarningComponent( this.partDescTextPane);
			warn.setBounds(new Rectangle(6, 76, 616, 109));
			return warn;
		}
		return partDescTextPane;
	}
	
	
	private JCheckBox 
		divulgavel = new JCheckBox("1 - Divulgável"), 
		managerExp = new JCheckBox("2 - Experiência Administrativa"),
		softDevExp = new JCheckBox("3 - Experiência com Desenvolvimento de Software"), 
		saleExp = new JCheckBox("4 - Experiência com Vendas"), 
		trainExp = new JCheckBox("5 - Experiência com treinamentos"), 
		researchExp = new JCheckBox("6 - Experiência em P&D");

	
	public void bind(PessoaEmDivulgacao p) throws Exception{
		participante = p;
		divulgavel.setSelected(p.isDivulgavel());
		managerExp.setSelected(p.isManagerExp());
		softDevExp.setSelected(p.isSoftDevExp());
		saleExp.setSelected(p.isSaleExp());
		trainExp.setSelected(p.isTrainExp());
		researchExp.setSelected(p.isResearchExp());
		partDescTextPane.setText(p.getDescricao());
		partDescEditor.updateByHtml();
		//getApresentacaoEditor().setText(p.getApresentacao());
		partApresTextPane.setText(p.getApresentacao());
		partApresentacaoEditor.updateByHtml();
		//observer.binder.bind(participante);
	}
	
	public PessoaEmDivulgacao reverseBind(){
		participante.setManagerExp(managerExp.isSelected());
		participante.setDivulgavel(divulgavel.isSelected());
		participante.setSoftDevExp(softDevExp.isSelected());
		participante.setSaleExp(saleExp.isSelected());
		participante.setTrainExp(trainExp.isSelected());
		participante.setResearchExp(researchExp.isSelected());
		participante.setApresentacao(partApresTextPane.getText());
		participante.setDescricao(partDescTextPane.getText());
		return participante;
	}
	
}
