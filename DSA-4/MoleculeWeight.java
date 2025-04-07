import java.util.HashMap;
import java.util.Stack;

public class MoleculeWeight{   

	private static int atomMW(HashMap<Character, Integer> atomMass, int val, char atom){
		int atomW = 0;
		if(val == 0) {
			atomW += atomMass.get(atom);
		} else {
			atomW += (atomMass.get(atom) * val);
		}
		return atomW;
	}

	static int compoundMWCalculate(String compound) {
		int len = compound.length();
		HashMap<Character, Integer> atomMass = new HashMap<>();
		atomMass.put('C', 12);
		atomMass.put('H', 1);
		atomMass.put('O', 16);

		Stack<Character> molecule = new Stack<>();
		int mw = 0;
        int idx = 0;
        int num = 1;
		int val = 0;

		while(idx < len) {
			char ch = compound.charAt(idx);
			
			if(ch == ')') {
				int compoundMW = 0;

				while(molecule.peek() != '(') {
					char popCh = molecule.pop();

					if(Character.isDigit(popCh)) {
						if(num == 1) {
							val = popCh - '0';
						} else {
							val = (popCh - '0') * num + val;
						}
						num *= 10;
					} else {
						compoundMW += atomMW(atomMass, val, popCh);
						val = 0;
						num = 1;
					}
				}
				compoundMW *= (compound.charAt(++idx) - '0');
				mw += compoundMW;
				molecule.pop();
				if(idx == len) break;
			}else{
				molecule.push(ch);
			}
			idx++;
		}

		int compoundMW = 0;
		// if stack is not empty so run this loop
		while (!molecule.isEmpty()) {
			char popCh = molecule.pop();

			if(Character.isDigit(popCh)){
				if(num == 1) {
					val = popCh - '0';
				} else {
					val = (popCh - '0') * num + val;
				}
				num *= 10;
			}else{
				compoundMW += atomMW(atomMass, val, popCh);
				val = 0;
				num = 1;
			}
		}
		mw += compoundMW;
		
		return mw;
	}

	public static void main(String[] args) {
		String compound = "(CH2)2(CH3)3";
		System.out.println(compoundMWCalculate(compound));
	}
}