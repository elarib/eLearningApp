package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import entities.CategorieCours;
import entities.Chapitre;
import entities.Cours;

public class CategorieDAO extends GenericDAO<CategorieCours, Integer> {
	
	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory(Constants.PERSISTANCE_UNIT_NAME);

	public CategorieDAO() {
		super(CategorieCours.class, Integer.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return EMF.createEntityManager();
	}
	

	public CategorieCours findByName(String nom){
		TypedQuery<CategorieCours> query = getEntityManager().createNamedQuery("Categorie.findByName", CategorieCours.class)
				.setParameter("nom", nom);
		CategorieCours result = query.getSingleResult();
		return result;
	}
	
	public List<String> findAllCategoriesNames(){
		TypedQuery<String> query = getEntityManager().createNamedQuery("Categorie.findAllCategoriesNames", String.class);
		ArrayList<String> result = (ArrayList<String>) query.getResultList();
		return result;
	}
	
	

}
