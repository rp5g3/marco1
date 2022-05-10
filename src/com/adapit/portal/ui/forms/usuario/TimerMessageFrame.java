package com.adapit.portal.ui.forms.usuario;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.workcase.gui.utils.UIUtil;
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class TimerMessageFrame extends JFrame{
	private String message;
	public TimerMessageFrame(String message){
		this.message = message;
		initialize();
		
	}
	
	private TimerMessagePanel lmp;
	@SuppressWarnings("deprecation")
	private void initialize(){	
		setAlwaysOnTop(true);
		setUndecorated(true);
		setSize(210,80);
		lmp = new TimerMessagePanel(message);
		add(lmp,BorderLayout.CENTER);
		setLocation(UIUtil.getScreenCenter(this));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
	}
	
	public void stop(){
		lmp.setStop(true);
		dispose();
	}
}
