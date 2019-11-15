package com.example.json;

import android.content.Context;
import android.util.Log;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.SpinnerAdapter;
import com.example.dto.MercadoDto;
import com.example.estaticas.Valores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CidadeJson extends AppCompatActivity {

    private  ArrayList<MercadoDto> mExampleList = new ArrayList<>();
    private  SpinnerAdapter mSpinnerAdapter;
    private  Context context;
    private  Spinner spinner;
    private  RequestQueue mRequestQueue;


    public CidadeJson(Context context, Spinner spinner){
        this.context = context;
        this.spinner= spinner;
        mRequestQueue = Volley.newRequestQueue(context);
    }



    public  void retornarCidades()
    {
        String url = Valores.URL_CASA+"/Mercado/rest/ws/listarCidades";
        JsonArrayRequest request = new  JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

        @Override
        public void onResponse(JSONArray response) {


            for (int i= 0; i< response.length(); i++)
            {
                try {

                    JSONObject obj = response.getJSONObject(i);
                    MercadoDto mercadoDto = new MercadoDto(obj.getString("cidade"));
                    mExampleList.add(mercadoDto);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

                mSpinnerAdapter = new SpinnerAdapter(context, mExampleList);
                spinner.setAdapter(mSpinnerAdapter);
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();

        }
    });
        mRequestQueue.add(request);
    }


}
