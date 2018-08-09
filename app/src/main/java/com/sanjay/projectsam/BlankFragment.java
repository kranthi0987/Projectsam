package com.sanjay.projectsam;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sanjay.projectsam.adapter.RecyclerAdapter;
import com.sanjay.projectsam.model.Projectsammodel;
import com.sanjay.projectsam.model.Row;
import com.sanjay.projectsam.retrofit.ApiClient;
import com.sanjay.projectsam.retrofit.ApiInterface;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class BlankFragment extends Fragment {

    public static ApiInterface apiInterface;
    RecyclerView listrecyclerview;
    List<Row> list;
    private RecyclerAdapter mAdapter;
    Row productModel = null;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        logincall();
//        return inflater.inflate(R.layout.fragment_blank, container, false);
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
//
        RecyclerView listrecyclerview = rootView.findViewById(R.id.listrecycle);
        listrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        System.out.println("onCreateView: " + list);
        listrecyclerview.setItemAnimator(new DefaultItemAnimator());
//        listrecyclerview.setAdapter((RecyclerView.Adapter) list);
        return rootView;

    }

    public void logincall() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Its loading....");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        Observable<Projectsammodel> observable = apiInterface.Getall()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<Projectsammodel>() {
            @Override
            public void onError(Throwable e) {
                Toast.makeText(getContext(), "error" + e, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onComplete() {
                progressDoalog.hide();
                Log.d("", "onNext:");
                Toast.makeText(getContext(), "Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Projectsammodel Listdata) {


                Projectsammodel ListModel = new Projectsammodel();
                ListModel.setTitle(Listdata.getTitle());

                Log.d("", "onNext:" + Listdata.getTitle());
                Toast.makeText(getContext(), "kkk" + Listdata.getRows(), Toast.LENGTH_LONG).show();

                System.out.println(Listdata.getRows());
                list = Listdata.getRows();
//                listrecyclerview.setAdapter(new RecyclerAdapter(list));
                for (int i = 0; i < list.size(); i++) {
                    productModel = list.get(i);
                    System.out.println(productModel.getImageHref());
                    System.out.println(productModel.getTitle());
                    System.out.println(productModel.getDescription());
                    String title = productModel.getTitle();
                    String description = productModel.getDescription();
                    Object image = productModel.getImageHref();
                    productModel.setTitle(title);
                    productModel.setDescription(description);
                    productModel.setImageHref(image);
                    list.add(productModel);
                }
//                mAdapter = new RecyclerAdapter(list, getActivity());
                listrecyclerview.setAdapter((RecyclerView.Adapter) list);
//                listrecyclerview.setAdapter(new RecyclerAdapter(list));
//                RecyclerView listrecyclerview = (RecyclerView) getActivity().findViewById(R.id.listrecycle);
//                listrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
//                listrecyclerview.setAdapter(new RecyclerAdapter(list));
//                    productModel.setImageurl(products.get(i).getImageurl());
//                    productModel.setColor(products.get(i).getColor());
//                    productModel.setPrice(products.get(i).getPrice());
//                    productModel.setInstock(products.get(i).getInstock());
//                    list.add(productModel);
//                }
//
//                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,ImageLoader.getInstance());
//                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,2);
//                recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
//                recyclerView.setLayoutManager(recyce);
//                recyclerView.setItemAnimator( new DefaultItemAnimator());
//                recyclerView.setAdapter(recyclerAdapter);

            }
        });
//        projectcall.enqueue(new Callback<Projectsammodel>() {
//            @Override
//            public void onResponse(Observable<Projectsammodel> call, Response<Projectsammodel> response) {
//                progressDoalog.dismiss();
//                Toast.makeText(getContext(),
//                        "Internet permission granted", 3000).show();
//                Toast.makeText(getContext(),
//                        new Gson().toJson(response.body()), 3000).show();
//                Log.e("Success", new Gson().toJson(response.body()));
//
//            }
//
//            @Override
//            public void onFailure(Observable<Projectsammodel> call, Throwable t) {
//                progressDoalog.dismiss();
//                Log.e("", "onResponse:" + t);
//            }
//        });
    }


}
