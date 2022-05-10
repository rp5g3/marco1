package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.menus.LookAndFeelMenu;

@SuppressWarnings("serial")
public class ArquivoMenu extends JMenu{

	public ArquivoMenu(){
		super("Arquivo");
		add(getLookAndFeelMenu());
		add(getEditarDadosMenuItem());
		add(new JSeparator());
		add(getConsoleMenu());
		add(getExitMenuItem());	
	}
	
	private JMenu getLookAndFeelMenu(){
		return new LookAndFeelMenu();
	}
	
	private JMenuItem exitMenuItem=null;
	
	private JMenuItem editarDadosMenuItem;
	private JMenuItem getEditarDadosMenuItem(){
		if (editarDadosMenuItem == null){
			editarDadosMenuItem = new JMenuItem("Editar dados pessoais");
			editarDadosMenuItem.setIcon(AdapitVirtualFrame.getIcon("imgs/register2.png", 16, 16));
			editarDadosMenuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarDadosPessoais();
				}				
			});
		}
		return editarDadosMenuItem;
	}
	
	private JMenuItem getExitMenuItem(){
		if (exitMenuItem == null){
			exitMenuItem = new JMenuItem("Sair");
			exitMenuItem.setIcon(AdapitVirtualFrame.getIcon("imgs/cancel.png", 16, 16));
			exitMenuItem.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().dispose();
				}				
			});
		}
		return exitMenuItem;
	}
	
	private JMenuItem console;
	private JMenuItem getConsoleMenu(){
		if (console == null){
			console = new JMenuItem("Console");
			console.setIcon(AdapitVirtualFrame.getIcon("imgs/cancel.png", 16, 16));
			console.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().showConsole();
				}				
			});
		}
		return console;
	}

}
