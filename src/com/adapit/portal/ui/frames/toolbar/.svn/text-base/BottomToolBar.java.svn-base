package com.adapit.portal.ui.frames.toolbar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import com.adapit.portal.ui.frames.AdapitVirtualFrame;

@SuppressWarnings("serial")
public class BottomToolBar extends JToolBar {
	
	private JLabel operacaoLabel = null;

	private JLabel avisoLabel = null;

	private JTextField avisoTextField = null;

	private JTextField operacaoTextField = null;

	private JButton helpBottomButton = null;

	private JPanel separator1label = null;

	private JPanel separator2Label = null;

	private JProgressBar progressBar = null;

	private JButton alertaButton = null;

	public BottomToolBar() {
		initialize();
	}

	public BottomToolBar(int arg0) {
		super(arg0);
		initialize();
	}

	public BottomToolBar(String arg0) {
		super(arg0);
		initialize();
	}

	public BottomToolBar(String arg0, int arg1) {
		super(arg0, arg1);
		initialize();
	}
	
	private void initialize(){
		separator2Label = new JPanel();
		
		separator2Label.setForeground(new Color(51, 51, 255));
		
		separator1label = new JPanel();
		
		separator1label.setForeground(new Color(51, 51, 255));
		
		avisoLabel = new JLabel();

		avisoLabel.setHorizontalTextPosition(JLabel.LEFT);
		avisoLabel.setText("Alerta:");
		operacaoLabel = new JLabel();
		operacaoLabel.setText("Operação:");
		
		setFloatable(false);
		
		add(operacaoLabel);

		add(getOperacaoTextField());
		add(getHelpBottomButton());
		add(separator1label);
		add(avisoLabel);
		add(getAvisoTextField());
		add(getAlertaButton());
		add(separator2Label);
		add(getProgressBar());
		setBorder(BorderFactory.createBevelBorder(2));
	}
	
	/**
	 * This method initializes avisoTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getAvisoTextField() {
		if (avisoTextField == null) {
			avisoTextField = new JTextField();
			avisoTextField.setEditable(false);
			avisoTextField.setText("");
			avisoTextField.setFont(new Font("Dialog", Font.BOLD, 9));
			avisoTextField.setForeground(new Color(250, 10, 10));
		}
		return avisoTextField;
	}

	/**
	 * This method initializes operacaoTextField
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getOperacaoTextField() {
		if (operacaoTextField == null) {
			operacaoTextField = new JTextField();
			operacaoTextField.setText("");
			operacaoTextField.setEditable(false);
			operacaoTextField.setFont(new Font("Dialog", Font.BOLD, 9));
			operacaoTextField.setForeground(new Color(10, 10, 250));
		}
		return operacaoTextField;
	}

	/**
	 * This method initializes helpBottomButton
	 * 
	 * @return javax.swing.JButton
	 */
	@SuppressWarnings("static-access")
	private JButton getHelpBottomButton() {
		if (helpBottomButton == null) {
			helpBottomButton = new JButton();
			helpBottomButton.setSize(20, 20);
			helpBottomButton.setIcon(AdapitVirtualFrame.getInstance().getIcon("/imgs/helpicon.png", 18, 18));
		}
		return helpBottomButton;
	}



	

	/**
	 * This method initializes alertaButton
	 * 
	 * @return javax.swing.JButton
	 */
	@SuppressWarnings("static-access")
	private JButton getAlertaButton() {
		if (alertaButton == null) {
			alertaButton = new JButton();
			alertaButton.setSize(20, 20);
			alertaButton.setIcon(AdapitVirtualFrame.getInstance().getIcon("/imgs/warn.png", 18, 18));
			alertaButton.setEnabled(false);
		}
		return alertaButton;
	}
	
	private JProgressBar oldProgress;
	/**
	 * This method initializes progressBar
	 * 
	 * @return javax.swing.JProgressBar
	 */
	public JProgressBar getProgressBar() {
		if (progressBar == null) {
			progressBar = new JProgressBar();
			progressBar.setPreferredSize(new Dimension(100, 18));
			//progressBar.setIndeterminate(true);
			progressBar.setEnabled(false);
			oldProgress = progressBar;
		}
		return progressBar;
	}
	
	private Hashtable<String,String> statusMessages = new Hashtable<String,String>();
	
	private boolean progressShown=false;
	public void beginStatusBar(String msg){
		getOperacaoTextField().setText("..."+msg);
		getOperacaoTextField().updateUI();
		//repaint();
		if (!statusMessages.containsKey(msg)){
			statusMessages.put(msg, msg);
			if ((statusMessages.size() > 0)){
				showProgressBar();
			}
		}
		
	}
	
	public void endStatusBar(String msg){
		if (statusMessages.containsKey(msg)){
			statusMessages.remove(msg);
			if (!(statusMessages.size() > 0)){
				remove(progressBar);
				add(oldProgress);
				updateUI();
				progressShown=false;
				getOperacaoTextField().setText("");
			}
		}
	}
	
	
	
	public void showProgressBar() {
		if (!progressShown){
			progressShown=true;
			remove(oldProgress);
			progressBar = new JProgressBar();
			progressBar.setPreferredSize(new Dimension(100, 18));
			progressBar.setIndeterminate(true);			
			progressBar.setVisible(true);
			add(progressBar);
			updateUI();
			repaint();
		}
	}
	
	public void displayErrorMsg(String message) {
		//instance.validate();
		avisoTextField.setText(message);
		alertaButton.setEnabled(true);
	}
	
	private Vector<String> errorMessages = new Vector<String>();
	public void appendErrorMsg(String message){
		errorMessages.add(message);
	}
	
	public void cleanErrorMsg(){
		avisoTextField.setText("");
		errorMessages.clear();
		alertaButton.setEnabled(false);
	}

	public void setOperationMessage(String string) {
		getOperacaoTextField().setText(string);
	}

	public void showWarningMsg(String string) {
		avisoTextField.setText(string);
		alertaButton.setEnabled(true);
	}

}
