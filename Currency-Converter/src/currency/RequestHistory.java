package currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.http.client.methods.HttpGet;

/**
 * @author Jeff Hsu
 */
public final class RequestHistory {

	private RequestHistory() {
	}

	/**
	 * @param date
	 * @return String containing properly formatted date
	 * @throws DateTimeParseException if date is outside range from today to January 1st of 18 years ago
	 */
	public static String verifyDate(String date) throws DateTimeParseException{
		try {
			LocalDate dateCheck = LocalDate.parse(date);
			// checks if date is outside range from today to 16 years before tomorrow
			if(dateCheck.isBefore(LocalDate.now().plusDays(1)) && dateCheck.isAfter(LocalDate.of(LocalDate.now().getYear()-19, 12, 31))) {
				return dateCheck.format(DateTimeFormatter.ISO_LOCAL_DATE);
			}
		}
		catch(DateTimeParseException e) {
			return "";
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

		RequestOutput.request(get, targetCurrency, amount);
	}
}
