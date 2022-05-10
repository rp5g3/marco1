package com.adapit.portal.ui.frames.publication;

import com.adapit.portal.ui.forms.publication.PublicationCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

@SuppressWarnings("serial")
public class CadastrarPublicationInternalFrame extends javax.swing.JInternalFrame{

	
	public CadastrarPublicationInternalFrame(){
		super("Cadastrar Artigo");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		publicationCadastreForm = new PublicationCadastreForm();
		add(publicationCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarPublicationFrame(null);
			}
		});
	}
	
	private PublicationCadastreForm publicationCadastreForm;

	public PublicationCadastreForm getPublicationCadastreForm() {
		return publicationCadastreForm;
	}

	public void setPublicationCadastreForm(PublicationCadastreForm publicationCadastreForm) {
		this.publicationCadastreForm = publicationCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
