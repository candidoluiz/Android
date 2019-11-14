package com.example.webservice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.MercadoProdutoAdapter;
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
    private int mercadoId = -1;
    private String url = Valores.URL_CASA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);

        Intent intent =getIntent();
        String cod = intent.getStringExtra("cod");

        if(cod.equals("2"))
        {
            String nome = intent.getStringExtra("nome").toUpperCase();
            mercadoId = intent.getIntExtra("mercadoId",0);
            url = url+"/Mercado/rest/ws/listarProdutoDeMercado/"+mercadoId;
            Log.v("URL",url);

            setTitle(nome);

            mRecyclerView = findViewById(R.id.recycle_produto);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            mExampleList = new ArrayList<>();
            mRequestQueue = Volley.newRequestQueue(this);

            carregarProdutoMercado();
        }else{
            setTitle("LISTA DE PRODUTOS");

            url = url+"/Mercado/rest/ws/listarProdutos/";

            mRecyclerView = findViewById(R.id.recycle_produto);
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            mExampleList = new ArrayList<>();
            mRequestQueue = Volley.newRequestQueue(this);

            parseJSON();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchItem = menu.findItem(R.id.search);/*
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });*/
        return true;
    }

    private void carregarProdutoMercado()
    {
        //String url=Valores.URL_PRODUTO+"/Mercado/rest/ws/listarProdutos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String url2= Valores.URL_CASA+"/Mercado";


                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        MercadoProdutoDto mercadoProdutoDto = new MercadoProdutoDto();
                        ProdutoDto produtoDto = new ProdutoDto();

                        //Log.v("TESTE-JSONS",obj.getJSONObject("produtoDto").getString("foto"));


                        //String foto = obj.getString("foto").substring(1);
                        //foto = url2+foto;

                        String foto = obj.getJSONObject("produtoDto").getString("foto").substring(1);
                        foto = url2+foto;

                        Log.v("TESTE-JSONS",foto);
                        String tipo = obj.getJSONObject("produtoDto").getString("tipo");
                        String medida = obj.getJSONObject("produtoDto").getString("medida");
                        String unMedida = obj.getJSONObject("produtoDto").getString("unMedida");
                        String marca = obj.getJSONObject("produtoDto").getString("marca");
                        double preco = Double.parseDouble(obj.getString("preco"));



                        produtoDto.setFoto(foto);
                        produtoDto.setTipo(tipo);
                        produtoDto.setMarca(marca);
                        produtoDto.setMedida(medida);
                        produtoDto.setUnMedida(unMedida);
                        mercadoProdutoDto.setPreco(preco);
                        mercadoProdutoDto.setProdutoDto(produtoDto);
                        mExampleList.add(mercadoProdutoDto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mMercadoProdutoAdapter = new MercadoProdutoAdapter(ProdutoActivity.this,mExampleList);
                mRecyclerView.setAdapter(mMercadoProdutoAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }


    private void parseJSON()
    {
        //String url=Valores.URL_PRODUTO+"/Mercado/rest/ws/listarProdutos";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET,url,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String url2= Valores.URL+"/Mercado";


                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        MercadoProdutoDto mercadoProdutoDto = new MercadoProdutoDto();
                        ProdutoDto produtoDto = new ProdutoDto();

                        Log.v("TESTE-JSONS",obj.getJSONObject("produtoDto").getString("foto"));


                        //String foto = obj.getString("foto").substring(1);
                        //foto = url2+foto;

                        String foto = obj.getString("foto");
                        String tipo = obj.getString("tipo");
                        String medida = obj.getString("medida");
                        String unMedida = obj.getString("unMedida");
                        String marca = obj.getString("marca");
                        double preco = Double.parseDouble(obj.getString("preco"));
                        String foto2 = obj.getString("produtoDto");


                        produtoDto.setFoto(foto);
                        produtoDto.setTipo(tipo);
                        produtoDto.setMarca(marca);
                        produtoDto.setMedida(medida);
                        produtoDto.setUnMedida(unMedida);
                        mercadoProdutoDto.setPreco(preco);
                        mercadoProdutoDto.setProdutoDto(produtoDto);
                        mExampleList.add(mercadoProdutoDto);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mMercadoProdutoAdapter = new MercadoProdutoAdapter(ProdutoActivity.this,mExampleList);
                mRecyclerView.setAdapter(mMercadoProdutoAdapter);

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
