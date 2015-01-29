package testing;

import static org.junit.Assert.*;

import org.junit.Test;

public class JSONValidatorTest {

	@Test
	public void testIsValidJSON() {
		assertTrue("JSONValidator asserts false on valid JSON 'abcde'", JSONValidator.isValidJSON("abcde"));
		assertTrue("JSONValidator asserts false on valid JSON '()'", JSONValidator.isValidJSON("()"));
		assertTrue("JSONValidator asserts false on valid JSON '[()()[][]{}()]'", JSONValidator.isValidJSON("[()()[][]{}()]"));
		
		assertTrue("JSONValidator asserts false on valid JSON '{}'", JSONValidator.isValidJSON("{}"));
		assertTrue("JSONValidator asserts false on valid JSON '[]'", JSONValidator.isValidJSON("[]"));
		assertTrue("JSONValidator asserts false on valid JSON '([{}])'", JSONValidator.isValidJSON("([{}])"));
		assertTrue("JSONValidator asserts false on valid JSON '(a b c (d (e {[ fg ( hij)]})))'", JSONValidator.isValidJSON("(a b c (d (e {[ fg ( hij)]})))"));


		assertFalse("JSONValidator asserts true on invalid JSON '(()'", JSONValidator.isValidJSON("(()"));
		assertFalse("JSONValidator asserts true on invalid JSON '())'", JSONValidator.isValidJSON("())"));
		assertFalse("JSONValidator asserts true on invalid JSON '()('", JSONValidator.isValidJSON("()("));
		assertFalse("JSONValidator asserts true on invalid JSON ')()'", JSONValidator.isValidJSON(")()"));
		assertFalse("JSONValidator asserts true on invalid JSON '{[)]'", JSONValidator.isValidJSON("{[)]"));
		assertFalse("JSONValidator asserts true on invalid JSON '(a b c (d (e {[ fg ( hij)]})) ()()() {)'", JSONValidator.isValidJSON("(a b c (d (e {[ fg ( hij)]})) ()()() {)"));
		}
}
