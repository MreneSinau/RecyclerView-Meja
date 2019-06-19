package com.mrenesinau.tampilmeja.SearchMeja;


import com.google.gson.annotations.SerializedName;

public class Meja {

    @SerializedName("idmeja") private int idmeja;
    @SerializedName("nmmeja") private String nmmeja;
    @SerializedName("model") private String model;
    @SerializedName("nama_status_meja") private String nama_status_meja;

    public int getIdmeja() {
        return idmeja;
    }

    public void setIdmeja(int idmeja) {
        this.idmeja = idmeja;
    }

    public String getNmmeja() {
        return nmmeja;
    }

    public void setNmmeja(String nmmeja) {
        this.nmmeja = nmmeja;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getNama_status_meja() {
        return nama_status_meja;
    }

    public void setNama_status_meja(String nama_status_meja) {
        this.nama_status_meja = nama_status_meja;
    }
}
