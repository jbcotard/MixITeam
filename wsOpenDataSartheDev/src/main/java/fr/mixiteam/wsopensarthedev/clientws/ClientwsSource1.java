package fr.mixiteam.wsopensarthedev.clientws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.URIUtil;

public class ClientwsSource1 {

	private static String URL_EVENEMENTIELLE = "http://wcf.tourinsoft.com/Syndication/3.0/cdt72/e9a8e2bf-c933-4831-9ebb-87eec559a21a/Objects";

	@GET
	@Path("test")
	public String test() {
		return getType();
	}

	public String getType() {

		String res = "";

		try {

			// Create an instance of HttpClient.
			HttpClient client = new HttpClient();

			// Create a method instance.
			GetMethod method = new GetMethod(URL_EVENEMENTIELLE);

			// Provide custom retry handler is necessary
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
					new DefaultHttpMethodRetryHandler(3, false));

			method.setQueryString(URIUtil.encodeQuery("$format=json&$select=Categorie"));

			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// System.out.println(new String(responseBody));
			res = new String(responseBody);

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
