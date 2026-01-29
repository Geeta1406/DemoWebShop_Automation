package utils;

import java.io.FileWriter;

public class ReportLogger {
	
	
	public static void log(String message) {
		try(FileWriter  fw=new FileWriter("report.txt",true))
		{
			fw.write(message +"\n");
	
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
