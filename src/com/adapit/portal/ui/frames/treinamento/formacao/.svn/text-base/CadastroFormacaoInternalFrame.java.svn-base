package com.adapit.portal.ui.frames.treinamento.formacao;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.FormacaoTreinamento;
import com.adapit.portal.ui.forms.treinamento.formacao.FormacaoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastroFormacaoInternalFrame extends JInternalFrame{

	public CadastroFormacaoInternalFrame (){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(675, 605));
		setTitle("Cadastro de Formações");
		setLocation(0,0);
		formacaoCadastreForm = new FormacaoCadastreForm();
		add(formacaoCadastreForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);	
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setLastCadastroFormacaoInternalFrame(null);
			}
		});
	}
	
	private FormacaoCadastreForm formacaoCadastreForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	public FormacaoCadastreForm getFormacaoCadastreForm() {
		return formacaoCadastreForm;
	}
	public void setFormacaoCadastreForm(FormacaoCadastreForm loteCadastreForm) {
		this.formacaoCadastreForm = loteCadastreForm;
	}
	
	public void editRegister(FormacaoTreinamento lote) {
		formacaoCadastreForm.editRegister(lote);		
		toFront();
	}

	
	public void newRegister() {
		formacaoCadastreForm.newRegister();
		toFront();
	}
}