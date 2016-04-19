package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.adapter.TrimesterListViewAdapter;
import org.grameenfoundation.noyawa.noyawaonthego.application.BaseActivity;
import org.grameenfoundation.noyawa.noyawaonthego.application.Noyawa;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHelper;

import java.io.File;

/**
 * Created by mac on 1/26/16.
 */
public class StoryMenuActivity extends BaseActivity implements AdapterView.OnItemClickListener {



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
        setContentView(R.layout.story_menu_activity);
        header=(TextView) findViewById(R.id.textView1);
        header.setText("Story Messages");
        Typeface custom_font = Typeface.createFromAsset(StoryMenuActivity.this.getAssets(),
                "fonts/Roboto-MediumItalic.ttf");
        header.setTypeface(custom_font);

        listView=(ListView) findViewById(R.id.pregnancy_menu_listView);
        String[] values={"General",
                "Abstinence",
                "Teenage Pregnancy",
                "Rape"};

        int[] images={R.drawable.player_icon,
                R.drawable.abstinence,
                R.drawable.teenage_pregnancy,
                R.drawable.rape};

        TrimesterListViewAdapter adapter=new TrimesterListViewAdapter(StoryMenuActivity.this,values,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

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
                        StoryMenuActivity.this);

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
                                deletePrefFile.delete();
                                dialog.cancel();
                                Intent intent=new Intent(StoryMenuActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                                StoryMenuActivity.this.finish();

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
                goHome.setClass(StoryMenuActivity.this, MenuActivity.class);
                goHome.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(goHome);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        String eweLocation;
        String englishLocation;
        String dagbaniLocation;
        String dagareLocation;
        String dangbeLocation;
        String gonjaLocation;
        String hausaLocation;
        String kasimLocation;
        String twiLocation;


        switch (position){
            case 0:
                intent=new Intent(StoryMenuActivity.this, AudioGalleryActivity.class);
                type="Audio";
                submodule="General";
                module= Noyawa.MODULE_STORY_MESSAGES;
                extras=" ";
                eweLocation="Noyawa/Voice Story Messages/EWE";
                englishLocation="Noyawa/Voice Story Messages/ENGLISH";
                dagbaniLocation="Noyawa/Voice Story Messages/DAGBANI";
                dagareLocation="Noyawa/Voice Story Messages/DAGARE";
                dangbeLocation="Noyawa/Voice Story Messages/DANGBE";
                gonjaLocation="Noyawa/Voice Story Messages/GONJA";
                hausaLocation="Noyawa/Voice Story Messages/HAUSA";
                kasimLocation="Noyawa/Voice Story Messages/KASIM";
                twiLocation="Noyawa/Voice Story Messages/TWI";


                intent.putExtra(Noyawa.TYPE, type);
                intent.putExtra(Noyawa.SUB_MODULE, submodule);
                intent.putExtra(Noyawa.MODULE, module);
                intent.putExtra(Noyawa.EXTRAS,extras);
                intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);
                intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
                intent.putExtra(Noyawa.DAGBANI_AUDIO_LOCATION, dagbaniLocation);
                intent.putExtra(Noyawa.DAGARE_AUDIO_LOCATION, dagareLocation);
                intent.putExtra(Noyawa.DANGBE_AUDIO_LOCATION, dangbeLocation);
                intent.putExtra(Noyawa.GONJA_AUDIO_LOCATION, gonjaLocation);
                intent.putExtra(Noyawa.HAUSA_AUDIO_LOCATION, hausaLocation);
                intent.putExtra(Noyawa.KASIM_AUDIO_LOCATION, kasimLocation);
                intent.putExtra(Noyawa.TWI_AUDIO_LOCATION, twiLocation);

                startActivity(intent);
                break;

            case 1	:
                intent=new Intent(StoryMenuActivity.this, AudioGalleryActivity.class);
                type="Audio";
                submodule="Abstinence";
                module=Noyawa.MODULE_STORY_MESSAGES;
                extras="";
                englishLocation="Noyawa/Voice Story Messages/Abstinence";

                intent.putExtra(Noyawa.TYPE, type);
                intent.putExtra(Noyawa.SUB_MODULE, submodule);
                intent.putExtra(Noyawa.MODULE, module);
                intent.putExtra(Noyawa.EXTRAS,extras);
                intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);

                startActivity(intent);
                break;

            case 2:
                intent=new Intent(StoryMenuActivity.this, AudioGalleryActivity.class);
                type="Audio";
                submodule="Teenage Pregnancy";
                module=Noyawa.MODULE_STORY_MESSAGES;
                extras=" ";
                englishLocation="Noyawa/Voice Story Messages/Teenage Pregnancy";

                intent.putExtra(Noyawa.TYPE, type);
                intent.putExtra(Noyawa.SUB_MODULE, submodule);
                intent.putExtra(Noyawa.MODULE, module);
                intent.putExtra(Noyawa.EXTRAS,extras);
                intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);

                startActivity(intent);
                break;

            case 3:
                intent=new Intent(StoryMenuActivity.this, AudioGalleryActivity.class);
                type="Audio";
                submodule="Rape";
                module=Noyawa.MODULE_STORY_MESSAGES;
                extras=" ";
                englishLocation="Noyawa/Voice Story Messages/Rape";

                intent.putExtra(Noyawa.TYPE, type);
                intent.putExtra(Noyawa.SUB_MODULE, submodule);
                intent.putExtra(Noyawa.MODULE, module);
                intent.putExtra(Noyawa.EXTRAS,extras);
                intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);

                startActivity(intent);
                break;


        }
    }

}
