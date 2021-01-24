package com.example.stockapplication.marketWatchUI.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockapplication.R;
import com.example.stockapplication.model.StockModel;

import java.util.List;

public class MarketWatcheRecyclerViewAdapter extends RecyclerView.Adapter<MarketWatcheRecyclerViewAdapter.MyViewHolder>
{
    private Context activity;
    private  List<StockModel> viewStockList;
    private  List<StockModel> viewStockListAllData;
    public MarketWatcheRecyclerViewAdapter(FragmentActivity activity, List<StockModel> viewStockList)
    {
        this.activity=activity;
        this.viewStockList=viewStockList;
        this.viewStockListAllData=viewStockList;
    }

    @NonNull
    @Override
    public MarketWatcheRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_display_stock, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MarketWatcheRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textExchangeName.setText(viewStockList.get(0).getResultSet().getResult().get(position).getExch());
        holder.expDisp.setText(viewStockList.get(0).getResultSet().getResult().get(position).getExchDisp());
        holder.name.setText(viewStockList.get(0).getResultSet().getResult().get(position).getName());
        holder.symbol.setText(viewStockList.get(0).getResultSet().getResult().get(position).getSymbol());
    }

    @Override
    public int getItemCount() {
        if(viewStockList.size()>0)
        {
            return viewStockList.get(0).getResultSet().getResult().size();
        }
        else
        {
            return 0;

        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView textExchangeName,expDisp,name,symbol;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textExchangeName=itemView.findViewById(R.id.textExchangeName);
            expDisp=itemView.findViewById(R.id.expDisp);
            name=itemView.findViewById(R.id.name);
            symbol=itemView.findViewById(R.id.symbol);
        }
    }

}
