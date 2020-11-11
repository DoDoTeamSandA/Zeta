package com.sunny.zeta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DefaultItemAnimator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    MutableLiveData<List<Pojo>> list;
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    public MutableLiveData<List<Pojo>> cartQueue;
    List<Pojo> queue;
    private MutableLiveData<Integer> pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getServerData();

        CardStackView cardStackView=findViewById(R.id.card_stack_view);
        list=new MutableLiveData<>();
        cartQueue=new MutableLiveData<>();
        pos=new MutableLiveData<>();

        queue=new LinkedList<>();


        findViewById(R.id.Cartbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,Cart.class));
            }
        });



        manager=new CardStackLayoutManager(this, new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                if (direction == Direction.Right){
                    Toast.makeText(MainActivity.this, "Direction Right"+pos.getValue(), Toast.LENGTH_SHORT).show();

                    queue.add(list.getValue().get(pos.getValue()));
                    cartQueue.setValue(queue);
                }
                if (direction == Direction.Top){
                    Toast.makeText(MainActivity.this, "Direction Top", Toast.LENGTH_SHORT).show();
                }
                if (direction == Direction.Left){






                }
                if (direction == Direction.Bottom){
                    Toast.makeText(MainActivity.this, "Direction Bottom", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCardSwiped(Direction direction) {

            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {


                pos.setValue(position);


            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        manager.setCanScrollVertical(false);

        cardStackView.setLayoutManager(manager);
        list.observeForever(new Observer<List<Pojo>>() {
            @Override
            public void onChanged(List<Pojo> list) {
                adapter = new CardStackAdapter(MainActivity.this,list);
                cardStackView.setAdapter(adapter);

            }
        });

        cardStackView.setItemAnimator(new DefaultItemAnimator());


    }

  public void  getServerData(){
       final String BASE_URL = "https://s3-ap-southeast-1.amazonaws.com/he-public-data/";

      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();

      getData getData=retrofit.create(com.sunny.zeta.getData.class);

      Call<List<Pojo>> call = getData.loadChanges();

      call.enqueue(new Callback<List<Pojo>>() {
          @Override
          public void onResponse(Call<List<Pojo>> call, Response<List<Pojo>> response) {
              list.setValue(response.body());

              Log.i("TAG", "onResponse:"+response.message());
          }

          @Override
          public void onFailure(Call<List<Pojo>> call, Throwable t) {

          }
      });




  }
}