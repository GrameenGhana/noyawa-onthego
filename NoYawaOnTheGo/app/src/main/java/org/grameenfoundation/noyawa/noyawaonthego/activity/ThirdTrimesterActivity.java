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


public class ThirdTrimesterActivity extends AppCompatActivity implements OnItemClickListener {
	
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
	    header.setText("3rd Trimester Messages");
	    Typeface custom_font = Typeface.createFromAsset(ThirdTrimesterActivity.this.getAssets(),
				"fonts/Roboto-MediumItalic.ttf");
	    header.setTypeface(custom_font);
	    String[] text={
	    		"Normal signs of pregnancy",
	    		"ANC",
	    		"Labor signs/Facility Delivery",
	    		"Breastfeeding",
	    		"PNC/Immunization",
	    		"Fonatelle/Cord care",
	    		"Warning signs of Pregnancy",
	    		"Handwashing"
	    	};
	    	
	    	int[] images={
	    		R.drawable.pregnancy,
	    		R.drawable.antenatal,
	    		R.drawable.labor_signs,
	    		R.drawable.breastfeeding,
	    		R.drawable.immunization,
	    		R.drawable.fonatelle,
	    		R.drawable.warning_signs,
	    		R.drawable.handwashing
	    	};
	    TrimesterListViewAdapter adapter=new TrimesterListViewAdapter(ThirdTrimesterActivity.this,text,images);
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
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterNormalSignsAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Normal Signs of Pregnancy";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/Normal Signs of Pregnancy";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/Normal Signs of Pregnancy";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 1:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterANCAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="ANC";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/ANC";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/ANC";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 2:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterLaborSignsAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Labor Signs and facility delivery";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/Labor Signs and facility delivery";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/Labor Signs and facility delivery";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 3:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterBreastfeedingAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Breastfeeding";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/Breastfeeding";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/Breastfeeding";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 4:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterPNCAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="PNC and Immunization";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/PNC and Immunization";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/PNC and Immunization";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 5:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterFonatelleAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Fontanelle and Cord Care";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/Fontanelle and Cord Care";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/Fontanelle and Cord Care";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 6:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterWarningSignsAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Warning Signs of Pregnancy";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/Warning Sign of Pregnancy";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/Warning Sign of Pregnancy";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 7:
			//intent=new Intent(ThirdTrimesterActivity.this, ThirdTrimesterHandWashingAudioGallery.class);
			intent=new Intent(ThirdTrimesterActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Handwashing";
			module=Noyawa.MODULE_PREGNANCY_MESSAGES;
			extras=Noyawa.EXTRAS_THIRD_TRIMESTER;
			 eweLocation="Noyawa/Ewe Pregnancy Messages/3rd Trimester/Handwashing";
			 englishLocation="Noyawa/English Pregancy Messages/3rd Trimester/Handwashing";
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
						ThirdTrimesterActivity.this);

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
								Intent intent=new Intent(ThirdTrimesterActivity.this, WelcomeActivity.class);
								startActivity(intent);
								ThirdTrimesterActivity.this.finish();

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
				goHome.setClass(ThirdTrimesterActivity.this, MenuActivity.class);
				goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(goHome);
				finish();

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}




}
