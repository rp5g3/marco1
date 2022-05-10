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
public class SoftwareOutlookPanel extends JPanel{

	public SoftwareOutlookPanel(JOutlookBar tabs){
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getCadastroSistemasSolutionsButton());				
		add(getListaSistemasButton());
		add(getCadastroDominiosButton());
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);

		// this to test the UI gets notified of changes
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Softwares");
		tabs.setToolTipTextAt(index,
				"Operações relacionadas com os softwares");
	}
	private JButton cadastroSistemasSolutionsButton;

	private JButton getCadastroSistemasSolutionsButton() {
		if (cadastroSistemasSolutionsButton == null) {
			cadastroSistemasSolutionsButton = new JButton("Cadastro de Software");
			try {
				cadastroSistemasSolutionsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroSistemasSolutionsButton.setIcon(new ImageIcon(getClass()
					.getResource("/imgs/system.png")));
			cadastroSistemasSolutionsButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().newSoftwareSolution();
				}

			});
		}
		return cadastroSistemasSolutionsButton;
	}
	
	private JButton listaSistemasButton;

	private JButton getListaSistemasButton() {
		if (listaSistemasButton == null) {
			listaSistemasButton = new JButton("Lista de Softwares");
			try {
				listaSistemasButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listaSistemasButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/system_list.png")));
			listaSistemasButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listSystemSolutions();
				}
			});
		}
		return listaSistemasButton;
	}
	
	private JButton cadastroDominiosButton;

	private JButton getCadastroDominiosButton() {
		if (cadastroDominiosButton == null) {
			cadastroDominiosButton = new JButton("Cadastro de Domínios");
			try {
				cadastroDominiosButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroDominiosButton.setIcon(new ImageIcon(getClass()
					.getResource("/imgs/categorias3.png")));
			cadastroDominiosButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().newSoftwareDomain();
				}

			});
		}
		return cadastroDominiosButton;
	}
	
}