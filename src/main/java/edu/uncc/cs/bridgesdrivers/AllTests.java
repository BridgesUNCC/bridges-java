/**
 * @author mihai mehedint
 * @Description: this test suite class runs all the test classes in the 
 * Eclipse JUnit test environment
 */
package edu.uncc.cs.bridgesdrivers;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author mihai
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({AbstractEdgeTest.class, 
				AbstractVertexTest.class, 
				BSTNodeTest.class, 
				EdgeTest.class, 
				SampleDataGeneratorTest.class, 
				StackNodeTest.class, 
				VertexTest.class })
public class AllTests {

} 

