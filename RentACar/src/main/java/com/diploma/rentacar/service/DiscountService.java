package com.diploma.rentacar.service;

import com.diploma.rentacar.entity.Discount;
import com.diploma.rentacar.enums.DiscountTypeEnum;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiscountService {

    public Discount generateDiscount(DiscountTypeEnum discountType) {
        return Discount.builder()
                .discount(10)
                .endDate(LocalDate.now())
                .startDate(LocalDate.now().plusMonths(1))
                .discountType(discountType)
                .build();
    }
}
