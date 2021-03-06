package currency;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public final class Readfile {
	
	private Readfile() {
	}
	
	public static String readfile(){
		try {
			BufferedReader reader = new BufferedReader(new FileReader("key.txt"));
			String key = reader.readLine();
			reader.close();
			return key;
		} catch(FileNotFoundException e) {
			System.out.println("API key file not found");
		} catch (IOException e) {
			System.out.println("Could not read file");
		}
		return "";
	}
}
