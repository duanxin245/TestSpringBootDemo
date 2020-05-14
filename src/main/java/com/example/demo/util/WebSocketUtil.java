package com.example.demo.util;


import com.example.demo.exception.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtil {
    private static Logger log = LoggerFactory.getLogger(WebSocketUtil.class);
    //储存在线用户
    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    //发送信息
    public static void sendMessage(Session session, String message){
        if(null == session){
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if(null == basic){
            return;
        }
        try {
            basic.sendText(message);
        }catch (IOException e){
            log.error("sendMessage IOException：",e);
        }
    }

    //获取全部聊天内容
    public static void sendMessageAll(String message){
        ONLINE_USER_SESSIONS.forEach((sessionId,session)->sendMessage(session,message));
    }


}
