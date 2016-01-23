package fr.mixiteam.wsopensarthedev.modele;

public class TypeEvenement {

	private String text;
	private Integer weight;

	public String getText() {
		return text;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setText(String categorie) {
		text= categorie;
	}

	public void setWeight(Integer compteur) {
		weight = compteur;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TypeEvenement [text=");
		builder.append(text);
		builder.append(", weight=");
		builder.append(weight);
		builder.append("]");
		return builder.toString();
	}

}
