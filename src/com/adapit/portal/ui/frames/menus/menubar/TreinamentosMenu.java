package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;

@SuppressWarnings("serial")
public class TreinamentosMenu extends JMenu {

	public TreinamentosMenu() {
		super("Treinamentos");
		add(getCadastrarLotesMenuItem());
		add(getListarLotesMenuItem());
		add(getFecharLotesMenuItem());
	}

	private JMenuItem cadastrarLotesMenuItem;

	private JMenuItem getCadastrarLotesMenuItem() {
		if (cadastrarLotesMenuItem == null) {
			cadastrarLotesMenuItem = new JMenuItem("Cadastro de Treinamentos");
			cadastrarLotesMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoTreinamento();
				}
			});
		}
		return cadastrarLotesMenuItem;
	}

	private JMenuItem listarLotesMenuItem;

	private JMenuItem getListarLotesMenuItem() {
		if (listarLotesMenuItem == null) {
			listarLotesMenuItem = new JMenuItem("Relatório de Treinamentos");
			listarLotesMenuItem.addActionListener(new AbstractAction() {
				@Override
				protected void doAction(ActionEvent e) {
					AdapitVirtualFrame.getInstance().relatorioTreinamentos();
				}

			});
		}
		return listarLotesMenuItem;
	}

	private JMenuItem fecharLotesMenuItem;

	private JMenuItem getFecharLotesMenuItem() {
		if (fecharLotesMenuItem == null) {
			fecharLotesMenuItem = new JMenuItem("Fechar/Encerrar Treinamentos");
			fecharLotesMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarFechamentoTreinamento();
				}
			});
		}
		return fecharLotesMenuItem;
	}

}
