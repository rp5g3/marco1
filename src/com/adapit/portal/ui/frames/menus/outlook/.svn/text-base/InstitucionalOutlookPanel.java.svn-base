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

@SuppressWarnings("serial")
public class InstitucionalOutlookPanel extends JPanel {

	public InstitucionalOutlookPanel(JOutlookBar tabs) {
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getCadastroArtigoButton());
		add(getListaArtigoButton());
		add(getCadastroNewsButton());
		add(getListarNewsButton());
		//add(getCadastroUpdateButton());
		//add(getListarUpdateButton());
		add(getDestquesButton());

		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Institucional");
		tabs.setToolTipTextAt(index, "Operações relacionadas com dados institucionais");

	}

	private JButton cadastroArtigoButton;

	private JButton getCadastroArtigoButton() {
		if (cadastroArtigoButton == null) {
			cadastroArtigoButton = new JButton("Cadastro de Artigos");
			try {
				cadastroArtigoButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroArtigoButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_cadastre.png")));
			cadastroArtigoButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoPublication();
				}

			});
		}
		return cadastroArtigoButton;
	}

	private JButton listaArtigoButton;

	private JButton getListaArtigoButton() {
		if (listaArtigoButton == null) {
			listaArtigoButton = new JButton("Lista de Artigos");
			try {
				listaArtigoButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listaArtigoButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_cadastre.png")));
			listaArtigoButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listPublication();
				}
			});
		}
		return listaArtigoButton;
	}
	
	private JButton cadastroNewsButton;

	private JButton getCadastroNewsButton() {
		if (cadastroNewsButton == null) {
			cadastroNewsButton = new JButton("Cadastro de Notícias");
			try {
				cadastroNewsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroNewsButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_list.png")));
			cadastroNewsButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoNews();
				}

			});
		}
		return cadastroNewsButton;
	}

	private JButton listarNewsButton;

	private JButton getListarNewsButton() {
		if (listarNewsButton == null) {
			listarNewsButton = new JButton("Lista de Notícias");
			try {
				listarNewsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listarNewsButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_list.png")));
			listarNewsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listNews();
				}
			});
		}
		return listarNewsButton;
	}

/*	private JButton cadastroUpdateButton;

	private JButton getCadastroUpdateButton() {
		if (cadastroUpdateButton == null) {
			cadastroUpdateButton = new JButton("Cadastro de Versões");
			try {
				cadastroUpdateButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroUpdateButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_list.png")));
			cadastroUpdateButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novoUpdate();
				}

			});
		}
		return cadastroUpdateButton;
	}

	private JButton listarUpdateButton;

	private JButton getListarUpdateButton() {
		if (listarUpdateButton == null) {
			listarUpdateButton = new JButton("Lista de Versões");
			try {
				listarUpdateButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listarUpdateButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_list.png")));
			listarUpdateButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listUpdate();
				}
			});
		}
		return listarUpdateButton;
	}
*/

	private JButton destaqueButton;

	private JButton getDestquesButton() {
		if (destaqueButton == null) {
			destaqueButton = new JButton("Destaques da Página");
			try {
				destaqueButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}/*
			destaqueButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/news_list.png")));*/
			destaqueButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().destaque();
				}
			});
		}
		return destaqueButton;
	}
}
