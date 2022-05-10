package com.adapit.portal.ui.frames.solution.sistema;

import javax.swing.JInternalFrame;

import com.adapit.portal.entidades.SoftwareDomainInterest;
import com.adapit.portal.ui.forms.software.domaininterest.SoftwareDomainInterestCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class CadastroDomainInterestInternalFrame extends JInternalFrame{
	public CadastroDomainInterestInternalFrame(){
		super();
		initialize();
	}
	
	private void initialize(){
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new java.awt.Dimension(470, 480));
		
		setTitle("Cadastro de Domínios de Software");
		comarcaCadastreForm = new SoftwareDomainInterestCadastreForm();
		add(comarcaCadastreForm, java.awt.BorderLayout.CENTER);
		setIconifiable(true);
		setResizable(false);
		setClosable(true);			
	}
	
	private SoftwareDomainInterestCadastreForm comarcaCadastreForm;
	
	public void postInDesktopPane(){
		if (!this.isShowing()){
			AdapitVirtualFrame.getInstance().beginStatusBar(getTitle());		
			AdapitVirtualFrame.getInstance().getMainDesktopPane().add(this);
			setVisible(true);
			AdapitVirtualFrame.getInstance().endStatusBar(getTitle());
			toFront();
		}
	}
	
	
	public void editRegister(SoftwareDomainInterest comarca) {
		
		comarcaCadastreForm.editRegister(comarca);
		toFront();
	}
	
	public void newRegister() {
		comarcaCadastreForm.newRegister();
		toFront();
	}
}
