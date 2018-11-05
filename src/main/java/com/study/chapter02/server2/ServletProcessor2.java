package com.study.chapter02.server2;

import com.study.chapter02.Constants;
import com.study.chapter02.Request;
import com.study.chapter02.Response;

import javax.servlet.Servlet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/5 17:13
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class ServletProcessor2 {

    public void process(Request request, Response response){
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;
        try {
            //创建URLClassLoader
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classPath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file",null,classPath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null,repository,streamHandler);
            loader = new URLClassLoader(urls);
        }catch (IOException e){
            e.printStackTrace();
        }
        Class myClass = null;
        try{
            myClass = loader.loadClass(servletName);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Servlet servlet = null;
        RequestFacade requestFacade = new RequestFacade(request);
        ResponseFacade responseFacade = new ResponseFacade(response);
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service(requestFacade,responseFacade);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
