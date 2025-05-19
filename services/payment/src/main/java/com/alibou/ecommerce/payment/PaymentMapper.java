package com.alibou.ecommerce.payment;

import org.springframework.stereotype.Service;



//lavorano insieme per gestire le informazioni relative
// ai pagamenti all'interno della tua applicazione e-commerce


/*
* Questa è una classe di servizio responsabile della mappatura
* tra l'oggetto PaymentRequest (che contiene i dati in arrivo)
*  e l'oggetto Payment (l'entity che verrà persistita nel database).
*
* */
@Service
public class PaymentMapper {
/*
  public Payment toPayment(PaymentRequest request) {
    if (request == null) {
      return null;
    }
    return Payment.builder()
        .id(request.id())
        .paymentMethod(request.paymentMethod())
        .amount(request.amount())
        .orderId(request.orderId())
        .build();
  }
  */
  public Payment toPayment(PaymentRequest request)
  {
    if (request==null)
    {return  null;}
    return Payment.builder()
            .id(request.id())
            .paymentMethod(request.paymentMethod())
            .amount(request.amount())
            .orderId(request.orderId())
            .build();

  }

}
