package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.MercadoProdutoAdapter;
import com.example.dto.MercadoProdutoDto;
import com.example.dto.ProdutoDto;
import com.example.estaticas.Valores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProdutoActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private MercadoProdutoAdapter mMercadoProdutoAdapter;
    private ArrayList<MercadoProdutoDto> mExampleList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        mRecyclerView = findViewById(R.id.recycle_produto);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON()
    {
        String url="";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String url2= Valores.URL+"";

                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        MercadoProdutoDto mercadoProdutoDto = new MercadoProdutoDto();
                        ProdutoDto produtoDto = new ProdutoDto();

                        String foto = obj.getString("foto").substring(1);
                        foto = url2+foto;
                        String tipo = obj.getString("tipo");
                        produtoDto.setTipo(tipo);
                        mercadoProdutoDto.setProdutoDto(produtoDto);
                        mExampleList.add(mercadoProdutoDto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
    }
}
