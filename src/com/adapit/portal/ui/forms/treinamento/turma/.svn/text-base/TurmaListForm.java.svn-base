package com.adapit.portal.ui.forms.treinamento.turma;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.ClassificacaoTreinamento;
import com.adapit.portal.entidades.PessoaEmDivulgacao;
import com.adapit.portal.entidades.CondicaoParticipacaoTreinamento;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.TipoExecucaoTreinamento;
import com.adapit.portal.entidades.TipoPacoteTreinamento;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.entidades.TurmaTreinamento;
import com.adapit.portal.entidades.TurnoTreinamento;
import com.adapit.portal.services.TrainingClassFilterType;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.services.remote.RemoteTurmaService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.AutoCompletion;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;

@SuppressWarnings({"serial","unchecked","unused","static-access"})
public class TurmaListForm extends JPanel {

	private JTabbedPane tabbedPane;

	private JPanel baseTab;

	@SuppressWarnings("unused")
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	@SuppressWarnings({ "unchecked", "unused" })
	private Map hashComps = new java.util.HashMap();

	private ResourceMessage messages = SpringResourceMessage.getInstance();

	private JPanel dataHoraPanel;

	private DateHourChooser dataOnlinePrimeira;

	private TurmaTreinamento turma = new TurmaTreinamento();  //  @jve:decl-index=0:

	private JLabel dataOnlineDateFieldChooserLabel;

	private DateHourChooser dataPresencialPrimeira;

	private JLabel dataPresencialDateFieldChooserLabel;

	private JPanel dataHoraPanel2;

	private DateHourChooser dataOnline2;

	private JLabel dataOnline2Label;

	private DateHourChooser dataPresencial2;

	private JLabel dataPresencial2Label;

	private JPanel procedenciaTipoTurmaPanel;

	private JComboBox classificacaoComboBox;

	private JLabel procedenciaComboBoxLabel;

	private JComboBox subclassificacaoComboBox;

	private JLabel tipoTurmaLabel;

	private JLabel executarTurmaComoLabel;

	@SuppressWarnings("unused")
	private CondicaoParticipacaoTreinamento instrucParticipacao = new CondicaoParticipacaoTreinamento();  
	

	@SuppressWarnings("unused")
	private boolean processFocus = true;

	@SuppressWarnings("unused")
	private JLabel condicaoParticipacaoTextAreaLabel;

	private JLabel localPresencialLabel = null;

	private JComboBox executarComoComboBox = null;
	
	private JComboBox regrasExecucaoComboBox = null;

	private JComboBox enderecoComboBox = null;

	private JButton refreshEnderecoButton = null;
	
	private JPanel dadosButtonsPanel;

	private JButton novaTurmaButton;

	private JButton removerTurmaButton;

	private JButton editarTurmaButton;

	@SuppressWarnings("unused")
	private JButton editarAgendaTurmaButton;

	public TurmaListForm() {
		initialize();
		updateEnderecoList();
	}

	private void initialize() {
		setSize(new Dimension(648, 466));
		setLocation(new java.awt.Point(0, 0));
		setLayout(null);
		add(getTabbedPane());
		add(getListarButton());
		add(getHelpButton());
		add(getResultadosTabbedPane());
		add(getOrdenarPorComboBox());
		add(getDadosButtonsPanel());
		ordenarPorLabel = new JLabel();
		ordenarPorLabel.setBounds(new Rectangle(217, 10, 114, 26));
		ordenarPorLabel.setText("Ordenar Por:");
		add(ordenarPorLabel);
		
		reportResultsLabel = new JLabel();
		reportResultsLabel.setBounds(new Rectangle(10, 151, 266, 20));
		reportResultsLabel.setText("");
		add(reportResultsLabel, null);
		add(getResultNumberPanel());
	}

	private JLabel ordenarPorLabel = null;

	private JComboBox ordenarPorComboBox = null;
	
