package com.adapit.portal.ui.frames.treinamento;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.treinamento.TreinamentoListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class RelatorioTreinamentosInternalFrame extends JInternalFrame{

	public RelatorioTreinamentosInternalFrame(){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(710, 480));
		setTitle("Relatório de Lotes");
		loteListForm = new TreinamentoListForm();
		add(loteListForm, java.awt.BorderLayout.CENTER);
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
	
	private TreinamentoListForm loteListForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
		}
		toFront();
	}
	public TreinamentoListForm getLoteListForm() {
		return loteListForm;
	}
	public void setLoteListForm(TreinamentoListForm loteListForm) {
		this.loteListForm = loteListForm;
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
