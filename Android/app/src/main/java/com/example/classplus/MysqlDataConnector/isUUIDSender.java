package com.example.classplus.MysqlDataConnector;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class isUUIDSender extends AsyncTask<String, Void, String> {
    ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

    @Override
    protected String doInBackground(String... params) {
        int total_uuid = Integer.parseInt(params[1]);
        int team_uuid = Integer.parseInt(params[2]);

        String serverURL = (String) params[0];


        serverURL = serverURL + "?" + "total_uuid=" + total_uuid + "&team_uuid=" + team_uuid;
        try {
            URL url = new URL(serverURL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();

            int responseStatusCode = httpURLConnection.getResponseCode();

            InputStream inputStream;
            if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
            } else {
                inputStream = httpURLConnection.getErrorStream();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            return sb.toString();

        } catch (Exception e) {
            return new String("Error: " + e.getMessage());
        }
    }
}