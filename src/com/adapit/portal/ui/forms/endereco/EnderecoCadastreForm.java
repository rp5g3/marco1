package com.adapit.portal.ui.forms.endereco;


import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.adapit.portal.entidades.AddressType;
import com.adapit.portal.entidades.BrasilEstado;
import com.adapit.portal.entidades.Endereco;
import com.adapit.portal.entidades.Pais;
import com.adapit.portal.entidades.Pessoa;
import com.workcase.gui.custom.logerror.LogErrorPanel;
import com.workcase.gui.custom.warning.JWarningComponent;
import com.workcase.gui.utils.ResourceMessage;
import com.workcase.gui.utils.SpringResourceMessage;
import com.workcase.gui.utils.SwingBinder;
import com.workcase.gui.utils.UIUtil;
import com.workcase.gui.utils.Validate;


@SuppressWarnings("serial")
public class EnderecoCadastreForm extends JPanel{
	
	private JPanel enderecoPanel;
	
	private JTextField cidadeTextField;
	
	private SwingBinder binder = new SwingBinder();  //  @jve:decl-index=0:
	
	private Endereco endereco = new Endereco();  //  @jve:decl-index=0:
	
	@SuppressWarnings("unchecked")
	private Map hashComps = new java.util.HashMap();  //  @jve:decl-index=0:
	
	private boolean processFocus = true;
	
	protected LogErrorPanel logErrorPanel;
	
	private JLabel cidadeTextFieldLabel;
	
	private ResourceMessage messages = SpringResourceMessage.getInstance();
	
	private JTextField ruaTextField;
	
	private JLabel ruaTextFieldLabel;
	
	private JTextField bairroTextField;
	
	private JLabel bairroTextFieldLabel;
	
	private JPanel caixaPostalComplementoPanel;
	
	private JTextField caixaPostalTextField;
	
	private JLabel caixaPostalTextFieldLabel;
	
	private JTextField complementoTextField;
	
	private JLabel complementoTextFieldLabel;
	
	private JPanel estadoNumeroPanel;
	
	private JComboBox estadoComboBox;
	
	private JLabel estadoComboBoxLabel;
	
	private JTextField numeroTextField;
	
	private JLabel numeroTextFieldLabel;
	
	private JTextField cepTextField;
	
	private JLabel cepTextFieldLabel;

	private AddressType tipoEndereco= AddressType.Residencial;  //  @jve:decl-index=0:

	private JLabel paisLabel = null;

	private SpringResourceMessage messages1 = null;

	private JComboBox paisComboBox = null;
	
	public EnderecoCadastreForm(){
		initialize();
	}
	
	private void initialize(){

		setSize(new Dimension(592, 230));
		setLocation(new java.awt.Point(0,0));
		setLayout(null);
		add(getEnderecoPanel());
		//Put such code into the end of initialize body
		add(this.getErrorPanel());
		newRegister();
		this.setErrorIcon(false);
		//End

	}
	
