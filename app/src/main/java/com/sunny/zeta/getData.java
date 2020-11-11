package com.sunny.zeta;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface getData {
    @GET("reciped9d7b8c.json")
    Call<List<Pojo>> loadChanges();

}
