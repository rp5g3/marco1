package com.workcase.gui.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import com.sun.pdfview.PDFPage;
import com.sun.pdfview.PDFRenderer;
import com.sun.pdfview.PagePanel;

public class PdfPrintable implements Printable {
	PDFPage pagePdf;
	PagePanel panel;
	public PdfPrintable(PDFPage page,PagePanel panel){
		this.pagePdf = page;
		this.panel = panel;
	}
	
	public int print2(Graphics g, PageFormat pf, int page)throws PrinterException {

		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid
		 * clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());

		/* Now we perform our rendering */
		//g.drawString("Hello world!", 100, 100);
		
		BufferedImage img = new BufferedImage((int) 500/*pagePdf.getWidth()*/,
				(int) 500/*pagePdf.getHeight()*/, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = img.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		PDFRenderer renderer = new PDFRenderer(pagePdf, g2, 
			    new Rectangle(0, 0, 500, 500), null, Color.RED);
		try {
			pagePdf.waitForFinish();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		renderer.run();
		
		/* tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}
	
	public int print(Graphics g, PageFormat pf, int pi) throws PrinterException {
		if (pi >= 1) {
			return Printable.NO_SUCH_PAGE;
		}
		pf.setOrientation(PageFormat.LANDSCAPE);
		Paper p = new Paper();
		p.setSize(1600, 1000);
		pf.setPaper(p);

		//draw((Graphics2D) g,null);
		//paint(g);
		Graphics2D g2 = (Graphics2D) g;

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setClip(0, 0, 1600, 1000);
		g2.setBackground(new Color(255, 255, 255));
		g2.setStroke(new BasicStroke(2.0f));
		
		
		PDFRenderer renderer = new PDFRenderer(pagePdf, g2, 
			    new Rectangle(0, 0, 500, 500), null, Color.RED);
		try {
			pagePdf.waitForFinish();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		renderer.run();
		/*g2.clearRect(0, 0, this.getWidth(), this.getHeight());
		paintElements((FeatureViewComponent) featuresDiagram.getRoot(), g2,
				null);*/

		return Printable.PAGE_EXISTS;
	}
}