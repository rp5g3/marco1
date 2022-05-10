package com.adapit.portal.ui.forms.update;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.workcase.gui.utils.UIUtil;

public class UpdateDescricaoPropertyEditorDialog extends JDialog {

	private static final long serialVersionUID = 2312531253L;
	private JPanel jContentPane = null;


	public UpdateDescricaoPropertyEditorDialog(JFrame frame) {
		super(frame);
		initialize();
	}

	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(450, 300);
		this.setModal(true);
		this.setTitle("Editor de descrição de versões de soluções comerciais");
		this.setLocation(UIUtil.getScreenCenter(this));
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());			
			jContentPane.add(getGridPropertyEditor(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonsPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;

	}
	
	

	private GridPropertyEditor gridPropertyEditor;
	private JPanel buttonsPanel = null;
	
	public GridPropertyEditor getGridPropertyEditor(){
		if (gridPropertyEditor == null){
			gridPropertyEditor = new GridPropertyEditor();
			
			return gridPropertyEditor;
		}
		return gridPropertyEditor;
	}
	private Hashtable<String,String> ht;
	
	@SuppressWarnings("unchecked")
	public void processString(String str){
		ht = new Hashtable();

		if (str.indexOf('\n')>0){
			String values[] = str.split('\n'+"");
			for (int i=0; i < values.length; i++){
				String sentence = values[i].trim();
				if (sentence.indexOf(":")>0){
					String sentences[] = sentence.split(":");
					if (sentences.length >0){
						String label = "";
						if (sentences[0] != null && sentences[0].length()>0) label=sentences[0].trim();
						String value = "";
						if (sentences.length > 1 && sentences[1] != null && sentences[1].length()>0) value = sentences[1].trim();
						ht.put(label,value);
					}
				}				
			}
		}else if(str.indexOf("|")>0){
			String values[] = str.split("|");
			for (int i=0; i < values.length; i++){
				String sentence = values[i].trim();
				if (sentence.indexOf(":")>0){
					String sentences[] = sentence.split(":");
					String label = sentences[0].trim();
					String value = sentences[1].trim();
					ht.put(label,value);
				}				
			}
		} 
		gridPropertyEditor.setElements(ht);
		gridPropertyEditor.updateElementList();
	}
	

	
	/**
	 * This method initializes buttonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getButtonsPanel() {
		if (buttonsPanel == null) {
			buttonsPanel = new JPanel();
			buttonsPanel.setLayout(new FlowLayout());
			JButton jb = new JButton("OK");
			buttonsPanel.add(jb);
			jb.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					/*
					Enumeration e = gp.getValues().keys();
					if (e != null){
						while (e.hasMoreElements()){
							String str = (String) e.nextElement();
							System.out.println(str+" ... " + gp.getValues().get(str));
						}
					}*/
					dispose();
				}				
			});
		}
		return buttonsPanel;
	}
	
	public Hashtable<String,String> getValues(){
		return gridPropertyEditor.getValues();		
	}
	
	

	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		final JFrame jf = new JFrame();
		jf.setSize(100,60);
		jf.setLocation(300,300);
		JButton jb = new JButton("Dialog");
		jb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				UpdateDescricaoPropertyEditorDialog p = new UpdateDescricaoPropertyEditorDialog(jf);
				p.setVisible(true);
			}
			
		});
		jf.add(jb);
		jf.show();
		
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
