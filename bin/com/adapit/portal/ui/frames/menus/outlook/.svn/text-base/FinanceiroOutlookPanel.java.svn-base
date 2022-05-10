package com.adapit.portal.ui.frames.menus.outlook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;

@SuppressWarnings("serial")
public class FinanceiroOutlookPanel extends JPanel{

	public FinanceiroOutlookPanel(JOutlookBar tabs){
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getParticipantesContasPagarButton());
		add(getAgendaLotesButton());
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);

		// this to test the UI gets notified of changes
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Financeiro");
		tabs.setToolTipTextAt(index,
				"Operações relacionadas com contas e receber de lotes leiloados");
	}
	
	private JButton agendaLotesButton;

	private JButton getAgendaLotesButton() {
		if (agendaLotesButton == null) {
			agendaLotesButton = new JButton("Fechamento de Lotes");
			try {
				agendaLotesButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
				agendaLotesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/Shopping cart date.png")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			agendaLotesButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarFechamentoTreinamento();
				}				
			});
		}
		return agendaLotesButton;
	}
	
	private JButton participantesContasPagarButton;

	private JButton getParticipantesContasPagarButton() {
		if (participantesContasPagarButton == null) {
			participantesContasPagarButton = new JButton("Contas a Pagar por Lote");
			try {
				participantesContasPagarButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
				participantesContasPagarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/Shopping cart money.png")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			participantesContasPagarButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarContaPagarLote();
				}				
			});
		}
		return participantesContasPagarButton;
	}
}
