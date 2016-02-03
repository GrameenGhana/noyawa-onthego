package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.application.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class NewClientRegistrationActivity extends BaseActivity  {


	private RadioGroup sex,location_status;
	private Spinner region,education,channel,language;
	private EditText address;
    private EditText age;
	private EditText phone_number;

	private Button submit;


	private String gender;
    private String selected_education;
	private String selected_region;
    private String selected_channel;
    private String selected_language;

	private RegistrationTask task;


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.new_client_registration);



	    	sex=(RadioGroup) findViewById(R.id.radioGroup_sex);
        age=(EditText) findViewById(R.id.editText_age);

        education=(Spinner) findViewById(R.id.spinner_education);
		populateEducationSpinner();
		education.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selected_education = education.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                selected_education = "None";
            }

        });

        channel=(Spinner) findViewById(R.id.spinner_channel);
        populateChannelSpinner();
        channel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selected_channel = channel.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                selected_channel = "SMS";
            }

        });

        language=(Spinner) findViewById(R.id.spinner_language);
        populateLanguageSpinner();
        language.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
                selected_language = language.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                selected_language = "English";
            }

        });

        location_status=(RadioGroup) findViewById(R.id.radioGroup_location_status);

	    	region=(Spinner) findViewById(R.id.spinner_region);
	    	populateRegionSpinner();
	    	region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	            @Override
	            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
	            	selected_region=region.getSelectedItem().toString();
	            }
	            @Override
	            public void onNothingSelected(AdapterView<?> arg0) {

	            }
	            
	        });
	    	address=(EditText) findViewById(R.id.editText_address);
	    	phone_number=(EditText) findViewById(R.id.editText_phoneNumber);

        submit=(Button) findViewById(R.id.button_submit);
	    	submit.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {
					 task=new RegistrationTask();
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
	ArrayAdapter<String> adapter=new ArrayAdapter<String>(NewClientRegistrationActivity.this,android.R.layout.simple_list_item_1,regions);
	region.setAdapter(adapter);	
	}

	public void populateEducationSpinner(){
		String[] educations={"SHS",
				"JHS",
				"Tertiary",
				"None"};
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(NewClientRegistrationActivity.this,android.R.layout.simple_list_item_1,educations);
		education.setAdapter(adapter);
	}

    public void populateChannelSpinner(){
        String[] channels={"SMS",
                "Voice"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(NewClientRegistrationActivity.this,android.R.layout.simple_list_item_1,channels);
        channel.setAdapter(adapter);
    }


    public void populateLanguageSpinner(){
        String[] educations={"English",
                "Ewe",
                "Dagaare",
                "Dagbani",
                "Dangme",
                "Gonja",
                "Hausa",
                "Kassem",
                "Twi"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(NewClientRegistrationActivity.this,android.R.layout.simple_list_item_1,educations);
        education.setAdapter(adapter);
    }

    private class RegistrationTask extends AsyncTask<String, Integer, Double> {

        final ProgressDialog progressDialog = new ProgressDialog(NewClientRegistrationActivity.this);

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
