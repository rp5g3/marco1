package com.adapit.portal.ui.frames.solution.sistema;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.software.SoftwareSolutionCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastrarSoftwareSolutionInternalFrame extends javax.swing.JInternalFrame{

	
	public CadastrarSoftwareSolutionInternalFrame(){
		super("Manutenção de Dados de Sistema");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		comercialSolutionCadastreForm = new SoftwareSolutionCadastreForm();
		add(comercialSolutionCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setMaximizable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarSoftwareSolutionFrame(null);
			}
		});
	}
	
	private SoftwareSolutionCadastreForm comercialSolutionCadastreForm;

	public SoftwareSolutionCadastreForm getComercialSolutionCadastreForm() {
		return comercialSolutionCadastreForm;
	}

	public void setComercialSolutionCadastreForm(SoftwareSolutionCadastreForm produtoCadastreForm) {
		this.comercialSolutionCadastreForm = produtoCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
