package currency;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

public class TestCases {

	@Test
	public void testLiveRequest() {
		final String API_KEY = Readfile.readfile();
		final String URL = "http://apilayer.net/api/";
		// requests hourly updated exchange rate
		final String LIVE = "live";

		// builds request to API
		HttpGet get = new HttpGet(URL + LIVE + "?access_key=" + API_KEY 
				+ "&currencies=" + "CAD");

		// makes requests to the API
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			JSONObject rate = new JSONObject(EntityUtils.toString(entity));
			if(!rate.getBoolean("success")) {
				ErrorCodeHandler.errorHandler(rate);
			}
			else {
				assertTrue(rate.getBoolean("success"));
			}
		} catch (ClientProtocolException e) {
			System.out.println("An error occured with the API response, please try again.");
		} catch (JSONException e) {
			System.out.println("Error reading API response, please try again.");
		} catch (ParseException e) {
			System.out.println("Unexpected error while parsing date, please try again."); 
		} catch (IOException e) {
			System.out.println("An error occured with the API response, please try again.");
		}
	}

	@Test
	public void testHistRequest() {
		final String API_KEY = Readfile.readfile();
		final String URL = "http://apilayer.net/api/";
		// requests hourly updated exchange rate
		final String LIVE = "historical";

		// builds request to API
		HttpGet get = new HttpGet(URL + LIVE + "?access_key=" + API_KEY 
				+ "&currencies=" + "CAD" + "&date=" + "2005-02-01");

		// makes requests to the API
		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			JSONObject rate = new JSONObject(EntityUtils.toString(entity));
			if(!rate.getBoolean("success")) {
				ErrorCodeHandler.errorHandler(rate);
			}
			else {
				assertTrue(rate.getBoolean("historical"));
				assertTrue(rate.getBoolean("success"));
			}
		} catch (ClientProtocolException e) {
			System.out.println("An error occured with the API response, please try again.");
		} catch (JSONException e) {
			System.out.println("Error reading API response, please try again.");
		} catch (ParseException e) {
			System.out.println("Unexpected error while parsing date, please try again."); 
		} catch (IOException e) {
			System.out.println("An error occured with the API response, please try again.");
		}
	}
}
