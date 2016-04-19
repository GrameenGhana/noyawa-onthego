package org.grameenfoundation.noyawa.noyawaonthego.application;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Handler;
import android.widget.SeekBar;
import android.widget.TextView;


import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHandler;

import java.io.File;
import java.util.ArrayList;

public class Player{

	MediaPlayer mediaPlayer;
	private boolean isPaused = false;
	SeekBar seek_bar;
	Handler seekHandler;
	Context ctx;
	private DatabaseHandler db;
	
	public Player(Context context,MediaPlayer mediaplayer)
	{
		mediaplayer=new MediaPlayer();
		this.mediaPlayer=mediaplayer;
		//this.mediaPlayer.setWakeMode(ctx, PowerManager.FULL_WAKE_LOCK);
		this.ctx=context;
		db=new DatabaseHandler(ctx);
	}
	public Player(Context context)
	{
		this.ctx=context;
	}
	

     public boolean isPlaying(MediaPlayer mediaplayer) {
       return mediaPlayer.isPlaying();
     }

     public void populateImages(int[] imageid,String[] list){
		 imageid=new int[list.length];  
		 
		 for(int i=0;i<list.length;i++){
			 imageid[i]= R.drawable.treble;
		 }
	   }
	   
	   public void populateSongNames(String[] songname,String[] list){
			 songname=new String[list.length];
			 
			 for(int i=0;i<list.length;i++){
				 int increment =1+i;
				 songname[i]="Message "+increment;
			 }
		   }
		public String mediaPlayerDuration(TextView audio_duration,MediaPlayer mediaPlayer){
			final int HOUR = 60*60*1000;
		    final int MINUTE = 60*1000;
		    final int SECOND = 1000;
		    String duration;
		    int durationInMillis = mediaPlayer.getDuration();
		    int durationHour = durationInMillis/HOUR;
		    int durationMint = (durationInMillis%HOUR)/MINUTE;
		    int durationSec = (durationInMillis%MINUTE)/SECOND;
		    if(durationHour>0){
				audio_duration.setText(String.format("%02d:%02d:%02d",
						durationHour, durationMint, durationSec));
				duration= String.format("%02d:%02d:%02d",
						durationHour, durationMint, durationSec);
		    }else{
		    	audio_duration.setText(String.format("%02d:%02d:%02d",
						durationHour, durationMint, durationSec));
		    	duration= String.format("%02d:%02d:%02d",
						durationHour, durationMint, durationSec);
		    
		}
		    return duration;
		}
		
		public String mediaPlayerCurrentDuration(MediaPlayer mediaPlayer){
			String duration_played;
			final int HOUR = 60*60*1000;
		    final int MINUTE = 60*1000;
		    final int SECOND = 1000;
		    int durationInMillis=mediaPlayer.getCurrentPosition();
		    int durationHour = durationInMillis/HOUR;
		    int durationMint = (durationInMillis%HOUR)/MINUTE;
		    int durationSec = (durationInMillis%MINUTE)/SECOND;
		    if(durationHour>0){
				duration_played= String.format("%02d:%02d:%02d",
						durationHour, durationMint, durationSec);
		    }else{
		    	duration_played= String.format("%02d:%02d:%02d",
						durationHour, durationMint, durationSec);
		    
		}
		    return duration_played;
		}
		
		public String messageStatus(MediaPlayer mediaPlayer){
			String message_status = null;
			int durationDifference=mediaPlayer.getDuration()-mediaPlayer.getCurrentPosition();
			
			if(durationDifference>0){
				message_status=Noyawa.AUDIO_STATUS_NOT_COMPLETED;
			}else {
				message_status=Noyawa.AUDIO_STATUS_COMPLETED;
			}
			return message_status;
			
		} 

			   public String getUserName(){
				   String userName;
				   SharedPreferences loginPref=ctx.getSharedPreferences("loginPrefs", ctx.MODE_WORLD_READABLE);
				   userName=loginPref.getString("username", "name");
				   return userName;
			   }
			   
			   public String getLanguage(){
				   String language;
				   SharedPreferences sharedPrefs=ctx.getSharedPreferences("myPrefs", ctx.MODE_WORLD_READABLE);
				   language=sharedPrefs.getString("option", "English");
				   return language;
			   }
			   
