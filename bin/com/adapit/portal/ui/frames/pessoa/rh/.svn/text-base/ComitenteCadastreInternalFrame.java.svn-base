package com.adapit.portal.ui.frames.pessoa.rh;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.forms.pessoa.comitentesimples.CadastrarPessoaDivulgavelTabs;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class ComitenteCadastreInternalFrame extends JInternalFrame{

	public ComitenteCadastreInternalFrame(PersonType pt) {
		initialize(pt);
	}

	private void initialize(PersonType pt){
		setTitle("Cadastro de Comitentes");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(570, 440));
		try {
			cadastroComitenteForm = new CadastrarPessoaDivulgavelTabs(pt);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		add(cadastroComitenteForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
	}
	
	private CadastrarPessoaDivulgavelTabs cadastroComitenteForm;

	public CadastrarPessoaDivulgavelTabs getCadastrarComitenteForm() {
		return cadastroComitenteForm;
	}

	public void setCadastrarComitenteForm(CadastrarPessoaDivulgavelTabs leilaoCadastreForm) {
		this.cadastroComitenteForm = leilaoCadastreForm;
	}
	
	public void postInDesktopPane(){
		AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());			
		AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
	}
}
