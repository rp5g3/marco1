package com.adapit.portal.ui.frames.solution;

import com.adapit.portal.ui.forms.solution.ComercialSolutionCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

@SuppressWarnings("serial")
public class CadastrarComercialSolutionInternalFrame extends javax.swing.JInternalFrame{

	
	public CadastrarComercialSolutionInternalFrame(){
		super("Cadastrar Soluções");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 531));
		comercialSolutionCadastreForm = new ComercialSolutionCadastreForm();
		add(comercialSolutionCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarComercialSolutionFrame(null);
			}
		});
	}
	
	private ComercialSolutionCadastreForm comercialSolutionCadastreForm;

	public ComercialSolutionCadastreForm getComercialSolutionCadastreForm() {
		return comercialSolutionCadastreForm;
	}

	public void setComercialSolutionCadastreForm(ComercialSolutionCadastreForm produtoCadastreForm) {
		this.comercialSolutionCadastreForm = produtoCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
