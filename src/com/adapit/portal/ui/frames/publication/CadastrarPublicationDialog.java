package com.adapit.portal.ui.frames.publication;

import com.adapit.portal.ui.forms.publication.PublicationCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

import java.awt.Dimension;

@SuppressWarnings("serial")
public class CadastrarPublicationDialog extends javax.swing.JDialog{

	
	public CadastrarPublicationDialog (){
		super(AdapitVirtualFrame.getInstance());
		setTitle("Cadastrar Artigos");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(700, 600));
		publicationCadastreForm = new PublicationCadastreForm();
		add(publicationCadastreForm,java.awt.BorderLayout.CENTER);		
		setResizable(false);	
	}
	
	private PublicationCadastreForm publicationCadastreForm;

	public PublicationCadastreForm getPublicationCadastreForm() {
		return publicationCadastreForm;
	}

	public void setPublicationCadastreForm(PublicationCadastreForm publicationCadastreForm) {
		this.publicationCadastreForm = publicationCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"