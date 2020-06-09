package com.sf.jkt.k.util.base;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogBaseTest {
    @Test
    public void log(){
        log.debug("---------debug------------");
        log.info("----------info------------");
        log.error("----------error------------");
    }
}
