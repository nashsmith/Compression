import java.io.*;
import java.util.*;

public class LZWdecode {
	public static void main (String[] args) throws IOException{
		//initialize output file
		FileOutputStream out = new FileOutputStream("output");
		//initialize dictionary
		Map<Integer, Phrase> dictionary = new HashMap<Integer,Phrase>();
		int i = 0;
		for(i = 0; i < 256; i++) {
			byte bytei = (byte)i;
			Phrase p = new Phrase(null, bytei);
			dictionary.put(i, p);
			System.out.print(bytei);
			}
		//get input
		File file = new File(args[0]);
		Scanner input = new Scanner(file);
		//initialize variables and take first pattern
		int currentmapkey = 256;
		int n = input.nextInt();
		Phrase v = dictionary.get(n);
		Stack<Byte> s = v.getPattern(dictionary);
		int lastphrasenumber = n;
		//output pattern
		outputByteStack(s, out);
        while(input.hasNext()){
        	//get next code
        	n = input.nextInt();
        	if(dictionary.get(n) != null) {
        		//get pattern
        		v = dictionary.get(n);
				s = v.getPattern(dictionary);
				//generate new phrase with last phrase number and mismatched character from new stack
        		Phrase tmp = new Phrase(lastphrasenumber, s.peek());
        		//add new phrase to map
        		dictionary.put(currentmapkey, tmp);
        	}
        	else {
        		//add phrase to map with repeat of last character
        		Phrase tmp = new Phrase(lastphrasenumber, s.peek());
        		dictionary.put(currentmapkey, tmp);
        		//get new pattern
        		s = tmp.getPattern(dictionary);
        	}
        	//output pattern
        	outputByteStack(s, out);
        	//iterate variables
        	lastphrasenumber = n;
        	currentmapkey++;      	
        }
        input.close();
	  }
	
	private static void outputByteStack(Stack<Byte> stack, FileOutputStream o) throws IOException {
		Stack<Byte> b = stack;
		while(b.peek() != null) {
			o.write(b.peek());
			b.pop();
		}
	}
}
