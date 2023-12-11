package com.example.order.controller.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Customer {
    @JsonProperty("id")
    int customerId;
    String customerName;

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
