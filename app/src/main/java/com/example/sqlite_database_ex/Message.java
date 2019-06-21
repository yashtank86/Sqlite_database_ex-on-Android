package com.example.sqlite_database_ex;

import android.content.Context;
import android.widget.Toast;

class Message {
    static void message(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
