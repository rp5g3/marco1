package com.adapit.portal.ui.forms.treinamento.contapagar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.jboleto.JBoleto;
import org.jboleto.JBoletoBean;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.Highlighter;

import com.adapit.portal.entidades.AgendaTreinamento;
import com.adapit.portal.entidades.Fisica;
import com.adapit.portal.entidades.FormaPagamento;
import com.adapit.portal.entidades.Juridica;
import com.adapit.portal.entidades.Participante;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.Treinamento;
import com.adapit.portal.services.PersonType;
import com.adapit.portal.services.remote.RemoteContaService;
import com.adapit.portal.services.remote.RemotePessoaService;
import com.adapit.portal.services.remote.RemoteServicesUtility;
import com.adapit.portal.ui.forms.treinamento.agenda.AgendaTreinamentoCadastreForm;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.PrintablePdfDocumentFrame;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.UIUtil;
import com.workcase.utils.Moeda;

@SuppressWarnings("serial")
public class ContaPagarListForm  extends JPanel{	

	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JPanel inicioPanel;
	
	private JLabel arrematanteLabel = null;

	private JTextField clienteTextField = null;

	private JButton removerContaButton = null;

	private JButton verDadosArrematanteButton = null;

	private AgendaTreinamento agenda = new AgendaTreinamento();  //  @jve:decl-index=0:
	
	private Treinamento lote;  //  @jve:decl-index=0:
	
	private DateHourChooser tempDateFieldChooser = new DateHourChooser(messages.getCurrentLocale(), false, true, false);
	
	//private ContaPagarCadastreForm condicaoPagtoForm;
	
	public ContaPagarListForm(){	
		initialize();
	}
	
	private void initialize(){		
		this.setSize(new Dimension(570, 367));
		setLocation(new java.awt.Point(0,0));
		setLayout(new BorderLayout());
		add(getTabbedPane());
	}
	
	private JTabbedPane tabbedPane;
	
	private JTabbedPane getTabbedPane(){
		if (tabbedPane == null){
			tabbedPane = new JTabbedPane();
			tabbedPane.setTabPlacement(JTabbedPane.TOP);
			tabbedPane.addTab("Contas a Pagar Relacionadas com o Lote", null, getContaPagarPanel(), null);
			//tabbedPane.addTab("Editar Conta a Pagar", null, getCondicaoPagtoForm(), null);
		}
		return tabbedPane;
	}
	


	private JPanel contaPagarPanel = null;

	private JPanel encerramentoPanel = null;

	//private JButton verEncerramentoButton = null;

	protected JPanel getInicioPanel(){
		if(inicioPanel == null){
			arrematanteLabel = new JLabel();
			arrematanteLabel.setBounds(new Rectangle(0, 0, 83, 20));
			//arrematanteLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			arrematanteLabel.setText("Arrematante:");
			encerramentoDataLabel = new JLabel();
			encerramentoDataLabel.setText("Encerramento:");
			encerramentoDataLabel.setBounds(new Rectangle(299, 0, 89, 20));
			inicioPanel = new JPanel();
			inicioPanel.setLayout(null);
			inicioPanel.setBounds(new Rectangle(15, 15, 541, 48));
			inicioPanel.add(arrematanteLabel, null);
			inicioPanel.add(getClienteTextField(), null);
			//inicioPanel.add(encerramentoDataLabel, null);
			//inicioPanel.add(getDataEncerramento(), null);
			inicioPanel.add(getVerDadosArrematanteButton(), null);
			//inicioPanel.add(getVerEncerramentoButton(), null);
			inicioPanel.add(getTodosEstadoCheckBox(), null);
		}
		return inicioPanel;
	}
	
	public void updateData(){
		getTabbedPane().setVisible(true);
		getContasList().updateTable();
		updateEncerramento();
		
	}
	
	public JTextField getClienteTextField() {
		if (clienteTextField == null) {
			clienteTextField = new JTextField();
			clienteTextField.setBounds(new Rectangle(82, 0, 208, 20));
			clienteTextField.setEditable(false);
		}
		return clienteTextField;
	}
	
