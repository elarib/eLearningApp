package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


@NamedQueries({
	@NamedQuery(name = "Cours.findByName", query = "SELECT cours FROM Cours cours WHERE cours.name= :name"),
	@NamedQuery(name = "Cours.findByNameAndDesc", query = "SELECT cours FROM Cours cours WHERE cours.name= :name and cours.description=  :description"),

})
@Entity
@Table(name = "COURS")
public class Cours implements Serializable {
	

	public Cours(){	
	}
	public Cours(String name, String description, Date dateAjout, String prerequis, String objectifs) {
		super();
		this.name = name;
		this.description = description;
		this.dateAjout = dateAjout;
		this.prerequis = prerequis;
		this.objectifs = objectifs;
	}



	//TODO l'annotation vaut mieux la faire avec hibernate ou springMVC
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	@Size(min = 5, max = 10)
	private String name;
	@Size(min = 10, max = 20)
	private String description;
	private Date dateAjout;
	private String prerequis;
	private String objectifs;
	@Enumerated(EnumType.ORDINAL)
	private StatusCours status;
	@ManyToOne
	@JoinColumn(name = "categ_id")
	CategorieCours categorie;
	@ManyToMany
	@JoinTable(name = "COURS_MCLE", joinColumns = @JoinColumn(name="cours_id"),
	inverseJoinColumns = @JoinColumn(name = "motCle_id"))
	private Collection<MotCle> motsCles;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cours")
	private Collection<Chapitre> chapitres;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDateAjout() {
		return dateAjout;
	}
	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}
	
	public String getPrerequis() {
		return prerequis;
	}
	public void setPrerequis(String prerequis) {
		this.prerequis = prerequis;
	}
	public String getObjectifs() {
		return objectifs;
	}
	public void setObjectifs(String objectifs) {
		this.objectifs = objectifs;
	}
	public StatusCours getStatus() {
		return status;
	}
	public void setStatus(StatusCours status) {
		this.status = status;
	}
	public CategorieCours getCategorie() {
		return categorie;
	}
	public void setCategorie(CategorieCours categorie) {
		this.categorie = categorie;
	}
	public Collection<MotCle> getMotsCles() {
		return motsCles;
	}
	public void setMotsCles(Collection<MotCle> motsCles) {
		this.motsCles = motsCles;
	}
	public Collection<Chapitre> getChapitres() {
		return chapitres;
	}
	public void setChapitres(Collection<Chapitre> chapitres) {
		this.chapitres = chapitres;
	}
	
	
	
}