	private JComboBox getOrdenarPorComboBox() {
		if (ordenarPorComboBox == null) {			
			ordenarPorComboBox = new JComboBox();
			ordenarPorComboBox.setBounds(new Rectangle(343, 10, 191, 26));
			ordenarPorComboBox.addItem("Classificação");
			ordenarPorComboBox.addItem("Sub-classificação");
			ordenarPorComboBox.addItem("Regra de Execução");
			ordenarPorComboBox.addItem("Tipo do Leilão");
		}
		return ordenarPorComboBox;
	}
	

	
	@SuppressWarnings("unchecked")
	private void listar(TrainingClassFilterType filtro, int left) throws Exception{
		if (filtro == null) throw new Exception("O tipo do filtro não pode ser nulo");
		
		boolean usarEndereco = false;
		int idEndereco=0;
		boolean usarData=false;
		Date dataTreinamento=null;
		Date dataEncerramento=null;
		
		boolean usarRegras=false;
		ClassificacaoTreinamento classificacaoLeilao=null;
		TipoPacoteTreinamento subClassificacaoLeilao=null;
		TurnoTreinamento regraExecucaoLeilao=null;
		TipoExecucaoTreinamento tipoExecucaoLeilao=null;
		int orderBy=0;
		
		if (getUsarEnderCheckBox().isSelected()){
			usarEndereco=true;
			idEndereco = ((Endereco)enderecos.get(getEnderecoComboBox().getSelectedIndex())).getId();			
		}
		if (getDataCheckBox().isSelected()){
			usarData = true;			
			
				if (getDataPresencialPrimeira().getCalendarButton().isEnabled()
						&& getDataPresencialPrimeira().getDate() != null){
					dataTreinamento = getDataPresencialPrimeira().getDate();
				}
				if (getDataPresencialSegunda().getCalendarButton().isEnabled()
					&& getDataPresencialSegunda().getDate() != null){
					dataEncerramento = getDataPresencialSegunda().getDate();					
				}
				
		}
		
		if (getUsarRegrasCheckBox().isSelected()){
			usarRegras = true;
			classificacaoLeilao = ClassificacaoTreinamento.valueOf(((String)getClassificacaoComboBox().getSelectedItem()).replace(" ","_"));
			subClassificacaoLeilao = TipoPacoteTreinamento.valueOf(((String)subclassificacaoComboBox.getSelectedItem()).replace(" ","_"));
			regraExecucaoLeilao = TurnoTreinamento.valueOf(((String)regrasExecucaoComboBox.getSelectedItem()).replace(" ","_"));
			tipoExecucaoLeilao = TipoExecucaoTreinamento.valueOf(((String)executarComoComboBox.getSelectedItem()).replace(" ","_"));			
		}
		if (filtro == TrainingClassFilterType.Turma){
			if (getOrdenarPorComboBox().getSelectedIndex() == 0)
				orderBy=0;
			else if (getOrdenarPorComboBox().getSelectedIndex() == 1)
				orderBy=1;
			else if (getOrdenarPorComboBox().getSelectedIndex() == 2)
				orderBy=2;
			else if (getOrdenarPorComboBox().getSelectedIndex() == 3)
				orderBy=3;
		}
		
		try {
			Object result = RemoteTurmaService.getInstance().listTrainingClassAccordingTo(filtro, left,
					usarEndereco,idEndereco, usarData,
					dataTreinamento, dataEncerramento,
					usarRegras, classificacaoLeilao,
					subClassificacaoLeilao,
					regraExecucaoLeilao,
					tipoExecucaoLeilao,orderBy);
			
			if (filtro == TrainingClassFilterType.Turma){
				List<Treinamento> leiloes = (List<Treinamento>) result;
				setElements(leiloes);
				getBaseTable().updateTable();
				getDatasTable().updateTable();
				getOutrosTable().updateTable();
			}else if(filtro == TrainingClassFilterType.TurmaCount){
				countFirst = (Integer) result;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/*private void listar(TipoFiltroLeilao filtro, int left) throws Exception{
		if (filtro == null) throw new Exception("O tipo do filtro não pode ser nulo");
		String query = null;
		if (filtro == TipoFiltroLeilao.Leilao) query= "select l from Leilao l";
		else if (filtro == TipoFiltroLeilao.LeilaoCount) query= "select count(l) from Leilao l";
		
		String comparator1=null;
		
		boolean whereused=false;
		if (getUsarEnderCheckBox().isSelected()){
			comparator1=" l.enderecoPresencial.id="+((Endereco)enderecos.get(getEnderecoComboBox().getSelectedIndex())).getId();			
		}
		if (comparator1 != null){
			query += " where " + comparator1;
			whereused=true;
		}
		
		if (getDataCheckBox().isSelected()){
			System.err.println("Filtro por Data");
			if (!whereused) {
				query+=" where ";
			}
			else query+=" and ";
				
			boolean and=false;
				if (getDataPresencialPrimeira().getCalendarButton().isEnabled()
						&& getDataPresencialPrimeira().getDate() != null){
					query+=" date(l.dataPresencialPrimeira)='"+LeilaoVirtualFrame.formatDateTime(getDataPresencialPrimeira().getDate(),"EN-US")+"'" ;
					and = true;
				}
				if (getDataPresencialSegunda().getCalendarButton().isEnabled()
					&& getDataPresencialSegunda().getDate() != null){
					if (and) query+=" and ";
					query+=" date(l.dataPresencialSegunda)='"+LeilaoVirtualFrame.formatDateTime(getDataPresencialSegunda().getDate(),"EN-US")+"'";	
					and = true;
				}
				if (getDataOnlinePrimeira().getCalendarButton().isEnabled()
					&& getDataOnlinePrimeira().getDate() != null){
					if (and) query+=" and ";
					query+=" date(l.dataOnlinePrimeira)='"+LeilaoVirtualFrame.formatDateTime(getDataOnlinePrimeira().getDate(),"EN-US")+"'";	
					and = true;
				}
				if (getDataOnlineSegunda().getCalendarButton().isEnabled()
					&& getDataOnlineSegunda().getDate() != null){
					if (and) query+=" and ";
					query+=" date(l.dataOnlineSegunda)='"+LeilaoVirtualFrame.formatDateTime(getDataOnlineSegunda().getDate(),"EN-US")+"' " ;	
					and = true;
				}
			
		}
		
		if (getUsarRegrasCheckBox().isSelected()){
			System.err.println("Filtro por Regras");
			if (!whereused) query+=" where l.classificacao="+ClassificacaoLeilao.valueOf(((String)getClassificacaoComboBox().getSelectedItem()).replace(" ","_")).ordinal()+" and " +
					"l.subClassificacao="+SubClassificacaoLeilao.valueOf(((String)subclassificacaoLeilaoComboBox.getSelectedItem()).replace(" ","_")).ordinal()+" and " +
					"l.regraExecucaoLeilao="+RegraExecucaoLeilao.valueOf(((String)regrasExecucaoLeilaoComboBox.getSelectedItem()).replace(" ","_")).ordinal()+" and " +
					"l.tipoExecucao="+TipoExecucaoLeilao.valueOf(((String)executarComoComboBox.getSelectedItem()).replace(" ","_")).ordinal();
			else query+=" and l.classificacao="+ClassificacaoLeilao.valueOf(((String)getClassificacaoComboBox().getSelectedItem()).replace(" ","_")).ordinal()+" and " +
			"l.subClassificacao="+SubClassificacaoLeilao.valueOf(((String)subclassificacaoLeilaoComboBox.getSelectedItem()).replace(" ","_")).ordinal()+" and " +
			"l.regraExecucaoLeilao="+RegraExecucaoLeilao.valueOf(((String)regrasExecucaoLeilaoComboBox.getSelectedItem()).replace(" ","_")).ordinal()+" and " +
			"l.tipoExecucao="+TipoExecucaoLeilao.valueOf(((String)executarComoComboBox.getSelectedItem()).replace(" ","_")).ordinal();
		}
		if (filtro == TipoFiltroLeilao.Leilao){
			if (getOrdenarPorComboBox().getSelectedIndex() == 0)
				query+=" order by l.classificacao, l.subClassificacao, l.regraExecucaoLeilao, l.tipoExecucao ASC";
			else if (getOrdenarPorComboBox().getSelectedIndex() == 1)
				query+=" order by l.subClassificacao, l.classificacao, l.regraExecucaoLeilao, l.tipoExecucao ASC";
			else if (getOrdenarPorComboBox().getSelectedIndex() == 2)
				query+=" order by l.regraExecucaoLeilao, l.subClassificacao, l.classificacao, l.tipoExecucao ASC";
			else if (getOrdenarPorComboBox().getSelectedIndex() == 3)
				query+=" order by l.tipoExecucao, l.regraExecucaoLeilao, l.subClassificacao, l.classificacao  ASC";
		}
		
		
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			if (filtro == TipoFiltroLeilao.Leilao){
				List<Lote> leiloes = s.createQuery(query).setMaxResults(max).setFirstResult(max*left).list();
				getBaseTable().setElements(leiloes);
				getBaseTable().updateTable();
				getDatasTable().updateTable();
			}else if(filtro == TipoFiltroLeilao.LeilaoCount){
				countFirst = (Integer) s.createQuery(query).uniqueResult();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
	}*/
	
	private Integer countFirst=0;  //  @jve:decl-index=0:
	
	int max=15;
	int total;
	int secBegin=0;
	int secCount;
	private void listar(){
		try {
			listar(TrainingClassFilterType.TurmaCount,0);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		total = countFirst;
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
								listar(TrainingClassFilterType.Turma,(i-1)*max);
								reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
							}
							else{
								int oldmax = max;
								max = dif;
								listar(TrainingClassFilterType.Turma,(i-1)*max);	
								
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
							listar(TrainingClassFilterType.Turma,(i-1)*max);
							reportResultsLabel.setText("Encontrados "+total+" itens. Mostrando "+(i));
							
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
				listar(TrainingClassFilterType.Turma,0);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				listar(TrainingClassFilterType.Turma,0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		resultNumberPanel.updateUI();
	}

	

	private JPanel resultNumberPanel = null;

	

	private JLabel reportResultsLabel = null;
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
			resultNumberPanel.setBounds(new Rectangle(281, 151, 405, 21));
			
		}
		return resultNumberPanel;
	}
	
	int buttons=10;
	
	private void changeResultNumberPanel() {
		resultNumberPanel.removeAll();
		GridLayout g = new GridLayout(1, buttons);
		g.setHgap(1);
		g.setVgap(1);
		resultNumberPanel.setLayout(g);

	}
	
	protected JTabbedPane getTabbedPane() {

		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.setSize(new Dimension(629, 108));
			tabbedPane.setLocation(new Point(10, 40));

			tabbedPane.add(getBaseTab(), "Filtrar pelo local presencial");
			
			tabbedPane.add(getRestricoesPanel(),"Filtrar pelo tipo");
			tabbedPane.add(getUsarDataPanel(), "Filtrar por datas");
		}
		return tabbedPane;
	}

	protected JPanel getBaseTab() {

		if (baseTab == null) {
			localPresencialLabel = new JLabel();
			localPresencialLabel.setBounds(new Rectangle(10, 10, 104, 22));
			localPresencialLabel.setText("Endereço Presencial:");
			baseTab = new JPanel();
			baseTab.setSize(new java.awt.Dimension(573, 271));
			baseTab.setLocation(new java.awt.Point(0, 3));
			baseTab.setLayout(null);
			baseTab.add(localPresencialLabel, null);
			baseTab.add(getEnderecoComboBox(), null);
			baseTab.add(getRefreshEnderecoButton(), null);
			baseTab.add(getUsarEnderCheckBox(), null);
			
		}
		return baseTab;
	}

	private JTabbedPane resultadosTabbedPane;
	protected JTabbedPane getResultadosTabbedPane() {

		if (resultadosTabbedPane == null) {
			resultadosTabbedPane = new JTabbedPane();
			resultadosTabbedPane.setBounds(new Rectangle(10, 176, 676, 225));
			getBaseTableScrollPane();
			
			resultadosTabbedPane.add(geDatasTableScrollPane(),"Datas da turmas");
			resultadosTabbedPane.add(geOutrosTableScrollPane(),"Elementos das Turmas");
			resultadosTabbedPane.add(getBaseTableScrollPane(),"Regras das Turmas");
		}
		return resultadosTabbedPane;
	}
	
	private JScrollPane baseTableScrollPane;

	private RegrasTable baseTable;
	
	protected JScrollPane getBaseTableScrollPane() {

		if (baseTableScrollPane == null) {
			baseTableScrollPane = new JScrollPane();
			baseTableScrollPane.setBounds(new Rectangle(10, 176, 676, 225));
			baseTableScrollPane.add(getBaseTable());
			baseTableScrollPane.setViewportView(getBaseTable());
		}
		return baseTableScrollPane;
	}

	protected RegrasTable getBaseTable() {
		if (baseTable == null) {
			baseTable = new RegrasTable();			
		}
		return baseTable;
	}
	
	private JScrollPane datasTableScrollPane;	
	
	protected JScrollPane geDatasTableScrollPane() {
		if (datasTableScrollPane == null) {
			datasTableScrollPane = new JScrollPane();
			datasTableScrollPane.setBounds(new Rectangle(10, 176, 676, 225));
			datasTableScrollPane.add(getDatasTable());
			datasTableScrollPane.setViewportView(getDatasTable());
		}
		return datasTableScrollPane;
	}
	protected DataTurmaTable datasTable;
	
	protected DataTurmaTable getDatasTable(){
		if (datasTable == null){
			datasTable = new DataTurmaTable();
		}
		return datasTable;
	}
	
	
	private JScrollPane outrosTableScrollPane;	
	
	protected JScrollPane geOutrosTableScrollPane() {
		if (outrosTableScrollPane == null) {
			outrosTableScrollPane = new JScrollPane();
			outrosTableScrollPane.setBounds(new Rectangle(10, 176, 676, 225));
			outrosTableScrollPane.add(getOutrosTable());
			outrosTableScrollPane.setViewportView(getOutrosTable());
		}
		return outrosTableScrollPane;
	}
	protected OutrosDadosTable outrosTable;
	
	protected OutrosDadosTable getOutrosTable(){
		if (outrosTable == null){
			outrosTable = new OutrosDadosTable();
		}
		return outrosTable;
	}
	
	private JButton helpButton;
	private JButton getHelpButton() {
		if (helpButton == null) {
			helpButton = new JButton();
			helpButton.setIcon(new ImageIcon(getClass().getResource("/imgs/helpicon.png")));
			helpButton.setBounds(new Rectangle(665, 10, 20, 20));
		}
		return helpButton;
	}
	@SuppressWarnings("unused")
	private PessoaEmDivulgacao initializeComitenteTipoPessoa(long pk){
		try {
			return RemotePessoaService.getInstance().getParticipante(pk);
		} catch (Exception e1) {
			e1.printStackTrace();			
		}
		return null;
	}
	/*private PessoaEmDivulgacao initializeComitenteTipoPessoa(long pk){
		Session s =null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Object obj = s.createQuery("from Participante p where p.id="+pk).uniqueResult();
			if (obj != null){
				((Participante)obj).getTipoPessoa();
				((Participante)obj).getUser();
				return (Participante) obj;
			}
			else{
				PessoaEmDivulgacao c = (PessoaEmDivulgacao) s.load(PessoaEmDivulgacao.class,pk);
				c.getTipoPessoa();
				return c;
			}
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}finally{
			if (s != null) s.close();
		}
		return null;
	}*/

	protected JPanel getDataHoraPanel() {

		if (dataHoraPanel == null) {
			dataHoraPanel = new JPanel();
			dataHoraPanel.setLayout(null);
			dataHoraPanel.setBounds(new Rectangle(10, 24, 612, 22));
			dataHoraPanel.add(getDataOnlinePrimeira());
			dataHoraPanel.add(getDataOnline1Label());
			dataHoraPanel.add(getDataPresencialPrimeira());
			dataHoraPanel.add(getDataPresencial1Label());
		}
		return dataHoraPanel;
	}

	protected DateHourChooser getDataOnlinePrimeira() {
		if (dataOnlinePrimeira == null) {
			dataOnlinePrimeira = new DateHourChooser(messages
					.getCurrentLocale(), false, true, false);
			dataOnlinePrimeira.setSize(new Dimension(134, 22));
			dataOnlinePrimeira.setLocation(new Point(416, 0));			
		}
		return dataOnlinePrimeira;
	}

	protected JLabel getDataOnline1Label() {

		if (dataOnlineDateFieldChooserLabel == null) {
			dataOnlineDateFieldChooserLabel = new JLabel("Data online do leilão 1:");
			dataOnlineDateFieldChooserLabel.setSize(new Dimension(130, 22));
			dataOnlineDateFieldChooserLabel
					.setLocation(new Point(282, 0));
			dataOnlineDateFieldChooserLabel.setHorizontalAlignment(JLabel.RIGHT);
		}
		return dataOnlineDateFieldChooserLabel;
	}

	protected DateHourChooser getDataPresencialPrimeira() {
		if (dataPresencialPrimeira == null) {
			dataPresencialPrimeira = new DateHourChooser(messages
					.getCurrentLocale(), false, true, false);
			dataPresencialPrimeira.setSize(new Dimension(134, 22));
			dataPresencialPrimeira.setLocation(new Point(141, 0));			
		}
		return dataPresencialPrimeira;
	}

	protected JLabel getDataPresencial1Label() {

		if (dataPresencialDateFieldChooserLabel == null) {
			dataPresencialDateFieldChooserLabel = new JLabel("Data presencial do leilão 1:");
			dataPresencialDateFieldChooserLabel.setSize(new java.awt.Dimension(
					135, 22));
			dataPresencialDateFieldChooserLabel.setLocation(new java.awt.Point(0, 0));
			dataPresencialDateFieldChooserLabel
					.setHorizontalAlignment(JLabel.LEFT);
		}
		return dataPresencialDateFieldChooserLabel;
	}

	protected JPanel getDataHoraPanel2() {

		if (dataHoraPanel2 == null) {
			dataHoraPanel2 = new JPanel();
			dataHoraPanel2.setLayout(null);
			dataHoraPanel2.setBounds(new Rectangle(10, 51, 611, 22));
			dataHoraPanel2.add(getDataOnlineSegunda());
			dataHoraPanel2.add(getDataOnline2Label());
			dataHoraPanel2.add(getDataPresencialSegunda());
			dataHoraPanel2.add(getDataPresencial2Label());
		}
		return dataHoraPanel2;
	}

	protected DateHourChooser getDataOnlineSegunda() {
		if (dataOnline2 == null) {
			dataOnline2 = new DateHourChooser(messages.getCurrentLocale(),
					false, true, false);
			dataOnline2.setSize(new Dimension(134, 22));
			dataOnline2.setLocation(new Point(416, 0));
			
		}
		return dataOnline2;
	}

	protected JLabel getDataOnline2Label() {

		if (dataOnline2Label == null) {
			dataOnline2Label = new JLabel("Data online do leilão 2:");
			dataOnline2Label.setSize(new java.awt.Dimension(130, 22));
			dataOnline2Label.setLocation(new java.awt.Point(282, 0));
			dataOnline2Label.setHorizontalAlignment(JLabel.RIGHT);
		}
		return dataOnline2Label;
	}

	protected DateHourChooser getDataPresencialSegunda() {

		if (dataPresencial2 == null) {
			dataPresencial2 = new DateHourChooser(messages.getCurrentLocale(),
					false, true, false);
			dataPresencial2.setSize(new java.awt.Dimension(134, 22));
			dataPresencial2.setLocation(new java.awt.Point(141, 0));
			
		}
		return dataPresencial2;
	}

	protected JLabel getDataPresencial2Label() {

		if (dataPresencial2Label == null) {
			dataPresencial2Label = new JLabel("Data presencial do leilão 2:");
			dataPresencial2Label.setSize(new java.awt.Dimension(135, 22));
			dataPresencial2Label.setLocation(new java.awt.Point(0, 0));
			dataPresencial2Label.setHorizontalAlignment(JLabel.LEFT);
		}
		return dataPresencial2Label;
	}

	protected JPanel getClassifSubClassifRegrasComLeilaoPanel() {

		if (procedenciaTipoTurmaPanel == null) {
			procedenciaTipoTurmaPanel = new JPanel();
			procedenciaTipoTurmaPanel.setLayout(null);
			procedenciaTipoTurmaPanel.setBounds(new Rectangle(10, 23, 612, 22));
			procedenciaTipoTurmaPanel.add(getClassificacaoComboBox());
			procedenciaTipoTurmaPanel.add(getProcedenciaComboBoxLabel());
			procedenciaTipoTurmaPanel.add(getSubclassificacaoComboBox());
			procedenciaTipoTurmaPanel.add(getTipoTurmaLabel());
			procedenciaTipoTurmaPanel.add(getRegrasComitentesLabel());
		}
		return procedenciaTipoTurmaPanel;
	}

	protected JComboBox getClassificacaoComboBox() {

		if (classificacaoComboBox == null) {
			classificacaoComboBox = new JComboBox();
			classificacaoComboBox.setSize(new java.awt.Dimension(170, 22));
			classificacaoComboBox.setLocation(new java.awt.Point(105, 0));
			for (int i=0; i < ClassificacaoTreinamento.values().length;i++){
				classificacaoComboBox.addItem(ClassificacaoTreinamento.values()[i].name().replace("_", " "));
			}
			classificacaoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						updateSubclassificacaoLeilaoComboBox(ClassificacaoTreinamento.valueOf(((String)classificacaoComboBox.getSelectedItem()).replace(" ", "_")));
					}
				}				
			});
			
