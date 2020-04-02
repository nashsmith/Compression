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

    Byte currentInput = new Byte(input.nextByte());

    //find the first input in the trie
    TrieNode currentTrieNode = phraseList._root;

    LeafNode bytesLeaf = currentTrieNode.findLeaf(currentInput);

    currentTrieNode = bytesLeaf.getNextTrieNode();

    int currentPhraseKey = currentTrieNode.getPhraseKey();

    System.out.println(currentPhraseKey);









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
