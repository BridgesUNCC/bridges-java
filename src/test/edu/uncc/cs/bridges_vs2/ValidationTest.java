import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

import edu.uncc.cs.bridgesV2.validation.Validation;


public class ValidationTest {

	
	@Test
	public void testValidateColorWithValidColor() {
		try{
			Validation.validateColor("blue");
		} catch (Exception e) {
			fail("validateColor threw error on valid color");
		}
	}

	@Test
	public void testValidateColorWithInvalidColor() {
		boolean thrown = false;
		
		try{
			Validation.validateColor("abcde");
		} catch (Exception e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("validateColor did not throw error on invalid color");			
		}
	}

	
	@Test
	public void testValidateShapeWithValidShape() {
		try{
			Validation.validateShape("circle");
		} catch (Exception e) {
			fail("validateShape threw error on valid shape");
		}
	}

	@Test
	public void testValidateShapeWithInvalidShape() {
		boolean thrown = false;
		
		try{
			Validation.validateShape("abcde");
		} catch (Exception e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("validateShape did not throw error on invalid shape");			
		}
	}

	@Test
	public void testValidateOpacityWithValidOpacity() {
		try{
			Validation.validateOpacity(0.5);
		} catch (Exception e) {
			fail("validateOpacity threw error on valid opacity");
		}
	}

	@Test
	public void testValidateOpacityWithInvalidOpacity() {
		boolean thrown = false;
		
		try{
			Validation.validateOpacity(99);
		} catch (Exception e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("validateOpacity did not throw error on invalid opacity");			
		}
	}
	
	@Test
	public void testValidateSizeWithValidSize() {
		try{
			Validation.validateSize(20);
		} catch (Exception e) {
			fail("validateSize threw error on valid size");
		}
	}

	@Test
	public void testValidateSizeWithInvalidSize() {
		boolean thrown = false;
		
		try{
			Validation.validateSize(1000);
		} catch (Exception e) {
			thrown = true;
		}
		
		if(!thrown) {
			fail("validateSize did not throw error on invalid size");			
		}
	}
	
	
	@Test
	public void testValidateADTSizeWithInvalidSize() {
		fail("can't test since this causes a system exit");
	}
	
	public void testValidateADTVisualizerWithInvalidDataThrowsError(){
		
	}
	
	
	
}
