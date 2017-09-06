package currency;

import java.util.Scanner;

/**
 * @author Jeff Hsu
 */
public final class Converter {

	private Converter() {
	}

	public static void main(String[] args) {
		
		/* user inputs amount to convert as well as
		 * which currencies to convert to
		 */
		Scanner input = new Scanner(System.in);

		// conversion mode
		System.out.println("Select mode [live/historical]: ");
		String endpoint = input.next();

		// populate string array with target currencies
		System.out.println("Enter target currencies in their respective 3 letter forms,"
				+ " separated by single spaces: ");
		input.nextLine();
		String[] targetCurrencies = input.nextLine().toUpperCase().split(" ");
		// removes duplicates
		targetCurrencies = CurrencyFormat.unique(targetCurrencies);


		// conversion amount
		System.out.println("Enter amount to convert: ");
		double amount = input.nextDouble();

		// call desired API request
		if("live".equals(endpoint)) {
			RequestLive.requestLive(targetCurrencies, amount);
		}
		else if("historical".equals(endpoint)) {
			System.out.println("Enter date in yyyy-mm-dd format, " 
					+ "separating each with single spaces: ");
			// pads with a leading 0 in event of single digit
			String year = String.valueOf(input.nextInt());
			String month = String.format("%02d", input.nextInt());
			String day = String.format("%02d", input.nextInt());
			String date = RequestHistory.verifyDate(year, month, day);
			// checks if date is valid without having to ask API, saves a request
			if(!date.isEmpty()) {
				RequestHistory.requestHistory(targetCurrencies, amount, date);
			}
		}

		input.close();
	}

}


