package com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase.model.ResponseMovie;
import com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase.model.ResultsItem;
import com.syafiqmarzuki21.msyafiqmarzuki.ad7onlinedatabase.retrofit.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    List<ResultsItem> dataMovie = new ArrayList<>();
    RecyclerView recycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.reyclerView);

        //1.layout per item
        //2.model datanya
        //dummy data
//        MovieModel movie1 = new MovieModel();
//        movie1.setJudulFilm("Judul Film");
//        movie1.setPosterFilm("https://image.tmdb.org/t/p/w185_and_h278_bestv2/5lpBvuPNaPiOpitehczWmJgf6wb.jpg");
//
//        for (int i = 0; i < 20 ; i++) {
//            dataMovie.add(movie1);
//
//        }

        getDataOnline();
        //3.Adapter
        recycler.setAdapter(new MovieAdapter(MainActivity.this, dataMovie));


        //4.Layout Manager
        recycler.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
    }

    private void getDataOnline() {
        final ProgressDialog progress = new ProgressDialog(MainActivity.this);
        progress.setMessage("Wait..");
        progress.show();
        Call<ResponseMovie> request = RetrofitConfig.getApiService().ambilDataMovie("12a64b1c16a7c5582be23414ffb9fed7");
        request.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {
                progress.dismiss();
                if (response.isSuccessful()){
                    dataMovie = response.body().getResults();
                    recycler.setAdapter(new MovieAdapter(MainActivity.this, dataMovie));

                }else{
                    Toast.makeText(MainActivity.this,"Request Gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
            }
        });
    }
}
