
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
public class readfilename {
 
	public static String[] readfilename(String file) {
 			String [] files = {"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
 			int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{
 
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
				files[i] = sCurrentLine;
				
				//System.out.println(files[i]);
				i++;
			}


 
		} catch (IOException e) {
			e.printStackTrace();
		} 
 		
 		return files;
	}
}
