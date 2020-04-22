package com.sf.jkt.j.spring.spi;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LogPrint implements Iprint {
    @Override
    public boolean verify(Integer condition) {
        return condition > 0;
    }

    @Override
    public void print(String msg) {
        log.info("log print: {}", msg);
    }
}
