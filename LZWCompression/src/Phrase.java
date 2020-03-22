import java.util.*;

public class Phrase {
	Integer phrasenumber = null;
	byte pattern;
	
	public Phrase(Integer p, byte b){
		phrasenumber = p;
		pattern = b;
	}
	
	
	public Stack<Byte> getPattern(Map<Integer, Phrase> map) {
		Stack<Byte> s = new Stack<Byte>();
		returnPattern(map, s);
		return s;
	}
	
	private void returnPattern(Map<Integer, Phrase> map, Stack<Byte> stack){
		if(phrasenumber == null) {
			stack.push(pattern);
		}
		else {
			stack.push(pattern);
			Phrase p = map.get(phrasenumber);
			p.returnPattern(map, stack);
		}
	}
}

