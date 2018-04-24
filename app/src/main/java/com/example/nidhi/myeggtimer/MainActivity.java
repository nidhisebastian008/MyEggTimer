package com.example.nidhi.myeggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTextView;
    SeekBar timerSeekBar;
    boolean counterActive =false;
    CountDownTimer countDownTimer;
    //go button
    Button goButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         goButton = (Button)findViewById(R.id.goButton);



        timerSeekBar = (SeekBar)findViewById(R.id.timerSeekBar);
        timerTextView =(TextView)findViewById(R.id.timerTextView);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);


        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void updateTimer(int secondsLeft)
    {
        int min=secondsLeft/60;
        int sec=secondsLeft-(min*60);


        String seconds=Integer.toString(sec);
        if(sec<=9)
        {
            seconds="0"+seconds;
        }
        timerTextView.setText(Integer.toString(min)+":"+seconds);


    }
    public void buttonClicked(View view)
    {
        if(counterActive)
        {
            resetTimer();
        }
        else
        {
            counterActive=true;
            goButton.setText("stop");
            timerSeekBar.setEnabled(false);

            countDownTimer= new CountDownTimer(timerSeekBar.getProgress()*1000+100,1000)
            {


                @Override
                public void onTick(long l)  {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mp = MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                    mp.start();
                    resetTimer();

                }
            }.start();
        }

    }
    public void resetTimer()
    {
        counterActive=false;
        goButton.setText("go");
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        timerSeekBar.setProgress(30);
        timerTextView.setText("0:30");



    }
}
