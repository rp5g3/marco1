package com.adapit.portal.ui.forms.projeto.agendaprojeto;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class AgendaProjetoListDialog extends JDialog{
	
	private AgendaProjetoListPanel agendaCadastrePanel;

	public AgendaProjetoListDialog(){
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		this.setSize(new Dimension(573, 293));
		setModal(true);
		setLayout(new BorderLayout());
		add(getAgendaLoteListPanel());		
		setLocation(UIUtil.getScreenCenter(this));
		this.setTitle("Lista das Agendas de um Lote");
	}
	
	private AgendaProjetoListPanel getAgendaLoteListPanel(){
		if (agendaCadastrePanel == null){
			agendaCadastrePanel = new AgendaProjetoListPanel();
			agendaCadastrePanel.getFecharDialogoButton().addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}				
			});
		}
		return agendaCadastrePanel;
	}

	public void setProjeto(Projeto lote) {
		agendaCadastrePanel.setProjeto(lote);
		agendaCadastrePanel.editRegister();
	}


}  //  @jve:decl-index=0:visual-constraint="10,10"
