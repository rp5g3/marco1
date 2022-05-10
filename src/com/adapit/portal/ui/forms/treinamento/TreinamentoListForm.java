package com.adapit.portal.ui.forms.treinamento;



import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.CondicaoPagamento;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.FormaPagamento;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.ScheduledTrainingFilterType;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class TreinamentoListForm extends JPanel {

	private JTabbedPane loteTabbedPane;

	private JPanel statusPanel;  //  @jve:decl-index=0:visual-constraint="115,372"

	private JPanel filterBasePanel;

	private JComboBox statusLoteBaseComboBox;

	private JLabel statusLoteBaseComboBoxLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JComboBox dataLeilaoBaseComboBox;

	private JLabel dataLeilaoBaseComboBoxLabel;

	private JButton consultarBaseButton;

	private JScrollPane baseTableScrollPane;

	private BaseTable baseTable;

	private JPanel dadosButtonsPanel;

	private JButton novoLoteButton;

	private JButton removerLoteButton;

	private JButton editarLoteButton;

	private JButton editarLeilaoLoteButton;

	private JPanel lotesArrematadosPanel;

	private JPanel arrematadosFilterPanel;

	private JComboBox formaPagtoComboBox;

	private JLabel formaPagtoComboBoxLabel;

	private JButton consultarFormPagtoButton;

	private JScrollPane arrematadosTableScrollPane;

	private ArrematadosTable arrematadosTable;

	private JPanel arrematadosbuttonsPanel;

	private JButton efetuarPagtoButton;

	private JButton retirarDoDepositoButton;

	private JButton verDadosContatoCliente;

	private JButton gerarRelatorioPagtoButton;

	private JButton verificarDadosPagtoButton;

	
	private JButton helpButton = null;

	private JCheckBox filtrarSomenteLeilaoCheckBox = null;

	public TreinamentoListForm() {
		initialize();
	}

	private void initialize() {

		ordenarPorLabel = new JLabel();
		ordenarPorLabel.setBounds(new Rectangle(10, 35, 80, 24));
		ordenarPorLabel.setText("Ordenar Por:");
		reportResultsLabel = new JLabel();
		reportResultsLabel.setBounds(new Rectangle(10, 161, 241, 24));
		reportResultsLabel.setText("");
		setSize(new Dimension(692, 485));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getLoteTabbedPane());
		this.add(getDataLeilaoBaseComboBoxLabel(), null);
		this.add(getDataLeilaoBaseComboBox(), null);
		this.add(getEditarLeilaoLoteButton(), null);
		this.add(getFiltrarSomenteLeilaoCheckBox(), null);
		this.add(getRefreshLeiloesButton(), null);
		//this.add(getTodosCheckBox(), null);
		this.add(getRetiradosCheckBox(), null);
		this.add(getArrematadosCheckBox(), null);
		this.add(getComPagamentoQuitadoCheckBox(), null);
		//this.add(getBuscarLeilaoButton(), null);
		this.add(getHabilitarRetiradosButton(), null);
		this.add(getHabilitarArremButton(), null);
		this.add(getHabilitarPagtoQuitadoButton(), null);
		this.add(getDadosButtonsPanel(), null);
		this.add(getBaseTableScrollPane(), null);
		this.add(getHelpButton(), null);
		this.add(getResultNumberPanel(), null);
		this.add(reportResultsLabel, null);
		this.add(ordenarPorLabel, null);
		this.add(getOrdenarPorComboBox(), null);
		updateLeilaoList();
	}
	
	
	private List<TurmaTreinamento> turmas = new ArrayList<TurmaTreinamento>();

	private JButton refreshLeiloesButton = null;

	private JCheckBox retiradosCheckBox = null;

	private JCheckBox arrematadosCheckBox = null;

	private JCheckBox comPagamentoQuitadoCheckBox = null;

	//private JCheckBox todosCheckBox = null;

	//private JButton buscarLeilaoButton = null;

	private JButton habilitarRetiradosButton = null;

	private JButton habilitarArremButton = null;

	private JButton habilitarPagtoQuitadoButton = null;

	private JPanel porDescricaoPanel = null;

	private JPanel listarPorDescricaoPanel = null;

	private JButton listarDescricaoButton = null;

	private JLabel listarDescricaoLabel = null;

	private JTextField descricaoTextField = null; 
	
	@SuppressWarnings("unchecked")
	public void updateLeilaoList(){	
		getDataLeilaoBaseComboBox().removeAllItems();
		turmas.clear();
		try {
			Iterator it = RemoteTurmaService.getInstance().listTrainingClassValues()
					.iterator();

			int i = 0;
			while (it.hasNext()) {
				if (i > 9)
					break;
				Object obj[] = (Object[]) it.next();

				TurmaTreinamento l = new TurmaTreinamento();
				l.setId(((Integer) obj[0]).intValue());
				l.setDataTreinamento((Date) obj[1]);
				turmas.add(l);
				getDataLeilaoBaseComboBox().addItem(
						ScheduledTrainingCadastreForm.formatDataTreinamento(l));
				i++;
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (turmas == null || turmas.size() == 0)
			getDataLeilaoBaseComboBox().setEnabled(false);
		else
			getDataLeilaoBaseComboBox().setEnabled(true);	
	}
/*	public void updateLeilaoList(){	
		getDataLeilaoBaseComboBox().removeAllItems();
		leiloes.clear();
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			Iterator it = s.createQuery("select leilao.id, leilao.dataPresencialPrimeira, leilao.dataOnlinePrimeira from Leilao leilao order by leilao.dataPresencialPrimeira ASC").list().iterator();
			{
				int i=0;
				while(it.hasNext()){
					if (i >9) break;
					Object obj[] = (Object[])it.next();
					
					Leilao l = new Leilao();
					l.setId(((Integer)obj[0]).intValue());
					l.setDataPresencialPrimeira((Date)obj[1]);
					l.setDataOnlinePrimeira((Date)obj[2]);					
					leiloes.add(l);
					getDataLeilaoBaseComboBox().addItem(LoteCadastreForm.formatDataLeilao(l));
					i++;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		if (leiloes == null || leiloes.size() == 0) getDataLeilaoBaseComboBox().setEnabled(false);
		else getDataLeilaoBaseComboBox().setEnabled(true);	
	}*/
	@SuppressWarnings("unchecked")
	protected JTabbedPane getLoteTabbedPane() {

		if (loteTabbedPane == null) {
			loteTabbedPane = new JTabbedPane();
			loteTabbedPane.setSize(new Dimension(676, 97));
			loteTabbedPane.setLocation(new Point(10, 62));
			loteTabbedPane.add(getStatusPanel(),"Listar pelo estado do lote");
			loteTabbedPane.add(getPorDescricaoPanel(), "Listar pela descrição do lote");
			loteTabbedPane.add(getOutrosFiltrosPanel(), "Listar lotes divididos em sub-lotes");
			loteTabbedPane.add(getListarPorProcessoPanel(), "Listar por número do processo");
					
		}
		return loteTabbedPane;
	}

	private Hashtable<Integer,Treinamento> comsublotes = new Hashtable<Integer,Treinamento>();  //  @jve:decl-index=0:
	
	protected JPanel getStatusPanel() {

		if (statusPanel == null) {
			statusPanel = new JPanel();
			statusPanel.setLayout(null);
			statusPanel.add(getFilterBasePanel());
		}
		return statusPanel;
	}

	protected JPanel getFilterBasePanel() {

		if (filterBasePanel == null) {
			filterBasePanel = new JPanel();
			filterBasePanel.setSize(new Dimension(650, 31));
			filterBasePanel.setLocation(new java.awt.Point(10, 5));
			filterBasePanel.setLayout(null);
			filterBasePanel.add(getStatusLoteBaseComboBox(), null);
			filterBasePanel.add(getStatusLoteBaseComboBoxLabel(), null);
			filterBasePanel.add(getConsultarBaseButton(), null);
		}
		return filterBasePanel;
	}

	protected JComboBox getStatusLoteBaseComboBox() {

		if (statusLoteBaseComboBox == null) {
			statusLoteBaseComboBox = new JComboBox();
			statusLoteBaseComboBox.setBounds(new Rectangle(335, 5, 307, 20));
			statusLoteBaseComboBox.addItem("Qualquer um dos estados");
			for (int i=0; i < ScheduledTrainingStatus.values().length;i++){
				statusLoteBaseComboBox.addItem(ScheduledTrainingStatus.values()[i].name().replaceAll("_"," "));
			}
			return statusLoteBaseComboBox;
		}
		return statusLoteBaseComboBox;
	}

	protected JLabel getStatusLoteBaseComboBoxLabel() {

		if (statusLoteBaseComboBoxLabel == null) {
			statusLoteBaseComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EstadodoLote"));
			statusLoteBaseComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
			statusLoteBaseComboBoxLabel.setText("Consultar pelo Estado do Lote:");
			statusLoteBaseComboBoxLabel.setBounds(new Rectangle(132, 4, 194, 20));
		}
		return statusLoteBaseComboBoxLabel;
	}

	protected JComboBox getDataLeilaoBaseComboBox() {

		if (dataLeilaoBaseComboBox == null) {
			dataLeilaoBaseComboBox = new JComboBox();
			dataLeilaoBaseComboBox.setBounds(new Rectangle(280, 5, 235, 24));
			return dataLeilaoBaseComboBox;
		}
		return dataLeilaoBaseComboBox;
	}

	protected JLabel getDataLeilaoBaseComboBoxLabel() {

		if (dataLeilaoBaseComboBoxLabel == null) {
			dataLeilaoBaseComboBoxLabel = new JLabel(/*
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.DatadoLeilão")*/);
			dataLeilaoBaseComboBoxLabel.setText("Data do Leilão:");
			dataLeilaoBaseComboBoxLabel.setBounds(new Rectangle(152, 5, 127, 24));
			dataLeilaoBaseComboBoxLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/date.png")));
			dataLeilaoBaseComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
		}
		return dataLeilaoBaseComboBoxLabel;
	}

	@SuppressWarnings("unchecked")
	private void listar(ScheduledTrainingFilterType filtro, int left) throws Exception{
		if (filtro == null) throw new Exception("O tipo do filtro não pode ser nulo");
		
		int tabSelectedIndex=0;
		boolean filtrarSomenteLeilao=false;
		String statusLote=null;
		String descricaoLote=null;
		String numeroProcesso=null;
		Boolean filtrarRetirados=null;
		Boolean filtrarArrematados=null;
		Boolean filtrarComPagamentoQuitado=null;
		Integer idLeilao=null;
		Integer paiSubLote=null;
		int orderBy=0;
		
		
		if (getFiltrarSomenteLeilaoCheckBox().isSelected()){
			filtrarSomenteLeilao=true;
			idLeilao = new Integer(turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex()).getId());
		}		
		int index = getLoteTabbedPane().getSelectedIndex();
		tabSelectedIndex = index;
		if (index == 0){
			String str = (String) getStatusLoteBaseComboBox().getSelectedItem();
			statusLote = str;
		}else if(index == 1){
			String str = (String) getDescricaoTextField().getText();
			descricaoLote = str;
		}else if(index == 3){
			String str = (String) getNumeroProcessoTextField().getText();
			numeroProcesso=str;
		}
		
		if (getRetiradosCheckBox().isEnabled()){
			if (getRetiradosCheckBox().isSelected()){
				filtrarRetirados = new Boolean(true);
			}else{
				filtrarRetirados = new Boolean(false);
			}
			
			if (getArrematadosCheckBox().isEnabled()){
				if (getArrematadosCheckBox().isSelected()){
					filtrarArrematados = new Boolean(true);
				}else{
					filtrarArrematados = new Boolean(false);
				}
				
				if (getComPagamentoQuitadoCheckBox().isEnabled()){
					if (getComPagamentoQuitadoCheckBox().isSelected()){
						filtrarComPagamentoQuitado = new Boolean(true);
					}else{
						filtrarComPagamentoQuitado = new Boolean(false);
					}
				}
			}
		} else if (getArrematadosCheckBox().isEnabled()){			
			if (getArrematadosCheckBox().isSelected()){
				filtrarArrematados = new Boolean(true);				
			}else{
				filtrarArrematados = new Boolean(false);				
			}
			
			if (getComPagamentoQuitadoCheckBox().isEnabled()){
				if (getComPagamentoQuitadoCheckBox().isSelected()){
					filtrarComPagamentoQuitado = new Boolean(true);
				}else{
					filtrarComPagamentoQuitado = new Boolean(false);
				}
			}
		} else if (getComPagamentoQuitadoCheckBox().isEnabled()){			
			if (getComPagamentoQuitadoCheckBox().isSelected()){
				filtrarComPagamentoQuitado = new Boolean(true);				
			}else{
				filtrarComPagamentoQuitado = new Boolean(false);			
			}
		}
		

		
		if (filtro == ScheduledTrainingFilterType.Lote ){
			if (getOrdenarPorComboBox().getSelectedIndex() == 0)
				orderBy=0;
			if (getOrdenarPorComboBox().getSelectedIndex() == 1)
				orderBy=1;
		}
		
		
		try {
			if (filtro == ScheduledTrainingFilterType.Lote){
				List<Treinamento> lotes = 
					(List<Treinamento>)
					RemoteTreinamentoService.getInstance().filterScheduledTrainingBy(filtro, left,	tabSelectedIndex, 
							filtrarSomenteLeilao,	filtrarRetirados, filtrarArrematados, 
							filtrarComPagamentoQuitado, idLeilao, statusLote, descricaoLote, 
							numeroProcesso,	paiSubLote, orderBy, secBegin);
				getBaseTable().setElements(lotes);
				getBaseTable().updateTable();
			}else if(filtro == ScheduledTrainingFilterType.LoteCount){
				countFirst = (Integer) 
					RemoteTreinamentoService.getInstance().filterScheduledTrainingBy(filtro, left,	tabSelectedIndex, 
						filtrarSomenteLeilao,	filtrarRetirados, filtrarArrematados, 
						filtrarComPagamentoQuitado, idLeilao, statusLote, descricaoLote, 
						numeroProcesso,	paiSubLote, orderBy, secBegin);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
	}

	private Integer countFirst=0;  //  @jve:decl-index=0:
	private Integer countSecound=0;  //  @jve:decl-index=0:
	
	int max=15;

	private JPanel resultNumberPanel = null;


	private JLabel reportResultsLabel = null;
	

	int total;
	int secBegin=0;
	int secCount;
	private void listar(){		
		getBaseTable().setElements(null);
		try {
			listar(ScheduledTrainingFilterType.LoteCount,0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		total = countFirst;
		int number = total/max;
		int rest = total%max;
		if (rest > 0) number++;
		secBegin=0;
		secCount=0;
		reportResultsLabel.setText("Encontrados "+total+" itens.");
		getResultNumberPanel();
		changeResultNumberPanel();
		
		for (int i=0; i < buttons; i++){
			if (i < number){
				JButton jb = new JButton((i+1)+"");
				jb.setToolTipText("lista os registros do intervalor de "+((i)*max+1)+" até "+((i+1)*max));
				jb.setOpaque(true);
				jb.setFont(new Font("Arial", Font.PLAIN, 9));
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());							
							if (countFirst > (i-1)*max){
								listar(ScheduledTrainingFilterType.Lote,i-1);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando  de "+((i)*max+1)+" até "+((i+1)*max));
							}							
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else{
				JLabel jl = new JLabel();
				resultNumberPanel.add(jl);
			}
		}
		
		if (total < max){
			try{
				listar(ScheduledTrainingFilterType.Lote,0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				listar(ScheduledTrainingFilterType.Lote,0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultNumberPanel.updateUI();
	}
	
	
	
	@SuppressWarnings("unused")
	private void listarOld(){
		//listarFirstCount();
		//listarSecoundCount();
		try {
			listar(ScheduledTrainingFilterType.LoteCount,0);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		total = countFirst + countSecound;
		final int number = total/max;
		int rest = total%max;
		secBegin=0;
		secCount=0;
		reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando 1");
		getResultNumberPanel();
		changeResultNumberPanel();
		
		for (int i=0; i < buttons; i++){
			if (i < number){
				JButton jb = new JButton((i+1)+"");
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							int dif = (countFirst - ((i-1)*max));
							if (countFirst > (i-1)*max){
								//listarFirst(i-1);
								listar(ScheduledTrainingFilterType.Lote,i-1);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
							}/*else if (countSecound < (i-1)*max && (((i-1)*max)) - countFirst > 0){
								
								int oldmax = max;
								max = dif;
								listarFirst(i-1);
								max = oldmax - dif;
								secBegin=max;
								listarSecound(0);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
								
								max = oldmax;
							}*/
							else{
								//int dif = ((i-1)*max) - countFirst;
								int oldmax = max;
								max = dif;
								//listarFirst(i-1);
								listar(ScheduledTrainingFilterType.Lote,i-1);
								
								max = oldmax - dif;
								
								//listarSecound((i-1)-(countFirst/oldmax));
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
								
								max = oldmax;
							}
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else if(rest > 0){				
				rest = 0;
				JButton jb = new JButton((i+1)+"");
				jb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent evt) {
						try {
							JButton bt = (JButton) evt.getSource();
							Integer i = Integer.parseInt(bt.getText());
							if (countSecound < (i-1)*max && countFirst > (i-1)*max){
								int dif = countFirst - ((i-1)*max);
								int oldmax = max;
								max = dif;
								//listarFirst(i-1);
								listar(ScheduledTrainingFilterType.Lote,i-1);
								
								max = oldmax - dif;
								secBegin=max;
								//listarSecound(0);
								
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
								
								max = oldmax;
							}else if(countSecound < (i-1)*max){							
								//listarSecound(secCount++);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));							
							}
							/*if (countSecound < max){
								listarSecound(0);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+number);
							}else{
								Integer i = Integer.parseInt(baseTable.getText());
								listarSecound((i-1)-(countFirst/max));
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
							}*/
						} catch (NumberFormatException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}				
				});
				resultNumberPanel.add(jb);
			}else{
				JLabel jl = new JLabel();
				resultNumberPanel.add(jl);
			}
		}
		
		if (total < max){
			//listarFirst(0);
			//listarSecound(0);
			try{
				listar(ScheduledTrainingFilterType.Lote,0);
			
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			//listarFirst(0);
			try {
				listar(ScheduledTrainingFilterType.Lote,0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultNumberPanel.updateUI();
	}

	protected JButton getConsultarBaseButton() {

		if (consultarBaseButton == null) {
			consultarBaseButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Consultar"));
			consultarBaseButton.setBounds(new Rectangle(4, 0, 128, 26));
			consultarBaseButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			consultarBaseButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					listar();
				}
			});
		}
		return consultarBaseButton;
	}

	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setBounds(new Rectangle(10, 187, 676, 185));
			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	protected BaseTable getBaseTable() {

		if (baseTable == null) {
			baseTable = new BaseTable();
			return baseTable;
		}
		return baseTable;
	}

	protected JPanel getDadosButtonsPanel() {

		if (dadosButtonsPanel == null) {
			dadosButtonsPanel = new JPanel();
			dadosButtonsPanel.setLayout(new java.awt.FlowLayout());
			dadosButtonsPanel.setBounds(new Rectangle(10, 375, 676, 97));
			dadosButtonsPanel.add(getNovoLoteButton());
			dadosButtonsPanel.add(getRemoverLoteButton());
			dadosButtonsPanel.add(getEditarLoteButton());
			dadosButtonsPanel.add(getEfetuarPagtoButton());
			dadosButtonsPanel.add(getRetirarDoDepositoButton());
			dadosButtonsPanel.add(getVerDadosContatoCliente());
			dadosButtonsPanel.add(getGerarRelatorioPagtoButton());
			dadosButtonsPanel.add(getVerificarDadosPagtoButton());
		}
		return dadosButtonsPanel;
	}

	protected JButton getNovoLoteButton() {

		if (novoLoteButton == null) {
			novoLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NovoLote"));
			novoLoteButton.setSize(new java.awt.Dimension(150, 20));
			novoLoteButton.setLocation(new java.awt.Point(0, 0));
			novoLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_add.png")));
			novoLoteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//int row = getLotesEmLeilaoList().getSelectedRow();
					//if (row >=0){
						TurmaTreinamento leilao = (TurmaTreinamento)turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex());
						AdapitVirtualFrame.getInstance().novoTreinamento();
						AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(leilao);
						//LeilaoVirtualFrame.getInstance().lastCadastroLoteInternalFrame.setVisible(true);
						AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().novoTreinamento();
					//}
				}
			});
		}
		return novoLoteButton;
	}

	protected JButton getRemoverLoteButton() {

		if (removerLoteButton == null) {
			removerLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.RemoverLote"));
			removerLoteButton.setSize(new java.awt.Dimension(80, 22));
			removerLoteButton.setLocation(new java.awt.Point(0, 20));
			removerLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_delete.png")));
			removerLoteButton
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
						if (l == null) return;
						TurmaTreinamento leilao = (TurmaTreinamento)turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex());
						if (AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame != null){
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(leilao);
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTreinamento(l);
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().getRemoverAction().doAction(e);
							//getBaseTable().getElements().remove(row);
							//getBaseTable().updateTable();
							listar();
						}
						else if (l != null){
							int resp = JOptionPane.showConfirmDialog(TreinamentoListForm.this, "Apagar o lote " + l.getCodigo(),"Apagar Lote",JOptionPane.YES_NO_OPTION);
							if (resp == JOptionPane.YES_OPTION){
								try {
									
									try {
										RemoteTreinamentoService.getInstance().removeScheduledTraining(l, leilao);
										
										JOptionPane.showMessageDialog(TreinamentoListForm.this, "O lote foi removido com sucesso!","Apagar Lote",JOptionPane.INFORMATION_MESSAGE);
										//getBaseTable().getElements().remove(row);
										//getBaseTable().updateTable();
										listar();
									} catch (Exception e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(TreinamentoListForm.this, "O lote não foi removido!","Apagar Lote",JOptionPane.ERROR_MESSAGE);
									}
									/*Session s = LocalServicesUtility.getInstance().openSession();
									try {
										s.beginTransaction();
										
										l = (Lote) s.load(Lote.class,l.getId());
										leilao.getLotes().remove(l);
										
										Iterator<ItemProduto> it = l.getItensProduto().iterator();
										while(it.hasNext()){
											ItemProduto ip = it.next();
											ip.setLote(null);
											s.save(ip);
										}
								
										s.delete(l);
										s.getTransaction().commit();
										
										JOptionPane.showMessageDialog(LoteListForm.this, "O lote foi removido com sucesso!","Apagar Lote",JOptionPane.INFORMATION_MESSAGE);
										//getBaseTable().getElements().remove(row);
										//getBaseTable().updateTable();
										listar();
									} catch (Exception e1) {
										e1.printStackTrace();
										s.getTransaction().rollback();
										JOptionPane.showMessageDialog(LoteListForm.this, "O lote não foi removido!","Apagar Lote",JOptionPane.ERROR_MESSAGE);
									}finally{
										s.close();
									}*/
									
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							}
						}
							
						
						
						//LeilaoVirtualFrame.getInstance().lastCadastroLoteInternalFrame.setVisible(true);
						AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().novoTreinamento();
					}
				}
			});
		}
		return removerLoteButton;
	}

	protected JButton getEditarLoteButton() {

		if (editarLoteButton == null) {
			editarLoteButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.EditarLote"));
			editarLoteButton.setSize(new java.awt.Dimension(80, 22));
			editarLoteButton.setLocation(new java.awt.Point(0, 42));
			editarLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarLoteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
						if (l == null) return;
						TurmaTreinamento leilao = (TurmaTreinamento)turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex());
						if (AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame != null){
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(leilao);
							AdapitVirtualFrame.getInstance().editarTreinamento(l,(String) getDataLeilaoBaseComboBox().getSelectedItem());
						}else{
							AdapitVirtualFrame.getInstance().novoTreinamento();
							AdapitVirtualFrame.getInstance().lastCadastroTreinamentoInternalFrame.getTreinamentoCadastreForm().setSelectedTurma(leilao);
							AdapitVirtualFrame.getInstance().editarTreinamento(l,(String) getDataLeilaoBaseComboBox().getSelectedItem());
						}
						
						//LeilaoVirtualFrame.getInstance().lastCadastroLoteInternalFrame.requestFocus();
					}
				}
			});
		}
		return editarLoteButton;
	}

	protected JButton getEditarLeilaoLoteButton() {

		if (editarLeilaoLoteButton == null) {
			editarLeilaoLoteButton = new JButton("Editar");
			editarLeilaoLoteButton.setBounds(new Rectangle(540, 5, 114, 24));
			editarLeilaoLoteButton.setText("Editar");
			editarLeilaoLoteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_edit.png")));
			editarLeilaoLoteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().editarTurma(turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex()));					
				}
			});
		}
		return editarLeilaoLoteButton;
	}

	protected JPanel getLotesArrematadosPanel() {

		if (lotesArrematadosPanel == null) {
			lotesArrematadosPanel = new JPanel();
			lotesArrematadosPanel.setSize(new java.awt.Dimension(150, 20));
			lotesArrematadosPanel.setLocation(new java.awt.Point(0, 281));
			lotesArrematadosPanel.setLayout(null);
			lotesArrematadosPanel.add(getArrematadosFilterPanel());
			lotesArrematadosPanel.add(getArrematadosTableScrollPane());
			lotesArrematadosPanel.add(getArrematadosbuttonsPanel());
		}
		return lotesArrematadosPanel;
	}

	protected JPanel getArrematadosFilterPanel() {

		if (arrematadosFilterPanel == null) {
			arrematadosFilterPanel = new JPanel();
			arrematadosFilterPanel.setSize(new Dimension(650, 31));
			arrematadosFilterPanel.setLocation(new java.awt.Point(10, 13));
			arrematadosFilterPanel.setLayout(null);
			arrematadosFilterPanel.add(getConsultarFormPagtoButton());
			arrematadosFilterPanel.add(getFormaPagtoComboBox(), null);
			arrematadosFilterPanel.add(getFormaPagtoComboBoxLabel(), null);
		}
		return arrematadosFilterPanel;
	}

	protected JComboBox getFormaPagtoComboBox() {

		if (formaPagtoComboBox == null) {
			formaPagtoComboBox = new JComboBox();
			formaPagtoComboBox.setBounds(new Rectangle(335, 5, 241, 22));
			formaPagtoComboBox.addItem("Todas");
			for (int i=0; i < FormaPagamento.values().length;i++){
				formaPagtoComboBox.addItem(FormaPagamento.values()[i].name().replaceAll("_"," "));
			}
			return formaPagtoComboBox;
		}
		return formaPagtoComboBox;
	}

	protected JLabel getFormaPagtoComboBoxLabel() {

		if (formaPagtoComboBoxLabel == null) {
			formaPagtoComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.FormadePagamento"));
			formaPagtoComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
			formaPagtoComboBoxLabel.setBounds(new Rectangle(219, 5, 110, 22));
		}
		return formaPagtoComboBoxLabel;
	}

	/*protected JPanel getPagoRadioButtonsPanel() {

		if (pagoRadioButtonsPanel == null) {
			pagoRadioButtonsPanel.add(getAmbosPagtoRadioButton());
			pagoRadioButtonsPanel.add(getPagosRadioButton());
			pagoRadioButtonsPanel.add(getNaoPagosRadioButton());
			
			ButtonGroup bg = new ButtonGroup();
			bg.add(getAmbosPagtoRadioButton());
			bg.add(getPagosRadioButton());
			bg.add(getNaoPagosRadioButton());
		}
		return pagoRadioButtonsPanel;
	}*/

	protected JButton getConsultarFormPagtoButton() {

		if (consultarFormPagtoButton == null) {
			consultarFormPagtoButton = new JButton(
					messages
							.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Consultar"));
			consultarFormPagtoButton.setSize(new Dimension(110, 24));
			consultarFormPagtoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			consultarFormPagtoButton.setLocation(new Point(2, 5));
			consultarFormPagtoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					getArrematadosTable().updateElements();
					getArrematadosTable().updateTable();
				}
			});
			
		}
		return consultarFormPagtoButton;
	}

	protected JScrollPane getArrematadosTableScrollPane() {

		if (arrematadosTableScrollPane == null) {
			arrematadosTableScrollPane = new JScrollPane();
			arrematadosTableScrollPane
					.setSize(new Dimension(650, 230));
			arrematadosTableScrollPane.setLocation(new Point(10, 46));

			arrematadosTableScrollPane.add(getArrematadosTable());
			arrematadosTableScrollPane.setViewportView(getArrematadosTable());
		}
		return arrematadosTableScrollPane;
	}

	protected ArrematadosTable getArrematadosTable() {

		if (arrematadosTable == null) {
			arrematadosTable = new ArrematadosTable();
			return arrematadosTable;
		}
		return arrematadosTable;
	}

	protected JPanel getArrematadosbuttonsPanel() {

		if (arrematadosbuttonsPanel == null) {
			arrematadosbuttonsPanel = new JPanel();
			arrematadosbuttonsPanel.setSize(new Dimension(650, 94));
			arrematadosbuttonsPanel.setLocation(new java.awt.Point(10, 279));
			arrematadosbuttonsPanel.setLayout(new java.awt.FlowLayout());
			/*arrematadosbuttonsPanel.add(getEfetuarPagtoButton());
			arrematadosbuttonsPanel.add(getRetirarDoDepositoButton());
			arrematadosbuttonsPanel.add(getVerDadosContatoCliente());
			arrematadosbuttonsPanel.add(getGerarRelatorioPagtoButton());
			arrematadosbuttonsPanel.add(getVerificarDadosPagtoButton());
			arrematadosbuttonsPanel.add(getListarLotesPagtoAtrazoButton());*/
		}
		return arrematadosbuttonsPanel;
	}

	protected JButton getEfetuarPagtoButton() {

		if (efetuarPagtoButton == null) {
			efetuarPagtoButton = new JButton();
			efetuarPagtoButton.setSize(new java.awt.Dimension(150, 20));
			efetuarPagtoButton.setText("Quitar");
			efetuarPagtoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
			efetuarPagtoButton.setLocation(new java.awt.Point(0, 0));
			efetuarPagtoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
						if (l == null) return;
						int resp = JOptionPane.showConfirmDialog(TreinamentoListForm.this, "Quitar todas as contas do lote " + l.getCodigo(),"Quitar Dívidas de Lote",JOptionPane.YES_NO_OPTION);
						if (resp == JOptionPane.NO_OPTION){
							return;
						}
						try {
							/*RemoteTreinamentoService.getInstance()
								.updateTreinamentoPagtoQuitado(l.getId(), true);*/
							AdapitVirtualFrame.getInstance().showOperationSucess("Atualização de Lote", 
										"Todas as contas respectivas ao lote foram quitadas."+'\n'+"O lote foi setado com o pagamento quitado");
							listar();
						} catch(org.hibernate.exception.SQLGrammarException e){
							e.printStackTrace();
							AdapitVirtualFrame.getInstance().showErrorDialog("Problemas ao Quitar o Lote", "Não existem contas a pagar para quitar a venda do lote");
						}catch (Exception e) {
							e.printStackTrace();
						}
						/*Session s = null;
						
						try {
							s = LocalServicesUtility.getInstance().openSession();
							s.beginTransaction();
							
							String updateLote="update Lote lote set lote.pagtoQuitado=true where lote.id=:id";
							s.createQuery(updateLote).setParameter("id",l.getId()).executeUpdate();
							
							s.getTransaction().commit();
							LeilaoVirtualFrame.getInstance().showOperationSucess("Atualização de Lote", 
										"Todas as contas respectivas ao lote foram quitadas."+'\n'+"O lote foi setado com o pagamento quitado");
							listar();
						} catch(org.hibernate.exception.SQLGrammarException e){
							e.printStackTrace();
							LeilaoVirtualFrame.getInstance().showErrorDialog("Problemas ao Quitar o Lote", "Não existem contas a pagar para quitar a venda do lote");
						}catch (Exception e) {
							e.printStackTrace();
						}finally{
							if (s != null && s.isOpen()) s.close();
						}*/
					}
				}
			});
		}
		return efetuarPagtoButton;
	}

	protected JButton getRetirarDoDepositoButton() {

		if (retirarDoDepositoButton == null) {
			retirarDoDepositoButton = new JButton();
			retirarDoDepositoButton.setSize(new java.awt.Dimension(150, 20));
			retirarDoDepositoButton.setText("Retirar do Depósito");
			retirarDoDepositoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/accept.png")));
			retirarDoDepositoButton.setLocation(new java.awt.Point(0, 20));
			retirarDoDepositoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
						if (l == null) return;
						int resp = JOptionPane.showConfirmDialog(TreinamentoListForm.this, "Retirar o lote " + l.getCodigo()+" do depósito","Retirada de Bens",JOptionPane.YES_NO_OPTION);
						if (resp == JOptionPane.NO_OPTION){
							return;
						}
						try {
							/*RemoteTreinamentoService.getInstance().updateLoteRetiradoDeposito(l.getId(), true);
							*/AdapitVirtualFrame.getInstance().showOperationSucess("Atualização de Lote", 
										"O lote foi retirado do depósito");
							listar();
						} catch (Exception e) {
							e.printStackTrace();
						}
						/*Session s = null;
						
						try {
							s = LocalServicesUtility.getInstance().openSession();
							s.beginTransaction();
							
							String updateLote="update Lote lote set lote.retiradoDeposito=true where lote.id=:id";
							s.createQuery(updateLote).setParameter("id",l.getId()).executeUpdate();
							
							s.getTransaction().commit();
							LeilaoVirtualFrame.getInstance().showOperationSucess("Atualização de Lote", 
										"O lote foi retirado do depósito");
							listar();
						} catch (Exception e) {
							e.printStackTrace();
						}finally{
							if (s != null && s.isOpen()) s.close();
						}*/
					}
				}
			});
		}
		return retirarDoDepositoButton;
	}

	protected JButton getVerDadosContatoCliente() {

		if (verDadosContatoCliente == null) {
			verDadosContatoCliente = new JButton();
			verDadosContatoCliente.setSize(new java.awt.Dimension(150, 20));
			verDadosContatoCliente.setText("Editar Arrematante");
			verDadosContatoCliente.setIcon(new ImageIcon(getClass().getResource("/imgs/user_go.png")));
			verDadosContatoCliente.setLocation(new java.awt.Point(0, 40));
			verDadosContatoCliente.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int row = getBaseTable().getSelectedRow();
					if (row >=0){
						Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
						if (l == null) return;
						Participante arrematante = getArrematante(l);
						if (arrematante != null ){
							if (arrematante.getTipoPessoa() instanceof Fisica)
								AdapitVirtualFrame.getInstance().editarParticipante(((Participante)arrematante).getUser(),PersonType.Fisica);
							else AdapitVirtualFrame.getInstance().editarParticipante(((Participante)arrematante).getUser(),PersonType.Juridica);
						}else{
							AdapitVirtualFrame.getInstance().showWarningDialog("Editar Arrematante", "Lote sem arrematante");
						}
					}
				}
			});
		}
		return verDadosContatoCliente;
	}
	
	private Participante getArrematante(Treinamento l){
		try {
			return RemoteTreinamentoService.getInstance().getAuthorByScheduledTrainingId(l.getId());
		} catch (Exception e1) {
			e1.printStackTrace();			
		}
		return null;
	}

	/*private Participante getArrematante(Lote l){
		Session s =null;
		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Long id = (Long) s.createQuery("select lote.comprador.id from Lote lote where lote.id="+l.getId()).uniqueResult();
			if (id == null) return null;
			Participante obj = (Participante) s.load(Participante.class, id);
			if (obj != null){
				
				((Participante)obj).getTipoPessoa();
				((Participante)obj).getUser();
				return (Participante) obj;
			}
			
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}finally{
			if (s != null) s.close();
		}
		return null;
	}
*/
	protected JButton getGerarRelatorioPagtoButton() {

		if (gerarRelatorioPagtoButton == null) {
			gerarRelatorioPagtoButton = new JButton("Fechamento do Lote");
			gerarRelatorioPagtoButton.setSize(new java.awt.Dimension(150, 20));
			gerarRelatorioPagtoButton.setEnabled(true);
			gerarRelatorioPagtoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/fechamento.png")));
			gerarRelatorioPagtoButton.setLocation(new java.awt.Point(0, 60));
			gerarRelatorioPagtoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int row = getBaseTable().getSelectedRow();
							if (row >=0){
								Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
								if (l == null) return;
								TurmaTreinamento leilao = (TurmaTreinamento)turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex());
								if (AdapitVirtualFrame.getInstance().fechamentoTreinamentoInternalFrame != null){
									AdapitVirtualFrame.getInstance().fechamentoTreinamentoInternalFrame.getFechamentoTreinamentoForm().setSelectedLeilao(leilao);
									AdapitVirtualFrame.getInstance().editarFechamentoTreinamento(l,(String) getDataLeilaoBaseComboBox().getSelectedItem());
								}else{
									AdapitVirtualFrame.getInstance().editarFechamentoTreinamento();
									AdapitVirtualFrame.getInstance().fechamentoTreinamentoInternalFrame.getFechamentoTreinamentoForm().setSelectedLeilao(leilao);
									AdapitVirtualFrame.getInstance().editarFechamentoTreinamento(l,(String) getDataLeilaoBaseComboBox().getSelectedItem());
								}
								
								//LeilaoVirtualFrame.getInstance().lastCadastroLoteInternalFrame.requestFocus();
							}
						}
					});
		}
		return gerarRelatorioPagtoButton;
	}

	protected JButton getVerificarDadosPagtoButton() {

		if (verificarDadosPagtoButton == null) {
			verificarDadosPagtoButton = new JButton("Pagamentos do Lote");
			verificarDadosPagtoButton.setSize(new java.awt.Dimension(150, 20));
			verificarDadosPagtoButton.setEnabled(true);
			verificarDadosPagtoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/contas.png")));
			verificarDadosPagtoButton.setLocation(new java.awt.Point(0, 80));
			verificarDadosPagtoButton
					.addActionListener(new java.awt.event.ActionListener() {
						public void actionPerformed(java.awt.event.ActionEvent e) {
							int row = getBaseTable().getSelectedRow();
							if (row >=0){
								Treinamento l = (Treinamento) getBaseTable().getElements().get(row);
								if (l == null) return;
								TurmaTreinamento leilao = (TurmaTreinamento)turmas.get(getDataLeilaoBaseComboBox().getSelectedIndex());
								if (AdapitVirtualFrame.getInstance().contaPagarLoteInternalFrame != null){
									AdapitVirtualFrame.getInstance().contaPagarLoteInternalFrame.getParticipanteContaPagarLoteForm().setSelectedLeilao(leilao);
									AdapitVirtualFrame.getInstance().editarContaPagarLote(l,(String) getDataLeilaoBaseComboBox().getSelectedItem());
								}else{
									AdapitVirtualFrame.getInstance().editarContaPagarLote();
									AdapitVirtualFrame.getInstance().contaPagarLoteInternalFrame.getParticipanteContaPagarLoteForm().setSelectedLeilao(leilao);
									AdapitVirtualFrame.getInstance().editarContaPagarLote(l,(String) getDataLeilaoBaseComboBox().getSelectedItem());
								}
								
								//LeilaoVirtualFrame.getInstance().lastCadastroLoteInternalFrame.requestFocus();
							}
						}
					});
		}
		return verificarDadosPagtoButton;
	}

	/**
	 * This method initializes helpButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/helpicon.png",16,16));
			helpButton.setBounds(new Rectangle(660, 5, 24, 24));
		}
		return helpButton;
	}

	/**
	 * This method initializes filtrarSomenteLeilaoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getFiltrarSomenteLeilaoCheckBox() {
		if (filtrarSomenteLeilaoCheckBox == null) {
			filtrarSomenteLeilaoCheckBox = new JCheckBox();
			filtrarSomenteLeilaoCheckBox.setBounds(new Rectangle(10, 5, 142, 24));
			filtrarSomenteLeilaoCheckBox.setText("Filtrar lotes pelo leilão");
			filtrarSomenteLeilaoCheckBox.setSelected(true);
		}
		return filtrarSomenteLeilaoCheckBox;
	}

	/**
	 * This method initializes refreshLeiloesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshLeiloesButton() {
		if (refreshLeiloesButton == null) {
			refreshLeiloesButton = new JButton();
			refreshLeiloesButton.setBounds(new Rectangle(515, 5, 26, 24));
			refreshLeiloesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshLeiloesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateLeilaoList();
				}
			});
			
		}
		return refreshLeiloesButton;
	}

	/**
	 * This method initializes retiradosCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getRetiradosCheckBox() {
		if (retiradosCheckBox == null) {
			retiradosCheckBox = new JCheckBox();
			retiradosCheckBox.setText("Retirados");
			retiradosCheckBox.setEnabled(false);
			retiradosCheckBox.setBounds(new Rectangle(280, 35, 77, 22));
		}
		return retiradosCheckBox;
	}

	/**
	 * This method initializes arrematadosCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getArrematadosCheckBox() {
		if (arrematadosCheckBox == null) {
			arrematadosCheckBox = new JCheckBox();
			arrematadosCheckBox.setText("Arrematados");
			arrematadosCheckBox.setEnabled(false);
			arrematadosCheckBox.setBounds(new Rectangle(398, 35, 92, 22));
		}
		return arrematadosCheckBox;
	}

	/**
	 * This method initializes comPagamentoQuitadoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getComPagamentoQuitadoCheckBox() {
		if (comPagamentoQuitadoCheckBox == null) {
			comPagamentoQuitadoCheckBox = new JCheckBox();
			comPagamentoQuitadoCheckBox.setText("Com pagamentos quitados");
			comPagamentoQuitadoCheckBox.setBounds(new Rectangle(524, 35, 183, 22));
			comPagamentoQuitadoCheckBox.setEnabled(false);
		}
		return comPagamentoQuitadoCheckBox;
	}

	/**
	 * This method initializes todosCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	/*private JCheckBox getTodosCheckBox() {
		if (todosCheckBox == null) {
			todosCheckBox = new JCheckBox();
			todosCheckBox.setText("Ignorar as seguintes opções de consulta:");
			todosCheckBox.setBounds(new Rectangle(10, 38, 150, 21));
			todosCheckBox.setSelected(true);
			todosCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					if (e.getStateChange() == ItemEvent.SELECTED){
						getRetiradosCheckBox().setEnabled(false);
						getArrematadosCheckBox().setEnabled(false);
						getComPagamentoQuitadoCheckBox().setEnabled(false);
					}else{
						getRetiradosCheckBox().setEnabled(true);
						getArrematadosCheckBox().setEnabled(true);
						getComPagamentoQuitadoCheckBox().setEnabled(true);
					}
				}
			});
		}
		return todosCheckBox;
	}*/

	/**
	 * This method initializes buscarLeilaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/*private JButton getBuscarLeilaoButton() {
		if (buscarLeilaoButton == null) {
			buscarLeilaoButton = new JButton();
			buscarLeilaoButton.setBounds(new Rectangle(582, 10, 104, 22));
			buscarLeilaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_magnify.png")));
			buscarLeilaoButton.setEnabled(false);
			buscarLeilaoButton.setText("Encontrar");
		}
		return buscarLeilaoButton;
	}*/

	/**
	 * This method initializes habilitarRetiradosButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHabilitarRetiradosButton() {
		if (habilitarRetiradosButton == null) {
			habilitarRetiradosButton = new JButton();
			habilitarRetiradosButton.setBounds(new Rectangle(253, 34, 26, 26));
			habilitarRetiradosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
			habilitarRetiradosButton.setText("");
			habilitarRetiradosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!getRetiradosCheckBox().isEnabled()){
						getRetiradosCheckBox().setEnabled(true);
						habilitarRetiradosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_delete.png")));
					}else{
						getRetiradosCheckBox().setEnabled(false);
						habilitarRetiradosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
					}
					
				}
			});
		}
		return habilitarRetiradosButton;
	}

	/**
	 * This method initializes habilitarArremButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHabilitarArremButton() {
		if (habilitarArremButton == null) {
			habilitarArremButton = new JButton();
			habilitarArremButton.setBounds(new Rectangle(371, 34, 26, 26));
			habilitarArremButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
			habilitarArremButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!getArrematadosCheckBox().isEnabled()){
						getArrematadosCheckBox().setEnabled(true);
						habilitarArremButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_delete.png")));
					}else{
						getArrematadosCheckBox().setEnabled(false);
						habilitarArremButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
					}
					
				}
			});
		}
		return habilitarArremButton;
	}

	/**
	 * This method initializes habilitarPagtoQuitadoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getHabilitarPagtoQuitadoButton() {
		if (habilitarPagtoQuitadoButton == null) {
			habilitarPagtoQuitadoButton = new JButton();
			habilitarPagtoQuitadoButton.setBounds(new Rectangle(498, 34, 26, 26));
			habilitarPagtoQuitadoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
			habilitarPagtoQuitadoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (!getComPagamentoQuitadoCheckBox().isEnabled()){
						getComPagamentoQuitadoCheckBox().setEnabled(true);
						habilitarPagtoQuitadoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_delete.png")));
					}else{
						getComPagamentoQuitadoCheckBox().setEnabled(false);
						habilitarPagtoQuitadoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/table_add.png")));
					}
					
				}
			});
		}
		return habilitarPagtoQuitadoButton;
	}

	/**
	 * This method initializes porDescricaoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPorDescricaoPanel() {
		if (porDescricaoPanel == null) {
			porDescricaoPanel = new JPanel();
			porDescricaoPanel.setLayout(null);
			porDescricaoPanel.add(getListarPorDescricaoPanel(), null);
		}
		return porDescricaoPanel;
	}

	/**
	 * This method initializes listarPorDescricaoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getListarPorDescricaoPanel() {
		if (listarPorDescricaoPanel == null) {
			listarDescricaoLabel = new JLabel();
			listarDescricaoLabel.setBounds(new Rectangle(131, 4, 152, 20));
			listarDescricaoLabel.setText("Consultar pela descrição:");
			listarDescricaoLabel.setHorizontalAlignment(JLabel.RIGHT);
			listarPorDescricaoPanel = new JPanel();
			listarPorDescricaoPanel.setLayout(null);
			listarPorDescricaoPanel.setSize(new Dimension(650, 31));
			listarPorDescricaoPanel.setLocation(new java.awt.Point(10, 5));
			listarPorDescricaoPanel.add(getListarDescricaoButton(), null);
			listarPorDescricaoPanel.add(listarDescricaoLabel, null);
			listarPorDescricaoPanel.add(getDescricaoTextField(), null);
		}
		return listarPorDescricaoPanel;
	}

	/**
	 * This method initializes listarDescricaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarDescricaoButton() {
		if (listarDescricaoButton == null) {
			listarDescricaoButton = new JButton("Consultar");
			listarDescricaoButton.setBounds(new Rectangle(4, 0, 128, 26));
			listarDescricaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			listarDescricaoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					listar();
				}
			});
		}
		return listarDescricaoButton;
	}

	/**
	 * This method initializes descricaoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDescricaoTextField() {
		if (descricaoTextField == null) {
			descricaoTextField = new JTextField();
			descricaoTextField.setBounds(new Rectangle(289, 5, 351, 20));
			descricaoTextField.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					listar();
				}
			});
		}
		return descricaoTextField;
	}

	/**
	 * This method initializes resultNumberPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getResultNumberPanel() {
		if (resultNumberPanel == null) {
			resultNumberPanel = new JPanel();
			GridLayout g = new GridLayout(1,20);
			g.setHgap(1);
			g.setVgap(1);
			resultNumberPanel.setLayout(g);
			resultNumberPanel.setBounds(new Rectangle(253, 161, 433, 24));
			
		}
		return resultNumberPanel;
	}
	
	int buttons=12;

	private JLabel ordenarPorLabel = null;

	private JComboBox ordenarPorComboBox = null;

	private JPanel outrosFiltrosPanel = null;

	private JButton listarSublotesButton = null;

	private JTextField numeroProcessoTextField = null;

	private JButton buscarProcessoButton = null;

	private JComboBox comSublotesComboBox = null;

	private JPanel listarPorProcessoPanel = null;
	
	private void changeResultNumberPanel() {
		resultNumberPanel.removeAll();
		GridLayout g = new GridLayout(1, buttons);
		g.setHgap(1);
		g.setVgap(1);
		resultNumberPanel.setLayout(g);

	}

	/**
	 * This method initializes listCountButton
	 * 
	 * @return javax.swing.JButton
	 */
	/*private JButton getListCountButton() {
		if (listCountButton == null) {
			listCountButton = new JButton();
			listCountButton.setText("1");
			listCountButton.setOpaque(true);
			
			listCountButton.setFont(new Font("Arial", Font.PLAIN, 8));
			listCountButton.setSize(new Dimension(23, 23));
		}
		return listCountButton;
	}*/

	/**
	 * This method initializes ordenarPorComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getOrdenarPorComboBox() {
		if (ordenarPorComboBox == null) {
			ordenarPorComboBox = new JComboBox();
			ordenarPorComboBox.setBounds(new Rectangle(94, 35, 150, 24));
			ordenarPorComboBox.addItem("Código do Lote");
			ordenarPorComboBox.addItem("Estado do Lote");
		}
		return ordenarPorComboBox;
	}

	/**
	 * This method initializes outrosFiltrosPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getOutrosFiltrosPanel() {
		if (outrosFiltrosPanel == null) {
			outrosFiltrosPanel = new JPanel();
			outrosFiltrosPanel.setLayout(null);
			
			outrosFiltrosPanel.add(getComSublotesComboBox(), null);
			
		}
		return outrosFiltrosPanel;
	}



	/**
	 * This method initializes numeroProcessoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNumeroProcessoTextField() {
		if (numeroProcessoTextField == null) {
			numeroProcessoTextField = new JTextField();
			numeroProcessoTextField.setBounds(new Rectangle(332, 5, 256, 24));
		}
		return numeroProcessoTextField;
	}

	/**
	 * This method initializes buscarProcessoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBuscarProcessoButton() {
		if (buscarProcessoButton == null) {
			buscarProcessoButton = new JButton();
			buscarProcessoButton.setBounds(new Rectangle(72, 5, 250, 26));
			buscarProcessoButton.setText("Listar pelo número do processo");
			buscarProcessoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.gif")));
			buscarProcessoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					listar();
				}
			});
		}
		return buscarProcessoButton;
	}

	/**
	 * This method initializes comSubprocessosComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getComSublotesComboBox() {
		if (comSublotesComboBox == null) {
			comSublotesComboBox = new JComboBox();
			comSublotesComboBox.setBounds(new Rectangle(371, 7, 114, 22));
		}
		return comSublotesComboBox;
	}

	/**
	 * This method initializes listarPorProcessoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getListarPorProcessoPanel() {
		if (listarPorProcessoPanel == null) {
			listarPorProcessoPanel = new JPanel();
			listarPorProcessoPanel.setLayout(null);
			listarPorProcessoPanel.add(getNumeroProcessoTextField(), null);
			listarPorProcessoPanel.add(getBuscarProcessoButton(), null);
			
		}
		return listarPorProcessoPanel;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(692, 450));
				gui.add(new TreinamentoListForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unchecked")
	private class ArrematadosTable extends JTable {

		private List<Object[]> objects;
		private List<Treinamento> elements = new ArrayList();

		public void setObjects(List objects) {
			this.objects = objects;
		}

		public List<Object[]> getObjects() {
			return this.objects;
		}

		public ArrematadosTable() {

			super();
			//this.setModel(new ArrematadosTableModel(null));
			this.addPropertyChangeListener(new ArrematadosTablePropertyChangeListener());
			updateTable();
			/*Highlighter highlighters = new AlternateRowHighlighter();
			setHighlighters(highlighters);*/
		}

		
		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}
		
		public void updateElements(){
			/*try {
				if(getFiltrarSomenteLeilaoCheckBox().isSelected())
				objects = RemoteTreinamentoService.getInstance().listArrematados(true, 
						leiloes.get(dataLeilaoBaseComboBox.getSelectedIndex()).getId());
				else
					objects = RemoteTreinamentoService.getInstance().listArrematados(false, null);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
			
		}

		public void updateTable() {

			if (objects != null && objects.size() > 0) {
				int ne = objects.size();
				java.util.Iterator<Object[]> it = objects.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				elements.clear();
				while (it.hasNext()) {
					Object[] objs = it.next();
					if (objs != null) {
						Treinamento l = new Treinamento();
						l.setId((Integer)objs[0]);
						l.setCodigo((String)objs[1]);
						
						Participante comprador = new Participante();
						comprador.setId((Long)objs[3]);
						comprador.setNome((String)objs[4]);
						elements.add(l);
						
						CondicaoPagamento cond = (CondicaoPagamento) objs[6];
						
						values[i][0] = l.getCodigo();
						//values[i][1] = l.getAutor().getNome();
						values[i][2] = cond.getTipo().name().replace("_", " ");
						values[i][3] = (Float) objs[5];
						
						String strcond="";
						if (cond.getCarenciaMeses() > 0) strcond+=" Carência: "+cond.getCarenciaMeses()+" mêses";
						if (cond.getNumeroEntradas() > 0) strcond+=" Entradas: "+cond.getNumeroEntradas();
						if (cond.getNumeroPrestacoes() > 0) strcond+=" Prestações: " + cond.getNumeroPrestacoes();
						values[i][4] = strcond;
						
						
						
						i++;
					}
				}// End of while Loop
				setModel(new ArrematadosTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(80);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				getColumnModel().getColumn(2).setPreferredWidth(150);
				getColumnModel().getColumn(3).setPreferredWidth(80);
				getColumnModel().getColumn(4).setPreferredWidth(150);
				getColumnModel().getColumn(5).setPreferredWidth(60);
				updateUI();
			} else {
				setModel(new ArrematadosTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(80);
				getColumnModel().getColumn(1).setPreferredWidth(150);
				getColumnModel().getColumn(2).setPreferredWidth(150);
				getColumnModel().getColumn(3).setPreferredWidth(80);
				getColumnModel().getColumn(4).setPreferredWidth(150);
				getColumnModel().getColumn(5).setPreferredWidth(60);
				updateUI();
			}
		}

		private class ArrematadosTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.treinamento.TreinamentoListForm.ArrematadosTable jt = (com.adapit.portal.ui.forms.treinamento.TreinamentoListForm.ArrematadosTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.Treinamento) {
							com.adapit.portal.entidades.Treinamento lote = (com.adapit.portal.entidades.Treinamento) obj;
							if (col == 0)
								lote.setCodigo(((java.lang.String) jt
										.getValueAt(row, col)));
							/*if (col == 2)
								lote.setComprador(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 3)
								lote.setComprador(((java.lang.String) jt
										.getValueAt(row, col)));
							if (col == 4)
								lote.setComprador(((java.lang.String) jt
										.getValueAt(row, col)));*/
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class ArrematadosTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, Float.class, String.class, Boolean.class };

			boolean canEdit[] = new boolean[] { false, false, false, false, false, false };

			public ArrematadosTableModel(Object[][] values) {

				super(
						values,
						new String[] {"Lote",
								messages
								.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.NomedoArrematante"),
								
								"Forma de Pagamento","Valor Total","Condição de Pagamento","Quitado" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		public List<Treinamento> getElements() {
			return elements;
		}

	}

/*	private class ProdutosTable extends JTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		public ProdutosTable() {

			super();
			this.setModel(new ProdutosTableModel(null));
			this
					.addPropertyChangeListener(new ProdutosTablePropertyChangeListener());
		}

		public ProdutosTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new ProdutosTableModel(null));
			this
					.addPropertyChangeListener(new ProdutosTablePropertyChangeListener());
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][4];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
				}// End of while Loop
				setModel(new ProdutosTableModel(values));
				updateUI();
			} else {
				setModel(new ProdutosTableModel(null));
				updateUI();
			}
		}

		private class ProdutosTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.baseleilao.ui.lote.LoteListForm.ProdutosTable jt = (com.adapit.portal.ui.forms.baseleilao.ui.lote.LoteListForm.ProdutosTable) e
						.getSource();
				int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

		private class ProdutosTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					Integer.class, String.class };

			boolean canEdit[] = new boolean[] { false, false, false, false };

			public ProdutosTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Produto"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Categoria"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.Qtd"),
								messages
										.getMessage("com.adapit.portal.ui.forms.baseleilao.LoteListForm.ValordoItem") });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

	}*/
	@SuppressWarnings("unchecked")
	private class BaseTable extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		@SuppressWarnings("deprecation")
		public BaseTable() {

			super();
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			updateTable();
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		@SuppressWarnings("deprecation")
		public BaseTable(List elements) {

			super();
			this.elements = elements;
			this.setModel(new BaseTableModel(null));
			this.addPropertyChangeListener(new BaseTablePropertyChangeListener());
			updateTable();
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof com.adapit.portal.entidades.Treinamento) {
						com.adapit.portal.entidades.Treinamento lote = (com.adapit.portal.entidades.Treinamento) obj;
						values[i][0] = lote.getCodigo();
						values[i][1] = lote.getStatus().name().replace("_", " ");					
						values[i][5] = (i+1);
						i++;
					}
				}// End of while Loop
				setModel(new BaseTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(60);
				getColumnModel().getColumn(1).setPreferredWidth(250);
				getColumnModel().getColumn(2).setPreferredWidth(60);
				getColumnModel().getColumn(3).setPreferredWidth(60);
				getColumnModel().getColumn(4).setPreferredWidth(100);
				getColumnModel().getColumn(5).setPreferredWidth(8);
				updateUI();
			} else {
				setModel(new BaseTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(60);
				getColumnModel().getColumn(1).setPreferredWidth(250);
				getColumnModel().getColumn(2).setPreferredWidth(60);
				getColumnModel().getColumn(3).setPreferredWidth(60);
				getColumnModel().getColumn(4).setPreferredWidth(100);
				getColumnModel().getColumn(5).setPreferredWidth(8);
				updateUI();
			}
		}

		private class BaseTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,Boolean.class, Boolean.class,Boolean.class,Integer.class};

			boolean canEdit[] = new boolean[] { false, false,false,false,false,false};

			public BaseTableModel(Object[][] values) {

				super(
						values,
						new String[] {
								"Lote","Estado do Lote","Retirado","Arrematado","Pagamento Quitado",""
								 });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class BaseTablePropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e ){

			BaseTable jt = (BaseTable) e.getSource();
			int col = jt.getSelectedColumn();
			int row = jt.getSelectedRow();
			if (jt.getElements() != null && row > -1) try{
				java.lang.Object obj = jt.getElements().get(row);
				if (obj instanceof com.adapit.portal.entidades.Treinamento){
					com.adapit.portal.entidades.Treinamento lote=(com.adapit.portal.entidades.Treinamento) obj;
					if (col ==0) lote.setCodigo(((java.lang.String)jt.getValueAt(row, col)));
					/*if (col ==2) lote.setStatus(((java.lang.String)jt.getValueAt(row, col)));
					if (col ==3) lote.setAgendas((new java.lang.Date((java.lang.String)jt.getValueAt(row, col)));
					if (col ==4) lote.setAgendas((new java.lang.Date((java.lang.String)jt.getValueAt(row, col)));*/
				}
			}catch(java.lang.Exception ex){
				ex.printStackTrace();
			}
	}

		}

	}

}  //  @jve:decl-index=0:visual-constraint="10,10"