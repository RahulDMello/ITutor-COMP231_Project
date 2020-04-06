package com.example.itutor.main.profile.view;


import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itutor.main.databinding.FragmentBookedMeetingsBinding;
import com.example.itutor.main.profile.viewmodel.TutorProfileViewModel;
import com.example.itutor.main.tools.BookedMeetingsAdapter;
import com.example.itutor.main.tools.DateUtilsHelper;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

// import com.example.itutor.main.databinding.BookedMeetingsFragmentBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookedMeetingsFragment extends DialogFragment {

    public BookedMeetingsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentBookedMeetingsBinding binding = FragmentBookedMeetingsBinding.inflate(inflater);
        TutorProfileViewModel viewModel = ViewModelProviders.of(getActivity()).get(TutorProfileViewModel.class);
        BookedMeetingsAdapter adapter = new BookedMeetingsAdapter(viewModel.getProfileValue().getBookedMeetings()
                .values().stream().filter(
                        bookedMeeting -> !DateUtilsHelper.getShortDate(bookedMeeting.getFormattedDate()).before(yesterday())
                ).collect(Collectors.toList()));
        binding.bookedMeetingsList.setAdapter(adapter);
        binding.bookedMeetingsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.bookedMeetingsList.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL));
        return binding.getRoot();
    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
