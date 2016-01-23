package fr.mixiteam.wsopensarthedev.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.mixiteam.wsopensarthedev.core.EvenementCore;
import fr.mixiteam.wsopensarthedev.modele.Evenement;
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
	public Evenement getEvenement(@PathParam("id") String id) 
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
}
