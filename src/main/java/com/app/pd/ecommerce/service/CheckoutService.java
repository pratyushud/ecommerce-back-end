package com.app.pd.ecommerce.service;

import com.app.pd.ecommerce.dto.PaymentInfo;
import com.app.pd.ecommerce.dto.Purchase;
import com.app.pd.ecommerce.dto.PurchaseResponse;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
    PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException;
}
