package com.adapit.portal.ui.forms.solution;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.ComercialSolution;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class SelecionarComercialSolutionListForm extends JDialog implements CategoriaSelectable{

	private ComercialSolutionListForm produtoListForm;
	private JButton selecionarButton;
	//private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	public SelecionarComercialSolutionListForm() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize() {
		setSize(getProdutoListForm().getSize().width+20,getProdutoListForm().getSize().height+20);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		//setLayout(null);
		setLocation(UIUtil.getScreenCenter(this));
		add(getProdutoListForm());
		
	}
	
	private ComercialSolutionListForm getProdutoListForm(){
		if (produtoListForm == null){
			produtoListForm = new ComercialSolutionListForm(true);
			produtoListForm.getBaseTable().addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent evt) {
					getSelecionarButton().setEnabled(true);
					
				}

				public void focusLost(FocusEvent evt) {
				}				
			});
			produtoListForm.getButtonsPanel().add(getSelecionarButton());
		}
		return produtoListForm;
	}

	protected JButton getSelecionarButton() {

		if (selecionarButton == null) {
			selecionarButton = new JButton();
			selecionarButton.setSize(new java.awt.Dimension(120, 22));
			selecionarButton.setText("Selecionar");
			selecionarButton.setEnabled(false);
			selecionarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/accept.png")));
			selecionarButton.setLocation(new java.awt.Point(0, 66));
			selecionarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getProdutoListForm().getBaseTable().getSelectedRow();
					if (row >=0){
						produto = (ComercialSolution) getProdutoListForm().getBaseTable().getElements().get(row);
						dispose();
					}
				}
			});
			
		}
		return selecionarButton;
	}
	
	private ComercialSolution produto;
	
	public ComercialSolution getProduto(){
		return produto;
	}

	@Override
	public Categoria getSelectedElement() {
		return produtoListForm.getSelectedElement();
	}

	@Override
	public void setSelectedElement(Categoria categoria) {
		produtoListForm.setSelectedElement(categoria);
		
	}
}  //  @jve:decl-index=0:visual-constraint="10,10"