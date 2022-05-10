package com.adapit.portal.ui.frames.imagem;

import javax.swing.JInternalFrame;

import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ListaImagemInternalFrame extends JInternalFrame{
	public ListaImagemInternalFrame(){
		super();
		initialize();
	}
	
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(720, 510));		
		setTitle("Lista de Imagens");
		imageListForm = new ImageListForm();
		add(imageListForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);			
	}
	
	private ImageListForm imageListForm;
	
	public void postInDesktopPane(){
		try {
			if (!this.isShowing()){
				AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
				AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
				setVisible(true);
				AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
				toFront();
			}
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	}

	public ImageListForm getImageListForm() {
		return imageListForm;
	}	

}
