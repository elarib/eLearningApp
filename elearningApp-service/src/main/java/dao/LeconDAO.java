package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Lecon;

public class LeconDAO extends GenericDAO<Lecon,Long>{
	
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public LeconDAO() {
		super(Lecon.class, Long.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}

}
