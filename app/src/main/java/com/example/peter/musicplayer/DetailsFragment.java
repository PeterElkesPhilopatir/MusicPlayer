package com.example.peter.musicplayer;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;


public class DetailsFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, Runnable {

    View rootView;
    Bundle arg;
    Intent intent;
    MediaPlayer mediaPlayer;
    SeekBar seekBar;
    ToggleButton playBtn;


    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        arg = args;
    }


    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_details, container, false);
        intent = getActivity().getIntent();
        if (arg != null) {
            final Hymn hymn = new Hymn("", "", -1);
            hymn.setName(arg.getString(Hymn.HYMN_NAME));
            hymn.setKlam(arg.getString(Hymn.HYMN_KLAM));
            hymn.setMusic(Integer.valueOf(arg.getString(Hymn.HYMN_MUSIC)));


            msg(hymn.getName());
            mediaPlayer = MediaPlayer.create(getContext(), hymn.getMusic());
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            int mFileDuration = mediaPlayer.getDuration();

            seekBar = (SeekBar) rootView.findViewById(R.id.seekBar);
            seekBar.setMax(mFileDuration);
            seekBar.setOnSeekBarChangeListener(this);
            playBtn = (ToggleButton) rootView.findViewById(R.id.playBtn);

            playBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked) {
//                        msg("checked");
//                        mediaPlayer.seekTo(100000);
//                        seekBar.setProgress(100000);
                        new Sync().execute();
                        mediaPlayer.start();

                    } else {
//                        msg("unchecked");
                        mediaPlayer.pause();
                        mediaPlayer = MediaPlayer.create(getContext(), hymn.getMusic());

                    }
                }
            });

        }
        return rootView;
    }

    public void msg(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        mediaPlayer.seekTo(progress);
//        seekBar.setProgress(progress);


//        int mCurrentPosition = mediaPlayer.getCurrentPosition();
//        seekBar.setProgress(mCurrentPosition);

//        msg(String.valueOf(progress) + "___" + mCurrentPosition);

//        msg(String.valueOf(progress));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public void run() {
        int currentPosition = mediaPlayer.getCurrentPosition();
        int total = mediaPlayer.getDuration();

        msg("total" + total);

        while (mediaPlayer != null && currentPosition < total) {
            try {
                Thread.sleep(1/1000);
                msg("sleep");
                currentPosition = mediaPlayer.getCurrentPosition();
            } catch (InterruptedException e) {
                return;
            } catch (Exception e) {
                return;
            }
            seekBar.setProgress(currentPosition);
        }
    }

    @Override
    public void onDestroy() {
        mediaPlayer.start();
        mediaPlayer = null;
        ((MainFragment.Callback) getActivity()).homeFragment();
        super.onDestroy();


    }

    private class Sync extends AsyncTask<Void, Void, Void>  // UI thread
    {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {

            while (true) {
                int currentPosition = mediaPlayer.getCurrentPosition();
                int total = mediaPlayer.getDuration();
                while (mediaPlayer != null && currentPosition < total) {
                    try {
                        Thread.sleep(1000);
                        msg("sleep");
                        currentPosition = mediaPlayer.getCurrentPosition();
                    } catch (InterruptedException e) {
                        msg("BREAK");
                        break;
                    } catch (Exception e) {
                        msg("BREAK");
                        break;
                    }
                    seekBar.setProgress(currentPosition);
                }
            }
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);


        }
    }
}
