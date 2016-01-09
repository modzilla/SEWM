package sewm.epic.util;


import java.sql.Timestamp;

public class StaticLogger {
	public static final int ERROR = 1, WARN = 2, INFO = 3, VERBOSE = 4, DEBUG = 9;
	public static boolean sysoLogging = true;
	public static String logFile = "tmp.log";
	public static int level = DEBUG;
	private static StringBuilder logBuilder = new StringBuilder();

	
	private static final String preLog = "[", preCaller = "@ ", inCaller = " > ", conCaller = "]: ",timeSplit = " ";
	
	public static void log(String s, int level) {
		if (level <= StaticLogger.level) {
			StackTraceElement[] ste = Thread.currentThread().getStackTrace();
			logBuilder.delete(0, logBuilder.length());
			logBuilder.append(preLog + getTime() + timeSplit + preCaller + ste[2].getClassName() + inCaller + ste[2].getMethodName() + conCaller);
			logBuilder.append(s);
			logBuilder.append('\n');
			if(sysoLogging){
				if(level < INFO)
					System.err.print(logBuilder.toString());
				if(level >= INFO)
					System.out.print(logBuilder.toString());
			}
			
		}
	}
	public static String getTime(){
		return new Timestamp(System.currentTimeMillis()).toString();
	}

}
