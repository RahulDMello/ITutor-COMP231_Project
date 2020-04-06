package com.example.itutor.main.tools;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.itutor.main.R;
import com.example.itutor.main.model.TutorProfile;

import java.util.List;

public class SearchTutorAdapter extends RecyclerView.Adapter<SearchTutorAdapter.ViewHolder> {

    public TextView tutorName;

    private List<TutorProfile> tutorProfiles;

    private LaunchTutorProfileListener listener;

    public SearchTutorAdapter(LaunchTutorProfileListener listener, List<TutorProfile> tutorProfiles) {
        this.listener = listener;
        this.tutorProfiles = tutorProfiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tutor_holder, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        tutorName.setText(String.format("%s %s", tutorProfiles.get(i).getFirstName(), tutorProfiles.get(i).getLastName()));
        tutorName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.launchTutorProfile(tutorProfiles.get(i).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tutorProfiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tutorName = itemView.findViewById(R.id.tutorName);
        }
    }
}
