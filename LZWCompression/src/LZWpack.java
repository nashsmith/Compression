import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class LZWpack {
	public static void main (String[] args) throws IOException{
		//take in input file
		File file = new File(args[0]);
		Scanner input = new Scanner(file);
		//find largest bit required by iterating through file
		int n = input.nextInt();
		int t;
		while(input.hasNext()){
			 t = input.nextInt();
			 if(n <= t) {
				 t = n;
			 }
	    }
		input.close();
		//output largest bit required
		int bitsize = 0;
		while (n > 0) {
		    bitsize++;
		    n = n >> 1;
		}
		//initialize byte buffers
		byte b = 0;
		//initialize buffer space b = 8
		int bufferspace = 8;
		//initialize input and output
		input = new Scanner(file);
		FileOutputStream out = new FileOutputStream("bitpack");
		int bit;
		int tmp;
		int x;
		//while file is not empty
		while(input.hasNext()) {
			//get next 32-bit integer
			bit = input.nextInt();
			//set x to largest bit required
			x = bitsize;
			while(x!=0) {
				if(x > 8) {
					//shift right x - b where x = number of significant bits left and b is spaces left on buffer
					tmp = bit >> (x - bufferspace);
					//set x to x - b
					x = x - bufferspace;
					//cast to byte through OR operation
					b = (byte)(b | tmp);
					//output byte
					out.write(b);
				}
				else {
					//shift left 8 - x
					tmp = bit << (8 - x);
					//cast to byte through OR operation
					b = (byte)(b | tmp);
					//set b to b - x
					bufferspace = bufferspace - x;
					//set x to 0
					x = 0;
				}
			}
		//output byte 
		out.write(b);
		}
		out.close();
		input.close();
	}
}
