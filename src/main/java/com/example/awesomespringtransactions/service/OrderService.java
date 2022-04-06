package com.example.awesomespringtransactions.service;


import java.io.FileNotFoundException;

import com.example.awesomespringtransactions.model.Order;
import com.example.awesomespringtransactions.model.Payment;
import com.example.awesomespringtransactions.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final PaymentService paymentService;
    private final OrderRepository orderRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = FileNotFoundException.class)
    public void placeOrder(Order order) throws FileNotFoundException {
        this.orderRepository.save(order);
        log.info("======> Order Id: {}", order.getId());
        Payment payment = Payment.builder()
            .price(order.getTotalPrice())
            .orderId(order.getId())
            .userId(order.getUserId())
            .build();
        this.paymentService.pay(payment);
    }
}
