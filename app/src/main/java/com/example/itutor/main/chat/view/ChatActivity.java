package com.example.itutor.main.chat.view;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.chat.viewmodel.ChatViewModel;
import com.example.itutor.main.databinding.ActivityChatBinding;
import com.example.itutor.main.model.Message;
import com.example.itutor.main.tools.ChatAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;

public class ChatActivity extends MasterActivity {

    public static final String RECIPIENT_ID_KEY = "RECIPIENT_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityChatBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_chat);
        ChatViewModel activeModel = new ViewModelProvider(this).get(ChatViewModel.class);
        RecyclerView recycler = binding.recycler;

        LinearSmoothScroller smoothScroller=new LinearSmoothScroller(this){
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_END;
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return Float.MIN_VALUE;
            }
        };

        binding.send.setOnClickListener(view -> {
            activeModel.postMessage(binding.message.getText().toString());
            binding.message.setText("");
//            recycler.setAdapter(new ChatAdapter(activeModel.getMessages().getValue(), "sender"));
//            smoothScroller.setTargetPosition(activeModel.getMessages().getValue().size());
//            recycler.post(() -> {
//                recycler.getLayoutManager().scrollToPosition(activeModel.getMessages().getValue().size());
//                recycler.getLayoutManager().startSmoothScroll(smoothScroller);
//            });
        });

        activeModel.setCurrentRecipientId(getIntent().getStringExtra(RECIPIENT_ID_KEY));

        FirebaseRecyclerOptions<Message> options =
                new FirebaseRecyclerOptions.Builder<Message>()
                        .setQuery(activeModel.getCurrentReference().orderByChild("timestamp"), Message.class)
                        .setLifecycleOwner(this)
                        .build();

        ChatAdapter adapter = new ChatAdapter(options, FirebaseAuth.getInstance().getCurrentUser().getUid());
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recycler.smoothScrollToPosition(positionStart);

            }
        });

        recycler.setAdapter(adapter);
        recycler.scrollToPosition(adapter.getItemCount() - 1);
    }
}
