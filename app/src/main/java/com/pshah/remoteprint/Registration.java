package com.pshah.remoteprint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Rushabh on 3/26/2018.
 */

public class Registration extends AppCompatActivity {

    public void onCreate(Bundle SavedInstance) {
        super.onCreate(SavedInstance);
        setContentView(R.layout.activity_registration);
    }

    public void doRegister(View v)
    {
        EditText ed = findViewById(R.id.name);
        EditText ed1 = findViewById(R.id.email);
        EditText ed2 = findViewById(R.id.password);
        EditText ed3 = findViewById(R.id.contact);

        String name = ed.getText().toString();
        String email = ed1.getText().toString();
        String password = ed2.getText().toString();
        String contact = ed3.getText().toString();

        BackgroundTask b = new BackgroundTask(this);
        String check = b.doInBackground("Registration",name,email,password,contact);
        if (check.equals("Register Success"))
        {
            Toast.makeText(this,"Registration Successful", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        else
            Toast.makeText(this,"Error in Registration", Toast.LENGTH_LONG).show();
    }
}
