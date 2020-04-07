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
    //System.out.println(log2(largestKeyValue));
    int bitsPerKey = updateBitsPerKey(seenKeys);
    System.out.println(bitsPerKey);

    currentInput = 0;
    buffer = 0;

    while((buffer = inputstream.read()) != -1){
      System.out.println("bitsPerKey: "+ bitsPerKey);

      System.out.println("Buffer at read: " + Integer.toBinaryString(buffer));

      if(Integer.numberOfLeadingZeros(currentInput) == 32){
        int mask = (int)Math.pow(2, bitsPerKey) - 1;
        System.out.println("Mask: " + Integer.toBinaryString(mask));
        currentInput = buffer & mask;
        System.out.println("Input after buffer added: " + Integer.toBinaryString(currentInput));
      }else{
        buffer = buffer << (32 - Integer.numberOfLeadingZeros(currentInput));
        System.out.println("Buffer at shift: " + Integer.toBinaryString(buffer));
        currentInput = buffer | currentInput;
        System.out.println("Input after buffer added: " + Integer.toBinaryString(currentInput));
      }

      if((32 - Integer.numberOfLeadingZeros(currentInput) >= bitsPerKey)){
        //unpack
        int andValue = (int)Math.pow(2, bitsPerKey) - 1;
        System.out.println("andValue: " + Integer.toBinaryString(andValue));
        int keyValue = currentInput & andValue;
        System.out.println("keyValue: " + Integer.toBinaryString(keyValue));

        currentInput >>= bitsPerKey;
        System.out.println("Input after shift off: " + Integer.toBinaryString(currentInput));

        if(!seenKeys.contains(keyValue)){
          seenKeys.add(keyValue);
          bitsPerKey = updateBitsPerKey(seenKeys);
        }
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

  public static int updateBitsPerKey(LinkedList<Integer> listOfKeys){
    return (int)Math.ceil(log2(listOfKeys.size()));
  }
}
