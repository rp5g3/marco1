package com.adapit.portal.ui.forms.news;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;



@SuppressWarnings("serial")
public class GridPropertyEditor extends JPanel{

	private Hashtable<String,String> elements = new Hashtable<String,String>();
	private Hashtable<String,String> values = new Hashtable<String,String>();
	
	public GridPropertyEditor(){
		initialize();
	}
	
	private void initialize() {		
		setLayout(new BorderLayout());
		add(getJScrollPane(), BorderLayout.CENTER);		
	}
	
	private JScrollPane jScrollPane;

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getMessagesPanel());
			
		}
		return jScrollPane;
	}
	
	private JPanel messagesPanel;

	/**
	 * This method initializes optionsPanel
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getMessagesPanel() {
		if (messagesPanel == null) {
			messagesPanel = new JPanel();
			GridBagLayout gbl = new GridBagLayout();
			gbl.columnWidths = new int[3];
			gbl.columnWidths[0]=100;
			gbl.columnWidths[1]=300;
			gbl.columnWidths[2]=5;
			
			messagesPanel.setLayout(gbl);
			{
				Enumeration<String> it = elements.keys();
				int i = 0;
	
				while (it.hasMoreElements()) {
					String labelMsg = it.nextElement();
					{
						GridBagConstraints labelConstraint = new GridBagConstraints();
						
						labelConstraint.gridx = 0;
						labelConstraint.gridy = i;
						labelConstraint.anchor = GridBagConstraints.WEST;
						labelConstraint.fill =GridBagConstraints.REMAINDER;
				        labelConstraint.weightx = 1.0;
				        labelConstraint.insets = new Insets (5, 5, 0, 0);
						
						JLabel jl = new JLabel(labelMsg+":");
						//jl.setSize(100,20);
						jl.setHorizontalAlignment(SwingConstants.LEFT);
						messagesPanel.add(jl, labelConstraint);
					}
					{
						GridBagConstraints fieldContraint = new GridBagConstraints();
						fieldContraint.gridx = 1;
						fieldContraint.gridy = i;
						fieldContraint.fill = GridBagConstraints.BOTH;
						fieldContraint.insets = new Insets (5, 3, 0, 0);
						
						String content = elements.get(labelMsg);
						
						if (content.indexOf(",")>0){
							String contents[] = content.split(",");
							JComboBox field = new JComboBox();
							for (int j=0; j < contents.length; j++){
								String str = contents[j].trim();								
								field.addItem(str);			
								if (j == 0 ) values.put(labelMsg,str);
							}
							field.addItemListener(new TextItemListener(field,labelMsg));
							messagesPanel.add(field, fieldContraint);
						}
						else{
							JTextField field = new JTextField(content);
							TextListener tl = new TextListener(field,labelMsg);
							field.setSize(300,24);
							field.addActionListener(tl);
							field.addFocusListener(tl);
							//jl.setIcon(getIcon("/imgs/errorIcon.png"));
							//jl.addMouseListener(new ErrorLabelMouseListener());
							//jl.setToolTipText("Duplo clique para editar o valor");
							messagesPanel.add(field, fieldContraint);
							values.put(labelMsg,content);
						}
					}
					
					{
						GridBagConstraints labelConstraint = new GridBagConstraints();
						
						labelConstraint.gridx = 2;
						labelConstraint.gridy = i;
						labelConstraint.anchor = GridBagConstraints.WEST;
						labelConstraint.fill =GridBagConstraints.REMAINDER;
				        labelConstraint.weightx = 1.0;
				        
						
						JLabel jl = new JLabel("");
						jl.setHorizontalAlignment(SwingConstants.RIGHT);
						messagesPanel.add(jl, labelConstraint);
					}
					
					i++;
				}
			}
			
			
		}
		return messagesPanel;
	}
	
	private class TextItemListener implements ItemListener{

		private JComboBox field;
		private String label;
		
		public TextItemListener(JComboBox field, String label){
			this.field=field;
			this.label = label;
		}
		
		@Override
		public void itemStateChanged(ItemEvent evt) {
			if (evt.getStateChange() == ItemEvent.SELECTED){
				values.put(label,(String) field.getSelectedItem());
			}
		}
		
	}
	
	private class TextListener extends FocusAdapter implements ActionListener{

		private JTextField field;
		private String label;
		
		public TextListener(JTextField field, String label){
			this.field=field;
			this.label = label;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			values.put(label,field.getText());
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			values.put(label,field.getText());
		}
		
	}
	
	


	
	public void removeAllElements(){		
		elements.clear();
		messagesPanel = null;
		jScrollPane.setViewportView(getMessagesPanel());
	}
	
	public void updateElementList(){
		messagesPanel = null;
		jScrollPane.setViewportView(getMessagesPanel());
		updateUI();
	}

	public Hashtable<String, String> getElements() {
		return elements;
	}

	public void setElements(Hashtable<String, String> elements) {
		this.elements = elements;
		/*Enumeration e = elements.keys();
		if (e != null){
			while (e.hasMoreElements()){
				String label = (String) e.nextElement();
				 String value = elements.get(label);
				 values.put(label,value);
			}
		}*/
		
	}

	public Hashtable<String, String> getValues() {
		return values;
	}

	public void setValues(Hashtable<String, String> values) {
		this.values = values;
	}




}
