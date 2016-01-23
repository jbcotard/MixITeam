package fr.mixiteam.wsopensarthedev.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.mixiteam.wsopensarthedev.modele.Evenement;


@Path("/Evenement/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EvenementService {
	Map<String, Evenement> evenements = new HashMap<String, Evenement>();

	public void init() {

		

	}

	public EvenementService() {
		init();
	}

	@POST
	@Path("/{id}/")
	public Evenement getEvenement(@PathParam("id") String id) {
		Evenement c = evenements.get(id);
		return c;
	}

	@POST
	@Path("/getall")
	public List getAllEvenements(Evenement Evenement) {
		List EvenementList = new ArrayList();
		for (int i = 0; i <= evenements.size(); i++) {
			EvenementList.add((Evenement) evenements.get(i));
		}
		return EvenementList;
	}
	
	@GET
	@Path("/all")
	public List<Evenement> getAllEvenements() {
		List<Evenement> EvenementList = new ArrayList<Evenement>();
		for (int i = 0; i <= evenements.size(); i++) {
			EvenementList.add((Evenement) evenements.get(i));
		}
		return EvenementList;
	}
}
