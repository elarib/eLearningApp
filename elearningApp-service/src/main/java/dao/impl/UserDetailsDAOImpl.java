package dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.GenericDAO;
import entities.UserDetails;

public class UserDetailsDAOImpl extends  GenericDAO<UserDetails, Integer> {

	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public UserDetailsDAOImpl() {
		super(UserDetails.class, Integer.class);

	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return EMF.createEntityManager();
	}

}
