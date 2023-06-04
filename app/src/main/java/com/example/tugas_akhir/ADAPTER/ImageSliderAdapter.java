package com.example.tugas_akhir.ADAPTER;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.tugas_akhir.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageSliderAdapter  extends SliderViewAdapter<ImageSliderAdapter.SliderAdapterVH> {

    private Context context;
    private ArrayList<String> fotomobil;

    public ImageSliderAdapter(Context c, ArrayList<String> fotomobil){
        this.context = c;
        this.fotomobil = fotomobil;
    }

    public void renewItems(ArrayList<String> fotomobil){
        this.fotomobil = fotomobil;
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image_view_slider ,parent,false);
        return new SliderAdapterVH(v);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
        Picasso.get().load(fotomobil.get(position)).into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        return fotomobil.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.imageMobil);
            this.itemView = itemView;
        }
    }

}
