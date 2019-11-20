package com.example.webservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.TodosProdutosAdapter;
import com.example.dto.MercadoDto;
import com.example.dto.MercadoProdutoDto;
import com.example.dto.ProdutoDto;
import com.example.estaticas.Valores;
import com.example.json.ProdutosJson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MercadoProdutoActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private TodosProdutosAdapter mTodosProdutosAdapter;
    private ArrayList<MercadoProdutoDto> mExampleList;
    private RequestQueue mRequestQueue;
    private int mercadoId = -1;
    private String url = Valores.URL_CASA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mercado_produto);

        setTitle("LISTA DE PRODUTOS");
        Intent intent =getIntent();
        String barCode = intent.getStringExtra("codBarra");
        if(barCode==null)
        {
            url = url+"/Mercado/rest/ws/listarTodosProdutos/";
        }else
        {
            url = url+"/Mercado/rest/ws/listarCodBarra/"+barCode;
        }


        mRecyclerView = findViewById(R.id.recycle_mercado_produto);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);

        //ProdutosJson produtosJson = new ProdutosJson(this, mExampleList,mRequestQueue,mRecyclerView, mTodosProdutosAdapter);
       // produtosJson.retornarTodosProdutos();
       // mTodosProdutosAdapter = new TodosProdutosAdapter(this,mExampleList);
        retornarTodosProdutos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search,menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        //SearchView searchView = (SearchView)findViewById(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                mTodosProdutosAdapter.getFilter().filter(query);


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                mTodosProdutosAdapter.getFilter().filter(newText);

                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.search)
        {
            //Toast.makeText(this, "Seeting", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void retornarTodosProdutos()
    {
        //String url = Valores.URL_CASA+"/Mercado/rest/ws/listarTodosProdutos";

        JsonArrayRequest request = new  JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                String url2= Valores.URL_CASA+"/Mercado";
                for (int i= 0; i< response.length(); i++)
                {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        MercadoProdutoDto mercadoProdutoDto = new MercadoProdutoDto();
                        ProdutoDto produtoDto = new ProdutoDto();
                        MercadoDto mercadoDto = new MercadoDto();

                        String foto = obj.getJSONObject("produtoDto").getString("foto").substring(1);
                        foto = url2+foto;

                        String tipo = obj.getJSONObject("produtoDto").getString("tipo");
                        String medida = obj.getJSONObject("produtoDto").getString("medida");
                        String unMedida = obj.getJSONObject("produtoDto").getString("unMedida");
                        String marca = obj.getJSONObject("produtoDto").getString("marca");
                        String nome = obj.getJSONObject("mercadoDto").getString("nome");
                        String bairro = obj.getJSONObject("mercadoDto").getString("bairro");
                        String cidade = obj.getJSONObject("mercadoDto").getString("cidade");
                        double preco = Double.parseDouble(obj.getString("preco"));

                        mercadoDto.setNome(nome);
                        mercadoDto.setBairro(bairro);
                        mercadoDto.setCidade(cidade);
                        produtoDto.setFoto(foto);
                        produtoDto.setTipo(tipo);
                        produtoDto.setMarca(marca);
                        produtoDto.setMedida(medida);
                        produtoDto.setUnMedida(unMedida);
                        mercadoProdutoDto.setPreco(preco);
                        mercadoProdutoDto.setProdutoDto(produtoDto);
                        mercadoProdutoDto.setMercadoDto(mercadoDto);
                        mExampleList.add(mercadoProdutoDto);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                mTodosProdutosAdapter = new TodosProdutosAdapter(MercadoProdutoActivity.this, mExampleList);
                mRecyclerView.setAdapter(mTodosProdutosAdapter);

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
