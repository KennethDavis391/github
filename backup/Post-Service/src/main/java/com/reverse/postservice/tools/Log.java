package com.reverse.postservice.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * A logging system used to print and log information.
 */
public class Log {
    private static Logger log = LogManager.getLogger();

    public static Logger getLog() { return log; }
}
