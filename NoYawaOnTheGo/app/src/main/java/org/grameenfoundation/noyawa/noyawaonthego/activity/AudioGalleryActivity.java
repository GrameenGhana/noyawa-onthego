package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.FirstTrimesterAudioBaseAdapter;
import org.grameenfoundation.noyawa.noyawaonthego.application.BaseActivity;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.grameenfoundation.noyawa.noyawaonthego.application.Player;
import org.grameenfoundation.noyawa.noyawaonthego.application.URLMediaPlayerActivity;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHandler;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHelper;

import java.io.File;

public class AudioGalleryActivity extends AppCompatActivity implements OnItemClickListener {

	private ListView audioGrid;
	private String[] songList;
	private int[] imageid;
	private String[] songname;
	private Player player;
	private String path;
	private DatabaseHandler db;
	private String type;
	private String submodule;
	private String module;
	private String extras;
	private Intent intent;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.first_trimester_gallery);
	    audioGrid=(ListView) findViewById(R.id.first_trimester_audio_gridview);
	    player=new Player(AudioGalleryActivity.this);
	    intent = getIntent();
	    preparePlayer();
	}
	
	 public void preparePlayer(){
		 try{
			String eweLocation=intent.getStringExtra(Noyawa.EWE_AUDIO_LOCATION);
			String ewePath=eweLocation+ File.separator;
			String englishLocation=intent.getStringExtra(Noyawa.ENGLISH_AUDIO_LOCATION);
			String englishPath=englishLocation+ File.separator;

			 String dagbaniLocation=intent.getStringExtra(Noyawa.DAGBANI_AUDIO_LOCATION);
			 String dagareLocation=intent.getStringExtra(Noyawa.DAGARE_AUDIO_LOCATION);
			 String dangbeLocation=intent.getStringExtra(Noyawa.DANGBE_AUDIO_LOCATION);
			 String gonjaLocation=intent.getStringExtra(Noyawa.GONJA_AUDIO_LOCATION);
			 String hausaLocation=intent.getStringExtra(Noyawa.HAUSA_AUDIO_LOCATION);
			 String kasimLocation=intent.getStringExtra(Noyawa.KASIM_AUDIO_LOCATION);
			 String twiLocation=intent.getStringExtra(Noyawa.TWI_AUDIO_LOCATION);

			 //loading name of files
			//songList=player.loadAssets(englishLocation,eweLocation,
	 		//		  		ewePath,englishPath);

	 		songList=player.loadFilesFromPhone(englishLocation,eweLocation,dagbaniLocation,dagareLocation,dangbeLocation,gonjaLocation,hausaLocation,kasimLocation,twiLocation);

			//loading the path
			path=player.getFilePath(eweLocation, englishLocation,dagbaniLocation,dagareLocation,dangbeLocation,gonjaLocation,hausaLocation,kasimLocation,twiLocation);
			populateImages();
			populateSongNames();
			db=new DatabaseHandler(AudioGalleryActivity.this);
			FirstTrimesterAudioBaseAdapter adapter= new FirstTrimesterAudioBaseAdapter(AudioGalleryActivity.this,imageid,songname);
			audioGrid.setAdapter(adapter);
			audioGrid.setOnItemClickListener(this);
			type=intent.getStringExtra(Noyawa.TYPE);
			submodule=intent.getStringExtra(Noyawa.SUB_MODULE);
			module=intent.getStringExtra(Noyawa.MODULE);
			extras=intent.getStringExtra(Noyawa.EXTRAS);
			
		 }catch(Exception e){
			 e.printStackTrace();
			 
		 }
	 }
	  public void populateImages(){
			 imageid=new int[songList.length];  
			 
			 for(int i=0;i<songList.length;i++){
				 imageid[i]=R.drawable.treble;
			 }
		   }
		   
		   public void populateSongNames(){
				 songname=new String[songList.length];
				 
				 for(int i=0;i<songList.length;i++){
					 int increment =1+i;


						 songname[i]="Message "+increment;


				 }
			   }

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			System.out.println("File to be played : " + songList[position]);

			Intent intent=new Intent(AudioGalleryActivity.this,URLMediaPlayerActivity.class);
			intent.putExtra(Noyawa.AUDIO_URL, path+songList[position]);
			intent.putExtra(Noyawa.TYPE, type);
			intent.putExtra(Noyawa.SUB_MODULE, submodule);
			intent.putExtra(Noyawa.MODULE, module);
			intent.putExtra(Noyawa.EXTRAS,extras);
			startActivity(intent);
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
						AudioGalleryActivity.this);

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
								Intent intent=new Intent(AudioGalleryActivity.this, WelcomeActivity.class);
								startActivity(intent);
								AudioGalleryActivity.this.finish();

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
				goHome.setClass(AudioGalleryActivity.this, MenuActivity.class);
				goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(goHome);
				finish();

				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}


}
