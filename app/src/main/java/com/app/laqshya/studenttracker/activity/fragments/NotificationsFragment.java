package com.app.laqshya.studenttracker.activity.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.laqshya.studenttracker.databinding.FragmentBroadcastAllFacultyBinding;
import com.app.laqshya.studenttracker.databinding.FragmentHomeAdminBinding;
import com.app.laqshya.studenttracker.databinding.FragmentNotificationBinding;

import java.util.List;

import timber.log.Timber;
    
public class NotificationsFragment extends Fragment {
    FragmentNotificationBinding fragmentNotificationBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Timber.d("onCreated");
        fragmentNotificationBinding = FragmentNotificationBinding.inflate(inflater, container, false);
        return fragmentNotificationBinding.getRoot();
    }
}
