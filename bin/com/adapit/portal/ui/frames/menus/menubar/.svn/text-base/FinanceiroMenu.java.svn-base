package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class FinanceiroMenu  extends JMenu{

	public FinanceiroMenu(){
		super("Financeiro");
		
		add(getParticipantesContasPagarMenuItem());
		add(getAgendaLotesMenuItem());
		
	}
	
	private JMenuItem agendaLotesMenuItem;

	private JMenuItem getAgendaLotesMenuItem() {
		if (agendaLotesMenuItem == null) {
			agendaLotesMenuItem = new JMenuItem("Fechamento de Lotes");
			agendaLotesMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarFechamentoTreinamento();
				}				
			});
		}
		return agendaLotesMenuItem;
	}
	
	private JMenuItem participantesContasPagarMenuItem;

	private JMenuItem getParticipantesContasPagarMenuItem() {
		if (participantesContasPagarMenuItem == null) {
			participantesContasPagarMenuItem = new JMenuItem("Contas a Pagar por Lote");
			participantesContasPagarMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarContaPagarLote();
				}				
			});
		}
		return participantesContasPagarMenuItem;
	}
}