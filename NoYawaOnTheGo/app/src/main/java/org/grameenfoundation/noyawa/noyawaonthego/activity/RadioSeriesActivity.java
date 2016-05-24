package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.Activity;
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
public class RadioSeriesActivity  extends AppCompatActivity implements AdapterView.OnItemClickListener  {



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
        setContentView(R.layout.radioseries_menu_activity);
        header=(TextView) findViewById(R.id.textView1);
        header.setText("Radio Series");
        Typeface custom_font = Typeface.createFromAsset(RadioSeriesActivity.this.getAssets(),
                "fonts/Roboto-MediumItalic.ttf");
        header.setTypeface(custom_font);

        listView=(ListView) findViewById(R.id.pregnancy_menu_listView);
        String[] values={"Synopsis","Episodes"
                };

        int[] images={R.drawable.player_icon,
                R.drawable.player_icon};

        TrimesterListViewAdapter adapter=new TrimesterListViewAdapter(RadioSeriesActivity.this,values,images);
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
                        RadioSeriesActivity.this);

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
                                Intent intent=new Intent(RadioSeriesActivity.this, WelcomeActivity.class);
                                startActivity(intent);
                                RadioSeriesActivity.this.finish();

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
                goHome.setClass(RadioSeriesActivity.this, MenuActivity.class);
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
        String englishLocation;

        String eweLocation;
        String dagbaniLocation;
        String twiLocation;


        switch (position){

            case 0:
                intent=new Intent(RadioSeriesActivity.this, AudioGalleryActivity.class);
                type="Audio";
                submodule="Synopsis";
                module=Noyawa.MODULE_RADIO_STORY_MESSAGES;
                extras=" ";
                englishLocation="Noyawa/Radio Story Messages/ENGLISH SYNOPSIS";
                eweLocation="Noyawa/Radio Story Messages/EWE SYNOPSIS/";
                dagbaniLocation="Noyawa/Radio Story Messages/DAGBANI SYNOPSIS/";
                twiLocation="Noyawa/Radio Story Messages/TWI SYNOPSIS/";

                intent.putExtra(Noyawa.TYPE, type);
                intent.putExtra(Noyawa.SUB_MODULE, submodule);
                intent.putExtra(Noyawa.MODULE, module);
                intent.putExtra(Noyawa.EXTRAS,extras);
                intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
                intent.putExtra(Noyawa.DAGBANI_AUDIO_LOCATION, dagbaniLocation);
                intent.putExtra(Noyawa.TWI_AUDIO_LOCATION, twiLocation);
                intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);

                startActivity(intent);
                break;

            case 1	:
                intent=new Intent(RadioSeriesActivity.this, AudioGalleryActivity.class);
                type="Audio";
                submodule="Episodes";
                module=Noyawa.MODULE_RADIO_STORY_MESSAGES;
                extras="";
                englishLocation="Noyawa/Radio Story Messages/ENGLISH/";
                eweLocation="Noyawa/Radio Story Messages/EWE/";
                dagbaniLocation="Noyawa/Radio Story Messages/DAGBANI/";
                twiLocation="Noyawa/Radio Story Messages/TWI/";

                intent.putExtra(Noyawa.TYPE, type);
                intent.putExtra(Noyawa.SUB_MODULE, submodule);
                intent.putExtra(Noyawa.MODULE, module);
                intent.putExtra(Noyawa.EXTRAS,extras);
                intent.putExtra(Noyawa.ENGLISH_AUDIO_LOCATION, englishLocation);
                intent.putExtra(Noyawa.DAGBANI_AUDIO_LOCATION, dagbaniLocation);
                intent.putExtra(Noyawa.TWI_AUDIO_LOCATION, twiLocation);
                intent.putExtra(Noyawa.EWE_AUDIO_LOCATION, eweLocation);

                startActivity(intent);
                break;





        }
    }

}
