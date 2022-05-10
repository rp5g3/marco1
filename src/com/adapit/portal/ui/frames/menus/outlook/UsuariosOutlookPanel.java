package com.adapit.portal.ui.frames.menus.outlook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;
import com.workcase.gui.AbstractAction;


@SuppressWarnings("serial")
public class UsuariosOutlookPanel extends JPanel{
	
	public UsuariosOutlookPanel(JOutlookBar tabs) {
		super();
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);

		// this to test the UI gets notified of changes
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Manutenção de Usuários");
		tabs.setToolTipTextAt(index,
				"Operações de manutenção de usuários do sistema");
		initialize();
	}

	private void initialize(){
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getEditarDadosPessoaisButton());
		add(getListarUsuariosButton());			
		add(getCadastroUsuariosPessoaFisicaButton());	
		add(getCadastroUsuariosPessoaJuridicaButton());	

		add(getCadastroInstrutorButton());
		add(getCadastroFuncionarioButton());
		
	}
	
	private JButton cadastroInstrutorButton;

	private JButton getCadastroInstrutorButton() {
		if (cadastroInstrutorButton == null) {
			cadastroInstrutorButton = new JButton("Adicionar Instrutor");
			try {
				cadastroInstrutorButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroInstrutorButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cadastrar_leiloeiros.png")));
			cadastroInstrutorButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					try {
						AdapitVirtualFrame.getInstance().cadastrarInstrutor();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}				
			});
		}
		return cadastroInstrutorButton;
	}
	
	private JButton cadastroFuncionarioButton;

	private JButton getCadastroFuncionarioButton() {
		if (cadastroFuncionarioButton == null) {
			cadastroFuncionarioButton = new JButton("Adicionar Funcionário");
			try {
				cadastroFuncionarioButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroFuncionarioButton.setIcon(new ImageIcon(getClass().getResource("/imgs/funcionarios.png")));
			cadastroFuncionarioButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarFuncionario();
				}				
			});
		}
		return cadastroFuncionarioButton;
	}
	
	private class ListarUsuariosAction extends AbstractAction{

		@Override
		protected void doAction(ActionEvent e) {
			AdapitVirtualFrame.getInstance().listarUsuarios();
		}
		
	}
	

	
	private JButton cadastroUsuariosPessoaFisicaButton;

	private JButton getCadastroUsuariosPessoaFisicaButton() {
		if (cadastroUsuariosPessoaFisicaButton == null) {
			cadastroUsuariosPessoaFisicaButton = new JButton("Adicionar Participante (PF)");
			try {
				cadastroUsuariosPessoaFisicaButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroUsuariosPessoaFisicaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cadastrar_usuarios.png")));
			cadastroUsuariosPessoaFisicaButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarComitenteParticipante(PersonType.Fisica);
				}
				
			});
		}
		return cadastroUsuariosPessoaFisicaButton;
	}
	
	private JButton cadastroUsuariosPessoaJuridicaButton;

	private JButton getCadastroUsuariosPessoaJuridicaButton() {
		if (cadastroUsuariosPessoaJuridicaButton == null) {
			cadastroUsuariosPessoaJuridicaButton = new JButton("Adicionar Participante (PJ)");
			try {
				cadastroUsuariosPessoaJuridicaButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroUsuariosPessoaJuridicaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/cadastrar_usuarios_pj.png")));
			cadastroUsuariosPessoaJuridicaButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarComitenteParticipante(PersonType.Juridica);
				}
				
			});
		}
		return cadastroUsuariosPessoaJuridicaButton;
	}
	
	private JButton listarUsuariosButton;

	private JButton getListarUsuariosButton() {
		if (listarUsuariosButton == null) {
			listarUsuariosButton = new JButton("Relatório de Usuários");
			try {
				listarUsuariosButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listarUsuariosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/lista_usuarios.png")));
			listarUsuariosButton.addActionListener(new ListarUsuariosAction());
		}
		return listarUsuariosButton;
	}
	
	private JButton editarDadosPessoaisButton;

	private JButton getEditarDadosPessoaisButton() {
		if (editarDadosPessoaisButton == null) {
			editarDadosPessoaisButton = new JButton("Editar meus dados");
			try {
				editarDadosPessoaisButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			editarDadosPessoaisButton.setIcon(new ImageIcon(getClass().getResource("/imgs/register2.png")));
			editarDadosPessoaisButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarDadosPessoais();
				}				
			});
		}
		return editarDadosPessoaisButton;
	}
	
		
}
