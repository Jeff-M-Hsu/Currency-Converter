package currency;

import org.apache.http.client.methods.HttpGet;

/**
 * @author Jeff Hsu
 */
public final class RequestLive {

	private RequestLive() {
	}

	/**
	 * @param targetCurrency
	 * @param amount
	 * requests currency exchange, rates updated every hour
	 */
	public static void requestLive(String[] targetCurrency, double amount) {

		/** constants used to build URL */
		final String API_KEY = Readfile.readfile();
		final String URL = "http://apilayer.net/api/";
		// requests hourly updated exchange rate
		final String LIVE = "live";

		// builds request to API
		HttpGet get = new HttpGet(URL + LIVE + "?access_key=" + API_KEY + "&currencies=" 
				+ CurrencyFormat.combine(targetCurrency));

		RequestOutput.request(get, targetCurrency, amount);
	}
}
