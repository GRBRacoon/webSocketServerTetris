package com.example.webSocketServer.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessage {
    public enum MessageType{
        ENTER, GAME,END
    }
    private MessageType type;
    private String roomId;
    private String sender;
    private String message;
}