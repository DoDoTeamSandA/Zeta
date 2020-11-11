package com.sunny.zeta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.List;

public class CardStackAdapter  extends RecyclerView.Adapter<CardStackAdapter.ViewHolder> {

    List<Pojo> list;
    Context context;



    public CardStackAdapter(Context context,List<Pojo> list){
        this.context=context;
        this.list=list;


    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.stack_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());
        Glide.with(context).load(list.get(position).getImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView name,price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.item_name);
            price=itemView.findViewById(R.id.item_Price);
            imageView=itemView.findViewById(R.id.item_image);
        }
    }
}


