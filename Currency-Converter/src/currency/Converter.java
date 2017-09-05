package currency;

import java.util.Scanner;

/**
 * @author Jeff Hsu
 */
public class Converter {

	// requests exchange rate from specific day in YYYY-MM-DD format
	public static final String HIST = "historical";
		
	
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
		System.out.println("\n");
		if("live".equals(endpoint))
			RequestLive.requestLive(targetCurrencies, amount);
		
		input.close();
	}

}


