package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filter.TodosProdutosFilter;
import com.example.webservice.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dto.MercadoProdutoDto;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class TodosProdutosAdapter extends RecyclerView.Adapter<TodosProdutosAdapter.ExampleViewHolder> implements Filterable {

    private Context mContext;
    public ArrayList<MercadoProdutoDto> mExampleList, filterList;
    private TodosProdutosAdapter.OnItemClickListener mListener;
    private DecimalFormat df = new DecimalFormat("#,###.00");
    private TodosProdutosFilter filter;

    public TodosProdutosAdapter(Context mContext, ArrayList<MercadoProdutoDto> mExampleList) {
        this.mContext = mContext;
        this.mExampleList = mExampleList;
        this.filterList = mExampleList;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
        {
            filter = new TodosProdutosFilter(this, filterList);

        }
        return filter;

    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.lista_produto,parent,false);
        return new TodosProdutosAdapter.ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        MercadoProdutoDto currentItem = mExampleList.get(position);
        String foto = currentItem.getProdutoDto().getFoto();
        String tipo = currentItem.getProdutoDto().getTipo();
        String marca = currentItem.getProdutoDto().getMarca();
        String peso = currentItem.getProdutoDto().getMedida();
        String unMedida = currentItem.getProdutoDto().getUnMedida();
        String preco = df.format(currentItem.getPreco());
        String nome = currentItem.getMercadoDto().getNome();
        String cidade = currentItem.getMercadoDto().getCidade();
        String bairro = currentItem.getMercadoDto().getBairro();


        Picasso.get().load(foto).fit().centerInside().into(holder.mImageView);
        holder.txtTipoProduto.setText(tipo);
        holder.txtMarcaProduto.setText(marca);
        holder.txtPesoProduto.setText(peso);
        holder.txtUnMedidaProduto.setText(unMedida);
        holder.txtPrecoProduto.setText("R$ "+preco);
        holder.txtNomeMercado.setText(nome);
        holder.txtCidade.setText(cidade);
        holder.txtBairro.setText(bairro);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public interface OnItemClickListener{
    void onItemClick(int position);
}

    public void setOnItemClickListener(TodosProdutosAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView txtIdProduto;
        public TextView txtTipoProduto;
        public TextView txtMarcaProduto;
        public TextView txtPesoProduto;
        public TextView txtUnMedidaProduto;
        public TextView txtPrecoProduto;
        public TextView txtNomeMercado;
        public TextView txtCidade;
        public TextView txtBairro;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagem_produto);
            txtTipoProduto = itemView.findViewById(R.id.tipo_produto);
            txtMarcaProduto = itemView.findViewById(R.id.marca_produto);
            txtPesoProduto = itemView.findViewById(R.id.peso_produto);
            txtUnMedidaProduto = itemView.findViewById(R.id.unidade_medida_produto);
            txtPrecoProduto = itemView.findViewById(R.id.preco_produto);
            txtNomeMercado = itemView.findViewById(R.id.layout_lista_produto_nome_mercado);
            txtCidade = itemView.findViewById(R.id.layout_lista_produto_cidade);
            txtBairro = itemView.findViewById(R.id.layout_lista_produto_bairro);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mListener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}


