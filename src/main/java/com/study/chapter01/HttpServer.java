package com.study.chapter01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author cuizhiquan
 * @Description
 * @date 2018/11/3 13:56
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class HttpServer {

    /**
     * web容器根目录
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        server.await();
    }

    public void await(){
        int port = 8080;
        try(ServerSocket serverSocket = new ServerSocket(port,1, InetAddress.getByName("127.0.0.1"))) {
            this.doAccept(serverSocket);
        }catch (UnknownHostException e){
            e.printStackTrace();
            System.exit(1);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void doAccept(ServerSocket serverSocket){
        while(!shutdown){
            try(Socket socket = serverSocket.accept();
                InputStream input = socket.getInputStream();
                OutputStream output = socket.getOutputStream()) {

                //创建Request对象
                Request request = new Request(input);
                request.parse();
                //创建Response对象
                Response response = new Response(output);
                response.setRequest(request);
                response.sendStaticResource();

                shutdown = request.getUri().equalsIgnoreCase(SHUTDOWN_COMMAND);
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