	/**
	 * This method initializes notificarDesistenciaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getRemoverContaButton() {
		if (removerContaButton == null) {
			removerContaButton = new JButton();
			removerContaButton.setText("Remover");
			removerContaButton.setBounds(new Rectangle(28, 101, 100, 26));
			removerContaButton.setEnabled(true);
			removerContaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					try {
						ParticipanteContaPagar conta = getSelectedContaPagar();
						RemoteServicesUtility.getInstance().deleteById(ParticipanteContaPagar.class, conta.getId());
						getContasList().updateTable();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					/*Session s = null;
					try{
						s = LocalServicesUtility.getInstance().openSession();									
						s.beginTransaction();
						ParticipanteContaPagar conta = getSelectedContaPagar();
						s.createQuery("delete ParticipanteContaPagar conta where conta.id=:id")
							.setParameter("id", conta.getId()).executeUpdate();
						s.getTransaction().commit();
						getContasList().updateTable();
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
					}*/
				}
			});
		}
		return removerContaButton;
	}

	/**
	 * This method initializes verDadosArremateButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getVerDadosArrematanteButton() {
		if (verDadosArrematanteButton == null) {
			verDadosArrematanteButton = new JButton();
			verDadosArrematanteButton.setText("Ver os Dados Pessoais");
			verDadosArrematanteButton.setBounds(new Rectangle(297, 0, 164, 22));
			verDadosArrematanteButton.setEnabled(true);
			verDadosArrematanteButton
			.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if (cliente != null){
						try {
							cliente = initializeArrematanteTipoPessoa();
							if (cliente instanceof Participante){
								if (cliente.getTipoPessoa() instanceof Fisica)
									AdapitVirtualFrame.getInstance().editarParticipante(((Participante)cliente).getUser(),PersonType.Fisica);
								else AdapitVirtualFrame.getInstance().editarParticipante(((Participante)cliente).getUser(),PersonType.Juridica);
							}
						} catch (Exception e1) {
							e1.printStackTrace();
						}						
					}
				}
			});
		}
		return verDadosArrematanteButton;
	}


	/**
	 * This method initializes ParticipanteContaPagarsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getContaPagarPanel() {
		if (contaPagarPanel == null) {
			valorTotalLabel = new JLabel();
			valorTotalLabel.setBounds(new Rectangle(436, 232, 120, 21));
			valorTotalLabel.setHorizontalAlignment(SwingConstants.CENTER);
			valorTotalLabel.setText("Valor Total:");
			contaPagarPanel = new JPanel();
			contaPagarPanel.setLayout(null);
			contaPagarPanel.add(getInicioPanel(),null);
			contaPagarPanel.add(getContaPagarScroll(),null);
			contaPagarPanel.add(getEncerramentoPanel(),null);
			contaPagarPanel.add(valorTotalLabel, null);
			contaPagarPanel.add(getValorTotalTextField(), null);
			
		}
		return contaPagarPanel;
	}
	
	protected JScrollPane contaPagarScroll;
	
	protected JScrollPane getContaPagarScroll(){
		if(contaPagarScroll == null){
			contaPagarScroll = new JScrollPane();
					
			contaPagarScroll.setBounds(new Rectangle(15, 69, 411, 215));
			contaPagarScroll.setViewportView(getContasList());
			
		}
		return contaPagarScroll;
	}
	
	protected ContasList contasList;
	protected ContasList getContasList(){
		if (contasList == null){
			contasList = new ContasList();
		}
		return contasList;
	}

	//private Encerramento encerramento;  //  @jve:decl-index=0:
	
	public void updateEncerramento(){
		
		
	}
	/**
	 * This method initializes encerramentoPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEncerramentoPanel() {
		if (encerramentoPanel == null) {
			
			
			encerramentoPanel = new JPanel();
			encerramentoPanel.setLayout(new GridLayout(6,1));
			encerramentoPanel.setSize(121, 160);
			encerramentoPanel.setLocation(435, 67);
			
			encerramentoPanel.add(getNovaContaButton(), null);
			encerramentoPanel.add(getEditarContaButton(), null);
			encerramentoPanel.add(getRemoverContaButton(), null);
			encerramentoPanel.add(getEmitirBoletoButton(), null);
			encerramentoPanel.add(getEmitirFaturaButton(), null);
			encerramentoPanel.add(getDesistenciaContaButton(), null);
		}
		return encerramentoPanel;
	}
	
	/*private DateHourChooser dataEncerramento;
	
	private DateHourChooser getDataEncerramento(){
		if (dataEncerramento == null){
			dataEncerramento = new DateHourChooser(messages.getCurrentLocale(), true, true, false);
			dataEncerramento.getCalendarButton().setEnabled(false);
			dataEncerramento.setEnabled(false);
			dataEncerramento.setBounds(new Rectangle(388, 0, 147, 20));
		}
		return dataEncerramento;
	}*/
	/**
	 * This method initializes verContasPagarArrematanteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	/*private JButton getVerEncerramentoButton() {
		if (verEncerramentoButton == null) {
			verEncerramentoButton = new JButton();
			verEncerramentoButton.setText("Ver Encerramento");
			verEncerramentoButton.setBounds(new Rectangle(364, 23, 125, 23));
			verEncerramentoButton.setEnabled(true);
		}
		return verEncerramentoButton;
	}*/

