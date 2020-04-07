package com.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Album {

    private String albumName;
    private List<Song> songsInAlbum = new LinkedList<>();

    public Album(String albumName) {
        this.albumName = albumName;
//        this.songsInAlbum = new LinkedList<>();
    }

    public String getAlbumName() {
        return albumName;
    }


    public List<Song> getSongsInAlbum() {
        return songsInAlbum;
    }

    // add song
    public void addSong(Song song) {
        this.songsInAlbum.add(song);
    }


    // find song
    public Song findSong(String songName) {
        for(Song x: songsInAlbum) {
            if(x.getSongName().equals(songName)) {
                return x;
            }
        }
        return null;
    }

    // get songs in album
    public void showIt() {
        System.out.println("--- Album '" + this.albumName + "' ---");
        for(int i=0; i<this.songsInAlbum.size(); i++) {
            System.out.print(this.songsInAlbum.get(i).getSongName() + ": ");
            System.out.print(this.songsInAlbum.get(i).getDuration() + " min");
            System.out.println();
        }
    }

    @Override
    public String toString() {
        return "Album: " + this.albumName;// +
             //   "Songs in album: " + this.songsInAlbum;

    }
}
