package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
	@NamedQuery(name = "MotCle.findByName", query = "SELECT mot FROM MotCle mot WHERE mot.nom= :nom"),
	@NamedQuery(name = "MotCle.findAllMotsClesNames", query = "SELECT mot.nom FROM MotCle mot"),
})
@Entity
public class MotCle implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@ManyToMany(mappedBy = "motsCles", fetch = FetchType.LAZY)
	private Collection<Cours> cours;
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
