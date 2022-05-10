package com.adapit.portal.ui.frames.pessoa.rh;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.adapit.portal.services.PersonType;
import com.adapit.portal.ui.forms.pessoa.comitentesimples.CadastrarPessoaDivulgavelTabs;
import com.adapit.portal.ui.forms.pessoa.comitentesimples.PessoaDivulgavelFilter;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class ComitenteCadastreDialog extends JDialog{

	public ComitenteCadastreDialog(PersonType pt) {
		super(AdapitVirtualFrame.getInstance());
		initialize(pt);
	}
	
	

	private void initialize(PersonType pt){
		setTitle("Cadastro de Comitentes");
		setModal(true);
		setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(/*new java.awt.BorderLayout()*/null);
		setSize(new Dimension(570, 500));
		try {
			cadastroComitenteForm = new CadastrarPessoaDivulgavelTabs(pt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		add(getHelpButton());
		PessoaDivulgavelFilter form = new PessoaDivulgavelFilter(this);
		form.setLocation(0,0);
		add(form/*, java.awt.BorderLayout.NORTH*/);
		cadastroComitenteForm.setLocation(0,60);
		cadastroComitenteForm.setComitenteFilter(form);
		add(cadastroComitenteForm/*, java.awt.BorderLayout.CENTER*/);
		//setIconifiable(true);
		//setResizable(false);
		//setClosable(true);
		setLocation(UIUtil.getScreenCenter(this));
	}
	
	private CadastrarPessoaDivulgavelTabs cadastroComitenteForm;

	public CadastrarPessoaDivulgavelTabs getCadastrarComitenteForm() {
		return cadastroComitenteForm;
	}

	public void setCadastrarComitenteForm(CadastrarPessoaDivulgavelTabs leilaoCadastreForm) {
		this.cadastroComitenteForm = leilaoCadastreForm;
	}
	
	private JButton helpButton;
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setBounds(new Rectangle(527, 7, 20, 20));
			helpButton.setIcon(new ImageIcon(getClass().getResource("/imgs/helpicon.png")));
			helpButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					/*JavaHelpFrame helpFrame = JavaHelpFrame.getInstance();
					helpFrame.setCurrentTopic("Cadastrando_Comitentes");
					helpFrame.setVisible(true);*/
				}
			});
		}
		return helpButton;
	}
	
}
