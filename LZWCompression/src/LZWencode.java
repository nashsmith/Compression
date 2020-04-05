import java.io.*;
import java.util.*;

public class LZWencode {

  public static void main (String[] args) throws IOException{
    //initialize output file
		FileOutputStream out = new FileOutputStream("output");
    //get input
    File file = new File(args[0]);
		Scanner input = new Scanner(file);

    //setup Trie phrase dictionary
    Trie phraseList = new Trie(true);
    int currentByte = 0;
    Integer[] outputValues = new Integer[512];
    int phraseKeyCount = 257;
    Byte[] buffer = new Byte[1];
    // InputStream inputstream = new FileInputStream(args[0]);
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream(args[0]));

    //check if the next input is in the phraseList
    // while(currentByte < fileContent.length){
    //   Byte[] ar = {fileContent[currentByte]};
    //   System.out.println(findLongestPhrase(ar, null, null).getPhraseKey());
    // }
    //YES
    //find the phrase number of the inputs
    //check if the next input is in the Trie ^
    //NO
    //add the next input into the Trie
    //update the bits needed to encode phrase number (Ceiling of Log2 P or 2^? = P)
    //output the phrase number of the first input

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

    //find the first input in the trie
    // TrieNode currentTrieNode = phraseList._root;
    //
    // LeafNode bytesLeaf = currentTrieNode.findLeaf(currentInput);
    //
    // currentTrieNode = bytesLeaf.getNextTrieNode();
    //
    // int currentPhraseKey = currentTrieNode.getPhraseKey();
    //
    // System.out.println(currentPhraseKey);


  }




}
