    package com.example.intel.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<ProductDetails> productList;
    OnClickListener onClickListener;

    public interface OnClickListener {
        void onLayoutClick(int position);
    }

    public ProductAdapter(Context mContext, ArrayList<ProductDetails> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    public void setProductList(ArrayList<ProductDetails> productList) {
        this.productList = productList;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.productlist,null);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ProductDetails item=productList.get(position);
        holder.email.setText(item.getEmail());
        holder.phone.setText(item.getMobile());
        holder.name.setText(item.getName());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,phone,email;
        LinearLayout mLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            mLayout= (LinearLayout) itemView.findViewById(R.id.layout3);
            name=(TextView)itemView.findViewById(R.id.namelist_tv);
            email=(TextView)itemView.findViewById(R.id.emaillist_tv);
            phone=(TextView)itemView.findViewById(R.id.phonelist_tv);

            mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(onClickListener!=null)
                        onClickListener.onLayoutClick(getAdapterPosition());
                    return false;
                }
            });

        }
    }
}
