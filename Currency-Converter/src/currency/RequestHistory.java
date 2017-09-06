package currency;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

/**
 * @author Jeff Hsu
 */
public final class RequestHistory {

	private RequestHistory() {
	}

	public static String verifyDate(String year, String month, String day) throws DateTimeParseException{
		try {
			String formattedDate = year + "-" + month + "-" + day;
			LocalDate date = LocalDate.parse(formattedDate);
			if(date.isBefore(LocalDate.now()) && date.isAfter(LocalDate.now().minusYears(16))) {
				return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
			}
		}
		catch(DateTimeParseException e) {
			System.out.println("Invalid date");
		}
		return "";
	}

	/**
	 * @param targetCurrency
	 * @param amount
	 */
	public static void requestHistory(String[] targetCurrency, double amount, String date) {

		/** constants used to build URL */
		final String API_KEY = Readfile.readfile();
		final String URL = "http://apilayer.net/api/";
		final String HIST = "historical";

		// builds request to API
		HttpGet get = new HttpGet(URL + HIST + "?access_key=" + API_KEY + "&currencies=" 
				+ CurrencyFormat.combine(targetCurrency) + "&date=" + date);

		// makes requests to the API
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
				// parsed objects accessed by date
				Date timeStamp = new Date((long)(rate.getLong("timestamp")*1000));
				String formattedDate = new SimpleDateFormat("yyyy-MM-dd").format(timeStamp);
				String source = rate.getString("source");

				// forms target currency quotes for parsing JSONObject
				String[] quotes = new String[targetCurrency.length];

				// prints conversion with timestamps
				for(int i = 0; i < targetCurrency.length; i++) {
					quotes[i] = source.concat(targetCurrency[i].toUpperCase());
					double exchangeRate = rate.getJSONObject("quotes").getDouble(quotes[i]);
					System.out.printf("%.2f" + " " + source + " in " 
							+ targetCurrency[i] + " " + exchangeRate + " = "
							+ "%.2f" + " (Date: " + formattedDate + ")",amount,amount*exchangeRate);
					System.out.println("\n");
				}
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
}
