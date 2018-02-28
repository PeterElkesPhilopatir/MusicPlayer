package com.example.peter.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment implements AdapterView.OnItemClickListener {


    MediaPlayer mediaPlayer;
    List<Hymn> hymns;
    View rootView;
    List<String> mNames;
    ListView hymnsListView;

    public MainFragment() {
        // Required empty public constructor
    }

    public interface Callback {
        void OnItemSelected(Hymn hymn);
        void homeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        hymnsListView = (ListView) rootView.findViewById(R.id.listVIEW);
        hymnsListView.setOnItemClickListener(this);
        return rootView;
    }


    @Override
    public void onStart() {
        load();

        Log.d("SIZE", "size =  " + mNames.size());

        super.onStart();
    }

    public void load() {
        hymns = new ArrayList<>();
        hymns.add(new Hymn("اشكال الكنيسة", "klam", R.raw.one));
        hymns.add(new Hymn("الشورية", "klam", R.raw.two));
        hymns.add(new Hymn("الشمعة", "klam", R.raw.three));
        hymns.add(new Hymn("الكتب", "klam", R.raw.four));
        hymns.add(new Hymn("الهيكل", "klam", R.raw.five));
        hymns.add(new Hymn("الاسرار", "klam", R.raw.six));
        hymns.add(new Hymn("ما اجملك", "klam", R.raw.seven));

        mNames = new ArrayList<>();
        for (Hymn hymn : hymns) {
            mNames.add(hymn.getName());
        }

        hymnsListView.setAdapter(new ArrayAdapter<String>(getActivity(),
                R.layout.row,
                R.id.textView,
                mNames));
    }

    public void msg(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Hymn hymn = hymns.get(position);
        getActivity().setTitle(hymn.getName());
        ((Callback) getActivity()).OnItemSelected(hymn);
    }
}
