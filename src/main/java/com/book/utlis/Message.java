package com.book.utlis;

import lombok.Data;

@Data
public class Message {
    public Message(){
    }
    public Message(String code, String message){
        this.code=code;
        this.message=message;
    }
    String code;
    String message;

}
