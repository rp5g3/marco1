package com.adapit.portal.services;

import java.util.Date;
import java.util.List;

import com.adapit.portal.dto.LoteReportDTO;
import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.Access;
import com.adapit.portal.entidades.CategoriaPreferidaEnum;
import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;

public interface UserService {

	@SuppressWarnings("unchecked")
	public List listByActiveValue(boolean active);

	/**
	 * retorna todos os usuário que alguma vez já foram desativados
	 */
	@SuppressWarnings("unchecked")
	public List listJustDeactivated(DeactivationReason inactivationReasons);

	/**
	 * retorna todos os usuários que são do tipo de pessoa juridica
	 */
	@SuppressWarnings("unchecked")
	public List listByTipoPessoaJuridica(TipoPessoa joinTipoPessoa);

	/**
	 * retorna todos os usuários que são do tipo de pessoa física
	 */
	@SuppressWarnings("unchecked")
	public List listByTipoPessoaFisica(TipoPessoa joinTipoPessoa);

	@SuppressWarnings("unchecked")
	public List listClientsByLastAccessDateInterval(Date valorA, Date valorB);

	@SuppressWarnings("unchecked")
	public List listByLastAccessDateInterval(Date valorB, Date valorA);

	@SuppressWarnings("unchecked")
	public List listByRole(UserCadastreType role);

	@SuppressWarnings("unchecked")
	public List listNullPessoa(Pessoa dadosPessoais);

	@SuppressWarnings("unchecked")
	public List listLikeNomePessoa(String joinPessoaNome);

	@SuppressWarnings("unchecked")
	public List listAll();

	public boolean saveOrUpdate(Usuario user) throws Exception;

	public boolean save(Usuario user) throws Exception;

	public boolean update(Usuario user) throws Exception;

	/**
	 * Remove o usuário pelo identificador da tabela
	 */
	public Usuario deleteById(String id) throws Exception;

	/**
	 * Remove o usuário pelo login de usuário
	 */
	public Usuario deleteByLogin(String login) throws Exception;

	/**
	 * Remove o usuário pela senha de usuário
	 */
	public Usuario deleteByPassword(String password) throws Exception;

	/**
	 * verifica se o usuário existe no sistema
	 */
	public boolean isValid(String login, String password);
	
	/**
	 * verifica se o usuário com o login informado já existe no sistema
	 */
	public boolean isValid(String login);

	/**
	 * Retorna o usuário pelo identificador da tabela
	 */
	public Usuario getUserById(int id) throws Exception;

	/**
	 * Retorna o usuário pelo login e senha e estado de ativação
	 */
	public Usuario getUserByLoginAndPasswordAndActive(String login,
			String password, boolean active) throws Exception;

	/**
	 * Retorna o usuário pelo login e senha
	 */
	public Usuario getUserByLoginAndPassword(String login, String password)
			throws Exception;

	@SuppressWarnings("unchecked")
	public List listAccordingTo(String desc, StringQueryKind byDescKind, UserDataFilterType un, UserCadastreType ur, UserSystemAccessFilterType byActive, int firstResult)  throws Exception;
	
	public Integer countAccordingTo(String desc, StringQueryKind byDescKind, UserDataFilterType un, UserCadastreType ur, UserSystemAccessFilterType byActive)  throws Exception;
	
	public UsuarioDTO loggingUser(String login, String pass) throws Exception;
	
	public List<LoteReportDTO> getLotesParticipacao(String userid) throws Exception;
	
	public List<LoteReportDTO> getAllLotesParticipacao(String userid) throws Exception;
	
	public List<DeactivationReason> listDeactivationReasonByUserLogin(String login) throws Exception;
	
	public Usuario getUsuarioByIdCascadingProperties(java.io.Serializable id, boolean accessList, boolean deactivationReasons, boolean dadosPessoais) throws Exception;
	
	public Usuario updateUsuario(Usuario user) throws Exception;
	
	public Usuario deactiveUsuario(Usuario user, DeactivationReason deactivationReason) throws Exception;
	
	public Usuario activeUsuario(Usuario user, DeactivationReason deactivationReason) throws Exception;
	
	public List<Usuario> listUsuariosAccordingTo(UserDataFilterType qKind, String value);
	
	public Usuario getByCpf(String cpf);
	
	public Usuario getByCnpj(String cnpj);
	
	public Usuario getByInscricaoEstadual(String inscEst);
	
	public Usuario getByRg(String rg);
	
	public String getNomePessoaByUsuarioLogin(String login);
	
	public String getEmailPessoaByUsuarioLogin(String login);
	
	public List<Access> listAccessByUsuarioLogin(String login) throws Exception;
	
	public Usuario getUserByLogin(String login)	throws Exception;

	public Object remove(Usuario[] users) throws Exception;

	public Usuario autenticateUsuario(Usuario usuario, boolean value) throws Exception;

	public List<Usuario> listUsuariosByPreference(
			CategoriaPreferidaEnum cat)
			throws Exception;
	
}