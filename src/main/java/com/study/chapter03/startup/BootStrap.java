package com.study.chapter03.startup;

import com.study.chapter03.connector.http.HttpConnector;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/6 13:51
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public final class BootStrap {

    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }

}
