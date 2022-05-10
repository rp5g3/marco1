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
import com.workcase.gui.AbstractAction;

@SuppressWarnings("serial")
public class AgendaTreinamentoOutlookPanel extends JPanel{

	public AgendaTreinamentoOutlookPanel(JOutlookBar tabs){
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		
		add(getCadastrarAgendaTreinamentosButton());
		add(getListarTreinamentosButton());
		add(getAgendaTreinamentosButton());
		
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);

		// this to test the UI gets notified of changes
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Agendas de Treinamentos");
		tabs.setToolTipTextAt(index,
				"Operações relacionadas com os treinamentos");
	}
	
	/*private JButton cadastrarFormacoesButton;
	
	private JButton getCadastrarFormacoesButton() {
		if (cadastrarFormacoesButton == null) {
			cadastrarFormacoesButton = new JButton("Cadastro de Formações");
			try {
				cadastrarFormacoesButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastrarFormacoesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/formacoes.png")));
			cadastrarFormacoesButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					LeilaoVirtualFrame.getInstance().novaFormacao();
				}				
			});
		}
		return cadastrarFormacoesButton;
	}
	*/
	private JButton cadastrarAgendaTreinamentosButton;

	private JButton getCadastrarAgendaTreinamentosButton() {
		if (cadastrarAgendaTreinamentosButton == null) {
			cadastrarAgendaTreinamentosButton = new JButton("Agendar Treinamentos");
			try {
				cadastrarAgendaTreinamentosButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastrarAgendaTreinamentosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/shopping cart 32.png")));
			cadastrarAgendaTreinamentosButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoTreinamento();
				}				
			});
		}
		return cadastrarAgendaTreinamentosButton;
	}
	
	private JButton listarTreinamentosButton;

	private JButton getListarTreinamentosButton() {
		if (listarTreinamentosButton == null) {
			listarTreinamentosButton = new JButton("Relatório de Agendas");
			try {
				listarTreinamentosButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
				listarTreinamentosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/Shopping cart 32 list.png")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			//listarLotesButton.setIcon(getIcon("/imgs/user_go.png", 40, 40));
			listarTreinamentosButton.addActionListener(new AbstractAction(){
				@Override
				protected void doAction(ActionEvent e) {
					AdapitVirtualFrame.getInstance().relatorioTreinamentos();
				}
				
			});
		}
		return listarTreinamentosButton;
	}
	
	private JButton agendaTreinamentosButton;

	private JButton getAgendaTreinamentosButton() {
		if (agendaTreinamentosButton == null) {
			agendaTreinamentosButton = new JButton("Fechamento de Agendas");
			try {
				agendaTreinamentosButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
				agendaTreinamentosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/Shopping cart date.png")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			agendaTreinamentosButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarFechamentoTreinamento();
				}				
			});
		}
		return agendaTreinamentosButton;
	}

	
}
