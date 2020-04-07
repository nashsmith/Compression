import java.io.*;
import java.util.*;

public class LZWunpack {
  //take in 32 bits, largest bit size for the file

  static int buffer;
  static int currentInput;

  public static void main(String[] args) throws IOException{
	System.out.println("Started bitunpacking");

    //initialize output file
	FileOutputStream out = new FileOutputStream("unpacked");
    //get input
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream("packed"));

    LinkedList<Integer> seenKeys = new LinkedList<Integer>();

    for(int i = 0; i < 255; i++){
      seenKeys.add(i);
    }

    int phraseNumber = 256;
    int offset = 0;
    int bitsPerKey = updateBitsPerKey(phraseNumber);


    currentInput = 0;
    buffer = 0;

    while((buffer = inputstream.read()) != -1){

      if(currentInput == 0 && offset == 0){
        int mask = (int)Math.pow(2, bitsPerKey) - 1;
        currentInput = buffer & mask;
        currentInput <<= 24;
        offset += 8;
      }else{
        buffer <<= 24;
        buffer >>>= offset;
        offset += 8;
        currentInput = buffer | currentInput;
      }

      if((offset >= bitsPerKey)){
        //unpack
        int keyValue = currentInput;
        keyValue >>>= 32 - bitsPerKey;
        currentInput <<= bitsPerKey;
        offset -= bitsPerKey;
        phraseNumber++;
        bitsPerKey = updateBitsPerKey(phraseNumber);
        String s = keyValue + "\n";
        byte[] o = s.getBytes();
        out.write(o);
      }else{
        //get another byte
      }

    }
    out.close();
    inputstream.close();
    System.out.println("Finished bitunpacking");
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
