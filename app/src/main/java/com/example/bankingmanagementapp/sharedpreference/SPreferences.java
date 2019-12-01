package com.example.bankingmanagementapp.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;

public class SPreferences {

        public static SharedPreferences getSharedPreferences(Context context){

            return context.getSharedPreferences("login_credentials",context.MODE_PRIVATE);

        }
    }

