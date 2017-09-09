package currency;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Jeff Hsu
 */
public final class Converter {

	private Converter() {
	}

	public static void main(String[] args) {

		// variable declarations
		Scanner input = new Scanner(System.in);

		// conversion mode
		System.out.println("Select mode [live/historical]: ");
		String endpoint = input.next();
		// checks if user has entered a valid endpoint
		if(!"historical".equals(endpoint) && !"live".equals(endpoint)){
			System.out.println("Invalid Mode");
		}

		else {
			// populate string array with target currencies
			System.out.println("Enter target currencies in their respective 3 letter forms,"
					+ " separated by single spaces: ");
			input.nextLine();
			String[] targetCurrencies = input.nextLine().toUpperCase().split(" ");
			// removes duplicates
			targetCurrencies = CurrencyFormat.unique(targetCurrencies);

			// asks for conversion amount and verifies that it is a double
			System.out.println("Enter amount to convert: ");
			double amount = 0;
			try {
				amount = input.nextDouble();
			} catch(InputMismatchException e) {
				System.out.println("Invalid amount");
			}

			// call desired API request
			if("live".equals(endpoint) && amount > 0) {
				RequestLive.requestLive(targetCurrencies, amount);
			}

			else if("historical".equals(endpoint) && amount > 0) {
				System.out.println("Enter date in yyyy-mm-dd format: ");
				// checks if date is valid without having to bother API
				String date = RequestHistory.verifyDate(input.next());
				if(!date.isEmpty()) {
					RequestHistory.requestHistory(targetCurrencies, amount, date);
				}
				else System.out.println("Invalid date");
			}

			input.close();
		}
	}

}


