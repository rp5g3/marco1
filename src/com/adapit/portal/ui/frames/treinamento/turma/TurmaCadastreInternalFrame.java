package com.adapit.portal.ui.frames.treinamento.turma;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.ui.forms.treinamento.turma.TurmaCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class TurmaCadastreInternalFrame extends JInternalFrame{

	public TurmaCadastreInternalFrame() {
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Turmas para Treinamentos");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(648, 486));
		leilaoCadastreForm = new TurmaCadastreForm();
		add(leilaoCadastreForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
		
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setTurmaCadastreFrame(null);
			}
		});
	}
	
	private TurmaCadastreForm leilaoCadastreForm;

	public TurmaCadastreForm getLeilaoCadastreForm() {
		return leilaoCadastreForm;
	}

	public void setLeilaoCadastreForm(TurmaCadastreForm leilaoCadastreForm) {
		this.leilaoCadastreForm = leilaoCadastreForm;
	}
	
	public void postInDesktopPane(){
		AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());			
		AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
		toFront();
	}

	public void editRegister(TurmaTreinamento leilao) {
		leilaoCadastreForm.editRegister(leilao);
	}
}
