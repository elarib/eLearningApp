package model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AjoutLeconModel {

	@NotNull
	@Size(min = 5, max = 10)
	private String name;
	@NotNull
	@Size(min = 10, max = 20)
	private String lienVideo;

	public AjoutLeconModel() {
		super();
	}
	
	

	public AjoutLeconModel(String name, String lienVideo) {
		super();
		this.name = name;
		this.lienVideo = lienVideo;
	}

	public String getLienVideo() {
		return lienVideo;
	}

	public void setLienVideo(String lienVideo) {
		this.lienVideo = lienVideo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
