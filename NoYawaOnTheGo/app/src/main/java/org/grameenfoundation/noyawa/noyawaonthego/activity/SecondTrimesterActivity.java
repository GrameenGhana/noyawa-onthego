package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.TrimesterListViewAdapter;
import org.grameenfoundation.noyawa.noyawaonthego.application.BaseActivity;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHelper;

import java.io.File;


public class SecondTrimesterActivity extends AppCompatActivity implements OnItemClickListener {
	private ListView listView;
	private TextView header;
	private String type;
	private String submodule;
	private String module;
	private String extras;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.trimester_menu_activity);
	    listView=(ListView) findViewById(R.id.pregnancy_menu_listView);
	    header=(TextView) findViewById(R.id.textView1);
	    header.setText("2nd Trimester Messages");
	    Typeface custom_font = Typeface.createFromAsset(SecondTrimesterActivity.this.getAssets(),
				"fonts/Roboto-MediumItalic.ttf");
	    header.setTypeface(custom_font);
	    String[] text={
	    		"Birth Preparedness/Facility Delivery",
	    		"Warning Signs of Pregnancy",
	    		"Malaria & Antenatal Care",
	    		"Nutrition",
	    		"Hygiene/Sex/Food safety"
	    	};
	    	
	    	int[] images={
	    		R.drawable.birth_preparedness,
	    		R.drawable.warning_signs,
	    		R.drawable.pregnancymalaria,
	    		R.drawable.nutrition,
	    		R.drawable.handwashing
	    	};
	    TrimesterListViewAdapter adapter=new TrimesterListViewAdapter(SecondTrimesterActivity.this,text,images);
		   listView.setAdapter(adapter);
		   listView.setOnItemClickListener(this);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent;
		String eweLocation;
		String englishLocation;
		switch (position){
		
		case 0:
			//intent=new Intent(SecondTrimesterActivity.this, SecondTrimesterBirthPreparednessAudioGallery.class);
			intent=new Intent(SecondTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Birth Preparedness";
			module= Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_SECOND_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/2nd Trimester/Birth Preparedness";
			 englishLocation="Noyawa/English Pregancy Messages/2nd Trimester/Birth Preparedness";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 1:
			//intent=new Intent(SecondTrimesterActivity.this, SecondTrimesterWarningSignsAudioGallery.class);
			intent=new Intent(SecondTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Warning signs of pregnancy";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_SECOND_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/2nd Trimester/Danger or Warning signs of pregnancy";
			 englishLocation="Noyawa/English Pregancy Messages/2nd Trimester/Danger or Warning signs of pregnancy";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 2:
			//intent=new Intent(SecondTrimesterActivity.this, SecondTrimesterMalariaAudioGallery.class);
			intent=new Intent(SecondTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Malaria and ANC";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_SECOND_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/2nd Trimester/Malaria and ANC";
			 englishLocation="Noyawa/English Pregancy Messages/2nd Trimester/Malaria and ANC";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 3:
			//intent=new Intent(SecondTrimesterActivity.this, SecondTrimesterNutritionAudioGallery.class);
			intent=new Intent(SecondTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Nutrition";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_SECOND_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/2nd Trimester/Nutrition";
			 englishLocation="Noyawa/English Pregancy Messages/2nd Trimester/Nutrition";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 4:
			//intent=new Intent(SecondTrimesterActivity.this, SecondTrimesterHygieneAudioGallery.class);
			intent=new Intent(SecondTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Hygiene,sex and food safety";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_SECOND_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/2nd Trimester/Hygiene, sex and food safety";
			 englishLocation="Noyawa/English Pregancy Messages/2nd Trimester/Hygiene, sex and food safety";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		}
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.action_bar_all, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items
		switch (item.getItemId()) {
			case R.id.action_logout:
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						SecondTrimesterActivity.this);

				// set title
				alertDialogBuilder.setTitle("Logout");

				// set dialog message
				alertDialogBuilder
						.setMessage("You will be logged out.Continue?")
						.setCancelable(false)
						.setIcon(R.drawable.ic_warning)
						.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								getBaseContext().deleteDatabase(DatabaseHelper.DATABASE_NAME);
								String filePath = getApplicationContext().getFilesDir().getPath()+"/"+"shared_prefs/loginPrefs.xml";
								File deletePrefFile = new File(filePath );
								//deletePrefFile.delete();
								dialog.cancel();
								Intent intent=new Intent(SecondTrimesterActivity.this, WelcomeActivity.class);
								startActivity(intent);
								SecondTrimesterActivity.this.finish();

							}
						})
						.setNegativeButton("No",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								dialog.cancel();

							}
						});

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();

				return true;

			case R.id.action_home:
				Intent goHome = new Intent(Intent.ACTION_MAIN);
				goHome.setClass(SecondTrimesterActivity.this, MenuActivity.class);
				goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(goHome);
				finish();

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


}
