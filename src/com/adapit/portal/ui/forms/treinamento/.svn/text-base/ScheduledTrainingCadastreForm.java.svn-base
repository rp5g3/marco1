package com.adapit.portal.ui.forms.treinamento;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.ComercialSolutionItem;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ScheduledTrainingStatus;
import com.adapit.portal.entidades.TrainingSolution;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.local.LocalServicesUtility;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.services.remote.RemoteTreinamentoService;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.ui.forms.pessoa.participante.SelectPessoaDivulgavelParticipanteDialog;
import com.adapit.portal.ui.forms.solution.itemsolution.ComercialSolutionItemCadastreForm;
import com.adapit.portal.ui.forms.training.SelecionarTrainingSolutionListForm;
import com.adapit.portal.ui.forms.treinamento.agenda.AgendaTreinamentoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AbstractAction;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;
import com.workcase.utils.Moeda;
import javax.swing.JTextPane;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class ScheduledTrainingCadastreForm extends JPanel {

	private JPanel numeroTreinamentoPanel;

	private JComboBox codigoComboBox;

	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:

	private Treinamento scheduledTraining = new Treinamento();  //  @jve:decl-index=0:

	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:

	private boolean processFocus = true;

	protected LogErrorPanel logErrorPanel;

	private JLabel codigoLabel;

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JComboBox dataTurmaComboBox;

	private JLabel dataTurmaLabel;

	private JPanel receitaPanel;

	private JTextField receitaTextField;

	private JLabel receitaLabel;

	private JButton calcularPelosItensButton;

	private JPanel matriculaPanel;  //  @jve:decl-index=0:visual-constraint="10,518"

	private JScrollPane inscricoesListScrollPane;

	private ProdutosList inscricoesList;

	private JPanel inscricoesPanel;

	private JButton addInscricoesButton;

	private JButton editarInscricaoButton;

	private JButton removeInscricaoButton;

	private JPanel scheduledTrainingButtonsPanel;

	private JButton saveSchedTrainingButton;

	private JButton newSchedTrainingButton;

	private JButton removeSchedTrainingButton;

	public ScheduledTrainingCadastreForm() {
		initialize();
	}

	private void initialize() {
		statusAgendaLabel = new JLabel();
		statusAgendaLabel.setBounds(new Rectangle(174, 1, 225, 29));
		statusAgendaLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/date_error.png")));
		statusAgendaLabel.setText("Matrículas Encerradas");
		
		setLayout(null);
		add(getNumeroTreinamentoPanel());
		add(getTabbedPanePrincipal());
		add(getScheduledTrainingButtonsPanel());
		this.setBounds(new Rectangle(0, 0, 660, 585));
		this.add(getDataTurmaLabel(), null);
		this.add(getCodigoLabel(), null);
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		// End
		this.add(statusAgendaLabel, null);
		updateTurmaList();
		initializeTreinamentos();
	}

	protected JPanel getNumeroTreinamentoPanel() {
		if (numeroTreinamentoPanel == null) {
			numeroTreinamentoPanel = new JPanel();
			numeroTreinamentoPanel.setSize(new Dimension(631, 60));
			numeroTreinamentoPanel.setLocation(new Point(15, 30));
			numeroTreinamentoPanel.setLayout(null);
			numeroTreinamentoPanel.add(getCodigoComboBox());
			numeroTreinamentoPanel.add(getDataTurmaComboBox());
			numeroTreinamentoPanel.add(getRefreshLeiloesButton(), null);
			numeroTreinamentoPanel.add(getRefreshTreinamentosButton(),null);
			numeroTreinamentoPanel.add(getEditarTreinamentoButton(), null);
			numeroTreinamentoPanel.add(getEditarTurmaButton(), null);
			numeroTreinamentoPanel.add(getNovaTurmaButton(), null);
		}
		return numeroTreinamentoPanel;
	}
	
	private Hashtable<String,Treinamento> schedTrainings;  //  @jve:decl-index=0:

	
	@SuppressWarnings("unchecked")
	public void initializeTreinamentos(){
		getTabbedPanePrincipal().setVisible(false);
		if (getDataTurmaComboBox().getItemCount() == 0) return;		
		try {			
			List<Treinamento> list = RemoteTreinamentoService.getInstance().listAllScheduledTrainingByTurmaId(
					turmas.get(getDataTurmaComboBox().getSelectedIndex()).getId());
			schedTrainings = new Hashtable<String,Treinamento>();
			codigoComboBox.removeAllItems();
			int i=0;
			if (list != null && list.size() > 0){
				Iterator<Treinamento> it = list.iterator();
				while (it.hasNext()){
					Treinamento l = it.next();					
					if (i == 0) scheduledTraining = l;
					if (!schedTrainings.containsKey(l.getCodigo())){
						codigoComboBox.addItem(l.getCodigo());
						schedTrainings.put(l.getCodigo(),l);
					}
					i++;
				}				
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}		
	}
	/*public void initializeTreinamentos(){
		getTabbedPanePrincipal().setVisible(false);
		if (getDataLeilaoComboBox().getItemCount() == 0) return;
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			List list = s.createQuery("select lote.id, lote.codTreinamento from Treinamento lote where lote.leilao.id =" + leiloes.get(getDataLeilaoComboBox().getSelectedIndex()).getId()+" order by lote.codTreinamento").list();//LocalTreinamentoService.getInstance().l//.listLikeCodTreinamento("");
			lotes = new Hashtable<String,Treinamento>();
			codTreinamentoComboBox.removeAllItems();
			int i=0;
			if (list != null && list.size() > 0){
				Iterator it = list.iterator();
				while (it.hasNext()){
					Object obj[] = (Object[]) it.next();
					Treinamento l = new Treinamento();//it.next();
					l.setId((Integer)obj[0]);
					l.setCodTreinamento((String)obj[1]);
					if (i == 0) lote = l;
					if (!lotes.containsKey(l.getCodTreinamento())){
						codTreinamentoComboBox.addItem(l.getCodTreinamento());
						lotes.put(l.getCodTreinamento(),l);
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
	private void codigoComboEdited(){
		String num = (String) codigoComboBox.getSelectedItem();
		try {
			Treinamento lote = schedTrainings.get(num);			
			if (lote != null){
				ScheduledTrainingCadastreForm.this.scheduledTraining = lote;
				editarTreinamentoSelecionado();
			}
			else{
				int conf = JOptionPane.showConfirmDialog(ScheduledTrainingCadastreForm.this, "A agenda de treinamento de número " + num + " não existe. Você deseja criá-la?", "Criar nova agenda", JOptionPane.YES_NO_OPTION);
				if (conf == JOptionPane.YES_OPTION){
					if (getDataTurmaComboBox().getSelectedItem() == null){
						JOptionPane.showMessageDialog(ScheduledTrainingCadastreForm.this,"É necessário primeiro selecionar a turma","Não foi possível cadastrar a agenda",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						Treinamento newTreinamento = new Treinamento();
						newTreinamento.setCodigo(num);
						newTreinamento.setReceita(0.0f);
						newTreinamento.setResumo("");
						newTreinamento.setTurma(getSelectedTurma());
						
						try{
							newTreinamento = (Treinamento) RemoteServicesUtility.getInstance().save(newTreinamento);							
							if (schedTrainings != null && !schedTrainings.containsKey(newTreinamento.getCodigo())){
								codigoComboBox.addItem(newTreinamento.getCodigo());
								schedTrainings.put(newTreinamento.getCodigo(),newTreinamento);
								codigoComboBox.setSelectedItem(newTreinamento.getCodigo());								
							}
						}catch(Exception ex){
							ex.printStackTrace();							
						}
						editarTreinamentoSelecionado();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	}
/*	private void codTreinamentoComboEdited(){
		String num = (String) codTreinamentoComboBox.getSelectedItem();
		try {
			Treinamento lote = lotes.get(num);			
			if (lote != null){
				TreinamentoCadastreForm.this.lote = lote;
				editarTreinamentoSelecionado();
			}
			else{
				int conf = JOptionPane.showConfirmDialog(TreinamentoCadastreForm.this, "O lote de número " + num + " não existe. Você deseja criá-lo", "Criar novo lote", JOptionPane.YES_NO_OPTION);
				if (conf == JOptionPane.YES_OPTION){
					if (getDataLeilaoComboBox().getSelectedItem() == null){
						JOptionPane.showMessageDialog(TreinamentoCadastreForm.this,"É necessário primeiro selecionar o leilão","Não foi possível cadastrar o lote",JOptionPane.ERROR_MESSAGE);
						return;
					}
					try {
						Treinamento newTreinamento = new Treinamento();
						newTreinamento.setCodTreinamento(num);
						newTreinamento.setAvaliacao(0.0f);
						newTreinamento.setDescricao("lote vazio");
						newTreinamento.setResumo("");
						newTreinamento.setLeilao(getSelectedLeilao());
						Session s = LocalServicesUtility.getInstance().openSession();
						try{
							s.beginTransaction();											
							s.save(newTreinamento);
							s.getTransaction().commit();
							
							if (lotes != null && !lotes.containsKey(newTreinamento.getCodTreinamento())){
								codTreinamentoComboBox.addItem(newTreinamento.getCodTreinamento());
								lotes.put(newTreinamento.getCodTreinamento(),newTreinamento);
								codTreinamentoComboBox.setSelectedItem(newTreinamento.getCodTreinamento());								
							}
						}catch(Exception ex){
							ex.printStackTrace();
							s.getTransaction().rollback();
						}finally{
							s.close();
						}
						editarTreinamentoSelecionado();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	
	}*/
	
	private boolean refreshingCodTreinamentoComboBox=false;
	
	@SuppressWarnings("unchecked")
	public JComponent getCodigoComboBox() {
		if (codigoComboBox == null) {
			codigoComboBox = new JComboBox();
			codigoComboBox.setEditable(true);
			codigoComboBox.setSize(new java.awt.Dimension(106, 22));
			codigoComboBox.setLocation(new java.awt.Point(0, 0));
			
			codigoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED && !refreshingCodTreinamentoComboBox){
						if (schedTrainings != null){
							scheduledTraining = schedTrainings.get(codigoComboBox.getSelectedItem());
							if (scheduledTraining != null) editarTreinamentoSelecionado();
						} 
					}
				}				
			});
			
			this.binder.addBindProperty(this.scheduledTraining, this.codigoComboBox,
					"codigo");

			this.hashComps.put("codigo", this.codigoComboBox);
			JWarningComponent warn = new JWarningComponent(
					this.codigoComboBox);
			warn.setBounds(0, 0, 106, 22);
			codigoComboBox.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					if (evt.getActionCommand().equalsIgnoreCase("comboBoxEdited")){
						codigoComboEdited();
					}
				}				
			});
			return warn;
		}
		return codigoComboBox;
	}
	
	public TurmaTreinamento getSelectedTurma(){
		try {
			return (TurmaTreinamento) RemoteTurmaService.getInstance().loadTrainingClassByTrainingClassId(
					((TurmaTreinamento)turmas.get(getDataTurmaComboBox().getSelectedIndex())).getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return null;
	}
	/*public Leilao getSelectedLeilao(){
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			return (Leilao) s.load(Leilao.class, ((Leilao)leiloes.get(getDataLeilaoComboBox().getSelectedIndex())).getId());
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		return null;
	}*/

	public Treinamento validateTreinamentoForm() throws Exception {
		setErrorIcon(false);
		//binder.bind(lote);
		scheduledTraining.setTurma(getSelectedTurma());
		try {
			scheduledTraining.setReceita((float) Moeda.valorRealToDouble(receitaTextField.getText()));
		} catch (Exception e) {
			e.printStackTrace();			
		}
		scheduledTraining.setResumo(resumoTreinamentoTextField.getText());
		scheduledTraining.setStatus(ScheduledTrainingStatus.valueOf(((String)scheduledTrainingStateComboBox.getSelectedItem()).replace(" ", "_")));
		if (scheduledTraining.getTurma() == null) throw new Exception("treinamento.turma");
		if (!validateTreinamentoBean()) throw new Exception("Treinamento Inválido");
		// Manual Code
		/*if (!validateTreinamentoBean())
			throw new Exception("ErroTreinamentoDadosInvalidos");*/
		return scheduledTraining;
	}

	
	public Treinamento cadastreTreinamento() throws Exception {
		try{
			validateTreinamentoForm();
			RemoteTreinamentoService.getInstance().updateScheduledTrainingProperties(scheduledTraining);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return scheduledTraining;
	}
	/*public Treinamento cadastreTreinamento() throws Exception {
		
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			validateTreinamentoForm();
			//s.update(lote);
			Query query = s.createQuery("update Treinamento l set l.resumo=:resumo, l.status=:status, l.descricao=:desc, l.avaliacao=:avaliacao where l.id=:id");
			query.setParameter("resumo", lote.getResumo());
			query.setParameter("status", lote.getStatus().name());
			query.setParameter("desc", lote.getDescricao());
			query.setParameter("avaliacao", lote.getAvaliacao());
			query.setParameter("id", lote.getId());
			query.executeUpdate();
			s.getTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			if (s != null) s.close();
		}
		//LocalTreinamentoService.getInstance().saveOrUpdate(l);
		return lote;
	}*/

	@SuppressWarnings("unchecked")
	public boolean validateTreinamentoBean() {
		getErrorPanel().removeAllElements();
		if (processFocus) try{
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}catch(Exception ex){
			
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.scheduledTraining, "treinamento");
		if (errors == null)
			return true;
		Map.Entry[] entrys = Validate.getOrder(errors, this.hashComps);
		String name;
		JComponent comp;
		for (int i = 0; i < entrys.length; i++) {
			name = (String) entrys[i].getKey();
			comp = (JComponent) this.hashComps.get(name);
			if (comp != null) {
				comp.firePropertyChange("warnBorder", false, true);
				Object[] obj = (Object[]) entrys[i].getValue();
				String msg = (String) obj[0];
				if (obj[1] == null) {
					try {
						getErrorPanel()
								.addError(messages.getMessage(msg), comp);
						comp.setToolTipText(messages.getMessage(msg));
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					List args = (List) obj[1];
					Object[][] params = new Object[args.size()][2];
					for (int j = 0; j < args.size(); j++) {
						String key = (String) args.get(j);
						params[j][0] = key;
						params[j][1] = null;
					}
					try {
						getErrorPanel().addError(
								messages.getMessage(msg, params), comp);
						comp.setToolTipText(messages.getMessage(msg, params));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}

	public void newRegister() {

		// lote.setComprador(null);
		// lote.setComitente(null);
		scheduledTraining.setComercialSolutionItens(null);
		scheduledTraining.setId(0);
		scheduledTraining.setResumo(null);
		scheduledTraining.setReceita(0.0f);
		scheduledTraining.setCodigo(null);
		scheduledTraining.setStatus(ScheduledTrainingStatus.Em_planejamento);
		// lote.setRetirado();

		binder.reverseBind(this.scheduledTraining);
		//getLanceAgendaButton().setEnabled(false);
		agendaPanel.setVisible(false);
		this.setErrorIcon(false);
		updateUI();
	}
	
	@SuppressWarnings("unchecked")
	public List<ComercialSolutionItem> getItens(int loteId){			
		try{
			return RemoteTreinamentoService.getInstance().getComercialSolutionItensByScheduledTrainingId(loteId);			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return null;
	}
	/*public List<ItemProduto> getItens(int loteId, Session s){
		List<ItemProduto> itens = new ArrayList<ItemProduto>();		
		try{
			String qItens = "select ip.id from ItemProduto ip where ip.lote.id="+ loteId;
			List list =  s.createQuery(qItens).list();
			//int i=0;
			if (list != null && list.size() > 0){
				Iterator it = list.iterator();
				while (it.hasNext()){
					Integer id = (Integer) it.next();
					ItemProduto ip = new ItemProduto();
					ip.setId(id);
					itens.add(ip);
				}
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}		
		return itens;
	}*/

	@SuppressWarnings("unchecked")
	public void editRegister(Treinamento objTrein) {
		// Nunca passar como argumento novos objetos!!!
		getTabbedPanePrincipal().setVisible(true);
		
		if (this.scheduledTraining == null) return;	
		
		try {
			objTrein = (Treinamento) RemoteTreinamentoService.getInstance().loadScheduledTrainingById(objTrein.getId(),false);
			
			objTrein.setTurma(turmas.get(getDataTurmaComboBox().getSelectedIndex()));
						
			
			this.scheduledTraining.setReceita(objTrein.getReceita());
			this.scheduledTraining.setId(objTrein.getId());
			this.scheduledTraining.setCodigo(objTrein.getCodigo());
			this.scheduledTraining.setResumo(objTrein.getResumo());
			this.scheduledTraining.setCusto(objTrein.getCusto());
			
			
			try {
				
				if (objTrein.getAgendaTreinamento() != null)
					this.scheduledTraining.setAgendaTreinamento(objTrein.getAgendaTreinamento());
				else this.scheduledTraining.setAgendaTreinamento(null);
				
			} catch (Exception e) {
				this.scheduledTraining.setAgendaTreinamento(null);
			}
			
			
			
			try {
				this.scheduledTraining.setTurma(objTrein.getTurma());
			} catch (Exception e) {
				this.scheduledTraining.setTurma(null);
			}
			
			this.scheduledTraining.setStatus(objTrein.getStatus());
			if (scheduledTraining.getStatus() != null)try{
				scheduledTrainingStateComboBox.setSelectedItem(scheduledTraining.getStatus().name().replace("_", " "));				
			}catch(Exception ex){
				
			}
			
			try {
				if (objTrein.getTreinamento() != null){
					scheduledTraining.setTreinamento(objTrein.getTreinamento());
					getNomeTextField().setText(scheduledTraining.getTreinamento().getNome());
					trainingCodTextField.setText(scheduledTraining.getTreinamento().getCodigo());
					cargaHorariaTextField.setText(""+scheduledTraining.getTreinamento().getCargaHoraria());
					resumoTrainSolTextPane.setText(scheduledTraining.getTreinamento().getResumo());
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
			try {				
				getInscricoesList().updateTable();
				//gerarDescButton.setEnabled(true);
				//anexarButton.setEnabled(true);
				addInscricoesButton.setEnabled(true);
				calcularPelosItensButton.setEnabled(true);
					
				saveSchedTrainingButton.setEnabled(true);
				addInscricoesButton.setEnabled(true);
				receitaTextField.setEnabled(true);
				receitaTextField.setText(Moeda.getValorReal((Float)  scheduledTraining.getReceita()));
				
				despezaTextField.setEnabled(true);
				despezaTextField.setText(Moeda.getValorReal((Float)  scheduledTraining.getCusto()));
								
				if (scheduledTraining.getResumo() != null){
					resumoTreinamentoTextField.setText(scheduledTraining.getResumo());
				}else{
					resumoTreinamentoTextField.setText("");
				}
				
				
				if (scheduledTraining.getStatus() != null)
					scheduledTrainingStateComboBox.setSelectedItem(scheduledTraining.getStatus().name().replace("_", " "));
				
				
				updateAgendaPanel();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setErrorIcon(false);
		agendaPanel.setEnabled(true);
	}
/*	public void editRegister(Treinamento objTreinamento) {
		// Nunca passar como argumento novos objetos!!!
		getTabbedPanePrincipal().setVisible(true);
		
		if (this.lote == null) return;
		Session s = LocalServicesUtility.getInstance().openSession();
		
		try {
			objTreinamento = (Treinamento) s.createQuery("select lote from Treinamento lote where lote.id =" + objTreinamento.getId()+" ").uniqueResult();//s.load(Treinamento.class,objTreinamento.getId());
			
			objTreinamento.setLeilao(leiloes.get(getDataLeilaoComboBox().getSelectedIndex()));
						
			
			this.lote.setAvaliacao(objTreinamento.getAvaliacao());
			this.lote.setId(objTreinamento.getId());
			this.lote.setCodTreinamento(objTreinamento.getCodTreinamento());
			this.lote.setCancelado(objTreinamento.isCancelado());
			this.lote.setArrematado(objTreinamento.isArrematado());
			this.lote.setPagtoQuitado(objTreinamento.isPagtoQuitado());
			this.lote.setResumo(objTreinamento.getResumo());
			
			try {
				
				if (objTreinamento.getProcesso() != null) this.lote.setProcesso(objTreinamento.getProcesso());
				else this.lote.setProcesso(null);
				getProcessoJudicialPanel().setTreinamento(lote);
				if (lote.getProcesso() != null)
					getProcessoJudicialPanel().editRegister(lote.getProcesso());
				else getProcessoJudicialPanel().newRegister();
			} catch (Exception e) {
				this.lote.setProcesso(null);
			}
			
			try {
				
				if (objTreinamento.getAgendaTreinamento() != null) this.lote.setAgendaTreinamento(objTreinamento.getAgendaTreinamento());
				else this.lote.setAgendaTreinamento(null);
				
			} catch (Exception e) {
				this.lote.setAgendaTreinamento(null);
			}
			
			this.lote.setDescricao(objTreinamento.getDescricao());
			
			try {
				this.lote.setLeilao(objTreinamento.getLeilao());
			} catch (Exception e) {
				this.lote.setLeilao(null);
			}
			
			this.lote.setStatus(objTreinamento.getStatus());
			if (lote.getStatus() != null)try{
				estadoTreinamentoComboBox.setSelectedItem(lote.getStatus().name().replace("_", " "));				
			}catch(Exception ex){
				
			}
			if (lote.getStatus() == StatusTreinamento.Dividido_em_sublotes){
				showTreinamentoProdutosIndividuaisPanel();
			}else{
				ocultarTreinamentoProdutosIndividuaisPanel();
			}
		
			try {				
				getProdutosList().updateTable();
				gerarDescButton.setEnabled(true);
				anexarButton.setEnabled(true);
				addProdutosButton.setEnabled(true);
				calcularPelosItensButton.setEnabled(true);
					
				atualizarTreinamentoButton.setEnabled(true);
				addProdutosButton.setEnabled(true);
				avaliacaoTextField.setEnabled(true);
				avaliacaoTextField.setText(Moeda.getValorReal((Float)  lote.getAvaliacao()));
				descricaoTextArea.setEnabled(true);
				descricaoTextArea.setText(lote.getDescricao());
				
				if (lote.getResumo() != null){
					resumoTreinamentoTextField.setText(lote.getResumo());
				}else{
					resumoTreinamentoTextField.setText("");
				}
				retiradoCheckBox.setSelected(lote.isRetiradoDeposito());
				arrematadoCheckBox.setSelected(lote.isArrematado());
				pagtoQuitadoCheckBox.setSelected(lote.isPagtoQuitado());
				
				if (lote.getStatus() != null)
					estadoTreinamentoComboBox.setSelectedItem(lote.getStatus().name().replace("_", " "));
				
				if (!lote.isArrematado() && !lote.isCancelado()
						&& lote.getStatus() == StatusTreinamento.Em_execução_de_lances)
					getLanceAgendaButton().setEnabled(true);
				else getLanceAgendaButton().setEnabled(false);
				updateAgendaPanel(s);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s!=null) s.close();
		}
		this.setErrorIcon(false);
		agendaPanel.setEnabled(true);
	}*/

	public LogErrorPanel getErrorPanel() {
		if (logErrorPanel == null) {
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setBounds(new Rectangle(16, 523, 629, 53));
		}
		return logErrorPanel;
	}

	public void setErrorIcon(boolean bool) {
		this.codigoComboBox.firePropertyChange("warnBorder", !bool, bool);
		this.receitaTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}

	protected JLabel getCodigoLabel() {
		if (codigoLabel == null) {
			codigoLabel = new JLabel("Treinamento");
			codigoLabel.setBounds(new Rectangle(15, 7, 136, 22));
			codigoLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_put.png")));
			codigoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return codigoLabel;
	}
	
	private boolean updatingTurmaComboBox = false;

	public JComboBox getDataTurmaComboBox() {
		if (dataTurmaComboBox == null) {
			dataTurmaComboBox = new JComboBox();
			dataTurmaComboBox.setSize(new Dimension(212, 22));
			dataTurmaComboBox.setLocation(new Point(385, 0));
			
			dataTurmaComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED && !updatingTurmaComboBox){						
						if (turmas != null){
							int index = dataTurmaComboBox.getSelectedIndex();
							if (index < 0) return;
							updateScheduledTrainingList();
							initializeTreinamentos();
						}
					}
				}				
			});
		}
		return dataTurmaComboBox;
	}

	protected JLabel getDataTurmaLabel() {
		if (dataTurmaLabel == null) {
			dataTurmaLabel = new JLabel("Data da Turma");
			dataTurmaLabel.setHorizontalAlignment(JLabel.CENTER);
			dataTurmaLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/date.png")));
			dataTurmaLabel.setBounds(new Rectangle(401, 7, 210, 22));
		}
		return dataTurmaLabel;
	}

	protected JPanel getReceitaPanel() {
		if (receitaPanel == null) {
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(3);
			gridLayout.setColumns(1);
			receitaPanel = new JPanel();
			receitaPanel.setLayout(gridLayout);
			receitaPanel.setBounds(new Rectangle(522, 190, 99, 76));
			receitaPanel.add(getReceitaLabel(), null);
			receitaPanel.add(getReceitaTextField(), null);
			receitaPanel.add(getCalcularPelosItensButton(), null);
		}
		return receitaPanel;
	}

	@SuppressWarnings("unchecked")
	protected JComponent getReceitaTextField() {
		if (receitaTextField == null) {
			receitaTextField = new JTextField();
			receitaTextField.setText("");
			receitaTextField.setHorizontalAlignment(SwingConstants.RIGHT);
			this.binder.addBindProperty(this.scheduledTraining, this.receitaTextField,"receita");

			this.hashComps.put("receita", this.receitaTextField);
			JWarningComponent warn = new JWarningComponent(
					this.receitaTextField);
			warn.setBounds(105, 0, 95, 20);
			return warn;
		}
		return receitaTextField;
	}

	protected JLabel getReceitaLabel() {
		if (receitaLabel == null) {
			receitaLabel = new JLabel("Receita:");
			receitaLabel.setHorizontalAlignment(SwingConstants.CENTER);
			receitaLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/money.png")));
		}
		return receitaLabel;
	}

	protected JButton getCalcularPelosItensButton() {
		if (calcularPelosItensButton == null) {
			calcularPelosItensButton = new JButton("Calcular");
			calcularPelosItensButton.setText("Calcular");
			calcularPelosItensButton.setIcon(new ImageIcon(getClass().getResource("/imgs/calculator.png")));
			calcularPelosItensButton.setEnabled(false);
			calcularPelosItensButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					try {
						//if (lote.getItensProduto() != null && lote.getItensProduto().size() > 0){
							//float soma = 0;
							/*Iterator<ItemProduto> it = lote.getItensProduto().iterator();
							while (it.hasNext()){
								ItemProduto ip = it.next();
								soma +=ip.getValorTotal();
							}*/
							
							receitaTextField.setText(Moeda.getValorReal((Float)  soma));
						//}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return calcularPelosItensButton;
	}

	protected JScrollPane descScrollPane;

	private JButton novaTurmaButton = null;

	private JButton editarTurmaButton = null;


	//private JButton gerarDescButton = null;

	private JButton editarTreinamentoButton = null;

	//private JButton anexarButton = null;

	private JButton refreshLeiloesButton = null;

	
	protected JTabbedPane tabbedPanePrincipal;
	
	
	protected JTabbedPane getTabbedPanePrincipal() {
		if (tabbedPanePrincipal == null) {			
			tabbedPanePrincipal = new JTabbedPane();
			tabbedPanePrincipal.setSize(new Dimension(635, 395));
			tabbedPanePrincipal.setLocation(new Point(15, 90));
			tabbedPanePrincipal.add(getMatriculaPanel(),"Informações do Treinamento");
			tabbedPanePrincipal.add(getAgendaPanel(),"Agenda para o Treinamento");
			//tabbedPanePrincipal.add(getInteressadosPanel(),"Interessados");
		}
		return tabbedPanePrincipal;
	}
	
	private JPanel interessadosPanel;
	
	private JPanel getInteressadosPanel(){
		if (interessadosPanel == null){
			interessadosPanel = new JPanel();
		}
		return interessadosPanel;
	}
	
/*	protected JTabbedPane cadastramentoTabbedPane;
	
	
	protected JTabbedPane getCadastramentoTabbedPane() {
		if (cadastramentoTabbedPane == null) {			
			cadastramentoTabbedPane = new JTabbedPane();
			cadastramentoTabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
			cadastramentoTabbedPane.setSize(new Dimension(635, 395));
			cadastramentoTabbedPane.setLocation(new Point(15, 90));
			cadastramentoTabbedPane.add(getMatriculaPanel(),"Dados do Treinamento");			
		}
		return cadastramentoTabbedPane;
	}
	
	*/

	protected JPanel getMatriculaPanel() {
		if (matriculaPanel == null) {			
			cargaHoraLabel = new JLabel();
			cargaHoraLabel.setBounds(new Rectangle(354, 247, 94, 22));
			cargaHoraLabel.setText("Carga Horária:");
			nomeTreinamentoLabel = new JLabel();
			nomeTreinamentoLabel.setBounds(new Rectangle(160, 247, 127, 22));
			nomeTreinamentoLabel.setText("Código do Treinamento:");
			despezaLabel = new JLabel();
			despezaLabel.setText("Custo:");
			receitaValueLabel = new JLabel();
			receitaValueLabel.setBounds(new Rectangle(522, 172, 99, 17));
			receitaValueLabel.setText("Receita");
			resumoLabel = new JLabel();
			resumoLabel.setBounds(new Rectangle(15, 190, 96, 22));
			resumoLabel.setText("OBS:");
			matriculaPanel = new JPanel();
			matriculaPanel.setSize(new Dimension(631, 361));
			matriculaPanel.setLocation(new Point(15, 85));
			matriculaPanel.setLayout(null);
			matriculaPanel.add(getInscricoesListScrollPane());
			matriculaPanel.add(getInscricoesPanel());
			matriculaPanel.add(getReceitaPanel(), null);
			matriculaPanel.add(getScheduledTrainingStateComboBox(), null);
			estadoTreinamentoLabel = new JLabel();
			estadoTreinamentoLabel.setBounds(new Rectangle(15, 220, 96, 22));
			estadoTreinamentoLabel.setText("Estado:");
			matriculaPanel.add(estadoTreinamentoLabel, null);
			matriculaPanel.add(resumoLabel, null);
			matriculaPanel.add(getResumoTreinamentoTextField(), null);
			matriculaPanel.add(getPagtoQuitadoCheckBox(), null);
			matriculaPanel.add(receitaValueLabel, null);
			matriculaPanel.add(getDespezaPanel(), null);
			matriculaPanel.add(getBuscarTreinamentoButton(), null);
			matriculaPanel.add(getNomeTextField(), null);
			matriculaPanel.add(nomeTreinamentoLabel, null);
			matriculaPanel.add(getTrainingCodTextField(), null);
			matriculaPanel.add(cargaHoraLabel, null);
			matriculaPanel.add(getCargaHorariaTextField(), null);
			matriculaPanel.add(getTrainingSolScrollPane(), null);
		}
		return matriculaPanel;
	}

	protected JScrollPane getInscricoesListScrollPane() {
		if (inscricoesListScrollPane == null) {
			inscricoesListScrollPane = new JScrollPane();
			inscricoesListScrollPane.setSize(new Dimension(503, 168));
			inscricoesListScrollPane.setLocation(new java.awt.Point(15, 20));
			inscricoesListScrollPane.add(getInscricoesList());
			inscricoesListScrollPane.setViewportView(getInscricoesList());
		}
		return inscricoesListScrollPane;
	}

	protected ProdutosList getInscricoesList() {
		if (inscricoesList == null) {
			inscricoesList = new ProdutosList();
			inscricoesList.addFocusListener(new FocusAdapter(){
				@Override
				public void focusGained(FocusEvent arg0) {
					editarInscricaoButton.setEnabled(true);
					removeInscricaoButton.setEnabled(true);
					getEditarItemCompraClienteButton().setEnabled(true);
				}				
			});
			return inscricoesList;
		}
		return inscricoesList;
	}

	protected JPanel getInscricoesPanel() {
		if (inscricoesPanel == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(6);
			gridLayout1.setColumns(1);
			inscricoesPanel = new JPanel();
			inscricoesPanel.setBounds(new Rectangle(524, 25, 100, 144));
			inscricoesPanel.setLayout(gridLayout1);
			inscricoesPanel.add(getBuscarParticipanteButton(), null);
			inscricoesPanel.add(getAddprodutosButton(), null);
			inscricoesPanel.add(getEditarInscricaoButton(), null);
			inscricoesPanel.add(getRemoveProdutosButton(), null);
			inscricoesPanel.add(getEditarItemCompraClienteButton(),null);
		}
		return inscricoesPanel;
	}

	//private CadastrarTrainingSolutionDialog cadastrarProdutoDialog;
	
	protected JButton getAddprodutosButton() {
		if (addInscricoesButton == null) {
			addInscricoesButton = new JButton("Novo");
			addInscricoesButton.setSize(new java.awt.Dimension(112, 20));
			addInscricoesButton.setLocation(new java.awt.Point(0, 0));
			addInscricoesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_add.png")));
			addInscricoesButton.setEnabled(false);
			
			/*addInscricoesButton.addActionListener(new AdicionarProdutoTreinamento());*/
		}
		return addInscricoesButton;
	}
	
	
	private SelectPessoaDivulgavelParticipanteDialog advacedSearchDialog;
	
	private SelectPessoaDivulgavelParticipanteDialog getAdvacedSearchDialog(){		
		if (advacedSearchDialog == null){
			advacedSearchDialog = new SelectPessoaDivulgavelParticipanteDialog();
		}
		return advacedSearchDialog;
	}
	

	protected JButton buscarTreinamentoButton;
	
	@SuppressWarnings("unchecked")
	protected JButton getBuscarTreinamentoButton() {
		if (buscarTreinamentoButton == null) {
			buscarTreinamentoButton = new JButton("Buscar Treinamento");
			buscarTreinamentoButton.setBounds(new Rectangle(15, 247, 142, 22));
			buscarTreinamentoButton.setEnabled(true);
			buscarTreinamentoButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent evt) {
					SelecionarTrainingSolutionListForm sel = new SelecionarTrainingSolutionListForm();
					sel.setVisible(true);
					TrainingSolution training = sel.getTrainingSolution();
					if (training != null){
						String code = scheduledTraining.getCodigo();								
						try{
							RemoteTreinamentoService.getInstance()
								.mergeNewTrainingSolutionOnScheduledTraining(training, scheduledTraining);
							
							AdapitVirtualFrame.getInstance()
								.showOperationSucess("Adição de Treinamentos em Formações", "Treinamento "
										+training.getCodigo()+" adicionado!");
						}catch(Exception ex){
							ex.printStackTrace();
							AdapitVirtualFrame.getInstance().showErrorDialog("Adição de Treinamento na Agenda", "Não foi possível adicionar este treinamento na agenda");
						}
												
						initializeTreinamentos();						
						codigoComboBox.setSelectedItem(code);
						editarTreinamentoSelecionado();
						
					}
				}
				
			});
		}
		return buscarTreinamentoButton;
	}

	protected JButton buscarParticipanteButton;
	
	@SuppressWarnings("unchecked")
	protected JButton getBuscarParticipanteButton() {
		if (buscarParticipanteButton == null) {
			buscarParticipanteButton = new JButton("Buscar");
			buscarParticipanteButton.setSize(new java.awt.Dimension(112, 20));
			buscarParticipanteButton.setLocation(new java.awt.Point(0, 0));
			buscarParticipanteButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_go.png")));
			buscarParticipanteButton.setEnabled(true);
			buscarParticipanteButton.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent evt) {
					if (scheduledTraining.getTreinamento() == null){
						AdapitVirtualFrame.getInstance().showErrorDialog("Selecione o Treinamento", "Você precisa antes selecionar o treinamento!");
						return;
					}
					getAdvacedSearchDialog().setVisible(true);
					Participante participante = (Participante) getAdvacedSearchDialog().getUsuario().getDadosPessoais();
					if (participante != null){
						String code = scheduledTraining.getCodigo();						
						try{
							ComercialSolutionItem ip = RemoteTreinamentoService.getInstance().mergeParticipanteOnScheduledTraining(participante, scheduledTraining);
							AdapitVirtualFrame.getInstance().showOperationSucess("Matrícula de Participantes", "Item do participante adicionado! Atualize os dados do item deste participante");
						}catch(Exception ex){
							ex.printStackTrace();
							AdapitVirtualFrame.getInstance().showErrorDialog("Matrícula de Participantes", "Não foi possível adicionar este participante no treinamento");
						}
						initializeTreinamentos();						
						codigoComboBox.setSelectedItem(code);
						editarTreinamentoSelecionado();
						
					}
				}
				
			});
		}
		return buscarParticipanteButton;
	}
	

	private JDialog itemParticipanteDialog;  //  @jve:decl-index=0:visual-constraint="711,9"
	
	private JDialog getItemParticipanteDialog(){
		if (itemParticipanteDialog == null){
			itemParticipanteDialog = new javax.swing.JDialog(AdapitVirtualFrame.getInstance());
			itemParticipanteDialog.setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
			itemParticipanteDialog.setLayout(new java.awt.BorderLayout());
			itemParticipanteDialog.setSize(new Dimension(409, 264));
			itemParticipanteDialog.setResizable(false);
			itemParticipanteDialog.setModal(true);
			itemParticipanteDialog.setTitle("Dados do Item do Participante");
			itemParticipanteDialog.setLocation(UIUtil.getScreenCenter(itemParticipanteDialog));
			itemParticipanteDialog.add(getItemParticipanteCadastreForm(),java.awt.BorderLayout.CENTER);
			getItemParticipanteCadastreForm().getConfirmarButton().addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){					
					getItemParticipanteDialog().dispose();
				}
			});
		}
		return itemParticipanteDialog;
	}
	
	private ComercialSolutionItemCadastreForm itemParticipanteCadastreForm;
	
	private ComercialSolutionItemCadastreForm getItemParticipanteCadastreForm(){
		if (itemParticipanteCadastreForm == null){
			itemParticipanteCadastreForm = new ComercialSolutionItemCadastreForm(scheduledTraining);
			
		}
		return itemParticipanteCadastreForm;
	}
	
	public void editItemCompraCliente(ComercialSolutionItem ip, Participante p){
		//getItemProdutoDialog();
		
		getItemParticipanteCadastreForm().setParticipante(p);
		getItemParticipanteCadastreForm().setTreinamento(scheduledTraining);
		getItemParticipanteCadastreForm().editRegister(ip);
		getItemParticipanteDialog().setVisible(true);
		try {
			getItemParticipanteCadastreForm().cadastreItemProduto();
			getInscricoesList().updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void newItemCompraCliente(Participante p){
		getItemParticipanteCadastreForm().setParticipante(p);
		getItemParticipanteCadastreForm().setTreinamento(scheduledTraining);
		getItemParticipanteCadastreForm().newRegister();
		getItemParticipanteDialog().setVisible(true);
		try {
			getItemParticipanteCadastreForm().cadastreItemProduto();
			getInscricoesList().updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	private JButton editarItemCompraClienteButton;
	protected JButton getEditarItemCompraClienteButton(){
		if(editarItemCompraClienteButton == null){
			editarItemCompraClienteButton = new JButton("Editar Item");
			editarItemCompraClienteButton.setEnabled(false);
			editarItemCompraClienteButton.setIcon(AdapitVirtualFrame.getIcon("/imgs/user_edit.png",18,18));
			editarItemCompraClienteButton.setToolTipText("Editar o Item Desta Matrícula");
			editarItemCompraClienteButton.setSize(new java.awt.Dimension(100,24));
			editarItemCompraClienteButton.setLocation(new java.awt.Point(0,48));
			editarItemCompraClienteButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt) {
					int row = getInscricoesList().getSelectedRow();
					if (row >= 0)try{
						ComercialSolutionItem ip = scheduledTraining.getComercialSolutionItens().get(row);
						//Produto p = LocalServicesUtility.getInstance().getProdutoByIdItemProduto(ip.getId());
						if (ip != null){
							editItemCompraCliente(ip,ip.getInscrito());
						}
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}				
			});
		}
		return editarItemCompraClienteButton;
	}
/*	
	public class ProdutoFrameListener extends WindowAdapter{
		@Override
		public void windowClosed(WindowEvent evt) {	
			if (novoProduto){
				TrainingSolution p = getCadastrarProdutoDialog().getComercialSolutionCadastreForm().getSol();			
				newItemProduto(p);
				
			}else{
				TrainingSolution p = getCadastrarProdutoDialog().getComercialSolutionCadastreForm().getSol();			
				int row = getInscricoesList().getSelectedRow();
				if (row >= 0)try{
					ComercialSolutionItem ip = scheduledTraining.getComercialSolutionItens().get(row);
					editItemProduto(ip,p);
				}catch(Exception ex){
					ex.printStackTrace();
				}
				novoProduto = true;
			}
			getInscricoesList().updateTable();			
		}		
	}*/
	
	protected JButton getEditarInscricaoButton() {
		if (editarInscricaoButton == null) {
			editarInscricaoButton = new JButton("Editar");
			editarInscricaoButton.setSize(new java.awt.Dimension(112, 20));
			editarInscricaoButton.setEnabled(false);
			editarInscricaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/vcard_add.png")));
			editarInscricaoButton.setLocation(new java.awt.Point(0, 20));
			editarInscricaoButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					int row = getInscricoesList().getSelectedRow();
					if (row >= 0)try{
						ComercialSolutionItem ip = scheduledTraining.getComercialSolutionItens().get(row);
						AdapitVirtualFrame.getInstance()
						.editarParticipante(
								ip.getInscrito().getUser(), 
								(ip.getInscrito().getTipoPessoa() instanceof
										Fisica ? PersonType.Fisica : PersonType.Juridica));
						
					}catch(Exception ex0){
						
					}
				}				
			});
		}
		return editarInscricaoButton;
	}
	
/*	private boolean novoProduto=true;
	private class EditarProdutoTreinamento extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			int row = getInscricoesList().getSelectedRow();
			if (row >= 0)try{
				ComercialSolutionItem ip = scheduledTraining.getComercialSolutionItens().get(row);
				Participante p = ip.getComercialSolution();//LocalServicesUtility.getInstance().getProdutoByIdItemProduto(ip.getId());
				if (p != null){
					novoProduto = false;
					
					CadastrarTrainingSolutionDialog c = getCadastrarProdutoDialog();
					c.getComercialSolutionCadastreForm().editRegister(p);
					c.setLocation(UIUtil.getScreenCenter(c));
					c.setVisible(true);
					getInscricoesList().updateTable();
					getErrorPanel().removeAllElements();
					logErrorPanel.addAlert("Não esqueça de atualizar o valor e a descrição do lote! salve as alterações clicando no botão 'Atualizar Treinamento'");
					logErrorPanel.updateErrorList();
					logErrorPanel.setVisible(true);
					
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}*/

	protected JButton getRemoveProdutosButton() {
		if (removeInscricaoButton == null) {
			removeInscricaoButton = new JButton("Remover");
			removeInscricaoButton.setEnabled(false);
			removeInscricaoButton.setSize(new java.awt.Dimension(112, 20));
			removeInscricaoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/user_delete.png")));
			removeInscricaoButton.setLocation(new java.awt.Point(0, 40));
			removeInscricaoButton.addActionListener(new RemoverProdutoTreinamento());
		
		}
		return removeInscricaoButton;
	}
	
	public void removerInscricao(){
		int row = getInscricoesList().getSelectedRow();
		if (row >= 0)try{
			//String code = lote.getCodTreinamento();
			ComercialSolutionItem ip = scheduledTraining.getComercialSolutionItens().get(row);
			String desc = "";
			desc = ip.getInscrito().getNome();
			if (desc != null){
				int resp = JOptionPane.showConfirmDialog(ScheduledTrainingCadastreForm.this, "Você quer retirar o cliente " + '\n'+desc + '\n'+" da agenda " + scheduledTraining.getCodigo() + "?","Remover Cliente da Agenda",JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION){
					
					try {
													
							Session s = LocalServicesUtility.getInstance().openSession();
							try{
								s.beginTransaction();
								
								{					
									Participante part = (Participante) ip.getInscrito();
									part = (Participante) s.load(Participante.class, part.getId());
									Treinamento t = (Treinamento) s.load(Treinamento.class, scheduledTraining.getId());									
									part.getMeusTreinamentos().remove(t);
									t.getInteressados().remove(part);
									if (ip != null) s.delete(ip);
									s.merge(t);
									s.merge(part);
								}
								
								s.getTransaction().commit();
								
							}catch(Exception ex){
								ex.printStackTrace();
								s.getTransaction().rollback();
								throw ex;
							}finally{
								if (s != null) s.close();
							}
						
						
						getInscricoesList().updateTable();					
						
						JOptionPane.showMessageDialog(ScheduledTrainingCadastreForm.this, "Operação realizada com sucesso","Remover Produto",JOptionPane.INFORMATION_MESSAGE);
							
					} catch(org.hibernate.exception.ConstraintViolationException ex){
						ex.printStackTrace();
						JOptionPane.showMessageDialog(ScheduledTrainingCadastreForm.this, "O cliente não foi removido! Favor repetir a operação.","Problema ao remover cliente",JOptionPane.ERROR_MESSAGE);												
					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(ScheduledTrainingCadastreForm.this, "O cliente não foi removido!","Problema ao remover cliente",JOptionPane.ERROR_MESSAGE);
					}					
				}
				
			}			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private class RemoverProdutoTreinamento extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			removerInscricao();
		}
	}

	protected JPanel getScheduledTrainingButtonsPanel() {
		if (scheduledTrainingButtonsPanel == null) {
			scheduledTrainingButtonsPanel = new JPanel();
			scheduledTrainingButtonsPanel.setSize(new Dimension(628, 35));
			scheduledTrainingButtonsPanel.setLocation(new Point(16, 487));
			scheduledTrainingButtonsPanel.setLayout(new java.awt.FlowLayout());
			scheduledTrainingButtonsPanel.add(getSaveSchedTrainingButton());
			scheduledTrainingButtonsPanel.add(getNewSchedTrainingButton());
			scheduledTrainingButtonsPanel.add(getRemoveSchedTrainingButton());
		}
		return scheduledTrainingButtonsPanel;
	}

	protected JButton getSaveSchedTrainingButton() {
		if (saveSchedTrainingButton == null) {
			saveSchedTrainingButton = new JButton("Atualizar Treinamento");
			saveSchedTrainingButton.setSize(new java.awt.Dimension(80, 22));
			saveSchedTrainingButton.setEnabled(false);
			saveSchedTrainingButton.setIcon(getIcon("/imgs/basket_save.png"));
			saveSchedTrainingButton.setLocation(new java.awt.Point(0, 0));
			saveSchedTrainingButton.addActionListener(new AbstractAction(){
				public void doAction(ActionEvent evt){
					try {
						cadastreTreinamento();						
						getErrorPanel().removeAllElements();
						getErrorPanel().setVisible(false);
						//updateAgendaPanel(null);
						
						AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de treinamentos", "Operação de cadastro realizada com sucesso!");
						editarTreinamentoSelecionado();
					} catch (Exception e) {
						AdapitVirtualFrame.getInstance().showErrorDialog("Formulário de cadastro de treinamento",messages.getMessage(e.getMessage()));
					}
				}
			});
		}
		return saveSchedTrainingButton;
	}

	protected JButton getNewSchedTrainingButton() {
		if (newSchedTrainingButton == null) {
			newSchedTrainingButton = new JButton("Novo Treinamento");
			newSchedTrainingButton.setSize(new java.awt.Dimension(80, 22));
			newSchedTrainingButton.setIcon(getIcon("/imgs/basket_add.png"));
			newSchedTrainingButton.setLocation(new java.awt.Point(0, 22));
			newSchedTrainingButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					novoTreinamento();
				}
			});
			
		}
		return newSchedTrainingButton;
	}
	
	public void novoTreinamento(){
		initializeTreinamentos();
		String last ="";
		if (codigoComboBox.getItemCount() > 0){
			last = (String) codigoComboBox.getItemAt(codigoComboBox.getItemCount()-1);
			last = last.substring(0, last.length()-1);
		}else last="001";
		codigoComboBox.setSelectedItem(last);
		codigoComboBox.requestFocus();
		saveSchedTrainingButton.setEnabled(false);
	}

	protected JButton getRemoveSchedTrainingButton() {
		if (removeSchedTrainingButton == null) {
			removeSchedTrainingButton = new JButton("Remover Treinamento");
			removeSchedTrainingButton.setSize(new java.awt.Dimension(80, 22));
			removeSchedTrainingButton.setIcon(getIcon("/imgs/basket_delete.png"));
			removeSchedTrainingButton.setLocation(new java.awt.Point(0, 44));
			removeSchedTrainingButton.addActionListener(getRemoverAction());
		}
		return removeSchedTrainingButton;
	}
	
	private RemoverTreinamentoAction removerAction;
	public RemoverTreinamentoAction getRemoverAction(){
		if (removerAction == null){
			removerAction = new RemoverTreinamentoAction();
		}
		return removerAction;
	}
	
	public class RemoverTreinamentoAction extends AbstractAction{
		public void doAction(java.awt.event.ActionEvent e) {
			Treinamento l = schedTrainings.get(codigoComboBox.getSelectedItem());
			if (l != null){
				int resp = JOptionPane.showConfirmDialog(ScheduledTrainingCadastreForm.this, "Apagar o treinamento " + l.getCodigo(),"Apagar Treinamento",JOptionPane.YES_NO_OPTION);
				if (resp == JOptionPane.YES_OPTION){
					try {
						try {
							RemoteTreinamentoService.getInstance().delete(l, 
									((TurmaTreinamento)turmas.get(getDataTurmaComboBox().getSelectedIndex())).getId());
							initializeTreinamentos();
							saveSchedTrainingButton.setEnabled(false);
							JOptionPane.showMessageDialog(ScheduledTrainingCadastreForm.this, "O treinamento foi removido com sucesso!","Apagar Treinamento",JOptionPane.INFORMATION_MESSAGE);
						} catch (Exception e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(ScheduledTrainingCadastreForm.this, "O treinamento não foi removido!","Apagar Treinamento",JOptionPane.ERROR_MESSAGE);
						}
						/*Session s = LocalServicesUtility.getInstance().openSession();
						try {
							s.beginTransaction();
							Leilao leilao = (Leilao) s.load(Leilao.class, ((Leilao)leiloes.get(getDataLeilaoComboBox().getSelectedIndex())).getId());
							l = (Treinamento) s.load(Treinamento.class,l.getId());
							leilao.getTreinamentos().remove(l);
							Iterator<ItemProduto> it = l.getItensProduto().iterator();
							while(it.hasNext()){
								ItemProduto ip = it.next();
								ip.setTreinamento(null);
								s.save(ip);
							}
					
							s.delete(l);
							s.getTransaction().commit();
							
							initializeTreinamentos();
							atualizarTreinamentoButton.setEnabled(false);
							JOptionPane.showMessageDialog(TreinamentoCadastreForm.this, "O lote foi removido com sucesso!","Apagar Treinamento",JOptionPane.INFORMATION_MESSAGE);
							
						} catch (Exception e1) {
							e1.printStackTrace();
							s.getTransaction().rollback();
							JOptionPane.showMessageDialog(TreinamentoCadastreForm.this, "O lote não foi removido!","Apagar Treinamento",JOptionPane.ERROR_MESSAGE);
						}finally{
							s.close();
						}*/
						
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * This method initializes novoLeilaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getNovaTurmaButton() {
		if (novaTurmaButton == null) {
			novaTurmaButton = new JButton();
			novaTurmaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_add.png")));
			novaTurmaButton.setBounds(new Rectangle(493, 24, 105, 24));
			novaTurmaButton.setText("Nova");
			novaTurmaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().cadastrarTurma();
				}
			});
		}
		return novaTurmaButton;
	}

	/**
	 * This method initializes editarLeilaoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarTurmaButton() {
		if (editarTurmaButton == null) {
			editarTurmaButton = new JButton();
			editarTurmaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_edit.png")));
			editarTurmaButton.setBounds(new Rectangle(384, 24, 109, 24));
			editarTurmaButton.setText("Editar");
			editarTurmaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().editarTurma(turmas.get(getDataTurmaComboBox().getSelectedIndex()));					
				}
			});
		}
		return editarTurmaButton;
	}

	
	
	private AgendaTreinamentoCadastreForm agendaPanel;
	
	public AgendaTreinamentoCadastreForm getAgendaPanel(){
		if (agendaPanel == null){
			agendaPanel = new AgendaTreinamentoCadastreForm();
			agendaPanel.setTreinamentoCadastreForm(this);
		}
		return agendaPanel;
	}
	
	private void updateAgendaPanel(){		
		scheduledTraining = schedTrainings.get((String)codigoComboBox.getSelectedItem());		
		try{
						
			if (scheduledTraining.getAgendaTreinamento() == null)try {
				AgendaTreinamento agenda = RemoteTreinamentoService.getInstance().getScheduledTrainingScheduleIdByScheduledTrainingId(scheduledTraining.getId());
				scheduledTraining.setAgendaTreinamento(agenda);
			} catch (Exception e) {
				scheduledTraining.setAgendaTreinamento(null);
			}
			
			if (scheduledTraining.getAgendaTreinamento() != null){
				editRegister(scheduledTraining.getAgendaTreinamento(), scheduledTraining);
			}else{
				newRegister(scheduledTraining);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	/*private void updateAgendaPanel(Session s){		
		lote = lotes.get((String)codTreinamentoComboBox.getSelectedItem());
		boolean sessionlocal=false;
		try{
			if (s == null) {
				s = LocalServicesUtility.getInstance().openSession();
				sessionlocal=true;
			}			
			try {
				int id = (Integer) s.createQuery("select agenda.id from AgendaTreinamento agenda join agenda.lote lote where lote.id="+lote.getId()+" and agenda.id = lote.agendaTreinamento.id").uniqueResult();
				AgendaTreinamento agenda = new AgendaTreinamento();
				agenda.setId(id);
				lote.setAgendaTreinamento(agenda);
			} catch (Exception e) {
				lote.setAgendaTreinamento(null);
			}
			
			if (lote.getAgendaTreinamento() != null){
				editRegister(lote.getAgendaTreinamento(), lote, s);
			}else{
				newRegister(lote);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null && sessionlocal) s.close();
		}
		getAgendaPanel().limparLancesPanel();		
	}*/
	public void editRegister(AgendaTreinamento agendaTreinamento, Treinamento lote) {
		agendaPanel.setTreinamento(lote);
		try {
			agendaPanel.editRegister(agendaTreinamento);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}
/*	public void editRegister(AgendaTreinamento agendaTreinamento, Treinamento lote,Session s) {
		agendaPanel.setTreinamento(lote);
		agendaPanel.editRegister(agendaTreinamento,s);	
		if(agendaTreinamento.getStatus() == StatusAgenda.Encerrada_com_lances
			|| agendaTreinamento.getStatus() == StatusAgenda.Encerrada_sem_lances){
			statusAgendaLabel.setText("Lances Encerrados!");
			getLanceAgendaButton().setEnabled(true);
		}else if(agendaTreinamento.getStatus() == StatusAgenda.Confirmada){
			statusAgendaLabel.setText("Lances Abertos!");
			getLanceAgendaButton().setEnabled(true);
		}else if(agendaTreinamento.getStatus() == StatusAgenda.Cadastrada){
			statusAgendaLabel.setText("Definir Agenda!");
			getLanceAgendaButton().setEnabled(false);
		}else if(agendaTreinamento.getStatus() == StatusAgenda.Cancelada){
			statusAgendaLabel.setText("Agenda Cancelada!");
			getLanceAgendaButton().setEnabled(false);
		}else if(agendaTreinamento.getStatus() == StatusAgenda.Não_cadastrada){
			statusAgendaLabel.setText("Cadastrar a Agenda!");
			getLanceAgendaButton().setEnabled(false);
		}
	}*/
	
	public void newRegister(Treinamento lote){
		agendaPanel.setTreinamento(lote);
		try {
			agendaPanel.newRegister();
		} catch (Exception e) {
			e.printStackTrace();
		}
		agendaPanel.setEnabled(false);
		statusAgendaLabel.setText("Cadastrar a Agenda!");
		
	}


	/**
	 * This method initializes editarTreinamentoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarTreinamentoButton() {
		if (editarTreinamentoButton == null) {
			editarTreinamentoButton = new JButton();
			editarTreinamentoButton.setText("Editar");
			editarTreinamentoButton.setBounds(new Rectangle(1, 25, 133, 24));
			editarTreinamentoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/basket_edit.png")));
			editarTreinamentoButton.addActionListener(new AbstractAction(){
				@Override
				public void doAction(ActionEvent evt) {
					editarTreinamentoSelecionado();
				}				
			});
		}
		return editarTreinamentoButton;
	}

	private void editarTreinamentoSelecionado(){
		try {
			saveSchedTrainingButton.setEnabled(true);
			addInscricoesButton.setEnabled(true);
			receitaTextField.setEnabled(true);			
			
			if (codigoComboBox.getSelectedItem() != null){
				if (scheduledTraining != null && scheduledTraining.getCodigo() != null
					&&((JComboBox)getCodigoComboBox()).getSelectedItem() != null
					&& scheduledTraining.getCodigo().equals(((JComboBox)getCodigoComboBox()).getSelectedItem())){
					
				}else{
					scheduledTraining = schedTrainings.get(codigoComboBox.getSelectedItem());				
				}
				if (scheduledTraining != null) editRegister(scheduledTraining);
				
			}else{
				System.out.println("Treinamento não selecionado!");
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
			refreshLeiloesButton.setBounds(new Rectangle(605, 0, 22, 22));
			refreshLeiloesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshLeiloesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateTurmaList();
				}
			});
		}
		return refreshLeiloesButton;
	}
	
	private JButton refreshTreinamentosButton;
	
	private JButton getRefreshTreinamentosButton() {
		if (refreshTreinamentosButton == null) {
			refreshTreinamentosButton = new JButton();
			refreshTreinamentosButton.setBounds(new Rectangle(115, 0, 22, 22));
			refreshTreinamentosButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshTreinamentosButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateScheduledTrainingList();
				}
			});
		}
		return refreshTreinamentosButton;
	}
	
	public static String getFormatedDate(Date d){
		return AdapitVirtualFrame.getInstance().format(d).trim();
	}
	@SuppressWarnings("unchecked")
	public void updateTurmaList(){	
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
					getDataTurmaComboBox().addItem(formatDataTreinamento(l));
					i++;
				}
			}			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (turmas == null || turmas.size() == 0) getDataTurmaComboBox().setEnabled(false);
		else getDataTurmaComboBox().setEnabled(true);			
	}
/*	public void updateLeilaoList(){	
		getDataLeilaoComboBox().removeAllItems();
		leiloes.clear();
		Session s = LocalServicesUtility.getInstance().openSession();		
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
					getDataLeilaoComboBox().addItem(formatDataLeilao(l));
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
	}*/
	
	private List<TurmaTreinamento> turmas = new ArrayList<TurmaTreinamento>();  //  @jve:decl-index=0:

	private JLabel estadoTreinamentoLabel = null;

	private JComboBox scheduledTrainingStateComboBox = null;

	private JLabel resumoLabel = null;

	private JTextField resumoTreinamentoTextField = null;

	private JCheckBox pagtoQuitadoCheckBox = null;

	@SuppressWarnings("unchecked")
	public void updateScheduledTrainingList(){	
		
		if (schedTrainings != null) schedTrainings.clear();
		else{
			initializeTreinamentos();
		}
		codigoComboBox.removeAllItems();
		refreshingCodTreinamentoComboBox=true;
		
		try {
			int index = dataTurmaComboBox.getSelectedIndex();
			if (index < 0) return;
			TurmaTreinamento leilao = turmas.get(index);
			Iterator<Treinamento> it = 
				RemoteTreinamentoService.getInstance().listAllScheduledTrainingByTurmaId(leilao.getId()).iterator();
			{
				int i=0;
				while(it.hasNext()){					
					Treinamento l = it.next();
					l.setTurma(leilao);														
					schedTrainings.put(l.getCodigo(),l);
					codigoComboBox.addItem(l.getCodigo());
					i++;
				}
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		refreshingCodTreinamentoComboBox=false;
					
	}
	/*public void updateTreinamentosList(){	
		
		if (lotes != null) lotes.clear();
		else{
			initializeTreinamentos();
		}
		codTreinamentoComboBox.removeAllItems();
		refreshingCodTreinamentoComboBox=true;
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			int index = dataLeilaoComboBox.getSelectedIndex();
			if (index < 0) return;
			Leilao leilao = leiloes.get(index);
			Iterator it = s.createQuery("select lote.id, lote.codTreinamento from Treinamento lote where lote.leilao.id ="+leilao.getId()+" order by lote.codTreinamento ASC").list().iterator();
			{
				int i=0;
				while(it.hasNext()){
					
					Object obj[] = (Object[])it.next();
					
					Treinamento l = new Treinamento();
					l.setLeilao(leilao);
					
					l.setId(((Integer)obj[0]).intValue());
					l.setCodTreinamento((String)obj[1]);									
					lotes.put(l.getCodTreinamento(),l);
					codTreinamentoComboBox.addItem(l.getCodTreinamento());
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
		refreshingCodTreinamentoComboBox=false;
					
	}*/
	
	/**
	 * This method initializes estadoTreinamentoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getScheduledTrainingStateComboBox() {
		if (scheduledTrainingStateComboBox == null) {
			scheduledTrainingStateComboBox = new JComboBox();
			scheduledTrainingStateComboBox.setEditable(false);
			scheduledTrainingStateComboBox.setBounds(new Rectangle(120, 220, 247, 22));
			for (int i=0; i < ScheduledTrainingStatus.values().length;i++){
				scheduledTrainingStateComboBox.addItem(ScheduledTrainingStatus.values()[i].name().replace("_"," "));
			}
		}
		return scheduledTrainingStateComboBox;
	}

	/**
	 * This method initializes resumoTreinamentoTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getResumoTreinamentoTextField() {
		if (resumoTreinamentoTextField == null) {
			resumoTreinamentoTextField = new JTextField();
			resumoTreinamentoTextField.setBounds(new Rectangle(120, 190, 398, 22));
		}
		return resumoTreinamentoTextField;
	}

	/**
	 * This method initializes retiradoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getPagtoQuitadoCheckBox() {
		if (pagtoQuitadoCheckBox == null) {
			pagtoQuitadoCheckBox = new JCheckBox();
			pagtoQuitadoCheckBox.setBounds(new Rectangle(376, 222, 132, 21));
			pagtoQuitadoCheckBox.setEnabled(false);
			pagtoQuitadoCheckBox.setText("Pagamento Quitado");
		}
		return pagtoQuitadoCheckBox;
	}

	/**
	 * This method initializes despezaPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getDespezaPanel() {
		if (despezaPanel == null) {
			despezaPanel = new JPanel();
			despezaPanel.setLayout(new GridLayout(2,1));
			despezaPanel.setBounds(new Rectangle(520, 280, 98, 46));
			despezaPanel.add(despezaLabel, null);
			despezaPanel.add(getDespezaTextField(), null);
		}
		return despezaPanel;
	}

	/**
	 * This method initializes despezaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getDespezaTextField() {
		if (despezaTextField == null) {
			despezaTextField = new JTextField();
			this.binder.addBindProperty(this.scheduledTraining, this.despezaTextField,
			"custo");
		}
		return despezaTextField;
	}

	/**
	 * This method initializes nomeTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getNomeTextField() {
		if (nomeTextField == null) {
			nomeTextField = new JTextField();
			nomeTextField.setBounds(new Rectangle(15, 271, 500, 22));
			nomeTextField.setEditable(false);
		}
		return nomeTextField;
	}

	/**
	 * This method initializes trainingCodTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTrainingCodTextField() {
		if (trainingCodTextField == null) {
			trainingCodTextField = new JTextField();
			trainingCodTextField.setBounds(new Rectangle(291, 247, 58, 22));
			trainingCodTextField.setEditable(false);
		}
		return trainingCodTextField;
	}

	/**
	 * This method initializes cargaHorariaTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCargaHorariaTextField() {
		if (cargaHorariaTextField == null) {
			cargaHorariaTextField = new JTextField();
			cargaHorariaTextField.setBounds(new Rectangle(454, 247, 61, 22));
			cargaHorariaTextField.setEditable(false);
		}
		return cargaHorariaTextField;
	}

	/**
	 * This method initializes trainingSolScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getTrainingSolScrollPane() {
		if (trainingSolScrollPane == null) {
			trainingSolScrollPane = new JScrollPane();
			trainingSolScrollPane.setBounds(new Rectangle(15, 295, 500, 58));
			trainingSolScrollPane.setViewportView(getResumoTrainSolTextPane());
		}
		return trainingSolScrollPane;
	}

	/**
	 * This method initializes resumoTrainSolTextPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getResumoTrainSolTextPane() {
		if (resumoTrainSolTextPane == null) {
			resumoTrainSolTextPane = new JTextPane();
			resumoTrainSolTextPane.setEditable(false);
		}
		return resumoTrainSolTextPane;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(449, 389));
				gui.add(new ScheduledTrainingCadastreForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	private static Icon getIcon(String name) {

		try {
			java.net.URL imURL = java.lang.Class.class.getResource(name);
			if (imURL != null) {
				java.awt.Image image = new javax.swing.ImageIcon(imURL)
						.getImage();
				if (image != null) {
					image = image.getScaledInstance(18, 18,
							java.awt.Image.SCALE_SMOOTH);
					javax.swing.Icon icon = new javax.swing.ImageIcon(image);
					return icon;
				}
			}
		} catch (java.lang.StackOverflowError e) {
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}// end of catch block
		return null;
	}
	
	private float soma=0;

	private JLabel statusAgendaLabel = null;

	private JLabel receitaValueLabel = null;

	private JLabel despezaLabel = null;

	private JPanel despezaPanel = null;

	private JTextField despezaTextField = null;

	private JTextField nomeTextField = null;

	private JLabel nomeTreinamentoLabel = null;

	private JTextField trainingCodTextField = null;

	private JLabel cargaHoraLabel = null;

	private JTextField cargaHorariaTextField = null;

	private JScrollPane trainingSolScrollPane = null;

	private JTextPane resumoTrainSolTextPane = null;
	@SuppressWarnings("unchecked")
	private class ProdutosList extends JXTable {

		private List elements;

		public void setElements(List elements) {
			this.elements = elements;
		}

		public List getElements() {
			return this.elements;
		}

		@SuppressWarnings("deprecation")
		public ProdutosList() {

			super();
			this.setModel(new ProdutosListModel(null));
			getColumnModel().getColumn(0).setPreferredWidth(200);
			this.addPropertyChangeListener(new ProdutosListPropertyChangeListener());
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			//updateTable();
			setModel(new ProdutosListModel(null));
			getColumnModel().getColumn(0).setPreferredWidth(200);
			updateUI();
		}

		@SuppressWarnings("deprecation")
		public ProdutosList(List elements) {

			super();
			this.elements = elements;
			this.setModel(new ProdutosListModel(null));
			this.addPropertyChangeListener(new ProdutosListPropertyChangeListener());
			getColumnModel().getColumn(0).setPreferredWidth(200);
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			updateTable();
		}

		public void setDefineCellRenderers() {

			/*
			 * LabelCellRenderer labelRenderer = new LabelCellRenderer();
			 * ComboBoxCellRenderer comboBoxRenderer = new
			 * ComboBoxCellRenderer();
			 */
		}

		/*public void updateTableOld() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][5];
				int i = 0;
				Session s = null;
				try{
					
					s = LocalServicesUtility.getInstance().openSession();
					while (it.hasNext()) {
						Object obj = it.next();
						if (obj instanceof com.adapit.portal.entidades.ItemProduto) {
							com.adapit.portal.entidades.ItemProduto itemProduto = (com.adapit.portal.entidades.ItemProduto) obj;
							
							
							try {
								values[i][0] =	LocalServicesUtility.getInstance().getDescricaoProdutoByIdItemProduto(itemProduto.getId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							try {
								values[i][1] =	LocalServicesUtility.getInstance().getCategoriaProdutoByIdItemProduto(itemProduto.getId());
							} catch (Exception e) {
								e.printStackTrace();
							}
							
							ItemProduto ip = (ItemProduto) s.load(ItemProduto.class,itemProduto.getId());
							
							values[i][2] = ip.getProduto().getAvaliacao();//itemProduto.getValorTotal()/itemProduto.getQtd();
							s.close();
							
							values[i][3] = itemProduto.getQtd();
							values[i][4] = itemProduto.getValorTotal();
							i++;
						}
					}// End of while Loop
				}catch(Exception ex){
					ex.printStackTrace();
				}finally{
					if (s != null) s.close();
				}
				setModel(new ProdutosListModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				updateUI();
			} else {
				setModel(new ProdutosListModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(200);
				updateUI();
			}
		}*/
		
		public void updateTable() {			
			soma=0;
			try{
				//"Participante","Confirmado","Quitou Pagto","Desconto","A Pagar"
				//idp,nomep, conf,quit,desc,val
				List list = RemoteTreinamentoService.getInstance().getComercialSolutionItensByScheduledTrainingId(scheduledTraining.getId());
				scheduledTraining.setComercialSolutionItens(new ArrayList<ComercialSolutionItem>());
				if (elements == null) elements = new ArrayList();
				elements.clear();
				if (list != null && list.size() > 0) {
					int ne = list.size();
					java.util.Iterator<ComercialSolutionItem> it = list.iterator();
					java.lang.Object values[][] = new java.lang.Object[ne][5];
					int i = 0;					
					while (it.hasNext()) {						
						ComercialSolutionItem ip = (ComercialSolutionItem) it.next();
						
						Object[] prods = RemoteTreinamentoService.getInstance().getScheduledTrainingItemPropertiesByItemId(ip.getId());
						Participante p = new Participante();
						p.setId((Long) prods[0]);
						p.setNome((String)prods[1]);
						ip.setInscrito(p);
						ip.setTreinamento(scheduledTraining);
						
						values[i][0] = p.getNome();
						values[i][1] = prods[2];
						values[i][2] = prods[3];
						values[i][3] = prods[4];
						values[i][4] = Moeda.getValorReal((Float) prods[5]);
						soma += (Float) prods[5];
						
						
						if (scheduledTraining.getComercialSolutionItens() == null) scheduledTraining.setComercialSolutionItens(new ArrayList());
						scheduledTraining.getComercialSolutionItens().add(ip);
											
						i++;
						elements.add(ip);
					}
								
					setModel(new ProdutosListModel(values));
					getColumnModel().getColumn(0).setPreferredWidth(200);
					updateUI();
				} else {
					setModel(new ProdutosListModel(null));
					getColumnModel().getColumn(0).setPreferredWidth(200);
					updateUI();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
/*		public void updateTable() {
			Session s = null;
			soma=0;
			try{
				s = LocalServicesUtility.getInstance().openSession();
				String query="select ip from ItemProduto ip where ip.lote.id="+lote.getId();
				List list = s.createQuery(query).list();
				lote.setItensProduto(new ArrayList<ItemProduto>());
				if (elements == null) elements = new ArrayList();
				elements.clear();
				if (list != null && list.size() > 0) {
					int ne = list.size();
					java.util.Iterator<ItemProduto> it = list.iterator();
					java.lang.Object values[][] = new java.lang.Object[ne][5];
					int i = 0;
					
					while (it.hasNext()) {
						
						ItemProduto ip = (ItemProduto) it.next();
						String queryprod = "select ip.produto.id, ip.produto.dataCriacao, ip.produto.descricao, ip.produto.avaliacao from ItemProduto ip where ip.id="+ip.getId();
						Object[] prods = (Object[]) s.createQuery(queryprod).uniqueResult();
						String catname = (String) s.createQuery("select prod.categoria.nome from Produto prod where prod.id="+prods[0]).uniqueResult();
						

						values[i][0] = prods[2];
						values[i][1] = catname;
						values[i][2] = Moeda.getValorReal((Float)  prods[3]);
						values[i][3] = ip.getQtd();
						values[i][4] = Moeda.getValorReal(ip.getValorTotal());
						soma += ip.getValorTotal();
						
						Produto prod = new Produto();
						prod.setId((Integer) prods[0]);
						prod.setAvaliacao((Float)prods[3]);
						prod.setDescricao((String)prods[2]);
						prod.setDataCriacao((Date)prods[1]);
						ip.setProduto(prod);
						
						if (lote.getItensProduto() == null) lote.setItensProduto(new ArrayList());
						lote.getItensProduto().add(ip);
											
						i++;
						elements.add(ip);
					}
								
					setModel(new ProdutosListModel(values));
					getColumnModel().getColumn(0).setPreferredWidth(200);
					updateUI();
				} else {
					setModel(new ProdutosListModel(null));
					getColumnModel().getColumn(0).setPreferredWidth(200);
					updateUI();
				}
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
		}*/

		private class ProdutosListModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class,Boolean.class,Boolean.class,
					Integer.class, String.class };

			boolean canEdit[] = new boolean[] {false, false, false, false, false };

			public ProdutosListModel(Object[][] values) {

				super(
						values,
						new String[] {"Participante","Confirmado","Quitou Pagto","Desconto","Valor" });
			}

			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		private class ProdutosListPropertyChangeListener implements
				PropertyChangeListener {

			public void propertyChange(PropertyChangeEvent e) {

				com.adapit.portal.ui.forms.treinamento.ScheduledTrainingCadastreForm.ProdutosList jt = (com.adapit.portal.ui.forms.treinamento.ScheduledTrainingCadastreForm.ProdutosList) e
						.getSource();
				//int col = jt.getSelectedColumn();
				int row = jt.getSelectedRow();
				if (jt.getElements() != null && row > -1)
					try {
						java.lang.Object obj = jt.getElements().get(row);
						if (obj instanceof com.adapit.portal.entidades.ComercialSolutionItem) {
							//com.adapit.portal.entidades.ItemProduto itemProduto = (com.adapit.portal.entidades.ItemProduto) obj;
							// if (col ==1)
							// itemProduto.getNome(((java.lang.String)jt.getValueAt(row,
							// col)));
							// if (col ==2)
							// itemProduto.setQtd((java.lang.Integer.parseInt((java.lang.String)jt.getValueAt(row,
							// col)));
							// if (col ==3)
							// itemProduto.setValorTotal(((java.lang.String)jt.getValueAt(row,
							// col)));
						}
					} catch (java.lang.Exception ex) {
						ex.printStackTrace();
					}
			}

		}

	}
	
	public static String formatDataTreinamento(TurmaTreinamento l){		
		try {
			if (l.getDataTreinamento() != null)	
				return "Data do Treinamento: "+getFormatedDate(l.getDataTreinamento());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setSelectedTurma(TurmaTreinamento leilao) {
		updatingTurmaComboBox=true;
		//updateLeilaoList();
		initializeTreinamentos();
		updatingTurmaComboBox=false;
		getDataTurmaComboBox().setSelectedItem(formatDataTreinamento(leilao));		
	}

	public void setSelectedTreinamento(Treinamento l) {		
		editRegister(l);
	}
	
	public void setSelectedTreinamentoRefreshByTurma(Treinamento l, String turmaDate) {
		updatingTurmaComboBox=true;
		updateTurmaList();
		getDataTurmaComboBox().setSelectedItem(turmaDate);
		refreshingCodTreinamentoComboBox=true;
		//updateTreinamentosList();
		initializeTreinamentos();
		refreshingCodTreinamentoComboBox=false;
		codigoComboBox.setSelectedItem(l.getCodigo());
		editarTreinamentoSelecionado();
		/*Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			List<String> list= s.createQuery("select l.codTreinamento from Treinamento l where l.leilao.id="+leilaoId).list();
			for(String str : list){
				codTreinamentoComboBox.addItem(str);
				updateTreinamentosList();
				updateLeilaoList();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}*/
		//editRegister(l);
		updatingTurmaComboBox=false;
	}

	public JLabel getStatusAgendaLabel() {
		return statusAgendaLabel;
	}

	public void setStatusAgendaLabel(JLabel statusAgendaLabel) {
		this.statusAgendaLabel = statusAgendaLabel;
	}

	public Treinamento getScheduledTraining() {
		return scheduledTraining;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"