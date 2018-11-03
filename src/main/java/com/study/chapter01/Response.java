package com.study.chapter01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/3 14:55
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class Response {

    private static final int BUFFER_SIZE = 1024;
    Request request;
    OutputStream output;

    public Response(OutputStream output){
        this.output = output;
    }

    public void setRequest(Request request){
        this.request = request;
    }

    public void sendStaticResource() throws IOException{
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;
        File file = new File(HttpServer.WEB_ROOT,request.getUri());
        if(file.exists()){
            fis = new FileInputStream(file);
            int ch = fis.read(bytes,0,BUFFER_SIZE);
            while (ch != -1){
                output.write(bytes,0,ch);
                ch = fis.read(bytes,0,BUFFER_SIZE);
            }
        }else {
            String errorMessage = "HTTP/1.1 404 File Not Found";
            output.write(errorMessage.getBytes());
        }
    }
}
