package com.adapit.portal.ui.frames.pessoa.instrutor;

import java.awt.Dimension;

import javax.swing.JDialog;

import com.adapit.portal.ui.forms.pessoa.instrutor.CadastrarInstrutorForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class InstrutorCadastreDialog extends JDialog{

	public InstrutorCadastreDialog() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Instrutores");
		setModal(true);
		
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		//setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(800, 440));
		cadastroInstrutoresForm = new CadastrarInstrutorForm();
		add(cadastroInstrutoresForm, java.awt.BorderLayout.CENTER);		
		//setResizable(false);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private CadastrarInstrutorForm cadastroInstrutoresForm;

	public CadastrarInstrutorForm getCadastroInstrutorForm() {
		return cadastroInstrutoresForm;
	}

	public void setCadastroInstrutorForm(CadastrarInstrutorForm leilaoCadastreForm) {
		this.cadastroInstrutoresForm = leilaoCadastreForm;
	}
	
	
}
