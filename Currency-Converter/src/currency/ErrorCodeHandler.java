package currency;

import org.json.JSONObject;


/**
 * @author Jeff Hsu
 */
public class ErrorCodeHandler {
	
	public static void errorHandler(JSONObject rate){
		int errorCode = rate.getJSONObject("error").getInt("code");
		switch(errorCode) {
		
		case 404: System.out.println("Requested resource does not exist");
		break;
		
		case 101: System.out.println("Invalid access key");
		break;
		
		case 103:  System.out.println("Requested API function does not exist");
		break;
		
		case 104:  System.out.println("Monthly request allowance exceeded");
		break;
		
		case 105:  System.out.println("Current subscription plan does not allow this function");
		break;
		
		case 201:  System.out.println("Invalid source currency");
		break;
		
		case 202:  System.out.println("One or more currency codes are invalid");
		break;
		
		case 301:  System.out.println("Date unspecified");
		break;
		
		case 302:  System.out.println("Invalid date");
		break;
		
		}
		System.exit(1);
	}

	
}
