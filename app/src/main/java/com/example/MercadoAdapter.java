package com.example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dto.MercadoDto;
import com.example.webservice.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MercadoAdapter extends RecyclerView.Adapter<MercadoAdapter.ExampleViewHolder> {

    private Context mContext;
    private ArrayList<MercadoDto> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }

    public MercadoAdapter(Context context, ArrayList<MercadoDto> exampleList)
    {
        mContext=context;
        mExampleList=exampleList;
    }


    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item,parent,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        MercadoDto currentItem = mExampleList.get(position);
        String imgUrl=currentItem.getFoto();
        String nome = currentItem.getNome();
        String bairro = currentItem.getBairro();

        holder.mTextView.setText(nome);
        holder.mBairro.setText(bairro);
        Picasso.get().load(imgUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }


    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
        public TextView mBairro;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagem_mercado);
            mTextView = itemView.findViewById(R.id.nome_mercado);
            mBairro = itemView.findViewById(R.id.bairro_mercado);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        mListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
