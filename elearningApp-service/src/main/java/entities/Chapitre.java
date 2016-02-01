package entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Chapitre implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private int ordreDuChapitre;
	private String nom;
	@Column(length=2000)
	private String description;
	@ManyToOne
	@JoinColumn(name = "cours_id")
	private Cours cours;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "chapitre")
	private Collection<Lecon> lecons;
	
	public Chapitre() {
		super();
	}
	
	public Chapitre(int ordreDuChapitre, String nom, String description) {
		super();
		this.ordreDuChapitre = ordreDuChapitre;
		this.nom = nom;
		this.description = description;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Cours getCours() {
		return cours;
	}
	public void setCours(Cours cours) {
		this.cours = cours;
	}
	public Collection<Lecon> getLecons() {
		return lecons;
	}
	public void setLecons(Collection<Lecon> lecons) {
		this.lecons = lecons;
	}
	public int getOrdreDuChapitre() {
		return ordreDuChapitre;
	}
	public void setOrdreDuChapitre(int ordreDuChapitre) {
		this.ordreDuChapitre = ordreDuChapitre;
	}
	
}
