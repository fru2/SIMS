package com.patnacollege.sims;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

public class HelperFunctions {


    public static void makeToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void logText(String str){
        final String TAG = "LODA_ERROR";
        Log.d(TAG, "logText: " + str);
    }

}
