package com.mylearn.sprbootfunction.BigDecimalTest;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecimalTest {
    public static void main(String[] args) {

        BigDecimal cur = new BigDecimal(1980.28);
        BigDecimal total = new BigDecimal(161000);


        BigDecimal divide = cur.multiply(new BigDecimal(100)).divide(total, 2,RoundingMode.HALF_UP);
        System.out.println(divide);


    }
}
