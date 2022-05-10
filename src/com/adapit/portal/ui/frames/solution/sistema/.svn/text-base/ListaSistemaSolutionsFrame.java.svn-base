package com.adapit.portal.ui.frames.solution.sistema;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.software.SoftwareSolutionListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaSistemaSolutionsFrame extends javax.swing.JInternalFrame {
	
	public ListaSistemaSolutionsFrame(){
		super("Lista de Sistemas");
		initialize();
	}
	
	public void initialize(){		
		setLayout(new java.awt.BorderLayout());
		
		SoftwareSolutionListForm plf = new SoftwareSolutionListForm();
		setSize(plf.getSize().width+20,plf.getSize().height+20);
		add(plf, java.awt.BorderLayout.CENTER);	
	
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setListSistemaSolutionFrame(null);
			}
		});
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"