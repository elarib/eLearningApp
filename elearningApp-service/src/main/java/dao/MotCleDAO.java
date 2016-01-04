package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.MotCle;

public class MotCleDAO extends GenericDAO<MotCle, Integer> {

	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public MotCleDAO() {
		super(MotCle.class, Integer.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}

	public MotCle findByName(String motCleName) {
		//
		TypedQuery<MotCle> query = getEntityManager().createNamedQuery("MotCle.findByName", MotCle.class)
				.setParameter("nom", motCleName);
		MotCle result = query.getSingleResult();
		return result;
	}

	// MotCle.findAllMotsClesNames
	public List<String> findAllMotsClesNames() {

		TypedQuery<String> query = getEntityManager().createNamedQuery("MotCle.findAllMotsClesNames", String.class);
		List<String> result = query.getResultList(); 
		return result;
		
	}
}
