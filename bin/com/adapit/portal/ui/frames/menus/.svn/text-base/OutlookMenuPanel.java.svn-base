package com.adapit.portal.ui.frames.menus;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import com.adapit.portal.ui.frames.menus.outlook.AgendaTreinamentoOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.BasicDataOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.FinanceiroOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.InstitucionalOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.SoftwareOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.TreinamentosOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.TurmaOutlookPanel;
import com.adapit.portal.ui.frames.menus.outlook.UsuariosOutlookPanel;
import com.l2fprod.common.swing.JOutlookBar;
import com.l2fprod.common.swing.PercentLayout;

@SuppressWarnings("serial")
public class OutlookMenuPanel extends JPanel {

	public OutlookMenuPanel() {
		super();
		initialize();
	}

	public OutlookMenuPanel(LayoutManager arg0) {
		super(arg0);
		initialize();
	}

	public OutlookMenuPanel(boolean arg0) {
		super(arg0);
		initialize();
	}

	public OutlookMenuPanel(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		initialize();
	}
	
	private void initialize(){
		setLayout(new BorderLayout());
		add(makeOutlookPanel(SwingConstants.LEFT),
				BorderLayout.CENTER);
	}
	
	private JPanel makeOutlookPanel(int alignment) {
		JOutlookBar outlook = new JOutlookBar();
		
		outlook.setTabPlacement(JTabbedPane.LEFT);
		getProdutosPanel(outlook);
		getLeiloesPanel(outlook);
		getTreinamentosPanel(outlook);
		getSoftwarePanel(outlook);
		getLotesPanel(outlook);
		
		getUsuariosPanel(outlook);
		getFinanceiroPanel(outlook);
		getNewsPanel(outlook);
		outlook.setAllTabsAlignment(alignment);

		//outlook.setBackgroundAt(0, new Color(200,200,200));
		//outlook.setBackgroundAt(1, new Color(200,200,200));
		JPanel panel = new JPanel(
				new PercentLayout(PercentLayout.HORIZONTAL, 3));
		panel.add(outlook, "200");
		return panel;
	}
	
	private JPanel newsPanel;

	private JPanel getNewsPanel(JOutlookBar tabs) {
		if (newsPanel == null) {
			newsPanel = new InstitucionalOutlookPanel(tabs);			
		}
		return newsPanel;
	}
	
	
	private JPanel produtosPanel;

	private JPanel getProdutosPanel(JOutlookBar tabs){
		if (produtosPanel == null) {
			produtosPanel= new BasicDataOutlookPanel(tabs);			
		}
		return produtosPanel;
	}
	
	private JPanel treinamentosPanel;

	private JPanel getTreinamentosPanel(JOutlookBar tabs) {
		if (treinamentosPanel == null) {
			treinamentosPanel= new TreinamentosOutlookPanel(tabs);			
		}
		return treinamentosPanel;
	}
	
	private JPanel softwarePanel;

	private JPanel getSoftwarePanel(JOutlookBar tabs) {
		if (softwarePanel == null) {
			softwarePanel= new SoftwareOutlookPanel(tabs);			
		}
		return softwarePanel;
	}
	
	private JPanel agendaTreinamentoPanel;

	private JPanel getLotesPanel(JOutlookBar tabs) {
		if (agendaTreinamentoPanel == null) {
			agendaTreinamentoPanel= new AgendaTreinamentoOutlookPanel(tabs);			
		}
		return agendaTreinamentoPanel;
	}

	private JPanel turmasPanel;

	private JPanel getLeiloesPanel(JOutlookBar tabs) {
		if (turmasPanel == null) {
			turmasPanel = new TurmaOutlookPanel(tabs);			
		}
		return turmasPanel;
	}
	

	
	private JPanel usuariosPanel;

	private JPanel getUsuariosPanel(JOutlookBar tabs) {
		if (usuariosPanel == null) {
			usuariosPanel = new UsuariosOutlookPanel(tabs);			
		}
		return usuariosPanel;
	}
	
	private JPanel financeiroPanel;
	
	private JPanel getFinanceiroPanel(JOutlookBar tabs) {
		if (financeiroPanel == null) {
			financeiroPanel = new FinanceiroOutlookPanel(tabs);			
		}
		return financeiroPanel;
	}
	


}
