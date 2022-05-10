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
import com.adapit.portal.ui.frames.imagem.CadastrarImagemDialog;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;

@SuppressWarnings("serial")
public class BasicDataOutlookPanel extends JPanel {

	public BasicDataOutlookPanel(JOutlookBar tabs) {
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getCadastroComercialSolutionsButton());
		add(getListaComercialSolutionsButton());
		add(getManutencaoCategoriasButton());
		
		
		add(getListaImagensButton());
		add(getCadastroImagensButton());
		
		add(getManutencaoArquivosButton());
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Soluções e Imagens");
		tabs.setToolTipTextAt(index,
				"Operações relacionadas com soluções, categorias e Imagens");

	}

	private JButton manutencaoCategoriasButton;

	private JButton getManutencaoCategoriasButton() {
		if (manutencaoCategoriasButton == null) {
			manutencaoCategoriasButton = new JButton("Manutenção de Categorias");
			try {
				manutencaoCategoriasButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			manutencaoCategoriasButton.setIcon(new ImageIcon(getClass()
					.getResource("/imgs/categorias.png")));
			manutencaoCategoriasButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							AdapitVirtualFrame.getInstance()
									.cadastrarCategorias();
						}
					});
		}
		return manutencaoCategoriasButton;
	}

	private JButton cadastroComercialSolutionsButton;

	private JButton getCadastroComercialSolutionsButton() {
		if (cadastroComercialSolutionsButton == null) {
			cadastroComercialSolutionsButton = new JButton("Cadastro de Produtos");
			try {
				cadastroComercialSolutionsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroComercialSolutionsButton.setIcon(new ImageIcon(getClass()
					.getResource("/imgs/produto.png")));
			cadastroComercialSolutionsButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().newComercialSolution();
				}

			});
		}
		return cadastroComercialSolutionsButton;
	}

	private JButton listaComercialSolutionsButton;

	private JButton getListaComercialSolutionsButton() {
		if (listaComercialSolutionsButton == null) {
			listaComercialSolutionsButton = new JButton("Lista de Soluções");
			try {
				listaComercialSolutionsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listaComercialSolutionsButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/listaprodutos.png")));
			listaComercialSolutionsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listCommercialSolutions();
				}
			});
		}
		return listaComercialSolutionsButton;
	}
	


	
	
	private JButton listaImagensButton;

	private JButton getListaImagensButton() {
		if (listaImagensButton == null) {
			listaImagensButton = new JButton("Pesquisar Imagens");
			try {
				listaImagensButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listaImagensButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/image-viewer.png")));
			listaImagensButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listImagens();
				}
			});
		}
		return listaImagensButton;
	}

	private JButton cadastroImagensButton;

	private JButton getCadastroImagensButton() {
		if (cadastroImagensButton == null) {
			cadastroImagensButton = new JButton("Cadastrar Imagens");
			try {
				cadastroImagensButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroImagensButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/image.png")));
			cadastroImagensButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					CadastrarImagemDialog c = new CadastrarImagemDialog();
					c.getImagemCadastreForm().newRegister();
					c.setVisible(true);
				}
			});
		}
		return cadastroImagensButton;
	}
	
	
	private JButton manutencaoArquivosButton;

	private JButton getManutencaoArquivosButton() {
		if (manutencaoArquivosButton == null) {
			manutencaoArquivosButton = new JButton("Manutenção de Arquivos");
			try {
				manutencaoArquivosButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			manutencaoArquivosButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							AdapitVirtualFrame.getInstance().showArquivosFrame();
						}
					});
		}
		return manutencaoArquivosButton;
	}
	
}
