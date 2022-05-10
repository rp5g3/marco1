package com.adapit.portal.ui.frames.pessoa.instrutor;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import com.adapit.portal.ui.forms.pessoa.instrutor.CadastrarInstrutorForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class InstrutorCadastreInternalFrame extends JInternalFrame{

	public InstrutorCadastreInternalFrame() {
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Leiloeiros");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(570, 440));
		cadastroLeiloeirosForm = new CadastrarInstrutorForm();
		add(cadastroLeiloeirosForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
	}
	
	private CadastrarInstrutorForm cadastroLeiloeirosForm;

	public CadastrarInstrutorForm getCadastrarLeiloeiroForm() {
		return cadastroLeiloeirosForm;
	}

	public void setCadastrarLeiloeiroForm(CadastrarInstrutorForm leilaoCadastreForm) {
		this.cadastroLeiloeirosForm = leilaoCadastreForm;
	}
	
	public void postInDesktopPane(){
		AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());			
		AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
	}
}
