package com.adapit.portal.ui.frames.solution.treinamento;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.training.TrainingSolutionCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastrarTrainingSolutionInternalFrame extends javax.swing.JInternalFrame{

	
	public CadastrarTrainingSolutionInternalFrame(){
		super("Cadastrar Treinamentos");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 531));
		comercialSolutionCadastreForm = new TrainingSolutionCadastreForm();
		add(comercialSolutionCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarTrainingSolutionFrame(null);
			}
		});
	}
	
	private TrainingSolutionCadastreForm comercialSolutionCadastreForm;

	public TrainingSolutionCadastreForm getComercialSolutionCadastreForm() {
		return comercialSolutionCadastreForm;
	}

	public void setComercialSolutionCadastreForm(TrainingSolutionCadastreForm produtoCadastreForm) {
		this.comercialSolutionCadastreForm = produtoCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
