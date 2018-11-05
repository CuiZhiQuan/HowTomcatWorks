package com.study.chapter02;

import java.io.IOException;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/5 17:10
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class StaticResourceProcessor {

    public void process(Request request, Response response){
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
