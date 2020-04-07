import java.io.*;

public class LZWencode {

  public static void main (String[] args) throws IOException{
	  System.out.println("Started encoding");
    //initialize output file
	  FileOutputStream out = new FileOutputStream("encoded");

    //setup Trie phrase dictionary
    Trie phraseList = new Trie(true);
    //keep track of the amount of phrases
    int phraseKeyCount = 256;

    //get input stream from file
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream(args[0]));

    Byte currentInput; //current byte encoding
    int phraseKey = 0; //the phrasekey of the pattern
    int temp; //temp variable for reading in the file
    //while there is data to read
    while((temp = inputstream.read()) != -1){
      //create a byte from that input value
      if(temp < 128){
        currentInput = (byte)temp;
      }else{
        currentInput = (byte)(temp - 256);
      }

      //find the first input in the trie
      TrieNode currentTrieNode = phraseList._root;

      //while the current byte is in the current trienodes list of values
      while(currentTrieNode.contains(currentInput)){
        //if the current bytes leaf node has a nexttrienode then the pattern exists
        if(currentTrieNode.findValue(currentInput).getNextTrieNode() != null){
          //set the currentTrieNode to the next Trie node of the leaf
          currentTrieNode = currentTrieNode.findValue(currentInput).getNextTrieNode();
          //the currentTrieNode now contains the phrasekey
          phraseKey = currentTrieNode.getPhraseKey();
          //set a marker to reset to when loop exists one file read ahead
          inputstream.mark(1);
          //read the next byte
          temp = inputstream.read();
          if(temp < 128){
            currentInput = (byte)temp;
          }else{
            currentInput = (byte)(temp - 256);
          }
        //if the nexttrienode is not initialized then the pattern doesnt exists
        //add the next trienode with currentPhraseKey
        //break
        }else{
          currentTrieNode.findValue(currentInput)._trieNode = new TrieNode(currentInput, phraseKeyCount);
          phraseKeyCount++;
          break;
        }
      }
      //if the phrase isnt in the trie already
      if(!(currentTrieNode.contains(currentInput))){
        //add the pattern to the trie (last value to the second last values next trie)
        currentTrieNode._values.insert(currentInput, phraseKeyCount);
        //increment amount of phrasekeys
        phraseKeyCount++;
      }
      //build string to output to file
      String s = phraseKey + "\n";
      //output the phraseKey
      byte[] o = s.getBytes();
      out.write(o);
      //reset inputstream
      inputstream.reset();
    }
    inputstream.close();
    out.close();
    System.out.println("Finished encoding");
  }
}
