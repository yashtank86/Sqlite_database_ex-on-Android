package com.example.sqlite_database_ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    SQLite_Helper_Data sqLite_helper_data;
    EditText username, pass, user_search_values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.edt_username);
        pass = findViewById(R.id.edt_password);
        user_search_values = findViewById(R.id.edt_user_name);
        sqLite_helper_data = new SQLite_Helper_Data(this);

    }

    public void insert_data(View view) {

        String u_name = username.getText().toString();
        String u_pass = pass.getText().toString();
        long id_one = sqLite_helper_data.insert_data(u_name, u_pass);
        if (id_one < 0) {
            Message.message(this, "unsuccessful_data_insert");

        } else {
            Message.message(this, "successful_data_insert");
        }
    }

    public void read_All_data(View view) {

        String data = sqLite_helper_data.readAllDAta();
        Message.message(this, data);
    }

    public void search_values(View view) {

        String srch_name = user_search_values.getText().toString();
        String sub1 = srch_name.substring(0, srch_name.indexOf(" "));
        String sub2 = srch_name.substring(srch_name.indexOf(" ") + 1);
        String search_val = sqLite_helper_data.readSearchDAta(sub1, sub2);
        Message.message(this, search_val);

    }

    public void data_update(View view) {

        try {
            sqLite_helper_data.data_update("yoyo", "yash");
            Message.message(this, "data_updated");
        } catch (Exception e) {
            Message.message(this, "some_error" + e);
        }
    }

    public void data_delete(View view) {

        try {
            sqLite_helper_data.data_delete();
            Message.message(this, "delete data");
        } catch (Exception e) {
            Message.message(this, "some_error" + e);
        }
    }
}
