package com.pshah.remoteprint;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Rushabh on 3/25/2018.
 */

public class BackgroundTask extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }
    public void onPreExecute() {
    }

    @Override
    protected String doInBackground(String... params) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String user = params[0];
            String pass = params[1];
            String work = params[2];
            if (work.equals("login")) {
                try {
                    URL url = new URL(Api.login_url);

                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("POST");
                    huc.setDoOutput(true);
                    OutputStream os = huc.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(user, "UTF-8") + "&"
                            + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");
                    Log.w("Error", data);
                    bw.write(data);
                    bw.flush();
                    bw.close();
                    os.close();
                    InputStream is = huc.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                    String respone = br.readLine();
                    //Toast.makeText(ctx,respone,Toast.LENGTH_LONG).show();
                    Log.w("Response", respone);

                    is.close();
                    return respone;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (work.equals("store_list")) {

                try {
                    URL url = new URL(Api.display_url);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("POST");
                    huc.setDoOutput(true);
                    InputStream is = huc.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                    String respone = br.readLine();
                    Log.w("Response", respone);
                    is.close();

                    return respone;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(work.equals("Register")) {
                try {

                    String name = params[1];
                    String email = params[2];
                    String password = params[3];
                    String mobile = params[4];
                    URL url = new URL(Api.register_url);
                    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
                    huc.setRequestMethod("POST");
                    huc.setDoOutput(true);
                    OutputStream os = huc.getOutputStream();
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&"
                            + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&"
                            + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&"
                            + URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8") + "&";
                    Log.w("Error", data);
                    bw.write(data);
                    bw.flush();
                    bw.close();
                    os.close();
                    InputStream is = huc.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                    String respone = br.readLine();
                    Log.w("Response", respone);
                    is.close();
                    return respone;
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }


             return null;
         }

    protected void onPostExecute(String result) {
        String a = result;

        if(a.equals("success")) {
            Toast.makeText(ctx,"success",Toast.LENGTH_LONG).show();
        }
        else{

            //ad.setMessage(result);
            //ad.show();
            //ListAdapter ba = new ArrayAdapter<String>((ShowData)ctx).this,android.R.layout.activity_list_item,result);
            /*t_display = (TextView)((ShowData)ctx).findViewById(R.id.showdata);
            t_display.setText(result);*/
            Log.w("ab",result);
            //Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();


        }
    }
}
