package com.adapit.portal.ui.forms;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.text.JTextComponent;

import com.adapit.portal.entidades.Arquivo;
import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;

public class HtmlEditorEventListener extends KeyAdapter implements ActionListener{

	private JTextComponent comp, comp2;
	private JFrame parent;
	private String title;
	private boolean comp1=true;
	public HtmlEditorEventListener(JTextComponent comp, JFrame parent, String title){
		this.comp=comp;
		this.title = title;
		this.parent=parent;
	}
	
	@Override
	public void keyReleased(KeyEvent evt) {
		Component c = evt.getComponent();
		if(c == comp)
			comp1=true;
		else comp1=false;
		if(evt.getKeyCode() == KeyEvent.VK_F 
				&& evt.isControlDown()){
			runAction();
		}else if(evt.getKeyCode() == KeyEvent.VK_I 
				&& evt.isControlDown()){
			
			String text = "";
			if(comp1)
				text = comp.getText();
			else 
				text = comp2.getText();
			int position = 0;
			if(comp1)
				position = comp.getCaretPosition();
			else position = comp2.getCaretPosition();
			
			Imagem img = AdapitVirtualFrame.getInstance().selectImagem();
			if(img != null){				
				String str1 = text.substring(0,position);
				//System.out.println("1 - " + str1);
				String str2 = text.substring(position,text.length());
				//System.out.println("2 - " + str2);
				String str = '\n'+"<center>" +
						'\n'+" <a  onclick=\"modalImageWin('fullImage.html?image_id=" +
								img.getId()+"','Imagem ');" +
								" return false;\" style=\"cursor: hand; cursor: pointer;\">" +
								'\n'+"  <img src=\"comercialSolutionBigImage.html?image_id=" +
								img.getId()+"\"" +
								" alt=\"Carregando a Imagem ... Por favor, espere.\"" +
								" title=\"Clique para visualizar a imagem em tamanho original\">" +
								'\n'+" </a>" +
								'\n'+"</center>";
				if(comp1){
					comp.setText(str1+str+str2);
					comp2.setText(comp.getText());
				}else{
					comp2.setText(str1+str+str2);
					comp.setText(comp2.getText());
				}
			}else{
				System.err.println("Não selecionou");
			}
		}
		else if(evt.getKeyCode() == KeyEvent.VK_L 
				&& evt.isControlDown()){
			String text = "";
			if(comp1)
				text = comp.getText();
			else 
				text = comp2.getText();
			int position = 0;
			if(comp1)
				position = comp.getCaretPosition();
			else position = comp2.getCaretPosition();
			
			Arquivo arq = AdapitVirtualFrame.getInstance().selectArquivo();
			if(arq != null){
				String str1 = text.substring(0,position);
				//System.out.println("1 - " + str1);
				String str2 = text.substring(position,text.length());
				//System.out.println("2 - " + str2);
				String str = '\n'+"<center>" +
						'\n'+" <a href=\"files/"+arq.getName()+"\"	 target=\"blanck\"> Baixar o Arquivo" +
								'\n'+" </a>" +
								'\n'+"</center>";
				if(comp1){
					comp.setText(str1+str+str2);
					comp2.setText(comp.getText());
				}else{
					comp2.setText(str1+str+str2);
					comp.setText(comp2.getText());
				}
			}else{
				System.err.println("Não selecionou");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		runAction();
	}
	
	private void runAction(){
		if(comp2 == null)
			HtmlEditorFactory.formatarAsNewEditor(comp, parent, title);
		else HtmlEditorFactory.formatarAsNewEditor(comp, comp2, parent, title);
	}

	public void setComp2(JTextComponent comp2) {
		this.comp2 = comp2;
	}
	
}
