package com.alibou.ecommerce.email;

import lombok.Getter;



//è un enum Java che definisce i tipi di email che il sistema può inviare, associando a ciascuno:
public enum EmailTemplates {

    PAYMENT_CONFIRMATION("payment-confirmation.html", "Payment successfully processed"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order confirmation")
    ;
//Un file template HTML (per generare il contenuto dell'email con Thymeleaf)
    @Getter
    private final String template;
    @Getter
    private final String subject;


    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }


    public String getTemplate() {
        return template;
    }

    public String getSubject() {
        return subject;
    }
}
