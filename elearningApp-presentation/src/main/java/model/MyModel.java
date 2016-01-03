package model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


public class MyModel {

	@NotNull
	@Size(min = 5, max = 10)
	private String name;
	@NotNull
	@NotEmpty
	private String description;
	@NotNull
	@NotEmpty
	private String categorieName;
	private ArrayList<String> allmotsCles;
	@NotNull
	private ArrayList<String> motsClesChoisis;
	private List<String> allCategoriesNames;
	@NotEmpty
	private String prerequis;
	@NotEmpty
	private String objectifs;

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

	
	public String getCategorieName() {
		return categorieName;
	}

	public void setCategorieName(String categorieName) {
		this.categorieName = categorieName;
	}


	public ArrayList<String> getAllmotsCles() {
		return allmotsCles;
	}

	public void setAllmotsCles(ArrayList<String> allmotsCles) {
		this.allmotsCles = allmotsCles;
	}

	public ArrayList<String> getMotsClesChoisis() {
		return motsClesChoisis;
	}

	public void setMotsClesChoisis(ArrayList<String> motsClesChoisis) {
		this.motsClesChoisis = motsClesChoisis;
	}

	public List<String> getAllCategoriesNames() {
		return allCategoriesNames;
	}

	public void setAllCategoriesNames(List<String> allCategoriesNames) {
		this.allCategoriesNames = allCategoriesNames;
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

}
