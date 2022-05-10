package com.adapit.portal.ui.frames.pessoa.rh;

import java.awt.Dimension;

import javax.swing.JDialog;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.forms.pessoa.participante.CadastrarParticipanteTabs;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class ParticipanteCadastreDialog extends JDialog{

	public ParticipanteCadastreDialog(PersonType pt) {
		super(AdapitVirtualFrame.getInstance());
		initialize(pt);
	}

	private void initialize(PersonType pt){
		setTitle("Cadastro de Participantes");
		setModal(true);
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 440));
		cadastroComitenteForm = new CadastrarParticipanteTabs(pt);
		add(cadastroComitenteForm, java.awt.BorderLayout.CENTER);
		//setIconifiable(true);
		setResizable(false);
		//setClosable(true);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private CadastrarParticipanteTabs cadastroComitenteForm;

	public CadastrarParticipanteTabs getCadastrarComitenteForm() {
		return cadastroComitenteForm;
	}

	public void setCadastrarComitenteForm(CadastrarParticipanteTabs leilaoCadastreForm) {
		this.cadastroComitenteForm = leilaoCadastreForm;
	}
	
	public void postInDesktopPane(){
	/*	LeilaoVirtualFrame.getInstance().beginStatusBar(getTitle());			
		LeilaoVirtualFrame.getInstance().getMainDesktopPane().add(this);*/
		try {
			/*if (!isShowing())*/ setVisible(true);
		} catch (Exception e) {
			initialize(PersonType.Fisica);
			setVisible(true);
		}
		toFront();
		/*
		LeilaoVirtualFrame.getInstance().endStatusBar(getTitle());*/
	}
}
