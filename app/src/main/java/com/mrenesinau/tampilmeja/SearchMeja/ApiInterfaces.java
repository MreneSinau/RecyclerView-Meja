package com.mrenesinau.tampilmeja.SearchMeja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterfaces {

    @GET("getMeja.php")
    Call < List<Meja> > getMeja (@Query("key") String keyword);
}
