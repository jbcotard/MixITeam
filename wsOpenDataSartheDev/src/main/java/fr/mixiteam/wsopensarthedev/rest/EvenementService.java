package fr.mixiteam.wsopensarthedev.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import fr.mixiteam.wsopensarthedev.core.EvenementCore;
import fr.mixiteam.wsopensarthedev.modele.Evenement;
import fr.mixiteam.wsopensarthedev.modele.EvenementDetails;
import fr.mixiteam.wsopensarthedev.modele.TypeEvenement;


@Path("/Evenements/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EvenementService {
	Map<String, Evenement> evenements = new HashMap<String, Evenement>();

	public void init() 
	{
	}

	public EvenementService() 
	{
		init();
	}

	@GET
	@Path("/{id}/")
	public EvenementDetails getEvenement(@PathParam("id") String id)
	{
		return EvenementCore.getEvenement(id);
	}
	
	@GET
	@Path("/typeEvenements")
	public List<TypeEvenement> getTypeEvenements() 
	{		
		return EvenementCore.getTypeEvenement();
	}
	
	@GET
	@Path("/search/{type}")
	public List<Evenement> searchEvenements(@PathParam("type") String type) 
	{
		return EvenementCore.searchEvenements(type);
	}

	@GET
	@Path("/note/{id}/{note}")
	public void saveNote(@PathParam("id") String id, @PathParam("note") int note) {
		EvenementCore.saveNote(id,note);
	}
}
