package com.adapit.portal.ui.frames.news;

import com.adapit.portal.ui.forms.news.NewsCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

@SuppressWarnings("serial")
public class CadastrarNewsInternalFrame extends javax.swing.JInternalFrame{

	
	public CadastrarNewsInternalFrame(){
		super("Cadastrar News");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		newsCadastreForm = new NewsCadastreForm();
		add(newsCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarNewsFrame(null);
			}
		});
	}
	
	private NewsCadastreForm newsCadastreForm;

	public NewsCadastreForm getNewsCadastreForm() {
		return newsCadastreForm;
	}

	public void setNewsCadastreForm(NewsCadastreForm newsCadastreForm) {
		this.newsCadastreForm = newsCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
