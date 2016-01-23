package fr.mixiteam.wsopensarthedev.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.mixiteam.wsopensarthedev.modele.Evenement;


@Path("/Evenementservice/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EvenementService {
	Map<String, Evenement> Evenements = new HashMap<String, Evenement>();

	public void init() {

		Evenement newEvenement1 = new Evenement();
		newEvenement1.setId(1);
		newEvenement1.setName("Alvin Reyes");

		Evenement newEvenement2 = new Evenement();
		newEvenement2.setId(2);
		newEvenement2.setName("Rachelle Ann de Guzman Reyes");

		Evenements.put("1", newEvenement1);
		Evenements.put("2", newEvenement2);

	}

	public EvenementService() {
		init();
	}

	@POST
	@Path("/Evenements/{id}/")
	public Evenement getEvenement(@PathParam("id") String id) {
		Evenement c = Evenements.get(id);
		return c;
	}

	@POST
	@Path("/Evenements/getall")
	public List getAllEvenements(Evenement Evenement) {
		List EvenementList = new ArrayList();
		for (int i = 0; i <= Evenements.size(); i++) {
			EvenementList.add((Evenement) Evenements.get(i));
		}
		return EvenementList;
	}

}
