package dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.GenericDAO;
import dao.UserDAO;
import entities.User;
import exception.AuthenticationException;
import exception.ConfirmationException;
import exception.EntityNotFoundException;

public class UserDaoImpl extends GenericDAO<User, Long> implements UserDAO {

	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public UserDaoImpl() {
		super(User.class, Long.class);

	}
	
	
	
	
	

	public User getUserByUsername(String username) throws EntityNotFoundException {

		List<User> results = null;

		Query query = getEntityManager().createQuery("from User as u where u.userName = :username");
		query.setParameter("username", username);
		results = query.getResultList();
		if (results == null || results.size() <= 0) {
			throw new EntityNotFoundException(username + " not found ");
		} else {
			return results.get(0);
		}

	}

	public User getUserByEmail(String email) throws EntityNotFoundException {
		List<User> results = null;

		Query query = getEntityManager().createQuery("from User as u where u.email = :email");
		query.setParameter("email", email);
		results = query.getResultList();
		if (results == null || results.size() <= 0) {
			throw new EntityNotFoundException(email + " not found ");
		} else {
			return results.get(0);
		}
	}

	public User authenticatePersonUsingUsername(String username, String password) throws AuthenticationException {
		List<User> results = null;

		Query query = getEntityManager()
				.createQuery("from User as u where u.userName = :username and u.motDePasse = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);

		results = query.getResultList();
		if (results == null || results.size() <= 0) {
			throw new AuthenticationException("No users found");
		} else {
			return results.get(0);
		}

	}

	public User authenticatePersonUsingEmail(String email, String password) throws AuthenticationException {
		List<User> results = null;

		Query query = getEntityManager()
				.createQuery("from User as u where u.email = :email and u.motDePasse = :password");
		query.setParameter("email", email);
		query.setParameter("password", password);

		results = query.getResultList();
		if (results == null || results.size() <= 0) {
			throw new AuthenticationException("No users found");
		} else {
			return results.get(0);
		}

	}

	public User authenticatePersonViaSocialN(String username, String password) throws AuthenticationException {
		// TODO Auto-generated method stub
		return null;
	}

	protected EntityManager getEntityManager() {

		return EMF.createEntityManager();
	}

	public boolean confirmInscription(String email, String token) throws ConfirmationException {

		Query query = getEntityManager().createQuery("from User as u where u.email = :email and u.token = :token");
		query.setParameter("email", email);
		query.setParameter("token", token);

		List<User> results = query.getResultList();

		if (results == null || results.size() <= 0) {
			throw new ConfirmationException("The Token or The Email are not correct !");
		} else {

			User user = results.get(0);
			user.setEstConfirme(true);
			(new UserDaoImpl()).edit(user);

			return true;
		}

	}

	public boolean findEmail(String email) throws EntityNotFoundException {

		Query query = getEntityManager().createQuery("from User as u where u.email = :email ");
		query.setParameter("email", email);

		List<User> results = query.getResultList();

		if (results == null || results.size() <= 0) {
			throw new EntityNotFoundException("There is no such Email !");
		} else {

			return true;
		}
	}

	public User modifierInformation(User user, Object... Infos) throws ParseException {

		// Infos={Nom,Prenom,MotdePasse,dateDeNaissance}

		if (Infos[0] != null) {
			user.setNom(Infos[0].toString());
		}
		if (Infos[1] != null) {
			user.setPrenom(Infos[1].toString());
		}
		if (Infos[2] != null) {
			user.setMotDePasse(Infos[2].toString());
		}
		if (Infos[3] != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			user.setDateNaissance((Date) Infos[3]);
		}

		new UserDaoImpl().edit(user);

		return user;
	}






	
}