			   public String[] loadAssets(String Englishlocation,String Ewelocation,
					   				  String Ewepath,String EnglishPath){
				    String[] fileslist = null;
				    ArrayList<String> f = new ArrayList<String>();
				    AssetManager assetManager = ctx.getAssets();
				    try {
				    	if(getLanguage().equalsIgnoreCase("Ewe")){
				    		fileslist = assetManager.list(Ewelocation);
				    		Ewepath=Ewelocation+"/";
			        for(int i=0;i<fileslist.length;i++){
			        	f.add(fileslist[i]);
			        }
				    	}else if(getLanguage().equalsIgnoreCase("English")){
				    		fileslist = assetManager.list(Englishlocation);
				    		EnglishPath=Englishlocation+"/";
				        for(int i=0;i<fileslist.length;i++){
				        	f.add(fileslist[i]);
				        }
				    	}
				    }
				    catch (Exception e) {
				        e.printStackTrace();
				    }
				    return fileslist;
			   }

	public  String[] loadFilesFromPhone(String Englishlocation,String Ewelocation,String DagbaniLocation,String DagareLocation,String DangbeLocation,String GonjaLocation,String HausaLocation,String KasimLocation,String TwiLocation){
		String[] fileslist = null;
		ArrayList<String> f = new ArrayList<String>();
		File files = null;

		try {
			if(getLanguage().equalsIgnoreCase("Ewe")){
				files = new File(Environment.getExternalStorageDirectory(),Ewelocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",Ewelocation);
				}


			}else if(getLanguage().equalsIgnoreCase("English")){
				files = new File(Environment.getExternalStorageDirectory(),Englishlocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",Englishlocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Dagbani")){
				files = new File(Environment.getExternalStorageDirectory(),DagbaniLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",DagbaniLocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Dagare")){
				files = new File(Environment.getExternalStorageDirectory(),DagareLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",DagareLocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Dangbe")){
				 files = new File(Environment.getExternalStorageDirectory(),DangbeLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",DangbeLocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Gonja")){
				files = new File(Environment.getExternalStorageDirectory(),GonjaLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",GonjaLocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Hausa")){
				files = new File(Environment.getExternalStorageDirectory(),HausaLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",HausaLocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Kasim")){
				files = new File(Environment.getExternalStorageDirectory(),KasimLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",KasimLocation);
				}


			}
			else if(getLanguage().equalsIgnoreCase("Twi")){
				 files = new File(Environment.getExternalStorageDirectory(),TwiLocation);
				if(!files.exists()){
					files=new File("/mnt/extSdCard/",TwiLocation);
				}


			}



			fileslist = files.list();
			for(int i=0;i<fileslist.length;i++){
				f.add(fileslist[i]);
			}

		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return fileslist;

	}



			   public String getFilePath(String Ewelocation,String Englishlocation,String DagbaniLocation,String DagareLocation,String DangbeLocation,String GonjaLocation,String HausaLocation,String KasimLocation,String TwiLocation){
				   String path=null;


				   System.out.println("Language :" +getLanguage());

				 	if(getLanguage().equalsIgnoreCase("Ewe")){
				 		path=Ewelocation+ File.separator;
			    	}else if(getLanguage().equalsIgnoreCase("English")){

			    		path=Englishlocation+ File.separator;

			    	}else if(getLanguage().equalsIgnoreCase("Dagbani")){

						path=DagbaniLocation+ File.separator;

					}else if(getLanguage().equalsIgnoreCase("Dagare")){

						path=DagareLocation+ File.separator;

					}else if(getLanguage().equalsIgnoreCase("Dangbe")){

						path=DangbeLocation+ File.separator;

					}else if(getLanguage().equalsIgnoreCase("Gonja")){

						path=GonjaLocation+ File.separator;

					}else if(getLanguage().equalsIgnoreCase("Hausa")){

						path=HausaLocation+ File.separator;

					}else if(getLanguage().equalsIgnoreCase("Kasim")){

						path=KasimLocation+ File.separator;

					}else if(getLanguage().equalsIgnoreCase("Twi")){

						path=TwiLocation+ File.separator;

					}


				   System.out.println("Path :" + path);
				   
				   
				   return path;
			   }
}
