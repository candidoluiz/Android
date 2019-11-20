package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.filter.CustomFilter;
import com.example.webservice.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dto.MercadoProdutoDto;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MercadoProdutoAdapter extends RecyclerView.Adapter<MercadoProdutoAdapter.ExampleViewHolder> implements Filterable{

    private Context mContext;
    public ArrayList<MercadoProdutoDto> mExampleList, filterList;
    private MercadoProdutoAdapter.OnItemClickListener mListener;
    private DecimalFormat df = new DecimalFormat("#,###.00");
    private CustomFilter filter;

    @Override
    public Filter getFilter() {
        if (filter == null)
        {
            filter = new CustomFilter(this, filterList);

        }
        return filter;
    }


    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(MercadoProdutoAdapter.OnItemClickListener listener){
        this.mListener = listener;
    }

    public MercadoProdutoAdapter(Context mContext, ArrayList<MercadoProdutoDto> mExampleList) {
        this.mContext = mContext;
        this.mExampleList = mExampleList;
        this.filterList = mExampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.lista_produto,parent,false);
        return new ExampleViewHolder(v);
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


        Picasso.get().load(foto).fit().centerInside().into(holder.mImageView);
        holder.txtTipoProduto.setText(tipo);
        holder.txtMarcaProduto.setText(marca);
        holder.txtPesoProduto.setText(peso);
        holder.txtUnMedidaProduto.setText(unMedida);
        holder.txtPrecoProduto.setText("R$ "+preco);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView txtIdProduto;
        public TextView txtTipoProduto;
        public TextView txtMarcaProduto;
        public TextView txtPesoProduto;
        public TextView txtUnMedidaProduto;
        public TextView txtPrecoProduto;


        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagem_produto);
            txtTipoProduto = itemView.findViewById(R.id.tipo_produto);
            txtMarcaProduto = itemView.findViewById(R.id.marca_produto);
            txtPesoProduto = itemView.findViewById(R.id.peso_produto);
            txtUnMedidaProduto = itemView.findViewById(R.id.unidade_medida_produto);
            txtPrecoProduto = itemView.findViewById(R.id.preco_produto);

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
