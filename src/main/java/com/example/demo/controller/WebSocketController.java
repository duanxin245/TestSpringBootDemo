package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.UUID;

import static com.example.demo.util.WebSocketUtil.*;



@RestController
@ServerEndpoint("/webServer/{uuid}/{username}")
public class WebSocketController {
    //判断当前用户是哪位
    private String uid = "";
    //信息类型
    private String type = "";

    //用户建立连接
    @OnOpen
    public void openSession(Session session,@PathParam("username") String username,@PathParam("uuid") String uuid ){
        this.uid = uuid;
        this.type = "1";

       ONLINE_USER_SESSIONS.put(username,session);
        String message = "{\"name\":\""+username+"\",\"message\":\"加入房间\",\"uuid\":\""+uid+"\",\"type\":\"1\"}";
       sendMessageAll(message);
    }

    //发送消息
    @OnMessage
    public void onMessage(String message,@PathParam("username") String username){
        String messageJSON = "{\"name\":\""+username+"\",\"message\":\""+message+"\",\"uuid\":\""+uid+"\",\"type\":\"2\"}";
        sendMessageAll(messageJSON);
    }

    //关闭连接
    @OnClose
    public void onClose(Session session,@PathParam("username") String username){
        ONLINE_USER_SESSIONS.remove(username);
        String message = "{\"name\":\""+username+"\",\"message\":\"离开房间\",\"type\":\"3\"}";
        //通知其他人离开
        sendMessageAll(message);
        try {
            session.close();
        }catch (IOException e){System.err.println("onClose err："+e.getMessage());}
    }

    //异常时
    @OnError
    public void onError(Session session,Throwable hrowable){
        try {
            session.close();
        } catch (IOException e) {
            System.err.println("onError excepiton "+e.getMessage());
        }
    }


}
