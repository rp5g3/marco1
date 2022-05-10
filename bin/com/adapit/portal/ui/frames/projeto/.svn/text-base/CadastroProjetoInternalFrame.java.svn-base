package com.adapit.portal.ui.frames.projeto;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.projeto.ProjetoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastroProjetoInternalFrame extends JInternalFrame{

	public CadastroProjetoInternalFrame(){
		super();
		initialize();
	}
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(660, 528));
		setTitle("Cadastro de Lotes");
		loteCadastreForm = new ProjetoCadastreForm();
		add(loteCadastreForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);	
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setLastCadastroTreinamentoInternalFrame(null);
			}
		});
	}
	
	private ProjetoCadastreForm loteCadastreForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	public ProjetoCadastreForm getLoteCadastreForm() {
		return loteCadastreForm;
	}
	public void setLoteCadastreForm(ProjetoCadastreForm loteCadastreForm) {
		this.loteCadastreForm = loteCadastreForm;
	}
	
	/*public void editRegister(Projeto lote) {
		loteCadastreForm.editRegister(lote);
		toFront();
	}*/
	
	public void newRegister() {
		loteCadastreForm.newRegister();
		toFront();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
