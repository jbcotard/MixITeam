package opendata.modele;


public class EvenementDetail {
	String id;
	private String entreprise;
	String nameEvenement;
	String commune;
	String lattitude;
	String longitude;
	String adresse;
	String codepotal;
	
	public String getEquipement() {
		return equipement;
	}

	public void setEquipement(String equipement) {
		this.equipement = equipement;
	}

	String tarif;
	String tel;
	String services;
	String codePostal;
	String modePaiement;
	String tarifGratuit;
	String acces;
	String ouverture;
	String mail;
	String site;
	String reseauSociaux;
	String equipement;
	private String videosUrl;

	public String getLattitude() {
		return lattitude;
	}

	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCodepotal() {
		return codepotal;
	}

	public void setCodepotal(String codepotal) {
		this.codepotal = codepotal;
	}

	public String getTarif() {
		return tarif;
	}

	public void setTarif(String tarif) {
		this.tarif = tarif;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String commTel) {
		this.tel = commTel;
	}

	public String getServices() {
		return services;
	}

	public void setServices(String services) {
		this.services = services;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getModePaiement() {
		return modePaiement;
	}

	public void setModePaiement(String modePaiement) {
		this.modePaiement = modePaiement;
	}

	public String getTarifGratuit() {
		return tarifGratuit;
	}

	public void setTarifGratuit(String tarifGratuit) {
		this.tarifGratuit = tarifGratuit;
	}

	public String getAcces() {
		return acces;
	}

	public void setAcces(String acces) {
		this.acces = acces;
	}

	public String getOuverture() {
		return ouverture;
	}

	public void setOuverture(String ouverture) {
		this.ouverture = ouverture;
	}

	public String getCartOsm() {
		
		String iframe = "<iframe width=\"400\" height=\"350\" frameborder=\"0\" scrolling=\"no\"" +
		"marginheight=\"0\" marginwidth=\"0\" src=\"http://cartosm.eu/map?"
				+ "lon=" + getLongitude()
		+ "&lat=" + getLattitude()
		+ "&zoom=14&width=400&height=350&mark=true&nav=true&pan=true&zb=inout&style=default&icon=down\"></iframe>";
		return iframe;
	}

	@Override
	public String toString() {
		return "EvenementDetails [id=" + id + ", nameEvenement="
				+ nameEvenement + ", commune=" + commune + ", lattitude="
				+ lattitude + ", longitude=" + longitude + ", adresse="
				+ adresse + ", codepotal=" + codepotal + ", tarif=" + tarif
				+ ", Tel=" + tel + ", services=" + services
				+ ", codePostal=" + codePostal + ", modePaiement="
				+ modePaiement + ", tarifGratuit=" + tarifGratuit + ", acces="
				+ acces + ", ouverture=" + ouverture + ",VideosUrl=" + videosUrl
				+ ",cartOsm=" + getCartOsm() + "]";
	}

	public EvenementDetail() {
		super();
	}

	public EvenementDetail(String id, String nameEvenement, String commune) {
		super();
		this.id = id;
		this.nameEvenement = nameEvenement;
		this.commune = commune;
	}

	public void setId(String i) {
		id = i;

	}

	public void setNameEvenement(String name) {
		nameEvenement = name;

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

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getReseauSociaux() {
		return reseauSociaux;
	}

	public void setReseauSociaux(String reseauSociaux) {
		this.reseauSociaux = reseauSociaux;
	}

	public String getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(String entreprise) {
		this.entreprise = entreprise;
	}

	public String getVideosUrl() {
		return videosUrl;
	}
	public void setVideoUrl(String videosUrl) {
		this.videosUrl = videosUrl;
	}

	
}

