package com.adapit.portal.ui.frames.usuario;

import java.awt.Dimension;

import javax.swing.JDialog;

import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.ui.forms.usuario.ManutencaoUsuarioForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class ManutencaoUsuarioDialog extends JDialog{

	public ManutencaoUsuarioDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		setTitle("Manutenção de Dados de Usuários");
		setModal(true);
		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
	
		this.setSize(new Dimension(320, 285));
		
		manutencaoUsuarioForm = new ManutencaoUsuarioForm();
		add(manutencaoUsuarioForm, java.awt.BorderLayout.CENTER);		
		setResizable(false);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private ManutencaoUsuarioForm manutencaoUsuarioForm;

	public ManutencaoUsuarioForm getManutencaoUsuarioForm() {
		return manutencaoUsuarioForm;
	}

	public void setManutencaoUsuarioForm(ManutencaoUsuarioForm manutencaoUsuarioForm) {
		this.manutencaoUsuarioForm = manutencaoUsuarioForm;
	}
	
	public void editRegister(Usuario user){
		this.manutencaoUsuarioForm.editRegister(user);
	}
	
	public Usuario getUsuario(){
		return manutencaoUsuarioForm.getUsuario();
	}
	
}
