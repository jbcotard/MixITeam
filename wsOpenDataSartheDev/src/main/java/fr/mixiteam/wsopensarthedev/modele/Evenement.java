package fr.mixiteam.wsopensarthedev.modele;

public class Evenement {
	String id;
	String nameEvenement;
	String commune;
	Integer note;

	public Evenement() {
		super();
	}

	public Evenement(String id, String nameEvenement,String commune) {
		super();
		this.id = id;
		this.nameEvenement = nameEvenement;
		this.commune=commune;
		this.note = Integer.valueOf(0);
	}

	public void setId(String i) {
		id=i;
		
	}

	public void setNameEvenement(String name) {
		nameEvenement=name;
		
	}

	public String getId() {
		return id;
	}

	public String getNameEvenement() {
		return nameEvenement;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}



	public Integer getNote() {
		return note;
	}

	public void setNote(Integer note) {
		this.note = note;
	}


	@Override
	public String toString() {
		return "Evenement{" +
				"id='" + id + '\'' +
				", nameEvenement='" + nameEvenement + '\'' +
				", commune='" + commune + '\'' +
				", note=" + note +
				'}';
	}
}
