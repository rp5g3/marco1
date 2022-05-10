package com.adapit.portal.ui.frames.news;

import com.adapit.portal.ui.forms.news.NewsCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class CadastrarNewsDialog extends javax.swing.JDialog{

	
	public CadastrarNewsDialog (){
		super(AdapitVirtualFrame.getInstance());
		setTitle("Cadastrar News");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		newsCadastreForm = new NewsCadastreForm();
		add(newsCadastreForm,java.awt.BorderLayout.CENTER);		
		setResizable(false);	
	}
	
	private NewsCadastreForm newsCadastreForm;

	public NewsCadastreForm getNewsCadastreForm() {
		return newsCadastreForm;
	}

	public void setNewsCadastreForm(NewsCadastreForm newsCadastreForm) {
		this.newsCadastreForm = newsCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"