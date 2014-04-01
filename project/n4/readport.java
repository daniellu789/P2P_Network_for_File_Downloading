
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
public class readport {
 
	public static int[] readport() {
 			int [] ports = {0,0};
 			int i = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("file.txt")))
		{
 
			String sCurrentLine;
 
			while ((sCurrentLine = br.readLine()) != null) {
				ports[i] = Integer.parseInt(sCurrentLine);
				
				//System.out.println(ports[i]);
				i++;
			}
 
		} catch (IOException e) {
			e.printStackTrace();
		} 
 		
 		return ports;
	}
}
