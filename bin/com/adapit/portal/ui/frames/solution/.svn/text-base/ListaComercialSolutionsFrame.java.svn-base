package com.adapit.portal.ui.frames.solution;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.solution.ComercialSolutionListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaComercialSolutionsFrame extends javax.swing.JInternalFrame {
	
	public ListaComercialSolutionsFrame(){
		super("Lista de Soluções");
		initialize();
	}
	
	public void initialize(){		
		setLayout(new java.awt.BorderLayout());
		
		ComercialSolutionListForm plf = new ComercialSolutionListForm();
		setSize(plf.getSize().width+20,plf.getSize().height+20);
		add(plf, java.awt.BorderLayout.CENTER);	
	
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
		
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setListComercialSolutionFrame(null);
			}
		});
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"