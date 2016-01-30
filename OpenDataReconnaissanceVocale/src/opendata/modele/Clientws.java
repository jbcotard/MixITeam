package opendata.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;


public class Clientws {
	
	private static String URL = "http://localhost:8080/wsopendatasarthedev/rservice";
	private static String URL_CATEGORIES = URL+"/Evenements/TypeEvenements";
	private static String URL_EVENEMENTS = URL+"/Evenements/search/";
	private static String URL_EVENEMENT = URL+"/Evenements/";
	private boolean isStart = false;
	private DefaultHttpClient httpClient;
	
	public Clientws()
	{
		
	}
	
	public void stop()
	{
		if (isStart)
		{
			httpClient.getConnectionManager().shutdown();
			isStart = false;
		}
	}
	
	public List<Evenement> getEvenements(String categorie) throws ClientProtocolException, IOException
	{
		List<Evenement> listeEvenements =new ArrayList<Evenement>();
		httpClient = new DefaultHttpClient();
		isStart = true;
		HttpGet getRequest = new HttpGet(URL_EVENEMENTS+categorie);
		System.out.println(URL_EVENEMENTS+categorie);
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		for (String line = null; (line = reader.readLine()) != null;) {
		    builder.append(line).append("\n");
		}

		JSONArray data = new JSONArray(builder.toString());

		for (int i = 0; i < data.length(); i++) {
			JSONObject item = (JSONObject) data.get(i);
			
			Evenement evenement=new Evenement();
			evenement.setId(String.valueOf(item.get("id")));
			evenement.setCommune(String.valueOf(item.get("commune")));
			evenement.setNameEvenement(String.valueOf(item.get("nameEvenement")));

			listeEvenements.add(evenement);
		}
		
		return listeEvenements;
	}
	
	public EvenementDetail getEvenement(String id) throws ClientProtocolException, IOException
	{
		EvenementDetail evenement =new EvenementDetail();
		httpClient = new DefaultHttpClient();
		isStart = true;
		HttpGet getRequest = new HttpGet(URL_EVENEMENT+id);
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		StringBuilder builder = new StringBuilder();
		for (String line = null; (line = reader.readLine()) != null;) {
		    builder.append(line).append("\n");
		}

		JSONObject item = new JSONObject(builder.toString());
			
			// Evenement evenement=new Evenement();
			evenement.setCommune(String.valueOf(item.get("commune")));
			evenement.setEntreprise(String.valueOf(item.get("entreprise")));
			
			evenement.setNameEvenement(String.valueOf(item.get("nameEvenement")));
			evenement.setLattitude(String.valueOf(item.get("lattitude")));
			evenement.setLongitude(String.valueOf(item.get("longitude")));
							

			evenement.setAdresse(String.valueOf(item.get("adresse")));
		
			evenement.setTarifGratuit(String.valueOf(item.get("tarifGratuit")));
						
			System.out.println(evenement.toString());
		
		return evenement;
	}
	
	public List<String> getCategoriesEvenements() throws ClientProtocolException, IOException
	{
		List<String> listeEvenements =new ArrayList<String>();
		if (isStart) return listeEvenements;
		HttpGet getRequest = new HttpGet(URL_CATEGORIES);
		getRequest.addHeader("accept", "application/json");

		HttpResponse response = httpClient.execute(getRequest);

		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
			   + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		String output;
		while ((output = br.readLine()) != null) {
			listeEvenements.add(output);
		}
		
		return listeEvenements;
	}
}
