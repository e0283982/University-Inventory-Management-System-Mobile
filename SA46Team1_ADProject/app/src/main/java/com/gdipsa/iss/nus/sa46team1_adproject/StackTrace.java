package com.gdipsa.iss.nus.sa46team1_adproject;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by Hendri Setia Wardana
 */

public class StackTrace {
    public static String trace(Exception ex) {
        StringWriter outStream = new StringWriter();
        ex.printStackTrace(new PrintWriter(outStream));
        return outStream.toString();
    }
}
