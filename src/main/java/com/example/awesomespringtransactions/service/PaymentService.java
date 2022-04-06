package com.example.awesomespringtransactions.service;


import java.io.FileNotFoundException;

import com.example.awesomespringtransactions.model.Payment;
import com.example.awesomespringtransactions.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void pay(Payment payment) throws FileNotFoundException {
        this.paymentRepository.save(payment);
        log.info("======> Payment Id: {}", payment.getId());
        throw new FileNotFoundException("asdfasd");
    }
}
