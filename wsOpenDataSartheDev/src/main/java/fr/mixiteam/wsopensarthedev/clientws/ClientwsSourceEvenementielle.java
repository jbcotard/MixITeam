package fr.mixiteam.wsopensarthedev.clientws;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import fr.mixiteam.wsopensarthedev.modele.Evenement;
import fr.mixiteam.wsopensarthedev.modele.EvenementDetails;

public class ClientwsSourceEvenementielle {

	private static String URL_EVENEMENTIELLE = "http://wcf.tourinsoft.com/Syndication/3.0/cdt72/e9a8e2bf-c933-4831-9ebb-87eec559a21a/Objects";

	

	public static List<String> getType() {

		String res = "";
		ArrayList<String> listeTypes = new ArrayList<String>();
		GetMethod method = null;
		
		try {

			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			method = new GetMethod(URL_EVENEMENTIELLE);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=Categorie&$orderby=Categorie/ThesLibelle"));

			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// System.out.println(new String(responseBody));
			res = new String(responseBody);

			JSONObject jsonObject = new JSONObject(res);

			JSONArray data = (JSONArray) jsonObject.get("value");

			for (int i = 0; i < data.length(); i++) {
				JSONObject item = (JSONObject) data.get(i);
				JSONObject categorie = (JSONObject) item.get("Categorie");

				String thesLibelle = String.valueOf(categorie.get("ThesLibelle"));
				if (!thesLibelle.equals("null")) {
					listeTypes.add(thesLibelle);
					System.out.println(thesLibelle);
				}
			}
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			// Release the connection.
			 method.releaseConnection();
		}

		return listeTypes;
	}


	public static List<Evenement> getListeActivite(String type) {

		String res = "";
		ArrayList<Evenement> listeEvenement = new ArrayList<Evenement>();
		GetMethod method = null;
		
		try {

			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			method = new GetMethod(URL_EVENEMENTIELLE);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=NomOffre,Commune,SyndicStructureId&$filter=indexof(Categorie/ThesLibelle,'"+type+"') gt -1 "));

			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// System.out.println(new String(responseBody));
			res = new String(responseBody);

			System.out.println(res);
			
			JSONObject jsonObject = new JSONObject(res);

			JSONArray data = (JSONArray) jsonObject.get("value");

			for (int i = 0; i < data.length(); i++) {
				JSONObject item = (JSONObject) data.get(i);
				
				Evenement evenement=new Evenement();
				evenement.setId(String.valueOf(item.get("SyndicObjectID")));
				evenement.setCommune(String.valueOf(item.get("Commune")));
				evenement.setNameEvenement(String.valueOf(item.get("NomOffre")));

				listeEvenement.add(evenement);
			}
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			// Release the connection.
			 method.releaseConnection();
		}

		return listeEvenement;
	}
	
	
	public String getTest() {
		String res = "";

		try {

			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			GetMethod method = new GetMethod(URL_EVENEMENTIELLE);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil
					.encodeQuery("$format=json&$select=NomOffre&$filter=(TarifGratuit eq null or TarifGratuit eq false) and indexof(Commune ,'Mans') gt -1"));

			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// System.out.println(new String(responseBody));
			res = new String(responseBody);

			//
			// JSONObject jsonObject = new JSONObject(res);
			//
			// JSONObject data = (JSONObject) jsonObject.get("data");
			// final String nomOffre = (String) data.get("Categorie");
			//

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} finally {
			// Release the connection.
			// method.releaseConnection();
		}

		return res;
	}

	public static EvenementDetails getEvenementDetail(String id) {

		String res = "";
		EvenementDetails evenement = new EvenementDetails();
		GetMethod method = null;
		
		try {	
			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			method = new GetMethod(URL_EVENEMENTIELLE);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=SyndicObjectID,SyndicObjectName,GmapLatitude,GmapLongitude,Commune,Adresse1,Adresse2,CodePostal,Adresse3,Adresse1Suite,Cedex,NomOffre,Categorie,TarifGratuit"
					+ "&$filter=SyndicObjectID eq '"+id+"'"));

			System.out.println("URI TEST ->"+method.getURI());
			
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// System.out.println(new String(responseBody));
			res = new String(responseBody);

			JSONObject jsonObject = new JSONObject(res);

			JSONArray data = (JSONArray) jsonObject.get("value");

			for (int i = 0; i < data.length(); i++) {
				JSONObject item = (JSONObject) data.get(i);
				
				// Evenement evenement=new Evenement();
				evenement.setId(String.valueOf(item.get("SyndicObjectID")));
				evenement.setCommune(String.valueOf(item.get("Commune")));
				evenement.setEntreprise(String.valueOf(item.get("SyndicObjectName")));
				
				evenement.setNameEvenement(String.valueOf(item.get("NomOffre")));
				evenement.setLattitude(String.valueOf(item.get("GmapLatitude")));
				evenement.setLongitude(String.valueOf(item.get("GmapLongitude")));
								
				String adresse = "";
				
				if (!String.valueOf(item.get("Adresse1")).equals("null")) {
					adresse = String.valueOf(item.get("Adresse1"));
				}
				
				if (!String.valueOf(item.get("Adresse2")).equals("null")) {
					adresse += " " + String.valueOf(item.get("Adresse2"));
				}
				
				if (!String.valueOf(item.get("Adresse3")).equals("null")) {
					adresse += " "+ String.valueOf(item.get("Adresse3"));
				}

				if (!String.valueOf(item.get("Adresse1Suite")).equals("null")) {
					adresse += " " + String.valueOf(item.get("Adresse1Suite"));
				}

				
				evenement.setAdresse(adresse);
			
				evenement.setCodePostal(String.valueOf(item.get("CodePostal")));
				evenement.setTarifGratuit(String.valueOf(item.get("TarifGratuit")));
							
				System.out.println(evenement.toString());
			}
		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			// Release the connection.
			 method.releaseConnection();
		}

		return evenement;	
	}	
	
}
