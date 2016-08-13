package com.summer.whm.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.context.support.StandardServletEnvironment;

public abstract class AbstractBaseServlet extends HttpServlet implements EnvironmentAware {
    private Environment environment = new StandardServletEnvironment();
    private static final long serialVersionUID = 1L;
    
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        exec(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        exec(req, resp);
    }

    abstract public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
