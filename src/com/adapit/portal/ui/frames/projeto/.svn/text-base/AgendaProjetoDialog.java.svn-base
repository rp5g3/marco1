package com.adapit.portal.ui.frames.projeto;

import javax.swing.JDialog;

import com.adapit.portal.entidades.AgendaProjeto;
import com.adapit.portal.entidades.Projeto;
import com.adapit.portal.ui.forms.projeto.agendaprojeto.AgendaProjetoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class AgendaProjetoDialog extends JDialog{

	public AgendaProjetoDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Datas de Leilões por Lotes");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(580, 406));
		agendaLoteCadastreForm = new AgendaProjetoCadastreForm();
		add(agendaLoteCadastreForm,java.awt.BorderLayout.CENTER);
		setResizable(false);
		setModal(true);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private AgendaProjetoCadastreForm agendaLoteCadastreForm;

	
	
	/*public void postInDesktopPane(){
		LeilaoVirtualFrame.getInstance().beginStatusBar(getTitle());			
		LeilaoVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		LeilaoVirtualFrame.getInstance().endStatusBar(getTitle());
	}*/

	public AgendaProjetoCadastreForm getAgendaLoteCadastreForm() {
		return agendaLoteCadastreForm;
	}

	public void setAgendaLoteCadastreForm(
			AgendaProjetoCadastreForm agendaLoteCadastreForm) {
		this.agendaLoteCadastreForm = agendaLoteCadastreForm;
	}

	public void editRegister(AgendaProjeto agendaLote, Projeto lote) {
		agendaLoteCadastreForm.setProjeto(lote);
		agendaLoteCadastreForm.editRegister(agendaLote);
	}
	
	public void newRegister(Projeto lote){
		agendaLoteCadastreForm.setProjeto(lote);
		agendaLoteCadastreForm.newRegister();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
