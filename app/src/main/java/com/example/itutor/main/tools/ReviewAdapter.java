package com.example.itutor.main.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.itutor.main.R;
import com.example.itutor.main.model.Review;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ReviewAdapter extends FirebaseRecyclerAdapter<Review, ReviewAdapter.ViewHolder> {
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public ReviewAdapter(@NonNull FirebaseRecyclerOptions<Review> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Review model) {
        holder.name.setText(getItem(position).getFullname());
        holder.comment.setText(getItem(position).getComment());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReviewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_review_holder, parent, false));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView comment;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            comment = itemView.findViewById(R.id.message);
        }
    }
}
