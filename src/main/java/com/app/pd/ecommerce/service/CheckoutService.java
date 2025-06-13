package com.app.pd.ecommerce.service;

import com.app.pd.ecommerce.dto.Purchase;
import com.app.pd.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {

    PurchaseResponse placeOrder(Purchase purchase);
}
