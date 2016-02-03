package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.TrimesterListViewAdapter;
import org.grameenfoundation.noyawa.noyawaonthego.application.BaseActivity;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;


public class YouthHealthMenuActivity extends BaseActivity implements OnItemClickListener {

	private ListView listView;
	private TextView header;
	private String type;
	private String submodule;
	private String module;
	private String extras;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.submenu_activity);
	    
	    listView=(ListView) findViewById(R.id.pregnancy_menu_listView);
	    String[] values={"Abstinence",
	    		"Rape",
	    		"Teenage Pregnancy"};
	    int[] images={R.drawable.abstinence,
	    		R.drawable.rape,
	    		R.drawable.teenage_pregnancy};
	    header=(TextView) findViewById(R.id.textView1);
	    header.setText("Youth Sexual Health Messages");
	    Typeface custom_font = Typeface.createFromAsset(YouthHealthMenuActivity.this.getAssets(),
				"fonts/Roboto-MediumItalic.ttf");
	    header.setTypeface(custom_font);
	    TrimesterListViewAdapter adapter=new TrimesterListViewAdapter(YouthHealthMenuActivity.this,values,images);
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
			//intent=new Intent(YouthHealthMenuActivity.this, YouthHealthAbstinenceAudioGallery.class);
			intent=new Intent(YouthHealthMenuActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Abstinence";
			module= Noyawa.MODULE_YOUTH_SEXUAL_HEALTH;
			extras=" ";
			 eweLocation="Ewe Health Drama Series/Abstinence";
			 englishLocation="English Health Drama Series/Abstinence";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 1	:
			//intent=new Intent(YouthHealthMenuActivity.this, YouthHealthRapeAudioGallery.class);
			intent=new Intent(YouthHealthMenuActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Rape";
			module=Noyawa.MODULE_YOUTH_SEXUAL_HEALTH;
			extras="";
			 eweLocation="Ewe Health Drama Series/Rape";
			 englishLocation="English Health Drama Series/Rape";
			 intent.putExtra(Noyawa.TYPE, type);
				intent.putExtra(Noyawa.SUB_MODULE, submodule);
				intent.putExtra(Noyawa.MODULE, module);
				intent.putExtra(Noyawa.EXTRAS,extras);
				intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
				intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
			startActivity(intent);
			break;
		case 2:
			//intent=new Intent(YouthHealthMenuActivity.this, YouthHealthTeenagePregnancyAudioGallery.class);
			intent=new Intent(YouthHealthMenuActivity.this, AudioGalleryActivity.class);
			type="Audio";
			submodule="Teenage pregnancy";
			module=Noyawa.MODULE_YOUTH_SEXUAL_HEALTH;
			extras=" ";
			 eweLocation="Ewe Health Drama Series/Teenage pregnancy";
			 englishLocation="English Health Drama Series/Teenage pregnancy";
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
	
}
