package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.ui.forms.endereco.EnderecoCadastreDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class TurmaTreinamentoMenu extends JMenu{

	public TurmaTreinamentoMenu(){
		super("Turmas");
		add(getCadastroLeiloesMenuItem());
		add(getListarLeiloesMenuItem());
		add(getLocalPresCadastreMenuItem());
		add(getComarcaCadastreMenuItem());
		add(getComarcaListMenuItem());
		
	}
	
	private JMenuItem cadastroLeiloesMenuItem;
	
	private JMenuItem getCadastroLeiloesMenuItem(){
		if (cadastroLeiloesMenuItem == null){
			cadastroLeiloesMenuItem = new JMenuItem("Cadastro de Turmas");
			cadastroLeiloesMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarTurma();
				}				
			});
		}
		return cadastroLeiloesMenuItem;
	}
	
	private JMenuItem listarLeiloesMenuItem;
	
	private JMenuItem getListarLeiloesMenuItem(){
		if (listarLeiloesMenuItem == null){
			listarLeiloesMenuItem = new JMenuItem("Listar Turmas");
			listarLeiloesMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().relatorioTurmas();
				}				
			});
		}
		return listarLeiloesMenuItem;
	}
	
	private JMenuItem localPresCadastreMenuItem;
	
	private JMenuItem getLocalPresCadastreMenuItem(){
		if (localPresCadastreMenuItem == null){
			localPresCadastreMenuItem = new JMenuItem("Cadastro de Local de Treinamentos");
			localPresCadastreMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					EnderecoCadastreDialog end = new EnderecoCadastreDialog(AddressType.Presencial);
					end.setVisible(true);
				}				
			});
		}
		return localPresCadastreMenuItem;
	}
	
	private JMenuItem comarcaCadastreMenuItem;
	
	private JMenuItem getComarcaCadastreMenuItem(){
		if (comarcaCadastreMenuItem == null){
			comarcaCadastreMenuItem = new JMenuItem("Cadastro de Comarcas");
			comarcaCadastreMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarContato();
				}				
			});
		}
		return comarcaCadastreMenuItem;
	}
	
	private JMenuItem comarcaListMenuItem;
	
	private JMenuItem getComarcaListMenuItem(){
		if (comarcaListMenuItem == null){
			comarcaListMenuItem = new JMenuItem("Lista de Processos por Comarcas");
			comarcaListMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listarContatos();	
				}				
			});
		}
		return comarcaListMenuItem;
	}
}
