package org.grameenfoundation.noyawa.noyawaonthego.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.grameenfoundation.noyawa.noyawaonthego.R;
import org.grameenfoundation.noyawa.noyawaonthego.database.DatabaseHandler;

/**
 * Created by mac on 1/25/16.
 */
public class WelcomeActivity extends Activity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000;
    private DatabaseHandler db;
    private int logInCheck;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        TextView header = (TextView)findViewById(R.id.textView1);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),
                "fonts/Roboto-MediumItalic.ttf");
        header.setTypeface(custom_font);
        header.setTextSize(24);
        header.setTextColor(Color.rgb(238, 106, 80));
        header.setText("Loading....");

        //create an instance of the database
        db=new DatabaseHandler(WelcomeActivity.this);
        //check if user is logged in
        logInCheck=db.getRowLoginActivityCount();



        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                if (logInCheck <= 0){
                    Intent mainIntent = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(mainIntent);
                }else{
                    //take the user to the main menu
                    Intent mainIntent = new Intent(getApplicationContext(),MenuActivity.class);
                    startActivity(mainIntent);

                }
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);


        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }
        
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }






}