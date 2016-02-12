package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.format.Time;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.application.BaseActivity;
import org.grameenfoundation.noyawa.noyawaonthego.application.JsonParser;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHelper;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mac on 1/26/16.
 */
public class MeetingsActivity extends BaseActivity {


    @Bind(R.id.editText_address) EditText address;
    @Bind(R.id.editText_male_attendance) EditText male_attendance;
    @Bind(R.id.editText_female_attendance) EditText female_attendance;

    @Bind(R.id.spinner_region) Spinner region;

    @Bind(R.id.button_submit) Button submit;

    private String selected_region;


    private static final String TAG = "MeetingsActivity";

    //server responses
    private Boolean error = true;
    private String message = "";

    private JsonParser jsonParser;

    private static String meetingURL = "http://41.191.245.72/noyawagh/api/v2/startMeetingSession";

    private SharedPreferences loginPref;
    private String name;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_activity);
        ButterKnife.bind(this);

        populateRegionSpinner();
        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selected_region = region.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }

        });



        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startMeeting();

            }

        });

        jsonParser = new JsonParser();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        loginPref=MeetingsActivity.this.getSharedPreferences("loginPrefs", MODE_WORLD_READABLE);
        name=loginPref.getString("username", "name");

    }


    public void populateRegionSpinner(){
        String[] regions={"Greater Accra Region",
                "Central Region",
                "Western Region",
                "Eastern Region",
                "Northern Region",
                "Upper East Region",
                "Upper West Region",
                "Brong Ahafo Region",
                "Volta Region",
                "Ashanti Region"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MeetingsActivity.this,android.R.layout.simple_list_item_1,regions);
        region.setAdapter(adapter);
    }


    public void startMeeting(){
        Log.d(TAG, "Start Meeting");

        if (!validate()) {
            onStartFailed("check inputs and try again!");
            return;
        }


        submit.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(MeetingsActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing...");
        progressDialog.show();


        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(Noyawa.SERVER_ADDRESS+"startMeetingSession");

        //getting current time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd,HH mm ss");
        String currentDateandTime = sdf.format(new Date());


            List<NameValuePair> nameValuePairs = new ArrayList<>(8);
            nameValuePairs.add(new BasicNameValuePair("male_attendance",male_attendance.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("female_attendance",female_attendance.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("location",address.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("start_time",currentDateandTime));
            nameValuePairs.add(new BasicNameValuePair("region",selected_region));
            nameValuePairs.add(new BasicNameValuePair("user_id",name));



            try {
                JSONObject json = jsonParser.getJSONFromUrl(meetingURL, nameValuePairs);
                error = json.getBoolean("error");
                message = json.getString("msg");

                Log.i(TAG, "Message -> " + message);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        if (!error) {
                            onStartSuccess();
                        }else {
                            onStartFailed(message);
                        }

                        progressDialog.dismiss();


                    }
                }, 3000);
    }


    public void onStartSuccess() {
        submit.setEnabled(true);

        Intent intent=new Intent(MeetingsActivity.this, MenuActivity.class);
        startActivity(intent);

        finish();
    }

    public void onStartFailed(String meesage) {
        Toast.makeText(getBaseContext(), "Start meeting failed,"+message, Toast.LENGTH_LONG).show();

        submit.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String add = address.getText().toString();
        String males = male_attendance.getText().toString();
        String females = female_attendance.getText().toString();

        if (add.isEmpty() ) {
            address.setError("enter a valid address ");
            valid = false;
        } else {
            address.setError(null);
        }

        if (males.isEmpty() ) {
            male_attendance.setError("enter a valid male attendance");
            valid = false;
        } else {
            male_attendance.setError(null);
        }

        if (females.isEmpty() ) {
            female_attendance.setError("enter a valid male attendance");
            valid = false;
        } else {
            female_attendance.setError(null);
        }

        return valid;
    }



}
