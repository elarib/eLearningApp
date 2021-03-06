package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.Cours;
import entities.User;

public class CoursDAO extends GenericDAO<Cours, Long> {

	private static final EntityManagerFactory EMF = Persistence
			.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public CoursDAO() {
		super(Cours.class, Long.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}

	public Cours findByName(String name) {
		TypedQuery<Cours> query = getEntityManager().createNamedQuery("Cours.findByName", Cours.class)
				.setParameter("name", name);
		Cours result = query.getSingleResult();
		return result;
	}

	public Cours findByNameAndDescription(String name, String description) {
		TypedQuery<Cours> query = getEntityManager().createNamedQuery("Cours.findByNameAndDesc", Cours.class)
				.setParameter("name", name).setParameter("description", description);
		Cours result = query.getSingleResult();
		return result;
	}

	public List<Cours> findByProf(User prof) {
		List<Cours> coursProf;
		TypedQuery<Cours> query = getEntityManager().createNamedQuery("Cours.findByProf", Cours.class)
				.setParameter("prof", prof);
		coursProf = query.getResultList();
		return coursProf;
	}

}