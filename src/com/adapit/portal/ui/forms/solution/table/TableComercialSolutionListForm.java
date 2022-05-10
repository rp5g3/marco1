package com.adapit.portal.ui.forms.solution.table;


import java.util.List;

import javax.swing.JTable;

import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.TrainingSolution;


@SuppressWarnings({"serial","unchecked"})
public class TableComercialSolutionListForm extends JTable{
	
	private List elements;


	
	public void setElements(List elements ){
	this.elements = elements;
	}
	
	public List getElements(){
	return this.elements;
	}
	
	public TableComercialSolutionListForm(){

		super();
		this.setModel( new TableComercialSolutionListFormModel(null));
		this.addPropertyChangeListener(new TableComercialSolutionListFormPropertyChangeListener());
	}
	
	public TableComercialSolutionListForm(List elements ){

		super();
		this.setModel( new TableComercialSolutionListFormModel(null));
		this.addPropertyChangeListener(new TableComercialSolutionListFormPropertyChangeListener());
	}
	
	public void setDefineCellRenderers(){

		/*LabelCellRenderer labelRenderer = new LabelCellRenderer();
		ComboBoxCellRenderer comboBoxRenderer = new ComboBoxCellRenderer();
	*/
	}
	
	public void updateTable(){
		if (elements != null && elements.size()>0){
			int ne = elements.size();
			java.util.Iterator it = elements.iterator();
			java.lang.Object values[][] = new java.lang.Object[ne][5];
			int i = 0;
			while (it.hasNext()) {
				Object obj = it.next();
				if (obj instanceof TrainingSolution){
					TrainingSolution comsol=(TrainingSolution) obj;
					values[i][0]=comsol.getDescricao();
					values[i][1]=comsol.getAvaliacao();
					values[i][2]=comsol.getCategoria();
					if (comsol.getTrainingFormationItens() != null){
						for(ComercialSolutionItem ip : comsol.getComercialSolutionItens()){
							if (ip.getTreinamento() != null){
								values[i][3]=ip.getTreinamento().getCodigo();
								values[i][4]="";
							}
						}
					}
					else{
						values[i][3]="";
						values[i][4]="";
					}
				}
					i++;
			}//End of while Loop
			setModel(new TableComercialSolutionListFormModel(values));
			updateUI();
		}		
		else{
				setModel(new TableComercialSolutionListFormModel(null));
				updateUI();
		}
	}



}