package com.example.stockapplication.marketWatchUI.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stockapplication.R;
import com.example.stockapplication.marketWatchUI.adapter.MarketWatcheRecyclerViewAdapter;
import com.example.stockapplication.databinding.FragmentMarcketWatchBinding;
import com.example.stockapplication.marketWatchUI.handler.IstockHandler;
import com.example.stockapplication.model.Result;
import com.example.stockapplication.model.ResultSet;
import com.example.stockapplication.model.StockModel;
import com.example.stockapplication.marketWatchUI.viewModel.StockDisplayViewModel;

import java.util.ArrayList;
import java.util.List;

import static java.util.Locale.filter;

public class MarcketWatchFragment extends Fragment implements IstockHandler {
     FragmentMarcketWatchBinding mBinding;
    private MarketWatcheRecyclerViewAdapter recyclerAdapter;
    private List<StockModel> viewStockList = new ArrayList<>();
    private List<StockModel> filterviewStockList = new ArrayList<>();
    private StockDisplayViewModel stockDisplayViewModel;

    public MarcketWatchFragment() {
        // Required empty public constructor;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_marcket_watch, container, false);

        View view = mBinding.getRoot();
        initViewModel();
        //get live data from view model
       mBinding.progressBar.setVisibility(View.VISIBLE);

        stockDisplayViewModel.hitApigetStockList();
        stockDisplayViewModel.getMutableLiveData().observe(getActivity(), new Observer<StockModel>() {
            @Override
            public void onChanged(StockModel stockSearchModel) {
                mBinding.progressBar.setVisibility(View.GONE);
                StockModel stockModel=new StockModel();
                stockModel.setResultSet(stockSearchModel.getResultSet());
                viewStockList.clear();
                viewStockList.add(stockSearchModel);
                setRecyclerView(viewStockList);
            }
        });


     //added textwatcher for searching on typing
        mBinding.searchByName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                if(charSequence.toString().trim().isEmpty())
                {
                    setRecyclerView(viewStockList);

                }

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().trim().isEmpty())
                {
                    setRecyclerView(viewStockList);

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().trim().isEmpty())
                {
                    setRecyclerView(viewStockList);

                }
            else
                {
                    if(editable.toString()!=null)
                    {
                        filter(editable.toString());

                    }

                }

            }
        });


        return view;
    }


//initialisation of view model
    private void initViewModel() {
        //Init viewModel
        stockDisplayViewModel = ViewModelProviders.of(getActivity()).get(StockDisplayViewModel.class);
    }
//pass arraylist to the adapter
    private void setRecyclerView(List<StockModel> filterviewStockList) {
        recyclerAdapter = new MarketWatcheRecyclerViewAdapter(getActivity(), filterviewStockList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.RvFeedback.setLayoutManager(linearLayoutManager);
        mBinding.RvFeedback.setAdapter(recyclerAdapter);

    }


    //FilterLogicForStockName

    public void filter(String name)
    {
        String filterPattern = name.toString().toUpperCase().trim();
        filterviewStockList.clear();
        List<Result> resultSets= new ArrayList<>();
        StockModel stockSearchModels=new StockModel();

        for (int i=0;i<viewStockList.get(0).getResultSet().getResult().size();i++) {
            if (viewStockList.get(0).getResultSet().getResult().get(i).getExch().contains(filterPattern))
            {
                Result result=new Result();
                result.setName(viewStockList.get(0).getResultSet().getResult().get(i).getName());
                result.setExch(viewStockList.get(0).getResultSet().getResult().get(i).getExch());
                result.setExchDisp(viewStockList.get(0).getResultSet().getResult().get(i).getExchDisp());
                result.setSymbol(viewStockList.get(0).getResultSet().getResult().get(i).getSymbol());
                result.setType(viewStockList.get(0).getResultSet().getResult().get(i).getType());
                result.setTypeDisp(viewStockList.get(0).getResultSet().getResult().get(i).getTypeDisp());
                ResultSet resultSet=new ResultSet();
                resultSets.add(result);
                resultSet.setResult(resultSets);
                stockSearchModels.setResultSet(resultSet);
                filterviewStockList.add(stockSearchModels);

            }

        }

        setRecyclerView(filterviewStockList);
    }


    @Override
    public void onApiFailure(String message) {
        mBinding.progressBar.setVisibility(View.GONE);

        Toast.makeText(getContext(), ""+message, Toast.LENGTH_SHORT).show();
    }


}