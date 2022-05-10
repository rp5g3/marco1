package com.workcase.gui.utils;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import com.sun.pdfview.PDFFile;
import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PagePanel;
import java.awt.Dimension;

public class PrintablePdfDocumentFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private String url;
	/**
	 * This is the default constructor
	 */
	public PrintablePdfDocumentFrame(String url) {
		super();
		this.url=url;
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		
		this.setContentPane(getJContentPane());
				

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(new Dimension(359, 110));
		panel = new PagePanel();
		
		// load a pdf from a byte buffer
		final File file = new File(url);
		RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		FileChannel channel = raf.getChannel();
		ByteBuffer buf = null;
		try {
			buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		PDFFile pdffile = null;
		try {
			pdffile = new PDFFile(buf);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		add(panel,BorderLayout.CENTER);
		add(getBar(), BorderLayout.NORTH);
		
		pack();
		setVisible(true);

		

		// show the first page
		page = pdffile.getPage(0);
		panel.showPage(page);
		
		
	}
	
	private PagePanel panel;
	private PDFPage page;
	private JButton print = null;	
	private JToolBar bar;
	
	private JToolBar getBar(){
		if (bar==null){
			bar = new JToolBar();			
			bar.add(getPrintButton());
		}
		return bar;
	}
	
	private JButton getPrintButton(){
		if (print == null){
			print = new JButton("");
			print.setSize(26, 26);
			print.setLocation(0, 0);
			print.setIcon(new ImageIcon(getClass().getResource("/imgs/printer.png")));
			print.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					// create and configure a graphics object
					/*BufferedImage img = new BufferedImage((int) page.getWidth(),
							(int) page.getHeight(), BufferedImage.TYPE_INT_ARGB);
					Graphics2D g2 = img.createGraphics();
					g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);*/

					/*RandomAccessFile raf = null;
					try {
						raf = new RandomAccessFile(file, "r");
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					FileChannel channel = raf.getChannel();
					ByteBuffer buf = null;
					try {
						buf = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					PDFFile pdffile = null;
					try {
						pdffile = new PDFFile(buf);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// get the first page
					PDFPage page = pdffile.getPage(0);*/

					/*PrinterJob job = PrinterJob.getPrinterJob();
					job.setPrintable(new PdfPrinter(page,panel));
					boolean doPrint = job.printDialog();
					if (doPrint) {
						try {
							job.print();
							System.out.println("imprimiu");
						} catch (PrinterException e) {
							 The job did not successfully complete 
							e.printStackTrace();
						}
					}*/

					PrinterJob printJob = PrinterJob.getPrinterJob();
		        	@SuppressWarnings("unused")
					PageFormat pf = printJob.pageDialog(printJob.defaultPage());
		            printJob.setPrintable(new PdfPrintable(page,panel));
		            if (printJob.printDialog()) {
		                try {
		                    printJob.print();
		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                }
		            }


					// panel.print(g2);
					
				}

			});
			
		}
		return print;
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
		}
		return jContentPane;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
