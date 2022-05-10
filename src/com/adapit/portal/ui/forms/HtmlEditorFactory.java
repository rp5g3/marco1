package com.adapit.portal.ui.forms;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.text.JTextComponent;

import com.hexidec.ekit.EkitDialog;
import com.workcase.gui.utils.UIUtil;

public class HtmlEditorFactory {

	
	private static EkitDialog ekit = null;
	
	public static void formatar(final JTextComponent textPane, Frame parentFrame, String title){
		if (ekit == null){
			String sDocument = null;
			String sStyleSheet = "//style//default.css";
			String sRawDocument = null;
			URL urlStyleSheet = null;
			boolean includeToolBar = true;
			boolean multibar = true;
			boolean includeViewSource = false;
			boolean includeMenuIcons = true;
			boolean modeExclusive = true;
			String sLang = null;
			String sCtry = null;
			boolean base64 = false;
			boolean debugOn = false;
			boolean spellCheck = false;
			
			
			ekit = new EkitDialog(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, includeViewSource, includeMenuIcons, modeExclusive, sLang, sCtry, base64, debugOn, spellCheck, multibar, parentFrame);
			ekit.pack();
			
			
			
			ekit.addWindowListener(new WindowAdapter(){
	
				@Override
				public void windowClosed(WindowEvent evt) {
					textPane.setText(ekit.getSourceText());
				}
				
			});		
		}
		if (textPane.getText() != null)
			ekit.setSourceText(textPane.getText());
		else ekit.setSourceText("");
		
		ekit.setSize(800,600);
		ekit.setLocation(UIUtil.getScreenCenter(ekit));
		ekit.setTitle(title);
		
		ekit.setVisible(true);
		
	}
	
	public static void formatar(final JTextComponent textPane, final JTextComponent sourcePane, Frame parentFrame, String title){
		if (ekit == null){
			String sDocument = null;
			String sStyleSheet = "//style//default.css";
			String sRawDocument = null;
			URL urlStyleSheet = null;
			boolean includeToolBar = true;
			boolean multibar = true;
			boolean includeViewSource = false;
			boolean includeMenuIcons = true;
			boolean modeExclusive = true;
			String sLang = null;
			String sCtry = null;
			boolean base64 = false;
			boolean debugOn = false;
			boolean spellCheck = false;
			
			
			ekit = new EkitDialog(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, includeViewSource, includeMenuIcons, modeExclusive, sLang, sCtry, base64, debugOn, spellCheck, multibar, parentFrame);
			ekit.pack();
			
			
			
			ekit.addWindowListener(new WindowAdapter(){
	
				@Override
				public void windowClosed(WindowEvent evt) {
					textPane.setText(ekit.getSourceText());
					sourcePane.setText(ekit.getSourceText());
				}
				
			});		
		}
		if (textPane.getText() != null)
			ekit.setSourceText(textPane.getText());
		else ekit.setSourceText("");
		
		ekit.setSize(800,600);
		ekit.setLocation(UIUtil.getScreenCenter(ekit));
		ekit.setTitle(title);
		
		ekit.setVisible(true);
		
	}
	
	public static void formatarAsNewEditor(final JTextComponent textPane, Frame parentFrame, String title){
		try{
			String sDocument = null;
			String sStyleSheet = "//style//default.css";
			String sRawDocument = null;
			URL urlStyleSheet = null;
			boolean includeToolBar = true;
			boolean multibar = true;
			boolean includeViewSource = false;
			boolean includeMenuIcons = true;
			boolean modeExclusive = true;
			String sLang = null;
			String sCtry = null;
			boolean base64 = false;
			boolean debugOn = false;
			boolean spellCheck = false;
			
			
			ekit = new EkitDialog(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, includeViewSource, includeMenuIcons, modeExclusive, sLang, sCtry, base64, debugOn, spellCheck, multibar, parentFrame);
			ekit.pack();
			
			
			
			ekit.addWindowListener(new WindowAdapter(){
	
				@Override
				public void windowClosed(WindowEvent evt) {
					textPane.setText(ekit.getSourceText());
				}
				
			});		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if (textPane.getText() != null)
			ekit.setSourceText(textPane.getText());
		else ekit.setSourceText("");
		
		ekit.setSize(800,600);
		ekit.setLocation(UIUtil.getScreenCenter(ekit));
		ekit.setTitle(title);
		
		ekit.setVisible(true);
		
	}
	
	public static void formatarAsNewEditor(final JTextComponent textPane, final JTextComponent sourcePane, Frame parentFrame, String title){
		try{
			String sDocument = null;
			String sStyleSheet = "//style//default.css";
			String sRawDocument = null;
			URL urlStyleSheet = null;
			boolean includeToolBar = true;
			boolean multibar = true;
			boolean includeViewSource = false;
			boolean includeMenuIcons = true;
			boolean modeExclusive = true;
			String sLang = null;
			String sCtry = null;
			boolean base64 = false;
			boolean debugOn = false;
			boolean spellCheck = false;
			
			
			ekit = new EkitDialog(sDocument, sStyleSheet, sRawDocument, urlStyleSheet, includeToolBar, includeViewSource, includeMenuIcons, modeExclusive, sLang, sCtry, base64, debugOn, spellCheck, multibar, parentFrame);
			ekit.pack();
			
			
			
			ekit.addWindowListener(new WindowAdapter(){
	
				@Override
				public void windowClosed(WindowEvent evt) {
					textPane.setText(ekit.getSourceText());
					sourcePane.setText(ekit.getSourceText());
				}
				
			});		
		}catch(Exception ex){
			ex.printStackTrace();
		}
		if (textPane.getText() != null)
			ekit.setSourceText(textPane.getText());
		else ekit.setSourceText("");
		
		ekit.setSize(800,600);
		ekit.setLocation(UIUtil.getScreenCenter(ekit));
		ekit.setTitle(title);
		
		ekit.setVisible(true);
		
	}
}
