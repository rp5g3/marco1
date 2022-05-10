package com.adapit.portal.ui.frames.imagem;

import java.awt.Dimension;

import javax.swing.JDialog;

import com.adapit.portal.ui.forms.imageform.ImagemCadastrePanel;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class CadastrarImagemDialog  extends javax.swing.JDialog{

	public CadastrarImagemDialog (JDialog dialog){
		super(dialog);
		setTitle("Cadastrar Imagens");
		initialize();
	}
	
	public CadastrarImagemDialog (){
		super(AdapitVirtualFrame.getInstance());
		setTitle("Cadastrar Imagens");
		initialize();
	}
	
	public void initialize(){		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 600));
		imagemCadastreForm = new ImagemCadastrePanel();
		add(imagemCadastreForm,java.awt.BorderLayout.CENTER);		
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private ImagemCadastrePanel imagemCadastreForm;

	public ImagemCadastrePanel getImagemCadastreForm() {
		return imagemCadastreForm;
	}

	public void setImagemCadastreForm(ImagemCadastrePanel produtoCadastreForm) {
		this.imagemCadastreForm = produtoCadastreForm;
	}
}