package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Lecon implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;
	@Lob 
	@Column(name="content", length=1024)
	private String content;
	private String lienVideo;
	@ManyToOne
	@JoinColumn(name = "chapitre_id")
	private Chapitre chapitre;
	
	
	public Lecon() {
		super();
	}
	
	public Lecon(String nom, String lienVideo) {
		super();
		this.nom = nom;
		this.lienVideo = lienVideo;
	}


	public Lecon(String nom, String lienVideo, String content) {
		super();
		this.nom = nom;
		this.content = content;
		this.lienVideo = lienVideo;
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
	
	public String getLienVideo() {
		return lienVideo;
	}
	public void setLienVideo(String lienVideo) {
		this.lienVideo = lienVideo;
	}
	public Chapitre getChapitre() {
		return chapitre;
	}
	public void setChapitre(Chapitre chapitre) {
		this.chapitre = chapitre;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
