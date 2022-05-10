package com.workcase.gui.custom;

import java.io.File;

import javax.swing.Icon;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileView;

public class ImageFileView extends FileView {

	/**
	 * If .java file, add length to name
	 */
	public String getName(File file) {
		String filename = file.getName();
		if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".gif") || filename.endsWith(".png")) {
			filename += " : " + file.length();
			return filename;
		}
		return null;
	}

	/**
	 * Return special icons for .java and .class files
	 */
	public Icon getIcon(File file) {
		// default icons for all directories
		if (file.isDirectory()) {
			return null;
		}
		String filename = file.getName();
		if (filename.endsWith(".gif") || filename.endsWith(".jpg")
				|| filename.endsWith(".jpeg") || filename.endsWith(".png")) {
			return getIcon(file.getAbsolutePath());
		} else
			return null;

	}
	
	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(25, 25,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			// TODO
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			// TODO
			e.printStackTrace();
		}// end of catch block
		return null;
	}

	public static void main(String args[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFileChooser fileChooser = new JFileChooser(".");
				FileView view = new ImageFileView();
				fileChooser.setFileView(view);
				@SuppressWarnings("unused")
				int status = fileChooser.showOpenDialog(null);
				System.exit(0);
			}
		});
	}
}