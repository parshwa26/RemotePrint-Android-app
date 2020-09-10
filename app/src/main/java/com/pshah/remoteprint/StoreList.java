package com.pshah.remoteprint;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StoreList extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public void onCreate(Bundle SavedInstance)
    {
        super.onCreate(SavedInstance);
        setContentView(R.layout.store_list);

        try {
            getData();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void getData() throws JSONException {
        BackgroundTask b = new BackgroundTask(this);

        String respone = b.doInBackground("null", "null", "store_list");

 //        String  arr[]={"Red","Green","Blue","Yellow","Cyan"};

        JSONArray JA=new JSONArray(respone);
        JSONObject json;
        final String[] str1 = new String[JA.length()];
        for(int i=0;i<JA.length();i++)
        {
            json=JA.getJSONObject(i);
            str1[i] =  json.getString("name")+"\n";
            str1[i] = str1[i] + json.getString("address")+"\n";
            str1[i] = str1[i] + json.getString("number")+"\n";
        }
        final List<String> list = new ArrayList<String>();

        for(int i=0;i<str1.length;i++)
        {
            list.add(str1[i]);
        }
        final ListView l= findViewById(R.id.list_items);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        l.setAdapter(adapter);
        l.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> l, View v, int position, long id) {
        Log.w("HelloListView", "You clicked Item: " + id + " at position:" + position);
    }

    @Override
    public void onBackPressed()
    {
        Intent i = new Intent(this,Home.class);
        startActivity(i);
    }

}
