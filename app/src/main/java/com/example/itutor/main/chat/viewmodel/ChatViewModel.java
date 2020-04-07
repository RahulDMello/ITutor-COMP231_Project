package com.example.itutor.main.chat.viewmodel;

import androidx.lifecycle.ViewModel;

import com.example.itutor.main.model.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatViewModel extends ViewModel {
    private FirebaseAuth mAuth;
    private String currentSenderId;
    private String currentRecipientId;
    private String chatId = "";

    public void setCurrentRecipientId(String currentRecipientId) {
        this.currentRecipientId = currentRecipientId;
    }

    public DatabaseReference getCurrentReference() {
        return FirebaseDatabase.getInstance().getReference().child("chat").child(getChatId());
    }

    private String getChatId() {
        if (chatId.equals("")) {
            mAuth = FirebaseAuth.getInstance();
            currentSenderId = mAuth.getCurrentUser().getUid();
            if (currentRecipientId.compareTo(currentSenderId) > 0) {
                chatId = currentSenderId + "_" + currentRecipientId;
            } else {
                chatId = currentRecipientId + "_" + currentSenderId;
            }
        }
        return chatId;
    }

    public void postMessage(String message) {
        if (!message.trim().isEmpty()) {
            Message m = new Message();
            m.setMessage(message);
            m.setSenderId(currentSenderId);
            m.setTimestamp(System.currentTimeMillis());

            getCurrentReference().push().setValue(m);

        }
    }

}
