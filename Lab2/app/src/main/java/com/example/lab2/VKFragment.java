package com.example.lab2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class VKFragment extends Fragment {

    private int position;
    private TextView textPosition;
    private TextView textName;
    public View view;

    public VKFragment(int position) {
        this.position = position;
    }

    public static VKFragment newInstance(int position) {
        VKFragment fragmentPager = new VKFragment(position);
        return fragmentPager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_vk, parent, false);
        textPosition = view.findViewById(R.id.textPosition);
        textName = view.findViewById(R.id.textName);


        URL url = null;
        try {
            url = new URL("https://api.vk.com/method/users.get?user_ids=" + (position + 1) + "&fields=photo_max_orig&v=5.8&access_token=9a763b3bbb20066cd777f1853a477ca7d1227c1d2846996d84b9517a9d7237378ece3dc1c482240afc218");
        } catch (Exception e) {
            e.printStackTrace();
        }

        new VKQueryTask(view).execute(url);

//        textPosition.setText(String.valueOf(position  + 1));
//        textName.setText(String.valueOf(position  + 1));

        return view;
    }

}

