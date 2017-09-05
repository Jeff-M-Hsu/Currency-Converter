package currency;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

class RequestLive {
	
	
	public static void requestLive(String[] targetCurrency, double amount) {
		
		/** constants used to build URL */
		final String API_KEY = Readfile.readfile();
		final String URL = "http://apilayer.net/api/";
		// requests hourly updated exchange rate
		final String LIVE = "live";
		
		// builds request to API
		HttpGet get = new HttpGet(URL + LIVE + "?access_key=" + API_KEY + "&currencies=" + CurrencyFormat.combine(targetCurrency));
		
		// makes requests to the API
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
			try {
				CloseableHttpResponse response = httpClient.execute(get);
				HttpEntity entity = response.getEntity();
				
				// converts response to java object
				JSONObject rate = new JSONObject(EntityUtils.toString(entity));
				if(rate.getBoolean("success") == false) {
					ErrorCodeHandler.errorHandler(rate);
				}
				else {
				// parsed objects accessed by date
				Date timeStamp = new Date((long)(rate.getLong("timestamp")*1000));
				String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a").format(timeStamp);
				String source = rate.getString("source");
				
				// forms target currency quotes for parsing JSONObject
				String[] quotes = new String[targetCurrency.length];
				
				// prints conversion with timestamps
				for(int i = 0; i < targetCurrency.length; i++) {
					quotes[i] = source.concat(targetCurrency[i].toUpperCase());
					double exchangeRate = rate.getJSONObject("quotes").getDouble(quotes[i]);
					System.out.printf("%.2f" + " " + rate.getString("source") + " in " 
									+ targetCurrency[i] + " " + exchangeRate + " = "
									+ "%.2f" + " (Date: " + formattedDate + ")",amount,amount*exchangeRate);
					System.out.println("\n");
				}
				response.close();
				}
				
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
}
