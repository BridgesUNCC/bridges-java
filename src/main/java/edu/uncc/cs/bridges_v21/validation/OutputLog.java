package bridges.validation;
/**
 * @Title: Log file generator
 * @author: Mihai Mehedint
 * @Description: this class generates a log file 
 * containing both the standard output and the error output
 * with a time stamp. The file named "bridgesLog.txt" is located in the 
 * user's directory.
 * To use the logger one must create an OutputLog object at the beginning 
 * of main method:
 * OutputLog theLog = new OutputLog();
 * Then the log stream must be return to normal output by using:
 * theLog.returnStream();
 * at the end of the main method. Also include:
 * throws IOException
 * 
 * Warning: You can use the logger on one file at a time.
 */


import java.io.*;
import java.sql.Timestamp;
import java.util.logging.Logger;

//public class OutputLog extends OutputStream{
public class OutputLog extends OutputStream{	
	protected static PrintStream capturedStream;
	protected static PrintStream newOutputStream;
	protected static PrintStream logStream;
	protected static PrintStream logStream2;
	protected static FileOutputStream logFile;
	protected static ByteArrayOutputStream temp1;
	protected static ByteArrayOutputStream temp2;
	protected static String aPathToLog; 	
			
	/**
	 * Constructor
	 */
	public OutputLog(){
		try{
			newOutputStream = System.out;
			logFile = new FileOutputStream(generatedPath(), true);
			
			
			splitStream();
			recordLog();
			
			
		}  catch (FileNotFoundException e) {
			System.out.println("An error occured while logging the output errors. Log file not available.");
			e.printStackTrace();
		}  catch (Exception e){
			System.out.println("An error occured while trying to record the output.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructs the complete path to the log file
	 * @return String
	 */
	private String generatedPath() {
		aPathToLog = System.getProperty("user.home")+ File.separator+"bridgesLog.txt";
		return aPathToLog;
	}

	/**
	 * This method redirects the IO stream
	 *  
	 */
	public static void splitStream(){
		Runnable r1 = new Runnable() {
			  public void run() {
			   // try {
				  logStream = new PrintStream(logFile);
			        temp1 = new ByteArrayOutputStream();
					PrintStream Stre = new PrintStream(temp1);
					System.setErr(Stre);
					System.setOut(Stre);
			        //Thread.sleep(1000L);
			      
			    //} catch (InterruptedException iex) {}
			  }
			};
			Runnable r2 = new Runnable() {
				  public void run() {
				   // try {
					  logStream2 = new PrintStream(logFile);
				        temp2 = new ByteArrayOutputStream();
						PrintStream Stre2 = new PrintStream(temp2);
						
						System.setErr(Stre2);
						System.setOut(Stre2);
				        //Thread.sleep(1000L);
				   
				   // } catch (InterruptedException iex) {}
				  }
				};
				Thread thr1 = new Thread(r1);
				Thread thr2 = new Thread(r2);
				thr1.start();
				thr2.start();	
		
	}
	
	/**
	 * This method records the time stamp to the current log entry
	 * @return boolean
	 */
	public boolean recordLog(){
		try {
			java.util.Date date= new java.util.Date();
			logStream.println();
			logStream.print(new Timestamp(date.getTime()));
			logStream.println();
			
		} catch (Exception e){
			System.out.println("Log to file.");
			e.printStackTrace();
		} 
		return true;	
	}
	
	/**
	 * This method returns the stream to standard output
	 * @return boolean
	 */
	public boolean returnStream() throws IOException{
		this.write(temp1.toString().getBytes());
		this.write(temp2.toString().getBytes());
			this.flush();	
			this.close();
			System.setOut(newOutputStream);
			System.setErr(newOutputStream);
			System.out.println();
			this.logMessage();
			System.out.println();
			return true;
	}


	@Override
	public void write(int b) throws IOException {
		newOutputStream.write(b);
		logStream.write(b);
		
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		newOutputStream.write(b);
		logStream.write(b);
		
	}
	
	@Override
	public void write(byte[] b, int a, int lenght) throws IOException {
		newOutputStream.write(b, a, lenght);
		logStream.write(b, a, lenght);
		
	}
	
	@Override
	public void close() throws IOException{
		logStream.close();
	}
	
	@Override
	public void flush() throws IOException{
		newOutputStream.flush();
		logStream.flush();
	}
	
	/**
	 * This prints the Logger message to standard output
	 */
	 public void logMessage() {
		 	Logger logger = Logger.getLogger("bridges.logging");
		 	logger.info("The log file is found here: " + aPathToLog);
		  }
}

