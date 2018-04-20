package com.example.anders.createuserapp;

public class Artwork {

    private String Title;
    private String Artist;
    private int Thumbnail;

    //base constructor
    public Artwork(){

    }

    public Artwork(String title, String artist, int thumbnail){
        Title = title;
        Artist = artist;
        Thumbnail = thumbnail;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public int getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        Thumbnail = thumbnail;
    }
}
