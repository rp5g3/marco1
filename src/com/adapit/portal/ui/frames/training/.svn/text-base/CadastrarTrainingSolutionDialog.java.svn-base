package com.adapit.portal.ui.frames.training;

import java.awt.Dimension;

import com.adapit.portal.ui.forms.training.TrainingSolutionCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastrarTrainingSolutionDialog extends javax.swing.JDialog{


	public CadastrarTrainingSolutionDialog (){
		super(AdapitVirtualFrame.getInstance());
		setTitle("Cadastrar Treinamento");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 600));
		comercialSolutionCadastreForm = new TrainingSolutionCadastreForm();
		add(comercialSolutionCadastreForm,java.awt.BorderLayout.CENTER);		
		setResizable(true);	
	}
	
	private TrainingSolutionCadastreForm comercialSolutionCadastreForm;

	public TrainingSolutionCadastreForm getComercialSolutionCadastreForm() {
		return comercialSolutionCadastreForm;
	}

	public void setComercialSolutionCadastreForm(TrainingSolutionCadastreForm produtoCadastreForm) {
		this.comercialSolutionCadastreForm = produtoCadastreForm;
	}
}