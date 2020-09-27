package com.example.mediaapplication;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView photo;
    private FrameLayout videoLayout;
    private VideoView video;
    private LinearLayout audioLayout;
    private SeekBar volumeSeekBar, scrubber;
    private TextView elapsedTimeLabel, remainingTimeLabel;
    private Button btnShowPhoto, btnPlayVideo, btnPlayAudio;

    private MediaController mediaController;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clicked();
    }

    private void clicked() {
        btnShowPhoto.setOnClickListener(this);
        btnPlayVideo.setOnClickListener(this);
        btnPlayAudio.setOnClickListener(this);
    }

    private void init() {
        photo = findViewById(R.id.photo);
        videoLayout = findViewById(R.id.videoLayout);
        video = findViewById(R.id.video);
        audioLayout = findViewById(R.id.audioLayout);
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        scrubber = findViewById(R.id.scrubber);
        elapsedTimeLabel = findViewById(R.id.elapsedTimed);
        remainingTimeLabel = findViewById(R.id.remainingTime);
        btnShowPhoto = findViewById(R.id.btnShowPhoto);
        btnPlayVideo = findViewById(R.id.btnPlayVideo);
        btnPlayAudio = findViewById(R.id.btnPlayAudio);

        setImage();
        setVideo();
        setAudio();
    }

    private void setAudio() {
        mediaPlayer = MediaPlayer.create(this, R.raw.cat_sound);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        volumeSeekBar.setMax(maxVolume);
        volumeSeekBar.setProgress(currentVolume);

        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        scrubber.setMax(mediaPlayer.getDuration());

        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null) {
                    try {
                        Message message = new Message();
                        message.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            int currentPosition = msg.what;
            scrubber.setProgress(currentPosition);

            String elapsedTime = createTimeLabel(currentPosition);
            elapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(mediaPlayer.getDuration() - currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);

            Log.d("asd", String.valueOf(mediaPlayer.getDuration()));
            Log.d("asd", String.valueOf(currentPosition));

            if (mediaPlayer.getDuration() <= currentPosition) {
                scrubber.setProgress(0);
                mediaPlayer.seekTo(0);
                mediaPlayer.stop();
            }
        }
    };

    private String createTimeLabel(int currentPosition) {
        String timeLabel = "";
        int min = currentPosition / 1000 / 60;
        int sec = currentPosition / 1000 % 60;

        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }

    private void setVideo() {
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.cat_video;
        Uri videoUri = Uri.parse(videoPath);
        video.setVideoURI(videoUri);
        mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);
    }

    private void setImage() {
        photo.setImageResource(R.drawable.cat_pict);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnShowPhoto:
                showPhoto(true);
                playVideo(false);
                playAudio(false);
                return;
            case R.id.btnPlayVideo:
                showPhoto(false);
                playVideo(true);
                playAudio(false);
                return;
            case R.id.btnPlayAudio:
                showPhoto(false);
                playVideo(false);
                playAudio(true);
                return;
        }
    }

    private void playAudio(boolean b) {
        if (b) {
            audioLayout.animate().alpha(1f).setDuration(500);
            mediaPlayer = MediaPlayer.create(this, R.raw.cat_sound);
            mediaPlayer.start();
        } else {
            mediaPlayer.stop();
            audioLayout.animate().alpha(0f).setDuration(500);
        }
    }

    private void playVideo(boolean b) {
        if (b) {
            videoLayout.animate().alpha(1f).setDuration(500);
            video.start();
            video.resume();
        } else {
            videoLayout.animate().alpha(0f).setDuration(500);
            video.suspend();
        }
    }

    private void showPhoto(boolean b) {
        if (b) {
            photo.animate().alpha(1f).setDuration(500);
        } else {
            photo.animate().alpha(0f).setDuration(500);
        }
    }
}