package com.pslyp.viewvideo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView mVideo;
    private RelativeLayout mVideoLayout;
    private RelativeLayout mRelative_2;
    private ImageView mPlay;
    private TextView mTime;

    private boolean layout_state = false;
    private boolean pause_state = false;

    private int duration = 0;

    private long init, now, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();


        String path = "android.resource://" + getPackageName() + "/" + R.raw.video_1;

        mVideo.setVideoPath(path);
//        mVideo.seekTo(60000);
        mVideo.start();

        duration = mVideo.getDuration();

        init = System.currentTimeMillis();

        mTime.setText(String.valueOf(init));
    }

    @Override
    protected void onPause() {
        super.onPause();

        mVideo.pause();

        now = System.currentTimeMillis();
        time = now - init;
    }

    @Override
    protected void onStop() {
        super.onStop();

//        mVideo.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        mVideo.seekTo((int) time);
        mVideo.start();
    }

    private void initInstance() {
        mVideo = findViewById(R.id.video_view_1);
        mVideoLayout = findViewById(R.id.relative_layout_1);
        mRelative_2 = findViewById(R.id.relative_layout_2);
        mPlay = findViewById(R.id.image_view_play);
        mTime = findViewById(R.id.text_view_1);

        mVideoLayout.setOnClickListener(this);
        mPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.relative_layout_1:
                if(!layout_state) {
                    showLayout();
                    layout_state = true;
                } else {
                    hideLayout();
                    layout_state = false;
                }
                break;
            case R.id.image_view_play:
                if(mVideo.isPlaying()) {
                    mPlay.setImageResource(R.drawable.ic_pause_white_24dp);
                    mVideo.pause();

                    now = System.currentTimeMillis();
                    time = now - init;
                    mTime.setText(String.valueOf(time));
                } else {
                    mPlay.setImageResource(R.drawable.ic_play_arrow_white_24dp);
                    mVideo.start();
                }
                break;
        }
    }

    private void showLayout() {
        mRelative_2.setVisibility(View.VISIBLE);
    }

    private void hideLayout() {
        mRelative_2.setVisibility(View.INVISIBLE);
    }
}
