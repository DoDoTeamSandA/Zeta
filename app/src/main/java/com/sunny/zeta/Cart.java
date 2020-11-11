package com.sunny.zeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Cart extends AppCompatActivity {

    RecyclerView cartrecycle;
    List<Pojo> alist;
    MainActivity act;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        alist=new LinkedList<>();


         act=new MainActivity();

        cartrecycle=findViewById(R.id.Cart_Recycle);

        alist.addAll(act.cartQueue.getValue());

        CartAdapter adapter=new CartAdapter(this,alist);
        cartrecycle.setAdapter(adapter);








    }




}