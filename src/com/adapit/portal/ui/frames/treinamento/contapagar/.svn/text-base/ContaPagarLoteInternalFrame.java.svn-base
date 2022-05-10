package com.adapit.portal.ui.frames.treinamento.contapagar;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.ui.forms.treinamento.contapagar.ParticipanteContaPagarLoteForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ContaPagarLoteInternalFrame extends JInternalFrame{

	public ContaPagarLoteInternalFrame(){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(615, 490));
		setTitle("Contas a Pagar Filtradas por Lotes");
		setLocation(0,0);
		participanteContaPagarLoteForm = new ParticipanteContaPagarLoteForm();
		add(participanteContaPagarLoteForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);	
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent evt) {
				AdapitVirtualFrame.getInstance().setLastContaPagarLoteInternalFrame(null);
			}
		});
	}
	
	private ParticipanteContaPagarLoteForm participanteContaPagarLoteForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	public ParticipanteContaPagarLoteForm getParticipanteContaPagarLoteForm() {
		return participanteContaPagarLoteForm;
	}
	public void setParticipanteContaPagarLoteForm(ParticipanteContaPagarLoteForm participanteContaPagarLoteForm) {
		this.participanteContaPagarLoteForm = participanteContaPagarLoteForm;
	}
	
	public void editRegister(Treinamento lote) {
		
		//participanteContaPagarLoteForm.editRegister(lote);	
		((JComboBox)participanteContaPagarLoteForm.getCodLoteComboBox()).setSelectedItem(lote.getCodigo());
		toFront();
	}
	

	public void editRegister(Treinamento lote, String leilaoDates) {
		participanteContaPagarLoteForm.setSelectedLoteRefreshByLeilao(lote,leilaoDates);
		
		toFront();
	}
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
