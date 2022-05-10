package com.adapit.portal.ui.frames.update;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.ui.forms.update.UpdateCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastrarUpdateInternalFrame extends javax.swing.JInternalFrame{

	private ComercialSolution solution;
	
	public CadastrarUpdateInternalFrame(ComercialSolution solution){
		super("Cadastrar versão");
		this.solution = solution;
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		updateCadastreForm = new UpdateCadastreForm(solution);
		add(updateCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarUpdateFrame(null);
			}
		});
	}
	
	private UpdateCadastreForm updateCadastreForm;

	public UpdateCadastreForm getUpdateCadastreForm() {
		return updateCadastreForm;
	}

	public void setUpdateCadastreForm(UpdateCadastreForm updateCadastreForm) {
		this.updateCadastreForm = updateCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
