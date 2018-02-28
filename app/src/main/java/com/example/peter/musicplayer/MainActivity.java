package com.example.peter.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainFragment.Callback {
    MediaPlayer mediaPlayer;
    List<Hymn> hymns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.mainActivityId, new MainFragment()).commit();

    }

    public void msg(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnItemSelected(Hymn hymn) {
        Bundle bundle = new Bundle();

        bundle.putString(Hymn.HYMN_NAME, hymn.getName());
        bundle.putString(Hymn.HYMN_KLAM, hymn.getKlam());
        bundle.putString(Hymn.HYMN_MUSIC, String.valueOf(hymn.getMusic()));

        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityId, detailsFragment).commit();
    }

    @Override
    public void homeFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.mainActivityId, new MainFragment()).commit();
    }
}
