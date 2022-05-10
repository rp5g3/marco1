package com.adapit.portal.ui.forms.treinamento.agenda;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class AgendaTreinamentoListDialog extends JDialog{
	
	private AgendaTreinamentoListPanel agendaCadastrePanel;

	public AgendaTreinamentoListDialog(){
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		this.setSize(new Dimension(573, 293));
		setModal(true);
		setLayout(new BorderLayout());
		add(getAgendaLoteListPanel());		
		setLocation(UIUtil.getScreenCenter(this));
		this.setTitle("Lista das Agendas de um Lote");
	}
	
	private AgendaTreinamentoListPanel getAgendaLoteListPanel(){
		if (agendaCadastrePanel == null){
			agendaCadastrePanel = new AgendaTreinamentoListPanel();
			agendaCadastrePanel.getFecharDialogoButton().addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}				
			});
		}
		return agendaCadastrePanel;
	}

	public void setTreinamento(Treinamento lote) {
		agendaCadastrePanel.setTreinamento(lote);
		agendaCadastrePanel.editRegister();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
