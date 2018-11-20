package cn.cld.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);
    @Override
    public void init() throws ServletException {
        logger.info("InitServlet Start !!!!");
    }
}
