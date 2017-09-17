package currency;

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

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jeff Hsu
 */
public final class RequestOutput {
	
	private RequestOutput() {
	}

	/**
	 * @param get
	 * @param targetCurrency
	 * @param amount
	 * makes requests to the API
	 */
	public static void request(HttpGet get, String[] targetCurrency, Double amount) {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		try {
			CloseableHttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();

			// converts response to java object
			JSONObject rate = new JSONObject(EntityUtils.toString(entity));
			if(!rate.getBoolean("success")) {
				ErrorCodeHandler.errorHandler(rate);
			}
			else {
				print(rate, targetCurrency, amount);
				response.close();
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

	/**
	 * @param rate
	 * @param targetCurrency
	 * @param amount
	 * takes JSON response and user input and outputs it in a formatted way
	 */
	public static void print(JSONObject rate, String[] targetCurrency, double amount) {
		// parsed objects accessed by date
		Date timeStamp = new Date((long)(rate.getLong("timestamp")*1000));
		String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(timeStamp);
		String source = rate.getString("source");

		// forms target currency quotes for parsing JSONObject
		String[] quotes = new String[targetCurrency.length];

		// prints conversion with timestamps
		System.out.println("\n");
		for(int i = 0; i < targetCurrency.length; i++) {
			quotes[i] = source.concat(targetCurrency[i].toUpperCase());
			double exchangeRate = rate.getJSONObject("quotes").getDouble(quotes[i]);
			System.out.printf("%.2f" + " " + source + " in " 
					+ targetCurrency[i] + " = " + "%.2f\n",amount,amount*exchangeRate);
			System.out.println("1 --> " + exchangeRate + "\n" + "Date: " + formattedDate + "\n");

		}
	}

	

}