	public JPanel getEnderecoPanel(){

		if(enderecoPanel == null){
			paisLabel = new JLabel("* " +getMessages1().getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Cidade"));
			paisLabel.setBounds(new Rectangle(15, 0, 102, 20));
			paisLabel.setText("País:");
			paisLabel.setHorizontalAlignment(JLabel.LEFT);
			enderecoPanel = new JPanel();
			//enderecoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Endereço")));
			enderecoPanel.setSize(new Dimension(310, 160));
			enderecoPanel.setLocation(new Point(0, 10));
			enderecoPanel.setLayout(null);
			enderecoPanel.add(paisLabel);
			enderecoPanel.add(getPaisComboBox());
			enderecoPanel.add(getCidadeTextField());
			enderecoPanel.add(getCidadeTextFieldLabel());
			enderecoPanel.add(getRuaTextField());
			enderecoPanel.add(getRuaTextFieldLabel());
			enderecoPanel.add(getBairroTextField());
			enderecoPanel.add(getBairroTextFieldLabel());
			enderecoPanel.add(getCaixaPostalComplementoPanel());
			enderecoPanel.add(getEstadoNumeroPanel());
			enderecoPanel.add(getCepTextField());
			enderecoPanel.add(getCepTextFieldLabel());
			
		}
		return enderecoPanel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getCidadeTextField(){
		if(cidadeTextField == null){
			cidadeTextField = new JTextField();
			cidadeTextField.setText("");
			cidadeTextField.setSize(new java.awt.Dimension(180,20));
			cidadeTextField.setLocation(new java.awt.Point(120,25));
			this.binder.addBindProperty(this.endereco, this.cidadeTextField, "cidade");
			
			this.hashComps.put("cidade", this.cidadeTextField);
			JWarningComponent warn = new JWarningComponent( this.cidadeTextField);
			warn.setBounds(120, 25, 180, 20);
			return warn;
		}
		return cidadeTextField;
	}
	
	public Endereco validateEnderecoForm() throws Exception{
		setErrorIcon(false);
		binder.bind(endereco);
		endereco.setTipo(tipoEndereco);
		try {
			endereco.setPais(Pais.valueOf(((String)paisComboBox.getSelectedItem()).replace(" ", "_")));
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
	
		if (!validateEnderecoBean()){
			throw new Exception(messages.getMessage("ErroCamposEnderecoIncorretos"));
		}
		return endereco;
	}
	
	public Endereco cadastreEndereco() throws Exception{
		return validateEnderecoForm();
	}
	@SuppressWarnings("unchecked")
	public boolean validateEnderecoBean(){
		getErrorPanel().removeAllElements();
		if (processFocus) {
			if (UIUtil.processFocus(this)) {
				processFocus = false;
			}
		}
		Validate validate = new Validate();
		Map errors = validate.validate(this.endereco, "endereco");
		if (errors == null) return true;
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
					try{
						getErrorPanel().addError(messages.getMessage(msg),comp);
						comp.setToolTipText(messages.getMessage(msg));
					}catch(Exception e){
						e.printStackTrace();
					}
				} else {
					List args = (List) obj[1];
					Object[][] params = new Object[args.size()][2];
					for(int j=0; j < args.size(); j++) {
					   String key = (String) args.get(j);
					   params[j][0] = key;
					   params[j][1] = null;
					}
					try{
						getErrorPanel().addError(messages.getMessage(msg, params),comp);
						comp.setToolTipText(messages.getMessage(msg, params));
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		getErrorPanel().updateErrorList();
		getErrorPanel().setVisible(true);
		return false;
	}
	
	public void newRegister(){
//Nunca definir um novo objeto entidade!!!
		endereco.setId(0);
		endereco.setBairro(null);
		endereco.setCidade(null);
		endereco.setEstado(null);
		endereco.setNumero(0);
		endereco.setCaixaPostal(null);
		endereco.setCep(null);
		endereco.setRua(null);
		endereco.setComplemento(null);
		endereco.setTipo(tipoEndereco);

		binder.reverseBind(this.endereco);

		this.setErrorIcon(false);
		updateUI();
	}
	
	public void editRegister(Endereco objEndereco ){
//Nunca passar como argumento novos objetos!!!
		if (objEndereco == null) return;
		try {
			//org.apache.commons.beanutils.BeanUtils.copyProperties(this.endereco, objEndereco);
			this.endereco.setId(objEndereco.getId());
			this.endereco.setCidade(objEndereco.getCidade());
			this.endereco.setPais(objEndereco.getPais());
			this.endereco.setRua(objEndereco.getRua());
			this.endereco.setBairro(objEndereco.getBairro());
			this.endereco.setNumero(objEndereco.getNumero());
			this.endereco.setComplemento(objEndereco.getComplemento());
			this.endereco.setCep(objEndereco.getCep());
			this.endereco.setCaixaPostal(objEndereco.getCaixaPostal());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try {
			binder.reverseBind(this.endereco);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setErrorIcon(false);
	}
	
	public LogErrorPanel getErrorPanel(){
		if (logErrorPanel == null){
			logErrorPanel = new LogErrorPanel();
			logErrorPanel.setSize(new Dimension(286, 50));
			logErrorPanel.setLocation(15, 172);
		}
		return logErrorPanel;
	}
	
	public void setErrorIcon(boolean bool ){

		this.cidadeTextField.firePropertyChange("warnBorder", !bool, bool);
		this.ruaTextField.firePropertyChange("warnBorder", !bool, bool);
		this.bairroTextField.firePropertyChange("warnBorder", !bool, bool);
		this.complementoTextField.firePropertyChange("warnBorder", !bool, bool);
		this.estadoComboBox.firePropertyChange("warnBorder", !bool, bool);
		this.numeroTextField.firePropertyChange("warnBorder", !bool, bool);
		this.cepTextField.firePropertyChange("warnBorder", !bool, bool);
		this.getErrorPanel().setVisible(false);
	}
	
	protected JLabel getCidadeTextFieldLabel(){

		if(cidadeTextFieldLabel == null){
			cidadeTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Cidade"));
			cidadeTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			cidadeTextFieldLabel.setLocation(new java.awt.Point(15,25));
			cidadeTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return cidadeTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getRuaTextField(){
		if(ruaTextField == null){
			ruaTextField = new JTextField();
			ruaTextField.setText("");
			ruaTextField.setSize(new java.awt.Dimension(180,20));
			ruaTextField.setLocation(new java.awt.Point(120,48));
			this.binder.addBindProperty(this.endereco, this.ruaTextField, "rua");
			
			this.hashComps.put("rua", this.ruaTextField);
			JWarningComponent warn = new JWarningComponent( this.ruaTextField);
			warn.setBounds(120, 48, 180, 20);
			return warn;
		}
		return ruaTextField;
	}
	
	protected JLabel getRuaTextFieldLabel(){

		if(ruaTextFieldLabel == null){
			ruaTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Rua"));
			ruaTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			ruaTextFieldLabel.setLocation(new java.awt.Point(15,48));
			ruaTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return ruaTextFieldLabel;
	}
	@SuppressWarnings("unchecked")
	protected JComponent getBairroTextField(){
		if(bairroTextField == null){
			bairroTextField = new JTextField();
			bairroTextField.setText("");
			bairroTextField.setSize(new java.awt.Dimension(180,20));
			bairroTextField.setLocation(new java.awt.Point(120,71));
			this.binder.addBindProperty(this.endereco, this.bairroTextField, "bairro");
			
			this.hashComps.put("bairro", this.bairroTextField);
			JWarningComponent warn = new JWarningComponent( this.bairroTextField);
			warn.setBounds(120, 71, 180, 20);
			return warn;
		}
		return bairroTextField;
	}
	
	protected JLabel getBairroTextFieldLabel(){

		if(bairroTextFieldLabel == null){
			bairroTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Bairro"));
			bairroTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			bairroTextFieldLabel.setLocation(new java.awt.Point(15,71));
			bairroTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return bairroTextFieldLabel;
	}
	
	protected JPanel getCaixaPostalComplementoPanel(){

		if(caixaPostalComplementoPanel == null){
			caixaPostalComplementoPanel = new JPanel();
			caixaPostalComplementoPanel.setSize(new java.awt.Dimension(285,20));
			caixaPostalComplementoPanel.setLocation(new java.awt.Point(15,94));
			caixaPostalComplementoPanel.setLayout(null);
			caixaPostalComplementoPanel.add(getCaixaPostalTextField());
			caixaPostalComplementoPanel.add(getCaixaPostalTextFieldLabel());
			caixaPostalComplementoPanel.add(getComplementoTextField());
			caixaPostalComplementoPanel.add(getComplementoTextFieldLabel());
		}
		return caixaPostalComplementoPanel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getCaixaPostalTextField(){
		if(caixaPostalTextField == null){
			caixaPostalTextField = new JTextField();
			caixaPostalTextField.setText("");
			caixaPostalTextField.setSize(new java.awt.Dimension(67,20));
			caixaPostalTextField.setLocation(new java.awt.Point(105,0));
			this.binder.addBindProperty(this.endereco, caixaPostalTextField, "caixaPostal");
			
			this.hashComps.put("caixaPostal", caixaPostalTextField);
			JWarningComponent warn = new JWarningComponent(caixaPostalTextField);
			warn.setBounds(105, 0, 67, 20);
			return warn;			
		}
		return caixaPostalTextField;
	}
	
	protected JLabel getCaixaPostalTextFieldLabel(){

		if(caixaPostalTextFieldLabel == null){
			caixaPostalTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.CaixaPostal"));
			caixaPostalTextFieldLabel.setSize(new java.awt.Dimension(72,20));
			caixaPostalTextFieldLabel.setLocation(new java.awt.Point(0,0));
			caixaPostalTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return caixaPostalTextFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getComplementoTextField(){
		if(complementoTextField == null){
			complementoTextField = new JTextField();
			complementoTextField.setText("");
			complementoTextField.setSize(new java.awt.Dimension(35,20));
			complementoTextField.setLocation(new java.awt.Point(249,0));
			this.binder.addBindProperty(this.endereco, this.complementoTextField, "complemento");
			
			this.hashComps.put("complemento", this.complementoTextField);
			JWarningComponent warn = new JWarningComponent( this.complementoTextField);
			warn.setBounds(249, 0, 35, 20);
			return warn;
		}
		return complementoTextField;
	}
	
	protected JLabel getComplementoTextFieldLabel(){

		if(complementoTextFieldLabel == null){
			complementoTextFieldLabel = new JLabel(messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Complemento"));
			complementoTextFieldLabel.setSize(new java.awt.Dimension(72,20));
			complementoTextFieldLabel.setLocation(new java.awt.Point(175,0));
			complementoTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return complementoTextFieldLabel;
	}
	
	protected JPanel getEstadoNumeroPanel(){

		if(estadoNumeroPanel == null){
			estadoNumeroPanel = new JPanel();
			estadoNumeroPanel.setSize(new java.awt.Dimension(285,20));
			estadoNumeroPanel.setLocation(new java.awt.Point(15,117));
			estadoNumeroPanel.setLayout(null);
			estadoNumeroPanel.add(getEstadoComboBox());
			estadoNumeroPanel.add(getEstadoComboBoxLabel());
			estadoNumeroPanel.add(getNumeroTextField());
			estadoNumeroPanel.add(getNumeroTextFieldLabel());
		}
		return estadoNumeroPanel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getEstadoComboBox(){
		if(estadoComboBox == null){
			estadoComboBox = new JComboBox();
			estadoComboBox.setSize(new java.awt.Dimension(67,20));
			estadoComboBox.setLocation(new java.awt.Point(105,0));
			estadoComboBox.setEditable(true);
			for (int i=0; i < BrasilEstado.values().length;i++){
				estadoComboBox.addItem(BrasilEstado.values()[i].name());
			}
			this.binder.addBindProperty(this.endereco, this.estadoComboBox, "estado");
			
			this.hashComps.put("estado", this.estadoComboBox);
			JWarningComponent warn = new JWarningComponent( this.estadoComboBox);
			warn.setBounds(105, 0, 67, 20);
			return warn;
		}
		return estadoComboBox;
	}
	
	protected JLabel getEstadoComboBoxLabel(){

		if(estadoComboBoxLabel == null){
			estadoComboBoxLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Estado"));
			estadoComboBoxLabel.setSize(new java.awt.Dimension(72,20));
			estadoComboBoxLabel.setLocation(new java.awt.Point(0,0));
/*			estadoComboBoxLabel.setHorizontalAlignment(JLabel.RIGHT);
			estadoComboBoxLabel.setVerticalAlignment(JLabel.CENTER);*/
		}
		return estadoComboBoxLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getNumeroTextField(){
		if(numeroTextField == null){
			numeroTextField = new JTextField();
			numeroTextField.setText("");
			numeroTextField.setSize(new java.awt.Dimension(35,20));
			numeroTextField.setLocation(new java.awt.Point(249,0));
			this.binder.addBindProperty(this.endereco, this.numeroTextField, "numero");
			
			this.hashComps.put("numero", this.numeroTextField);
			JWarningComponent warn = new JWarningComponent( this.numeroTextField);
			warn.setBounds(249, 0, 35, 20);
			return warn;
		}
		return numeroTextField;
	}
	
	protected JLabel getNumeroTextFieldLabel(){

		if(numeroTextFieldLabel == null){
			numeroTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.Número"));
			numeroTextFieldLabel.setSize(new java.awt.Dimension(72,20));
			numeroTextFieldLabel.setLocation(new java.awt.Point(172,0));
			numeroTextFieldLabel.setHorizontalAlignment(JLabel.RIGHT);
		}
		return numeroTextFieldLabel;
	}
	
	@SuppressWarnings("unchecked")
	protected JComponent getCepTextField(){
		if(cepTextField == null){
			try {
				javax.swing.text.MaskFormatter format = new javax.swing.text.MaskFormatter("#####-###");
				cepTextField = new JFormattedTextField(format);
			} catch (Exception e) {
				cepTextField = new JTextField();
			}
			
			cepTextField.setText("");
			cepTextField.setSize(new java.awt.Dimension(180,20));
			cepTextField.setLocation(new java.awt.Point(120,140));
			this.binder.addBindProperty(this.endereco, this.cepTextField, "cep");
			
			this.hashComps.put("cep", this.cepTextField);
			JWarningComponent warn = new JWarningComponent( this.cepTextField);
			warn.setBounds(120, 140, 180, 20);
			return warn;
		}
		return cepTextField;
	}
	
	protected JLabel getCepTextFieldLabel(){

		if(cepTextFieldLabel == null){
			cepTextFieldLabel = new JLabel("* " +messages.getMessage("com.adapit.portal.ui.forms.manutencaousuario.AdminEnderecoCadastreForm.CEP"));
			cepTextFieldLabel.setSize(new java.awt.Dimension(100,20));
			cepTextFieldLabel.setLocation(new java.awt.Point(15,140));
			cepTextFieldLabel.setHorizontalAlignment(JLabel.LEFT);
		}
		return cepTextFieldLabel;
	}
	
	/**
	 * This method initializes messages1	
	 * 	
	 * @return com.workcase.gui.utils.SpringResourceMessage	
	 */
	private SpringResourceMessage getMessages1() {
		if (messages1 == null) {
			messages1 = (SpringResourceMessage) SpringResourceMessage.getInstance();
		}
		return messages1;
	}

	/**
	 * This method initializes paisComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getPaisComboBox() {
		if (paisComboBox == null) {
			paisComboBox = new JComboBox();
			paisComboBox.setBounds(new Rectangle(120, 0, 180, 22));
			for(int i=0; i< Pais.values().length; i++){
				paisComboBox.addItem(Pais.values()[i].name().replace("_"," "));
			}
			paisComboBox.setSelectedItem(Pais.Brasil.name().replace("_"," "));
		}
		return paisComboBox;
	}

	public static void main(String args[] ){

		new java.lang.Thread(
			new Runnable(){
				 public void run(){
					javax.swing.JFrame gui = new javax.swing.JFrame();
					gui.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
					gui.setLayout(new java.awt.BorderLayout());
					gui.setSize(new java.awt.Dimension(319,214));
					gui.add(new EnderecoCadastreForm(),java.awt.BorderLayout.CENTER);
					gui.setVisible(true);
				}
			}
		).run();
	}
	
	

	public void editRegister(Pessoa p) {
		editRegister(p.getEndereco());
	}

	public AddressType getTipoEndereco() {
		return tipoEndereco;
	}

	public void setTipoEndereco(AddressType tipoEndereco) {
		this.tipoEndereco = tipoEndereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"