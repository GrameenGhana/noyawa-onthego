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
import android.widget.TextView;

import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHelper;

import java.io.File;

/**
 * Created by mac on 1/26/16.
 */
public class RadioSeriesActivity  extends Activity  {



    private TextView header;

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
                                deletePrefFile.delete();
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




}
