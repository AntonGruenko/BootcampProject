package com.example.bootcampproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<Product> productList;

    public CustomRecyclerViewAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.productList = productList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item, parent, false);
        return new CustomHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product product = productList.get(position);
        ((CustomHolder) holder).productName.setText(product.getName());
        ((CustomHolder) holder).calories.append("\n" + product.getKcal());
        ((CustomHolder) holder).proteins.append("\n" + product.getProteins());
        ((CustomHolder) holder).fats.append("\n" + product.getFats());
        ((CustomHolder) holder).carbs.append("\n" + product.getCarbs());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class CustomHolder extends RecyclerView.ViewHolder{
        private TextView productName, calories, proteins, fats, carbs;

        public CustomHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.product_name);
            calories = itemView.findViewById(R.id.calories_text);
            proteins = itemView.findViewById(R.id.proteins_text);
            fats = itemView.findViewById(R.id.fats_text);
            carbs = itemView.findViewById(R.id.carbs_text);
        }
    }
}

