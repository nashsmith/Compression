import java.io.*;

public class LZWencode {

  public static void main (String[] args) throws IOException{
	System.out.println("Started encoding");
    //initialize output file
	FileOutputStream out = new FileOutputStream("encoded");
		

    //setup Trie phrase dictionary
    Trie phraseList = new Trie(true);
    int phraseKeyCount = 256;
    
    //get input
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream(args[0]));

    Byte currentInput;
    int phraseKey = 0;
    int temp;
    while((temp = inputstream.read()) != -1){
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
          currentTrieNode = currentTrieNode.findValue(currentInput).getNextTrieNode();
          phraseKey = currentTrieNode.getPhraseKey();
          inputstream.mark(1);
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
    if(!(currentTrieNode.contains(currentInput))){
      currentTrieNode._values.insert(currentInput, phraseKeyCount);
      phraseKeyCount++;
    }
      //currentTrieNode is the nextTrieNode of the last byte of the longest pattern
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
