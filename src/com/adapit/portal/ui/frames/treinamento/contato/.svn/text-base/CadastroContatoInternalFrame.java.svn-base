package com.adapit.portal.ui.frames.treinamento.contato;

import javax.swing.JInternalFrame;

import com.adapit.portal.entidades.ContatoTreinamento;
import com.adapit.portal.ui.forms.treinamento.contato.ContatoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastroContatoInternalFrame extends JInternalFrame{
	public CadastroContatoInternalFrame(){
		super();
		initialize();
	}
	
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(470, 420));
		
		setTitle("Cadastro de Comarcas");
		comarcaCadastreForm = new ContatoCadastreForm();
		add(comarcaCadastreForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);			
	}
	
	private ContatoCadastreForm comarcaCadastreForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	
	
	public void editRegister(ContatoTreinamento comarca) {
		comarcaCadastreForm.editRegister(comarca, comarca.getEndereco());
		toFront();
	}
	
	public void newRegister() {
		comarcaCadastreForm.newRegister();
		toFront();
	}
}
