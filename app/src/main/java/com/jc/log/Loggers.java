package com.jc.log;

import java.util.logging.Logger;

/**
 * Created by jc on 1/20/2015.
 */
public class Loggers extends Logger {
    protected Loggers(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }
}
