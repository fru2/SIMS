package com.patnacollege.sims;

import android.content.Context;
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
