package com.adapit.portal.ui.forms.treinamento.contapagar;

import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.adapit.portal.entidades.FormaPagamento;
import com.adapit.portal.entidades.ParticipanteContaPagar;
import com.adapit.portal.services.remote.RemoteContaService;
import com.adapit.portal.ui.frames.AdapitVirtualFrame;
import com.workcase.gui.custom.calendar.DateHourChooser;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.utils.Moeda;

public class PagarContaForm extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel valorAPagarLabel = null;
	private JTextField valorPagarTextField = null;
	private JLabel tipoPagamentoLabel = null;
	private JComboBox tipoPagamentoComboBox = null;
	private JCheckBox marcarPagoCheckBox = null;
	private DateHourChooser dataPagamentoDateChooser = null;
	private JLabel dataPagamentoLabel = null;
	private JLabel vencimentoLabel = null;
	private DateHourChooser vencimentoDateChooser = null;
	private JButton salvarButton = null;
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	private ParticipanteContaPagar conta = new ParticipanteContaPagar();  //  @jve:decl-index=0:
	/**
	 * This is the default constructor
	 */
	public PagarContaForm() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		vencimentoLabel = new JLabel();
		vencimentoLabel.setBounds(new Rectangle(15, 37, 86, 21));
		vencimentoLabel.setText("Vencimento:");
		dataPagamentoLabel = new JLabel();
		dataPagamentoLabel.setBounds(new Rectangle(15, 110, 115, 22));
		dataPagamentoLabel.setText("Data do Pagamento:");
		tipoPagamentoLabel = new JLabel();
		
		tipoPagamentoLabel.setBounds(new Rectangle(15, 63, 104, 20));
		tipoPagamentoLabel.setText("Tipo do Pagamento:");
		valorAPagarLabel = new JLabel();
		valorAPagarLabel.setBounds(new Rectangle(15, 12, 85, 22));
		valorAPagarLabel.setText("Valor a Pagar:");
		this.setSize(284, 176);
		this.setLayout(null);
		this.add(valorAPagarLabel, null);
		this.add(getValorPagarTextField(), null);
		this.add(tipoPagamentoLabel, null);
		this.add(getTipoPagamentoComboBox(), null);
		this.add(getMarcarPagoCheckBox(), null);
		this.add(getDataPagamentoDateChooser(), null);
		this.add(dataPagamentoLabel, null);
		this.add(vencimentoLabel, null);
		this.add(getVencimentoDateChooser(), null);
		this.add(getSalvarButton(), null);
	}

	/**
	 * This method initializes valorPagarTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getValorPagarTextField() {
		if (valorPagarTextField == null) {
			valorPagarTextField = new JTextField();
			valorPagarTextField.setBounds(new Rectangle(106, 12, 120, 22));
			valorPagarTextField.setEditable(true);
		}
		return valorPagarTextField;
	}

	/**
	 * This method initializes tipoPagamentoComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getTipoPagamentoComboBox() {
		if (tipoPagamentoComboBox == null) {
			tipoPagamentoComboBox = new JComboBox();
			tipoPagamentoComboBox.setBounds(new Rectangle(15, 85, 213, 20));
			for(int i=0; i < FormaPagamento.values().length; i++){
				tipoPagamentoComboBox.addItem(
						FormaPagamento.values()[i].name().replace("_", " "));
			}
		}
		return tipoPagamentoComboBox;
	}

	/**
	 * This method initializes marcarPagoCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
	private JCheckBox getMarcarPagoCheckBox() {
		if (marcarPagoCheckBox == null) {
			marcarPagoCheckBox = new JCheckBox();
			marcarPagoCheckBox.setBounds(new Rectangle(143, 62, 130, 21));
			marcarPagoCheckBox.setText("Informar como Pago");
		}
		return marcarPagoCheckBox;
	}

	/**
	 * This method initializes dataPagamentoDateChooser	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private DateHourChooser getDataPagamentoDateChooser() {
		if (dataPagamentoDateChooser == null) {
			dataPagamentoDateChooser = new DateHourChooser(messages.getCurrentLocale(), false, true, false);
			dataPagamentoDateChooser.setBounds(new Rectangle(140, 110, 132, 22));
		}
		return dataPagamentoDateChooser;
	}

	/**
	 * This method initializes vencimentoDateChooser	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private DateHourChooser getVencimentoDateChooser() {
		if (vencimentoDateChooser == null) {
			vencimentoDateChooser = new DateHourChooser(messages.getCurrentLocale(), false, true, false);
			vencimentoDateChooser.setBounds(new Rectangle(106, 36, 120, 22));
		}
		return vencimentoDateChooser;
	}
	
	@SuppressWarnings("unused")
	private void bind() throws Exception{
		conta.setValor(Moeda.valorRealToFloat(getValorPagarTextField().getText()));
		conta.setDataVencimento(getVencimentoDateChooser().getDate());
		conta.setPaga(getMarcarPagoCheckBox().isSelected());
		conta.setFormaPagamento(FormaPagamento.valueOf(((String)getTipoPagamentoComboBox().getSelectedItem()).replace(" ", "_")));
		conta.setDataPagto(getDataPagamentoDateChooser().getDate());
	}

	/**
	 * This method initializes salvarButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSalvarButton() {
		if (salvarButton == null) {
			salvarButton = new JButton();
			salvarButton.setBounds(new Rectangle(93, 138, 91, 26));
			salvarButton.setText("Atualizar");
			salvarButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					try {
						boolean quitado = RemoteContaService.getInstance().isQuitadoWhenUpdatingContaPagar(conta);
						AdapitVirtualFrame.getInstance().showOperationSucess("Atualização de Conta a Pagar",
								"Conta a pagar atualizada com socesso");
						if (quitado)
							AdapitVirtualFrame.getInstance().showOperationSucess("Atualização de Lote", 
									"Todas as contas respectivas ao lote foram pagas."
									+'\n'
									+"O lote desta conta foi setado com o pagamento quitado");
					} catch (Exception e) {
						e.printStackTrace();
					}
					/*Session s = null;
					try {
						bind();
					} catch (Exception e1) {
						e1.printStackTrace();
						return;
					}
					try {
						s = LocalServicesUtility.getInstance().openSession();
						s.beginTransaction();
						s.createQuery("update ParticipanteContaPagar conta" +
								" set conta.valor=:valor, " +
								"conta.dataVencimento=:dtv, " +
								"conta.paga=:paga, " +
								"conta.formaPagamento=:forma, " +
								"conta.dataPagto=:dtp " +
								" where conta.id=:id")
								.setParameter("id", conta.getId())
								.setParameter("valor", conta.getValor())
								.setParameter("dtv", conta.getDataVencimento())
								.setParameter("paga", conta.isPaga())
								.setParameter("dtp", conta.getDataPagto())
								.setParameter("forma", conta.getFormaPagamento().ordinal())
								.executeUpdate();
						//s.update(conta);
						s.getTransaction().commit();
						LeilaoVirtualFrame.getInstance().showOperationSucess("Atualização de Conta a Pagar", "Conta a pagar atualizada com socesso");
						
						String contasPagas="select count(conta) from ParticipanteContaPagar conta where conta.agendaLote.id=:id and conta.paga=true";
						String numContas="select count(conta) from ParticipanteContaPagar conta where conta.agendaLote.id=:id";
						int ncontas = (Integer) s.createQuery(numContas).setParameter("id", conta.getAgendaLote().getId()).uniqueResult();
						int ncontaspagas = (Integer) s.createQuery(contasPagas).setParameter("id", conta.getAgendaLote().getId()).uniqueResult();
						if (ncontas == ncontaspagas){
							s.beginTransaction();
							String updateLote="update Lote lote set lote.pagtoQuitado=true where lote.agendaLote.id=:id";
							s.createQuery(updateLote).setParameter("id",conta.getAgendaLote().getId()).executeUpdate();
							s.getTransaction().commit();
							LeilaoVirtualFrame.getInstance().showOperationSucess("Atualização de Lote", 
									"Todas as contas respectivas ao lote foram pagas."+'\n'+"O lote desta conta foi setado com o pagamento quitado");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}finally{
						if (s != null && s.isOpen()) s.close();
					}*/
				}
			});
		}
		return salvarButton;
	}
	
	public void editRegister(ParticipanteContaPagar objConta){
		conta.setId(objConta.getId());
		conta.setAgenda(objConta.getAgenda());
		conta.setBoletoBancario(objConta.isBoletoBancario());
		conta.setCliente(objConta.getCliente());
		conta.setCondicaoPagamento(objConta.getCondicaoPagamento());
		conta.setDataPagto(objConta.getDataPagto());
		conta.setDataVencimento(objConta.getDataVencimento());
		conta.setDesitenciaArrematante(objConta.isDesitenciaArrematante());
		conta.setFaturaPorSedex(objConta.getFaturaPorSedex());
		conta.setFormaPagamento(objConta.getFormaPagamento());
		conta.setPaga(objConta.isPaga());
		conta.setValor(objConta.getValor());
		updateAll();
	}
	
	
	public void updateAll(){
		getValorPagarTextField().setText(Moeda.getValorReal(conta.getValor()));
		getVencimentoDateChooser().setDate(conta.getDataVencimento());
		getMarcarPagoCheckBox().setSelected(conta.isPaga());
		if (conta.getFormaPagamento()!= null)
			getTipoPagamentoComboBox().setSelectedItem(
					conta.getFormaPagamento().name().replaceAll("_", " "));
		else getTipoPagamentoComboBox().setSelectedIndex(0);
		if (conta.getDataPagto() != null) getDataPagamentoDateChooser().setDate(conta.getDataPagto());
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
