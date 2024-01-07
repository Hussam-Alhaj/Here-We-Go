package com.mydevz.softwarequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.softwarequizapp.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_play,btn_exit;
    Spinner spn1;
    public static final String[] languages = {"Select Language" ,"English", "Arabic"};
    static int getLanguage =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();


        btn_play = findViewById(R.id.btn_play);
        btn_exit = findViewById(R.id.btn_exit);
        spn1 = findViewById(R.id.spn1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item,languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn1.setAdapter(adapter);
        spn1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedLang = parent.getItemAtPosition(position).toString();
                if ( selectedLang.equals("English")){
                    setLocal(MainActivity.this,"en");
                    getLanguage =0;

                    finish();
                    startActivity(getIntent());

                }else if ( selectedLang.equals("Arabic")){
                    setLocal(MainActivity.this,"ar");
                    getLanguage =1;

                    finish();
                    startActivity(getIntent());
            }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });




        btn_play.setOnClickListener(this);
        btn_exit.setOnClickListener(this);

    }
    public void setLocal(Activity activity, String langCode){
        Locale locale = new Locale(langCode);
        locale.setDefault(locale);
        Resources resources = activity.getResources();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);
        resources.updateConfiguration(config,resources.getDisplayMetrics());



    }



    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        if (v.getId() == R.id.btn_play ) {
            Intent intent = new Intent(MainActivity.this,Play_Activity.class);

            startActivity(intent);
        }
        else if (R.id.btn_exit == btn.getId()) {
            this.finishAffinity();
        }
    }
    static public int getGetLanguage() {
        return getLanguage;
    }

}