/*	public boolean isMaiorParticipanteContaPagar(ParticipanteContaPagar l){
		Iterator<ParticipanteContaPagar> it = getContasList().getElements().iterator();
		float maiorValor = 0;
		ParticipanteContaPagar maiorParticipanteContaPagar=null;
		while(it.hasNext()){
			ParticipanteContaPagar ParticipanteContaPagar = it.next();
			if (ParticipanteContaPagar.getValor() > maiorValor){
				maiorParticipanteContaPagar = ParticipanteContaPagar;
				maiorValor = ParticipanteContaPagar.getValor();
			}
		}
		if (l == maiorParticipanteContaPagar) return true;
		else return false;
	}*/
	
	/**
	 * This method initializes aprovarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEditarContaButton() {
		if (editarContaButton == null) {
			editarContaButton = new JButton();
			editarContaButton.setBounds(new Rectangle(8, 41, 130, 26));
			editarContaButton.setEnabled(true);
			editarContaButton.setText("Editar");
			editarContaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ParticipanteContaPagar conta = getSelectedContaPagar();
					if (conta  == null) return;
					JDialog jd = new JDialog(AdapitVirtualFrame.getInstance());
					jd.setSize(294, 206);
					jd.setModal(true);
					jd.setTitle("Editar conta " + getSelectedContaPagarNumber() +" do lote " + lote.getCodigo());
					PagarContaForm p = new PagarContaForm();
					jd.add(p);
					p.editRegister(conta);
					jd.setLocation(UIUtil.getScreenCenter(jd));
					jd.setVisible(true);
					getContasList().updateTable();
					/*int resp = JOptionPane.showConfirmDialog(LeilaoVirtualFrame.getInstance(), "Você tem certeza de que quer aprovar a homologação deste lote?","Aprovação de Homologação",JOptionPane.YES_NO_OPTION);
					if (resp == JOptionPane.NO_OPTION) return;
					
					Session s = null;
					try{
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						lote.setStatus(StatusLote.Leiloado);
						
						s.createQuery("update Lote l set l.status='"+lote.getStatus().name()+"' where l.id="+lote.getId()).executeUpdate();
						encerramento.getEncerrCond().setAprovado(true);
						
						s.createQuery("update EncerramentoCondicionado e set e.aprovado=true, e.valorAprovado="+encerramento.getEncerrCond().getValorAprovado()+" where e.id="+encerramento.getEncerrCond().getId()).executeUpdate();
						s.getTransaction().commit();
						updateEncerramento();
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
					}*/
				}
			});
		}
		return editarContaButton;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	private ParticipanteContaPagar gerarConta(){
		ParticipanteContaPagar c = new ParticipanteContaPagar();
		c.setAgenda(agenda);
		c.setCliente(cliente);
		Calendar cal= Calendar.getInstance();
		ParticipanteContaPagar ultima = null;
		List list = getContasList().getElements();
		if (list != null && list.size()>0){
			int len = list.size();
			ultima = (ParticipanteContaPagar) list.get(len-1);
		}
		float value = 0.0f;
		if (ultima != null){
			//cal.setTime(ultima.getDataVencimento());
			cal.set(Calendar.DAY_OF_MONTH, ultima.getDataVencimento().getDay());
			cal.set(Calendar.MONTH, ultima.getDataVencimento().getMonth());
			cal.set(Calendar.YEAR, ultima.getDataVencimento().getYear()+1900);
			cal.add(Calendar.MONTH, 1);
		}else{
			cal.add(Calendar.DAY_OF_MONTH, 2);
		}
		
		Calendar today = Calendar.getInstance();
		if (cal.before(today))
			cal.add(Calendar.DAY_OF_MONTH, today.get(Calendar.DAY_OF_MONTH)+5);
		
		if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY){
			//cal.getTime().setDate(dia+1);
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		else if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY){
			//cal.getTime().setDate(dia+2);
			cal.add(Calendar.DAY_OF_MONTH, 2);
		}
		
		c.setDataVencimento(cal.getTime());
		c.setValor(value);
		return c;
	}
	
	
	private void gerarContaPagar(){
		try {
			RemoteServicesUtility.getInstance().save(gerarConta());			
			AdapitVirtualFrame.getInstance().showOperationSucess("Cadastro de Conta a Pagar", "Conta a pagar gerada com socesso");
			getContasList().updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*	private void gerarContaPagar(){
		Session s = null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			s.beginTransaction();
			s.save(gerarConta());			
			s.getTransaction().commit();
			LeilaoVirtualFrame.getInstance().showOperationSucess("Cadastro de Conta a Pagar", "Conta a pagar gerada com socesso");
			getContasList().updateTable();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if (s != null && s.isOpen()) s.close();
		}
	}*/
	/**
	 * This method initializes rejeitarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getNovaContaButton() {
		if (novaContaButton == null) {
			novaContaButton = new JButton();
			novaContaButton.setBounds(new Rectangle(8, 71, 130, 26));
			novaContaButton.setText("Nova");
			novaContaButton.setEnabled(true);
			novaContaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gerarContaPagar();
					/*int resp = JOptionPane.showConfirmDialog(LeilaoVirtualFrame.getInstance(), "Você tem certeza de que quer rejeitar a homologação deste lote?","Aprovação de Homologação",JOptionPane.YES_NO_OPTION);
					if (resp == JOptionPane.NO_OPTION) return;
					
					Session s = null;
					try{
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						lote.setStatus(StatusLote.Não_homologado);
						s.createQuery("update Lote l set l.status='"+lote.getStatus().name()+"' where l.id="+lote.getId()).executeUpdate();
						s.getTransaction().commit();
						getTabbedPane().setVisible(false);
					}catch(Exception ex){
						ex.printStackTrace();
					}finally{
						if (s != null) s.close();
					}*/
				}
			});
		}
		return novaContaButton;
	}

	/**
	 * This method initializes desistenciaContaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getDesistenciaContaButton() {
		if (desistenciaContaButton == null) {
			desistenciaContaButton = new JButton();
			desistenciaContaButton.setText("Desistência");
			desistenciaContaButton.setEnabled(false);
			desistenciaContaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Desistência"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return desistenciaContaButton;
	}

	/**
	 * This method initializes emitirBoletoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEmitirBoletoButton() {
		if (emitirBoletoButton == null) {
			emitirBoletoButton = new JButton();
			emitirBoletoButton.setText("Emitir Boleto");
			emitirBoletoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					emitirBoleto();
				}
			});
		}
		return emitirBoletoButton;
	}
	
	@SuppressWarnings("unchecked")
	private void emitirBoleto(){
		JBoletoBean jBoletoBean = new JBoletoBean();
		
		Date dt = new Date();
		tempDateFieldChooser.setDate(dt);
		String todayStr = tempDateFieldChooser.getDateHourField().getText();
		jBoletoBean.setDataDocumento(todayStr);
        jBoletoBean.setDataProcessamento(todayStr);
		try {
			Participante p = (Participante) RemotePessoaService.getInstance().getParticipante(cliente.getId());
			p.getEndereco().getId();
			TipoPessoa tp = RemotePessoaService.getInstance().getPessoaFisicaByIdPessoa(p.getId());
			if (tp == null) tp = 
				RemotePessoaService.getInstance().getPessoaJuridicaByIdPessoa(p.getId());
			p.setTipoPessoa(tp);
			
			jBoletoBean.setNomeSacado(p.getNomeFormatado());
			String rua = "Rua " + p.getEndereco().getRua();
			if (p.getEndereco().getNumero() > 0) rua+=" Número " + p.getEndereco().getNumero();
			if (p.getEndereco().getComplemento() != null
					&&
					!p.getEndereco().getComplemento().equals("")) rua+=" Complemento " + p.getEndereco().getComplemento();
	        jBoletoBean.setEnderecoSacado(rua);        
	        
	        jBoletoBean.setBairroSacado(p.getEndereco().getBairro());
	        jBoletoBean.setCidadeSacado(p.getEndereco().getCidade());
	        jBoletoBean.setUfSacado(p.getEndereco().getEstado());
	        jBoletoBean.setCepSacado(p.getEndereco().getCep());
	        
	        if (p.getEndereco().getCaixaPostal() != null
	        		&& p.getEndereco().getCaixaPostal().equals("")) ;
	        
	        if (p.getTipoPessoa() instanceof Fisica)
	        	jBoletoBean.setCpfSacado(((Fisica)p.getTipoPessoa()).getCpf());
	        else jBoletoBean.setCpfSacado(((Juridica)p.getTipoPessoa()).getCnpj());
	        
	        
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
        
        jBoletoBean.setLocalPagamento("Até o vencimento, preferencialmente no banco Bradesco");
        jBoletoBean.setLocalPagamento2("Após o vencimento, pagamento somente no caixa do banco Bradesco");        
        
        Vector descricoes = new Vector();
        //descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        //descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        //descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        //descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        descricoes.add("Valor do Boleto - R$ 2,50");
        jBoletoBean.setDescricoes(descricoes);
        
        ParticipanteContaPagar conta = getSelectedContaPagar();
        tempDateFieldChooser.setDate(conta.getDataVencimento());
		String vencStr = tempDateFieldChooser.getDateHourField().getText();        
        jBoletoBean.setDataVencimento(vencStr);
        
        jBoletoBean.setInstrucao1("Após o vencimento cobrar multa de 2%");
        jBoletoBean.setInstrucao2("Após o vencimento cobrar R$ 0,50 por dia de atrazo");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        //Dados do banco
        jBoletoBean.setCedente("Cargnelutti Agência de Leilões");  
        jBoletoBean.setCarteira("17");
        jBoletoBean.setAgencia("3415");
        jBoletoBean.setContaCorrente("00543004"); //completar com zeros quando necessario
               
        jBoletoBean.setNumConvenio("1101354");
        jBoletoBean.setNossoNumero("0005963971",10);
        
        jBoletoBean.setValorBoleto((conta.getValor()+2.5f)+"");
        
        JBoleto jBoleto = new JBoleto();
        
        jBoleto.addBoleto(jBoletoBean,JBoleto.BRADESCO);
        
        String fileStr = "boleto_banco_brasil_conta"+conta.getId()+"_lote"+lote.getCodigo()+".pdf";
        jBoleto.writeToFile(fileStr);
        //Viewer v = new Viewer();
        //v.
        showPdf(fileStr);
	}
/*	@SuppressWarnings("unchecked")
	private void emitirBoleto(){
		JBoletoBean jBoletoBean = new JBoletoBean();
		
		Date dt = new Date();
		tempDateFieldChooser.setDate(dt);
		String todayStr = tempDateFieldChooser.getDateHourField().getText();
		jBoletoBean.setDataDocumento(todayStr);
        jBoletoBean.setDataProcessamento(todayStr);      
            
       
        
        Session s =null;
		
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Participante p = (Participante) s.load(Participante.class, arrematante.getId());
			p.getEndereco().getId();
			TipoPessoa tp = (TipoPessoa) s.createQuery("from Fisica f where f.pessoa.id="+p.getId()).uniqueResult();
			if (tp == null) tp = (TipoPessoa) s.createQuery("from Juridica j where j.pessoa.id="+p.getId()).uniqueResult();
			p.setTipoPessoa(tp);
			
			jBoletoBean.setNomeSacado(p.getNomeFormatado());
			String rua = "Rua " + p.getEndereco().getRua();
			if (p.getEndereco().getNumero() > 0) rua+=" Número " + p.getEndereco().getNumero();
			if (p.getEndereco().getComplemento() != null
					&&
					!p.getEndereco().getComplemento().equals("")) rua+=" Complemento " + p.getEndereco().getComplemento();
	        jBoletoBean.setEnderecoSacado(rua);        
	        
	        jBoletoBean.setBairroSacado(p.getEndereco().getBairro());
	        jBoletoBean.setCidadeSacado(p.getEndereco().getCidade());
	        jBoletoBean.setUfSacado(p.getEndereco().getEstado());
	        jBoletoBean.setCepSacado(p.getEndereco().getCep());
	        
	        if (p.getEndereco().getCaixaPostal() != null
	        		&& p.getEndereco().getCaixaPostal().equals("")) ;
	        
	        if (p.getTipoPessoa() instanceof Fisica)
	        	jBoletoBean.setCpfSacado(((Fisica)p.getTipoPessoa()).getCpf());
	        else jBoletoBean.setCpfSacado(((Juridica)p.getTipoPessoa()).getCnpj());
	        
	        
			
		} catch (Exception e1) {
			e1.printStackTrace();
			
		}finally{
			if (s != null) s.close();
		}

        
        
        
        jBoletoBean.setLocalPagamento("ATE O VENCIMENTO, PREFERENCIALMENTE NO BANCO DO BRASIL");
        jBoletoBean.setLocalPagamento2("APOS O VENCIMENTO, SOMENTE NO BANCO DO BRASIL");        
        
        Vector descricoes = new Vector();
        //descricoes.add("Hospedagem I - teste descricao1 - R$ 39,90");
        //descricoes.add("Manutencao - teste ricao2 - R$ 32,90");
        //descricoes.add("Sistema - teste ssssde descricao3 - R$ 45,90");
        //descricoes.add("Extra - teste de descricao4 - R$ 78,90");
        descricoes.add("Valor do Boleto - R$ 2,50");
        jBoletoBean.setDescricoes(descricoes);
        
        ParticipanteContaPagar conta = getSelectedContaPagar();
        tempDateFieldChooser.setDate(conta.getDataVencimento());
		String vencStr = tempDateFieldChooser.getDateHourField().getText();        
        jBoletoBean.setDataVencimento(vencStr);
        
        jBoletoBean.setInstrucao1("APOS O VENCIMENTO COBRAR MULTA DE 2%");
        jBoletoBean.setInstrucao2("APOS O VENCIMENTO COBRAR R$ 0,50 POR DIA DE ATRASO");
        jBoletoBean.setInstrucao3("");
        jBoletoBean.setInstrucao4("");

        //Dados do banco
        jBoletoBean.setCedente("Cargnelutti Agência de Leilões");  
        jBoletoBean.setCarteira("17");
        jBoletoBean.setAgencia("3415");
        jBoletoBean.setContaCorrente("00543004"); //completar com zeros quando necessario
               
        jBoletoBean.setNumConvenio("1101354");
        jBoletoBean.setNossoNumero("0005963971",10);
        
        jBoletoBean.setValorBoleto((conta.getValor()+2.5f)+"");
        
        JBoleto jBoleto = new JBoleto();
        
        jBoleto.addBoleto(jBoletoBean,JBoleto.BANCO_DO_BRASIL);
        
        String fileStr = "boleto_banco_brasil_conta"+conta.getId()+"_lote"+lote.getCodLote()+".pdf";
        jBoleto.writeToFile(fileStr);
        //Viewer v = new Viewer();
        //v.
        showPdf(fileStr);
	}*/

	private void showPdf(String url){
		PrintablePdfDocumentFrame frame = new PrintablePdfDocumentFrame(url);
		frame.setTitle("Boleto Bancário");
	}
	/*private void showPdf2(String url){
		JFrame frame = new JFrame("Boleto Bancário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        PagePanel panel = new PagePanel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        //load a pdf from a byte buffer
        File file = new File(url+".pdf");
        RandomAccessFile raf = null;
		try {
			raf = new RandomAccessFile(file, "r");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        FileChannel channel = raf.getChannel();
        ByteBuffer buf=null;
		try {
			buf = channel.map(FileChannel.MapMode.READ_ONLY,
			    0, channel.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        PDFFile pdffile=null;
		try {
			pdffile = new PDFFile(buf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // show the first page
        PDFPage page = pdffile.getPage(0);
        panel.showPage(page);
	}*/
	/**
	 * This method initializes emitirFaturaButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getEmitirFaturaButton() {
		if (emitirFaturaButton == null) {
			emitirFaturaButton = new JButton();
			emitirFaturaButton.setText("Emitir Fatura");
			emitirFaturaButton.setEnabled(false);
			emitirFaturaButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.out.println("Emitir Fatura"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return emitirFaturaButton;
	}

	/**
	 * This method initializes valorTotalTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getValorTotalTextField() {
		if (valorTotalTextField == null) {
			valorTotalTextField = new JTextField();
			valorTotalTextField.setBounds(new Rectangle(438, 256, 117, 20));
			valorTotalTextField.setEditable(false);
		}
		return valorTotalTextField;
	}

	/**
	 * This method initializes todosEstadoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getTodosEstadoCheckBox() {
		if (todosEstadoCheckBox == null) {
			todosEstadoCheckBox = new JCheckBox();
			todosEstadoCheckBox.setBounds(new Rectangle(5, 23, 196, 21));
			todosEstadoCheckBox.setText("Listar somente as não pagas");
			todosEstadoCheckBox.addItemListener(new java.awt.event.ItemListener() {
				public void itemStateChanged(java.awt.event.ItemEvent e) {
					getContasList().updateTable();
				}
			});
		}
		return todosEstadoCheckBox;
	}

	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(419,322));
					gui.add(new AgendaTreinamentoCadastreForm(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}
	
	

	public AgendaTreinamento getAgenda() {
		return agenda;
	}

	public Treinamento getLote() {
		return lote;
	}

	public void setLote(Treinamento lote) {
		this.lote = lote;
	}
	
	
/*	public void editRegister(AgendaLote agendaLote, Session s) {
		try{
			agenda.setId(agendaLote.getId());
			s.refresh(agenda);	
			
			arrematante = (Participante) s.createQuery("select al.encerramento.lanceVencedor.participante from AgendaLote al where al.id="+agenda.getId()).uniqueResult();
			agenda.getId();					
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}*/	
	
	public void editRegister(AgendaTreinamento agendaLote) {		
		try{
			agenda.setId(agendaLote.getId());
			AgendaTreinamento al = (AgendaTreinamento) RemoteServicesUtility.getInstance().refresh(agenda);//s.refresh(agenda);
			agenda.setTurno(al.getTurno());
			agenda.setConfirmada(al.isConfirmada());
			agenda.setEncerrada(al.isEncerrada());
			//agenda.setIncremento(al.getIncremento());
			agenda.setInicio(al.getInicio());
			agenda.setInicioPrevisto(al.getInicioPrevisto());
			//agenda.setLanceInicial(al.getLanceInicial());
			agenda.setRecebendoInteressados(al.isRecebendoInteressados());
			agenda.setStatus(al.getStatus());
			agenda.setTermino(al.getTermino());
			agenda.setTerminoPrevisto(al.getTerminoPrevisto());
			agenda.getId();	
			
			cliente = null;//TODO buscar o pagante//RemoteAgendaTreinamentoService.getInstance().getArrematanteByAgendaId(agenda.getId());
					
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	/*public void editRegister(AgendaLote agendaLote) {		
		Session s = null;
		try{
			s = LocalServicesUtility.getInstance().openSession();
			editRegister(agendaLote,s);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if (s != null) s.close();
		}
		
	}*/

	
	@SuppressWarnings("unchecked")
	private class ContasList extends JXTable {
	
		
		private List<ParticipanteContaPagar> elements = new ArrayList();
		@SuppressWarnings("deprecation")
		public ContasList() {
			super();
			this.setModel(new ParticipanteContaPagarListModel(null));
			getColumnModel().getColumn(0).setPreferredWidth(10);
			getColumnModel().getColumn(1).setPreferredWidth(100);
			Highlighter highlighters = new org.jdesktop.swingx.decorator.AlternateRowHighlighter();
			setHighlighters(highlighters);
			addPropertyChangeListener(new MyPropertyChangeListener());
			updateTable();
			addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					int row = getSelectedRow();
					if (getElements() != null && row > -1){
						getEditarContaButton().setEnabled(true);
						getRemoverContaButton().setEnabled(true);
						getEmitirBoletoButton().setEnabled(true);
						getEmitirFaturaButton().setEnabled(true);
						getDesistenciaContaButton().setEnabled(true);					
					}
				}
				@Override
				public void focusLost(FocusEvent arg0) {
					
				}
				
			});
			
		}

		public void updateTable() {
			getEditarContaButton().setEnabled(false);
			getRemoverContaButton().setEnabled(false);
			getEmitirBoletoButton().setEnabled(false);
			getEmitirFaturaButton().setEnabled(false);
			getDesistenciaContaButton().setEnabled(false);
			try{
				
				elements.clear();
				boolean todasContas = true;
				if (!getTodosEstadoCheckBox().isSelected())
					todasContas = true;
				else todasContas = false;
				List list = RemoteContaService.getInstance().listContasPagarValues(agenda.getId(), todasContas);			
				float soma = 0.0f;
				if (list != null && list.size() > 0) {
					int ne = list.size();
					java.util.Iterator it = list.iterator();
					java.lang.Object values[][] = new java.lang.Object[ne][4];
					int i = 0;
					
					while (it.hasNext()) {
						Object objs[] = (Object[]) it.next();

						values[i][0] = (i+1);
						
						tempDateFieldChooser.setDate((Date)objs[1]);
						values[i][1] = tempDateFieldChooser.getDateHourField().getText();
						values[i][2] = objs[2];
						
						soma+=(Float)objs[2];
						
						ParticipanteContaPagar contaPagar = new ParticipanteContaPagar();						
						
						contaPagar.setId((Integer)objs[0]);
						contaPagar.setDataVencimento((Date) objs[1]);
						contaPagar.setValor((Float)objs[2]);
						contaPagar.setPaga((Boolean)objs[3]);
						contaPagar.setDesitenciaArrematante((Boolean)objs[4]);
						contaPagar.setDataPagto((Date)objs[5]);
						contaPagar.setFormaPagamento((FormaPagamento)objs[6]);
						contaPagar.setAgenda(agenda);
						
						if (contaPagar.isPaga()) values[i][3]="Paga";
						else if (contaPagar.isDesitenciaArrematante()) values[i][3]="Desistência";
						else{
							Calendar cal = Calendar.getInstance();
							Calendar venc = Calendar.getInstance();
							venc.setTime(contaPagar.getDataVencimento());
							if (cal.after(venc)){
								values[i][3]="Vencida";
								//getColumnModel().getRow(i).setPreferredWidth(100);
							}
							else values[i][3]="A pagar";
						}
						
						elements.add(contaPagar);
						
						i++;

					}					
					
					getValorTotalTextField().setText(Moeda.getValorReal(soma));
			
					if (cliente != null){
						getClienteTextField().setText(cliente.getNome());
					}
					
					
					setModel(new ParticipanteContaPagarListModel(values));
					getColumnModel().getColumn(0).setPreferredWidth(10);
					getColumnModel().getColumn(1).setPreferredWidth(100);
					updateUI();
				} else {
					setModel(new ParticipanteContaPagarListModel(null));
					getColumnModel().getColumn(0).setPreferredWidth(10);
					getColumnModel().getColumn(1).setPreferredWidth(100);
					updateUI();
				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		
		/*public void updateTable() {
			Session s = null;	
			getEditarContaButton().setEnabled(false);
			getRemoverContaButton().setEnabled(false);
			getEmitirBoletoButton().setEnabled(false);
			getEmitirFaturaButton().setEnabled(false);
			getDesistenciaContaButton().setEnabled(false);
			try{
				
				elements.clear();
				s = LocalServicesUtility.getInstance().openSession();
				String somenteNPagas="select contaPagar.id, contaPagar.dataVencimento, contaPagar.valor, contaPagar.paga, contaPagar.desitenciaArrematante, contaPagar.dataPagto, contaPagar.formaPagamento from ParticipanteContaPagar contaPagar where contaPagar.paga=false and contaPagar.agendaLote.id="+agenda.getId()+" order by contaPagar.dataVencimento ASC";
				String todas="select contaPagar.id, contaPagar.dataVencimento, contaPagar.valor, contaPagar.paga, contaPagar.desitenciaArrematante, contaPagar.dataPagto, contaPagar.formaPagamento from ParticipanteContaPagar contaPagar where contaPagar.agendaLote.id="+agenda.getId()+" order by contaPagar.dataVencimento ASC";				
				String query = null;
				if (!getTodosEstadoCheckBox().isSelected())
					query=todas;
				else query=somenteNPagas;
				
				List list = s.createQuery(query).list();				
				float soma = 0.0f;
				if (list != null && list.size() > 0) {
					int ne = list.size();
					java.util.Iterator it = list.iterator();
					java.lang.Object values[][] = new java.lang.Object[ne][4];
					int i = 0;
					
					while (it.hasNext()) {
						Object objs[] = (Object[]) it.next();

						values[i][0] = (i+1);
						
						tempDateFieldChooser.setDate((Date)objs[1]);
						values[i][1] = tempDateFieldChooser.getDateHourField().getText();
						values[i][2] = objs[2];
						
						soma+=(Float)objs[2];
						
						ParticipanteContaPagar contaPagar = new ParticipanteContaPagar();						
						
						contaPagar.setId((Integer)objs[0]);
						contaPagar.setDataVencimento((Date) objs[1]);
						contaPagar.setValor((Float)objs[2]);
						contaPagar.setPaga((Boolean)objs[3]);
						contaPagar.setDesitenciaArrematante((Boolean)objs[4]);
						contaPagar.setDataPagto((Date)objs[5]);
						contaPagar.setFormaPagamento((FormaPagamento)objs[6]);
						contaPagar.setAgendaLote(agenda);
						
						if (contaPagar.isPaga()) values[i][3]="Paga";
						else if (contaPagar.isDesitenciaArrematante()) values[i][3]="Desistência";
						else{
							Calendar cal = Calendar.getInstance();
							Calendar venc = Calendar.getInstance();
							venc.setTime(contaPagar.getDataVencimento());
							if (cal.after(venc)){
								values[i][3]="Vencida";
								//getColumnModel().getRow(i).setPreferredWidth(100);
							}
							else values[i][3]="A pagar";
						}
						
						elements.add(contaPagar);
						
						i++;

					}					
					
					getValorTotalTextField().setText(Moeda.getValorReal(soma));
									
					//getCondicaoPagtoForm().getLoteTextField().setText(lote.getCodLote());
					
					//getCondicaoPagtoForm().editRegister(agenda);
					
					if (arrematante != null){
						getArrematanteTextField().setText(arrematante.getNome());
					}
					
					
					setModel(new ParticipanteContaPagarListModel(values));
					getColumnModel().getColumn(0).setPreferredWidth(10);
					getColumnModel().getColumn(1).setPreferredWidth(100);
					updateUI();
				} else {
					setModel(new ParticipanteContaPagarListModel(null));
					getColumnModel().getColumn(0).setPreferredWidth(10);
					getColumnModel().getColumn(1).setPreferredWidth(100);
					updateUI();
				}
				
				
			}catch(Exception ex){
				ex.printStackTrace();
			}finally{
				if (s != null) s.close();
			}
		}*/
		
		/* public Component getTableCellRendererComponent(  JTable table, Object value, boolean isSelected, boolean hasFocus,int row,int col)
		 {
		      Component comp = super.getTableCellRendererComponent(table,  value, isSelected, hasFocus, row, col);
		       String s =  table.getModel().getValueAt(row, VALIDATION_COLUMN ).toString();
		       if(s.equalsIgnoreCase("Fail")) 
		      {
		          comp.setForeground(Color.red);
		      }
		      return( comp );
		  }*/

		private class ParticipanteContaPagarListModel extends DefaultTableModel {

			Class types[] = new java.lang.Class[] { Integer.class,String.class,Float.class,String.class };

			boolean canEdit[] = new boolean[] {false, true, true, false };

			public ParticipanteContaPagarListModel(Object[][] values) {
				super(values,new String[] {"","Vencimento","Valor","Estado"});
			}
			public Class getColumnClass(int columnIndex) {

				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {

				return canEdit[columnIndex];
			}

		}

		public List<ParticipanteContaPagar> getElements() {
			return elements;
		}


		public void setElements(List<ParticipanteContaPagar> elements) {
			this.elements = elements;
		}		
	}
	
	public ParticipanteContaPagar getSelectedContaPagar(){
		ContasList jt = (ContasList) getContasList();
		//int col = jt.getSelectedColumn();
		int row = jt.getSelectedRow();
		if (jt.getElements() != null && row > -1){
			try {
				int ordem = (Integer) jt.getValueAt(row, 0);
				java.lang.Object obj = jt.getElements().get(ordem-1);
				return (ParticipanteContaPagar) obj;
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public int getSelectedContaPagarNumber(){
		ContasList jt = (ContasList) getContasList();
		//int col = jt.getSelectedColumn();
		int row = jt.getSelectedRow();
		return (Integer) jt.getValueAt(row, 0);		
	}

	private class MyPropertyChangeListener implements PropertyChangeListener {

		@SuppressWarnings("deprecation")
		public void propertyChange(PropertyChangeEvent e) {
			ContasList jt=null;
			int col=0;
			int row=0;
			
			
			if (e.getPropertyName() != null
				&& !(e.getPropertyName().equals("tableCellEditor")
						&& e.getNewValue() == null	)	){
				//System.err.println(e.getPropertyName());
				/*if (e.getPropertyName().equals("tableCellEditor")
					&& e.getNewValue() == null	) System.out.println("Editou");
				*/return;
			}
			
			jt = (ContasList) e.getSource();
			col = jt.getSelectedColumn();
			row = jt.getSelectedRow();
			
			if (jt.getElements() != null && row > -1)
				try {
					//int ordem = (Integer) jt.getValueAt(row, 0);
					java.lang.Object obj = getSelectedContaPagar();//jt.getElements().get(ordem);
					if (obj instanceof ParticipanteContaPagar) {
						ParticipanteContaPagar conta = (ParticipanteContaPagar) obj;
						if (col == 1){							
							String str = ((java.lang.String) jt.getValueAt(row, col));
							String data[] = str.split(" ");
							if (data.length > 0 && data[0] != null){
								String values[] = (data[0]).split("/");
								String d = values[0];
								if (d.charAt(0) == '0')
									d=d.charAt(1)+"";
								
								String m = values[1];
								if (m.charAt(0) == '0')
									m=""+(Integer.parseInt((m.charAt(1)+""))-1);
								else m=""+(Integer.parseInt(m)-1);
								
								int dia = Integer.parseInt(d);
								int mes = Integer.parseInt(m);
								int ano = Integer.parseInt(values[2]);
								Date dt = new Date();
								dt.setDate(dia);
								dt.setMonth(mes);
								dt.setYear(ano-1900);
								
								RemoteContaService.getInstance().updateContaPagarDataVencimento(conta.getId(), dt);
								jt.updateTable();
								/*Session s = null;
								try{
									s = LocalServicesUtility.getInstance().openSession();									
									s.beginTransaction();
									s.createQuery("update ParticipanteContaPagar conta set conta.dataVencimento=:dt where conta.id=:id")
										.setParameter("dt", dt)
										.setParameter("id", conta.getId()).executeUpdate();
									s.getTransaction().commit();
									jt.updateTable();
								}catch(Exception ex){
									ex.printStackTrace();
								}finally{
									if (s != null) s.close();
								}*/
							}
						}
						else if (col == 2){
							float valor = ((Float) jt.getValueAt(row, col));
							RemoteContaService.getInstance().updateContaPagarValor(conta.getId(), valor);
							jt.updateTable();
								/*Session s = null;
								try{
									s = LocalServicesUtility.getInstance().openSession();									
									s.beginTransaction();
									
									s.createQuery("update ParticipanteContaPagar conta set conta.valor=:value where conta.id=:id")
										.setParameter("value", valor)
										.setParameter("id", conta.getId()).executeUpdate();
									s.getTransaction().commit();
									jt.updateTable();
								}catch(Exception ex){
									ex.printStackTrace();
								}finally{
									if (s != null) s.close();
								}*/
							
						}
					}
				} catch (java.lang.Exception ex) {
					ex.printStackTrace();
				}
		}

	}

	private JLabel encerramentoDataLabel = null;

	private Participante cliente;

	private JButton editarContaButton = null;

	private JButton novaContaButton = null;

	private JButton desistenciaContaButton = null;

	private JButton emitirBoletoButton = null;

	private JButton emitirFaturaButton = null;

	private JLabel valorTotalLabel = null;

	private JTextField valorTotalTextField = null;

	private JCheckBox todosEstadoCheckBox = null;

/*	public ContaPagarCadastreForm getCondicaoPagtoForm() {
		if (condicaoPagtoForm == null){
			condicaoPagtoForm = new ContaPagarCadastreForm(); 
		}
		return condicaoPagtoForm;
	}*/

	private Participante initializeArrematanteTipoPessoa() throws Exception{
		return RemotePessoaService.getInstance().initializeParticipante(cliente.getId());
	}
/*	private Participante initializeArrematanteTipoPessoa(){
		Session s =null;
		if (arrematante == null) return null;
		try {
			s = LocalServicesUtility.getInstance().openSession();
			Object obj = s.createQuery("from Participante p where p.id="+arrematante.getId()).uniqueResult();
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
	}*/

} 