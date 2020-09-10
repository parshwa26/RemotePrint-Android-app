package com.pshah.remoteprint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("Remote Print Pref", Context.MODE_PRIVATE);
        String b = sharedPreferences.getString("name", "");
        if (!b.equals("")) {
            Intent i = new Intent(this, Navigation.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_main);
    }

    public void doLogin(View v) throws JSONException {
        EditText user = findViewById(R.id.username);
        EditText pass = findViewById(R.id.password);
        String username = user.getText().toString();
        String password = pass.getText().toString();

        BackgroundTask b = new BackgroundTask(this);
        String i = b.doInBackground(username, password, "login");

        JSONObject JO = new JSONObject(i);
        JSONArray JA = JO.getJSONArray("login");


        if(JA.getJSONObject(0).getString("status").equalsIgnoreCase("success")) {
          //  Toast.makeText(this, "Done", Toast.LENGTH_LONG).show();

            SharedPreferences sharedpreferences = getSharedPreferences("Remote Print Pref", MODE_PRIVATE);

            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putBoolean("is_logged", true);
            ed.putString("name", username);
            ed.commit();
            Toast.makeText(this, "Welcome", Toast.LENGTH_LONG).show();
            Intent i1 = new Intent(this, Navigation.class);
            startActivity(i1);
        } else
            Toast.makeText(this, "Invalid Username or password", Toast.LENGTH_LONG).show();
    }
    public void doRegister(View v)
    {
        Intent i = new Intent(this,Registration.class);
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
