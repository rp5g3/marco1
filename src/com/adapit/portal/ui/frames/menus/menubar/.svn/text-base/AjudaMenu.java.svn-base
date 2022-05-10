package com.adapit.portal.ui.frames.menus.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class AjudaMenu extends JMenu{

	public AjudaMenu(){
		super("Ajuda");
		
		add(getCreditosMenuItem());
		add(getLicensaMenuItem());
		add(new JSeparator());
		add(getSobreMenuItem());
	}
	
	private JMenuItem sobreMenuItem;
	
	private JMenuItem getSobreMenuItem(){
		if (sobreMenuItem == null){
			sobreMenuItem = new JMenuItem("Sobre...");
		}
		return sobreMenuItem;
	}
	
	private JMenuItem creditosMenuItem;
	
	private JMenuItem getCreditosMenuItem(){
		if (creditosMenuItem == null){
			creditosMenuItem = new JMenuItem("Créditos");
		}
		return creditosMenuItem;
	}
	
	private JMenuItem licensaMenuItem;
	
	private JMenuItem getLicensaMenuItem(){
		if (licensaMenuItem == null){
			licensaMenuItem = new JMenuItem("Licensa de uso");
		}
		return licensaMenuItem;
	}
}
