import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LZWpack {
	public static void main (String[] args) throws IOException{
		//take in input file
		File file = new File(args[0]);
		Scanner input = new Scanner(file);
		//find largest bit required by iterating through file
		//output largest bit required
		//initialise byte buffer
		//initialise buffer space b = 8
		//while file is not empty
			//get next 32-bit integer
			//set x to largest bit required
			//while x is not 0
				//if x > 8
					//shift right x - b where x = number of significant bits left and b is spaces left on buffer
					//set x to x - b
					//cast to byte through OR operation
					//output byte
				//else
					//shift left 8 - x
					//cast to byte through OR operation
					//set b to b - x
					//set x to 0
		//output byte 
	}
}
