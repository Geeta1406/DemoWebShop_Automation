package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvUtil {
	
	public static List<String[]> readCSV(String filePath)
	{
		
		List<String[]> data = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            String line;
            br.readLine(); // skip header

            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }	
}
