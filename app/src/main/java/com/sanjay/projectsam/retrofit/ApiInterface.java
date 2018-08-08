package com.sanjay.projectsam.retrofit;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/2iodh4vg0eortkl/facts.json")
    Observable<ResponseBody> get();


}
