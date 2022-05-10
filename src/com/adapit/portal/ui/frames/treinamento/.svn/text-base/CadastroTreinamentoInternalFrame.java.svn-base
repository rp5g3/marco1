package com.adapit.portal.ui.frames.treinamento;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.ui.forms.treinamento.ScheduledTrainingCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastroTreinamentoInternalFrame extends JInternalFrame{

	public CadastroTreinamentoInternalFrame(){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(675, 605));
		setTitle("Cadastro de Treinamentos");
		setLocation(0,0);
		treinamentoCadastreForm = new ScheduledTrainingCadastreForm();
		add(treinamentoCadastreForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);	
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setLastCadastroTreinamentoInternalFrame(null);
			}
		});
	}
	
	private ScheduledTrainingCadastreForm treinamentoCadastreForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	public ScheduledTrainingCadastreForm getTreinamentoCadastreForm() {
		return treinamentoCadastreForm;
	}
	public void setTreinamentoCadastreForm(ScheduledTrainingCadastreForm loteCadastreForm) {
		this.treinamentoCadastreForm = loteCadastreForm;
	}
	
	public void editRegister(Treinamento lote) {
		treinamentoCadastreForm.editRegister(lote);		
		toFront();
	}
	
	/*public void editRegister(Lote lote, int leilaoId) {
		loteCadastreForm.setSelectedLoteRefreshByLeilao(lote,leilaoId);
		
		toFront();
	}*/
	
	public void editRegister(Treinamento lote, String turmaDate) {
		treinamentoCadastreForm.setSelectedTreinamentoRefreshByTurma(lote,turmaDate);
		
		toFront();
	}
	
	public void newRegister() {
		treinamentoCadastreForm.newRegister();
		toFront();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
