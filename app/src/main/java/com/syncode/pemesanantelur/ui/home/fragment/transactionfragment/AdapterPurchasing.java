package com.syncode.pemesanantelur.ui.home.fragment.transactionfragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.order.Order;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.ui.maps.MapsActivity;

import java.util.List;

public class AdapterPurchasing extends RecyclerView.Adapter<AdapterPurchasing.ViewHolder> {

    private List<Order> orderList;


    public AdapterPurchasing(List<Order> orderList) {
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchasing_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txtPrice.setText(String.valueOf(order.getPriceAll()));
        holder.txtName.setText(order.getProductName());
        Glide.with(holder.itemView.getContext()).load(ApiClient.BASE_URL_IMAGE + order.getImage()).into(holder.imgProduct);
        holder.container.setOnClickListener(view -> {
            Intent intent = new Intent(holder.container.getContext(), MapsActivity.class);
            holder.container.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtPrice;
        private ImageView imgProduct;
        private CardView container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            container = itemView.findViewById(R.id.container);
        }
    }

}
