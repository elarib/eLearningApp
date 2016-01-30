package entities;

public enum StatusCours {

	PRIVE("privé"), PUBLIQUE("publique"), WAITING("en attente de validation");

	private final String name;

	private StatusCours(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}

