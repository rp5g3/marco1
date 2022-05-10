package com.adapit.portal.ui.forms.usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class TimerMessagePanel extends JPanel implements ActionListener{
	
	@SuppressWarnings("unused")
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	private String message;
	public TimerMessagePanel(String message){
		this.message = message;
		initialize();
		setBackground(Color.WHITE);
	}
	
	private Timer timer;
	
	@SuppressWarnings("deprecation")
	private void initialize(){	
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		messageLabel = new JLabel();
		messageLabel.setFont(new Font("Arial", Font.BOLD, 12));
		messageLabel.setForeground(Color.DARK_GRAY);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setText(message);
		imageLabel = new JLabel();
		imageLabel.setSize(200,30);
		imageLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/bigrotation2.gif")));
		imageLabel.setText("");
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setBackground(Color.WHITE);
		timeLabel = new JLabel();
		timeLabel.setFont(new Font("Arial", Font.BOLD, 11));
		timeLabel.setForeground(Color.DARK_GRAY);
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setText("Tempo decorrido: 0 segundos");
		setOpaque(true);
		setSize(new Dimension(210, 80));
		setLayout(new BorderLayout());
		//imageLabel.setOpaque(false);
		
		add(timeLabel, BorderLayout.SOUTH);
		add(imageLabel,BorderLayout.CENTER);
		add(messageLabel, BorderLayout.NORTH);
		timer = new Timer((1)*1000, this);
		timer.start();
	}
	
	@SuppressWarnings("unused")
	private static Icon getIcon(String name ){

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL).getImage();
				if (image != null) {
					image = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace(); 
		} catch (java.lang.Exception e) {
			e.printStackTrace(); 
		}//end of catch block
		return null;
	}

	private JLabel imageLabel = null;
	private JLabel messageLabel = null;
	private JLabel timeLabel = null;
	private boolean stop = false;
	int cont=0;
	int segundos=0;
	@Override
	public void actionPerformed(ActionEvent e) {
		cont++;
		if (cont > 10)
			cont=0;
		segundos++;
		timeLabel.setText("Tempo decorrido: "+segundos + " segundos");
		//updateUI();
		if (stop){			
			timer.stop();
			System.out.println("Atividade concluída em " + segundos + " segundos" );
		}
		
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	

}