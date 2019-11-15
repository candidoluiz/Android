package com.example.webservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.adapter.MercadoAdapter;
import com.example.adapter.SpinnerAdapter;
import com.example.dto.MercadoDto;
import com.example.estaticas.Valores;
import com.example.json.CidadeJson;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MercadoAdapter.OnItemClickListener {

    private RecyclerView mRecyclerView;
    private MercadoAdapter mMercadoAdapter;
    private SpinnerAdapter mSpinnerAdapter;
    private ArrayList<MercadoDto> mExampleList;
    private RequestQueue mRequestQueue;
    private FloatingActionButton mFab;
    private Spinner mSpinner;
    private  Activity activity;
    private int mercadoId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Meeting Market");
         activity = this;

        mRecyclerView=findViewById(R.id.recycle_mercado);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mFab = findViewById(R.id.fab_id);
        mSpinner = findViewById(R.id.main_spinner_cidade);

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);


        CidadeJson city = new CidadeJson(MainActivity.this,mSpinner);
        city.retornarCidades();
        parseJSON();


        Scanear();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!=null)
        {
            if ((result.getContents()==null))
            {
                Toast.makeText(this, "Cancelado", Toast.LENGTH_LONG).show();
            }else {
                Intent intent = new Intent(MainActivity.this,ProdutoActivity.class);
                intent.putExtra("codBarra",result.getContents());
                intent.putExtra("cod","3");
                startActivity(intent);
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this,ProdutoActivity.class);
        intent.putExtra("cod","1");
        startActivity(intent);
        //return super.onOptionsItemSelected(item);
        return true;
    }

    private void Scanear() {
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator integrator = new IntentIntegrator(activity);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.setPrompt("Camera Scan");
                integrator.setCameraId(0);
                integrator.initiateScan();

            }
        });
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




            mMercadoAdapter = new MercadoAdapter(MainActivity.this,mExampleList);
            mRecyclerView.setAdapter(mMercadoAdapter);
           // mSpinnerAdapter = new SpinnerAdapter(MainActivity.this,mExampleList);
            //mSpinner.setAdapter(mSpinnerAdapter);
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

        intent.putExtra("cod","2");
        intent.putExtra("foto",mercadoDto.getFoto());
        intent.putExtra("nome",mercadoDto.getNome());
        intent.putExtra("mercadoId",mercadoDto.getMercadoId());
        startActivity(intent);

    }
}

