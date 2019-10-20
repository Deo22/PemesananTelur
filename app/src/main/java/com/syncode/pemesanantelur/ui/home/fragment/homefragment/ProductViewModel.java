package com.syncode.pemesanantelur.ui.home.fragment.homefragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.product.Product;
import com.syncode.pemesanantelur.data.network.repository.product.ProductRepository;

public class ProductViewModel extends ViewModel {
    private LiveData<Product> productLiveData;
    private ProductRepository productRepository;


    public ProductViewModel() {
        productRepository = new ProductRepository();
    }


    public void setProductLiveData() {
        productLiveData = productRepository.getProductData();
    }


    public LiveData<Product> getProductLiveData() {
        return productLiveData;
    }


}
