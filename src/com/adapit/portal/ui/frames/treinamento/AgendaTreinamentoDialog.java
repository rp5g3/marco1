package com.adapit.portal.ui.frames.treinamento;

import javax.swing.JDialog;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.ui.forms.treinamento.agenda.AgendaTreinamentoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
import java.awt.Dimension;

@SuppressWarnings("serial")
public class AgendaTreinamentoDialog extends JDialog{

	public AgendaTreinamentoDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Datas de Leilões por Lotes");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(580, 406));
		agendaLoteCadastreForm = new AgendaTreinamentoCadastreForm();
		add(agendaLoteCadastreForm,java.awt.BorderLayout.CENTER);
		setResizable(false);
		setModal(true);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private AgendaTreinamentoCadastreForm agendaLoteCadastreForm;

	
	
	/*public void postInDesktopPane(){
		LeilaoVirtualFrame.getInstance().beginStatusBar(getTitle());			
		LeilaoVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		LeilaoVirtualFrame.getInstance().endStatusBar(getTitle());
	}*/

	public AgendaTreinamentoCadastreForm getAgendaLoteCadastreForm() {
		return agendaLoteCadastreForm;
	}

	public void setAgendaLoteCadastreForm(
			AgendaTreinamentoCadastreForm agendaLoteCadastreForm) {
		this.agendaLoteCadastreForm = agendaLoteCadastreForm;
	}

	public void editRegister(AgendaTreinamento agendaLote, Treinamento lote) {
		agendaLoteCadastreForm.setTreinamento(lote);
		try {
			agendaLoteCadastreForm.editRegister(agendaLote);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void newRegister(Treinamento lote){
		agendaLoteCadastreForm.setTreinamento(lote);
		try {
			agendaLoteCadastreForm.newRegister();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
