package com.app.pd.ecommerce.service;

import com.app.pd.ecommerce.dao.CustomerDAO;
import com.app.pd.ecommerce.dto.Purchase;
import com.app.pd.ecommerce.dto.PurchaseResponse;
import com.app.pd.ecommerce.entity.Address;
import com.app.pd.ecommerce.entity.Customer;
import com.app.pd.ecommerce.entity.Order;
import com.app.pd.ecommerce.entity.OrderItem;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    CustomerDAO customerDAO;

    CheckoutServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        Customer customer = purchase.getCustomer();
        Order order = purchase.getOrder();
        Address shippingAddress = purchase.getShippingAddress();
        order.setShippingAddress(shippingAddress);
        Address billingAddress = purchase.getBillingAddress();
        order.setBillingAddress(billingAddress);
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(order::addOrderItem);
        String orderTrackingNumber = order.generateTrackingNumber();
        order.setStatus("Ordered");
        customer.addOrder(order);
        PurchaseResponse resp = new PurchaseResponse();
        resp.setOrderTrackingNumber(orderTrackingNumber);
        this.customerDAO.save(customer);
        return resp;
    }
}
