package com.app.pd.ecommerce.controller;

import com.app.pd.ecommerce.dto.Purchase;
import com.app.pd.ecommerce.dto.PurchaseResponse;
import com.app.pd.ecommerce.service.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {
    CheckoutService checkoutService;

    CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    PurchaseResponse purchase(@RequestBody Purchase body) {
        return this.checkoutService.placeOrder(body);
    }

}
