package com.mrenesinau.tampilmeja;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mrenesinau.tampilmeja.SearchMeja.Adapter;
import com.mrenesinau.tampilmeja.SearchMeja.ApiClient;
import com.mrenesinau.tampilmeja.SearchMeja.ApiInterfaces;
import com.mrenesinau.tampilmeja.SearchMeja.Meja;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_semua_meja extends Fragment {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    List<Meja> meja;
    Adapter adapter;
    ApiInterfaces apiInterfaces;
    ProgressBar progressBar;

    ImageView gambarmeja;


    public Fragment_semua_meja() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentview = inflater.inflate(R.layout.fragment_semua_meja, container, false);

//        gambarmeja = (ImageView)fragmentview.findViewById(R.id.gambarmeja);
//        gambarmeja.setImageResource(R.drawable.meja);

        progressBar = fragmentview.findViewById(R.id.progress);

        recyclerView = fragmentview.findViewById(R.id.recycler);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));

//
//        layoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setHasFixedSize(true);

        fetchAlat("");

        return fragmentview;
    }

    private void fetchAlat(String key) {


        apiInterfaces = ApiClient.getApiClient().create(ApiInterfaces.class);
        Call<List<Meja>> call = apiInterfaces.getMeja(key);

        call.enqueue(new Callback<List<Meja>>() {
            @Override
            public void onResponse(Call<List<Meja>> call, Response<List<Meja>> response) {
                progressBar.setVisibility(View.GONE);
                meja = response.body();
                adapter = new Adapter(meja, getActivity());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Meja>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Error : " + t.toString(), Toast.LENGTH_SHORT);

            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu, menu);
//
//        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
//        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
//
//        searchView.setSearchableInfo(
//                searchManager.getSearchableInfo(getComponentName())
//        );
//
//        searchView.setIconifiedByDefault(false);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                fetchAlat(query);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                fetchAlat(newText);
//                return false;
//            }
//        });
//
//        return true;
//    }

}
