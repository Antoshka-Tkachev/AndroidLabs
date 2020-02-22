package com.example.lab2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class VKQueryTask extends AsyncTask<URL, Void, String> {

    private URL url;
    private View view;

    public VKQueryTask(View view) {
        this.view = view;
    }

    protected String doInBackground(URL... urls) {
        String response = null;
        try {
            response = VKConnect.query(urls[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    protected void onPostExecute(String response) {
        TextView textPosition = view.findViewById(R.id.textPosition);
        TextView textName = view.findViewById(R.id.textName);

        String id = "Null";
        String firstName;
        String lastName;
        String resultName = "NoName";
        String photo = null;

        try {
            JSONObject jsonResponse = new JSONObject(response);
            JSONArray jsonArray = jsonResponse.getJSONArray("response");
            JSONObject user = jsonArray.getJSONObject(0);

            id = user.getString("id");
            firstName = user.getString("first_name");
            lastName = user.getString("last_name");
            photo = user.getString("photo_max_orig");

            resultName = firstName + "\n" + lastName;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        textPosition.setText(id);
        textName.setText(resultName);

        if (photo != null) {
            new DownloadImage((ImageView) view.findViewById(R.id.imageView)).execute(photo);
        }

    }
}
