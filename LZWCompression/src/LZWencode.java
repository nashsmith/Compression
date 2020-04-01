import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LZWencode {

  static byte[] temp;
  static Byte[] fileContent;
  //setup Trie phrase dictionary
  static Trie phraseList;
  static int currentByte;
  static int outputPhraseKey;

  public static void main (String[] args) throws IOException{
    //get input
    temp = Files.readAllBytes(Paths.get(args[0]));
    fileContent = new Byte[temp.length];
    for(int i = 0; i < temp.length; i++){
      fileContent[i] = new Byte(Byte.valueOf(temp[i]));
    }
    //setup Trie phrase dictionary
    phraseList = new Trie(true);
    currentByte = 0;
    Byte byteTemp;
    Byte[] currentPhrase = {fileContent[currentByte]};
    Byte nextBytePattern;
    Integer[] outputValues = new Integer[512];
    int outputCount = 0;
    int phraseKeyCount = 257;

    while((nextBytePattern = fileContent[currentByte + 1]) != null){
      for(int i = 0; i < currentPhrase.length; i++){
        System.out.print(currentPhrase[i] + " ");
        if(i == currentPhrase.length - 1){
          System.out.print('\n');
        }
      }
      Byte[] nextPhrase = new Byte[currentPhrase.length + 1];
      for(int i = 0; i < currentPhrase.length; i++){
         nextPhrase[i] = currentPhrase[i];
         //System.out.println(nextPhrase[i]);
       }

      nextPhrase[nextPhrase.length - 1] = nextBytePattern;
      if(phraseList.contains(nextPhrase)){
        currentPhrase = nextPhrase;
        currentByte++;
      }else{
        //System.out.println(phraseList.findPhrase(currentPhrase));
        //System.out.println(phraseList.findPhrase(currentPhrase)._values.find(currentPhrase[currentPhrase.length - 1]));
        LeafNode phrase = phraseList.findPhrase(currentPhrase).findValue(currentPhrase[currentPhrase.length - 1]);
        if(phrase == null){
          System.out.println("Did not find phrase");
          return;
        }
        outputValues[outputCount] = phrase.getPhraseKey();
        if(phrase._nextPhrase == null){
          System.out.println("Making nextPhrase TrieNode");
          phrase._nextPhrase = new TrieNode(nextBytePattern, phraseKeyCount);
        }else{
          System.out.println("Inserting into nextPhrase TrieNode");
          phrase._nextPhrase._values.insert(nextBytePattern, phraseKeyCount);
        }
      phraseKeyCount++;
        currentPhrase = new Byte[] {nextBytePattern};
        currentByte++;
      }

      // Byte[] ar = {fileContent[currentByte]};
      // System.out.println(phraseList.findPhraseKey(ar));

    }

    for(int i = 0; i < outputValues.length; i++){
      if(outputValues[i] != null){
        System.out.println(outputValues[i]);
      }else{
        break;
      }
    }

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

    //find first inputs phrase number
    // Byte[] firstInput = {new Byte(fileContent[0])};
    // int phrasekey = phraseList.findPhraseKey(firstInput);
    // System.out.print(Integer.toString(phrasekey));

    //check if the next input is in the Trie
    //YES
    //find the phrase number of the inputs
    //check if the next input is in the Trie ^
    //NO
    //add the next input into the Trie
    //update the bits needed to encode phrase number (Ceiling of Log2 P or 2^? = P)
    //output the phrase number of the first input
  }

  // public static LeafNode findLongestPhrase(Byte[] phrase, TrieNode lastNode, Byte[] lastPhrase){
  //   if(phraseList.findPhrase(phrase) != null){
  //     //outputPhraseKey = phraseList.findPhraseKey({fileContent[currentByte]});
  //     currentByte++;
  //     if(currentByte < fileContent.length){
  //       int phraseLength = phrase.length;
  //       Byte[] nextPhrase = new Byte[phraseLength + 1];
  //       for(int i = 0; i < phraseLength; i++){
  //         nextPhrase[i] = phrase[i];
  //       }
  //       nextPhrase[phraseLength] = fileContent[currentByte];
  //       return findLongestPhrase(nextPhrase, phraseList.findPhrase(phrase), phrase);
  //     }else{
  //       return phraseList.findPhrase(phrase)._values.find(phrase[phrase.length - 1]);
  //     }
  //   }
  //   return lastNode._values.find(phrase[phrase.length - 1]);
  //
  // }

}
