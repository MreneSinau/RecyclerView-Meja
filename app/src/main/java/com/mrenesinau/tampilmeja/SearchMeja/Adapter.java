package com.mrenesinau.tampilmeja.SearchMeja;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mrenesinau.tampilmeja.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Meja> meja;
    private Context context;

    public Adapter(List<Meja> meja, Context context) {
        this.meja = meja;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_alat, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.statusmeja.setText(meja.get(position).getNama_status_meja());
        holder.namameja.setText(meja.get(position).getNmmeja());
//        holder.idmeja.setText(meja.get(position).getIdmeja());
        holder.modelmeja.setText(meja.get(position).getModel());
//        holder.gambarmeja.setImageResource(R.drawable.meja);

        //memrubah warna tek berdasarkan apa
        if (meja.get(position).getNama_status_meja().equals("Kosong")) {
            holder.statusmeja.setTextColor(Color.GREEN);
        } else if (meja.get(position).getNama_status_meja().equals("Dipesan")) {
            holder.statusmeja.setTextColor(Color.RED);
        } else if (meja.get(position).getNama_status_meja().equals("Terisi")) {
            holder.statusmeja.setTextColor(Color.BLUE);
//            Picasso.with(context).load(meja.get(position).getAndroid_image_url()).resize(130, 190).into(holder.gambarmeja);
        }


        // fungsi onclicklistener pada cardview
        holder.cardviewMenja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, meja.get(position).getNama_status_meja(), Toast.LENGTH_SHORT).show();
            }
        });

//        holder.tvSubtitle.setText("Frau " + position);

    }

    @Override
    public int getItemCount() {
        return meja.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView statusmeja, namameja, idmeja, modelmeja;
        ImageView gambarmeja;
        CardView cardviewMenja;

        public MyViewHolder(View itemView) {
            super(itemView);
            statusmeja = itemView.findViewById(R.id.statusmeja);
            namameja = itemView.findViewById(R.id.namameja);
            idmeja = itemView.findViewById(R.id.idmeja);
            modelmeja = itemView.findViewById(R.id.modelmeja);
            gambarmeja = itemView.findViewById(R.id.gambarmeja);
            cardviewMenja = itemView.findViewById(R.id.cardviewMenja);

        }
    }
}
