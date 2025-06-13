package com.app.pd.ecommerce.dto;

import com.app.pd.ecommerce.entity.Address;
import com.app.pd.ecommerce.entity.Customer;
import com.app.pd.ecommerce.entity.Order;
import com.app.pd.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    Customer customer;
    Address shippingAddress;
    Address billingAddress;
    Order order;
    Set<OrderItem> orderItems;
}
