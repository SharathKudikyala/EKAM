package util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sharath on 1/4/18.
 */

public class SharedPrefHelper {
    private final static String PREF_FILE = "PREF_EKAM";

    public static void setSharedPreferenceString(Context context, String key, String value){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setSharedPreferenceInt(Context context, String key, int value){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void setSharedPreferenceBoolean(Context context, String key, boolean value){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static String getSharedPreferenceString(Context context, String key, String defValue){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        return preferences.getString(key, defValue);
    }

    public static int getSharedPreferenceInt(Context context, String key, int defValue){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        return preferences.getInt(key, defValue);
    }

    public static boolean getSharedPreferenceBoolean(Context context, String key, boolean defValue){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        return preferences.getBoolean(key, defValue);
    }

    public static void removePreferences(Context context, String key){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(key).apply();
    }

    public static void clearPreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_FILE, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }

}
