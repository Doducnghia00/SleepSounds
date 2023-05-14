package com.doducnghia00.sleepsounds.fragment;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.doducnghia00.sleepsounds.R;
import com.doducnghia00.sleepsounds.adapter.SoundAdapter;
import com.doducnghia00.sleepsounds.model.Sound;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment {
    private  RecyclerView recyclerView;
    private SoundAdapter soundAdapter;

    private SeekBar seekVol;
    private AudioManager audioManager;



    public BlankFragment() {
        // Required empty public constructor
    }


    public static BlankFragment newInstance() {
        return new BlankFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }



    @Override
    public void onPause() {
        super.onPause();
        //soundAdapter.stopAllMP();
    }
}