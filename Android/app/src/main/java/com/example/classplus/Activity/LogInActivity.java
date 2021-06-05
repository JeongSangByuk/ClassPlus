package com.example.classplus.Activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.classplus.Constant;
import com.example.classplus.MysqlDataConnector.FakeModel;
import com.example.classplus.MysqlDataConnector.IModel;
import com.example.classplus.R;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LogInActivity extends AppCompatActivity {


    private EditText idEditText;
    private EditText pwEditText;
    private TextView logInButton;
    private char result2;
    private String email;

    IModel model = new FakeModel();

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setStatusBar();
        setUnderLine();

        idEditText = findViewById(R.id.et_login_id);
        pwEditText = findViewById(R.id.et_login_pw);
        logInButton = findViewById(R.id.btn_login_activity);

        /*
        new ChooserDialog(LogInActivity.this)
                .withStartFile(String.valueOf(getFilesDir().getPath()))
                .withChosenListener(new ChooserDialog.Result() {
                    @Override
                    public void onChoosePath(String path, File pathFile) {
                        Toast.makeText(LogInActivity.this, "FILE: " + path, Toast.LENGTH_SHORT).show();
                    }
                })
                // to handle the back key pressed or clicked outside the dialog:
                .withOnCancelListener(new DialogInterface.OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Log.d("CANCEL", "CANCEL");
                        dialog.cancel(); // MUST have
                    }
                })
                .build()
                .show();

         */

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = idEditText.getText().toString();
                String password = pwEditText.getText().toString();

                // 로그인
                Login task = new Login();
                task.execute("http://" + Constant.IP_ADDRESS + "/login.php", email,password);
            }
        });
    }

    class Login extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            result2 = result.toString().charAt(0);

            if (result2 == Constant.LOGIN_SUCCESS) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 다릅니다. ", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected String doInBackground(String... params) {
            String email = (String) params[1];
            String password = (String) params[2];

            String serverURL = (String) params[0];
            serverURL = serverURL + "?" + "email=" + email + "&password=" + password;
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

    // 상태바 색 바꾸기
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBar() {
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(Color.parseColor("#00000000"));//색 지정
    }

    private void setUnderLine(){

        TextView signUpBnt = findViewById(R.id.tv_loggin_sigupbnt);
        TextView findIdBnt = findViewById(R.id.tv_loggin_findidbnt);

        String signUpString = (String) signUpBnt.getText();
        String findIdString = (String) findIdBnt.getText();

        signUpBnt.setText(Html.fromHtml("<u>" + signUpString + "</u>"));
        findIdBnt.setText(Html.fromHtml("<u>" + findIdString + "</u>"));

    }
}
