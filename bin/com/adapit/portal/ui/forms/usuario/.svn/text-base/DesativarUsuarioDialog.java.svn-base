package com.adapit.portal.ui.forms.usuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class DesativarUsuarioDialog extends JDialog {

	public DesativarUsuarioDialog(){
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}
	
	public DesativarUsuarioDialog(JFrame jf){
		super(jf);
		initialize();
	}
	
	private DeactivationReasonCadastreForm deactivationReasonCadastreForm;
	
	public void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(580, 450));
		deactivationReasonCadastreForm = new DeactivationReasonCadastreForm();
		deactivationReasonCadastreForm.getCancelButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}			
		});
		deactivationReasonCadastreForm.getConfirmarButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}			
		});
		deactivationReasonCadastreForm.getActivateButton().addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				dispose();
			}			
		});
		setTitle("Desativação de acesso de usuário");
		add(deactivationReasonCadastreForm,java.awt.BorderLayout.CENTER);
		setLocation(UIUtil.getScreenCenter(this));
		
		setModal(true);
	}

	public DeactivationReasonCadastreForm getDeactivationReasonCadastreForm() {
		return deactivationReasonCadastreForm;
	}

	public void setDeactivationReasonCadastreForm(
			DeactivationReasonCadastreForm deactivationReasonCadastreForm) {
		this.deactivationReasonCadastreForm = deactivationReasonCadastreForm;
	}
}
