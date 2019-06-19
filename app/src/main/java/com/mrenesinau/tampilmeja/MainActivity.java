package com.mrenesinau.tampilmeja;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mrenesinau.tampilmeja.SearchMeja.Adapter;
import com.mrenesinau.tampilmeja.SearchMeja.Meja;
import com.mrenesinau.tampilmeja.SearchMeja.ApiClient;
import com.mrenesinau.tampilmeja.SearchMeja.ApiInterfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Meja> meja;
    Adapter adapter;
    ApiInterfaces apiInterfaces;
    ProgressBar progressBar;

    ImageView gambarmeja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);

//        gambarmeja = (ImageView)findViewById(R.id.gambarmeja);
//        gambarmeja.setImageResource(R.drawable.ic_launcher_background);

        recyclerView = findViewById(R.id.recycler);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));

//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);



        fetchAlat("");
    }

    private void fetchAlat(String key) {
        apiInterfaces = ApiClient.getApiClient().create(ApiInterfaces.class);
        Call<List<Meja>> call = apiInterfaces.getMeja(key);

        call.enqueue(new Callback<List<Meja>>() {
            @Override
            public void onResponse(Call<List<Meja>> call, Response<List<Meja>> response) {
                progressBar.setVisibility(View.GONE);
                meja = response.body();
                adapter = new Adapter(meja, MainActivity.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Meja>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error : " + t.toString(), Toast.LENGTH_SHORT);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchAlat(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchAlat(newText);
                return false;
            }
        });

        return true;
    }
}
