import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZWdecode {
	public static void main (String[] args) throws IOException{
	    //get input
		byte[] fileContent = Files.readAllBytes(Paths.get(args[0]));
	   
	  }
}
