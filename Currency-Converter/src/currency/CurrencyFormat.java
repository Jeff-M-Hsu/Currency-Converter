package currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jeff Hsu
 */
public final class CurrencyFormat {

	private CurrencyFormat() {
	}

	/**
	 * @param currencies
	 * @return string of currencies formatted for the API request
	 */
	public static String combine(String[] currencies) {
		String result = "";
		for(int i = 0; i < currencies.length; i++) {
			result = result + currencies[i] + ",";
		}
		return result.substring(0, result.length()-1);
	}

	/**
	 * @param currencies
	 * @return array of currencies with duplicates removed
	 */
	public static String[] unique(String[] currencies) {
		Set<String> temp = new HashSet<String>(Arrays.asList(currencies));
		ArrayList<String> list = new ArrayList<String>(temp);
		Collections.reverse(list);
		return list.toArray(new String[list.size()]);
	}
}
