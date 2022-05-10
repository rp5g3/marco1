package com.adapit.portal.ui.frames.pessoa.rh;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.forms.pessoa.participante.CadastrarParticipanteTabs;

@SuppressWarnings("serial")
public class ParticipanteCadastreInternalFrame extends JInternalFrame{

	public ParticipanteCadastreInternalFrame(PersonType pt) {
		initialize(pt);
	}

	private void initialize(PersonType pt){
		setTitle("Cadastro de Participantes");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(570, 440));
		cadastroComitenteForm = new CadastrarParticipanteTabs(pt);
		add(cadastroComitenteForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
	}
	
	private CadastrarParticipanteTabs cadastroComitenteForm;

	public CadastrarParticipanteTabs getCadastrarComitenteForm() {
		return cadastroComitenteForm;
	}

	public void setCadastrarComitenteForm(CadastrarParticipanteTabs leilaoCadastreForm) {
		this.cadastroComitenteForm = leilaoCadastreForm;
	}
/*	
	public void postInDesktopPane(){
		LeilaoVirtualFrame.getInstance().beginStatusBar(getTitle());			
		LeilaoVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		LeilaoVirtualFrame.getInstance().endStatusBar(getTitle());
	}*/
}
