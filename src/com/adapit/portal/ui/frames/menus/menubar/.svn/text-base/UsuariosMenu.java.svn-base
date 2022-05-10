package com.adapit.portal.ui.frames.menus.menubar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;

@SuppressWarnings("serial")
public class UsuariosMenu extends JMenu{

	public UsuariosMenu(){
		super("Usuários");
		initialize();
	}
	
	private void initialize(){		
		add(getEditarDadosPessoaisMenuItem());
		add(getListarUsuariosMenuItem());	
		add(new JSeparator());
		add(getParticipantesMenu());
		add(getUsuariosDemaisMenu());
	}
	
	private JMenu usuariosDemaisMenu;
	
	private JMenu getUsuariosDemaisMenu(){
		if (usuariosDemaisMenu == null){
			usuariosDemaisMenu = new JMenu("Outros Usuários");
			usuariosDemaisMenu.add(getCadastroLeiloeiroMenuItem());
			usuariosDemaisMenu.add(getCadastroComitenteMenuItem() );
			usuariosDemaisMenu.add(getCadastroComitentePJMenuItem() );
			usuariosDemaisMenu.add(getCadastroFuncionarioMenuItem() );
		}
		return usuariosDemaisMenu;
	}
	
	
	private JMenuItem cadastroLeiloeiroMenuItem;

	private JMenuItem getCadastroLeiloeiroMenuItem() {
		if (cadastroLeiloeiroMenuItem == null) {
			cadastroLeiloeiroMenuItem = new JMenuItem("Adicionar Instrutor");
			cadastroLeiloeiroMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarInstrutor();
				}				
			});
		}
		return cadastroLeiloeiroMenuItem;
	}
	
	
	private JMenuItem cadastroComitenteMenuItem;

	private JMenuItem getCadastroComitenteMenuItem() {
		if (cadastroComitenteMenuItem == null) {
			cadastroComitenteMenuItem = new JMenuItem("Adicionar Comitente (PF)");
			cadastroComitenteMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarComitente(PersonType.Fisica);
				}				
			});
		}
		return cadastroComitenteMenuItem;
	}
	
	private JMenuItem cadastroComitentePJMenuItem;

	private JMenuItem getCadastroComitentePJMenuItem() {
		if (cadastroComitentePJMenuItem == null) {
			cadastroComitentePJMenuItem = new JMenuItem("Adicionar Comitente (PJ)");
			cadastroComitentePJMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarComitente(PersonType.Juridica);
				}				
			});
		}
		return cadastroComitentePJMenuItem;
	}
	

	private JMenuItem cadastroFuncionarioMenuItem;

	private JMenuItem getCadastroFuncionarioMenuItem() {
		if (cadastroFuncionarioMenuItem == null) {
			cadastroFuncionarioMenuItem = new JMenuItem("Adicionar Funcionario (PF)");
			cadastroFuncionarioMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarFuncionario();
				}				
			});
		}
		return cadastroFuncionarioMenuItem;
	}
	
	
	
	private class ListarUsuariosAction extends AbstractAction{

		@Override
		protected void doAction(ActionEvent e) {
			AdapitVirtualFrame.getInstance().listarUsuarios();
		}
		
	}
	

	
	private JMenu participantesMenu;
	
	private JMenu getParticipantesMenu(){
		if (participantesMenu == null){
			participantesMenu = new JMenu("Clientes");
			participantesMenu.add(getCadastroUsuariosPessoaFisicaMenuItem());
			participantesMenu.add(getCadastroUsuariosPessoaJuridicaMenuItem());
		}
		return participantesMenu;
	}
	
	private JMenuItem cadastroUsuariosPessoaFisicaMenuItem;

	private JMenuItem getCadastroUsuariosPessoaFisicaMenuItem() {
		if (cadastroUsuariosPessoaFisicaMenuItem == null) {
			cadastroUsuariosPessoaFisicaMenuItem = new JMenuItem("Adicionar Participante (PF)");
			cadastroUsuariosPessoaFisicaMenuItem.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarComitenteParticipante(PersonType.Fisica);
				}
				
			});
		}
		return cadastroUsuariosPessoaFisicaMenuItem;
	}
	
	private JMenuItem cadastroUsuariosPessoaJuridicaMenuItem;

	private JMenuItem getCadastroUsuariosPessoaJuridicaMenuItem() {
		if (cadastroUsuariosPessoaJuridicaMenuItem == null) {
			cadastroUsuariosPessoaJuridicaMenuItem = new JMenuItem("Adicionar Participante (PJ)");
			cadastroUsuariosPessoaJuridicaMenuItem.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarComitenteParticipante(PersonType.Juridica);
				}
				
			});
		}
		return cadastroUsuariosPessoaJuridicaMenuItem;
	}
	
	private JMenuItem listarUsuariosMenuItem;

	private JMenuItem getListarUsuariosMenuItem() {
		if (listarUsuariosMenuItem == null) {
			listarUsuariosMenuItem = new JMenuItem("Relatório de Usuários");
			listarUsuariosMenuItem.addActionListener(new ListarUsuariosAction());
		}
		return listarUsuariosMenuItem;
	}
	
	private JMenuItem editarDadosPessoaisMenuItem;

	private JMenuItem getEditarDadosPessoaisMenuItem() {
		if (editarDadosPessoaisMenuItem == null) {
			editarDadosPessoaisMenuItem = new JMenuItem("Editar meus dados");
			editarDadosPessoaisMenuItem.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarDadosPessoais();
				}				
			});
		}
		return editarDadosPessoaisMenuItem;
	}
	
	
}
