package fr.mixiteam.wsopensarthedev.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import fr.mixiteam.wsopensarthedev.clientws.ClientwsSourceCulturel;
import fr.mixiteam.wsopensarthedev.clientws.ClientwsSourceEvenementielle;
import fr.mixiteam.wsopensarthedev.modele.Evenement;
import fr.mixiteam.wsopensarthedev.modele.EvenementDetails;
import fr.mixiteam.wsopensarthedev.modele.TypeEvenement;

/**
 * R�cup�re les donn�es des clients ws et merge les r�sultats
 * @author user
 *
 */
public class EvenementCore 
{

	/**
	 * R�cup�re la liste des types d'�v�nements
	 * @return
	 */
	public static List<TypeEvenement> getTypeEvenement()
	{
		Map<String,Integer> typeEvenements = new HashMap<String,Integer>();
		List<TypeEvenement> typeEvenementsFinal = new ArrayList<TypeEvenement>();

		//Appel au ws 1
		List<String> liste1 = ClientwsSourceEvenementielle.getType();
//		liste1.add("Concert");
//		liste1.add("Concert");
//		liste1.add("Opera");
//		liste1.add("Cin�ma");
//		liste1.add("Parachute");
		//Appel au ws 2
		List<String> liste2 = ClientwsSourceCulturel.getType();
//		liste2.add("Opera");
//		liste2.add("Parachute");
//		liste2.add("Concert");

		//Parcours et comptage des categories d'�venement
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
		//requete de recherche des events � tous les ws par type
		List<Evenement> listeEvenementsFinale = new ArrayList<Evenement>();

		//ws1
		List<Evenement> listeWs1 = ClientwsSourceEvenementielle.getListeActivite(type);
//		Evenement e1= new Evenement();
//		e1.setId("1");
//		e1.setNameEvenement("toto");
//		listeWs1.add(e1);
		listeEvenementsFinale.addAll(listeWs1);

		//ws2
		List<Evenement> listeWs2 = ClientwsSourceCulturel.getListeActivite(type);
//		Evenement e2= new Evenement();
//		e2.setId("1");
//		e2.setNameEvenement("toto");
//		listeWs2.add(e2);
//		Evenement e3= new Evenement();
//		e3.setId("1");
//		e3.setNameEvenement("toto");
//		listeWs2.add(e3);
		listeEvenementsFinale.addAll(listeWs2);

		return listeEvenementsFinale;
	}

	/**
	 * retourne le detail d'un evenement.
	 * @param id
	 * @return
	 */
	public static EvenementDetails getEvenement(String id)
	{
		// requete de tous les ws pour recuperer les infos de l'evenement
		EvenementDetails evenement = new EvenementDetails();

		// ws1
		evenement = ClientwsSourceEvenementielle.getEvenementDetail(id);
		// ws2
		if (evenement == null || evenement.getId() == null ) {
			evenement = ClientwsSourceCulturel.getEvenementDetail(id);
		}


		return evenement;
	}

}
