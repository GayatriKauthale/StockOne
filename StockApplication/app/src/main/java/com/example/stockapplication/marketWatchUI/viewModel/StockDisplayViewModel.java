package com.example.stockapplication.marketWatchUI.viewModel;

import androidx.lifecycle.MutableLiveData;

import com.example.stockapplication.marketWatchUI.handler.IstockHandler;
import com.example.stockapplication.apiManager.retrofit.RetrofitClient;
import com.example.stockapplication.model.StockModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.stockapplication.constant.Strings.ApiContents.NAB;
import static com.example.stockapplication.constant.Strings.ApiContents.Region;
import static com.example.stockapplication.constant.Strings.ApiContents.apiKeys;
import static com.example.stockapplication.constant.Strings.ApiContents.host;


public class StockDisplayViewModel extends BaseViewModel
{

    public IstockHandler istockHandler;
    public MutableLiveData<StockModel> getMutableLiveData() {
        return mutableLiveData;
    }

    public void setMutableLiveData(MutableLiveData<StockModel> mutableLiveData) {
        this.mutableLiveData = mutableLiveData;
    }

    MutableLiveData<StockModel> mutableLiveData=new MutableLiveData<StockModel>();


    public void hitApigetStockList() {
//
        Call<StockModel> call = RetrofitClient.getInstance().getMyApi().viewStock(NAB, Region,apiKeys,host);
        call.enqueue(new Callback<StockModel>() {
            @Override
            public void onResponse(Call<StockModel> call, Response<StockModel> response) {
                StockModel getList = response.body();

                if (response.isSuccessful())
                {
                    if (getList.getResultSet().getResult().size()>0)
                    {
                        StockModel stockSearchModel=new StockModel();
                        stockSearchModel.setResultSet(getList.getResultSet());
                        mutableLiveData.postValue(stockSearchModel);
//                        istockHandler.onApiSucess();

                    }
                }
            }

            @Override
            public void onFailure(Call<StockModel> call, Throwable t)
            {
                istockHandler.onApiFailure(t.getMessage());

            }
        });

    }



}
