package com.example.abood.newproject;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Second_Activity extends Group_Activity {

    int score = 0;
    Button[] choisesbtn = new Button[3];
    MediaPlayer true_sound;
    MediaPlayer false_sound;
    TextView score_TV;
    ImageView imageView;
    TextView qustionTV;
    List<Qustions> qustions;
    MyCountDownTimer myCountDownTimer;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qustion);

        initilizetoolbar();

        myCountDownTimer = new MyCountDownTimer(10000, 1000);


        if (getIntent() != null && getIntent().hasExtra("name")) {
            String name = getIntent().getStringExtra("name");

            TextView textView = findViewById(R.id.Name_txt);
            TextView textView1 = findViewById(R.id.score_txt);

            textView.setText(name);
            textView1.setText(score + "");

        }

        true_sound = MediaPlayer.create(getApplicationContext(), R.raw.l);
        false_sound = MediaPlayer.create(getApplicationContext(), R.raw.a);
        progressBar = findViewById(R.id.progressBar);

        true_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                loadnextqustion();
            }
        });

        false_sound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                loadnextqustion();
            }
        });


        qustionTV = findViewById(R.id.qustion_txt);
        final Button bt1 = findViewById(R.id.obtion1_btn);
        final Button bt2 = findViewById(R.id.option2_btn);
        final Button bt3 = findViewById(R.id.option3_btn);
        imageView = findViewById(R.id.imageView2);
        score_TV = findViewById(R.id.score_txt);


        choisesbtn[0] = bt1;
        choisesbtn[1] = bt2;
        choisesbtn[2] = bt3;


        try {
            QustionsReader reader = new QustionsReader(this);
            qustions = reader.getQustions("qustions.txt");
            Collections.shuffle(qustions);
            loadnextqustion();


            // for(Qustions q : qu){
            //    Log.d("comp",q.getQuastionText());
            //TO READ QUSTION ON COSOLE SCREEN
            //  }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void loadnextqustion() {

        for (Button btn3 : choisesbtn) {
            btn3.setEnabled(true);
            btn3.setBackgroundResource(R.drawable.choise_defult_shape);
        }

        if (qustions.isEmpty()) {
            //show score and thank user
            return;
        }

        final Qustions q = qustions.remove(0);
        qustionTV.setText(q.getQuastionText());


        for (int r = 0; r < choisesbtn.length; r++) {
            final int h = r;
            choisesbtn[r].setText(q.getChoices().get(r));
            choisesbtn[r].setBackgroundResource(R.drawable.choise_defult_shape);
            choisesbtn[r].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (choisesbtn[h].getText().toString().equalsIgnoreCase(q.getCorrectAnswer())) {
                        choisesbtn[h].setBackgroundResource(R.drawable.choise_correct_shape);
                        for (Button btn : choisesbtn) {
                            btn.setEnabled(false);
                        }
                        score += 10;
                        score_TV.setText(score + "");
                        myCountDownTimer.cancel();
                        true_sound.start();

                    } else {
                        choisesbtn[h].setBackgroundResource(R.drawable.choise_error_shape);
                        for (Button btn : choisesbtn) {
                            btn.setEnabled(false);
                        }
                        myCountDownTimer.cancel();
                        false_sound.start();
                    }
                }


            });


        }

        if (!q.getPhoto().equalsIgnoreCase("no image")) {
            int dotloc = q.getPhoto().lastIndexOf(".");
            String photoName = q.getPhoto().substring(0, dotloc);
            int photoId = getResources().getIdentifier(photoName, "drawable", getPackageName());
            imageView.setImageResource(photoId);

        } else {
            imageView.setImageResource(0);
        }
        myCountDownTimer.start();


    }

    class MyCountDownTimer extends CountDownTimer {


        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            progressBar.setProgress((int) Math.round(millisUntilFinished / 1000d));

        }

        @Override
        public void onFinish() {

            progressBar.setProgress(0);
            for (Button btn : choisesbtn) {
                btn.setEnabled(false);
            }

            false_sound.start();


        }
    }



}
