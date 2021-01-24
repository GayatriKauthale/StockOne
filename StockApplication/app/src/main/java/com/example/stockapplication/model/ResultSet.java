package com.example.stockapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.stockapplication.model.Result;

import java.util.List;

public class ResultSet {

    @SerializedName("Query")
    @Expose
    private String query;
    @SerializedName("Result")
    @Expose
    private List<Result> result = null;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

}
