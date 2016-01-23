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

public class ClientwsSourceCulturel {

	private static String URL_CULTUREL = "http://wcf.tourinsoft.com/Syndication/3.0/cdt72/969e24f9-75a2-4cc6-a46c-db1f6ebbfe97/Objects";
		
			
	public static List<String> getType() {

		String res = "";
		ArrayList<String> listeTypes = new ArrayList<String>();
		GetMethod method = null;
		
		try {

			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			method = new GetMethod(URL_CULTUREL);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=Type&$orderby=Type"));

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
				String type = String.valueOf(item.get("Type"));

				
				if (!type.equals("null")) {
					//Separation des types contenant une liste separée par des ##
					if(type.contains("##")){						
						String[] typePrecis=type.split("##");
						for(int j=0;j<typePrecis.length;j++)
						{
							listeTypes.add(typePrecis[j]);
						}
					}else{
					listeTypes.add(type);
					}
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
			method = new GetMethod(URL_CULTUREL);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=NomOffre,Commune,SyndicObjectID&$filter=indexof(Type,'"+type+"') gt -1 "));

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

	public static EvenementDetails getEvenementDetail(String id) {

		String res = "";
		EvenementDetails evenement = new EvenementDetails();
		GetMethod method = null;
		
		try {

			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			method = new GetMethod(URL_CULTUREL);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

		// &$select=SyndicObjectName,GmapLatitude,GmapLongitude,CommMail,Commune,Tarifs,Equipements,Adresse2,CommWeb,NomOffre,plateformeURL,Type,Cedex,Adresse1,Adresse3,Services,CommTel,CodePostal,ModePaiement,Adresse1Suite,TarifGratuit,AccesOuvertureGranule

			
			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=SyndicObjectID,SyndicObjectName,GmapLatitude,GmapLongitude,CommMail,Commune,Tarifs,"
					+ "Equipements,Adresse2,CommWeb,NomOffre,plateformeURL,Type,Cedex,Adresse1,Adresse3,Services,CommTel,CodePostal,ModePaiement,"
					+ " Adresse1Suite,TarifGratuit,Acces,OuvertureGranule&$filter=SyndicObjectID eq '"+id+"'"));

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
				
				// Evenement evenement=new Evenement();
				evenement.setId(String.valueOf(item.get("SyndicObjectID")));
				evenement.setCommune(String.valueOf(item.get("Commune")));
				evenement.setEntreprise(String.valueOf(item.get("SyndicObjectName")));
				
				evenement.setNameEvenement(String.valueOf(item.get("NomOffre")));
				evenement.setLattitude(String.valueOf(item.get("GmapLatitude")));
				evenement.setLongitude(String.valueOf(item.get("GmapLongitude")));
		
				evenement.setMail(String.valueOf(item.get("CommMail")));
				evenement.setCommune(String.valueOf(item.get("Commune")));
				evenement.setTarif(String.valueOf(item.get("Tarifs")));
				evenement.setEquipement(String.valueOf(item
						.get("Equipements")));
				
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
				
				evenement.setSite(String.valueOf(item.get("CommWeb")));
				
				evenement.setReseauSociaux(String.valueOf(item
						.get("plateformeURL")));
				// evenement.setT(String.valueOf(item.get("Type")));
				evenement
						.setServices(String.valueOf(item.get("Services")));
				evenement.setTel(String.valueOf(item.get("CommTel")));
				evenement.setCodePostal(String.valueOf(item
						.get("CodePostal")));
				evenement.setModePaiement(String.valueOf(item
						.get("ModePaiement")));
				evenement.setTarifGratuit(String.valueOf(item
						.get("TarifGratuit")));
				evenement.setAcces(String.valueOf(item.get("Acces")));
				evenement.setOuverture(String.valueOf(item
						.get("OuvertureGranule")));
				
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
