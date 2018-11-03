package com.study.chapter01;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/3 14:33
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class Request {

    private InputStream input;
    private String uri;

    public Request(InputStream input){
        this.input = input;
    }

    public void parse(){
        StringBuffer request = new StringBuffer(2048);
        int i;
        byte[] buffer = new byte[2048];
        try {
            i= input.read(buffer);
        }catch (IOException e){
            e.printStackTrace();
            i = -1;
        }
        for (int j=0;j<i;j++){
            request.append((char) buffer[j]);
        }
        System.out.println(request.toString());
        uri = this.parseUri(request.toString());
    }

    private String parseUri(String requestString){
        int index1,index2;
        index1 = requestString.indexOf(' ');
        if(index1 != -1){
            index2 = requestString.indexOf(' ',index1 +1);
            if(index2 > index1){
                return requestString.substring(index1 + 1,index2);
            }
        }
        return null;
    }

    public String getUri(){
        return uri;
    }
}
