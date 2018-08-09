package com.sanjay.projectsam.retrofit;


import com.sanjay.projectsam.model.Projectsammodel;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("2iodh4vg0eortkl/facts.json")
    Observable<Projectsammodel> Getall();


}
