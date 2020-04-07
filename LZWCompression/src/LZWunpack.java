import java.io.*;
import java.util.*;

public class LZWunpack {
  //take in 32 bits, largest bit size for the file

  static int buffer;
  static int currentInput;

  public static void main(String[] args) throws IOException{

    //initialize output file
		FileOutputStream out = new FileOutputStream("unpacked.txt");
    //get input
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream(args[0]));

    LinkedList<Integer> seenKeys = new LinkedList<Integer>();

    for(int i = 0; i < 255; i++){
      seenKeys.add(i);
    }

    int phraseNumber = 256;
    int offset = 0;

    //System.out.println(log2(largestKeyValue));
    int bitsPerKey = updateBitsPerKey(phraseNumber);
    System.out.println(bitsPerKey);


    currentInput = 0;
    buffer = 0;

    while((buffer = inputstream.read()) != -1){

      System.out.println("bitsPerKey: "+ bitsPerKey);

      System.out.println("Buffer at read: " + Integer.toBinaryString(buffer));

      if(currentInput == 0){
        int mask = (int)Math.pow(2, bitsPerKey) - 1;
        // int mask = 0;
        System.out.println("Mask: " + Integer.toBinaryString(mask));
        currentInput = buffer & mask;
        currentInput <<= 24;
        offset += 8;
        // buffer >>= 32;
        System.out.println("Input after buffer added: " + Integer.toBinaryString(currentInput));
      }else{
        // buffer = buffer << (32 - Integer.numberOfLeadingZeros(currentInput));
        buffer <<= 24;
        buffer >>>= offset;
        offset += 8;
        System.out.println("Buffer at shift: " + Integer.toBinaryString(buffer));
        currentInput = buffer | currentInput;
        System.out.println("Input after buffer added: " + Integer.toBinaryString(currentInput));
      }

      if((offset >= bitsPerKey)){
        //unpack
        int andValue = 2147483647;
        System.out.println("andValue: " + Integer.toBinaryString(andValue));
        int keyValue = currentInput;
        System.out.println("keyValue: " + Integer.toBinaryString(keyValue));
        keyValue >>>= 32 - bitsPerKey;
        System.out.println("keyValue AFTER shift: " + Integer.toBinaryString(keyValue));
        currentInput <<= bitsPerKey;
        offset -= bitsPerKey;
        System.out.println("Input after shift off: " + Integer.toBinaryString(currentInput));

        phraseNumber++;
        bitsPerKey = updateBitsPerKey(phraseNumber);
        System.out.println("Phrase Key: " + keyValue);

        String s = keyValue + "\n";
        byte[] o = s.getBytes();
        out.write(o);

      }else{
        //get another byte
      }

    }

    //System.out.println(bitsPerKey);
  }

  public static void bufferToInput(){
    if(Integer.numberOfLeadingZeros(currentInput) == 32){
      currentInput = buffer;
    }else{
      int availableBits = Integer.numberOfLeadingZeros(currentInput);
      if(availableBits != 0){
        int mask = 2^availableBits;
        int temp = buffer & mask;
        buffer >>= availableBits;
        temp <<= 32 - availableBits;
        currentInput = temp & Integer.reverse(mask);
      }
    }
  }

  public static double log2(int x){
    return (Math.log(x) / Math.log(2));
  }

  public static int updateBitsPerKey(int phraseNumber){
    return (int)Math.ceil(log2(phraseNumber));
  }
}
