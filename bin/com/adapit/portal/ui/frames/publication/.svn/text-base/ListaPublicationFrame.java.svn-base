package com.adapit.portal.ui.frames.publication;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.publication.PublicationListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaPublicationFrame extends javax.swing.JInternalFrame {
	
	public ListaPublicationFrame(){
		super("Lista de Artigos");
		initialize();
	}
	
	public void initialize(){		
		setLayout(new java.awt.BorderLayout());
		
		PublicationListForm plf = new PublicationListForm();
		setSize(plf.getSize().width+20,plf.getSize().height+20);
		add(plf, java.awt.BorderLayout.CENTER);	
	
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setListaPublicationFrame(null);
			}
		});
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"