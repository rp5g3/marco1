package com.adapit.portal.ui.frames.treinamento.turma;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.treinamento.turma.TurmaListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class RelatorioTurmasInternalFrame extends JInternalFrame{

	public RelatorioTurmasInternalFrame(){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(710, 480));
		setTitle("Relatório de Turmas");
		turmaListForm = new TurmaListForm();
		add(turmaListForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);	
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setLastRelatorioTreinamentos(null);
			}
		});
	}
	
	private TurmaListForm turmaListForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
		}
		toFront();
	}
	public TurmaListForm getTurmaListForm() {
		return turmaListForm;
	}
	public void setTurmaListForm(TurmaListForm loteListForm) {
		this.turmaListForm = loteListForm;
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
