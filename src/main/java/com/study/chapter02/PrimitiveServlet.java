package com.study.chapter02;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/5 20:16
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("from service");
        PrintWriter out = response.getWriter();
        out.println("Hello.Roses are red.");
        out.print("Violets are blue.");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
