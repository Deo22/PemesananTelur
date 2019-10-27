package com.syncode.pemesanantelur.ui.home.fragment.homefragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.product.Product;
import com.syncode.pemesanantelur.data.model.product.ProductEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.ui.home.HomeActivity;
import com.syncode.pemesanantelur.ui.order.OrderActivity;
import com.syncode.pemesanantelur.utils.SwitchActivity;


public class HomeFragment extends Fragment implements Observer<Product> {


    private RecyclerView recyclerView;
    private ProductViewModel productViewModel;
    private HomeAdapter homeAdapter;
    private ProgressBar progressBar;
    private ImageView imgProduct;
    private TextView txtTitle, txtPrice, txtDesc;

    private CardView cardContainer;

    private Button btnBuy;

    private ProductEntity productEntity;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressBar);
        imgProduct = view.findViewById(R.id.imgProduct);
        txtTitle = view.findViewById(R.id.txtTitle);
        txtPrice = view.findViewById(R.id.txtPrice);
        txtDesc = view.findViewById(R.id.txtDesc);
        btnBuy = view.findViewById(R.id.btn_buy);
        cardContainer = view.findViewById(R.id.cardView);
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProductLiveData().observe(this, this);
        progressBar.setVisibility(View.VISIBLE);
        btnBuy.setOnClickListener(view1 -> {
            SwitchActivity.mainSwitch(getContext(), OrderActivity.class, productEntity, "product");
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onChanged(Product product) {
        if (product.getListProduct().size() > 0) {
            if (product.getListProduct().size() == 1) {
                String idProduct = product.getListProduct().get(0).getIdProduct();
                String image = product.getListProduct().get(0).getImage();
                String title = product.getListProduct().get(0).getProductName();
                String desc = product.getListProduct().get(0).getDesc();
                int price = product.getListProduct().get(0).getHarga();
                String type = product.getListProduct().get(0).getType();
                Glide.with(this).load(ApiClient.BASE_URL_IMAGE + image).into(imgProduct);
                txtTitle.setText(title);
                txtDesc.setText(desc);
                txtPrice.setText("Rp." + String.format("%,d", price) + "/Peti");
                cardContainer.setVisibility(View.VISIBLE);
                productEntity = new ProductEntity(idProduct, title, type, price, "", image);
            } else {
                homeAdapter = new HomeAdapter(product.getListProduct(), getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                cardContainer.setVisibility(View.GONE);
            }
            progressBar.setVisibility(View.GONE);
        }
    }
}
