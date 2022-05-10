package com.adapit.portal.ui.frames.toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class TopToolBar extends JToolBar {

	private JButton usersButton;
	private JButton dadosButton;
	private JButton agendaButton = null;
	private JButton financeiroButton = null;
	private JButton produtosButton = null;
	
	public TopToolBar() {
		initialize();
	}

	public TopToolBar(int orientation) {
		super(orientation);
		initialize();
	}

	public TopToolBar(String name) {
		super(name);
		initialize();
	}

	public TopToolBar(String name, int orientation) {
		super(name, orientation);
		initialize();
	}
	
	private void initialize(){
		this.setFloatable(false);
		this.add(getDadosButton());
		this.add(getUsersButton());
		this.add(getProdutosButton());
		this.add(getAgendaButton());
		this.add(getFinanceiroButton());
	}
	
	private JButton getDadosButton(){
		if(dadosButton == null){
			dadosButton = new JButton();
			dadosButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/register2.png",24,24));
			dadosButton.setToolTipText("Abre a tela para editar seus dados pessoais");
			dadosButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarDadosPessoais();
				}				
			});
		}
		return dadosButton;
	}

	
	private JButton getUsersButton(){
		if(usersButton == null){
			usersButton = new JButton();
			usersButton.setIcon(new ImageIcon(getClass().getResource("/imgs/config-users.png")));
			usersButton.setToolTipText("Abre a tela para listar os usuários do sistema");
			usersButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listarUsuarios();
				}				
			});
		}
		return usersButton;
	}

	/**
	 * This method initializes agendaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAgendaButton() {
		if (agendaButton == null) {
			agendaButton = new JButton();
			agendaButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/Shopping cart 32 list.png",24,24));
			agendaButton.setToolTipText("Listar lotes");
			agendaButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().relatorioTreinamentos();
				}				
			});
		}
		return agendaButton;
	}

	/**
	 * This method initializes financeiroButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getFinanceiroButton() {
		if (financeiroButton == null) {
			financeiroButton = new JButton();
			financeiroButton.setToolTipText("Visualizar as pendências de clientes");
			financeiroButton.setIcon(new ImageIcon(getClass().getResource("/imgs/gnome-finance.png")));
			financeiroButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().editarContaPagarLote();
				}				
			});
		}
		return financeiroButton;
	}

	/**
	 * This method initializes produtosButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getProdutosButton() {
		if (produtosButton == null) {
			produtosButton = new JButton();
			produtosButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/produto.png",24,24));
			produtosButton.setToolTipText("Listar os produtos cadastrados no sistema");
			produtosButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listCommercialSolutions();
				}				
			});
		}
		return produtosButton;
	}

}
