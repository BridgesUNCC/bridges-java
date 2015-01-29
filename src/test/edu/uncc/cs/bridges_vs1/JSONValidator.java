/**
 * Proper JSON has matching bracket closures. This is tested via a stack where left brackets push
 * onto stack and right brackets pop the stack. The right bracket should be
 * the closure for the pop. If not, JSON is invalid. Only push/pop when
 * OUTSIDE of double quotes in JSON file
 * 
 */

package testing;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class JSONValidator {
	// needed to check valid JSON
	private static char[] LEFT_BRACKETS = { '(', '{', '[' };
	private static char[] RIGHT_BRACKETS = { ')', '}', ']' };

	public JSONValidator() {

	}

	public static boolean isValidJSON(String json) {
		// map from right bracket to left bracket
		HashMap<Character, Character> rightToLeftBracketMap = buildMatchingCharMap();

		// make hashSets based on hashmap
		HashSet<Character> left_brackets = new HashSet<Character>(
				(Collection<Character>) rightToLeftBracketMap.values());
		HashSet<Character> right_brackets = new HashSet<Character>(
				(Collection<Character>) rightToLeftBracketMap.keySet());

		// stack to use for testing
		Stack<Character> stack = new Stack<Character>();
		boolean isOutsideQuotes = true;

		char[] JSONcharacters = json.toCharArray();

		for (char c : JSONcharacters) {
			// toggle inside/outside quotes
			if (c == '"') {
				isOutsideQuotes = !isOutsideQuotes;
			}

			if (isOutsideQuotes) { // check if outside quotes
				if (left_brackets.contains(c)) {
					// add to stack
					stack.push(c);
				} else if (right_brackets.contains(c)) {
					// if stack is empty, and have a right bracket, must be
					// invalid
					if (stack.isEmpty()) {
						return false;
					} else {
						// compare pop to hashmap value of c
						char pop = stack.pop();
						if (pop != rightToLeftBracketMap.get(c)) {
							// invalid if right bracket key does not match right
							// bracket value
							return false;
						}
					}
				}
			}
		}

		// at this point stack must be empty (each bracket had a match) or json
		// is invalid
		if (stack.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/** private method to build map between <right bracket, left bracket> */
	private static HashMap<Character, Character> buildMatchingCharMap() {
		HashMap<Character, Character> matchingCharMap = new HashMap<Character, Character>();

		for (int i = 0; i < RIGHT_BRACKETS.length; i++) {
			matchingCharMap.put(RIGHT_BRACKETS[i], LEFT_BRACKETS[i]);
		}

		return matchingCharMap;
	}

}
