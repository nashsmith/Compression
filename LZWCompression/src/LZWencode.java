import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZWencode {

  public static void main (String[] args) throws IOException{
    //get input
	byte[] fileContent = Files.readAllBytes(Paths.get(args[0]));
    //find first inputs phrase number
    //check if the next input is in the Trie
    //YES
      //find the phrase number of the inputs
      //check if the next input is in the Trie ^
    //NO
      //add the next input into the Trie
      //update the bits needed to encode phrase number (Ceiling of Log2 P or 2^? = P)
      //output the phrase number of the first input
  }
}

	