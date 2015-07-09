/**
 * @author mihai mehedint
 * @Description: this test suite class runs all the test classes in the 
 * Eclipse JUnit test environment
 */
import static org.junit.Assert.*;

import javax.swing.plaf.metal.MetalIconFactory.TreeLeafIcon;

import org.junit.Test;

/**
 * @author mihai
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.uncc.cs.bridgesV2.base.BSTElement;
import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;


@RunWith(Suite.class)
@SuiteClasses(
		{ADTVisualizerTest.class,
	ArrayElementTest.class,
	ArrayOfElementTest.class,
	BridgesTest.class,
	BSTElementTest.class,
	DataFormatterExceptionTest.class,
	DLelementTest.class,
	EdgeTest.class,
	ElementVisualizerTest.class,
	GraphListTest.class,
	InvalidValueExceptionTest.class,
	LinkVisualizerTest.class,
	OutputLogTest.class,
	RateLimitExceptionTest.class,
	SLelementTest.class,
	TreeElementTest.class,
	BSTElementTest.class,
	ValidationTest.class
	
	
})

/** Runs all test classes */
public class AllTests {

} 

