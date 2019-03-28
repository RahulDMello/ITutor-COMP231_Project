package com.example.itutor.main.booking.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.example.itutor.main.MasterActivity;
import com.example.itutor.main.R;
import com.example.itutor.main.booking.viewmodel.BookTutorViewModel;
import com.example.itutor.main.profile.view.TutorProfileActivity;
import com.example.itutor.main.tools.LaunchTutorProfileListener;
import com.example.itutor.main.tools.SearchTutorAdapter;

public class SearchTutorActivity extends MasterActivity implements LaunchTutorProfileListener {

    private BookTutorViewModel activeModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tutor);
        activeModel = ViewModelProviders.of(this).get(BookTutorViewModel.class);
        activeModel.loadProfiles();
        RecyclerView recyclerView = findViewById(R.id.tutorList);

        TextView subjectFilter = findViewById(R.id.subjectFilter);

        // TODO: filter capabilities
        activeModel.getAreProfilesLoaded().observe(this, areProfilesLoaded -> {
            if (areProfilesLoaded) {
                SearchTutorAdapter adapter = new SearchTutorAdapter(SearchTutorActivity.this, activeModel.getTutorProfiles(subjectFilter.getText().toString()));
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.HORIZONTAL));
            }
        });

        subjectFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                activeModel.notifyChangeInFilter();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void launchTutorProfile(String id) {
        // TODO: Change to go to the booking profile view
        Intent intent = new Intent(this, TutorProfileActivity.class);
        intent.putExtra(TutorProfileActivity.TUTOR_ID_KEY, id);
        startActivity(intent);
    }
}
