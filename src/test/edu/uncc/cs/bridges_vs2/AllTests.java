/**
 * @author mihai mehedint
 * @Description: this test suite class runs all the test classes in the 
 * Eclipse JUnit test environment
 */
package edu.uncc.cs.bridges_vs2;
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

import edu.uncc.cs.bridgesV2.base.SLelement;
import edu.uncc.cs.bridgesV2.base.TreeElement;


@RunWith(Suite.class)
@SuiteClasses({ADTVisualizerTest.class,
	ArrayElementTest.class,
	ArrayOfElementTest.class,
	BridgesTest.class,
	ConnectorTest.class,
	DataFormatterExceptionTest.class,
	DLelementTest.class,
	EdgeTest.class,
	ElementTest.class,
	InvalidValueExceptionTest.class,
	OutputLogTest.class,
	RateLimitExceptionTest.class,
	SLelementTest.class,
	TreeElementTest.class
	
	
})
public class AllTests {

} 

