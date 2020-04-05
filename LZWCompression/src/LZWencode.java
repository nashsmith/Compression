import java.io.*;
import java.util.*;

public class LZWencode {

  public static void main (String[] args) throws IOException{
    //initialize output file
		FileOutputStream out = new FileOutputStream("output");
    //get input
    File file = new File(args[0]);

    //setup Trie phrase dictionary
    Trie phraseList = new Trie(true);
    int phraseKeyCount = 257;
    // InputStream inputstream = new FileInputStream(args[0]);
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream(args[0]));

    Byte currentInput;
    int phraseKey = 0;
    int temp;
    while((temp = inputstream.read()) != -1){
      currentInput = new Byte((byte)(temp - 128));
      //find the first input in the trie
      TrieNode currentTrieNode = phraseList._root;

      //while the current byte is in the current trienodes list of values
      while(currentTrieNode.contains(currentInput)){
        //if the current bytes leaf node has a nexttrienode then the pattern exists
        if(currentTrieNode.findValue(currentInput).getNextTrieNode() != null){
          currentTrieNode = currentTrieNode.findValue(currentInput).getNextTrieNode();
          phraseKey = currentTrieNode.getPhraseKey();
          inputstream.mark(2);
          currentInput = new Byte((byte)inputstream.read());
        //if the nexttrienode is not initialized then the pattern doesnt exists
        //add the next trienode with currentPhraseKey
        //break
        }else{
          currentTrieNode.findValue(currentInput)._trieNode = new TrieNode(currentInput, phraseKeyCount);
          phraseKeyCount++;
          break;
        }
      }
      //currentTrieNode is the nextTrieNode of the last byte of the longest pattern
      //output the phraseKey
      System.out.println(phraseKey);
      String s = phraseKey + "\n";
      byte[] o = s.getBytes();
      out.write(o);
      //reset inputstream
      inputstream.reset();

    }

  }
}
