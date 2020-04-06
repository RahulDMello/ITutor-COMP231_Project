package com.example.itutor.main.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itutor.main.R;
import com.example.itutor.main.model.Message;

import java.util.Collections;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private static final int TYPE_SENDER = 1;
    private static final int TYPE_RECIPIENT = 2;

    private List<Message> messageList;
    private String currentSenderId;

    public ChatAdapter(List<Message> messageList, String currentSenderId) {
        this.messageList = messageList;
        this.currentSenderId = currentSenderId;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_SENDER)
            return new ChatAdapter.SenderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_sender_holder, viewGroup, false));
        return new ChatAdapter.SenderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_recipient_holder, viewGroup, false));
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).getSenderId().equals(currentSenderId))
            return TYPE_SENDER;
        return TYPE_RECIPIENT;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ViewHolder viewHolder, int i) {
        viewHolder.Message.setText(messageList.get(i).getMessage());
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class RecipientViewHolder extends ViewHolder {
        public RecipientViewHolder(@NonNull View itemView) {
            super(itemView);
            Message = itemView.findViewById(R.id.message);
        }
    }

    public class SenderViewHolder extends ViewHolder {
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            Message = itemView.findViewById(R.id.message);
        }
    }

    public abstract class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Message;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
