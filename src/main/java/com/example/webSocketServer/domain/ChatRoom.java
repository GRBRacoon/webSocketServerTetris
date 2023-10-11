package com.example.webSocketServer.domain;

import com.example.webSocketServer.service.ChatService;
import com.example.webSocketServer.service.MemberService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {

    private String roomId;
    private HashMap<Integer ,WebSocketSession> sessions = new HashMap<>();
    private int seed1;
    private int seed2;
    private String player1=null;
    private String player2=null;

    @Builder
    public ChatRoom(String roomId) {
        this.roomId = roomId;
        seed1=(int) Math.random()*100000;
        seed2=(int) Math.random()*100000;
        System.out.println("seed : "+seed1);
        System.out.println("seed : "+seed2);
    }

    public void handlerActions( ChatMessage chatMessage, ChatService chatService) {
        if(chatMessage.getType()== ChatMessage.MessageType.ENTER)
        {
            //각 플레이어 집어넣기
            if(player1==null){
                player1=chatMessage.getSender();
                chatMessage.setMessage(String.valueOf(seed1));
                sendMessage(chatMessage,chatService,sessions.get(0));
            }else{
                player2=chatMessage.getSender();
                chatMessage.setMessage(String.valueOf(seed2));
                sendMessage(chatMessage,chatService,sessions.get(1));
            }
            //시드를 주고 이름 받기.


        } else if(chatMessage.getType()== ChatMessage.MessageType.GAME ){
            if(chatMessage.getSender().equals(player1)){
                sendMessage(chatMessage, chatService,sessions.get(1));
            }else if(chatMessage.getSender().equals(player2)){
                sendMessage(chatMessage, chatService,sessions.get(0));
            }

        } else if (chatMessage.getType()== ChatMessage.MessageType.END) {
            if(chatMessage.getMessage().equals("win")){
                //game.stop;
                //승리한 플레이어의 승리수 추가

            }
            else if(chatMessage.getMessage().equals("lose")){
                //game.stop
                //패배한 플레이어의 패배수 증가.
                //승률 업데이트
            }

        }
        //게임 종료를 들어오는 텍스트로 확인.

    }

    private <T> void sendMessage(T message, ChatService chatService, WebSocketSession session) {
        //sessions.parallelStream()
        //        .forEach(session -> chatService.sendMessage(session, message));
        chatService.sendMessage(session,message);
    }
}