package org.bookStore.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
//@WebListener
public class MyServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("listener initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("listener destroyed");
    }
}
