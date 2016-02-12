package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.content.Intent;
import android.os.Bundle;
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

import java.io.File;

public class AudioGalleryActivity extends BaseActivity implements OnItemClickListener {

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
			 //loading name of files
			//songList=player.loadAssets(englishLocation,eweLocation,
	 		//		  		ewePath,englishPath);

	 		songList=player.loadFilesFromPhone(englishLocation,eweLocation);

			//loading the path
			path=player.getFilePath(eweLocation, englishLocation);
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
			Intent intent=new Intent(AudioGalleryActivity.this,URLMediaPlayerActivity.class);
			intent.putExtra(Noyawa.AUDIO_URL, path+songList[position]);
			intent.putExtra(Noyawa.TYPE, type);
			intent.putExtra(Noyawa.SUB_MODULE, submodule);
			intent.putExtra(Noyawa.MODULE, module);
			intent.putExtra(Noyawa.EXTRAS,extras);
			startActivity(intent);
		}
		   
}
