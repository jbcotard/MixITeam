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

}
