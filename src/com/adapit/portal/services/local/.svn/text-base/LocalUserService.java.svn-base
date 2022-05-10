package com.adapit.portal.services.local;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.adapit.portal.dto.LoteReportDTO;
import com.adapit.portal.dto.UsuarioDTO;
import com.adapit.portal.entidades.Access;
import com.adapit.portal.entidades.CategoriaPreferidaEnum;
import com.adapit.portal.entidades.DeactivationReason;
import com.adapit.portal.entidades.Pessoa;
import com.adapit.portal.entidades.TipoPessoa;
import com.adapit.portal.entidades.UserCadastreType;
import com.adapit.portal.entidades.Usuario;
import com.adapit.portal.services.StringQueryKind;
import com.adapit.portal.services.UserDataFilterType;
import com.adapit.portal.services.UserService;
import com.adapit.portal.services.UserSystemAccessFilterType;
import com.adapit.portal.services.dao.hibernate.UserServiceDAOHibernate;

public class LocalUserService implements UserService {

	private UserService userService;

	private static LocalUserService instance;

	private LocalUserService() {
		try {
			XmlBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("localServices.xml"));
			userService = (UserService) beanFactory
					.getBean("userServiceDAOHibernate");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static LocalUserService getInstance() {
		if (instance == null) {
			instance = new LocalUserService();
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public List listByActiveValue(boolean active) {
		try {
			return this.userService.listByActiveValue(active);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * retorna todos os usuário que alguma vez já foram desativados
	 */
	@SuppressWarnings("unchecked")
	public List listJustDeactivated(DeactivationReason inactivationReasons) {
		try {
			return this.userService.listJustDeactivated(inactivationReasons);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * retorna todos os usuários que são do tipo de pessoa juridica
	 */
	@SuppressWarnings("unchecked")
	public List listByTipoPessoaJuridica(TipoPessoa joinTipoPessoa) {
		try {
			return this.userService.listByTipoPessoaJuridica(joinTipoPessoa);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	/**
	 * retorna todos os usuários que são do tipo de pessoa física
	 */
	@SuppressWarnings("unchecked")
	public List listByTipoPessoaFisica(TipoPessoa joinTipoPessoa) {
		try {
			return this.userService.listByTipoPessoaFisica(joinTipoPessoa);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listClientsByLastAccessDateInterval(Date valorA, Date valorB) {
		try {
			return this.userService.listClientsByLastAccessDateInterval(valorA,
					valorB);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listByLastAccessDateInterval(Date valorB, Date valorA) {
		try {
			return this.userService
					.listByLastAccessDateInterval(valorB, valorA);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listByRole(UserCadastreType role) {
		try {
			return this.userService.listByRole(role);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listNullPessoa(Pessoa dadosPessoais) {
		try {
			return this.userService.listNullPessoa(dadosPessoais);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listLikeNomePessoa(String joinPessoaNome) {
		try {
			return this.userService.listLikeNomePessoa(joinPessoaNome);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List listAll() {
		try {
			return this.userService.listAll();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

	public boolean saveOrUpdate(Usuario user) throws Exception {
		try {
			return this.userService.saveOrUpdate(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteUserService_saveOrUpdateError");
		}
	}

	public boolean save(Usuario user) throws Exception {
		try {
			return this.userService.save(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteUserService_saveError");
		}
	}

	public boolean update(Usuario user) throws Exception {
		try {
			return this.userService.update(user);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteUserService_updateError");
		}
	}

	/**
	 * Remove o usuário pelo identificador da tabela
	 */
	public Usuario deleteById(String id) throws Exception {
		try {
			return this.userService.deleteById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * Remove o usuário pelo login de usuário
	 */
	public Usuario deleteByLogin(String login) throws Exception {
		try {
			return this.userService.deleteByLogin(login);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteUserService_deleteByLoginError");
		}
	}

	/**
	 * Remove o usuário pela senha de usuário
	 */
	public Usuario deleteByPassword(String password) throws Exception {
		try {
			return this.userService.deleteByPassword(password);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteUserService_deleteByPasswordError");
		}
	}

	/**
	 * verifica se o usuário existe no sistema
	 */
	public boolean isValid(String login, String password) {
		try {
			return this.userService.isValid(login, password);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	/**
	 * Retorna o usuário pelo identificador da tabela
	 */
	public Usuario getUserById(int id) throws Exception {
		try {
			return this.userService.getUserById(id);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("RemoteUserService_getUserByIdError");
		}
	}

	/**
	 * Retorna o usuário pelo login e senha e estado de ativação
	 */
	public Usuario getUserByLoginAndPasswordAndActive(String login,
			String password, boolean active) throws Exception {
		try {
			return this.userService.getUserByLoginAndPasswordAndActive(login,
					password, active);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"RemoteUserService_getUserByLoginAndPasswordAndActiveError");
		}
	}

	/**
	 * Retorna o usuário pelo login e senha
	 */
	public Usuario getUserByLoginAndPassword(String login, String password)
			throws Exception {
		try {
			return this.userService.getUserByLoginAndPassword(login, password);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"RemoteUserService_getUserByLoginAndPasswordError");
		}
	}

	public boolean isValid(String login) {
		try {
			return this.userService.isValid(login);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List listAccordingTo(String desc, StringQueryKind byDescKind,
			UserDataFilterType un, UserCadastreType ur, UserSystemAccessFilterType byActive, int firstResult) {
		try {
			return this.userService.listAccordingTo(desc, byDescKind, un, ur,
					byActive,firstResult);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	public Integer countAccordingTo(String desc, StringQueryKind byDescKind,
			UserDataFilterType un, UserCadastreType ur, UserSystemAccessFilterType byActive) {
		try {
			return this.userService.countAccordingTo(desc, byDescKind, un, ur,
					byActive);
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}
	
	public Usuario getUsuarioByIdCascadingProperties(java.io.Serializable id, boolean accessList, boolean deactivationReasons, boolean dadosPessoais) throws Exception {
		try {
			return ((UserServiceDAOHibernate)this.userService).getUsuarioByIdCascadingProperties(id, accessList, deactivationReasons, dadosPessoais);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception(
					"LocalUserService_getUsuarioByIdCascadingPropertiesError");
		}
	}
	
	public UsuarioDTO loggingUser(String login, String pass) throws Exception{
		try {
			return ((UserServiceDAOHibernate)this.userService).loggingUser(login,pass);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public List<LoteReportDTO> getLotesParticipacao(String userid) throws Exception{
		try {
			return this.userService.getLotesParticipacao(userid);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

	@Override
	public List<DeactivationReason> listDeactivationReasonByUserLogin(
			String login) throws Exception {
		return userService.listDeactivationReasonByUserLogin(login);
	}

	@Override
	public Usuario updateUsuario(Usuario user) throws Exception {
		return userService.updateUsuario(user);
	}

	@Override
	public Usuario activeUsuario(Usuario user,
			DeactivationReason deactivationReason) throws Exception {
		return userService.activeUsuario(user, deactivationReason);
	}

	@Override
	public Usuario deactiveUsuario(Usuario user,
			DeactivationReason deactivationReason) throws Exception {
		return userService.deactiveUsuario(user, deactivationReason);
	}

	@Override
	public List<Usuario> listUsuariosAccordingTo(UserDataFilterType kind,
			String value) {
		return userService.listUsuariosAccordingTo(kind, value);
	}

	@Override
	public Usuario getByCpf(String cpf) {
		return userService.getByCpf(cpf);
	}
	
	@Override
	public Usuario getByCnpj(String cnpj) {
		return userService.getByCpf(cnpj);
	}

	@Override
	public Usuario getByInscricaoEstadual(String inscEst) {
		return userService.getByInscricaoEstadual(inscEst);
	}

	@Override
	public Usuario getByRg(String rg) {
		return userService.getByRg(rg);
	}

	@Override
	public String getEmailPessoaByUsuarioLogin(String login) {
		return userService.getEmailPessoaByUsuarioLogin(login);
	}

	@Override
	public String getNomePessoaByUsuarioLogin(String login) {
		return userService.getNomePessoaByUsuarioLogin(login);
	}

	@Override
	public List<Access> listAccessByUsuarioLogin(String login) throws Exception {
		return userService.listAccessByUsuarioLogin(login);
	}

	@Override
	public Usuario getUserByLogin(String login) throws Exception {
		return userService.getUserByLogin(login);
	}

	@Override
	public List<LoteReportDTO> getAllLotesParticipacao(String userid)
			throws Exception {
		return userService.getAllLotesParticipacao(userid);
	}

	@Override
	public Object remove(Usuario[] users) throws Exception{
		return userService.remove(users);
	}

	@Override
	public Usuario autenticateUsuario(Usuario usuario, boolean value)
			throws Exception {
		return userService.autenticateUsuario(usuario, value);
	}
	
	@Override
	public List<Usuario> listUsuariosByPreference(
			CategoriaPreferidaEnum cat)  throws Exception{
		return userService.listUsuariosByPreference(cat);
	}
}