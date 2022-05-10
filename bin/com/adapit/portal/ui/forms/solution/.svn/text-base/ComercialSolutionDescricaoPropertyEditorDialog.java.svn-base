package com.adapit.portal.ui.forms.solution;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.adapit.portal.entidades.Categoria;
import com.adapit.portal.ui.forms.solution.panel.GridPropertyEditor;
import com.workcase.gui.utils.UIUtil;

public class ComercialSolutionDescricaoPropertyEditorDialog extends JDialog {

	private static final long serialVersionUID = 2312531253L;
	private JPanel jContentPane = null;



	private Categoria categoria;
	public ComercialSolutionDescricaoPropertyEditorDialog(JFrame frame, Categoria categoria) {
		super(frame);
		this.categoria = categoria;
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
		this.setTitle("Editor de descrição de solução comercializável");
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
			jContentPane.add(getCategoriaPanel(), java.awt.BorderLayout.NORTH);
			jContentPane.add(getGridPropertyEditor(), java.awt.BorderLayout.CENTER);
			jContentPane.add(getButtonsPanel(), java.awt.BorderLayout.SOUTH);
		}
		return jContentPane;

	}
	
	private JPanel categoriaPanel;
	
	private JPanel getCategoriaPanel(){
		if (categoriaPanel == null){
			categoriaPanel = new JPanel();
			categoriaPanel.setLayout(new FlowLayout());
			JLabel catLabel = new JLabel(categoria.getNome());
			if (categoria.getCategoriaImagem() != null) catLabel.setIcon(categoria.getCategoriaImagem().getSmallIcon(true));
			categoriaPanel.add(catLabel);
		}
		return categoriaPanel;
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
	private Hashtable<String,String> ht = new Hashtable<String,String>();
	private Vector<String> keys = new Vector<String>();
	@SuppressWarnings("unchecked")
	public void processString(String str){
		ht.clear();
		keys.clear();
		if (str == null) return;
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
						keys.add(label);
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
					keys.add(label);
				}				
			}
		}else{
			String sentence = str;
			if (sentence.indexOf(":")>0){
				String sentences[] = sentence.split(":");
				if (sentences.length >0){
					String label = "";
					if (sentences[0] != null && sentences[0].length()>0) label=sentences[0].trim();
					String value = "";
					if (sentences.length > 1 && sentences[1] != null && sentences[1].length()>0) value = sentences[1].trim();
					ht.put(label,value);
					keys.add(label);
				}
			}
		}
		gridPropertyEditor.setElements(ht);
		gridPropertyEditor.setKeys(keys);
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
				ComercialSolutionDescricaoPropertyEditorDialog p = new ComercialSolutionDescricaoPropertyEditorDialog(jf,new Categoria());
				p.setVisible(true);
			}
			
		});
		jf.add(jb);
		jf.show();
		
	}


	public Vector<String> getKeys() {
		return keys;
	}
	
	

}  //  @jve:decl-index=0:visual-constraint="10,10"
