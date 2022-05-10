package com.workcase.gui.custom.logerror;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class LogErrorPanel extends JPanel{

	private Hashtable<String,JComponent> errorComponents = new Hashtable<String,JComponent>();
	private Vector<String> errorMessages = new Vector<String>();
	private Vector<String> alertMessages = new Vector<String>();
	
	public LogErrorPanel(){
		initialize();
	}
	
	private void initialize() {		
		/*GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 1.0;
		gridBagConstraints.weighty = 1.0;
		gridBagConstraints.gridwidth = 1;
		gridBagConstraints.gridx = 0;
		GridBagLayout gbl = new GridBagLayout();*/
		setLayout(new BorderLayout());
		add(getJScrollPane(), BorderLayout.CENTER/*gridBagConstraints*/);		
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
			
			messagesPanel.setLayout(gbl);
			{
				Iterator<String> it = errorMessages.iterator();
				int i = 0;
	
				while (it.hasNext()) {
					String errorMsg = it.next();
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = i;
					
					JLabel jl = new JLabel(errorMsg);
					jl.setIcon(getIcon("/imgs/errorIcon.png"));
					jl.addMouseListener(new ErrorLabelMouseListener());
					jl.setToolTipText("Duplo clique para editar o valor");
					messagesPanel.add(jl, gbc);
					i++;
				}
			}
			{
				Iterator<String> it = alertMessages.iterator();
				int i = 0;
	
				while (it.hasNext()) {
					String alertMsg = it.next();
					GridBagConstraints gbc = new GridBagConstraints();
					gbc.gridx = 0;
					gbc.gridy = i;
					
					JLabel jl = new JLabel(alertMsg);
					jl.setIcon(getIcon("/imgs/warn.png"));
					/*jl.addMouseListener(new ErrorLabelMouseListener());
					jl.setToolTipText("Duplo clique para editar o valor");*/
					messagesPanel.add(jl, gbc);
					i++;
				}
			}
		}
		return messagesPanel;
	}
	
	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(12, 12,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			//TODO 
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			//TODO 
			e.printStackTrace();
		}//end of catch block
		return null;
	}

	private class ErrorLabelMouseListener extends MouseAdapter{

		@Override
		public void mousePressed(MouseEvent evt) {
			if (evt.getClickCount() == 2 && evt.getButton() == MouseEvent.BUTTON1){
				JLabel jl = (JLabel) evt.getSource();
				JComponent jc = errorComponents.remove(jl.getText());
				errorMessages.remove(jl.getText());
				getMessagesPanel().remove(jl);
				getMessagesPanel().updateUI();
				jc.requestFocus(true);
			}
		}
		
	}
	
	

	public Vector<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(Vector<String> errorMessages) {
		this.errorMessages = errorMessages;
	}

	public Hashtable<String, JComponent> getErrorComponents() {
		return errorComponents;
	}

	public void setErrorComponents(Hashtable<String, JComponent> errorComponents) {
		this.errorComponents = errorComponents;
	}
	
	public void addError(String errorMsg, JComponent component) throws Exception{
		errorMessages.add(errorMsg);
		errorComponents.put(errorMsg,component);
	}
	public void addError(String errorMsg) throws Exception{
		errorMessages.add(errorMsg);
		
	}
	
	public void removeAllElements(){
		errorMessages.clear();
		alertMessages.clear();
		errorComponents.clear();
		messagesPanel = null;
		jScrollPane.setViewportView(getMessagesPanel());
	}
	
	public void updateErrorList(){
		messagesPanel = null;
		jScrollPane.setViewportView(getMessagesPanel());
		updateUI();
	}

	public void addAlert(String alertMsg) {
		alertMessages.add(alertMsg);
	}
}
