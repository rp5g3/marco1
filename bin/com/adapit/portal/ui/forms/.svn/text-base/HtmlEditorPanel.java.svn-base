package com.adapit.portal.ui.forms;

import java.awt.BorderLayout;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.JTextComponent;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import javax.swing.text.html.HTMLEditorKit;

import com.adapit.portal.entidades.Imagem;
import com.adapit.portal.ui.forms.imageform.ImageListForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.UIUtil;
import com.workcase.utils.TextComponentSwingUtil;

public class HtmlEditorPanel extends JPanel{

	private JScrollPane htmlContentScrollPane;
	private JTextComponent htmlContentTextPane;
	private JTabbedPane jtb;
	private JToolBar jTool;
	private HtmlEditorEventListener eventListener;
	private String css;
	
	private static final int TAB_SIZE=3;
	
	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
		if(css != null){
			((HTMLEditorKit)viewContentTextPane.getEditorKit()).getStyleSheet().addRule(css);
		}		
	}
	/*static class TabDocument extends DefaultStyledDocument {
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
                str = str.replaceAll("\t", "      ");
                super.insertString(offs, str, a);
        }
    }*/
	
	public void setTabs(JTextPane textPane)	{
		int charactersPerTab = TAB_SIZE;
		TextComponentSwingUtil.setUndoRedo(textPane);
		FontMetrics fm = textPane.getFontMetrics(textPane.getFont());

		int charWidth = fm.charWidth('w');

		int tabWidth = charWidth * charactersPerTab;

		TabStop[] tabs = new TabStop[10];

		for (int j = 0; j < tabs.length; j++)

		{

			int tab = j + 1;

			tabs[j] = new TabStop(tab * tabWidth);

		}

		TabSet tabSet = new TabSet(tabs);

		SimpleAttributeSet attributes = new SimpleAttributeSet();

		StyleConstants.setTabSet(attributes, tabSet);

		int length = textPane.getDocument().getLength();

		textPane.getStyledDocument().setParagraphAttributes(0, length,
				attributes, true);

	}

	public HtmlEditorPanel(JTextComponent textComponent, HtmlEditorEventListener eventListener, String css){
		this.htmlContentTextPane = textComponent;
		this.eventListener = eventListener;
		this.css = css;
		htmlContentTextPane.addFocusListener(new FocusAdapter(){
			@Override
			public void focusLost(FocusEvent evt) {
				viewContentTextPane.setText(htmlContentTextPane.getText());
			}				
		});
		//htmlContentTextPane.setTabSize(3);
		/*Document doc = htmlContentTextPane.getDocument();
		if (doc instanceof PlainDocument) {
		    doc.putProperty(PlainDocument.tabSizeAttribute, 4);
		}*/
		
		//htmlContentTextPane.setDocument(new TabDocument());
		//TODO
		if(htmlContentTextPane instanceof JTextPane)
			setTabs((JTextPane)htmlContentTextPane);
		initialize();
	}
	
	private void initialize(){
		setLayout(new BorderLayout());
		add(getJTool(),BorderLayout.SOUTH);
		add(getJtb(),BorderLayout.CENTER);
		eventListener.setComp2(viewContentTextPane);
		viewContentTextPane.addKeyListener(eventListener);
	}
	
	
	private class SelectButton extends JButton implements ActionListener{
		
		public ImageListForm listForm;
		public SelectButton(){
			super("Selecionar a imagem");
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(listForm != null){
				Imagem im = listForm.getSelectedImage();
				if(im == null)
					return;
				String fullText = htmlContentTextPane.getText();
				//int index = htmlContentTextPane.getCaretPosition();
				//String first = fullText.substring(0, index-1);
				//String last = fullText.substring(index, fullText.length());
				String strImage="<img src=\"comercialSolutionFullImage.html?image_id="+im.getId()+"\" alt=\"Carregando a Imagem ... Por favor, espere.\">";
				//htmlContentTextPane.setText(first+strImage+last);
				htmlContentTextPane.setText(fullText+strImage);
			}
		}
	}
	
	private class SelectCssButton extends JButton implements ActionListener{
		
		public ImageListForm listForm;
		public SelectCssButton(){
			super("Selecionar o Css");
			addActionListener(this);
		}
		
		@Override
		public void actionPerformed(ActionEvent evt) {
			if(listForm != null){
				Imagem im = listForm.getSelectedImage();
				if(im == null)
					return;
				String fullText = htmlContentTextPane.getText();
				//int index = htmlContentTextPane.getCaretPosition();
				//String first = fullText.substring(0, index-1);
				//String last = fullText.substring(index, fullText.length());
				String strImage="<img src=\"comercialSolutionFullImage.html?image_id="+im.getId()+"\" alt=\"Carregando a Imagem ... Por favor, espere.\">";
				//htmlContentTextPane.setText(first+strImage+last);
				htmlContentTextPane.setText(fullText+strImage);
			}
		}
	}
	
	private JToolBar getJTool(){
		if(jTool == null){
			jTool = new JToolBar();
			
			jTool.setFloatable(false);
			JButton jb = new JButton();
			jb.setToolTipText("Abrir o editor de HTML");
			jb.setIcon(new ImageIcon(getClass().getResource("/imgs/note_edit.png")));
			jb.addActionListener(eventListener);
			jTool.add(jb);
			
			jb = new JButton();
			jb.setToolTipText("Editar e Formatar o Html");
			jb.setIcon(new ImageIcon(getClass().getResource("/imgs/html.png")));
			jb.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setTitle("Editar Html");
					jd.setModal(true);
					JTextPane form = new JTextPane();
					//TODO
					//form.setDocument(new TabDocument());
					setTabs((JTextPane)form);
					/*Document doc = form.getDocument();
					if (doc instanceof PlainDocument) {
					    doc.putProperty(PlainDocument.tabSizeAttribute, 4);
					}*/
					form.setText(htmlContentTextPane.getText());
					jd.add(new JScrollPane(form),BorderLayout.CENTER);
					jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					UIUtil.setFullScreen(jd);
					jd.setVisible(true);
					htmlContentTextPane.setText(form.getText());
				}
			});
			jTool.add(jb);
			
			jb = new JButton();
			jb.setIcon(new ImageIcon(getClass().getResource("/imgs/image_add.png")));
			jb.setToolTipText("Inserir Imagem");
			//jb.setEnabled(false);
			jb.addActionListener(new ActionListener() {				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					SelectButton bt = new SelectButton();
					bt.listForm = AdapitVirtualFrame.getInstance().listarImagens(bt);					
				}
			});
			jTool.add(jb);
			
			jb = new JButton();
			jb.setIcon(new ImageIcon(getClass().getResource("/imgs/tab_add.png")));
			jb.setToolTipText("Inserir Abas");
			jb.setEnabled(false);
			//jb.addActionListener(eventListener);
			jTool.add(jb);
			
			jb = new JButton();
			jb.setIcon(new ImageIcon(getClass().getResource("/imgs/application_split.png")));
			jb.setToolTipText("Inserir Accordions");
			jb.setEnabled(false);
			//jb.addActionListener(eventListener);
			jTool.add(jb);
			
			jb = new JButton();
			jb.setIcon(new ImageIcon(getClass().getResource("/imgs/application_add.png")));
			jb.setToolTipText("Inserir Diálogo");
			jb.setEnabled(false);
			//jb.addActionListener(eventListener);
			jTool.add(jb);
		}
		return jTool;
	}
	
	private JTabbedPane getJtb(){
		if(jtb == null){
			jtb = new JTabbedPane();
			jtb.setTabPlacement(JTabbedPane.BOTTOM);
			jtb.add("View",getViewContentScrollPane());
			jtb.add("Código-Fonte (CTRL+F para executar o editor html)",getHtmlContentScrollPane());
		}
		return jtb;
	}
	
	private JScrollPane getHtmlContentScrollPane() {
		if (htmlContentScrollPane == null) {
			htmlContentScrollPane = new JScrollPane();
			htmlContentScrollPane.setViewportView(htmlContentTextPane);			
		}
		return htmlContentScrollPane;
	}
	
	private JScrollPane viewContentScrollPane;
	private JScrollPane getViewContentScrollPane() {
		if (viewContentScrollPane == null) {
			viewContentScrollPane = new JScrollPane();
			viewContentScrollPane.setViewportView(getViewContentTextPane());
		}
		return viewContentScrollPane;
	}

	private JTextPane viewContentTextPane;
	private JTextPane getViewContentTextPane() {
		if (viewContentTextPane == null) {
			viewContentTextPane = new JTextPane();
			//viewContenttextPane.setTabSize(3);
			final HTMLEditorKit kit = new HTMLEditorKit();
			if(css != null){
				kit.getStyleSheet().addRule(css);
				//StyleSheet ss = new Styl
				//kit.setStyleSheet(s);
			}
			viewContentTextPane.setEditorKit(kit);
			
			viewContentTextPane.addFocusListener(new FocusAdapter(){
				@Override
				public void focusLost(FocusEvent evt) {
					htmlContentTextPane.setText(viewContentTextPane.getText());
				}				
			});
		}
		return viewContentTextPane;
	}
	
	public void updateByHtml(){
		viewContentTextPane.setText(htmlContentTextPane.getText());
	}
	
}
