package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entities.Chapitre;

public class ChapitreDAO extends GenericDAO<Chapitre, Long> {
	
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public ChapitreDAO() {
		super(Chapitre.class, Long.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}

}
