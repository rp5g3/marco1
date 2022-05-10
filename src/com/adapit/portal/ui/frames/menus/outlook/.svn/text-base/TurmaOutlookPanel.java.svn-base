package com.adapit.portal.ui.frames.menus.outlook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ButtonUI;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.ui.forms.endereco.EnderecoCadastreDialog;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;
import com.workcase.gui.AbstractAction;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class TurmaOutlookPanel extends JPanel{

	public TurmaOutlookPanel(JOutlookBar tabs){
		setLayout(new PercentLayout(PercentLayout.VERTICAL, 0));
		setOpaque(false);
		add(getCadastroTurmasButton());
		add(getListarTurmasButton());
		/*add(getLocalPresCadastreButton());
		add(getComarcaCadastreButton());
		add(getComarcaListButton());*/
		JScrollPane scroll = tabs.makeScrollPane(this);
		tabs.addTab("", scroll);

	
		int index = tabs.indexOfComponent(scroll);
		tabs.setTitleAt(index, "Manutenção de Turmas");
		tabs.setToolTipTextAt(index,
				"Operações relacionadas com turmas");
	}
	
	private JButton cadastroTurmasButton;

	private JButton getCadastroTurmasButton() {
		if (cadastroTurmasButton == null) {
			cadastroTurmasButton = new JButton("Cadastro de Turmas");
			try {
				cadastroTurmasButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			cadastroTurmasButton.setIcon(new ImageIcon(getClass().getResource("/imgs/turma.png")));
			cadastroTurmasButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarTurma();
				}				
			});
		}
		return cadastroTurmasButton;
	}
	
	private JButton listarTurmasButton;

	private JButton getListarTurmasButton() {
		if (listarTurmasButton == null) {
			listarTurmasButton = new JButton("Lista de Turmas");
			try {
				listarTurmasButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			listarTurmasButton.setIcon(new ImageIcon(getClass().getResource("/imgs/turma_list.png")));
			listarTurmasButton.addActionListener(new AbstractAction(){
				@Override
				protected void doAction(ActionEvent e) {
					AdapitVirtualFrame.getInstance().relatorioTurmas();
				}
				
			});
		}
		return listarTurmasButton;
	}
	
	private JButton localPresCadastreButton;

	private JButton getLocalPresCadastreButton() {
		if (localPresCadastreButton == null) {
			localPresCadastreButton = new JButton("Cadastro de Local Presencial");
			try {
				localPresCadastreButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			localPresCadastreButton.setIcon(new ImageIcon(getClass().getResource("/imgs/localpresencial.png")));
			localPresCadastreButton.addActionListener(new ActionListener(){

				public void actionPerformed(ActionEvent evt) {
					EnderecoCadastreDialog end = new EnderecoCadastreDialog(AddressType.Presencial);
					end.setVisible(true);
				}
				
			});
		}
		return localPresCadastreButton;
	}
	
	private JButton comarcaCadastreButton;

	private JButton getComarcaCadastreButton() {
		if (comarcaCadastreButton == null) {
			comarcaCadastreButton = new JButton("Cadastro de Comarcas");
			try {
				comarcaCadastreButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			comarcaCadastreButton.setIcon(new ImageIcon(getClass().getResource("/imgs/comarca.png")));
			comarcaCadastreButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().cadastrarContato();					
				}				
			});
		}
		return comarcaCadastreButton;
	}
	
	
	private JButton comarcaListButton;

	private JButton getComarcaListButton() {
		if (comarcaListButton == null) {
			comarcaListButton = new JButton("Processos por Comarca");
			try {
				comarcaListButton.setUI((ButtonUI) Class.forName(
						(String) UIManager.get("OutlookButtonUI"))
						.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			comarcaListButton.setIcon(new ImageIcon(getClass().getResource("/imgs/proc_comarcas.png")));
			comarcaListButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					AdapitVirtualFrame.getInstance().listarContatos();					
				}				
			});
		}
		return comarcaListButton;
	}
}
