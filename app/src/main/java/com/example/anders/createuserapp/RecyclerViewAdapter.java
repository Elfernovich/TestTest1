package com.example.anders.createuserapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;

import org.w3c.dom.Text;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Artwork> mData;
    private boolean checked_artwork;
    private String userID;


    public RecyclerViewAdapter(Context mContext, List<Artwork> mData){
        this.mContext = mContext;
        this.mData = mData;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_artwork_collect_overview, parent, false);

        return new MyViewHolder(view);
    }

    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.textView_Artwork_Title.setText(mData.get(position).getTitle());
        holder.img_Artwork_Thumbnail.setImageResource(mData.get(position).getThumbnail());
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(mContext, CollectArtworkActivity.class);

                //passing data to CollectActivity
                intent.putExtra("ARTWORK_TITLE", mData.get(position).getTitle());
                intent.putExtra("ARTWORK_ARTIST", mData.get(position).getArtist());
                intent.putExtra("THUMBNAIL", mData.get(position).getThumbnail());
                intent.putExtra("ID", mData.get(position).getID());


                //start activity

                mContext.startActivity(intent);


        }
        });

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView_Artwork_Title;
        ImageView img_Artwork_Thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView){
            super(itemView);

            textView_Artwork_Title = (TextView) itemView.findViewById(R.id.artwork_title_id);
            img_Artwork_Thumbnail = (ImageView) itemView.findViewById(R.id.artwork_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
