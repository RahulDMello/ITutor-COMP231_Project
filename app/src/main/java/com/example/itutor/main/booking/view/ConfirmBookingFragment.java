package com.example.itutor.main.booking.view;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.itutor.main.SessionInfo;
import com.example.itutor.main.databinding.FragmentConfirmBookingBinding;
import com.example.itutor.main.model.BookedMeeting;
import com.example.itutor.main.profile.viewmodel.TutorProfileViewModel;

public class ConfirmBookingFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentConfirmBookingBinding binding = FragmentConfirmBookingBinding.inflate(inflater);

        TutorProfileViewModel viewModel = ViewModelProviders.of(getActivity()).get(TutorProfileViewModel.class);

        BookedMeeting meeting = new BookedMeeting(SessionInfo.STUDENT_NAME);

        binding.setBookedMeeting(meeting);

        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.bookAMeeting(getActivity(), meeting);
                dismiss();
            }
        });

        return binding.getRoot();
    }
}
