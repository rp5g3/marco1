package com.workcase.gui.custom;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;

import com.workcase.gui.utils.UIUtil;

@SuppressWarnings("serial")
public class ImageFileChooser extends JFileChooser {

	public ImageFileChooser() {
		initialize();
	}

	public ImageFileChooser(String arg0) {
		super(arg0);
		initialize();
	}

	public ImageFileChooser(File arg0) {
		super(arg0);
		initialize();
	}

	public ImageFileChooser(FileSystemView arg0) {
		super(arg0);
		initialize();
	}

	public ImageFileChooser(File arg0, FileSystemView arg1) {
		super(arg0, arg1);
		initialize();
	}

	public ImageFileChooser(String arg0, FileSystemView arg1) {
		super(arg0, arg1);
		initialize();
	}
	
	private void initialize(){
		setDialogTitle("Escolha a Imagem da Categoria");
		setLocation(UIUtil.getScreenCenter(this));
		ExampleFileFilter filter = null;

		filter = new ExampleFileFilter("gif");
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("png");
		filter.setDescription("Todas as Imagens");
		addChoosableFileFilter(filter);

		LabelAccessory accessory = new LabelAccessory(this);
		setAccessory(accessory);
		addPropertyChangeListener(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, accessory);
		
		FileView view = new ImageFileView();
		setFileView(view);
	}

}
