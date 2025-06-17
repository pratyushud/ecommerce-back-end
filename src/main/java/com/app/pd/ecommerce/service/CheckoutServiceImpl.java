package com.app.pd.ecommerce.service;

import com.app.pd.ecommerce.dao.CustomerDAO;
import com.app.pd.ecommerce.dto.PaymentInfo;
import com.app.pd.ecommerce.dto.Purchase;
import com.app.pd.ecommerce.dto.PurchaseResponse;
import com.app.pd.ecommerce.entity.Address;
import com.app.pd.ecommerce.entity.Customer;
import com.app.pd.ecommerce.entity.Order;
import com.app.pd.ecommerce.entity.OrderItem;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    CustomerDAO customerDAO;



    CheckoutServiceImpl(CustomerDAO customerDAO,
                        @Value("${stripe.key.secret}") String secretKey) {
        this.customerDAO = customerDAO;
        Stripe.apiKey = secretKey;  //add the stripe secret key
    }

    @Transactional
    @Override
    public PurchaseResponse placeOrder(Purchase purchase) {
        Customer customer = customerDAO.findByEmail(purchase.getCustomer().getEmail());
        if (customer == null) {
            customer = purchase.getCustomer();
        }

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

    @Override
    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException {
        List<String> paymentMethodType = new ArrayList<>();
        paymentMethodType.add("card");

        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("payment_method_types", paymentMethodType);

        return PaymentIntent.create(params);
    }
}
