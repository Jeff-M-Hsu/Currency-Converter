package currency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class CurrencyFormat {
	public static String combine(String[] currencies) {
		String result = "";
		for(int i = 0; i < currencies.length; i++) {
			result = result + currencies[i] + ",";
		}
		return result.substring(0, result.length()-1);
	}
	public static String[] unique(String[] currencies) {
		Set<String> temp = new HashSet<String>(Arrays.asList(currencies));
		ArrayList<String> list = new ArrayList<String>(temp);
		Collections.reverse(list);
		return list.toArray(new String[list.size()]);
	}
}
