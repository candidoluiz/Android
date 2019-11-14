package com.example.filter;

import android.widget.Filter;

import com.example.adapter.MercadoProdutoAdapter;
import com.example.dto.MercadoProdutoDto;

import java.util.ArrayList;

public class CustomFilter extends Filter {

    MercadoProdutoAdapter mercadoProdutoAdapter;
    ArrayList<MercadoProdutoDto> filterList;

    public CustomFilter(MercadoProdutoAdapter mercadoProdutoAdapter, ArrayList<MercadoProdutoDto> filterList) {
        this.mercadoProdutoAdapter = mercadoProdutoAdapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length()>0)
        {
            constraint = constraint.toString().toUpperCase();
            ArrayList<MercadoProdutoDto> filteredModels = new ArrayList<>();
            for (int i = 0; i< filterList.size(); i++)
            {
                if (filterList.get(i).getProdutoDto().getTipo().toUpperCase().contains(constraint))
                {
                    filteredModels.add(filterList.get(i));
                }

            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mercadoProdutoAdapter.mExampleList = (ArrayList<MercadoProdutoDto>) results.values;
        mercadoProdutoAdapter.notifyDataSetChanged();

    }
}
