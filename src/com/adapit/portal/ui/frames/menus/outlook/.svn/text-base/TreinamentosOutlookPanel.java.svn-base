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
public class TreinamentosOutlookPanel extends JPanel{

	public TreinamentosOutlookPanel(JOutlookBar tabs){
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getCadastrarFormacoesButton());
		add(getCadastroTrainingSolutionsButton());
		add(getListarTrainingSolutionsButton());
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);

		// this to test the UI gets notified of changes
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Treinamentos e Formações");
		tabs.setToolTipTextAt(index,
				"Operações relacionadas com os treinamentos");
	}
	
	private JButton cadastrarFormacoesButton;
	
	private JButton getCadastrarFormacoesButton() {
		if (cadastrarFormacoesButton == null) {
			cadastrarFormacoesButton = new JButton("Cadastro de Formações");
			try {
				cadastrarFormacoesButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastrarFormacoesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/formacoes.png")));
			cadastrarFormacoesButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().novaFormacao();
				}				
			});
		}
		return cadastrarFormacoesButton;
	}
	
	private JButton cadastroTrainingSolutionsButton;

	private JButton getCadastroTrainingSolutionsButton() {
		if (cadastroTrainingSolutionsButton == null) {
			cadastroTrainingSolutionsButton = new JButton("Cadastro de Treinamentos");
			try {
				cadastroTrainingSolutionsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroTrainingSolutionsButton.setIcon(new ImageIcon(getClass()
					.getResource("/imgs/training.png")));
			cadastroTrainingSolutionsButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().newTrainingSolution();
				}

			});
		}
		return cadastroTrainingSolutionsButton;
	}
	
	private JButton listarTrainingSolutionsButton;

	private JButton getListarTrainingSolutionsButton() {
		if (listarTrainingSolutionsButton == null) {
			listarTrainingSolutionsButton = new JButton("Lista de Treinamentos");
			try {
				listarTrainingSolutionsButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listarTrainingSolutionsButton.setIcon(new ImageIcon(getClass().getResource(
					"/imgs/training_list.png")));
			listarTrainingSolutionsButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listTrainingSolutions();
				}
			});
		}
		return listarTrainingSolutionsButton;
	}
	
}