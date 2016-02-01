package model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AjoutChapitreModel {

	@NotNull
	private int ordreDuChapitre;
	@NotNull
	private String name;
	@NotNull
	@Size(max=2000)
	private String description;
	
	public AjoutChapitreModel() {
		super();
	}

	public AjoutChapitreModel(int ordreDuChapitre, String name, String description) {
		super();
		this.ordreDuChapitre = ordreDuChapitre;
		this.name = name;
		this.description = description;
	}

	public int getOrdreDuChapitre() {
		return ordreDuChapitre;
	}

	public void setOrdreDuChapitre(int ordreDuChapitre) {
		this.ordreDuChapitre = ordreDuChapitre;
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
	
	
	
}
