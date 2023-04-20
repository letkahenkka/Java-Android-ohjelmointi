package com.example.helloworld.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.helloworld.GameActivity;
import com.example.helloworld.R;
import com.example.helloworld.databinding.FragmentHomeBinding;

import YTJ.DataActivity;

public class HomeFragment extends Fragment {

    private Button startButton;
    private TextView helloText;
    private Button gameStart;

    private Button searchCompanyButton;
    private EditText searchText;

    public static final String TAG="MainActivity";
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        startButton = root.findViewById(R.id.startButton);
        helloText = root.findViewById(R.id.helloText);
        helloText.setVisibility(View.INVISIBLE);
        gameStart = root.findViewById(R.id.gameStart);
        searchCompanyButton = root.findViewById(R.id.searchButton);
        searchText = root.findViewById(R.id.searchField);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(helloText.getVisibility() == View.VISIBLE){
                    helloText.setVisibility(View.INVISIBLE);
                }
                else{
                    helloText.setVisibility(View.VISIBLE);
                }
            }
        });

        gameStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Game started");
                Intent i = new Intent(view.getContext(), GameActivity.class);
                startActivity(i);
            }
        });

        searchCompanyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), DataActivity.class);
                Log.i(TAG, searchText.getText().toString());
                i.putExtra("searchField", searchText.getText().toString());
                startActivity(i);
            }
        });

        Log.i(TAG, "Toimii toimii");

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}