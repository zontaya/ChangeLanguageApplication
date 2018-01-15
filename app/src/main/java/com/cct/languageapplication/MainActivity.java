package com.cct.languageapplication;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Locale myLocale;
    private final String LANGUAGE = "LANGUAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadLocale();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button buttonEn = (Button) findViewById(R.id.buttonEn);
        Button buttonIt = (Button) findViewById(R.id.butonIt);
        Button buttonTh = (Button) findViewById(R.id.butonTh);
        //ภาษาอังกฤษ
        buttonEn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLanguage(Locale.ENGLISH);
            }
        });
        //ภาษาอิตาลี่
        buttonIt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLanguage(Locale.ITALIAN);
            }
        });
        //ภาษาไทย
        buttonTh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                changeLanguage(new Locale("th"));
            }
        });
    }

    private void changeLanguage(Locale locale) {
        myLocale = locale;
        saveLanguage(myLocale.getCountry());
        Locale.setDefault(myLocale);

        Configuration config = new Configuration();
        config.locale = locale;
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        getResources().updateConfiguration(config, displayMetrics);
        recreate();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE);
        String language = prefs.getString(LANGUAGE, "");
        changeLang(language);
    }

    public void changeLang(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);
        saveLanguage(myLocale.getCountry());
        Locale.setDefault(myLocale);
        Configuration config = new Configuration();
        config.locale = myLocale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());

    }

    public void saveLanguage(String lang) {
        SharedPreferences prefs = getSharedPreferences("CommonPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(LANGUAGE, lang);
        editor.apply();
    }
}
