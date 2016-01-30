package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Role;

public class RoleDao extends GenericDAO<Role, Integer>{
	
	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public RoleDao() {
		super(Role.class, Integer.class);
	}

	protected EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}

}
