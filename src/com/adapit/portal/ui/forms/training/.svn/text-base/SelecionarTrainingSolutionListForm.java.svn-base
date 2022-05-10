package com.adapit.portal.ui.forms.training;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.ui.forms.categoria.CategoriaSelectable;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class SelecionarTrainingSolutionListForm extends JDialog implements CategoriaSelectable{

	private TrainingSolutionListForm trainingListForm;
	private JButton selecionarButton;
	//private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	public SelecionarTrainingSolutionListForm() {
		super(AdapitVirtualFrame.getInstance());
		initialize();
	}

	private void initialize() {
		setSize(getTrainingListForm().getSize().width+20,getTrainingListForm().getSize().height+20);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		//setLayout(null);
		setLocation(UIUtil.getScreenCenter(this));
		add(getTrainingListForm());
		
	}
	
	private TrainingSolutionListForm getTrainingListForm(){
		if (trainingListForm == null){
			trainingListForm = new TrainingSolutionListForm(true);
			trainingListForm.getBaseTable().addFocusListener(new FocusListener(){
				public void focusGained(FocusEvent evt) {
					getSelecionarButton().setEnabled(true);
					
				}

				public void focusLost(FocusEvent evt) {
				}				
			});
			trainingListForm.getButtonsPanel().add(getSelecionarButton());
		}
		return trainingListForm;
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
					int row = getTrainingListForm().getBaseTable().getSelectedRow();
					if (row >=0){
						trainingSolution = (TrainingSolution) getTrainingListForm().getSelectedSolução();
						dispose();
					}
				}
			});
			
		}
		return selecionarButton;
	}
	
	private TrainingSolution trainingSolution;
	
	public TrainingSolution getTrainingSolution(){
		return trainingSolution;
	}

	@Override
	public Categoria getSelectedElement() {
		return trainingListForm.getSelectedElement();
	}

	@Override
	public void setSelectedElement(Categoria categoria) {
		trainingListForm.setSelectedElement(categoria);
		
	}
}