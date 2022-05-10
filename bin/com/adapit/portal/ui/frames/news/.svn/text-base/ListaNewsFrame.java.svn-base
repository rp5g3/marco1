package com.adapit.portal.ui.frames.news;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.news.NewsListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaNewsFrame extends javax.swing.JInternalFrame {
	
	public ListaNewsFrame(){
		super("Lista de News");
		initialize();
	}
	
	public void initialize(){		
		setLayout(new java.awt.BorderLayout());
		
		NewsListForm plf = new NewsListForm();
		setSize(plf.getSize().width+20,plf.getSize().height+20);
		add(plf, java.awt.BorderLayout.CENTER);	
	
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setListaNewsFrame(null);
			}
		});
	}
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"