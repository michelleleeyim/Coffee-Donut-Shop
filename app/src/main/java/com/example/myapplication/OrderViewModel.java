package com.example.myapplication;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import code.Donut;
import code.Order;

public class OrderViewModel extends ViewModel {
    private MutableLiveData<Order> orderLiveData = new MutableLiveData<>();
    private Order currentOrder;

    public OrderViewModel() {
        currentOrder = new Order();
        orderLiveData.setValue(currentOrder);
    }

    public LiveData<Order> getOrder() {
        return orderLiveData;
    }

    public void addToCart(Donut donut) {
        currentOrder.add(donut);
        orderLiveData.setValue(currentOrder);
    }
}
