package com.adapit.portal.ui.forms.pessoa;

import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.adapit.portal.ui.frames.imagem.ListaImagemDialog;

public abstract class AbstractComitenteImage {
	
	private Container container;
	
	public AbstractComitenteImage(Container cont){
		this.container = cont;
	}
	
	public AbstractComitenteImage(Container cont, String att){
		this.container = cont;
		this.attachMessage = att;
	}

	private static Icon getIcon(String name ){

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL).getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}
	

	
	public void browseImage() {
		try {			
			int resp = getJfc().showOpenDialog(container);
			if (resp == JFileChooser.APPROVE_OPTION){
				File f = getJfc().getSelectedFile();
				this.getCaminhoImgTextField().setText(f.toURI().getPath());				
				Imagem img = new Imagem();
				img.setFullImageBytes(f);
				
				getPersonComitente().setLogotipo(img);
				updateImage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateImage() {
		if (getPersonComitente().getLogotipo() != null)
			try {
				try {
					getPersonComitente().getLogotipo().setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(getPersonComitente().getLogotipo().getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
				this.getCaminhoImgTextField().setText(getPersonComitente().getLogotipo().getPath());
				getImgLabelImage().setIcon(getPersonComitente().getLogotipo().getSmallImageIcon(true));
				getImgLabelImage().updateUI();
			} catch (Exception e) {
				e.printStackTrace();
			}
		else{
			getImgLabelImage().setIcon(null);
		}	
	}
	public void updateImage(boolean b) {
		if(getPersonComitente().getId() != 0){
			try {
				Imagem im =  null;
				try {
					im = RemotePessoaService.getInstance().getPersonalImage(getPersonComitente().getId());
				} catch (NullPointerException e1) {
					//e1.printStackTrace();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if (im != null) {
					getPersonComitente().setLogotipo(im);
					try {
						//im.setSmallImageBytes(RemoteImagemService.getInstance().getSmallImageBytesFromImage(im.getId()));
						im.setFullImageBytes(RemoteImagemService.getInstance().getFullImageBytesFromImage(im.getId()));
						this.getCaminhoImgTextField().setText(im.getPath());
						getImgLabelImage().setIcon(im.getScaledImage(200));//im.getSmallImageIcon(b));
						getImgLabelImage().updateUI();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				} else {
					this.getCaminhoImgTextField().setText("");
					getImgLabelImage().setIcon(null);
					getImgLabelImage().updateUI();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void setNullImage() {
		try {
			this.getCaminhoImgTextField().setText("");
			getImgLabelImage().setIcon(null);
			getImgLabelImage().updateUI();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JButton anexar;
	private ListaImagemDialog dialog;
	public void anexarImagem(){		
		try {			
			dialog = new ListaImagemDialog();
			if (anexar == null){				 
				anexar = new JButton(attachMessage);
				anexar.addActionListener(new AnexarImagemActionListener());	
				anexar.setIcon(getIcon("/imgs/picture_link.png"));
			}
			
			dialog.getImageListForm().getButtonsPanel().add(anexar,0);
			dialog.setVisible(true);
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}
	
	private String attachMessage = "Anexar na Pessoa";
	
	public class AnexarImagemActionListener implements ActionListener{
		
		public AnexarImagemActionListener(){	
			ilf = dialog.getImageListForm();			
		}
		private ImageListForm ilf;
		
		@Override
		public void actionPerformed(ActionEvent evt) {					
			int rows[] = ilf.getBaseTable().getSelectedRows();
			if (rows != null){
				try {
					Imagem im = (Imagem) ilf.getBaseTable().getElements().get(rows[0]);
					RemotePessoaService.getInstance().mergePersonalImage(im.getId(),getPersonComitente().getId());
					getPersonComitente().setLogotipo(im);
					getImgLabelImage().setIcon(im.getSmallImageIcon(false));
					getImgLabelImage().updateUI();
					AdapitVirtualFrame.getInstance().showOperationSucess("Anexar Fotografia", "Imagem anexada com sucesso!");
				} catch (Exception e) {
					e.printStackTrace();					
				}
				if (AdapitVirtualFrame.getInstance().getListaImagensFrame() != null)
					AdapitVirtualFrame.getInstance().getListaImagensFrame().getImageListForm().getButtonsPanel().remove(anexar);								
			}
			if (AdapitVirtualFrame.getInstance().getListaImagensFrame() != null)
				AdapitVirtualFrame.getInstance().getListaImagensFrame().dispose();
		}
	}
	
	public abstract PessoaEmDivulgacao getPersonComitente();
	
	public abstract JLabel getImgLabelImage();
	
	public abstract JTextField getCaminhoImgTextField();
	
	public abstract JFileChooser getJfc();
	
	public abstract JButton getBuscarImagemButton();

	public String getAttachMessage() {
		return attachMessage;
	}

	public void setAttachMessage(String attachMessage) {
		this.attachMessage = attachMessage;
	}
	
}
