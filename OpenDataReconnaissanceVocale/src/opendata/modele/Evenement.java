package opendata.modele;

public class Evenement {
	String id;
	String nameEvenement;
	String commune;
	int distance;
	
	

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "Evenement [id=" + id + ", nameEvenement=" + nameEvenement
				+ ", commune=" + commune + "]";
	}

	public Evenement() {
		super();
	}

	public Evenement(String id, String nameEvenement,String commune) {
		super();
		this.id = id;
		this.nameEvenement = nameEvenement;
		this.commune=commune;
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
	
	

}
