package com.pshah.remoteprint;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.util.LogWriter;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Rushabh on 3/25/2018.
 */

public class Home extends AppCompatActivity {


    public void onCreate(Bundle SavedInstance){

        super.onCreate(SavedInstance);

        SharedPreferences sharedPreferences = getSharedPreferences("Remote Print Pref", Context.MODE_PRIVATE);
        Boolean b = sharedPreferences.getBoolean("is_logged",true);
        if(!b) {
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.customer_home);

    }
    public void list_store(View v)
    {
        Intent i = new Intent(this,StoreList.class);
        startActivity(i);
    }
    public void doRemotePrint(View v)
    {
        Intent i = new Intent(this,RemotePrint.class);
        startActivity(i);
    }
    public void doLogout(View v)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("Remote Print Pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.clear();
        ed.commit();
        Intent i2 = new Intent(this,MainActivity.class);
        startActivity(i2);
    }
    @Override
    public void onBackPressed() {
        //finish();
        Log.w("checkk","call");
        // Simply Do noting!
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure you want to Logout")
            .setTitle("Warning");

        final Intent i1 = new Intent(this,Home.class);
        final Intent i2 = new Intent(this,MainActivity.class);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
                doLogout(null);
                startActivity(i2);
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startActivity(i1);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
