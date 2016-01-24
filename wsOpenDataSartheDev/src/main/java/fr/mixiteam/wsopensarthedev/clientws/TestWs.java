package fr.mixiteam.wsopensarthedev.clientws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import fr.mixiteam.wsopensarthedev.hsql.Notes;

public class TestWs {
	
	@GET
	@Path("testEV")
	public String test() {
		return ClientwsSourceEvenementielle.getEvenementDetail("FMA072878000016").toString();
	}

	@GET
	@Path("testCu")
	public String testCU() {
		return ClientwsSourceCulturel.getEvenementDetail("ASC1081000051x").toString();
	}	
	
	
	@GET
	@Path("addNote")
	public void addNote() {
		Notes notes = new Notes();
		notes.setNote("ID", 55);
		// return ClientwsSourceCulturel.getEvenementDetail("ASC1081000051x").toString();
	}
	
	@GET
	@Path("getNote")
	public String getNote() {
		Notes notes = new Notes();
		int i = notes.getNote("ID");
		
		return Integer.toString(i);
		// return ClientwsSourceCulturel.getEvenementDetail("ASC1081000051x").toString();
	}
}
