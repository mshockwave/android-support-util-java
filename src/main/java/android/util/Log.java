package android.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

/**
 * Dummy wrapper for android logger
 * **/
public class Log {
    private static final Logger sLogger = LogManager.getLogger(Log.class);

    public static void d(String tag, String message){
        sLogger.debug(MarkerManager.getMarker(tag), message);
    }
    public static void d(String tag, String message, Throwable t){
        d(tag, message + '\n' + getStackTraceString(t));
    }

    public static void v(String tag, String message){
        sLogger.info(MarkerManager.getMarker(tag), message);
    }
    public static void v(String tag, String message, Throwable t){
        v(tag, message + '\n' + getStackTraceString(t));
    }

    public static void i(String tag, String message){
        v(tag, message);
    }
    public static void i(String tag, String message, Throwable t){
        i(tag, message + '\n' + getStackTraceString(t));
    }

    public static void w(String tag, String message){
        sLogger.warn(MarkerManager.getMarker(tag), message);
    }
    public static void w(String tag, String message, Throwable t){
        w(tag, message + '\n' + getStackTraceString(t));
    }

    public static void e(String tag, String message){
        sLogger.error(MarkerManager.getMarker(tag), message);
    }
    public static void e(String tag, String message, Throwable t){
        e(tag, message + '\n' + getStackTraceString(t));
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) {
            return "";
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) {
                return "";
            }
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }
}
