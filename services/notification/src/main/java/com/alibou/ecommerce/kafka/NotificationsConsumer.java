package com.alibou.ecommerce.kafka;

import com.alibou.ecommerce.email.EmailService;
import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.payment.PaymentConfirmation;
import com.alibou.ecommerce.notification.Notification;
import com.alibou.ecommerce.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.alibou.ecommerce.notification.NotificationType.ORDER_CONFIRMATION;
import static com.alibou.ecommerce.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j

public class NotificationsConsumer {

    /*
    * è un consumer Kafka che si occupa di ascoltare eventi relativi
    * a pagamenti completati e ordini confermati, e reagisce salvando
    * una notifica e inviando un'email al cliente.
    *
    * */

    private static final Logger logger = LoggerFactory.getLogger(NotificationsConsumer.class);


    private final NotificationRepository repository;
    private final EmailService emailService;

    public NotificationsConsumer(NotificationRepository repository, EmailService emailService) {
        this.repository = repository;
        this.emailService = emailService;
    }

    /*
    *  Kafka Listener è un meccanismo fornito da framework come Spring Kafka (il più comune in ambiente Java) per ricevere e processare messaggi provenienti da uno o più topic Kafka.
    *per ascoltare quando nuovi messaggi vengono pubblicati. Quando un messaggio arriva al topic a cui il listener è sottoscritto
    *
    * Salva notifica + Invia email di pagamento riuscito

     * */
    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        logger.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        repository.save(

                //Logga il messaggio

                //Salva una notifica nel database

//                Invia un’email al cliente con conferma del pagamento


                Notification.builder()
                        .type(PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        //mandare un email al cliente

        //Salva notifica + Invia email di conferma ordine
        var customerName = paymentConfirmation.customerFirstname() + " " + paymentConfirmation.customerLastname();
        emailService.sendPaymentSuccessEmail(
                paymentConfirmation.customerEmail(),
                customerName,
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        logger.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        repository.save(
                Notification.builder()
                        .type(ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build()
        );
        var customerName = orderConfirmation.customer().firstname() + " " + orderConfirmation.customer().lastname();
        emailService.sendOrderConfirmationEmail(
                orderConfirmation.customer().email(),
                customerName,
                orderConfirmation.totalAmount(),
                orderConfirmation.orderReference(),
                orderConfirmation.products()
        );


    }
}