			return classificacaoComboBox;
		}
		return classificacaoComboBox;
	}

	protected JLabel getProcedenciaComboBoxLabel() {

		if (procedenciaComboBoxLabel == null) {
			procedenciaComboBoxLabel = new JLabel(
					messages
							.getMessage("com.adapit.portal.ui.forms.leilao.LeilaoCadastreForm.Procedência"));
			procedenciaComboBoxLabel.setSize(new java.awt.Dimension(100, 22));
			procedenciaComboBoxLabel.setLocation(new java.awt.Point(0, 0));
			procedenciaComboBoxLabel.setText("Classificação:");
			procedenciaComboBoxLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return procedenciaComboBoxLabel;
	}

	protected JComboBox getSubclassificacaoComboBox() {

		if (subclassificacaoComboBox == null) {
			subclassificacaoComboBox = new JComboBox();
			subclassificacaoComboBox.setSize(new java.awt.Dimension(230, 22));
			subclassificacaoComboBox.setLocation(new java.awt.Point(380, 0));
			for (int i=0; i < TipoPacoteTreinamento.values().length;i++){
				subclassificacaoComboBox.addItem(TipoPacoteTreinamento.values()[i].name().replace("_", " "));
			}
			
			return subclassificacaoComboBox;
		}
		return subclassificacaoComboBox;
	}
	
	private void updateSubclassificacaoLeilaoComboBox(ClassificacaoTreinamento proc){
		subclassificacaoComboBox.removeAllItems();
		if (proc == ClassificacaoTreinamento.In_Company){
			for (int i=0; i < TipoPacoteTreinamento.values().length;i++){
				if (TipoPacoteTreinamento.values()[i] != TipoPacoteTreinamento.Formação_Própria
						&& TipoPacoteTreinamento.values()[i] != TipoPacoteTreinamento.Formação_Empresa_Parceira)
					subclassificacaoComboBox.addItem(TipoPacoteTreinamento.values()[i].name().replace("_", " "));
			}
			
			try {
				regrasExecucaoComboBox.removeItem(TurnoTreinamento.Noite.name().replace("_"," "));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			subclassificacaoComboBox.addItem(TipoPacoteTreinamento.Formação_Própria.name());
			subclassificacaoComboBox.addItem(TipoPacoteTreinamento.Formação_Empresa_Parceira.name().replace("_", " "));
			if (regrasExecucaoComboBox.getItemCount()<3)
				regrasExecucaoComboBox.addItem(TurnoTreinamento.Noite.name().replace("_"," "));			
			
		}
	}
	
	@SuppressWarnings("unused")
	private void updateRegraLeilao(){		
		if (turma.getTurno() == TurnoTreinamento.Noite){
			updateDatasMaisQueUmLeilao();
		}else if (turma.getTurno() == TurnoTreinamento.Tarde){
			
			updateDatasMaisQueUmLeilao();
		}else{
			updateDatasUmLeilao();
		}
	}
	
	private void updateRegraLeilaoByState(){
		String str = (String) regrasExecucaoComboBox.getSelectedItem();
		TurnoTreinamento regra= TurnoTreinamento.valueOf(str.replace(" ", "_"));
		if (regra == TurnoTreinamento.Noite){
			
			updateDatasMaisQueUmLeilaoByState();
		}else if (regra == TurnoTreinamento.Tarde){
			
			updateDatasMaisQueUmLeilaoByState();
		}else{
			updateDatasUmLeilaoByState();
		}
	}
	
	private void updateDatasUmLeilao(){
		if (turma.getTipoExecucao() == TipoExecucaoTreinamento.Presencial){
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(false);
			
			
			
		}else if (turma.getTipoExecucao() == TipoExecucaoTreinamento.Virtual){
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(false);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
		}else {
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
		}
	}
	
	private void updateDatasUmLeilaoByState(){
		String str = (String) executarComoComboBox.getSelectedItem();
		TipoExecucaoTreinamento regra= TipoExecucaoTreinamento.valueOf(str.replace(" ", "_"));
		if (regra == TipoExecucaoTreinamento.Presencial){
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(false);
			
			
			
		}else if (regra == TipoExecucaoTreinamento.Virtual){
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(false);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
			
		}else {
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
		}
	}
	
	private void updateDatasMaisQueUmLeilao(){
		if (turma.getTipoExecucao() == TipoExecucaoTreinamento.Presencial){
			dataPresencial2.getCalendarButton().setEnabled(true);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(false);
			
			
		}else if (turma.getTipoExecucao() == TipoExecucaoTreinamento.Virtual){
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(true);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(false);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
		}else {
			dataPresencial2.getCalendarButton().setEnabled(true);
			dataOnline2.getCalendarButton().setEnabled(true);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
			
		}
	}
	
	private void updateDatasMaisQueUmLeilaoByState(){
		String str = (String) executarComoComboBox.getSelectedItem();
		TipoExecucaoTreinamento regra= TipoExecucaoTreinamento.valueOf(str.replace(" ", "_"));
		if (regra == TipoExecucaoTreinamento.Presencial){
			dataPresencial2.getCalendarButton().setEnabled(true);
			dataOnline2.getCalendarButton().setEnabled(false);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(false);
			
			
			
			
		}else if (regra == TipoExecucaoTreinamento.Virtual){
			dataPresencial2.getCalendarButton().setEnabled(false);
			dataOnline2.getCalendarButton().setEnabled(true);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(false);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
		}else {
			dataPresencial2.getCalendarButton().setEnabled(true);
			dataOnline2.getCalendarButton().setEnabled(true);
			
			dataPresencialPrimeira.getCalendarButton().setEnabled(true);
			dataOnlinePrimeira.getCalendarButton().setEnabled(true);
			
		}
	}

	
	private JLabel regrasComitentes;
	protected JLabel getRegrasComitentesLabel() {

		if (regrasComitentes == null) {
			regrasComitentes = new JLabel();
			regrasComitentes.setText("Seguir regras de:");
			regrasComitentes.setBounds(new Rectangle(292, 48, 90, 22));
			regrasComitentes.setHorizontalAlignment(JLabel.LEFT);
		}
		return regrasComitentes;
	}
	

	protected JLabel getTipoTurmaLabel() {

		if (tipoTurmaLabel == null) {
			tipoTurmaLabel = new JLabel("Tipo da Turma");
			tipoTurmaLabel.setSize(new java.awt.Dimension(90, 22));
			tipoTurmaLabel.setLocation(new java.awt.Point(282, 0));
			tipoTurmaLabel.setText("Sub classificação:");
			tipoTurmaLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return tipoTurmaLabel;
	}

	protected JLabel getExecutarTurmaComoLabel() {

		if (executarTurmaComoLabel == null) {
			executarTurmaComoLabel = new JLabel("Tipo Execução");
			executarTurmaComoLabel.setHorizontalAlignment(JLabel.LEFT);
			executarTurmaComoLabel.setBounds(new Rectangle(10, 48, 100, 22));
		}
		return executarTurmaComoLabel;
	}

	

	

	/**
	 * This method initializes executarComoList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JComboBox getExecutarComoComboBox() {
		if (executarComoComboBox == null) {
			executarComoComboBox = new JComboBox();
			for (int i=0; i < TipoExecucaoTreinamento.values().length;i++){
				executarComoComboBox.addItem(TipoExecucaoTreinamento.values()[i].name().replaceAll("_", " "));
			}
			executarComoComboBox.setSelectedIndex(3);
			executarComoComboBox.setBounds(new Rectangle(115, 48, 170, 22));
			executarComoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						//updateRegraLeilao();
						updateRegraLeilaoByState();
					}
				}
			});
			
		}
		return executarComoComboBox;
	}
	
	private JComboBox getRegrasExecucaoComboBox() {
		if (regrasExecucaoComboBox == null) {
			regrasExecucaoComboBox = new JComboBox();
			for (int i=0; i < TurnoTreinamento.values().length;i++){
				regrasExecucaoComboBox.addItem(TurnoTreinamento.values()[i].name().replaceAll("_", " "));
			}
			regrasExecucaoComboBox.setSelectedIndex(1);
			regrasExecucaoComboBox.setBounds(new Rectangle(390, 48, 230, 22));
			regrasExecucaoComboBox.addItemListener(new ItemListener(){
				@Override
				public void itemStateChanged(ItemEvent evt) {
					if (evt.getStateChange() == ItemEvent.SELECTED){
						//updateRegraLeilao();
						updateRegraLeilaoByState();
					}
				}				
			});
		}
		return regrasExecucaoComboBox;
	}

	/**
	 * This method initializes enderecoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getEnderecoComboBox() {
		if (enderecoComboBox == null) {
			enderecoComboBox = new JComboBox();
			enderecoComboBox.setBounds(new Rectangle(115, 11, 237, 22));
			enderecoComboBox.setEditable(true);
			AutoCompletion.enable(enderecoComboBox);
		}
		return enderecoComboBox;
	}

	
	@SuppressWarnings("unchecked")
	public void updateEnderecoList(){
		getEnderecoComboBox().removeAllItems();
		enderecos.clear();		
		try {
			List<Endereco> list = RemoteServicesUtility.getInstance().listEnderecoByTipo(AddressType.Presencial);
			for(Endereco l : list){				
				enderecos.add(l);
				getEnderecoComboBox().addItem(l.getCidade()+" ("+l.getRua()+")");				
			}			
		} catch (Exception e1) {
			e1.printStackTrace();			
		}
		
	}
/*	public void updateEnderecoList(){
		getEnderecoComboBox().removeAllItems();
		enderecos.clear();
		Session s = LocalServicesUtility.getInstance().openSession();
		try {
			s.beginTransaction();
			Iterator it = s.createQuery("select endereco.id, endereco.cidade, endereco.rua from Endereco endereco where endereco.tipo="+TipoEndereco.Presencial.ordinal()+" order by endereco.cidade ASC").list().iterator();
			{
				while(it.hasNext()){
					Object obj[] = (Object[])it.next();
					
					Endereco l = new Endereco();
					l.setId(((Integer)obj[0]).intValue());
					l.setCidade((String)obj[1]);
					l.setRua((String)obj[2]);
					enderecos.add(l);
					getEnderecoComboBox().addItem(l.getCidade()+" ("+l.getRua()+")");
				}
			}
			s.getTransaction().commit();
		} catch (Exception e1) {
			e1.printStackTrace();
			s.getTransaction().rollback();
		}finally{
			s.close();
		}
		
	}*/

	/**
	 * This method initializes refreshEnderecoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRefreshEnderecoButton() {
		if (refreshEnderecoButton == null) {
			refreshEnderecoButton = new JButton();
			//refreshEnderecoButton.setBounds(new Rectangle(292, 60, 33, 10));
			refreshEnderecoButton.setBounds(new Rectangle(356, 10, 22, 22));
			refreshEnderecoButton.setToolTipText("Atualizar a lista de enderços presenciais");
			refreshEnderecoButton.setIcon(new ImageIcon(getClass().getResource("/imgs/action_refresh_blue.gif")));
			refreshEnderecoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					updateEnderecoList();
				}
			});
		}
		return refreshEnderecoButton;
	}
	
	private List<Endereco> enderecos = new ArrayList<Endereco>();  //  @jve:decl-index=0:

	private JCheckBox usarEnderCheckBox = null;

	private JPanel restricoesPanel = null;

	private JPanel usarDataPanel = null;

	private JCheckBox usarRegrasCheckBox = null;

	private JCheckBox dataCheckBox = null;

	private JButton listarButton = null;

	/**
	 * This method initializes usarEnderCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getUsarEnderCheckBox() {
		if (usarEnderCheckBox == null) {
			usarEnderCheckBox = new JCheckBox();
			usarEnderCheckBox.setBounds(new Rectangle(384, 11, 237, 21));
			usarEnderCheckBox.setText("Apenas leilões ocorridos neste endereço");
		}
		return usarEnderCheckBox;
	}

	/**
	 * This method initializes restricoesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getRestricoesPanel() {
		if (restricoesPanel == null) {
			restricoesPanel = new JPanel();
			restricoesPanel.setLayout(null);
			restricoesPanel.setBounds(new Rectangle(0, 41, 629, 72));
			restricoesPanel.add(getClassifSubClassifRegrasComLeilaoPanel(), null);
			restricoesPanel.add(getExecutarTurmaComoLabel(), null);
			restricoesPanel.add(getExecutarComoComboBox(), null);
			restricoesPanel.add(getRegrasComitentesLabel(), null);
			restricoesPanel.add(getRegrasExecucaoComboBox(), null);
			restricoesPanel.add(getUsarRegrasCheckBox(), null);
		}
		return restricoesPanel;
	}

	/**
	 * This method initializes usarDataPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUsarDataPanel() {
		if (usarDataPanel == null) {
			usarDataPanel = new JPanel();
			usarDataPanel.setLayout(null);
			usarDataPanel.setBounds(new Rectangle(0, 124, 629, 80));
			usarDataPanel.add(getDataHoraPanel(), null);
			usarDataPanel.add(getDataHoraPanel2(), null);
			usarDataPanel.add(getDataCheckBox(), null);
		}
		return usarDataPanel;
	}

	/**
	 * This method initializes usarRegrasCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getUsarRegrasCheckBox() {
		if (usarRegrasCheckBox == null) {
			usarRegrasCheckBox = new JCheckBox();
			usarRegrasCheckBox.setBounds(new Rectangle(10, 0, 447, 21));
			usarRegrasCheckBox.setText("Usar as regras abaixo para filtrar os leilões");
		}
		return usarRegrasCheckBox;
	}

	/**
	 * This method initializes dataCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getDataCheckBox() {
		if (dataCheckBox == null) {
			dataCheckBox = new JCheckBox();
			dataCheckBox.setBounds(new Rectangle(10, 0, 442, 21));
			dataCheckBox.setText("Usar o período de datas para filtrar os leilões");
		}
		return dataCheckBox;
	}

	/**
	 * This method initializes listarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getListarButton() {
		if (listarButton == null) {
			listarButton = new JButton();
			listarButton.setBounds(new Rectangle(10, 10, 158, 27));
			listarButton.setIcon(new ImageIcon(getClass().getResource("/imgs/read_obj.png")));
			listarButton.setText("Pesquisar");
			listarButton.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent evt) {
					listar();
				}				
			});
		}
		return listarButton;
	}

	public static void main(String args[]) {

		new java.lang.Thread(new Runnable() {
			public void run() {
				javax.swing.JFrame gui = new javax.swing.JFrame();
				gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
				gui.setLayout(new java.awt.BorderLayout());
				gui.setSize(new java.awt.Dimension(595, 391));
				gui.add(new TurmaListForm(), java.awt.BorderLayout.CENTER);
				gui.setVisible(true);
			}
		}).run();
	}

	@SuppressWarnings("unused")
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
	
	private List elements;

	public void setElements(List elements) {
		this.elements = elements;
	}

	public List getElements() {
		return this.elements;
	}
	
	@SuppressWarnings("unchecked")
	private class RegrasTable extends JXTable {

		@SuppressWarnings("deprecation")
		public RegrasTable() {

			super();
			this.setModel(new RegrasTableModel(null));
			updateTable();
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}


		public void updateTable() {

			if (elements != null && elements.size() > 0) {
				int ne = elements.size();
				java.util.Iterator it = elements.iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof TurmaTreinamento) {
						TurmaTreinamento turma = (TurmaTreinamento) obj;
						values[i][0] = turma.getId();
						values[i][1] = turma.getClassificacao().name()
								.replace("_", " ");
						values[i][2] = turma.getSubClassificacao().name()
						.replace("_", " ");
						values[i][3] = turma.getTipoExecucao().name()
						.replace("_", " ");
						values[i][4] = turma.getTurno().name()
						.replace("_", " ");
						values[i][5] = (i + 1);
						i++;
					}
				}// End of while Loop
				setModel(new RegrasTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(8);
				getColumnModel().getColumn(1).setPreferredWidth(60);
				getColumnModel().getColumn(2).setPreferredWidth(60);
				getColumnModel().getColumn(3).setPreferredWidth(100);
				getColumnModel().getColumn(4).setPreferredWidth(250);
				getColumnModel().getColumn(5).setPreferredWidth(8);
				updateUI();
			} else {
				setModel(new RegrasTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(8);
				getColumnModel().getColumn(1).setPreferredWidth(60);
				getColumnModel().getColumn(2).setPreferredWidth(60);
				getColumnModel().getColumn(3).setPreferredWidth(100);
				getColumnModel().getColumn(4).setPreferredWidth(250);
				getColumnModel().getColumn(5).setPreferredWidth(8);
				updateUI();
			}
		}

		private class RegrasTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class, String.class,
					String.class, String.class, String.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,
					false, false };

			public RegrasTableModel(Object[][] values) {

				super(values, new String[] { "Turma", "Tipo",
						"Realização", "Tipo Execução", "Turno", "" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}		

	}
	@SuppressWarnings("unchecked")
	private class DataTurmaTable extends JXTable {

		@SuppressWarnings("deprecation")
		public DataTurmaTable() {

			super();
			this.setModel(new TurmaDataTableModel(null));
			updateTable();
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}

		

		public void updateTable() {

			if (getElements() != null && getElements().size() > 0) {
				int ne = getElements().size();
				java.util.Iterator it = getElements().iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof TurmaTreinamento) {
						TurmaTreinamento turma = (TurmaTreinamento) obj;
						values[i][0] = turma.getId();
						try {
							if (turma.getDataTreinamento() != null)
								values[i][1] = AdapitVirtualFrame.formatDateTime(turma.getDataTreinamento());
							else values[i][1] = "Não especificada";
							if (turma.getDataEncerramento() != null)
								values[i][2] = AdapitVirtualFrame.formatDateTime(turma.getDataEncerramento());
							else values[i][2] = "Não especificada";
							values[i][3] = turma.getNumeroMaximoParticipantes();
							values[i][4] = turma.getNumeroMinimoParticipantes();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						try{
							values[i][5] = (i + 1);
						} catch (Exception e) {
							e.printStackTrace();
						}
						i++;
					}
				}// End of while Loop
				setModel(new TurmaDataTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(8);
				getColumnModel().getColumn(1).setPreferredWidth(60);
				getColumnModel().getColumn(2).setPreferredWidth(60);
				getColumnModel().getColumn(3).setPreferredWidth(60);
				getColumnModel().getColumn(4).setPreferredWidth(60);
				getColumnModel().getColumn(5).setPreferredWidth(8);
				updateUI();
			} else {
				setModel(new TurmaDataTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(8);
				getColumnModel().getColumn(1).setPreferredWidth(60);
				getColumnModel().getColumn(2).setPreferredWidth(60);
				getColumnModel().getColumn(3).setPreferredWidth(60);
				getColumnModel().getColumn(4).setPreferredWidth(60);
				getColumnModel().getColumn(5).setPreferredWidth(8);
				updateUI();
			}
		}

		private class TurmaDataTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class, String.class,
					String.class, String.class, String.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false,	false, false };

			public TurmaDataTableModel(Object[][] values) {

				super(values, new String[] { "Turma", "Início Previsto",
						"Término Previsto", "Núm. Máx. de Alunos", "Núm. Mín. de Alunos" , "" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}		

	}
	
	@SuppressWarnings("unchecked")
	private class OutrosDadosTable extends JXTable {
		
		@SuppressWarnings("deprecation")
		public OutrosDadosTable() {
			super();
			this.setModel(new OutrosDadosTableTableModel(null));
			updateTable();
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
		}
		
		public void updateTable() {
			if (getElements() != null && getElements().size() > 0) {
				int ne = getElements().size();
				java.util.Iterator<TurmaTreinamento> it =getElements().iterator();
				java.lang.Object values[][] = new java.lang.Object[ne][6];
				int i = 0;
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof TurmaTreinamento) {
						TurmaTreinamento turma = (TurmaTreinamento) obj;
						try {
							Object objs[] = RemoteTurmaService.getInstance().getExtraInformationFromTurma(turma.getId());
							values[i][0] = objs[0];
							values[i][1] = objs[1];
							values[i][2] = objs[2] + "-" + objs[3];
							values[i][3] = (i + 1);
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						i++;
					}
				}// End of while Loop
				setModel(new OutrosDadosTableTableModel(values));
				getColumnModel().getColumn(0).setPreferredWidth(50);
				getColumnModel().getColumn(1).setPreferredWidth(280);
				getColumnModel().getColumn(2).setPreferredWidth(150);
				getColumnModel().getColumn(3).setPreferredWidth(8);
				updateUI();
			} else {
				setModel(new OutrosDadosTableTableModel(null));
				getColumnModel().getColumn(0).setPreferredWidth(50);
				getColumnModel().getColumn(1).setPreferredWidth(280);
				getColumnModel().getColumn(2).setPreferredWidth(150);
				getColumnModel().getColumn(3).setPreferredWidth(8);
				updateUI();
			}
		}

		private class OutrosDadosTableTableModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { String.class, String.class,
					String.class, Integer.class };

			boolean canEdit[] = new boolean[] { false, false, false, false};

			public OutrosDadosTableTableModel(Object[][] values) {

				super(values, new String[] { "Instrutor", "Formação", "Local de Realização", "" });
			}

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}		

	}
	
	protected JPanel getDadosButtonsPanel() {
		if (dadosButtonsPanel == null) {
			dadosButtonsPanel = new JPanel();
			dadosButtonsPanel.setLayout(new java.awt.FlowLayout());
			dadosButtonsPanel.setBounds(new Rectangle(10, 404, 676, 55));
			dadosButtonsPanel.add(getNovaTurmaButton());
			dadosButtonsPanel.add(getRemoverTurmaButton());
			dadosButtonsPanel.add(getEditarTurmaButton());
		}
		return dadosButtonsPanel;
	}

	protected JButton getNovaTurmaButton() {
		if (novaTurmaButton == null) {
			novaTurmaButton = new JButton("Nova Turma");
			novaTurmaButton.setSize(new java.awt.Dimension(150, 20));
			novaTurmaButton.setLocation(new java.awt.Point(0, 0));
			novaTurmaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_add.png")));
			novaTurmaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AdapitVirtualFrame.getInstance().cadastrarTurma();
				}
			});
		}
		return novaTurmaButton;
	}

	protected JButton getRemoverTurmaButton() {

		if (removerTurmaButton == null) {
			removerTurmaButton = new JButton("Remover Turma");
			removerTurmaButton.setSize(new java.awt.Dimension(80, 22));
			removerTurmaButton.setLocation(new java.awt.Point(0, 20));
			removerTurmaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_delete.png")));
			removerTurmaButton
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					{
						TurmaTreinamento l = (TurmaTreinamento) getSelectedElement();
						if (l == null) return;
						if (l != null){
							int resp = JOptionPane.showConfirmDialog(TurmaListForm.this, "Apagar a turma " + l.getId(),"Apagar Turma",JOptionPane.YES_NO_OPTION);
							if (resp == JOptionPane.YES_OPTION){
								try {
									try {
										RemoteServicesUtility.getInstance().delete(l);										
										JOptionPane.showMessageDialog(TurmaListForm.this, "A turma foi removido com sucesso!","Apagar Turma",JOptionPane.INFORMATION_MESSAGE);
										listar();
									} catch (Exception e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(TurmaListForm.this, "A turma não foi removida!","Apagar Turma",JOptionPane.ERROR_MESSAGE);
									}
									/*Session s = LocalServicesUtility.getInstance().openSession();
									try {
										s.beginTransaction();
										
										l = (Leilao) s.load(Leilao.class,l.getId());
										s.delete(l);
										
										s.getTransaction().commit();
										
										JOptionPane.showMessageDialog(LeilaoListForm.this, "O leilao foi removido com sucesso!","Apagar Leilao",JOptionPane.INFORMATION_MESSAGE);
										listar();
									} catch (Exception e1) {
										e1.printStackTrace();
										s.getTransaction().rollback();
										JOptionPane.showMessageDialog(LeilaoListForm.this, "O leilao não foi removido!","Apagar Leilao",JOptionPane.ERROR_MESSAGE);
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
			});
		}
		return removerTurmaButton;
	}
	
	public TurmaTreinamento getSelectedElement(){
		System.out.println(getResultadosTabbedPane().getSelectedIndex());
		if (getResultadosTabbedPane().getSelectedIndex() == 2){
			int row = getBaseTable().getSelectedRow();
			return (TurmaTreinamento) getElements().get(row);
		}else if (getResultadosTabbedPane().getSelectedIndex() == 0){
			int row = getDatasTable().getSelectedRow();
			return (TurmaTreinamento) getElements().get(row);
		}else if (getResultadosTabbedPane().getSelectedIndex() == 1){
			int row = getOutrosTable().getSelectedRow();
			return (TurmaTreinamento) getElements().get(row);
		}
		return null;
	}

	protected JButton getEditarTurmaButton() {

		if (editarTurmaButton == null) {
			editarTurmaButton = new JButton("Editar a Turma");
			editarTurmaButton.setSize(new java.awt.Dimension(80, 22));
			editarTurmaButton.setLocation(new java.awt.Point(0, 42));
			editarTurmaButton.setIcon(new ImageIcon(getClass().getResource("/imgs/date_edit.png")));
			editarTurmaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					{
						TurmaTreinamento l = (TurmaTreinamento) getSelectedElement();
						if (l == null) return;
						
						if (AdapitVirtualFrame.getInstance().getTurmaCadastreFrame() != null){
							AdapitVirtualFrame.getInstance().getTurmaCadastreFrame().getLeilaoCadastreForm().editRegister(l);
						}else{
							AdapitVirtualFrame.getInstance().cadastrarTurma();
							AdapitVirtualFrame.getInstance().getTurmaCadastreFrame().getLeilaoCadastreForm().editRegister(l);
						}
						
					}
				}
			});
		}
		return editarTurmaButton;
	}

	


}  //  @jve:decl-index=0:visual-constraint="10,10"