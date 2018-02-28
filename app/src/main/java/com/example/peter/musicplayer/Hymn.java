package com.example.peter.musicplayer;

/**
 * Created by Peter on 27/02/2018.
 */

public class Hymn {

    public static String HYMN_NAME = "NAME";
    public static String HYMN_KLAM = "KLAM";
    public static String HYMN_MUSIC = "MUSIC";

    public Hymn(String name, String klam, int music) {
        this.setName(name);
        this.setKlam(klam);
        this.setMusic(music);
    }

    private String name;
    private String klam;
    private int music;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKlam() {
        return klam;
    }

    public void setKlam(String klam) {
        this.klam = klam;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }
}
