package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class NewsMenu extends JMenu {

	public NewsMenu() {
		super("Notícias");
		add(getCadastroNewsMenuItem());
		add(getListaNewsMenuItem());
	}

	private JMenuItem cadastroNewsMenuItem;

	private JMenuItem getCadastroNewsMenuItem() {
		if (cadastroNewsMenuItem == null) {
			cadastroNewsMenuItem = new JMenuItem("Cadastro de Notícias");
			cadastroNewsMenuItem.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoNews();
				}

			});
		}
		return cadastroNewsMenuItem;
	}

	private JMenuItem listaNewsMenuItem;

	private JMenuItem getListaNewsMenuItem() {
		if (listaNewsMenuItem == null) {
			listaNewsMenuItem = new JMenuItem("Lista de Notícias");
			listaNewsMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listNews();
				}
			});
		}
		return listaNewsMenuItem;
	}

}
