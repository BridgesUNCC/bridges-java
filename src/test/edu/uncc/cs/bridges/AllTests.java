/**
 * @author mihai mehedint
 * @Description: this test suite class runs all the test classes in the 
 * Eclipse JUnit test environment
 */
package edu.uncc.cs.bridges;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author mihai
 *
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.uncc.cs.bridgesdrivers.SampleDataGeneratorTest;

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

