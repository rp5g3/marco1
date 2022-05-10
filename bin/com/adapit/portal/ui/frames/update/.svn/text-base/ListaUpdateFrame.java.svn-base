package com.adapit.portal.ui.frames.update;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.ui.forms.update.UpdateListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaUpdateFrame extends javax.swing.JInternalFrame {
	private ComercialSolution solution;
	public ListaUpdateFrame(ComercialSolution c){
		super("Lista de Versões de " + c.getNome());
		solution=c;
		initialize();
	}
	
	public void initialize(){		
		setLayout(new java.awt.BorderLayout());
		
		UpdateListForm plf = new UpdateListForm(solution);
		setSize(plf.getSize().width+20,plf.getSize().height+20);
		add(plf, java.awt.BorderLayout.CENTER);	
	
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setListaUpdateFrame(null);
			}
		});
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"