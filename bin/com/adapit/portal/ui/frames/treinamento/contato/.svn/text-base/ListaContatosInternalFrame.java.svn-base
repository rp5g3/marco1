package com.adapit.portal.ui.frames.treinamento.contato;

import javax.swing.JInternalFrame;

import com.adapit.portal.ui.forms.treinamento.contato.ProcessoTreinamentoListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaContatosInternalFrame extends JInternalFrame{
	public ListaContatosInternalFrame(){
		super();
		initialize();
	}
	
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(460, 325));		
		setTitle("Lista de Comarcas");
		comarcaListForm = new ProcessoTreinamentoListForm();
		add(comarcaListForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);			
	}
	
	private ProcessoTreinamentoListForm comarcaListForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}	

}
