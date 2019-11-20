package com.example.json;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.adapter.SpinnerAdapter;
import com.example.dto.MercadoDto;
import com.example.dto.MercadoProdutoDto;
import com.example.dto.ProdutoDto;
import com.example.estaticas.Valores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProdutosJson {

    private ArrayList<MercadoProdutoDto> mExampleList = new ArrayList<>();
    private SpinnerAdapter mSpinnerAdapter;
    private Context context;
    private RequestQueue mRequestQueue;

    public ProdutosJson(Context context){
        this.context= context;

    }

    public void retornarTodosProdutos()
    {
        String url = Valores.URL_CASA+"/Mercado/rest/ws/listarTodosProdutos";

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

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
