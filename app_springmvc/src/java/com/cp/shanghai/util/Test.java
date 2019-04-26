package com.cp.shanghai.util;

import org.apache.logging.log4j.LogManager;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Test
 * @Description TODO
 * @createdate 2018/10/8 星期一 10:43
 */
public class Test {

    public static void main(String args[]) {
        org.apache.logging.log4j.Logger log = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
        log.warn("this is message {}", 1);
        Exception ex = new Exception("this is a message.");
        log.error("a new exeception", ex);
        log.trace("trace message.");
        log.info("info message.");
        for (int i = 0; i < 10; i++)
            log.debug("debug message:{}={}", "line", i);
    }
}
