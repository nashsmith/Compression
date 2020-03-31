import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZWencode {

  public static void main (String[] args) throws IOException{
    //get input
    byte[] temp = Files.readAllBytes(Paths.get(args[0]));
    Byte[] fileContent = new Byte[temp.length];
    for(int i = 0; i < temp.length; i++){
      fileContent[i] = new Byte(Byte.valueOf(temp[i]));
    }
    //setup Trie phrase dictionary
    Trie phraseList = new Trie(true);

    //find longest match
      //find TrieNode



    //find first inputs phrase number
    Byte[] firstInput = {new Byte(fileContent[0])};
    int phrasekey = phraseList.findPhraseKey(firstInput);
    System.out.print(Integer.toString(phrasekey));

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
