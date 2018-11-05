package com.study.chapter02.server2;

import com.study.chapter02.Request;
import com.study.chapter02.Response;
import com.study.chapter02.StaticResourceProcessor;

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
 * @date 2018/11/5 16:51
 * @Copyright (c) 2017, DaChen All Rights Reserved.
 */
public class HttpServer2 {

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String[] args) {
        HttpServer2 server = new HttpServer2();
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
            System.exit(2);
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

                //检查是请求servlet还是静态资源
                if (request.getUri().startsWith("/servlet/")){
                    ServletProcessor2 processor = new ServletProcessor2();
                    processor.process(request,response);
                }else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request,response);
                }
                shutdown = request.getUri().equalsIgnoreCase(SHUTDOWN_COMMAND);
            }catch (IOException e){
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}
