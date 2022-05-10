package com.adapit.portal.ui.forms.treinamento.encerramento;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.remote.RemoteAgendaTreinamentoService;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.ui.forms.treinamento.ScheduledTrainingCadastreForm;
import com.workcase.gui.AbstractAction;

@SuppressWarnings("serial")
public class FechamentoTreinamentoForm extends JPanel {

	private JPanel numeroLotePanel;

	private JComboBox codigoComboBox;
	
	private Treinamento treinamento = new Treinamento();  //  @jve:decl-index=0:

	private JLabel codigoLabel;

	//private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JComboBox dataTurmaComboBox;

	private JLabel dataTurmaLabel;

	public FechamentoTreinamentoForm() {
		initialize();
	}

	private void initialize() {
		
		setLayout(null);
		add(getNumeroLotePanel());
		add(getAgendaPanel());

		this.setBounds(new Rectangle(0, 0, 605, 464));
		this.add(getDataTurmaLabel(), null);
		this.add(getDataTurmaComboBox(), null);
		this.add(getRefreshLeiloesButton(), null);
		
		updateTurmasList();
		initializeLotes();
	}

	protected JPanel getNumeroLotePanel() {

		if (numeroLotePanel == null) {
			statusLoteLabel = new JLabel();
			statusLoteLabel.setBounds(new Rectangle(3, 29, 206, 20));
			statusLoteLabel.setText("Estado:");
			numeroLotePanel = new JPanel();
			numeroLotePanel.setSize(new Dimension(575, 75));
			numeroLotePanel.setLocation(new Point(15, 30));
			numeroLotePanel.setLayout(null);
			numeroLotePanel.add(getCodigoComboBox());
			numeroLotePanel.add(getRefreshTreinamentosButton(),null);
			numeroLotePanel.add(getEditarLoteButton(), null);

			numeroLotePanel.add(getCodigoLabel(), null);
			numeroLotePanel.add(getDescricaoScrollPane(), null);
			numeroLotePanel.add(statusLoteLabel, null);
		}
		return numeroLotePanel;
	}
	
