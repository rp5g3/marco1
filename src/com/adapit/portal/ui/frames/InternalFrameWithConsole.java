package com.adapit.portal.ui.frames;

import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
@SuppressWarnings({"serial","unchecked","unused","static-access","deprecation"})
public class InternalFrameWithConsole extends JInternalFrame {
	TextAreaPrintStream stream;
    public InternalFrameWithConsole() {
    	setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
		setLayout(new java.awt.BorderLayout());
		setSize(new Dimension(653, 531));
		setIconifiable(true);
		setResizable(false);
		setClosable(true);
		setTitle("Console");
        JTextArea consoleTextArea = new JTextArea();
        consoleTextArea.setBounds(0,0,300,300);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(consoleTextArea);
        //jsp.add(consoleTextArea);
        add(jsp);
        stream = new TextAreaPrintStream(consoleTextArea);
        System.setOut(stream);
        System.setErr(stream);
        
    }
 
    private static class TextAreaPrintStream extends PrintStream {
        private JTextArea consoleTextArea;
 
        public TextAreaPrintStream(JTextArea consoleTextArea) {
        	super(System.out);
            this.consoleTextArea = consoleTextArea;
        }
 
        @Override
        public void println(String s) {
            consoleTextArea.append(s + '\n');
        }
 
        //override other PrintStream methods you want to support in the same fashion
    }
}