package com.adapit.portal.ui.frames.update;

import java.awt.Dimension;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.ui.forms.update.UpdateCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastrarUpdateDialog extends javax.swing.JDialog{

	private ComercialSolution solution;
	public CadastrarUpdateDialog (ComercialSolution c){
		super(AdapitVirtualFrame.getInstance());
		setTitle("Cadastrar versão");
		this.solution = c;
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		updateCadastreForm = new UpdateCadastreForm(solution);
		add(updateCadastreForm,java.awt.BorderLayout.CENTER);		
		setResizable(true);	
	}
	
	private UpdateCadastreForm updateCadastreForm;

	public UpdateCadastreForm getUpdateCadastreForm() {
		return updateCadastreForm;
	}

	public void setUpdateCadastreForm(UpdateCadastreForm updateCadastreForm) {
		this.updateCadastreForm = updateCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"