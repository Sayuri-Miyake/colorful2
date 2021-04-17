package com.example.tg_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tBox = (TextView) findViewById(R.id.textbox);
        TextView tBox2 = (TextView) findViewById(R.id.textbox2);
        Button change = (Button) findViewById(R.id.changeButton);
        Button clear = (Button) findViewById(R.id.clearButton);
        Button copy = (Button) findViewById(R.id.copyButton);





        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = String.valueOf(tBox.getText());
                tBox2.setText("おかげさまで、ご紹介いただいた就職が決まりました。");

            }
        });




        //変換機能↓

        /*
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = String.valueOf(tBox.getText());

                String baseUrl = "https://www.googleapis.com/language/translate/v2?key=";
                //APIキーの取得
                String srcLang = "&source=ja";
                String targetLang = "&target=en";
                String transChar = "&q=" + str1;

                String Url = baseUrl + srcLang + targetLang + transChar;



                tBox2.setText("「いろはにほへと」" + str1 + "「ゑひもせす」");

            }
        });

         */








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