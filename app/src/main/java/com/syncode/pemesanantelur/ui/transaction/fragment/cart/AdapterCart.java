package com.syncode.pemesanantelur.ui.transaction.fragment.cart;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.cart.Cart;
import com.syncode.pemesanantelur.data.viewmodel.cart.CartViewModel;
import com.syncode.pemesanantelur.ui.order.OrderActivity;

import java.util.List;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolder> {

    private List<Cart> cartList;


    private CartViewModel cartViewModel;

    public AdapterCart(List<Cart> cartList, CartViewModel cartViewModel) {
        this.cartList = cartList;
        this.cartViewModel = cartViewModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cart cart = cartList.get(position);
        holder.checkProduct.setText(cart.getProductName());
        holder.txtAmmount.setText(String.valueOf(cart.getAmmount()));
        int subTotal = cart.getPrice() * cart.getAmmount();
        holder.txtTotal.setText(String.valueOf(subTotal));
        holder.btnTrash.setOnClickListener(view -> AsyncTask.execute(() -> cartViewModel.deleteCart(cart)));
        holder.btnBayar.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), OrderActivity.class);
            intent.putExtra("product", cart);
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button btnBayar, btnPlus, btnMinus;
        private ImageView productImage;
        private TextView txtAmmount;
        private TextView txtTotal;
        private CheckBox checkProduct;
        private ImageButton btnTrash;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnBayar = itemView.findViewById(R.id.btnPay);
            productImage = itemView.findViewById(R.id.imageProduct);
            txtAmmount = itemView.findViewById(R.id.txtAmount);
            txtTotal = itemView.findViewById(R.id.subTotal);
            checkProduct = itemView.findViewById(R.id.checkProduct);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnTrash = itemView.findViewById(R.id.btnTrash);
        }

        public int getAdapterPos() {
            return getAdapterPosition();
        }
    }

}
