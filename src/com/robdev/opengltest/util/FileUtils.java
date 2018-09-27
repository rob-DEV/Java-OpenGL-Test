package com.robdev.opengltest.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtils {

	private FileUtils() {
		
	}
	
	public static String loadAsString(String filePath) {
		String result = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String buffer = "";
			while((buffer = reader.readLine()) != null) {
				result += buffer + "\n";
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
