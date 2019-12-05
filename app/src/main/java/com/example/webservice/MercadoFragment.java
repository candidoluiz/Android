package com.example.webservice;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.MercadoAdapter;
import com.example.dto.MercadoDto;
import com.example.estaticas.Valores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MercadoFragment extends Fragment implements MercadoAdapter.OnItemClickListener {

    private Context context;
    private ArrayList<MercadoDto> mExampleList;
    private RequestQueue mRequestQueue;
    private RecyclerView mRecyclerView;
    private MercadoAdapter mMercadoAdapter;
    private int mercadoId;




    public MercadoFragment() {
        // Required empty public constructor
    }
    public MercadoFragment(Context context){
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_mercado, container, false);

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(context);
        mRecyclerView=v.findViewById(R.id.recycle_mercado_fragment);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        parseJSON();

        return  v;
    }

    private void parseJSON()
    {
        //String url=" http://www.mocky.io/v2/5db2443a350000b61bf54f1f";
        //String url=Valores.URL_MERCADO;
        String url = Valores.URL_CASA+"/Mercado/rest/ws/listarMercados";
        Log.v("TAG3","create");

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String url2=Valores.URL_CASA+"/Mercado";


                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        MercadoDto mercadoDto = new MercadoDto();
                        mercadoId = obj.getInt("mercadoId");
                        String bairro = obj.getString("bairro");
                        String nome = obj.getString("nome");
                        String foto = obj.getString("foto").substring(1);
                        foto = url2+foto;
                        //String foto = obj.getString("foto");
                        String cidade = obj.getString("cidade");


                        mercadoDto.setCidade(cidade);
                        mercadoDto.setMercadoId(mercadoId);
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

                mMercadoAdapter = new MercadoAdapter(context,mExampleList);
                mRecyclerView.setAdapter(mMercadoAdapter);
                mMercadoAdapter.setOnItemClickListener((MercadoAdapter.OnItemClickListener) context);


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

        Intent intent = new Intent(context,ProdutoActivity.class);
        MercadoDto mercadoDto = mExampleList.get(position);

        intent.putExtra("cod","2");
        intent.putExtra("foto",mercadoDto.getFoto());
        intent.putExtra("nome",mercadoDto.getNome());
        intent.putExtra("mercadoId",mercadoDto.getMercadoId());
        startActivity(intent);

    }
}
