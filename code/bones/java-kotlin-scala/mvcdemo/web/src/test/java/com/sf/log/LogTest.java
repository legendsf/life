package com.sf.log;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private Logger logger=LoggerFactory.getLogger(LogTest.class);

    @Test
    public void log(){
        logger.error("error",new RuntimeException("eeee"));
        logger.info("info");
        logger.debug("debug");
    }
}
