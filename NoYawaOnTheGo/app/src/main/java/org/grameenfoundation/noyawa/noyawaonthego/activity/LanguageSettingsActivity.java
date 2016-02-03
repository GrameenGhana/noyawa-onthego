package org.grameenfoundation.noyawa.noyawaonthego.activity;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.SettingMenuBaseAdapter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LanguageSettingsActivity extends Activity implements OnItemClickListener {

	private RadioButton languageEnglishButton;
	private RadioButton languageEweButton;
	private RadioGroup languageOptions;
	private ListView listView;
	private ImageButton dialogButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.settings_menu);
	    listView=(ListView) findViewById(R.id.listView1);
	    listView.setOnItemClickListener(this);
	    String[] values={"Language options","Register Client","Logout"};
	    int[] images={R.drawable.language,
	    			 R.drawable.ic_people,
	    			 R.drawable.ic_action_system};
	    SettingMenuBaseAdapter adapter=new SettingMenuBaseAdapter(LanguageSettingsActivity.this,values,images);
	    listView.setAdapter(adapter);
	}

	
	
	public void Dialog(){
		final Dialog dialog = new Dialog(LanguageSettingsActivity.this);
		dialog.setContentView(R.layout.language_settings_activity);
		dialog.setTitle("Select a language");
	    
	    languageOptions=(RadioGroup) dialog.findViewById(R.id.language_options);
	    languageEnglishButton=(RadioButton) dialog.findViewById(R.id.language_english);
	    languageEweButton=(RadioButton) dialog.findViewById(R.id.language_ewe);
        
		dialogButton =(ImageButton) dialog.findViewById(R.id.imageButton1);
		dialogButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				if(languageOptions.getCheckedRadioButtonId()==R.id.language_english){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "English");
					prefsEditor.commit();
					 Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					 LanguageSettingsActivity.this.startActivity(mainIntent);
					 LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_ewe){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Ewe");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					 LanguageSettingsActivity.this.startActivity(mainIntent);
					 LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_dagaare){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Dagaare");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_dagbani){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Dagbani");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_gonja){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Gonja");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_dangme){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Dangme");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_kassem){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Kassem");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_hausa){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Hausa");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}else if(languageOptions.getCheckedRadioButtonId()==R.id.language_twi){
					SharedPreferences myPrefs = LanguageSettingsActivity.this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
					SharedPreferences.Editor prefsEditor = myPrefs.edit();
					prefsEditor.putString("option", "Twi");
					prefsEditor.commit();
					Intent mainIntent = new Intent(LanguageSettingsActivity.this,MenuActivity.class);
					LanguageSettingsActivity.this.startActivity(mainIntent);
					LanguageSettingsActivity.this.finish();
				}

			}
		});
				dialog.show();	
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		switch(position){
		case 0:
			Dialog();
			break;
			
		case 1:
		
			
			// task=new RegistrationTask();
			 /*
			 String value="staffId="+URLEncoder.encode("1")+
	 					"&facilityId="+URLEncoder.encode("414")+
	 					"&regMode="+URLEncoder.encode("PREGNANT_MOTHER")+
	 					"&firstName="+URLEncoder.encode("Florence")+
	 					"&middleName="+URLEncoder.encode("Lomotiorkor")+
	 					"&lastName="+URLEncoder.encode("Jones")+
	 					"&dob="+URLEncoder.encode("15-08-1990")+
	 					"&estimatedDob="+URLEncoder.encode("FALSE")+
	 					"&nhisNumber="+URLEncoder.encode("1234567")+
	 					"&nhisExpiryDate="+URLEncoder.encode("15-09-2014")+
	 					"&region="+URLEncoder.encode("Greater Accra")+
	 					"&address="+URLEncoder.encode("Accra")+
	 					"&phoneNumber"+URLEncoder.encode("233540827309")+
	 					"&username="+URLEncoder.encode("admin")+
	 					"&password="+URLEncoder.encode("P@ssword");
	 					*/
			// task.execute( "http://41.190.69.163:8080/ghana-national-web/registerPatient");
			 
			break;
		case 2:	
			Intent mainIntent = new Intent(LanguageSettingsActivity.this,NewClientRegistrationActivity.class);
			 LanguageSettingsActivity.this.startActivity(mainIntent);
			 LanguageSettingsActivity.this.finish();
			break;
		}
		
	}
	

}
