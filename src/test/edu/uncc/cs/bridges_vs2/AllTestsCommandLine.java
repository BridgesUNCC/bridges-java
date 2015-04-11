package edu.uncc.cs.bridges_vs2;
/**
 * @author mihai mehedint
 * @Description: This class runs all the test classes outside the Eclipse unit test
 * and it gives the results at the standard output and 
 * also in the log file bridgesLog.txt in the current user directory.
 */
import java.io.IOException;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import edu.uncc.cs.brdigesV2.validation.*;

public class AllTestsCommandLine {
	public static java.lang.Class<?>[] classTests = {ADTVisualizerTest.class,
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
		TreeElementTest.class,
		BSTElementTest.class
		};

	/**
	 * This method runs all the JUnit test classes outside the Eclipse JUnit test
	 * It can be used to run the tests at command line 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		OutputLog theLog = new OutputLog();
		
	    for(int i=0;i<classTests.length;i++){
	    		Result aClassTest = JUnitCore.runClasses(classTests[i]);
	    		for (Failure aClassFailure : aClassTest.getFailures())
	    			System.out.println(aClassFailure.toString());
	    }
	    
	    theLog.returnStream();

	}

}