	private Hashtable<String,Treinamento> lotes;  //  @jve:decl-index=0:

	
	@SuppressWarnings("unchecked")
	public void initializeLotes(){
		getAgendaPanel().setVisible(false);
		if (getDataTurmaComboBox().getItemCount() == 0) return;
		try {
			int idLeilao = turmas.get(getDataTurmaComboBox().getSelectedIndex()).getId();
			List list = RemoteAgendaTreinamentoService.getInstance().listScheduledTrainingToPrepareAccount(idLeilao);
			lotes = new Hashtable<String,Treinamento>();
			codigoComboBox.removeAllItems();
			int i=0;
			if (list != null && list.size() > 0){
				Iterator it = list.iterator();
				while (it.hasNext()){
					Object obj[] = (Object[]) it.next();
					Treinamento l = new Treinamento();
					l.setId((Integer)obj[0]);
					l.setCodigo((String)obj[1]);
					if (i == 0) treinamento = l;
					if (!lotes.containsKey(l.getCodigo())){
						codigoComboBox.addItem(l.getCodigo());
						lotes.put(l.getCodigo(),l);
					}
					i++;
				}				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}		
	}
/*	public void initializeLotes(){
		getAgendaPanel().setVisible(false);
		if (getDataLeilaoComboBox().getItemCount() == 0) return;
		Session s = null;
		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("select lote.id, lote.codLote from Lote lote" +
					" where lote.arrematado=true" +
					" and lote.status<>'"+StatusLote.Não_homologado.name()+"'" +
					" and lote.leilao.id =" + 
					leiloes.get(getDataLeilaoComboBox().getSelectedIndex()).getId()+
					" order by lote.codLote").list();
			List list2 = s.createQuery("select lote.id, lote.codLote from LoteProdutoIndividual lote" +
					" where lote.arrematado=true" +
					" and lote.status<>'"+StatusLote.Não_homologado.name()+"'" +
					" and lote.lote.leilao.id =" + 
					leiloes.get(getDataLeilaoComboBox().getSelectedIndex()).getId()+
					" order by lote.codLote").list();
			if (list != null && list.size() > 0){
				if(list2 != null && list2.size()>0) list.addAll(list2);
			}else{
				if(list2 != null && list2.size()>0) list = list2; 
			}
			
			lotes = new Hashtable<String,Lote>();
			codLoteComboBox.removeAllItems();
			int i=0;
			if (list != null && list.size() > 0){
				Iterator it = list.iterator();
				while (it.hasNext()){
					Object obj[] = (Object[]) it.next();
					Lote l = new Lote();
					l.setId((Integer)obj[0]);
					l.setCodLote((String)obj[1]);
					//l.setStatus((StatusLote)obj[2]);
					if (i == 0) lote = l;
					if (!lotes.containsKey(l.getCodLote())){
						codLoteComboBox.addItem(l.getCodLote());
						lotes.put(l.getCodLote(),l);
					}
					i++;
				}				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		
	}*/
	
	private void codLoteComboEdited(){
		String num = (String) codigoComboBox.getSelectedItem();
		try {
			Treinamento lote = lotes.get(num);			
			if (lote != null){
				FechamentoTreinamentoForm.this.treinamento = lote;
				editarLoteSelecionado();
			}
			else{
				JOptionPane.showMessageDialog(FechamentoTreinamentoForm.this, "O lote de número " + num + " não existe.", "Lote Inexistente", JOptionPane.ERROR_MESSAGE);				
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	}
	
	private boolean refreshingCodLoteComboBox=false;
	
	public JComponent getCodigoComboBox() {

		if (codigoComboBox == null) {
			codigoComboBox = new JComboBox();
			codigoComboBox.setEditable(true);
			codigoComboBox.setSize(new java.awt.Dimension(106, 22));
			codigoComboBox.setLocation(new Point(76, 2));
			
			codigoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED && !refreshingCodLoteComboBox){
						if (lotes != null){
							treinamento = lotes.get(codigoComboBox.getSelectedItem());
							if (treinamento != null) editarLoteSelecionado();
						} 
					}
				}				
			});
			
			
			codigoComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					if (evt.getActionCommand().equalsIgnoreCase("comboBoxEdited")){
						codLoteComboEdited();
					}
				}				
			});
		}
		return codigoComboBox;
	}
	
	public TurmaTreinamento getSelectedLeilao(){
		try {
			int idLeilao = ((TurmaTreinamento)turmas.get(getDataTurmaComboBox().getSelectedIndex())).getId();
			TurmaTreinamento l = RemoteTurmaService.getInstance().loadTrainingClassByTrainingClassId(idLeilao);
			return l;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
/*	public Leilao getSelectedLeilao(){
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			return (Leilao) s.load(Leilao.class, ((Leilao)leiloes.get(getDataLeilaoComboBox().getSelectedIndex())).getId());
			
			//s.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		return null;
	}*/

	public void editRegister(Treinamento objLote) {
		// Nunca passar como argumento novos objetos!!!
		getAgendaPanel().setVisible(true);

		if (this.treinamento == null) return;
		
		try {
			objLote = RemoteTreinamentoService.getInstance().loadScheduledTrainingById(objLote.getId(),false);
			descricaoTextPane.setText(objLote.getResumo());
			objLote.setTurma(turmas.get(getDataTurmaComboBox().getSelectedIndex()));		
			
			this.treinamento.setId(objLote.getId());
			this.treinamento.setCodigo(objLote.getCodigo());
						
			try {
				if (objLote.getAgendaTreinamento() != null) this.treinamento.setAgendaTreinamento(objLote.getAgendaTreinamento());
				else this.treinamento.setAgendaTreinamento(null);				
			} catch (Exception e) {
				this.treinamento.setAgendaTreinamento(null);
			}
			
			try {
				this.treinamento.setTurma(objLote.getTurma());
			} catch (Exception e) {
				this.treinamento.setTurma(null);
			}
			
			this.treinamento.setStatus(objLote.getStatus());
			statusLoteLabel.setText("Estado: " + treinamento.getStatus().name().replace("_", " "));
		
			try {				
				updateAgendaPanel();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	public void editRegister(Lote objLote) {
		// Nunca passar como argumento novos objetos!!!
		getAgendaPanel().setVisible(true);

		if (this.lote == null) return;
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			objLote = (Lote) s.createQuery("select lote from Lote lote where lote.id =" + objLote.getId()+" ").uniqueResult();
			descricaoTextPane.setText(objLote.getDescricao());
			objLote.setLeilao(leiloes.get(getDataLeilaoComboBox().getSelectedIndex()));		
			
			this.lote.setId(objLote.getId());
			this.lote.setCodLote(objLote.getCodLote());
						
			try {
				if (objLote.getAgendaLote() != null) this.lote.setAgendaLote(objLote.getAgendaLote());
				else this.lote.setAgendaLote(null);				
			} catch (Exception e) {
				this.lote.setAgendaLote(null);
			}
			
			try {
				this.lote.setLeilao(objLote.getLeilao());
			} catch (Exception e) {
				this.lote.setLeilao(null);
			}
			
			this.lote.setStatus(objLote.getStatus());
			statusLoteLabel.setText("Estado: " + lote.getStatus().name().replace("_", " "));
		
			try {				
				updateAgendaPanel(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s!=null) s.close();
		}
		
		
	}*/


	protected JLabel getCodigoLabel() {

		if (codigoLabel == null) {
			codigoLabel = new JLabel("Lote:");
			codigoLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_put.png")));
			codigoLabel.setBounds(new Rectangle(0, 2, 76, 22));
			codigoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return codigoLabel;
	}
	
	private boolean updatingLeilaoComboBox = false;

	public JComboBox getDataTurmaComboBox() {

		if (dataTurmaComboBox == null) {
			dataTurmaComboBox = new JComboBox();
			dataTurmaComboBox.setBounds(new Rectangle(230, 5, 231, 22));
			
			dataTurmaComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED && !updatingLeilaoComboBox){						
						if (turmas != null){
							int index = dataTurmaComboBox.getSelectedIndex();
							if (index < 0) return;
							//updateLotesList();
							initializeLotes();
						}
					}
				}				
			});
		}
		return dataTurmaComboBox;
	}

	protected JLabel getDataTurmaLabel() {
		if (dataTurmaLabel == null) {
			dataTurmaLabel = new JLabel("Buscar os Lotes Pela Data do Leilão");
			dataTurmaLabel.setHorizontalAlignment(JLabel.CENTER);
			dataTurmaLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/date.png")));
			dataTurmaLabel.setBounds(new Rectangle(16, 5, 211, 22));
		}
		return dataTurmaLabel;
	}


	private JButton editarLoteButton = null;

	private JButton refreshLeiloesButton = null;


	
/*	protected JTabbedPane tabbedPane;
	
	
	protected JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {			
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(635, 395));
			tabbedPane.setLocation(new Point(15, 90));
			tabbedPane.add(getProdutosPanel(),"Dados do lote");
			tabbedPane.add(getAgendaPanel(),"Agenda para os lances do lote");
			
		}
		return tabbedPane;
	}

*/
	
	private EncerramentoAgendaForm agendaPanel;
	
	public EncerramentoAgendaForm getAgendaPanel(){
		if (agendaPanel == null){
			agendaPanel = new EncerramentoAgendaForm();
			agendaPanel.setSize(new Dimension(575, 348));
			agendaPanel.setLocation(new Point(13, 106));
			/*agendaPanel.getRejeitarButton().addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					updateLotesList();
				}				
			});*/
			//agendaPanel.setFechamentoLoteForm(this);
		}
		return agendaPanel;
	}
	
	private void updateAgendaPanel(){		
		treinamento = lotes.get((String)codigoComboBox.getSelectedItem());
		try{
			try {
				AgendaTreinamento agenda = RemoteTreinamentoService.getInstance().getScheduledTrainingScheduleIdByScheduledTrainingId(treinamento.getId());
				treinamento.setAgendaTreinamento(agenda);
			} catch (Exception e) {
				treinamento.setAgendaTreinamento(null);
			}
			
			if (treinamento.getAgendaTreinamento() != null){
				editRegister(treinamento.getAgendaTreinamento(), treinamento);
			}else{
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		getAgendaPanel().updateData();
	}
	/*private void updateAgendaPanel(Session s){		
		//Session s = null;
		System.out.println("************ update Agenda panel *************");
		lote = lotes.get((String)codLoteComboBox.getSelectedItem());
		boolean sessionlocal=false;
		try{
			if (s == null) {
				s = LocalServicesUtility.getInstance().openSession();
				sessionlocal=true;
			}
			
			try {
				int id = (Integer) s.createQuery("select agenda.id from AgendaLote agenda join agenda.lote lote where lote.id="+lote.getId()+" and agenda.id = lote.agendaLote.id").uniqueResult();
				AgendaLote agenda = new AgendaLote();
				agenda.setId(id);
				lote.setAgendaLote(agenda);
			} catch (Exception e) {
				lote.setAgendaLote(null);
			}
			
			if (lote.getAgendaLote() != null){
				editRegister(lote.getAgendaLote(), lote, s);
			}else{
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null && sessionlocal) s.close();
		}
		getAgendaPanel().updateData();
		System.out.println("************ FIM update Agenda panel *************");
	}*/
	
	public void editRegister(AgendaTreinamento agendaLote, Treinamento lote) {
		agendaPanel.setLote(lote);
		agendaPanel.editRegister(agendaLote);		
	}
	/*public void editRegister(AgendaLote agendaLote, Lote lote,Session s) {
		agendaPanel.setLote(lote);
		agendaPanel.editRegister(agendaLote,s);	
		
	}*/
	

	/**
	 * This method initializes editarLoteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarLoteButton() {
		if (editarLoteButton == null) {
			editarLoteButton = new JButton();
			editarLoteButton.setText("Listar Dados de Fechamento do Lote");
			editarLoteButton.setBounds(new Rectangle(0, 50, 208, 24));
			editarLoteButton.addActionListener(new AbstractAction(){
				@Override
				public void doAction(ActionEvent evt) {
					editarLoteSelecionado();
				}				
			});
		}
		return editarLoteButton;
	}
	
	private void editarLoteSelecionado(){
		try {			
			
			if (codigoComboBox.getSelectedItem() != null){
				if (treinamento != null && treinamento.getCodigo() != null
					&&((JComboBox)getCodigoComboBox()).getSelectedItem() != null
					&& treinamento.getCodigo().equals(((JComboBox)getCodigoComboBox()).getSelectedItem())){
					
				}else{
					treinamento = lotes.get(codigoComboBox.getSelectedItem());				
				}
				if (treinamento != null) editRegister(treinamento);
				
			}else{
				System.out.println("Lote não selecionado!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	/**
	 * This method initializes refreshLeiloesButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshLeiloesButton() {
		if (refreshLeiloesButton == null) {
			refreshLeiloesButton = new JButton();
			refreshLeiloesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshLeiloesButton.setBounds(new Rectangle(462, 6, 22, 22));
			refreshLeiloesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateTurmasList();
				}
			});
		}
		return refreshLeiloesButton;
	}
	
	private JButton refreshTreinamentosButton;
	
	private JButton getRefreshTreinamentosButton() {
		if (refreshTreinamentosButton == null) {
			refreshTreinamentosButton = new JButton();
			refreshTreinamentosButton.setBounds(new Rectangle(185, 2, 22, 22));
			refreshTreinamentosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshTreinamentosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					initializeLotes();
				}
			});
		}
		return refreshTreinamentosButton;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void updateTurmasList(){	
		getDataTurmaComboBox().removeAllItems();
		turmas.clear();
		try {
			Iterator it = RemoteTurmaService.getInstance().listTrainingClassValues().iterator();
			{
				int i=0;
				while(it.hasNext()){
					Object obj[] = (Object[])it.next();
					
					TurmaTreinamento l = new TurmaTreinamento();
					l.setId(((Integer)obj[0]).intValue());
					l.setDataTreinamento((Date)obj[1]);
					turmas.add(l);
					getDataTurmaComboBox().addItem(ScheduledTrainingCadastreForm.formatDataTreinamento(l));
					i++;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (turmas == null || turmas.size() == 0) getDataTurmaComboBox().setEnabled(false);
		else getDataTurmaComboBox().setEnabled(true);
	}
	/*public void updateLeilaoList(){	
		getDataLeilaoComboBox().removeAllItems();
		leiloes.clear();
		Session s = LocalServicesUtility.getInstance().openSession();
		System.out.println("-------------Buscando a lista de leilões----------");
		try {
			Iterator it = s.createQuery("select leilao.id, leilao.dataPresencialPrimeira, leilao.dataOnlinePrimeira from Leilao leilao order by leilao.dataPresencialPrimeira ASC").list().iterator();
			{
				int i=0;
				while(it.hasNext()){
					//if (i >9) break;
					Object obj[] = (Object[])it.next();
					
					Leilao l = new Leilao();
					l.setId(((Integer)obj[0]).intValue());
					System.out.println("Leilao " + l.getId());
					l.setDataPresencialPrimeira((Date)obj[1]);
					l.setDataOnlinePrimeira((Date)obj[2]);					
					leiloes.add(l);
					getDataLeilaoComboBox().addItem(LoteCadastreForm.formatDataLeilao(l));
					i++;
				}
			}
			//s.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		if (leiloes == null || leiloes.size() == 0) getDataLeilaoComboBox().setEnabled(false);
		else getDataLeilaoComboBox().setEnabled(true);
		System.out.println("-------------Fim Buscando a lista de leilões----------");	
	}*/
	
	private List<TurmaTreinamento> turmas = new ArrayList<TurmaTreinamento>();  //  @jve:decl-index=0:

	/*public void updateLotesList(){	
		
		if (lotes != null) lotes.clear();
		else{
			initializeLotes();
		}
		codLoteComboBox.removeAllItems();
		refreshingCodLoteComboBox=true;
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			int index = dataLeilaoComboBox.getSelectedIndex();
			if (index < 0) return;
			Leilao leilao = leiloes.get(index);
			Iterator it = s.createQuery("select lote.id, lote.codLote from Lote lote where lote.arrematado=true and lote.leilao.id ="+leilao.getId()+" order by lote.codLote ASC").list().iterator();
			{
				int i=0;
				while(it.hasNext()){
					
					Object obj[] = (Object[])it.next();
					
					Lote l = new Lote();
					l.setLeilao(leilao);
					
					l.setId(((Integer)obj[0]).intValue());
					l.setCodLote((String)obj[1]);									
					lotes.put(l.getCodLote(),l);
					codLoteComboBox.addItem(l.getCodLote());
					i++;
				}
			}
			//s.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			//s.getTransaction().rollback();
		}finally{
			s.close();
		}
		refreshingCodLoteComboBox=false;
					
	}*/



	/**
	 * This method initializes descricaoScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getDescricaoScrollPane() {
		if (descricaoScrollPane == null) {
			descricaoScrollPane = new JScrollPane();
			descricaoScrollPane.setBounds(new Rectangle(216, 4, 358, 70));
			descricaoScrollPane.setViewportView(getDescricaoTextPane());
		}
		return descricaoScrollPane;
	}

	/**
	 * This method initializes descricaoTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getDescricaoTextPane() {
		if (descricaoTextPane == null) {
			descricaoTextPane = new JTextPane();
			descricaoTextPane.setEditable(false);
			
		}
		return descricaoTextPane;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(449, 389));
				gui.add(new FechamentoTreinamentoForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	private JScrollPane descricaoScrollPane = null;

	private JTextPane descricaoTextPane = null;

	private JLabel statusLoteLabel = null;
	
	
	public void setSelectedLeilao(TurmaTreinamento leilao) {
		updatingLeilaoComboBox=true;
		updateTurmasList();
		updatingLeilaoComboBox=false;
		getDataTurmaComboBox().setSelectedItem(ScheduledTrainingCadastreForm.formatDataTreinamento(leilao));		
	}

	public void setSelectedLote(Treinamento l) {		
		editRegister(l);
	}
	
	public void setSelectedLoteRefreshByLeilao(Treinamento l, String leilaoDates) {
		updatingLeilaoComboBox=true;
		updateTurmasList();
		getDataTurmaComboBox().setSelectedItem(leilaoDates);
		refreshingCodLoteComboBox=true;
		//updateLotesList();
		initializeLotes();
		refreshingCodLoteComboBox=false;
		codigoComboBox.setSelectedItem(l.getCodigo());
		editarLoteSelecionado();		
		updatingLeilaoComboBox=false;
	}

	public void editRegister(Treinamento lote, String leilaoDates) {
		setSelectedLoteRefreshByLeilao(lote,leilaoDates);		
	}

	

}  //  @jve:decl-index=0:visual-constraint="10,10"