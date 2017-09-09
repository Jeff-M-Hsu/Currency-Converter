package currency;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONObject;

/**
 * @author Jeff Hsu
 */
public final class RequestOutput {

	private RequestOutput() {
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
					+ targetCurrency[i] + " (1:" + exchangeRate + ") = "
					+ "%.2f" + " (Date: " + formattedDate + ")",amount,amount*exchangeRate);
			System.out.println("\n");
		}
	}

}
