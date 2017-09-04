package currency;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Date;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
public class Converter {

	// requests exchange rate from specific day in YYYY-MM-DD format
	public static final String HIST = "historical";
		
	
	public static void main(String[] args) {
		/* user inputs amount to convert as well as
		 * which currencies to convert to
		 */
		Scanner input = new Scanner(System.in);
		System.out.println("Select mode [live/historical]: ");
		String endpoint = input.next();
		System.out.println("Enter target currencies in their respective 3 letter forms,"
							+ " separated by single spaces: ");
		input.nextLine();
		String[] targetCurrencies = input.nextLine().split(" ");
		System.out.println("Enter amount to convert: ");
		System.out.println("\n");
		double amount = input.nextDouble();
		if(endpoint.equals("live"))
			RequestLive.requestLive(targetCurrencies, amount);
	}

}


