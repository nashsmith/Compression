import java.io.*;
import java.util.*;

public class LZWunpack {

  static int buffer;
  static int currentInput;

  public static void main(String[] args) throws IOException{
	System.out.println("Started bitunpacking");

    //initialize output file
	   FileOutputStream out = new FileOutputStream("unpacked");
    //get input
    BufferedInputStream inputstream = new BufferedInputStream(new FileInputStream("packed"));
    //init variables
    int phraseNumber = 256; //potential phrase numbers
    int offset = 0; //how many bits already in the current input
    int bitsPerKey = updateBitsPerKey(phraseNumber);
    currentInput = 0; //bits being converted currently
    buffer = 0; //where new bits come into

    //while there is a byte to read put it in buffer
    while((buffer = inputstream.read()) != -1){

      //if the current input is empty, put the buffer into the left most bits
      if(currentInput == 0 && offset == 0){
        int mask = (int)Math.pow(2, bitsPerKey) - 1;
        currentInput = buffer & mask;
        currentInput <<= 24;
        offset += 8;
      //if the buffer isnt empty, put the buffer to the right of the existing bits
      }else{
        buffer <<= 24;
        buffer >>>= offset;
        offset += 8;
        currentInput = buffer | currentInput;
      }

      //if there is enough bits to output
      if((offset >= bitsPerKey)){
        //calculate the key phrase
        int keyValue = currentInput;
        keyValue >>>= 32 - bitsPerKey;
        //shift out the unpacked bits
        currentInput <<= bitsPerKey;
        //adjust the offset
        offset -= bitsPerKey;
        //increment the amount of phrases
        phraseNumber++;
        //recalculate how many bits to use per phrase
        bitsPerKey = updateBitsPerKey(phraseNumber);
        //build output string
        String s = keyValue + "\n";
        //get string in bytes
        byte[] o = s.getBytes();
        //write bytes to the file
        out.write(o);
      }else{
        //get another byte
      }

    }
    out.close();
    inputstream.close();
    System.out.println("Finished bitunpacking");
  }

  //Log base 2
  public static double log2(int x){
    return (Math.log(x) / Math.log(2));
  }

  //calculate the amount of bits to use
  public static int updateBitsPerKey(int phraseNumber){
    return (int)Math.ceil(log2(phraseNumber));
  }
}
