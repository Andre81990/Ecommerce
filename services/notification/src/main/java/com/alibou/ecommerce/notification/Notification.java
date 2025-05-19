package com.alibou.ecommerce.notification;

import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;

    public Notification(Builder builder) {
        this.id = id;
        this.type = type;
        this.notificationDate = notificationDate;
        this.orderConfirmation = orderConfirmation;
        this.paymentConfirmation = paymentConfirmation;
    }

    public static class Builder{

        private String id;
        private NotificationType type;
        private LocalDateTime notificationDate;
        private OrderConfirmation orderConfirmation;
        private PaymentConfirmation paymentConfirmation;


        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder type(NotificationType type) {
            this.type = type;
            return this;
        }

        public Builder notificationDate(LocalDateTime notificationDate) {
            this.notificationDate = notificationDate;
            return this;
        }

        public Builder orderConfirmation(OrderConfirmation orderConfirmation) {
            this.orderConfirmation = orderConfirmation;
            return this;
        }

        public Builder paymentConfirmation(PaymentConfirmation paymentConfirmation) {
            this.paymentConfirmation = paymentConfirmation;
            return this;
        }

        public Notification build() {
            return new Notification(this);
        }
    }

    // Metodo statico per ottenere un'istanza del Builder
    public static Builder builder() {
        return new Builder();
    }

    public String getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public OrderConfirmation getOrderConfirmation() {
        return orderConfirmation;
    }

    public PaymentConfirmation getPaymentConfirmation() {
        return paymentConfirmation;
    }



    }



