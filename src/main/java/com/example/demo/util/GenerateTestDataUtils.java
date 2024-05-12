package com.example.demo.util;

import com.example.demo.dao.model.Payment;
import java.util.List;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenerateTestDataUtils {

    public static List<Payment> generatePayments() {

        return IntStream.range(0, 4)
                .boxed()
                .flatMap(i -> IntStream.range(0, i + 1)
                        .mapToObj(j -> generatePayment("customerId-" + i)))
                .toList();
    }

    private static Payment generatePayment(String customerId) {
        Payment payment = new Payment();

        payment.setCustomerId(customerId);
        payment.setAmount(1.00D);

        return payment;
    }
}
