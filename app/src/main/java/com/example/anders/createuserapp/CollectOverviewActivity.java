package com.example.anders.createuserapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CollectOverviewActivity extends AppCompatActivity {

    List<Artwork> listArtwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect_overview);

        listArtwork = new ArrayList<>();
        listArtwork.add(new Artwork("So Simple", "Michael Kvium", R.drawable.so_simple));
        listArtwork.add(new Artwork("Social Dream Painting", "Michael Kvium", R.drawable.social_dream_painting));
        listArtwork.add(new Artwork("Kulturkreds", "Michael Kvium", R.drawable.kulturkreds));
        listArtwork.add(new Artwork("Naturkreds", "Michael Kvium", R.drawable.naturkreds));
        listArtwork.add(new Artwork("So Simple", "Michael Kvium", R.drawable.so_simple));
        listArtwork.add(new Artwork("Social Dream Painting", "Michael Kvium", R.drawable.social_dream_painting));
        listArtwork.add(new Artwork("Kulturkreds", "Michael Kvium", R.drawable.kulturkreds));
        listArtwork.add(new Artwork("Naturkreds", "Michael Kvium", R.drawable.naturkreds));
        listArtwork.add(new Artwork("So Simple", "Michael Kvium", R.drawable.so_simple));
        listArtwork.add(new Artwork("Social Dream Painting", "Michael Kvium", R.drawable.social_dream_painting));
        listArtwork.add(new Artwork("Kulturkreds", "Michael Kvium", R.drawable.kulturkreds));
        listArtwork.add(new Artwork("Naturkreds", "Michael Kvium", R.drawable.naturkreds));

        RecyclerView myRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, listArtwork);
        myRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        myRecyclerView.setAdapter(myAdapter);

    }
}
