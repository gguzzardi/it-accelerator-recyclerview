package com.example.gguzzardi.it_accelerator_recyclerview.preferenes;

import android.content.Context;
import android.content.SharedPreferences;

public class MeliPreferences {

    private static final String SETTINGS_NAME = "default_settings";
    private static MeliPreferences sMeliPreferences;
    private SharedPreferences mPreferences;

    public enum Key {
        LAST_QUERY
    }

    private MeliPreferences(Context context) {
        mPreferences = context.getSharedPreferences(SETTINGS_NAME, Context.MODE_PRIVATE);
    }

    public static MeliPreferences getInstance(Context context) {
        if (sMeliPreferences == null) {
            sMeliPreferences = new MeliPreferences (context.getApplicationContext());
        }
        return sMeliPreferences;
    }

    public void put(Key key, String val) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key.name(), val);
        editor.commit();
    }

    public String getString(Key key, String defaultValue) {
        return mPreferences.getString(key.name(), defaultValue);
    }

    public boolean contains(Key key) {
        return mPreferences.contains(key.name());
    }

}
