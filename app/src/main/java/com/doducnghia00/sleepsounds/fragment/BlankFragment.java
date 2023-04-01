package com.doducnghia00.sleepsounds.fragment;

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
import android.widget.Toast;

import com.doducnghia00.sleepsounds.R;
import com.doducnghia00.sleepsounds.adapter.SoundAdapter;
import com.doducnghia00.sleepsounds.model.Sound;

import java.util.ArrayList;
import java.util.List;


public class BlankFragment extends Fragment {
    private  RecyclerView recyclerView;
    private SoundAdapter soundAdapter;



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

        recyclerView = view.findViewById(R.id.recyclerViewBlank);
        soundAdapter = new SoundAdapter(getContext());



        soundAdapter.setData(getListSound());

        recyclerView.setAdapter(soundAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);
        recyclerView.setLayoutManager(gridLayoutManager);

        soundAdapter.setOnItemClickListener(new SoundAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Click " + position, Toast.LENGTH_LONG).show();
                Log.e("Test","Clicked");
            }
        });
    }

    private List<Sound> getListSound() {
        List<Sound> list = new ArrayList<>();
        list.add(new Sound(1,"Rain",R.drawable.rainy, R.raw.rain_main));
        list.add(new Sound(2,"Thunderstorm",R.drawable.thunderstorm,R.raw.thunderstorm));
        list.add(new Sound(3,"Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves));
        list.add(new Sound(4,"Wind",R.drawable.air,R.raw.wind));

        list.add(new Sound(5,"Rain",R.drawable.rainy, R.raw.rain_main));
        list.add(new Sound(6,"Thunderstorm",R.drawable.thunderstorm,R.raw.thunderstorm));
        list.add(new Sound(7,"Rain on leaves", R.drawable.leaf, R.raw.rain_on_leaves));
        list.add(new Sound(8,"Wind",R.drawable.air,R.raw.wind));


        return list;
    }
}