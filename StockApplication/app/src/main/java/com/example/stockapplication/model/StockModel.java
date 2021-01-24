package com.example.stockapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockModel
{
        @SerializedName("ResultSet")
        @Expose
        private ResultSet resultSet;

        public ResultSet getResultSet() {
            return resultSet;
        }

        public void setResultSet(ResultSet resultSet) {
            this.resultSet = resultSet;
        }

    }
