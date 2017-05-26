package kr.ac.jejunu.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by replay on 2017. 5. 26..
 */
public class HelloContextListener implements ServletContextListener {
    private static Logger logger = LoggerFactory.getLogger(HelloContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("***************  context init *************");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("***************  context destroy *************");
    }

}
