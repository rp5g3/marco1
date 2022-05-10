package com.adapit.portal.ui.frames.imagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class ListaImagemDialog extends JDialog{
	public ListaImagemDialog(){
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}
	
	private void initialize(){
		setModal(true);
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(720, 510));		
		setTitle("Lista de Imagens");
		imageListForm = new ImageListForm();
		add(imageListForm, java.awt.BorderLayout.CENTER);
		setResizable(false);
		setLocation(UIUtil.getScreenCenter(this));
		imageListForm.getNovaImagemButton().removeActionListener(imageListForm.defaultNovaImagem);
		imageListForm.getNovaImagemButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				CadastrarImagemDialog c = new CadastrarImagemDialog(ListaImagemDialog.this);
				c.getImagemCadastreForm().newRegister();
				c.setVisible(true);
			}				
		});
	}
	
	private ImageListForm imageListForm;

	public ImageListForm getImageListForm() {
		return imageListForm;
	}
	
		

}
