package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@NamedQuery(name = "User.findAllEmails", query = "SELECT user.email FROM User user ")
@Entity
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NotNull
	@Column(unique = true)
	@NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
	@Size(min = 4, max = 10, message = "Le nom d'utilisateur doit être supérieur à 4 caractére ")
	private String userName;

	@NotNull
	@NotBlank(message = "Le prénom ne peut pas être vide")
	@Size(min = 1, max = 20, message = "Le prénon doit être inférieur à 15 caracteres")
	private String prenom;

	@NotNull
	@NotBlank(message = "Le nom ne peut pas être vide")
	@Size(min = 1, max = 20, message = "Le nom doit être inférieur à 15 caracteres")
	private String nom;

	@NotNull
	@NotBlank(message = "Le mot de passe ne peut pas être vide")
	@Size(min = 5, max = 80, message = "Le mot de passe doit être entre 5 à 15 caracteres")
	private String motDePasse;

	@Email
	@NotBlank(message = "L'email ne peut pas être vide")
	@Column(unique = true)
	private String email;

	// Juste pour vérifier la confirmation du motDePasse
	@NotNull
	@NotBlank(message = "Vous devez confirmer votre mot de passe")
	@Size(min = 5, max = 20, message = "Vous devez confirmer votre mot de passe")
	@Transient
	private String motDePasseConfirm;

	@Temporal(value = TemporalType.DATE)
	@NotNull(message = "La date de naissance ne peut pas être vide")
	private Date dateNaissance;

	private String dateInscription;
	private String dateDernierAcces;
	private boolean estConfirme;

	@OneToMany
	@JoinColumn(name = "user_id")
	private Collection<Role> roles;

	@OneToMany(mappedBy = "prof")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Collection<Cours> coursAjoutesParProf;
	
	@ManyToMany
	@JoinTable(name = "Etudiant_Cours", joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name = "cours_id"))
	private Collection<Cours> coursAutorises;

	private String token;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(String dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getDateDernierAcces() {
		return dateDernierAcces;
	}

	public void setDateDernierAcces(String dateDernierAcces) {
		this.dateDernierAcces = dateDernierAcces;
	}

	public boolean isEstConfirme() {
		return estConfirme;
	}

	public void setEstConfirme(boolean estConfirme) {
		this.estConfirme = estConfirme;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getMotDePasseConfirm() {
		return motDePasseConfirm;
	}

	public void setMotDePasseConfirm(String motDePasseConfirm) {
		this.motDePasseConfirm = motDePasseConfirm;
	}

	@Override
	public String toString() {
		return "User [Token=" + token + ", userName=" + userName + ", prenom=" + prenom + ", nom=" + nom
				+ ", motDePasse=" + motDePasse + ", motDePasseConfirm=" + motDePasseConfirm + ", email=" + email
				+ ", dateNaissance=" + dateNaissance.toString() + "]";
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Collection<Cours> getCoursAjoutesParProf() {
		return coursAjoutesParProf;
	}

	public void setCoursAjoutesParProf(Collection<Cours> coursAjoutesParProf) {
		this.coursAjoutesParProf = coursAjoutesParProf;
	}

	public Collection<Cours> getCoursAutorises() {
		return coursAutorises;
	}

	public void setCoursAutorises(Collection<Cours> coursAutorises) {
		this.coursAutorises = coursAutorises;
	}
	
	

}
