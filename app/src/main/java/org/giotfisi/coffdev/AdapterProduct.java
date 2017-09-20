package org.giotfisi.coffdev;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.giotfisi.coffdev.Models.Product;

import java.util.List;


/**
 * Created by Axel on 16/09/2017.
 */

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder>{

    List<Product> products;

    public AdapterProduct(List<Product> products) {
        this.products = products;
    }

    @Override
    public AdapterProduct.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(products.get(position).getTitle());
        holder.description.setText(products.get(position).getDescription());
        holder.p_imagen.setImageResource(products.get(position).getImgProduct());
    }


    @Override
    public int getItemCount() {
        return products.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView title,description;
        private ImageView p_imagen;

        public ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.TitleProduct);
            description= itemView.findViewById(R.id.ProductDescription);
            p_imagen= itemView.findViewById(R.id.imageProduct);
        }

    }


}
