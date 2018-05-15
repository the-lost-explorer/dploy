package dploy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dploy {
	
	public static boolean flag;

	public static void main(String[] args) {
		System.out.println("Started");
		// Check if user is opening dploy for the first time.
		try {
			// Try to read the file flag.txt
			String fileName = "flag.txt";
			File textFile = new File(fileName);
			Scanner s = new Scanner(textFile);
			flag = true;
			System.out.println(flag);
			s.close();

		} catch (FileNotFoundException e){
			flag = false;
			System.out.println(flag);
		}

		// Create UI for app
		new DployMiddleware(flag);	
	}

}
