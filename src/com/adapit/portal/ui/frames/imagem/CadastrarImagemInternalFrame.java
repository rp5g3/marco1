package com.adapit.portal.ui.frames.imagem;

import java.awt.Dimension;

import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.imageform.ImagemCadastrePanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastrarImagemInternalFrame extends javax.swing.JInternalFrame{

	
	public CadastrarImagemInternalFrame(){
		super("Cadastrar Imagens");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 600));
		imagemCadastreForm = new ImagemCadastrePanel();
		add(imagemCadastreForm,java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setCadastrarComercialSolutionFrame(null);
			}
		});
	}
	
	private ImagemCadastrePanel imagemCadastreForm;

	public ImagemCadastrePanel getImagemCadastreForm() {
		return imagemCadastreForm;
	}

	public void setImagemCadastreForm(ImagemCadastrePanel produtoCadastreForm) {
		this.imagemCadastreForm = produtoCadastreForm;
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
