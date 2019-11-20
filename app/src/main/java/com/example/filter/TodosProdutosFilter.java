package com.example.filter;

import android.widget.Filter;

import com.example.adapter.TodosProdutosAdapter;
import com.example.dto.MercadoProdutoDto;

import java.util.ArrayList;

public class TodosProdutosFilter extends Filter {

    TodosProdutosAdapter mTodosProdutosAdapter;
    ArrayList<MercadoProdutoDto> filterList;

    public TodosProdutosFilter(TodosProdutosAdapter mTodosProdutosAdapter, ArrayList<MercadoProdutoDto> filterList) {
        this.filterList = filterList;
        this.mTodosProdutosAdapter = mTodosProdutosAdapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if(constraint != null && constraint.length() > 0)
        {
                constraint = constraint.toString().toUpperCase();
                ArrayList<MercadoProdutoDto> filteredModels = new ArrayList<>();
                for (int i = 0; i < filterList.size(); i++) {
                    if (filterList.get(i).getProdutoDto().getTipo().toUpperCase().contains(constraint)) {
                        filteredModels.add(filterList.get(i));
                    }

                }
                results.count = filteredModels.size();
                results.values = filteredModels;
            } else {
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }


    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        mTodosProdutosAdapter.mExampleList=(ArrayList<MercadoProdutoDto>) results.values;
        mTodosProdutosAdapter.notifyDataSetChanged();
    }
}
