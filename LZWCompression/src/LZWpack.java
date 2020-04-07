import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class LZWpack {
	public static void main (String[] args) throws IOException{
		System.out.println("Started bitpacking");
		//take in input file
		File file = new File("encoded");
		Scanner input = new Scanner(file);
		//initialise largest bit required
		int bitsize = 8;
		//initialise phrase numbers
		int phrasenumber = 256;
		//initialize byte buffers
		byte b = 0;
		//initialize buffer space b = 8
		int bufferspace = 8;
		//initialize input and output
		FileOutputStream out = new FileOutputStream("packed");
		int bit;
		int tmp;
		int x;
		//while file is not empty
		while(input.hasNext()) {
			//get next 32-bit integer
			bit = input.nextInt();
			//iterate largest bit required if necessary
			int currentsize = (int) Math.ceil(Math.log(phrasenumber)/Math.log(2));
			if(currentsize > bitsize) {
				bitsize = currentsize;
			}
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
					b = 0;
					bufferspace = 8;
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
					if(bufferspace == 0) {
						out.write(b);
						b = 0;
						bufferspace = 8;
					}
				}
			}
			//iterate phrase number
			phrasenumber++;
		}
		if(bufferspace != 8) {
			out.write(b);
		}
		System.out.println("Finished bitpacking");
		out.close();
		input.close();
	}
}
