package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@NamedQueries({
	@NamedQuery(name = "Categorie.findByName", query = "SELECT categorie FROM CategorieCours categorie WHERE categorie.nom= :nom"),
	@NamedQuery(name = "Categorie.findAllCategoriesNames", query = "SELECT cat.nom FROM CategorieCours cat"),

})
@Entity
public class CategorieCours implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@OneToMany(mappedBy = "categorie" ,fetch = FetchType.LAZY)
	Collection<Cours> cours;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Collection<Cours> getCours() {
		return cours;
	}
	public void setCours(Collection<Cours> cours) {
		this.cours = cours;
	}
	
	
	

}
