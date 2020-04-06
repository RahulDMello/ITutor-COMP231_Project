package com.example.itutor.main.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.itutor.main.BR;

public class Message extends BaseObservable implements Comparable {

    private String chatId;
    private String messageId;
    private String senderId;
    private String senderName;
    private String message;
    private long timestamp;

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Message)) throw new IllegalArgumentException();
        Message temp = (Message) o;
        return Long.compare(temp.timestamp, timestamp);
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Bindable
    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
        notifyPropertyChanged(BR.senderName);
    }

    @Bindable
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        notifyPropertyChanged(BR.timestamp);
    }
}
