package com.adapit.portal.ui.frames.solution;

import com.adapit.portal.ui.forms.solution.ComercialSolutionCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class CadastrarComercialSolutionDialog extends javax.swing.JDialog{

	
	public CadastrarComercialSolutionDialog (){
		super(AdapitVirtualFrame.getInstance());
		setTitle("Cadastrar Soluções");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 531));
		comercialSolutionCadastreForm = new ComercialSolutionCadastreForm();
		add(comercialSolutionCadastreForm,java.awt.BorderLayout.CENTER);		
		setResizable(false);	
	}
	
	private ComercialSolutionCadastreForm comercialSolutionCadastreForm;

	public ComercialSolutionCadastreForm getComercialSolutionCadastreForm() {
		return comercialSolutionCadastreForm;
	}

	public void setComercialSolutionCadastreForm(ComercialSolutionCadastreForm produtoCadastreForm) {
		this.comercialSolutionCadastreForm = produtoCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"