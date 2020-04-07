package com.example.itutor.main.tools;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.itutor.main.R;
import com.example.itutor.main.model.BookedMeeting;

import java.util.List;

public class BookedMeetingsAdapter extends RecyclerView.Adapter<BookedMeetingsAdapter.ViewHolder> {
    private TextView studentName;
    private TextView date;
    private TextView time;
    private TextView place;
    private TextView message;
    private Button chat;

    private ChatClickListener listener;

    private List<BookedMeeting> bookedMeetings;

    public BookedMeetingsAdapter(List<BookedMeeting> bookedMeetings, ChatClickListener listener) {
        this.bookedMeetings = bookedMeetings;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookedMeetingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new BookedMeetingsAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_booked_meeting_holder, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookedMeetingsAdapter.ViewHolder viewHolder, int i) {
        studentName.setText(bookedMeetings.get(i).getStudentName());
        date.setText(bookedMeetings.get(i).getFormattedDate());
        time.setText(bookedMeetings.get(i).getFormattedTime());
        place.setText(bookedMeetings.get(i).getPlace());
        message.setText(bookedMeetings.get(i).getMessage());
        chat.setOnClickListener(v -> {
            listener.onClick(bookedMeetings.get(i).getStudentId());
        });
    }

    @Override
    public int getItemCount() {
        return bookedMeetings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            place = itemView.findViewById(R.id.place);
            message = itemView.findViewById(R.id.message);
            chat = itemView.findViewById(R.id.chat);
        }
    }

    public interface ChatClickListener {
        void onClick(String id);
    }
}
