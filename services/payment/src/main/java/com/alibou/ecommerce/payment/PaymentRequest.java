package com.alibou.ecommerce.payment;

import java.math.BigDecimal;


//classe che contiene dati
/*
*
* dati di richiesta quando
* si vuole creare o aggiornare
*un pagamento
* */
public record PaymentRequest(
    Integer id,
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Integer orderId,
    //Un riferimento all'ordine (potrebbe essere utile per la tracciabilità).


    /*
     Non mappa orderReference e customer direttamente
      all'entity Payment come è attualmente definita.
      Potrebbe esserci una ragione per questo (ad esempio,
       orderReference potrebbe non essere persistito direttamente
        nella tabella payment, o le informazioni sul cliente
        sono gestite in un'altra relazione).
    * */
    String orderReference,
    Customer customer
) {
}
