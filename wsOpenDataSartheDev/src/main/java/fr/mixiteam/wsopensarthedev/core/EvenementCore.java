package fr.mixiteam.wsopensarthedev.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import fr.mixiteam.wsopensarthedev.modele.Evenement;
import fr.mixiteam.wsopensarthedev.modele.TypeEvenement;

/**
 * Récupère les données des clients ws et merge les résultats
 * @author user
 *
 */
public class EvenementCore 
{

	/**
	 * Récupère la liste des types d'évènements
	 * @return
	 */
	public static List<TypeEvenement> getTypeEvenement()
	{
		Map<String,Integer> typeEvenements = new HashMap<String,Integer>();
		List<TypeEvenement> typeEvenementsFinal = new ArrayList<TypeEvenement>();

		//Appel au ws 1
		List<String> liste1 = new ArrayList<String>();
		liste1.add("Concert");
		liste1.add("Concert");
		liste1.add("Opera");
		liste1.add("Cinéma");
		liste1.add("Parachute");
		//Appel au ws 2
		List<String> liste2 = new ArrayList<String>();
		liste2.add("Opera");
		liste2.add("Parachute");
		liste2.add("Concert");

		//Parcours et comptage des categories d'évenement
		//liste 1
		for (String categorie : liste1) 
		{
			Integer compteur = 0;
			if (typeEvenements.containsKey(categorie)) 
			{
				compteur = typeEvenements.get(categorie);
			}
			typeEvenements.put(categorie, ++compteur);
		}

		//liste 2
		for (String categorie : liste2) 
		{
			Integer compteur = 0;
			if (typeEvenements.containsKey(categorie)) 
			{
				compteur = typeEvenements.get(categorie);
			}
			typeEvenements.put(categorie, ++compteur);
		}

		// transformation map -> liste finale
		typeEvenements.keySet();
		for (String categorie : typeEvenements.keySet()) 
		{
			TypeEvenement typeEvenement = new TypeEvenement();
			typeEvenement.setText(categorie);
			typeEvenement.setWeight(typeEvenements.get(categorie));
			typeEvenementsFinal.add(typeEvenement);
		}

		return typeEvenementsFinal;
	}

	/**
	 * recherche evenements par type.
	 * @param type
	 * @return
	 */
	public static List<Evenement> searchEvenements(String type) 
	{
		//requete de recherche des events à tous les ws par type
		List<Evenement> listeEvenementsFinale = new ArrayList<Evenement>();

		//ws1
		List<Evenement> listeWs1 = new ArrayList<Evenement>();
		Evenement e1= new Evenement();
		e1.setId(1);
		e1.setName("toto");
		listeWs1.add(e1);
		listeEvenementsFinale.addAll(listeWs1);

		//ws2
		List<Evenement> listeWs2 = new ArrayList<Evenement>();
		Evenement e2= new Evenement();
		e2.setId(1);
		e2.setName("toto");
		listeWs2.add(e2);
		Evenement e3= new Evenement();
		e3.setId(1);
		e3.setName("toto");
		listeWs2.add(e3);
		listeEvenementsFinale.addAll(listeWs2);

		return listeEvenementsFinale;
	}

	/**
	 * retourne le detail d'un evenement.
	 * @param id
	 * @return
	 */
	public static Evenement getEvenement(String id) 
	{
		// requete de tous les ws pour recuperer les infos de l'evenement
		Evenement evenement = new Evenement(Integer.parseInt(id), "toto");

		// ws1
		// evenement = search id sur ws1
		// ws2
		// evenement = search id sur ws1


		return evenement;
	}

}
