package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.MercadoAdapter;
import com.example.dto.MercadoDto;
import com.example.estaticas.Valores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MercadoAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private MercadoAdapter mMercadoAdapter;
    private ArrayList<MercadoDto> mExampleList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView=findViewById(R.id.recycle_mercado);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        Log.v("TAG3","create");


        parseJSON();

    }
/*
    private void parseJSON()
    {
       // String url=" http://www.mocky.io/v2/5db2443a350000b61bf54f1f";
        String url="http://10.10.85.165:8080/Mercado/rest/ws/listarMercados";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("MercadoDto");

                    for (int i=0;i<jsonArray.length();i++)
                    {
                        String juntar ="http://10.10.85.165:8080/Mercado";//ip do servidor

                        JSONObject mercados = jsonArray.getJSONObject(i);
                        int id = mercados.getInt("mercadoId");
                        String nome = mercados.getString("nome");
                        String ponto = mercados.getString("foto").substring(1);
                        String image = juntar+ponto;
                        String bairro = mercados.getString("bairro");
                        String numero = mercados.getString("numero");
                        String rua = mercados.getString("rua");
                        String cidade = mercados.getString("cidade");
                        String uf = mercados.getString("uf");

                        mExampleList.add(new MercadoDto(id,nome,rua,bairro,numero,image,cidade,uf));
                    }
                    mMercadoAdapter = new MercadoAdapter(MainActivity.this,mExampleList);
                    mRecyclerView.setAdapter(mMercadoAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
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
*/
private void parseJSON()
{
    // String url=" http://www.mocky.io/v2/5db2443a350000b61bf54f1f";
    //String url="http://10.10.85.170:8080/Mercado/rest/ws/listarMercados";
     String url = Valores.URL+"/Mercado/rest/ws/listarMercados";
    Log.v("TAG3","create");

    JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            String url2=Valores.URL+"/Mercado";


            for (int i = 0; i < response.length(); i++)
            {
                try {
                    JSONObject obj = response.getJSONObject(i);
                    MercadoDto mercadoDto = new MercadoDto();
                    String bairro = obj.getString("bairro");
                    String nome = obj.getString("nome");
                    String foto = obj.getString("foto").substring(1);
                     foto = url2+foto;

                    mercadoDto.setNome(nome);
                    mercadoDto.setFoto(foto);
                    mercadoDto.setBairro(bairro);
                    Log.v("TAG2",foto);

                    mExampleList.add(mercadoDto);


                }

                catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            mMercadoAdapter = new MercadoAdapter(MainActivity.this,mExampleList);
            mRecyclerView.setAdapter(mMercadoAdapter);
            mMercadoAdapter.setOnItemClickListener(MainActivity.this);

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();

        }
    });
    mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this,ProdutoActivity.class);
        MercadoDto mercadoDto = mExampleList.get(position);

        intent.putExtra("nome",mercadoDto.getFoto());
        intent.putExtra("mercadoId",mercadoDto.getMercadoId());
        startActivity(intent);

    }
}

