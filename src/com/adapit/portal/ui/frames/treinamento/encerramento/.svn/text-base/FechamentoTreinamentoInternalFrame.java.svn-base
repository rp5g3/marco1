package com.adapit.portal.ui.frames.treinamento.encerramento;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.ui.forms.treinamento.encerramento.FechamentoTreinamentoForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class FechamentoTreinamentoInternalFrame extends JInternalFrame{

	public FechamentoTreinamentoInternalFrame(){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(615, 490));
		setTitle("Fechamento de Treinamentos");
		setLocation(0,0);
		fechamentoTreinamentoForm = new FechamentoTreinamentoForm();
		add(fechamentoTreinamentoForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);	
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setLastFechamentoLoteInternalFrame(null);
			}
		});
	}
	
	private FechamentoTreinamentoForm fechamentoTreinamentoForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	public FechamentoTreinamentoForm getFechamentoTreinamentoForm() {
		return fechamentoTreinamentoForm;
	}
	public void setFechamentoTreinamentoForm(FechamentoTreinamentoForm FechamentoLoteForm) {
		this.fechamentoTreinamentoForm = FechamentoLoteForm;
	}
	
	public void editRegister(Treinamento lote) {
		fechamentoTreinamentoForm.editRegister(lote);		
		toFront();
	}
	

	public void editRegister(Treinamento lote, String leilaoDates) {
		fechamentoTreinamentoForm.setSelectedLoteRefreshByLeilao(lote,leilaoDates);
		
		toFront();
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
