package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;


import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.VisualAidsBaseAdapter;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.VisualAidsVideoBaseAdapter;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.grameenfoundation.noyawa.noyawaonthego.application.Player;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class VisualAidsViewPager extends FragmentActivity implements  ActionBar.TabListener{
	SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

	private static Player player;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
	    setContentView(R.layout.visual_aids_gallery);

	    final ActionBar actionBar =getActionBar();
	    final PagerTabStrip pagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_header);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(Color.rgb(255, 149, 156));
        player=new Player(VisualAidsViewPager.this);
      //  actionBar.setTitle("Visual Aids");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager_visualAids);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(4);
       
}
	 public class SectionsPagerAdapter extends FragmentPagerAdapter {

         public SectionsPagerAdapter(FragmentManager fm) {
                 super(fm);
         }

         @Override
         public Fragment getItem(int position) {
                 Fragment fragment = null;
                 if(position==0 ){
                        fragment= new VisualAidsImagesActivity();
                 }else if(position==1){
                	 fragment= new VisualAidsVideoActivity();
                 }
                 return fragment;
         }

         @Override
         public int getCount() {
                 return 2;
         }

         @Override
         public CharSequence getPageTitle(int position) {
                 Locale l = Locale.getDefault();
                 switch (position) {
                         case 0:
                                 return "Images";
                         case 1:
                                 return "Videos";
                         
                 
                 }
                 return null;
         }
 }
	 
	 public static class VisualAidsImagesActivity extends Fragment implements OnItemClickListener {

			View rootView;
			private ListView image_grid;
			int[] mThumbIds;
			String[] captions;
			private File[] fileList;
			private ArrayList<String> images;
			private String filePath;
			private DatabaseHandler db;
			private String username;
			private String submodule;
			private String module;
			private String type;
			private String extras;
			private SharedPreferences loginPref;
			 public VisualAidsImagesActivity(){

      }
			 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				 rootView=inflater.inflate(R.layout.visual_aids_image_gallery,null,false);
				 image_grid=(ListView) rootView.findViewById(R.id.listView_images);
				 image_grid.setOnItemClickListener(this);
				 db=new DatabaseHandler(getActivity());
				 mThumbIds=new int[]{R.raw.thumb_one,
						 			R.raw.thumb_two,
						 			R.raw.thumb_three,
						 			R.raw.thumb_four,
						 			R.raw.thumb_five};
			 captions=new String[]{"How to use a condom",
					 "Birth control 1",
					 "Sexually transmitted diseases 1",
					 "Birth control 2",
					 "Sexually transmitted diseases 1"};
			 	loginPref=getActivity().getSharedPreferences("loginPrefs", MODE_WORLD_READABLE);
			 	username=loginPref.getString("username", "name");
				type="Image";
				module= Noyawa.MODULE_VISUAL_AIDS;
				extras="";
				VisualAidsBaseAdapter adapter=new VisualAidsBaseAdapter(getActivity(),mThumbIds,captions);
				image_grid.setAdapter(adapter);
			return rootView;
				   
			}
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				submodule=captions[position];
				db.insertUsageActivity(player.getUserName(), module, submodule,type,db.getDateTime()," "," ",Noyawa.VISUAL_STATUS_COMPLETED,extras,Noyawa.SYNC_STATUS_NEW);
				Intent intent=new Intent(getActivity(), VisualAidsGalleryView.class);
				intent.putExtra("position", position);
				startActivity(intent);
				
			}
			
			public void listImages(){
			
				File files=new File("/storage/extSdCard/visual_aids_images");
				fileList=files.listFiles();
				images=new ArrayList<String>();
			for (int i=0;i<fileList.length;i++){
				
				images.add(fileList[i].getName());
				filePath="/storage/extSdCard/visual_aids_images/";
				System.out.println(images.get(i));
				System.out.println(filePath);
			
			}

			
			
	 }
	 }
	 
	 public static class VisualAidsVideoActivity extends Fragment implements OnItemClickListener {

			View rootView;
			private ListView video_grid;
			int[] mThumbIds;
			String[] captions;
			ArrayList<String> videos;
			ArrayList<String> videoPaths;
			private File[] fileList;
			private DatabaseHandler db;
			private String submodule;
			private String module;
			private String type;
			private String extras;
			 public VisualAidsVideoActivity(){

   }
			 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
				 rootView=inflater.inflate(R.layout.visual_aids_video_gallery,null,false);
				 try{
					 video_grid=(ListView) rootView.findViewById(R.id.listView_video);
					 video_grid.setOnItemClickListener(this);
					 db=new DatabaseHandler(getActivity());
					listVideo();			 	
					type="Video";
					module=Noyawa.MODULE_VISUAL_AIDS;
					extras="";
					if(videos.size()>0){
				
					}else {
						Toast.makeText(getActivity(), "Video files do not exist!", Toast.LENGTH_LONG).show();
					}
				 	}catch(Exception e){
				 		e.printStackTrace();
				 		Toast.makeText(getActivity(), "Video files do not exist!", Toast.LENGTH_LONG).show();
				 	}
			return rootView;
				   
			}
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				submodule= String.valueOf(fileList[position].getName());
				db.insertUsageActivity(player.getUserName(), module, submodule,type,db.getDateTime()," "," ",Noyawa.VISUAL_STATUS_COMPLETED,extras,Noyawa.SYNC_STATUS_NEW);
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
				Uri data = Uri.parse(getVideosLocation().getAbsolutePath() + File.separator + fileList[position].getName());
				intent.setDataAndType(data, "video/mp4");
				startActivity(intent);
			}
			
			public void listVideo(){
				try{
					fileList=new File[]{};
							if(getVideosLocation().exists()){
								fileList=getVideosLocation().listFiles();
								videos=new ArrayList<String>();
								videoPaths=new ArrayList<String>();
								captions=new String[fileList.length];
								for (int i=0;i<fileList.length;i++){
									videos.add(fileList[i].getName());
									captions[i]=fileList[i].getName();
									System.out.println(getVideosLocation().getAbsolutePath());
									videoPaths.add(getVideosLocation().getAbsolutePath()+ File.separator+fileList[i].getName());
								}
								VisualAidsVideoBaseAdapter adapter=new VisualAidsVideoBaseAdapter(getActivity(),videos,videoPaths,captions);
								video_grid.setAdapter(adapter);
							}else {
								Toast.makeText(getActivity(), "Video files do not exist!", Toast.LENGTH_LONG).show();
							}
					}catch(Exception e){
						e.printStackTrace();
						Toast.makeText(getActivity(), "Video files do not exist!", Toast.LENGTH_LONG).show();
					}
				}
	 }
	 
	 public static File getVideosLocation(){
		 File files = new File("/storage/extSdCard/Noyawa/visual_aids_videos/"+getVideoLanguageLocation());
		 if(!files.exists()){
			 files=new File(Environment.getExternalStorageDirectory(),"Noyawa/visual_aids_videos/"+getVideoLanguageLocation());
		 }
		return files;
		 
	 }
	 
	 
	 public static String getVideoLanguageLocation(){
		 String location=null;
		 if(player.getLanguage().equalsIgnoreCase("Ewe")){
			 location ="ewe_videos";
		 }else {
			 location ="english_videos";
		 }
		 
		 return location;
		 
	 }
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
