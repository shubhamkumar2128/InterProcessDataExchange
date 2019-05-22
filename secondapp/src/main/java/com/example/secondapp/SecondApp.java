package com.example.secondapp;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class SecondApp extends AppCompatActivity {
    EditText editText;
    Button button;
    String packagename = "com.example.interprocessdataexchange";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_app);
        editText = findViewById(R.id.etname);
        button = findViewById(R.id.savedatabtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager packageManager = getPackageManager();
                try {
                    ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packagename, PackageManager.GET_META_DATA);
                    String path = applicationInfo.dataDir  + "/files/sh.txt";
                    Toast.makeText(SecondApp.this, applicationInfo.dataDir, Toast.LENGTH_SHORT).show();
                    loadData(path);
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void loadData(String path) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(new File(path));
            int r = -1;
            String s = "";
            while ((r = fileInputStream.read()) != -1) {
                s = s + (char) r;
            }
            editText.setText(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
