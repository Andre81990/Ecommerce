package com.alibou.ecommerce.payment;

public record PayReq(

     Integer id,


     PaymentMethod paymentMethod,

     Integer orderId,

     Customer customer


) {
}
