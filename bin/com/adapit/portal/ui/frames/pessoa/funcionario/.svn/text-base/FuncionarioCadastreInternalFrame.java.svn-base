package com.adapit.portal.ui.frames.pessoa.funcionario;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import com.adapit.portal.ui.forms.pessoa.funcionario.CadastrarUsuarioFuncionario;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class FuncionarioCadastreInternalFrame extends JInternalFrame{

	public FuncionarioCadastreInternalFrame() {
		initialize();
	}

	private void initialize(){
		setTitle("Cadastro de Funcionários");
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(570, 440));
		cadastroFuncionariosForm = new CadastrarUsuarioFuncionario();
		add(cadastroFuncionariosForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
		this.addInternalFrameListener(new InternalFrameAdapter(){
			@Override
			public void internalFrameClosed(InternalFrameEvent arg0) {
				AdapitVirtualFrame.getInstance().setFuncionarioCadastreFrame(null);
			}
		});
	}
	
	private CadastrarUsuarioFuncionario cadastroFuncionariosForm;

	public CadastrarUsuarioFuncionario getCadastrarUsuarioFuncionario() {
		return cadastroFuncionariosForm;
	}

	public void setCadastrarUsuarioFuncionario(CadastrarUsuarioFuncionario leilaoCadastreForm) {
		this.cadastroFuncionariosForm = leilaoCadastreForm;
	}
	
	public void postInDesktopPane(){
		AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());			
		AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
		setVisible(true);
		AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
		toFront();
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"
