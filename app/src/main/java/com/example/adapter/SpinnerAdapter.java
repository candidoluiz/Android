package com.example.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dto.MercadoDto;
import com.example.webservice.R;

import java.util.ArrayList;

public class SpinnerAdapter extends BaseAdapter{

    private ArrayList<MercadoDto> mExampleList;
    private Context mContext;

    public SpinnerAdapter(Context mContext,ArrayList<MercadoDto> mExampleList) {
        this.mExampleList = mExampleList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mExampleList.size();
    }

    @Override
    public Object getItem(int i) {
        return mExampleList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(mContext, R.layout.layout_spinner_cidade,null);
        TextView textView =  v.findViewById(R.id.txt_spinner_cidade);
        textView.setText(mExampleList.get(i).getCidade());
        return v;
    }
}
