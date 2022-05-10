package com.adapit.portal.ui.forms.imageform;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.services.remote.RemoteImagemService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class ViewFullImageDialog extends JDialog{

	
	public ViewFullImageDialog(Imagem img, int scalex, int scaley){
		super(AdapitVirtualFrame.getInstance());
		if (img.getRotulo() != null
				&& !img.getRotulo().equals(""))
			setTitle(img.getRotulo());
		else setTitle("Imagem sem rótulo");
		JLabel jl = new JLabel();
		setSize(scalex,scaley);
		setSize(820,600);
		setModal(true);
		JScrollPane js = new JScrollPane();
		js.add(jl);
		js.setViewportView(jl);
		add(js);
		if (img.getFullImageBytes() == null){								
			try{
				byte[] imbytes = RemoteImagemService.getInstance().getFullImageBytesFromImage(img.getId());
				if (imbytes == null) return;									
				img.setFullImageBytes(imbytes);
			}catch (Exception e) {
				e.printStackTrace();
			}			
		}
		jl.setIcon(img.getImageIcon());
		setLocation(UIUtil.getScreenCenter(this));
		
	}
	
}
