package com.example.tg_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    private Translate service;

    public void getTranslateService() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = getResources().openRawResource(R.raw.credentials)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            service = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tBox = (TextView) findViewById(R.id.textbox);
        TextView tBox2 = (TextView) findViewById(R.id.textbox2);
        Button change = (Button) findViewById(R.id.changeButton);
        Button clear = (Button) findViewById(R.id.clearButton);
        Button copy = (Button) findViewById(R.id.copyButton);

        getTranslateService();





        /*
        //スクショ用
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = String.valueOf(tBox.getText());
                tBox2.setText("おかげさまで、ご紹介いただいた就職が決まりました。");

            }
        });

         */







        //変換機能↓

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = String.valueOf(tBox.getText());

                Translation translation = service.translate(str1, Translate.TranslateOption.targetLanguage("ja"), Translate.TranslateOption.model("base"));

                String str2 = String.valueOf(tBox2.getText());
                str2 = translation.getTranslatedText();
                tBox2.setText(str2);



            }
        });










        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tBox.setText("");
                tBox2.setText("");

            }
        });

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str2 = String.valueOf(tBox2.getText());

                ClipboardManager clipboard = (ClipboardManager)
                        getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("変更　された　テキスト",str2);
                clipboard.setPrimaryClip(clip);

            }
        });




    }
}