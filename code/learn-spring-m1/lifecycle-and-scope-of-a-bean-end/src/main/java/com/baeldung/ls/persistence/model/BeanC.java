package com.baeldung.ls.persistence.model;

import jakarta.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanC {

    private static Logger log = LoggerFactory.getLogger(BeanC.class);

    @PreDestroy
    public void preDestroy() {
        log.info("@PreDestroy method is called.");
    }

    public void destroy() {
        log.info("Custom destroy method is called.");
    }

}
