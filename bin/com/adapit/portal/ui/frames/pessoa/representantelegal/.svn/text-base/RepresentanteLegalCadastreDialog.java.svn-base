package com.adapit.portal.ui.frames.pessoa.representantelegal;

import java.awt.Dimension;

import javax.swing.JDialog;

import com.adapit.portal.ui.forms.pessoa.representantelegal.CadastrarRepresentanteLegal;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class RepresentanteLegalCadastreDialog extends JDialog{

	public RepresentanteLegalCadastreDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Representantes Legais");
		setModal(true);		
		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(570, 440));
		cadastroRepresentanteLegalsForm = new CadastrarRepresentanteLegal();
		add(cadastroRepresentanteLegalsForm, java.awt.BorderLayout.CENTER);
		//setIconifiable(true);
		setResizable(false);
		/*setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				LeilaoVirtualFrame.getInstance().setRepresentanteLegalCadastreFrame(null);
			}
		});*/
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private CadastrarRepresentanteLegal cadastroRepresentanteLegalsForm;

	public CadastrarRepresentanteLegal getCadastrarUsuarioRepresentanteLegal() {
		return cadastroRepresentanteLegalsForm;
	}

	public void setCadastrarUsuarioRepresentanteLegal(CadastrarRepresentanteLegal leilaoCadastreForm) {
		this.cadastroRepresentanteLegalsForm = leilaoCadastreForm;
	}
	
	public void postInDesktopPane(){
		//LeilaoVirtualFrame.getInstance().beginStatusBar(getTitle());			
		//LeilaoVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		//LeilaoVirtualFrame.getInstance().endStatusBar(getTitle());
		toFront();
	}
}
