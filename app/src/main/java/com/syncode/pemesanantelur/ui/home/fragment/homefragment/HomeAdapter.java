package com.syncode.pemesanantelur.ui.home.fragment.homefragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.product.ProductEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.ui.order.OrderActivity;
import com.syncode.pemesanantelur.utils.SwitchActivity;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<ProductEntity> productList;

    private Context context;

    HomeAdapter(List<ProductEntity> productList, Context context) {
        this.productList = productList;
        this.context = context;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        ProductEntity product = productList.get(position);
        holder.txtPrice.setText("Rp." + String.format("%,d", product.getHarga())+" /Peti");
        holder.txtQuality.setText(product.getQuality());
        holder.txtName.setText(product.getProductName());
        String imageUrl = ApiClient.BASE_URL_IMAGE + product.getImage();
        System.out.println(imageUrl);
        Glide.with(context).load(imageUrl).into(holder.imgProduct);
        holder.btnBuy.setOnClickListener(view -> SwitchActivity.mainSwitch(holder.itemView.getContext(), OrderActivity.class, product, "product"));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtPrice;
        private TextView txtQuality;
        private ImageView imgProduct;
        private Button btnBuy;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imageProduct);
            txtQuality = itemView.findViewById(R.id.txtQuality);
            btnBuy = itemView.findViewById(R.id.btn_buy);
        }
    }
}
