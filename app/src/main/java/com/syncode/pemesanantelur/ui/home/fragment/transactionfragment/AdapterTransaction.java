package com.syncode.pemesanantelur.ui.home.fragment.transactionfragment;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.order.Order;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.utils.SwitchActivity;

import java.util.List;

public class AdapterTransaction extends RecyclerView.Adapter<AdapterTransaction.ViewHolder> {

    private List<Order> orderList;


    AdapterTransaction(List<Order> orderList) {
        this.orderList = orderList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchasing_row, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txtPrice.setText("Rp." + String.format("%,d", order.getPriceAll()));
        holder.txtName.setText(order.getProductName());
        holder.txtAmmount.setText(order.getCountOrder() + " Peti");
        Glide.with(holder.itemView.getContext()).load(ApiClient.BASE_URL_IMAGE + order.getImage()).into(holder.imgProduct);
        holder.container.setOnClickListener(view -> SwitchActivity.mainSwitch(holder.itemView.getContext(), TransactionDetailActivity.class, order, "order"));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName, txtPrice, txtAmmount;
        private ImageView imgProduct;
        private ConstraintLayout container;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtTitle);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            container = itemView.findViewById(R.id.container);
            txtAmmount = itemView.findViewById(R.id.txtAmmount);
        }
    }

}
