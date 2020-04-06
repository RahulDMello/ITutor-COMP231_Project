package com.example.itutor.main.chat.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.itutor.main.model.Message;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatViewModel extends ViewModel {
    private MutableLiveData<List<Message>> messages;

    public LiveData<List<Message>> getMessages() {
        if (messages == null) {
            messages = new MutableLiveData<>();
            ArrayList<Message> list = new ArrayList<>();

            Message m = new Message();
            m.setMessage("message 1");
            m.setSenderId("sender");
            m.setTimestamp(System.currentTimeMillis());
            list.add(m);

            m = new Message();
            m.setMessage("message 2");
            m.setSenderId("reci");
            m.setTimestamp(System.currentTimeMillis());
            list.add(m);

            messages.setValue(list);
        }

        return messages;
    }

    public void postMessage(String message) {
        Message m = new Message();
        m.setMessage(message);
        m.setSenderId("sender");
        m.setTimestamp(System.currentTimeMillis());
        messages.getValue().add(m);
        messages.postValue(messages.getValue());
    }

}
