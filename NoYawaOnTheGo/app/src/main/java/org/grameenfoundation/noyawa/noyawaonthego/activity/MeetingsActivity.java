package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
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
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mac on 1/26/16.
 */
public class MeetingsActivity extends BaseActivity {


    private EditText address;
    private EditText male_attendance;
    private EditText female_attendance;

    private Spinner region;
    private String selected_region;

    private Button submit;



    private StartMeetingTask task;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meetings_activity);

        region=(Spinner) findViewById(R.id.spinner_region);
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



        address=(EditText) findViewById(R.id.editText_address);
        male_attendance=(EditText) findViewById(R.id.editText_male_attendance);
        female_attendance=(EditText) findViewById(R.id.editText_female_attendance);

        submit=(Button) findViewById(R.id.button_submit);
        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                task=new StartMeetingTask();
                task.execute( "http://192.168.10.154:8080/motech-platform-server/module/ghananational/api/patients/welcome");

            }

        });


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


    public void postRegister(){

    }


    private class StartMeetingTask extends AsyncTask<String, Integer, Double> {

        final ProgressDialog progressDialog = new ProgressDialog(MeetingsActivity.this);

        @Override
        protected Double doInBackground(String... params) {
            // TODO Auto-generated method stub
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Processing...");
            progressDialog.show();

            postData(params[0]);

            return null;
        }

        protected void onPostExecute(Double result) {

            progressDialog.dismiss();
        }

        protected void onProgressUpdate(Integer... progress) {
            progressDialog.setProgress(progress[0]);

        }

        public void postData(String valueIWantToSend) {
            // Create a new HttpClient and Post Header
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(
                    "http://users.aber.ac.uk/bym1/group/androidto.php");

            try {
                // Add your data
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("myHttpData",
                        valueIWantToSend));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);

            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
            } catch (IOException e) {
                // TODO Auto-generated catch block
            }
        }

    }




}
