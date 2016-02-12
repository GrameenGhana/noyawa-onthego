package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import org.grameenfoundation.noyawa.noyawaonthego.application.JsonParser;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewClientRegistrationActivity extends BaseActivity  {


    @Bind(R.id.editText_phoneNumber) EditText phone_number;
    @Bind(R.id.editText_age) EditText age;
    @Bind(R.id.editText_address) EditText address;

    @Bind(R.id.radioGroup_sex) RadioGroup gender;
    @Bind(R.id.radioGroup_location_status) RadioGroup location_status;

    @Bind(R.id.spinner_education) Spinner education;
    @Bind(R.id.spinner_channel) Spinner channel;
    @Bind(R.id.spinner_language) Spinner language;
    @Bind(R.id.spinner_region) Spinner region;

    @Bind(R.id.button_submit) Button submit;


	private String selected_gender;
    private String selected_education;
	private String selected_region;
    private String selected_channel;
    private String selected_language;
    private String selected_location_status;

    private static final String TAG = "NewClientRegistrationActivity";

    //server responses
    private Boolean error = true;
    private String message = "";

    private JsonParser jsonParser;

    private static String registerURL = "http://41.191.245.72/noyawagh/api/v2/register";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.new_client_registration);
        ButterKnife.bind(this);

        selected_gender = ((RadioButton)findViewById(gender.getCheckedRadioButtonId())).getText().toString();

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

         selected_location_status = ((RadioButton)findViewById(location_status.getCheckedRadioButtonId())).getText().toString();

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

        submit=(Button) findViewById(R.id.button_submit);
	    	submit.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v) {

					 postRegister(phone_number.getText().toString(),selected_gender,age.getText().toString(),selected_education,selected_channel,selected_language,selected_location_status,address.getText().toString(),selected_region);
					
				}
	    		
	    	});

        jsonParser = new JsonParser();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
	    	
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
        String[] languages={"English",
                "Ewe",
                "Dagaare",
                "Dagbani",
                "Dangme",
                "Gonja",
                "Hausa",
                "Kassem",
                "Twi"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(NewClientRegistrationActivity.this,android.R.layout.simple_list_item_1,languages);
        language.setAdapter(adapter);
    }

    public void postRegister(String phoneNumber,String gender,String age,String education,String channel,String language,String location_status,String location,String region){
        Log.d(TAG, "New client registration");

        if (!validate()) {
            onRegisterFailed("check inputs and try again!");
            return;
        }

        submit.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(NewClientRegistrationActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Processing...");
        progressDialog.show();



            List<NameValuePair> nameValuePairs = new ArrayList<>(8);
            nameValuePairs.add(new BasicNameValuePair("msisdn",phoneNumber));
            nameValuePairs.add(new BasicNameValuePair("gender",gender));
            nameValuePairs.add(new BasicNameValuePair("age",age));
            nameValuePairs.add(new BasicNameValuePair("education_level",education));
            nameValuePairs.add(new BasicNameValuePair("channel", channel));
            nameValuePairs.add(new BasicNameValuePair("language", language));
            nameValuePairs.add(new BasicNameValuePair("location_status", location_status));
            nameValuePairs.add(new BasicNameValuePair("location", location));
            nameValuePairs.add(new BasicNameValuePair("region", region));



            try {
                JSONObject json = jsonParser.getJSONFromUrl(registerURL, nameValuePairs);
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
                            onRegisterSuccess();
                        } else {
                            onRegisterFailed(message);
                        }

                        progressDialog.dismiss();


                    }
                }, 3000);
    }


    public void onRegisterSuccess() {
        submit.setEnabled(true);

        Intent intent=new Intent(NewClientRegistrationActivity.this, MenuActivity.class);
        startActivity(intent);

        finish();
    }

    public void onRegisterFailed(String message) {
        Toast.makeText(getBaseContext(), "New client registration failed," + message, Toast.LENGTH_LONG).show();

        submit.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String phoneNumber = phone_number.getText().toString();
        String ag = age.getText().toString();

        if (phoneNumber.isEmpty() ) {
            phone_number.setError("enter a valid phone number ");
            valid = false;
        } else {
            phone_number.setError(null);
        }

        if (phoneNumber.isEmpty() || phoneNumber.length() < 10 || phoneNumber.length() > 12) {
            phone_number.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            phone_number.setError(null);
        }

        return valid;
    }



}
