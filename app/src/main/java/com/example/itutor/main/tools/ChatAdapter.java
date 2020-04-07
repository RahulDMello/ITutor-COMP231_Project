package com.example.itutor.main.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itutor.main.R;
import com.example.itutor.main.model.Message;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ChatAdapter extends FirebaseRecyclerAdapter<Message, ChatAdapter.ViewHolder> {

    private static final int TYPE_SENDER = 1;
    private static final int TYPE_RECIPIENT = 2;

    private String currentSenderId;

    public ChatAdapter(FirebaseRecyclerOptions<Message> options, String currentSenderId) {
        super(options);
        this.currentSenderId = currentSenderId;
    }

    @NonNull
    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == TYPE_SENDER)
            return new ChatAdapter.SenderViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_sender_holder, viewGroup, false));
        return new ChatAdapter.RecipientViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chat_recipient_holder, viewGroup, false));
    }

    @Override
    public int getItemViewType(int position) {
        if(getItem(position).getSenderId().equals(currentSenderId))
            return TYPE_SENDER;
        return TYPE_RECIPIENT;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Message model) {
        holder.Message.setText(getItem(position).getMessage());
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
